-- [[mert-utils-io-serialize.lua]]

local u = {}

-- Use a metatable to lazily load mert-lua-utils
local mt = {
    __index = function(t, key)
        if key == "u" then
            local utils = require("mert-lua-utils")
            -- rawset(t, "u", utils)
            return utils
        end
    end
}

setmetatable(u, mt)

-- Safe for any encoding: turns non-ASCII bytes into \xHH escapes
local function escape_lua_string(s)
  -- id:: ae97444d-e212-48f3-b387-ccf93415980b
  -- date:: 20260430
	-- [grok](https://grok.com/share/bGVnYWN5_0b41f0d1-abd1-4c25-902b-17343b7363ed)
  if type(s) ~= "string" then
    return tostring(s)
  end

  local parts = { '"' }
  for i = 1, #s do
    local byte = s:byte(i)
    if byte == 34 then          -- "
      parts[#parts + 1] = '\\"'
    elseif byte == 92 then      -- \
      parts[#parts + 1] = '\\\\'
    elseif byte == 10 then      -- \n
      parts[#parts + 1] = '\\n'
    elseif byte == 13 then      -- \r
      parts[#parts + 1] = '\\r'
    elseif byte == 9 then       -- \t
      parts[#parts + 1] = '\\t'
    elseif byte >= 32 and byte <= 126 then
      parts[#parts + 1] = string.char(byte)
    else
      -- Everything else (including Turkish UTF-8 bytes) → \xHH
      parts[#parts + 1] = string.format('\\x%02x', byte)
    end
  end
  parts[#parts + 1] = '"'
  return table.concat(parts)
end

local function serialize(o, indent, func_registry, sort_keys)
  -- id:: e23f8dbd-c420-4321-b8e2-571b3d94e3af
  -- [grok: efficient serialize](https://grok.com/share/bGVnYWN5_b61f6779-28dd-49fe-be2a-795b38cf27df)
  -- mplm: [[20250502-serialize-w-fn-registry-04.lua]]
  indent = indent or ""
  sort_keys = sort_keys ~= false -- Default to sorting keys unless explicitly disabled
  func_registry = func_registry or {}

  -- Create reverse lookup for functions (function -> name)
  local func_to_name = {}
  for name, func in pairs(func_registry) do
    func_to_name[func] = name
  end

  -- Stack for iterative processing
  local stack = {{obj = o, indent = indent, first = true}}
  local output = {} -- Collects string fragments
  local output_len = 0

  while #stack > 0 do
    local state = stack[#stack]
    local obj, current_indent, first = state.obj, state.indent, state.first

    if first then
      -- First visit to this object
      state.first = false

      if type(obj) == "number" then
        output_len = output_len + 1
        output[output_len] = tostring(obj)
        -- Add comma if this is not the last element in the parent table
        if #stack > 1 and stack[#stack - 1].current_key then
          output_len = output_len + 1
          output[output_len] = ",\n"
        end
        stack[#stack] = nil
      -- elseif type(obj) == "string" then
      --   output_len = output_len + 1
      --   output[output_len] = string.format("%q", obj)
      --   -- Add comma if this is not the last element in the parent table
      --   if #stack > 1 and stack[#stack - 1].current_key then
      --     output_len = output_len + 1
      --     output[output_len] = ",\n"
      --   end
      --   stack[#stack] = nil
			-- [grok](https://grok.com/share/bGVnYWN5_0b41f0d1-abd1-4c25-902b-17343b7363ed)
      elseif type(obj) == "string" then
        output_len = output_len + 1
        output[output_len] = escape_lua_string(obj)   -- ← NEW
        -- Add comma if this is not the last element in the parent table
        if #stack > 1 and stack[#stack - 1].current_key then
          output_len = output_len + 1
          output[output_len] = ",\n"
        end
        stack[#stack] = nil
      elseif type(obj) == "boolean" then
        output_len = output_len + 1
        output[output_len] = obj and "true" or "false"
        -- Add comma if this is not the last element in the parent table
        if #stack > 1 and stack[#stack - 1].current_key then
          output_len = output_len + 1
          output[output_len] = ",\n"
        end
        stack[#stack] = nil
      elseif type(obj) == "function" then
        local name = func_to_name[obj]
        output_len = output_len + 1
        output[output_len] = name and "f." .. name or "nil"
        -- Add comma if this is not the last element in the parent table
        if #stack > 1 and stack[#stack - 1].current_key then
          output_len = output_len + 1
          output[output_len] = ",\n"
        end
        stack[#stack] = nil
      elseif type(obj) == "table" then
        -- Initialize table processing
        output_len = output_len + 1
        output[output_len] = "{\n"
        state.keys = {}
        state.key_index = 1

        -- Collect keys
        for k in pairs(obj) do
          state.keys[#state.keys + 1] = k
        end

        -- Sort keys if requested
        if sort_keys then
          table.sort(state.keys, function(a, b)
            return tostring(a) < tostring(b)
          end)
        end

        -- Start processing the first key
        state.current_key = state.keys[1]
      else
        output_len = output_len + 1
        output[output_len] = "nil"
        -- Add comma if this is not the last element in the parent table
        if #stack > 1 and stack[#stack - 1].current_key then
          output_len = output_len + 1
          output[output_len] = ",\n"
        end
        stack[#stack] = nil
      end
    elseif state.current_key then
      -- Processing table entries
      local k = state.current_key
      local serialized_key = type(k) == "number" and tostring(k) or serialize(k, current_indent, func_registry, sort_keys)
      output_len = output_len + 1
      output[output_len] = current_indent .. "  [" .. serialized_key .. "] = "
      -- Push the value onto the stack
      stack[#stack + 1] = {obj = obj[k], indent = current_indent .. "  ", first = true}
      state.key_index = state.key_index + 1
      state.current_key = state.keys[state.key_index]
    else
      -- Table is fully processed
      output_len = output_len + 1
      output[output_len] = current_indent .. "}"
      -- Add comma if this table is nested and not the last element
      if #stack > 1 and stack[#stack - 1].current_key then
        output_len = output_len + 1
        output[output_len] = ",\n"
      else
        output_len = output_len + 1
        output[output_len] = "\n"
      end
      stack[#stack] = nil
    end
  end

  return table.concat(output)
end

local function serialize_20250911(o, indent, func_registry, sort_keys)
  -- id:: 3d36c9b1-070e-4e01-b269-73b37cb6511a
  -- date:: 20260202
  -- [grok: efficient serialize](https://grok.com/share/bGVnYWN5_b61f6779-28dd-49fe-be2a-795b38cf27df)
  -- mplm: [[20250502-serialize-w-fn-registry-03.lua]]
  indent = indent or ""
  sort_keys = sort_keys ~= false -- Default to sorting keys unless explicitly disabled
  func_registry = func_registry or {}
  
  -- Create reverse lookup for functions (function -> name)
  local func_to_name = {}
  for name, func in pairs(func_registry) do
    func_to_name[func] = name
  end

  -- Stack for iterative processing
  local stack = {{obj = o, indent = indent, first = true}}
  local output = {} -- Collects string fragments
  local output_len = 0

  while #stack > 0 do
    local state = stack[#stack]
    local obj, current_indent, first = state.obj, state.indent, state.first

    if first then
      -- First visit to this object
      state.first = false

      if type(obj) == "number" then
        output_len = output_len + 1
        output[output_len] = tostring(obj)
        stack[#stack] = nil
      elseif type(obj) == "string" then
        output_len = output_len + 1
        output[output_len] = string.format("%q", obj)
        stack[#stack] = nil
      elseif type(obj) == "boolean" then
        output_len = output_len + 1
        output[output_len] = obj and "true" or "false"
        stack[#stack] = nil
      elseif type(obj) == "function" then
        local name = func_to_name[obj]
        output_len = output_len + 1
        output[output_len] = name and "fn_registry." .. name or "nil"
        stack[#stack] = nil
      elseif type(obj) == "table" then
        -- Initialize table processing
        output_len = output_len + 1
        output[output_len] = "{\n"
        state.keys = {}
        state.key_index = 1

        -- Collect keys
        for k in pairs(obj) do
          state.keys[#state.keys + 1] = k
        end

        -- Sort keys if requested
        if sort_keys then
          table.sort(state.keys, function(a, b)
            return tostring(a) < tostring(b)
          end)
        end

        -- Start processing the first key
        state.current_key = state.keys[1]
      else
        output_len = output_len + 1
        output[output_len] = "nil"
        stack[#stack] = nil
      end
    elseif state.current_key then
      -- Processing table entries
      local k = state.current_key
      local serialized_key = type(k) == "number" and tostring(k) or serialize(k, current_indent, func_registry, sort_keys)
      output_len = output_len + 1
      output[output_len] = current_indent .. "  [" .. serialized_key .. "] = "

      -- Push the value onto the stack
      stack[#stack + 1] = {obj = obj[k], indent = current_indent .. "  ", first = true, is_value = true}
      state.key_index = state.key_index + 1
      state.current_key = state.keys[state.key_index]
    else
      -- Table is fully processed
      output_len = output_len + 1
      output[output_len] = current_indent .. "}"
      stack[#stack] = nil
    end

    -- Add comma and newline after values (except for the last value in a table)
    if state.is_value and stack[#stack] and stack[#stack].current_key then
      output_len = output_len + 1
      output[output_len] = ",\n"
    end
  end

  return table.concat(output)
end

local function serialize_20250819(o, indent, func_registry)
  -- id:: a8ce3af6-0dbc-43d7-a1f9-d74b32b80f2b
  -- date:: 20260202
  -- pprv: local function serialize_20250813(o, indent, func_registry) || ((c9c98f95-faf3-4f45-bd05-a90d86bbcb58))
  -- [grok: efficient serialize](https://grok.com/share/bGVnYWN5_b61f6779-28dd-49fe-be2a-795b38cf27df)
	-- mplm: [[20250502-serialize-w-fn-registry-01.lua]]
  indent = indent or ""
  sort_keys = sort_keys ~= false -- Default to sorting keys unless explicitly disabled
  func_registry = func_registry or {}

  -- Create reverse lookup for functions (function -> name)
  local func_to_name = {}
  for name, func in pairs(func_registry) do
    func_to_name[func] = name
  end

  -- Stack for iterative processing
  local stack = {{obj = o, indent = indent, first = true}}
  local output = {} -- Collects string fragments
  local output_len = 0

  while #stack > 0 do
    local state = stack[#stack]
    local obj, current_indent, first = state.obj, state.indent, state.first

    if first then
      -- First visit to this object
      state.first = false

      if type(obj) == "number" then
        output_len = output_len + 1
        output[output_len] = tostring(obj)
        stack[#stack] = nil
      elseif type(obj) == "string" then
        output_len = output_len + 1
        output[output_len] = string.format("%q", obj)
        stack[#stack] = nil
      elseif type(obj) == "boolean" then
        output_len = output_len + 1
        output[output_len] = obj and "true" or "false"
        stack[#stack] = nil
      elseif type(obj) == "function" then
        local name = func_to_name[obj]
        output_len = output_len + 1
        output[output_len] = name and "fn_registry." .. name or "nil"
        stack[#stack] = nil
      elseif type(obj) == "table" then
        -- Initialize table processing
        output_len = output_len + 1
        output[output_len] = "{\n"
        state.keys = {}
        state.key_index = 1

        -- Collect keys
        for k in pairs(obj) do
          state.keys[#state.keys + 1] = k
        end

        -- Sort keys if requested
        if sort_keys then
          table.sort(state.keys, function(a, b)
            return tostring(a) < tostring(b)
          end)
        end

        -- Start processing the first key
        state.current_key = state.keys[1]
      else
        output_len = output_len + 1
        output[output_len] = "nil"
        stack[#stack] = nil
      end
    elseif state.current_key then
      -- Processing table entries
      local k = state.current_key
      local serialized_key = type(k) == "number" and tostring(k) or serialize(k, current_indent, func_registry, sort_keys)
      output_len = output_len + 1
      output[output_len] = current_indent .. "  [" .. serialized_key .. "] = "

      -- Push the value onto the stack
      stack[#stack + 1] = {obj = obj[k], indent = current_indent .. "  ", first = true}
      state.key_index = state.key_index + 1
      state.current_key = state.keys[state.key_index]
    else
      -- Table is fully processed
      output_len = output_len + 1
      output[output_len] = current_indent .. "}"
      stack[#stack] = nil
    end
  end

  return table.concat(output)
end

local function serialize_20250813(o, indent, func_registry)
  -- id:: c9c98f95-faf3-4f45-bd05-a90d86bbcb58
  -- pnxt: local function serialize(o, indent, func_registry, sort_keys) || ((e23f8dbd-c420-4321-b8e2-571b3d94e3af))
  -- [grok: efficient serialize](https://grok.com/share/bGVnYWN5_b61f6779-28dd-49fe-be2a-795b38cf27df)
	-- mplm: [[20250502-serialize-w-fn-registry-01.lua]]
  indent = indent or ""
  func_registry = func_registry or {}
  if type(o) == "number" then
    return tostring(o)
  elseif type(o) == "string" then
    return string.format("%q", o)
  elseif type(o) == "boolean" then
    return tostring(o)
  elseif type(o) == "function" then
		-- debug
		-- print("Function found:", tostring(o))
		-- for name, func in pairs(func_registry) do
		-- 	print("Comparing with:", name, tostring(func), "Match:", func == o)
		-- end
		-- Function found: function: 0x0234fef8a0
		-- Comparing with: fn03 function: 0x023500b128 Match: false
    for name, func in pairs(func_registry) do
      if func == o then
        return "fn_registry." .. name
      end
    end
    return "nil"
  elseif type(o) == "table" then
    -- Collect and sort keys
    local keys = {}
    for k in pairs(o) do
      table.insert(keys, k)
    end
    table.sort(keys, function(a, b)
      -- Convert keys to strings for consistent sorting
      return tostring(a) < tostring(b)
    end)

    local s = "{\n"
    for _, k in ipairs(keys) do
      local serialized_key = type(k) == "number" and k or serialize(k, indent, func_registry)
      s = s .. indent .. "  [" .. serialized_key .. "] = " .. serialize(o[k], indent .. "  ", func_registry) .. ",\n"
    end
    return s .. indent .. "}"
  else
    return "nil"
  end
end

local function serializeOrdered(tbl, indent)
  -- id:: 4fd34235-7867-4234-8926-8f0a2b673707
  -- date:: 20260202
  if type(tbl) ~= "table" then
    return tostring(tbl)
  end

  indent = indent or ""
  local nextIndent = indent .. "  "

  -- Handle array-like tables differently
  if u.u.isArrayLike(tbl) then
    local parts = {}
    local items = {}
    for i = 1, #tbl do
      if type(tbl[i]) == "string" then
        table.insert(items, string.format("%q", tbl[i]))
      else
        table.insert(items, serializeOrdered(tbl[i], nextIndent))
      end
    end
    return "{ " .. table.concat(items, ", ") .. " }"
  end

  -- Handle regular tables
  local keys = {}
  for k in pairs(tbl) do
    table.insert(keys, k)
  end
  table.sort(keys, function(a, b)
    return tostring(a):lower() < tostring(b):lower()
  end)

  local parts = {}
  table.insert(parts, "{\n")

  for _, k in ipairs(keys) do
    local v = tbl[k]
    local key
    if type(k) == "string" then
      key = k
    else
      key = "[" .. tostring(k) .. "]"
    end

    local value
    if type(v) == "table" then
      value = serializeOrdered(v, nextIndent)
    elseif type(v) == "string" then
      value = string.format("%q", v)
    else
      value = tostring(v)
    end

    table.insert(parts, nextIndent .. key .. " = " .. value .. ",\n")
  end

  table.insert(parts, indent .. "}")
  return table.concat(parts)
end

local function serialize_obj_str(tbl, fn_registry, header)
  -- id:: a84c6f36-9be5-4866-9c64-b9b54028f457
  -- date:: 20260202
  local serialized_table = serialize(tbl, "", fn_registry)  -- || ((e23f8dbd-c420-4321-b8e2-571b3d94e3af))

  local default_header = [[
-- testing
package.loaded["user.mert-lua-nvim"] = nil
local f = require("user.mert-lua-nvim")
]]
  local header = header or default_header
	local result = header .. "return " .. serialized_table
	return result
end

local function serialize_obj(tbl, fpath, fn_registry, header)
  -- id:: a475d7f8-73cd-44e5-8451-4b18e4590b2e
	-- mplm: [[20250502-serialize-w-fn-registry-01.lua]]
	-- exmp: [[20250502-serialize-w-fn-registry-02.lua]]
  -- Use the provided fn_registry instead of reloading it
  local serialized_table = serialize_obj_str(tbl, fn_registry, header)

  local file = io.open(fpath, "wb")
  file:write(serialized_table)
  file:close()
end

local function serialize_obj_utils(tbl, fpath, fn_registry)
  -- id:: 6b659bb0-12d5-4905-8b62-575f037383b6
  -- date:: 20260202
  local utils_header = [[
package.loaded["mert-lua-utils"] = nil
local f = require("mert-lua-utils")
]]
  local serialized_table = serialize_obj_str(tbl, fn_registry, utils_header)

  local file = io.open(fpath, "wb")
  file:write(serialized_table)
  file:close()
end

local function serialize_obj_factory_str(tbl, fn_registry, header)
  -- id:: b7f2b9e8-a336-4317-a79e-6d3b3f2d0c53
  -- date:: 20260429
	-- [grok](https://grok.com/share/bGVnYWN5_d62c5558-9655-445c-966b-827354b8bc7f)
  -- Reuses your existing serialize() function
  local serialized_table = serialize(tbl, "", fn_registry)

  local default_header = [[
package.loaded["mert-lua-utils"] = nil
local f = require("mert-lua-utils")
]]
  local header = header or default_header

  local result = header .. [[

return function()
  return ]] .. serialized_table .. [[
end
]]
  return result
end

local function serialize_obj_factory(tbl, fpath, fn_registry, header)
  -- id:: aa8ee197-7c67-4faf-bcfe-d49d24b3e59a
  -- date:: 20260429
	-- [grok](https://grok.com/share/bGVnYWN5_d62c5558-9655-445c-966b-827354b8bc7f)
  local serialized_str = serialize_obj_factory_str(tbl, fn_registry, header)

  local file = io.open(fpath, "wb")
  file:write(serialized_str)
  file:close()
end
local function serialize_cfile(tbl, fname)
  -- id:: 3b967060-e5bb-4f05-816a-353d2f69a33e
  -- spcs: Lua: How to get path of current script file's directory? `f/prmp prg/lua` || ((71b69a48-475d-486e-9acf-6749e1860014))
  local dpath = u.u.get_caller_script_path()
  local fpath = "" .. dpath .. fname
  serialize_obj(tbl, fpath)
end

local function serialize_fn(tbl, fname)
  -- id:: c1af423e-da2e-4d7e-a343-bcc5f8e7ac91
  -- date:: 20260202
  local fpath = u.u.fn2fp(fname)
  serialize_obj(tbl, fpath)
end

return {
  serialize = serialize,
  serializeOrdered = serializeOrdered,
	serialize_obj_str = serialize_obj_str,
  serialize_obj = serialize_obj,
	serialize_obj_utils = serialize_obj_utils,
	serialize_obj_factory_str = serialize_obj_factory_str,
	serialize_obj_factory = serialize_obj_factory,
  serialize_cfile = serialize_cfile,
  serialize_fn = serialize_fn,
}
-- [[serialize-oldfiles-map.lua]]

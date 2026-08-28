-- [[mert-utils-debug.lua]]

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

local function reload(moduleName)
  -- id:: c1c19476-48c8-4562-8fc7-dde95b3bfc24
  -- spcs: Lua: It doesn't reload an imported package `f/prmp prg/lua` || ((c762264d-eb8f-4d17-aaa0-37e0aace496e))
  --
  -- input2 = reload("20241222-prespec-edit-flat")
  --
  if moduleName:sub(-4) == ".lua" then
    moduleName = moduleName:sub(1, -5) -- Remove last 4 characters
  end
  package.loaded[moduleName] = nil
  _G[moduleName] = nil
  return require(moduleName)
end

local function eval_lua_variable(var_name)
  -- id:: 29952cbf-77a3-4019-85d5-cd59f3e12262
  -- This method works for global variables, but not for local variables.
  -- Usage
  -- some_global_variable = "Hello, world!"
  -- local result = eval_lua_variable("some_global_variable")
  -- print(result)  -- Outputs: Hello, world!
  --
  return _G[var_name]
end

local function eval_lua_expression(expr)
  -- id:: edbf44d3-33ad-4aaa-987f-b98692692c4f
  -- date:: 20260202
  -- Local variables included:
  --
  -- Usage
  -- local my_variable = "Test value"
  -- local result = eval_lua_expression("my_variable")
  -- print(result)  -- Outputs: Test value
  --
  local func, err = load("return " .. expr)
  if func then
    return func()
  else
    error("Error evaluating expression: " .. err)
  end
end

local function get_script_path()
  -- id:: 5778a96a-7cae-45e8-8a65-9880f61a5988
  -- date:: 20260202
  -- spcs: Lua: How to get path of current script file's directory? || ((71b69a48-475d-486e-9acf-6749e1860014))
  local source = debug.getinfo(1).source
  if source:sub(1, 1) == "@" then
    -- Running from a file
    return source:sub(2):match("(.*/)") or ""
  else
    -- Running from a string
    return ""
  end
end

local function get_caller_script_path()
  -- id:: 345faa02-9b90-488f-b669-57ca383e4491
  -- date:: 20260202
  -- spcs: Lua: How to get path of current script file's directory? `f/prmp prg/lua` || ((71b69a48-475d-486e-9acf-6749e1860014))
  -- Start from level 1 and work up the stack
  local level = 1
  while true do
    local info = debug.getinfo(level, "S")
    if not info then
      break
    end

    -- If we find a file path (starts with @)
    if info.source:sub(1, 1) == "@" then
      local path = info.source:sub(2):match("(.*/)") or ""
      local filename = info.source:sub(2):match(".*/(.*)$") or info.source

      -- Skip if it's mert-lua.lua
      if not filename:match("mert%-lua%.lua$") then
        return path, filename
      end
    end
    level = level + 1
  end
  return "", ""
end

local function toStringVar(var)
  -- id:: 88f76683-9f82-4cef-b0f4-510ba8b6b8e7
  -- date:: 20260202
  -- [grok](https://grok.com/chat/7df48c9b-4fb9-4da3-8cb8-98edc32fe30c)
  -- Example usage:
  -- local str = "Hello"
  -- local tbl = {a = 1, b = {c = 2, d = "test"}, e = "world"}
  --
  -- local strResult = toStringVar(str)
  -- print(strResult)  -- Output: Hello
  --
  -- local tblResult = toStringVar(tbl)
  -- print(tblResult)  -- Output: a: 1
  --                   --         b:
  --                   --           c: 2
  --                   --           d: test
  --                   --         e: world
	local result = {}

	if type(var) == "table" then
		-- Convert table to string (recursive for nested tables)
		local function tableToString(t, indent)
			indent = indent or ""
			for k, v in pairs(t) do
				if type(v) == "table" then
					table.insert(result, indent .. k .. ":\n")
					tableToString(v, indent .. "  ")
				else
					table.insert(result, indent .. k .. ": " .. tostring(v) .. "\n")
				end
			end
		end
		tableToString(var)
	else
		-- Convert non-table to string
		table.insert(result, tostring(var) .. "\n")
	end

	return table.concat(result)
end

-- Function to convert value to string
local function valueToString(value)
  -- id:: 1b33a117-d695-4a58-a3d5-1cb7f7b34e78
  -- date:: 20260202
  if value == nil then
    return ""
  elseif type(value) == "table" then
    return ""
  else
    return tostring(value)
  end
end

local function tableToString(rectTable, separator)
  -- id:: 80a86740-983c-4bef-99a1-733ff2a985cd
  -- date:: 20260202
  -- rfr: src: [[20241222-tfrm-tbl-2-tsv-06.lua]]
  separator = separator or ","
  local result = {}

  for _, row in ipairs(rectTable) do
    local stringRow = {}
    for _, value in ipairs(row) do
      table.insert(stringRow, valueToString(value))
    end
    table.insert(result, table.concat(stringRow, separator))
  end

  return table.concat(result, "\n")
end

local function printVar(var)
  -- id:: 1309a12c-4d4b-4d9a-8b7d-4f4314727ab7
  -- date:: 20260202
  -- [grok](https://grok.com/chat/7df48c9b-4fb9-4da3-8cb8-98edc32fe30c)
  -- -- Example usage:
  -- local str = "Hello"
  -- local tbl = {a = 1, b = {c = 2, d = "test"}, e = "world"}
  --
  -- printVar(str)  -- Output: Hello
  -- printVar(tbl)  -- Output: a: 1
  -- --         b:
  -- --           c: 2
  -- --           d: test
  -- --         e: world
	if type(var) == "table" then
		-- Print table (simple recursive function for nested tables)
		local function printTable(t, indent)
			indent = indent or ""
			for k, v in pairs(t) do
				if type(v) == "table" then
					print(indent .. k .. ":")
					printTable(v, indent .. "  ")
				else
					print(indent .. k .. ": " .. tostring(v))
				end
			end
		end
		printTable(var)
	else
		-- Print string or other types
		print(tostring(var))
	end
end

local function inspect(x)
  -- id:: 422c6946-62ab-4928-b307-215a9501426f
  -- date:: 20260202
  -- exmp: [[20250323-inspect.lua]]
  -- [grok](https://grok.com/chat/ad38db12-d052-41b9-96d3-949528b61b08)
  -- exmp:
  --   '20250810-213054 inspecting: 
  --   Variable name (global): tbl1
  --   table
  --   {
  --       ["20250616-nu-scrap"] = "term://~/prj/myrepo/logseq-myrepo//36014:cd /Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20250616-nu-scrap",
  --       ["f___pipeline.md"] = "/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/pages/f___pipeline.md",
  --       ...
  local result = u.u.get_ts() .. " inspecting: \n"
  -- Check local variables
  local i = 1
  while true do
    local name, value = debug.getlocal(2, i)  -- Level 2 is caller
    if not name then break end
    if value == x then
      result = result .. "Variable name (local): " .. name .. "\n"
      break
    end
    i = i + 1
  end

  -- Check globals if no local match
  for name, value in pairs(_G) do
    if value == x then
      result = result .. "Variable name (global): " .. name .. "\n"
      break
    end
  end

  -- Inspect the value
  result = result .. type(x) .. "\n"
  if type(x) == "table" then
    result = result .. u.u.table_to_literal(x) .. "\n"
  else
    result = result .. tostring(x) .. "\n"
  end
  
  print(result)
  return result
end

-- Function to print the table
local function printOrdered(tbl)
  -- id:: 323dec90-374f-4292-ada6-daf9a9fda856
  -- date:: 20260202
  print(serializeOrdered(tbl))
end

return {
	reload = reload,
	eval_lua_variable = eval_lua_variable,
	eval_lua_expression = eval_lua_expression,
	get_script_path = get_script_path,
	get_caller_script_path = get_caller_script_path,
	toStringVar = toStringVar,
	printVar = printVar,
	printTable = printTable,
	valueToString = valueToString,
	tableToString = tableToString,
	inspect = inspect,

}

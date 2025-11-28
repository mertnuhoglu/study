-- [[mert-utils-fp.lua]]

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

local function map(x, f)
  -- spcs: FP in Lua: `f/prmp prg/lua` || ((1458e6d0-d49d-4165-9c81-46ceb31656a1))
  local r = {}
  for i, v in ipairs(x) do
    r[i] = f(v)
  end
  return r
end

local function map_kv(tbl, f)
  -- id:: 1c8a4448-c2f6-49cb-b8b5-ea9853d2395c
	-- exmp: [[20250414-map-over-keys-of-tbl-01.lua]]
	-- alternative-to: tablex.map: exmp: [[20250414-map-over-keys-of-tbl-02.lua]]
  local result = {}
  for k, v in pairs(tbl) do
    local new_k, new_v = f(k, v)
    result[new_k] = new_v
  end
  return result
end

local function reduce(x, f, initial)
  -- spcs: Tfrm: Array to Logical Table `prg/lua f/prmp` || ((7f5d3217-5d41-4a12-94f8-12a2d11d39a6))
  local result = initial
  for _, v in ipairs(x) do
    result = f(result, v)
  end
  return result
end

local function partial(func, ...)
  -- spcs: FP in Lua: `f/prmp prg/lua` || ((1458e6d0-d49d-4165-9c81-46ceb31656a1))
  local args = { ... }
  return function(...)
    local new_args = { ... }
    local final_args = {}

    -- Copy partial args
    for i = 1, #args do
      final_args[i] = args[i]
    end

    -- Add new args
    for i = 1, #new_args do
      final_args[#args + i] = new_args[i]
    end

    -- Use unpack for Lua 5.0 or table.unpack for Lua 5.1+
    local unpack = table.unpack or unpack
    return func(unpack(final_args))
  end
end

local function identity(x)
  return x
end

local function filter_tbl(lhs, vals, key)
	-- id:: 76998e50-24d8-467a-a0ba-17ef669f2f29
	-- prt: filter_tbl2(lhs, vals2, key) || ((7b7ad64f-237f-40c0-aa6d-7f06fffa8ca1))
	-- complementary: local function drop_tbl(specs, path_set, path_key) || ((f09c6f06-acb8-492c-a058-09c63bd84d9c))
  -- spcs: Tfrm: specs.tsv to edits_stnd.lua  `nlys/spcs f/prmp` || ((dec3da82-9eb4-440c-80db-82e220e1d0ed))
  -- exmp: [[20241227-tfrm-join-specs-lua-with-specs-tsv-03.lua]]
  --
  -- lhs: a nested table
  -- vals: a tabular array of form: { key_1, key_2 }
  -- key: property in lhs
  if type(lhs) ~= "table" then
    return lhs
  end

	local vals2 = reduce(paths, function(acc, el)
		acc[el] = true
		return acc
	end, {})
  -- vals2: a tabular array of form: { key_n = true }

  return filter_tbl2(lhs, vals2, key)
end

local function drop_tbl2(specs, vals2, key)
	-- id:: 1fb9f4b3-1108-47c6-a9ee-6a123722dee0
	-- prn: local function drop_tbl(specs, vals, key) || ((f09c6f06-acb8-492c-a058-09c63bd84d9c))
  -- vals2: a tabular array of form: { key_n = true }
  if type(specs) ~= "table" then
    return specs
  end

  local result = {}
  local should_keep_node = true

  for k, v in pairs(specs) do
    if type(v) == "table" then
      local filtered = drop_tbl2(v, vals2, key)
      if filtered then
        result[k] = filtered
      end
    else
      result[k] = v
    end
  end

  -- Drop node if it matches TSV path
  if result[key] and vals2[result[key]] and result[1] and result[2] then
    return nil
  end

  return next(result) and result or nil
end

local function drop_tbl(specs, vals, key)
	-- id:: f09c6f06-acb8-492c-a058-09c63bd84d9c
  -- prt: local function drop_tbl2(specs, vals2, key) || ((1fb9f4b3-1108-47c6-a9ee-6a123722dee0))
  -- complementary: filter_tbl(lhs, rhs, key) || ((76998e50-24d8-467a-a0ba-17ef669f2f29))
  -- spcs: Now, I need exact opposite behaviour: `f/prmp` || ((fb422122-b5c0-4100-8161-16bf20ad2d85))
  -- exmp: [[20250403-exmp-drop_tbl.lua]]
  --
  -- lhs: a nested table
  -- vals: a tabular array of form: { key_1, key_2 }
  -- key: property in lhs
  if type(specs) ~= "table" then
    return specs
  end

	local vals2 = reduce(vals, function(acc, el)
		acc[el] = true
		return acc
	end, {})

	return drop_tbl2(specs, vals2, key)
end

return {
	map = map,
	map_kv = map_kv,
	reduce = reduce,
	partial = partial,
	identity = identity,
	filter_tbl = filter_tbl,
	drop_tbl2 = drop_tbl2,
	drop_tbl = drop_tbl,

}

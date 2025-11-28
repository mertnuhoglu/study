-- [[mert-utils-data-structure.lua]]

-- local u = {
-- 	u = require("mert-lua-utils"),
-- }

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

local function getTitle(value)
  -- If it's a table with a name property, use that
  if type(value) == "table" and value.name then
    return value.name
    -- If it's a table with at least 1 element and the first one is a string
  elseif type(value) == "table" and #value >= 1 and type(value[1]) == "string" then
    -- Trim the '#' prefix if present
    return value[1]:gsub("^#", "")
  end
  return nil
end

local function tableToOutline3Tbl(tbl, indent)
	-- rfr: specs: Lua table -> otl outline `f/prompt prg/lua f/spcs` || ((0b9c08d6-c3fa-4742-b075-c2fcc231279e))
	-- renamed: tableToOutline -> tableToOutline3Tbl
	-- write_runlog("## tbl: ")
	-- write_runlog(tbl)
  indent = indent or 0
  local result = {}

  -- First, add the name of the current table if it exists
  local currentTitle = u.u.getTitle(tbl)
  if currentTitle then
    table.insert(result, string.rep("  ", indent) .. currentTitle)
  end

  -- Collect all subtable entries with their titles
  local sortedEntries = {}
  for k, v in pairs(tbl) do
    -- Skip the name and name2 keys
    if k ~= "name" and k ~= "name2" then
      if type(v) == "table" then
        local title = getTitle(v)
        if title then
          table.insert(sortedEntries, { title = title, value = v })
        end
      end
    end
  end

  -- Sort entries by title
  table.sort(sortedEntries, function(a, b)
    return a.title < b.title
  end)

  -- Process sorted entries
  for _, entry in ipairs(sortedEntries) do
    local subResults = tableToOutline3Tbl(entry.value, indent + 1)
		-- write_runlog("## subResults: ")
		-- write_runlog(subResults)
    for _, line in ipairs(subResults) do
      table.insert(result, line)
    end
  end

	-- error:
	-- local result_text = table.concat(result, "\n")
	--  return result_text
	-- rtcs: recursive bir fn de çıktı aynı zamanda girdidir.
	-- bu yüzden çıktıyı değiştirirsen, dahili çağrılardaki girdiyi de değiştirmiş olursun
	return result
end

-- Function to convert the outline table to a string
local function tableToOutline(tbl)
  -- renamed: outlineToString -> tableToOutline
  local result = tableToOutline3Tbl(tbl)
  return table.concat(result, "\n")
end

-- Helper function to check if a table is array-like
local function isArrayLike(tbl)
  local maxIndex = 0
  local count = 0
  for k, v in pairs(tbl) do
    if type(k) == "number" and k > maxIndex then
      maxIndex = k
    end
    count = count + 1
  end
  return maxIndex == count -- true if all indices are sequential
end

--
--- Gets the total number of key-value pairs in a Lua table.
-- This function works for both sequence-like tables (arrays) and
-- hash-like tables (dictionaries/maps).
-- @param tbl The table whose size is to be determined.
-- @return number The total number of elements (key-value pairs) in the table,
--                or 0 if the input is not a table.
local function len(tbl)
	-- exmp: [[20250414-exmp-len.lua]]
	--
  -- Check if the input is actually a table
  if type(tbl) ~= "table" then
    -- Return 0 or raise an error if the input is not a table
    -- Returning 0 is often practical for a 'size' function.
    return 0
  end

  -- Initialize a counter
  local count = 0
  -- Iterate through all key-value pairs in the table
  for _ in pairs(tbl) do
    -- Increment the counter for each pair found
    count = count + 1
  end
  -- Return the final count
  return count
end

local function head_tbl_20250414(x)
	-- exmp: [[20250414-debug-head_tbl.lua]]
	--   [[20250414-debug-head_tbl-01.lua]]
	if type(x) == "string" then
		return x:sub(1, math.min(1000, #t))
	elseif type(x) == "table" then
		local new_table = {}
		for i = 1, math.min(10, len(x)) do
			new_table[i] = x[i]
		end
		return new_table
	else
		return x
	end
	return nil -- Handle unexpected types
end

local function head_tbl(x, size)
	-- id:: f6e320c6-e518-4ee3-81c7-0d79d5532ae5
	-- [grok](https://grok.com/chat/e26f7313-2be2-45a2-8ddb-8136333cfc79)
	-- exmp: [[20250414-debug-head_tbl-01.lua]]
  size = size or 10  -- Default to 10
	if type(x) == "string" then
		return x:sub(1, math.min(1000, #x))
	elseif type(x) == "table" then
		local result = {}
		local count = 0

		-- First, try to collect up to 10 numeric-indexed elements
		for i = 1, math.min(size, #x) do
			-- Recursively trim nested tables
			result[i] = type(x[i]) == "table" and head_tbl(x[i]) or x[i]
			count = count + 1
		end

		-- If fewer than 10 elements, fill with other key-value pairs
		if count < size then
			for k, v in pairs(x) do
				-- Skip numeric keys already processed
				if type(k) ~= "number" or k > count or k < 1 then
					if count < size then
						-- Recursively trim nested tables
						result[k] = type(v) == "table" and head_tbl(v) or v
						count = count + 1
					else
						break
					end
				end
			end
		end
		return result
	else
		return x
	end
end

function merge(t1, t2)
  -- [Claude](https://claude.ai/chat/0fc84ebd-56d7-4e97-aa45-b6b6f9d504de)
  -- Usage:
  -- local table1 = { a = 1, b = 2 }
  -- local table2 = { c = 3, d = 4 }
  -- local merged = merge(table1, table2)
  --
  local result = {}
  for k, v in pairs(t1) do
    result[k] = v
  end
  for k, v in pairs(t2) do
    result[k] = v
  end
  return result
end

function map(x, f)
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

function reduce(x, f, initial)
  -- spcs: Tfrm: Array to Logical Table `prg/lua f/prmp` || ((7f5d3217-5d41-4a12-94f8-12a2d11d39a6))
  local result = initial
  for _, v in ipairs(x) do
    result = f(result, v)
  end
  return result
end

function partial(func, ...)
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

function identity(x)
  return x
end

local function filter_tbl2(lhs, vals2, key)
	-- id:: 7b7ad64f-237f-40c0-aa6d-7f06fffa8ca1
	-- prn: filter_tbl(lhs, vals, key) || ((76998e50-24d8-467a-a0ba-17ef669f2f29))
  -- vals2: a tabular array of form: { key_n = true }
  if type(lhs) ~= "table" then
    return lhs
  end

  local result = {}
  local has_valid_children = false

  -- First pass: recursively filter children
  for k, v in pairs(lhs) do
    if type(v) == "table" then
      local filtered = filter_tbl2(v, vals2, key)
      if filtered then
        result[k] = filtered
        has_valid_children = true
      end
    else
      result[k] = v
    end
  end

  -- Check if this node should be kept
  local keep_node = false

  -- Keep if this node's path exists in TSV
  if result[key] and vals2[result[key]] then
    keep_node = true
  end

  -- Keep if it has valid children or is a leaf node with numeric indices
  if has_valid_children or (result[1] and result[2] and result[key] and vals2[result[key]]) then
    keep_node = true
  end

  -- Keep parent nodes that have any valid children
  if has_valid_children then
    keep_node = true
  end

  return keep_node and result or nil
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

-- Helper function to deep copy a table
function table.deepcopy(orig)
  local orig_type = type(orig)
  local copy
  if orig_type == "table" then
    copy = {}
    for orig_key, orig_value in pairs(orig) do
      copy[orig_key] = table.deepcopy(orig_value)
    end
  else
    copy = orig
  end
  return copy
end

-- [grok](https://grok.com/share/bGVnYWN5_2b95fc3d-78b8-4880-8237-54d5b61d7815)
function deep_merge(t1, t2, path)
  -- id:: b78b7ad7-8bd1-42b8-914f-a25aefdb7cbf
  -- Initialize path if not provided (for top-level call)
  path = path or "root"
  
  -- Validate inputs
  if type(t1) ~= "table" then
    print("Error: t1 is not a table at path: " .. path)
    print("Value of t1: " .. tostring(t1))
    print("Type of t1: " .. type(t1))
    error("deep_merge: t1 must be a table at path " .. path .. ", got " .. type(t1))
  end
  if type(t2) ~= "table" then
    print("Error: t2 is not a table at path: " .. path)
    print("Value of t2: " .. tostring(t2))
    print("Type of t2: " .. type(t2))
    error("deep_merge: t2 must be a table at path " .. path .. ", got " .. type(t2))
  end

  local result = {}

  -- Copy all fields from first table
  for k, v in pairs(t1) do
    local new_path = path .. "." .. tostring(k)
    if type(v) == "table" then
      result[k] = table.deepcopy(v)
    else
      result[k] = v
    end
  end

  -- Recursively merge with second table
  for k, v in pairs(t2) do
    local new_path = path .. "." .. tostring(k)
    if type(v) == "table" and type(result[k]) == "table" then
      result[k] = deep_merge(result[k], v, new_path)
    else
      result[k] = v
    end
  end

  return result
end

function innerJoinByPath(leftTable, rightTable)
	-- src: [[20250402-join-lua-tables-02.lua]]
	--
	-- Helper function to collect all paths from a table
	local function collectPaths(tbl, paths)
		if type(tbl) ~= 'table' then return end

		if tbl["_path"] then
			paths[tbl["_path"]] = true
		end

		for k, v in pairs(tbl) do
			if type(v) == 'table' then
				collectPaths(v, paths)
			end
		end
	end

	-- Collect paths from the right table
	local rightPaths = {}
	collectPaths(rightTable, rightPaths)

	-- Helper function to filter the left table
	local function filterTable(tbl)
		if type(tbl) ~= 'table' then return tbl end

		local result = {}

		-- Add metadata and numeric keys directly
		for k, v in pairs(tbl) do
			if k == "_path" or k == "_name" or type(k) == "number" then
				result[k] = v
			end
		end

		-- Process sub-tables
		for k, v in pairs(tbl) do
			if type(v) == 'table' and type(k) == "string" and not k:match("^_") then
				-- For each sub-table, check if its path exists in the right table
				if v["_path"] and rightPaths[v["_path"]] then
					result[k] = filterTable(v)
				end
				-- If path doesn't exist in right table, we omit this sub-table
			end
		end

		return result
	end

	return filterTable(leftTable)
end

local function extractAttribute(tbl, attrName)
	-- exmp: [[20250403-lua-table-extract-a-specific-element.lua]]
	--
	local result = {}

	local function recurse(t)
		-- Check if current table has the attribute
		if type(t) == "table" and t[attrName] then
			table.insert(result, t[attrName])
		end

		-- Recursively check all sub-tables
		if type(t) == "table" then
			for _, v in pairs(t) do
				recurse(v)
			end
		end
	end

	recurse(tbl)
	return result
end

local function ap_path(dt)
	-- id:: 87748cec-b7f0-4660-99fe-529f5d62399e
	-- src: local function pprv_to_w_path(wk_pprv) || ((5f40d111-a446-47d8-b4b4-24dd4e5b4c3e))
	-- doc: append _path attribute to every sub-table element
	-- exmp: [[20250403-exmp-drop_tbl.lua]]
	--
	local tfrm_ap_path = reload("tfrm-ap-path")
	local tfrm_remove_elem_w_nil = reload("tfrm-remove-elem-w-nil")

	dt5 = tfrm_ap_path(dt)
	dt6 = tfrm_remove_elem_w_nil(dt5)
	return dt6
end

local function innerJoin(lhs, rhs)
	-- id:: 5507f424-5960-4a41-b6c7-012bc09b19f0
	-- exmp: [[20250402-join-lua-tables-04.lua]]
	local lhsp = ap_path(lhs)
	local rhsp = ap_path(rhs)
	local result = innerJoinByPath(lhsp, rhsp)
	return result
end

local function removeElem(tbl, elem)
	-- src: [[20250403-table-remove-element-01.lua]]
	-- exmp: [[20250403-table-remove-element-03.lua]]
	if type(tbl) ~= "table" then
		return tbl
	end

	local result = {}
	for key, value in pairs(tbl) do
		if key ~= elem then
			if type(value) == "table" then
				result[key] = removeElem(value, elem)
			else
				result[key] = value
			end
		end
	end
	return result
end

local function renameElem(tbl, old_key, new_key)
	-- spcs: [[20250405-table-rename-element.lua]]
	-- exmp: [[20250405-table-rename-element-01.lua]]
  -- If the input is not a table, return it unchanged
  if type(tbl) ~= "table" then
    return tbl
  end

  local new_tbl = {}
  -- Iterate through all key-value pairs in the table
  for k, v in pairs(tbl) do
    local processed_v -- Variable to hold the potentially processed value

    -- Recursively process the value if it's a table
    if type(v) == "table" then
      processed_v = renameElem(v, old_key, new_key)
    else
      -- Otherwise, use the value as is
      processed_v = v
    end

    -- Check if the current key is the one to be renamed
    if k == old_key then
      new_tbl[new_key] = processed_v -- Use the new key
    else
      new_tbl[k] = processed_v      -- Keep the original key
    end
  end
  return new_tbl
end

local function tbl_minus(lhs, rhs)
	-- exmp: [[20250422-mnml-wk-setup-prcs.lua]]
	local lhs_w_path = ap_path(lhs)
	local rhs_w_path = ap_path(rhs)

	local paths_in_rhs = extractAttribute(rhs_w_path, "_path")
	local rs1 = drop_tbl(lhs_w_path, paths_in_rhs, "_path")
	local rs2 = removeElem(rs1, "_path")
	local rs3 = renameElem(rs2, "_name", "name")
	return rs3
end

return {
  -- data structure
	getTitle = getTitle,
	tableToOutline3Tbl = tableToOutline3Tbl,
	tableToOutline = tableToOutline,
	isArrayLike = isArrayLike,
	len = len,
	head_tbl = head_tbl,
  merge = merge,
  map = map,
  map_kv = map_kv,
  reduce = reduce,
  partial = partial,
  identity = identity,
  filter_tbl = filter_tbl,
	filter_tbl2 = filter_tbl2,
  drop_tbl = drop_tbl,
  drop_tbl2 = drop_tbl2,
  deep_merge = deep_merge,
	innerJoinByPath = innerJoinByPath,
	extractAttribute = extractAttribute,
	removeElem = removeElem,
	renameElem = renameElem,
	tbl_minus = tbl_minus,
	-- make_stnd = make_stnd,
}

-- mert-utils-debug.lua
-- mert-utils-tfrm.lua

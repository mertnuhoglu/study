-- [[mert-utils-tfrm.lua]]

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

local function df2kv3key_val(input)
	-- exmp: [[20250415-tfrm-kv2df.lua]]
	local result = {}
	for i = 1, #input.key do
		result[input.key[i]] = input.val[i]
	end
	return result
end

local function kv2df3key_val(input)
	-- exmp: [[20250415-tfrm-kv2df.lua]]
	local result = { key = {}, val = {} }
	for k, v in pairs(input) do
		table.insert(result.key, k)
		table.insert(result.val, v)
	end
	return result
end

local function filterTwoTuples(tbl)
  -- spcs: İkili olmayan satırları sil `f/prmp` || ((791c0c1c-8228-4d8a-b7f1-3fc7d031382f))
  -- local filtered = filterTwoTuples(yourTable)
  --
  local result = {}
  for _, entry in ipairs(tbl) do
    if entry.path and entry.title then
      table.insert(result, entry)
    end
  end
  return result
end

local function get_values(tbl)
  -- Get values from key-value pairs in a table
  --   [Claude](https://claude.ai/chat/5d7e38eb-241e-477b-b704-6e6e5047dd8f)
  -- Approach 2: If you need the values in a new table
  local rs = {}
  for _, value in pairs(tbl) do
    table.insert(rs, value)
  end
  return rs
end

local function df2r(tbl)
  -- id:: a3eb1354-2461-49de-8727-4e503e6dd2ad
  -- df2r: dataframe to record
  -- #f/renamed: tsv_to_redis_tbl -> df2r
  --
  -- exmp: [[20250107-tfrm-tsv-tbl-to-redis-tbl.lua]]
  -- exmp: full: [[20250107-dfl-tsv-to-tbl-to-redis-hset-hget.lua]]
  local out = {}

  -- Set id from the first group value (since they're identical)
  out.id = tbl.key[1]

  -- Map key-value pairs
  for i = 1, #tbl.field do
    out[tbl.field[i]] = tbl.val[i]
  end

  return out
end

return {
	df2kv3key_val = df2kv3key_val,
	kv2df3key_val = kv2df3key_val,
  filterTwoTuples = filterTwoTuples,
  get_values = get_values,
	df2r = df2r,

}

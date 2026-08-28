-- [[mert-utils-redis.lua]]
--
-- prn: [[mert-lua-utils.lua]]
-- prt: [[mert-utils-redis-bbl.lua]]
--

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

-- local redis = require("redis").connect("127.0.0.1", 6379)
local redis_client = nil  -- Module-level local for the connection

local function get_redis()
  -- id:: 1edb244b-7d84-48bd-8aad-694ea61fb447
  -- date:: 20260202
  if not redis_client then
    local redis = require("redis")
    redis_client = redis.connect("127.0.0.1", 6379)
    -- Optional: Set auth or timeout if needed
    -- redis_client:auth("password")
    -- redis_client:set_timeout(1000)  -- 1s, if supported by your client
  end
  return redis_client
end

local function rset(key, val)
  -- id:: 9839ddf6-c68a-4a2d-bf2d-86433d8028be
	-- exmp: [[20241012-redis-putRefId.lua]]
	-- renamed: putRefId -> rset
  get_redis():set(key, val)
end

local function rget(key)
  -- id:: a595edb5-84b8-4822-87eb-51c4499bfdd5
	-- exmp: [[20241012-redis-putRefId.lua]]
	-- renamed: getFpath -> rget
  return get_redis():get(key)
end

local function rget_prefix(prefix)
  -- id:: afeee1e2-01b8-46f7-8b6c-2868fefaa19f
	return function(key)
		return rget(prefix .. key)
	end
end

local rget_path = rget_prefix('path:')

local function rget_keys(keys)
  -- id:: 3c3a3f8b-5c15-4547-b732-84fe66d2ad69
  -- a<key> -> m<key, val>
  -- exmp: [[20250107-flat-lua-tbl-to-redis-set.lua]]
  local redis = get_redis()

  local m = {}
	for _, key in ipairs(keys) do
		local val = redis:get(key)
		if not val then
			error("redis: Key not exists: " .. key)
		else
			m[key] = val
		end
	end

  return m
end

local function fn2fp(fn)
  -- id:: 518bcbf8-2781-4545-9861-db01db8766eb
  -- exmp: [[20250413-exmp-fn2fp.lua]]
  local redis = get_redis()

  local rs = redis:get("path:" .. fn)
  if not rs then
    error("redis: Not exists: " .. fn)
  end
  return rs
end

local function set_kv3df(df)
  -- id:: 3b648d7a-002e-4908-a3cd-a0fc9e611268
  -- date:: 20260202
  -- exmp: [[20250107-flat-lua-tbl-to-redis-set.lua]]
	-- #f/renamed: redis_set -> set_kv3df
	-- df structure:
	-- {
	--   key = { "path:20241121-wk-edits-1121-134809.lua", ...},
	--   val = { "/Users/mertnuhoglu/prj/private_dotfiles/.config/lazyvim/lua/config/20241121-wk-edits-1121-134809.lua", ... }
	-- }
	-- 20250415 tfrm kv2df
  local client = get_redis()

  for i = 1, #df.key do
    client:set(df.key[i], df.val[i])
  end
end

local function removeRefidPrefix(str)
  -- id:: 766888c9-ccf1-4d72-bca4-89ea88d8d5e8
  -- date:: 20260202
    return string.match(str, "^refid:(.*)") or str
end

local function hget_fields(fields)
  -- id:: e78c1058-9122-49c3-a68c-b6193bb13dff
  -- date:: 20260202
	-- #f/renamed: hget_tbl -> hget_fields
	-- exmp: [[20250413-exmp-hget_tbl.lua]]
  -- exmp: [[20250107-tfrm-tsv-tbl-to-redis-tbl-hget.lua]]
  local client = get_redis()
  local out = {}

  -- For each key-value pair
  for i = 1, #fields.field do
    local field = fields.field[i]
    -- Use the first group value as the hash key since they're identical
    out[field] = client:hget(fields.key[1], field)
  end
	out.id = removeRefidPrefix(fields.key[1])

  return out
end

local function rhset_refid_fp_lnum(refid, fpath, lnum)
  -- id:: a2142f03-3e42-4757-ab4c-1c9214cf0e16
	-- renamed: putRefIdObj -> rhset_refid_fp_lnum
	-- usage: call g:f.putRefIdObj(uuid, fpath, lnum) || ((af615167-09dd-4528-98ee-d98e99fd2d33))
  get_redis():hset("refid:" .. refid, "fpath", fpath, "lnum", lnum)
end

local function escapeDashes(str)
  -- id:: 98106ee9-5745-447c-b410-9c1d5bf8ae50
  return str:gsub("-", "%%%-")
end

local function fuzzyLookup(tbl, prefix)
  -- id:: b2729c5d-0d3f-4601-9f16-84cc1f1cc964
  local search_kw = escapeDashes(prefix)
  for key, value in pairs(tbl) do
    if type(key) == "string" and key:match("^" .. search_kw) then
      return value
    end
  end
  return nil -- Return nil if no match is found
end

local function removeExtension(fname)
  -- id:: 47000f50-8bc9-4d52-b5f9-ef048d5f3543
  -- date:: 20260202
  return fname:match("(.+)%.[^%.]+$") or fname
end

local function has_extension(filename)
  -- id:: 6a658122-85ad-427e-a506-98fb6fe39614
  -- date:: 20260202
	-- [grok](https://grok.com/share/bGVnYWN5_db49e351-5a26-4b8c-9d61-852fe1013fc3)
	return filename:match("^.+(%..+)$") ~= nil
end

-- The cached table (starts as nil)
local _cached_mFn2Fp = nil

local function load_mFn2Fp()
  -- id:: 8687481b-ec7b-4553-bb49-23ff7bb3cf57
  -- date:: 20260202
	-- The actual loading function – keep it private
	local map_fpath = '/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/luapath/lua/mFn2Fp.lua'
	return u.u.loadTableFromFile(map_fpath)
end

local function get_mFn2Fp() -- Public accessor that memoizes the result
  -- id:: 34f4b545-8601-4f6d-89ac-6ae175e34023
  -- date:: 20251119
	-- [grok: memoize](https://grok.com/share/bGVnYWN5_c4776afa-77cc-4a9b-9d80-d7c02e317d2c)
	--
	if _cached_mFn2Fp == nil then
		_cached_mFn2Fp = load_mFn2Fp()
	end
	return _cached_mFn2Fp
end

local function mFn2Fp_lua(fname)
  -- id:: 47a66bdd-f0e9-47e0-a8f3-a634297197f4
	--
	-- renamed: getInOldfiles -> mFn2Fp -> mFn2Fp_lua
	-- exmp: [[20250415-debug-PutBref2NdxGeneric.vim]]
	-- debug: [[20251119-debug-mFn2Fp_lua.lua]]
	--
  -- Usage:
  -- r0 = getInOldfiles("nsettings.otl")
  -- getInOldfiles("nsettings")
  -- local fname = "nsettings.otl"
  --
	-- #f/deprecated: oldfiles
  -- local map_fpath = vim.fn.GetPlainFileInOldfiles(".lua")
	-- use index of all files:
	-- local map_fpath = '/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/luapath/lua/mFn2Fp.lua'
	-- local mFn2Fp = u.u.loadTableFromFile(map_fpath)
	local mFn2Fp = get_mFn2Fp()

  local fnameNoExt = removeExtension(fname)
  local rs = ""
	if not has_extension(fname) then
		local fnameMd = fname .. '.md'
	end
	
  if mFn2Fp[fname] then
    rs = mFn2Fp[fname]
	elseif fnameMd and mFn2Fp[fnameMd] then
		rs = mFn2Fp[fnameMd]
  else
    rs = fuzzyLookup(mFn2Fp, fnameNoExt)
  end
  return rs
end

local function mFn2Fp_redis(fname)
  -- id:: ec358c40-271f-4a0d-9fff-143659cfddd6
	--
	-- renamed: getInOldfiles -> mFn2Fp -> mFn2Fp_redis
	--
  -- Usage:
  -- r0 = mFn2Fp_redis("nsettings.otl")
  --
  local rs = rget_path(fname)
  if not rs then
    rs = mFn2Fp_lua(fname)
  end
  return rs
end

local mFn2Fp = mFn2Fp_redis

local function rhget_refid(refid)
  -- id:: c9de1b95-69d2-481c-8d5e-78b58e2cb521
	-- exmp: [[20241012-redis-hset-hget.lua]]
	-- renamed: getFpathObj -> rhget_refid
	-- rltd: local function hget_tbl_block(uuid) || ((a8d07b5e-3ae1-4750-9a8f-3373c45538f4))
	local rs = get_redis():hgetall("refid:" .. refid)
	rs.id = refid
  return rs
end

local function hset_rcrd(data, prefix)
  -- id:: 024edfe1-aee4-4e74-b671-7c7e4f977fa5
  -- date:: 20260202
	-- #f/renamed: hset_tbl -> hset_rcrd
	-- exmp: [[20250413-exmp-hget_tbl.lua]]
  -- exmp: [[20241012-redis-lua-06.lua]]
  -- exmp: [[20250107-tfrm-tsv-tbl-to-redis-tbl.lua]]
  --
  local client = get_redis()
  local cjson = require("cjson")
  if type(data) ~= "table" then
    return nil, "Data must be a table"
  end

  -- Get the key from the data table
  local redis_key = data.key or data.id
  if not redis_key then
    return nil, "Table must have 'key' or 'id' field"
  end

  -- Add prefix if provided
  if prefix then
    redis_key = prefix .. ":" .. redis_key
  end

  -- Convert table to array of field-value pairs
  local args = { redis_key } -- Start with the key
  for k, v in pairs(data) do
    if k ~= "key" and k ~= "id" then
      if type(v) == "table" then
        table.insert(args, k)
        table.insert(args, cjson.encode(v))
      else
        table.insert(args, k)
        table.insert(args, tostring(v))
      end
    end
  end

  -- Use unpack for Lua 5.0 or table.unpack for Lua 5.1+
  --   id:: 12a009f4-0366-4343-bfcc-2ba3fe87ada3
  local unpack = table.unpack or unpack
  -- Use redis command directly
  return client:hset(unpack(args))
end

local function importWikilinkIntoRedis()
  -- id:: 9c18708c-d4b4-4b24-bfb4-15bf2222caee
  -- date:: 20260202
  -- id:: dk747af15-e39b-44b0-b712-bcd7ba88e8ff
	-- exmp: [[20250414-exmp-importWikilinkIntoRedis.lua]]
	-- debug: [[20250414-debug-importWikilinkIntoRedis.lua]]
	--   [[20250414-debug-importWikilinkIntoRedis-02.lua]]
	--   [[20250821-debug-importWikilinkIntoRedis.lua]]
	--
	local mFn2Fp_path = '/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/luapath/lua/mFn2Fp.lua'
	local mFn2Fp_str = u.u.read_file(mFn2Fp_path)
	local mFn2Fp = load(mFn2Fp_str)()
	-- {
  --   ["20241022-specs-all.lua"] = "/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20240924-tfrm-wk-table/lua/20241022-specs-all.lua",
  --   ["20241121-prespec-edits-09.tsv"] = "/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241122-specs-8-edits-p-2-edits/lua/20241121-prespec-edits-09.tsv",
	--   ...
	-- }
	local kv = u.u.map_kv(mFn2Fp, function(k, v)
		return u.u.addPrefix(k, "path:"), v
	end)
	local df = u.u.kv2df3key_val(kv)
	set_kv3df(df)
	return rget_keys(df.key)
end

local function params_to_redis(params, paths)
  -- id:: f3d68af2-c3ef-4faf-9077-fcc04e39060e
	local df = read_tsv_file(params)
	local redis_tbl = u.u.df2r(df)  -- || ((a3eb1354-2461-49de-8727-4e503e6dd2ad))
	u.u.write_runlog_lua(u.u.inspect(redis_tbl))
	hset_rcrd(redis_tbl)

	local df2 = u.u.read_tsv_file(paths)
	set_kv3df(df2)
end

local function redis_to_fn(params)
  -- id:: bc39831e-bd68-49cb-9c53-6da9db82379c
  -- date:: 20260202
  -- exmp: [[20250324-exmp-redis_to_fn.lua]]
	local df = u.u.read_tsv_file(params)
	local fn = hget_fields(df)
	return fn
end

local function import_tbl_to_redis(tbl)
  -- id:: 7555ea11-7ee8-4bd8-ad33-f2db8aad27dd
  -- date:: 20251209
	-- rltd: local function hset_tbl_block(tbl) || ((07ef6954-b0b2-409b-a453-7561422f31f0))
	--
	-- Function to import JSON data into Redis as hash-maps
	-- mplm: [[20250825-import-json-into-redis.lua]]
	local redis = get_redis()

	-- exmp: [[20251209-lua-tbl-loop-over-each-field.lua]]
	-- [grok](https://grok.com/share/bGVnYWN5_a6845d9a-3d5a-4720-82c9-22bfc4cf5fba)
	for _, item in ipairs(tbl) do
		local key

		if item.id then
			key = "refid:" .. tostring(item.id)
		elseif item.refid then
			key = "refid:" .. tostring(item.refid)
		else
			-- Skip this item if neither id nor refid exists
			u.u.printVar(item)
			print("WARNING: import_tbl_to_redis: Item skipped - missing both 'id' and 'refid'")
			-- Continue to next item (no goto)
		end

		-- Only process fields if we have a valid key
		if key then
			for field, value in pairs(item) do
				if field ~= "id" and field ~= "refid" then
					redis:hset(key, field, tostring(value))
				end
			end
		end
	end
end

local function import_json_file_to_redis(json_fp)
  -- id:: 88d73b64-e2b6-41f2-a50f-eff654d4b888
  -- date:: 20260202
	-- mplm: [[20250825-import-json-into-redis.lua]]
	local tbl = u.u.read_json(json_fp)

	return import_tbl_to_redis(tbl)
end

return {
  -- redis
	rset = rset,
	rget = rget,
	rget_path = rget_path,
	rhget_refid = rhget_refid,
	rget_prefix = rget_prefix,
	rget_keys = rget_keys,
  redis_set = set_kv3df,
  set_kv3df = set_kv3df,
  hset_tbl = hset_rcrd,
  hset_rcrd = hset_rcrd,
  hget_tbl = hget_fields,
  hget_fields = hget_fields,
	fn2fp = fn2fp,
	rhset_refid_fp_lnum = rhset_refid_fp_lnum,
	mFn2Fp_lua = mFn2Fp_lua,
	mFn2Fp_redis = mFn2Fp_redis,
	mFn2Fp = mFn2Fp_redis,
	map_oldfiles = map_oldfiles,
	fuzzyLookup = fuzzyLookup,
	importWikilinkIntoRedis = importWikilinkIntoRedis,
	params_to_redis = params_to_redis,
	redis_to_fn = redis_to_fn,
	import_tbl_to_redis = import_tbl_to_redis,
	import_json_file_to_redis = import_json_file_to_redis,
}

-- mert-utils-regex.lua


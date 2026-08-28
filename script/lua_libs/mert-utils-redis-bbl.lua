-- [[mert-utils-redis-bbl.lua]]
--
-- prn: [[mert-lua-utils.lua]]
-- prn: [[mert-utils-redis.lua]]
--
-- #rfr/toc
--   clsf: kms pipeline: bbl to bldt to rds    || ((e3b60cd7-2b32-4c7e-8408-5b9c1d9c6c86))
-- #rfr/ndx
-- .
  -- local function bldt2redis(tbl) || ((07ef6954-b0b2-409b-a453-7561422f31f0))
  -- local function get_bldt(uuid) || ((04e922ae-85e0-4604-ad8c-907ab555b51a))
  -- local function bbl2redis(ln, nxln) || ((19ef2511-f3f5-4905-beab-fe5ffe340bca))
-- bbl:

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

local function bldt2redis(tbl)
  -- id:: 07ef6954-b0b2-409b-a453-7561422f31f0
  -- date:: 20251210
	-- rltd: local function import_tbl_to_redis(tbl) || ((7555ea11-7ee8-4bd8-ad33-f2db8aad27dd))
	-- mplm: [[20251209-redis-set-status--from-cln-attr-03.lua]]
	-- Import a logseq block table into redis. Uses redis sets for tags element.
	-- renamed: hset_tbl_block -> hset_bldt -> bldt2redis
	--
  local redis = get_redis()

  for _, item in ipairs(tbl) do
    local base_key = "refid:" .. item.id  -- e.g., "refid:cae99f5b-..."

    -- Store simple fields in the main hash (exclude 'tags')
    for field, value in pairs(item) do
      if field ~= "id" and field ~= "tags" then
        redis:hset(base_key, field, tostring(value))
      end
    end

    -- Store tags as a set (sub-key)
    if item.tags and type(item.tags) == "table" then
      local tags_key = base_key .. ":tags"
      for _, tag in ipairs(item.tags) do
        redis:sadd(tags_key, tag)  -- SADD: adds uniquely
      end
    end
  end
end

local function bbl2redis(ln, nxln)
  -- id:: 19ef2511-f3f5-4905-beab-fe5ffe340bca
  -- date:: 20260211
	-- pprv: local function set_block(ln, nxln) || ((10a57e93-e331-4478-8b3a-30109da5798c))
	-- f/ffcf::  local function set_block_cln() || ((08d09ccf-0267-43ae-89e4-e7e76f2a2845)) 
	-- f/mplm::  [[20251209-redis-set-status--from-cln-attr-03.lua]]
	local bldt = u.u.bbl2bldt(ln, nxln)
	local blocks = {bldt}
	bldt2redis(blocks)
	return blocks
end

-- local function get_bldt(uuid)
--   -- id:: 04e922ae-85e0-4604-ad8c-907ab555b51a
--   -- date:: 20251210
-- 	-- f/mplm::  [[20251209-redis-set-status--from-cln-attr-03.lua]]
-- 	-- renamed: get_block -> get_bldt
-- 	return hget_tbl_block(uuid)
-- end
--
local function get_bldt(uuid)
  -- id:: a8d07b5e-3ae1-4750-9a8f-3373c45538f4
  -- date:: 20251210
	-- rltd: local function rhget_refid(refid) || ((c9de1b95-69d2-481c-8d5e-78b58e2cb521))
	-- mplm: [[20251209-redis-set-status--from-cln-attr-03.lua]]
	-- renamed: hget_tbl_block -> get_block -> get_bldt
  local redis = get_redis()
  local rs = redis:hgetall("refid:" .. uuid)
  rs.id = uuid

  -- Fetch tags as a set and convert to Lua array
  local tags_key = "refid:" .. uuid .. ":tags"
  local tags_set = redis:smembers(tags_key)  -- Returns Lua array of strings (unordered)
  if #tags_set > 0 then
    rs.tags = tags_set  -- Assign as array
  else
    rs.tags = {}  -- Empty if no tags
  end

  return rs
end

local function set_block(ln, nxln)
	-- code: local function set_block(ln, nxln) || ((10a57e93-e331-4478-8b3a-30109da5798c))
	local opts = {tag = true}
	local ttl = u.u.bbl_title(ln, opts)
	local dc = u.u.bbl2tbl(ttl)
	local uuid = u.u.bbl_uuid(nxln)
	dc.id = uuid
	local pat_status = "^s/"
	dc.status = u.u.tbl_find_first_match(dc.tags, pat_status)
	local block = {
		{
			id = dc.id,
			status = dc.status,
			tags = dc.tags,
			title = dc.title
		}
	}
	bldt2redis(block)
end

return {
  -- redis
	hset_bldt = bldt2redis,  -- f/dprc
	bldt2redis = bldt2redis,
	bbl2redis = bbl2redis,
	get_bldt = get_bldt,
	hset_tbl_block = bldt2redis,  -- f/dprc
	hget_tbl_block = get_bldt,  -- f/dprc
}

-- mert-utils-regex.lua


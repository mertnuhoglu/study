-- [[mert-utils-text-bbl.lua]]
--
-- prn: [[mert-lua-utils.lua]]
-- prn: [[mert-utils-text.lua]]
-- rltd: [[mert-utils-text-refid.lua]]
-- exmp: [[20260210-lua-bbl-parse-01.lua]]
--
-- #rfr/toc
--   clsf: kms pipeline: bbl to bldt to rds    || ((e3b60cd7-2b32-4c7e-8408-5b9c1d9c6c86))
-- #rfr/ndx
-- .
-- to-text:
  -- local function bbl_uuid(line) || ((3a3674ca-4b68-49b7-a767-749d166f7aa5))
  -- local function bbl_title(line, opts) || ((97b978ba-7827-4edb-bcd3-eb877b2ca506))
-- to-tbl:
  -- local function bbl2tbl(input) || ((70c0ee87-ab73-41d3-bea1-cee292a1b060))
  -- local function bbl2bldt(ln, nxln)  -- SPC re || ((853e75f0-db08-4fd6-97a7-e1e00d37ef67))
-- to-edn:
  -- local function bbl2edn(ln, nxln)  -- SPC re || ((d3572b23-71c2-41a0-a408-738af70dac9b))
  -- local function bldt2edn(bldt) || ((86bd24ed-14ed-46b4-b211-54417d08b864))

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

-- Helper: extract UUID from a line (same regex)
local function bbl_uuid(line)
  -- id:: 3a3674ca-4b68-49b7-a767-749d166f7aa5
  -- date:: 20260210
  -- Capture only inside ((uuid)) — case-insensitive hex digits
	-- mplm: [[20260210-lua-bbl-parse.lua]]
	-- exmp: [[20260210-lua-bbl-parse-01.lua]]
	-- vim: function! LogseqUuidExtract(line) "  || ((7562e184-6b4f-4bde-a20e-a6b3e55e6bdd))
	-- renamed: LogseqUuidExtract -> logseq_uuid_extract -> bbl_uuid
  line = line or ""

  -- Style 1: ((uuid)) block reference
  local uuid = line:match("%(%(([0-9a-fA-F%-]+)%)%)")
  if uuid and #uuid == 36 and uuid:find("^%x%x%x%x%x%x%x%x%-%x%x%x%x%-%x%x%x%x%-%x%x%x%x%-%x%x%x%x%x%x%x%x%x%x%x%x$") then
    return uuid:lower()
  end

  -- Style 2: id:: uuid  (property drawer / block id)
  uuid = line:match("id%s*::%s*([0-9a-fA-F%-]+)")
  if uuid and #uuid == 36 and uuid:find("^%x%x%x%x%x%x%x%x%-%x%x%x%x%-%x%x%x%x%-%x%x%x%x%-%x%x%x%x%x%x%x%x%x%x%x%x$") then
    return uuid:lower()
  end

  -- Style 3: loose UUID anywhere in the line (fallback)
  uuid = line:match("([0-9a-fA-F]{8}%-[0-9a-fA-F]{4}%-[0-9a-fA-F]{4}%-[0-9a-fA-F]{4}%-[0-9a-fA-F]{12})")
  if uuid then
    return uuid:lower()
  end

  return ""
end

-- Simple parser: title + backticked tags
local function bbl2tbl(input)
  -- id:: 70c0ee87-ab73-41d3-bea1-cee292a1b060
  -- date:: 20260210
	-- mplm: [[20260210-lua-bbl-parse.lua]]
	-- exmp: [[20260210-lua-bbl-parse-01.lua]]
	-- vim: function! LogseqBlockTitle2Dict(input) || ((f35ac646-b6c8-47e8-951d-88392cdd76a6))
	-- renamed: LogseqBlockTitle2Dict -> logseq_block_title_2_dict -> bbl2tbl
	-- [grok](https://grok.com/share/bGVnYWN5_715de8a9-8f0c-4b19-8722-70b17755ff22)
	-- Remove everything after the last # that looks like tags
	-- (very simple version – works when tags are at the end)

	local title_part = input:match("^(.-)%s+#")     -- everything before first #
	if not title_part then
		-- no tags → whole line is title
		return { title = input:match("^%s*(.-)%s*$") or "", tags = {} }
	end

	local tags_part = input:sub(#title_part + 1)

	local tags = {}
	for tag in tags_part:gmatch("#([%w/%-.:]+)") do
		table.insert(tags, tag)
	end

	local title = title_part:match("^%s*(.-)%s*$") or ""

	return { title = title, tags = tags }
end

---@param line string
---@param opts? table
---@return string
local function bbl_title(line, opts)
  -- id:: 97b978ba-7827-4edb-bcd3-eb877b2ca506
  -- date:: 20260210
	-- mplm: [[20260210-lua-bbl-parse.lua]]
	-- exmp: [[20260210-lua-bbl-parse-01.lua]]
	-- vim: function! LogseqBlockTitleExtractPure(line, opts = {})  "  || ((e9196ada-b5f0-4bfa-8b80-fd5982207892))
	-- renamed: LogseqBlockTitleExtractPure -> logseq_block_title_extract_pure -> bbl_title
  opts = opts or {}
  local l = line

  l = l:gsub("%s*<url:file[^>]+>", "")
  l = l:gsub("^##+%s*", "")
  l = l:gsub("#f/log%s+", "f/log ")
  l = l:gsub("%sTODO", " #TODO")

  if opts.tag == true then
    local tags = {}
    l = l:gsub("#([%w/%-]+)%f[: ]", function(tag)
      table.insert(tags, tag)
      return ""
    end):gsub("%s+$", "")

    if #tags > 0 then
      l = l .. " `" .. table.concat(tags, " ") .. "`"
    end
  else
    l = l:gsub("#[%w][%w-/:]*:?", "")
  end

  l = l:gsub("%(%([^%)]+%)%)%s*", "")
  l = l:gsub("^%s*[-*+]%s+", "")
  l = l:gsub("^%s*#+%s+", "")
  -- Keep trailing colon if it was part of the title
  -- l = l:gsub(":%s*$", "")   ← comment out if you want to preserve :

  l = l:gsub("%s*||%s*", " ")
  l = l:gsub("^%s*%(", "")

  return l:gsub("%s+", " "):gsub("^%s+", ""):gsub("%s+$", "")
end

local function bbl2title(line, opts)
  -- id:: bcbf1be6-a88e-4c1d-a0e6-28c1ba3c927b
  -- date:: 20260305
  -- Translated from LogseqLineExtractWithoutIdString(line, opts = {})
	-- renamed: logseq_line_extract_without_id_string -> bbl2title
  local line02 = bbl_title(line, opts)
  local line05 = line02:gsub(' id=g%d+%s*$', '', 1)
  local line06 = line05:gsub('%[([^%]]+)%](https?:[^)]+)', '%1', 1)
  return line06
end

local function bbl2title_w_url(line, opts)
  -- id:: 22ac9b86-20c2-4c01-b5fc-ebce4ecc1184
  -- date:: 20260325
  local line02 = bbl_title(line, opts)
  local line05 = line02:gsub(' id=g%d+%s*$', '', 1)
  return line05
end

local function bbl2bldt(ln, nxln)  -- SPC re
  -- id:: 853e75f0-db08-4fd6-97a7-e1e00d37ef67
  -- date:: 20260210
	-- clsf: clsf: kms pipeline: bbl to bldt to rds    || ((e3b60cd7-2b32-4c7e-8408-5b9c1d9c6c86))
	-- mplm: [[20260210-lua-bbl-parse.lua]]
	-- exmp: fnl: [[20260217-ppln--bbl-bldt-rds-edn.lua]]
	-- exmp: pprv: [[20260210-lua-bbl-parse-01.lua]]
	-- pprv: local function bbl2bldt(ln, nxln) || ((07798a71-c819-4b21-b5a2-51465b80217f))
	-- npt:
	--   ln = "Title of some task #s/cancel #f/tag"
	--   nxln = "  id:: cae99f5b-abb6-407b-b366-157d79ffffff"
	-- tpt:
	--   {
	--     id = "",
	--     tags = { "s/cancel" },
	--     title = "Title of some task #f/tag"
	--   }
	local opts = {tag = true}
	local dc = bbl2tbl(ln)
	local uuid = bbl_uuid(nxln)
	dc.id = uuid
	local pat_status = "^s/"
	dc.status = u.u.tbl_find_first_match(dc.tags, pat_status)
	return dc
end

local function bldt2edn(bldt)
  -- id:: 86bd24ed-14ed-46b4-b211-54417d08b864
  -- date:: 20260107
	-- called-by: local function cblock_to_edn(tbl) || ((ecf94541-a0f0-4c62-b1b5-f87b063a8eee))
	-- renamed: block_to_edn -> bldt_to_edn -> bldt2edn
	
	-- mplm: [[20260107-tfrm-block-to-bldt-edn.lua]]
  -- 1. Convert the tags table to a list of quoted strings
  local tag_list = {}
  if bldt.tags then
    for _, tag in ipairs(bldt.tags) do
      table.insert(tag_list, string.format('"%s"', tag))
    end
  end

  -- 2. Join tags with spaces and wrap in brackets (EDN vector format)
  local tags_str = "[" .. table.concat(tag_list, " ") .. "]"

  -- 3. Construct the final map string
  -- Note: We use strict ordering here to match your requested output string exactly.
  return string.format(
    '{:id "%s", :tags %s, :title "%s"}',
    bldt.id,
    tags_str,
    bldt.title
  )
end

local function bbl2edn(ln, nxln)  -- SPC re
  -- id:: d3572b23-71c2-41a0-a408-738af70dac9b
  -- date:: 20260210
	-- mplm: [[20260210-lua-bbl-parse.lua]]
	-- exmp: [[20260210-lua-bbl-parse-01.lua]]
	-- npt:
	--   ln = "Title of some task #s/cancel #f/tag"
	--   nxln = "  id:: cae99f5b-abb6-407b-b366-157d79ffffff"
	-- tpt:
	--   '{:id "", :tags ["s/cancel"], :title "Title of some task #f/tag"}'
	local dc = bbl2bldt(ln, nxln)
	local result = bldt2edn(dc)
	return result
end

return {
	bbl_uuid = bbl_uuid,
	logseq_uuid_extract = bbl_uuid,  -- deprecated
	bbl2tbl = bbl2tbl,
	bbl_title = bbl_title,
	logseq_block_title_extract_pure = bbl_title,  -- deprecated
	logseq_line_extract_without_id_string = bbl2title,  -- deprecated
	bbl2title = bbl2title,
	bbl2title_w_url = bbl2title_w_url,
	block_to_edn = bldt2edn,  -- deprecated
	bldt2edn = bldt2edn,
	bbl2edn = bbl2edn,
	bbl2bldt = bbl2bldt,
}


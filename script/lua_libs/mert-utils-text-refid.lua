-- [[mert-utils-text-refid.lua]]

-- rltd: [[mert-utils-text-bbl.lua]]
-- prn: [[mert-lua-utils.lua]]
-- prn: [[mert-utils-text.lua]]
-- exmp: [[20260306-exmp-put_bref_2_ndx_ln.lua]]
--
-- #rfr/toc
-- #rfr/ndx
-- .
  -- local function run_on_each_heading__put_bref_2_ndx_ln(content) || ((f56b7543-a332-40ae-acbd-22d1077cc5c9))
  -- local function put_bref_2_ndx_ln(ln, nxln) || ((255bc016-8e07-403e-abaa-5d8b833472b2))
  -- local function put_bref_2_ndx_by_tag(ln, nxln, tag) || ((9d2a9dda-869a-45e0-9deb-c3f34e0b7dbb))
  -- local function line_contains_logseq_id(line_text) || ((d5db1d04-2219-464d-9876-4d54da550a99))
  -- local function ref_id_fl(line, nxln, opts) || ((f1576a8d-bf33-4c1a-85d5-9c714ea352d4))

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

local function ref_id_fl(line, nxln, opts)
  -- id:: f1576a8d-bf33-4c1a-85d5-9c714ea352d4
  -- date:: 20260310
  -- Translated from RefIdLogseqFl(line, nxln, opts = {})
  local line2 = bbl2title(line, opts)
  local uuid = bbl_uuid(nxln)
  local uuid_ref = "((" .. uuid .. "))"
  local ref = uuid_ref .. " || " .. line2
  local ref2 = line2 .. " || " .. uuid_ref
  return ref2
end

local function line_contains_logseq_id(line_text)
  -- id:: d5db1d04-2219-464d-9876-4d54da550a99
  -- date:: 20260305
  -- Translated from LineContainsLogseqId(ln)
	--
  -- Use [0-9a-fA-F] instead of %x → compatible with Lua 5.1
  local uuid_pattern = "id:: [0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]"
                      .. "[0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]"
                      .. "%-[0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]"
                      .. "%-[0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]"
                      .. "%-[0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]"
                      .. "%-[0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]"
                      .. "[0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]"
                      .. "[0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]"

  return line_text:match(uuid_pattern) ~= nil
end

--- Put block reference into <ns___tag>.md and ndx-journal.txt
--- @param tag string
--- @return string the created reference string
local function put_bref_2_ndx_by_tag(ln, nxln, tag)
  -- id:: 9d2a9dda-869a-45e0-9deb-c3f34e0b7dbb
  -- date:: 20260310
	-- translated-from: PutBref2NdxByTag(tag)  " || ((0dd0cb9b-4fd4-422a-9ecd-6f14e388ec6d))
  local path = u.u.get_path_by_tag(tag)   -- ← you need to implement / already have similar
  -- If you don't have get_logseq_path_by_tag yet, you can temporarily use:
  -- local path = vim.fn.GetPathByTag(tag)   -- but better to port this too

  local opts = { tag = true }
  -- local ref = ref_id(opts)                 -- reuse existing ref_id(_logseq) function
  local ref = ref_id_fl(ln, nxln, opts)                 -- reuse existing ref_id(_logseq) function

  u.u.put_line_to_end(path, ref)
  -- Assuming you already have / will port PuttoNdxJournal
  -- If not yet ported, you can inline a simple version:
  u.u.put_line_to_end("/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/pages/ndx-journal.txt", ref)

  return ref
end

--- Parse current line, extract first tag, put bref into corresponding files
--- @param ln string
--- @return string the created reference
local function put_bref_2_ndx_ln(ln, nxln)
  -- id:: 255bc016-8e07-403e-abaa-5d8b833472b2
  -- date:: 20260306
	-- translated-from: PutBref2NdxLn(ln) || ((c546b589-bfe1-47ee-91b3-11d395f5be33))
	-- exmp: [[20260306-exmp-put_bref_2_ndx_ln.lua]]
  local bldt = u.u.bbl2tbl(ln)   -- already exists
  local tags = bldt.tags or {}
  if #tags == 0 then
    print("No tags found in line")
    return ""
  end

  local tag0 = tags[1]
  return put_bref_2_ndx_by_tag(ln, nxln, tag0)
end

--- Process a Logseq-style markdown content and call put_bref_2_ndx_ln for each heading + id pair
--- @param content string  full file/buffer content
--- @return table  list of successfully processed blocks {heading_line, id_line, result?}
local function run_on_each_heading__put_bref_2_ndx_ln(content)
	-- id:: f56b7543-a332-40ae-acbd-22d1077cc5c9
	-- date:: 20260309
	-- mplm: [[20260309-run-on-each-heading--put_bref_2_ndx_cln.lua]]
	local processed = {}
	local lines = {}

	-- split into lines (adding sentinel newline)
	for line in (content .. "\n"):gmatch("(.-)\n") do
		table.insert(lines, line)
	end

	local i = 1
	while i <= #lines do
		local line = lines[i]

		-- Look for a heading line
		-- Typical pattern: optional indent + - + optional spaces + ##? + title + optional tags
		local is_heading = line:match('^%s*-%s*##?%s+.+')
		and not line:match('id::')     -- exclude id lines themselves

		if is_heading then
			local heading_line = line:gsub('%s+$', '')

			-- Look at the next non-empty line for the id::
			local next_nonempty_idx = i + 1
			while next_nonempty_idx <= #lines and lines[next_nonempty_idx]:match('^%s*$') do
				next_nonempty_idx = next_nonempty_idx + 1
			end

			local id_line = nil
			if next_nonempty_idx <= #lines then
				local candidate = lines[next_nonempty_idx]
				if candidate:match('id::%s*%x%x%x%x%x%x%x%x%-%x%x%x%x%-%x%x%x%x%-%x%x%x%x%-%x%x%x%x%x%x%x%x%x%x%x%x') then
					id_line = candidate:gsub('^%s*', ''):gsub('%s+$', '')
				end
			end

			-- Call the function if we have both parts (id is optional but usually present)
			if heading_line then
				local result = put_bref_2_ndx_ln(heading_line, id_line or "")
				table.insert(processed, {
					heading = heading_line,
					blockref = id_line,
					result = result,   -- whatever the function returns (often the key)
				})
			end

			-- Skip to after the id line if it existed
			if id_line then
				i = next_nonempty_idx + 1
			else
				i = i + 1
			end
		else
			i = i + 1
		end
	end

	return processed
end

return {
	line_contains_logseq_id = line_contains_logseq_id,
	ref_id_fl = ref_id_fl,
	put_bref_2_ndx_by_tag = put_bref_2_ndx_by_tag,
	put_bref_2_ndx_ln = put_bref_2_ndx_ln,
	run_on_each_heading__put_bref_2_ndx_ln = run_on_each_heading__put_bref_2_ndx_ln,
}


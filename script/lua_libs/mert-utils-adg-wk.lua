-- [[mert-utils-adg-wk.lua]]
-- ~/prj/study/script/lua_libs/mert-utils-adg-wk.lua

local u = {}

-- Use a metatable to lazily load mert-lua-utils
local mt = {
    __index = function(t, key)
        if key == "u" then
            local utils = require("mert-lua-utils")
            rawset(t, "u", utils)
            return utils
        end
    end
}

setmetatable(u, mt)

local function make_stnd(lhs, rhs)
  -- mplm: [[20250421-dfl-wk-setup.lua]]
	local s0 = u.u.innerJoin(lhs, rhs)
	local s1 = u.u.removeElem(s0, "_path")
	local stnd = u.u.renameElem(s1, "_name", "name")
	return stnd
end

local function tfrm_edits_to_root(input)
  -- mplm: [[20250508-mnml-stnd-to-root.lua]]
	local output = {}

	for key, value in pairs(input) do
		if type(value) == "table" then
			-- Check if the table is an array (has numeric indices)
			local is_array = false
			for k, _ in pairs(value) do
				if type(k) == "number" and k >= 1 and math.floor(k) == k then
					is_array = true
					break
				end
			end

			if is_array then
				-- Process array-like tables
				output[key] = {}
				for i, item in ipairs(value) do
					if i == 1 and type(item) == "string" and item:match("^<cmd>e ") then
						-- Extract file name from command string
						local file_name = item:match("pages/([^<]+)")
						output[key][i] = file_name or item -- Fallback to original if no match
					else
						-- Copy other elements as-is
						output[key][i] = item
					end
				end
			else
				-- Recursively process non-array tables
				output[key] = tfrm_edits_to_root(value)
			end
		elseif key == "name" then
			-- Copy "name" field directly
			output[key] = value
		else
			-- Copy non-table, non-name values as-is
			output[key] = value
		end
	end
	table.sort(output)

	return output
end

local function tfrm_root2wk(input, prefix, postfix, substitute_slash)
  -- id:: 6fa68401-3b1c-46bd-b0c6-041f70787bf6
  -- mplm: [[20250505-mnml-root-to-stnd.lua]]
		-- [[20251017-mnml-root-to-stnd.lua]]
	local output = {}

	for key, value in pairs(input) do
		if type(key) == "string" and #key > 1 and key ~= "name" then
			-- Split multi-char key into nested single-char keys
			-- ex: "aoe" -> "a" "o" "e"
			local chars = {}
			for i = 1, #key do
				chars[i] = key:sub(i, i)
			end
			local current = output
			for i = 1, #chars - 1 do
				local c = chars[i]
				if not current[c] then
					current[c] = {name = c}
				end
				current = current[c]
			end
			local last_c = chars[#chars]
			-- Transform the value
			local transformed_value
			if type(value) == "table" then
				local fn = value[1]
				if #value == 2 and type(fn) == "string" and type(value[2]) == "string" then
					if substitute_slash then
						fn = string.gsub(fn, "/", "___")
					end
					transformed_value = {prefix .. fn .. postfix, value[2]}
				else
					transformed_value = tfrm_root2wk(value, prefix, postfix, substitute_slash)
				end
			else
				transformed_value = value
			end
			if not current[last_c] then
				current[last_c] = transformed_value
			else
				print("Warning: key " .. last_c .. " already exists in nested structure")
			end
		else
			if key == "name" then
				output[key] = value
			elseif type(value) == "table" then
				local fn = value[1]
				local count = 0
				if #value == 2 and type(fn) == "string" and type(value[2]) == "string" then
					if substitute_slash then
						fn, count = string.gsub(fn, "/", "___")
					end
					output[key] = {prefix .. fn .. postfix, value[2]}
				else
					output[key] = tfrm_root2wk(value, prefix, postfix, substitute_slash)
				end
			else
				output[key] = value
			end
		end
	end
	table.sort(output)

	return output
end

function tfrm_root2edits(input)
  -- id:: 8842aaec-1f94-4f38-8ff0-c8a91722b39a
	local prefix = "<cmd>e ~/prj/myrepo/logseq-myrepo/pages/"
	local postfix = "<cr>"
	local substitute_slash = true
	return tfrm_root2wk(input, prefix, postfix, substitute_slash)
end

function tfrm_root2tags(input)
  -- id:: ce490b0f-f0e3-42a0-aba7-6cbd25ff0b92
	-- Check if input is a string and not nil
	local prefix = "<cmd>call AppendTag('#"
	local postfix = "')<cr>"
	local substitute_slash = false
	return tfrm_root2wk(input, prefix, postfix, substitute_slash)
end

function tfrm_root2brefs(input)
	local prefix = "<cmd>call PutBref2NdxGeneric('~/prj/myrepo/logseq-myrepo/pages/"
	local postfix = "')<cr>"
	local substitute_slash = true
	return tfrm_root2wk(input, prefix, postfix, substitute_slash)
end

function tfrm_root2edit_block_by_tags(input)
  -- id:: 96e7abda-94cb-4ede-b9b5-c84db8ef59a7
  -- date:: 20251119
	local prefix = "<cmd>e /Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20250312-filter-outline/.out/20251001-xtrc-blocks-blocks_by_tags-"
	local postfix = "<cr>"
	local substitute_slash = true
	-- print("tfrm_root2edits: substitute 01: " .. tostring(substitute_slash))
	return tfrm_root2wk(input, prefix, postfix, true)
end

local function merge_cstm_w_stnd(arg_fp, wk_type)
  -- id:: 2286a182-578d-4cdf-97ea-3694ccea663d
  -- mplm: local function mnml_update_parametric_20250422(arg_fp, wk_type) || ((096111d1-966c-420d-a6c2-abf0e76a2439))
  -- debug: [[20250513-debug-merge_cstm_w_stnd.lua]]
  --   [[20250614-debug-merge_cstm_w_stnd.lua]]
	--   [[20250910-debug-merge_cstm_w_stnd.lua]]
	local original = wk_type .. '_original'
	local cstm = wk_type .. '_cstm'
	local stnd = wk_type .. '_stnd'
	local tmp = wk_type .. '_tmp'
	local pnxt = wk_type .. '_pnxt'

	local arg = u.u.read_json(arg_fp)
	local m1 = u.u.GetDefaultFlPtArgs()
	m1.flPt = arg[original]
	local wk_pnxt_fn = u.u.nm_w_dt_pre_ts_post(m1)
	local cdir = vim.fn.fnamemodify(m1.flPt, ":p:h")
	arg[pnxt] = cdir .. "/" .. wk_pnxt_fn
	local arg_dir = vim.fn.fnamemodify(arg_fp, ":p:h")
	local arg_fn = vim.fn.fnamemodify(arg_fp, ":t:r")
	local arg_gnrt_fp = arg_dir .. '/' .. arg_fn .. '-gnrt.json'
	u.u.write_json(arg, arg_gnrt_fp)
	local dfl = {
		name = u.u.getScriptFilename() .. "--" .. u.u.getCurrentFunctionName(),
		inlist = {arg[stnd], arg[cstm]},
		outlist = {arg_gnrt_fp, arg[pnxt]},
		ts = u.u.get_ts(),
	}
	u.u.write_outlist_cfile(dfl)

	local wk_cstm_str = u.u.read_file(arg[cstm])
	local wk_cstm = load(wk_cstm_str)()
	local stnd_str = u.u.read_file(arg[stnd])
	local wk_stnd = load(stnd_str)()

	-- local wk_pnxt = utils.deep_merge(wk_stnd, wk_cstm)
  -- [grok](https://grok.com/share/bGVnYWN5_2b95fc3d-78b8-4880-8237-54d5b61d7815)
	local wk_pnxt = u.u.deep_merge(wk_stnd, wk_cstm, "root")
	u.u.serialize_obj(wk_pnxt, arg[pnxt])

  -- prdt: predate
	local m = u.u.GetDefaultFlPtArgs()
	m.flPt = arg[stnd]
	local stnd_prdt = vim.fn.GetFpInDirOfThatFl(vim.g.fn.NmWDt, m)
	u.u.cp8mv(arg[stnd], stnd_prdt)

	local m = u.u.GetDefaultFlPtArgs()
	m.flPt = arg[cstm]
	local cstm_prdt = vim.fn.GetFpInDirOfThatFl(vim.g.fn.NmWDt, m)
	u.u.cp8mv(arg[cstm], cstm_prdt)

	u.u.cp8mv(arg[original], arg[tmp])
	u.u.cp8mv(arg[pnxt], arg[original])

	-- otl output
  -- local otl = u.u.tableToOutline(wk_cstm)
	local otl_stnd = u.u.clean_wk_otl(u.u.tableToOutline(wk_stnd))
	local m = u.u.GetDefaultFlPtArgs()
	m.flPt = arg[stnd]
	m.ext = ".otl"
	local otl_stnd_fp = vim.fn.GetFilePath(vim.g.fn.NmFCFileChangeExt, vim.g.fn.DirLazyvimConfig, m)
	u.u.write_file(otl_stnd, otl_stnd_fp)
	u.u.write_runlog("otl_stnd: " .. otl_stnd_fp)

	local otl_cstm = u.u.clean_wk_otl(u.u.tableToOutline(wk_cstm))
	m.flPt = arg[cstm]
	m.ext = ".otl"
	local otl_cstm_fp = vim.fn.GetFilePath(vim.g.fn.NmFCFileChangeExt, vim.g.fn.DirLazyvimConfig, m)
	u.u.write_file(otl_cstm, otl_cstm_fp)
	u.u.write_runlog("otl_cstm: " .. otl_cstm_fp)

	local otl_pnxt = u.u.clean_wk_otl(u.u.tableToOutline(wk_pnxt))
	m.flPt = arg[pnxt]
	m.ext = ".otl"
	local otl_pnxt_fp = vim.fn.GetFilePath(vim.g.fn.NmFCFileChangeExt, vim.g.fn.DirLazyvimConfig, m)
	u.u.write_file(otl_pnxt, otl_pnxt_fp)
	u.u.write_runlog("otl_pnxt: " .. otl_pnxt_fp)

	m.flPt = arg[original]
	m.ext = ".otl"
	local otl_original_fp = vim.fn.GetFilePath(vim.g.fn.NmFCFileChangeExt, vim.g.fn.DirLazyvimConfig, m)
	u.u.write_file(otl_pnxt, otl_original_fp)
	u.u.write_runlog("otl_original: " .. otl_original_fp)
end

local function helloworld_adgwk()
	print("hello from mert-utils-adg-wk.lua 11")
end

local function tfrm_root2listtags(root)
  -- id:: 842a4068-ccff-4d88-90d0-fb586c85697e
  -- date:: 20251119
	-- spcs: Tfrm: wk-root to wk-tags    || ((7785302b-f587-4bad-a02a-b547ce0d2b7a))
	-- mplm: [[20251119-mnml-root-to-list-tags.lua]]
	--
	local seen = {}     -- to eliminate duplicates
	local result = {}

	local function recurse(tbl)
		for _, v in pairs(tbl) do
			if type(v) == "table" then
				-- Leaf node: { "group___slug___maybe___more.md", "display name" }
				if #v >= 2 and type(v[1]) == "string" and v[1]:match("%.md$") then
					local filename = v[1]

					local stem = filename:gsub("%.md$", "")          -- remove .md
					local group, slug = stem:match("^(.-)___(.*)$")  -- split on FIRST ___

					local hashtag
					if group and slug then
						slug = slug:gsub("___", "/")
						hashtag = "#" .. group .. "/" .. slug
					else
						-- safety fallback (no ___ at all)
						hashtag = "#" .. stem:gsub("___", "/")
					end

					if not seen[hashtag] then
						seen[hashtag] = true
						table.insert(result, hashtag)
					end

				else
					-- recurse deeper
					recurse(v)
				end
			end
		end
	end

	recurse(root)

	table.sort(result)                -- alphabetical order
	return table.concat(result, "\n")
end

local function tfrm_root2kypn_tags(current)
  -- id:: d9c2833d-6f9c-412a-8c98-72fd492b4759
  -- date:: 20251125
	-- mplm: [[20251125-4-wk-root-9-xtrc-fn-if-kypn.lua]]
  local result = {}
  local function recurse(curr, res)
    for k, v in pairs(curr) do
      if type(v) == "table" then
        if v.name ~= nil then
          -- Category: recurse into sub-items excluding 'name'
          local sub = {}
          for sk, sv in pairs(v) do
            if sk ~= "name" then
              sub[sk] = sv
            end
          end
          recurse(sub, res)
        else
          -- Leaf: check for kypn == 1 and collect first element if present
          if v.kypn == 1 then
            table.insert(res, v[1])
          end
        end
      end
    end
  end
  recurse(current, result)
  return result
end

return {
	helloworld_adgwk = helloworld_adgwk,

	tfrm_edits_to_root = tfrm_edits_to_root,
	tfrm_root2wk = tfrm_root2wk,
	tfrm_root2edits = tfrm_root2edits,
	tfrm_root2tags = tfrm_root2tags,
	tfrm_root2brefs = tfrm_root2brefs,
	tfrm_root2edit_block_by_tags = tfrm_root2edit_block_by_tags,
	merge_cstm_w_stnd = merge_cstm_w_stnd,
	tfrm_root2listtags = tfrm_root2listtags,
	tfrm_root2kypn_tags = tfrm_root2kypn_tags,


}

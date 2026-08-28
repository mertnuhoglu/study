-- [[mert-utils-io-logging.lua]]
--
-- prn: [[mert-lua-utils.lua]]

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

local function append_file(content, file_path)
  -- id:: af86ac11-c434-44ad-8d79-cf84424c1437
  -- date:: 20260202
  file = io.open(file_path, "a")
  file:write(content .. "\n")
  file:close()
end

local function write_file(content, file_path)
  -- id:: abcc98c7-93d4-4631-92a8-5b43cc29479a
  -- date:: 20260217
  file = io.open(file_path, "w")
  file:write(content .. "\n")
  file:close()
end

local function write_runlog(content)
  -- id:: b34d9836-a17f-415a-96c6-c6f40cf63fb6
  -- exmp: write to runlog in lua || ((b1d81750-5745-43ef-b2a4-00cea89720ec))
	-- exmp:
	-- u.u.write_runlog("otl_stnd: " .. otl_stnd_fp)
	--
  local fpath = "/Users/mertnuhoglu/prj/private_dotfiles/.config/params/runlog/runlog.txt"
  local c2 = u.u.get_ts() .. " type: " .. type(content) .. "\n" .. u.u.toStringVar(content)
  append_file(c2, fpath)
end

local function write_runlog_lua(content)
	-- id:: dc937f40-2eef-494e-99d0-bb7828022df3
  local fpath = "/Users/mertnuhoglu/prj/private_dotfiles/.config/params/runlog/runlog_lua.txt"
  local c2 = u.u.get_ts() .. ":\n" .. content
  append_file(c2, fpath)
end

local function write_outlist(out_files, fname)
  -- id:: bad28662-5e1e-4cd3-8228-e6eff4f63071
  -- exmp: write_outlist in lua || ((457f8a55-1c07-49dc-a421-059899fcbb69))
  -- Join out_files table with newlines and write to file
  local content = table.concat(out_files, "\n")
  local file = io.open(u.u.get_fp_outlist(fname), "w")
  if file then
    file:write(content)
    file:close()
  end
end

local function write_outlist_json(dfl, fname)
  -- id:: cfd342d6-8679-41b4-a7a1-9a5c67e77105
  -- date:: 20260202
  local json = require "dkjson"
  local json_str = json.encode(dfl, { indent = true })
  local file = io.open(u.u.get_fp_outlist(fname), "w")
  if file then
    file:write(json_str)
    file:close()
  end
end

local function write_outlist_fl(dfl)
	-- id:: 420aeb1d-fe26-4c43-aef8-31d9c32ff505
	-- exmp: [[20250325-exmp-write_outlist.lua]]
	--
  -- Assuming glue.glue is replaced with string.format or concatenation
  local fname = string.format("%s-%s.json", u.u.get_ts(), dfl.name)

  dfl.ts = u.u.get_ts()
  write_outlist_json(dfl, fname)
  local lines = {
    string.format("# %s outlist:", u.u.get_ts()),
    u.u.get_fp_outlist(fname)
  }
  local runlog_content = table.concat(lines, "\n")
  write_runlog(runlog_content)

  -- Format JSON using system command
  -- Note: This assumes 'jq' and 'sponge' are available in the system
  os.execute(string.format("jq 'to_entries | sort_by(.key) | from_entries' %s | sponge %s", u.u.get_fp_outlist(fname), u.u.get_fp_outlist(fname)))
end

local function write_bbl(content)
  -- id:: 448bc4f0-3076-4cd5-9d8a-7a7830d7de3d
  -- date:: 20260216
	-- clsf: kms pipeline: bbl to bldt to rds    || ((e3b60cd7-2b32-4c7e-8408-5b9c1d9c6c86))
	-- mplm: [[20260211-bldt-write_bbl.lua]]
	local m = u.u.get_dict_fl_pt_args2('edn', 'bldt')
	local nm_fn = u.u.nm_tmpl
	local nm = nm_fn(m)
	local dir_fn = u.u.get_dir_fn("bldt")
	local fp = u.u.get_file_path(nm_fn, dir_fn, m)
  local c2 = ";; " .. u.u.get_ts() .. " type: " .. type(content) .. "\n" .. u.u.toStringVar(content)
  write_file(c2, fp)
	return fp
end

--- GetLineToBePut: Prepare line for append
---@param line string
---@param path string
---@return string
local function get_line_to_be_put(line, path)
  -- id:: 1baf1974-5d17-476d-91b9-4709b55e082f
  -- date:: 20260307
  local ext = u.u.get_file_ext(path)
  local line01 = u.u.line2_comment4_ext(line, ext)
  local line02 = line01:gsub('- TODO ', '- #TODO ')
  return line02
end

--- Append transformed line to the end of the given file
--- @param path string
--- @param line string
--- @return string the line that was actually written
local function put_line_to_end(path, line)
  -- id:: d7062aec-08d6-442b-91a2-32fdd198f67a
  -- date:: 20260307
	-- translated-from: PutLineToEnd(path, line) || ((1458eeae-2b73-409f-a4a9-65d6f51e9081))
  local path2 = path:gsub("^~", "/Users/mertnuhoglu")   -- very user-specific → consider generalizing later
  local line_to_write = get_line_to_be_put(line, path2)

  -- Neovim-friendly append (list of lines)
  local file = io.open(path2, "a")
  if file then
    file:write(line_to_write .. "\n")
    file:close()
  else
    -- vim.notify("Could not open file for appending: " .. path2, vim.log.levels.WARN)
  end

  -- vim.notify("Put into: " .. path)
  return line_to_write
end

return {
	append_file = append_file,
  write_runlog = write_runlog,
  write_runlog_lua = write_runlog_lua,
	write_outlist = write_outlist,
	write_outlist_json = write_outlist_json,
	write_outlist_cfile = write_outlist_fl,
	write_outlist_fl = write_outlist_fl,
	write_bbl = write_bbl,
	get_line_to_be_put = get_line_to_be_put,
	put_line_to_end = put_line_to_end,
}
-- mert-utils-io-serialize.lua


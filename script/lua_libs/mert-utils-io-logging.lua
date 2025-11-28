-- [[mert-utils-io-logging.lua]]

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
  file = io.open(file_path, "a")
  file:write(content .. "\n")
  file:close()
end

local function write_runlog(content)
  -- id:: b34d9836-a17f-415a-96c6-c6f40cf63fb6
  -- exmp: write to runlog in lua || ((b1d81750-5745-43ef-b2a4-00cea89720ec))
	-- exmp:
	-- u.u.write_runlog("otl_stnd: " .. otl_stnd_fp)
	--
  local fpath = "/Users/mertnuhoglu/prj/private_dotfiles/.config/params/runlog.txt"
  local c2 = u.u.get_ts() .. " type: " .. type(content) .. "\n" .. u.u.toStringVar(content)
  append_file(c2, fpath)
end

local function write_runlog_lua(content)
	-- id:: dc937f40-2eef-494e-99d0-bb7828022df3
  local fpath = "/Users/mertnuhoglu/prj/private_dotfiles/.config/params/runlog_lua.txt"
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
  local json = require "dkjson"
  local json_str = json.encode(dfl, { indent = true })
  local file = io.open(u.u.get_fp_outlist(fname), "w")
  if file then
    file:write(json_str)
    file:close()
  end
end

local function write_outlist_cfile(dfl)
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

return {
  write_runlog = write_runlog,
  write_runlog_lua = write_runlog_lua,
	write_outlist = write_outlist,
	write_outlist_json = write_outlist_json,
	write_outlist_cfile = write_outlist_cfile,
}
-- mert-utils-io-serialize.lua


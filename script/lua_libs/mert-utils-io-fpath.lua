-- [[mert-utils-io-fpath.lua]]
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


function enew_npt_fl_f_fl(fn)
  -- id:: 216f8062-1c69-40d6-947a-27a70f2e118f
  -- date:: 20260314
	-- exmp: [[20260313-new-npt-fl-fc-fl.lua]]
	if type(fn) ~= "string" or fn == "" then
		error("filename must be a non-empty string", 2)
	end

	-- Clean up potentially dangerous characters (very basic sanitization)
	fn = fn:gsub("[/\\:%*%?\"<>|]", "_")

	local dir  = "/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20250320-scrap/data/" .. fn
	local file = dir .. "/" .. fn .. "-npt.txt"

	-- Create directory structure
	u.u.mkdir_p(dir)

	-- Create the file (empty)
	local f, err = io.open(file, "w")
	if not f then
		error("failed to create file: " .. tostring(err), 2)
	end

	f:close()
	print("Created: " .. file)
	local dfl = {
		name = "enew_npt_fl_fc_fl()",
		inlist = {fn},
		outlist = {fl},
	}
	u.u.write_outlist_fl(dfl)

	return file
end

return {
	enew_npt_fl_f_fl = enew_npt_fl_f_fl,
}

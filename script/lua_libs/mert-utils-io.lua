-- [[mert-utils-io.lua]]

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

local function cp(src_fp, dst_fp)
  -- [grok](https://grok.com/chat/64ef289e-708c-476b-9af2-0b9845c7692a)
  -- exmp: [[20250407-exmp-copy-file.lua]]
  -- Open source file for reading
	local source = io.open(src_fp, "rb")
	if not source then
		return false, "Cannot open source file"
	end

  -- Open destination file for writing
	local dest = io.open(dst_fp, "wb")
	if not dest then
		source:close()
		return false, "Cannot create destination file"
	end

  -- Read content and write to new file
	local content = source:read("*all")
	dest:write(content)

  -- Clean up
	source:close()
	dest:close()
	return true
end

local function mv(src_fp, dst_fp)
  -- id:: 664b85cd-76ff-40ab-9750-4b63eea39156
	local result, err = os.rename(src_fp, dst_fp)

	dfl = {
		name = "mv",
		inlist = {src_fp},
		outlist = {dst_fp},
		ts = u.u.get_ts(),
	}

	if result then
		print("File moved successfully!")
		u.u.write_outlist_cfile(dfl)
	else
		print("Error moving file: " .. err)
	end
end

local function cp8mv(src_fp, dst_fp)
  -- exmp: [[20250407-exmp-copy-file-02.lua]]
	local src_fn = u.u.get_fn(src_fp)
	local tmp_fp = "/Users/mertnuhoglu/tmp/" .. src_fn
	cp(src_fp, tmp_fp)
	mv(tmp_fp, dst_fp)
end

local function cp8mv_fn(fn_src, fn_dst)
  -- exmp: [[20250407-exmp-copy-file-02.lua]]
	local src_fp = u.u.fn2fp(fn_src)
	local dst_fp = u.u.fn2fp(fn_dst)
	cp8mv(src_fp, dst_fp)
end

return {
	cp = cp,
	mv = mv,
	cp8mv = cp8mv,
	cp8mv_fn = cp8mv_fn,
}

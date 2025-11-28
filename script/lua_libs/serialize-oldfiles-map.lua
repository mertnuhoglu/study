-- [[serialize-oldfiles-map.lua]]
--
-- mplm: [[20250815-serialize-oldfiles-map.lua]]

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

function serialize_oldfiles_map()
  -- id:: 2b3b7e66-d856-4be4-896e-03a6500c3a06
  -- tpt: 
	-- [[oldfiles.lua]]
	-- [[mFn2Fp.lua]]
	-- note: oldfiles diyor ama aslında tüm dosyalar: [[files_in_dirs.txt]]
  -- ~/prj/myrepo/scrap/out/oldfiles/oldfiles.lua
  -- f/rltd: function ExportOldfiles(filename) || ((dfcb38d6-228f-41a8-9eb4-8255238b25eb))
  -- dpnd: [[cron_vim_index.sh]]
	-- debug: [[20250809-debug-serialize_oldfiles_map.lua]]
  --
  local fp1 = "/Users/mertnuhoglu/prj/myrepo/scrap/out/oldfiles/files_in_dirs.txt"
  local input1 = u.u.read_file(fp1)
  local tbl1 = u.u.file_paths_to_map(input1)

  local fp2 = "/Users/mertnuhoglu/prj/myrepo/scrap/out/oldfiles/files_in_dirs.txt"
  local input2 = u.u.read_file(fp2)
  local tbl2 = u.u.file_paths_to_map(input2)
	local mFn2Fp = '/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/luapath/lua/mFn2Fp.lua'
  u.u.serialize_obj_utils(tbl2, mFn2Fp)

  local tbl3 = u.u.merge(tbl1, tbl2)
  local map_fpath = "/Users/mertnuhoglu/prj/myrepo/scrap/out/oldfiles/oldfiles.lua"
  u.u.serialize_obj_utils(tbl3, map_fpath)

end

return {
	serialize_oldfiles_map = serialize_oldfiles_map
}


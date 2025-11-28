-- [[repl_init.lua]]
--
-- spcs: Run lua script from terminal: Load common library functions first `prg/lua` || ((6b9fa937-b067-451e-af30-3f9021103d82))
--
-- initializer: [[lua_init.lua]]
--
-- [grok: initialize all lua script runs with repl_init.lua](https://grok.com/share/bGVnYWN5_fdf743a2-c00a-4c00-b830-b5496c52145f)
-- package.path = package.path .. ";/Users/mertnuhoglu/prj/study/script/lua_libs/?.lua"
-- require("repl_init")
--
-- mert-lua-stnd.lua
--
function mock_vim()
	-- Mock vim object
	--   id:: 84ac04d5-609e-4d8c-ab4e-b1d732d16254
	-- [grok](https://grok.com/chat/32486e9b-718a-46f7-99ab-e288dec1ba8b)
	--
	vim = {
		fn = {
			expand = function(str)
				if str == '%:p:h' then
					return os.getenv("HOME") .. "/prj/study/script"  -- Adjust as needed
				end
				return str
			end
		},
		keymap = {
			set = function(mode, lhs, rhs, opts)
				-- Mock implementation: Print the mapping details
				local desc = opts and opts.desc or "No description"
				print(string.format("Mock keymap: mode=%s, key=%s, action=%s, desc=%s",
					mode, lhs, tostring(rhs), desc))
				-- You could also store mappings in a table if needed:
				-- mappings[#mappings + 1] = {mode = mode, lhs = lhs, rhs = rhs, opts = opts}
			end
		}
	}
end

-- package.path: import lua files #prg/lua
--   id:: 5d075222-8e43-43f0-b25b-e59aaf3e72aa
package.path = package.path .. ";" .. os.getenv("HOME") .. "/.config/nvim/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/.config/lazyvim/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/.config/lazyvim/lua/config/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/luapath/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20240924-tfrm-wk-table/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241117-setops-ndx-files/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241117-setops-ndx-files/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241117-setops-ndx-files/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241117-setops-ndx-files/lua/?.lua"
package.path = package.path
	.. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241122-filter-edits-D-specs/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241121-edits-kybd/lua/?.lua"
package.path = package.path
	.. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241122-specs-8-edits-p-2-edits/lua/?.lua"

-- repl_init.lua as a module
local M = {}  -- Create a table to hold the module's functions

function M.hello()
    print("Hello from repl_init!")
end

function M.add(a, b)
    return a + b
end

function M.load_utils()
	package.loaded["mert-lua-utils"] = nil
	local u = require("mert-lua-utils")
	return u
end

return M  -- Return the module table

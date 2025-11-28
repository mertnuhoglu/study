-- [[20250821-debug-serialize_obj_utils.lua]]

package.loaded["mert-lua-utils"] = nil
-- local u = require("mert-lua-utils")

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

tbl = {a = 10}
utils_header = [[
package.loaded["user.mert-lua-utils"] = nil
local f = require("user.mert-lua-utils")
]]
serialized_table = u.u.serialize_obj_str(tbl, {}, utils_header)
-- 20250822-085058 inspecting: 
-- Variable name (local): x
-- Variable name (global): serialized_table
-- string
-- package.loaded["user.mert-lua-utils"] = nil
-- local f = require("user.mert-lua-utils")
-- return {
--   ["a"] = 10}

srl = u.u.serialize(tbl)


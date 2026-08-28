-- [[mert-utils-regex.lua]]

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

local function extract_uuid(line)
  -- id:: 3575bf88-d43d-41d5-9347-860ecf4336e7
  -- date:: 20260202
  local uuid = line:match("(%x%x%x%x%x%x%x%x%-%x%x%x%x%-%x%x%x%x%-%x%x%x%x%-%x%x%x%x%x%x%x%x%x%x%x%x)")
  return uuid
end

return {
  extract_uuid = extract_uuid,

}

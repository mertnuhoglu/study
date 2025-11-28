-- [[mert-utils-date.lua]]

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

local function get_dt()
    -- Returns date in YYYYMMDD format (equivalent to %Y%m%d)
    return os.date("%Y%m%d")
end

local function get_time()
    -- Returns time in HHMMSS format (equivalent to %H%M%S)
    return os.date("%H%M%S")
end

local function get_ts()
    -- Returns time in HHMMSS format (equivalent to %H%M%S)
    return get_dt() .. "-" .. get_time()
end

return {
  -- date
  get_dt = get_dt,
  get_time = get_time,
  get_ts = get_ts,
}


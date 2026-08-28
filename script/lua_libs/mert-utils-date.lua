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
  -- id:: ca794a7d-7b52-42b7-bf74-c7d7e162acdb
  -- date:: 20260202
    -- Returns date in YYYYMMDD format (equivalent to %Y%m%d)
    return os.date("%Y%m%d")
end

local function get_time()
  -- id:: 08bb2490-1a76-43e1-93fb-5cd1166d38eb
  -- date:: 20260202
    -- Returns time in HHMMSS format (equivalent to %H%M%S)
    return os.date("%H%M%S")
end

local function get_ts()
  -- id:: c8dd4e52-af1a-4a24-8f30-1a6678629b21
  -- date:: 20260202
    -- Returns time in HHMMSS format (equivalent to %H%M%S)
    return get_dt() .. "-" .. get_time()
end

return {
  -- date
  get_dt = get_dt,
  get_time = get_time,
  get_ts = get_ts,
}


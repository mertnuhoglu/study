#!/usr/bin/env lua
-- [[lua-fn-dispatch.lua]]
--
-- exmp: [[20260311-call-lua-fn-from-nu.nu]]
--
-- Call lua fn from nushell script #prg/nu #prg/lua #f/prmp
  -- id:: b0ee9e48-696f-475f-8fbb-b355c9bb6ece
  -- date:: 20260311
-- Nushell and Lua: I have a nushell script. I also have a lua module. I want to call a specific lua function from nushell script. For example:
-- This is lua module: `~/prj/study/script/lua_libs/mert-lua-utils.lua`
-- I want to call `get_file_name_no_ext(path)` function that takes a string argument.
-- [grok](https://grok.com/share/bGVnYWN5_c65a77cd-c17e-4326-b13a-6465b1dc51b9)
--
-- 20260311 call lua fn from nu

-- ~/bin/lua-fn-dispatch.lua
local modpath = arg[1]          -- path to your module
local fn_name = arg[2]          -- function name
local input   = arg[3] or ""    -- the argument (path in your case)

local mod = dofile(modpath)

local fn = mod[fn_name]
if type(fn) ~= "function" then
    io.stderr:write("No such function: " .. fn_name .. "\n")
    os.exit(1)
end

local result = fn(input)
print(result)

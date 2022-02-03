#!/usr/local/bin/lua
-- taken from: https://github.com/jiyinyiyong/json-lua
JSON = require("JSON")
local inspect = require("inspect")

local raw_json_text = "[1,2,[3,4]]"

local lua_value = JSON:decode(raw_json_text) -- decode example
local raw_json_text2    = JSON:encode(lua_value)        -- encode example
local pretty_json_text = JSON:encode_pretty(lua_value) -- "pretty printed" version

print(inspect(lua_value))
print(inspect(raw_json_text))
print(inspect(raw_json_text2))
print(inspect(pretty_json_text))
-- { 1, 2, { 3, 4 } }
-- "[1,2,[3,4]]"
-- "[1,2,[3,4]]"
-- "[ 1, 2, [ 3, 4 ] ]"



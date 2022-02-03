#!/usr/local/bin/lua
JSON = require("JSON")
local open = io.open
local inspect = require("inspect")

local function read_file(path)
    local file = open(path, "rb") -- r read mode and b binary mode
    if not file then return nil end
    local content = file:read "*a" -- *a or *all reads the whole file
    file:close()
    return content
end

local fileContent = read_file("j01.json");
local t0 = JSON:decode(fileContent) -- decode example
print(inspect(t0));
print(inspect(t0[1]))
local t1 = t0[1]
print(inspect(t1.id))

#!/usr/local/bin/lua
-- taken from: https://leafo.net/guides/customizing-the-luarocks-tree.html

print(package.path) -- where .lua files are searched for
-- /usr/local/share/lua/5.4/?.lua;/usr/local/share/lua/5.4/?/init.lua;/usr/local/lib/lua/5.4/?.lua;/usr/local/lib/lua/5.4/?/init.lua;./?.lua;./?/init.lua
print(package.cpath) -- where native modules are searched for
-- /usr/local/lib/lua/5.4/?.so;/usr/local/lib/lua/5.4/loadall.so;./?.so

-- add a new directory to the path
package.path = package.path .. ";/opt/custom/?.lua"

require("hello.zone") -- might load /opt/custom/hello/zone.lua

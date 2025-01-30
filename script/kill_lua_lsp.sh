#!/bin/bash
# [[kill_lua_lsp.sh]]
# run by cron job

echo started: ~/prj/study/script/kill_lua_lsp.sh
date
# Find and kill lua-language-server processes
pkill -f "lua-language-server"
echo completed: ~/prj/study/script/kill_lua_lsp.sh


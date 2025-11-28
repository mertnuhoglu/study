#!/bin/sh
# [[cron_vim_index.sh]]
# f/mpct: [[vim-index-docs.lua]]
# rltd: [[list_files_for_gotodef.sh]]
# lgs: /Users/mertnuhoglu/tmp/cron_vim_index.txt
# lgs: /Users/mertnuhoglu/tmp/cron_vim_index.log
#
# rfr: spcs: Convert Vim Scheduled Job to Bash Cron Job `f/prmp prg/vim prg/bash` || ((b7fe329c-391e-4af3-b360-ffeaad74db38))
#
# rfrmap:
# A: [[cron_vim_index.sh]]
# B: [[vim-index-docs]]
# C: function ExportOldfiles(filename) || ((dfcb38d6-228f-41a8-9eb4-8255238b25eb))
# F: pprv: [[20250113-cron-with-nvim-fn.sh]]
# [[files_in_dirs.txt]]
# [[list_files_for_gotodef.sh]]
# tpt:
	# /Users/mertnuhoglu/prj/myrepo/scrap/out/oldfiles/oldfiles.txt
	# ~/prj/myrepo/scrap/out/oldfiles/oldfiles.lua
#
# crontab -e
# run every 15 mins
# 0/15 * * * * /Users/mertnuhoglu/prj/study/script/cron_vim_index.sh >> /tmp/cron_vim_index.log 2>&1
#
echo $(date +"%Y%m%d-%H%M%S") started: /Users/mertnuhoglu/prj/study/script/cron_vim_index.sh

PATH="/usr/local/bin:/usr/bin:/bin"
export PATH

# sh /Users/mertnuhoglu/prj/study/script/list_files_for_gotodef.sh >> /Users/mertnuhoglu/tmp/cron_vim_index.log 2>&1
echo "started: list_files_for_gotodef.nu"
nu /Users/mertnuhoglu/prj/study/script/list_files_for_gotodef.nu >> /Users/mertnuhoglu/tmp/cron_vim_index.log 2>&1
# [[vim-index-docs.lua]]
# /usr/local/bin/nvim --headless \
# 			-c "lua require('user.vim-index-docs')" \
# 			-c "quit" >> /Users/mertnuhoglu/tmp/cron_vim_index.log 2>&1
echo $(date +"%Y%m%d-%H%M%S") started: 20250815-serialize-oldfiles-map.lua
lua ~/prj/myrepo/logseq-myrepo/exmp/20250320-scrap/20250815-serialize-oldfiles-map.lua >> /Users/mertnuhoglu/tmp/cron_vim_index.log 2>&1
echo $(date +"%Y%m%d-%H%M%S") started: vim-index-docs.lua
lua ~/prj/private_dotfiles/.config/lazyvim/lua/user/vim-index-docs.lua >> /Users/mertnuhoglu/tmp/cron_vim_index.log 2>&1
# log_script
echo $(date +"%Y%m%d-%H%M%S") completed: ~/prj/study/script/cron_vim_index.sh

#!/bin/sh
# [[cron_hourly.sh]]
#   id:: 5ea20dbe-3ac3-40b5-9a0b-b5e0f42624bd
#   date:: 20250101
#
# rltd: 
#   [[cron_vim_index.sh]]
#   [[cron_daily.sh]]
# 20 * * * * /Users/mertnuhoglu/prj/study/script/cron_hourly.sh >> /Users/mertnuhoglu/tmp/cron_hourly.log 2>&1
# 30 10 * * * /Users/mertnuhoglu/prj/study/script/cron_daily.sh >> /Users/mertnuhoglu/tmp/cron_daily.log 2>&1
# 30 11 * * 1 /Users/mertnuhoglu/prj/study/script/cron_weekly.sh >> /Users/mertnuhoglu/tmp/cron_weekly.log 2>&1

echo $(date +"%Y%m%d-%H%M%S") started: ~/prj/study/script/cron_hourly.sh

PATH="/usr/local/bin:/usr/bin:/bin"
export PATH

nu ~/prj/myrepo/logseq-myrepo/exmp/20250317-nu-list-files/list-files-in-outlist.nu >> /Users/mertnuhoglu/tmp/cron_hourly.log
nu ~/prj/myrepo/logseq-myrepo/exmp/20250317-nu-list-files/list-files-in-args.nu >> /Users/mertnuhoglu/tmp/cron_hourly.log
lua ~/prj/myrepo/logseq-myrepo/exmp/20250319-update-wk-tags/20251119-dfl-root-to-list-tags.lua
bb ~/prj/myrepo/logseq-myrepo/exmp/d-20250311-filter-json-by-tag/d20260302-wk_tags-to-wk_tagns.clj /Users/mertnuhoglu/.out/rbl/wk_tags.txt > /Users/mertnuhoglu/.out/rbl/wk_tagns.txt
nu ~/prj/myrepo/logseq-myrepo/exmp/20250312-filter-outline/20260326-dfl-rbl.nu "/Users/mertnuhoglu/prj/private_dotfiles/.config/params/args/20260326-dfl-rbl-myr-cllogseq.json" >> /Users/mertnuhoglu/tmp/cron_daily.log 2>&1

# spcs: Rotate runlog.txt/log output files   || ((4a15fffe-dff9-461f-81eb-acd5bcbc5896))
/usr/local/sbin/logrotate -s /Users/mertnuhoglu/.config/logrotate.status /Users/mertnuhoglu/.config/logrotate.conf
nu -c 'ps | where name == "contactsd" | where cpu > 50 | each { |it| kill $it.pid }'
echo $(date +"%Y%m%d-%H%M%S") completed: ~/prj/study/script/cron_hourly.sh

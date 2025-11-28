#!/bin/sh
# [[cron_hourly.sh]]
#   id:: 5ea20dbe-3ac3-40b5-9a0b-b5e0f42624bd
#
# 20 * * * * /Users/mertnuhoglu/prj/study/script/cron_hourly.sh >> /Users/mertnuhoglu/tmp/cron_hourly.log 2>&1
# 30 10 * * * /Users/mertnuhoglu/prj/study/script/cron_daily.sh >> /Users/mertnuhoglu/tmp/cron_daily.log 2>&1
# 30 11 * * 1 /Users/mertnuhoglu/prj/study/script/cron_weekly.sh >> /Users/mertnuhoglu/tmp/cron_weekly.log 2>&1


echo $(date +"%Y%m%d-%H%M%S") started: ~/prj/study/script/cron_hourly.sh

PATH="/usr/local/bin:/usr/bin:/bin"
export PATH

nu ~/prj/myrepo/logseq-myrepo/exmp/20250317-nu-list-files/list-files-in-outlist.nu >> /Users/mertnuhoglu/tmp/cron_hourly.log 2>&1
nu ~/prj/myrepo/logseq-myrepo/exmp/20250317-nu-list-files/list-files-in-args.nu >> /Users/mertnuhoglu/tmp/cron_hourly.log 2>&1
nu ~/prj/myrepo/logseq-myrepo/exmp/20250312-filter-outline/20251124-dfl-xtrc-blocks-by-tags.nu myr >> /Users/mertnuhoglu/tmp/cron_daily.log 2>&1
nu ~/prj/myrepo/logseq-myrepo/exmp/20250312-filter-outline/20251124-dfl-xtrc-blocks-by-tags.nu cl >> /Users/mertnuhoglu/tmp/cron_daily.log 2>&1

echo $(date +"%Y%m%d-%H%M%S") completed: ~/prj/study/script/cron_hourly.sh

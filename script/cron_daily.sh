#!/bin/sh
# [[cron_daily.sh]]
#   id:: 7a675d96-7517-4b48-966d-b52af34a5d75

echo $(date +"%Y%m%d-%H%M%S") started: ~/prj/study/script/cron_daily.sh

PATH="/usr/local/bin:/usr/bin:/bin"
export PATH

echo $(date +"%Y%m%d-%H%M%S") started: 20250825-redis-import-refid-to-date-from-all-docs.nu
nu ~/prj/myrepo/logseq-myrepo/exmp/20250320-scrap/20250825-redis-import-refid-to-date-from-all-docs.nu >> /Users/mertnuhoglu/tmp/cron_vim_index.log 2>&1

echo $(date +"%Y%m%d-%H%M%S") started: 20250312-dfl-filter-all-02.nu
nu ~/prj/myrepo/logseq-myrepo/exmp/20250312-filter-outline/20250312-dfl-filter-all-02.nu >> /Users/mertnuhoglu/tmp/cron_vim_index.log 2>&1

# ((601cd2b8-56e9-4e0b-9931-fe4d9a2e3992))
# vim.fn.ShellGrep()  -- || ((c4d3c680-ba0f-4b29-af80-a119f4d2076f))
sh /Users/mertnuhoglu/prj/study/script/grep_2_file_4_gtd_by_all.sh >> /Users/mertnuhoglu/tmp/cron_daily.log 2>&1
sh /Users/mertnuhoglu/prj/study/script/grep_2_file_4_todo_by_all.sh >> /Users/mertnuhoglu/tmp/cron_daily.log 2>&1

nu ~/prj/study/script/dir_2_list_in_repo.nu >> /Users/mertnuhoglu/tmp/cron_daily.log 2>&1
nu ~/prj/myrepo/logseq-myrepo/exmp/20250320-scrap/20251031-cron-git-pull.nu

echo $(date +"%Y%m%d-%H%M%S") completed: ~/prj/study/script/cron_daily.sh

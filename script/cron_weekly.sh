#!/bin/sh
# [[cron_weekly.sh]]
#   id:: 4506117e-dd93-4bfc-8606-e1fbd77b7ff4

echo $(date +"%Y%m%d-%H%M%S") started: ~/prj/study/script/cron_weekly.sh

PATH="/usr/local/bin:/usr/bin:/bin"
export PATH

nu ~/prj/study/script/dir_2_list_tag.nu
echo $(date +"%Y%m%d-%H%M%S") completed: ~/prj/study/script/cron_weekly.sh

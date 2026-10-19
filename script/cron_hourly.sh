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
# { cons.cat.action, "", "R", "echo_hello_args_nu", { echo_hello_args_nu, } },
	# screenshotMenu = {
	# 	-- id:: 1b4439b4-e4a2-4cca-98a2-c1d1467e093d
	# 	-- date:: 20251105
	# 	-- spcs: Hammerspoon fonksiyonlarını menü komutlarıyla çağıralım || ((56c261db-cfd9-46bb-ab2e-972b07860ed6))
	# 	-- espanso: screenshot -> img url for md ".mdimge" || ((680dbf7f-0e56-4250-86b9-3fe18e41b833))
	# 	-- script: [[ss_full_clinbox_d1.sh]]
	# 	--
	# 	-- like which-key
	# 	parentMenu = commandsMenu,
	# 	menuHotkey = nil,
	# 	menuItems = {
	# 		fncActionExport("A", "Export Scan"),  -- SPC SPC sa
	# 		fncActionFn("E", "toggle shortcuts", enableShortcutSet),  -- SPC SPC se
	# 		fncActionLastFn("F", "Last File", ""),
	# 		fncActionFn("H", "shottr", triggerShottrScreenshotRectangle),
	#        -- id:: dc152b72-f44f-4774-a12a-aa2f33302f22
	# 		{ cons.cat.action, "", "M", "d1", { ss_full_clinbox_d1, } },
	# 		{ cons.cat.submenu, "", "O", "other", { { cons.act.menu, screenshotOtherMenu } } },
	# 		{ cons.cat.action, "", "S", "d2", { ss_full_clinbox_d2, } },
	# 		{ cons.cat.action, "", "R", "rect", { ss_rect_clinbox, } },
	# 	},
	# },

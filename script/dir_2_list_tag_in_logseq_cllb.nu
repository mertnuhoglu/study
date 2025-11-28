# [[dir_2_list_tag_in_logseq_cllb.nu]]
#
# pprv: [[dir_2_list_tag_in_logseq_cllb.sh]]
# prn: [[dir_2_list_tag.nu]]
# [grok](https://grok.com/chat/ea38b467-dab6-43f0-995d-9f8d97aa3bb5)

let DIR = $env.DIR_CLLOGSEQ
let REPO = "cllb"

dir_2_list_tag.nu $DIR $REPO | save --append /Users/mertnuhoglu/tmp/cron_hourly.log


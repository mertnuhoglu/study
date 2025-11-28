#!/usr/bin/env nu
# [[dir_2_list_tag_in_logseq_myrepo.nu]]
#
# pprv: [[dir_2_list_tag_in_logseq_myrepo.sh]]
# prn: [[dir_2_list_tag.nu]]
# [grok](https://grok.com/chat/ea38b467-dab6-43f0-995d-9f8d97aa3bb5)

let DIR = $env.DIR_MYREPO_LOGSEQ
let REPO = "myr"

print $DIR
# dir_2_list_tag.nu $DIR $REPO | save --append $env.RUNLOG
dir_2_list_tag.nu $DIR $REPO 

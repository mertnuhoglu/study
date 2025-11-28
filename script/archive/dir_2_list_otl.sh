#!/bin/sh

# Title: Directory to List of otl Files [[dir_2_list_otl.sh]] #myst #f/script
#   id:: 42b240d6-c9f8-4305-98fc-6b684c28ff6a
# Date: 20240121
# [[dir_2_list_otl.sh]]
#
# prt:
# [[dir_2_list_otl_in_logseq_myrepo.sh]]
# [[dir_2_list_otl_in_logseq_cllb.sh]]
#
# Usage:
#   dir_2_list_otl.sh <dir> > <output>
# rfr: which-key: local dir_2_list_otl = { -- SPC hd || ((2aec545b-0bc2-4920-a06b-533df10802fa))
#
# Output: [[otl-list]]

DIR="$1"
fd --extension otl --type f --base-directory="${DIR}" | sed -e 's|^.*/||' -e 's|^|[[|' -e 's|$|]]|'

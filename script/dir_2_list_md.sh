#!/bin/sh

# Title: Directory to List of Markdown Files [[dir_2_list_md.sh]] #myst #f/script
#   id:: df371dc9-e2c6-493d-b946-f6f1d3503caa
# Date: 20240121
# [[dir_2_list_md.sh]]
#
# prt:
# [[dir_2_list_md_in_logseq_myrepo.sh]]
# [[dir_2_list_md_in_logseq_cllb.sh]]
#
# Usage:
#   dir_2_list_md.sh <dir> > <output>
# rfr: which-key: local dir_2_list_md = { -- SPC hd || ((2aec545b-0bc2-4920-a06b-533df10802fa))
# Output: [[Document-List]]

DIR="$1"
# fd --extension md --type f --base-directory="${DIR}" | sed -e 's|^./||' -e 's|.md$||' -e 's|^|[[|' -e 's|$|]]|' -e 's#pages/##' -e 's#journals/##' | sort
fd --extension md \
	--type f \
	--base-directory="${DIR}" | \
sed -e 's|^./||' \
    -e 's|.md$||' \
    -e 's|^|[[|' \
    -e 's|$|]]|' \
    -e 's#pages/##' \
    -e 's#journals/##' | \
sort


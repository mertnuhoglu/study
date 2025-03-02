#!/bin/sh

# Title: Directory to List of Files 
# rfr: similar-to: [[dir_2_list_md.sh]]
# Date: 20240323
#
# Usage:
#   dir_2_list_all.sh <dir> > <output>
# rfr: which-key: local dir_2_list_md = { -- SPC hd || ((2aec545b-0bc2-4920-a06b-533df10802fa))
# Output: text

DIR="$1"
fd --type f --no-ignore --base-directory="${DIR}" | sed -e 's|^./||' -e 's|^|[[|' -e 's|$|]]|'

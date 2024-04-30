#!/bin/sh

# Title: Move last shottr screencapture to Logseq assets
# Date: 20240428
#
# Usage:
#
# Output: (in clipboard)
#
#   1. Side Effect:
#
#     SCR-20240427-jrqj.png is in /Users/mertnuhoglu/projects/myrepo/logseq-myrepo/assets/
#
#   2. Output:
#
#     ![](assets/SCR-20240427-jrqj.png)
#


DEST="$1"

LAST_FILE=$(ls "${DIR_SCREENCAPTURE}" | sort | rg '^SCR-202' | tail -n1)
mv "${DIR_SCREENCAPTURE}/${LAST_FILE}" "${DEST}/assets"
echo "moved ${LAST_FILE} to ${DEST}/assets"
echo "![](assets/${LAST_FILE})" | pbcopy


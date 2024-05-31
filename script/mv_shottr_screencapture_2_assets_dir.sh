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

DEST_DIR="$1"

LAST_FILE=$(ls -t "${DIR_SCREENCAPTURE}" | rg '^SCR-202' | head -n1)
DEST_FILE="${DEST_DIR}/assets/${LAST_FILE}"
echo "![](assets/${LAST_FILE})" | pbcopy

mv "${DIR_SCREENCAPTURE}/${LAST_FILE}" "${DEST_DIR}/assets"
echo "moved ${LAST_FILE} to ${DEST_DIR}/assets"

if test -f "${DEST_FILE}"; then
	echo "$DEST_FILE exists."
	/Applications/ImageOptim.app/Contents/MacOS/ImageOptim "${DEST_FILE}"
else
	echo "$DEST_FILE does not exist." | pbcopy
fi

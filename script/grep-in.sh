#!/bin/sh

# [[grep-in.sh]] #prg/bash #f/myst #org/kms
#   id:: 267e6e71-e717-4ba5-9f55-42e5eb0d7546
#
# Title:
# Date: 20240907
#
#
# Usage:

QRY=$1
R_FILES=$2

DEST_DIR="/Users/mertnuhoglu/prj/myrepo/scrap"

DATE=$(date +%Y%m%d)
TIME=$(date +%H%M%S)
FILENAME="${DATE}-${TIME}-grep.txt"
OUTPUT="${DEST_DIR}/out/${FILENAME}"
echo $OUTPUT | pbcopy

gxargs --delimiter '\n' \
	--arg-file "${R_FILES}" \
	rg --vimgrep \
	"${QRY}" \
	>${OUTPUT}

echo "Output: "
echo $OUTPUT

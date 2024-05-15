#!/bin/sh

#
# [[images_compress_in_dir.sh]]
#   id:: 261b22d8-4d16-44c3-866c-77f164ff1d71
# Title: Loop Image Files and Compress Them
# Date: 20240104
#
# Usage:
#

DIR=$1
cd "${DIR}"

# for file in SCR-202*.png; do
for file in *.jpg *.png; do
	/Applications/ImageOptim.app/Contents/MacOS/ImageOptim "${file}"
done

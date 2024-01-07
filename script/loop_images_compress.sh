#!/bin/sh

# Title: Loop Image Files and Compress Them
# Date: 20240104
# rfr: [[20240103-Loop-Image-Files-and-Compress-Them]]
#
# Usage:
#
# sh ~/prj/study/script/loop_images_compress.sh images.txt

IMAGE_LIST="$1"

for file in $(cat ${IMAGE_LIST}); do
	basename=${file%.*}
	convert "$file" "${file%.png}.jpg"
done

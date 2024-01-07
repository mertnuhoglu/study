#!/bin/sh

# Title: Loop Over Lines of a File
# Date: 20240104
#
# Usage:
#
# sh ~/prj/study/script/exmp_loop_over_lines_in_file.sh images.txt
# sh exmp_loop_over_lines_in_file.sh file_list.txt

IMAGE_LIST="$1"

for line in $(cat ${IMAGE_LIST}); do
	echo "$line" # Example: Print each line
done

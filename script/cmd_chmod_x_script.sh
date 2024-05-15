#!/bin/sh

# [[cmd_chmod_x_script.sh]]
# Title: Returns cmd for chmod +x [[cmd_chmod_x_script.sh]]
#   id:: 26234106-633e-4083-ab16-b6dad40ac39e
# Date: 20240513
#
# Usage:
#

FILE=$1
echo "chmod +x \"${FILE}\"" | pbcopy

echo "run command is put into clipboard"

#!/bin/sh
#
# Title: Get basename (no extension) of a file
# Date: 20240106
# rfr: Get basename (no extension) of a file `bash` || ((2e2a3728-cd40-4164-931c-1ef6fae8fcf8))
#
# Usage:
#
# sh exmp_get_basename.sh

filename="file01.txt"
basename=${filename%.*}
echo $basename


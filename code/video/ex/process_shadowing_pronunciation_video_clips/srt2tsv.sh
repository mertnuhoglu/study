#!/bin/bash
INPUT_SRT=$1
OUTPUT_TSV=$2
# script taken from: https://stackoverflow.com/questions/31684194/how-to-create-a-shell-script-to-copy-srt-content-into-corresponding-excel-col
# replaces newlines and arrows with tabs (keeping double newline as one newline).
perl -0777 -pe 's/\n([^\n])/\t$1/g; s/ --> /\t/g' $INPUT_SRT | \
# removes tabs from line beginnings and removes redundant empty lines.
perl -ne 's/^\t//; print unless /^$/' | \
# replace 4. tab letter with <br>
perl -pe 's/(^[^\t]*(\t[^\t]*){3})\t/$1<br> /g' | \
# replace commas with dots in time digits
perl -pe 's/(\d\d),(\d\d\d)/$1.$2/g' > $OUTPUT_TSV

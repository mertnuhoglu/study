awk  'BEGIN{FS=OFS="@"} {if ($1 ~ /^[ \t]*$/) $1=ch; else ch=$1} 1' input03.txt


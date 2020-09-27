awk  'BEGIN{FS=OFS="	"} {if ($2 ~ /^[ \t]*$/) $2=ch; else ch=$2} 1' input03b4.txt


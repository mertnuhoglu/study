awk  'BEGIN{FS=OFS="	"} {if ($2 ~ /^[ \t]*$/) $2=ch; else ch=$2} 2' input03b3.txt


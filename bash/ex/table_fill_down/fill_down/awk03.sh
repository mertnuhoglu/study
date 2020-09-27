awk 'BEGIN{FS=OFS="@"}/^[ \t]+/{$1=c}{c=$1}1' input03.txt

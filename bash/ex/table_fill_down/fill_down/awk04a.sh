awk  'BEGIN{FS=OFS="	"} 
			{
				if ($1 ~ /^[ \t]*$/) $1=ch; else ch=$1;
			} 1' input04.tsv > input04a1.tsv
awk  'BEGIN{FS=OFS="	"} 
			{
				if ($2 ~ /^[ \t]*$/) $2=ch; else ch=$2;
			} 1' input04a1.tsv > input04a2.tsv

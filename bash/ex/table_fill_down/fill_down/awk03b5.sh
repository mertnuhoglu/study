awk  'BEGIN{FS=OFS="	"} 
      {
				if ($2 ~ /^[ \t]*$/) $2=ch; else ch=$2;
				if ($1 ~ /^[ \t]*$/) $1=ch; else ch=$1;
			} 1' input03b4.txt


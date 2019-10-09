#!/usr/local/bin/awk -f
# parse "ls -l"
{
	if (NF == 8) {
		print $3 " " $8;
	} else if (NF == 9) {
		print $3 " " $9;
	}
}
END {print NF;}

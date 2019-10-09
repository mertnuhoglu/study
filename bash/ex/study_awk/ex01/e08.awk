#!/usr/local/bin/awk -f
# print lines after 10
{
	if (NR > 10) {
		print NR $0;
	}
}

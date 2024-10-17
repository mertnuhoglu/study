#!/bin/sh

# prn: [[grep_2_file_4_gtd_by_person.sh]]

PERSON="#p/huseyin"
PERSON_NAME=$(echo "${PERSON}" | sed -nE 's/#([^/]+)\/([^/]+)$/\1-\2/p')
# #p/huseyin -> huseyin
echo $PERSON_NAME

FILENAME="$DATE-grep-gtd-by-${PERSON_NAME}.txt"

#!/bin/sh

# Title: Extract nicknames from vivaldi bookmarks id=g14288
# Date: 20230511
#
# rfr: [[20230511-bash-Export-Vivaldi-Bookmark-nicknames]] <url:file:///~/prj/study/logseq-study/pages/20230511-bash-Export-Vivaldi-Bookmark-nicknames.md#r=g14287>

DAY=$(date +%Y%m%d)
gsed -e '/"Nickname":/!d' "/Users/mertnuhoglu/Library/Application Support/Vivaldi/Default/Bookmarks" | gsed -e 's/"Nickname": "//' | gsed -e 's/",*//' | gsed -e 's/^  *//' | sort -u > temp && mv temp "$HOME/gdrive/logbook/vivaldi_bookmark_nicknames_${DAY}.txt"

echo "$HOME/gdrive/logbook/vivaldi_bookmark_nicknames_${DAY}.txt"

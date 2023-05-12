tags:: study, bash

# 20230511-bash-Export-Vivaldi-Bookmark-nicknames id=g14287

rfr: Extract nicknames from vivaldi bookmarks <url:file:///~/prj/study/script/vivaldi_bookmarks_to_nicknames.sh#r=g14288>

```
gsed -e '/"Nickname":/!d' "/Users/mertnuhoglu/Library/Application Support/Vivaldi/Default/Bookmarks" | gsed -e 's/"Nickname": "//' | gsed -e 's/",*//' | gsed -e 's/^  *//' | sort -u > temp && mv temp ~/gdrive/logbook/vivaldi_bookmark_nicknames_20230511.txt
```


---
title: "Study fd"
date: 2020-12-14T13:13:14+03:00 
draft: true
description: ""
tags:
categories: bash, find
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css

---

# Quickstart fd

```bash
fd --help
fd --extension jpg 
fd --type file
fd --change-newer-than  '2020-12-07 06:00:00' --change-older-than  '2020-12-08 10:00:00'
fd --extension jpg --exec convert {} {.}.png
```

# Problems

## Belli bir tarihte update edilen dosyaları nasıl bulurum? id=g11822

```bash
find . -type f -ls | grep 'Sep'
find . -type f -ls | grep 'Sep 10'
```

[sharkdp/fd: A simple, fast and user-friendly alternative to 'find'](https://github.com/sharkdp/fd)

```bash
# Convert all jpg files to png files:
fd -e jpg -x convert {} {.}.png

# Unpack all zip files (if no placeholder is given, the path is appended):
fd -e zip -x unzip

# Convert all flac files into opus files:
fd -e flac -x ffmpeg -i {} -c:a libopus {.}.opus

# Count the number of lines in Rust files (the command template can be terminated with ';'):
fd -x wc -l \; -e rs
```

options:

```bash
	-e	--extension
	--changed-within <date|dur>
	--changed-before <date|dur>
	-L	--follow
```

```bash
fd --changed-before '2020-10-27 10:00:00'
fd --change-older-than 6days
fd --changed-within 2weeks
fd --change-newer-than  '2020-10-27 10:00:00'
```

Final:

```bash
fd --change-newer-than  '2020-12-07 06:00:00' --change-older-than  '2020-12-08 10:00:00'
fd --follow --change-newer-than  '2020-12-07 06:00:00' --change-older-than  '2020-12-08 10:00:00'
```




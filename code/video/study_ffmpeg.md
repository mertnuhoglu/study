---
title: "study ffmpeg"
date: 2020-05-21T19:37:39+03:00 
draft: true
description: ""
tags:
categories: video, ffmpeg
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css

---

## ex01: split/cut a video in two clips and merge/concat them id=g_11013

``` 
youtube-dl https://www.youtube.com/watch?v=fqS4znBZ5F8
filename="ffmpeg x264 CRF 0 (losless) youtube upload video testing-fqS4znBZ5F8.mkv"
duration=10
ffmpeg -i "${filename}" -t "00:00:${duration}" -c:v copy -c:a copy test01.mkv
start_time=00:00:01.50
end_time=00:00:03.25
original_video=test01.mkv
ffmpeg -ss "${start_time}" -to "${end_time}" -i "${original_video}" -c:v libx264 -crf 0 -c:a aac test02.mp4
ffmpeg -ss "${start_time}" -to "${end_time}" -i "${original_video}" -c:v libx264 -crf 0 -c:a aac test02b.mkv

start_time=00:00:05.50
end_time=00:00:07.25
ffmpeg -ss "${start_time}" -to "${end_time}" -i "${original_video}" -c:v libx264 -crf 0 -c:a aac test03.mp4

echo "file 'test02.mp4'
file 'test03.mp4'" > video_files.in
ffmpeg -f concat -i video_files.in -c copy test04.mp4
``` 


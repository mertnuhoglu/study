---
title: "Process to Generate Shadowing Pronunciation Video Clips 20190727"
date: 2019-07-27T20:20:35+03:00 
draft: true
description: ""
tags:
categories: anki
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
resource_files:

path: ~/projects/study/code/video/process_shadowing_pronunciation_video_clips.md
state: wip

---

# Process

``` bash
clip_name=rock_dog
input=${clip_name}.mkv
output_mp4="${clip_name}.mp4"
ffprobe -i ${input} 2>&1 | rg Audio
  ##>     Stream #0:1(eng): Audio: dts (DTS), 48000 Hz, 5.1(side), fltp, 1536 kb/s
stream=2
VOLUME_INCREASE=2
``` 

Edit `~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips.sh`



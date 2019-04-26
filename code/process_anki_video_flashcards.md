---
title: "Process Anki: Make Video Flashcards 20190329"
date: 2019-03-29T14:58:41+03:00 
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
- 

path: ~/projects/study/code/process_anki_video_flashcards.md
state: wip

---

## Example: Prepare "kitten memes" flashcards

01. Search for "kitten memes"

...

02. tüm dosyaların ismini değiştir

``` bash
for f in *; do mv "$f" "$f.mp4"; mv "$f.mp4" "`echo $f | tr "[:upper:]" "[:lower:]"`"; done
rename 's/[- ~]/_/g' *.mp4
rename 's/[- ~]/_/g' *.gif
``` 

dosyalara ön kelime ekle:

``` bash
TAG=book_kidnap
rename "s/^/${TAG}_/" *.mp4
``` 


03. tüm resimlerin boyutlarını küçült

``` bash
mogrify -quality 100 -density 300x300 -resize 400x400 *.gif
``` 

Sadece boyutu büyük olan videoların ölçülerini küçült

``` bash
find . -size +100k -exec ls -lh {} +

for f in ./*.mp4; ffmpeg -i "$f" -vf scale=320:240 "${f%.*}.mp4"
``` 

04. vim dosya isimlerini düzeltme

``` bash
rename 's/^/zootopia02_/' *.mp4
ls *.mp4 | sed -e 's/.*/[sound:\0] <br> /'| pbcopy
``` 

05. Take screenshots automatically at 1 sec of each video

https://stackoverflow.com/questions/27568254/how-to-extract-1-screenshot-for-a-video-with-ffmpeg-at-a-given-time

``` bash
for f in ./*.mp4; ffmpeg -ss 00:00:01 -i "$f" -vframes 1 -q:v 2 "$f".jpg
``` 

05b. Move jpg files to `collection.media` folder: `/Users/mertnuhoglu/Library/Application Support/Anki2/ozgureminnuhoglu/collection.media/`

``` bash
mv *.mp4 "/Users/mertnuhoglu/Library/Application Support/Anki2/ozgureminnuhoglu/collection.media/"
``` 

06. questions

06a. cloze questions for repeat:

		- Skipper look
		->
		- Skipper {{c1::look}}

06b. normal questions

``` vim
s#\(-[^;]*\);#\1; \1 <br>#
s#\(-[^;]*\)#\1; \1 <br>#
``` 

		- Gang! Congratulations on
		->
		- [...]! Congratulations on

07. append tag

06. Anki > import text > + Allow HTML 
	
## Extract clips from a video automatically using subtitle text

01. Prepare `phrases.txt`

Ex: `~/Downloads/english/book_kidnap/clips/phrases.txt`

02. Call the script:

``` bash
mp4_extract_clips /Users/mertnuhoglu/gdrive/shared/ozguremin/Kidnap.mp4 phrases.txt
``` 

03. 

		Convert filenames <url:/Users/mertnuhoglu/projects/study/code/process_anki_video_flashcards.md#tn=02. tüm dosyaların ismini değiştir>
		tüm resimlerin boyutlarını küçült <url:/Users/mertnuhoglu/projects/study/code/process_anki_video_flashcards.md#tn=03. tüm resimlerin boyutlarını küçült>

03. Make anki questions

``` bash
TAG='book_kidnap'
sed -e "s/.*/\0; \0 <br>; ${TAG}/" phrases.txt > questions.txt
``` 

Prepare gap (cloze) questions in `questions.txt`

04. Merge everything

``` bash
paste links questions.txt > anki.txt
``` 

``` bash
mv *.mp4 "/Users/mertnuhoglu/Library/Application Support/Anki2/ozgureminnuhoglu/collection.media/"
``` 


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

path: ~/projects/study/code/anki/process_anki_video_flashcards.md
state: wip

---

## Ex03: Extract clips from movie fideos with subtitles 20190712 

Ref: cut video clips 20190711 <url:/Users/mertnuhoglu/projects/study/logbook/log_20190710.md#tn=cut video clips 20190711>

Code in: `/Users/mertnuhoglu/projects/study/code/anki/ex/process_anki_video_flashcards/ex03`

01. Manually copy scenes from `~/projects/study/code/anki/ex/process_anki_video_flashcards/ex03/subtitles.srt` into `~/projects/study/code/anki/ex/process_anki_video_flashcards/ex03/marks.txt`

02. Convert marks.txt into tsv file: 

``` bash
cd /Users/mertnuhoglu/projects/study/code/anki/ex/process_anki_video_flashcards/ex03
cp marks0.txt marks.txt
``` 

Edit in vim: `~/projects/study/code/anki/ex/process_anki_video_flashcards/ex03/marks.txt`

input

		00:01:26,720 --> 00:01:28,324
		MAX: I've lived in this city all my life.

		00:01:29,000 --> 00:01:32,886
		I'm Max.
		And I'm the luckiest dog in New York

output

		00:01:26,720	00:01:28,324	"MAX: I've lived in this city all my life."
		00:01:29,000	00:01:32,886	"I'm Max.
		And I'm the luckiest dog in New York"

``` vim
:ConvertMarksTxt2MarksTsv
``` 

``` bash
echo "start_time\tend_time\ttext" > marks.tsv
cat marks.txt >> marks.tsv
``` 

03. Data processing: add id and generate ffmpeg commands

ref: `~/projects/study/r/one.video.to.multiple.clips/R/generate_ffmpeg_cmd_for_splitting_videos.R`

``` r
	specs = readr::cols(
		start_time = readr::col_time(format = "%H:%M:%OS"),
		end_time = readr::col_time(format = "%H:%M:%OS"),
		text = readr::col_character()
	)
	m0 = readr::read_tsv("marks.tsv", col_types = specs) %>%
		generate_ffmpeg_cmd_for_splitting_videos(offset_clip_id = 0, original_video = "movie.mp4", clip_name = "movie")
	writeLines(m0$cmd, "cmd.sh")
	writeLines(m0$cmd_concat_silence, "cmd_concat_silence.sh")
	readr::write_tsv(m0, "clips.tsv")
``` 

Output: 

		~/projects/study/code/anki/ex/process_anki_video_flashcards/ex03/clips.tsv
		~/projects/study/code/anki/ex/process_anki_video_flashcards/ex03/cmd.sh

04. Run ffmpeg commands to cut video clips

``` bash
cd /Users/mertnuhoglu/Movies/secret_life_of_pets/
mkdir -p clips
bash ~/projects/study/code/anki/ex/process_anki_video_flashcards/ex03/cmd.sh
``` 

05. Prepare anki file

``` r
library(dplyr)
m1 = m0 %>%
	dplyr::select(clip_id, text, filename) %>%
	dplyr::mutate(line = glue::glue("[sound:{filename}] <br> {text} ; {text}; secret_life_of_pets"))
writeLines(m1$line, "anki_secret_life_of_pets_repeat_basic.txt")
``` 

06. mv files

``` bash
cd /Users/mertnuhoglu/Movies/secret_life_of_pets/clips
mv *.mp4 "/Users/mertnuhoglu/Library/Application Support/Anki2/ozgureminnuhoglu/collection.media/"
``` 

07. Edit anki file: `~/projects/study/code/anki/ex/process_anki_video_flashcards/ex03/anki_secret_life_of_pets_repeat_basic.txt`

08. Import into anki app

Import `~/Downloads/english/top_words_100/anki.tsv`

``` bash
ANKI_FILE=~/projects/anki_english/decks/anki_secret_life_of_pets_repeat_basic.txt
NEW_FILE=~/gdrive/mynotes/stuff/ozgur_emin/english/anki/secret_life_of_pets/anki_secret_life_of_pets_repeat_basic.txt
cat ${NEW_FILE} >> $ANKI_FILE
echo $ANKI_FILE | pbcopy
``` 

## Example: Prepare "kitten memes" flashcards from video files

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

02. Call the script: `~/projects/stuff/bash/mp4_extract_clips`

``` bash
mp4_extract_clips /Users/mertnuhoglu/gdrive/shared/ozguremin/Kidnap.mp4 phrases.txt
``` 

03. 

		Convert filenames <url:/Users/mertnuhoglu/projects/study/code/anki/process_anki_video_flashcards.md#tn=02. tüm dosyaların ismini değiştir>
		tüm resimlerin boyutlarını küçült <url:/Users/mertnuhoglu/projects/study/code/anki/process_anki_video_flashcards.md#tn=03. tüm resimlerin boyutlarını küçült>

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


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

Ref: 

		cut video clips 20190711 <url:/Users/mertnuhoglu/projects/study/logbook/log_20190710.md#tn=cut video clips 20190711>
		~/projects/study/logbook/make_shadowing_videos_20190719.md
		~/projects/study/logbook/log_20190722.md

### opt03: generate sub video only (no silence clips)

``` bash
VOLUME_INCREASE=2
clip_name="Incredibles 2 - İnanılmaz Aile 2 - 2018 - 1080p - Dual - Altyazısız"
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips.sh -c "${clip_name}" -v $VOLUME_INCREASE -S
``` 

### opt01: nearly full automatic

01: download english srt

02: 

optional steps:

		optional: youtube playlist <url:/Users/mertnuhoglu/projects/study/code/anki/process_anki_video_flashcards.md#tp=optional: youtube playlist>

normal path:

``` bash
clip_name="spotlight"
input="${clip_name}".mkv
output_mp4="${clip_name}.mp4"
ffprobe -i ${input} 2>&1 | rg eng | rg Stream | rg Audio
  ##>     Stream #0:2(eng): Audio: dts (DTS), 48000 Hz, 5.1(side), fltp, 1536 kb/s
stream=1
VOLUME_INCREASE=2
``` 

``` bash
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips.sh -c $clip_name -v $VOLUME_INCREASE -s $stream -N
``` 

05. Edit anki file

Check `clips/anki_${clip_name}.txt`

Ex: `~/projects/study/code/anki/ex/process_anki_video_flashcards/ex03/anki_secret_life_of_pets_repeat_basic.txt`

Put `[...]` with vim commands: `ürt` or `:ReplaceWithCloze2`

optional steps:

		optional: youtube srt correction <url:/Users/mertnuhoglu/projects/study/code/anki/process_anki_video_flashcards.md#tp=optional: youtube srt correction>

06. mv files

``` bash
anki_media="/Users/mertnuhoglu/Library/Application Support/Anki2/ozgureminnuhoglu/collection.media/"
mv clips/split02/*.mp4 "${anki_media}"
  ##> mv clips/split02/${clip_name}_{0002..0200}.mp4 "${anki_media}"
``` 

08. Import into anki app

Import `~/Downloads/english/top_words_100/anki.tsv`

``` bash
ANKI_FILE=~/projects/anki_english/decks/anki_${clip_name}_repeat_basic.txt
  ##> NEW_FILE=~/gdrive/mynotes/stuff/ozgur_emin/english/anki/${clip_name}/anki_${clip_name}_repeat_basic.txt
NEW_FILE=clips/anki_${clip_name}01.txt
cat ${NEW_FILE} >> $ANKI_FILE
echo $ANKI_FILE | pbcopy
  ##> /Users/mertnuhoglu/projects/anki_english/decks/anki_sherlock_yack_01_repeat_basic.txt
	##> /Users/mertnuhoglu/projects/anki_english/decks/anki_rock_dog_repeat_basic.txt
  ##> /Users/mertnuhoglu/projects/anki_english/decks/anki_sing_repeat_basic.txt
  ##> /Users/mertnuhoglu/projects/anki_english/decks/anki_ice_age_repeat_basic.txt
	##> /Users/mertnuhoglu/projects/anki_english/decks/anki_sherlock_yack_20_who_robbed_the_baboon_repeat_basic.txt
``` 

New deck:

``` bash
echo $clip_name | pbcopy
  ##> sherlock_yack_20_who_robbed_the_baboon
``` 

### opt02: semi automatic

01: compress video. ref: `~/projects/study/logbook/ffmpeg_video_compression_20190711.md`

``` bash
clip_name=spotlight
input="${clip_name}".mkv
output_mp4="${clip_name}.mp4"
ffprobe -i ${input} 2>&1 | rg eng | rg Stream | rg Audio
  ##>     Stream #0:2(eng): Audio: dts (DTS), 48000 Hz, 5.1(side), fltp, 1536 kb/s
stream=2
VOLUME_INCREASE=2
``` 

02: split video into parts

Generate `marks.tsv`

``` bash
mkdir -p clips
cp "${clip_name}.srt" clips/marks.txt
nvim -s "${DIR}/ConvertMarksTxt2MarksTsv.vim" clips/marks.txt 
cp "${clip_name}.tr.srt" clips/marks.tr.txt 
nvim -s "${DIR}/ConvertMarksTxt2MarksTsvTr.vim" clips/marks.tr.txt 
``` 

``` bash
offset_clip_id=0
  ##> clip_name="spotlight"
output_mp4="${clip_name}.mp4"
R --vanilla -e "one.video.to.multiple.clips::main_generate_ffmpeg_cmd_for_splitting_videos(path = 'clips/marks.tsv', offset_clip_id = ${offset_clip_id}, original_video = '${output_mp4}', clip_name = '${clip_name}')"
rg "\bNA\b" clips/marks.tsv
rg "\bNA\b" clips/clips.tsv
``` 

split into parts

``` bash
bash clips/split01.sh
bash clips/split02.sh
``` 

03: Generate silence video file

``` bash
silence01=clips/silence01.mp4
VOLUME_INCREASE=0.1
ffmpeg -ss 00:00 -to 00:03 -i ${output_mp4} -c:v libx264 -crf 23 -c:a aac -filter:a "volume=${VOLUME_INCREASE}" $silence01
out_silence=clips/silence.mp4
ffmpeg -i ${silence01} -t 00:03 -c:v copy -c:a copy $out_silence
ffprobe -i $out_silence 2>&1 | rg Duration 
  ##>   Duration: 00:00:03.02, start: 0.000000, bitrate: 497 kb/s
``` 

04: merge into shadowing video

``` bash
  ##> clip_name=spotlight
ffmpeg -f concat -i clips/video_files_merge.in -c copy clips/${clip_name}_silence.mp4
``` 

05: Follow normal path

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

#### optional: youtube playlist

rename youtube playlist files:

``` bash
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/rename_youtube_playlist_files.sh
``` 

move files to own directories:

``` bash
R --vanilla -e 'source("~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/move_youtube_playlist_files_to_their_own_dirs.R")'
``` 

loop through all directories and process videos:

``` bash
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/loop_dirs_and_make_shadowing_video_clips.sh
``` 

bütün klasörlerdeki sonuç (silence) dosyaları topla

``` bash
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/collect_youtube_playlist_results_into_sep_dir.sh clips_sub
``` 

bütün klasörlerdeki orjinal dosyaları topla:

``` bash
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/collect_youtube_playlist_originals_into_sep_dir.sh
``` 

3 veya 5 saniyelik boşluklarla videoları birleştir: (klasördeki tüm filmler için)

``` bash
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/loop_dirs_and_merge_silenced_clips02.sh
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/collect_youtube_playlist_results_into_sep_dir.sh
``` 

(sherlock yack için)

``` bash
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/loop_dirs_and_merge_silenced_clips.sh 05
``` 

(ted videoları için): ytlf ile indirilmiş klasörde çalıştır:

``` bash
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/rename_youtube_playlist_files.sh
R --vanilla -e 'source("~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/move_youtube_playlist_files_to_their_own_dirs.R")'
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/loop_dirs_and_make_shadowing_video_clips.sh
mkdir collected
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/collect_youtube_playlist_results_into_sep_dir.sh
``` 

make shadowing for all videos inside a folder

``` bash
function loop_streams() {
	VOLUME_INCREASE=2
	bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/loop_dirs_and_make_shadowing_video_clips.sh -v $VOLUME_INCREASE -S
}
loop_streams
``` 

#### optional: youtube srt correction

``` vim
%s/<br>[^<]*//
%s#;[^<]*#; #
``` 


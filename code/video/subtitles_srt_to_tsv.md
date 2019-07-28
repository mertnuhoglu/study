---
title: "Convert subtitles.srt files to tsv table 20190724"
date: 2019-07-24T09:40:24+03:00 
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

path: ~/projects/study/code/video/subtitles_srt_to_tsv.md
state: wip

---

# 01: Convert marks.txt to marks.tsv

## opt01: vim

Şu an vim'de yapıyoruz: `~/projects/study/code/video/ex/convert_marks_txt_2_marks_tsv/ConvertMarksTxt2MarksTsv.vim`

`:ConvertMarksTxt2MarksTsv`

Türkçe altyazıları da sorulara ekle. Ama önce vim kodunu r koduna çevirelim.

``` bash
mkdir -p clips
clip_name=sing.srt
cp "${clip_name}.srt" clips/marks.txt
nvim -c ":ConvertMarksTxt2MarksTsv" -c ":wq" clips/marks.txt
echo "start_time\tend_time\ttext" > clips/marks.tsv
cat clips/marks.txt >> clips/marks.tsv
``` 

``` r
library(dplyr)
offset_clip_id = 0
input = "sing.mp4"
clip_name = "sing"
one.video.to.multiple.clips::main_generate_ffmpeg_cmd_for_splitting_videos("clips/marks.tsv", offset_clip_id = offset_clip_id, original_video = input, clip_name = clip_name)
``` 

## opt02: R ile yap

### e01: sing.srt 

Input: `/Users/mertnuhoglu/projects/study/code/video/ex/convert_marks_txt_2_marks_tsv/e01/sing.srt`

Başlatmak için:

``` bash
mkdir -p clips
clip_name=sing
cp "${clip_name}.srt" clips/marks.txt
``` 

``` r
library(dplyr)
offset_clip_id = 0
input = "sing.mp4"
clip_name = "sing"
``` 

İngilizce altyazılar marks.tsv dosyasından geliyor. 

opt01: marks.tr.tsv oluştur. sonra bunları süreleri kullanarak birleştir

Edit `~/projects/study/code/video/ex/convert_marks_txt_2_marks_tsv/e01/convert_marks_txt_2_marks_tsv.R`

``` r
library(dplyr)
marks_txt = "clips/marks.txt"
m0 = readLines(marks_txt)
``` 

## opt03: sed ile yap

Edit `~/projects/study/code/video/ex/convert_marks_txt_2_marks_tsv/e02/convert_marks_txt_2_marks_tsv.sh`

## opt04: ex ile yap

Edit `~/projects/study/code/video/ex/convert_marks_txt_2_marks_tsv/e03/e01.vim`

Notlar:

" ile başlayan comment lineları scriptleri bozuyor

``` bash
mkdir -p clips
clip_name=sing
cp $clip_name.srt clips/marks.txt
nvim -s e01.vim clips/marks.txt
``` 

Final script: `~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/ConvertMarksTxt2MarksTsv.vim`

``` bash
nvim -s ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/ConvertMarksTxt2MarksTsv.vim clips/marks.txt
``` 

## Logs 20190727

### Ex: friends_01_01.srt

Input file: `/Users/mertnuhoglu/projects/study/code/video/ex/subtitles_srt_to_tsv/friends_01_01/friends_01_01.srt`

``` bash
cd ~/projects/study/code/video/ex/subtitles_srt_to_tsv/
cd friends_01_01
mkdir -p clips
input_srt=friends_01_01.srt
cp "${input_srt}" clips/marks.txt
``` 

Output: `~/projects/study/code/video/ex/subtitles_srt_to_tsv/friends_01_01/marks.txt`

Edit `/Users/mertnuhoglu/projects/study/code/video/ex/subtitles_srt_to_tsv/ex01.sh`

### Logs

#### delete lines with numbers

``` bash
  # delete number lines such as 24
sed '/^\d\+$/d' $input_srt > $out
sed '/^\d$/d' $input_srt > $out
sed '/^\d.*$/d' $input_srt > $out
``` 


# 02: Join marks.tsv with marks.tr.tsv

Türkçe altyazılarla İngilizce altyazıları birleştirelim

``` r
m0 = read_marks_tsv(path = "clips/marks.tsv")
m1 = read_marks_tsv(path = "clips/marks.tr.tsv")
m2 = m0 %>%
	left_join(m1, by = c("start_time", "end_time"))
``` 


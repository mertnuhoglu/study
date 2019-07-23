
# Cut video without losing quality 20190720 

ref:

		/Volumes/Elements/arsivden/bluray/spotlight/test_cut_video/

``` bash
input=cut14.mp4
dir_silence=test_silence_merge
dir_gen=$dir_silence/gen
mkdir -p $dir_gen
``` 

opt01: concat demuxer 

``` bash
out=${dir_gen}/merge01.mp4
ffmpeg -f concat -safe 0 -i <(printf "file '$PWD/cut14.mp4'\nfile '$PWD/cut14.mp4'") -c copy $out
``` 

opt02: add silence.mp4

``` bash
out=${dir_gen}/merge02.mp4
ffmpeg -f concat -safe 0 -i <(printf "file '$PWD/cut14.mp4'\nfile '$PWD/clips/silence.mp4'") -c copy $out
  ##> Non-monotonous DTS
``` 

opt03: videonun içinden 3 saniye kırp ve bunu kullan

``` bash
input=spotlight.mkv
out=${dir_gen}/silence01.mp4
ffmpeg -ss 00:56 -to 00:59 -i ${input} -c:v libx264 -crf 23 -c:a aac $out
input=$out
out=${dir_gen}/silence02.mp4
ffmpeg -i ${input} -t 00:03 -c:v copy -c:a copy $out
ffprobe -i $out 2>&1 | rg Duration 
  ##>   Duration: 00:00:03.02, start: 0.000000, bitrate: 497 kb/s
``` 

``` bash
silence=$out
out=${dir_gen}/merge03.mp4
ffmpeg -f concat -safe 0 -i <(printf "file '$PWD/cut14.mp4'\nfile '$PWD/$silence'") -c copy $out
``` 

opt04: videonun içinden 3 saniye kırp fakat sesi kaldır

``` bash
input=spotlight.mkv
out=${dir_gen}/silence01.mp4
ffmpeg -ss 00:56 -to 00:59 -i ${input} -c:v libx264 -crf 23 -c:a aac $out
input=$out
out=${dir_gen}/silence02.mp4
ffmpeg -i ${input} -t 00:03 -c:v copy -c:a copy $out
ffprobe -i $out 2>&1 | rg Duration 
  ##>   Duration: 00:00:03.02, start: 0.000000, bitrate: 497 kb/s
``` 

``` bash
silence=$out
out=${dir_gen}/merge03.mp4
ffmpeg -f concat -safe 0 -i <(printf "file '$PWD/cut14.mp4'\nfile '$PWD/$silence'") -c copy $out
``` 


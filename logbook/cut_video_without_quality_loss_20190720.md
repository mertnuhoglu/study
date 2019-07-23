
# Cut video without losing quality 20190720 

ref:

		/Volumes/Elements/arsivden/bluray/spotlight/test_cut_video/

next ref: `~/projects/study/logbook/add_silence_to_video_20190722.md`

``` bash
input=spotlight.mkv
dir_cut=test_cut_video
dir_gen=$dir_cut/gen
mkdir -p $dir_gen
``` 

opt01: 

https://stackoverflow.com/questions/15629490/how-to-cut-part-of-mp4-video-using-ffmpeg-without-losing-quality

``` bash
out=${dir_gen}/cut01.mp4
ffmpeg -i ${input} -t 150 -acodec copy -vcodec copy $out
ffprobe -i $out 2>&1 | rg Duration 
  ##>   Duration: 00:02:30.11, start: 0.000000, bitrate: 10910 kb/s
``` 

Düzgün.

Fakat düzgün çalışması başlangıçtan itibaren kesilmiş olmasından kaynaklı.

opt02: use -ss and -to

``` bash
out=${dir_gen}/cut02.mp4
ffmpeg -i ${input} -ss 00:00 -to 01:00 -acodec copy -vcodec copy $out
ffprobe -i $out 2>&1 | rg Duration 
  ##>   Duration: 00:01:00.00, start: 0.000000, bitrate: 5676 kb/s
``` 

Görüntünün ilk kısmı çıkmıyor. 

opt03: -ss before -i

``` bash
out=${dir_gen}/cut03.mp4
ffmpeg -ss 01:30 -to 02:00 -i ${input} -acodec copy -vcodec copy $out
ffprobe -i $out 2>&1 | rg Duration 
  ##>   Duration: 02:07:38.16, start: 0.000000, bitrate: 50 kb/s
``` 

Zaman bilgisi yanlış 

opt04: copy from middle

``` bash
out=${dir_gen}/cut04.mp4
ffmpeg -i ${input} -ss 01:00 -t 30 -acodec copy -vcodec copy $out
ffprobe -i $out 2>&1 | rg Duration 
  ##>   Duration: 00:00:30.02, start: 0.000000, bitrate: 8935 kb/s
``` 

Görüntünün ilk kısmı çıkmıyor. 

opt05: encode

``` bash
out=${dir_gen}/cut05.mp4
ffmpeg -ss 01:30 -to 02:00 -i ${input} -c:v libx264 -crf 23 -c:a aac $out
ffprobe -i $out 2>&1 | rg Duration 
  ##>   Duration: 02:07:38.16, start: 0.000000, bitrate: 10 kb/s
``` 

Zaman bilgisi yanlış

opt06: encode

``` bash
out=${dir_gen}/cut06.mp4
ffmpeg -i ${input} -ss 01:30 -to 02:00 -c:v libx264 -crf 23 -c:a aac $out
ffprobe -i $out 2>&1 | rg Duration 
  ##>   Duration: 00:00:30.03, start: 0.000000, bitrate: 2743 kb/s
``` 

Zaman doğru, fakat seeking yavaş.

opt07: inpoint

``` bash
out=${dir_gen}/cut07.mp4
ffmpeg -f concat -i files01.in -c:v libx264 -c:a aac $out
  ##> [aac @ 0x7fe5fd803800] Queue input is backward in time
  ##> [mp4 @ 0x7fe5fd800e00] Non-monotonous DTS in output stream 0:1; previous: 339232, current: 303776; changing to 339233. This may result in incorrect timestamps in the output file.
ffprobe -i $out 2>&1 | rg Duration 
  ##>   Duration: 00:00:08.07, start: 0.000000, bitrate: 3320 kb/s
``` 

Seslerin bir kısmı kopuk.

opt08: inpoint -y

``` bash
out=${dir_gen}/cut08.mp4
ffmpeg -y -f concat -i files01.in -c:v libx264 -c:a aac $out
  ##> [aac @ 0x7fe5fd803800] Queue input is backward in time
  ##> [mp4 @ 0x7fe5fd800e00] Non-monotonous DTS in output stream 0:1; previous: 339232, current: 303776; changing to 339233. This may result in incorrect timestamps in the output file.
ffprobe -i $out 2>&1 | rg Duration 
  ##>   Duration: 00:00:08.07, start: 0.000000, bitrate: 3320 kb/s
``` 

Seslerin bir kısmı kopuk.

opt09: encode -y

``` bash
out=${dir_gen}/cut09.mp4
ffmpeg -y -ss 01:30 -to 01:40 -i ${input} -c:v libx264 -crf 23 -c:a aac $out
ffprobe -i $out 2>&1 | rg Duration 
  ##>   Duration: 02:07:38.16, start: 0.000000, bitrate: 10 kb/s
``` 

Duration yanlış yine.

opt10: -use_wallclock_as_timestamps 1

``` bash
out=${dir_gen}/cut10.mp4
ffmpeg -use_wallclock_as_timestamps 1 -ss 01:30 -to 01:40 -i ${input} -c:v libx264 -crf 23 -c:a aac $out
``` 

Video çalışmıyor bile.

opt11: -f concat -use_wallclock_as_timestamps 1

``` bash
out=${dir_gen}/cut11.mp4
ffmpeg -use_wallclock_as_timestamps 1 -f concat -i files01.in -c:v libx264 -c:a aac $out
  ##> [aac @ 0x7fe5fd803800] Queue input is backward in time
  ##> [mp4 @ 0x7fe5fd800e00] Non-monotonous DTS in output stream 0:1; previous: 339232, current: 303776; changing to 339233. This may result in incorrect timestamps in the output file.
``` 

Tamamen hatalı.

opt12: opt05 kopyası. farklı bir mp4 dosyasını girdi olarak kullan

``` bash
input=mazjobrani/6_men_having_babies.mp4
out=${dir_gen}/cut12.mp4
ffmpeg -ss 00:30 -to 01:00 -i ${input} -c:v libx264 -crf 23 -c:a aac $out
ffprobe -i $out 2>&1 | rg Duration 
  Duration: 00:00:30.06, start: 0.000000, bitrate: 1419 kb/s
``` 

Zaman doğru

opt13: çifte encoding. opt05 çıktısı için

``` bash
input=${dir_gen}/cut05.mp4
out=${dir_gen}/cut13.mp4
ffmpeg -ss 00:10 -to 00:20 -i ${input} -c:v libx264 -crf 23 -c:a aac $out
ffprobe -i $out 2>&1 | rg Duration 
  ##>   Duration: 02:07:28.16, start: 0.000000, bitrate: 2 kb/s
``` 

opt014: -t ile tekrar copy et

https://video.stackexchange.com/questions/19784/howto-repaire-or-fix-the-duration-of-a-mp4-video 

``` bash
out=${dir_gen}/cut05.mp4
ffmpeg -ss 01:30 -to 02:00 -i ${input} -c:v libx264 -crf 23 -c:a aac $out
``` 

``` bash
input=${dir_gen}/cut05.mp4
out=${dir_gen}/cut14.mp4
ffmpeg -i $input -t 00:00:30 -c:v copy -c:a copy $out
ffprobe -i $out 2>&1 | rg Duration 
  ##>   Duration: 02:07:38.16, start: 0.000000, bitrate: 10 kb/s
``` 

Bu düzgün ve en hızlı çözüm.


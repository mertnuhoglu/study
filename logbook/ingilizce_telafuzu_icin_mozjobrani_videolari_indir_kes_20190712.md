
## İngilizce telafuzu için youtubedan mozjobrani videolarını indir ve kes 20190712 

### Result

#### 01: get video

``` bash
cd /Users/mertnuhoglu/Movies/mozjobrani
youtube-dl --write-auto-sub --write-sub --sub-lang en --convert-subs srt -i -t -f best -a links
mkdir -p saudi_indian_iranian_walk_into_a_qatari_bar_moz_jobrani
``` 

``` bash
ffmpeg -i "${input}" \
	-map 0:0 -map 0:2 \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a libfaac -q:a 32 \
	"${output}" 
``` 

#### 01: subtitle.srt to marks.tsv

Follow: Ex03: Extract clips from movie fideos with subtitles 20190712 <url:/Users/mertnuhoglu/projects/study/code/anki/process_anki_video_flashcards.md#tn=Ex03: Extract clips from movie fideos with subtitles 20190712>

``` bash
cp "~/Movies/mozjobrani/A Saudi, an Indian and an Iranian walk into a Qatari bar ... _ Maz Jobrani-9kxL9Cf46VM.en.srt" "~/Movies/mozjobrani/saudi_indian_iranian_walk_into_a_qatari_bar_moz_jobrani/marks0.txt"
cd /Users/mertnuhoglu/Movies/mozjobrani/saudi_indian_iranian_walk_into_a_qatari_bar_moz_jobrani
cp marks0.txt marks.txt
echo "start_time\tend_time\ttext" > marks.tsv
``` 

Convert `^M` into new line

``` bash
:ConvertMarksTxt2MarksTsv
:w
``` 

``` bash
echo "start_time\tend_time\ttext" > marks.tsv
cat marks.txt >> marks.tsv
``` 

#### 02: generate silence.mp4

``` bash
mkdir -p clips
ffprobe -i about_time.mp4
  ##>     Stream #0:1(eng): Audio: aac (mp4a / 0x6134706D), 48000 Hz, 5.1, fltp, 117 kb/s (default)
SAMPLE_RATE=48000
  # SAMPLE_RATE=44100
  # CHANNEL_LAYOUT=stereo
CHANNEL_LAYOUT=5.1
``` 

``` bash
ffmpeg -f lavfi -i anullsrc=channel_layout=${CHANNEL_LAYOUT}:sample_rate=${SAMPLE_RATE} -t 3 clips/silence.ac3
ffmpeg -i img001.jpg -i clips/silence.ac3 -acodec libfaac -vcodec libx264 clips/silence.mp4
``` 

Test silence.mp4 file. Edit `~/Movies/about_time/video_files_test.in`

``` txt
file 'clips/about_time_0005.mp4'
file 'clips/silence.mp4'
``` 

``` bash
ffmpeg -f concat -i video_files_test.in -c:v libx264 -crf 28 -vf "scale=320:240" -c:a libfaac -q:a 32 test.mp4
``` 

#### 03: split video into multiple clips

ref: `~/projects/study/r/one.video.to.multiple.clips/R/generate_ffmpeg_cmd_for_splitting_videos.R`

``` bash
cd /Users/mertnuhoglu/Movies/mozjobrani/saudi_indian_iranian_walk_into_a_qatari_bar_moz_jobrani/
mkdir -p clips/with_silence
``` 

``` r
offset_clip_id = 0
original_video = "moz_jobrani_01.mp4"
clip_name = "moz_jobrani_01"
one.video.to.multiple.clips::main_generate_ffmpeg_cmd_for_splitting_videos("marks.tsv", offset_clip_id = offset_clip_id, original_video = original_video, clip_name = clip_name)
``` 

``` bash
bash cmd.sh
VOLUME_INCREASE='-filter:a "volume=3.5"' 
CLIP_NAME="about_time_02"
MERGE_FILE="clips/video_files_merge.in"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a libfaac -q:a 32 "${VOLUME_INCREASE}" \
	clips/${CLIP_NAME}_silence.mp4
``` 

### Logs

Follow: Ex03: Extract clips from movie fideos with subtitles 20190712 <url:/Users/mertnuhoglu/projects/study/code/anki/process_anki_video_flashcards.md#tn=Ex03: Extract clips from movie fideos with subtitles 20190712>

``` bash
cd /Users/mertnuhoglu/Movies/mozjobrani
youtube-dl --write-auto-sub --write-sub --sub-lang en --convert-subs srt -i -t -f best -a links
mkdir -p saudi_indian_iranian_walk_into_a_qatari_bar_moz_jobrani
cp "~/Movies/mozjobrani/A Saudi, an Indian and an Iranian walk into a Qatari bar ... _ Maz Jobrani-9kxL9Cf46VM.en.srt" "~/Movies/mozjobrani/saudi_indian_iranian_walk_into_a_qatari_bar_moz_jobrani/marks0.txt"
cd /Users/mertnuhoglu/Movies/mozjobrani/saudi_indian_iranian_walk_into_a_qatari_bar_moz_jobrani
cp marks0.txt marks.txt
``` 

Convert `^M` into new line

``` bash
:ConvertMarksTxt2MarksTsv
``` 

``` r
offset_clip_id = 0
original_video = "moz_jobrani_01.mp4"
clip_name = "moz_jobrani_01"
``` 

``` bash
cd /Users/mertnuhoglu/Movies/mozjobrani/
mkdir -p clips
bash ~/Movies/mozjobrani/saudi_indian_iranian_walk_into_a_qatari_bar_moz_jobrani/cmd.sh
``` 

Şimdi videoların görüntülerini silip birleştirelim.

#### 01: video kliplerinin sonuna 3 saniye sessizlik ekle

Result

``` bash
mkdir -p with_silence
ffmpeg -f lavfi -i anullsrc=channel_layout=stereo:sample_rate=44100 -t 3 silence.ac3
ffmpeg -i img001.jpg -i silence.ac3 -acodec libfaac -vcodec libx264 silence.mp4
``` 

##### örnek tek bir video klibinin sonuna 3 saniye ekleme:

Result:

https://superuser.com/questions/1041816/combine-one-image-one-audio-file-to-make-one-video-using-ffmpeg

https://stackoverflow.com/questions/12368151/adding-silent-audio-in-ffmpeg

https://superuser.com/questions/579008/add-1-second-of-silence-to-audio-through-ffmpeg

``` bash
ffmpeg -f lavfi -i anullsrc=channel_layout=stereo:sample_rate=44100 -t 3 silence03b.ac3
ffprobe -i silence03b.ac3
  ##>     Stream #0:0: Audio: ac3, 44100 Hz, stereo, fltp, 192 kb/s
ffmpeg -i img001.jpg -i silence03b.ac3 -acodec libfaac -vcodec libx264 s06.mp4
ffprobe -i s06.mp4
  ##>     Stream #0:1(und): Audio: aac (mp4a / 0x6134706D), 44100 Hz, stereo, fltp, 3 kb/s (default)
``` 

Check `~/Movies/mozjobrani/clips/video_files06.in`

``` txt
file 'moz_jobrani_01_0001.mp4'
file 's06.mp4'
``` 

``` bash
ffmpeg -f concat -i video_files06.in -c:v libx264 -c:a libfaac 01g.mp4
``` 

###### Error: videoları birbirine eklemedi

``` bash
ffmpeg -f lavfi -i anullsrc=channel_layout=5.1:sample_rate=48000 -t 3 silence03.ac3
ffmpeg -i img001.jpg -i silence03.ac3 -acodec libfaac -vcodec libx264 s04.mp4
``` 

Gerçek video üzerinde yapalım:

Check `~/Movies/mozjobrani/clips/video_files.in`

``` txt
file 'moz_jobrani_01_0001.mp4'
file 's04.mp4'
``` 

``` bash
ffmpeg -f concat -i video_files04.in -c:v libx264 -c:a libfaac 01e.mp4
``` 

Gerçek video üzerinde yapalım:

Check `~/Movies/mozjobrani/clips/video_files04.in`

``` txt
file 'moz_jobrani_01_0001.mp4'
file 's04.mp4'
``` 

``` bash
ffmpeg -f concat -i video_files04.in -c:v libx264 -c:a libfaac 01e.mp4
``` 

opt01: iki videoyu birleştirmeyi dene

Check `~/Movies/mozjobrani/clips/video_files05.in`

``` txt
file 'moz_jobrani_01_0001.mp4'
file 'moz_jobrani_01_0002.mp4'
``` 

``` bash
ffmpeg -f concat -i video_files05.in -c:v libx264 -c:a libfaac 01f.mp4
``` 

opt02: match format channel, layout and sample rate

``` bash
ffprobe -i moz_jobrani_01_0001.mp4
  ##>     Stream #0:1(und): Audio: aac (mp4a / 0x6134706D), 44100 Hz, stereo, fltp, 98 kb/s (default)
ffprobe -i silence04.mp4
  ##>     Stream #0:0: Audio: ac3, 48000 Hz, 5.1(side), fltp, 448 kb/s
``` 

``` bash
ffmpeg -f lavfi -i anullsrc=channel_layout=5.1:sample_rate=44100 -t 3 silence03.ac3
ffmpeg -i img001.jpg -i silence03.ac3 -acodec libfaac -vcodec libx264 s04.mp4
``` 

What is channel layout?

https://www.get-metadata.com/file-info/channel-layout

mono or stereo.

exp01: channel layout stereo silence üret

``` bash
ffmpeg -f lavfi -i anullsrc=channel_layout=stereo:sample_rate=44100 -t 3 silence03b.ac3
ffprobe -i silence03b.ac3
  ##>     Stream #0:0: Audio: ac3, 44100 Hz, stereo, fltp, 192 kb/s
ffmpeg -i img001.jpg -i silence03b.ac3 -acodec libfaac -vcodec libx264 s06.mp4
ffprobe -i s06.mp4
  ##>     Stream #0:1(und): Audio: aac (mp4a / 0x6134706D), 44100 Hz, stereo, fltp, 3 kb/s (default)
``` 

Check `~/Movies/mozjobrani/clips/video_files06.in`

``` txt
file 'moz_jobrani_01_0001.mp4'
file 's06.mp4'
``` 

``` bash
ffmpeg -f concat -i video_files06.in -c:v libx264 -c:a libfaac 01g.mp4
ffprobe -i 01g.mp4
  ##>   Duration: 00:00:07.44, start: 0.000000, bitrate: 847 kb/s
  ##>     Stream #0:0(und): Video: h264 (High) (avc1 / 0x31637661), yuv420p, 1280x720 [SAR 1:1 DAR 16:9], 1310 kb/s, 25 fps, 25 tbr, 12800 tbn, 50 tbc (default)
  ##>     Metadata:
  ##>       handler_name    : VideoHandler
  ##>     Stream #0:1(und): Audio: aac (mp4a / 0x6134706D), 44100 Hz, stereo, fltp, 59 kb/s (default)
ffprobe -i moz_jobrani_01_0001.mp4
  ##>   Duration: 00:00:04.37, start: 0.023220, bitrate: 1447 kb/s
  ##>     Stream #0:0(und): Video: h264 (High) (avc1 / 0x31637661), yuv420p, 1280x720 [SAR 1:1 DAR 16:9], 1344 kb/s, 25 fps, 25 tbr, 12800 tbn, 50 tbc (default)
  ##>     Metadata:
  ##>       handler_name    : VideoHandler
  ##>     Stream #0:1(und): Audio: aac (mp4a / 0x6134706D), 44100 Hz, stereo, fltp, 98 kb/s (default)
``` 

Ok, this works well. 

###### Error: video süreleri hatalı

``` bash
ffmpeg -f lavfi -i anullsrc=channel_layout=5.1:sample_rate=48000 -t 1 silence01.ac3
ffmpeg -i img001.jpg -i silence01.ac3 -acodec libfaac -vcodec libx264 s03.mp4
``` 

``` bash
ffmpeg -f lavfi -i anullsrc=channel_layout=stereo:sample_rate=44100 -i moz_jobrani_01_0001.mp4 \
ffmpeg -f lavfi -i aevalsrc=0 -i ${input} -vcodec copy -acodec aac -map 0:0 -map 1:0 -shortest -strict experimental -y 01b.mp4
``` 

``` bash
ffmpeg -loop 1 -i img001.jpg -f lavfi -i anullsrc=channel_layout=5.1:sample_rate=48000 -t 3 -c:v libx264 -t 3 -pix_fmt yuv420p -vf scale=320:240 -y silent.mp4
``` 

şimdi mp4 dosyalarını birleştir

Check `~/Movies/mozjobrani/clips/video_files.in`

Now concat videos:

``` bash
ffmpeg -f concat -i video_files.in -codec copy 01c.mp4
``` 

Bu şekilde süreler bozuluyor. 

opt01: `-codec copy` yerine yine `lib264` ile kodla.

``` bash
ffmpeg -f concat -i video_files.in -c:v libx264 -c:a libfaac 01d.mp4
``` 

İlk video tamam, ama bu sefer silent video hatalı olmuş. 

###### Error: silent video süresi neden çok uzun?

https://superuser.com/questions/579008/add-1-second-of-silence-to-audio-through-ffmpeg

``` bash
ffmpeg -f lavfi -i anullsrc=channel_layout=5.1:sample_rate=48000 -t 1 silence01.ac3
``` 

Bu şekilde 1 saniyelik oluyor doğru.

Buna resim nasıl ekleyeceğiz?

``` bash
ffmpeg -loop 1 -i img001.jpg -f lavfi -i anullsrc=channel_layout=5.1:sample_rate=48000 -t 3 -c:v libx264 -t 3 -pix_fmt yuv420p -vf scale=320:240 -y silent.mp4
``` 

https://superuser.com/questions/1041816/combine-one-image-one-audio-file-to-make-one-video-using-ffmpeg

``` bash
ffmpeg -loop 1 -i img001.jpg -i silence01.ac3 -c:v libx264 -tune stillimage -c:a libfaac -b:a 192k -pix_fmt yuv420p -shortest s02.mp4
``` 

Yine de süre olması gerekenden daha uzun, neden?

opt02: 

``` bash
ffmpeg -i img001.jpg -i silence01.ac3 -acodec libfaac -vcodec libx264 s03.mp4
``` 

Tamam, bu çalıştı.

#### download new videos

``` bash
youtube-dl --write-auto-sub --write-sub --sub-lang en --convert-subs srt -i -f best -o "%(playlist)s/%(playlist_index)s-%(title)s-%(id)s.%(ext)s" -a links
``` 

``` bash
ffmpeg -i "${input}" \
	-map 0:0 -map 0:2 \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a libfaac -q:a 32 \
	"${output}" 
``` 

#### split video into multiple clips

##### Result

ref: `~/projects/study/r/one.video.to.multiple.clips/R/generate_ffmpeg_cmd_for_splitting_videos.R`

``` bash
cd /Users/mertnuhoglu/Movies/mozjobrani/saudi_indian_iranian_walk_into_a_qatari_bar_moz_jobrani/
mkdir -p clips/with_silence
``` 

``` r
library(one.video.to.multiple.clips)
library(dplyr)
specs = readr::cols(
	start_time = readr::col_time(format = "%H:%M:%OS"),
	end_time = readr::col_time(format = "%H:%M:%OS"),
	text = readr::col_character()
)
offset_clip_id = 0
original_video = "moz_jobrani_01.mp4"
clip_name = "moz_jobrani_01"
m0 = readr::read_tsv("marks.tsv", col_types = specs) %>%
	one.video.to.multiple.clips::generate_ffmpeg_cmd_for_splitting_videos(offset_clip_id = offset_clip_id, original_video = original_video, clip_name = clip_name)
writeLines(m0$cmd, "cmd.sh")
writeLines(m0$cmd_concat_silence, "cmd_concat_silence.sh")
readr::write_tsv(m0, "clips.tsv")
for (i in seq_len(nrow(m0))) {
	row = m0[i, ]
	writeLines(row$video_files_for_concat_silence, glue::glue("clips/video_files{row$clip_id}.in"))
}
``` 

``` bash
bash cmd.sh
bash cmd_concat_silence.sh
``` 

##### Logs

``` r
usethis::create_package("generate_ffmpeg_cmd_for_splitting_videos")
usethis::use_package("dplyr")
usethis::use_package("glue")
``` 

``` bash
cd /Users/mertnuhoglu/Movies/mozjobrani/saudi_indian_iranian_walk_into_a_qatari_bar_moz_jobrani
``` 

``` r
library(one.video.to.multiple.clips)
library(dplyr)
specs = readr::cols(
	start_time = readr::col_time(format = "%H:%M:%OS"),
	end_time = readr::col_time(format = "%H:%M:%OS"),
	text = readr::col_character()
)
offset_clip_id = 0
original_video = "moz_jobrani_01.mp4"
clip_name = "moz_jobrani_01"
m0 = readr::read_tsv("marks.tsv", col_types = specs) %>%
	one.video.to.multiple.clips::generate_ffmpeg_cmd_for_splitting_videos(offset_clip_id = offset_clip_id, original_video = original_video, clip_name = clip_name)
writeLines(m0$cmd, "cmd.sh")
writeLines(m0$cmd_concat_silence, "cmd_concat_silence.sh")
readr::write_tsv(m0, "clips.tsv")
for (i in seq_len(nrow(m0))) {
	row = m0[i, ]
	writeLines(row$video_files_for_concat_silence, glue::glue("clips/video_files{row$clip_id}.in"))
}
``` 

Here is the `ffmpeg` `concat` commands: `~/Movies/mozjobrani/saudi_indian_iranian_walk_into_a_qatari_bar_moz_jobrani/cmd_concat_silence.sh`

###### generate video_files.in files

``` r
m0$video_files_for_concat_silence[1]
  ##> file 'moz_jobrani_01_0001.mp4'
  ##> file 'silence.mp4'
``` 

``` r
install.packages("purrrlyr")
``` 

``` r
writeLines(m0$video_files_for_concat_silence[1], glue::glue("clips/video_files{1}.in"))
``` 

###### run script

``` bash
cd /Users/mertnuhoglu/Movies/mozjobrani/saudi_indian_iranian_walk_into_a_qatari_bar_moz_jobrani/
mkdir -p clips/with_silence
bash cmd.sh
``` 

``` bash
cd clips
bash cmd_concat_silence.sh
``` 

#### merge all videos

``` bash
ffmpeg -f concat -i clips/video_files_merge2.in -c:v libx264 -c:a libfaac clips/moz_jobrani_01_silence.mp4
``` 


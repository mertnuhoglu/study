## ffmpeg video compression 20190711 

### Result

Birden çok audio stream varken, sadece 2 nolu audio stream ve 0 nolu video stream ile kayıt yap

``` bash
input=Spotlight\ 2015\ 1080p\ DUAL.mkv
clip_name=spotlight
output="${clip_name}.mp4"
ffprobe -i ${input} 2>&1 | rg eng | rg Stream | rg Audio
  ##>     Stream #0:2(eng): Audio: dts (DTS), 48000 Hz, 5.1(side), fltp, 1536 kb/s
stream=2
VOLUME_INCREASE=2
``` 

``` bash
ffmpeg -i "${input}" \
	-map 0:0 -map 0:${stream} \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a aac -q:a 32 -filter:a "volume=${VOLUME_INCREASE}" \
	"${output}" 
``` 

opt01: copy audio (no compression). don't use this. synchronization problems between audio and video

``` bash
ffmpeg -i "${input}" \
	-map 0:0 -map 0:${stream} \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a copy \
	"${output}" 
``` 

### Logs

https://trac.ffmpeg.org/wiki/Encode/AAC

Compress 90 min video into 700 MB

``` bash
file="Ice Age.mkv"
ffmpeg -y -i "${file}" -c:v libx264 -b:v 933k -preset:v veryfast -pass 1 -an /dev/null && \
ffmpeg -i "${file}" -c:v libx264 -b:v 933k -preset:v veryfast -pass 2 \
-ac 2 -c:a libfdk_aac -b:a 128k ice_age_700.mp4
``` 

libx264 with no `-c:a`

``` bash
ffmpeg -i "${file}" -vcodec libx264 -crf 28 ice_age_264_28.mp4
``` 

What is `crf`

Scaling the frame size

``` bash
ffmpeg -i "${file}" -vcodec libx264 -crf 28 -vf "scale=iw/4:ih/4" ice_age_28_size4.mp4
ffmpeg -i "${file}" -vcodec libx264 -crf 28 -vf "scale=iw/6:ih/6" ice_age_28_size6.mp4
ffmpeg -i "${file}" -vcodec libx264 -crf 28 -vf "scale=320:240" ice_age_28_size320.mp4
``` 

libaac

``` bash
ffmpeg -i "${file}" -vcodec libx264 -crf 28 -vf "scale=320:240" -c:a libfaac -q:a 64  ice_age_28_size320_aac64.mp4
ffmpeg -i "${file}" -vcodec libx264 -crf 28 -vf "scale=320:240" -c:a libfaac -q:a 32  ice_age_28_size320_aac32.mp4
ffmpeg -i "${file}" -vcodec libx264 -crf 28 -vf "scale=160:120" -c:a libfaac -q:a 32  ice_age_28_size160_aac32.mp4
``` 

sizes:

``` bash
ffprobe -i ice_age_28.mp4 2>&1 | rg Duration
  ##>   Duration: 00:02:40.82, start: 0.021333, bitrate: 1302 kb/s
ffprobe -i ice_age_28_size320_aac64.mp4 2>&1 | rg Duration
  ##>   Duration: 00:11:09.88, start: 0.021333, bitrate: 322 kb/s
ffprobe -i ice_age_28_size320.mp4 2>&1 | rg Duration
  ##>   Duration: 00:02:48.38, start: 0.021333, bitrate: 280 kb/s
ffprobe -i ice_age_28_size320_aac32.mp4 2>&1 | rg Duration
  ##>   Duration: 00:02:35.73, start: 0.021333, bitrate: 200 kb/s
``` 

Best compression

``` bash
ffmpeg -i "${file}" -vcodec libx264 -crf 28 -vf "scale=320:240" -c:a libfaac -q:a 32  ice_age_28_size320_aac32.mp4
``` 

Make it a function:

``` bash
function convert2mp4_h264_size320() {
	input=$1 &&
	output=$2 &&
	ffmpeg -i "${input}" -vcodec libx264 -crf 28 -vf "scale=320:240" -c:a libfaac -q:a 32  "${output}"
}
``` 

Result:

``` bash
file="Ice Age.mkv"
convert2mp4_h264_size320 ${file} ice_age.mp4
``` 

### multiple audio streams

``` bash
ffmpeg -i "${input}" -c copy -map 0 -vcodec libx264 -crf 28 -vf "scale=320:240" -c:a libfaac -q:a 32  "${output}"
  ##> Stream mapping:
  ##>   Stream #0:0 -> #0:0 (h264 -> libx264)
  ##>   Stream #0:1 -> #0:1 (copy)
  ##>   Stream #0:2 -> #0:2 (copy)
  ##>   Stream #0:3 -> #0:3 (subrip -> ?)
  ##> Encoder (codec none) not found for output stream #0:3
``` 

Manual:

           For example

                   ffmpeg -i INPUT -map 0 -c:v libx264 -c:a copy OUTPUT

           encodes all video streams with libx264 and copies all audio streams.

           For each stream, the last matching "c" option is applied, so

                   ffmpeg -i INPUT -map 0 -c copy -c:v:1 libx264 -c:a:137 libvorbis OUTPUT

           will copy all the streams except the second video, which will be encoded with libx264, and the 138th audio, which will be encoded with libvorbis.

Hata mesajı:

		##> Encoder (codec none) not found for output stream #0:3

stream #0:3 aslında subrip yani altyazı. onu dahil etmemeliyim

#### Example: map

https://trac.ffmpeg.org/wiki/Map

		In all following examples, we will use an example input file like this one:

		fmpeg -i input.mkv

		ffmpeg version ... Copyright (c) 2000-2012 the FFmpeg developers
		...
		Input #0, matroska,webm, from 'input.mkv':
			Duration: 01:39:44.02, start: 0.000000, bitrate: 5793 kb/s
				Stream #0:0(eng): Video: h264 (High), yuv420p, 1920x800, 23.98 fps, 23.98 tbr, 1k tbn, 47.95 tbc (default)
				Stream #0:1(ger): Audio: dts (DTS), 48000 Hz, 5.1(side), s16, 1536 kb/s (default)
				Stream #0:2(eng): Audio: dts (DTS), 48000 Hz, 5.1(side), s16, 1536 kb/s
				Stream #0:3(ger): Subtitle: text (default)
		At least one output file must be specified

		Example 1

		Now, let's say we want to:

		copy video stream
		encode german audio stream to mp3 (128kbps) and aac (96kbps) (creating 2 audio streams in the output)
		drop english audio stream
		copy subtitle stream
		This can be done using the following FFmpeg command line:

		ffmpeg -i input.mkv \
				-map 0:0 -map 0:1 -map 0:1 -map 0:3 \
				-c:v copy \
				-c:a:0 libmp3lame -b:a:0 128k \
				-c:a:1 libfaac -b:a:1 96k \
				-c:s copy \
				output.mkv

#### Kendi kodumuz

``` bash
  ##>   Stream #0:0 -> #0:0 (h264 -> libx264)
  ##>   Stream #0:1 -> #0:1 (copy)
  ##>   Stream #0:2 -> #0:2 (copy)
  ##>   Stream #0:3 -> #0:3 (subrip -> ?)
ffmpeg -i "${input}" \
	-map 0:0 -map 0:1 -map 0:2 \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a libfaac -q:a 32 \
	"${output}" 
``` 

##### Error: not write header for output file #0 (incorrect codec parameters ?): Invalid argument

reproduce error:

``` bash
ffmpeg -i "${input}" \
	-map 0:0 -map 0:1 -map 0:2 -map 0:3 \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a libfaac -q:a 32 \
	-c:s copy \
	"${output}" 
``` 

Remove `c:s`

``` bash
ffmpeg -i "${input}" \
	-map 0:0 -map 0:1 -map 0:2 \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a libfaac -q:a 32 \
	"${output}" 
``` 

It works

#### Audio stream sıralamasını değiştir

``` bash
ffmpeg -i "${input}" \
	-map 0:0 -map 0:2 -map 0:1 \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a libfaac -q:a 32 \
	"${output}" 
``` 

#### Sadece İngilizce ses kaydını muhafaza et

``` bash
ffmpeg -i "${input}" \
	-map 0:0 -map 0:2 \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a libfaac -q:a 32 \
	"${output}" 
``` 

``` bash
ffmpeg -i "${input}" \
	-map 0:0 -map 0:1 \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a libfaac -q:a 32 \
	"${output}" 
``` 

## marking video bookmarks with vlc

Install Moments Tracker:

``` bash
mv 168031-moments_tracker_V1.lua /Applications/VLC.app/Contents/MacOS/share/lua/extensions/
``` 

Çok iyi çalışmıyor. bookmarkları kullanma. onun yerine `Ex03: Extract clips from movie videos with subtitles 20190712 <url:/Users/mertnuhoglu/projects/study/code/anki/process_anki_video_flashcards.md#tn=Ex03: Extract clips from movie videos with subtitles 20190712>` içindeki gibi doğrudan altyazıların zamanlamalarını marker olarak kullan.


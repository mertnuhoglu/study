
# Logs 20190719  

## Make shadowing videos 20190719 

Ref: Önceki loglar: `~/projects/study/logbook/log_20190712.md`

Ref: opt: `~/projects/study/logbook/cut_video_without_quality_loss_20190720.md`

### Result

Ref: Final: `~/projects/study/code/anki/process_anki_video_flashcards.md`

01: compress video. ref: `~/projects/study/logbook/ffmpeg_video_compression_20190711.md`

02: generate cutting commands

Generate `marks.tsv`

``` bash
cp "${clip_name}.srt" marks.txt
nvim -c ":ConvertMarksTxt2MarksTsv" -c ":wq" marks.txt
echo "start_time\tend_time\ttext" > marks.tsv
cat marks.txt >> marks.tsv
``` 

``` r
offset_clip_id = 0
input = "spotlight.mp4"
clip_name = "spotlight"
one.video.to.multiple.clips::main_generate_ffmpeg_cmd_for_splitting_videos("marks.tsv", offset_clip_id = offset_clip_id, original_video = input, clip_name = clip_name)
``` 

02: split into parts

``` bash
bash cmd.sh
``` 

03: Generate silence video file

``` bash
ffprobe -i ${input} 2>&1 | rg eng | rg Stream | rg Audio
  ##>     Stream #0:2(eng): Audio: dts (DTS), 48000 Hz, 5.1(side), fltp, 1536 kb/s
  # SAMPLE_RATE=44100
  # CHANNEL_LAYOUT=stereo
stream=2
export SAMPLE_RATE=48000
export CHANNEL_LAYOUT=5.1
``` 

``` bash
ffmpeg -f lavfi -i anullsrc=channel_layout=${CHANNEL_LAYOUT}:sample_rate=${SAMPLE_RATE} -t 3 clips/silence.ac3
ffmpeg -i img001.jpg -vf scale=320:240 img001.jpg
ffmpeg -i img001.jpg -i clips/silence.ac3 -acodec aac -vcodec libx264 clips/silence.mp4
``` 

04: merge into shadowing video

``` bash
MERGE_FILE="clips/video_files_merge.in"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a copy \
	clips/${clip_name}_silence.mp4
``` 

## Error: ses kalitesi çok düşüyor

Hangi işlemleri yapıyorum?

01: compress video:

ref: `~/projects/study/logbook/ffmpeg_video_compression_20190711.md`

``` bash
input=Spotlight\ 2015\ 1080p\ DUAL.mkv
output=spotlight.mp4
ffmpeg -i "${input}" \
	-map 0:0 -map 0:${stream} \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a libfaac -q:a 32 -filter:a "volume=${VOLUME_INCREASE}" \
	"${output}" 
``` 

02: split into parts

ref: `~/projects/study/code/anki/process_anki_video_flashcards.md`

``` bash
ffmpeg -i secret_life_of_pets.mp4 -ss 00:01:26 -to 00:01:28 -c:v libx264 -c:a libfaac secret_life_of_pets_0001.mp4
``` 

03: merge with silence

``` bash
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a libfaac -q:a 32 "${VOLUME_INCREASE}" \
	clips/${CLIP_NAME}_silence.mp4
``` 

### Article: FFmpeg Encode / AAC

https://trac.ffmpeg.org/wiki/Encode/AAC

### Article: FFmpeg Encode/HighQualityAudio

https://trac.ffmpeg.org/wiki/Encode/HighQualityAudio

### opt01: doğrudan copy ile sesi aktar

01: compress video

``` bash
input=Spotlight\ 2015\ 1080p\ DUAL.mkv
output=spotlight.mp4
stream=2
ffmpeg -i "${input}" \
	-map 0:0 -map 0:${stream} \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a copy \
	"${output}" 
``` 

01b: marks.tsv

``` bash
cp spotlight.srt marks.txt
nvim -c ":ConvertMarksTxt2MarksTsv" -c ":wq" marks.txt
echo "start_time\tend_time\ttext" > marks.tsv
cat marks.txt >> marks.tsv
``` 

``` r
offset_clip_id = 0
input = "spotlight.mp4"
clip_name = "spotlight"
one.video.to.multiple.clips::main_generate_ffmpeg_cmd_for_splitting_videos("marks.tsv", offset_clip_id = offset_clip_id, original_video = input, clip_name = clip_name)
``` 

01c: silence video

``` bash
ffprobe -i ${input} 2>&1 | rg eng | rg Stream | rg Audio
  ##>     Stream #0:2(eng): Audio: dts (DTS), 48000 Hz, 5.1(side), fltp, 1536 kb/s
stream=2
export VOLUME_INCREASE=3.5
  # SAMPLE_RATE=44100
  # CHANNEL_LAYOUT=stereo
export SAMPLE_RATE=48000
export CHANNEL_LAYOUT=5.1
``` 

``` bash
ffmpeg -f lavfi -i anullsrc=channel_layout=${CHANNEL_LAYOUT}:sample_rate=${SAMPLE_RATE} -t 3 clips/silence.ac3
ffmpeg -i img001.jpg -vf scale=320:240 img001.jpg
ffmpeg -i img001.jpg -i clips/silence.ac3 -acodec aac -vcodec libx264 clips/silence.mp4
``` 

02: split into parts

``` bash
bash cmd.sh
``` 

Content:

opt01: audio copy

``` bash
ffmpeg -i 'spotlight.mp4' -ss 00:01:47.817 -to 00:01:50.111 -c:v libx264 -c:a copy clips/'spotlight_0013.mp4'
ffmpeg -i 'spotlight.mp4' -ss 00:01:50.195 -to 00:01:52.614 -c:v libx264 -c:a copy clips/'spotlight_0014.mp4'
ffmpeg -i 'spotlight.mp4' -ss 00:01:53.656 -to 00:01:55.658 -c:v libx264 -c:a copy clips/'spotlight_0015.mp4'
ffmpeg -i 'spotlight.mp4' -ss 00:01:55.909 -to 00:01:58.203 -c:v libx264 -c:a copy clips/'spotlight_0016.mp4'
``` 

opt02: audio libfaac

03: merge into shadowing video

``` bash
clip_name="spotlight"
MERGE_FILE="clips/video_files01.in"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a copy \
	clips/${clip_name}_silence.mp4
``` 

### acaba libx264 yerine copy desek de düzgün çalışır mı?

``` bash
ffmpeg -ss 00:01:23.835 -i 'spotlight.mp4' -to 00:01:24.919 -c:v copy -c:a copy -copyts clips_copy/'spotlight_0001.mp4'
ffmpeg -ss 00:01:25.003 -i 'spotlight.mp4' -to 00:01:27.172 -c:v copy -c:a copy -copyts clips_copy/'spotlight_0002.mp4'
ffmpeg -ss 00:01:27.964 -i 'spotlight.mp4' -to 00:01:28.965 -c:v copy -c:a copy -copyts clips_copy/'spotlight_0003.mp4'
ffmpeg -ss 00:01:29.424 -i 'spotlight.mp4' -to 00:01:31.259 -c:v copy -c:a copy -copyts clips_copy/'spotlight_0004.mp4'
ffmpeg -ss 00:01:31.342 -i 'spotlight.mp4' -to 00:01:33.344 -c:v copy -c:a copy -copyts clips_copy/'spotlight_0005.mp4'
ffmpeg -ss 00:01:33.970 -i 'spotlight.mp4' -to 00:01:35.096 -c:v copy -c:a copy -copyts clips_copy/'spotlight_0006.mp4'
``` 

Daha kaliteli ve dosya boyutu hemen hemen aynı.

### birleştirmeyi de test et

``` bash
MERGE_FILE="clips/video_files03.in"
clip_name="spotlight03x264"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a copy \
	clips/${clip_name}_silence.mp4
``` 

Hatalı birleştiriyor. Aşırı derecede uzatıyor bazı klipleri.

``` bash
MERGE_FILE="clips/video_files03.in"
clip_name="spotlight03copy"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v copy \
	-c:a copy \
	clips/${clip_name}_silence.mp4
``` 

Hatalı birleştiriyor

``` bash
MERGE_FILE="clips/video_files03.in"
clip_name="spotlight03x264aac"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a aac \
	clips/${clip_name}_silence.mp4
``` 

Hatalı birleştiriyor

### Error: audio copy olunca videoları birleştirince hata çıkıyor

opt01: ilk parçalama sırasında da libx264 yapalım

``` bash
ffmpeg -ss 00:01:23.835 -i 'spotlight.mp4' -to 00:01:24.919 -c:v libx264 -c:a copy -copyts clips_opt01/'spotlight_0001.mp4'
ffmpeg -ss 00:01:25.003 -i 'spotlight.mp4' -to 00:01:27.172 -c:v libx264 -c:a copy -copyts clips_opt01/'spotlight_0002.mp4'
ffmpeg -ss 00:01:27.964 -i 'spotlight.mp4' -to 00:01:28.965 -c:v libx264 -c:a copy -copyts clips_opt01/'spotlight_0003.mp4'
ffmpeg -ss 00:01:29.424 -i 'spotlight.mp4' -to 00:01:31.259 -c:v libx264 -c:a copy -copyts clips_opt01/'spotlight_0004.mp4'
ffmpeg -ss 00:01:31.342 -i 'spotlight.mp4' -to 00:01:33.344 -c:v libx264 -c:a copy -copyts clips_opt01/'spotlight_0005.mp4'
ffmpeg -ss 00:01:33.970 -i 'spotlight.mp4' -to 00:01:35.096 -c:v libx264 -c:a copy -copyts clips_opt01/'spotlight_0006.mp4'
``` 

#### Error: Too many packets buffered for output stream 0:1.  Conversion failed!

try01:

``` bash
MERGE_FILE="clips_opt01/video_files04.in"
clip_name="spotlight04x264"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a copy \
	clips/${clip_name}_silence.mp4
``` 

ffprobe:

		[mov,mp4,m4a,3gp,3g2,mj2 @ 0x7ff6e1806400] Could not find codec parameters for stream 0 (Video: h264 (avc1 / 0x31637661), none, 320x240, 156 kb/s): unspecified pixel format
		Consider increasing the value for the 'analyzeduration' and 'probesize' options

try02: 

``` bash
MERGE_FILE="clips_opt01/video_files04.in"
clip_name="spotlight04x264aac"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a aac \
	clips/${clip_name}_silence.mp4
``` 

Aynı hata

opt01: `-max_muxing_queue_size 1024`

https://stackoverflow.com/questions/49686244/ffmpeg-too-many-packets-buffered-for-output-stream-01

#### Error: More than 1000 frames duplicated

``` bash
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 -max_muxing_queue_size 1024 \
	-c:a copy \
	clips_opt01/${clip_name}_silence.mp4
``` 

		[mov,mp4,m4a,3gp,3g2,mj2 @ 0x7fa9f200e400] Could not find codec parameters for stream 0 (Video: h264 (avc1 / 0x31637661), none, 320x240, 114 kb/s): unspecified pixel format
		Consider increasing the value for the 'analyzeduration' and 'probesize' options
		[mov,mp4,m4a,3gp,3g2,mj2 @ 0x7fa9f200e400] Auto-inserting h264_mp4toannexb bitstream filter
		[swscaler @ 0x10d6dc000] deprecated pixel format used, make sure you did set range correctly
		More than 1000 frames duplicated

https://ffmpeg.org/pipermail/ffmpeg-user/2018-February/038973.html

> Quoting from https://video.stackexchange.com/questions/20958/ffmpeg-dropping-duplicate-frames
> 
>     FFmpeg duplicates or drops frames when the input and output framerates differ. 
> 
> If I apply an ffmpeg command line to process a raw MTS file
> from a camera, I do not get "More than 1000 frames duplicated"
> warnings during processing.
> 
> However if I combine MTS files using pipe concatenation,
> e.g.
> 
>     ffmpeg -i concat:"./00000.MTS|./00001.MTS" -codec copy ./00000.mp4
> 
> then run the same command line on the mp4 result, I get
> plentiful duplicate frame warnings.

Hipotez: Girdi video stream ile girdi audio streamin framerateleri farklı. Neden? Çünkü girdi olarak kullandığım videoyu, daha önce libx264 ile video tarafını oluşturmuş, fakat audio tarafını copy ile direk almıştım.

##### try01: Compress both video and audio in first step

``` bash
VOLUME_INCREASE=1
stream=2
output=spotlight_audio_aac.mp4
ffmpeg -i "${input}" \
	-map 0:0 -map 0:${stream} \
	-c:v libx264 -crf 28 -vf "scale=320:240" \
	-c:a aac \
	"${output}" 
``` 

``` bash
cp clips/silence.mp4 clips_audio_aac
ffmpeg -ss 00:01:23.835 -i 'spotlight_audio_aac.mp4' -to 00:01:24.919 -c:v copy -c:a copy -copyts clips_audio_aac/'spotlight_0001.mp4'
ffmpeg -ss 00:01:25.003 -i 'spotlight_audio_aac.mp4' -to 00:01:27.172 -c:v copy -c:a copy -copyts clips_audio_aac/'spotlight_0002.mp4'
ffmpeg -ss 00:01:27.964 -i 'spotlight_audio_aac.mp4' -to 00:01:28.965 -c:v copy -c:a copy -copyts clips_audio_aac/'spotlight_0003.mp4'
ffmpeg -ss 00:01:29.424 -i 'spotlight_audio_aac.mp4' -to 00:01:31.259 -c:v copy -c:a copy -copyts clips_audio_aac/'spotlight_0004.mp4'
ffmpeg -ss 00:01:31.342 -i 'spotlight_audio_aac.mp4' -to 00:01:33.344 -c:v copy -c:a copy -copyts clips_audio_aac/'spotlight_0005.mp4'
ffmpeg -ss 00:01:33.970 -i 'spotlight_audio_aac.mp4' -to 00:01:35.096 -c:v copy -c:a copy -copyts clips_audio_aac/'spotlight_0006.mp4'
``` 

opt01: libx264

``` bash
cp clips_opt01/video_files04.in clips_audio_aac/
MERGE_FILE="clips_audio_aac/video_files04.in"
clip_name="spotlight04x264"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a copy \
	clips_audio_aac/${clip_name}_silence.mp4
``` 

opt02: libx264 ve aac

``` bash
cp clips_opt01/video_files04.in clips_audio_aac/
MERGE_FILE="clips_audio_aac/video_files04.in"
clip_name="spotlight04x264aac"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a aac \
	clips_audio_aac/${clip_name}_silence.mp4
``` 

opt03: libx264 ve aac fakat no silence

``` bash
cp clips_opt01/video_files04.in clips_audio_aac/
MERGE_FILE="clips_audio_aac/video_files05.in"
clip_name="spotlight05x264aac"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a aac \
	clips_audio_aac/${clip_name}_nosilence.mp4
``` 

##### try02: Compress and cut in first step

``` bash
mkdir clips_compress_cut
out_dir=clips_compress_cut
cp clips/silence.mp4 clips_compress_cut
ffmpeg -ss 00:01:23.835 -i "${input}" -map 0:0 -map 0:${stream} -to 00:01:24.919 -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts clips_compress_cut/spotlight_0001.mp4
ffmpeg -ss 00:01:25.003 -i "${input}" -map 0:0 -map 0:${stream} -to 00:01:27.172 -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts clips_compress_cut/spotlight_0002.mp4
ffmpeg -ss 00:01:27.964 -i "${input}" -map 0:0 -map 0:${stream} -to 00:01:28.965 -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts clips_compress_cut/spotlight_0003.mp4
ffmpeg -ss 00:01:29.424 -i "${input}" -map 0:0 -map 0:${stream} -to 00:01:31.259 -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts clips_compress_cut/spotlight_0004.mp4
ffmpeg -ss 00:01:31.342 -i "${input}" -map 0:0 -map 0:${stream} -to 00:01:33.344 -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts clips_compress_cut/spotlight_0005.mp4
ffmpeg -ss 00:01:33.970 -i "${input}" -map 0:0 -map 0:${stream} -to 00:01:35.096 -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts clips_compress_cut/spotlight_0006.mp4
``` 

opt01: libx264

``` bash
cp clips_opt01/video_files04.in $out_dir/
MERGE_FILE="$out_dir/video_files04.in"
clip_name="spotlight04x264"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a copy \
	$out_dir/${clip_name}_silence.mp4
``` 

opt02: libx264 ve aac

``` bash
cp clips_opt01/video_files04.in $out_dir/
MERGE_FILE="$out_dir/video_files04.in"
clip_name="spotlight04x264aac"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a aac \
	$out_dir/${clip_name}_silence.mp4
``` 

opt03: libx264 ve aac fakat no silence

``` bash
cp clips_audio_aac/video_files05.in $out_dir/
MERGE_FILE="$out_dir/video_files05.in"
clip_name="spotlight05x264aac"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a aac \
	$out_dir/${clip_name}_nosilence.mp4
``` 

##### try03: Compress and cut in first step with -ss after -i

``` bash
out_dir=clips_ss_after_i
mkdir $out_dir
cp clips/silence.mp4 $out_dir
ffmpeg -i "${input}" -map 0:0 -map 0:${stream} -ss 00:01:23.835 -to 00:01:24.919 -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts $out_dir/spotlight_0001.mp4
ffmpeg -i "${input}" -map 0:0 -map 0:${stream} -ss 00:01:25.003 -to 00:01:27.172 -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts $out_dir/spotlight_0002.mp4
ffmpeg -i "${input}" -map 0:0 -map 0:${stream} -ss 00:01:27.964 -to 00:01:28.965 -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts $out_dir/spotlight_0003.mp4
ffmpeg -i "${input}" -map 0:0 -map 0:${stream} -ss 00:01:29.424 -to 00:01:31.259 -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts $out_dir/spotlight_0004.mp4
ffmpeg -i "${input}" -map 0:0 -map 0:${stream} -ss 00:01:31.342 -to 00:01:33.344 -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts $out_dir/spotlight_0005.mp4
ffmpeg -i "${input}" -map 0:0 -map 0:${stream} -ss 00:01:33.970 -to 00:01:35.096 -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts $out_dir/spotlight_0006.mp4
``` 

opt01: libx264

``` bash
cp clips_opt01/video_files04.in $out_dir/
MERGE_FILE="$out_dir/video_files04.in"
clip_name="spotlight04x264"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a copy \
	$out_dir/${clip_name}_silence.mp4
``` 

opt02: libx264 ve aac

``` bash
MERGE_FILE="$out_dir/video_files04.in"
clip_name="spotlight04x264aac"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a aac \
	$out_dir/${clip_name}_silence.mp4
``` 

opt03: libx264 ve aac fakat no silence

``` bash
cp clips_audio_aac/video_files05.in $out_dir/
MERGE_FILE="$out_dir/video_files05.in"
clip_name="spotlight05x264aac"
ffmpeg -f concat -i ${MERGE_FILE} \
	-c:v libx264 \
	-c:a aac \
	$out_dir/${clip_name}_nosilence.mp4
``` 

Tamam, şimdi zamanlamalar düzgün.

##### try04: -ss ve -to önde olsun

``` bash
out_dir=clips_ss_to_before_i
mkdir $out_dir
cp clips/silence.mp4 $out_dir
ffmpeg -ss 00:01:23.835 -to 00:01:24.919 -i "${input}" -map 0:0 -map 0:${stream} -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts $out_dir/spotlight_0001.mp4
``` 

##### try05: -ss -to copyts önde olsun

``` bash
out_dir=clips_ss_to_copyts_before_i
mkdir $out_dir
cp clips/silence.mp4 $out_dir
ffmpeg -ss 00:01:23.835 -to 00:01:24.919 -copyts -i "${input}" -map 0:0 -map 0:${stream} -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac $out_dir/spotlight_0001.mp4
``` 

##### try05: -ss -to önde start_at_zero sonra

``` bash
out_dir=clips_start_at_zero
mkdir $out_dir
cp clips/silence.mp4 $out_dir
ffmpeg -ss 00:01:23.835 -to 00:01:24.919 -i "${input}" -map 0:0 -map 0:${stream} -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts -start_at_zero $out_dir/spotlight_0001.mp4
``` 

##### study timestamp metadata

``` bash
cd clips_start_at_zero 
ffprobe -i spotlight_0001.mp4
  ##> 	Duration: 02:09:08.16, start: 83.813000, bitrate: 0 kb/s
  ##> 		Chapter #0:0: start 0.000000, end 500.750000
  ##> 		Metadata:
  ##> 			title           : Chapter 1
  ##> 		Chapter #0:1: start 500.750000, end 870.536000
``` 


``` bash
cd clips_ss_after_i 
ffprobe -i spotlight_0001.mp4
  ##>   Duration: 00:00:01.11, start: 0.000000, bitrate: 504 kb/s
  ##>     Chapter #0:0: start 0.000000, end 1.084000
``` 

##### bir tane küçük bir mkv dosyası oluştur, doğrudan onunla test yap

``` bash
ffmpeg -ss 00:00:00.000 -to 00:02:00.000 -i "${input}" -map 0:0 -map 0:${stream} -c:v copy -c:a copy -copyts -start_at_zero spotlight_short.mp4
ffmpeg -ss 00:00:00.000 -to 00:02:00.000 -i "${input}" -map 0:0 -map 0:${stream} -c:v copy -c:a copy spotlight_short2.mp4
``` 

Bu şekilde yapınca, videonun boyutu hala eski boyutu olarak görünüyor.

``` bash
ffprobe -i spotlight_short.mp4
  ##>   Duration: 02:09:08.16, start: 0.000000, bitrate: 164 kb/s
``` 

https://stackoverflow.com/questions/35136468/trim-mp4-files-without-encoding-it-again?noredirect=1&lq=1

Mutlaka transcoding yapman gerekiyor diyor. Fakat crf 18 ve altı olursa, kalite kaybı çok az olurmuş.

``` bash
file 'in.mp4'
inpoint 48.101
outpoint 67.459
file 'in.mp4'
inpoint 76.178
outpoint 86.399
``` 

then run,

``` bash
ffmpeg -f concat -i list.txt -an -crf 18 out_merged.mp4
``` 

https://ffmpeg.org/ffmpeg-formats.html#concat

opt01: crf ve copy birlikte kullanım

``` bash
ffmpeg -ss 00:00:00.000 -to 00:02:00.000 -i "${input}" -map 0:0 -map 0:${stream} -c:v libx264 -crf 18 -c:a copy spotlight_short3.mp4
ffmpeg -ss 00:00:00.000 -to 00:02:00.000 -i "${input}" -map 0:0 -map 0:${stream} -c:v libx264 -crf 23 -c:a copy spotlight_short4.mp4
``` 

opt02: tek adımda yap her şeyi

``` bash
ffmpeg -f concat -i files01.in -map 0:0 -map 0:${stream} -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts -start_at_zero spotlight_single01.mp4
``` 

Ses kaydı video ile uyuşmuyor.

###### Error: Ses kaydı video ile uyuşmuyor.

``` bash
ffmpeg -f concat -i files01.in -map 0:0 -map 0:${stream} -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -copyts -start_at_zero spotlight_single01.mp4
``` 

		[aac @ 0x7fb8de810600] Queue input is backward in time
		[mp4 @ 0x7fb8de82a200] Non-monotonous DTS in output stream 0:1; previous: 338216, current: 303240; changing to 338217. This may result in incorrect timestamps in the output file.

opt01: copyts olmasın

``` bash
ffmpeg -f concat -i files01.in -map 0:0 -map 0:${stream} -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac spotlight_single02.mp4
``` 

opt02: -absf aac_adtstoasc

``` bash
ffmpeg -f concat -i files01.in -map 0:0 -map 0:${stream} -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -absf aac_adtstoasc spotlight_single03.mp4
``` 

opt03: copy audio

``` bash
ffmpeg -f concat -i files01.in -map 0:0 -map 0:${stream} -c:v libx264 -crf 28 -vf "scale=320:240" -c:a copy spotlight_single04.mp4
``` 

opt04: map all

``` bash
ffmpeg -f concat -i files01.in -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac spotlight_single05.mp4
``` 

opt05: seconds format in files03.in

``` bash
ffmpeg -f concat -i files03.in -map 0:0 -map 0:${stream} -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -fflags +genpts -avoid_negative_ts make_zero spotlight_single06.mp4
``` 

opt06: mpdecimate

https://video.stackexchange.com/questions/20958/ffmpeg-dropping-duplicate-frames

``` bash
ffmpeg -f concat -i files03.in -map 0:0 -map 0:${stream} -c:v libx264 -crf 28 -vf "scale=320:240" -c:a aac -fflags +genpts -avoid_negative_ts make_zero spotlight_single06.mp4
``` 

----

opt03: -ss ve to önde copy yap

``` bash
ffmpeg -ss 00:01:23.835 -to 00:01:28.965 -i "${input}" -map 0:0 -map 0:${stream} -c:v copy -c:a copy spotlight_short5.mp4
``` 

Metadata hatalı

``` bash
ffmpeg -ss 00:01:23.835 -to 00:01:28.965 -i "${input}" -map 0:0 -map 0:${stream} -c:v copy -c:a copy -copyts spotlight_short6.mp4
``` 

Şimdi silence ekle ve reencode et

``` bash
ffmpeg -f concat -i files02.in -c:v libx264 -c:a aac spotlight_short6b.mp4
``` 

opt03b: use_wallclock_as_timestamps

``` bash
ffmpeg -ss 00:01:23.835 -to 00:01:28.965 -use_wallclock_as_timestamps 1 -i "${input}" -map 0:0 -map 0:${stream} -c:v copy -c:a copy -copyts spotlight_short7.mp4
``` 

opt03c: use_wallclock_as_timestamps with reencoding

``` bash
ffmpeg -ss 00:01:23.835 -to 00:01:28.965 -use_wallclock_as_timestamps 1 -i "${input}" -map 0:0 -map 0:${stream} -c:v libx264 -c:a aac spotlight_short8.mp4
``` 

opt03d: -to sonra

``` bash
ffmpeg -ss 00:01:23.835 -i "${input}" -map 0:0 -map 0:${stream} -to 00:01:28.965 -c:v libx264 -c:a aac spotlight_short9.mp4
``` 


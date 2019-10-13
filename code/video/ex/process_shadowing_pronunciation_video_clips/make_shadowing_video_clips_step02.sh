#!/bin/bash
clip_name=$1
VOLUME_INCREASE=${2:-1}
output_mp4=$3
OUT_DIR=$4
echo $clip_name $VOLUME_INCREASE $output_mp4 $OUT_DIR
offset_clip_id=0

mkdir -p $OUT_DIR &&
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )" &&
~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/srt2tsv.sh "${clip_name}.srt" "${OUT_DIR}/marks.tsv" &&
if [ ! -f "${clip_name}.tr.srt" ]; then
  echo "
1
00:00:00,000 --> 00:00:01,000
test satırı

" > "${clip_name}.tr.srt" 
fi
~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/srt2tsv.sh "${clip_name}.tr.srt" "${OUT_DIR}/marks.tr.tsv" &&
R --vanilla -e "one.video.to.multiple.clips::main_generate_ffmpeg_cmd_for_splitting_videos(path = '$OUT_DIR/marks.tsv', offset_clip_id = ${offset_clip_id}, original_video = '${output_mp4}', clip_name = '${clip_name}')" &&
#rg "\bNA\b" $OUT_DIR/marks.tsv &&
#rg "\bNA\b" $OUT_DIR/$OUT_DIR.tsv &&
bash ./$OUT_DIR/split01.sh &&
bash ./$OUT_DIR/split02.sh &&
silence01=$OUT_DIR/silence01.mp4 && 
VOLUME_INCREASE=0.01 && 
SILENCE_DURATION=02 &&
ffmpeg -ss 00:00 -to 00:$SILENCE_DURATION -i ${output_mp4} -c:v libx264 -crf 23 -c:a aac -filter:a "volume=${VOLUME_INCREASE}" $silence01 && 
out_silence=$OUT_DIR/silence.mp4 && 
ffmpeg -i ${silence01} -t 00:$SILENCE_DURATION -c:v copy -c:a copy $out_silence && 
ffmpeg -f concat -i $OUT_DIR/video_files_merge.in -c copy $OUT_DIR/${clip_name}_silence.mp4
echo "done"


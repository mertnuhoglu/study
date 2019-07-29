#!/bin/bash
clip_name=$1
input="${clip_name}.mkv"
output_mp4="${clip_name}.mp4"
VOLUME_INCREASE=${2:-1}
stream=${3:-2}
offset_clip_id=0
if [ ! -f ${output_mp4}  ]; then
	ffmpeg -i "${input}" \
		-map 0:0 -map 0:${stream} \
		-c:v libx264 -crf 23 -vf "scale=320:240" \
		-c:a aac -q:a 32 -filter:a "volume=${VOLUME_INCREASE}" \
		"${output_mp4}" &&
fi
mkdir -p clips &&
cp "${clip_name}.srt" clips/marks.txt &&
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )" &&
nvim -s "${DIR}/ConvertMarksTxt2MarksTsv.vim" clips/marks.txt &&
cp "${clip_name}.tr.srt" clips/marks.tr.txt &&
nvim -s "${DIR}/ConvertMarksTxt2MarksTsvTr.vim" clips/marks.tr.txt &&
R --vanilla -e "one.video.to.multiple.clips::main_generate_ffmpeg_cmd_for_splitting_videos(path = 'clips/marks.tsv', offset_clip_id = ${offset_clip_id}, original_video = '${output_mp4}', clip_name = '${clip_name}')" &&
rg "\bNA\b" clips/marks.tsv &&
rg "\bNA\b" clips/clips.tsv &&
chmod +x clips/split01.sh &&
chmod +x clips/split02.sh &&
./clips/split01.sh &&
./clips/split02.sh &&
silence01=clips/silence01.mp4 && 
VOLUME_INCREASE=0.01 && 
SILENCE_DURATION=02 &&
ffmpeg -ss 00:00 -to 00:$SILENCE_DURATION -i ${output_mp4} -c:v libx264 -crf 23 -c:a aac -filter:a "volume=${VOLUME_INCREASE}" $silence01 && 
out_silence=clips/silence.mp4 && 
ffmpeg -i ${silence01} -t 00:$SILENCE_DURATION -c:v copy -c:a copy $out_silence && 
ffmpeg -f concat -i clips/video_files_merge.in -c copy clips/${clip_name}_silence.mp4
echo "done"

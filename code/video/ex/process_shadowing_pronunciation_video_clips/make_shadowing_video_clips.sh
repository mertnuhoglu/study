#!/bin/bash
clip_name=$1
VOLUME_INCREASE=${2:-1}
stream=${3:-2}
input="${clip_name}.mkv"
output_mp4="${clip_name}.mp4"
offset_clip_id=0
if [ ! -f ${output_mp4}  ]; then
	ffmpeg -i "${input}" \
		-map 0:0 -map 0:${stream} \
		-c:v libx264 -crf 23 -vf "scale=320:240" \
		-c:a aac -q:a 32 -filter:a "volume=${VOLUME_INCREASE}" \
		"${output_mp4}" 
fi


bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips_step02.sh $clip_name $VOLUME_INCREASE output_mp4 clips_nosub

ffmpeg -i "${clip_name}.srt" "${clip_name}0.ass"
sed -e 's/^Style: Default.*/Style: Default,Arial,28,\&H0000FFFF,\&H0077FF00,\&H00000000,\&H00000000,0,0,0,0,100,100,0,0,1,2,2,2,10,10,10,1/' ${clip_name}0.ass | tr -d $'\r' > ${clip_name}.ass
output_sub=${clip_name}_sub.mp4
ffmpeg -i ${output_mp4} -c:v libx264 -crf 12 -vf "ass=${clip_name}.ass" $output_sub
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips_step02.sh $clip_name $VOLUME_INCREASE $output_sub clips_sub

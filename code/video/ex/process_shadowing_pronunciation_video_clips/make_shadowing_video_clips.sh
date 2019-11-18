#!/bin/bash
clip_name=""
VOLUME_INCREASE=1
stream=2
usage() {
  echo "Usage: $0 [ -c clip_name ] [ -v VOLUME_INCREASE ] [ -i input ] [ -o output_mp4 ]" 1>&2 
}
exit_abnormal() {
  usage
  exit 1
}
while getopts "c:v:NS" options; do

  case "${options}" in
    c)
      clip_name=${OPTARG}
      ;;
    v)
      VOLUME_INCREASE=${OPTARG}
      re_isanum='^[1-9]$'
      if ! [[ $VOLUME_INCREASE =~ $re_isanum ]] ; then
        echo "Error: VOLUME_INCREASE must be a positive, whole number. Input: $VOLUME_INCREASE"
        exit_abnormally
        exit 1
      elif [ $VOLUME_INCREASE -eq "0" ]; then
        echo "Error: VOLUME_INCREASE must be greater than zero."
        exit_abnormal
      fi
      ;;
		N)
			NOSUB_VIDEO=1
			;;
		S)
			SUB_VIDEO=1
			;;
    :)
      echo "Error: -${OPTARG} requires an argument."
      exit_abnormal
      ;;
    *)
      exit_abnormal
      ;;
  esac
done
echo called make_shadowing_video_clips.sh with:
echo -c $clip_name -v $VOLUME_INCREASE NOSUB_VIDEO $NOSUB_VIDEO SUB_VIDEO $SUB_VIDEO
input="${clip_name}.mkv"
output_mp4="${clip_name}_320.mkv"
offset_clip_id=0
echo input: $input
echo output_mp4: $output_mp4
echo offset_clip_id: $offset_clip_id

if [ ! -f "${output_mp4}"  ]; then
	stream_line=$(ffprobe -i "${input}" 2>&1 | rg eng | rg Stream | rg Audio)
	pat='Stream #0:([0-9])'
	[[ $stream_line =~ $pat ]]
	stream_eng="${BASH_REMATCH[1]}"
	echo $stream_line
	echo matched: $stream_eng
	stream_line=$(ffprobe -i "${input}" 2>&1 | rg tur | rg Stream | rg Audio)
	pat='Stream #0:([0-9])'
	[[ $stream_line =~ $pat ]]
	stream_tur="${BASH_REMATCH[1]}"
	echo $stream_line
	echo matched: $stream_tur
	ffmpeg -i "${input}" \
		-map 0:0 -map 0:${stream_eng} -map 0:${stream_tur} \
		-c:v libx264 -crf 23 -vf "scale=320:240" \
		-c:a aac -q:a 32 -filter:a "volume=${VOLUME_INCREASE}" \
		"${output_mp4}" 
fi

echo bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips_step02.sh -c "${clip_name}" -v $VOLUME_INCREASE -o "${output_mp4}" clips_nosub &&
if [ $NOSUB_VIDEO = 1 ]; then
	bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips_step02.sh "${clip_name}" $VOLUME_INCREASE "${output_mp4}" clips_nosub 
fi

if [ $SUB_VIDEO = 1 ]; then
	ffmpeg -i "${clip_name}.srt" "${clip_name}0.ass" &&
	sed -e 's/^Style: Default.*/Style: Default,Arial,28,\&H0000FFFF,\&H0077FF00,\&H00000000,\&H00000000,0,0,0,0,100,100,0,0,1,2,2,2,10,10,10,1/' "${clip_name}0.ass" | tr -d $'\r' > "${clip_name}.ass" &&
	output_sub="${clip_name}_sub.mkv" &&
	ffmpeg -i "${output_mp4}" \
		-map 0:0 -map 0:1 -map 0:2 \
		-c:v libx264 -crf 12 -vf "ass=${clip_name}.ass" "${output_sub}" &&
	echo "skipping clips"
	#bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips_step02.sh "${clip_name}" $VOLUME_INCREASE "${output_sub}" clips_sub 
else
	echo "no subbed video is requested" 
fi
echo "script is completed. check clips_nosub/ and clips_sub/ folders"


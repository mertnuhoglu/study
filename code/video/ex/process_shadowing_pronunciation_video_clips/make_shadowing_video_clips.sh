#!/bin/bash
clip_name=""
VOLUME_INCREASE=1
stream=2
usage() {
  echo "Usage: $0 [ -c clip_name ] [ -v VOLUME_INCREASE ] [ -s stream ] [ -i input ] [ -o output_mp4 ]" 1>&2 
}
exit_abnormal() {
  usage
  exit 1
}
while getopts "c:v:s:S" options; do

  case "${options}" in
    c)
      clip_name=${OPTARG}
      ;;
    v)
      VOLUME_INCREASE=${OPTARG}
      re_isanum='^[1-9]$'
      if ! [[ $VOLUME_INCREASE =~ $re_isanum ]] ; then
        echo "Error: VOLUME_INCREASE must be a positive, whole number."
        exit_abnormally
        exit 1
      elif [ $VOLUME_INCREASE -eq "0" ]; then
        echo "Error: VOLUME_INCREASE must be greater than zero."
        exit_abnormal
      fi
      ;;
    s)
      stream=${OPTARG}
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
echo -c $clip_name -v $VOLUME_INCREASE -s $stream
input="${clip_name}.mkv"
output_mp4="${clip_name}.mp4"
offset_clip_id=0
echo input: $input
echo output_mp4: $output_mp4
echo offset_clip_id: $offset_clip_id

if [ ! -f ${output_mp4}  ]; then
	ffmpeg -i "${input}" \
		-map 0:0 -map 0:${stream} \
		-c:v libx264 -crf 23 -vf "scale=320:240" \
		-c:a aac -q:a 32 -filter:a "volume=${VOLUME_INCREASE}" \
		"${output_mp4}" 
fi

echo bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips_step02.sh -c $clip_name -v $VOLUME_INCREASE -o $output_mp4 clips_nosub &&
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips_step02.sh $clip_name $VOLUME_INCREASE $output_mp4 clips_nosub &&

if [ $SUB_VIDEO = 1 ]; then
	ffmpeg -i "${clip_name}.srt" "${clip_name}0.ass" &&
	sed -e 's/^Style: Default.*/Style: Default,Arial,28,\&H0000FFFF,\&H0077FF00,\&H00000000,\&H00000000,0,0,0,0,100,100,0,0,1,2,2,2,10,10,10,1/' ${clip_name}0.ass | tr -d $'\r' > ${clip_name}.ass &&
	output_sub=${clip_name}_sub.mp4 &&
	ffmpeg -i ${output_mp4} -c:v libx264 -crf 12 -vf "ass=${clip_name}.ass" $output_sub &&
bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips_step02.sh $clip_name $VOLUME_INCREASE $output_mp4 clips_sub 
else
	echo "no subbed video is requested" 
fi
echo "script is completed. check clips_nosub/ and clips_sub/ folders"


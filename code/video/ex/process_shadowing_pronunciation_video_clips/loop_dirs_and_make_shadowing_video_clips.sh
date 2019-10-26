
usage() {
  echo "Usage: $0 [ -v VOLUME_INCREASE ] [ -S ] [ -N ]" 1>&2 
}
exit_abnormal() {
  usage
  exit 1
}
while getopts "v:NS" options; do

  case "${options}" in
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
echo called loop_dirs_and_make_shadowing_video_clips.sh with:
echo -v $VOLUME_INCREASE stream NOSUB_VIDEO $NOSUB_VIDEO SUB_VIDEO $SUB_VIDEO
for d in */
do
	cd "$d"
	clip_name=${d%/*}
	echo "clip_name: $clip_name"
	if [ $NOSUB_VIDEO = 1 ]; then
		echo calling: bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips.sh -c $clip_name -v $VOLUME_INCREASE -N
		bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips.sh -c $clip_name -v $VOLUME_INCREASE -N
	fi
	if [ $SUB_VIDEO = 1 ]; then
		echo calling: bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips.sh -c $clip_name -v $VOLUME_INCREASE -S
		bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips.sh -c $clip_name -v $VOLUME_INCREASE -S
	fi
	cd ..
done


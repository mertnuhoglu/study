
VOLUME_INCREASE=2
stream=2
usage() {
  echo "Usage: $0 [ -v VOLUME_INCREASE ] [ -s stream ] [ -S ] [ -N ]" 1>&2 
}
exit_abnormal() {
  usage
  exit 1
}
while getopts "v:s:NS" options; do

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
    s)
      stream=${OPTARG}
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
echo -c $clip_name -v $VOLUME_INCREASE -s $stream
VOLUME_INCREASE=${1:-2}
stream=${2:-2}
for d in */
do
	cd "$d"
	clip_name=${d%/*}
	if [ $NOSUB_VIDEO = 1 ]; then
		bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips.sh -c $clip_name -v $VOLUME_INCREASE -s $stream -N
	fi
	if [ $SUB_VIDEO = 1 ]; then
		bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips.sh -c $clip_name -v $VOLUME_INCREASE -s $stream -S
	fi
	cd ..
done


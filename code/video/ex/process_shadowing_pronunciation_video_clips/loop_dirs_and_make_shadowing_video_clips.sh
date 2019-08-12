
VOLUME_INCREASE=${2:-2}
stream=${3:-1}
for d in */
do
	cd "$d"
	clip_name=${d%/*}
	bash ~/projects/study/code/video/ex/process_shadowing_pronunciation_video_clips/make_shadowing_video_clips.sh "${clip_name}" $VOLUME_INCREASE $stream
	cd ..
done


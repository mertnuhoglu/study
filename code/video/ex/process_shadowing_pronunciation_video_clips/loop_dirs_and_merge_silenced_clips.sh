
SILENCE_DURATION=${1:-03}
for d in */
do
	cd "$d"
	clip_name=${d%/*}
	cp /Volumes/Elements/arsivden/cizgi_film_dizi/sherlock_yack/silence/silence${SILENCE_DURATION}.mp4 clips/silence.mp4
	ffmpeg -f concat -i clips/video_files_merge.in -c copy clips/${clip_name}_silence${SILENCE_DURATION}.mp4
	cd ..
done


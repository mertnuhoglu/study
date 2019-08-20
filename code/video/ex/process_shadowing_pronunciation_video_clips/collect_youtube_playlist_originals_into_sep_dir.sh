mkdir -p collected_original
for d in */
do
	clip_name=${d%/*}
	mv ${clip_name}/${clip_name}.mp4 ./collected_original
	mv ${clip_name}/${clip_name}.srt ./collected_original
done



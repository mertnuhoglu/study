mkdir -p collected_original
for d in */
do
	clip_name=${d%/*}
	mv ${clip_name}/${clip_name}.en.srt ${clip_name}/${clip_name}.srt
done




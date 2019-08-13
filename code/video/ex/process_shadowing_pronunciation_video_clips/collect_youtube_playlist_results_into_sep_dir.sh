mkdir -p collected
for d in */
do
	clip_name=${d%/*}
	mv ${clip_name}/clips/${clip_name}_silence.mp4 ./collected
done


OUT_DIR=$1
mkdir -p collected
for d in */
do
	clip_name=${d%/*}
	mv "${clip_name}/${OUT_DIR}/${clip_name}_silence.mp4" ./collected
done


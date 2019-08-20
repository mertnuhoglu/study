
VOLUME_INCREASE=${2:-1}
for d in */
do
	cd "$d"
	clip_name=${d%/*}
	input="${clip_name}.mkv"
	output_mp4="${clip_name}.mp4"
		#-c:v libx264 -crf 23 -vf "scale=1080:-1" \
	ffmpeg -i "${input}" \
		-c:v libx264 -crf 23 \
		-c:a aac -q:a 32 -filter:a "volume=${VOLUME_INCREASE}" \
		"${output_mp4}" 
	cd ..
done




SILENCE_DURATION=${1:-03}
for d in */
do

cd "$d"
clip_name=${d%/*}
echo "file 'silence.mp4'
file 'silence.mp4'
file 'silence.mp4'" > clips/silence03.in
ffmpeg -f concat -i clips/silence03.in -c copy clips/silence03.mp4
mv clips/silence.mp4 clips/silence0.mp4
cp clips/silence${SILENCE_DURATION}.mp4 clips/silence.mp4
ffmpeg -f concat -i clips/video_files_merge.in -c copy clips/${clip_name}_silence${SILENCE_DURATION}.mp4
cd ..

done


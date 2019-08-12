#!/bin/bash
# rename youtube playlist files as such:
# 
# 05-Sherlock Yack - Episode 5 - Who Painted the Ostrich-5ovMwo3gUkE.mp4
# ->
# sherlock_yack_05_who_painted_the_ostrich.mp4

# 01-A Story About IQ Tests-17T3fZIpT8I.mp4
# A Story About IQ Tests-17T3fZIpT8I.mp4
# 
rename 's/ - Episode ([0-9]+) - /sprintf "_%02d_", $1/ge' *.mp4
rename 's/\d+-//' *.mp4
rename 's/-\w+\././' *.mp4
rename 's/ /_/g' *.mp4
for f in *.mp4; do mv "$f" "$f.tmp"; mv "$f.tmp" "`echo $f | tr "[:upper:]" "[:lower:]"`"; done

rename 's/ - Episode ([0-9]+) - /sprintf "_%02d_", $1/ge' *.srt
rename 's/^\d+-//' *.srt
rename 's/-\w+\././' *.srt
rename 's/ /_/g' *.srt
rename 's/\.en\.srt/.srt/' *.srt
for f in *.srt; do mv "$f" "$f.tmp"; mv "$f.tmp" "`echo $f | tr "[:upper:]" "[:lower:]"`"; done

#!/bin/bash
cd friends_01_01
input_srt=friends_01_01.srt
out=marks.txt
# Convert `^M` into new line
#sed 's//\r/g' $input_srt > $out
# delete number lines such as 24
sed '/^\d\+$/d' $input_srt > $out
sed '/^\d$/d' $input_srt > $out
sed '/^\d.*$/d' $input_srt > $out
# delete html tags
%s/<\/\?\w\+>//g
# wrap text lines inside double quotes
# note that a text line may start with -.'":;, symbols
%s/"/'/g
v/^\d\d:\d\d:\d\d/ s/\(^\([-# .'":;,]\|\k\).*\n\)\+/"\0"/
# put ### at the start of each scene
g/^"$/ s/\n/###/g
# replace all newlines 
%s/\n/@@@/g
# put each scene into a new line
%s/###/\r/g
# delete the last @@@
%s/@@@"$/"/
# delete all intermediate time digits
%s#--> \([0-9:,]\+@@@[0-9:,]\+ --> \)\+#--> #g
%s/@@@"/\t"/
%s/ --> /\t/
g/^@@@$/d
%s/@@@/ <br> /g
%s/([A-Z]\+)//g
%s#\(\d\d\),\(\d\d\d\)#\1.\2#g

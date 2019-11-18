
# Presentation Timestamp

http://www.wikizero.biz/index.php?q=aHR0cHM6Ly9lbi53aWtpcGVkaWEub3JnL3dpa2kvUHJlc2VudGF0aW9uX3RpbWVzdGFtcA

PTS is a timestamp metadata field to do synchronization of streams (video, audio)

## What are PTS and DTS

https://stackoverflow.com/questions/6044330/ffmpeg-c-what-are-pts-and-dts-what-does-this-code-block-do-in-ffmpeg-c

DTS (decoding time stamp)

The decoding timestamp tells us when we need to decode something, and the presentation time stamp tells us when we need to display something. So, in this case, our stream might look like this:

			 PTS: 1 4 2 3
			 DTS: 1 2 3 4
		Stream: I P B B

## Video compression picture type

http://www.wikizero.biz/index.php?q=aHR0cHM6Ly9lbi53aWtpcGVkaWEub3JnL3dpa2kvVmlkZW9fY29tcHJlc3Npb25fcGljdHVyZV90eXBlcw

Different algorithms for video frames: called as picture types or frame types.

I‑frames are the least compressible but don't require other video frames to decode.
P‑frames can use data from previous frames to decompress and are more compressible than I‑frames.
B‑frames can use both previous and forward frames for data reference to get the highest amount of data compression.

# Queue input is backward in time

https://ubuntuforums.org/showthread.php?t=2170398

``` bash
ffmpeg -y 
``` 

# Map

https://trac.ffmpeg.org/wiki/Map

## Default:

``` bash
ffmpeg -i INPUT OUTPUT
``` 

Send best video and audio to output. Discard all other input streams.

If you want to include more than one streams, you need to specify `-map` explicitly.

## Input file

### Example:

``` bash
  # fmpeg -i input.mkv

ffmpeg version ... Copyright (c) 2000-2012 the FFmpeg developers
...
Input #0, matroska,webm, from 'input.mkv':
  Duration: 01:39:44.02, start: 0.000000, bitrate: 5793 kb/s
    Stream #0:0(eng): Video: h264 (High), yuv420p, 1920x800, 23.98 fps, 23.98 tbr, 1k tbn, 47.95 tbc (default)
    Stream #0:1(ger): Audio: dts (DTS), 48000 Hz, 5.1(side), s16, 1536 kb/s (default)
    Stream #0:2(eng): Audio: dts (DTS), 48000 Hz, 5.1(side), s16, 1536 kb/s
    Stream #0:3(ger): Subtitle: text (default)
At least one output file must be specified

``` 

### Ex01:

``` bash
ffmpeg -i input.mkv \
    -map 0:0 -map 0:1 -map 0:1 -map 0:3 \
    -c:v copy \
    -c:a:0 libmp3lame -b:a:0 128k \
    -c:a:1 libfaac -b:a:1 96k \
    -c:s copy \
    output.mkv
``` 

- Drops stream 0:2
- Copies stream 0:1 twice with different audio settings

Output:

``` bash
Output #0, matroska, to 'output.mkv':
    Stream #0:0(eng): Video ...
    Stream #0:1(ger): Audio ...
    Stream #0:2(ger): Audio ...
    Stream #0:3(ger): Subtitle ...
``` 



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



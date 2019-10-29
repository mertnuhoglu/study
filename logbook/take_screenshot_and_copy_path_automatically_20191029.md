# screenshotların otomatik olarak yollarını kopyalamak 20191029 

## keyboard - Take screenshot and copy its file path to clipboard - Ask Different

https://apple.stackexchange.com/questions/115999/take-screenshot-and-copy-its-file-path-to-clipboard

``` bash
f=~/Desktop/$(date +%Y%m%d%H%M%S).png
screencapture -i $f
printf %s $f | pbcopy
``` 

> If you just want to copy the path as text, replace the last line with `printf %s $f | pbcopy`

Edit `~/projects/stuff/bash/screenshot_copy_path.sh`

opt01: use applescript to copy into clipboard

``` bash
osascript -e 'set the clipboard to POSIX file "'$f'"'
``` 

Use applescript if you want to copy the picture into clipboard.

## assign a shortcut to a bash script

https://superuser.com/questions/153890/assign-a-shortcut-to-running-a-script-in-os-x

opt01: KeyRemap

opt02: AppleScript

opt03: hammerspoon

https://groups.google.com/forum/?nomobile=true#!msg/hammerspoon/4ILCM79xH2Y/ehUaHo5NCgAJ

``` bash
result = hs.applescript('do shell script "my shell script here"')
``` 

hs.execute and hs.task

opt04: karabiner

Example: Launch apps by right shift+letters

``` bash
                    {
                        "description": "Launch apps by right shift+letters.",
                        "manipulators": [
                            {
                                "from": {
                                    "key_code": "a",
                                    "modifiers": {
                                        "mandatory": [
                                            "right_shift"
                                        ],
                                        "optional": [
                                            "caps_lock"
                                        ]
                                    }
                                },
                                "to": [
                                    {
                                        "shell_command": "open '/Applications/Utilities/Activity Monitor.app'"
                                    }
                                ],
                                "type": "basic"
                            },
``` 








tags:: study, tag, tll/mac

- # c/osx 
  id=g15117
	- rfr: pprv: ~/projects/study/otl/cosx.otl
- pnt - osx
	- clipboard history  id=g15110
		- Maccy
			- #!9
	- excel türü veri analiz araçları id=g14243
		- Ultorg
		- Explorer - R
	- çizim grafik tasarım foto diyagram araçları id=g13860
		- Shottr: Screenshot
		- FlameShot
		- Ksnip
		- SnagIt
		- Skitch
		- Sketch
		- Fragment
		- Figma
		- Monosnap
		- Snappy
- tpc - osx
	- Shottr: Screenshot
	- ref: shortcuts kısayollar keybindings - osx <url:file:///~/projects/study/otl/cosx.otl#r=g12084>
	- UTM: vm virtual machine
		tags:: tll/mac
		alternative:: virtualbox
		- [[20230510-Mac-M1-Windows-XP-Kurulumu]]
	- AltTab - window navigation id=g13821
		- rfr: [AltTab - Windows alt-tab on macOS](https://alt-tab-macos.netlify.app/)
		- pencereler ve uygulamalar arasında dolaşma
		- Command+Tab gibi ama önizleme görüntüsü var
		- Aynı uygulamanın pencereleri arasında dolaşmak
			- [macos - Shortcut for toggling between different windows of same app? - Ask Different](https://apple.stackexchange.com/questions/193937/shortcut-for-toggling-between-different-windows-of-same-app)
			- Disable: Keyboard > Shortcuts > Keyboard > Move focus to next window
	- sesli kayıt alma - audio recording
		- Simple Recorder: küresel kısayollarla ses kaydı başlatıp tamamlama
		- Vox: Playlist ile bunları dinleme
		- VLC: m4a dosyalarının taglerini düzenleme
		- Syncthing: mac ve telefon arasında senkronizasyon
	- Simple Recorder id=g12829
		- #^+u | record
		- #^+i | pause
		- #^+o | stop
		- myrecords folder
	- Vim Motion: macos vim keybindings id=g12830
		- #+SPC | activate vim
	- music/mp3/audio players
		- Vox
		- foobar2000
		- Strawberry
	- transfer files from osx to ipad
	- image viewer
		- finder > select all > space
	- osx setup guide for development tools
		- http://sourabhbajaj.com/mac-setup/index.html
	- karabiner
		- dahili tanım dosyaları burada
			- <url:file:///Applications/Karabiner.app/Contents/Resources>
		- kendi özelleştirmelerim burada
			- <url:file:///~/Library/Application Support/Karabiner/private.xml>
		- ModifierFlag::NONE
			- http://blog.iansinnott.com/using-keyremap4macbooks-private-xml/
			- like non-recursive mapping in vim
		- mapping alt+shift to return
			- http://apple.stackexchange.com/questions/236774/how-to-map-altshiftspace-to-return-correctly-in-karabiner
		- KeyOverlaidModifier
			- --KeyOverlaidModifier--
			- KeyCode::<key we want to affect>,
			- KeyCode::<key to fire when held continuously>,
			- KeyCode::<key to fire when pressed and released quickly>
- ## Tree
	- applications
		- AltTab - window navigation <url:file:///~/projects/study/otl/cosx.otl#r=g13821>
		- UTM: virtual machine windows on m1
		- krispy: zoom kullanırken arka fondaki gürültüyü temizler
		- iterm2
			- solarized renkleri iterm için bozuk
				- https://gitlab.com/gnachman/iterm2/issues/3989
				- https://github.com/altercation/solarized/issues/257
				- violet light ve violet dark kullan onlar yerine
					- https://github.com/mbadolato/iTerm2-Color-Schemes
		- spotify
			- limit cache size
				- https://community.spotify.com/t5/Desktop-Mac/How-to-limit-cache-size/td-p/2907725
				- cd "~/Library/Application Support/Spotify/"
				- nvim prefs
		- ukelele: keyboard layout manager
		- mucommander
		- fragment: image viewer
		- fork: git client
		- syncthing: android-desktop sync
		- be focused: pomodoro timer
		- anki: spaced repetition for memory
		- karabiner: shortcuts, hotkeys management
		- copyq: multiple clipboard manager
	- shortcuts kısayollar keybindings - osx id=g12084
		- sol alt köşe: #^+
      - | r | obs: streaming           |
      - | n | obs: recording           |
      - | h | obs: pause               |
      - | t | simple recorder: start   |
      - | y | simple recorder: stop    |
      - | ş | simple recorder: pause   |
      - | s | screenshot: rectangle    |
      - | m | screenshot: screen 2     |
      - | l | screenshot: screen 1     |
      - | k | hammerspoon: window list |
	- apps
		- contexts: spotlight alternative
			- switch any window (including multiple windows of one application)
	- bash completion
		- bash autocomplete is not setup by default
		- to enable it:
			- https://www.simplified.guide/macos/bash-completion
			- brew install bash-completion
	- spotlight
		- dictionary lookup in spotlight id=g11938
			- <word> def
				- shows the dictionary definition on top
	- bugs
		- keyboard stops responding - klavye donması
			- bilgisayarı kapatma tuşuna basarak, logoff ve login ol düzeliyor.

- ### Internet wifi network
  - wifi connection issues id=g10099
    - ref
      - <url:file:///~/Dropbox/mynotes/content/code/cosx/internet_connection_drops_constantly.md>
    - opt1: bounce mDNSResponder
      - http://apple.stackexchange.com/questions/26616/dns-not-resolving-on-mac-os-x
      - code
        - sudo launchctl unload -w /System/Library/LaunchDaemons/com.apple.mDNSResponder.plist
        - sudo launchctl load -w /System/Library/LaunchDaemons/com.apple.mDNSResponder.plist
        - restart wifi
        - chrome
          - chrome://net-internals/#dns
          - clear host cache
    - http://apple.stackexchange.com/questions/177873/full-wi-fi-ethernet-signal-but-no-internet
    - https://support.apple.com/en-ca/HT202222
    - http://apple.stackexchange.com/questions/26616/dns-not-resolving-on-mac-os-x
    - http://serverfault.com/questions/64837/dns-name-lookup-was-ssh-not-working-after-snow-leopard-upgrade
    - http://apple.stackexchange.com/questions/26616/dns-not-resolving-on-mac-os-x
    - http://apple.stackexchange.com/questions/55129/i-cannot-connect-to-internet-but-my-macbook-pro-detects-the-network-and-my-hp-co
    - https://business.tutsplus.com/tutorials/what-to-do-when-your-mac-wont-connect-to-the-internet--mac-47294
    

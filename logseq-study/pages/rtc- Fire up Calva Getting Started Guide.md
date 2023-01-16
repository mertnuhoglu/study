tags:: rtc, clj, clj/calva

- `:rcf)`
	- Rich comment formunu bitirir. Böylece paredit parantezlerin sırasını değiştirmez.
- `!delete`: balanced paren silmek için
- `^!c ^c`: eval sonucunu kopyalar
- [Finding Calva Commands - Calva User Guide](https://calva.io/finding-commands/)
	- Command Palette kullan
	- Tüm Calva komutları, "Calva" ile başlar
	  ![](https://calva.io/images/finding-commands.png)
	- Tüm ayarlar: Extensions > Calva > Contributions
	  ![](https://user-images.githubusercontent.com/30010/66733740-c754b800-ee60-11e9-877b-962f6b920cd7.png)
	- `calva.toggleKeybindingsEnabled` kısayollar arasında çatışma olunca, iptal etmek için.
- [The Top 10 Commands - Calva User Guide](https://calva.io/commands-top10/)
	- Grow/expand selection: `^w`
	- Paste form into REPL: `^!c ^!e`
	- eval and comment current form: `^!c c`
	- eval and comment top form: `^!c ^SPC`
	- select form: `^!c s`
- [Why you should consider Calva - Calva User Guide](https://calva.io/why-calva/)
	-
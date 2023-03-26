
# 20230325-Luasnip-Function-Node--Snippet-Fonksiyonlari id=g14125

pprv: [[20230325-Luasnip-Choice-Node--Coktan-Secmeli-Snippet]] <url:file:///~/prj/study/logseq-study/pages/20230325-Luasnip-Choice-Node--Coktan-Secmeli-Snippet.md#r=g14121>

Örnek:

	Function Node: snippet fonksiyonları  <url:file:///~/prj/private_dotfiles/.config/nvim/lua/mert/snippets/e03.lua#r=g14124>

Yazdığın metni bir lua fonksiyonunda işleyerek kullanmanı sağlar.

Örnek: `<input>` kısmına yazdığım metni işleyip `{}` kısmına çıktı üretir.

	local {} = require "<input>"
	->
	local {} = require "telescope.builtin"
	->
	local builtin = require "telescope.builtin"



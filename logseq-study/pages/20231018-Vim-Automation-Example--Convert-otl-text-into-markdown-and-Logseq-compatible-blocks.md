tags:: study, vim, f/blg, f/example
date:: 20231018

# 20231018-Vim-Automation-Example--Convert-otl-text-into-markdown-and-Logseq-compatible-blocks id=g15056

Input: 

rfr: ~/projects/myrepo/otl/nidea.otl

```
  iş fikri: lorem ipsum
		...
      pr uzmanı
  iş fikri: something
		...
```

Output:

rfr: [[f/isfkr]] <url:file:///~/prj/myrepo/logseq-myrepo/pages/f___isfkr.md#r=g15053>

```
  - iş fikri: lorem ipsum
	  id:: 8720cc92-970c-4d37-a3b7-854481711d47
    - ...
      - pr uzmanı
  - iş fikri: something
	  id:: 42ff1076-3282-4b10-a1b4-79ab1beeed20
		- ...
```

1. Convert tab to spaces
2. Put dash in front of every line
3. Put `id::` lines into each block
4. Run macro n times

3. Put `id::` lines into each block

rfr: function! CnvOtl2LogseqBlock01() "   <url:file:///~/projects/private_dotfiles/vim/my-vim-custom2/plugin/my-vim-custom2.vim#r=g15058>

```
 rU/^  -
```

Note: ` rU` calls a function.

5. Find n by `g//co$`

6. Put references into index

Input:

```
  - wardley haritalarını topluluk projesi olarak yapmak
	  id:: 668fd4be-dc75-4ac5-94ac-9c2857754f07
    - inovasyon wikipediasında wardley haritaları oluşturmak
  - youtubeda deep learning, R, data science, postgrest, gereksinim analizi, vim gibi konularda ücretsiz eğitimler hazırla
	  id:: 223c9695-a2f6-44a6-80a3-727c85f52d40
```

Output:

```
	ssyfkr
		((668fd4be-dc75-4ac5-94ac-9c2857754f07)) wardley haritalarını topluluk projesi olarak yapmak
		((223c9695-a2f6-44a6-80a3-727c85f52d40)) youtubeda deep learning, R, data science, postgrest, gereksinim analizi, vim gibi konularda ücretsiz eğitimler hazırla
```

rfr: function! LogseqBlockPutReferencesIntoIndex() "    <url:file:///~/projects/private_dotfiles/vim/my-vim-custom2/plugin/my-vim-custom2.vim#r=g15059>

Macro:

```
/id:: 
k^ ruıypıknnk^
```

Run n times. Find count using: `:g//co$`


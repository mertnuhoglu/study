tags:: study, org/kms

# 20230925-Etiket-Tasnifi id=g14778

## Step01: Etiketleri Gruplama

rfr: data: ~/prj/study/logseq-study/pages/ex/20230925-Etiket-Tasnifi/20230925-tags01.tsv

Ana gruplar:

	frm
	org
	p
	prd
	prg
	tll
	tpc

Gruplanmamış etiketler:

	fkr
	ekip
	gnd
	grsm
	tst
	opal
	gtd

Bunları da gruplayalım:

	fkr -> f/fkr
	ekip -> p/ekip
	gnd -> g/gnd
	grsm 
	tst -> f/tst
	opal -> org/opal
	gtd -> g/nxt

Ana aliaslar:

	frm = f = frm
	gtd = g
	person = p
	product = prd
	programming = prg
	organization = org
	tool = tll
	topic = t = tpc

## Step02: Text Generation

rfr: data-replacements: ~/prj/study/logseq-study/pages/ex/20230925-Etiket-Tasnifi/20230926-replacements.tsv

rfr: template: ~/prj/study/logseq-study/pages/ex/20230925-Etiket-Tasnifi/20230926-template-sed.txt

rfr: Script: std: [[20230924-Script--Apply-Data-To-Template]]

```
apply_data_to_template 20230926-replacements.tsv 20230926-template-sed.txt
```

rfr: output: ~/prj/study/logseq-study/pages/ex/20230925-Etiket-Tasnifi/20230926-out.txt

## Step03: Verification

rfr: script: ~/prj/study/logseq-study/pages/ex/20230925-Etiket-Tasnifi/20230926-out.txt

Script düzgün çalışacak mı önden test edelim.

Logseq'te herhangi bir sorun çıkar mı bir tag replacement yaparsak?

Scriptin ilk komutunu çalıştır.

rfr: script: ~/prj/study/logseq-study/pages/ex/20230925-Etiket-Tasnifi/20230926-e01.sh

rfr: script: ~/prj/study/logseq-study/pages/ex/20230925-Etiket-Tasnifi/20230926-e02.sh

```sh
MATCH_TEXT="ekip/gnd"
REPLACE_TEXT="p\\/ekip, g\\/gnd"
rg -l "tags::.*${MATCH_TEXT}"
rg "tags::.*${MATCH_TEXT}"
```

Düzeltmeler:

1. export kullan

```
export MATCH_TEXT="ekip/gnd"
```

2. sed içinde escapeleme yaparsan, `/` sembolleri karışıyor. `#` ile clause'ları birbirinden ayır.

```
rg -l "tags::.*${MATCH_TEXT}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s#${MATCH_TEXT}#${REPLACE_TEXT}#" {}
```

Tekrar text generation scriptini çalıştır:

```
apply_data_to_template 20230926-replacements.tsv 20230926-template-sed.txt >20230926-out02.txt 
```

## sed komutunun yaptığı değişikliklerini görmek için:

rfr: [[20230926-sed-komutlarini-verbose-calistirma]]

## Final script

rfr: ~/prj/study/logseq-study/pages/ex/20230925-Etiket-Tasnifi/20230926-e04.sh

```
> sh ~/prj/study/logseq-study/pages/ex/20230925-Etiket-Tasnifi/20230926-e04.sh
pages/20230918-Obsidian-Logseq-Kurulum.md
1:tags:: grsm, oryantasyon, tll/obsidian, tll/logseq

pages/20230905-Yazilimci-Oryantasyonu.md
1:tags:: grsm, oryantasyon
tags:: grsm, org/orientation
tags:: grsm, org/orientation, tll/obsidian, tll/logseq
tags:: org/orientation, prg/clj
pages/20230328-Promena-Demo.md
pages/20230328-Promena-Demo.md
1:tags:: p/pms
tags:: prj/pms
...
```



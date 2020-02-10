
# Anki: Ses Alıştırmaları Üretme 20200210 

Mantık:

1. Soru tarafı: Bir ses kaydı dinle

2. Cevap tarafı: Bu ses kaydının yazılı halini bul.

Zaten elimde çok sayıda ses kaydı ve yazılı cümle var. Bunları sadece farklı bir şekilde yerleştirmek yeterli olacak.

## Error: Bazı ses kayıtları niye yok?

Örnek: 

https://audio.tatoeba.org/sentences/eng/5758079.mp3

opt

		dosyaların var olup olmadığını kontrol edelim önce

## Girdi verimiz ne?

`/Users/mertnuhoglu/projects/anki_english/decks/anki_top_100_words.tsv`

Dosyalar da burada:

`/Users/mertnuhoglu/Library/Application Support/Anki2/ozgureminnuhoglu/collection.media`

Örnek satır:

``` bash
580774	1	a	bir	artikel	I'm a man.	Ben bir erkeğim.	https://audio.tatoeba.org/sentences/eng/580774.mp3	[sound:580774.mp3]
``` 

``` bash
example_id word_id	english	turkish	explain	example_en	example_tr	mp3_url	sound
``` 

1. Bu tsv dosyasını oku
2. sound ve example_en kolonlarını çek
3. yeni bir tsv dosyası oluştur
4. bunu anki'ye import et

## dosyaların mevcut olup olmadığını nasıl test ederiz?

``` bash
file.exists("/Users/mertnuhoglu/Library/Application Support/Anki2/ozgureminnuhoglu/collection.media/580774.mp3")
file.exists("/Users/mertnuhoglu/Library/Application Support/Anki2/ozgureminnuhoglu/collection.media/5758079.mp3")
``` 

Dosya isimlerini path haline getir:

``` bash
path = "/Users/mertnuhoglu/Library/Application Support/Anki2/ozgureminnuhoglu/collection.media"
d1 = d0 %>%
	dplyr::select("example_id", "sound", "text" = "example_en") %>%
	dplyr::filter(file.exists(sprintf("%s/%s.mp3", path, example_id)))
``` 

## Anki card type oluştur

Front:

``` bash
<div class="example_id">{{example_id}}</div>
{{sound}}
``` 

Back:

``` bash
{{FrontSide}}

<hr id=answer>

<div class="english_text">{{english_text}}</div>
``` 

## Process:

``` bash
~/projects/anki_english/scripts/anki_top_words_2_listening.R
``` 



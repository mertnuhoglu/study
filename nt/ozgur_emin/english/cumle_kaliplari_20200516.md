
# I <verb1> you to <verb2>

Check `~/projects/study/nt/ozgur_emin/english/sentence_20200516/example_en.tsv`

use: `~/projects/study/code/anki/process_anki_tatoeba_extract_sentences.md`

output: `/Users/mertnuhoglu/projects/anki_english/decks/anki_sentence_20200516.tsv`

examples:

    I want you to run
    I want you to go
    I want you to swim
    I want you to read this book.
    I want you to eat the water.
    I want you to go to bed.

# ... that I ...  id=g_11008

``` bash
cd ~/Downloads/anki/tatoeba_sentences
sed -n '/\<that\>/ p' sentence_pairs.tsv > gen/that.tsv
nvim ~/Downloads/anki/tatoeba_sentences/gen/that.tsv
``` 

``` vim
:v/that \(you\b\|I\|he\b\|she\|tom\b\)/d
``` 

copy paste rows to: `~/projects/study/nt/ozgur_emin/english/sentence_20200521/example_en.tsv`

belirli kalıpları seç:

``` 
g/^I wish/m$
g/^It's/m$
g/^tell/m$
``` 

use: `~/projects/study/code/anki/process_anki_tatoeba_extract_sentences.md`

``` 
tags="sentence_20200521"
cd "~/projects/study/nt/ozgur_emin/english/${tags}"
sentence_ids_eng="~/Downloads/anki/tatoeba_sentences/sentence_ids_eng.tsv"
example_en="~/projects/study/nt/ozgur_emin/english/${tags}/example_en.tsv"
~/projects/anki_english/scripts/anki_process_tatoeba_sentences "${tags}" "${sentence_ids_eng}" "${example_en}"
``` 

# other sentence patterns: id=g_10955

I hope you will do it
I though you could swim.
I go where I like most
I like how you swim
I think that we should move
I wish you were here
I thought you didn't like it
I liked you eating that way
I wrote that we missed him 

I think I want you to stay.	Sanırım kalmanı istiyorum.
I told Tom we were friends.	Tom'a bizim arkadaş olduğumuzu söyledim.
I told Tom who I really am.	Gerçekten kim olduğumu Tom'a söyledim.
I suppose Tom told you that.	Sanırım Tom onu sana söyledi.
I was afraid you'd say that.	Ben senin onu söyleyeceğinden korktum.
I was compelled to go there.	Oraya gitmeye mecbur edildim.
I was here when you came in.	Sen içeri girdiğinde ben buradaydım.
I assume that Tom can't swim.	Sanırım Tom yüzemez.
I believe he is not a lawyer.	Ben onun bir avukat olmadığına inanıyorum.
I consider Tom a good friend.	Tom'u iyi bir arkadaş olarak düşünüyorum.
I did what Tom told me to do.	Ben Tom bana ne yapmamı anlattıysa yaptım.
I did what everyone else did.	Başka herkesin yaptıklarını yaptım.
I didn't catch the last word.	Son sözcüğü anlamadım.
I think Tom told me the truth.	Sanırım Tom bana gerçeği söyledi.

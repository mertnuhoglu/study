---
title: "Study anki"
date: 2019-02-21T13:52:38+03:00 
raft: true
description: ""
tags:
categories: bash, 
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
resource_files:
path: ~/projects/study/bash/study_anki.md
state: wip

---

# refcard

    Import tsv files into anki <url:file:///~/projects/study/code/anki/study_anki.md#r=g12334>
		Import md files into anki  <url:file:///~/projects/study/code/anki/study_anki.md#r=g12355>
    Card Templates  <url:file:///~/projects/study/code/anki/study_anki.md#r=g11563>
      Card Type: Cloze: Text -> Text+Extra
        anki > browse > 
          din
          eyez_open_cloze


# Import tsv files into anki id=g12334

## Basic Cards:

01: Edit `~/projects/study/code/anki/ex/study_anki/import_tsv_to_anki/basic01.tsv`

02: Anki > Import > 

- Type = Basic (and reversed card)
- Import even if existing note has same first field
- Field mapping:
  - Field 1 = Note ID
  - Field 2 = Front
  - Field 3 = Back

## From Excel

Aynı şekilde, ancak çok satırlı hücreler kullanabilirsin.

Bu durumda tsv'ye import etmeden önce, vim'de `ConvertAnkiTsv` fonksiyonuyla formatı düzeltmelisin.

# Import md files into anki  id=g12355

## Basic Cards

01: Edit `~/projects/study/logbook/anki_log_20200222.md`

02: `How to convert markdown to anki? <url:file:///~/projects/study/code/anki/study_anki.md#r=g12330>`

## Cloze Cards:

# Articles

## Anki Manual

https://apps.ankiweb.net/docs/manual.html

### The Basics

#### Cards

A question and answer pair is a card

#### Decks

A group of cards is a deck.

Putting decks into a tree:

opt01: Name them with "::" between each level.

opt02: drag and drop

# Tasks

## Customize Settings for Cloze Card Type

I usually use anki for code blocks. To format them in an easy to read style, I changed styling card settings of `Cloze`.

1. Tools > Manage Note Types

3. Select `Cloze` > Cards

### Card Templates  id=g11563

4. Cards

ref: `~/projects/study/code/anki/ex/study_anki/card_templates/cloze.md`

## How to convert markdown to anki? id=g12330

Edit `~/.vim/bundle/my-vim-custom/plugin/my-vim-custom.vim`

``` vimscript
function! ConvertAnkiMd()
  set expandtab
  retab
  g/<url:/ s/<url:file:...// | s/>$//
  " indented lines match this regex:
  " \(  \+\)\(.*\)\(<br>$\)\@!
  " escape < > ` in not indented lines
  v/\(  \+\)\(.*\)\(<br>$\)\@!/ s/<\(br>\)\@!/\&lt;/g | s/\(<br\)\@<!>/\&gt;/g
  " wrap in ` and append <br>
  %s/^\(  \+\)\(.*\)/\1`` \2 `` <br>/
  " replace indentation spaces with center dots · because anki ignores spaces
  g/^  / s/  /··/g 
  %s/··``/  ``/
  " escape [..](..)
  " wrap underscore words with backquote
  v/^·/ s/](/\&#093;(/ | s/\( \)\@<=[^ `]\+_[^ `]\+/`\0`/g
  g/<br>/left
endfunction
command! ConvertAnkiMd call ConvertAnkiMd()
``` 

Prepare a md file such as:

``` md

---

## Question Title

Question body {{c2::cloze_placeholder01}}/{{c1::cloze_placeholder02}}

%

Answer body (optional)

% 

clozeq

---

``` 

Usage: 

1. Call `:ConvertAnkiMd` on a md file in vim. Save it as similar to: `anki_<something>.md`

Ex: `/Users/mertnuhoglu/projects/study/js/anki_js.md`

2. Convert to apkg

``` bash
ankdown -p <file>.apkg -d db01 -i <file>.md
``` 

3. Open anki and import apkg: File > Import ...

4. Select deck. Open (B) > Whole Collection > Select cards > File > Change card type (#+m) > Cloze

## Shortcut for placeholder making:

``` vimscript
vnoremap <Leader>e s{{c1::<c-r>"}}<esc>
``` 

## Çoktan seçmeli sorular hazırlama id=g12332

### 20191220 

Örnek:

eyes_open_test deck in Anki

ref: `~/projects/anki_english/decks/anki_eyes_open_choice.txt`

Note Type: English Grammar in Use Exercises

### Logs 20191220  id=g12331

Edit `~/gdrive/mynotes/stuff/ozgur_emin/english/anki/exam_20191024/cloze_choice_20191220.tsv`




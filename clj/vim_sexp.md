---
title: "vim-sexp"
date: 2020-12-28T22:09:44+03:00 
draft: false
description: ""
tags:
categories: clojure
type: post
url:
author: "Mert Nuhoglu"
output:
  pdf_document:
    highlight: tango

---

# Tool: vim-sexp id=g_11846

[tpope/vim-sexp-mappings-for-regular-people: vim-sexp mappings for regular people](https://github.com/tpope/vim-sexp-mappings-for-regular-people)

[guns/vim-sexp: Precision Editing for S-expressions](https://github.com/guns/vim-sexp)

## Definitions:

	| compound form    | text delimited by parens                  |
	| string           | text                                      |
	| comment          |                                           |
	| macro characters | leading chars                             |
	| element          | current string/comment/c.form/macro.chars |

## Keybindings:

selections (visual):

	| af if | compound forms |
	| aF iF | top c.forms    |
	| as is | strings        |
	| ae ie | elements       |

motions (tpope):

	| W B E gE    | move word               |
	| ()          | move to parens          |
	| M-b M-w M-e | move element-wise       |
	| [[  ]]      | top element             |
	| [e ]e       | select adjacent element |

indent:

	| ==| compound form|
	| =-| top compound form|

<mark>list<mark> manipulation (tpope): 

	| >) <) >( <( | slurp/barf: parantezi o yöne taşı    |
	| >f <f       | move form                            |
	| >e <e       | move element                         |
	| <I >I       | insert at start/end of a form        |

surround.vim like mappings (tpope):

	| dsf           | splice (delete surroundings of form) |
	| cse( cse) cseb | surround in parens                   |

wrap:

	| ,i ,I   | c.form ()  |
	| ,[ ,]   | c.form []  |
	| ,{ ,}   | c.form {}  |
	| ,W ,w   | element () |
	| ,e[ ,e] | element [] |
	| ,e{ ,e} | element {} |


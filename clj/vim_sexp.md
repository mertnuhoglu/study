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

# Tool: vim-sexp id=g11846

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

  | af if            | compound forms                            |
  | aF iF            | top c.forms                               |
  | as is            | strings                                   |
  | ae ie            | elements                                  |

motions (tpope):

  | W B E gE         | move word                                 |
  | ()               | move to parens                            |
  | M-b M-w M-e      | move element-wise                         |
  | [[  ]]           | top element                               |
  | [e ]e            | select adjacent element                   |

indent:

  | ==               | compound form                             |
  | =-               | top compound form                         |

<mark>list<mark> manipulation (tpope): 

  | >) <) >( <(      | slurp/barf: parantezi o yöne taşı         |
  | >f <f            | move form                                 |
  | >e <e            | move element                              |
  | <I >I            | insert at start/end of a form             |

surround.vim like mappings (tpope):

  | dsf              | splice (delete surroundings of form)      |
  | cse( cse) cseb   | surround in parens                        |

wrap:

  | ,i ,I            | c.form ()                                 |
  | ,[ ,]            | c.form []                                 |
  | ,{ ,}            | c.form {}                                 |
  | ,W ,w            | element ()                                |
  | ,e[ ,e]          | element []                                |
  | ,e{ ,e}          | element {}                                |

# sexp cheatsheet id=g11971

ref: `vim-sexp <url:file:///~/projects/vim_repos/my-vim-custom/plugin/my-vim-custom.vim#r=g12870>`

`sexp_mappings_for_regular_people`: `~/.vim/bundle/vim-sexp-mappings-for-regular-people/plugin/sexp_mappings_for_regular_people.vim`

  | motions          |                                           |
  | B                | sexp_move_to_prev_element_head            |
  | W                | sexp_move_to_next_element_head            |
  | gE               | sexp_move_to_prev_element_tail            |
  | E                | sexp_move_to_next_element_tail            |
  | flow             |                                           |
  | M-]              | <Plug>(sexp_flow_to_next_open)            |
  | insertion        |                                           |
  | <I               | sexp_insert_at_list_head                  |
  | >I               | sexp_insert_at_list_tail                  |
  | moving           |                                           |
  | <f               | sexp_swap_list_backward                   |
  | >f               | sexp_swap_list_forward                    |
  | <e               | sexp_swap_element_backward                |
  | >e               | sexp_swap_element_forward                 |
  | >(               | sexp_emit_head_element                    |
  | <)               | sexp_emit_tail_element                    |
  | <(               | sexp_capture_prev_element                 |
  | >)               | sexp_capture_next_element                 |
  | wrapping a01     | rfr: g12870                               |
  | SPC si           | () sexp_round_head_wrap_list              |
  | SPC sı           | [] sexp_square_head_wrap_list             |
  | SPC se           | {} sexp_curly_head_wrap_list              |
  | SPC Si           | () sexp_round_head_wrap_element           |
  | SPC Sı           | [] sexp_square_head_wrap_element          |
  | SPC Se           | {} sexp_curly_head_wrap_element           |
  | wrapping a02     | rfr: vim-sexp regular-people              |
  | dsf              | dpplice (delete surroundings of form)     |
  | cse(             | surround element in parens                |
  | cse[             | surround element in brackets              |
  | cse{             | surround element in curly                 |

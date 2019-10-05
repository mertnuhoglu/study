---
title: "Study Vim UTL"
date: 2019-10-04T12:05:04+03:00 
draft: false
description: ""
tags:
categories: vimscript, 
type: post
url:
author: "Mert Nuhoglu"
output: rmarkdown::html_document
blog: mertnuhoglu.com
resource_files:
-
---

# Article: Help File UTL

<url:vimhelp:utl>

Jump to vimhelp files:

		<url:vimhelp:vimfiler-default-key-mappings>

Utl: universal text links <url:/Users/mertnuhoglu/gdrive/mynotes/content/code/cvim/cvim.md#tn=Utl: universal text links>

<url:../plugin/utl.vim>
<url:vimscript::reg *>
:Utl copyLink currentFile
:Utl openLink <URL pasted from clipboard>
<url:./infos/20080523_Instructions_Workreports_ext.ppt>
:Utl copyFileName
:Utl help commands
:let g:utl_cfg_hdl_scm_http=g:utl_cfg_hdl_scm_http__wget
<url:vimscript:let g:utl_cfg_hdl_scm_http=g:utl_cfg_hdl_scm_http__wget>
<url:http://www.ietf.org/rfc/rfc3986.txt>
<url:http://www.ietf.org/rfc/rfc3986.txt#line=343>
<url:http://www.ietf.org/rfc/rfc3986.txt#^1.1.2.  Examples>
<url:http://www.vim.org/scripts/download_script.php?src_id=3640#horizontal or vertical>

PDF page addressing: <url:vimhelp:utl-tutpdffrag>

		<url:http://www.tech-invite.com/RFCs/PDF/RFC3986.pdf#page=4>

Enable: <url:config:#r=utl_cfg_hdl_mt_application_pdf__acrobat>

``` bash
let g:utl_cfg_hdl_mt_application_pdf_acrobat_exe_path = ":silent !open %p"
``` 

<url:/Users/mertnuhoglu/Downloads/r_libraries/shiny.pdf>
:Utl ol /Users/mertnuhoglu/Downloads/r_libraries/shiny.pdf

Lookup word

``` bash
nmap ,l :exe ":Utl ol https://dict.leo.org/?search=" . expand("<cword>")
``` 


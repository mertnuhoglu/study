---
title: "Study Css"
date: 2019-09-11T14:29:20+03:00 
draft: true
description: ""
tags: []
categories: ["html", "css"]
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
blog: mertnuhoglu.com
resource_files:
path: ~/projects/study/html/study_css.md
state: wip

---

# Articles

## Article01: CSS is Awesome | CSS-Tricks

https://css-tricks.com/css-is-awesome/

CSS is hard because its properties interact, often in unexpected ways

One rule of thumb for mitigating this is, never be more explicit than you need to be

Another rule of thumb is to let either width or height be determined by content

Discussion in https://news.ycombinator.com/item?id=19021719

Here's the biggest point that I think has to click: CSS isn't a programming language, it's a constraint language. The browser has lots of default, complex behaviors that drive layout, and whereas in a traditional programming language you tend to "build up" from nothing, CSS is more about paring down and guiding that automatic engine with as few statements as necessary. You tell the browser exactly what your layout requires, and the browser figures out how to flesh that out into various contexts and situations

The float era was certainly a dark time, and I'm glad I came into the field on the tail end of it. It was a hold-over from CSS' document-focused origins

Your Autodesk comparison belies a fundamental misunderstanding about the nature of the DOM - it is, to its great advantage, a 1D space which flows into a 2D shape. In fact, this was the great flaw with using tables for layout in the first place. By making the content fundamentally 2D, all it could do to respond to different screen sizes and different browser window resizes is stretch. But you don't actually want your layout to be totally percentage-based. You want your content to wrap and flow to make the best use of the available space that it can, with no assumptions about screen size or ratio. The web is 1D because text is 1D

CSS is actually pretty simple, as long as you start with the smallest elements and work up. It becomes a nightmare if you try and address everything individually. It’s not really a programming language, but a series of guidelines.

I’ve seen this simplicity actually throw developers as they’re used to something much more complex and systematic

---
title: "Json Tool: fx"
date: 2021-01-19T22:22:52+03:00 
draft: false
description: ""
tags:
categories: json
type: post
url:
author: "Mert Nuhoglu"
output: rmarkdown::html_document

---

# Quickstart fx id=g_11922

# Index fx id=g_11921

# Issues fx

## How to use

```bash
fx ~/codes/data/planets.json
```

Use `hjkl` keys to navigate

Interactive query mode:

```
.results[2]
```

# README: antonmedv/fx: Command-line tool and terminal JSON viewer 🔥

[antonmedv/fx: Command-line tool and terminal JSON viewer 🔥](https://github.com/antonmedv/fx)

# Article: 

[Discover how to use fx effectively, a JSON manipulation command line tool | by Anton | Medium](https://medium.com/@antonmedv/discover-how-to-use-fx-effectively-668845d2a4ea)

```bash
curl https://swapi.dev/api/planets/1/ | fx .
```

## Lodash

Edit `~/.fxrc`

```js
Object.assign(global, require('lodash/fp'))
```

```bash
curl 'https://api.github.com/repos/facebook/react/commits' \
| fx 'groupBy("commit.author.name")' 'mapValues(size)' \
      toPairs 'sortBy(1)' reverse 'take(10)' fromPairs
```


## Tables

```bash
fx ~/codes/data/commits.json .[].commit.author table
fx ~/codes/data/planets.json .results[2] table
fx ~/codes/data/planets.json . table
```



---
title: "Study: Pedestal Library"
date: 2021-04-17T18:53:43+03:00 
draft: false
description: ""
tags:
categories: clojure
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css

---

# Article: Pedestal - What is an Interceptor? id=g_12100

[Pedestal - What is an Interceptor?](http://pedestal.io/guides/what-is-an-interceptor)

> Suppose you need to add entity-level access control to an API. You can create an interceptor that looks at the entity to decide if authorization is needed. If so, enqueue an interceptor that does the auth check or redirects.

> Most controller logic takes this form:

> 1. Get information from the request.
> 2. If the request can’t be done, generate an error response.
> 3. Fetch some data.
> 4. Decide what operation to perform on the data.
> 5. Decide if the operation can be done on the data.
> 6. If so, attempt an operation on the data.
> 7. If not, generate an error response.
> 8. Attempt to store some data.
> 9. If it fails, generate an error response.
> 10. Otherwise, generate a response.

> Every place the words "decide" or "if" appear here, you could enqueue different interceptors. This breaks the controller logic down into a handful of very small pieces that each consume and produce context maps. They are easily tested in isolation from a database or external service.

# Article: Sieppari Readme

[metosin/sieppari: Small, fast, and complete interceptor library for Clojure/Script](https://github.com/metosin/sieppari)


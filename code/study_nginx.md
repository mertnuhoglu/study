---
title: "Study nginx"
date: 2020-01-02T11:32:53+03:00 
draft: true
description: ""
tags:
categories: nginx
type: post
url:
author: "Mert Nuhoglu"
output: html_document
blog: mertnuhoglu.com
resource_files:
-
path: ~/projects/study/code/study_nginx.md
---

## reverse_proxy

### Article01: NGINX Docs | NGINX Reverse Proxy

https://docs.nginx.com/nginx/admin-guide/web-server/reverse-proxy/

``` bash
location /some/path/ {
    proxy_pass http://www.example.com/link/;
}
``` 

The request with the `/some/path/page.html` URI will be proxied to `http://www.example.com/link/page.html`

``` bash
location ~ \.php {
    proxy_pass http://127.0.0.1:8000;
}

``` 

# Articles

## Article02: What Is The Difference Between A URI And A URL? - DEV Community 

https://dev.to/flippedcoding/what-is-the-difference-between-a-uri-and-a-url-4455

URI stands for uniform resource identifier and URL stands for uniform resource locator

## Article: Understanding Cross-Origin Resource Sharing (CORS) - DEV Community

https://dev.to/miguelmota/understanding-cross-origin-resource-sharing-cors-2i3e


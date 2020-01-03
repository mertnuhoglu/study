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

Same-Origin vs Cross-Origin:

Same-Origin: Communication in the same house

Cross-origin request

``` bash
node server.js
  ##> listening on port 8000
node server2.js
  ##> listening on port 9000
``` 

`index.html`

``` bash
  <script>
    (async () => {
      const res = await fetch('http://localhost:8000/api/posts')
``` 

1. Open http://localhost:9000

2. Server 9000 responds `index.html`

3. Browser loads `index.html` and makes the request `http://localhost:8000/api/posts`

4. Server 8000 gets the request and sends a response with `/api/posts` data and header.

5. Browser gets the data and header. Browser checks if client at `localhost:9000` can access it. It cannot so, it returns CORS error.

Simple chat:

Browser: "hello server http://127.0.0.1:8000 please give me the data at /api/posts and let me know if the client at http://localhost:9000 can access it. Here's the HTTP message:"

``` bash
Get /api/posts HTTTP1.1
User-Agent: Chrome
Host: 127.0.0.1:8000
Accept: */*
Origin: http://localhost:9000
``` 

Server: "here is the data, and the client cannot have access because I don't have the origin http://localhost:9000 as an allowed origin to access the data."

``` bash
HTTP/1.1 200 OK
``` 

Server responds the data but the browser doesn't give it to the JS client.

Solution: Server sets `Access-Control-Allow-Origin` response header.

``` bash
app.use((req, res, next) => {
  res.set('Access-Control-Allow-Origin', '*')
``` 

### Access-Control-Allow-Origin

CORS headers:

- `Origin`
- `Access-Control-Allow-Origin`

Example origins:

- `http://localhost:3000`

`null` value as origin means: it is an absolute file path like `file:///Users/temp.html`

Example `Access-Control-Allow-Origin` values:

- *
- http://localhost:800
- null

`null` means: you don't care if the origin is not set.

### Preflight requests

HTTP methods that trigger preflight request: PUT, PATCH, DELETE, TRACE

Don't trigger (simple requests): HEAD, GET, POST

### Access-Control-Request-Method

Asks permission to use a specific HTTP method. 

It is set by the browser with a non-simple method request.

### Access-Control-Allow-Methods

Used in preflight requests to tell the client which methods are allowed with CORS

``` bash
Access-Control-Allow-Methods: HEAD, GET, POST, PUT, PATCH, DELETE
``` 

GET, POST are redundant. They are always allowed.

### Access-Control-Request-Headers



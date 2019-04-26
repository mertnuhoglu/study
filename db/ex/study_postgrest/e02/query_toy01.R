library(magrittr)
response <- httr::GET("http://localhost:8080/rest/table01")
print(response)
  ##> Response [http://localhost:8080/rest/table01]
  ##>   Date: 2019-01-20 18:31
  ##>   Status: 200
  ##>   Content-Type: application/json; charset=utf-8
  ##>   Size: 56 B
  ##> [{"id":101,"title":"t101"},
response$url
[1] "http://localhost:8080/rest/table01"
response$status_code
[1] 200
response$header
  ##> $server
  ##> [1] "openresty"
  ##> 
  ##> $date
  ##> [1] "Sun, 20 Jan 2019 18:51:59 GMT"
  ##> 
  ##> $`content-type`
  ##> [1] "application/json; charset=utf-8"
  ##> 
  ##> $`transfer-encoding`
  ##> [1] "chunked"
  ##> 
  ##> $connection
  ##> [1] "keep-alive"
  ##> 
  ##> $vary
  ##> [1] "Accept-Encoding"
  ##> 
  ##> $`content-range`
  ##> [1] "0-1/*"
  ##> 
  ##> $`x-frame-options`
  ##> [1] "SAMEORIGIN"
  ##> 
  ##> $`x-content-type-options`
  ##> [1] "nosniff"
  ##> 
  ##> $`x-xss-protection`
  ##> [1] "1; mode=block"
  ##> 
  ##> $`content-location`
  ##> [1] "/rest/table01"
  ##> 
  ##> $`request-time`
  ##> [1] "0.013"
  ##> 
  ##> $`content-encoding`
  ##> [1] "gzip"
  ##> 
  ##> attr(,"class")
  ##> [1] "insensitive" "list"
response$cookies
  ##> [1] domain     flag       path       secure     expiration name       value
  ##> <0 rows> (or 0-length row.names)
response$content
  ##>  [1] 5b 7b 22 69 64 22 3a 31 30 31 2c 22 74 69 74 6c 65 22 3a 22 74 31 30 31 22 7d 2c 20 0a 20 7b 22 69 64 22 3a 31 30 31 2c 22 74 69 74 6c 65 22 3a
  ##> [49] 22 74 32 30 31 22 7d 5d
response$time
  ##>      redirect    namelookup       connect   pretransfer starttransfer         total
  ##>      0.000000      0.001900      0.002074      0.002111      0.018099      0.018143
response$config
  ##> NULL
str(httr::content(response)) 
  ##> List of 2
  ##>  $ :List of 2
  ##>   ..$ id   : int 101
  ##>   ..$ title: chr "t101"
  ##>  $ :List of 2
  ##>   ..$ id   : int 101
  ##>   ..$ title: chr "t201"
httr::content(response, as = "parsed")
  ##> [[1]]
  ##> [[1]]$id
  ##> [1] 101
  ##> 
  ##> [[1]]$title
  ##> [1] "t101"
  ##> 
  ##> 
  ##> [[2]]
  ##> [[2]]$id
  ##> [1] 101
  ##> 
  ##> [[2]]$title
  ##> [1] "t201"
httr::content(response, as = "text")
  ##> [1] "[{\"id\":101,\"title\":\"t101\"}, \n {\"id\":101,\"title\":\"t201\"}]"

response <- httr::GET("http://localhost:8080/rest/table01", 
											query = list( select = "id")
											)
httr::content(response, as = "text")
  ##> [1] "[{\"id\":101}, \n {\"id\":101}]"

response <- httr::GET("http://localhost:8080/rest/table01?select=id")
httr::content(response, as = "parsed") %>% str
  ##> List of 2
  ##>  $ :List of 1
  ##>   ..$ id: int 101
  ##>  $ :List of 1
  ##>   ..$ id: int 101

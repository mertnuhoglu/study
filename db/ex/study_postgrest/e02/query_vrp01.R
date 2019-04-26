library(magrittr)
response <- httr::GET("http://localhost:8080/rest/address")
print(response)
  ##> Response [http://localhost:8080/rest/address]
  ##>   Date: 2019-01-22 07:11
  ##>   Status: 401
  ##>   Content-Type: application/json; charset=utf-8
  ##>   Size: 94 B

print(http_status(response))
  ##> $category
  ##> [1] "Client error"
  ##> 
  ##> $reason
  ##> [1] "Unauthorized"
  ##> 
  ##> $message
  ##> [1] "Client error: (401) Unauthorized"
response <- httr::GET("http://localhost:8080/rest/address",
	add_headers(
		"Authorization" = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
	)
)
print(response)
  ##> Response [http://localhost:8080/rest/address]
  ##>   Date: 2019-01-22 07:10
  ##>   Status: 200
  ##>   Content-Type: application/json; charset=utf-8
  ##>   Size: 367 B
  ##> [{"address_id":1,"address":"address_3_3_3","company_extid":null},
  ##>  {"address_id":2,"address":"address_3_3_3","company_extid":"company_e"},
  ##>  {"address_id":3,"address":"address_5_5_5_5_","company_extid":"company_e"},
  ##>  {"address_id":4,"address":"address_2_2_2_2_","company_extid":"company_e"},
print(status_code(response))
  ##> [1] 200

httr::content(response, as = "text")
  ##> [1] "[{\"address_id\":1,\"address\":\"address_3_3_3\",\"company_extid\"...
httr::content(response, as = "parsed") %>% str                        
  ##> List of 5
  ##>  $ :List of 3
  ##>   ..$ address_id   : int 1
  ##>   ..$ address      : chr "address_3_3_3"
  ##>   ..$ company_extid: NULL
  ##>  $ :List of 3
 


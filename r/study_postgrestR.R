library(devtools)
install_github("clesiemo3/postgrestR")
library(postgrestR)

# ex
domain <- "https://postgrest.herokuapp.com"

pg.get(domain, "speakers")

# copied from pg.get
filter.convert <- function(filter, pg.filter.syntax){
	if(pg.filter.syntax){
		filter.exp <- filter
	} else {
		filter.exp <- filter
		filter.exp <- gsub("( |c*\\(|\\))", "",filter.exp)
		filter.exp <- gsub(">=","=gte.",filter.exp, fixed = TRUE)
		filter.exp <- gsub("<=","=lte.",filter.exp, fixed = TRUE)
		filter.exp <- gsub(">", "=gt.",filter.exp, fixed = TRUE)
		filter.exp <- gsub("<", "=lt.",filter.exp, fixed = TRUE)
		filter.exp <- gsub("==","=eq.",filter.exp, fixed = TRUE)
		filter.exp <- gsub("!=","=neq.",filter.exp, fixed = TRUE)
		filter.exp <- gsub("%*in%*","=in.",filter.exp)
	}

	filter <- paste(filter.exp, collapse = "&")
	return(filter)
}
table = ""
select = ""
filter = ""
limit = ""
order = ""
pg.filter.syntax = FALSE
url.only = FALSE
encoding = "UTF-8"

if(length(select) > 1){
  select <- paste(select, collapse = ",")
}
select <- paste0("select=", select)

## filter ##
if(length(filter) == 1){
  # negative lookahead to only split on commas not inside parens
  filter <- unlist(strsplit(filter, ",(?![^\\(]*\\))", perl=TRUE))
}

filter <- filter.convert(filter, pg.filter.syntax)

## limit ##
if(!limit==""){
  limit <- as.integer(limit)
  if(is.na(limit)){
    stop("limit is not an integer or cannot be coerced to one.")
  }
  limit <- paste0("limit=", limit)
}

## order ##
if(length(order)>1){
  order <- paste(order, collapse = ",")
  order <- gsub("(desc\\()(.+)\\)", "\\2.desc", order)
  order <- paste0("order=", order)
}

## url build ##
base.url <- paste0(domain, "/", table, "?")
url <- paste(base.url, select, filter, order, limit, sep = "&")
url <- gsub("(\\n|\\t|\\r)", "", url)


r <- httr::GET(url)
r.content <- httr::content(r, "text", encoding = encoding)

dat <- jsonlite::fromJSON(r.content)

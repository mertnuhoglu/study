library(dplyr)

ba0 = jsonlite::fromJSON("~/projects/jekyll/burakpehlivan_org/wp2hugo/blog2md/burak02.json", simplifyDataFrame = T) 
ba1 = ba0$items$item %>%
	as_tibble() %>%
	dplyr::select(c("title", "link", "post_id" , "post_type" )) 

rio::export(ba1, "out/ba1.tsv")
ba2 = ba1 %>%
	dplyr::filter(post_type %in% c("page", "post")) %>%
	dplyr::mutate(page_name = stringr::str_replace(link, "/$","") )  %>%
	dplyr::mutate(page_name = stringr::str_replace(page_name, "^.*/","")) %>%
	dplyr::rename(alias = link) %>%
	dplyr::mutate(link = glue::glue("/blog/{page_name}")) %>%
	dplyr::filter(!stringr::str_detect(link, "[?#]"))
rio::export(ba2, "out/ba2.tsv")
nrow(ba2)
  ##> 421

length(ba2$page_name)
  ##> 421

# Dosya listesiyle bunları kıyasla:

fl = list.files("/Users/mertnuhoglu/projects/jekyll/burakpehlivan_org/burakpehlivan_org/content/blog/") %>%
	stringr::str_replace("\\.md","")

missing_pages = setdiff(fl, ba2$page_name)
writeLines(missing_pages, "out/missing_pages.txt")

gen_redirection_pages <- function(ba2) {
	ba2 %>%
		dplyr::mutate(content = 
			glue::glue('
				<!DOCTYPE html>
				<html>
					<head>
						<title>{link}</title>
						<link rel="canonical" href="{link}"/>
						<meta name="robots" content="noindex">
						<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
						<meta http-equiv="refresh" content="0; url={link}"/>
					</head>
				</html>
			'
		))
}

rp = gen_redirection_pages(ba2)

for (i in seq_len(nrow(rp))) {
	row = rp[i, ]
	print(row)
	dir.create(glue::glue("out/html/{row$post_id}"), showWarnings = TRUE, recursive = T)
	writeLines(row$content, glue::glue("out/html/{row$post_id}/{row$page_name}.html"))
}

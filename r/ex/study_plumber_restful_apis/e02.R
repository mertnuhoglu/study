# e02.R

#' @post /echo
function(req){
	formContents = Rook::Multipart$parse(req)
  list(formContents = formContents)
}

#' @post /echo2
function(req){
	formContents = Rook::Multipart$parse(req)
	somefile <- readLines(con = formContents$upload$tempfile)
	print(formContents$upload$tempfile)
  list(formContents=formContents)
}

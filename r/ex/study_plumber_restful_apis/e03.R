# e03.R

#' @post /echo3
function(req){
	formContents = Rook::Multipart$parse(req)
	print(formContents)
	print(formContents$file02$tempfile)
  list(formContents=formContents)
}

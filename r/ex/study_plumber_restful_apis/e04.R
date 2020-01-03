# e04.R

#' @post /readexcel
function(req){
	formContents = Rook::Multipart$parse(req)
	print(formContents)
	print(formContents$file$tempfile)
	path = formContents$file$tempfile
	d1 = readxl::read_excel(path)
	str(d1)
  list(formContents=formContents)
}

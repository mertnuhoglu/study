source("source_libraries.R") 

options(repos=structure(c(CRAN="http://cran.ncc.metu.edu.tr/"))) 

options(width=150)
options(error=traceback)
options(max.print=100)
# disable scientific notation in numbers
options(scipen=999)

rmall = function() { rm(list = ls()) }
l = length
n = nrow
nn = names
g = grep
s = utils::str
is = intersect
rt = function() rtags(ofile = 'tags')

error_recover = function() options(error=recover)
error_traceback = function() options(error=traceback)
# override functions
filter = dplyr::filter

# shortcuts
tb = traceback

make_rows = function(alist, indexes) {
	# convert a list that contains multiple rows of a dataframe to a dataframe
	# where multiple rows are handled properly
	df0 = lapply(alist, function(el) data.frame(val = el)) %>% rbindlist
	counts = lapply(alist, length)
	nl = rep(indexes, counts)
	result = cbind(df0, index = nl)
	return(result)
}
noop = function(...) { 
	dots = list(...)
	return(unlist(dots, recursive = F))
}

# efficient versions of set functions
intersect = dplyr::intersect
union = dplyr::union
setdiff = dplyr::setdiff
setequal = dplyr::setequal

taildt = function(x, n = 6L, ...) 
	x[ (nrow(x)-n):nrow(x) ] 

null_fun = function(fun) {
	function(...) {
		if (is.blank(...)) return()
		fun(...)
	}
}

null2na = function(mat) {
    mat[sapply(mat, is.null)] <- NA
    return(mat)
}

compareo = function(x, y) compare(x, y, ignoreOrder=T)

join_filename_cik = function(df) read_mapping_cik_filename() %>% right_join(df, by="filename")

# filter blank values out
compact = partial(Filter, Negate(is.null))

unlistr = partial(unlist, recursive = F)
pastec = partial(paste, collapse=", ")

# lapply variants lapply2
ldfapply = function( m, fun, ... ) {
	lapply( seq_along(m), function( i, m, ...) {
		k = names(m)[i]
		v = m[[i]]
		fun( v, k, ... )
  }, m,
	...)
}
lapply_stop = function(x, f, ...) {
	force(f)
	out = vector("list", length(x))
	for (i in seq_along(x)) {
		out[[i]] = f(x[[i]], ...)
		if ( ! is.blank(out[[i]]) ) return( out %>% unlist(recursive=F) )
	}
	out %>% unlist(recursive=F)
}

# lapply rows of a dataframe
applyr = partial(apply, MARGIN = 1)
applyc = partial(apply, MARGIN = 2)
# function parameters modified st. data arg is always the first arg (for magrittr)
sprintm = function(x, fmt, ...) sprintf(fmt, x, ...)
# map x with names(x)
mapn = function(x, fun) Map(fun, x, names(x))
# map x magrittr way
mapm = function(x, fun, ...) Map(fun, x, ...)
# map and performance tracking
mapp = function(fun, x, y, ...) 
	llplyp(seq_along(x),
		function(i, x, y)
			fun(x[[i]], y[[i]])
		, x, y, ...
	)

# grep variants
filterm = function(x, fun) Filter(fun, x)
triml = function(x, ch) ltrim_char(ch, x)
trimr = function(x, ch) rtrim_char(ch, x)
duplicatedv = function(x) x %>% duplicated %>% extractm(x)
reverse_args = function(fun) 
	function(arg1, arg2, ...)
		fun(arg2, arg1, ...)
mag = reverse_args
m = reverse_args
extractm = function(x, df) extract(df, x)
partialm = function(fun, x) partial(x, fun)
grepm = reverse_args(grep)
grepv = partial(grepm, value = T)
greplm = function(x, pattern, ...) {
	grepl(pattern, x, ...)
}
vgrep = function(x, patterns, ...) {
	x %>% 
		grepm( patterns %>% paste(collapse="|"), invert = T, ...) %>%
		unique
}
vgrepv = partial(vgrep, value = T)
subm = function(x, pattern, replacement, ...) {
	sub(pattern, replacement, x, ...)
}
gsubm = function(x, pattern, replacement, ...) {
	gsub(pattern, replacement, x, ...)
}

pre0 = function(x, y) paste0(y, x)
"%+%" = function(...) paste0(...,sep="")

sample_dataframe = function(dt, n = 100) dt[ sample(nrow(dt), size = n), ]
sample_datatable = function(dt, n = 100) dt[ sample(nrow(dt), size = n) ]
sample_with_replace = function(v, n = 100) {
	if (length(v) == 1)
		return(rep(v, n))
	sample(v, size = n, replace = T)
}
sampler = sample_with_replace

form_name = function( urls, ext = T) {
	file_name_pattern = '\\d+/[^/]*$'
	result = sub( "/", "-", str_extract(urls, file_name_pattern) )
	if ( ext ) {
		return(result)
	} else {
		return( sub( "\\.\\w*$", "", result ) )
	}
}
.url2fn = function(url) form_name(url, ext=F)
url2fn = null_fun(.url2fn)
.url = function(filename) {
	pattern = "^(\\d+)-([^.]*)$"
	m = str_match(filename, pattern)
	cik = m[, 2]
	filing = m[, 3]
	sprintf("http://www.sec.gov/Archives/edgar/data/%s/%s.txt", cik, filing)
}
fn2url = null_fun(.url)
.cik = function(filename) {
	str_match(filename, "^(\\d+)-([^.]*)$")[, 2] %>%
		as.numeric %>%
		sprintm("%010d")
}
fn2cik = null_fun(.cik)
interactive_xbrl = function(filename) {
	pattern = "^(\\d+)-([^.]*)$"
	m = str_match(filename, pattern)
	cik = m[, 2]
	filing = m[, 3]
	sprintf("https://www.sec.gov/cgi-bin/viewer?action=view&cik=%s&accession_number=%s&xbrl_type=v", cik, filing)
}


partial_year_quarter = function(fun) {
	partial(fun, year = 2009:year(Sys.Date()), quarter = 1:4)
}

sprintf1 = function(fmt, param) sprintf(fmt, param)
sprintf_year_quarter = function(fmt, year, quarter) {
	df = CJ(as.character(year), as.character(quarter))
	sprintf(fmt, df$V1, df$V2)
}

build_periods2 = partial( sprintf_year_quarter, fmt = "%s/QTR%s" )

# output: "2013/QTR1" "2013/QTR2" "2013/QTR3" "2013/QTR4" "2014/QTR1" "2014/QTR2" "2014/QTR3" "2014/QTR4"
build_periods = function( from = 2003, to = 2015) { build_periods2(from:to, 1:4) }

build_urls2 = function(year, quarter) {
	periods = build_periods2(year, quarter)
	build.urls(periods)
}

build.urls = function(periods) {
	paste("http://www.sec.gov/Archives/edgar/full-index/", periods, "/company.zip", sep='')
}

count_isna = function(x) { sum(is.na(x)) }
count_unique = function(x) { length(unique(x)) }
cu = count_unique
ci = count_isna

# note: is_na is not usable inside dplyr
is_na = function(x) is.na(x) | all(ifelse( class(x) == "character", x == "NA", FALSE))
not.na = . %>% is.na %>% `!`
not_na = . %>% is_na %>% `!`

is_null = function(v) {
	n = length(v)
	r = rep(T,n)
	for (i in 1:n) {
		x = v[i]
	  if (is.list(x)) x = unlist(x)
		r[i] = is.null(x)
	}
	return(r)
}
not_null = . %>% is_null %>% `!`
is.blank <- function(x, false.triggers=FALSE){
    if(is.function(x)) return(FALSE) # Some of the tests below trigger
                                     # warnings when used on functions
	 if (is.list(x)) x = unlist(x)
    return(
        is.null(x) ||                # Actually this line is unnecessary since
        length(x) == 0 ||            # length(NULL) = 0, but I like to be clear
        all(is.na(x)) ||
        all(x=="") ||
        (false.triggers && all(!x))
    )
}
not_blank = . %>% is.blank %>% `!`

is_empty = function(x) {
	if (is.data.frame(x)) return(nrow(x) == 0)
	if (is.vector(x)) return(length(x) == 0)
	if (is.list(x)) return(length(x) == 0)

	stop("it should be a data.frame or vector or list")
}
not_empty = . %>% is_empty %>% `!`

# Compare vectors while taking NA as value
# http://www.cookbook-r.com/Manipulating_data/Comparing_vectors_or_factors_with_NA/
compareNA <- function(v1,v2) {
    # This function returns TRUE wherever elements are the same, including NA's,
    # and false everywhere else.
    same <- (v1 == v2)  |  (is.na(v1) & is.na(v2))
    same[is.na(same)] <- FALSE
    return(same)
}

# sanitize empty vectors of character() after parsing data
san = function(char) {
	if(length(char)==0) '' else char
}

basename_noext = function(filenames) basename( str_replace(filenames, "\\.\\w+$", "" ) )
ltrim_char = function(ch, x) sub( paste0("^\\", ch, "*"),"",x)
rtrim_char = function(ch, x) sub( paste0("\\",ch,"*$"),"",x)
trim_char = function(x, ch) x %>% triml(ch) %>% trimr(ch)
trim.beforecolon = function(x) sub("^.*:\\s+","",x)
trim = function(x) str_trim(x, side="both")
trimQuotes <- function (x) gsub("^'(.*)'$", "\\1", x)

months.before = function(m) {
	d = m*30
	Sys.Date() - days(d)
}

re = function(regex, x) {
	m = regexpr(regex,x,perl=T)
	regmatches(url,m)
}

convert.company.class.logicals.to.character = function( filing ) {
	result = c()
	if (filing$us_public) result = c(result, 'us_public')
	if (filing$us_ipo) result = c(result, 'us_ipo')
	if (filing$us_funded) result = c(result, 'us_funded')
	if (filing$us_delisted) result = c(result, 'us_delisted')
	if (filing$sec_delinquent) result = c(result, 'sec_delinquent')
	if (filing$foreign_sec_ipo) result = c(result, 'foreign_sec_ipo')
	if (filing$foreign_sec_filer) result = c(result, 'foreign_sec_filer')
	if (filing$foreign_sec_filer_delisted) result = c(result, 'foreign_sec_filer_delisted')
	if (filing$canadian_sec) result = c(result, 'canadian_sec')
	if (filing$canadian_delisted) result = c(result, 'canadian_delisted')
	if (filing$adr) result = c(result, 'adr')
	result
}

init.data.frame = function( df, rows ) {
	df = if( length(df) == 0 ) rows else rbind(df, rows)
}


log = function( ... ) {
	print( paste0( ... ) )
}

config_strategies_for_company_exchange_parsing_from_sec = function() {
	strategies = fread('config/strategies_for_company_exchange_parsing_from_sec.csv', header=T, stringsAsFactors=F)
	setkey(strategies, order_no)
}

config_filenames_real = function() { fread('config/filenames.csv', header=T, stringsAsFactors=F) }
config_filenames = config_filenames_real
config_colclasses = function() { fromJSON(file='config/colclasses.json') }
config_xbrl_tags = function() { fromJSON(file='config/xbrl_tags.json') }

.path_array_fun = function(fun_get_filename)
	function(file) 
		function(arg)
			sprintf(fun_get_filename(file), arg)
path_array_fun = .path_array_fun(get_filename)
path_array_json_fun = .path_array_fun(get_filename_json)
.read_array_fun = function(fun_path_array, fun_read_file)
	function(file)
		function(arg, ...)
			read_file_with_path(fun_path_array(file)(arg), file, .read_file_fun = fun_read_file, ...) 

.get_directory = function(name_in, test = F, data_root = '') {
	test_in = test
	filename= config_filenames()[name==name_in & test==test_in]$filename
	paste0(data_root, filename)
}                
get_directory_real = partial(.get_directory, data_root = '')
get_directory_test = partial(.get_directory, data_root = 'data/test/')
get_directory = get_directory_real

.get_filename = function(name_in, from = '', ext = '', test = F, file_ext = 'csv', data_root = '') {
	get_filename_from = function(filename, from, ext, file_ext = 'csv'){
		file_ext = ifelse( is.blank(file_ext), '', paste0('.', file_ext) )
		filename = paste0( filename, from, file_ext )
		sub( paste0('\\.', file_ext), paste0( ext, '\\.', file_ext ), filename)
	}
	test_in = test
	filename= config_filenames()[name==name_in & test==test_in]$filename
	filename = paste0(data_root, filename)
	get_filename_from(filename, from, ext, file_ext = file_ext)
}
get_filename_real = partial(.get_filename, data_root = '')
get_filename_test = partial(.get_filename, data_root = "data/test/")
get_filename = get_filename_real
get_filename_array = path_array_fun(file)
get_filename_json = partial(get_filename, file_ext = 'json')

.read.dt = function(file.name, cols = NA, ...) {
	if (is.blank(cols)) {
		cols = NA
	}
	dt = data.table( read.csv(file.name, header=T, stringsAsFactors=F, colClasses=cols, ...) )
}
.read.fread = function(file.name, cols = NULL, ...) {
	dt = fread(file.name, colClasses = cols, ...)
}
.readLines = function(filename, ...) {
	readLines(filename)
}

.read.list.load = function(filename, ...)
	list.load(filename, ...)
.read.json = function(file.name, var_name, read_fun = .read.list.load, ...){
	read_fun(file.name, ...)
}
.read.csv = function(file.name, var_name, read_fun = .read.fread, ...){
	colclasses = config_colclasses()
	cols = NULL
	if (var_name %in% names(colclasses)) {
		cols = colclasses[[var_name]]
	}
	read_fun(file.name, cols, ...)
}
read.file = function(var_name, from = '' , ext = '', test = F, fun_get_filename = get_filename, .read_file_fun = .read.csv, ...) {
	file.name = fun_get_filename(var_name, from, ext, test)
	.read_file_fun(file.name, var_name, ...)
}
read_file_real = partial(read.file, fun_get_filename = get_filename_real)
read_file_with_path = function(file.name, var_name, from = '' , ext = '', test = F, .read_file_fun = .read.csv, ...) {
	.read_file_fun(file.name, var_name, ...)
}

.write.list.save = function(alist, filename, ...) {
	list.save(alist, filename)
}
.write.json = function( alist, file.name, use_table = F, write_fun = .write.list.save, ...) {
	write_fun(alist, file.name, ...)
}
.write.csv = function( df, file.name, use_table = F, ...) {
	dir.create( dirname(file.name), showWarnings = F, recursive = T )
	if (use_table) {
		write.table(df, file.name, sep=",", ...)
	} else {
		write.csv(df, file.name, append=F, row.names=F, ...)
	} 
}           

write.file = function( df, var_name, from = '', test = F, use_table = F, fun_get_filename = get_filename, .write_file_fun = .write.csv, ...) {
	file.name = fun_get_filename(var_name, from, test = test)
	.write_file_fun(df, file.name, use_table, ...)
}
write_file_real = partial(write.file, fun_get_filename = get_filename_real)
write_file_with_path = function( df, file.name, from = '', test = F, use_table = F, .write_file_fun = .write.csv, ...) {
	.write_file_fun(df, file.name, use_table, ...)
}

.read_data = function(data) {
	fun_name = paste0('read_', data, '()')
	fun = as.expression(parse(text=fun_name))
	df = eval(fun)
}

.write_data = function(data, df) {
	fun_name = paste0('write_', data, '(df)')
	fun = as.expression(parse(text=fun_name))
	eval(fun)
}

read_file_fun = function(file, fun_get_filename, fun_read_file)
	function(...) {
		read.file(file, fun_get_filename = fun_get_filename, .read_file_fun = fun_read_file)
	}
write_file_fun = function(file, fun_get_filename, fun_write_file)
	function(df, ...)
		write.file(df, file, fun_get_filename = fun_get_filename, .write_file_fun = fun_write_file, ...)
read_csv_fun = partial( read_file_fun, fun_get_filename = get_filename, fun_read_file = .read.csv )
write_csv_fun = partial( write_file_fun, fun_get_filename = get_filename, fun_write_file = .write.csv)
read_json_fun = partial( read_file_fun, fun_get_filename = get_filename_json, fun_read_file = .read.json)
write_json_fun = partial( write_file_fun, fun_get_filename = get_filename_json, fun_write_file = .write.json)

read_array_fun = .read_array_fun(fun_path_array = path_array_fun, fun_read_file = .read.csv)
read_array_json_fun = .read_array_fun(fun_path_array = path_array_json_fun, fun_read_file = .read.json)

.write_array_fun = function(fun_path_array, fun_write_file)
	function(file)
		function(df, arg, ...)
			write_file_with_path(df, fun_path_array(file)(arg), .write_file_fun = fun_write_file, ...) 
write_array_fun = .write_array_fun(fun_path_array = path_array_fun, fun_write_file = .write.csv)
write_array_json_fun = .write_array_fun(fun_path_array = path_array_json_fun, fun_write_file = .write.json)

write_list_fun = function(file) {
	write_file_fun = write_array_fun(file)
	function(df_l, ...) {
		for (i in seq_along(df_l)) {
			title = names(df_l)[[i]]
			write_file_fun(df_l[[i]], title)
		}
	}
}
# @deprecated
read_json_fun0 = function(file)
	function(...)
		read.file(file, fun_get_filename = partial(get_filename, file_ext = 'json'), .read_file_fun = .read.json, ...)
# @deprecated
write_json_fun0 = function(file)
	function(alist, ...)
		write.file(alist, file, fun_get_filename = partial(get_filename, file_ext = 'json'), .write_file_fun = .write.json, ...)


log.progress = function( current.step, total.steps ) {
	is.multiple = function( current.step, step.size ) {
		remainder = current.step %% step.size
		remainder == 0 
	}
	decide.step.no = function( total.steps ) {
		if ( total.steps > 5000000 ) return( 50000 )
		if ( total.steps > 1000000 ) return( 10000 )
		if ( total.steps > 100000 ) return( 500 )
		if ( total.steps > 10000 ) return( 100 )
		return( 50 )
	}
	step.no = decide.step.no( total.steps )
	step.size = ceiling(total.steps/step.no)
	if ( is.multiple( current.step, step.size ) ) {
		print( paste0( format(Sys.time(), "%H:%M:%S"), " total.steps: ", total.steps, " current.step: ", current.step ))
	}
}

profile.fun = function(from = '', trial = '', write.csv = F, fun, ...) {
	extension = paste0( from, trial )
	fun_name = as.character( substitute(fun) )
	fun_name = gsub("\\.",'_',fun_name,perl=T)
	file_profiler_base = paste0( 'profiler/profiler_', fun_name )
	file_profiler = paste0( file_profiler_base, extension )
	csv_profiler = paste0(file_profiler, '.csv')
	Rprof(NULL)

	Rprof(file_profiler)

	result = fun( ... )

	Rprof(NULL)
	profile = summaryRprof(file_profiler)
	total = profile$by.total
	if ( write.csv ) {
		write.table(total, csv_profiler, sep=',', quote=F)
	}
	return(total)
}

log.error = function() {
}

moveme <- function (invec, movecommand) {
  movecommand <- lapply(strsplit(strsplit(movecommand, ";")[[1]], 
                                 ",|\\s+"), function(x) x[x != ""])
  movelist <- lapply(movecommand, function(x) {
    Where <- x[which(x %in% c("before", "after", "first", 
                              "last")):length(x)]
    ToMove <- setdiff(x, Where)
    list(ToMove, Where)
  })
  myVec <- invec
  for (i in seq_along(movelist)) {
    temp <- setdiff(myVec, movelist[[i]][[1]])
    A <- movelist[[i]][[2]][1]
    if (A %in% c("before", "after")) {
      ba <- movelist[[i]][[2]][2]
      if (A == "before") {
        after <- match(ba, temp) - 1
      }
      else if (A == "after") {
        after <- match(ba, temp)
      }
    }
    else if (A == "first") {
      after <- 0
    }
    else if (A == "last") {
      after <- length(myVec)
    }
    myVec <- append(temp, values = movelist[[i]][[1]], after = after)
  }
  myVec
}

get_url_list = function(filings, target_dir = 'filings/') {
	url_list = filings[ , c('url','cik'), with = F]
	url_list$file_name = paste0( target_dir, url_list$cik, '.txt' )
	url_list = url_list[ , c(1,3,2), with=F]
}

single_zip_header_filings = function() {
	root_arg = sprintf("-r ../%s", dir_filings_root())
	arg = root_arg
	setwd('filings/')
	cmd = sprintf("./zip_filings.sh %s", arg) #@ < txt/formname.txt > zip/formname.zip
	system( cmd )
	setwd('..')
}

dir_cache_temp = function(path = 'temp') return(path)

uncache = function(path_arg = "") {
	path = file.path(dir_cache_temp(), path_arg)
	files = list.files(path = path, full.names = T, include.dirs = F) 
	print( sprintf("uncaching %s files", length(files)) )
	r = llply(files, readRDS)
	unlink(files)
	return(r)
}

cache_fun = function(fun) 
	function(...) 
		fun(...) %>% cache

cache = function(data, path_arg = "") {
	path = file.path(dir_cache_temp(), path_arg)
	saveRDS(data, cache_index(path = path))
}

cache_index = function(path = dir_cache_temp()) {
	dir.create(path, showWarnings = F, recursive=T)
	files = list.files(path = path, full.names = T, include.dirs = F) 
	filename = length(files) + 1
	file.path(path, filename %>% paste0('.rds'))
}

cache_index0 = function(path = dir_cache_temp()) {
	dir.create(path, showWarnings = F)
	if (! exists("counter_cache_index") ) {
		counter_cache_index <<- 0
	}
	counter_cache_index <<- counter_cache_index + 1
	file.path(path, counter_cache_index %>% paste0('.rds'))
}

llplyp = partial(llply, .progress = "text")

llplyc = function(.data, .fun, ...) {
	llplyp(.data, cache_fun(.fun), ...)
	result = uncache()
}

restore_cache = function(path_arg = "") {
	path = file.path(dir_cache_temp(), path_arg)
	files = list.files(path = path, full.names = T, include.dirs = F) 
	print( sprintf("uncaching %s files", length(files)) )
	r = llply(files, readRDS)
	return(r)
}

llplyc_nouncache = function(.data, .fun, ...) {
	llplyp(.data, cache_fun(.fun), ...)
	result = restore_cache()
}                 

setNamesNull = function(object, nm) {
	if ( is.blank(object) ) {
		object
	} else {
		setNames(object, nm) 
	}
}

combine.vectors.by.non.na = function(a, b) {
	a[is.na(a)]  <- b[is.na(a)]
	a
}

log_errors_llplyc = function(.fun) {
	function(filename) {
		return( 
			tryCatch(
				.fun(filename), 
				error = function(e) {
					# @todo
					if (! exists("counter_cache_index") ) {
						counter_cache_index <<- 0
					}
					write(x=counter_cache_index+1,file='error_logs.log',sep="\n", append=T)
				}
			)
		)
	}
}

# compare files in two directories and return difference
setdiff_files_in_dirs = function(dir1, dir2) {
	f1 = list.files(dir1) %>% basename_noext
	f2 = list.files(dir2) %>% basename_noext
	r = setdiff(f1, f2) %>%
		paste0(".txt")
}

zip_clean_txt_filings = function() {
	files = setdiff_files_in_dirs( dir_filings_txt(), dir_filings_zip() )
	llplyp(files, zip_remove_filing_script)
}

zip_remove_filing_script = function(filename) {
	zip_filename_base = filename %>% basename_noext %>% paste0(".zip")
	zip_filename = paste0(dir_filings_zip(), zip_filename_base)
	if (file.exists(zip_filename)) {
		unlink(filename %>% filepath_txt)
		return(filename)
	}

	filename_base = filename %>% basename_noext %>% paste0(".txt")
	arg = sprintf("-r %s -f %s", dir_filings_root(), filename_base)
	cmd = sprintf("./zip_remove_filing.sh %s", arg) #@ < txt/formname.txt > zip/formname.zip
	system( cmd )
	return(filename)
}

# @deprecated
basename_txt = function(filename) {
	filename %>% basename_ext("txt")
}

# @deprecated
filepath_txt = function(filename) {
	filename %>% path_filing("txt")
}

basename_ext = function(filename, ext) 
	filename %>% basename_noext %>% paste0('.', ext)

dir_filings_ext = function(ext)
	switch(ext,
		txt=dir_filings_txt(),
		xml=dir_filings_xbrl(),
		zip=dir_filings_zip(),
		header=dir_filings_header(),
		xbrl=dir_filings_xbrl()
	)

path_filing = function(filename, ext) 
	filename %>% 
		basename_ext(ext) %>%
		pre0(dir_filings_ext(ext))

make_trycatch = function(fun)
	function(x)
		tryCatch(
			fun(x),
			error = function(cond) x
		)

make_try_capture = function(fun)
	function(...) {
		res = try_capture_stack(
			fun(...),
			sys.frame()
		)
		if (is.error(res)) {
			arguments <- list(...)
			params = stringr::str_c("PARAMETERS: ", toString(arguments), "\n\n", collapse = "\n")
			traceback <- stringr::str_c(create_traceback(res$calls), collapse = "\n")
			msg = stringr::str_c("ERROR: ", res$message, "\n\n", traceback, "\n\n", params)
			str_split(msg, '\n')
			return(msg)
		} else {
			return(res)
		}
	}

"%+%" = function(...) paste0(...)


# test
generate_data = function(base_name, end) {
	1:end %>%
		sprintm("%03s") %>%
		pre0(base_name)
}

safe_extract = function(x, i) {
	if (is.blank(x))
		return(x)
	else
		return(x[i])
}

matches_all = function(x, patterns, ...) 
	lapply(patterns, grepl, x, ...) %>%
		lapply(any) %>%
		unlist %>%
		which 

matches_first = function(x, patterns, ...) 
	matches_all(x, patterns, ...) %>>%
		safe_extract(1)

process_cut_filing_fun = function(script) {
	function(filename) {
		filepath = filename %>% paste0(".txt") %>% pre0(dir_filings_header())
		if (file.exists(filepath)) return(filename)
		file_list_arg = sprintf( "-s %s", filename %>% basename_txt )
		root_arg = sprintf("-r %s", dir_filings_root())
		arg = paste0(file_list_arg, ' ', root_arg)
		cmd = sprintf( "./%s.sh %s", script, arg) #@ < txt/formname.txt > header/formname.txt
		system( cmd )
		return(filename)
	}
}

write_incr = function(data, filename, ...) {
	files = list.files(".") %>%
		grepv("^" %+% filename %+% "(\\d+)\\.csv") %>%
		str_match("^" %+% filename %+% "(\\d+)\\.csv") 
	idx = 1 + if (is.blank(files)) {
			0
		} else {
			files %>%
				extract(, 2) %>%
				as.numeric %>%
				sort(decreasing=T) %>%
				extract(1)
		} 
	filepath = filename %+% idx %+% ".csv"
		
	write_csv(data, filepath, ...)
}

list_files_xbrl = function() {
	setwd(dir_filings_xbrl())
	cmd = "find -type f"
	files = system(cmd, intern=T) %>% basename_noext
	setwd("../../..")
	return(files)
}

list_filings = function() {
	setwd(dir_filings_txt())
	cmd = "find -type f"
	files = system(cmd, intern=T) %>% basename_noext
	setwd("../../..")
	setwd(dir_filings_header())
	cmd = "find -type f"
	files = system(cmd, intern=T) %>% basename_noext %>%
		c(files)
	setwd("../../..")
	setwd(dir_filings_zip())
	cmd = "find -type f"
	files = system(cmd, intern=T) %>% basename_noext %>%
		c(files)
	setwd("../../..")
	files = unique(files)
	return(files)
}

get_empty_filings = function() {
	setwd(dir_filings_txt())
	cmd = "find -type f -size -400c"
	empty_files = system(cmd, intern=T) %>% basename_noext
	setwd("../../..")
	setwd(dir_filings_header())
	cmd = "find -type f -size -400c"
	empty_files = system(cmd, intern=T) %>% basename_noext %>%
		c(empty_files)
	setwd("../../..")
	setwd(dir_filings_zip())
	cmd = "find -type f -size -400c"
	empty_files = system(cmd, intern=T) %>% basename_noext %>%
		c(empty_files)
	setwd("../../..")
	return(empty_files)
}

unduplicate = function(df, cols) {
	dup = function(col, df) 
		duplicated(df[[col]])
	dups = lapply(cols, dup, df) %>%
		c(T)
	dupped = do.call('and', dups)
	df[! dupped]
}

# append (add,concat) a vector to each element of a list
clv = function(alist, vec)
	Map(c, alist, rep(list(vec), length(alist)))
# prepend a vector to each element of a list
cvl = function(vec, alist)
	Map(c, rep(list(vec), length(alist)), alist)

root_xml = function(filename)
	filename %>% path_filing("xml") %>% read_xml

root_xml_fun = function(filename, dir) {
	filepath = filename %>%
		pre0(dir) %>%
		paste0( ".xml" ) %>%
		xmlParse
}

root_xml2 = partial(root_xml_fun, dir = dir_formd_xml())
root_xbrl = partial(root_xml_fun, dir = dir_filings_xbrl())

read_filing = function(filename)
	filename %>% path_filing("txt") %>% readLines

read_xbrl = function(filename)
	filename %>% path_filing("xml") %>% readLines

dl_data_file = function(file) {
	filepath = get_filename(file)
	cmd = sprintf("scp mert@162.243.237.190:/home/mert/SEC_Filings/parser/%s %s", filepath, filepath)
	system(cmd)
}

dl_data_file_raw = function(filepath) {
	cmd = sprintf("scp mert@162.243.237.190:/home/mert/SEC_Filings/parser/%s %s", filepath, filepath)
	system(cmd)
}

.dl_data_file_array = function(fun_path_array) {
	function(file)
		function(arg, ...)
			dl_data_file_raw(fun_path_array(file)(arg))
}
dl_data_file_array = .dl_data_file_array(fun_path_array = path_array_fun)
dl01 = function(file, arg) {
	lapply(arg, dl_data_file_array(file))
}

length_list3 = function(list3) {
	# lengths of elements of each sublist in 3rd order list
	# length_list3(list(list(list(1,2), list(3)), list(3,5)))
	length_list2 = function(list2) {
		lapply(list2, length) %>%
			unlist %>%
			sum
	}
	lapply(list3, length_list2)
}

# remove all na rows
remove_all_na_rows = function(df) filter( df, !applyr(is.na(df), all) )
remove_all_na_columns = function(df) df[ applyc(!is.na(df), any) ]
remove_blank_column_headings = function(df) {
	df[ !unlist(lapply(names(df), is.blank)) ]
	 #unlist(lapply(names(df), !is.blank)) 
	#lapply(names(df), is.blank)
}
remove_all_newlines_inside_cells = function(df) {
	map(df, ~ str_replace_all(.x, '\\r|\\n', '') ) %>%
		as_data_frame
}
escape_all_single_quotes_inside_cells = function(df) {
	map(df, ~ str_replace_all(.x, "'", "''") ) %>%
		as_data_frame
}

str_starts_with <- function(vars, match, ignore.case = TRUE) {
  if (ignore.case) match <- tolower(match)
  n <- nchar(match)

  if (ignore.case) vars <- tolower(vars)
  substr(vars, 1, n) == match
}

str_ends_with <- function(vars, match, ignore.case = TRUE) {
  if (ignore.case) match <- tolower(match)
  n <- nchar(match)

  if (ignore.case) vars <- tolower(vars)
  length <- nchar(vars)

  substr(vars, pmax(1, length - n + 1), length) == match
}

make_numeric = function(df) {
	cols = names(df)
	cols_to_select = str_ends_with(cols, '_id') | cols == 'id'
	cols = cols[cols_to_select] 
	for (i in seq_along(cols)) {
		df[[ cols[i] ]] = df[[ cols[i] ]] %>% as.numeric
	}
	return(df)
}

read_excel2 = function( path, table, with_invalid = F, ...) {
	# exceptional case: all columns na
	df = read_excel(path, table, ...) 
	is_any_column_exists = applyc(!is.na(df), any) %>% any
	if(!is_any_column_exists) return(remove_all_na_rows(df))

	# normal case
	df = df %>%
		remove_all_na_columns %>% 
		remove_blank_column_headings %>%
		remove_all_na_rows %>%
		remove_all_newlines_inside_cells %>%
		escape_all_single_quotes_inside_cells %>%
		make_numeric
	if (none(names(df) == "invalid"))
		return(df)
	if ( with_invalid ) {
		return(df)
	}
	else {
		return( 
			df %>%
				filter( is_na(invalid) | invalid == 0 ) %>%
				select( -invalid )
		) 
	}
}

recreate <- function(DF) {
	# http://stackoverflow.com/questions/36198485/how-to-get-the-code-to-recreate-a-dataframe-in-r
  res <- textConnection("foo", "w")

  dput(lapply(DF, 
              function(x) if(is.factor(x)) as.character(x) else x), 
       control = c("keepNA", "keepInteger"), file = res)

  close(res)

  foo <- sub("list", "data.frame", foo, fixed = TRUE)

  parse(text = paste(foo, collapse = "\n"))[[1]]
}

filter_na = function(df, column) {
	df[is.na(df[[column]]), ]
}
filter_nonna = function(df, column) {
	df[!is.na(df[[column]]), ]
}
all_nonna = function(v) { ci(v) == 0 }
none = function(v) { !any(v) }
all_unique = function(v) { duplicated(v) %>% sum == 0 }

filter_nonna = function(df, column) {
	df[!is.na(df[[column]]), ]
}

ifempty_na = function(x) {
	if ( is_empty(x) ) {
		return(NA)
	} else {
		return(x)
	}
}

unduplicate_rows = function(df, columns) {
	# multiple columns
	# usage: 
	# columns = c("window_id", "window_name")
	# wnd %>%
	# 	unduplicate_rows(columns)
	.dots <- lapply(columns, as.symbol)
	df %>%
		group_by_(.dots = .dots) %>%
		filter( n() == 1 )
}

duplicated_rows_ = function(df, columns) {
	# multiple columns
	# usage: 
	# columns = c("window_id", "window_name")
	# wnd %>%
	# 	duplicated_rows_(columns)
	.dots <- lapply(columns, as.symbol)
	df %>%
		group_by_(.dots = .dots) %>%
		filter( n() > 1 ) %>%
		arrange_(.dots = .dots)
}

duplicated_values = function(df, column) {
	v = filter_nonna(df, column)[[column]]
	v[ duplicated(v) ] %>% unique
}

duplicated_rows = function(df, column) {
	# only for one column
	df %>%
		group_by_(column) %>%
		filter( n() > 1 )
	# if multiple columns use:
	#dup_awdf = awdf %>%
		#group_by(awindow_id, data_field_id) %>%
		#filter( n() > 1 )
}

delete_sql_template = function( table_name, columns ) {
	# example output
	# [1] "DELETE FROM T_Kesim_Noktasi WHERE id = %s;"
	template = "DELETE FROM %s WHERE id = %%s;"
	result = sprintf( template, table_name )
	return(result)
}

update_sql_template = function( table_name, columns ) {
	# usage:
	# template = update_sql_template( "t_c_bonitet_bali", names(cbb) )
	# example output:
	# [1] "UPDATE T_Kesim_Noktasi SET sorlasma_enum_id = %s, eroziya_enum_id = %s WHERE id = %(id)s;"
	#template = "UPDATE %s SET %s WHERE id = %%(id)s;"
	template = "    UPDATE %s SET %s WHERE id = %%s;"
	columns = grep( "\\bid\\b", columns, value = T, invert = T )
	set_column_clause = sprintf("%s = '%%s'", columns) %>% 
		paste(collapse=", ")
	result = sprintf( template, table_name, set_column_clause )
	return(result)
}

insert_sql_template = function( table_name, columns ) {
	# usage:
	# template = insert_sql_template( "t_c_bonitet_bali", names(cbb) )
	# example output:
	# [1] "INSERT INTO T_Kesim_Noktasi (id,eroziya_enum_id,qranulometrik_terkib_enum_id,soraketlesme_derecesi_enum_id,sorlasma_enum_id) VALUES (%s,%s,%s,%s,%s);"
	template = "    INSERT INTO %s (%s) VALUES (%s);"
	column_names = columns %>% paste(collapse=",")
	data_placeholders = rep( "'%s'", length(columns ) ) %>% paste(collapse=",") 
	result = sprintf( template, table_name, column_names, data_placeholders )
	return(result)
}

update_sql = function( data, template ) {
	# usage:
	# sql = update_sql( test_data, template$update_template )

	if ("id" %in% names(data)) {
		data = select( data, -id, everything(), id )
	} 
	arg = c( list(template), as.list(data) )
	sql = do.call( 'sprintf', arg ) %>%
		str_replace_all( "enum_id\\b", "enum" ) %>%
		str_replace_all( "'\\bNA\\b'", "null" ) 
}

insert_sql = function( df, template ) {
	# usage:
	# sql = insert_sql( test_data, template$insert_template )
	arg = c( list(template), as.list(df) )
	sql = do.call( 'sprintf', arg ) %>%
		str_replace_all( "enum_id\\b", "enum" ) %>%
		str_replace_all( "'NA'", "null" )
}

delete_sql = function( df, template ) {
	arg = c( list(template), as.list(df) )
	sql = do.call( 'sprintf', arg ) 
}

dbReadTibble = function(db, table_name) {
	dbReadTable(db, table_name) %>%
		as_tibble
}

`%format%` <- function(fmt, list) {
    pat <- "%\\(([^)]*)\\)"
    fmt2 <- gsub(pat, "%", fmt)
    list2 <- list[strapplyc(fmt, pat)[[1]]]
    do.call("sprintf", c(fmt2, list2))
}

df2sql_delete = function(df, table_name) {
	template = build_delete_template_df( table_name, df )
	delete_sql( df, template$delete_template )
}

df2sql_update = function(df, table_name) {
	template = build_update_template_df( table_name, df )
	update_sql( df, template$update_template )
}

df2sql_insert = function(df, table_name) {
	template_df = build_insert_template_df( table_name, df )
	template = template_df$insert_template
	insert_sql( df, template )
}

to_title = partial( stri_trans_totitle, locale = "tr_TR" ) 
to_lower = partial( stri_trans_tolower, locale = "tr_TR" ) 
to_upper = partial( stri_trans_toupper, locale = "tr_TR" ) 

transliterate_tr_to_ascii = function( lines ) {
	lines %>%
		str_replace_all(c("ü"="u", "ö"="o", "ı"="i", "Ü"="U", "Ö"="O", "İ"="I", "ş"="s", "ğ"="g", "ç"="c", "Ş"="S", "Ğ"="G", "Ç"="C", "ə"="e", "Ə"="E"))
}

select_columns = function(df, columns) {
	select_(df, .dots = columns)
}

rename_id_column = function(df, id_col) {
	rename_( df, .dots = setNames( id_col, "id" ) )
}

toUnderscore <- function(x) {
	x %>%		
		str_replace_all("([A-Za-z])([A-Z])([a-z])", "\\1_\\2\\3" ) %>%
		str_replace_all("[. ]", "_") %>%
		str_replace_all("([a-z])([A-Z])", "\\1_\\2") %>%
		tolower()
}

underscore2camel <- function(x) {
	gsub("_(.)", "\\U\\1", x, perl = TRUE)
}

split_df_by = function(df, column) {
	values = df[[column]] %>% unique
	lapply( values, function( value, df, column) {
		.dots = list( interp( ~y == x ,
			.values = list( y = as.name(column), x = value ) ))
		filter_( df, .dots = .dots )
	}, df, column ) %>%
		setNames( values )
}

export_ldf = function( ldf, path_template, ...) {
	# usage:
	# export_ldf( ldf, "../view/enums/EnumValue_split_by_id/%s" )
	ldfapply( ldf, function( df, n, ... ) {
		print(n)
		path = sprintf( "%s.tsv", path_template )
		export( df, sprintf( path, n), ... )
		path = sprintf( "%s.xlsx", path_template )
		export( df, sprintf( path, n), ... )
	}, ...)
}

convert_json_2_df = function(filepath) {
	# converts json to df as key-value pairs independent of the data structure
	# input:
	# {
	# 	"a": {
	# 		"b": "value1",
	# 		"d": {
	# 			"e": "value3",
	# 			"f": "value4",
	# 			"g": {
	# 				"h": "value4",
	# 				"j": "value5"
	# 			}
	# 		}
	# 	}
	# }
	#
	# output:
	#    value    path   key
	#    <chr>   <chr> <chr>
	# 1 value1     a.b     b
	# 2 value3   a.d.e     e
	# 3 value4   a.d.f     f
	# 4 value4 a.d.g.h     h
	# 5 value5 a.d.g.j     j
	d1 = fromJSON( filepath ) %>%
		unlist(1)
	d2 = data_frame( value = d1, path = attr(d1, "names") )
	d2 %>%
		mutate( key = str_split( path, "\\." ) ) %>%
		unnest(key) %>%
		group_by(path) %>%
		filter(row_number()==n())
}

copy_tsv_to_txt = function(fn_folder) {
	src = list.files(fn_folder, recursive = T) %>%
		grepv(".tsv") %>%
		pre0(fn_folder)
	dest = src %>%
		str_replace_all("\\.tsv$", ".txt")
	file.copy(src, dest, recursive = T)
}
 
copy_txt_to_json = function(fn_folder) {
	src = list.files(fn_folder) %>%
		grepv(".txt") %>%
		pre0(fn_folder)
	dest = src %>%
		str_replace_all("\\.txt$", ".json")
	file.copy(src, dest)
}
 
only_if = function(condition) {
	function(func) {
		if (condition) {
			func
		} else {
			function(., ...) .
		}
	}
}

rename_each <- function(df, fun){ 
  # https://github.com/tidyverse/dplyr/issues/2120
  setNames(df, fun(names(df))) 
}

# https://stackoverflow.com/questions/21618423/extract-a-dplyr-tbl-column-as-a-vector
pull <- function(x,y) {x[,if(is.name(substitute(y))) deparse(substitute(y)) else y, drop = FALSE][[1]]}


mutate_looping = function(df, elems, col_name) {
  # > a2
  # # A tibble: 3 × 1
  #      id
  #   <int>
  # 1     5
  # 2     6
  # 3     7
  # > elems
  # [1] 1 2
  # a6 = a2 %>%
  #   mutate_looping(elems, "col")
  #      id   col
  #   <int> <int>
  # 1     5     1
  # 2     5     2
  # 3     6     1
  # 4     6     2
  # 5     7     1
  # 6     7     2
  if (nrow(df) == 0) {
    mutate_(df, .dots = setNames("NA", col_name))
  } else {
    df %>%
      slice( rep(1:n(), each = length(elems))) %>%
      mutate( temp_column = unlist(replicate(n()/length(elems), elems, simplify = F)) ) %>%
      rename_( .dots = setNames("temp_column", col_name))
  }
}

is.true <- function(x) {
  # https://stackoverflow.com/questions/16822426/r-dealing-with-true-false-na-and-nan
  # a = c(T,F,F,NA,F,T,NA,F,T)
  # is.true(a)
  # [1]  TRUE FALSE FALSE FALSE FALSE  TRUE FALSE FALSE  TRUE
  !is.na(x) & x

  # but this still doesn't handle empty set vectors
  # is.true(character(0) == "a")
  # logical(0)
  # use identical for them
  # identical(character(0), "a")
  # F
  # identical("a", "a")
  # T
}

remove_dup_by_fuzzy = function(group, cols) {
  rl = lapply( group[[cols]], function(text, group) { 
    agrep(text, group[[cols]]) %>%
      head(1) 
  }, group)
  rl[sapply(rl, is.blank)] <- NA
  group$temp_id = unlist(rl)
  g2 = group %>%
    group_by(temp_id) %>%
    filter(n() == 1)
}


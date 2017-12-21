---
title: R Study Notes & Refcard
file_path: <url:file:///~/projects/study/study_r.md>
url: http://mertnuhoglu.com/tech/study_r/
_ id=r_lastid sr_0007
---

ref - input

    ~/Dropbox/mynotes/ref.otl

## unexpected bugs

    when running in aws/linux docker always set encoding first
      Sys.setlocale("LC_CTYPE", "en_US.UTF-8")
      https://stackoverflow.com/questions/25808595/issues-with-encoding-when-using-an-rscript-through-launchd

## refcard with examples

    install update upgrade
      devtools::install_github("AndreaCirilloAC/updateR")
      library(updateR)
      updateR(admin_password = '<pass>') 
    run R from console
      oneliner
        R -e 'rmarkdown::render("data_generation.Rmd", "html_document")'
      run script
        R CMD BATCH file.R
          # output to standard out
        Rscript file.R
          # output in a file: file.Rout
      shebang
        #!/usr/bin/env Rscript
    conventions
      iterative
        export(.., na = "")
          always export, write csv files where na = ""
          because java DataFrame doesn't read rows with "NA" when the columns are numeric
    ggplot
      library("ggplot2")
      equivalent scatter plots
        plot(mtcars$wt, mtcars$mpg)
        qplot(mtcars$wt, mtcars$mpg)
        qplot(wt, mpg, data = mtcars)
    recreate - reproducing dataframes - unserializing
      recreate(df)
      ancak şunu eklemeyi unutma: stringsAsFactors = F
    basics
      option
        > options(mert_test = "selam")
        > getOption("mert_test")
        [1] "selam"
      environment variables
        > Sys.getenv("PATH")
        [1] "/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/bin:/opt/X11/bin:/Library/TeX/texbin"
        > Sys.setenv(mert_test = "selam")
        > Sys.getenv("mert_test")
        [1] "selam"
      vector
        x=c(1,2,4,8,16 )               #create a data vector with specified elements
        y=c(1:10)                 #create a data vector with elements 1-10
        vect=c(x,y)               #combine them into one vector of length 2n
      date
        why multiple date classes
          as.Date: simplest. without times
          chron: handles dates and times but not time zones
          POSIXct, POSIXlt: dates and times with time zones
          POSIXlt: stores a list of day, month, year ...
          POSIXct: stores seconds since unix epoch
          strptime: converts char to POSIXlt
          as.POSIXlt: converts some to POSIXlt 
            if char arg: expects ISO8601 standard format: "2017-12-30"
          as.POSIXct: converts some to POSIXlt
          use simplest possible
        POSIX.ct ve POSIXlt
          as.POSIXct("2015-01-01")
          as.POSIXct(df02$validFrom, format = "%d.%m.%Y")
        char to POSIX.ct
          strptime("20160115", "%Y%m%d")
        as.Date
          as.Date('1915-6-16')
          as.Date('20170517', format = "%Y%d%m")
        format
          format(Sys.time(), "%y%m%d%H%M")
        ?strftime
        ?strptime
          z <- strptime("20/2/06 11:16:16.683", "%d/%m/%y %H:%M:%OS")
          strptime("20160115", "%Y%m%d")
          # [1] "2016-01-15 AST"
        extract year, mon out of date
          as.numeric(format(date1, "%m"))
        convert char to date
          as.Date( '2012-05-12' )
          as.Date('20140408',"%Y%m%d")
        lubridate
          month(date1)
          year(date1)
        current date
          Sys.time()
          Sys.Date()
        convert string to time
          t
          # [1] "1505" "1825" "1156" "1925" "1055" "1850"
          t %>%
            strptime( format = "%H%M" ) %>%
            strftime( "%H" )
          # [1] "15" "18" "11" "19" "10" "18"
          t %>%
            strptime( format = "%H%M" ) 
          # [1] "2015-09-15 15:05:00 EEST" "2015-09-15 18:25:00 EEST" "2015-09-15 11:56:00 EEST" 
        sequence of dates
          ## first days of years
          seq(as.Date("1910/1/1"), as.Date("1999/1/1"), "years")
          seq(from = as.Date("1910/1/1"), to = as.Date("1999/1/1"), "day")
          seq(from = as.Date("1910/1/1"), by = "day", length.out = 30)
        difference of time
          ex
            d1 = Sys.Date()
            d2 = as.Date("2017-03-04")
            difftime( d1, d2, units = "days")
          ex
            a = strptime("20160115", "%Y%m%d")
            b = strptime("20160119", "%Y%m%d")
            difftime(a, b, units = "days") 
            # Time difference of -4 days
            difftime(a, b, units = "days") %>% as.double
            # -4
          ex
            mutate( gecikme_gun = 0 - as.double(difftime( strptime(termin_tarihi, "%Y%m%d"), strptime(kesim_tarihi, "%Y%m%d"), units = "days")) )
          t0 = strptime("0000", format = "%H%M")
          difftime(today(), t0)
          difftime(now(), t0)
          difftime(t2, t0)
          note: give units = "hours" to difftime to make it reproducible
        increment date by period
          d1 = strptime("20170512", "%Y%m%d")
          d2 = d1 + days(1)
          format(d2, "%Y%m%d")
        excel date as number to posixct date
          opt1: 
            readxl::read_excel(path = sevk_emri_file, col_types = c("text", "date", "text", "text", "text", "text", "text", "text", "text", "numeric", "text", "text", "text", "text"))
          opt2:
            as.POSIXct(sem$shipment_date * (60*60*24) , origin="1899-12-30" , tz="GMT")
      Operator precedence
        x = text[data_starts_at+1:length(text)]
        -->
        x = text[(data_starts_at+1):length(text)]
      Loop
        vector/list
          for (e in mylist) {...}
        data frame/table
          for (i in 1:nrow(df)) {
            df[i,]
            dt[i]
          # wrong: for (row in df/dt)
      set operations
        union(x, y)
        intersect(x, y)
        setdiff(x, y)
        setequal(x, y)
        is.element(el, set)
    control
      if (cond) expr1 else expr2
      for (var in seq) expr
      while (cond) expr
      ifelse(cond, yes, no)
    debug
      debug(fun); setBreakpoint('script.R#5')
      debug(get_olasi_kombin);
        bu durumda bu fonksiyonun ilk satırında breakpoint konulmuş olur
      browser()
    exception handling - trycatch
      non-interactive
        opt2: quit if error and print traceback
          options( error=function() { traceback(2); if(!interactive()) quit("no", status = 1, runLast = FALSE) } )
          pros: 
            quits always even in assert_that errors
            prints tracebacks
        opt1: quit if error
          code
            tryCatch({
              prepare_csv_for_sql()
              verify_no_dup_actual()
              verify_organization_data()
            }, error = function(e) {
              print("err3")
              quit(status=10)
            })
          cons: doesn't print traceback in assert_that errors
      opt1: make_try_capture to capture traceback message
        make_try_capture(read_excel2)(path, sheet)
        # return value of make_try_capture
        make_try_capture(read_excel2)(path, sheets[1])
        # how to use in lapply
        dfl2 = sheets[1:2] %>>%
          lapply( function(sheet) make_try_capture(read_excel2)(path, sheet)  ) %>%
          setNames( sheets[1:2] )
        # how to use lapply normally?
        dfl3 = sheets[1:2] %>>%
          lapply( function(sheet) read_excel2(path, sheet) ) %>%
          setNames( sheets[1:2] )
        setequal(dfl3, dfl2)
      make_trycatch = function(fun)
        function(x)
          tryCatch(
            fun(x),
            error = function(cond) x
          )
      try_root_xbrl = make_trycatch(xbrl_tester)
      # alt
      try_root_xbrl = make_trycatch(
        function(x) x %>% root_xbrl %>>% (x ~ NA)
      )
      # testing
      filenames %>% 
        llply(. %>% try_root_xbrl, .progress = "text") %>%
        unlist %>>%
        `[`(is.na(.) %>% `!`)
    non-standard evaluation
      quote
      eval
      library("evaluate")
    reflection-metaprogramming
      str
        no return
      summary
      toString
        convert an object to string
      args
    data structures
      m = cbind(m1, m2) # column bind
      m[4, 2]
      m[3, ] # row
      m[ , 2] # col
      rbind(df1, df2)
    datatable
      assign a column of a subset of rows 
        dt[idx_of_ims]$ilce = ilce
      managing
        tables()
        setkey(dt, pk, name)
        setkeyv(dt, 'pk')
        copy(dt) # new object
        setnames(df, old_names, new_names)    # data.table
      accessing rows
        dt[2] # 2. row
        dt[ c(2,3) ] # 2,3. rows
        df[ , 2:3 ] # in df
        dt['b'] == dt[ key == 'b' ]
        dt[i, j, by=..]
          by: list of functions
        dt[ c(F,T) ] # even rows
      accessing columns
        dt[ , v] == dt[ , 'v', with=F] # returns vector
        dt[ , list(v) ] # returns dt
        dt[ 2:3, sum(v) ] # sum(v) over 2:3
        dt[ , c(1, 'col1'), with=F]
      joins
        setkey(X, key1)
        setkey(Y, key2)
        dt = X[Y, nomatch=0]
          nomatch=NA # default returns NA for non-matches: right outer join
          nomatch=0 # no rows returned for non-matches: inner join
          Y[X] # left outer join
          rbind(Y[X],X[Y]) # full outer join
        cross join
          ft = c('D','D/A')
          c3 = c('1571745','1571673')
          CJ(ft,c3) # cross join
        remove duplicated key value rows
          dt[unique(dt$key), mult = "first"]
      conditional assignment / if true
        a[cik=='1291703']$test = 'ali'
    dataframe
      managing
        colnames(df)
        setNames( 1:3, c("foo", "bar", "baz") )
          > setNames(data.frame(v1=c(1:10), v2=seq(1, 100, length=10)), c("X","Y"))
          X   Y
          1   1   1
          2   2  12
          df = import(path) %>%
            setNames(c("IsYeri","IE_Lot_No","Stok_Kodu","Kalite_Kodu","Siparis","Str","L_Eni","L_Boy","IE_Tarihi","Terrmin_Tarihi","Miktar","sira"))
        names(df) <- tolower(names(df)) 
        unname(obj) # remove names
      access
        df[i,]   # row i 
        df[,j]   # column j
        tail(df, 1)
          Last row
      columns
        cols = names(df) %in% c("q3", "q4")
        df = df[!cols] # remove
        df = df[c(-8, -9)] # remove
        dt[ , -6:-16 ] # by range
        df[cols] # keep
        df$cols = NULL # remove
        df[ , c(2,1,3) ] # change column order
      rows
        remove rows
          df[ -ids, ] # remove by index
          df[ !dups, ] # remove by T/F
          but if ids is integer() above methods won't work
          opt1
            df %>%
               filter(!row_number() %in% drop)
          opt2 
            df[!seq_len(nrow(df)) %in% drop, ]
          opt3
            df[ setdiff(1:nrow(df), drop), ]
      rownames
        rownames(f) <- c()
      subset/query
        df[ df$col == logical ]          
      examples/use cases
        find rows with all NA cells
          d4 = data.frame( x = c(1, NA), y = c(NA, NA))
          applyr = partial(apply, MARGIN = 1)
          r4 = applyr(is.na(d4), all)
          r4 == c(F,T)
        remove rows with all NA values
          df %>%
            filter( !applyr(is.na(.), all) )
        { # loop over all columns of some df
          df3 = data.frame( operation_id = df[['operation_id']] )
          # opt1
          for (i in seq_along(cols)) {
            df3[[cols[i]]] = df[[cols[i]]]
          }
          # opt2
          df4 = df[cols] 
          # opt3
          df6 = lapply(seq_along(cols), function(i) df[[cols[i]]] %>% as.numeric )
          # opt4
          df7 = lapply(cols, function(col) df[[col]] %>% as.numeric ) %>% 
            setNames(cols) %>%
            as.data.frame
          }
        apply for datframe is lapply
          convert list of vectors to dataframe
            df7 = lapply(cols, function(col) df[[col]] %>% as.numeric ) %>% 
              setNames(cols) %>%
              as.data.frame
            map(df, ~ str_replace_all(.x, '\\n', '') ) %>%
              as.data.frame
        study_create_dataframe_with_columns_specified_in_list 
          opt1
            l = list( a = NA, b = NA )
            df = as_data_frame(l)
          opt2
            l2 = setNames( replicate(2,NA, simplify = F), c('a', 'b'))
            df = as_data_frame(l2)
    lapply variants lapply2
      ldfapply: list of dataframe
        # v0: lapply
        lapply( seq_along(ldf), function( i, ldf ) {
            sheet = names(ldf)[i]
            print(sheet)
            text = unlist( ldf[i] )
            writeLines( text, paste0( "../rdm/auto_dm_", sheet, ".md" ) )
          }, ldf
        )
        # v1: ldfapply
        ldfapply( ldf, function( df, n ) {
          print(n)
          text = unlist(df)
          writeLines( text, paste0( "../rdm/auto_dm_", n, ".md" ) )
        })
    split_by into list of df
      lapply( ect$enum_category_id, function( ecid, evl ) {
        filter( evl, enum_category_id == ecid )
      }, evl )
    list
      [    # same class + multiple returns
      [[    # any type + single element
      $    # semantics similar to [[
      ls[ [length(ls)+1] ] = elem # append item
    rep vs replicate 
      a2 = data_frame( id = 5:7 )
      a3 = a2 %>%
        slice( rep(1:n(), each = 2)) %>%
        mutate( col = rep(1:2, each = 3) )
      #      id   col
      #   <int> <int>
      # 1     5     1
      # 2     5     1
      # 3     6     1
      # 4     6     2
      # 5     7     2
      # 6     7     2
      replicate(3, 1:2, simplify=F) %>% unlist
      # [1] 1 2 1 2 1 2
      a4 = a2 %>%
        slice( rep(1:n(), each = 2)) %>%
        mutate( col = unlist(replicate(3, 1:2, simplify = F)) )
      #      id   col
      #   <int> <int>
      # 1     5     1
      # 2     5     2
      # 3     6     1
      # 4     6     2
      # 5     7     1
      # 6     7     2
      elems = 1:2
      a5 = a2 %>%
        slice( rep(1:n(), each = length(elems))) %>%
        mutate( col = unlist(replicate(n()/length(elems), elems, simplify = F)) )
      a6 = a2 %>%
        mutate_looping(elems, "col")
    subset/query
      x %in% y
      v[ v == logical ]
      all(x)
      any(x)
      grep / filter 
        grep(pattern, x, ignore.case = FALSE, perl = FALSE, value = FALSE, fixed = FALSE, useBytes = FALSE, invert = FALSE) 
        grep("[a-z]", letters) # returns indexes
          value=T  # return values
        grepl      # returns logical
        vgrepv
          files = list.files( "data/" ) %>%
            vgrepv( "\\.tsv$" )
      get row index of subset
        row.idx = as.numeric(rownames(rows))
      subset and assign values
        lfs = data.frame( from = c(NA, 'x', 'x', 'y'), to = c('x', 'y', 'p', 'z'), level = NA)
        # from to
        #   NA  x
        #    x  y
        #    x  p
        #    y  z
        lfs[ is.na(lfs$from), ]$level = 1
        # from to level
        # <NA>  x     1
        #    x  y    NA
        #    x  p    NA
        #    y  z    NA
        lfs = lfs %>%
          filter( is.na(from) ) %>%
          mutate( level = 1 ) 
        # from to level
        # <NA>  x     1
    sort
      sort/order difference
        order(symbols)
        [1] 1 2 3   # indexes
        sort(symbols)
        [1] "A"  "AA" "AA^"  # actual values
      dataframe
        df[ order(df$B), ]  
        df[ rev(order(df$B)), ] # reverse order
      data table
        dt[order(x,y))
        dt[order(-rank(x),y))
          no dt$col since dt is an environment
    conversions
      text -> yaml -> list -> dataframe
        study_build_ddl_2_table = function() {
          ddl = readLines("data/delete_sql/hibernate_ddl_create_table.sql")
          out = ddl %>%
            str_replace_all("create table", "") %>%
            str_replace_all("number\\([^)]*\\)[^,]*", "") %>%
            str_replace_all("varchar2?\\([^)]*\\)", "") %>%
            str_replace_all("primary key *\\([^)]*\\)", "") %>%
            str_replace_all(", *\\)$", "") %>%
            str_replace_all(" *\\(", "\t") %>%
            str_replace_all(" *, *", "\t") %>%
            str_trim(side = "both")
          writeLines(out, "data/delete_sql/ddl_out1.txt")
          out2 = out %>%
            str_replace_all("\t(\\w+)", "\n  - \\1") %>%
            str_trim(side = "both") %>%
            str_replace_all("^(\\w+)", "\\1:") %>%
            str_trim(side = "both") 
          writeLines(out2, "data/delete_sql/ddl_out2.yaml")
          yml = yaml.load_file( "data/delete_sql/ddl_out2.yaml")
          extract_columns = function(i, yml) {
            table = yml[i] %>% names
            data_frame( 
              table_name = table,
              column_name = yml[[table]] 
            )
          }
          out3 = lapply( seq_along(yml), extract_columns, yml) %>%
            bind_rows
          writeLines(out3, "data/delete_sql/ddl_out3.tsv" )
        }
      convert list to dataframe / tree to flat
        opt7: purr map_chr
          repos = my_repos("owner", limit = 100)
          toJSON(repos) %>%
            writeLines( "data/repos.json" )
          df = tibble(
            name = repos %>% map_chr("name", .null = NA_character_),
            full_name = repos %>% map_chr("full_name", .null = NA_character_)
          )
        opt6: str_split unnest group_by spread
          str_split then convert to dataframe column  <url:#r=sr_0003>
        opt5: using nest
          mygenes
            Entrez  symbols
            7841    MOGS,CDG2B,CWH41,DER7,GCS1 
          mygenes %>% 
            mutate(symbols=strsplit(as.character(symbols), ",")) %>% 
            unnest(symbols)
                 Entrez symbols
              1    7841    MOGS
              2    7841   CDG2B
              3    7841   CWH41 
        opt1
          https://gist.github.com/aammd/9ae2f5cce9afd799bafb
          https://github.com/krlmlr/kimisc/blob/develop/R/list_to_df.R
            unnamed.list <- replicate(10,rand_mat(),simplify = FALSE) 
            named.list <- unnamed.list %>% set_names(LETTERS[1:10])
            list_to_df <- function(listfordf){
              if(!is.list(named.list)) stop("it should be a list")
            df <- list(list.element = listfordf)
            class(df) <- c("tbl_df", "data.frame")
            attr(df, "row.names") <- .set_row_names(length(listfordf))
            if (!is.null(names(listfordf))) {
              df$name <- names(listfordf)
            }
            df
          }
          rand_mat <- function() {
            Nrow <- sample(2:15,1)
            Ncol <- sample(2:15,1)
            rpois(Nrow*Ncol,20) %>%
              matrix(nrow = Nrow,ncol = Ncol)
          }
          list_to_df(unnamed.list)
        opt2
          http://stackoverflow.com/questions/29265702/r-reorganize-list-into-dataframe-using-dplyr
          l =list()
          l[[1]] = list(member1=c(a=rnorm(1)),member2=matrix(rnorm(3),nrow=3,ncol=1 2016-06-12imnames=list(c(letters[2:4]),c("sample"))))
          l[[2]] = list(member1=c(a=rnorm(1)),member2=matrix(rnorm(3),nrow=3,ncol=1 2016-06-12imnames=list(c(letters[2:4]),c("sample"))))
          l[[3]] = list(member1=c(a=rnorm(1)),member2=matrix(rnorm(3),nrow=3,ncol=1 2016-06-12imnames=list(c(letters[2:4]),c("sample"))))
          lapply(l, `[[`, 2) %>% 
            data.frame %>% 
            add_rownames("key") %>% 
            gather(x, value, -key) %>% 
            select(-x) 
        opt3
          obs1 <- list(x="a", value=123)
          obs2 <- list(x="b", value=27)
          obs3 <- list(x="c", value=99)
          dlist <- list(obs1, obs2, obs3)
          dlist
          opt1: lapply
            dlist %>% lapply(as_data_frame) %>% bind_rows()
            df %>% lapply(as_data_frame) %>% bind_rows()
          opt2: do.call
            as.data.frame(do.call(rbind, dlist), stringsAsFactors = FALSE) 
        opt4: manual lapply per each list
          # study_convert_list_to_dataframe = function() { <url:file:///~/Dropbox (BTG)/TEUIS PROJECT 05-ANALYSIS/working_library/requirements_database/scripts/verify_enums.R#r=g_10023>
      convert factor columns to character
        http://stackoverflow.com/questions/2851015/convert-data-frame-columns-from-factors-to-characters
        opt1: lapply df[]
          df[] = lapply(df, as.character)
        opt2: purr
          bob %>% map_if(is.factor, as.character)
      convert list of vectors to dataframe
        df7 = lapply(cols, function(col) df[[col]] %>% as.numeric ) %>% 
          setNames(cols) %>%
          as.data.frame
    operators
      [ [<- [[ $ [[<- $<-
    input/output
      csv
        dt = fread(file) 
        read.csv(filename, header=T)   
        write.csv(df, file)
        read.csv(text = "..")
          csv = 'id,size
          1,100
          2,150'
          read.csv(text = csv)
      fread arguments
        skip
          skip = "string"
            search "string" start on that line
          skip = 10
            skip first 10 lines
        select = cols # columns to keep
        drop = cols # column names to drop
        fread(url) # read url directly
        fread(string) # read string directly
      readLines writeLines - text
        text = readLines( file )
        writeLines(lines, "names_stats.txt")
        readLines(con <- file("Unicode.txt", encoding = "UCS-2LE"))
      read.csv args
        na.strings = c("foo", "bar") # custom NA labels
        header = T
        sep = ","
        read.csv(file, header = TRUE, sep = ",", quote = "\"",
          dec = ".", fill = TRUE, comment.char = "", ...)
        read.delim(file, header = TRUE, sep = "\t", quote = "\"",
          dec = ".", fill = TRUE, comment.char = "", ...)
      readr
        delimited: read_delim(), read_csv(), read_tsv(), read_csv2().
        fixed width: read_fwf(), read_table().
        lines: read_lines().
        whole file: read_file().
        write_csv()
      excel readxl
        read_excel("my-spreadsheet.xls", sheet = "data")
        link içeren excel dosyaları
          bir excel dosyası başka bir dosyaya link içerdiğinde, "update links" demek gerekiyor
          aksi taktirde eski veriler okunur
      write excel
        opt1
          library(openxlsx)
          write.xlsx( r, "temp3.xlsx" )
          write.xlsx( r, "temp4.xlsx", asTable = T)
          write.xlsx( r, "temp.xlsx", sheetName = "storyboard2", append = T )
          write.xlsx( r, "temp.xlsx", sheetName = "storyboard3", append = T )
          write.xlsx( r, "rdb_mockups.xlsx", sheetName = "storyboard3", append = T )
        opt2
          library(xlsx)
          write.xlsx(report, 'view_open_problems.xlsx', row.names = F)
        opt3     
          ## Lists elements are written to individual worksheets, using list names as sheet names if available
          l <- list("IRIS" = iris, "MTCATS" = mtcars, matrix(runif(1000), ncol = 5))
          write.xlsx(l, "writeList1.xlsx")
      read.table
        read.table(file, header = FALSE, sep = "", quote = "\"'",
          dec = ".", numerals = c("allow.loss", "warn.loss", "no.loss"),
          row.names, col.names, as.is = !stringsAsFactors,
          na.strings = "NA", colClasses = NA, nrows = -1,
          skip = 0, check.names = TRUE, fill = !blank.lines.skip,
          strip.white = FALSE, blank.lines.skip = TRUE,
          comment.char = "#",
          allowEscapes = FALSE, flush = FALSE,
          stringsAsFactors = default.stringsAsFactors(),
          fileEncoding = "", encoding = "unknown", text, skipNul = FALSE)
      openxlsx
        read.xlsx(xlsxFile, sheet = 1, startRow = 1, colNames = TRUE, 
          rowNames = FALSE, detectDates = FALSE, skipEmptyRows = TRUE, 
          rows = NULL, cols = NULL, check.names = FALSE, namedRegion = NULL)
    base
      vignette
        browseVignettes("dplyr")
        vignette("backend", package = "DBI")
      match.arg
        ‘match.arg’ matches ‘arg’ against a table of candidate values as specified by ‘choices’, where ‘NULL’ means to take the first one.
        code
          my_repos <- function(type = c("all", "owner", "public", "private", "member")) {
            type <- match.arg(type)
    file system
      file name from path
        basename("C:/some_dir/a")
        > [1]  "a"
        dirname("C:/some_dir/a")
        >[1] "C:/some_dir"
      dir.create(path = ... ) # mkdir
        dir.create(path = ..., recursive = T) # mkdir -p
      list.files(path = ".", pattern = NULL, all.files = FALSE,
        full.names = FALSE, recursive = FALSE,
        ignore.case = FALSE, include.dirs = FALSE, no.. = FALSE)
      dir(path = ".", pattern = NULL, all.files = FALSE,
        full.names = FALSE, recursive = FALSE,
        ignore.case = FALSE, include.dirs = FALSE, no.. = FALSE)
      list.dirs(path = ".", full.names = TRUE, recursive = TRUE)
      home directory
        setwd("~")
      join/concat paths
        file.path(dir1, dir2)
      file.copy(from, to)
        copy directories
          file.copy("data/verify", get_transaction_dir_v1(file_name), recursive = T)
      file.create(..., showWarnings = TRUE)
      file.exists(...)
      file.remove(...)
      file.rename(from, to)
      file.append(file1, file2)
      file.copy(from, to, overwrite = recursive, recursive = FALSE,
                copy.mode = TRUE, copy.date = FALSE)
      file.symlink(from, to)
      file.link(from, to)
    sequence rep length cut seq
      rep(x, ntimes)
        rep(c(0, 5), times=c(3, 2)) # 0 0 0 5 5 
        rep(c(0, 5), c(3, 2)) # 0 0 0 5 5 
        rep(c(0, 5), each=4) # 0 0 0 0 5 5 5 5
      length(x)
      seq(from, to, by)
      cut(x, n)
      sample(x, size, replace = F)
      replicate(n, expr)
        replicate(5, sample(1:10, 15, replace = T), simplify = F)
          list of 5 vectors with 15 numbers
        simplify=T # dataframe of 15 rows 5 columns
        unlist(..) # 75 numbers
    String
      stringi
        transliterate
        totitle case
          label %>%
            str_replace_all( "_", " " ) %>%
            stri_trans_totitle( locale = "tr_TR" )
      substring
        substring("ahmet", 1, 3)
        substring("ahmet", 1, 3:5)
        remove last n chars
          substr(x, 1, nchar(x) - n)
      string templating
        sprintf
          sprintf("Filings: %d", nrow(hfs) )
          sprintf("Filings: %f", 7.2 )
          out of order
            sprintf("%2$s %1$s", "hello", "world")
        leading zeros
          sprintf("%03s", 1:end)
        escaping percent
          sprintf("%s escape %%that", "ali")
        examples
        sprintf: arguments cannot be recycled to the same length
          problem
            sprintf( "%s/QTR%s", as.character(year), as.character(quarter) )
            year and quarter cannot be recycled
          cross join and using dataframe with sprintf
            df = CJ(year, quarter)
            sprintf("%s,%s",df$V1, df$V2)
        named placeholders
          gsubfn
            library("gsubfn")
            df = data.frame( id = 1:3, eroziya = 5:7 )
            '%(id)s: %(eroziya)d' %format% df
            #[1] "1: 5" "2: 6" "3: 7"
      paste (concat)
        na'leri blank ile replace et
          > na.exclude(c(NA, 3)) %>% as.character
          [1] "3"
        paste("q", 1:5, sep="") # concat +
          [1] "q1" "q2" "q3" "q4" "q5"
        vektör için collapse: # python join
          paste(c("ali","veli"), collapse=",")
            [1] "ali,veli"
        collapse: tek parçaya collapse eder
        sep: concat edilen stringler nasıl ayrılmalı. 
        paste0('converted ', "here")
          [1] "converted here"
      regex
        https://www.regex101.com/ 
          debug regex
        ref
          grep(pattern, x, ignore.case = FALSE, perl = FALSE, value = FALSE, fixed = FALSE, useBytes = FALSE, invert = FALSE)
          grepl(pattern, x, ignore.case = FALSE, perl = FALSE, fixed = FALSE, useBytes = FALSE)
          sub(pattern, replacement, x, ignore.case = FALSE, perl = FALSE, fixed = FALSE, useBytes = FALSE)
          gsub(pattern, replacement, x, ignore.case = FALSE, perl = FALSE, fixed = FALSE, useBytes = FALSE)
          regexpr(pattern, text, ignore.case = FALSE, perl = FALSE, fixed = FALSE, useBytes = FALSE)
          gregexpr(pattern, text, ignore.case = FALSE, perl = FALSE, fixed = FALSE, useBytes = FALSE)
          regexec(pattern, text, ignore.case = FALSE, fixed = FALSE, useBytes = FALSE)
        stringr
          str_replace(string, pattern, replacement) # "string" %s/pattern/repl/
          str_replace(fruits, "[aeiou]", "-")
          str_replace_all(fruits, "[aeiou]", "-")
          str_replace_all("\t(\\w+)", "\n  - \\1") %>%
          multiple patterns
            fruits <- c("one apple", "two pears", "three bananas")
            # If you want to apply multiple patterns and replacements to the same
            # string, pass a named version to pattern.
            str_replace_all(str_c(fruits, collapse = "---"),
            c("one" = 1, "two" = 2, "three" = 3)) 
            # [1] "1 apple---2 pears---3 bananas"
          str_match
            strings = c(" 219 733 8965", "329-293-8753 ", "banana")
            pattern <- "([2-9][0-9]{2})[- .]([0-9]{3})[- .]([0-9]{4})"
            str_extract(strings, pattern)
            m = str_match(strings, pattern)
                   [,1]      [,2] [,3]  [,4]
              [1,] "219 733 8965" "219" "733" "8965"
              [2,] "329-293-8753" "329" "293" "8753"
              [3,] NA      NA NA  NA
            m[1,1] # match 1 group 1
            m[1,2] # match 1 group 2
          str_locate("aaa12xxx", "[0-9]+")
            #      start end
            # [1,]     4   5
          str_extract("aaa12xxx", "[0-9]+")
            # [1] "12"
        lookaround
          lookbehind
            (?<=) positive
            (?<!) positive
          lookahead
            (?=)  positive
            (?!)  negative
        escapes backslashes
          backslashes need to be doubled for regex
          newline: single
            "\n"
            cat("this\nis new")
            # this
            # is new
        character classes
          [:alnum:]
            [:alpha:] [:digit]
          [:blank:]
        examples
          trim whitespace
            # returns string w/o leading whitespace
            trim.leading <- function (x)  sub("^\\s+", "", x)
              # returns string w/o trailing whitespace
            trim.trailing <- function (x) sub("\\s+$", "", x)
              # returns string w/o leading or trailing whitespace
            trim <- function (x) gsub("^\\s+|\\s+$", "", x)
            To use one of these functions on myDummy$country:
            myDummy$country <- trim(myDummy$country)
      character functions
        nchar(x)    
          number of char in x
        substr(x, start, stop)    
          substr(x, 2, 4)
          substr(x, 2, 4) <- "222"
        grep(pattern, x, ignore.case=FALSE, fixed=FALSE)
          fixed=FALSE   regex
          returns matching indices
        sub(pattern, replacement, x, ignore.case=FALSE, fixed=FALSE)
          sub("\\s", ".", "Hello there")
          > Hello.there
        strsplit(x, split)
          strsplit("abc", "")
        paste(..., sep="")
          concatenate strings
          paste("x", 1:3, sep="m")
          > c("xM1", "xm2", "xm3")
          paste( 1:3, collapse = "; " )
          > [1] "1; 2; 3"
        case conversions
          toUnderscore(x)
            convert camel case to underscore separated lower case
          toupper(x)
          tolower(x)
          tocamel(x)
            library("rapportools")
            tocamel("foo.bar")
            ## [1] "fooBar"
            tocamel("foo.bar", upper = TRUE)
            ## [1] "FooBar"
            tocamel(c("foobar", "foo.bar", "camel_case", "a.b.c.d"))
            ## [1] "foobar"    "fooBar"    "camelCase" "aBCD"
          unicode: stri_trans_tolower
            stri_trans_totitle( locale = "tr_TR" )
      rematch
        install_github("MangoTheCat/rematch")
        match
          re_match(text = dates, pattern = iso)
      stringr
        str_trim
          str_trim(string, side = c("both", "left", "right"))
          x %>% 
            str_trim(side = "both")
        str_split
          returns list
          str_split with dplyr: take last element
            df = data_frame( a = c("ali,veli", "can,cin" ) )
            d6 = df %>%
              mutate( b = str_split(a, ",") ) %>%
              unnest(b) %>%
              group_by(a) %>%
              filter(row_number()==n())
          use unlist to convert to vector
            t %>%
            str_split("\\n") %>%
            unlist
          str_split then convert to dataframe column  id=sr_0003
            str_split then convert to dataframe column  <url:#r=sr_0003>
            d4 = ft %>%
              mutate( bn = str_split(sinif_tip_formasiya_adi, "\\(") ) %>%
              unnest(bn) %>%
              group_by( fte_id ) %>%
              mutate( info = row_number() ) %>%
              spread( info, bn ) %>%
              rename( dom_subdom = `1`, bitki_adlari = `2`, other = `3` )
        str_trim( unlist( str_split(goog,',') ) )
          [1] "GOOG"  "GOOGL"
        basic
          str_c
            paste0 like
          str_length
            nchar like
            preserves NA
          str_sub
            substr like
            negative positions
              end: -1
            zero length input
            ex
              hw <- "Hadley Wickham"
              str_sub(hw, 1, 6)
              str_sub(hw, end = 6)
              str_sub(hw, 8, 14)
              str_sub(hw, 8)
              str_sub(hw, c(1, 8), c(6, 14))
              # Negative indices
              str_sub(hw, -1)
              str_sub(hw, -7)
              str_sub(hw, end = -7)
              # Alternatively, you can pass in a two colum matrix, as in the
              # output from str_locate_all
              pos <- str_locate_all(hw, "[aeio]")[[1]]
              str_sub(hw, pos)
              str_sub(hw, pos[, 1], pos[, 2])
              # Vectorisation
              str_sub(hw, seq_len(str_length(hw)))
              str_sub(hw, end = seq_len(str_length(hw)))
              # Replacement form
              x <- "BBCDEF"
              str_sub(x, 1, 1) <- "A"; x
              str_sub(x, -1, -1) <- "K"; x
              str_sub(x, -2, -2) <- "GHIJ"; x
              str_sub(x, 2, -2) <- ""; x 
          str_str<-
            substr<-
          str_dup
            to duplicate chars
          str_trim
          str_pad
            pad extra whitespace
        pattern matching
          detect
            str_detect
              grepl like
          locate
            str_locate
            str_locate_all
            based on: regexpr
          extract
            str_extract
            str_extract_all
          match
            str_match
              capture groups by ()
            return: matrix
              one column for each group
            str_match_all
          replace
            str_replace
            str_replace_all
            based: sub
          split
            str_split_fixed
      unicode
        detect encoding
          s = readLines(paste0(dir, "siparisler.csv"), n = 100) %>% paste(collapse = "\\n")
          if (stri_enc_isutf8(s)) 
        totitle case
          label %>%
            str_replace_all( "_", " " ) %>%
            stri_trans_totitle( locale = "tr_TR" )
      examples
        append new lines
          r = character()
          r = c(r, sprintf("filings that don't have xbrl: %s", length(missing_xbrl)))
    dplyr
      vignettes and tutorials
        http://www.dataschool.io/dplyr-tutorial-for-faster-data-manipulation-in-r/
        https://cran.r-project.org/web/packages/dplyr/vignettes/databases.html
      alias
        extract2 [[
        check magrittr alias
      examples
        http://stackoverflow.com/questions/31358953/in-r-subset-or-dplyrfilter-with-variable-from-vector
        rbind: filter mutate select left_join(original)
          fkd = r_data_field() %>%
            select( 1:2, pk_fk ) %>%
            filter( pk_fk == "FK" ) %>%
            mutate( fk_data_entity_name = ...)
            select( data_field_id, fk_data_entity_name )
          dfl = r_data_field(with_invalid=T) %>%
            select( -fk_data_entity_name ) %>%
            left_join(fkd)
          export(dfl, "data/updates/DataField_updated.tsv")
        generify nse columns with se
          http://www.carlboettiger.info/2015/02/06/fun-standardizing-non-standard-evaluation.html
          https://cran.r-project.org/web/packages/dplyr/vignettes/nse.html
          http://www.r-bloggers.com/dplyr-use-cases-non-interactive-mode/
          mutate
            https://stackoverflow.com/questions/26003574/r-dplyr-mutate-use-dynamic-variable-names
            opt1
              varname <- paste("petal", n , sep=".")
              varval <- lazyeval::interp(~Petal.Width * n, n=n)
              mutate_(df, .dots= setNames(list(varval), varname))
            opt2
              varname <- paste("petal", n, sep=".")
              df<-mutate_(df, .dots=setNames(paste0("Petal.Width*",n), varname))
          rename
            rename with whitespaces
              sip = r_Siparis_v1(file_name, "xlsx") %>%
                rename( 
                  tesis = `İşYeri`,
                  id = `İE Lot No`,
            # opt0
              rename( iris, genus = Species )
            # opt1
              rename_( iris, "new" = "Species" )
            # opt1
              lhs = "new"
              rename_( iris, lhs = "Species" )
            # opt2
              rhs = "Species"
              rename_( iris, .dots = setNames(rhs, "new") )
              # setNames(object = nm, nm)
              # ===>
              # setNames( "new" = x )
          select
            columns = c("enum_value_id", "enum_value_name", "enum_id", "value", "parent_id", "order_no")
            entity = "EnumValue"
            df = r_rdb(entity) %>%
              select_(.dots = columns)
            nse
              df = r_rdb(entity) %>%
                select(enum_value_id, enum_value_name, enum_id, value, parent_id, order_no) 
          filter
            only_if adverb
              https://twitter.com/drob/status/785880369073500161
            # opt0: nse
              filter( iris, Species == "setosa" )
            # opt1: se for value of formula expression
              .dots = list( ~Species == "setosa" )
              filter_( iris, .dots = .dots )
            # opt1 application: treat arguments as variables
              value = "setosa"
              .dots = list( ~Species == value )
              filter_( iris, .dots = .dots )
            # opt2: se for column of formula expression
              library("lazyeval")
              value = "setosa"
              column = "Species"
              .dots = list( interp( ~y == x ,
                                    .values = list( y = as.name(column), x = value ) ))
              filter_( iris, .dots = .dots )
            opt1
              family <- 'Scaridae'
              field <- 'Family'
              .dots <- list(interp(~y == x, 
                .values = list(y = as.name(field), x = family)))
              x3 <- filter_(all_taxa, .dots=.dots)
          group_by
            kullanım: group_by dışı değişkenleri nasıl gösterceğiz?
              1. önce mutate yap
              2. summarise yap
              3. sonra orjinal tabloyla join et
              kmbg = kmb %>%
                select(kombin_id, genislik) %>%
                left_join(ism, by = "kombin_id") %>%
                select(kombin_id, siparis_id, is_emri_id, genislik, bicak_sayisi) %>%
                left_join(sip, by = "siparis_id") %>%
                select(kombin_id, siparis_id, is_emri_id, genislik, bicak_sayisi, en) %>%
                group_by(kombin_id) %>%
                mutate(en_carpi_bicak = en * bicak_sayisi) %>%
                summarise(toplam_en = sum(en_carpi_bicak))
              cmp = kmb %>%
                left_join(kmbg, by = "kombin_id") %>%
                select(kombin_id, genislik, toplam_en) %>%
                mutate(enden_trim = genislik - toplam_en - 30)
            select max value in each group
              http://stackoverflow.com/questions/24237399/how-to-select-the-rows-with-maximum-values-in-each-group-with-dplyr
              https://stackoverflow.com/questions/21308436/dplyr-filter-get-rows-with-minimum-of-variable-but-only-the-first-if-multiple
              df %>% group_by(A,B) %>% slice(which.max(value))
              opt
                filter( rank(enden_trim_m2, ties.method="first") == 1)
            opt
              columns = c("screen_id", "window_id")
              .dots <- lapply(columns, as.symbol)
              d = swn %>%
                group_by_(.dots = .dots) %>%
                filter( n() > 1 )
            study_generify_group_by = function() {
              # opt1: nse default
              dup_wnd = wnd %>%
                group_by(window_id) %>%
                filter( n() > 1 )
              # opt2: se using character vector
                .dots <- lapply(fk_name, as.symbol)
                have_no_children = parent_df %>%
                  inner_join( child_df, by = fk_name ) %>%
                  group_by_( .dots = .dots ) %>%
                  summarise( count = n() ) %>%
                  filter( count == 0 )
              # opt2: se using formula
              .dots = list(~window_id)
              dup_wnd = wnd %>%
                group_by_(.dots = .dots) %>%
                filter( n() > 1 )
              # opt3: treat arguments as variables
              .dots = list(~window_id)
              dup_wnd = wnd %>%
                group_by_(.dots = .dots) %>%
                filter( n() > 1 )
              # opt4: treat key and values as variables
              columns = "window_id"
              .dots = list( interp( ~ y, .values = list(y = as.name(columns) )))
              dup_wnd = wnd %>%
                group_by_(.dots = .dots) %>%
                filter( n() > 1 )
              # opt4.2: multiple keys
              columns = c("window_id", "window_name")
              .dots = list( interp( ~ y, .values = list(y = as.name(columns) )))
              dup_wnd = wnd %>%
                group_by_(.dots = .dots) %>%
                filter( n() > 1 )
              # opt5: encapsulate this into a function
              group_filter_duplicates = function(df, columns) {
                .dots = list( interp( ~ y, .values = list(y = as.name(columns) )))
                df %>%
                  group_by_(.dots = .dots) %>%
                  filter( n() > 1 )
              }
              columns = c("window_id", "window_name")
              wnd %>%
                group_filter_duplicates(columns)
            }
          join
            opt1: setNames
              d1 = data_frame(x = seq(1,20),y = rep(1:10,2),z = rep(1:5,4))
              head(d1)
              d2 = data_frame(xx = seq(1,20),yy = rep(1:10,2),zz = rep(1:2,10))
              join_fn <-function(d_in1,d_in2,var_vec1,var_vec2){
                d_out = d_in1 %>%
                  left_join(d_in2,setNames(var_vec2,var_vec1))
              }
              var_vec1 = c("x","y")
              var_vec2 = c("xx","yy")
              d_join_out = join_fn(d1,d2,var_vec1,var_vec2)
              head(d_join_out)
        incremental row id
          fte02 = import( "inbox/formasiya_tomi_emsallari.xlsx" ) %>%
            remove_all_na_rows %>%
            mutate( fte_id = 1:n() )
        result = by_cik %>%
          summarise_each( funs(last(.)) )
        tbl_df
        join by multiple columns
          inner_join(xcr, by = c("filename", "contextRef"))
        join by different columns
          org3 = org %>%
            left_join( org2, by = c("parent_id" = "organization_id") )
        filtering vector
          cols = c("op_id", "no")
          opt1
            cols[ends_with(cols, '_id')]
          opt2
            cols %>%
              extract( ends_with(., '_id') )
        selecting columns of some df
          opt1
            cols = cols[ends_with(cols, '_id')] 
            df4 = df[cols]
          opt2
            df5 = df %>%
              extract( cols[ends_with(cols, '_id')] )
      verbs
        filter + slice
        arrange
        select + rename
        distinct
        mutate + transmute
        summarise
        sample_n + sample_frac
      case_when     
        ex1
          x <- 1:50
          case_when(
            x %% 35 == 0 ~ "fizz buzz",
            x %% 5 == 0 ~ "fizz",
            x %% 7 == 0 ~ "buzz",
            TRUE ~ as.character(x)
          )
      mutate if
        mutate( type = ifelse(  f$type == f$field, NA, f$type ) )
        opts
          data.table
            id = "field_id" 
            flden = import("data/translation/field_en.xlsx") %>%
              select( one_of("field_id"), ends_with("_en") ) %>%
              as.data.table
            setkey(flden, field_id)
            flden2 = flden[!is.na(field_name_en)]
            fld = read_rdb_field() %>%
              as.data.table
            setkey(fld, field_id)
            assert_that(all( flden2$field_id %in% fld$field_id ) )
            fld2 = fld
            fld2[field_id %in% flden2$field_id]$field_name_en = flden2$field_name_en
            assert_that( setequal( fld[[id]], fld2[[id]] ) )
            export(fld2, "data/translation/field2.tsv")
          dplyr
            id = "field_id" 
            flden = import("data/translation/field_en.xlsx") %>%
              select( one_of("field_id"), ends_with("_en") )
            fld = read_rdb_field()
            fld2 = fld %>%
              left_join( flden, by = id) %>%
              mutate( field_name_en = ifelse( is.na(field_name_en.y), field_name_en.x, field_name_en.y )) %>%
              select( -one_of("field_name_en.y", "field_name_en.x") )
            assert_that( setequal( fld[[id]], fld2[[id]] ) )
            export(fld2, "data/translation/field2.tsv")
      slice     
        row_number'a göre filtreleme yapar
        slice(mtcars, 1L)
        slice(mtcars, n())
        slice(mtcars, 5:n())
        by_cyl <- group_by(mtcars, cyl)
        slice(by_cyl, 1:2)
        # Equivalent code using filter that will also work with databases,
        # but won't be as fast for in-memory data. For many databases, you'll
        # need to supply an explicit variable to use to compute the row number.
        filter(mtcars, row_number() == 1L)
        filter(mtcars, row_number() == n())
        filter(mtcars, between(row_number(), 5, n()))
      filter
        select by position
          slice(flights, 1:10)
        boolean operators explicit
          filter(flights, month == 1 | day == 1)
        filter(flights, month == 1, day == 1)
        babynames %>%
          filter(name %>% substr(1, 3) %>% equals("Ste")) %>%
          group_by(year, sex) %>%
          summarize(total = sum(n)) %>%
          qplot(year, total, color = sex, data = ., geom = "line") %>%
          add(ggtitle('Names starting with "Ste"')) %>%
          print
        filter vector
          files = list.files( "data/" ) %>%
            vgrepv( "\\.tsv$" )
      arrange
        arrange(flights, year, month, day)
        descending order: desc()  
          arrange(flights, desc(year))
        more verbose
          flights[order(flights$year, flights$month, flights$day), ]
      select columns
        select(flights, year, month, day)
        select(flights, year:day)
        select(flights, -(year:day))
        helper functions
          starts_with()
          ends_with
          matches()
          contains()
          ?select
        rename variables using named arguments
          select(flights, tail_num = tailnum) # others are dropped
          rename(flights, tail_num = tailnum)
          rename using string functions
            iris %>% rename_(.dots=setNames(names(.), tolower(gsub("\\.", "_", names(.)))))
        order columns
          select(field_id, data_entity_id:variable_name)
          remaining columns ordering
            http://stackoverflow.com/questions/32040742/dplyrselect-including-all-other-columns-at-end-of-new-data-frame-or-beginni
            all other columns at end
              col <- c("carrier", "tailnum", "year", "month", "day")
              select(flights, one_of(col), everything()) 
            all other at beginning
              select(flights, -one_of(col), one_of(col))
            all dataframe at end
              bind_cols(select(flights, one_of(col)), flights)
            all dataframe at beginning
              bind_cols(flights, select(flights, one_of(col)))
      distinct (unique) rows
        distinct(select(flights, tailnum))
        normalde sadece seçtiğin kolonları tutar
          tüm kolonları tutması için:
            .keep_all = T
      add new columns with mutate()
        mutate(flights, gain = arr_delay - dep_delay)
      summarise values with summarise()
        summarise(flight, delay = mean(dep_delay, na.rm = T))
      sample rows
        sample_n(flights, 10)
        sample_frac(flights, 0.01)
          replace = T
      grouped operations
        arrayization: convert single valued cells into multiple valued cells
          var = r_variable() %>%
            group_by( test_id ) %>%
            summarise( variable_id_list = paste( variable_id, collapse = "," ) ) 
        unarrayization with unnest
          ex2
            http://bioinfoblog.it/2015/02/the-most-useful-r-command-unnest-from-tidyr/comment-page-1/
            d1
              k v
              k1  v1,v2
            ->
            d2
              k v
              k1  v1
              k1  v2
            opt1
              d1 %>%
                mutate( v = str_split(v, ",") ) %>%
                unnest(v)
            opt2
              d1 %>%
                unnest( v = str_split(v, ",") )
          ex
            denp1 = r_data_entity() %>%
              select(data_entity_id, bps_id_list)
            denp2 = r_data_entity() %>%
              mutate(bps_id=str_split(bps_id_list, ",")) %>% 
              unnest(bps_id) %>%
              select(data_entity_id, bps_id)
            #> denp1
               #data_entity_id                    bps_id_list
            #1              86 1,2,3,5,6,7,8,9,10,11,12,13,14
            #2              99                             11
            #> denp2
               #data_entity_id bps_id
            #1              86      1
            #2              86      2
            #3              86      3
        group_by and concat strings by column
          rvw2 = rvw %>%
            left_join( vsc, by = "view_id" ) %>%
            left_join( scw, by = "screen_id" ) %>%
            group_by( view_id ) %>%
            distinct( window_id ) %>%
            summarise( window_id_list = paste( window_id, collapse = "," ) ) %>%
            arrange( view_id )
        filter first row from group_by
          http://stackoverflow.com/questions/31528981/dplyr-select-first-and-last-row-from-grouped-data
          opt1: row_number() == 1
            df %>%
              group_by(id) %>%
              arrange(stopSequence) %>%
              filter(row_number()==1 | row_number()==n())
          opt2: slice(1)
            df %>% arrange(stopSequence) %>% group_by(id) %>% slice(c(1,n()))
        verbs affected by grouping as:
          select() no change
          arrange() orders first by grouping variables
          mutate() and filter() most usefil with window functions (rank() or min(x) == x)
            vignette("window-function")
          sample_n() sample rows in each group
          slice() extract rows within each group
          summarise() explained below
        example
          planes = group_by(flights, tailnum)
          delay = summarise(planes,
            count = n(),
            dist = mean(distance, na.rm = T),
            delay = mean(arr_delay, na.rm = T))
          delay = filter(delay, count > 20, dist < 2000)
        summarise()
          use summarise() with aggregate functions
            that take a vector values, return a single number
            base R: min, max, sum, mean,
            dplyr: n, n_distinct(x), first(x), last(x), nth(x,n)
          ex
            destinations = group_by(flights, dest)
            summarise(destinations, 
              planes = n_distinct(tailnum),
              flights = n()
            )
          when grouping by multiple variables, each summary peels off one level of grouping
            daily = group_by(flights, year, month, day)
            per_day = summarise(daily, flights = n())
            per_month = summarise(per_day, flights = sum(flights))
        grouping without summarising
          flights %>%
            group_by(Dest) %>%
            select(Cancelled) %>%
            table() %>%
            head()
        how does summarise_each work?
          > by_species <- iris %>% group_by(Species)
          > by_species %>% summarise_each(funs(length))
          Source: local data frame [3 x 5]
               Species Sepal.Length Sepal.Width Petal.Length Petal.Width
          1     setosa           50          50           50          50
          2 versicolor           50          50           50          50
          3  virginica           50          50           50          50
          funs(...) applied to each column separately
            > by_species %>% summarise_each(funs(mean), matches("Width"))
            Source: local data frame [3 x 3]
                 Species Sepal.Width Petal.Width
            1     setosa       3.428       0.246
            2 versicolor       2.770       1.326
            3  virginica       2.974       2.026
            > by_species %>% summarise_each(funs(mean), Petal.Width)
            Source: local data frame [3 x 2]
                 Species Petal.Width
            1     setosa       0.246
            2 versicolor       1.326
            3  virginica       2.026
      window functions
        n inputs and n outputs
        ex
          filter(min_rank(desc(DepDelay)) <= 2) %>%
          =
          top_n(2) %>%
        # for each month, calculate the number of flights and the change from the previous month
          flights %>%
            group_by(Month) %>%
            summarise(flight_count = n()) %>%
            mutate(change = flight_count - lag(flight_count))
          # rewrite more simply with tally
            tally() %>%
            mutate(change = n - lag(n))
        # row numbers of each element in each group
          r2 %>%
            group_by( from ) %>%
            mutate( order2 = row_number(order) )
          # from to order order2
          #    a  b     1      1
          #    a  c     1      2
          #    b  d     2      1
          r2 %>%
            group_by( from ) %>%
            mutate( order2 = row_number() )
          # from to order order2
          #    a  b     1      1
          #    a  c     1      2
          #    b  d     2      1
        ranking 
          functions
            row_number
            min_rank
            dense_rank
          difference:
            how to solve ties
          example
            nums = c(1, 1, 2, 3)
            > min_rank(nums)
            [1] 1 1 3 4
            > dense_rank(nums)
            [1] 1 1 2 3
            > row_number(nums)
            [1] 1 2 3 4
          how to handle ties
            min_rank
              normal ranking
            dense_rank
              doesn't skip the places
            row_number
              ignores ties
      utilities
        instead of str()
          glimpse(flights)
      databases - sql
        jdbc - most reliable
          username = "btg_mis"
          password = "..."
          conStr =  "jdbc:oracle:thin:@52.73.23.191:1521:btgdev"
          drv <- JDBC("oracle.jdbc.driver.OracleDriver",
            "other/ojdbc6.jar",
            identifier.quote="`")
          conn = dbConnect(drv, conStr, username, password)
        read table
          dbReadTibble = function(db, table_name) {
            dbReadTable(db, table_name) %>%
              as_tibble
          }
          act_evt_log = function(db) dbReadTibble(db, "ACT_EVT_LOG")
        select query 
          conn = get_db_aws()
          df = dbGetQuery(conn, "SELECT * FROM T_TEST")
        my_db = src_sqlite("my_db.sqlite3")
        tbl(my_db, "hflights")
        tbl(my_db, sql("SELECT * FROM hflights LIMIT 100"))
        sql command?
          %>% explain()
      join
        join key: group_by keys
          delays <- flights %>%
            group_by(dest) %>%
            summarise(arr_delay = mean(arr_delay, na.rm = TRUE), n = n()) %>%
            arrange(desc(arr_delay)) %>%
            inner_join(location)
        multiple join keys
          inner_join(xcr, by = c("filename", "contextRef")) 
        join types
          left_join
          inner_join
          anti_join
            excluded in right
            but not join
          semi_join
            intersection rows
            but not join
          right_join
            reverse of left
          outer_join
            union
        keys to join
          default: all common columns
          join by different columns
            org3 = org %>%
              left_join( org2, by = c("parent_id" = "organization_id") )
          by: explicitly specify
            inner_join(xcr, by = c("filename", "contextRef")) 
            inner_join(mcf, by="filename") %>%
            documentation says but doesn't work
              by = c("a")
              by = c("a" = "b")
      tally
        planes %>% group_by(type) %>% tally()
        simple count() by group
      do()
        if existing verbs don't work, use do()
        similar to dlply()
        slower
        uses pronoun: . to refer to current group
        ex
          df: houseID, year, price
          by_house = df %>% 
            group_by(houseID) 
          by_house %>% do(na.locf(.))
            na.locf: last observation carried forward. replace na with last non-na value
          by_house %>% do(head(., 2))
          by_house %>% do(data.frame(year = .$year[1]))
      database
        create new database
          my_db <- src_sqlite("my_db.sqlite3", create = T)
        # put/insert/copy data to database
          copy_to(hflights_db, as.data.frame(flights), name = "flights", 
            indexes = list(c("date", "hour"), "plane", "dest", "arr"), temporary = FALSE)
        # load data
          weather_db <- hflights_db %>% tbl("weather")
        # work with data as if they are local data frames
          flights_db %>% left_join(planes_db)
        # operations are lazy, until you see the data
        # show sql and show plan
          flights_db %>% filter(n > 10) %>% explain()
        # get all data locally
          hourly_local <- collect(hourly)
      learning sql
        how indices work
          sqlite.org/queryplanner.html
        how select works
          10 easy steps to a complete understanding of sql
      bind_rows
        do.call(rbind, x) # ==
        bind_rows(x)
      aggregate with summarize
        # <url:Dropbox (BTG)/TEUIS PROJECT 05-ANALYSIS/working_library/requirements_database/scripts/prepare_rdb_field_operations.R#p108_rdb_gfield_aggregate_dfield>
        # the result is not useful usually
        fld2 = fld %>%
          left_join(scr, by = "screen_id") %>%
          left_join(dfl, by = "data_entity_id") 
        fld3 = fld2 %>%
          group_by(field_id) %>%
          summarize(list(data_field_name))
        { # testing
          head(fld3)[[2]]
          paste(head(fld3)[[2]], collaps=",")
          toString(unlist(fld3[1,][[2]]))
          fld3[1:3,][[2]]
        }
        dfn = lapply( fld3[1:nrow(fld3),][[2]], toString ) %>% unlist
        fld4 = data_frame(
          field_id = fld3$field_id,
          data_field_name_aggregated = dfn
        )
        fld5 = fld %>%
          left_join(fld4, by = "field_id")
      errors
        # Error: `false` has type 'integer' not 'double'
          sip3 %>%
            mutate( bicak_sayisi = if_else( T, 1, as.integer(1) ))
          if_else'in tüm çıktıları aynı type'ta olmalı. fakat integer ve numeric farklı tipler.
          ex2
            if_else: Error: `false` has type 'logical' not 'double'
              if_else(TRUE, 1, NA)
              #> Error: `false` has type 'logical' not 'double'
              if_else(TRUE, 1, NA_real_)
              #> [1] 1
        error: index out of bounds
          join key mevcut değil
        Error: argument "x" is missing, with no default
          4: lapply(result, . %>% "kombin"[[]]) %>% bind_rows() %>% mutate(kombin_id = row_number()) at optimize_trim_in_one_step.R#70
          sebep: 
            result = list()
    magrittr pipe
      basic
        x %>% f === f(x)
        x %>% f(y) === f(x,y)
        x %>% f %>% g === g(f(x))
      argument placeholder
        x %>% f(y, .) === f(y,x)
      reusing placeholder
        x %>% f(y = nrow(.)) === f(x, y = nrow(x))
        overrule this by enclosing in braces
        x %>% {f(y = nrow(.))} === f(y = nrow(x))
      unary function
        f <- . %>% cos %>% sin # ==
        f <- function(.) sin(cos(.)) 
      create functions (or functional sequences)
        mae <- . %>% abs %>% mean(na.rm = TRUE)
        mae(rnorm(10))
        #> [1] 0.5605
        ex
          n1 = lapply(filenames,
            . %>% nchar )
          n2 = filenames %>% 
            lapply( . %>% nchar )
          n3 = filenames %>% 
            lapply(function(x) nchar(x))
          n4 = filenames %>% 
            lapply(., function(x) nchar(x))
        rules:
          if dot is used, then first arg is not passed automatically
          if dot is used as lambda, then first arg is still passed
        exception
          works
            lapply(x, . %>% {ifelse(is.blank(.),NA,.)} )
          fails
            lapply(x, . %>% ifelse(is.blank(.),NA,.) )
          lesson:
            if using dot as inner arg, then first arg is automatically passed
        ex2: remove na records
          # opt1: works
          df = data.frame( id = c(1, 2, NA) )
          r1 = dplyr::filter( df, !is.na(df$id) )
          # opt2: doesn't work
          r2 = df %>%
            filter( !is.na(.$id) )
          # opt3: works
          r3 = df %>>%
            (dplyr::filter(., !is.na(.$id) ))
        ex3: mutate some columns
          # opt1: works
          df = data.frame( id = c(1, 2, NA) )
          as.character(df$id)
          # opt2: works
          r2 = df %>%
            mutate( id = as.character(id) )
      alias 
        equals add multiply_by
        extract [
          ecd %>% extract("independent_id")
        extract2 [[
        use_series $
          ecd %>% use_series("independent_id")
        ‘extract’                 ‘`[`’
        ‘extract2’                ‘`[[`’
        ‘inset’                   ‘`[<-`’
        ‘inset2’                  ‘`[[<-`’
        ‘use_series’              ‘`$`’
        ‘add’                     ‘`+`’
        ‘subtract’                ‘`-`’
        ‘multiply_by’             ‘`*`’
        ‘raise_to_power’          ‘`^`’
        ‘multiply_by_matrix’      ‘`%*%`’
        ‘divide_by’               ‘`/`’
        ‘divide_by_int’           ‘`%/%`’
        ‘mod’                     ‘`%%`’
        ‘is_in’                   ‘`%in%`’
        ‘and’                     ‘`&`’
        ‘or’                      ‘`|`’
        ‘equals’                  ‘`==`’
        ‘is_greater_than’         ‘`>`’
        ‘is_weakly_greater_than’  ‘`>=`’
        ‘is_less_than’            ‘`<`’
        ‘is_weakly_less_than’     ‘`<=`’
        ‘not’ (‘`n'est pas`’)     ‘`!`’
        ‘set_colnames’            ‘`colnames<-`’
        ‘set_rownames’            ‘`rownames<-`’
        ‘set_names’               ‘`names<-`’
      map function
        equvalent:
          lapply( rownames %>% {. %>% partial( path_array_exchange_listing_x, . )})
          rownames %>% { partial( path_array_exchange_listing_x, . ) }
          rownames %>% partialm(path_array_exchange_listing_x)
      argument placeholder
        x %>% f(y, .) === f(y,x)
      stepwise string-cleaning
        files %<>%
          basename %>%
          str_replace("...", "") %>%
          str_replace("...", "")
      paste
        "this" %>% paste("is not") %>% paste("a pipe")
      https://twitter.com/isthatsol/status/557981863432564739
        foo_foo %>%
          hop_through(forest) %>%
          scoop_up(field_mouse) %>%
          bop_on(head)
      assign and str
        x = x %T>% str
      using operations instead of aliases
        x %>% .[3] %>% `+`(3)
        setnames
          `names<-`
          `colnames<-`
          `rename
      tee: return lhs
        matrix(ncol = 2) %T>%
          plot %>%
          colSums
      exposition of variables
        iris %$% cor(Sepal.Length, Sepal.Width)
      define function on fly
        long_vector %>%
        lapply(
          . %>%
          one_action %>%
          two_action
        )
      lambdas (unary function)
        iris %>% 
          {
            n = sample(1:10, size = 1)
            H = head(., n)
            T = tail(., n)
            rbind(H, T)
          } %>%
      examples
        x[!is.na(x)] # equivalent in pipe where x is any vector.
          x %>% '['(is.na(.) %>% '!')
    piper
      install.packages("pipeR")
      rules of magrittr
        if no dot, then pipe to first arg
        if naked, then pipe to dot
        if dot in expression, then pipe to first arg and dot
        if subexpression, then pipe ?
      rules of piper
        pipe to first argument and to . (dot)
        pipe to . only if followed expression is enclosed within:
          {}
          ()
          (x ~ f(x))
      example 
        f <- function(x, y, z = "nothing") {
          cat("x =", x, "\n")
          cat("y =", y, "\n")
          cat("z =", z, "\n")
        }
        > 1:10 %>% f(1, .-1)
        x = 1 2 3 4 5 6 7 8 9 10
        y = 1
        z = 0 1 2 3 4 5 6 7 8 9
        > 1:10 %>>% f(1, .)
        x = 1 2 3 4 5 6 7 8 9 10
        y = 1
        z = 1 2 3 4 5 6 7 8 9 10>
        > 1:10 %>>% ( f(min(.),max(.)) )
        x = 1
        y = 10
        z = nothing
      lambda expression
        use (x ~ f(x))
        > 1:10 %>>% (x ~ f(min(x), max(x)))
        x = 1
        y = 10
        z = nothing
    pipe examples
      make_na
          filename %>% root_xbrl %>>% (x ~ NA),
          filename %>% root_xbrl %>>% function(x) NA,
      detect filename that causes error
        filenames %>% l_ply(. %T>% print %>% root_xbrl2, .progress = "text")
    functional programming
      currying  
        partial
      generifying a function using functional programming
        write_xbrl_data_x_hd = write_array_fun('xbrl_data_x_hd')
        write_list_xbrl_data_x_hd = function(xbrl_data_list) {
          for (i in seq_along(xbrl_data_list)) {
            title = names(xbrl_data_list)[[i]]
            write_xbrl_data_x_hd(xbrl_data_list[[i]], title)
          }
        }
        ->
        write_list_fun = function(file) {
          write_file_fun = write_array_fun(file)
          function(df_l, arg, ...) {
            for (i in seq_along(df_l)) {
              title = names(df_l)[[i]]
              write_file_fun(df_l[[i]], title)
            }
          }
        }
      problem: how to make a function testable
        change its dependencies without changing its definition
        download_company_idx_files(use_cache = use_cache)
        solution 1: using partials
          definition
            .download_company_idx_files = function(year, quarter, use_cache = F ) {
              file.names = path_array_company_0000_qtr0_zip(year, quarter)
              for (i in 1:length(file.names)) {
                file.name = file.names[i]
                download.file(url, destfile=file.name, method="wget") #@ > company_0000-qtr0.zip
              }
            }
            download_company_idx_files_real = partial(.download_company_idx_files, year = 2009:year(Sys.Date()), quarter = 1:4)
            download_company_idx_files = download_company_idx_files_real 
          test code
            year = '2014'
            quarter = '4'
            download_company_idx_files_test = partial(.download_company_idx_files, year = year, quarter = quarter)
            download_company_idx_files = download_company_idx_files_test
        problem 2: we have lots of similar test functions. how to abstract commonalities?
          example:
            unzip_company_idx_files_test = partial(.unzip_company_idx_files, year = year, quarter = quarter)
            unzip_company_idx_files = unzip_company_idx_files_test
            convert_idx2csv_test = partial(.convert_idx2csv, year = year, quarter = quarter)
        solution 2: using higher order function generator for partial
          make_test_fun = function(fun) {
            function(year, quarter) {
              partial(fun, year = year, quarter = quarter)
            }
          }
          download_company_idx_files_test = make_test_fun(.download_company_idx_files)
          download_company_idx_files = download_company_idx_files_test(year, quarter)
          unzip_company_idx_files_test = make_test_fun(.unzip_company_idx_files)
          unzip_company_idx_files = unzip_company_idx_files_test(year, quarter)
        solution 3: abstract one more step
          make_test_fun = function(fun, year, quarter) {
            partial(fun, year = year, quarter = quarter)
          }
          download_company_idx_files = make_test_fun(.download_company_idx_files, year, quarter)
      three dots/ellipsis/...
        arguments <- list(...)
    Rstudio
      custom shortcuts
        #F12  go to file/function
        #B    go to function definition
        ^O    navigate back
      View() Rstudio
        planes %>% filter(no.seats < 10) %>% View()
    dplyrExtras
    Conditional replace in-place
      mutate_if(df,a==3,x=100)
      use
        mutate( type = ifelse(  f$type == f$field, NA, f$type ) )
    Select/arrange columns with character variables
      cols = c("mpg","cyl, hp:vs")
      mtcars %.%
        filter(gear == 3,cyl == 8) %.%
        s_select(cols)
      s_arrange(mtcars, c("gear", "-mpg"))
      normal way:
        select(mpg, cyl)
    XML
      xmlParse()
        doc = xmlParse(file)
      xmlRoot()
        root = xmlRoot(doc)
      navigating
        xmlChildren(root)
        xmlName # name of node
      looping over nodes
        root %>% xmlChildren %>% lapply(xmlName) # ==
        root %>% xmlApply(xmlName)
      xpath
        links = xpathSApply(root, "path")
        link_attr_vals = xpathSApply(root, "path", xmlGetAttr, "href")
    apply/ldply/foreach list generations
      cases for lapply, map
        we need to loop over this function:
          find_correct_tag = function(fn, revenue, xdca) {..}
        opt1: make xdca global
          ct = Map(find_correct_tag, vxtf$filename, vxtf$revenue) %>%
            rbindlist
        opt2: partial find_correct_tag
          fun1 = partial(find_correct_tag, xdca = xdca)
          ct = Map(fun1, vxtf$filename, vxtf$revenue) %>%
            rbindlist
        opt3: use seq_along in lapply
          ct = lapply(seq_along(vxtf$filename), 
            function(i, vxtf, xdca) 
              find_correct_tag(vxtf[i]$filename, vxtf[i]$revenue, xdca)
            , vxtf, xdca
          ) %>%
            rbindlist
      lapply over names
        example_apply_with_names = function() {
          ls = list( a = 3, b = 5 )
          # opt1 
          for (n in names(ls)) {
            print(ls[[n]] + 1)
          } 
          # opt2 
          lapply( seq_along(ls), function(i, ns, ls) {
              ls[[ns[i]]] + 1
            }, names(ls), ls)
          # opt4
          mapn(ls, function(elem, name) {
               print(elem + 1)
               print(name)
            })
        }
      lapply datatable columns
        lapply(data, function(x) sprintf(t, x))
      for loop functionals: lapply/sapply/vapply/mapply
        lapply for lists
          for rows of data frames: use apply
        lapply(l, f)
          apply f to each element of list
          return: list as l
        aggregating l elements with f
          lapply(l, f) %>% unlist # ==
          sapply(l, f)
          replicate(n, expr, simplify = "array")
            wrapper for sapply
          simplify
            result simplifed to vector, matrix, array?
          simplify = F, value: list
        Map
          lapply: one argument varies
          Map: multiple args
            Map(f, list1, list2)
          mtmeans <- lapply(mtcars, mean) 
          mtmeans[] <- Map(`/`, mtcars, mtmeans) # ==
          mtcars[] <- lapply(mtcars, function(x) x / mean(x))
        mapply
          variant of Map
          do.call vs. lapply
            do.call(fun, args)
              fun(args)
            lapply(args, fun)
              args passed to fun one by one
              fun(args[[1]])
            sprintf
              do.call( 'sprintf', list( fmt = t, data[,1], data[,2] ) ) # works
              arg = c( list(t), as.list(data) )
              do.call( 'sprintf', arg ) # works
              not works
                do.call( 'sprintf', t, data )
                do.call( 'sprintf', t, list(data[,1], data[,2]) )
                sprintf( t, list(data[,1], data[,2]) )
                do.call( 'sprintf', list( fmt = t, data ) )
    Data Use Cases
      distinct/unique values of vector
        unique()
      switch(ext,
        txt=dir_filings_txt(),
        xml=dir_filings_xbrl(),
        zip=dir_filings_zip(),
        xbrl=dir_filings_xbrl())
      duplicates
        duplicated rows in dplyr
          # opt1: duplikasyonları ve orjinal kayıtları siler
          dups8 = dd8 %>%
            group_by( entity_name, data_field_name ) %>%
            filter( n() > 1 )
          # opt2: sadece duplikasyonları siler
          dups8b = dd8 %>%
            distinct( entity_name, data_field_name, .keep_all = T ) 
          duplicated_rows = function(df, column) {
            fld %>%
              group_by_(column) %>%
              filter( n() > 1 )
          }
          duplicated_rows(fld, "field_id")
        duplicated(vec) # T, F, T
        get both: duplicated and its reference
          x = c(1,3,1)
          duplicated(x) | duplicated(x, fromLast = T)
        Remove duplicate rows 
          dups = duplicated( dt$cik )
          dt[!dups] 
        filter and select duplicate values
          v = filter_nonna(df, "enum_id")$enum_id
          v[ duplicated(v) ]
          ===
          duplicated_values = function(df, column) {
            v = filter_nonna(df, column)[[column]]
            v[ duplicated(v) ] %>% unique
          }
          duplicated_values(df, "enum_id")
        all_unique = function(v) { duplicated(v) %>% sum == 0 }
      which(logical) # 1, 3
      is.na(d1)
      rep(x, times)
      unlist(x) # flatten
      do.call('fun', iterable) # fun(iterable[1], iterable[2] ..)
      Access last value
        tail(vector, n=1)
        data frame :
          x[length(x[,1]),]
          x[dim(x)[1],]
          x[nrow(x),]
      is.null check: is.blank() in utils.R
      split df by filename
        split(df, df$filename)
      Queries/Subsetting
        Assignment if true
          df$agecat[age > 75] <- "Elder"
        how many exists?
          a = length( which(x$category == 'I.setosa') )
        non na values from vector
          d[!is.na(d)]
        non na rows from df
          filter_nonna = function(df, column) {
            df[!is.na(df[[column]]), ]
          }
          filter_nonna(df, "enum_id")
      Growing
        build parts then join them
          using for loop
            rl = vector('list', n)
            for(i in 1:n) {
              rl[[i]] = data.table(..)
            }
            dt = do.call('rbind', rl)
          dt = rbindlist(rl) # better
            rbindlist bug
              when columns order is different, rbindlist will produce nonsense 
              use use.names=T
      Serialization
        saveRDS(women, "women.rds")
        women2 <- readRDS("women.rds")
        dput(mean, "foo") # write in ascii
        bar <- dget("foo")
        unlink("foo") # remove
      Conversions
        dataframe to datatable
          den = read_excel2(path, 'DataEntity') %>% data.table
        list to data frame/table
          opt1
            my.df <- do.call('rbind', my.list)
            rbindlist(my.list)
          opt2
            as.data.frame(e)
          opt3: differing sizes
            test4 <- list('Row1'=letters[1:5], 'Row2'=letters[1:7], 'Row3'=letters[8:14])
            as.data.table(test4)
          http://www.r-bloggers.com/converting-a-list-to-a-data-frame/
        dataframe to list conversion
          # 2: transpose and as.list. elements are vectors
            dl2 = df %>%
              t %>%
              as.data.frame %>%
              as.list
          # 3: unlist. elements are vectors
            dl3 = df %>%
              apply(1, list) %>%
              unlist(recursive = F)
        convert vector to list
          as.list(c(1,2,3)
        vector to list
          kn <- c("1", "a", "b")
          nl <- vector(mode="list", length=length(kn)-1)
          names(nl) <- kn[-1]
          ml <- lapply(nl, function(x) kn[1])
          ml
            $a
            [1] "1"
            $b
            [1] "1"
        build list from a vector and multiple valued vector
          input
            > tags
            [[1]]
            [1] "Revenues"                "SalesRevenueServicesNet"
            [[2]]
            [1] "Revenues"
            > f
            [1] "1000045-0001193125-14-237425" "1000180-0001000180-15-000013"
          target
            ft 
            [[1]]
            $filename "100045"
            $tags list
              [[1]] "Revenues" ...
          using for loop
            ft = vector("list", length(f))
            for (i in seq_along(ft)) {
              ft[[i]]$filename = f[i]
              ft[[i]]$tags = tags[[i]]
            }
          using lapply
            ft2 = lapply(seq_along(f), 
              function(i, f, tags)
                list(
                  filename = f[[i]],
                  tags = tags[[i]]
                ),
              f, tags
              )
            identical(ft, ft2)
      Generate Test Data
        sample_with_replace = function(v, n = 100) sample(v, size = n, replace = T)
        sample_datatable = function(dt, n = 100) dt[ sample(nrow(dt), size = n) ]
        auction_data = data.frame(
          Price = 1:100 %>% sample_with_replace)
        s = auction_data %>% sample_datatable(5)
      read header of csv only
        con = file("data/flights4.csv")
        open(con)
        h4 = read.table(con, skip = 0, nrow = 1, sep = ",") %>% 
          unlist %>% unname
        close(con)
      complete
        It turns implicitly missing values into explicitly missing values.
        df <- data_frame(
          group = c(1:2, 1),
          item_id = c(1:2, 2),
          item_name = c("a", "b", "b"),
          value1 = 1:3,
          value2 = 4:6
        )
        df %>% complete(group, c(item_id, item_name))
          group item_id item_name value1 value2
          1       1         a      1      4
          1       2         b      3      6
          2       1         a     NA     NA
          2       2         b      2      5
        df
          Source: local data frame [3 x 5]
          group item_id item_name value1 value2
          1       1         a      1      4
          2       2         b      2      5
          1       2         b      3      6
    Platform
      install from github
        library("devtools")
        install.packages("devtools")
        install_github("repo/username")
      update all packages from CRAN
        update.packages(checkBuilt=TRUE, ask=FALSE)
      Performance
        measure time
          system.time(for(i in 1:100) mad(runif(1000)))
        profiling
          Rprof('file')
          # code
          Rprof(NULL)
          summaryRprof('file')
      System
        system(cmd)
        system(cmd, intern=T)
          capture output of command 
        system2("echo", "hello")
        calling R from shell
          bash
            Rscript RscriptEcho.R study_rscript1.R test 10
          study_rscript1.R
            #! /usr/bin/Rscript --vanilla --default-packages=utils
            args <- commandArgs(TRUE)
            print(args)
        taking argument in R scripts
          args <- commandArgs(trailingOnly = TRUE)
          print(args)
      initial/startup/default session settings
        ~/.Rprofile
      options/settings
        options(max.width=100)
        GetOption("max.width")
        options(max.print=100)
        options(max.print=6)
    # knitr
      ref
        http://kbroman.org/knitr_knutshell/
      what is knitr
        dynamic documents 
        difference to rmarkdown
          knitr supports rmarkdown in addition to latex, lyx etc.
      how to run
        opt1: Rstudio
          #+K "Knit"
        opt2: terminal
          rmarkdown::render("vignettes/my-vignette.Rmd")
        opt3: bash terminal
          rmarkdown <file>
          R -e 'rmarkdown::render("example.Rmd")'
          R -e 'rmarkdown::render("example.Rmd", "pdf_document")'
      relation between knitr and rmarkdown
        http://rmarkdown.rstudio.com/lesson-2.html
        knitr takes Rmd as input and produces md
        pandoc takes md as input and produces html/pdf
        rmarkdown::render() feeds .Rmd file to knitr
        knitr executes code chunks and creates a new .md file
      http://kbroman.org/knitr_knutshell/
        Knitr Overview
          Code Chunks
            ex:
              We see that this is an intercross with `r nind(sug)` individuals.
              There are `r nphe(sug)` phenotypes, and genotype data at
              `r totmar(sug)` markers across the `r nchr(sug)` autosomes.  The genotype
              data is quite complete.
              Use `plot()` to get a summary plot of the data.
              ```{r summary_plot, fig.height=8}
              plot(sug)
              ```
            inline: `r fun()`
            new line: ```{r}
            different text types allowed: Rmarkdown, Latex, AsciiDoc
          Compiling the document
            for Rmarkdown: 
              opt1: Rstudio > Knit button
              opt2: rmarkdown::render(file)
              opt3: command line (gnu make)
                R -e 'rmarkdown::render("example.Rmd")'
    rmarkdown
      ref
        http://rmarkdown.rstudio.com/
        https://www.rstudio.com/wp-content/uploads/2016/03/rmarkdown-cheatsheet-2.0.pdf
        https://www.rstudio.com/wp-content/uploads/2015/03/rmarkdown-reference.pdf
      render("input.Rmd", "pdf_document")
      getting started
        rstudio > file > new > rmarkdown > .html
          örnek bir şablon dosya açılır
        button bar > knit
      install
        library("rmarkdown")
        install.packages("rmarkdown")
      run
        rmarkdown::render("input.Rmd")
        render("input.Rmd")
        render("input.Rmd", "pdf_document")
        R -e 'rmarkdown::render("data_generation.Rmd", "html_document")'
      notebook in rstudio
      code chunks in rstudio
        #!i add new chunk
      code languages
        bash, python, sql, js
      parameters
        heading içinde parametre tanımlayabilirsin
        code  
          ---
          params: 
            data: "hawaii"
          ---
          data(list = params$data)
        setting parameters values
          render("file.Rmd", params = list(data = "niagara"))
      markdown format
        inline code
          formatting
            `kpv['kw003']` 
          evaluate expression
            `r kpv['kw003']` 
        embedding mathematical formulas
          latex
            $\frac{1}{n}$
        export to pdf
          opt
            output: pdf_document
            output: beamer_presentation
          pdf_document
            produces normal pdf doc
            render("input.Rmd", "pdf_document")
          beamer_presentation
            produces slides
            note: single heading level
        embedding code
          basic
            ```{r}
            code()
            ```
        table
          table of figures/data using kable
            http://yihui.name/printr/
            http://kbroman.org/knitr_knutshell/pages/figs_tables.html
            knitr::kable(matrix, digits = 2, caption = "A table produced by printr.")
          inside markdown
            ex1
              Firs | Secont
              -----|-------
              conte|con2
              cmo  | con 3
            ex2
              | name      | address         | phone   |
              |-----------------|--------------------------|------------|
              | John Adams    | 1600 Pennsylvania Avenue | 0123456789 |
              | Sherlock Holmes | 221B Baker Street   | 0987654321 |
            ex3
              |-----------------|--------------------------|------------|
              | name      | address         | phone   |
              |-----------------|--------------------------|------------|
              | John Adams    | 1600 Pennsylvania Avenue | 0123456789 |
              | Sherlock Holmes | 221B Baker Street   | 0987654321 |
              |-----------------|--------------------------|------------|
      output formats
        render("input.Rmd", output_format = "pdf_document")
        opt
          documents
            html notebook: interactive notebooks
            html document
            pdf
            word
            rtf
            md
          presentations
            ioslides
            beamer
        output options
          ex: table of contens
            ---
            output:
              html_document:
                toc: true
          help on options
            ?html_document
      notebooks
        ref
          http://rmarkdown.rstudio.com/r_notebooks.html
        interactive
          open from rstudio
          open in browser
            output: html_notebook
      presentation
        Presenter Mode
        add this to the end of the url while starting
          ?presentme=true
          /Users/mertnuhoglu/projects/dewey/data_analysis_presentations/istanbulcoders/input.html?presentme=true
        adding to slides
          <div class="notes">
          this is notes
          </div>
        new slide
          # title only
          ## title and text
          ----
            no title
        display modes
          f   full
          w   wide
          o   overview
          h   highlight
          p   presenter
      dashboards
      websites
        each .Rmd = page of site
        _site.yml
          header
      interactive documents 
        htmlwidgets
        shiny
    other
      override functions
        filter = dplyr::filter
      open browser from R
        browseURL(url)
    gis use cases
      Türkiye illerinin poligon haritası
        library(rgdal)
        library(leaflet)
        file =  "ne_50m_admin_0_countries.zip"
        download.file(file.path('http://www.naturalearthdata.com/http/',
            'www.naturalearthdata.com/download/50m/cultural',
            'ne_50m_admin_0_countries.zip'), 
            destfile = file)
        unzip(file)
        world <- readOGR(tempdir(), 'ne_50m_admin_0_countries', encoding='UTF-8')
        leaflet() %>%
          addTiles() %>%
          addPolygons(data=subset(world, name %in% "Turkey"), weight=2)
    igraph
      ex
        library(igraph); 
        dor = data.frame( from = c(1, 1, 2, 3, 4, 5, 6), 
          to = c(2, 3, 4, 5, 6, 6, 7) )
        g = graph_from_data_frame(dor)
        plot(g)
      dependency ordering
        opt1: topological sorting
          g <- graph_from_data_frame(deps)
          t = topo_sort(g)
          which_loop(g)
          V(g)
          V(g)$name
          names(t)
        opt2: shortest path distances
          ex1
            lfs = data.frame( from = c('start', 'x', 'x', 'y'), 
                              to = c('x', 'y', 'p', 'z'), 
                              level = 0)
            # create graph from data.frame
            g <- graph_from_data_frame(lfs)
            # find distances from chosen node
            distances(g, "start")
          ex2
            g <- graph_from_data_frame(deps)
            d = distances(g, "EnumCategory")[1, ]
            dst = data_frame(
              entity_name = names(d),
              dependency_level = d
            )
      plot parameters
        http://kateto.net/network-visualization
      interactive params
        library(manipulate)
        ced4 = ced3 %>%
          mutate( ind = str_replace(ind, "_enum_id", "") ) %>%
          mutate( dep = str_replace(dep, "_enum_id", "") ) 
        g = graph_from_data_frame(ced4)
        l <- layout.grid(g)
        pdf("tmp/complex_enum_dependencies.pdf")
        plot(g, 
          layout = l,
          edge.arrow.size=0.2, 
          vertex.size = 5, 
          vertex.shape = "none",
          vertex.label.cex = 0.2
        )
        dev.off()
        manipulate(
          plot(g, 
            layout = l,
            edge.arrow.size=eas, 
            vertex.size = vs, 
            vertex.shape = "none",
            vertex.label.cex = vlc
          ),
          eas = slider(0,2,initial = 0.5, step = 0.1),
          vs = slider(0,10,initial = 5, step = 0.1),
          vlc = slider(0,2,initial = 1, step = 0.1)
        )
    rest client - httr
      get with no args:
        jsonlite
        json - rest <url:#r=sr_0004>
      post and real rest
        httr
      GET 
        r <- GET("http://localhost:8080/greeting", 
          query = list(name = "Mert")
        )
        str(content(r))
        # List of 2
        #  $ id     : int 2
        #  $ content: chr "Hello, Mert!"
      content(r)$content
      # [1] "Hello, Mert!"
    json - rest id=sr_0004
      json - rest <url:#r=sr_0004>
      jsonlite
        tercih
      all.equal(mtcars, fromJSON(toJSON(mtcars)))
      result = jsonlite::fromJSON("data/input/postman_20160719.json")
        bunu kullan
      jsonlite::fromJSON("data/arcgis/aws.json", simplifyDataFrame = T)
        df üretir, bazen
      emdkj = jsonlite::fromJSON("data/arcgis/emdk.json")$services
        direk df döndü
      other
        result = rjson::fromJSON(file="data/input/postman_20160719.json")
      call rest api
        https://cran.r-project.org/web/packages/jsonlite/vignettes/json-apis.html
        hadley_orgs <- fromJSON("https://api.github.com/users/hadley/orgs")
        response: 
          json [{..}, {..}]
          ->
          dataframe
        ex
          [
            {
              "login": "ggobi",
              "id": 423638,
              "url": "https://api.github.com/orgs/ggobi",
              "repos_url": "https://api.github.com/orgs/ggobi/repos",
              "events_url": "https://api.github.com/orgs/ggobi/events",
              "hooks_url": "https://api.github.com/orgs/ggobi/hooks",
              "issues_url": "https://api.github.com/orgs/ggobi/issues",
              "members_url": "https://api.github.com/orgs/ggobi/members{/member}",
              "public_members_url": "https://api.github.com/orgs/ggobi/public_members{/member}",
              "avatar_url": "https://avatars2.githubusercontent.com/u/423638?v=3",
              "description": ""
            },
          ->
          hadley_orgs %>% s
          'data.frame'
           $ login             
           $ id                
           $ url               
           $ repos_url         
           $ events_url        
           $ hooks_url         
           $ issues_url        
           $ members_url       
           $ public_members_url
           $ avatar_url        
           $ description       
    yaml
      library('yaml')
      install.packages('yaml')
      install_github("MangoTheCat/rematch")
      yaml.load(aString)
      yaml.load_file(apath)
      as.yaml(obj)
    assert_that
      https://github.com/hadley/assertthat
      install.packages('assertthat')
        library('assertthat')
      replacement for stopifnot
        assert_that(is.character(x))
        # Error: x is not a character vector
      examples
        assert_that( all_nonna(de$data_entity_id) )
        assert_that( nrow(dd) == nrow(dd3) )
        assert_that( none(n1 & n2) )
        assert_that( (sum(n1) + sum(n2) + sum(n3)) == nrow(dd4) )
        assert_that( setequal(dd$entity_name, de$entity_name) )
        assert_that( nrow(dd2) == 0 )
        assert_that( all_unique(df$data_field_id) )
    rio
      install.packages("rio")
      library("rio")
      ev = import("data/enum_value.csv")
      export(ev, "data/enum_value.tsv")
      ev2 = import("data/enum_value.tsv")
      all.equal(ev, ev2, check.attributes = F)
      convert("data/enum_value.csv", "data/enum_value.tsv")
      # Rscript -e "rio::convert('data/enum_value.csv', 'data/enum_value.tsv')"
    pryr
      install.packages("pryr")
      library("pryr")
    ## rmarkdown
      install.packages("rmarkdown")
      library("rmarkdown")
      run
      rmarkdown::render("input.Rmd")
      render("input.Rmd", "pdf_document")
      Presenter Mode
      add this to the end of the url while starting
        ?presentme=true
        /Users/mertnuhoglu/projects/dewey/data_analysis_presentations/istanbulcoders/input.html?presentme=true
      adding to slides
        <div class="notes">
        this is notes
        </div>
    ## slidify
      install
        install_github('ramnathv/slidify')
        install_github('ramnathv/slidifyLibraries')
      create deck
        library(slidify)
        author("slidify-demo-01")
      push to github
        github: create a new repo "slidify-demo-01"
        git remote add origin https://github.com/mertnuhoglu/slidify-demo-01.git
        local: git add+commit
      generate/update deck
        slidify("index.Rmd")
      publish github
        publish(user = "mertnuhoglu", repo = "slidify-demo-01", host = "github")
      open
        http://mertnuhoglu.github.io/slidify-demo-01/index.html
      publish dropbox
        publish(user = "mydeck", host = "dropbox")
      open
        https://dl.dropboxusercontent.com/u/103580364/mydeck/index.html
      extensions and themes
        http://ramnathv.github.io/slidifyExamples/
        http://slidify.org/style.html
        http://stackoverflow.com/questions/19348763/how-i-can-include-the-use-of-the-extension-deck-automation-js-when-i-create-a-do
          http://slidify.github.io/add-deck-ext/
      deckjs framework
        https://raw.githubusercontent.com/ramnathv/slidifyExamples/gh-pages/examples/deckjs/index.Rmd
        put into heading part (indent with spaces)
          framework: deckjs
          deckjs:
            transition: horizontal-slide
            extensions: [goto, hash, menu, navigation, scale, status]
        themes
          web-2.0
          swiss
        shortcuts
          m    view menu
          g#  go to slide
      add extensions
        http://slidify.github.io/add-deck-ext/
      add extension: automatic.js
        setup
          curl -o automatic.zip https://github.com/rchampourlier/deck.automatic.js/archive/master.zip
          unzip -oq automatic.zip deck.automatic.js-master/automatic/ 
          mv deck.automatic.js-master/automatic libraries/frameworks/deckjs/extensions/
          rm automatic.zip
          rm -r deck.automatic.js-master
        add to heading
          extensions: [goto, hash, menu, navigation, scale, status, automatic]
        add snippet to libraries/frameworks/deckjs/partials/snippets.html
          <!-- Initialize the deck -->
          <script>
          $(function() {
            // required only if the automatic extension is enabled.
            $.extend(true, $.deck.defaults, {
            automatic: {
              startRunning: false,  // true or false
              cycle: false,      // true or false
              slideDuration: 10000 // duration in milliseconds
            }})
            $.deck('.slide');
          });
          </script>
        add play/pause buttons to libraries/frameworks/deckjs/layouts/deck.html
          <!-- Begin slides -->
          {{{ page.content }}}
          <div class='deck-automatic-link' title="Play/Pause">Play/Pause</div>
      use cases
        impressjs
          visually stunning
        deckjs
          easy to use
        landslide
          question answer
        flowtime
          hierarchical
        
      multiple presentations
        subdirectory
          author("new_slidify_project")
          cd new_slidify_project
        new file
          cp index.Rmd new_slideshow.Rmd
          slidify("new_slideshow.Rmd")
    potential bugs
      data.frame objelerinde factor -> numeric hatası
        data.frame numeric bir değeri factor'e çevirebilir
        sonra bunu geri numeric'e çevirdiğinde, farklı bir değer alırsın
        bu yüzden asla as.data.frame kullanma, as_data_frame kullan
    transliterate
      iconv
        x = "Addyişm__NİO_Yasamal.PDF"
        iconv(x, "utf-8", "ASCII//TRANSLIT")
      stringi
        label %>%
          stri_trans_totitle( locale = "tr_TR" )
      regex
        transliterate_tr_to_ascii = function( lines ) {
          lines %>%
            str_replace_all(c("ü"="u", "ö"="o", "ı"="i", "Ü"="U", "Ö"="O", "İ"="I", "ş"="s", "ğ"="g", "ç"="c", "Ş"="S", "Ğ"="G", "Ç"="C", "ə"="e", "Ə"="E"))
        }
    tidyr
      tutorial
        https://rpubs.com/bradleyboehmke/data_wrangling
      extract_numeric
        mutate(valuation = extract_numeric(`Valuation ($B)`))
      gather
        takes multiple columns, gathers them into key-value pairs
        wide to longer
      spread
        takes key-value columns, spreads into multiple columns
        logn to wider
      separate
        split single column into multiple
      unite
        unite multiple columns into single
      gather
        ex
          data
            trt wT1 hT1 wT2 hT2
            ....
          output
            trt key value
            ..  wT1 ..
            ..  wT2 ..
          api
            gather( data, key, value, ..)
              data: df
              key: column for new variable
              value: column for values
            gather( data, key, value, wT1:hT2)
      reshape
        reverse gather
        ex
        api
          data
            trt key value
            ..  wT1 ..
            ..  wT2 ..
          output
            trt wT1 hT1 wT2 hT2
            ....
          spread(data, key, value, ..)
            params
              data: df
              key: column to convert
              value: new column
            error: duplicate identifiers for rows
              bir identifier eklemelisin
              mutate( row = row_number() ) 
          gather( data, key, value)
      separate
        ex
          data
            yr  qtr rev
            ..  q1  10
            ..  q2  20
          output
            yr  int id  rev
            ..  q   1   10
            ..  q   2   20
          api
            separate( data, col, into, sep)
              data: df
              col: current var
              into: new variables
              sep: separator
      unite
        reverse of separate
    database
      RPostgreSQL
        dbDriver
        dbConnect
          dbDisconnect
        dbApply: apply function to each row
        dbCallProc: call stored procedure
        dbCommit
          dbRollback
        dbGetInfo
          dbGetInfo(rs, what = "rowsAffected")
          names(dbGetInfo)
        ex
          dbDriver
          con = dbConnect(..)
          df = dbGetQuery(con, ..)
          rs = dbSendQuery(..)
          df = fetch(rs, n = -1)
        dbDataType.
        dbListTables
        dbReadTable
          dbRemoveTable
          dbWriteTable
          dbExistsTable
        rs = dbSendQuery
          df = dbGetQuery
      ROracle
        install.packages("ROracle")
        installing
          tutorial
            http://www.baldwhiteguy.co.nz/technical/index_files/mac-osx-oracle-instantclient.html
          download:
            instantclient-basic-macos.x64-11.2.0.4.0.zip
            instantclient-sdk-macos.x64-11.2.0.4.0.zip
            instantclient-sqlplus-macos.x64-11.2.0.4.0.zip
            put into ~/tools/oracle/instantclient_11_2
          setup
            cd ~/tools/oracle/instantclient_11_2
            ln -s libclntsh.dylib.11.1 libclntsh.dylib
            export PATH=~/tools/oracle/instantclient_11_2:$PATH
          https://docs.oracle.com/cd/E11882_01/install.112/e38228/inst_task.htm#BABHEBIG
            export ORACLE=$HOME/tools/oracle/instantclient_11_2
            export PATH=$ORACLE:$PATH
            export DYLD_LIBRARY_PATH=$ORACLE
            export NLS_LANG=$ORACLE
            export OCI_LIB_DIR=$ORACLE
            export OCI_INC_DIR=$ORACLE/sdk/include
            sqlplus
          install ROracle
            http://dba.stackexchange.com/questions/66424/how-to-install-roracle-on-linux
              R CMD INSTALL --configure-args='--with-oci-lib=/Users/mertnuhoglu/tools/oracle/instantclient_11_2 --with-oci-inc=/Users/mertnuhoglu/tools/oracle/instantclient_11_2/sdk/include' ROracle_1.2-2.tar.gz
              
        connection
          opt1
            drv <- dbDriver("Oracle")
            username = "system"
            password = "..."
            dbname = "52.73.23.191:1521/btgdev"
            con <- dbConnect(drv, user = username, password = password, dbname = dbname)
          opt2
            drv <- dbDriver("Oracle")
            username = "system"
            password = "..."
            host = "52.73.23.191"
            port = "1521"
            sid = "btgdev"
            connect.string <- paste( 
              "(DESCRIPTION=",
              "(ADDRESS=(PROTOCOL=tcp)(HOST=", host, 
              ")(PORT=", port, "))", 
              "(CONNECT_DATA=(SID=", sid, ")))", sep = "")
      RJdbc
        install.packages("RJDBC")
    purrr
      install.packages("tidyverse")
      reduce
        ex
          <url:file:///~/Dropbox/mynotes/content/mine/study_assign_kombin_termin.R>
        ex1
          # step4: full_join için for loop kullan
          res = evls[[1]]
          for ( i in 2:length(evls) ) {
            res = res %>%
              full_join( evls[[i]], by = "dependent_id" )
          }
          # step5: reduce ile yap
          full_join_by_dependent_id = function( evl1, evl2 ) {
            evl1 %>%
              full_join( evl2, by = "dependent_id" )
          }
          evls %>% reduce( full_join_by_dependent_id )
    password kullanma
      env variable tanımla
        .Renviron içinde
      password = Sys.getenv("LERIS_ORACLE_BTG_MIS_PASSWORD")
    dplyrOracle
      install_github("tomasgreif/dplyrOracle")
      ref
        # <url:file:///~/Dropbox (BTG)/TEUIS PROJECT 05-ANALYSIS/working_library/requirements_database/scripts/study_oracle.R>
    datatree data.tree
      convert_parent_child_fk_into_pathString
        ref
          study_convert_parent_child_fk_into_pathString()
        ex
          get_parent_path = function( df, ids ) {
            get_parent_pathe = function(df, id) {
              find_parent = function(df, pid) {
                filter(df, id == pid)$parent_id
              }
              id_path = id
              pid = df[id, ]$parent_id
              while (!is_na(pid)) {
                id_path = c(id_path, pid)
                pid = find_parent(df, pid)
              }
              paste( filter(df, id %in% rev(id_path))$name, collapse = "/" )
            }
            r = vector('character')
            for (id in ids) {
              r = c(r, get_parent_pathe(df, id))
            }
            return(r)
          }
          # input data:
          df = data.frame(id = 1:3, name = c("asia", "iran", "tehran"), parent_id = c(NA, 1, 2), stringsAsFactors=F)
          # id,name,parent_id
          # 1,asia,NA
          # 2,iran,1
          # 3,tehran,2
          # target data:
          target = data.frame(id = 1:3, name = c("asia", "iran", "tehran"), parent_id = c(NA, 1L, 2L), pathString = c("asia", "asia/iran", "asia/iran/tehran"), stringsAsFactors=F)
          # id,name,parent_id,pathString
          # 1,asia,NA,asia
          # 2,iran,1,asia/iran
          # 3,tehran,2,asia/iran/tehran
          result = df %>%
            mutate( pathString = get_parent_path(., id) )
          result
          #      id   name parent_id       pathString
          #   <int>  <chr>     <int>            <chr>
          # 1     1   asia        NA             asia
          # 2     2   iran         1        asia/iran
          # 3     3 tehran         2 asia/iran/tehran
          dt <- as.Node(result)
          #        levelName
          # 1 asia
          # 2  °--iran
          # 3      °--tehran
    rhandsontable
      install.packages("rhandsontable")
    shiny
      install.packages("shiny")
      publishing to shinyapps.io
        shinyapps.io/admin
        new domain name
        authorize account
        library(rsconnect)
        rsconnect::deployApp('path/to/your/app')
          rsconnect::deployApp('shiny/lesson01')
      conf
        location
          site_dir
            folder where multiple apps are stored in each folder
          app_dir
            only one application
          ex
            # Define the location '/specialApp'
            location /specialApp {
              # Run this location in 'app_dir' mode, which will host a single Shiny
              # Application available at '/srv/shiny-server/myApp'
              app_dir /srv/shiny-server/myApp
            }
            # Define the location '/otherApps'
            location /otherApps {
              # Run this location in 'site_dir' mode, which hosts the entire directory
              # tree at '/srv/shiny-server/apps'
              site_dir /srv/shiny-server/apps;
            }
        ...
    opencpu
      use an external library function inside your own package
        ex:
          upload_run <- function(file) {
            rio::import(file)
          }
        note: import() alone gives error because opencpu doesn't load packages by itself
      install package to global library id=sr_0005
        install package to global library <url:#r=sr_0005>
        note: give permission first to write to global library
          cd /usr/local/lib/R
          sudo chmod o+w site-library
          opt: use custom container:
            mertnuhoglu/opencpu_libs:2
          else: ocpu.call paths will be different
            http://localhost:8004/ocpu/user/opencpu/library/vrpdata/R/hello
        1. step: install into global library
          .libPaths("/usr/local/lib/R/site-library")
        2. create new package / open package
          help
            create your own functions in rstudio <url:#r=sr_0006>
        3. rstudio > build & load
          devtools::load_all()
          devtools::install()
        4. check if library is installed correctly
      function calls
        curl url
          mypackage
            fun
          GET
            http://localhost:8004/ocpu/library/mypackage/R/fun/print
          POST
            http://localhost:8004/ocpu/library/mypackage/R/fun/
              returns several temporary urls
            http://localhost:8004/ocpu/library/mypackage/R/fun/json
              returns result directly
        js calls
          ref
            ~/projects/itr/vrp/frontend/views/test_pug/opencpu_*.pug
          ex: 04 
            ocpu.seturl("//localhost/ocpu/library/stats/R")
            var req = ocpu.call("rnorm", {n: 100, mean: rnd}, function (session) {..}
          ex: base
            ocpu.seturl("//localhost/ocpu/library/base/R")
            var req = ocpu.call("identity", { "x": mysnippet }, function (tmp) {
          ex: 05
            ocpu.seturl("//localhost/ocpu/library/utils/R")
            var csv = $("#input").val();
            var req = ocpu.call("read.csv", {text: csv}, function (session) {
          ex: 06
            ocpu.seturl("//localhost/ocpu/library/utils/R")
            var arg = [1,2,3];
            var req = ocpu.call("str", {object: arg}, function (session) {
          ex: 07
            ocpu.seturl("//localhost/ocpu/library/utils/R")
            var arg1 = [[1,2,3], [10,20,30]];
            var req1 = ocpu.call("str", {object: arg1}, function (session) {
          ex: 08_df custom package
            ocpu.seturl("//localhost/ocpu/user/opencpu/library/pmf/R")
            var arg1 = [[1,2,3], [10,20,30]];
            var req1 = ocpu.call("upload_data", {mat: arg1}, function (session) {
          ex: upload01 file upload
            <b>CSV File</b> <input type="file" id="csvfile">
            ocpu.seturl("//localhost/ocpu/library/utils/R")
            var myheader = $("#header").val() == "true";
            var myfile = $("#csvfile")[0].files[0];
            var req = ocpu.call("read.csv", { "file": myfile, "header": myheader }, function (session) {
          ex: upload02 file upload (rio)
            ocpu.seturl(`//${ocpu_domain}/ocpu/library/rio/R`)
            var req = ocpu.call("import", { "file": myfile }, ..
      run docker container
        docker pull opencpu/rstudio
        docker run -p 80:80 -p 8004:8004 --name ocp \
          -v /Users/mertnuhoglu/projects/itr/plan_management_frontend/r/pkg/:/home/opencpu \
          opencpu/rstudio 
        hata: sigwinch signal docker container'ı kapatıyor
          -t opsiyonunu kaldır
          --sig-proxy=false ile çalıştır
        hata: tüm portlar çalışmıyor
          80, 8004 çalışıyor
          8090, 9090 çalışmıyor
        opt
          docker run -t -p 80:80 -p 8004:8004 --name ocp --sig-proxy=false opencpu/rstudio 
          docker run -t -p 80:80 -p 8004:8004 --name ocp opencpu/rstudio 
          docker run -p 80:80 -p 8004:8004 --name ocp opencpu/rstudio 
        docker exec -i -t ocp /bin/bash
      test api
        http://localhost/ocpu/test/
          ../library/stats/R/rnorm/json
            n 3
            mean  10
            sd  10
          ../library/utils/R/read.csv
            file
          open Location
            http://localhost/ocpu/tmp/x040cd9fda3/
            http://localhost/ocpu/tmp/x040cd9fda3/R/.val/print
            http://localhost/ocpu/tmp/x040cd9fda3/R/.val/json
            http://localhost/ocpu/tmp/x040cd9fda3/R/.val/csv
            http://localhost/ocpu/tmp/x040cd9fda3/files
            http://localhost/ocpu/tmp/x040cd9fda3/files/siparis.csv
            http://localhost/ocpu/tmp/x040cd9fda3/info
          use key as argument
            ../library/ggplot2/R/qplot
              x en
              y boy
              data x040cd9fda3
            open Location
              http://localhost/ocpu/tmp/x05680d0a35/
              http://localhost/ocpu/tmp/x05680d0a35/graphics/1/png
              http://localhost/ocpu/tmp/x05680d0a35/graphics/1/png?width=1200
              http://localhost/ocpu/tmp/x05680d0a35/console/text
                > qplot(x = en, y = boy, data = x040cd9fda3::.val)
                [[ plot ]]
              http://localhost/ocpu/tmp/x05680d0a35/source/text
      nodejs
        https://github.com/albertosantini/node-opencpu
        npm install -S opencpu
        ex
          var opencpu = require("opencpu");
          opencpu.rCall("/library/datasets/R/mtcars/json", {}, function (err, data) {
              if (!err) {
                  console.log(data[0].mpg + data[1].mpg); // => 42
              } else {
                  console.log("opencpu call failed.");
              }
          });
      test js client
        test R:
          function(x, n){  return(x^n) }
        ex:
          http://jsfiddle.net/opencpu/7torLdk9/
          /Users/mertnuhoglu/projects/itr/plan_management_frontend/views/test_pug/opencpu01.pug
          copy opencpu-0.4.js to public/js/lib
          layout.pug
            script(src='/js/lib/opencpu-0.4.js')
        ex02:
          ocpu.seturl("//localhost/ocpu/library/base/R")
        ex03: file upload 
          http://jsfiddle.net/opencpu/hc5b9w7r/
      docker opencpu rstudio image
        https://hub.docker.com/r/opencpu/rstudio/
        run
          docker run -p 80:80 -p 8004:8004 --name ocp \
            opencpu/rstudio 
        apps
          rstudio app
            http://localhost/rstudio/
            opencpu/opencpu
          test app
            http://localhost/ocpu/
      OpenCPU presentation at useR! 2014-kAfVWxiZ-Cc.mp4
        hello world
          curl .../library/stats/R/rnorm/json -H "Content-Type: .." -d '{"n":3, "mean":10, "sd":10}'
          ===
          library(jsonlite)
          args = fromJSON('{"n":3, "mean":10, "sd":10}')
          output = do.call(stats::rnorm, args)
          toJSON(output)
          ===
          rnorm(n=3, mean=10, sd=10)
        what opencpu does
          interoperable http api
          rpc and object management
          io: json, protocol buffers, csv
          parallel/async
          security  policies
          client libraries: js, ruby, ...
        keys and objects
          /Users/mertnuhoglu/Dropbox/public/img/ss-180.png
          curl -v http://../stocks/R/smoothplot -d 'ticker="GOOG"'
          ===
          POST /stocks/R/smoothplot
          Content-Type: application/x-www-form-urlencoded
        other: curl and http content type
          application/x-www-form-urlencoded or multipart/form-data?
            https://stackoverflow.com/questions/4007969/application-x-www-form-urlencoded-or-multipart-form-data
            2 ways to POST:
              application/x-www-form-urlencoded
              multipart/form-data
                to upload files
            these are two MIME types that HTML forms use
              http has no such limitation
            tl;dr
              if you have binary data or big payload
                multipart/form-data
              otherwise
                application/x-www-form-urlencoded
            MIME types are 2 Content-Type headers for POST requests
              purpose: to send a list of key/value pairs
            application/x-www-form-urlencoded
              body of http message: one giant query string
              separated by &
              key/value by =
              non-alphanumeric characters replaced by '%HH'
                thus triples payload for binary files
            multipart/form-data
              each key/value pair: a part in a MIME message
              each part has
                MIME headers like 
                  Content-Type
                  Content-Disposition: this gives a part is name (key)
            other content-types:
              application/json
          curl examples
            https://gist.github.com/joyrexus/524c7e811e4abf9afe56
            ex
              URL=https://foo.io/users/joyrexus/shoes
              Url-encoded
                curl -d "brand=nike" -d "color=red" -d "size=11" $URL
                curl --data "brand=nike&color=red&size=11" $URL
              Multipart
                curl --form "image=@nikes.png" --form "brand=nike" --form "color=red" --form "size=11" $URL
                curl -F "image=@nikes.png" -F "brand=nike" -F "color=red" -F "size=11" $URL
                Change the name field of a file upload part by setting filename=:
                  curl -F "image=@nikes.png;filename={desired-name}.png" -F "brand=nike" -F "color=red" -F "size=11" $URL
                Specify Content-Type by using type=:
                  curl -F "image=@nikes.png;filename={desired-name}.png;type=image/png" -F "brand=nike" -F "color=red" -F "size=11" $URL
          curl post examples
            https://gist.github.com/subfuzion/08c5d85437d5d4f00e58
            -d, --data <data> Send specified data in POST request. Details provided below.
            -F, --form <name=content> Submit form data.
            -X, --request The request method to use.
            common curl options
              request type
                -X POST
                -X PUT
              content type header
                -H "Content-Type: application/x-www-form-urlencoded"
                -H "Content-Type: application/json"
              data
                form urlencoded: -d "param1=value1&param2=value2" or -d @data.txt
                json: -d '{"key1":"value1", "key2":"value2"}' or -d @data.json
            common examples
              POST application/x-www-form-urlencoded
                application/x-www-form-urlencoded is the default:
                  curl -d "param1=value1&param2=value2" -X POST http://localhost:3000/data
                explicit:
                  curl -d "param1=value1&param2=value2" -H "Content-Type: application/x-www-form-urlencoded" -X POST http://localhost:3000/data
                with a data file
                  curl -d "@data.txt" -X POST http://localhost:3000/data
              POST application/json
                curl -d '{"key1":"value1", "key2":"value2"}' -H "Content-Type: application/json" -X POST http://localhost:3000/data
                with a data file
                  curl -d "@data.json" -X POST http://localhost:3000/data
            server.js
              var app = require('express')();
              var bodyParser = require('body-parser');
              app.use(bodyParser.json()); // for parsing application/json
              app.use(bodyParser.urlencoded({ extended: true })); // for parsing application/x-www-form-urlencoded
              app.post('/data', function (req, res) {
                console.log(req.body);
                res.end();
              });
              app.listen(3000);
        get R function's code
          http://../stocks/R/smoothplot/print
        response:
          Location: https://tmp.ocpu.io/xlklmk/
          this contains the output
        state in opencpu
          difference with other R web frameworks
          each request is stateless
            no single, permanent R process
          instead: "functional state"
            each rpc stores object and returns key. no side-effects
            use key to get stored object
        public.opencpu.org/ocpu/test
          /Users/mertnuhoglu/Dropbox/public/img/ss-181.png
          read.csv with a file
            /Users/mertnuhoglu/Dropbox/public/img/ss-182.png
          open Location
            /Users/mertnuhoglu/Dropbox/public/img/ss-183.png
            get dataframe
              /Users/mertnuhoglu/Dropbox/public/img/ss-184.png
            get as json
              /Users/mertnuhoglu/Dropbox/public/img/ss-186.png
            get as csv
              /R/.val/csv
          you can use this Location in other function calls
            /Users/mertnuhoglu/Dropbox/public/img/ss-187.png
            /Users/mertnuhoglu/Dropbox/public/img/ss-189.png
          each key is secret
            so no authentication is needed
        javascript client
          /Users/mertnuhoglu/Dropbox/public/img/ss-191.png
        create your own functions in rstudio id=sr_0006
          create your own functions in rstudio <url:#r=sr_0006>
          build it as a package from inside RStudio
          rstudio
            file > new project > new directory > r package >
              .package name
            rstudio > files > r > mypackage.R
              test = function(x) {
                ..
              }
            rstudio > build > build & reload 
            opencpu:
              ocpu/user/jeroen/library
                /mypackage/R/test
              docker içinde
                /ocpu/user/opencpu/library/pmf/R/hello
            docker içindeki package'ı lokalde değiştirmek
              lokalde değiştir
              dockerda tekrar "build and reload" de
              opencpu'da artık kullanılabilir
          note: custom functions have different path than other libraries if they are not installed into global library
            ref
              install package to global library <url:#r=sr_0005>
            http://localhost:8004/ocpu/user/opencpu/library/vrpdata/R/hello
          ex
            /Users/mertnuhoglu/Dropbox/public/img/ss-192.png
        workflow
          setup opencpu
          write functions
          make them a package
          call them from opencpu
        trying
          free public server
            public.opencpu.org/ocpu
          single dev server
            install.packages("opencpu")
            library(opencpu)
        public package/app on ocpu.io
          github webhook
            public.opencpu.org/ocpu/webhook
          add this webhook to your github/webhooks
          then everytime you push, it will be updated
          your package
            yourname.ocpu.io/pkgname
      OpenCPU API
        https://www.opencpu.org/api.html
        default root path: /ocpu/
        debugging:
          /ocpu/info
            shows sessionInfo
          /ocpu/test
            testing 
        http methods
          GET
            to retrieve a resource
          POST
            for RPC
          GET
            object
              /ocpu/library/MASS/R/cats/json
            file
              /ocpu/library/MASS/NEWS
          POST
            object
              /ocpu/library/stats/R/rnorm
            file
              /ocpu/library/MASS/scripts/ch01.R
        api libraries
          /ocpu/library/{pkgname}/
            r packages
          /ocpu/apps/{gituser}/{reponame}/
            packages in github
        r package api
          /{pkgname}/ support endpoints:
            /info
            /www
              apps included
            /R/
              r objects
            /data/
            /man/
            /man/{topic}/{format}
              format: text, html, pdf
            /html
            /*
              files
        r object api
          /R api: to read objects and call functions
            /R/
              list r objects
            /data/
              list data objects
            /{R|data}/{object}
              if object is a function, it is called using POST
            /{R|data}/{object}/{format}
        r session api
          session: holds resources from a rpc call
            /tmp/{key}/
              list available output
            /tmp/{key}/R
              r objects
            /tmp/{key}/
              graphics/
                graphics/{n}/{format}
              source
                input source code
              stdout
              console
              zip
                dl session as zip
              files/*
                file api in working dir
        output formats for r objects
          print
          json
          csv
          tab
          md
          feather
          png
          pdf
        argument formats for r function calls
          primitives
          json
          r code
          file upload
          temp key
        running scripts and documents
          file.r
          file.tex
          file.md
            knitr::pandoc
          file.rmd
            knitr::knit
        json io rpc
          ex
            /ocpu/library/stats/R/rnorm/json
              sonuç doğrudan json olur, GET request gerekmez
          ex
            curl http://cloud.opencpu.org/ocpu/library/stats/R/rnorm/json \
            -H "Content-Type: application/json" -d '{"n":3, "mean": 10, "sd":10}'
            [
            4.9829,
            6.3104,
            11.411
            ]
          equivalent to
            library(jsonlite)
            args <- fromJSON('{"n":3, "mean": 10, "sd":10}')
            output <- do.call(stats::rnorm, args)
            toJSON(output)
        opencpu apps
          put into /inst/www/ directory
          interfaces with R package functions
        github ci hook
      OpenCPU JS Client
        https://www.opencpu.org/jslib.html
        cdn
          <script src="//cdn.opencpu.org/opencpu-0.4.js"></script>
        apps  
          apps = r packages
          install
            library(devtools)
            install_github(c("stocks", "markdownapp", "nabel"), username="opencpu")
          web pages
            /inst/www/
          to use an app
            opt1
              library(opencpu)
              opencpu$browse("/library/stocks/www")
              opencpu$browse("/library/nabel/www")
            opt2
              https://cloud.opencpu.org/ocpu/library/stocks/www
              https://cloud.opencpu.org/ocpu/library/markdownapp/www
        CORS
          opt1: include web pages in R package
          opt2: call opencpu as web services
            ocpu.seturl()
              specify external opencpu server
            must: all R functions are in a single R package
        stateless functions
          $("#mydiv").rplot( fun[, args][, callback])
            r
              smoothplot = function(arg1, arg2) {
                ..
                plot(..)
              }
            js
              $("#plotdiv").rplot("smoothplot", {arg1: val1, ..})
          ocpu.rpc(fun[, args][, complete])
            js
              var mydata = [1,2,3]
              var req = ocpu.rpc("sd", {x: mydata})
        calls and sessions
          state in opencpu
            session id is return to client
          call an r function
            ocpu.call vs. ocpu.rpc
              stateful equivalent of ocpu.rpc
              difference: callback function
              rpc: callback argument is json object
              call: callback argument is a session object
            ex
              var req = ocpu.call("rnorm", {n:100}, function(session) {
                $("#key").text(session.getKey())
                session.getObject(function(data) {
                  console.log( data.slice(0,3) )
                })
              })
          argument types
            4 types
              js value: converted to R via json
              session: represents R value
              file
              code snippet
          session object
            ref: https://www.opencpu.org/jslib.html
            methods
              getKey():string
              getLoc():string
              getFileURL(path):string
                path: path wrt working directory
              getObject(name[, data][, success]):jqXHR
              getConsole([success]):jqXHR
                ===
                getStdout([success]):jqXHR
              getFile(path[, success]):string
                path: path wrt working directory
            ex
              // http://jsfiddle.net/opencpu/tmqab/
              var req = ocpu.call("rnorm", {n: 100}, function(session){
                $("#key").text(session.getKey());
                // Session ID: x09ebbe143d 
                $("#location").text(session.getLoc());
                // http://public.opencpu.org/ocpu/tmp/x09ebbe143d/
                session.getConsole(function(outtxt){
                  $("#output").text(outtxt);
                });
                // > rnorm(n = 100L, mean = 0.214583808813093)
                // [1]  1.675699563  2.696433712 -0.371826912  1.139382581 -0.043985045
                //retrieve the returned object async
                session.getObject(function(data){
                  alert("Array of length " + data.length + ".\nFirst few values:" + data.slice(0,3));
                  // 100
                  // 1.67 2.69 -0.37
                });
              })
      OpenCPU Server Manual
        https://opencpu.github.io/server-manual/opencpu-server.pdf
        1. What is OpenCPU
          http api'leriyle R fonksiyonlarını kullanmaya izin verir
            ex
              curl http://localhost/ocpu/library/stats/R/rnorm/json --data n=3
                [
                  3.05644,
                  0.38511,
                  1.11983
                ]
          1.3 OpenCPU Apps
            app = R package
              + some web pages
            web pages inside /inst/www/
          1.4 OpenCPU single-user server
            bu geliştirme amaçlı
            running
              install.packages("opencpu")
              library(opencpu)
              ocpu_start_server()
            app yüklemek için:
              ocpu_start_app("rwebapps/markdownapp")
                githubdan çekip yükler
              http://localhost:5656/ocpu/apps/rwebapps/markdownapp
            cloud server: normal sunucu, bu sadece lokalde çalışmak için
        2. Installing OpenCPU cloud server
        3. Managing the OpenCPU cloud server
          3.2. Installing R packages 
            should be installed in global library
              opt1
                wget https://cran.r-project.org/src/contrib/Rcpp_0.12.12.tar.gz
                sudo R CMD INSTALL Rcpp_0.12.12.tar.gz --library=/usr/local/lib/R/site-library
              opt2
                install.packages("Rcpp")
            after restarting they will be available through API
              sudo apachectl restart
              http://localhost:8004/ocpu/library/lubridate
              it restarts automatically after library installation
        4. Testing the OpenCPU API
          4.3 Calling a function
            general
              1. Perform a POST 
                curl http://your.server.com/ocpu/library/stats/R/rnorm -d "n=10&mean=100"
              2. OpenCPU returns locations of the output data
                /ocpu/tmp/x032a8fee/R/.val
                /ocpu/tmp/x032a8fee/stdout
              3. Perform a GET request
                http://your.server.com/ocpu/tmp/x032a8fee/R/.val
            exception: return json directly
              add /json to POST request
                http://../R/norm/json
            input arguments as json
              curl http://your.server.com/ocpu/library/stats/R/rnorm \
              -H "Content-Type: application/json" -d '{"n":10, "mean": 10, "sd":10}'
    mongolite
      ref
        https://jeroen.github.io/mongolite
      install.packages("mongolite")
      library(mongolite)
      ref
        study_dentas_mongodb.R
      !!!tırnak meselesi
        json ifadelerinin dış tırnağı ', iç tırnakları " olmalı, yoksa kabul etmiyor
      connection to a database and collection
        con <- mongo("master_rates", url = "mongodb://myUserAdmin:12345@localhost:27017/demo")
        con$count()
      import / export
        import json
          con <- mongo("temp_rates", url = "mongodb://myUserAdmin:12345@localhost:27017/demo")
          con$import(file("/Users/mertnuhoglu/Dropbox/mynotes/prj/itr/pitr/dentas/mongo_export03_newline_delimited/master_rates.json"))
        import df
          con$insert(df)
      query
        all documents
          mrt = con$find()
        by date
          library(GetoptLong)
          datemillis <- as.integer(as.POSIXct("2015-01-01")) * 1000
          data <- data_collection$find(qq('{"createdAt":{"$gt": { "$date" : { "$numberLong" : "@{datemillis}" } } } }'))
          ===
          query2 = '{"validFromD" : { "$gte" : { "$date" : { "$numberLong" : "1488315600000" } }} }'
      update
        # single document
          con$update('{"depotName": "CORLU"}', '{"$set":{"ratePerExtraDrop": "51"}}')
        # multiple documents
          con$update('{"depotName": "CORLU"}', '{"$set":{"ratePerExtraDrop": "51"}}', multiple = T)
      aggregate
        ex
          stats <- flt$aggregate(
            '[{"$group":{"_id":"$carrier", "count": {"$sum":1}, "average":{"$avg":"$distance"}}}]',
            options = '{"allowDiskUse":true}'
          )
    roxygen2  l
      running
        roxygen2::roxygenise()
        devtools::document()
        ^+D
      ex
        #' Add together two numbers
        #'
        #' @param x A number
        #' @param y A number
        #' @return The sum of \code{x} and \code{y}
        #' @examples
        #' add(1, 1)
        #' add(10, 1)
        add <- function(x, y) {
          x + y
        }
        generated: man/add.Rd
        accessed: ?add help("add") example("add")
    unclassified
      execute a script from another directory / getting path of an executing script
        http://stackoverflow.com/posts/1815743/
        https://stackoverflow.com/posts/3473388/
        #!/usr/bin/env Rscript
        initial.options <- commandArgs(trailingOnly = FALSE)
        file.arg.name <- "--file="
        script.name <- sub(file.arg.name, "", initial.options[grep(file.arg.name, initial.options)])
        script.dirname <- dirname(script.name)
        source(file.path(script.dirname, "source_scripts.R"), chdir = T)
      suppress disable library sourcing loading messages
        suppressMessages(library(x))
    packages packagesr
      basic steps
        ref
          devtools for package building <url:#r=sr_0007>
      devtools for package building id=sr_0007
        devtools for package building <url:#r=sr_0007>
        devtools::create("mypackage")
          create a new package
        devtools::document()
          man/add.Rd
          #+B   build and reload
          updates NAMESPACE (for exported methods)
        devtools::load_all()
          reload your code
          #+L
        devtools::install()
        devtools::build_vignettes()
          build all vignettes
          target dir: inst/doc
        devtools::build()
          create package with vignettes included
        correct sequence
          library("devtools")
          devtools::document()
          devtools::build_vignettes()
          devtools::build()
          devtools::install()
          opt: encapsulate all
            rutils::make_package()
          git commit
          git push
        install/use your package
          library("devtools")
          devtools::install_github("username/packagename")
      github project
        # create R package in RStudio
        git init
        git add .
        git commit -m "First commit"
        git remote add origin https://github.com/mertnuhoglu/study_r_package01.git
        git remote -v
        git push -u origin master
        devtools::install_github("mertnuhoglu/study_r_package01")
      install path
        library: the directory where packages are installed
        get list of all libraries:
          .libPaths()
        by default R installs packages into the first directory in .libPaths()
        overriding
          R CMD INSTALL --library=/path/to/Rlibs
          -l --library
          env variable: R_LIBS R_LIBS_USER
          .libPaths( "/Users/tex/lib/R" ) 
            before installing or put into .Rprofile
      installed packages
        sessionInfo()
        installed.packages()
        check dplyr version
          mat = installed.packages()
          df = as_data_frame(as.table(mat))
          filter(df, Var1 == "dplyr")
      file structure
        DESCRIPTION
        R/
        man/*.Rd: automatically generated by roxygen
        NAMESPACE: automatically generated
        data/
      Creating a package
        rstudio
          file > new project > new directory > r package
        cli
          devtools::create("path/to/package/pkgname")
      package files
        pkgname.Rproj
        .Rbuildignore
          ^.*\.Rproj$
        bundle: .tar.gz file
      using magrittr pipe in external package
        https://stackoverflow.com/questions/27947344/r-use-magrittr-pipe-operator-in-self-written-package#27979637
        DESCRIPTION
          Imports: 
              magrittr (>= 0.7.1)
        opt1: manually add to NAMESPACE
          NAMESPACE
            importFrom(magrittr,"%>%")
        opt2: use roxygen comments for NULL (not a specific function)
          #' @importFrom magrittr "%>%"
          NULL
          2. step
            devtools::document()
      roxygen basics
        1. write comments
          ex:
            #' Add together two numbers
            #'
            #' @param x A number
            #' @return The sum of \code{x} and 
            #' @examples
            #' add(1, 1)
            add <- function(x, y) { x + y }
        2. devtools::document()
        3. ?add, example("add"), help("add")
      library: install path
        a directory containing installed packages
        paths of libraries:
          .libPaths()
      Dependencies
        ex
          Imports: 
            dplyr,
      book: R Packages - Hadley Wickham
        http://r-pkgs.had.co.nz/intro.html
        content
          r code
            r/ directory
            where all R code lives
          package metadata
            DESCRIPTION
          documentation
            how to use functions
            roxygen2 to document functions
          vignettes
            big picture documentation
            uses Rmarkdown and knitr
          tests
            testthat
          namespace
            NAMESPACE
          external data
            data/ directory
            3 ways
              binary data and available to user: data/
                ex: example datasets
              parsed data but not available to user: R/sysdata.rda
                ex: data that your functions need
              store raw data: inst/extdata
            raw data
              put original data files in:
                inst/extdata
              open with: system.file()
                system.file("extdata", "2012.csv", package = "testdat")
                ## [1] "/home/travis/R/Library/testdat/extdata/2012.csv"
              dok et
          compiled code
            src/ directory
            compiled c code
          other components
            demo/
            exec/
            po/
            tools/
          other
            git
            automated checking
              R CMD check
            release
        getting started
          install.packages(c("devtools", "roxygen2", "testthat", "knitr"))
          install.packages(c("testthat", "knitr"))
        Package structure
          naming your package 
          Creating a package
            rstudio
              file > new project > new directory > r package
            cli
              devtools::create("path/to/package/pkgname")
          RStudio Projects
            pkgname.Rproj
              text file like:
                Version: 1.0
                RestoreWorkspace: No
                SaveWorkspace: No
          What is a package
            Source packages
              directory with R/ DESCRIPTION etc.
            Bundled package
              .tar.gz file
              .Rbuildignore
                ^.*\.Rproj$
              Binary packages
              Installed packages
                decompressed into a package library
                installation done with: R CMD INSTALL
              In memory packages
                library(x)
          What is a library
            a directory containing installed packages
            note:
              library = directory containing packages
            paths of libraries:
              .libPaths()
              # osx:
                # [1] "/Users/mertnuhoglu/Library/R/3.3/library"                       "/Library/Frameworks/R.framework/Versions/3.3/Resources/library"
              # opencpu:
                # [1] "/usr/local/lib/R/site-library" "/usr/lib/R/site-library"
                # [3] "/usr/lib/R/library"
            lapply(.libPaths(), dir)
            When you use library(pkg) or require(pkg) to load a package, R looks through each path in .libPaths() to see if a directory called pkg exists.
        R Code
          reload your code
            devtools::load_all()
            #+L
          organizing functions
            extremes: 
              all functions in one file
              one file per function
            file extension: .R
            no subdirectories: use prefix
              abc-*.R
            split file when you can't remember where some function resides
            jump to definition: 
              F2
              ^. Code > Go to file/function
            navigate back: #F9
          Code style
            automatic formatting (lint): formatR
              install.packages("formatR")
              formatR::tidy_dir("R")
            opt lint: lintr
              install.packages("lintr")
              lintr::lint_package()
            Object names
            Spacing
              spaces around all infix ops =+-<- etc
                except : :: :::
              space before left parantheses, except function call
                if (debug)
                plot(x, y)
            Curly braces
              new line after {
              } in own line
            Line length
            Indentation
              2 spaces per tab
              second line where definition starts
                long_function_name <- function(a = "a long argument", 
                                               b = "another argument",
                                               c = "another long argument") {
            Assignment
              use <- not =
            Commenting guidelines
              # space
              # Part 1 ---------
          Top level code
            don't execute code at top level
            only define functions or objects
            ex
              foo package contains
                library(ggplot2)
                show_mtcars <- function() {
                  qplot(mpg, wt, data = mtcars)
                }
              If someone tries to use it:
                library(foo)
                show_mtcars()
              this won't work because library(ggplot2) is executed when package is built, not when it's loaded
            The R Landscape
              never change global R landscape
                don't use library()
                  these modify search path
                  package requirements should be installed and sourced separately
                don't use source()
                  it modifies current environment
                  rely on devtools::load_all()
                    it sources all files in R/
            When you do need side effects
              two specifal functions: .onLoad() .onAttach()
                called when packages is loaded and attached
                use .onLoad() in general
              common uses of .onLoad()
                display an informative message when package loads
                set custom options
                  .onLoad <- function(libname, pkgname) {
                    op <- options()
                    op.devtools <- list(
                      devtools.path = "~/R-dev",
                      devtools.install.args = "",
                connect java, c etc
                  ex
                    rJava::.jpackage()
                register vignette engines
              .onLoad() saved in: zzz.R
              .onUnload()
            S4 classes
          CRAN notes
        Package metadata
          DESCRIPTION file
          devtools::create("mypackage")
          ex
            Package: mypackage
            Title: What The Package Does (one line, title case required)
          Dependencies
            packages needed
            ex
              Imports: 
                dplyr,
                ggvis
              Suggests:
                rlist
            how to add to Imports:
              devtools::use_package("dplyr")
            package::function()
              explicitly refer to external functions
            Suggests: optional
              check if the package is available
                if (!requireNamespace("pkg" ...)
          Versioning
            Imports:
              ggvis (>= 0.2)
          Other dependencies
            Depends:
              deprecated
            LinkingTo
              for C code
            Enhances
              enhanced by your package
              don't use
          Title and description
            Title: one line description
            Description
            Authors@R
              r code
              Authors@R: person("Hadley", "Wickham", email = "hadley@rstudio.com",
                role = c("aut", "cre"))
          License
            MIT
              license must always be distributed with the code
            GPL-2 GPL-3
              copy left
              whole bundle should be GLP compatible
            CC0
              you give all your rights
          Version
            major.minor.patch
              1.2.3
            major.minor.patch.indevelopment
              0.0.0.9000
              first version
          Other components
        Object Documentation
          intro
            accessed by ? or help()
            like a dictionary
              you know the word
              learn what that word means
            vignette
              what the right word is
            man/ directory
              .Rd files
              loosely based on latex
              roxygen2 generates them
          documentation workflow
            steps
              1. add roxygen comments to .R files
              2. run devtools::document()
              3. preview doc with ?
              4. rinse and repeat
            roxygen comments
              #' Add together two numbers
              #'
              #' @param x A number
              #' @return The sum of \code{x} and 
              #' @examples
              #' add(1, 1)
              add <- function(x, y) { x + y }
            devtools::document() generates: 
              man/add.Rd
            ?add, help("add", example("add")
              generates HTML
          Alternative documentation workflow
            way to show links between pages
              1. roxygen comments
              2. "Build & Reload" or #+B
              3. ?
              4. rinse and repeat
          Roxygen comment
            lines preceding called: block
            tags: @tagName details
            @@: literal @
            introduction: first sentence (must)
              one line
            second paragraph: description (must)
            third and then: details
            \code{} \link{}
              formatting commands
              lines less than 80 chars
                #+/: reflow comment
            @section
              break details into chunks
              #' @section Warning:
            @seealso
              point useful resourcs
                \url{http://www.r-project.org}
                \code{\link{functionname}}
                \code{\link[packagename]{functionname}}
              @family
                family of related functions
            @aliases alias1 alias2
              ?alias1
            @keywords keyword1 keyword2
              not useful
          Documenting functions
            @param name Description.
            @param x,y Numeric vectors.
            @examples
              run automatically as part of R CMD check
              code that includes error:
                \dontrun{}
            @return description
          Documenting packages
            help for package 
              access
                package?foo
              @docType package
              put into: <package-name>.R
          Documenting classes
            S3
            S4
            RC
          Special characters
            @: @@
            %: \%
            \: \\
          Do repeat yourself
            DRY is not valid here
            Inheriting parameters from other functions
              @inheritParams foo
            Documenting multiple functions
          Text formatting
            Character
              \emph{italics}
              \strong{bold}
              \code{..}
              \preformatted{}
            Links
              \code{\link{function}}
              \link[=dest]{name}
              \url{..}
              \email{..}
            Lists
              \enumerate{
                \item ..
              }
              \itemize{ ..}
            Mathematics
              \eqn{a + b}
            Tables
              \tabular{}
        # Vignettes
          intro
            browseVignettes()
              see all installed vignettes in browser
            browseVignettes("packagename")
            vignette(package="dplyr")
              list vignettes in console
            consists of
              source file
              HTML/PDF
              R code
            vignette(x)
              read a specific one
            edit(vignette(x))
              see its code
          Vignette workflow
            1. devtools::use_vignette("my-vignette")
              creates vignettes/
              Adds dependencies to DESCRIPTION
              Drafts a vignette
                vignettes/my-vignette.Rmd
            2. modify file: vignettes/my-vignette.Rmd
            3. RStudio #+K "Knit"
              rmarkdown::render("vignettes/my-vignette.Rmd")
          Metadata
            first few lines
              ---
              title: "Vignette Title"
              author: "Vignette Author"
              vignette: >
                %\VignetteIndexEntry{Vignette Title}
            written in yaml
              >: literal text not yaml
          Markdown
            code
              ```{r}
              ```
          Knitr
            ex
              ```{r}
              # Add two numbers together
              add <- function(a, b) a + b
              add(10, 20)
              ```
              This generates the following Markdown:
              ```r
              # Add two numbers together
              add <- function(a, b) a + b
              add(10, 20)
              ## [1] 30
              ```
            tables
              ```{r, results = "asis"}
              pander::pandoc.table(iris[1:3, 1:4])
          Development cycle
            run chunk
              #!C
            run entire document
              #+K
            build all vignettes
              devtools::build_vignettes()
            create package with vignettes included
              devtools::build()
          Advice
            If you’re thinking without writing, you only think you’re thinking. — Leslie Lamport
            Style, J. Williams
          Organization
          CRAN notes
        Testing
          intro
            workflow upto now
              1. write function
              2. load it #+L or devtools::load_all()
              3. experiment in console
          Test workflow
            setup
              devtools::use_testthat()
              this will
                1. create tests/testthat/
                2. Adds testthat to DESCRIPTION
                3. create tests/testthat.R
            workflow 
              1. modify code
              2. test package #+T or devtools::test()
            testing output
              Expectation: ....
              Variance: ...123.45.
              "." a passed test
              number: failed test
            Test structure
              names start with "test"
              inside: tests/testtthat/
              ex
                context("file name")
                test_that("str_length is number of characters", {
                  expect_equal(str_length("a"), 1)
                  expect_equal(str_length("ab"), 2)
                  expect_equal(str_length("abc"), 3)
                })
            Writing tests
            Skipping a test
              skip("api not available")
        Namespace
          Motivation
            :: operator
            two ways to make packages self-contained:
              imports
              exports
          Search path
            search()
              global environment
              attached packages
          The NAMESPACE
            roxygen2 can generate NAMESPACE file
          Workflow
            1. add roxygen comments
            2. devtools::document() or #+D
            3. check NAMESPACE and run tests to check
          Exports
            to make a function usable outside of your package
            by default: devtools::create() exports everything
            #' @export
            foo <- function(..)
          Imports
            which external functions can be used without ::
            best to be explicit: pkg::fun()
        Installed files (inst/)
          intro
            opposite of .Rbuildignore
              .Rbuildignore: remove files
              inst/: add files to top level
            to find a file in `inst/`
              system.file("extdata", "mydata.csv", package = "mypackage")
            ex
              system.file("bash/build_datamodel_sdb.sh", package = "yumltordbschema")
          Other languages
            put scripts of java, perl, bash into inst/
              inst/python, inst/bash
            document in: DESCRIPTION > SystemRequirements
        External data
        Automated checking
          intro
            R CMD check
          workflow
            run devtools::check() or #+E 
              wraps R CMD check
              runs devtools::document()
              bundles package
          Checks
      article: R package primer - kbroman.org
        http://kbroman.org/pkg_primer/
        Building and installing
          opt1: console
            go to parent directory
            R CMD build mypackage
              * building ‘package01_0.0.0.9000.tar.gz’
            R CMD INSTALL mypackage.tar.gz
              R CMD INSTALL package01_0.0.0.9000.tar.gz
          opt2: devtools
            # go to package directory
            library(devtools)
            build()
              builds tar.gz
            install()
      article: Writing an R package from scratch - Hilary Parker
        https://hilaryparker.com/2014/04/29/writing-an-r-package-from-scratch/
        0. load libraries
          install.packages("devtools")
          library("devtools")
          devtools::install_github("klutometis/roxygen")
          library(roxygen2)
        1. create package directory
          setwd("parent_directory")
          create("cats")
        2. add function files
        3. ad documentation in roxygen comments
        4. build documentation
          setwd("./cats")
          document()
        5. install
          setwd("..")
          install("cats")
        6. push to github
    reprex: reproducible example codes
      install.packages("reprex")
      http://reprex.tidyverse.org
      library(reprex)
      opt1: copy some code into clipboard
        reprex()
        ex:
          (y <- 1:4)
          mean(y)
        out:
          ``` r
          (y <- 1:4)
          #> [1] 1 2 3 4
          mean(y)
          #> [1] 2.5
          ```
      opt2: from expression
        reprex(mean(rnorm(10)))
      opt3: from character vector
        reprex(input = "mean(rnorm(10))\n")
        reprex(input = "> mean(rnorm(10))\n")
      opt4: from file
        reprex(input = "my_reprex.R") 
      opt5: RStudio addin text
      for stackoverflow
        reprex(..., venue = "so")
      for runnable R script
        reprex(..., venue = "R")
    usethis
      https://www.tidyverse.org/articles/2017/11/usethis-1.0.0/
      install.packages("usethis")
      library(usethis)
      Create a new package -------------------------------------------------
        tmp <- file.path(tempdir(), "mypkg")
        create_package(tmp)
        #> Changing active project to mypkg
        #> ✔ Creating 'R/'
        #> ✔ Creating 'man/'
        #> ✔ Writing 'DESCRIPTION'
        #> ✔ Writing 'NAMESPACE'
      create/edit a script file in R/:
        use_r("foo")
        #> ● Edit 'R/foo.R'
      unit testing
        use_test("foo")
        #> ✔ Adding 'testthat' to Suggests field in DESCRIPTION
        #> ✔ Creating 'tests/testthat/'
        #> ✔ Writing 'tests/testthat.R'
        #> ✔ Writing 'tests/testthat/test-foo.R'
        #> ● Edit 'tests/testthat/test-foo.R'
      dependency/library
        use_package("ggplot2")
        #> ✔ Adding 'ggplot2' to Imports field in DESCRIPTION
        #> ● Refer to functions with `ggplot2::fun()`
        opt
          use_dev_package()
      use_roxygen_md() 
        sets up roxygen2 and enables markdown mode 
      use_package_doc() 
        creates a skeleton documentation file for the complete package
      use_readme_rmd() 
        creates a README.Rmd
      use_news_md() 
        creates a basic NEWS.md 
      use_vignette("vignette-name") 
        sets you up for success by configuring DESCRIPTION and creating a .Rmd template in vignettes/
      licenses
        use_mit_license("Mert Nuhoglu")
        use_apl2_license()
        use_gpl3_license()
        use_cc0_license()
      init git
        use_git()
        #> ✔ Initialising Git repo
        #> ✔ Adding '.Rhistory', '.RData', '.Rproj.user' to './.gitignore'
        #> ✔ Adding files and committing
      publish to github
        use_github()
      browsing config files
        R
          edit_r_profile()
          edit_r_environ()
          edit_r_makevars()
        git
          edit_git_config()
          edit_git_ignore()
    datapasta
      https://github.com/MilesMcBain/datapasta
      ref
        <url:file:///~/projects/study/r/refcard_datapasta.Rmd>
      install.packages("datapasta")
      copy paste table/vector data
      ex: terminal
        library(magrittr); library(datapasta)
        mtcars %>% head() %>% dpasta()
        #> data.frame(
        #>          mpg = c(21, 21, 22.8, 21.4, 18.7, 18.1),
        #>          cyl = c(6, 6, 4, 6, 8, 6),
        #>         disp = c(160, 160, 108, 258, 360, 225),
        #> )
      ex: copy table from excel
        df_paste()
      functions
        dpasta()
        dmdclip()
          for md. preceded by 4 spaces
        tribble_paste()
        vector_paste()
        vector_paste_vertical()
    blogdown
      install.packages("blogdown")
      cmd
        library(blogdown)
        blogdown::serve_site()
        blogdown::new_site()
        blogdown::new_post()
        blogdown::build_site()
      hugo + blogdown
        migrate hugo site to blogdown
          rename config.yml as config.yaml
        copy files to tech/ directory
          sync_rmd_to_blog_dirs
          deprecated:
            rsync -a --files-from=.to_blog_tech.tsv ./ /Users/mertnuhoglu/projects/jekyll/mertnuhoglu.com/content/tech
        yaml header
          ---
          title: "Example: R Time and Date"
          date: '`r strftime(Sys.time(), "%Y-%m-%dT%H:%M:%S+03:00")`'
          draft: false
          description: ""
          tags:
          categories: examples R 
          type: post
          url:
          author: "Mert Nuhoglu"
          output: rmarkdown::html_document
          blog: mertnuhoglu.com
          resource_files:
          -
          ---
        deploy
          ./deploy.sh
      conventions
        links: [ex_sql_generation.Rmd](/tech/ex_sql_generation/ "SQL Generation")
    next
      read funs
      http://rmaps.github.io/
      http://gis.stackexchange.com/questions/3310/what-is-the-most-useful-spatial-r-trick


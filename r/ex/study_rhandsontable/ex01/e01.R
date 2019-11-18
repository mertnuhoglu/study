library(magrittr)
DF = data.frame(integer = 1:10,
                   numeric = rnorm(10),
                   logical = rep(TRUE, 10), 
                   character = LETTERS[1:10],
                   factor = factor(letters[1:10], levels = letters[10:1], 
                                   ordered = TRUE),
                   factor_allow = factor(letters[1:10], levels = letters[10:1], 
                                         ordered = TRUE),
                   date = seq(from = Sys.Date(), by = "days", length.out = 10),
                   stringsAsFactors = FALSE)

rhandsontable::rhandsontable(DF, width = 600, height = 300) %>%
  rhandsontable::hot_col("factor_allow", allowInvalid = TRUE)


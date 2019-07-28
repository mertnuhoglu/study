library(dplyr)
marks_txt = "clips/marks.txt"
m0 = readLines(marks_txt)
m1 = stringr::str_subset(m0, "^\\d+$", negate = T)
m2 = stringr::str_replace_all(m1, "<\\/?\\w+>", "")
m3 = stringr::str_replace_all(m2, '"', "'")
m4 = paste0(m3, collapse = "\n")
m5 = stringr::str_subset(m4, "^\\d\\d:\\d\\d:\\d\\d", negate = T)
m5 = stringr::str_replace_all(m4, "(^([-()# .':;,]|\\w).*\\n)+", "\\0")




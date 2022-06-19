# Source: [skranz/dbmisc: Tools for working with databases in R](https://github.com/skranz/dbmisc)

library(dbmisc)
library(clipr)

writeClipboard <- function(x = .Last.value) {
  clipr::write_clip(x)
}

df = data.frame(a=1:5,b="hi",c=Sys.Date(),d=Sys.time())
dbmisc::schema.template(df,"mytable")

# girdi: df
# çıktı: bu df'ye uygun yaml scheması

# mytable:
#   table:
#   a: INTEGER
#   b: TEXT
#   c: DATE
#   d: DATETIME
# index:
#   - a # example index on first column

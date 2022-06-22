# Source: [skranz/dbmisc: Tools for working with databases in R](https://github.com/skranz/dbmisc)

library(dbmisc)
library(clipr)

writeClipboard <- function(x = .Last.value) {
  clipr::write_clip(x)
}

db <- dbConnectSQLiteWithSchema("data/userdb.sqlite")
db <- set.db.schema(db, schema.file="dbmisc01/userdb.yaml")

dbmisc::schema.template(df,"mytable")

# df'den yaml schemasını oluşturur

# mytable:
#   table:
#   a: INTEGER
#   b: TEXT
#   c: DATE
#   d: DATETIME
# index:
#   - a # example index on first column

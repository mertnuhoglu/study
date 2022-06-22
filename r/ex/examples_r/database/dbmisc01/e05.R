# Source: [skranz/dbmisc: Tools for working with databases in R](https://github.com/skranz/dbmisc)

library(dbmisc)

# Getting data with dbGet
# dbGet

# to get the database connection:
get.userdb = function(db.dir = getwd()) {
  db = getOption("userdb.connection")
  if (is.null(db)) {
    db.file = file.path(db.dir, "userdb.sqlite")
    db = dbConnectSQLiteWithSchema(db.file, schema.file = "dbmisc01/userdb.yaml")
    options(userdb.connection = db)
  }
  db
}
is <- inherits

db <- get.userdb(db.dir = "data")

# output: dataframe
dat = dbGet(db,table="user", list(userid="user1"))
dat
#   userid          email age female             created descr
# 1  user1 test@email.com  47   TRUE 2022-06-19 12:23:39  <NA>

# output: SQL only
# run = FALSE
dbGet(db,table="user", list(userid="user1"), run = FALSE)
## [1] "SELECT * FROM user WHERE userid = :userid"

## joinby
dbGet(db,c("course","coursestud"), list(courseid="course1"),
      fields = "*, coursestud.email", joinby=c("courseid"), run = FALSE)
## [1] "SELECT *, coursestud.email FROM course INNER JOIN coursestud USING(courseid) WHERE courseid = :courseid"
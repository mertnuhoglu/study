# Source: [skranz/dbmisc: Tools for working with databases in R](https://github.com/skranz/dbmisc)

library(dbmisc)

# Inserting data
# dbInsert

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
new_user = list(created=Sys.time(), userid="user1",age=47, female=TRUE, email="test@email.com", gender="female")
dbInsert(db,table="user", new_user)
# inserts into db

dbInsert(db,table="user", new_user,run = FALSE)
# outputs sql
# [1] "insert into user values (:userid, :email, :age, :female, :created, :descr)"
# Source: [skranz/dbmisc: Tools for working with databases in R](https://github.com/skranz/dbmisc)

library(dbmisc)
library(clipr)

is <- inherits
writeClipboard <- function(x = .Last.value) {
  clipr::write_clip(x)
}

db.dir = "data"
dbmisc::dbCreateSQLiteFromSchema(schema.file="dbmisc01/coursedb.yaml", db.dir=db.dir, db.name="coursedb.sqlite")

get.coursedb = function(db.dir = getwd()) {
  db = getOption("coursedb.connection")
  if (is.null(db)) {
    db.file = file.path(db.dir, "coursedb.sqlite")
    db = dbConnectSQLiteWithSchema(db.file, schema.file = "dbmisc01/coursedb.yaml")
    options(coursedb.connection = db)
  }
  db
}

db <- get.coursedb(db.dir = "data")

# a01: insert each list one by one

new_course = list(courseid="1", title="mat101", year=2022)
dbInsert(db,table="course", new_course)

new_stud = list(email="stud01@gmail.com", name="John Ali", country="Turkey", city="Istanbul", create_time=Sys.time())
dbInsert(db,table="student", new_stud)

new_cs = list(courseid="1", email="stud01@gmail.com")
dbInsert(db,table="coursestud", new_cs)

# a02: use dataframe to insert rows

c02 = data.frame(courseid=c("2", "3"), title= c("mat102", "phys101"), year=2022)
lapply(1:nrow(c02), function(row, df) {
  dbInsert(db,table="course", as.list(df[row, ]))
}, c02)

# a02b: use dataframe with a convert to list of rows functions

df_to_list_of_rows <- function(df) {
  lapply(1:nrow(df), function(row, d) {
    as.list(d[row, ])
  }, df)
}
c03 <- data.frame(courseid=c("4", "5"), title= c("mat102", "phys101"), year=2022)
l03 <- df_to_list_of_rows(c03)
l03a <- lapply(l03, function(lrow, table) {
  dbInsert(db,table=table, lrow)
}, "course")

# a02b2:

c04 <- data.frame(courseid=c("6", "7"), title= c("mat102", "phys101"), year=2022)
l04 <- df_to_list_of_rows(c04)
l04a <- lapply(l04, dbInsert(lrow, table) {
  dbInsert(db,table=table, lrow)
}, "course")


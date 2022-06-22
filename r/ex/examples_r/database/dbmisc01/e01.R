# Source: [skranz/dbmisc: Tools for working with databases in R](https://github.com/skranz/dbmisc)

library(dbmisc)

db.dir = "data"
dbmisc::dbCreateSQLiteFromSchema(schema.file="dbmisc01/userdb.yaml", db.dir=db.dir, db.name="userdb.sqlite")

# girdi: userdb.yaml schema tanım dosysaı
# çıktı: userdb.sqlite dosyasını ve sql schemasını oluşturur

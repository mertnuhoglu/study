library(dbmisc)
db.dir = "data"
dbmisc::dbCreateSQLiteFromSchema(schema.file="dbmisc01/userdb.yaml", db.dir=db.dir, db.name="userdb.sqlite")

# çıktı: userdb.sqlite dosyasını ve schemasını oluşturur

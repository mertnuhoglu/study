
## r: connect postgresql

··  con &lt;- {{c1::DBI}}::dbConnect(RPostgreSQL::PostgreSQL() <br>
····  , user = Sys.getenv("SUPER—USER") <br>
····  , password = Sys.getenv("SUPER—USER—PASSWORD") <br>
····  , dbname = "app" <br>
····  , host = "localhost" <br>
····  , port = "5432" <br>
··  ) <br>
··  df = DBI::{{c2::dbGetQuery}}({{c3::con}}, "SELECT * FROM data.client") <br>

%

%

clozeq

---


# [[20241012-203712-test.R]]

# Test if pairs are inserted

rconn <- rutils::redis()

key <- "cf5b542b-75b7-4d70-96af-b98d30ab686b"
rconn$GET(key)
# [1] "pages/prvx/20240220-prvx.md"

key <- "fb98313a-b053-48ad-83bd-3e1a9ccadc26"
rconn$GET(key)
# [1] "/Users/mertnuhoglu/projects/study/clj/book_clojure_practicalli.md"

# [[20241012-redis-hset.R]]


rconn <- rutils::redis()
rconn <- redux::hiredis()

rconn$HSET("hash_name", "field_name", "field_value")

# You can also set multiple fields at once
rconn$HSET("user:1000", "age", "30")
rconn$HSET("user:1000", "city", "New York")

refid <- "f9d46173-7733-46d2-86de-9b9980fc9334"
fpath <- "/Users/mertnuhoglu/projects/myrepo/global_doc.otl"
lnum <- "39"
key <- glue::glue("refid:{refid}")
rconn$HSET(key, "fpath", fpath)
rconn$HSET(key, "lnum", lnum)

r1 <- rconn$HGETALL(key)
str(r1)
# List of 4
#  $ : chr "fpath"
#  $ : chr "/Users/mertnuhoglu/projects/myrepo/global_doc.otl"
#  $ : chr "lnum"
#  $ : chr "39"

r2 <- redux::from_redis_hash(rconn, key)
str(r2)
 # Named chr [1:2] "/Users/mertnuhoglu/projects/myrepo/global_doc.otl" "39"
 # - attr(*, "names")= chr [1:2] "fpath" "lnum"

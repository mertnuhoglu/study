# [[20241013-001754-test.R]]

rconn <- redux::hiredis()
refid <- "f9d46173-7733-46d2-86de-9b9980fc9334"
key <- glue::glue("refid:{refid}")

r2 <- redux::from_redis_hash(rconn, key)
# Named chr [1:2] "/Users/mertnuhoglu/projects/myrepo/global_doc.otl" "39"
# - attr(*, "names")= chr [1:2] "fpath" "lnum"


#!/usr/bin/env Rscript
library("dplyr")
f0 = list.files(".") %>%
	stringr::str_subset("mp4$") %>%
	stringr::str_sub(1, -5)
f1 = sprintf("%s.mp4", f0)
f2 = sprintf("%s.srt", f0)
d0 = tibble::tibble(
	dir = f0
	, mp4 = f1
	, srt = f2
)
for (i in seq_len(nrow(d0))) {
	r = d0[i, ]
	dir.create(r$dir)
	file.rename( r$mp4, sprintf("%s/%s", r$dir, r$mp4))
	file.rename( r$srt, sprintf("%s/%s", r$dir, r$srt))
}



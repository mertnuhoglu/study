library(dplyr)
library(readr)
library(glue)

process = function() {
	gr = read_tsv("git_repos.tsv") %>%
		filter(!is.na(local_dir)) %>%
		mutate(line = glue("export GIT_REPO_NAME={git_repo_name}
											 cd {local_dir}"))
	writeLines(gr$line, "gen/script.sh")
}

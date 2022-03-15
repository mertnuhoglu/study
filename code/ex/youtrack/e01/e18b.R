# Project Progress Report for all Projects

library(httr)
library(jsonlite)
library(lubridate)
library(tidyverse)
library(knitr)
library(dplyr)
library(glue)
library(googlesheets4)
library(readr)

done <- c("To Verify", "Verified", "Done")

config <- httr::add_headers(authorization = glue("Bearer perm:{Sys.getenv('YOUTRACK_AUTH')}"))
h2 <- httr::accept_json()
h3 <- httr::content_type_json()
config$headers <- c(config$headers, h2$headers, h3$headers)
GET_auth <- purrr::partial(httr::GET, config = config)
POST_auth <- purrr::partial(httr::POST, config = config)

get_issues <- function(yt_project_id, phase) {
  # note: {yt_project_id}
  url <- glue::glue("https://youtrack.layermark.com/api/issues?fields=id,idReadable,summary,customFields($type,id,projectCustomField($type,id,field($type,id,name)),value($type,name))&query=project:%20{yt_project_id}%20Type:%20%7BRequirement%20Implementation%7D%20Requirement%20ID:%20*%20Phase:Phase-{phase}%20")
  resp <- GET_auth(url)
  issues <- fromJSON(rawToChar(resp$content))
}

get_indexes <- function(issues) {
  nms <- issues$customFields[[1]]$projectCustomField$field$name
  idx_rqt <- match("Requirement ID", nms)
  idx_state <- match("State", nms)
  list(idx_rqt = idx_rqt, idx_state = idx_state)
}

get_requirement_ids <- function(issues, idx_rqt) {
  lapply(1:nrow(issues), function(i, df) {
    rqt_id <- df$customFields[[i]]$value[[idx_rqt]]
  }, issues) %>%
    unlist()
}

get_states <- function(issues, idx_state) {
  lapply(1:nrow(issues), function(i, df) {
    rqt_id <- df$customFields[[i]]$value[[idx_state]]$name
  }, issues) %>%
    unlist()
}

add_youtrack_attributes <- function(issues, rqt_ids, rqt_states) {
  issues %>%
    dplyr::select(idReadable, summary) %>%
    dplyr::mutate(requirement_id = rqt_ids) %>%
    dplyr::mutate(state = rqt_states)
}


filter_done_issues <- function(issues) {
  issues %>%
    dplyr::filter(state %in% done)
}

get_requirements <- function(gdrive_rdb_id, phase) {
  googlesheets4::gs4_deauth()
  url_rdb_rqt <- glue::glue("https://docs.google.com/spreadsheets/d/{gdrive_rdb_id}")
  rqt <- googlesheets4::read_sheet(url_rdb_rqt, sheet = "Requirement", na = "N/A") %>%
    dplyr::filter(invalid == 0) %>%
    dplyr::filter(phase == phase)
  rqt
}

get_progress_stats <- function(yt_project_id, gdrive_rdb_id, phase) {
  issues <- get_issues(yt_project_id, phase)
  if (rutils::is.blank(issues)) {
    return(NA)
  }
  idxs <- get_indexes(issues)
  rqt_ids <- get_requirement_ids(issues, idxs$idx_rqt)
  states <- get_states(issues, idxs$idx_state)
  issues02 <- add_youtrack_attributes(issues, rqt_ids, states)
  issues_done <- filter_done_issues(issues02)
  rqts <- get_requirements(gdrive_rdb_id, phase)
  no_done_unique_rqt <- unique(issues_done$requirement_id) %>% length()
  total_unique_rqt <- nrow(rqts)
  no_done_unique_rqt / total_unique_rqt
}

main <- function() {
  projects <- readr::read_tsv("data/projects.tsv")
  stats <- map_dbl(1:nrow(projects), function(i, df) {
    p01 <- df[i, ]
    get_progress_stats(p01$yt_project_id, p01$gdrive_rdb_id, p01$phase)
  }, projects)
  stats02 <- stats %>%
    tibble(progress_rate = .) %>%
    mutate(yt_project_id = projects$yt_project_id)
  stats02
}

comment <- function() {
  # rich comment
  # kurcalamak için yazdığın kodları buraya koyuyorsun

  yt_project_id <- p01$yt_project_id
  gdrive_rdb_id <- p01$gdrive_rdb_id

  # for loop vs lapply
  # eğer side-effect varsa for loop kullanalım +
  # eğer loopun sonunda bir değer döndürmemiz gerekiyorsa, lapply kullanalım
  # lapply yerine de onun muadili purrr::map() fonksiyonlarını tercih edelim

  # side-effect nedir?
  # bizim fonksiyonlarımızın bir kısmı matematiksel fonksiyonlar gibi
  # f(x) = 2x
  # f fonksiyonuna verdiğimiz girdi aynı olduğu sürece, her zaman aynı çıktıyı alırız
  # bu durumda bu fonksiyon side-effect yok demektir
  # ama bazen bir fonksiyonun çıktısı (sonucu) sadece girdisine bağlı olmayabilir
  # bu gibi durumlarda side-effect var diyoruz

  # loopa çevirme:
  get_progress_stats(p01$yt_project_id, p01$gdrive_rdb_id)
  p01 <- projects[2, ]
  get_progress_stats(p01$yt_project_id, p01$gdrive_rdb_id)
  p01 <- projects[3, ]
  get_progress_stats(p01$yt_project_id, p01$gdrive_rdb_id)
  p01 <- projects[4, ]
  get_progress_stats(p01$yt_project_id, p01$gdrive_rdb_id)
  p01 <- projects[5, ]
  get_progress_stats(p01$yt_project_id, p01$gdrive_rdb_id)

  # ->

  stats <- map_dbl(1:nrow(projects), function(i, df) {
    p01 <- df[i, ]
    get_progress_stats(p01$yt_project_id, p01$gdrive_rdb_id)
  }, projects)


  {
    # hydrant neden inf olarak geliyor?
  }
}
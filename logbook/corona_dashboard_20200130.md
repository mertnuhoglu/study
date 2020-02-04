
# Corona Dashboard 20200131 

## yeni proje

``` bash
cd /Users/mertnuhoglu/projects/study/logbook/ex/corona_dashboard_20200130
R
``` 

``` bash
library(usethis)
usethis::create_package("coronadash")
usethis::use_package("dplyr")
usethis::use_package("stringr")
usethis::use_package("tidyr")
usethis::use_package("shiny")
``` 

## önce bir shiny app oluştur

Ref: `App Template <url:/Users/mertnuhoglu/projects/study/r/shiny/study_shiny.Rmd#tn=App Template>`

``` bash
cp ~/projects/study/r/shiny/ex/study_shiny/app_template/app.R /Users/mertnuhoglu/projects/study/logbook/ex/corona_dashboard_20200130/ex01
``` 

## shiny örneklerini bir gözden geçir


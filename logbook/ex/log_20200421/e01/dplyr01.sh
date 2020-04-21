cat mtcars.csv | dplyr filter -c "mpg == 21"
cat mtcars.csv | dplyr filter -c "mpg < 11"
dplyr select --file mtcars.csv -c cyl | head -n 6

cat mtcars.csv | \
   dplyr mutate "cyl2 = 2 * cyl"  | \
   dplyr filter "cyl == 8" | \
   dplyr kable


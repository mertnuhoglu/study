d0 = dplyr::tibble(a = 1:3, b = 10:12)
d1 = dplyr::tibble(c = letters[1:3], d = letters[10:12])
x0 = list(d0, d1)

WriteXLS::WriteXLS(x0, "file02.xlsx", SheetNames = c("d0", "d1"))

d0a = readxl::read_excel("file02.xlsx", sheet = "d0")
d1a = readxl::read_excel("file02.xlsx", sheet = "d1")
print(d1a)


# [[20241012-import-tsv-into-redis-1012-181939.R]]

# Install and load required packages
if (!requireNamespace("redux", quietly = TRUE)) install.packages("redux")
if (!requireNamespace("readr", quietly = TRUE)) install.packages("readr")
library(redux)
library(readr)

# Connect to Redis
redis_conn <- redux::hiredis(
  host = "localhost",
  port = 6379,
  db = 0
)

# Read the TSV file
tsv_data <- readr::read_tsv("your_file.tsv", col_names = FALSE)

# Iterate through each row and set key-value pairs in Redis
for (i in 1:nrow(tsv_data)) {
  key <- tsv_data[[1]][i]
  value <- tsv_data[[2]][i]
  redis_conn$SET(key, value)
}

# Print completion message
print("Import completed successfully.")

# Close the Redis connection
redis_conn$disconnect()

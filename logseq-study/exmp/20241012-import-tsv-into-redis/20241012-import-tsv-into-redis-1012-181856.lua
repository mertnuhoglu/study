-- [[20241012-import-tsv-into-redis-1012-181856.lua]]

local redis = require "redis"

-- Connect to Redis
local client = redis.connect('127.0.0.1', 6379)

-- Function to split a string by delimiter
local function split(str, delimiter)
    local result = {}
    for match in (str..delimiter):gmatch("(.-)"..delimiter) do
        table.insert(result, match)
    end
    return result
end

-- Open the TSV file
local file = io.open("your_file.tsv", "r")
if not file then
    print("Failed to open the file")
    return
end

-- Read and process each line
for line in file:lines() do
    local columns = split(line, "\t")
    if #columns >= 2 then
        local key = columns[1]
        local value = columns[2]
        client:set(key, value)
    end
end

-- Close the file
file:close()

print("Import completed successfully.")

-- Close the Redis connection
client:quit()

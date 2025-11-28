-- [[mert-utils-readr.lua]]

local u = {}

-- Use a metatable to lazily load mert-lua-utils
local mt = {
    __index = function(t, key)
        if key == "u" then
            local utils = require("mert-lua-utils")
            -- rawset(t, "u", utils)
            return utils
        end
    end
}

setmetatable(u, mt)

local function read_file(file_path)
  local file = assert(io.open(file_path, "r"))
  local content = file:read("*all")
  file:close()
  return content
end

local function write_file(content, file_path)
  -- id:: 8dee7405-2948-44c9-bbec-45381094d0b1
  local file = io.open(file_path, "w")
  file:write(content)
  file:close()
end

local function read_json(file_path)
  -- exmp: [[20250421-exmp-json.lua]]
	local json_text = read_file(file_path, "r")
	return require("dkjson").decode(json_text)
end

local function format_json(file_path)
	local command = string.format("nu -c 'open --raw %s | jq --sort-keys . | sponge %s'", file_path, file_path)
	local success, exit_type, code = os.execute(command)

	if type(success) == "number" then -- Lua 5.1
		if success == 0 then
			print("File processed and saved with sorted keys successfully")
		else
			print("Error processing file. Exit code: " .. success)
		end
	else -- Lua 5.2+
		if success then
			print("File processed and saved with sorted keys successfully")
		else
			print("Error processing file. Exit type: " .. tostring(exit_type) .. ", Code: " .. tostring(code))
		end
	end
end

local function write_json(data, file_path)
	-- exmp: [[20250421-exmp-json.lua]]
	local json_text = require("dkjson").encode(data, { indent = true })
	write_file(json_text, file_path, "w")
	format_json(file_path)
end

local function s2df(tsv)
	-- src: [[20241227-read_tsv.lua]]
	-- string to dataframe (tabular data)
	local result = {}
	local headers = {}
	local lines = {}

	-- Split input string into lines
	for line in tsv:gmatch("[^\r\n]+") do
		table.insert(lines, line)
	end

	-- Check if we have at least a header row
	if #lines < 1 then
		return result
	end

	-- Process header row
	for header in lines[1]:gmatch("[^\t]+") do
		headers[#headers + 1] = header
		result[header] = {}
	end

	-- Process data rows
	for i = 2, #lines do
		local col_index = 1
		for value in lines[i]:gmatch("[^\t]+") do
			if col_index <= #headers then
				table.insert(result[headers[col_index]], value)
			end
			col_index = col_index + 1
		end
	end

	return result
end

local function read_tsv_file(fpath)
	-- exmp: [[20250107-read-tsv.lua]]
	--
	local tsv = read_file(fpath)
	return s2df(tsv)
end

function read_tsv_cfile(fname)
	return read_tsv_file(u.u.cfile(fname))
end

function read_tsv_fn(fname)
	return read_tsv_file(u.u.fn2fp(fname))
end

local function read_cfile(fname)
	return read_file(u.u.cfile(fname))
end

local function write_cfile(content, fname)
	local dpath = u.u.get_caller_script_path()
	local fpath = "" .. dpath .. fname
	write_file(content, fpath)
end

local function read_fn(fname)
	return read_file(u.u.fn2fp(fname))
end

local function write_fn(content, fname)
	write_file(content, u.u.fn2fp(fname))
end

local function write_table_2_tsv(tbl, file_path)
	-- Flat table of tables
	-- Inner tables: rows
	-- First element: column headers
	--
	-- input:
	-- { { "tag", "title" },
	--   { "#f/isfkr", "isfkr" },
	--   { "#f/tfk", "tfk" },
	-- }
	--
	-- output: (as file)
	-- tag  title
	-- #f/isfkr  isfkr
	-- #f/tfk  tfk
	--
	file = io.open(file_path, "w")
	-- Write header based on tuple length
	local n = #tbl[1] -- Get length from first tuple

	-- Write data rows
	for _, tuple in ipairs(tbl) do
    for i, value in ipairs(tuple) do
      file:write(value)
      file:write(i < n and "\t" or "\n")
    end
  end
  file:close()
end

-- Function to read TSV file and create a lookup table based on title
local function read_tsv_file_as_tbl(filename)
  local lookup = {}
  local file = io.open(filename, "r")
  if not file then
    error("Could not open file: " .. filename)
  end

  -- Skip header line
  file:read()

  -- Read each line and create lookup table
  for line in file:lines() do
    local fields = split(line, "\t")
    local path = fields[1]
    local title = fields[2]
    lookup[title] = path
  end

  file:close()
  return lookup
end

local function read_tsv_cfile_as_tbl(fname)
  local dpath = u.u.get_caller_script_path()
  local fpath = "" .. dpath .. fname
  return read_tsv_file_as_tbl(fpath)
end

local function loadTableFromFile(filename)
  -- id:: 4a6e0f6c-7740-46f5-b62f-dcc8b9efd397
	local file = assert(io.open(filename, "r"), "Could not open file: " .. filename)
	local content = file:read("*all")
	file:close()

	-- Use load() to compile the string as Lua code
	-- local chunk, err = load("return " .. content)
	local chunk, err = load(content)

	if chunk then
		-- Execute the chunk to get the table
		return chunk()
	else
		error("Error loading table: " .. tostring(err))
	end
end

local function write_lines(content, file)
	-- exmp: [[20251125-4-wk-root-9-xtrc-fn-if-kypn.lua]]
	local str = table.concat(content, "\n")

	write_file(str, file, f)
end

return {
  read_file = read_file,
  write_file = write_file,
  read_cfile = read_cfile,
  write_cfile = write_cfile,
  read_fn = read_fn,
  write_fn = write_fn,

	s2df = s2df,

  read_tsv_file = read_tsv_file,
  read_tsv_cfile = read_tsv_cfile,
  read_tsv = read_tsv_file,
  read_tsvc = read_tsv_cfile,
  read_tsv_fn = read_tsv_fn,

	read_json = read_json,
	write_json = write_json,

	format_json = format_json,

  write_table_2_tsv = write_table_2_tsv,

	loadTableFromFile = loadTableFromFile,

	write_lines = write_lines,

  -- deprecated:
  read_tsv_file_as_tbl = read_tsv_file_as_tbl,
  read_tsv_cfile_as_tbl = read_tsv_cfile_as_tbl,
}

-- mert-utils-fpath.lua
-- mert-utils-io-logging.lua

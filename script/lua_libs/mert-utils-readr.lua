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
  -- id:: 4c82ddf9-5efc-4e77-ae9c-b4644e2c7b31
  -- date:: 20260202
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
  -- id:: 494b2be2-b82d-4f91-b7dc-d5b30a2413df
  -- date:: 20260202
  -- exmp: [[20250421-exmp-json.lua]]
	local json_text = read_file(file_path, "r")
	return require("dkjson").decode(json_text)
end

local function format_json(file_path)
  -- id:: df40d11d-fa02-47b5-b057-03fcc7d488f6
  -- date:: 20260202
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
  -- id:: c8b39c3b-0353-4c69-b051-979c8e675dac
  -- date:: 20260202
	-- exmp: [[20250421-exmp-json.lua]]
	local json_text = require("dkjson").encode(data, { indent = true })
	write_file(json_text, file_path, "w")
	format_json(file_path)
end

local function s2df(tsv)
  -- id:: ff2bca61-2f14-449a-b58c-b12c02b00319
  -- date:: 20260202
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
  -- id:: 380f4b52-99c4-4d1d-b171-01a09ab4d9e0
  -- date:: 20260202
	-- exmp: [[20250107-read-tsv.lua]]
	--
	local tsv = read_file(fpath)
	return s2df(tsv)
end

local function read_tsv_cfile(fname)
  -- id:: 46eaa20d-3d07-49cb-91cc-fa4ee93a66ac
  -- date:: 20260202
	return read_tsv_file(u.u.cfile(fname))
end

local function read_tsv_fn(fname)
  -- id:: 00278ad5-2aaf-4172-b34c-95373ecc2ea1
  -- date:: 20260202
	return read_tsv_file(u.u.fn2fp(fname))
end

local function read_cfile(fname)
  -- id:: a1f11c3d-e925-47d1-b493-7d344f01c939
  -- date:: 20260202
	return read_file(u.u.cfile(fname))
end

local function write_cfile(content, fname)
  -- id:: 9c748dc0-bbb0-4f50-bed1-f7ca49cc82ce
  -- date:: 20260202
	local dpath = u.u.get_caller_script_path()
	local fpath = "" .. dpath .. fname
	write_file(content, fpath)
end

local function read_fn(fname)
  -- id:: 1ce588f8-82e2-44b6-b908-71dd7e4f67de
  -- date:: 20260202
	return read_file(u.u.fn2fp(fname))
end

local function write_fn(content, fname)
  -- id:: 51344f10-a3e5-4fc3-8389-55d527eef1d5
  -- date:: 20260202
	write_file(content, u.u.fn2fp(fname))
end

local function write_table_2_tsv(tbl, file_path)
  -- id:: 89411aa0-9291-4582-911c-6c31171bd999
  -- date:: 20260202
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
  -- id:: 50b56ba3-c14f-4cf4-96ed-4c1e0906ce1a
  -- date:: 20260202
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
  -- id:: 9d7c9b1c-e35b-449d-b23a-482294838232
  -- date:: 20260202
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
  -- id:: 7188419b-eebe-4d16-941e-389043de78e6
  -- date:: 20260202
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

-- [[mert-utils-text.lua]]

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

local function tsv2tbl(tsv_string)
  local result = {}
  local headers = {}
  local lines = {}

  -- Split input string into lines
  for line in tsv_string:gmatch("[^\r\n]+") do
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

local function table_to_tsv(data)
  -- id:: d6d4e921-3a6a-4a23-acc1-0e50fd4e83a9
  -- input: flat table
  -- output: tsv string
  --
  -- column titles are key names of the properties
  --
  -- spcs: TSV çıktısını doğrudan dosyaya yazma. `f/prmp prg/lua` || ((56c5cc0f-a675-4f1e-954f-b05f76509f09))
  -- input:
  -- {
  --   {
  --     path = "",
  --     title = "tfk",
  --   },
  --   {
  --     path = "~/prj/myrepo/logseq-myrepo/pages/ndx___fkr.md",
  --     title = "fkr",
  --   },
  -- }
  --
  -- output:
  -- "path\ttitle\ntfk.md\ttfk\nfkr.md\tfkr\n"

  local output = ""

  -- Convert each entry to TSV format
  for _, entry in ipairs(data) do
    -- Escape any tabs or newlines in the values if they exist
    local path = entry.path:gsub("\t", "\\t"):gsub("\n", "\\n")
    local title = entry.title:gsub("\t", "\\t"):gsub("\n", "\\n")

    output = output .. path .. "\t" .. title .. "\n"
  end

  return output
end

-- Function to split a string by delimiter
local function split(str, delimiter)
  local result = {}
  for match in (str .. delimiter):gmatch("(.-)" .. delimiter) do
    table.insert(result, match)
  end
  return result
end

local function flat_table_2_tsv_string(input, delimiter)
  -- src: [[20241223-tfrm-flat-tbl-2-tsv.lua]]
  -- csv = flat_table_2_tsv_string(input, ",")
  -- tsv = flat_table_2_tsv_string(input, "\t")
  --
  -- local input = {
  --   [1] = {
  --     [1] = "col2",
  --     [2] = "col1",
  --     [3] = "name",
  --   },
  --   [2] = {
  --     [1] = "",
  --     [2] = "",
  --     [3] = "append",
  --   },
  --   [3] = {
  --     [1] = "",
  --     [2] = "",
  --     [3] = "root",
  --   },
  --   [4] = {
  --     [1] = "v2",
  --     [2] = "v1",
  --     [3] = "root",
  --   },
  -- }
  --
  -- output:
  -- col2,col1,name
  -- ,,append
  -- ,,root
  -- v2,v1,root
  --
  -- Default to comma if no delimiter specified
  delimiter = delimiter or ","

  -- Get all numeric keys and sort them
  local keys = {}
  for k, _ in pairs(input) do
    if type(k) == "number" then
      table.insert(keys, k)
    end
  end
  table.sort(keys)

  if #keys == 0 then
    return ""
  end

  local result = {}

  -- Add headers row (first row)
  local headers = input[keys[1]]
  local headerRow = table.concat(headers, delimiter)
  table.insert(result, headerRow)

  -- Process data rows
  for i = 2, #keys do
    local row = input[keys[i]]
    local rowStr = table.concat(row, delimiter)
    table.insert(result, rowStr)
  end

  return table.concat(result, "\n")
end

-- Function to split TSV line into fields
local function split_tsv(line)
  local result = {}
  for field in line:gmatch("[^\t]+") do
    table.insert(result, field)
  end
  return result
end

local function get_column_f_tsv_str(tsv_data, column)
  -- #f/renamed: get_column -> get_column_f_tsv_str
  -- column: column number
  local titles = {}
  for line in tsv_data:gmatch("[^\n]+") do
    local fields = split_tsv(line)
    if #fields >= column then -- Ensure we have at least path and title
      titles[fields[column]] = true
    end
  end
  return titles
end

local function file_paths_to_map(input_text)
  local result = {}
  for line in input_text:gmatch("[^\r\n]+") do
    local file_name = line:match("([^/]+)$")
    if file_name then
      result[file_name] = line
    end
  end
  return result
end

local function table_to_literal(t, indent)
  -- [grok](https://grok.com/chat/042a26bd-1721-408a-8f77-72dd56c0dd13)
  indent = indent or "  "  -- Default to empty string for indentation
  local str = "{"

  local first = true
  for k, v in pairs(t) do
    if not first then str = str .. "," end
    first = false

    -- Add newline and indent for readability if table has nested content
    str = str .. "\n" .. indent .. "  "

    -- Format the key
    if type(k) == "string" and k:match("^[a-zA-Z_][a-zA-Z0-9_]*$") then
      str = str .. k  -- Use bare key if it’s a valid Lua identifier
    else
      str = str .. "[" .. (type(k) == "string" and "\"" .. k .. "\"" or tostring(k)) .. "]"
    end

    str = str .. " = "

    -- Format the value
    if type(v) == "table" then
      str = str .. table_to_literal(v, indent .. "  ")  -- Recurse for nested tables
    elseif type(v) == "string" then
      str = str .. "\"" .. v .. "\""  -- Quote strings
    else
      str = str .. tostring(v)  -- Numbers, booleans, etc.
    end
  end

  if not first then str = str .. "\n" .. indent end  -- Close with indent if non-empty
  str = str .. "}"
  return str
end

local function addPrefix(str, prefix)
  -- dls: trimPrefix(str, prefix) || ((699d9077-c72e-4eb0-adec-5107c1149a2a))
  return prefix .. str
end

local function trimPrefix(str, prefix)
  -- id:: 699d9077-c72e-4eb0-adec-5107c1149a2a
  -- exmp: [[20250414-trim-string.lua]]
  --
  -- local input = "path:20241022-specs-all.lua"
  -- local prefix_to_remove = "path:"
  -- output = trimPrefix(input, prefix_to_remove)
  -- -- "20241022-specs-all.lua"
  return string.gsub(str, "^" .. prefix, "", 1) 
end

local function get_fn(fp)
	-- id:: cabcf83e-ec2e-45c2-a983-dca0305c6bd4
	-- GetFileName
	-- [grok](https://grok.com/chat/71f60cc8-37e4-43fd-9eb6-e1b0d62102b1)
	-- -- Examples
	-- print(getFileNameWithoutExtension("/home/user/docs/file.txt"))  -- Outputs: file
	-- print(getFileNameWithoutExtension("C:\\folder\\file.txt"))     -- Outputs: file
	-- print(getFileNameWithoutExtension("file.txt"))                 -- Outputs: file
	-- print(getFileNameWithoutExtension("file"))                     -- Outputs: file
	local separator = package.config:sub(1,1) -- OS-specific separator (/ or \)
	local parts = {}
	-- Split path by separator
	for part in fp:gmatch("[^" .. separator .. "]+") do
		table.insert(parts, part)
	end
	local filename = parts[#parts] or fp
	-- Remove extension
	return filename:match("([^%.]+)") or filename
end

local function sbst_underscore_w_slash(input)
	-- [grok](https://grok.com/chat/774e2f40-edb1-45bc-ab6a-a78ed61f815c)
	return string.gsub(input, "___", "/")
end

local function sbst_slash_w_underscore(input)
	return string.gsub(input, "/", "___")
end

local function gen_uuid()
	-- id:: 3dae15f8-dd79-41b2-b69a-9c6a54082b47
	-- exmp: [[20250413-exmp-uuid-generator.lua]]
	math.randomseed(os.time() + os.clock() * 1000000)

	local function generate_uuid_v4_pure_lua()
		local template = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'
		local hex_chars = '0123456789abcdef'

		local function random_hex(len)
			local s = ''
			for _ = 1, len do
				s = s .. hex_chars:sub(math.random(1, 16), math.random(1, 16))
			end
			return s:sub(1, len) -- Ensure exact length
		end

		return string.gsub(template, '[xy]', function(c)
			-- For 'x', any hex digit
			-- For 'y', specific hex digits (8, 9, a, b) according to RFC 4122 variant 1
			local v = (c == 'x') and math.random(0, 0xf) or math.random(8, 0xb)
			return string.format('%x', v)
		end)
	end

	return generate_uuid_v4_pure_lua()
end

local function substitutel(str, pattern, repl, flags)
  -- substitute per line instead of complete string
  -- [grok](https://grok.com/chat/dba744d0-728b-42eb-bf95-40e7083152ec)
  --
  -- Split the string into lines
  local lines = vim.split(str, '\n')
  -- Apply substitution to each line
  for i, line in ipairs(lines) do
    lines[i] = vim.fn.substitute(line, pattern, repl, flags)
  end
  -- Join the lines back together
  return table.concat(lines, '\n')
end

local function clean_wk_otl(wk_otl)
	local wk_otl02 = substitutel(wk_otl, "<.\\{-}'", '', '')
	local wk_otl03 = substitutel(wk_otl02, "<.*/", '', '')
	local wk_otl04 = substitutel(wk_otl03, "'.*", '', '')
	local wk_otl05 = substitutel(wk_otl04, "<.*", '', '')
	return wk_otl05
end

return {
	substitutel = substitutel,
	clean_wk_otl = clean_wk_otl,

	tsv2tbl = tsv2tbl,
	table_to_tsv = table_to_tsv,
	split = split,
  flat_table_2_tsv_string = flat_table_2_tsv_string,
  get_column_f_tsv_str = get_column_f_tsv_str,
	file_paths_to_map = file_paths_to_map,
  table_to_literal = table_to_literal,
	addPrefix = addPrefix,
	trimPrefix = trimPrefix,
	get_fn = get_fn,
	sbst_underscore_w_slash = sbst_underscore_w_slash,
	sbst_slash_w_underscore = sbst_slash_w_underscore,

	-- random
	gen_uuid = gen_uuid,
}


-- [[mert-utils-fpath.lua]]
--
-- mplm: [[20250821-lua-naming-fns.lua]]
	-- [grok](https://grok.com/share/bGVnYWN5_00eae4e3-1266-48c8-9275-978f90f19737)
-- rltd: [[mert-lua-nvim-fpath.lua]]

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

local function GetDefaultFlPtArgs()
  local m = {}
  m.flPt = ''
  m.tmpl = {}
  m.tmpl.title = ''
  m.ext = ''
  return m
end

local function GetDictFlPtArgs1(ext)
  local m = GetDefaultFlPtArgs()
  m.ext = '.' .. ext
  return m
end

local function GetDictFlPtArgs2(ext, title)
  local m = GetDictFlPtArgs1(ext)
  m.tmpl.title = title
  return m
end

local function cfile(fname)
  local dpath = u.u.get_caller_script_path()
  local fpath = "" .. dpath .. fname
  return fpath
end

function get_fp_outlist(fname)
	-- id:: 178b14fb-1f98-43e5-a3fa-69b766f32e22
	return "/Users/mertnuhoglu/prj/private_dotfiles/.config/params/outlist/" .. fname
end

local function getScriptFilename()
	-- exmp: [[20250422-mnml-wk-update-prcs.lua]]
	local path = arg[0] or debug.getinfo(1).source
	return path:match("^.+/(.+)$") or path:match("^@(.+)$") or path
end

local function getCurrentFunctionName()
	-- exmp: [[20250422-mnml-wk-update-prcs.lua]]
	return debug.getinfo(2, "n").name or "anonymous"
end

-- Utility functions (pure Lua equivalents where possible)
local function get_dt()
	return os.date("%Y%m%d")
end

local function get_ts_kebab()
	local date = os.date("%Y%m%d")
	local time = os.date("%H%M%S")
	return date .. "-" .. time
end

local function get_date_time_as_kebab_short()
	local date = os.date("%m%d")
	local time = os.date("%H%M%S")
	return date .. "-" .. time
end

-- Simple path modifiers (pure Lua string manipulation)
local function fname_modify(path, modifier)
	if modifier == ':p:h' then
		return path:match('^(.*)/[^/]*$') or ''
	elseif modifier == ':t:r' then
		local tail = path:match('/([^/]*)$') or path
		return tail:gsub('%.[^.]+$', '')
	elseif modifier == ':e' then
		return path:match('%.([^.]+)$') or ''
	elseif modifier == ':t' then
		return path:match('/([^/]*)$') or path
	end
	return path
end

-- Regex patterns approximated as Lua patterns (note: Lua patterns differ from Vim regex)
local rgx = {}
rgx.Dt = '(20[0-3]%d%d%d%d%d)'  -- Approximated without word boundaries
rgx.TsInFlName = '(-%d%d%d%d-%d%d%d%d%d%d%.)'
rgx.PreTsInFlName = '(^20%d%d%d%d-%d%d%d%d%d%d)'
rgx.file_name_counter = '([_-])%d%d$'

-- BlockLine2KebabFileName (assumed based on description; takes line as input)
local function block_line_2_kebab_file_name(line)
	local begin_pat = '^(%s*[-"#]+)+%s*'
	local content = line:gsub(begin_pat, '')
	local kebab = content:gsub(' ', '-')
	return kebab
end

-- TrimCounterNumber (fn without ext)
local function trim_counter_number(fn)
	return fn:gsub('[_-]%d%d$', '')
end

-- Get files in directory using io.popen (platform-dependent, assumes Unix-like 'ls')
local function get_files_in_dir(dir)
	local handle = io.popen('ls ' .. dir)
	if not handle then return {} end
	local files = {}
	for line in handle:lines() do
		table.insert(files, line)
	end
	handle:close()
	return files
end

-- GetLastFileByPatternInDir (sorts lexically)
local function get_last_file_by_pattern_in_dir(base_dir, pattern_w_ext)
	local files = get_files_in_dir(base_dir)
	local matching = {}
	for _, file in ipairs(files) do
		if file:match(pattern_w_ext) then
			table.insert(matching, file)
		end
	end
	table.sort(matching)
	return matching[#matching]
end

-- GetFlNameByIncrCFl (increments counter in filename with ext)
local rgx_file_name_counter_with_ext = '([_-])(%d%d)(%.%w+)$'
local function get_fl_name_by_incr_c_fl(last_fn)
	local prefix, counter, ext = last_fn:match(rgx_file_name_counter_with_ext)
	if counter then
		local num = tonumber(counter) + 1
		return last_fn:gsub(rgx_file_name_counter_with_ext, prefix .. string.format('%02d', num) .. ext, 1)
	else
		return last_fn:gsub('(%.%w+)$', '-01%1', 1)
	end
end

-- Translated functions
local function get_default_fl_pt_args()
	return {
		flPt = '',
		tmpl = { title = '' },
		ext = ''
	}
end

local function get_dict_fl_pt_args1(ext)
	local m = get_default_fl_pt_args()
	m.ext = '.' .. ext
	return m
end

local function get_dict_fl_pt_args2(ext, title)
	local m = get_dict_fl_pt_args1(ext)
	m.tmpl.title = title
	return m
end

local function get_fl_nm(nm_fn, fl_pt)
	return nm_fn(fl_pt)
end

-- NmFCLine requires a line (pure Lua, no dependency on current buffer)
local function nm_fc_line(m, line)
	local nm = block_line_2_kebab_file_name(line)
	return nm
end

local function nm_fc_file_incr(m)
	local path = m.flPt
	local base_dir = fname_modify(path, ':p:h')
	local fn = fname_modify(path, ':t:r')
	local ext = fname_modify(path, ':e')
	local base_fn = trim_counter_number(fn)
	local pattern_w_ext = base_fn .. '.*%.' .. ext .. '$'
	local last_fn = get_last_file_by_pattern_in_dir(base_dir, pattern_w_ext)
	if not last_fn then
		-- Fallback: use base_fn with -01 suffix
		return base_fn .. '-01.' .. ext
	end
	return get_fl_name_by_incr_c_fl(last_fn)
end

local function nm_fc_file_change_ext(m)
	local path = m.flPt
	local fn = fname_modify(path, ':t:r')
	local ext = m.ext
	return fn .. ext
end

local function nm_w_dt(m)
	local cflnm = fname_modify(m.flPt, ':t')
	local date_in_cflnm = cflnm:match(rgx.Dt)
	local cflnm2
	if date_in_cflnm then
		cflnm2 = cflnm:gsub(date_in_cflnm, get_dt(), 1)
	else
		cflnm2 = cflnm:gsub('^', get_dt() .. '-')
	end
	return cflnm2
end

local function nm_w_ts_post(m)
	local current_filename = fname_modify(m.flPt, ':t')
	local cfname
	if current_filename:match(rgx.TsInFlName) then
		cfname = current_filename:gsub(rgx.TsInFlName, '-@@@.', 1)
	else
		cfname = current_filename:gsub('%.(%w+)$', '-@@@.%1', 1)
	end
	local timestamp = get_date_time_as_kebab_short()
	local new_fname = cfname:gsub('@@@', timestamp, 1)
	return new_fname
end

local function nm_w_ts_pre(m)
	local current_filename = fname_modify(m.flPt, ':t')
	local cfname
	if current_filename:match(rgx.PreTsInFlName) then
		cfname = current_filename:gsub(rgx.PreTsInFlName, '@@@-', 1)
	else
		cfname = '@@@-' .. current_filename
	end
	local timestamp = get_ts_kebab()
	local new_fname = cfname:gsub('@@@', timestamp, 1)
	return new_fname
end

local function nm_w_dt_pre_ts_post(m)
	local w_date = nm_w_dt(m)
	local fl_pt = fname_modify(m.flPt, ':p:h') .. '/' .. w_date
	m.flPt = fl_pt
	local w_ts = nm_w_ts_post(m)
	return w_ts
end

local function nm_w_ts_only(fl_pt)
	return get_ts_kebab()
end

local function nm_tmpl(m)
	local title = (type(m) == 'table' and m.tmpl and m.tmpl.title) or 'tmp'
	local ext = m.ext or ''
	local fname = string.format("%s-%s%s", get_ts_kebab(), title, ext)
	return fname
end

local function nm_tmpl_w_fn(m)
	local title = (type(m) == 'table' and m.tmpl and m.tmpl.title) or 'tmp'
	local fl_nm = fname_modify(m.flPt, ':t')
	local fname = string.format("%s-%s-%s", get_ts_kebab(), title, fl_nm)
	return fname
end

local function dir_of_that_fl(m)
	-- translated: DirOfThatFl || ((55933025-ce8a-464a-8278-65770e74e587))
  local that_flpt = m.flPt
  local that_dir = fname_modify(that_flpt, ':p:h')
  return that_dir
end

local function get_file_path(nm_fn, dir_fn, m)
	-- translated: GetFilePath || ((c090e44a-adda-43e4-9b22-ebf1374dba8f))
  local dir = dir_fn(m)
  local nm = nm_fn(m)
  local fl_pt = dir .. '/' .. nm
  return fl_pt
end

local function get_fp_in_dir_of_that_fl(nm_fn, m)
  -- id:: cb2f87b3-517f-497b-a377-bf4d322e9fa1
	-- translated: GetFpInDirOfThatFl || ((4259a225-476d-4075-a816-d948dadc35ec))
  return get_file_path(nm_fn, dir_of_that_fl, m)
end

local function cp3NmFn(NmFn)
  -- id:: f676b4f8-1eff-4697-a10d-4029aaaf00d9
	-- moved-from: cp3NmFn(NmFn) || ((3197305f-f38e-4fb1-bc62-1cf868869b17))
	return function(src_fp)
		local m = GetDefaultFlPtArgs()
		m.flPt = src_fp
		-- {
		--   ext = "",
		--   flPt = "/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20250320-scrap/out/base-file.txt",
		--   tmpl = {
		--     title = ""
		--   }
		-- }
		local dest_fp = get_fp_in_dir_of_that_fl(NmFn, m)
		-- "/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20250320-scrap/out/20250511-base-file.txt"
		u.u.cp8mv(src_fp, dest_fp)
		return dest_fp
	end
end

local cp3NmWTsPre = cp3NmFn(nm_w_ts_pre)
local cp3NmWDt = cp3NmFn(nm_w_dt)

return {
	GetDefaultFlPtArgs = GetDefaultFlPtArgs,
	GetDictFlPtArgs1 = GetDictFlPtArgs1,
	GetDictFlPtArgs2 = GetDictFlPtArgs2,
	cfile = cfile,
	get_fp_outlist = get_fp_outlist,
	getScriptFilename = getScriptFilename,
	getCurrentFunctionName = getCurrentFunctionName,
	fname_modify = fname_modify,
	block_line_2_kebab_file_name = block_line_2_kebab_file_name,
	trim_counter_number = trim_counter_number,
	get_files_in_dir = get_files_in_dir,
	get_last_file_by_pattern_in_dir = get_last_file_by_pattern_in_dir,
	get_fl_name_by_incr_c_fl = get_fl_name_by_incr_c_fl,
	get_default_fl_pt_args = get_default_fl_pt_args,
	get_dict_fl_pt_args1 = get_dict_fl_pt_args1,
	get_dict_fl_pt_args2 = get_dict_fl_pt_args2,
	get_fl_nm = get_fl_nm,
	nm_fc_line = nm_fc_line,
	nm_fc_file_incr = nm_fc_file_incr,
	nm_fc_file_change_ext = nm_fc_file_change_ext,
	nm_w_dt = nm_w_dt,  -- vim.fn.NmWDt
	nm_w_ts_post = nm_w_ts_post,
	nm_w_ts_pre = nm_w_ts_pre,
	nm_w_dt_pre_ts_post = nm_w_dt_pre_ts_post,  -- vim.fn.NmWDtPreTsPost
	nm_w_ts_only = nm_w_ts_only,
	nm_tmpl = nm_tmpl,
	nm_tmpl_w_fn = nm_tmpl_w_fn,

	dir_of_that_fl = dir_of_that_fl,
	get_file_path = get_file_path,
	get_fp_in_dir_of_that_fl = get_fp_in_dir_of_that_fl,
	cp3NmFn = cp3NmFn,
	cp3NmWTsPre = cp3NmWTsPre,
	cp3NmWDt = cp3NmWDt,

}

-- 20250819 circular deps a

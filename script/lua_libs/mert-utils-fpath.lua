-- [[mert-utils-fpath.lua]]
--
-- prt: [[mert-utils-fpath-dir.lua]]
-- exmp: 
--   mplm:[[20250821-lua-naming-fns.lua]]
--   nm fns: [[20250821-lua-naming-fns-01.lua]]
--   cp fns: [[20250821-lua-naming-fns-02.lua]]
	-- [grok](https://grok.com/share/bGVnYWN5_00eae4e3-1266-48c8-9275-978f90f19737)
-- dls: [[mert-lua-nvim-fpath.lua]]
--
-- #rfr/toc
--   clsf: fpath: naming functions: exmp: NmFCLine  || ((e5d209bd-86ff-471d-9290-5137a1a0416d))
--     nm_f_line nm_incr nm_w_dt ...

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

-- local function GetDefaultFlPtArgs()
--   -- id:: 602bc687-59f9-4322-9f10-65bb8f5f63c0
--   -- date:: 20260202
-- 	-- f/dprc
-- 	-- replaced-by: local function get_default_fl_pt_args() || ((f99e1880-caae-43cc-969c-3aed4002a9bd))
--   return m
-- end

-- local function GetDictFlPtArgs1(ext)
--   -- id:: 0ea9c8ce-b8d4-455d-808d-403ca025da99
--   -- date:: 20260202
--   local m = GetDefaultFlPtArgs()
--   m.ext = '.' .. ext
--   return m
-- end
--
-- local function GetDictFlPtArgs2(ext, title)
--   -- id:: b33be5de-9a4e-43fb-9b08-35845c0b24e4
--   -- date:: 20260202
--   local m = GetDictFlPtArgs1(ext)
--   m.tmpl.title = title
--   return m
-- end

local function cfile(fname)
  -- id:: ac6f3878-d29a-4dd6-a83c-21b3b78d8936
  -- date:: 20260202
  local dpath = u.u.get_caller_script_path()
  local fpath = "" .. dpath .. fname
  return fpath
end

local function get_fp_outlist(fname)
	-- id:: 178b14fb-1f98-43e5-a3fa-69b766f32e22
	return "/Users/mertnuhoglu/prj/private_dotfiles/.config/params/outlist/" .. fname
end

local function getScriptFilename()
  -- id:: b240c711-903e-4c30-9f16-661080d570cb
  -- date:: 20260202
	-- exmp: [[20250422-mnml-wk-update-prcs.lua]]
	local path = arg[0] or debug.getinfo(1).source
	return path:match("^.+/(.+)$") or path:match("^@(.+)$") or path
end

local function getCurrentFunctionName()
  -- id:: 0f260113-752a-4fa3-96e5-a41eab364e0b
  -- date:: 20260202
	-- exmp: [[20250422-mnml-wk-update-prcs.lua]]
	return debug.getinfo(2, "n").name or "anonymous"
end

-- Utility functions (pure Lua equivalents where possible)
local function get_dt()
  -- id:: 5822522b-8676-4ded-8e89-053d575097a4
  -- date:: 20260202
	return os.date("%Y%m%d")
end

local function get_ts_kebab()
  -- id:: 5d475422-2402-4e54-9fc9-6782cf602a23
  -- date:: 20260202
	local date = os.date("%Y%m%d")
	local time = os.date("%H%M%S")
	return date .. "-" .. time
end

local function get_date_time_as_kebab_short()
  -- id:: 51129e57-e501-4fc5-98de-e5df48ce6090
  -- date:: 20260202
	local date = os.date("%m%d")
	local time = os.date("%H%M%S")
	return date .. "-" .. time
end

-- Simple path modifiers (pure Lua string manipulation)
local function fname_modify(path, modifier)
  -- id:: 40451bad-47a5-499e-a76e-47e7664cd195
  -- date:: 20260202
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
  -- id:: 51afd0f2-79a7-4c77-87b2-18632c49f41a
  -- date:: 20260202
	local begin_pat = '^(%s*[-"#]+)+%s*'
	local content = line:gsub(begin_pat, '')
	local kebab = content:gsub(' ', '-')
	return kebab
end

-- TrimCounterNumber (fn without ext)
local function trim_counter_number(fn)
  -- id:: 04865918-4b9d-44de-9e2c-f3b69d9ff9f8
  -- date:: 20260202
	return fn:gsub('[_-]%d%d$', '')
end

-- Get files in directory using io.popen (platform-dependent, assumes Unix-like 'ls')
local function get_files_in_dir(dir)
  -- id:: 1a2c05bc-da57-47d1-b071-250506b519ed
  -- date:: 20260202
	local handle = io.popen('ls ' .. dir)
	if not handle then return {} end
	local files = {}
	for line in handle:lines() do
		table.insert(files, line)
	end
	handle:close()
	return files
end

local function match_files_in(base_dir, pattern_w_ext)
  -- id:: 2d78aacd-8815-4e7f-9275-104e49fe183f
  -- date:: 20260216
	local files = get_files_in_dir(base_dir)
	local matching = {}
	for _, file in ipairs(files) do
		if file:match(pattern_w_ext) then
			table.insert(matching, file)
		end
	end
	return matching
end

local function get_last_file_by_pattern_in_dir(base_dir, pattern_w_ext)
  -- id:: 4b66f4e0-db04-46ea-a444-34d320b896ec
  -- date:: 20260202
	-- pprv: vim: function! ELsInDirByPat(dirpath, pattern, ext)  " SPC icd || ((18b03b9a-3a4e-4f98-aec8-6137e564904b))
	-- mplm: [[20260216-mplm-get_last_file_by_pattern_in_dir.lua]]
	-- debug: [[20260216-debug-get_last_file_by_pattern_in_dir.lua]]
	-- exmp: [[20260331-dbg-sort_filenames_by_counter.lua]]
	local matching = match_files_in(base_dir, pattern_w_ext)
	local sorted = u.u.sort_filenames_by_counter(matching)
	return sorted[#sorted]
end

local rgx_file_name_counter_with_ext = '([_-])(%d%d)(%.%w+)$'

local function get_fl_name_by_incr_c_fl(last_fn)
  -- id:: 82bd1eb5-35f3-4648-ac54-771436c7244e
  -- date:: 20260202
	-- Increment counter in the given file name.
	--
	-- vim: function! GetFlNameByIncrCFl(fname) || ((abcc3f68-94b9-43f4-9744-63c9477bd29c))
	-- exmp: [[20260216-exmp-get_fl_name_by_incr_c_fl.lua]]
	-- [grok](https://grok.com/share/bGVnYWN5_070c6c49-d20f-472f-89ea-bfcaff26f8b1)
	local name = last_fn:match("[^/\\]+$") or last_fn

	local prefix, counter, ext = name:match(rgx_file_name_counter_with_ext)

	if counter then
		local num = tonumber(counter) + 1
		return name:gsub(rgx_file_name_counter_with_ext,
			prefix .. ('%02d'):format(num) .. ext, 1)
	end

	return name:gsub('(%.%w+)$', '-01%1', 1)
end

-- Translated functions
local function get_default_fl_pt_args()
  -- id:: f99e1880-caae-43cc-969c-3aed4002a9bd
  -- date:: 20260202
	return {
		flPt = '',
		tmpl = { title = '' },
		cline = '',
		ext = ''
	}
end

local function get_dict_fl_pt_args1(ext)
  -- id:: 2818f04f-216a-410c-a158-ad03ae7e5614
  -- date:: 20260202
	local m = get_default_fl_pt_args()
	m.ext = '.' .. ext
	return m
end

local function get_dict_fl_pt_args2(ext, title)
  -- id:: ef241642-d01a-43e8-9eb4-4e205a117904
  -- date:: 20260202
	local m = get_dict_fl_pt_args1(ext)
	m.tmpl.title = title
	return m
end

local function get_fl_nm(nm_fn, fl_pt)
  -- id:: d3a80367-253a-4349-88df-4307f95e0848
  -- date:: 20260202
	return nm_fn(fl_pt)
end

local function nm_f_line(m)
  -- id:: d0dc40b7-b311-4abf-b2b5-a38f0fc35aeb
  -- date:: 20260216
	local line = m.cline
  local nm = block_line_2_kebab_file_name(line)
  return nm
end

local function nm_incr(m)
  -- id:: 97cc5d56-154d-402d-b656-78ff4d66cfc9
  -- date:: 20260202
	-- debug: [[20260216-debug-nm_incr.lua]]
	-- exmp: [[20250821-lua-naming-fns-01.lua]]
	local path = m.flPt
	local base_dir = fname_modify(path, ':p:h')
	local fn = fname_modify(path, ':t:r')
	local ext = fname_modify(path, ':e')
	local base_fn = trim_counter_number(fn)
	local escaped_base = u.u.escape_pattern_literal(base_fn)
	-- local pattern_w_ext = base_fn .. '.*%.' .. ext .. '$'
	pattern_w_ext = "^" .. escaped_base .. ".*%." .. ext .. "$"
	local last_fn = get_last_file_by_pattern_in_dir(base_dir, pattern_w_ext)
	if not last_fn then
		-- Fallback: use base_fn with -01 suffix
		return base_fn .. '-01.' .. ext
	end
	return get_fl_name_by_incr_c_fl(last_fn)
end

local function nm_change_ext(m)
  -- id:: 7735398c-f83b-4366-8b23-845c9ce0326f
  -- date:: 20260202
	local path = m.flPt
	local fn = fname_modify(path, ':t:r')
	local ext = m.ext
	return fn .. ext
end

local function nm_w_dt(m)
  -- id:: 6b0e62ec-cbdb-47e3-bae4-420232c0cd09
  -- date:: 20260202
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
  -- id:: f630fcff-2157-4a5b-aaa6-5556f3cadc9f
  -- date:: 20260202
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
  -- id:: 2f548e49-69f9-4fec-b10c-c420fc0fedbb
  -- date:: 20260202
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
  -- id:: 8bf9eb3d-ced6-46a2-b7a0-a92a6e5a0d19
  -- date:: 20260202
	local w_date = nm_w_dt(m)
	local fl_pt = fname_modify(m.flPt, ':p:h') .. '/' .. w_date
	m.flPt = fl_pt
	local w_ts = nm_w_ts_post(m)
	return w_ts
end

local function nm_w_ts_only(fl_pt)
  -- id:: dd9447b0-b98a-44b0-940f-c8c270c994a0
  -- date:: 20260202
	return get_ts_kebab()
end

local function nm_tmpl(m)
  -- id:: 567dcd74-dd2f-4b4d-bea6-1e081c5fd138
  -- date:: 20260202
	local title = (type(m) == 'table' and m.tmpl and m.tmpl.title) or 'tmp'
	local ext = m.ext or ''
	local fname = string.format("%s-%s%s", get_ts_kebab(), title, ext)
	return fname
end

local function nm_tmpl_w_fn(m)
  -- id:: 604917bb-59e7-451a-99f5-dc847fb5a991
  -- date:: 20260202
	local title = (type(m) == 'table' and m.tmpl and m.tmpl.title) or 'tmp'
	local fl_nm = fname_modify(m.flPt, ':t')
	local fname = string.format("%s-%s-%s", get_ts_kebab(), title, fl_nm)
	return fname
end

local function dir_of_that_fl(m)
  -- id:: ea3af90e-019a-4ea8-9824-a86c3deb82a5
  -- date:: 20260202
	-- translated: DirOfThatFl || ((55933025-ce8a-464a-8278-65770e74e587))
	local that_dir = m.dir
	if that_dir == nil then
		local that_flpt = m.flPt
		that_dir = fname_modify(that_flpt, ':p:h')
	end
  return that_dir
end

local function get_file_path(nm_fn, dir_fn, m)
  -- id:: 449cffe4-3007-468d-a98c-e8f12366b72a
  -- date:: 20260202
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
	--
	-- rfrmap: 	cp3NmWDt fn lerine arg atamak || ((06bc1306-f684-42e0-a320-e0ecac678ed4))
	-- 	F: local function cp3NmFn(NmFn, m) || ((f676b4f8-1eff-4697-a10d-4029aaaf00d9))
	-- 	M: mplm: [[20250821-lua-naming-fns.lua]]
	-- 		N: cp3NmFn: configure dest dir || ((88c14035-e5ae-410f-b6e6-6429ca6320f6))
	return function(src_fp, m)
		local opts = u.u.shallow_copy(m)

		if opts == nil then
			opts = GetDefaultFlPtArgs()
		elseif (opts.dir ~= nil) and u.u.is_blank(opts.flPt) then
			opts.flNm = fname_modify(src_fp, ':t')
			opts.flPt = opts.dir .. '/' .. opts.flNm
		end
		-- m.flPt = src_fp
		-- {
		--   ext = "",
		--   flPt = "/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20250320-scrap/out/base-file.txt",
		--   tmpl = {
		--     title = ""
		--   }
		-- }
		local dest_fp = get_fp_in_dir_of_that_fl(NmFn, opts)
		-- "/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20250320-scrap/out/20250511-base-file.txt"
		u.u.cp8mv(src_fp, dest_fp)
		return dest_fp
	end
end

local cp3NmWTsPre = cp3NmFn(nm_w_ts_pre)
local cp3NmWDt = cp3NmFn(nm_w_dt)

--- GetPathByTag: Tag to md file path
---@param tag string
---@return string
local function get_path_by_tag(tag)
  -- id:: 71574e4d-a39b-40aa-a935-fdbc96ecdc6a
  -- date:: 20260307
  local pages_dir = '/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/pages'
  local filename = tag:gsub('/', '___') .. '.md'
  return pages_dir .. '/' .. filename
end

-- Two directories up: /a/b/c/d.txt → /a/b
local function get_up_dir(path)
	-- translated from: GetUpDir
  -- Remove trailing path separator if present
  path = path:gsub('[/\\]$', '')
  -- Remove filename + one directory
  local up1 = path:match('^(.*)[/\\][^/\\]+$') or path
  -- Remove one more directory
  local up2 = up1:match('^(.*)[/\\][^/\\]+$') or up1
  return up2
end

-- Name of the directory two levels up: /a/b/c/d.txt → "b"
local function get_file_up_dir_name(path)
	-- translated from: GetFileUpDirName
  local up2 = GetUpDir(path)
  -- Get last component
  return up2:match('[^/\\]+$') or up2
end

-- Filename without extension:  hello.world.lua → hello.world
local function get_file_name_no_ext(path)
	-- translated from: GetFileNameNoExt
  local tail = path:match('[^/\\]+$') or path
  return tail:gsub('%.[^.]+$', '') or tail
end

-- Filename with extension (basename): /a/b/c/hello.lua → hello.lua
local function get_file_name_w_ext(path)
	-- translated from: GetFileNameWExt
  return path:match('[^/\\]+$') or path
end

-- Directory of the file (one level up): /a/b/c/d.txt → /a/b/c
local function get_file_dir(path)
	-- translated from: GetFileDir
  -- Remove trailing separator if any
  path = path:gsub('[/\\]$', '')
  -- Remove last component
  return path:match('^(.*)[/\\][^/\\]+$') or path or '.'
end

-- Full absolute path simulation (just removes relative parts if obvious, but mostly returns input)
-- Without vim.fn we can't resolve .. / ~ / $ENV / symlinks — so this is very limited version
local function get_file_path2(path)
	-- translated from: GetFilePath2
  -- Very basic cleanup only — real resolution isn't possible in pure Lua without os/fs access
  path = path:gsub('[/\\]+', '/')           -- normalize separators
  path = path:gsub('/%./', '/')             -- remove /./
  -- We cannot safely resolve ../ without full path awareness → returning input
  return path
end

-- File extension without dot:  file.tar.gz → gz    (last extension only)
local function get_file_ext(path)
	-- translated from: GetFileExt
  local tail = path:match('[^/\\]+$') or path
  return tail:match('%.([^%.]+)$') or ''
end

return {
	GetDefaultFlPtArgs = get_default_fl_pt_args,  -- #f/dprc
	GetDictFlPtArgs1 = get_dict_fl_pt_args1,  -- #f/dprc
	GetDictFlPtArgs2 = get_dict_fl_pt_args2,  -- #f/dprc
	get_default_fl_pt_args = get_default_fl_pt_args,
	get_dict_fl_pt_args1 = get_dict_fl_pt_args1,
	get_dict_fl_pt_args2 = get_dict_fl_pt_args2,

	cfile = cfile,
	get_fp_outlist = get_fp_outlist,
	getScriptFilename = getScriptFilename,
	getCurrentFunctionName = getCurrentFunctionName,

	fname_modify = fname_modify,
	block_line_2_kebab_file_name = block_line_2_kebab_file_name,
	trim_counter_number = trim_counter_number,

	get_files_in_dir = get_files_in_dir,
	get_last_file_by_pattern_in_dir = get_last_file_by_pattern_in_dir,
	match_files_in = match_files_in,

	get_fl_name_by_incr_c_fl = get_fl_name_by_incr_c_fl,
	get_fl_nm = get_fl_nm,
	nm_f_line = nm_f_line,
	nm_incr = nm_incr,
	nm_change_ext = nm_change_ext,
	nm_w_dt = nm_w_dt,  -- vim.fn.NmWDt
	nm_w_ts_post = nm_w_ts_post,
	nm_w_ts_pre = nm_w_ts_pre,
	nm_w_dt_pre_ts_post = nm_w_dt_pre_ts_post,  -- vim.fn.NmWDtPreTsPost
	nm_w_ts_only = nm_w_ts_only,
	nm_tmpl = nm_tmpl,
	nm_tmpl_w_fn = nm_tmpl_w_fn,

	get_file_path = get_file_path,
	get_fp_in_dir_of_that_fl = get_fp_in_dir_of_that_fl,
	dir_of_that_fl = dir_of_that_fl,

	cp3NmFn = cp3NmFn,
	cp3NmWTsPre = cp3NmWTsPre,
	cp3NmWDt = cp3NmWDt,
	get_path_by_tag = get_path_by_tag,

  get_up_dir         = get_up_dir,
  get_file_up_dir_name = get_file_up_dir_name,
  get_file_name_no_ext = get_file_name_no_ext,
  get_file_name_w_ext  = get_file_name_w_ext,
  get_file_dir       = get_file_dir,
  get_file_path2     = get_file_path2,   -- limited
  get_file_ext       = get_file_ext,
}

-- 20250819 circular deps a

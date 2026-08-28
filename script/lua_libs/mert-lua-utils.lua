-- [[mert-lua-utils.lua]]

-- importing:
--
-- a03: new
-- local f = require("lua_init").utils()
--
-- a02: short
-- local u = require("mert-lua-utils")
-- u.read_file(mFn2Fp_path)
--
-- a03: refresh
-- package.loaded["mert-lua-utils"] = nil
-- local u = require("mert-lua-utils")

-- [[lua_init.lua]]
-- /usr/local/share/lua/5.4/lua_init.lua
-- /usr/local/share/lua/5.1/lua_init.lua
--
-- a02: long
-- package.path = package.path .. ";" .. os.getenv("HOME") .. "/prj/study/script/lua_libs/?.lua"
-- package.loaded["mert-lua-utils"] = nil
-- local u = require("mert-lua-utils")
--
-- u.read_file(mFn2Fp_path)
--
-- nvim: [[mert-lua-nvim.lua]]
--
--
-- rfr/toc::
--   clsf:
--     clsf: fpath: nm fns
--
-- package.path: import lua files #prg/lua
package.path = package.path .. ";" .. os.getenv("HOME") .. "/prj/study/script/lua_libs/?.lua"
package.path = package.path .. ";" .. os.getenv("HOME") .. "/.config/nvim/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/.config/lazyvim/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/.config/lazyvim/lua/config/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/luapath/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20240924-tfrm-wk-table/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241117-setops-ndx-files/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241117-setops-ndx-files/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241117-setops-ndx-files/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241117-setops-ndx-files/lua/?.lua"
package.path = package.path
	.. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241122-filter-edits-D-specs/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241121-edits-kybd/lua/?.lua"
package.path = package.path
	.. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241122-specs-8-edits-p-2-edits/lua/?.lua"
package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20250319-update-wk-tags/?.lua"

-- local u = {
-- 	dbg = require("mert-utils-debug"),
-- 	ds = require("mert-utils-data-structure"),
-- 	dt = require("mert-utils-date"),
-- 	fp = require("mert-utils-fpath"),
-- 	lg = require("mert-utils-io-logging"),
-- 	sr = require("mert-utils-io-serialize"),
-- 	rdr = require("mert-utils-readr"),
-- 	redis = require("mert-utils-redis"),
-- 	rgx = require("mert-utils-regex"),
-- 	text = require("mert-utils-text"),
-- 	tfrm = require("mert-utils-tfrm"),
-- 	wk = require("mert-utils-adg-wk"),
-- }

local u = {}

-- Use a metatable to lazily load mert-utils-data-structure
local mt = {
    __index = function(t, key)
        if key == "dbg" then
            local dbg = require("mert-utils-debug")
            -- rawset(t, "dbg", dbg)
            return dbg
        end
        if key == "ds" then
            local ds = require("mert-utils-data-structure")
            -- rawset(t, "ds", ds)
            return ds
        end
        if key == "dt" then
            local dt = require("mert-utils-date")
            -- rawset(t, "dt", dt)
            return dt
        end
        if key == "fp" then
            local fp = require("mert-utils-fp")
            -- rawset(t, "fp", fp)
            return fp
        end
        if key == "fpath" then
            local fpath = require("mert-utils-fpath")
            -- rawset(t, "fpath", fpath)
            return fpath
        end
        if key == "fpath_dir" then
            local fpath_dir = require("mert-utils-fpath-dir")
            -- rawset(t, "fpath", fpath)
            return fpath_dir
        end
        if key == "io" then
            local io = require("mert-utils-io")
            -- rawset(t, "io", io)
            return io
        end
        if key == "io_fpath" then
            local io_fpath = require("mert-utils-io-fpath")
            return io_fpath
        end
        if key == "lg" then
            local lg = require("mert-utils-io-logging")
            -- rawset(t, "lg", lg)
            return lg
        end
        if key == "sr" then
            local sr = require("mert-utils-io-serialize")
            -- rawset(t, "sr", sr)
            return sr
        end
        if key == "rdr" then
            local rdr = require("mert-utils-readr")
            -- rawset(t, "rdr", rdr)
            return rdr
        end
        if key == "redis" then
            local redis = require("mert-utils-redis")
            -- rawset(t, "redis", redis)
            return redis
        end
        if key == "redis_bbl" then
            local redis_bbl = require("mert-utils-redis-bbl")
            return redis_bbl
        end
        if key == "rgx" then
            local rgx = require("mert-utils-regex")
            -- rawset(t, "rgx", rgx)
            return rgx
        end
        if key == "text" then
            local text = require("mert-utils-text")
            -- rawset(t, "text", text)
            return text
        end
        if key == "text_bbl" then
            local text_bbl = require("mert-utils-text-bbl")
            return text_bbl
        end
        if key == "text_refid" then
            local text_refid = require("mert-utils-text-refid")
            return text_refid
        end
        if key == "tfrm" then
            local tfrm = require("mert-utils-tfrm")
            -- rawset(t, "tfrm", tfrm)
            return tfrm
        end
        if key == "wk" then
            local wk = require("mert-utils-adg-wk")
            rawset(t, "wk", wk)
            return wk
        end
    end
}

setmetatable(u, mt)

local M = {}  -- Create a table to hold the module's functions

-- mert-lua-utils.lua
-- Pure Lua utilities with no Neovim dependencies

local function executeSh(sh_script_path, args)
  -- id:: e43eb338-c3ef-441e-bb49-6c0df7b9e5dc
  -- date:: 20260202
  -- src: [[20241124-call-R-script-from-Lua.lua]]
  -- doc: Example usage to call R script from Lua via command line || ((c066ebb6-65e5-47f0-97a2-f0e088904871))
  --
  -- Validate input
  if not sh_script_path then
    error("Script path is required")
  end

  -- Check if file exists
  local f = io.open(sh_script_path, "r")
  if not f then
    error("Script file not found: " .. sh_script_path)
  end
  f:close()

  -- Construct the command
  local cmd = string.format('sh "%s"', sh_script_path)

  -- Add optional arguments if provided
  if args then
    if type(args) == "table" then
      for _, arg in ipairs(args) do
        cmd = cmd .. string.format(' "%s"', tostring(arg))
      end
    else
      cmd = cmd .. " " .. tostring(args)
    end
  end

  -- Execute the command
  local handle = io.popen(cmd)
  local result = handle:read("*a")
  local success, exit_type, exit_code = handle:close()

  -- Return results
  return {
    success = success,
    exit_type = exit_type,
    exit_code = exit_code,
    output = result,
  }
end

local function executeR(r_script_path, args)
  -- id:: ec2405b5-53f5-45b5-b788-86cd52720246
  -- date:: 20260202
  -- src: [[20241124-call-R-script-from-Lua.lua]]
  -- doc: Example usage to call R script from Lua via command line || ((c066ebb6-65e5-47f0-97a2-f0e088904871))
  --
  -- Validate input
  if not r_script_path then
    error("R script path is required")
  end

  -- Check if file exists
  local f = io.open(r_script_path, "r")
  if not f then
    error("R script file not found: " .. r_script_path)
  end
  f:close()

  -- Construct the command
  local cmd = string.format('Rscript "%s"', r_script_path)

  -- Add optional arguments if provided
  if args then
    if type(args) == "table" then
      for _, arg in ipairs(args) do
        cmd = cmd .. string.format(' "%s"', tostring(arg))
      end
    else
      cmd = cmd .. " " .. tostring(args)
    end
  end

  -- Execute the command
  local handle = io.popen(cmd)
  local result = handle:read("*a")
  local success, exit_type, exit_code = handle:close()

  -- Return results
  return {
    success = success,
    exit_type = exit_type,
    exit_code = exit_code,
    output = result,
  }
end

local function packagePathAdg()
  -- id:: 25276245-7670-4995-9ac1-8d429462f59d
  -- spcs: Import neovim lua scripts into lua repl environment `prg/lua prg/bash f/prmp` || ((48e50dc5-8d71-40d7-a9cf-15775502cf59))
  -- runtime append path
  print("specs_2_edits started")
  package.path = package.path .. ";/Users/mertnuhoglu/.config/lazyvim/lua/?.lua"
  package.path = package.path .. ";/Users/mertnuhoglu/.config/lazyvim/lua/config/?.lua"
  package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/luapath/lua/?.lua"
  package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20240924-tfrm-wk-table/lua/?.lua"
  package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241117-setops-ndx-files/lua/?.lua"
  package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241122-filter-edits-D-specs/lua/?.lua"
  package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241121-edits-kybd/lua/?.lua"
  package.path = package.path .. ";/Users/mertnuhoglu/prj/myrepo/logseq-myrepo/exmp/20241122-specs-8-edits-p-2-edits/lua/?.lua"
  package.path = package.path .. ";/Users/mertnuhoglu/prj/private_dotfiles/.config/params/out/?.lua"
end

local function helloworld()
  -- id:: b1efc16d-c872-4fd2-b4f3-840746cc5744
  -- date:: 20260202
	print("hello from mert-lua-utils.lua")
end

return {
	helloworld = helloworld,
	helloworld_adgwk = u.wk.helloworld_adgwk,
	-- utils

	-- reload
	packagePathAdg = packagePathAdg,
	executeR = executeR,
	executeSh = executeSh,


	-- [[mert-utils-data-structure.lua]]
	-- data structure
	getTitle = u.ds.getTitle,
	tableToOutline3Tbl = u.ds.tableToOutline3Tbl,
	tableToOutline = u.ds.tableToOutline,
	isArrayLike = u.ds.isArrayLike,
	len = u.ds.len,
	head_tbl = u.ds.head_tbl,
	tail_tbl = u.ds.tail_tbl,
	getLastN = u.ds.getLastN,
	merge = u.ds.merge,
	map = u.ds.map,
	map_kv = u.ds.map_kv,
	reduce = u.ds.reduce,
	partial = u.ds.partial,
	identity = u.ds.identity,
	filter_tbl = u.ds.filter_tbl,
	filter_tbl2 = u.ds.filter_tbl2,
	drop_tbl = u.ds.drop_tbl,
	drop_tbl2 = u.ds.drop_tbl2,
	deepcopy = u.ds.deepcopy,
	deep_merge = u.ds.deep_merge,
	innerJoinByPath = u.ds.innerJoinByPath,
	extractAttribute = u.ds.extractAttribute,
	removeElem = u.ds.removeElem,
	renameElem = u.ds.renameElem,
	tbl_minus = u.ds.tbl_minus,
	tbl_find_first_match = u.ds.tbl_find_first_match,
	is_empty = u.ds.is_empty,

	-- [[mert-utils-date.lua]]
	get_dt = u.dt.get_dt,
	get_time = u.dt.get_time,
	get_ts = u.dt.get_ts,

	-- [[mert-utils-adg-wk.lua]]
	make_stnd = u.wk.make_stnd,
	tfrm_edits_to_root = u.wk.tfrm_edits_to_root,
	tfrm_root2wk = u.wk.tfrm_root2wk,
	tfrm_root2edits = u.wk.tfrm_root2edits,
	tfrm_root2tags = u.wk.tfrm_root2tags,
	tfrm_root2brefs = u.wk.tfrm_root2brefs,
	tfrm_root2attrs = u.wk.tfrm_root2attrs,
	tfrm_root2tabrename = u.wk.tfrm_root2tabrename,
	tfrm_root2edit_rbl = u.wk.tfrm_root2edit_rbl,
	merge_cstm_w_stnd = u.wk.merge_cstm_w_stnd,
	tfrm_root2listtags = u.wk.tfrm_root2listtags,
	tfrm_root2kypn_tags = u.wk.tfrm_root2kypn_tags,
	tfrm_wk_tree_2_text_outline = u.wk.tfrm_wk_tree_2_text_outline,

	-- [[mert-utils-fp.lua]]
	-- functional programming
	map = u.fp.map,
	map_kv = u.fp.map_kv,
	reduce = u.fp.reduce,
	partial = u.fp.partial,
	identity = u.fp.identity,
	filter_tbl = u.fp.filter_tbl,
	drop_tbl2 = u.fp.drop_tbl2,
	drop_tbl = u.fp.drop_tbl,
	shallow_copy = u.fp.shallow_copy,

	-- [[mert-utils-fpath.lua]]
	-- fpath
	GetDefaultFlPtArgs = u.fpath.get_default_fl_pt_args,  -- #f/dprc
	GetDictFlPtArgs1 = u.fpath.get_dict_fl_pt_args1,  -- #f/dprc
	GetDictFlPtArgs2 = u.fpath.get_dict_fl_pt_args2,  -- #f/dprc
	get_default_fl_pt_args = u.fpath.get_default_fl_pt_args,
	get_dict_fl_pt_args1 = u.fpath.get_dict_fl_pt_args1,
	get_dict_fl_pt_args2 = u.fpath.get_dict_fl_pt_args2,

	cfile = u.fpath.cfile,
	get_fp_outlist = u.fpath.get_fp_outlist,
	getScriptFilename = u.fpath.getScriptFilename,
	getCurrentFunctionName = u.fpath.getCurrentFunctionName,

	fname_modify = u.fpath.fname_modify,
	block_line_2_kebab_file_name = u.fpath.block_line_2_kebab_file_name,
	trim_counter_number = u.fpath.trim_counter_number,

	get_files_in_dir = u.fpath.get_files_in_dir,
	get_last_file_by_pattern_in_dir = u.fpath.get_last_file_by_pattern_in_dir,
	match_files_in = u.fpath.match_files_in,

	get_fl_name_by_incr_c_fl = u.fpath.get_fl_name_by_incr_c_fl,
	get_fl_nm = u.fpath.get_fl_nm,
	nm_f_line = u.fpath.nm_f_line,
	nm_incr = u.fpath.nm_incr,
	nm_fc_file_change_ext = u.fpath.nm_change_ext,  -- #f/dprc
	nm_change_ext = u.fpath.nm_change_ext,
	nm_w_dt = u.fpath.nm_w_dt,  -- vim.fn.NmWDt
	nm_w_ts_post = u.fpath.nm_w_ts_post,
	nm_w_ts_pre = u.fpath.nm_w_ts_pre,
	nm_w_dt_pre_ts_post = u.fpath.nm_w_dt_pre_ts_post,  -- vim.fn.NmWDtPreTsPost
	nm_w_ts_only = u.fpath.nm_w_ts_only,
	nm_tmpl = u.fpath.nm_tmpl,
	nm_tmpl_w_fn = u.fpath.nm_tmpl_w_fn,

	get_file_path = u.fpath.get_file_path,
	get_fp_in_dir_of_that_fl = u.fpath.get_fp_in_dir_of_that_fl,
	dir_of_that_fl = u.fpath.dir_of_that_fl,

	cp3NmFn = u.fpath.cp3NmFn,
	cp3NmWTsPre = u.fpath.cp3NmWTsPre,
	cp3NmWDt = u.fpath.cp3NmWDt,
	get_path_by_tag = u.fpath.get_path_by_tag,

  get_up_dir         = u.fpath.get_up_dir,
  get_file_up_dir_name = u.fpath.get_file_up_dir_name,
  get_file_name_no_ext = u.fpath.get_file_name_no_ext,
  get_file_name_w_ext  = u.fpath.get_file_name_w_ext,
  get_file_dir       = u.fpath.get_file_dir,
  get_file_path2     = u.fpath.get_file_path2,   -- limited
  get_file_ext       = u.fpath.get_file_ext,

	-- [[mert-utils-fpath-dir.lua]]
	-- fpath-dir
  validate_logseq_repo_page_type = u.fpath_dir.validate_logseq_repo_page_type,
  get_dir_logseq_repo            = u.fpath_dir.get_dir_logseq_repo,
  get_logseq_path                = u.fpath_dir.get_logseq_path,
  get_nested_dict                = u.fpath_dir.get_nested_dict,
  get_dir                        = u.fpath_dir.get_dir,
  get_dir_fn                     = u.fpath_dir.get_dir_fn,
  get_fl_dir                     = u.fpath_dir.get_fl_dir,
  dir_path_2_dir_name            = u.fpath_dir.dir_path_2_dir_name,
  get_dir_last_in_nm_as_dt_pre   = u.fpath_dir.get_dir_last_in_nm_as_dt_pre,
  tilde2long                     = u.fpath_dir.tilde2long,
  dir_ls_dir                     = u.fpath_dir.dir_ls_dir,
  dir_of_that_fl                 = u.fpath_dir.dir_of_that_fl,
  get_dirpath_enew_exmp          = u.fpath_dir.get_dirpath_enew_exmp,
  dir_ts_in_exmp                 = u.fpath_dir.dir_ts_in_exmp,
  get_file_path_in               = u.fpath_dir.get_file_path_in,
  get_file_path_in_myr_exmp      = u.fpath_dir.get_file_path_in_myr_exmp,
  dir_myr_exmp                   = u.fpath_dir.dir_myr_exmp,

	-- [[mert-utils-io.lua]]
	-- io
	cp = u.io.cp,
	mv = u.io.mv,
	cp8mv = u.io.cp8mv,
	cp8mv_fn = u.io.cp8mv_fn,
	mkdir_p = u.io.mkdir_p,

	-- [[mert-utils-io-fpath.lua]]
	enew_npt_fl_f_fl = u.io_fpath.enew_npt_fl_f_fl,

	-- [[mert-utils-text.lua]]
	-- text
	is_blank = u.text.is_blank,
	substitutel = u.text.substitutel,
	clean_wk_otl = u.text.clean_wk_otl,
	tsv2tbl = u.text.tsv2tbl,
	table_to_tsv = u.text.table_to_tsv,
	split = u.text.split,
	flat_table_2_tsv_string = u.text.flat_table_2_tsv_string,
	get_column_f_tsv_str = u.text.get_column_f_tsv_str,
	file_paths_to_map = u.text.file_paths_to_map,
	table_to_literal = u.text.table_to_literal,
	addPrefix = u.text.addPrefix,
	add_prefix = u.text.addPrefix,
	trimPrefix = u.text.trimPrefix,
	trim_prefix = u.text.trimPrefix,
	get_fn = u.text.get_fn,
	sbst_underscore_w_slash = u.text.sbst_underscore_w_slash,
	sbst_slash_w_underscore = u.text.sbst_slash_w_underscore,
	gen_uuid = u.text.gen_uuid,
	escape_pattern_literal = u.text.escape_pattern_literal,
	sort_filenames_by_counter = u.text.sort_filenames_by_counter,
	strip = u.text.strip,
	strip2 = u.text.strip2,
	line2_comment4_ext = u.text.line2_comment4_ext,

	-- [[mert-utils-text-bbl.lua]]
	bbl_uuid = u.text_bbl.bbl_uuid,
	logseq_uuid_extract = u.text_bbl.bbl_uuid,
	bbl2tbl = u.text_bbl.bbl2tbl,
	logseq_block_title_2_dict = u.text_bbl.bbl2tbl,
	bbl_title = u.text_bbl.bbl_title,
	logseq_block_title_extract_pure = u.text_bbl.bbl_title,
	logseq_line_extract_without_id_string = u.text_bbl.logseq_line_extract_without_id_string,
	block_to_edn = u.text_bbl.bldt2edn,
	bldt2edn = u.text_bbl.bldt2edn,
	bbl2edn = u.text_bbl.bbl2edn,
	bbl2bldt = u.text_bbl.bbl2bldt,
	line_contains_logseq_id = u.text_bbl.line_contains_logseq_id,

	-- [[mert-utils-text-refid.lua]]
	logseq_check_id_exists = u.text_refid.logseq_check_id_exists,
	ref_id_logseq_fl = u.text_refid.ref_id_fl,
	ref_id_fl = u.text_refid.ref_id_fl,
	put_bref_2_ndx_by_tag = u.text_refid.put_bref_2_ndx_by_tag,
	put_bref_2_ndx_ln = u.text_refid.put_bref_2_ndx_ln,
	run_on_each_heading__put_bref_2_ndx_ln = u.text_refid.run_on_each_heading__put_bref_2_ndx_ln,

	-- [[mert-utils-io-logging.lua]]
	-- io: logging debug
	append_file = u.lg.append_file,
	write_runlog = u.lg.write_runlog,
	write_runlog_lua = u.lg.write_runlog_lua,
	write_outlist = u.lg.write_outlist,
	write_outlist_json = u.lg.write_outlist_json,
	write_outlist_cfile = u.lg.write_outlist_fl,  -- deprecated
	write_outlist_fl = u.lg.write_outlist_fl,
	write_bbl = u.lg.write_bbl,
	get_line_to_be_put = u.lg.get_line_to_be_put,
	put_line_to_end = u.lg.put_line_to_end,

	-- [[mert-utils-io-serialize.lua]]
	-- io: serialize
	serialize = u.sr.serialize,
	serializeOrdered = u.sr.serializeOrdered,
	serialize_obj_str = u.sr.serialize_obj_str,
	serialize_obj = u.sr.serialize_obj,
	serialize_obj_utils = u.sr.serialize_obj_utils,
	serialize_obj_factory_str = u.sr.serialize_obj_factory_str,
	serialize_obj_factory = u.sr.serialize_obj_factory,
	serialize_cfile = u.sr.serialize_cfile,
	serialize_fn = u.sr.serialize_fn,

	-- io: [[mert-utils-readr.lua]]
	read_file = u.rdr.read_file,
	write_file = u.rdr.write_file,
	read_cfile = u.rdr.read_cfile,
	write_cfile = u.rdr.write_cfile,
	read_fn = u.rdr.read_fn,
	write_fn = u.rdr.write_fn,
	s2df = u.rdr.s2df,
	read_tsv_file = u.rdr.read_tsv_file,
	read_tsv_cfile = u.rdr.read_tsv_cfile,
	read_tsv = u.rdr.read_tsv_file,
	read_tsvc = u.rdr.read_tsv_cfile,
	read_tsv_fn = u.rdr.read_tsv_fn,
	read_json = u.rdr.read_json,
	write_json = u.rdr.write_json,
	format_json = u.rdr.format_json,
	write_table_2_tsv = u.rdr.write_table_2_tsv,
	loadTableFromFile = u.rdr.loadTableFromFile,
	write_lines = u.rdr.write_lines,

	-- [[mert-utils-redis.lua]]
	rset = u.redis.rset,
	rget = u.redis.rget,
	rget_path = u.redis.rget_path,
	rhget_refid = u.redis.rhget_refid,
	rget_prefix = u.redis.rget_prefix,
	rget_keys = u.redis.rget_keys,
	redis_set = u.redis.set_kv3df,
	set_kv3df = u.redis.set_kv3df,
	hset_tbl = u.redis.hset_rcrd,
	hset_rcrd = u.redis.hset_rcrd,
	hget_tbl = u.redis.hget_fields,
	hget_fields = u.redis.hget_fields,
	fn2fp = u.redis.fn2fp,
	rhset_refid_fp_lnum = u.redis.rhset_refid_fp_lnum,
	mFn2Fp_lua = u.redis.mFn2Fp_lua,
	mFn2Fp_redis = u.redis.mFn2Fp_redis,
	mFn2Fp = u.redis.mFn2Fp,
	map_oldfiles = u.redis.map_oldfiles,
	fuzzyLookup = u.redis.fuzzyLookup,
	importWikilinkIntoRedis = u.redis.importWikilinkIntoRedis,
	params_to_redis = u.redis.params_to_redis,
	redis_to_fn = u.redis.redis_to_fn,
	import_tbl_to_redis = u.redis.import_tbl_to_redis,
	import_json_file_to_redis = u.redis.import_json_file_to_redis,

	-- [[mert-utils-redis-bbl]]
	bldt2redis = u.redis_bbl.bldt2redis,
	hset_bldt = u.redis_bbl.bldt2redis,  -- f/dprc
	hset_tbl_block = u.redis_bbl.bldt2redis,  -- f/dprc
	bbl2redis = u.redis_bbl.bbl2redis,
	get_bldt = u.redis_bbl.get_bldt,
	hget_tbl_block = u.redis_bbl.hget_tbl_block,

	-- [[mert-utils-debug.lua]]
	-- debug
	reload = u.dbg.reload,
	eval_lua_variable = u.dbg.eval_lua_variable,
	eval_lua_expression = u.dbg.eval_lua_expression,
	get_script_path = u.dbg.get_script_path,
	get_caller_script_path = u.dbg.get_caller_script_path,
	toStringVar = u.dbg.toStringVar,
	printVar = u.dbg.printVar,
	printTable = u.dbg.printTable,
	valueToString = u.dbg.valueToString,
	tableToString = u.dbg.tableToString,
	inspect = u.dbg.inspect,

	-- [[mert-utils-tfrm.lua]]
	-- transform
	df2kv3key_val = u.tfrm.df2kv3key_val,
	kv2df3key_val = u.tfrm.kv2df3key_val,
	filterTwoTuples = u.tfrm.filterTwoTuples,
	get_values = u.tfrm.get_values,
	df2r = u.tfrm.df2r,

}


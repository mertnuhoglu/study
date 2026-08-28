-- [[mert-utils-fpath-dir.lua]]
--
-- prn: [[mert-utils-fpath.lua]]
-- prn: [[mert-lua-utils.lua]]
-- mplm: [[20260211-lua-dir-path-fns.lua]]

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

local function tilde2long(path)
  -- Very naive replacement – in real usage consider vim.fn.expand("~") or os.getenv("HOME")
  return (path:gsub("^~", os.getenv("HOME") or "/Users/mertnuhoglu"))
end

-- ────────────────────────────────────────────────────────────────────────────────
--  Main translated functions
-- ────────────────────────────────────────────────────────────────────────────────

local p = {}

p = {
  logseq_repo_set = { "study", "cllb", "myrepo", "prvx" },
  page_type_set   = { "journals", "pages", "twtp", "log", "tmp", "exmp", "prmp" },
}

p.dir = {}

p.dir.params = {
  root   = "/Users/mertnuhoglu/prj/private_dotfiles/.config/params",
  args   = nil,   -- will be filled below
  out    = nil,
  outlist = nil,
}
local params = p.dir.params
params.args   = params.root .. "/args"
params.out    = params.root .. "/out"
params.outlist = params.root .. "/outlist"

-- Put this e.g. in lua/user/config/paths.lua or similar
-- and then do: require("user.config.paths") somewhere in your config

p.dir.myrepo = "/Users/mertnuhoglu/prj/myrepo"

p.dir.logseq = {
  myr  = {},
  std  = {},
  cllb = {},
}

p.dir.logseq.myr.root  = "/Users/mertnuhoglu/prj/myrepo/logseq-myrepo"
p.dir.logseq.std.root  = "/Users/mertnuhoglu/prj/study/logseq-study"
p.dir.logseq.cllb.root = "/Users/mertnuhoglu/prj/collabryio/cldocs/cllogseq"

p.dir.logseq.myr.jrnl  = p.dir.logseq.myr.root  .. "/journals"
p.dir.logseq.std.jrnl  = p.dir.logseq.std.root  .. "/journals"
p.dir.logseq.cllb.jrnl = p.dir.logseq.cllb.root .. "/journals"

p.dir.logseq.myr.pages  = p.dir.logseq.myr.root  .. "/pages"
p.dir.logseq.std.pages  = p.dir.logseq.std.root  .. "/pages"
p.dir.logseq.cllb.pages = p.dir.logseq.cllb.root .. "/pages"

p.dir.logseq.myr.exmp = {
  root  = p.dir.logseq.myr.root .. "/exmp",
  scrap = p.dir.logseq.myr.root .. "/exmp/20250320-scrap",
}

p.dir.logseq.std.exmp  = p.dir.logseq.std.root  .. "/exmp"
p.dir.logseq.cllb.exmp = p.dir.logseq.cllb.root .. "/exmp"

p.dir.logseq.cllb.clinbox = {
  whatsapp_archive = "/Users/mertnuhoglu/projects/collabryio/clbackup/archive/whatsapp-konusmalar",
}

p.dir.logseq.myr.luapath = p.dir.logseq.myr.exmp.root .. "/luapath/lua"
p.dir.logseq.myr.bldt    = p.dir.logseq.myr.root  .. "/bldt"

p.dir.scrap     = "/Users/mertnuhoglu/prj/myrepo/scrap"

p.dir.out       = p.dir.scrap .. "/out"
p.dir.myrexmp   = p.dir.logseq.myr.root .. "/exmp"
p.dir.exmp_scrap = p.dir.logseq.myr.exmp.scrap
p.dir.myrdebug  = p.dir.logseq.myr.root .. "/exmp/debug"
p.dir.session   = p.dir.out .. "/session"
p.dir.data      = p.dir.scrap .. "/data"
p.dir.oldfiles  = p.dir.out .. "/oldfiles"
p.dir.refid     = p.dir.out .. "/refid"

p.dir.script    = "/Users/mertnuhoglu/prj/study/script"
p.dir.luapath   = p.dir.logseq.myr.luapath

p.dir.myinbox = {
  root  = "/Users/mertnuhoglu/prj/myinbox",
  mydoc = "/Users/mertnuhoglu/prj/myinbox/mydoc",
}

p.dir_ext = {
  out      = ".txt",
  session  = ".vim",
  data     = ".txt",
  oldfiles = ".txt",
  refid    = ".lua",          -- ← note: this one was different in original
}

p.dir.lazyvim        = "/Users/mertnuhoglu/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua"
p.dir.lazyvim_plugins = p.dir.lazyvim .. "/plugins"
p.dir.lazyvim_config  = "/Users/mertnuhoglu/prj/private_dotfiles/.config/nvimconfigs/lazyvim/lua/config"

p.dir.params = {
  root   = "/Users/mertnuhoglu/prj/private_dotfiles/.config/params",
  args   = "/Users/mertnuhoglu/prj/private_dotfiles/.config/params/args",
  out    = "/Users/mertnuhoglu/prj/private_dotfiles/.config/params/out",
  outlist = "/Users/mertnuhoglu/prj/private_dotfiles/.config/params/outlist",
}

p.dir.paramsout = p.dir.params.out           -- alias
p.dir.bldt      = p.dir.logseq.myr.bldt      -- alias

p.logseq_path = {
  study  = "prj/study/logseq-study",
  cllb   = "prj/collabryio/cldocs/cllogseq",
  myrepo = "prj/myrepo/logseq-myrepo",
}

-- Optional: make it read-only / frozen (good practice)
local function freeze(t) return setmetatable(t, { __newindex = function() error("attempt to modify frozen table") end }) end
p = freeze(p)

-- luafilesystem: [grok](https://grok.com/share/bGVnYWN5_d9c3d9b6-cb8c-4c25-842c-a51c36001bc1)
local lfs = require("lfs")

local function get_date_dirs(path)
	-- mplm: [[20260216-get-dirs-in-a-path.lua]]
  local dirs = {}

  -- lfs.attributes can throw → pcall or assert
  local ok, iter, dir_obj = pcall(lfs.dir, path)
  if not ok then
    return dirs  -- or error(iter) depending on taste
  end

  for entry in iter, dir_obj do
    if entry ~= "." and entry ~= ".." then
      local full = path .. "/" .. entry
      local attr = lfs.attributes(full)
      if attr and attr.mode == "directory" then
        if entry:match("^%d%d%d%d%d%d%d%d%-") then
          table.insert(dirs, full)
        end
      end
    end
  end

  -- Optional: sort like glob usually does
  table.sort(dirs)
  return dirs
end

local function validate_logseq_repo_page_type(logseq_repo, page_type)
  local page_type_set = p.page_type_set 
  local logseq_repo_set = p.logseq_repo_set 

  local page_ok = false
  for _, v in ipairs(page_type_set) do
    if v == page_type then page_ok = true; break end
  end
  if not page_ok then
    error(string.format("page_type must be one of: %s", table.concat(page_type_set, ", ")))
  end

  local repo_ok = false
  for _, v in ipairs(logseq_repo_set) do
    if v == logseq_repo then repo_ok = true; break end
  end
  if not repo_ok then
    error(string.format("logseq_repo must be one of: %s", table.concat(logseq_repo_set, ", ")))
  end
end

local function get_dir_logseq_repo(logseq_repo)
  local logseq_path = {
    study  = "prj/study/logseq-study",
    cllb   = "prj/collabryio/cldocs/cllogseq",
    myrepo = "prj/myrepo/logseq-myrepo",
  }

  local dir_rel = logseq_path[logseq_repo] or "prj/collabryio/cldocs/cllogseq"
  local full_dir = tilde2long("~/" .. dir_rel)
  return full_dir
end

local function get_logseq_path(logseq_repo, page_type, custom_title)
  custom_title = custom_title or ""

  validate_logseq_repo_page_type(logseq_repo, page_type)

  local dir = get_dir_logseq_repo(logseq_repo)

  if page_type == "journals" then
    return dir .. "/journals/" .. os.date("%Y_%m_%d") .. ".md"

  elseif page_type == "pages" then
    if custom_title == "" then
      return dir .. "/pages/" .. u.u.get_dt() .. "-" .. logseq_repo .. ".md"
    else
      local filename = u.u.block_line_2_kebab_file_name(custom_title)
      return dir .. "/pages/" .. filename .. ".md"
    end

  elseif page_type == "prmp" then
    return dir .. "/pages/" .. u.u.get_dt() .. "-prmp.md"

  elseif page_type == "twtp" then
    return dir .. "/pages/" .. u.u.get_dt() .. "-twtp.md"

  elseif page_type == "log" then
    return dir .. "/pages/" .. u.u.get_dt() .. "-log.md"

  elseif page_type == "tmp" then
    return dir .. "/pages/tmp/" .. u.u.get_dt() .. "-tmp.md"

  elseif page_type == "prvx" then
    return dir .. "/pages/prvx/" .. u.u.get_dt() .. "-prvx.md"

  elseif page_type == "exmp" then
    return dir .. "/exmp"

  else
    error("Unknown page_type: " .. tostring(page_type))
  end
end

-- ────────────────────────────────────────────────────────────────────────────────
--  Dictionary / Path utilities
-- ────────────────────────────────────────────────────────────────────────────────

--- Get nested value from table using dot notation path
--- @param dict table
--- @param key_path string  e.g. "dir.myr.exmp.root"
--- @return any
local function get_nested_dict(dict, key_path)
  local keys = {}
  for part in key_path:gmatch("[^.]+") do
    table.insert(keys, part)
  end

  local current = dict
  for _, key in ipairs(keys) do
    if type(current) == "table" and current[key] ~= nil then
      current = current[key]
    else
      error("Key not found: " .. key)
    end
  end
  return current
end

--- Shortcut for g:p.dir access
--- @param dirname string  e.g. "myr.exmp.root"
local function get_dir(dirname)
  if not p or not p.dir then
    error("g:p.dir is not defined")
  end
  return get_nested_dict(p.dir, dirname)
end

--- Returns a closure function that always returns the same directory
--- @param dirname string
local function get_dir_fn(dirname)
  return function(_)
    return get_dir(dirname)
  end
end

--- Call a directory function with a file path (most implementations ignore arg)
--- @param dir_fn function
--- @param fl_pt string
local function get_fl_dir(dir_fn, fl_pt)
  return dir_fn(fl_pt)
end

--- Get last directory name from path
--- @param dirpath string
local function dir_path_2_dir_name(dirpath)
  local cleaned = dirpath:gsub("/$", "")
  return u.u.fname_modify(cleaned, ":t")
end

--- Find the most recent directory that starts with 8-digit date
--- @param path string
local function get_dir_last_in_nm_as_dt_pre(path)
  -- id:: 77cd1681-0de8-43fb-bfd7-4a6e50aba1f5
  -- date:: 20260216
	-- [grok](https://grok.com/share/bGVnYWN5_d9c3d9b6-cb8c-4c25-842c-a51c36001bc1)
  local dirs = get_date_dirs(path)
  return dirs[#dirs]
end

--- Parent directory of given file path
local function dir_of_that_fl(m)
  return u.u.fname_modify(m.flPt, ":p:h")
end

--- New example directory path (timestamp based)
local function get_dirpath_enew_exmp()
  local ex_path = get_logseq_path("myrepo", "exmp")
  local dirname = u.u.get_ts()
  local dir = ex_path .. "/" .. dirname
  return tilde2long(dir)
end

local function dir_ts_in_exmp(_)
  return get_dirpath_enew_exmp()
end

--- Generic file path generator inside known directories
--- @param dir string key in g:p.dir
--- @param ext? string optional extension override
local function get_file_path_in(dir, ext)
  ext = ext or (p and p.dir_ext and p.dir_ext[dir]) or ""

  local filename = u.u.get_ts()
  local dest_dir = p and p.dir and p.dir[dir]

  if not dest_dir then
    error("Directory key not found in g:p.dir: " .. dir)
  end

  return dest_dir .. "/" .. filename .. "-" .. dir .. ext
end

local function get_file_path_in_myr_exmp()
  return get_file_path_in("myrexmp")
end

local function dir_myr_exmp(_)
  local filepath = get_file_path_in_myr_exmp()
  return u.u.fname_modify(filepath, ":p:h")
end

return {
  validate_logseq_repo_page_type = validate_logseq_repo_page_type,
  get_dir_logseq_repo            = get_dir_logseq_repo,
  get_logseq_path                = get_logseq_path,
  get_nested_dict                = get_nested_dict,
  get_dir                        = get_dir,
  get_dir_fn                     = get_dir_fn,
  get_fl_dir                     = get_fl_dir,
  dir_path_2_dir_name            = dir_path_2_dir_name,
  get_dir_last_in_nm_as_dt_pre   = get_dir_last_in_nm_as_dt_pre,
  tilde2long                     = tilde2long,
  dir_ls_dir                     = dir_ls_dir,
  dir_of_that_fl                 = dir_of_that_fl,
  get_dirpath_enew_exmp          = get_dirpath_enew_exmp,
  dir_ts_in_exmp                 = dir_ts_in_exmp,
  get_file_path_in               = get_file_path_in,
  get_file_path_in_myr_exmp      = get_file_path_in_myr_exmp,
  dir_myr_exmp                   = dir_myr_exmp,
}

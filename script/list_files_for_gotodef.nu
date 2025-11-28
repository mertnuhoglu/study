#!/usr/bin/env nu
# [[list_files_for_gotodef.nu]]
# 
# tpt: [[files_in_dirs.txt]]
# pprv: [[list_files_for_gotodef.sh]]
# spcs: src: [[20250111-gotoDef-all-files.sh]]
# mpct: function gotoDef() -- SPC ffe || ((1348ce1d-805d-431c-a2a2-83d57b7e941f))
# [[files_in_dirs.txt]]
# debug: [[20250716-debug-list_files_for_gotodef.nu]]
# 20250716 debug list_files_for_gotodef

# [grok](https://grok.com/share/bGVnYWN5_08e6e8e3-f7a6-4f04-bfe7-3890c31b3305)

source ~/.config/nushell/modules/mert_utils.nu

let home = $env.HOME

let exts = [
    clj, cljc, cljs, txt, md, lua, otl, tsv, html, R, sh, css, Rmd, json, yaml, sql, edn, csv, vim, log, xml, bb, nu
]

let ext_args = ($exts | each { |it| ["-e", $it] } | flatten)

let dirs = [
    $"($home)/prj/myrepo",
    $"($home)/prj/study",
    $"($home)/prj/myinbox",
    $"($home)/prj/stuff",
    $"($home)/prj/rutils",
    $"($home)/prj/collabryio/cldocs/cllogseq",
    $"($home)/prj/collabryio/clteam",
    $"($home)/prj/collabryio/clinbox",
    $"($home)/projects/collabryio/oryn-app",
    $"($home)/prj/private_dotfiles",
    $"($home)/prj/private_dotfiles/.config",
    $"($home)/.local/share/nvim/lazy",
    $"($home)/prj/vim_repos",
    $"($home)/codes/clj/ex/electric-fiddle",
    $"($home)/prj/myrepo/prj/cmmi",
    $"($home)/prj/myrepo/prj/cmmi/doc",
    $"($home)/Downloads"
]

let exclude_patterns = [
    "/node_modules/",
    ".cache.",
    "/target/",
    "/logseq/version-files",
    "/out/clojure",
    "/logseq/bak/",
    "/quarto/_site/",
    "/.local/share/nvim/lazy",
    "/public/js",
    "/resources/public/",
    "/vendors/",
    "/cljs-out/",
    "/public/cljs/"
]

let files = (fd --hidden --absolute-path -t f --no-ignore ...$ext_args . ...$dirs | lines | where { |line|
    not ($exclude_patterns | any { |pat| $line | str contains $pat })
})

$files | str join (char newline) | save -f "/Users/mertnuhoglu/prj/myrepo/scrap/out/oldfiles/files_in_dirs.txt"

print $"(get_ts) completed: list_files_for_gotodef.nu"



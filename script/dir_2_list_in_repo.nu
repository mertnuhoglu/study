# [[dir_2_list_in_repo.nu]]
#
# List all files in cllogseq #f/scrp
  # id:: 83752498-9352-46b4-819f-064c60158bdb
  # date:: 20251127
# pprv: [[dir_2_list_md_in_logseq_cllb.sh]]
# tpt: [[Document-List-cllb.md]]
#   [[Document-List-myr.md]]
# prt: [[dir_2_list_md.nu]]
#
# Usage:
#   nu ~/prj/study/script/dir_2_list_in_repo.nu

def get_params [repo: string] {
	source ~/.config/nushell/env.nu
	if $repo not-in ["myr", "cl"] {
		error make {
			msg: $"Invalid argument: '($repo)'\nExpected one of: myr  cllb"
		}
	}

	let repos = {
		myr: $env.DIR_MYREPO_LOGSEQ,
		cl: $env.DIR_CLLOGSEQ,
	}
	let docs = {
		myr: "myr",
		cl: "cllb",
	}

	{
		DIR: ($repos | get $repo)
		doc: ($docs | get $repo)
		repo: $repo
	}
}

def dir_2_list_in_repo_by_ext [ repo: string, ext: string] {  #  #f/myst #f/scrp #prg/nu
	let p = get_params $repo

	let DIR = $p.DIR
	let DATE = (date now | format date "%Y%m%d")
	let FILENAME = $"Doc-List-($ext)-($p.doc).md"
	let OUTPUT = $"($DIR)/pages/Doc-List-($ext)-($p.doc).md"

	let files = (nu ~/prj/study/script/dir_2_list_md.nu $DIR $ext)

	let hl = [$"tags:: ($p.doc), f/ndx", $"date:: ($DATE)", ".", $"- # Doc-List-($ext)-($p.doc)", "."]
	let hls = $hl | str join "\n"
	let lns = ([$hls, $files] | str join "\n")
	$lns | save -f $OUTPUT

	do { $FILENAME | ^pbcopy }
	print $OUTPUT

}
def dir_2_list_in_repo [ repo: string ] {  #  #f/myst #f/scrp #prg/nu
  # id:: c7e662f3-3c28-4dcb-a449-9875cc745b99
  # date:: 20251128
	dir_2_list_in_repo_by_ext $repo 'md'
	dir_2_list_in_repo_by_ext $repo 'otl'
}

def main [] {
	dir_2_list_in_repo cl
	dir_2_list_in_repo myr
}

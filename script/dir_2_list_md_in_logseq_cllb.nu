# [[dir_2_list_md_in_logseq_cllb.nu]]
#
# List all files in cllogseq #f/scrp
  # id:: 83752498-9352-46b4-819f-064c60158bdb
  # date:: 20251127
  # [[dir_2_list_md_in_logseq_cllb.nu]]

def get_params [repo: string] {
	source ~/.config/nushell/env.nu
	if $repo not-in ["myr", "cl"] {
		error make {
			msg: $"Invalid argument: '($repo)'\nExpected one of: myr  cllb"
		}
	}

	let repos = {
		myr: $env.DIR_MYREPO_LOGSEQ,
		cl: $env.DIR_STUDY_LOGSEQ,
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

def dir_2_list_in_repo [ repo: string ] {
	let p = get_params $repo

	let DIR = $p.DIR
	let DATE = (date now | format date "%Y%m%d")
	let FILENAME = $"Document-List-($p.doc).md"
	let OUTPUT = $"($DIR)/pages/Document-List-($p.doc).md"

	let files = (nu ~/prj/study/script/dir_2_list_md.nu $DIR)

	let hl = [$"tags:: ($p.doc), f/ndx", $"date:: ($DATE)", ".", "- # ($DATE)-Document-List-($p.doc)", "."]
	let lns = ($files + $hl)
	$result | save -f $OUTPUT

	do { $FILENAME | ^pbcopy }
	print $OUTPUT

}

def main [] {
	dir_2_list_in_repo cl
}

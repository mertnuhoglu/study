#!/usr/bin/env nu
# [[dir_2_list_tag.nu]]
#
# Title: Directory to List of Tags [[dir_2_list_tag.nu]] #myst #f/script
#   id:: d1238b17-5cf1-4e80-bf8b-46d51c477d27
# Date: 20250528
#
# pprv: [[dir_2_list_tag.sh]]
# [grok](https://grok.com/chat/ea38b467-dab6-43f0-995d-9f8d97aa3bb5)

def main [DIR: string, REPO: string] {
	let DATE = (date now | format date "%Y%m%d")
	let BASENAME = $"($DATE)-Tag-List-($REPO)"
	let FILENAME = $"($BASENAME).md"
	let SCRAP_DIR = $"($env.HOME)/prj/myrepo/scrap/out"
	let OUTPUT_RG = $"($SCRAP_DIR)/($BASENAME).txt"
	let OUTPUT = $"($DIR)/out/($FILENAME)"
	let COPY_CURRENT = $"($DIR)/pages/Tag-List-out-($REPO).md"
	# let dfl = {
	# 	name = "dir_2_list_tag.nu",
	# 	inlist = [ $DIR ],
	# 	outlist = [ $OUTPUT ],
	# }

	print $OUTPUT
	cd $DIR
	# ignore files that already includes previously extracted tags such as: Tag-List.md
	rg --ignore-file ~/prj/study/script/.tag_extract_ignore "#\\w+" | save -f --raw $"($OUTPUT_RG)"


	let header = $"tags:: ($REPO), f/ndx
date:: ($DATE)
.
- # ($BASENAME)
.
- prn: [[ndx/Tag-List-myr]]
- prn: [[ndx/Tag-List-cllb]]
"

	use ~/.config/nushell/modules/mert_utils.nu logseq-extract-tags-run
	# let r01 = logseq-extract-tags-run $"($OUTPUT_RG)"
	# # let r01 = open "logseq-output1.txt"
	# let r02 = $r01 | lines 
	# let r03 = $r02 | sort
	# let r04 = $r03 | uniq
	# cd $"($SCRAP_DIR)"
	# $r01 | save -f "logseq-output1.txt"
	# $r02 | save -f "logseq-output2.txt"
	# $r03 | save -f "logseq-output3.txt"
	# $r04 | save -f "logseq-output4.txt"
	# $r04 | save -f --raw $"($DIR)/out/logseq-output4b.txt"
	# let OUTPUT2 = $"($DIR)/out/($BASENAME)2.md"
	# $r04 | save -f --raw $"($OUTPUT2)"
	# $r04 | save -f $"($FILENAME)"
	# $r01 | lines | sort | uniq | save -f $"($FILENAME)"
	logseq-extract-tags-run $"($OUTPUT_RG)" | lines | sort | uniq | prepend $header | save -f --raw $"($OUTPUT)"
	# nvim -c "LogseqExtractTags" -c "wq" $BASENAME.txt

	cp -f $"($OUTPUT)" $"($SCRAP_DIR)/tags/tags_($REPO).txt"
	cp -f $"($OUTPUT)" $COPY_CURRENT

	# pbcopy equivalent in Nushell would need external command or clipboard module
}

# Character encoding formatting: 
# [grok](https://grok.com/chat/a774a2b0-4315-4f01-8825-e9b63925cfe2)
# Use `--raw` flag with `save` command

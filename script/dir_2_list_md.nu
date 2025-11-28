# [[dir_2_list_md.nu]]
#
# Directory to List of Markdown Files  #myst #f/script
#   id:: df371dc9-e2c6-493d-b946-f6f1d3503caa
#   date: 20240121
# [[dir_2_list_md.nu]]
#
# pprv: [[dir_2_list_md.sh]]
# [grok](https://grok.com/share/bGVnYWN5_8a7e871f-bafe-434c-a9bb-82a10c00c44d)
# [grok](https://grok.com/share/bGVnYWN5_6f2f9e79-7198-4b2f-883a-3ffc60792987)
#
# nu ~/prj/study/script/dir_2_list_md.nu /Users/mertnuhoglu/prj/collabryio/cldocs/cllogseq
# [[dir_2_list_md_in_logseq_cllb2.nu]]

def main [dir: string] {
	let base = ($dir | path expand)
	let files = (glob ($base | path join "**/*.md"))
	let transformed = (
		$files
		| each { |f|
			let rel = ($f | path relative-to $base)
			let cleaned = ($rel | str replace --regex '\.md$' '')
			let wikified = '[[' + $cleaned + ']]'
			let trimmed = ($wikified | str replace 'pages/' '' | str replace 'journals/' '')
			$trimmed
		}
		| where { |line| 
			(not 
				(($line | str contains 'logseq/version-files') or
				($line | str contains 'archive/') or
				($line | str contains 'logseq/bak')))
		}
	)
	$transformed | sort
}

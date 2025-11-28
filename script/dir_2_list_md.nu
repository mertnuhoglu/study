# [[dir_2_list_md.nu]]
#
# Directory to List of Markdown Files  #myst #f/script
#   id:: df371dc9-e2c6-493d-b946-f6f1d3503caa
#   date: 20240121
#
# called-by: [[dir_2_list_in_repo.nu]]
# pprv: [[dir_2_list_md.sh]]
# [grok](https://grok.com/share/bGVnYWN5_8a7e871f-bafe-434c-a9bb-82a10c00c44d)
# [grok](https://grok.com/share/bGVnYWN5_6f2f9e79-7198-4b2f-883a-3ffc60792987)
#
# Usage:
#   nu ~/prj/study/script/dir_2_list_md.nu /Users/mertnuhoglu/prj/collabryio/cldocs/cllogseq

def main [DIR: string, ext: string] {
	let base = ($DIR | path expand)
	let files = (glob ($base | path join $"**/*.($ext)"))
	let transformed = (
		$files
		| each { |f|
			let rel = ($f | path relative-to $base)
			# let cleaned = ($rel | str replace --regex $'\.($ext)$' '')
			let wikified = '[[' + $rel + ']]'
			let trimmed = ($wikified | str replace 'pages/' '' | str replace 'journals/' '')
			$trimmed
		}
		| where { |line| 
			(not 
				(($line | str contains 'logseq/version-files') or
				 ($line | str contains 'archive/') or
				 ($line | str contains '.out/') or
				 ($line | str contains 'logseq/bak')))
		}
	)
	$transformed | sort | to text
}

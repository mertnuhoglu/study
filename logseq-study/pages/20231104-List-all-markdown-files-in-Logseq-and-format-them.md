tags:: study, myst, vim, f/script
date:: 20231104

- # 20231104-List-all-markdown-files-in-Logseq-and-format-them #myst #f/script
  id:: a40d9829-8a42-4148-bf05-7f01763208be

id=g15155

- ((3cf0f5b1-db25-421c-a4b2-d1e5ed37aebe)) || List all files into a text file 
- ((11d3fb50-a6f2-4e52-812b-719fbf79a601)) || Format markdown file list into wikilinks
- ((4032de0d-216e-4c9d-8725-3912f76599be)) || Create a date string of current date 

All together:

```sh
fd --extension md --type f | sed -e 's|^./||' -e 's|.md$||' -e 's|^|[[|' -e 's|$|]]|' > "`date +%Y%m%d`-Document-List.md"
```

Example Output: `~/gdrive/grsm/opal/docs-grsm/pages/20231104-Document-List.md`

- rfr: script: 
	- [[dir_2_list_md.sh]]
	- [[dir_2_list_md_in_logseq_myrepo.sh]]
	- [[dir_2_list_md_in_logseq_cllb.sh]]

```sh
sh ~/prj/study/script/dir_2_list_md_in_logseq_myrepo.sh
```


# [[list_files_for_gotodef.sh]]
#
# spcs: src: [[20250111-gotoDef-all-files.sh]]
# mpct: function gotoDef() -- SPC ffe || ((1348ce1d-805d-431c-a2a2-83d57b7e941f))

echo started: ~/prj/study/script/list_files_for_gotodef.sh
FILE_EXTENSIONS="-e clj -e txt -e md -e lua -e otl -e tsv -e html -e R -e sh -e css -e Rmd -e json -e yaml -e sql  -e edn -e csv -e vim -e log "
DIRS="$HOME/prj/myrepo $HOME/prj/study $HOME/prj/myinbox $HOME/prj/stuff $HOME/prj/rutils $HOME/prj/collabryio/cldocs/cllogseq $HOME/prj/collabryio/clteam $HOME/prj/collabryio/clinbox $HOME/projects/collabryio/oryn-app $HOME/prj/private_dotfiles $HOME/prj/private_dotfiles/.config $HOME/.local/share/nvim/lazy $HOME/prj/vim_repos $HOME/codes/clj/ex/electric-fiddle $HOME/prj/myrepo/prj/cmmi $HOME/prj/myrepo/prj/cmmi/doc "
CMD="fd -a -t f ${FILE_EXTENSIONS} . ${DIRS}"
eval "$CMD" > /Users/mertnuhoglu/prj/myrepo/scrap/out/oldfiles/files_in_dirs.txt
echo $(date '+%Y%m%d-%H%M%S') ": completed: list_files_for_gotodef.sh" 


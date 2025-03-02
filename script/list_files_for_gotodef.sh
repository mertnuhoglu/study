# [[list_files_for_gotodef.sh]]
#
# spcs: src: [[20250111-gotoDef-all-files.sh]]
# mpct: function gotoDef() -- SPC ffe || ((1348ce1d-805d-431c-a2a2-83d57b7e941f))
# [[files_in_dirs.txt]]

echo $(timestamp) started: ~/prj/study/script/list_files_for_gotodef.sh
FILE_EXTENSIONS="-e clj -e cljc -e cljs -e txt -e md -e lua -e otl -e tsv -e html -e R -e sh -e css -e Rmd -e json -e yaml -e sql  -e edn -e csv -e vim -e log -e xml "
DIRS="$HOME/prj/myrepo $HOME/prj/study $HOME/prj/myinbox $HOME/prj/stuff $HOME/prj/rutils $HOME/prj/collabryio/cldocs/cllogseq $HOME/prj/collabryio/clteam $HOME/prj/collabryio/clinbox $HOME/projects/collabryio/oryn-app $HOME/prj/private_dotfiles $HOME/prj/private_dotfiles/.config $HOME/.local/share/nvim/lazy $HOME/prj/vim_repos $HOME/codes/clj/ex/electric-fiddle $HOME/prj/myrepo/prj/cmmi $HOME/prj/myrepo/prj/cmmi/doc $HOME/Downloads "
CMD="fd --absolute-path -t f --no-ignore ${FILE_EXTENSIONS} . ${DIRS}"
eval "$CMD" | \
sed \
  -e '/\/node_modules\//d' \
  -e '/\.cache\./d' \
  -e '/\/target\//d' \
  -e '/\/logseq\/version-files/d' \
  -e '/\/out\/clojure/d' \
  -e '/\/logseq\/bak\//d' \
  -e '/\/quarto\/_site\//d' \
  -e '/\/\.local\/share\/nvim\/lazy/d' \
  -e '/\/public\/js/d' \
  -e '/\/resources\/public\//d' \
  -e '/\/vendors\//d' \
  -e '/\/cljs-out\//d' \
  -e '/\/public\/cljs\//d' \
  > /Users/mertnuhoglu/prj/myrepo/scrap/out/oldfiles/files_in_dirs.txt
echo $(timestamp) completed: list_files_for_gotodef.sh


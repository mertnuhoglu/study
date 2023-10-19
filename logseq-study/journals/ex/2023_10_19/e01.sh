# rfr: ((3436638c-9876-45a3-aec9-944de07b65a0)) rg aramalarında çok uzun match olan satırlara sahip dosyaları bulma.

SCRIPT_DIR=~/prj/study/logseq-study/journals/ex/2023_10_19
cd $SCRIPT_DIR
mkdir -p out
SEARCH_DIR=~/projects/myrepo
cd $SEARCH_DIR
OUT01="${SCRIPT_DIR}/out/d01.log"
rg path > $OUT01
OUT02="${SCRIPT_DIR}/out/d02.log"
rg '.{400}' $OUT01 > $OUT02

export MATCH_TEXT="ekip/gnd"
export REPLACE_TEXT="p\\/ekip, g\\/gnd"
rg -l "tags::.*${MATCH_TEXT}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s#${MATCH_TEXT}#${REPLACE_TEXT}#" {}
export MATCH_TEXT="grsm/tst"
export REPLACE_TEXT="grsm, f\\/tst"
rg -l "tags::.*${MATCH_TEXT}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s#${MATCH_TEXT}#${REPLACE_TEXT}#" {}
export MATCH_TEXT="p/engin/gnd"
export REPLACE_TEXT="p\\/engin, g\\/gnd"
rg -l "tags::.*${MATCH_TEXT}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s#${MATCH_TEXT}#${REPLACE_TEXT}#" {}
export MATCH_TEXT="pcm/rpa"
export REPLACE_TEXT="org\\/pcm\\/rpa"
rg -l "tags::.*${MATCH_TEXT}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s#${MATCH_TEXT}#${REPLACE_TEXT}#" {}
export MATCH_TEXT="rdb"
export REPLACE_TEXT="prd\\/rdb"
rg -l "tags::.*${MATCH_TEXT}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s#${MATCH_TEXT}#${REPLACE_TEXT}#" {}

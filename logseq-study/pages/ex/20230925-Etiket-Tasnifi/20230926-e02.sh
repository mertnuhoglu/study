export MATCH_TEXT="ekip/gnd"
export REPLACE_TEXT="p\\/ekip, g\\/gnd"
rg -l "tags::.*${MATCH_TEXT}"
rg "tags::.*${MATCH_TEXT}"
rg -l "tags::.*${MATCH_TEXT}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s/${MATCH_TEXT}/${REPLACE_TEXT}/" {}
rg -l "tags::.*${MATCH_TEXT}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s#${MATCH_TEXT}#${REPLACE_TEXT}#" {}

# 20230926-out02.txt
export MATCH_TEXT="ekip/gnd"
export REPLACE_TEXT="p\\/ekip, g\\/gnd"
rg -l "tags::.*${MATCH_TEXT}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s#${MATCH_TEXT}#${REPLACE_TEXT}#" {}

export MATCH_TEXT="ekip/gnd"
export REPLACE_TEXT="p\\/ekip, g\\/gnd"
rg -l "tags::.*${MATCH_TEXT}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s#${MATCH_TEXT}#${REPLACE_TEXT}#;w /dev/stdout" {}
rg -l "tags::.*${MATCH_TEXT}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s#${MATCH_TEXT}#${REPLACE_TEXT}#w /dev/stdout" {}

# 20230926-out03.txt
export MATCH_TEXT="ekip/gnd"
export REPLACE_TEXT="p\\/ekip, g\\/gnd"
rg -l "tags::.*${MATCH_TEXT}"
rg "tags::.*${MATCH_TEXT}"
rg -l "tags::.*${MATCH_TEXT}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s#${MATCH_TEXT}#${REPLACE_TEXT}#w /dev/stdout" {}

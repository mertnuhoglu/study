MATCH="ekip/gnd"
REPLACE="p\\/ekip, g\\/gnd"
rg -l "tags::.*${MATCH}" | gxargs -n1 -d '\n' -I {} gsed -i -e "1s/${MATCH}/${REPLACE}/" {}

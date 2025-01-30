#!/bin/sh
# [[specs_2_edits.sh]]

# Title: Specs to edits
# Date: 20241207
# [[20241207-dfl-specs-8-edits-p-2-edits.lua]]
#
# Usage:
#

# specs_lua=$(redis-cli HGET script:specs_2_edits specs_lua) 
# edits_pprv_lua=$(redis-cli HGET script:specs_2_edits edits_pprv_lua) 
# specs_tsv_fn=$(redis-cli HGET script:specs_2_edits specs_tsv_fn) 
# edits_pprv_tsv_fn=$(redis-cli HGET script:specs_2_edits edits_pprv_tsv_fn) 
# dfl_join_specs_8_edits_fn=$(redis-cli HGET script:specs_2_edits dfl_join_specs_8_edits_fn) 
# join_tsv_fn=$(redis-cli HGET script:specs_2_edits join_tsv_fn) 
# anti_join_tsv_fn=$(redis-cli HGET script:specs_2_edits anti_join_tsv_fn) 
# edits_pnxt_lua_fn=$(redis-cli HGET script:specs_2_edits edits_pnxt_lua_fn) 
####

script=$(redis-cli HGET script:specs_2_edits script) 
lua "${script}"


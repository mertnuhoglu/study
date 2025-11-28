# source ~/prj/study/script/zsh_libs/mert_utils.sh
#

function get_dt() {
	# usage: "$(get_date)"
	date +%Y%m%d
}

function get_time() {
	date +%H%M%S
}

function get_ts() {
	date +%Y%m%d-+%H%M%S
}


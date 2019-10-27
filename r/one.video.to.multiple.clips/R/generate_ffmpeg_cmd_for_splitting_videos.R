#' @importFrom magrittr "%>%"
NULL

#' @export
generate_ffmpeg_cmd_for_splitting_videos = function(marks, offset_clip_id = 0, original_video = "movie.mp4", clip_name = "movie", dir = "clips") {
	split01 = "ffmpeg -ss {start_time} -to {end_time} -i '{original_video}' -c:v libx264 -crf 23 -c:a aac '{dir}/split01/{filename}'"
	split02 = "ffmpeg -i '{dir}/split01/{filename}' -t 00:00:{duration} -c:v copy -c:a copy '{dir}/split02/{filename}'"
	#slow_splitting = "ffmpeg -i '{original_video}' -ss {start_time} -to {end_time} -c:v libx264 -c:a aac clips/'{filename}'"
	marks %>%
		dplyr::mutate(
			start_time = hms::as_hms(start_time)
			, end_time = hms::as_hms(end_time)
			, duration = end_time - start_time
			, clip_id = dplyr::row_number() + offset_clip_id
			, filename = glue::glue("{clip_name}_{sprintf('%04d', clip_id)}.mp4")
		) %>%
		dplyr::filter(duration > 0.1) %>%
		dplyr::mutate(
			split01 = glue::glue(split01)
			, split02 = glue::glue(split02)
			, video_files_merge = glue::glue("file 'split02/{filename}'\nfile 'silence.mp4'")
		) %>%
		dplyr::mutate(anki_line = glue::glue("[sound:{filename}] <br> {text} ; {text} <br> {text_tr}; {clip_name}")) %>%
		dplyr::select(clip_id, text, dplyr::everything())
}

write_files = function(marks, clip_name, dir) {
	dir.create(path = glue::glue("{dir}/split01"), recursive = T)
	dir.create(path = glue::glue("{dir}/split02"), recursive = T)
	writeLines(marks$split01, glue::glue("{dir}/split01.sh"))
	writeLines(marks$split02, glue::glue("{dir}/split02.sh"))
	readr::write_tsv(marks, glue::glue("{dir}/clips.tsv"))
	writeLines(marks$video_files_merge, glue::glue("{dir}/video_files_merge.in"))
	writeLines(marks$anki_line, glue::glue("{dir}/anki_{clip_name}.txt"))
}

read_marks_tsv = function(path) {
	marks = read.csv(path, sep = "\t", col.names = c("subtitle_id", "start_time", "end_time", "text"), stringsAsFactors = F, header = F) %>%
		dplyr::mutate( start_time = hms::as_hms(start_time)
			, end_time = hms::as_hms(end_time)) %>%
		dplyr::filter(!is.na(start_time)) %>%
		dplyr::filter(!is.na(end_time)) %>%
		dplyr::filter(!is.na(text)) %>%
		dplyr::filter(!text == '') %>%
		dplyr::filter(!stringr::str_detect(text, "^\\(.*\\)$")) 
	return(marks)
}

#' @export
main_generate_ffmpeg_cmd_for_splitting_videos = function(path = "clips_sub/marks.tsv", offset_clip_id = 0, original_video = "movie.mp4", clip_name = "movie") {
	dir = dirname(path)
	filename = basename(path)
	print(glue::glue("{dir}/marks.tr.tsv"))
	mtr = read_marks_tsv(path = glue::glue("{dir}/marks.tr.tsv")) %>%
		dplyr::rename(text_tr = text) %>%
		dplyr::select(-end_time)
	marks = read_marks_tsv(path) %>%
		dplyr::left_join(mtr, by = c("start_time")) 
	m0 = marks %>%
		generate_ffmpeg_cmd_for_splitting_videos(offset_clip_id = offset_clip_id, original_video = original_video, clip_name = clip_name, dir )
	write_files(m0, clip_name, dir)
}

main_write_anki_again = function(clips_tsv = "clips/clips.tsv", clip_name = "movie") {
	marks = readr::read_tsv(clips_tsv)
	dir = dirname(clips_tsv)
	filename = basename(clips_tsv)
	writeLines(marks$anki_line, glue::glue("{dir}/anki_{clip_name}.txt"))
}

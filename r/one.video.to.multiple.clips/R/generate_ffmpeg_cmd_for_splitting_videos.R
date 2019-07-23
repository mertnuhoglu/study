#' @importFrom magrittr "%>%"
NULL

#' @export
generate_ffmpeg_cmd_for_splitting_videos = function(marks, offset_clip_id = 0, original_video = "movie.mp4", clip_name = "movie") {
	split01 = "ffmpeg -ss {start_time} -to {end_time} -i '{original_video}' -c:v libx264 -crf 23 -c:a aac clips/split01/'{filename}'"
	split02 = "ffmpeg -i clips/split01/{filename} -t 00:00:{duration} -c:v copy -c:a copy clips/split02/{filename}"
	#slow_splitting = "ffmpeg -i '{original_video}' -ss {start_time} -to {end_time} -c:v libx264 -c:a aac clips/'{filename}'"
	marks %>%
		dplyr::mutate(
			start_time = hms::as_hms(start_time)
			, end_time = hms::as_hms(end_time)
			, duration = end_time - start_time
			, clip_id = dplyr::row_number() + offset_clip_id
			, filename = glue::glue("{clip_name}_{sprintf('%04d', clip_id)}.mp4")
		) %>%
		dplyr::mutate(
			split01 = glue::glue(split01)
			, split02 = glue::glue(split02)
			, video_files_merge = glue::glue("file 'split02/{filename}'\nfile 'silence.mp4'")
		) %>%
		dplyr::select(clip_id, text, dplyr::everything())
}

write_files = function(marks) {
	dir.create(path = "clips/split01", recursive = T)
	dir.create(path = "clips/split02", recursive = T)
	writeLines(marks$split01, "clips/split01.sh")
	writeLines(marks$split02, "clips/split02.sh")
	readr::write_tsv(marks, "clips/clips.tsv")
	writeLines(marks$video_files_merge, "clips/video_files_merge.in")
}

read_marks_tsv = function(path) {
	specs = readr::cols(
		start_time = readr::col_time(format = "%H:%M:%OS"),
		end_time = readr::col_time(format = "%H:%M:%OS"),
		text = readr::col_character()
	)
	marks = readr::read_tsv(path, col_types = specs) %>%
		dplyr::filter(!is.na(start_time)) %>%
		dplyr::filter(!is.na(end_time))
	return(marks)
}

#' @export
main_generate_ffmpeg_cmd_for_splitting_videos = function(path = "marks.tsv", offset_clip_id = 0, original_video = "movie.mp4", clip_name = "movie") {
	m0 = read_marks_tsv(path) %>%
		generate_ffmpeg_cmd_for_splitting_videos(offset_clip_id = offset_clip_id, original_video = original_video, clip_name = clip_name)
	write_files(m0)
}


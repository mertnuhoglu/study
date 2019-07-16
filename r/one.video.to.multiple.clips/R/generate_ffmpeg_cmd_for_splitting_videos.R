#' @importFrom magrittr "%>%"
NULL

#' @export
generate_ffmpeg_cmd_for_splitting_videos = function(marks, offset_clip_id = 0, original_video = "movie.mp4", clip_name = "movie") {
	marks %>%
		dplyr::mutate(
			start_time = hms::as_hms(start_time)
			, end_time = hms::as_hms(end_time)
			, duration = end_time - start_time
			, clip_id = dplyr::row_number() + offset_clip_id
			, filename = glue::glue("{clip_name}_{sprintf('%04d', clip_id)}.mp4")
		) %>%
		dplyr::mutate(
			cmd = glue::glue("ffmpeg -i {original_video} -ss {start_time} -to {end_time} -c:v libx264 -c:a libfaac clips/{filename}")
			, video_files_for_concat_silence = glue::glue("file '{filename}'\nfile 'silence.mp4'")
			, cmd_concat_silence = glue::glue("ffmpeg -f concat -i clips/video_files{clip_id}.in -c:v libx264 -c:a libfaac clips/with_silence/{filename}")
		) %>%
		dplyr::select(clip_id, text, dplyr::everything())
}

write_files = function(marks) {
	dir.create(path = "clips/with_silence", recursive = T)
	writeLines(marks$cmd, "cmd.sh")
	writeLines(marks$cmd_concat_silence, "cmd_concat_silence.sh")
	readr::write_tsv(marks, "clips.tsv")
	for (i in seq_len(nrow(marks))) {
		row = marks[i, ]
		writeLines(row$video_files_for_concat_silence, glue::glue("clips/video_files{row$clip_id}.in"))
	}
}

read_marks_tsv = function(path) {
	specs = readr::cols(
		start_time = readr::col_time(format = "%H:%M:%OS"),
		end_time = readr::col_time(format = "%H:%M:%OS"),
		text = readr::col_character()
	)
	marks = readr::read_tsv("marks.tsv", col_types = specs)
	return(marks)
}

#' @export
main_generate_ffmpeg_cmd_for_splitting_videos = function(path = "marks.tsv", offset_clip_id = 0, original_video = "movie.mp4", clip_name = "movie") {
	m0 = read_marks_tsv(path) %>%
		generate_ffmpeg_cmd_for_splitting_videos(offset_clip_id = offset_clip_id, original_video = original_video, clip_name = clip_name)
	write_files(m0)
}

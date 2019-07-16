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

main_generate_ffmpeg_cmd_for_splitting_videos = function() {
	specs = readr::cols(
		start_time = readr::col_time(format = "%H:%M:%OS"),
		end_time = readr::col_time(format = "%H:%M:%OS"),
		text = readr::col_character()
	)
	m0 = readr::read_tsv("marks.tsv", col_types = specs) %>%
		generate_ffmpeg_cmd_for_splitting_videos(offset_clip_id = 0, original_video = "movie.mp4", clip_name = "movie")
	writeLines(m0$cmd, "cmd.sh")
	writeLines(m0$cmd_concat_silence, "cmd_concat_silence.sh")
	readr::write_tsv(m0, "clips.tsv")

	for (i in seq_len(nrow(m0))) {
		row = m0[i, ]
		writeLines(row$video_files_for_concat_silence, glue::glue("clips/video_files{row$clip_id}.in"))
	}
}

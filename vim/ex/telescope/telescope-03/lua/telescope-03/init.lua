local function alert(body)
	require "notify"(body, "info", { title = "Buffer API Demo" })
end


local function buffer_list(opts)
	local pickers = require "telescope.pickers"
	local finders = require "telescope.finders"
	local conf = require("telescope.config").values

  local bufnr = vim.api.nvim_get_current_buf()
  local filename = vim.fn.expand(vim.api.nvim_buf_get_name(bufnr))
  local filetype = vim.api.nvim_buf_get_option(bufnr, "filetype")

  local lines = vim.api.nvim_buf_get_lines(0, 0, -1, false)
  local lines_with_numbers = {}

  for lnum, line in ipairs(lines) do
    table.insert(lines_with_numbers, {
      lnum = lnum,
      bufnr = bufnr,
      filename = filename,
      text = line,
    })
  end
	alert(vim.inspect({
		bufnr = bufnr,
		filename = filename,
		filetype = filetype,
	}))
  pickers.new(opts, {
    prompt_title = "Buffer Lines",
    finder = finders.new_table {
      results = lines_with_numbers,
    },
    sorter = conf.generic_sorter(opts),
    previewer = conf.grep_previewer(opts),
  }):find()
end

return {
	buffer_list = buffer_list
}


local function alert(body)
	require "notify"(body, "info", { title = "Buffer API Demo" })
end

local buffer_lines = vim.api.nvim_buf_get_lines(0, 0, 3, 0)
local mark_pos = vim.api.nvim_buf_get_mark(0, "t")
alert(vim.inspect(mark_pos))
-- run:
-- :source %

local function alert(body)
	require "notify"(body, "info", { title = "Buffer API Demo" })
end


local function color_list()
	local buffer_lines = vim.api.nvim_buf_get_lines(0, 0, 3, 0)
	local mark_pos = vim.api.nvim_buf_get_mark(0, "t")
	alert(vim.inspect(mark_pos))
	alert(vim.inspect("ali"))

	local pickers = require "telescope.pickers"
	local finders = require "telescope.finders"
	local conf = require("telescope.config").values
	local actions = require "telescope.actions"
	local action_state = require "telescope.actions.state"

	-- our picker function: colors
	local colors = function(opts)
		opts = opts or {}
		pickers.new(opts, {
			prompt_title = "colors",

			-- @chng
			finder = finders.new_table {
				results = {
					{ "red", "#ff0000" },
					{ "green", "#00ff00" },
					{ "blue", "#0000ff" },
				},
				entry_maker = function(entry)
					print(vim.inspect(entry))
					-- >
					-- {
					-- 	display = "green",
					-- 	index = 2,
					-- 	ordinal = "green",
					-- 	value = { "green", "#00ff00" }
					-- }

					return {
						value = entry,
						display = entry[1],
						ordinal = entry[1],
					}
				end
			},

			sorter = conf.generic_sorter(opts),
			attach_mappings = function(prompt_bufnr, map)
				actions.select_default:replace(function()
					actions.close(prompt_bufnr)
					local selection = action_state.get_selected_entry()
					alert(vim.inspect(selection))
					vim.api.nvim_put({ selection[1] }, "", false, true)
				end)
				return true
			end,
		}):find()
	end

	colors(require("telescope.themes").get_dropdown{})
end

return {
	color_list = color_list
}


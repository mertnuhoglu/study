local function color_list()
	local logger = require("mert/logger").logger
	local pickers = require "telescope.pickers"
	local finders = require "telescope.finders"
	local conf = require("telescope.config").values
	local actions = require "telescope.actions"
	local previewers = require "telescope.previewers"
	local action_state = require "telescope.actions.state"

	JSON = require("JSON")
	local open = io.open

	local function read_file(path)
			local file = open(path, "rb") -- r read mode and b binary mode
			if not file then return nil end
			local content = file:read "*a" -- *a or *all reads the whole file
			file:close()
			return content
	end

	local fileContent = read_file("colors.json");
	local t0 = JSON:decode(fileContent)
	logger:info(vim.inspect(t0[1]))
	local t1 = t0[1]
	logger:info(vim.inspect(t1.color))

	local colors = function(opts)
		opts = opts or {}
    local logger = require("mert/logger").logger
    logger:info("telescope-07.colors")
    logger:info(vim.inspect(opts))
		pickers.new(opts, {
			prompt_title = "custom previewer",

			previewer = previewers.cat.new(opts),

			finder = finders.new_table {
				results = t0,
				entry_maker = function(entry)
					return {
						value = entry,
						display = entry.color,
						ordinal = entry.color,
					}
				end
			},

			sorter = conf.generic_sorter(opts),
			attach_mappings = function(prompt_bufnr, map)
				actions.select_default:replace(function()
					actions.close(prompt_bufnr)
					local selection = action_state.get_selected_entry()
					logger:info("selection:")
					logger:info(vim.inspect(selection.display))
					-- vim.api.nvim_put({ selection.display }, "", false, true)
					-- vim.api.nvim_put({"test"}, "", false, true)
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


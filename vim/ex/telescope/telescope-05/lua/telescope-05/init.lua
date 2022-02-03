local function color_list()
	local logger = require("mert/logger").logger
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
					{ color = "red",   code = "#ff0000" },
					{ color = "green", code = "#00ff00" },
					{ color = "blue",  code = "#0000ff" },
				},
				entry_maker = function(entry)
					print(vim.inspect(entry))
					-- >
					-- {                                                                                                                                                                                                                         
					-- 	display = "red",                                                                                                                                                                                                                              
					-- 	index = 1,                                                                                                                                                                                                                                    
					-- 	ordinal = "red",                                                                                                                                                                                                                              
					-- 	value = {                                                                                                                                                                                                                                     
					-- 		code = "#ff0000",                                                                                                                                                                                                                           
					-- 		color = "red"                                                                                                                                                                                                                               
					-- 	}                                                                                                                                                                                                                                             
					-- }
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
					logger:info(vim.inspect(selection))
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


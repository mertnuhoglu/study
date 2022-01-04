-- [telescope.nvim/developers.md at master · nvim-telescope/telescope.nvim](https://github.com/nvim-telescope/telescope.nvim/blob/master/developers.md)

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
    finder = finders.new_table {
      results = { "red", "green", "blue" }
    },
    sorter = conf.generic_sorter(opts),
    attach_mappings = function(prompt_bufnr, map)
      actions.select_default:replace(function()
        actions.close(prompt_bufnr)
        local selection = action_state.get_selected_entry()
        print(vim.inspect(selection))
				-- >
				-- { "red",
				-- 	index = 1,
				-- 	<metatable> = {
				-- 		__index = <function 1>
				-- 	}
				-- }
        vim.api.nvim_put({ selection[1] }, "", false, true)
      end)
      return true
    end,
  }):find()
end

colors(require("telescope.themes").get_dropdown{})


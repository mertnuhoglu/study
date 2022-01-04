print("mert")

local function printWindowSize()
	-- print("hello")
    print(vim.api.nvim_win_get_width(0),
          vim.api.nvim_win_get_height(0))
end

return {
	printWindowSize = printWindowSize
}

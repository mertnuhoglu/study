local function alert(body)
	require "notify"(body, "info", { title = "Buffer API Demo" })
end

alert "Code Smell"
-- run:
-- :source %

#!/usr/local/bin/lua
-- taken from: https://www.lua.org/pil/20.2.html
local s = "Deadline is 30/05/1999, firm"
local date = "%d%d/%d%d/%d%d%d%d"
print(string.sub(s, string.find(s, date)))   --> 30/05/1999

-- character classes:
-- .	all characters
-- %a	letters
-- %c	control characters
-- %d	digits
-- %l	lower case letters
-- %p	punctuation characters
-- %s	space characters
-- %u	upper case letters
-- %w	alphanumeric characters
-- %x	hexadecimal digits
-- %z	the character with representation 0

-- complement character classes with uppercase:
print(string.gsub("hello, up-down!", "%A", "."))  --> hello..up.down.

-- count the number of vowels in a text, use char-sets
local _, nvow = string.gsub("hello a b", "[AEIOUaeiou]", "")  --> 3

-- modifiers:
-- +	1 or more repetitions
-- *	0 or more repetitions
-- -	also 0 or more repetitions (non-greedy)
-- ?	optional (0 or 1 occurrence)

print(string.gsub("one, and two; and three", "%a+", "word"))
--> word, word word; word word

local i, j = string.find("the number 1298 is even", "%d+")
print(i,j)   --> 12  15

local k = string.find("telescope.nvim", "telescope%.nvim")
print(k)   --> 1

k = string.find("telescope.nvim", "telescope.nvim")
print(k)   --> 1


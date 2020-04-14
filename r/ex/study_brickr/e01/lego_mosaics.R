# Taken from `https://twitter.com/ryantimpe/status/1248223887306997760`

library(brickr)
library(magrittr)

pic = png::readPNG("panda.png")
pic = png::readPNG("tiger.png")
pic = png::readPNG("ozgur_yagmur.png")
pic = png::readPNG("ozgur01.png")
pic = png::readPNG("yagmur.png")

list(c(2,3,1), c(3,2,1),
		 c(1,3,2), c(3,1,2)) %>%
	purrr::map(
		~ pic %>%
			image_to_mosaic(
				32, # Make it smaller
				brightness = 1.2, # and brighter
				warhol = .x # pass it new rgb values
			) %>%
			build_mosaic()
	) %>%
	patchwork::wrap_plots()


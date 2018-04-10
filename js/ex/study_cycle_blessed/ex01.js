// Require the Blessed API. 
var Blessed = require('blessed'); 

// Initialize the screen widget.
var screen = Blessed.screen({
	// Example of optional settings:
	smartCSR: true,
	useBCE: true,
	cursor: {
		artificial: true,
		blink: true,
		shape: 'underline'
	},
	log: `${__dirname}/application.log`,
	debug: true,
	dockBorders: true
});

// Specify the title of the application.
screen.title = 'La pizza de Don Cangrejo.';

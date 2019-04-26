var express = require('express');
var bodyParser = require('body-parser');

var app = express();

app.use(bodyParser.json({limit: '100mb'}));

app.use('/guide', function(req, res){
		var payload = req.body; // payload varibale has your state object
})

const port = 3000
app.listen(port, () => console.log(`Example app listening on port ${port}!`))

const cors = require('cors')
var express = require('express')
var multer  = require('multer')

const DB_NAME = 'db.json';
var upload = multer({ dest: 'uploads/' })

var app = express()
app.use(cors())

app.post('/profile', upload.single('avatar'), function (req, res, next) {
  // req.file is the `avatar` file
  // req.body will hold the text fields, if there were any
	console.log("file uploaded")
})

app.get('/',function(req,res){
	res.sendFile("e04/index.html");
});
app.listen(3000,function(){
  console.log("Started on PORT 3000");
})


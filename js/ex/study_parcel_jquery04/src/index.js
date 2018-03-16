import {addText} from './app.js'
$('#root').append(`
  Date: <input type="text" id="datepicker">
`)
$("#datepicker").datepicker();
addText("Look up!")

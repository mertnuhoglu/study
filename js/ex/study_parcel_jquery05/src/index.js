const jquery = require("jquery")
window.$ = window.jQuery = jquery;
require("jquery-ui-dist/jquery-ui.css")
require("jquery-ui-dist/jquery-ui.js")
import {addText} from './app.js'

$('#root').append(`
  Date: <input type="text" id="datepicker">
`)
$("#datepicker").datepicker();
addText("Look up jquery!")

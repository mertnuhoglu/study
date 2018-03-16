const jquery = require("jquery")
window.$ = window.jQuery = jquery;
require("jquery-ui-dist/jquery-ui.css")
require("jquery-ui-dist/jquery-ui.js")
$(document).ready(function () {
  $('#root').html('')
  $('#root').append(`
    Date: <input type="text" id="datepicker">
  `)
  $(function() {
    $("#datepicker").datepicker();
  });
})

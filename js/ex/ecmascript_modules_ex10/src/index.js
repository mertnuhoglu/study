import './style.css'; 
import printMe from './print.js';
import {$, jQuery} from 'jquery';

window.$ = $;
window.jQuery = jQuery;
import "./vendor/chosen.jquery.min";
//require('imports-loader?window.jQuery=jquery!./node_modules/path-to-slider-script.js');

//require('imports-loader?window.jQuery=jquery')
require('imports-loader?$=jquery!./example.js')

function component() {
  var element = document.createElement('div');
  element.innerHTML = 'merhaba';
  return element;
}
document.body.appendChild(component());

if (module.hot) {
  module.hot.accept('./print.js', function() {
    console.log('Accepting the updated printMe module!');
    printMe();
  })
}

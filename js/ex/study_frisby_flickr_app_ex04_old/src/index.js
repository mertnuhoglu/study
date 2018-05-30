import './style.css'; 
import printMe from './print.js';
import R from 'ramda';
import {$, jQuery} from 'jquery';

window.$ = $;
window.jQuery = jQuery;

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

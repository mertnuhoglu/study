const jquery = require("jquery")
window.$ = window.jQuery = jquery;
const R = require('ramda');

const Impure = {
  getJSON: R.curry((callback, url) => $.getJSON(url, callback)),
  setHtml: R.curry((sel, html) => $(sel).html(html)),
  trace: R.curry((tag, x) => { console.log(tag, JSON.stringify(x, null, 2)); return x; }),
}; 
const host = 'api.flickr.com';
const path = '/services/feeds/photos_public.gne';
const query = t => `?tags=${t}&format=json&jsoncallback=?`;
const url = t => `https://${host}${path}${query(t)}`;
const app = R.compose(Impure.getJSON(Impure.trace('response')), url);
app('cats');

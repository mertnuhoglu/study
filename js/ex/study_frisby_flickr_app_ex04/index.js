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
const mediaUrl = R.compose(R.prop('m'), R.prop('media'));
const mediaUrls = R.compose(R.map(mediaUrl), R.prop('items'));
const render = R.compose(Impure.setHtml('js-main'), mediaUrls);
const app = R.compose(Impure.getJSON(render), url);
app('cats');

var request = require('request-promise-native');
var {compose, curry, map, prop} = require('ramda');
//var {map} = require('ramda-fantasy');
var R = require('ramda');

var Impure = {
  getJSON: curry((callback, url) => 
    request(url).then(callback).
    catch( err => console.log('error:', err)) 
  ),
  trace: curry((tag, x) => { console.log(x); return x; }),
}; 
var host = 'api.flickr.com';
var path = '/services/feeds/photos_public.gne';
var query = t => `?tags=${t}&format=json&jsoncallback=?`;
var url = t => `https://${host}${path}${query(t)}`;
var mediaUrl = compose(prop('m'), prop('media'));
var mediaUrls = compose(map(mediaUrl), prop('items'));
//var mediaUrls = compose(prop('items'));
var render = compose(console.log, mediaUrls);
var app = compose(Impure.getJSON(render), url);
//var app = compose(Impure.getJSON(Impure.trace('response')), url);
app('cats');


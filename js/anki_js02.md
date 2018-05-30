
## js: import ramda

··  `` let {{c1::{compose, curry}}} = {{c2::require('ramda')}}; `` <br>
··  `` compose(..) `` <br>
··  `` curry(..) `` <br>


%

%

clozeq
active

---

## js: request 01 promise

··  `` var request = require('{{c1::request-promise-native}}'); `` <br>
··  `` request('http://jsonplaceholder.typicode.com/users/1') `` <br>
····  `` .{{c2::then}}( html => console.log('body:', html) ) `` <br>
····  `` .catch( err => console.log('error:', err) ); `` <br>


%

%

clozeq
suspended

---

## js: request 02 json response

··  `` request('http://jsonplaceholder.typicode.com/users/1') `` <br>
····  `` .then( json => console.log({{c1::JSON.parse}}(json).company)) `` <br>

%

%

clozeq
suspended

---

## js: node repl error

error: invalid repl keyword

··  `` request('...') `` <br>
····  `` .then( html => console.log('body:', html) ) `` <br>

cause:

··  `` node's repl has commands that begin with `.` such as `.clear` `` <br>

solution:

··  `` request('...'){{c1::.}} `` <br>
····  `` then( html => console.log('body:', html) ) `` <br>

%

%

clozeq
suspended

---

## js: ramda 01 require

··  `` const {{c1::R}} = require('ramda'); `` <br>
··  `` R.map(R.prop('id')); `` <br>
··  `` R.map(([k, v]) => {{c2::global}}[k] = v, R.toPairs(R)); `` <br>
··  `` map(prop('id')); `` <br>

%

%

clozeq
active

---

## js: ramda 02 map prop

··  `` const data = [ `` <br>
····  `` {'id': 1, 'title': "a"}, `` <br>
····  `` {'id': 2, 'title': "b"}, `` <br>
··  `` ]; `` <br>
··  `` const getId = {{c1::R.map}}({{c2::R.prop}}('id')); `` <br>
··  `` console.log(getId(data)); `` <br>

%

%

clozeq
active

---

## js: ramda 03 map nested prop

··  `` const data = [ `` <br>
····  `` {'sub': {'id': 1, 'title': "a"}}, `` <br>
····  `` {'sub': {'id': 2, 'title': "b"}}, `` <br>
··  `` ]; `` <br>
··  `` const getId = R.map(R.compose({{c1::R.prop('id'), R.prop('sub')}})); `` <br>
··  `` // or `` <br>
··  `` const getId = R.map(e => {{c2::e.sub.id}}); `` <br>
··  `` getId(data) `` <br>


%

%

clozeq
active

---

## js: ramda 04 convert into 2-tuples

··  `` { 'group1-perm1': true, ... } `` <br>
··  `` --->>> `` <br>
··  `` [ [ 'group1-perm1', true ], ... ] `` <br>

··  `` const fn = compose({{c1::toPairs}}); `` <br>
··  `` console.log(fn(data)); `` <br>


%

%

clozeq
active

---

## js: ramda 05 append

··  `` [ [ 'group1-perm1', true ], ... ] `` <br>
··  `` --->>> `` <br>
··  `` [ { value: 'group1-perm1', checked: true, label: 'group1-perm1' }, ... `` <br>

··  `` const addLabel = ({{c1::[value, checked]}}) => ({value, checked, label: value}); `` <br>
··  `` const fn = compose({{c2::map(addLabel)}}, toPairs); `` <br>
··  `` console.log(fn(data)); `` <br>

%

%

clozeq
active

---

## js: ramda 06 regex

··  `` [ { value: 'group1-perm1', checked: true, label: 'group1-perm1' }, ... `` <br>
··  `` --->>> `` <br>
··  `` [ { value: 'group1-perm1', checked: true, label: 'perm1' }, ... `` <br>

··  `` const getLabel = R.compose(R.{{c1::head}}, R.{{c2::match}}(/perm[0-9]/g)); `` <br>
··  `` const addLabel = ([value, checked]) => ({value, checked, label: getLabel(value)}); `` <br>

%

%

clozeq
active

---

## js: ramda 07 Group items using `groupBy`

··  `` [ { value: 'group1-perm1', checked: true, label: 'perm1' }, ... `` <br>
··  `` --->>> `` <br>
··  `` { 'group1-perm1': [ { value: 'group1-perm1', checked: true, label: 'perm1' } ], ... `` <br>

··  `` const groupName = R.{{c1::prop('value')}}; `` <br>
··  `` const fn = compose(R.{{c2::groupBy}}(groupName), map(addLabel), toPairs); `` <br>

%

%

clozeq
active

---

## js: ramda 08

··  `` { 'group1-perm1': [ { value: 'group1-perm1', checked: true, label: 'perm1' } ], ... `` <br>
··  `` --->>> `` <br>
··  `` { group1: `` <br>
····   `` [ { value: 'group1-perm1', checked: true, label: 'perm1' }, `` <br>
······   `` { value: 'group1-perm2', checked: false, label: 'perm2' } ], `` <br>

··  `` const getGroup = R.compose(R.{{c1::defaultTo}}('general'), R.head, R.match(/group[0-9]/g)); `` <br>
··  `` const groupName = R.compose({{c2::getGroup}}, R.prop('value')); `` <br>
··  `` const fn = compose(R.groupBy(groupName), map(addLabel), toPairs); `` <br>

%

%

clozeq
active

---

## js: ramda 09 pipe

··  `` const fn = compose(R.groupBy(groupName), map(addLabel), toPairs); `` <br>
··  `` --->>>> `` <br>

··  `` const fn = {{c1::pipe}}( `` <br>
····  `` {{c2::toPairs}}, `` <br>
····  `` map(addLabel),  `` <br>
····  `` R.groupBy(groupName),  `` <br>
··  `` ); `` <br>

%

%

clozeq
active

---

## js wes bos: string templating

····  `` const markup = ` `` <br>
······  `` <div> `` <br>
········  `` <h2>${beer.name}</h2> `` <br>
········  `` ${{{c1::renderKeywords}}(beer.keywords)} `` <br>
······  `` </div> `` <br>
····  `` ` `` <br>
····  `` funcion renderKeywords(kw) { `` <br>
······  `` return ( `` <br>
········  `` `<ul> `` <br>
··········  `` ${kw.{{c2::map}}(key => `li>${key}</li>`).{{c3::join}}('')} `` <br>
········  `` </ul>`); `` <br>
····  `` } `` <br>


%

%

clozeq
active

---

## js: hyperaxe 01

··  `` a({ href: '#' }, 'click') `` <br>

%

··  `` <a href="#">click</a> `` <br>

%

back hyperscript

---

## js: hyperaxe 02

··  `` img({ src: 'cats.gif', alt: 'lolcats' }) `` <br>

%

··  `` <img src="cats.gif" alt="lolcats"> `` <br>

%

back hyperscript

---

## js: hyperaxe 03

··  `` video({ src: 'dogs.mp4', autoplay: true }) `` <br>

%

··  `` <video src="dogs.mp4" autoplay="true"></video> `` <br>

%

back hyperscript

---

## js: hyperaxe 04

··  `` var x = require('hyperaxe') `` <br>
··  `` var custom = x('custom') `` <br>
··  `` custom('over 9000') `` <br>

%

··  `` <custom>over 9000</custom> `` <br>

%

back hyperscript

---

## js: hyperaxe 05 css shorthand

··  `` var default = x('.class1.class2') `` <br>
··  `` default('content') `` <br>

%

··  `` <div class="class1 class2">content</div> `` <br>

%

clozeq
suspended

---

## js: rxjs 01 collect ids from nested subitems

··  `` listsOfItems :: [{name, items:[{id}]}] `` <br>

··  `` listsOfItems.map( {{c1::list =>}} `` <br>
······  `` list.items.map((e) => {{c1::(e.id)}}) `` <br>
····  `` ).concatAll(); `` <br>
··  `` // [ 1, 2, 5, 7 ] `` <br>

%

%

clozeq
active

---

## js: learnrx 01 forEach

··  `` var names = ["Ali", "Veli"]; `` <br>
··  `` names.{{c1::forEach}}(function(name) { `` <br>
····  `` console.log(name); `` <br>
··  `` }); `` <br>

%

%

clozeq
suspended

---

## js: learnrx 02 forEach projecting

··  `` var items = [ { "id": 1, "title": "a", ...}, `` <br>
··  `` // --->>> `` <br>
··  `` [ { id: 1, title: 'a' }, { id: 2, title: 'b' } ] `` <br>

··  `` result = []; `` <br>
··  `` items.forEach( e =>  `` <br>
····  `` result.{{c2::push}}({ {{c1::id: e.id, title: e.title}} }) `` <br>
··  `` ); `` <br>

%

%

clozeq
suspended

---

## js: learnrx 03 implementing map()

··  `` Array.{{c1::prototype}}.map = function(projection) { `` <br>
····  `` var results = []; `` <br>
····  `` this.forEach(function(item) { `` <br>
······  `` results.{{c2::push}}({{c1::projection}}(item)); `` <br>
····  `` }); `` <br>
····  `` return results; `` <br>
··  `` }; `` <br>


%

%

clozeq
active

---

## js: learnrx 04 forEach filtering

··  `` result = [] `` <br>
··  `` items.forEach( e => { `` <br>
····  `` if (e.rating === 5.0) { `` <br>
······  `` result.{{c1::push}}(e); `` <br>
····  `` } `` <br>
··  `` }); `` <br>


%

%

clozeq
suspended

---

## js: learnrx 05 implement filter()

··  `` Array.prototype.filter = function(predicate) { `` <br>
····  `` var results = []; `` <br>
····  `` this.forEach(function(item) { `` <br>
······  `` if ({{c1::predicate}}(item)) { `` <br>
········  `` results.{{c1::push}}(item); `` <br>
······  `` } `` <br>
····  `` }); `` <br>
····  `` return results; `` <br>
··  `` }; `` <br>
··  `` var result = items.filter( e => e.rating === 5.0); `` <br>


%

%

clozeq
suspended

---

## js: learnrx 06 query trees and flatten

··  `` var listsOfItems = [ { name: "list01", items: [ { "id": 1, ... `` <br>

··  `` listsOfItems.{{c1::forEach}}( list => { `` <br>
····  `` list.items.{{c1::forEach}}( e =>  `` <br>
······  `` result.push(e.id)  `` <br>
····  `` ); `` <br>
··  `` }); `` <br>
··  `` console.log(result); `` <br>
··  `` // [ 1, 2, 5, 7 ] `` <br>

%

%

clozeq
suspended

---

## js: learnrx 07 implement `concatAll`()

··  `` Array.prototype.concatAll = function() { `` <br>
····  `` var results = []; `` <br>
····  `` this.forEach(function(subArray) { `` <br>
······  `` results.push.{{c2::apply}}({{c1::results}}, subArray); `` <br>
····  `` }); `` <br>

····  `` return results; `` <br>
··  `` }; `` <br>

%

%

clozeq
active

---

## js: `Function.prototype.apply()`

`apply()` calls a function with a given `this` and `arguments` provided as an array.

··  `` var numbers = [5, 6, 2, 3, 7]; `` <br>
··  `` var max = Math.max.apply({{c1::null, numbers}}); `` <br>
··  `` console.log(max); `` <br>
··  `` // 7 `` <br>
··  `` var min = Math.min.apply(null, numbers); `` <br>
··  `` console.log(min); `` <br>
··  `` // 2 `` <br>

%

%

clozeq
active

---

## js: learnrx 08 `map` `filter` `concatAll`

··  `` listsOfItems :: [ { name, items: [ { id, title, subitems: [ { id, time } ] } ] } ] `` <br>
··  `` --->>> `` <br>
··  `` result :: [ { id, time } ] `` <br>

··  `` var result = listsOfItems.{{c1::map}}( (list) => `` <br>
····  `` list.items.map( (e) => `` <br>
······  `` e.subitems.{{c2::filter}}( (s) => `` <br>
········  `` s.time > 20 `` <br>
······  `` ).{{c3::map}}( (s) => `` <br>
········  `` ({id: e.id, s: s.time}) `` <br>
······  `` ) `` <br>
····  `` ).{{c4::concatAll}}() `` <br>
··  `` ).concatAll(); `` <br>
··  `` console.log(result); `` <br>
··  `` // [ { id: 1, s: 70 }, { id: 2, s: 30 }, ... `` <br>

%

%

clozeq
suspended

---

## js: learnrx 09 implement `concatMap`

··  `` Array.prototype.concatMap = function(projection) { `` <br>
····  `` return this. `` <br>
······  `` {{c1::map}}(function(item) { `` <br>
········  `` return {{c2::projection}}(item); `` <br>
······  `` }). `` <br>
······  `` // apply the concatAll function to flatten the two-dimensional array `` <br>
······  `` {{c3::concatAll}}(); `` <br>
··  `` }; `` <br>

%

%

clozeq
active

---

## js: learnrx 10 concatMap and filter

··  `` listsOfItems :: [ { name, items: [ { id, title, subitems: [ { id, time } ] } ] } ] `` <br>
··  `` --->>> `` <br>
··  `` result :: [ { id, time } ] `` <br>

··  `` var result = listsOfItems.{{c1::concatMap}}( (list) => `` <br>
····  `` list.{{c2::items}}.concatMap( (e) => `` <br>
······  `` e.subitems.{{c3::filter}}( (s) => `` <br>
········  `` s.time > 20 `` <br>
······  `` ).{{c4::map}}( (s) => `` <br>
········  `` ({id: e.id, s: s.time}) `` <br>
······  `` ) `` <br>
····  `` ) `` <br>
··  `` ) `` <br>
··  `` console.log(result); `` <br>
··  `` // [ { id: 1, s: 70 }, { id: 2, s: 30 }, { id: 5, s: 50 }, { id: 5, s: 40 } ] `` <br>

%

%

clozeq
suspended

---

## js: learnrx 11 forEach to find max rating item

··  `` items :: [ { id, title, rating } ] `` <br>
··  `` --->>> `` <br>
··  `` maxItem :: { id, title, rating } `` <br>

··  `` var maxRating = -1 `` <br>
··  `` var maxItem = null `` <br>
··  `` items.{{c1::forEach}}(function(e) { `` <br>
····  `` if (e.rating > maxRating) { `` <br>
······  `` maxItem = e; `` <br>
······  `` maxRating = e.rating; `` <br>
····  `` } `` <br>
··  `` }); `` <br>
··  `` console.log(maxItem); `` <br>
··  `` // { id: 2, title: 'b', rating: 5 } `` <br>

%

%

clozeq
suspended

---

## js: learnrx 12 implement `reduce`

··  `` Array.prototype.reduce = function(combiner, initial) { `` <br>
····  `` ... `` <br>
······  `` while(counter < this.length) { `` <br>
········  `` {{c1::acc}} = {{c2::combiner}}(acc, this[{{c3::counter}}]) `` <br>
········  `` counter++; `` <br>
······  `` } `` <br>
······  `` return [acc]; `` <br>
····  `` } `` <br>
··  `` }; `` <br>
··  `` var r = [1,2,3].reduce( (acc, x) => acc + x ) `` <br>
··  `` console.log(r) `` <br>
··  `` var r2 = [1,2,3].reduce( (acc, x) => acc + x, 10 ) `` <br>
··  `` console.log(r2) `` <br>

%

%

clozeq
active

---

## js: learnrx 13 Find max number with `reduce`

··  `` var r = [2,3,1,4,5].reduce( (acc, curr) => { `` <br>
····  `` if({{c1::acc > curr}}) { `` <br>
······  `` return acc; `` <br>
····  `` } `` <br>
····  `` else { `` <br>
······  `` return curr; `` <br>
····  `` } `` <br>
··  `` }); `` <br>
··  `` console.log(r) `` <br>
··  `` // [ 5 ] `` <br>

%

%

clozeq
active

---

## js: learnrx 14 Find max rating item's title

··  `` items :: [ { id, title, rating } ] `` <br>
··  `` --->>> `` <br>
··  `` result :: [ title ] `` <br>

··  `` var r = items.{{c1::reduce}}( (acc,curr) => { `` <br>
····  `` if (acc.rating > curr.rating) { `` <br>
······  `` return acc; `` <br>
····  `` } `` <br>
····  `` else { `` <br>
······  `` return curr; `` <br>
····  `` } `` <br>
··  `` }).{{c2::map}}(e => {{c3::e.title}}) `` <br>
··  `` console.log(r) `` <br>
··  `` // [ 'b' ] `` <br>

%

%

clozeq
active

---

## js: learnrx 15 Converting an Array to a Map Using Reduce

··  `` videos :: [ { id, title } ] `` <br>
··  `` --->>> `` <br>
··  `` result :: [ { id: title } ] `` <br>

··  `` var r = videos.reduce(  `` <br>
····  `` (acc, x) => { `` <br>
······  `` var obj = {}; `` <br>
······  `` {{c1::obj[x.id]}} = x.title; `` <br>
······  `` return {{c2::Object.assign}}(acc, obj); `` <br>
····  `` }, {}); `` <br>
··  `` console.log(r) `` <br>
··  `` // [ { '675465': 'Fracture', `` <br>

%

%

clozeq
active

---

## js: learnrx 16 Get id, title, and shortest subitem url for every item

··  `` listsOfItems :: [ { name, items: [ { id, title, subitems: [ { time, url } ] } ] } ] `` <br>
··  `` --->>> `` <br>
··  `` result :: [ { id, title, url } ] `` <br>

··  `` var r = listsOfItems.concatMap((list) => `` <br>
····  `` list.items.{{c1::concatMap}}((e) => `` <br>
······  `` e.subitems.{{c2::reduce}}((acc,curr) => { `` <br>
········  `` if (acc.time < curr.time) { `` <br>
··········  `` return acc; `` <br>
········  `` } `` <br>
········  `` else { `` <br>
··········  `` return curr; `` <br>
········  `` } `` <br>
······  `` }).{{c3::map}}((s) => `` <br>
········  `` ({id: e.id, title: e.title, url: {{c4::s.url}}}) `` <br>
······  `` ) `` <br>
····  `` ) `` <br>
··  `` ); `` <br>
··  `` console.log(r) `` <br>
··  `` // [ { id: 1, title: 'a', url: 'url01' }, `` <br>
··  `` //   { id: 2, title: 'b', url: 'url04' }, `` <br>


%

%

clozeq
active

---

## js: Simple Example for `concatMap` 01

··  `` var xs = [1,2,3] `` <br>
··  `` var r01 = xs.map(x => x * 2) `` <br>
··  `` console.log(r01) `` <br>
··  `` // [ 2, 4, 6 ] `` <br>
··  `` var r02 = xs.map(x => {{c1::[x * 2] }} ).{{c2::concatAll}}() `` <br>
··  `` console.log(r02) `` <br>
··  `` // [ 2, 4, 6 ] `` <br>

%

%

clozeq
active

---

## js: Simple Example for `concatMap` 01b

mnemonics: 

··  `` {{c1::concatMap}}  `` <br>
····  `` = {{c2::flatMap}}  `` <br>
····  `` = {{c3::chain}}  `` <br>
····  `` = {{c4::map then join}}  `` <br>
····  `` = {{c5::map then concat}} `` <br>
··  `` map: extra boxing `` <br>
··  `` concat: {{c6::unbox once}} `` <br>

%

%

clozeq
active

---

## js: Simple Example for `concatMap` 01c

`chain` or `concatMap` are described in:

Frisby's Mostly Adequate Guide to Functional Programming - Brian Lonsdorf

Chapter 9: {{c1::Monadic}} Onions

%

%

clozeq
active

---

## js: Simple Example for `concatMap` 02

··  `` var xs = [ `` <br>
····  `` {x_id: 11, ys: [{y_id: 51}, {y_id: 52}]}, `` <br>
····  `` {x_id: 12, ys: [{y_id: 61}, {y_id: 62}]}, `` <br>
··  `` ] `` <br>
··  `` var r01 = xs.map(x => {{c1::x.ys.map}}(y => y.y_id)) `` <br>
··  `` console.log(r01) `` <br>
··  `` // [ [ 51, 52 ], [ 61, 62 ] ] `` <br>
··  `` var r02 = xs.map(x => x.ys.map(y => y.y_id)).{{c2::concatAll}}() `` <br>
··  `` console.log(r02) `` <br>
··  `` // [ 51, 52, 61, 62 ] `` <br>
··  `` var r03 = xs.{{c3::concatMap}}(x => x.ys.map(y => y.y_id)) `` <br>
··  `` console.log(r03) `` <br>
··  `` // [ 51, 52, 61, 62 ] `` <br>

%

%

clozeq
active

---

## js: Simple Example for `concatMap` 02b

Why do we use `concatMap` especially when mapping over subarray items?

Because map over subarray returns a {{c1::nested array}}.

%

%

clozeq
active

---

## js: learnrx 17 Zipping Arrays with forEach: Combine two arrays by index

··  `` videos :: [ { id, title } ] `` <br>
··  `` bookmarks :: [ { id, time } ] `` <br>
··  `` --->>> `` <br>
··  `` pairs :: [ { video_id, bookmark_id } ] `` <br>

··  `` for(i = 0; i < Math.min(videos.length, bookmarks.length); i++) { `` <br>
····  `` pairs.{{c1::push}}({video_id: videos[i].id, bookmark_id: bookmarks[i].id}); `` <br>
··  `` } `` <br>
··  `` console.log(pairs) `` <br>
··  `` // [ { video_id: 1, bookmark_id: 101 }, `` <br>
··  `` //   { video_id: 2, bookmark_id: 102 }, `` <br>

%

%

clozeq
suspended

---

## js: learnrx 18 Implement `zip`

··  `` Array.{{c1::zip}} = function(xs, ys, {{c2::combiner}}) { `` <br>
····  `` var i, `` <br>
······  `` results = []; `` <br>
····  `` for(i = 0; i < Math.min(xs.length, ys.length); i++) { `` <br>
······  `` results.push({{c3::combiner}}(xs[i],ys[i])); `` <br>
····  `` } `` <br>
····  `` return results; `` <br>
··  `` }; `` <br>

%

%

clozeq
active

---

## js: learnrx 19 Zipping Arrays with `zip`

··  `` videos :: [ { id, title } ] `` <br>
··  `` bookmarks :: [ { id, time } ] `` <br>
··  `` --->>> `` <br>
··  `` pairs :: [ { video_id, bookmark_id } ] `` <br>

··  `` var r = Array.zip( `` <br>
····  `` videos, `` <br>
····  `` bookmarks, `` <br>
····  `` {{c1::(video, bookmark)}} => `` <br>
······  `` ({ {{c1::videoId: video.id}}, bookmarkId: bookmark.id}) `` <br>
····  `` ) `` <br>
··  `` console.log(r) `` <br>
··  `` // [ { videoId: 1, bookmarkId: 101 }, `` <br>
··  `` //   { videoId: 2, bookmarkId: 102 } ] `` <br>

%

%

clozeq
active

---

## js: learnrx 20 Get Each Item's id, title, `sub_b.m,` shortest `sub_a` url

··  `` listsOfItems :: [ { name, items: [ { id, title, sub_a: [ { time, url } ], sub_b: [ { type, time } ] } ] } ] `` <br>
··  `` --->>> `` <br>
··  `` result :: [ { id, time, url } ] `` <br>

··  `` var r = listsOfItems.concatMap((list) => `` <br>
····  `` list.items.concatMap((e) => `` <br>
······  `` Array.{{c1::zip}}( `` <br>
········  `` e.{{c2::sub_a}}.reduce((acc,curr) => { `` <br>
··········  `` if (acc.time < curr.time) { `` <br>
············  `` return acc; `` <br>
··········  `` } `` <br>
··········  `` else { `` <br>
············  `` return curr; `` <br>
··········  `` } `` <br>
········  `` }), `` <br>
········  `` e.sub_b.filter((sub_b) => sub_b.type === "m"), `` <br>
········  `` {{c3::(sub_a, sub_b)}} => ({id: e.id, title: e.title, time: sub_b.time, url: sub_a.url}) `` <br>
······  `` ) `` <br>
····  `` ) `` <br>
··  `` ); `` <br>
··  `` console.log(r) `` <br>
··  `` // [ { id: 1, title: 'a', time: 20, url: 'url01' }, `` <br>

%

%

clozeq
active

---

## js: learnrx 21 Converting from Arrays to Trees

··  `` lists :: [ { id, name } ] `` <br>
··  `` videos :: [ { list_id, id, title } ] `` <br>
··  `` --->>> `` <br>
··  `` result :: [ { name, videos: [ { id, title } ] } ] `` <br>

··  `` var r = lists.{{c1::map}}((list) => { `` <br>
····  `` return { `` <br>
······  `` name: list.name, `` <br>
······  `` videos: {{c2::videos}}. `` <br>
········  `` {{c3::filter}}((video) => video.list_id === list.id). `` <br>
········  `` {{c4::map}}((video) => ({id: video.id, title: video.title})) `` <br>
····  `` }; `` <br>
··  `` }); `` <br>
··  `` console.log(JSON.stringify(r)) `` <br>
··  `` // [ `` <br>
··  `` //   { `` <br>
··  `` //··   "name": "group_01", `` <br>
··  `` //··   "videos": [ `` <br>
··  `` //····   { `` <br>
··  `` //······   "id": 1, `` <br>
··  `` //······   "title": "a" `` <br>
··  `` //····   }, `` <br>

%

%

clozeq
active

---

## js: learnrx 22 Converting from Arrays to Deeper Trees

··  `` lists :: [ { id, name } ] `` <br>
··  `` videos :: [ { list_id, id, title } ] `` <br>
··  `` boxarts :: [ { video_id, size, url } ] `` <br>
··  `` bookmarks :: [ { video_id, time } ] `` <br>
··  `` --->>> `` <br>
··  `` result :: [ { name, videos: [ { id, title, time, boxart } ] } ] `` <br>

··  `` var r = lists.map((list) => { `` <br>
····  `` {{c1::return}} { name: list.name, `` <br>
······  `` videos: videos. `` <br>
········  `` filter((video) => video.list_id === list.id ). `` <br>
········  `` {{c2::concatMap}}((video) => ( `` <br>
··········  `` {{c3::Array.zip}}( `` <br>
············  `` bookmarks.filter((bookmark) => bookmark.video_id === video.id), `` <br>
············  `` boxarts.filter((boxart) => boxart.video_id === video.id). `` <br>
··············  `` {{c4::reduce}}( (acc,curr) => ( acc.size < curr.size ? acc : curr )), `` <br>
············  `` {{c5::(bookmark, boxart)}} => ({ id: video.id, title: video.title, time: bookmark.time, boxart: boxart.url }) `` <br>
··········  `` ) `` <br>
········  `` )) `` <br>
····  `` }; `` <br>
··  `` }); `` <br>

%

%

clozeq
active

---

## js: json-server 01

mimic rest api servers

··  `` json-server --watch ex/{{c1::db.json}} `` <br>

db.json

··  `` { "posts": [ `` <br>
······  `` { "id": 1, "title": "json-server", "author": "typicode" } `` <br>
····  `` ], `` <br>

··  `` curl http://localhost:3000/{{c2::posts/1}} `` <br>
··  `` ## { `` <br>
··  `` ##   "id": 1, `` <br>
··  `` ##   "title": "json-server", `` <br>
··  `` ##   "author": "typicode" `` <br>
··  `` ## } `` <br>


%

%

clozeq
active

---

## js: webpack error: devServer.proxy is not a function

··  `` webpack.config.js `` <br>
····  `` //devServer.proxy({ `` <br>
······  `` //'/api': { target: 'http://localhost:3000' } `` <br>
····  `` //}), `` <br>
··  `` npm run build `` <br>
··  `` open {{c1::build}}/index.html `` <br>

%

%

clozeq
suspended

---

## js: introspection Reflect API 01

Reflect is a collection of {{c1::internal methods}} like `[[Get]]` `[[Set]]` `[[HasOwnProperty]]`

%

%

clozeq

---

## js: introspection Reflect API 02

Reflect.apply is similar to `Function#apply`

··  `` var ages = [11, 33, 12, 54, 18, 96]; `` <br>

··  `` // Function.prototype style: `` <br>
··  `` var youngest = Math.min.apply(Math, ages); `` <br>
··  `` var type = Object.prototype.toString.call(youngest); `` <br>

··  `` // Reflect style: `` <br>
··  `` var youngest = Reflect.apply({{c1::Math.min, Math, ages}}); `` <br>
··  `` var type = Reflect.apply({{c2::Object.prototype.toString, youngest}}); `` <br>

%

%

clozeq

---

## js: introspection Reflect API 03

··  `` function totalNumbers() { `` <br>
····  `` return Array.prototype.reduce.call(arguments, function (total, next) { `` <br>
······  `` return total + next; `` <br>
····  `` }, 0); `` <br>
··  `` } `` <br>
··  `` Reflect.apply({{c1::totalNumbers, null, [1, 2, 3, 4]}}) === 10; `` <br>

%

%

clozeq

---

## js: introspection Reflect API 04

··  `` function MyDate() { `` <br>
····  `` /*…*/ `` <br>
··  `` } `` <br>
··  `` Reflect.{{c1::defineProperty}}(MyDate, 'now', { `` <br>
····  `` value: () => currentms `` <br>
··  `` }); `` <br>

%

%

clozeq

---

## js: introspection Reflect API 05

··  `` var myObject = {}; `` <br>
··  `` Object.defineProperty(myObject, 'hidden', { `` <br>
····  `` value: true, `` <br>
····  `` enumerable: false, `` <br>
··  `` }); `` <br>
··  `` var theDescriptor = Reflect.{{c1::getOwnPropertyDescriptor}}({{c2::myObject, 'hidden'}}); `` <br>
··  `` assert.deepEqual(theDescriptor, { value: true, enumerable: true }); `` <br>

··  `` assert(Object.getOwnPropertyDescriptor(1, 'foo') === undefined) `` <br>
··  `` Reflect.getOwnPropertyDescriptor(1, 'foo'); // throws TypeError `` <br>

%

%

clozeq

---

## js: introspection Reflect API 06

··  `` var myObj = { foo: 'bar' }; `` <br>
··  `` delete myObj.foo; `` <br>
··  `` assert(myObj.{{c1::hasOwnProperty}}('foo') === false); `` <br>

··  `` myObj = { foo: 'bar' }; `` <br>
··  `` Reflect.{{c2::deleteProperty}}(myObj, 'foo'); `` <br>
··  `` assert(myObj.hasOwnProperty('foo') === false); `` <br>

%

%

clozeq

---

## js: introspection Reflect API 07

··  `` var myObj = new FancyThing(); `` <br>
··  `` assert(Reflect.{{c1::getPrototypeOf}}(myObj) === FancyThing.prototype); `` <br>

··  `` // Old style `` <br>
··  `` assert(Object.getPrototypeOf(myObj) === FancyThing.prototype); `` <br>

··  `` Object.getPrototypeOf(1); // undefined `` <br>
··  `` Reflect.getPrototypeOf(1); // TypeError `` <br>

%

%

clozeq

---

## js: introspection Reflect API 08

··  `` var myObj = new FancyThing(); `` <br>
··  `` assert(Reflect.{{c1::setPrototypeOf}}(myObj, OtherThing.{{c2::prototype}}) === true); `` <br>
··  `` assert(Reflect.getPrototypeOf(myObj) === OtherThing.prototype); `` <br>

%

%

clozeq

---

## js: introspection Reflect API 09

··  `` var myObject = { `` <br>
····  `` foo: 1, `` <br>
····  `` bar: 2, `` <br>
····  `` baz() { `` <br>
······  `` return this.foo + this.bar; `` <br>
····  `` }, `` <br>
··  `` } `` <br>

··  `` assert(Reflect.{{c1::get}}({{c2::myObject, 'foo'}}) === 1); `` <br>

%

%

clozeq

---

## js: introspection Reflect API 10

··  `` myObject = { `` <br>
····  `` foo: 1, `` <br>
··  `` }; `` <br>
··  `` Object.setPrototypeOf(myObject, { `` <br>
····  `` get bar() { `` <br>
······  `` return 2; `` <br>
····  `` }, `` <br>
····  `` baz: 3, `` <br>
··  `` }); `` <br>
··  `` assert(Reflect.{{c1::has}}(myObject, 'foo') === true); `` <br>

%

%

clozeq

---

## js: introspection Reflect API 11

··  `` var myObject = { `` <br>
····  `` foo: 1, `` <br>
····  `` bar: 2, `` <br>
····  `` [Symbol.for('baz')]: 3, `` <br>
····  `` [Symbol.for('bing')]: 4, `` <br>
··  `` }; `` <br>
··  `` assert.deepEqual(Object.getOwnPropertyNames(myObject), ['foo', 'bar']); `` <br>
··  `` .deepEqual(Reflect.{{c1::ownKeys}}(myObject), ['foo', 'bar', Symbol.for('baz'), Symbol.for('bing')]); `` <br>

%

%

clozeq

---

## js: source-map 01

compiled some file with Babel:

    "use strict";

    var square = function square(x) {
      return x * x;
    };
    //# {{c1::sourceMappingURL}}=test.js.map

%

%

clozeq

---

## js: source-map 02

the source map content inside “test.js.map”:

    {
        "version": 3,
        "sources": ["test.es6.js"],
        "names": [],
        "{{c1::mappings}}": ";;AAAA,IAAM,MAAM,GAAG,SAAT,MAAM,CAAI,CAAC;SAAK,CAAC,GAAG,CAAC;CAAA,CAAC",
        "file": "test.js",
        "sourcesContent": ["const square = (x) => x * x;"]
    }


%

%

clozeq

---

## js: source-map 03

"mappings" data format uses "Base 64 VLQ" format.

{{c1::VLQ}}: variable-length quantity. It is used to store a number in space-efficient way.

For example “AAAA” stands for [0,0,0,0] and “GAAG” stands for [3,0,0,3].

%

%

clozeq

---

## js: source-map 04

Limitations of source maps

1. Source maps map positions in the code, but they don’t map how two variable names relate to one another.

2. Optimizations can make the compiled source code {{c1::not match}} the behavior you’d expect from the code you wrote.


%

%

clozeq

---

## js: esmodules 01 How to load an ES6/7/X application in the browser without a bundler

    <script type="{{c1::module}}" src="path/to/es6/module.js"></script>

The browser recursively resolves all `import` dependencies.

But they cannot resolve {{c2::NPM}} dependencies.

%

%

clozeq

---

## js: esmodules 02 Service Workers and Unchained

Service Workers {{c1::intercept}} network requests and handle their response.

Use SW to:

- fetch files
- remap `import` files to `node_modules` folder
- detect `JSX` and transpile them

Unchained is like bundlers and transpilers.

It provides helpers to register Service Workers, a polyfill to support dynamic `import`. It can resolve file `import`.


%

%

clozeq

---

## js: esmodules 03 Using ES modules natively in Node.js

Nodejs supports ES6 modules natively with option `--experimental-modules`

    // main.mjs
    import {add} from './lib.mjs';

    node --{{c1::experimental-modules}} main.mjs

%

%

clozeq

---

## js: esmodules 04 decoding source map mappings VLQ numbers

Decode using `vlq`

    const vlq = require('vlq')
    {{c1::vlq}}.decode('QAAQC')

%

%

clozeq

---

## js: devtools Chrome DevTools: Never Pause Here

{{c1::Behavior-based}} breakpoints (Chrome calls them conditional breakpoints):

- An exception is thrown
- The DOM is modified
- A DOM event is triggered (e.g. when the user click on a button)
- An Ajax request is made


%

%

clozeq

---


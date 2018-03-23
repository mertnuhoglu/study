
## js: import ramda

··  `` let {{c1::{compose, curry}}} = {{c2::require('ramda')}}; `` <br>
··  `` compose(..) `` <br>
··  `` curry(..) `` <br>


%

%

clozeq

---

## js: request 01 promise

··  `` var request = require('{{c1::request-promise-native}}'); `` <br>
··  `` request('http://jsonplaceholder.typicode.com/users/1') `` <br>
····  `` .{{c2::then}}( html => console.log('body:', html) ) `` <br>
····  `` .catch( err => console.log('error:', err) ); `` <br>


%

%

clozeq

---

## js: request 02 json response

··  `` request('http://jsonplaceholder.typicode.com/users/1') `` <br>
····  `` .then( json => console.log({{c1::JSON.parse}}(json).company)) `` <br>

%

%

clozeq

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

---

## js: ramda 01 require

··  `` const {{c1::R}} = require('ramda'); `` <br>
··  `` R.map(R.prop('id')); `` <br>
··  `` R.map(([k, v]) => {{c2::global}}[k] = v, R.toPairs(R)); `` <br>
··  `` map(prop('id')); `` <br>

%

%

clozeq

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

---

## js: rxjs 01 collect ids from nested subitems

    listsOfItems :: [{name, items:[{id}]}]

    listsOfItems.map( {{c1::list =>}}
        list.items.map((e) => {{c1::(e.id)}})
      ).concatAll();
    // [ 1, 2, 5, 7 ]

%

%

clozeq

---


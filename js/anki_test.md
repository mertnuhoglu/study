## js: `study_parcel_jquery.Rmd` 02 parceljs steps

··  `` mkdir my_project && cd $_ && npm init -y  `` <br>
··  `` npm i parcel-bundler jquery jquery-ui-dist `` <br>

index.html

··  `` <script src="./src/index.js"></script> `` <br>

{{c1::index.js}}

··  `` window.$ = require('jquery') `` <br>
··  `` {{c2::import}} {addText} from './app.js' `` <br>

package.json

··  `` "start": "{{c3::parcel}} index.html", `` <br>
··  `` "build": "parcel build index.html --public-url ./", `` <br>

··  `` npm start `` <br>


%

%

clozeq

---

## js: `study_parcel_jquery`.Rmd 03 import jquery

import jquery and make it global variable

Differences in `index.js` 

··  `` window.$ = require('jquery') `` <br>
··  `` --->>> `` <br>
··  `` import "./{{c1::import-jquery}}"; `` <br>

a separate file `import-jquery.js` to import `jquery`:

··  `` import jquery from "jquery"; `` <br>
··  `` {{c2::export}} default ({{c3::window.$}} = window.jQuery = jquery); `` <br>


%

%

clozeq

---

## js: use unpkg
 
··  `` <script src="https://unpkg.com/{{c1::jquery}}@3.1.1/dist/{{c2::jquery.js}}"></script> `` <br>


%

%

clozeq

---

## js: `study_parcel_jquery`.Rmd 02 parceljs steps

··  `` <script src="./src/index.js"></script> `` <br>

%

%

clozeq

---

## js: update npm and node

update npm

··  `` `sudo npm install -g {{c1::npm}}` `` <br>

update node

··  `` sudo npm cache clean -f `` <br>
··  `` sudo npm install -g {{c2::n}} `` <br>
··  `` nvm {{c3::ls}} `` <br>
··  `` # check local node versions `` <br>
··  `` nvm ls-remote `` <br>
··  `` # check available node versions `` <br>
··  `` nvm {{c4::install}} 9.8.0 `` <br>
··  `` # install node version `` <br>


%

%

clozeq

---

## js: document.querySelector instead of jquery

··  `` const {{c1::$}} = (e) => document.querySelector(e) `` <br>
··  `` const {{c1::$$}} = (e) => document.querySelectorAll(e) `` <br>

··  `` $('#root').innerHTML = `<h1>Parcel for VanillaJS</h1><br><time>${moment().format('LTS')}</time>` `` <br>


%

%

clozeq

---

## js: `study_parcel_jquery.Rmd` 01 ES + CommonJS 

··  `` const jquery = {{c1::require}}("jquery") `` <br>
··  `` window.$ = window.jQuery = jquery; `` <br>
··  `` require("jquery-ui-dist/jquery-ui.css") `` <br>
··  `` require("jquery-ui-dist/jquery-ui.js") `` <br>
··  `` {{c2::import}} {addText} from './app.js' `` <br>


%

%

clozeq

---

## js: `study_parcel_jquery.Rmd` 02 parceljs steps

··  `` mkdir :'my_projec`t' && cd $_ && npm init -y  `` <br>
··  `` npm i parcel-bundler jquery jquery-ui-dist `` <br>

index.html

··  `` <script src="./src/index.js"></script> `` <br>

{{c1::index.js}}

··  `` window.$ = require('jquery') `` <br>
··  `` {{c2::import}} {addText} from './app.js' `` <br>

package.json

··  `` "start": "{{c3::parcel}} index.html", `` <br>
··  `` "build": "parcel build index.html --public-url ./", `` <br>

··  `` npm start `` <br>


%

%

clozeq

---

## js: `'study_parcel_jquery.Rmd'` 03 import jquery

import jquery and make it global variable

Differences in `index.js` 

··  `` window.$ = require('jquery') `` <br>
··  `` --->>> `` <br>
··  `` import "./{{c1::import-jquery}}"; `` <br>

a separate file `import-jquery.js` to import `jquery`:

··  `` import jquery from "jquery"; `` <br>
··  `` {{c2::export}} default ({{c3::window.$}} = window.jQuery = jquery); `` <br>


%

%

clozeq

---

## js: use unpkg
 
··  `` <script src="https://unpkg.com/{{c1::jquery}}@3.1.1/dist/{{c2::jquery.js}}"></script> `` <br>


%

%

clozeq

---


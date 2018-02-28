
## tab sekmeleri nasıl oluşturulur

{{c1::ul}}.nav.nav-tabs <br>
{{c2::li}}.active <br>
{{c3::a}}(href="#tab1" data-toggle="tab") header tab1 <br>
{{c2::li}} <br>
{{c3::a}}(href="#tab2" data-toggle="tab") header tab2 <br>
.{{c4::tab-content}} <br>
.{{c5::tab-pane}}.active(id="tab1") <br>
content tab1 <br>
.{{c5::tab-pane}}(id="tab2") <br>
content tab2 <br>

%

%

clozeq

---

## intellij bootstrap support

{{c1::bs3}}-... <br>
shortcut: {{c1::#j}} <br>

%

%

clozeq

---

## twitter bootstrap dokümantasyonu

örneklerle resmi dokümantasyonu:

https://{{c1::getbootstrap}}.com/docs/3.3/javascript/#tabs <br>

%

%

clozeq

---

## Bootstrap Getting Started TOC

··  Download <br>
····  Bootstrap CDN <br>
····  Install with npm <br>
··  What's included <br>
····  Precompile Bootstrap <br>
····  Compiling CSS and JS <br>
··  Basic template <br>
····  start with basic html template <br>
··  {{c1::Examples}} <br>
····  Using the framework <br>
······  Starter template <br>
······  Bootstrap {{c2::theme}} <br>
········  more visual <br>
······  {{c3::Grids}} <br>
········  examples of grid layouts <br>
······  Jumbotron <br>
········  with navbar and basic grid columns <br>
······  Narrow jumbotron <br>
····  {{c4::Navbars}} in action <br>
······  Navbar <br>
········  basic template <br>
······  Static top navbar <br>
······  Fixed navbar <br>
····  Custom {{c5::components}} <br>
······  Cover <br>
········  one-page template <br>
······  Carousel <br>
······  Blog <br>
········  two column blog layout <br>
······  {{c6::Dashboard}} <br>
········  admin dashboard <br>
········  fixed sidebar and navbar <br>
······  Sign-in page <br>
······  Justified nav <br>
······  Sticky footer <br>
········  fotter at bottom <br>
······  Sticky footer with navbar <br>

%

%

clozeq

---

## Bootstrap CSS TOC 01

··  https://getbootstrap.com/docs/3.3/css/ <br>
··  Overview <br>
····  HTML5 doctype <br>
········  &lt;!DOCTYPE html&gt; <br>
········  &lt;html lang="en"&gt; <br>
····  Mobile first <br>
····  Typography and links <br>
······  basic global display params <br>
······  background-color: #fff; <br>
······  ... <br>
····  {{c1::Containers}} <br>
······  bs requires a containing element to wrap contents and grid system <br>
······  opt <br>
··········  &lt;div class="container"&gt; <br>
··········  # fixed width  <br>
··········  &lt;div class="container-fluid"&gt; <br>
··········  # spans entire width <br>
··  {{c2::Grid}} system <br>
····  what is <br>
······  12 columns <br>
······  predefined classes <br>
······  mixins for more semantic layouts <br>
····  Introduction <br>
······  .{{c3::row}} within .container <br>
······  content within columns <br>
······  predefined classes: .{{c4::col-xs}}-4 <br>
······  gutters: gaps between column content via `padding` <br>
····  Grid options <br>
······  small devices: .col-xs- <br>
······  small devices tablets: .col-sm- <br>
······  medium devices: .col-md- <br>
······  large: .{{c5::col-lg}}- <br>
··  {{c6::Typography}} <br>
····  Headings <br>
····  Body copy <br>
····  Inline text elements <br>
······  Marked text <br>
········  for highlighting <br>
··········  &lt;mark&gt; <br>
····  {{c7::Alignment}} classes <br>
······  .text-left <br>
····  Transformation classes <br>
······  text capitalization <br>
······  .text-lowercase <br>
··  {{c8::Tables}} <br>
····  Basic example <br>
········  add .{{c9::table}} to &lt;table&gt; <br>
····  {{c10::Striped}} rows <br>
······  .table-striped  <br>
····  Bordered table <br>
······  .table-bordered <br>
····  {{c11::Hover}} rows <br>
······  .table-hover <br>
····  Responsive tables <br>
······  .table-responsive <br>
········  scroll horizontally on phones <br>

%

%

clozeq

---

## Bootstrap CSS TOC 02

··  {{c1::Forms}} <br>
····  Basic Examples <br>
······  width: {{c2::100}}%; by default <br>
······  wrap in .{{c3::form-group}} <br>
······  ex: <br>
··········  &lt;form&gt; <br>
············  &lt;div class="form-group"&gt; <br>
··············  &lt;label for="exampleInputEmail1"&gt;Email address&lt;/label&gt; <br>
··············  &lt;input type="email" class="form-control" id="exampleInputEmail1" placeholder="Email"&gt; <br>
············  &lt;/div&gt; <br>
····  {{c4::Inline}} Form  <br>
······  .form-inline <br>
······  /Users/mertnuhoglu/Dropbox/public/img/ss-277.png <br>
····  {{c5::Horizontal}} form <br>
······  like rows <br>
······  ex <br>
··········  &lt;form class="form-horizontal"&gt; <br>
············  &lt;div class="form-group"&gt; <br>
··············  &lt;label for="inputEmail3" class="col-sm-2 control-label"&gt;Email&lt;/label&gt; <br>
··············  &lt;div class="{{c6::col-sm}}-10"&gt; <br>
················  &lt;input type="email" class="form-control" id="inputEmail3" placeholder="Email"&gt; <br>
··············  &lt;/div&gt; <br>
····  Supported controls <br>
······  Inputs <br>
········  text, password, datetime, datetime-local, number, email, ... <br>
······  {{c7::Selects}} <br>
········  .form-control <br>
··········  &lt;select {{c8::multiple}} class="form-control"&gt; <br>
············  &lt;{{c9::option}}&gt;1&lt;...&gt; <br>
····  Static control <br>
······  static text next to a form label <br>
······  /Users/mertnuhoglu/Dropbox/public/img/ss-278.png <br>
······  ex <br>
··········  &lt;form class="form-horizontal"&gt; <br>
··········  &lt;div class="form-group"&gt; <br>
············  &lt;label class="col-sm-2 control-label"&gt;Email&lt;/label&gt; <br>
············  &lt;div class="col-sm-10"&gt; <br>
··············  &lt;p class="form-control-static"&gt;email@example.com&lt;/p&gt; <br>
············  &lt;/div&gt; <br>
····  Disabled state <br>
······  disabled attribute <br>
········  &lt;input ... disabled&gt; <br>

%

%

clozeq

---

## Bootstrap CSS TOC 03

··  {{c1::Buttons}} <br>
····  Button tags <br>
······  &lt;a&gt; &lt;button&gt; &lt;input&gt; elements <br>
······  button types: {{c2::link}} {{c5::button}} {{c6::input}} {{c7::submit}} <br>
········  &lt;a class="btn {{c3::btn-default}}" href="#" {{c8::role}}="button"&gt;Link&lt;/a&gt; <br>
········  &lt;button class="btn btn-default" type="submit"&gt;Button&lt;/button&gt; <br>
········  &lt;input class="btn btn-default" type="{{c4::button}}" value="Input"&gt; <br>
········  &lt;input class="btn btn-default" type="submit" value="Submit"&gt; <br>
····  Options <br>
······  styled buttons <br>
······  classes: btn-default btn-primary btn-success ... <br>
····  Sizes <br>
······  .btn-lg .btn-sm .btn-xs <br>
····  Active state <br>
····  Disabled state <br>
··  {{c9::Images}} <br>
····  Responsive images <br>
······  .img-responsive <br>
····  Image shapes <br>
······  .{{c10::img-rounded}} .img-circle .img-thumbnail <br>
··  {{c11::Helper}} classes <br>
····  Contextual colors <br>
······  /Users/mertnuhoglu/Dropbox/public/img/ss-279.png <br>
····  Contextual backgrounds <br>
······  /Users/mertnuhoglu/Dropbox/public/img/ss-280.png <br>
····  Close icon <br>
······  /Users/mertnuhoglu/Dropbox/public/img/ss-281.png <br>
··  Responsive utilities <br>
····  Print classes <br>
····  Test cases <br>
··  Using Less <br>


%

%

clozeq

---

## Bootstrap Components TOC 01

··  https://getbootstrap.com/docs/3.3/components/ <br>
··  {{c1::Glyphicons}} <br>
··  {{c2::Dropdowns}} <br>
····  for displaying lists of links <br>
····  .dropdown <br>
··  {{c3::Button groups}} <br>
····  Basic example <br>
········  &lt;div class="btn-group" ...&gt; <br>
······  /Users/mertnuhoglu/Dropbox/public/img/ss-282.png <br>
····  Button toolbar <br>
······  multiple .btn-group elements grouped <br>
······  .btn-toolbar <br>
····  Sizing <br>
······  .btn-group-lg <br>
····  Nesting <br>
······  for dropdown menus mixed with normal buttons <br>
····  Vertical variation <br>
······  .btn-group-vertical <br>
··  Button dropdowns <br>
····  use buttons to trigger dropdown <br>
··  Input groups <br>
····  /Users/mertnuhoglu/Dropbox/public/img/ss-283.png <br>
··  {{c4::Navs}} <br>
····  Tabs <br>
········  &lt;ul class="nav nav-tabs"&gt; <br>
······  /Users/mertnuhoglu/Dropbox/public/img/ss-284.png <br>
····  Pills <br>
······  .nav-pills instead of .nav-tabs <br>

%

%

clozeq

---

## Bootstrap Components TOC 02

··  {{c1::Navbar}} <br>
····  Default navbar <br>
······  as navigation headers <br>
······  /Users/mertnuhoglu/Dropbox/public/img/ss-285.png <br>
······  ex: <br>
········  &lt;nav class="navbar" <br>
··········  &lt;div .. <br>
············  &lt;div class="navbar-header" <br>
··············  &lt;button .. class="navbar-toggle"... <br>
··  Breadcrumbs <br>
··  Pagination <br>
····  Deafult pagination <br>
··  Labels <br>
····  &lt;span class="label" <br>
··  Badges <br>
····  /Users/mertnuhoglu/Dropbox/public/img/ss-286.png <br>
····  &lt;span class="badge"&gt;42 <br>
··  Jumbotron <br>
····  key content on page <br>
····  &lt;div class="jumbotron" <br>
··  Page header <br>
····  .page-header <br>
··  {{c2::Thumbnails}} <br>
····  to display grids of images, text <br>
······  &lt;a class="thumbnail" <br>
··  Alerts <br>
··  Progress bars <br>
··  Media Object <br>
····  image + alongside text <br>
····  /Users/mertnuhoglu/Dropbox/public/img/ss-287.png <br>
··  {{c3::List group}} <br>
····  .list-group <br>
····  /Users/mertnuhoglu/Dropbox/public/img/ss-288.png <br>

%

%

clozeq

---

## Bootstrap Components TOC 03

··  {{c1::Panels}} <br>
····  to put element in a box <br>
····  Basic example <br>
······  .panel <br>
······  /Users/mertnuhoglu/Dropbox/public/img/ss-289.png <br>
····  With tables <br>
······  /Users/mertnuhoglu/Dropbox/public/img/ss-290.png <br>
··  {{c2::Wells}} <br>
····  .well <br>
····  Default well <br>
······  gives inset effect <br>
······  /Users/mertnuhoglu/Dropbox/public/img/ss-291.png <br>

%

%

clozeq 

--- 

## Bootstrap Javascript TOC 01

··  https://getbootstrap.com/docs/3.3/javascript/ <br>
··  Overview <br>
····  Individual or compiled <br>
······  bootstrap plugins: {{c1::individual}} *.js files <br>
······  or all: bootstrap.js <br>
····  {{c2::Data attributes}} <br>
······  use bs plugins without js <br>
······  or use js <br>
····  Programmatic API <br>
······  use bs plugins through js api <br>
······  ex: $('.btn.danger').button('toggle').addClass('fat') <br>
······  methods  <br>
········  chainable <br>
··········  returns collection acted upon <br>
········  accept optional options object <br>
····  No conflict <br>
······  name collisions with other UI frameworks <br>
····  Events <br>
······  ex:  <br>
········  show: triggered at the start of event <br>
········  shown: triggered on completion of an action <br>
······  all events are namespaced <br>
······  ex: stop execution of action before it starts <br>
········  $('#myModal').on('show.{{c3::bs.modal}}', function (e) { <br>
··········  if (!data) return e.preventDefault() // stops modal from being shown <br>
········  }) <br>
····  Version numbers <br>
······  $.fn.tooltip.Constructor.VERSION // =&gt; "3.3.7" <br>
····  When JavaScript is disabled <br>
······  how to gracefully fail when js is disabled <br>
··  Transitions <br>
····  transition.js <br>

%

%

clozeq

---

## Bootstrap Javascript TOC 02

··  {{c1::Modal}} <br>
····  what is <br>
······  dialog prompts <br>
····  Examples <br>
······  static examples <br>
······  sections: {{c2::header}}, body, set of actions in footer <br>
······  .modal <br>
······  .modal-header <br>
······  ex <br>
········  &lt;div class="modal fade" tabindex="-1" role="dialog"&gt; <br>
··········  &lt;div class="modal-dialog" role="document"&gt; <br>
············  &lt;div class="modal-content"&gt; <br>
··············  &lt;div class="modal-header"&gt; <br>
················  &lt;button type="button" class="close" data-dismiss="modal" aria-label="Close"&gt;&lt;span aria-hidden="true"&gt;&times;&lt;/span&gt;&lt;/button&gt; <br>
················  &lt;h4 class="modal-title"&gt;Modal title&lt;/h4&gt; <br>
··············  &lt;/div&gt; <br>
····  Sizes <br>
······  .modal-lg <br>
····  Remove animation <br>
······  .fade: appears with animation <br>
····  Using the grid system <br>
······  nest .row within .modal-body <br>
······  ex <br>
········  &lt;div class="modal-body"&gt; <br>
··········  &lt;div class="{{c3::row}}"&gt; <br>
············  &lt;div class="col-md-4"&gt;.col-md-4&lt;/div&gt; <br>

%

%

clozeq

---

## Bootstrap Javascript TOC 03

··  {{c1::Dropdown}} <br>
····  what is <br>
······  add dropdown menus to anything <br>
········  within a navbar <br>
········  within pills <br>
····  Examples <br>
······  via data attributes <br>
········  {{c2::data-toggle}}="dropdown" <br>
········  {{c3::data-target}}="#" <br>
··········  use data-target instead of href="#" <br>
········  ex <br>
··········  &lt;div class="dropdown"&gt; <br>
············  &lt;button id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"&gt; <br>
······  via js <br>
········  $('.dropdown-toggle').dropdown() <br>
··  {{c4::Scrollspy}} <br>
····  what is <br>
······  scroll ederken bulunulan konuma göre nav target'ını highlight eder <br>
····  Examples <br>
····  Usage <br>
······  requires  <br>
········  nav <br>
········  position: relative; <br>
······  via data attributes <br>
········  {{c5::data-spy}}="scroll" <br>
··········  to element that you spy on <br>
········  {{c6::data-target}}="#navbar-example" <br>
········  ex: <br>
··········  &lt;body data-spy="scroll" data-target="#navbar-example"&gt; <br>
············  ... <br>
············  &lt;div id="navbar-example"&gt; <br>
··············  &lt;ul class="nav nav-tabs" role="tablist"&gt; <br>
······  via js <br>
········  $('body').scrollspy({ target: '#navbar-example' }) <br>

%

%

clozeq

---

## Bootstrap Javascript TOC 04

··  {{c1::Tab}} <br>
····  what is <br>
······  tab.js <br>
······  panes of local content <br>
····  Examples <br>
······  via markup <br>
········  {{c2::data-toggle}}="tab" <br>
······  ex <br>
········  &lt;div&gt; <br>
··········  &lt;ul class="nav nav-tabs" role="tablist"&gt; <br>
············  &lt;li role="presentation" class="active"&gt;&lt;a href="#home" aria-controls="home" role="tab" data-toggle="tab"&gt;Home&lt;/a&gt;&lt;/li&gt; <br>
············  &lt;li role="presentation"&gt;&lt;a href="#profile" aria-controls="profile" role="tab" data-toggle="tab"&gt;Profile&lt;/a&gt;&lt;/li&gt; <br>
··········  &lt;/ul&gt; <br>
··········  &lt;div class="tab-content"&gt; <br>
············  &lt;div role="tabpanel" class="tab-pane active" id="home"&gt;...&lt;/div&gt; <br>
············  &lt;div role="tabpanel" class="tab-pane" id="profile"&gt;...&lt;/div&gt; <br>
··  {{c3::Tooltip}} <br>
····  what is <br>
······  tooltip.js <br>
······  hover over a link to see tooltips <br>
····  Examples <br>
····  Usage <br>
······  markup <br>
········  {{c4::data-toggle}}="tooltip" <br>
········  {{c5::title}}="content of tip" <br>

%

%

clozeq

---

## Bootstrap Javascript TOC 05

··  {{c1::Popover}} <br>
····  what is <br>
······  popver.js <br>
······  add small overlays of content <br>
······  konuşma balonu gibi <br>
····  Examples <br>
····  Usage <br>
······  markup <br>
········  {{c2::data-toggle}}="popver" <br>
········  title="title of it" <br>
········  data-content="content of it" <br>
··  {{c3::Alert}} <br>
····  what is <br>
······  alert.js <br>
······  alert messages <br>
····  Examples <br>
····  Usage <br>
······  markup <br>
········  data-dismiss="alert" <br>
········  ex: <br>
··········  &lt;button type="button" class="close" data-dismiss="alert" aria-label="Close"&gt; <br>
············  &lt;span aria-hidden="true"&gt;&times;&lt;/span&gt; <br>
····  Methods <br>
······  $().alert() <br>
····  Events <br>

%

%

clozeq

---

## Bootstrap Javascript TOC 06

··  {{c1::Button}} <br>
····  what is <br>
······  button.js <br>
····  Examples <br>
······  single toggle button <br>
······  checkbox/radio <br>
········  ex: <br>
··········  &lt;div class="btn-group" data-toggle="buttons"&gt; <br>
············  &lt;label class="btn btn-primary active"&gt; <br>
··············  &lt;input type="checkbox" autocomplete="off" checked&gt; Checkbox 1 (pre-checked) <br>
··  {{c2::Collapse}} <br>
····  what is <br>
······  collapse.js <br>
······  collapse/expand a pane <br>
····  Examples <br>
······  accordion <br>
········  data-parent="#accordion" <br>
····  Usage <br>
······  markup <br>
········  {{c3::data-toggle}}="collapse" <br>
········  data-target="" <br>
··········  what to collapse <br>
··  {{c4::Carousel}} <br>
····  what is <br>
······  carousel.js <br>
······  slideshow <br>
····  Examples <br>
····  Usage <br>
······  markup <br>
········  data-ride="carousel" <br>
········  data-slide <br>
······  ex <br>
········  &lt;div id="carousel-example-generic" class="carousel slide" data-ride="carousel"&gt; <br>
··········  &lt;!-- Indicators --&gt; <br>
··········  &lt;ol class="carousel-indicators"&gt; <br>
············  &lt;li data-target="#carousel-example-generic" data-slide-to="0" class="active"&gt;&lt;/li&gt; <br>
············  &lt;li data-target="#carousel-example-generic" data-slide-to="1"&gt;&lt;/li&gt; <br>
··········  ... <br>
··········  &lt;a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"&gt; <br>

%

%

clozeq

---

## css grid ex 01

··  columns and rows sizes <br>
····  .container { <br>
········  {{c1::display}}: grid; <br>
········  {{c2::grid-template-columns}}: 100px auto 100px; <br>
········  {{c3::grid-template-rows}}: 50px 50px; <br>
····  grid-template-columns: {{c4::repeat}}(auto-fit, 100px); <br>
··  sizes: <br>
····  100px <br>
····  {{c5::1fr}} <br>
····  {{c6::auto}} <br>
··  display: grid; <br>
··  grid-{{c7::gap}}: 3px; <br>

%

%

clozeq

---

## css grid ex 02

··  positioning <br>
····  .header { <br>
······  grid-column-start: 1; <br>
······  grid-column-end: 3; <br>
····  shorthand form: <br>
······  {{c1::grid-column}}: 1 / 3;  <br>
········  # start-end lines <br>
······  grid-column: 1 / {{c2::span}} 2;  <br>
········  # size <br>
······  grid-column: 1 / {{c3::-}}1;  <br>
········  # from end <br>
····  grid-{{c4::row}}: 1 / 3; <br>

%

%

clozeq

---

## css grid ex 03

··  naming lines <br>
····  grid-template-columns: {{c1::[main-start]}} 1fr [content-start] 5fr [content-end main-end]; <br>
····  grid-template-rows: [main-start] 40px {{c2::[content-start]}} auto [content-end] 40px [main-end]; <br>
····  grid-column: {{c3::main-start}} / main-end; <br>
····  grid-column: {{c4::main}}; <br>
····  grid-{{c5::area}}: content; <br>
··  grid-template-areas <br>
····  grid-{{c6::template-areas}}: <br>
······  "m . . h h h h h h h h h" <br>
······  "m c c c c c c c c c c c" <br>
······  "m f f f f f f f f f f f" <br>
····  .header { <br>
······  grid-area: {{c7::h}}; <br>

%

%

clozeq

---

## css grid ex 04

··  auto-fit minmax auto-fill <br>
····  grid-template-columns: {{c1::repeat}}(auto-fit, 100px); <br>
····  grid-template-columns: repeat(auto-fit, {{c2::minmax}}(100px, 1fr)); <br>
····  auto-fit <br>
····  auto-{{c3::fill}} <br>
··  grid-auto-rows flow <br>
····  grid-{{c4::auto-rows}}: 100px; <br>
····  grid-auto-{{c5::flow}}: dense; <br>
··  justify-{{c6::content}} align <br>
····  justify-content: {{c7::end}}; <br>
····  start {{c8::center}} end <br>
····  space-evenly {{c9::space-between}} space-around <br>
····  {{c10::align-content}}: end; <br>

%

%

clozeq

---

## css basic selectors 01

··  | description··················   | example··············   | <br>
··  | select all elements of any type | {{c1::*}}{}··················   | <br>
··  | select some—id················  | {{c2::#some—id}}{}············  | <br>
··  | select class1 and class2 elems  | {{c3::.class1.class2}} {}····   | <br>
··  | select h1 h3 types············  | {{c4::h1, h3}} {}············   | <br>
··  | p follows img directly········  | {{c5::img + p}} {}············  | <br>
··  | li direct children of ul······  | {{c6::ul &gt; li}} {}············  | <br>

%

%

clozeq

---

## css basic selectors 02

··  | span follows p················  | {{c1::p ~ span}} {}··········   | <br>
··  | li descendant of ul··········   | {{c2::ul li}} {}··············  | <br>
··  | &lt;a&gt; with title attribute······  | {{c3::a[title]}} {}··········   | <br>
··  | &lt;a&gt; href matches g.com········  | {{c4::a[href="https://g.com"]}} | <br>
··  | &lt;a&gt; href contains g··········   | {{c5::a[href*="g"]}}··········  | <br>
··  | &lt;a&gt; href ends with ".org"····   | {{c6::a[href$=".org"]}}······   | <br>
··  | &lt;a&gt; attr contains "val" word··  | {{c7::a[attr~="val"]}}········  | <br>

%

%

clozeq

---

## css pseudo selectors 01

··  | description··········  | selector············   | <br>
··  | mouse over··········   | a{{c1:::hover}}······  | <br>
··  | active link··········  | a{{c2:::active}}····   | <br>
··  | focus················  | input{{c3:::focus}}··  | <br>
··  | visited links········  | a{{c4:::visited}}····  | <br>
··  | link (not yet visited) | a{{c5:::link}}······   | <br>
··  | checked elements····   | input{{c6:::checked}}  | <br>
··  | disabled elements····  | input{{c7:::disabled}} | <br>
··  | enabled elements····   | input{{c8:::enabled}}  | <br>

%

%

clozeq

---

## css pseudo selectors 01

··  | not a specified element······  | {{c1:::not(p)}}······   | <br>
··  | first line··················   | p{{c2::::first-line}}   | <br>
··  | first letter················   | p{{c3::::first-letter}} | <br>
··  | first child··················  | p{{c4:::first-child}}   | <br>
··  | last child··················   | p{{c5:::last-child}}··  | <br>
··  | nth child (every 4th)········  | p{{c6:::nth-child(4n)}} | <br>
··  | first among same type siblings | p{{c7:::first-of-type}} | <br>
··  | element with no children····   | p{{c8:::empty}}······   | <br>

%

%

clozeq

---

## css box properties 01

··  | description | css················  | values··············   | <br>
··  | sizing····  | {{c1::box-sizing}}   | border-box··········   | <br>
··  | margin····  | {{c2::margin}}····   | 2px 4px 6px 8px······  | <br>
··  | padding··   | {{c3::padding}}····  | ..··················   | <br>
··  | color····   | {{c4::border-color}} |······················  | <br>
··  | style····   | {{c5::border-style}} | none hidden dotted ... | <br>
··  | width····   | {{c6::border-width}} | 10px················   | <br>

%

%

clozeq

---

## embed iframe

··  &lt;iframe src="{{c1::page.html}}" {{c2::width}}="200" height="30"&gt;&lt;/iframe&gt; <br>

%

%

clozeq

---



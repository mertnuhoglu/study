var snabbdom = require('snabbdom');
var h = require('snabbdom/h').default; 
var toHTML = require('snabbdom-to-html')

const items = [
  {'id': 1, 'title': "a"},
  {'id': 2, 'title': "b"},
];
var vdom = h("div", [
  h("ul#myTab.nav.nav-tabs", {
    "attrs": {
      "role": "tablist",
    },
  }, [
    h("li.nav-item", [
      h("a#home-tab.nav-link.active", {
        "attrs": {
          "data-toggle": "tab",
          "href": "#home",
          "role": "tab",
          "aria-controls": "home",
          "aria-selected": "true",
        },
      }, `Home`)
    ]),
    h("li.nav-item", [
      h("a#profile-tab.nav-link", {
        "attrs": {
          "data-toggle": "tab",
          "href": "#profile",
          "role": "tab",
          "aria-controls": "profile",
          "aria-selected": "false",
        },
      }, `Profile`)
    ])
  ]),
  h("div#myTabContent.tab-content", [
    h("div#home.tab-pane.fade.show.active", {
      "attrs": {
        "role": "tabpanel",
        "aria-labelledby": "home-tab",
      },
    }, `Tab 01`),
    h("div#profile.tab-pane.fade", {
      "attrs": {
        "role": "tabpanel",
        "aria-labelledby": "profile-tab",
      },
    }, `Tab 02`)
  ])
])
var out = toHTML(vdom)
console.log(out)
console.log(vdom)


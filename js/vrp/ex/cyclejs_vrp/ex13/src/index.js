import { run } from '@cycle/run';
import { makeDOMDriver } from '@cycle/dom';
import {makeHTTPDriver} from '@cycle/http';
import xs from 'xstream'
import {label, input, hr, div, h1} from '@cycle/dom';
import {h} from '@cycle/dom';

function main(sources) {
	return {
		DOM: xs.of(
			div([
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
    )
  }
}

const drivers = {
  DOM: makeDOMDriver('#app'),
}

run(main, drivers);


import { run } from '@cycle/run';
import { makeDOMDriver } from '@cycle/dom';
import {makeHTTPDriver} from '@cycle/http';
import xs from 'xstream'
import {label, input, hr, div, h1} from '@cycle/dom';
import {a, li, ul} from '@cycle/dom';
import {h} from '@cycle/dom';
import "./import_jquery";

function main(sources) {
	return {
		DOM: xs.of(
			div([
        ul("#myTab.nav.nav-tabs", {
          "attrs": {
            "role": "tablist",
          },
        }, [
          li(".nav-item", [
            a("#home-tab.nav-link.active", {
              "attrs": {
                "data-toggle": "tab",
                "href": "#home",
                "role": "tab",
                "aria-controls": "home",
                "aria-selected": "true",
              },
            }, `Home`)
          ]),
          li(".nav-item", [
            a("#profile-tab.nav-link", {
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
        div("#myTabContent.tab-content", [
          div("#home.tab-pane.fade.show.active", {
            "attrs": {
              "role": "tabpanel",
              "aria-labelledby": "home-tab",
            },
          }, `Tab 01`),
          div("#profile.tab-pane.fade", {
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


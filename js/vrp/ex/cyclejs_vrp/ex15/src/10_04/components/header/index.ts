import xs from 'xstream';
import { nav, a, ul, li, header, h1 } from '@cycle/dom';
import { style } from 'typestyle/lib';
import { Sources } from '../../cycle-isolate';
import { Sinks, Omit } from '../../interfaces';

const headerStyles = style({
});

export default function Header(sources: Sources) {
  const vdom$ = xs.of(
    header([
      nav(".navbar.navbar-light.bg-light.navbar-expand-md", [
        a(".navbar-brand", {
          "attributes": {
            "href": "#",
          }
        }, ["RotaPlan"]),
        ul(".nav.nav-pills", [
          li(".nav-item", [
            a(".nav-link.active", {
              "attributes": {
                "href": "/plan",
                "className": "nav-link active"
              }
            }, ["Planla"])
          ]),
          li(".nav-item", [
            a(".nav-link", {
              "attributes": {
                "href": "/manage",
                "className": "nav-link"
              }
            }, ["Yönet"])
          ]),
          li(".nav-item", [
            a(".nav-link", {
              "attributes": {
                "href": "/report",
                "className": "nav-link"
              }
            }, ["Raporlama"])
          ]),
          li(".nav-item", [
            a(".nav-link", {
              "attributes": {
                "href": "/",
                "className": "nav-link"
              }
            }, ["Çıkış"])
          ])
        ]),
        a(".nav-link", {
          "attributes": {
            "href": "#",
            "className": "nav-link"
          }
        }, ["#username"])
      ])
    ])
  );
  const sinks: Omit<Sinks, 'onion'> = {
    DOM: vdom$,
  }
  return sinks;
}

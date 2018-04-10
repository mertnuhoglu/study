import xs from 'xstream';
import { nav, a, ul, li, header, h1 } from '@cycle/dom';
import { style } from 'typestyle';

const headerStyles = style({
});

export default function Header() {
  return xs.of(
    header([
      nav(".navbar.navbar-light.bg-light.navbar-expand-md", [
        a(".navbar-brand", {
          "attributes": {
            "href": "#",
            "className": "navbar-brand"
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
}

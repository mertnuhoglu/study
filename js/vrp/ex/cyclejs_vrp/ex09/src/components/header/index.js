import xs from 'xstream';
import { nav, a, ul, li, header, h1 } from '@cycle/dom';
import { style } from 'typestyle';

const headerStyles = style({
});

export default function Header() {
  return xs.of(
    header(
      nav(".navbar.navbar-light.bg-light.navbar-expand-md",
        [
          a(".navbar-brand", {href: "#"}, "VRP Rota Plan"),
          ul(".nav.nav-pills",
            [
              li(".nav-item", a(".nav-link.active", {href: "/plan"}, "Planla")),
              li(".nav-item", a(".nav-link.active", {href: "/manage"}, "Yönet")),
              li(".nav-item", a(".nav-link.active", {href: "/report"}, "Raporlama")),
              li(".nav-item", a(".nav-link.active", {href: "/"}, "Çıkış")),
            ]
          ),
          a(".nav-link", {href: "#"}, "#username"),
        ]
      )
    )
  );
}

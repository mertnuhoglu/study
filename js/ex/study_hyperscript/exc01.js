var x = require('hyperaxe')
var siteNav = (...links) => x('nav.site')(
  links.map(link =>
    x('a.link')({ href: link.href }, link.text)
  )
)
var out = x.body(
  siteNav(
    { href: '#apps', text: 'apps' },
    { href: '#games', text: 'games' }
  )
)
console.log(out.outerHTML)

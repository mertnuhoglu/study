// index.js:
const itt = require('itt')
const math = require('./math')
console.log(itt.range(8).map(math.square).join(' '))


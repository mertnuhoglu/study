"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var xstream_1 = require("xstream");
var stream = xstream_1.default.periodic(1000)
    .filter(function (i) { return i % 2 === 0; })
    .map(function (i) { return i * i; })
    .endWhen(xstream_1.default.periodic(5000).take(1));
stream.addListener({
    next: function (i) { return console.log(i); },
    error: function (err) { return console.error(err); },
    complete: function () { return console.log('completed'); },
});
//# sourceMappingURL=ex08.js.map
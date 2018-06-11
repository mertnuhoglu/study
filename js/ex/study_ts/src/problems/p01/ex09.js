"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var http_1 = require("@cycle/http");
var xstream_1 = require("xstream");
var HTTP = http_1.makeHTTPDriver();
var requests$ = xstream_1.default.from([
    {
        url: 'http://localhost:8080/rest/plan?select=plan_id,usr,depot_id',
        method: 'GET',
        headers: {
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
        },
        category: 'plan',
    },
]);
var httpSource = HTTP(requests$);
var plans$ = httpSource.select('plan')
    .flatten()
    .map(function (res) {
    return res.body;
});
plans$.addListener({
    next: function (i) { return console.log("result: " + JSON.stringify(i)); },
    error: function (err) { return console.error(err); },
    complete: function () { return console.log('s1 completed'); },
});
//# sourceMappingURL=ex09.js.map
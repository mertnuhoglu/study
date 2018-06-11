"use strict";
var __assign = (this && this.__assign) || Object.assign || function(t) {
    for (var s, i = 1, n = arguments.length; i < n; i++) {
        s = arguments[i];
        for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
            t[p] = s[p];
    }
    return t;
};
Object.defineProperty(exports, "__esModule", { value: true });
var http_1 = require("@cycle/http");
var xstream_1 = require("xstream");
var cycle_onionify_1 = require("cycle-onionify");
function intent() {
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
    return requests$;
}
function model(httpSource) {
    var initReducer$ = xstream_1.default.of(function initReducer(prevState) {
        var initialState = {
            plans: [],
            purchase_orders: [],
        };
        return initialState;
    });
    var planReducer$ = httpSource.select('plan')
        .flatten()
        .map(function (res) { return function planReducer(prevState) {
        return __assign({}, prevState, { plans: res.body });
    }; });
    return xstream_1.default.merge(initReducer$, planReducer$);
}
function onion(reducer$) {
    var state$ = xstream_1.default.merge(reducer$)
        .fold(function (prevState, reducer) { return reducer(prevState); }, {});
    return state$;
}
function main(sources) {
    var requests$ = intent();
    var HTTP = http_1.makeHTTPDriver();
    var httpSource = HTTP(requests$);
    var reducer$ = model(httpSource);
    var state$ = sources.onion.state$;
    return {
        onion: reducer$,
        state$: state$
    };
}
var mainOnionified = cycle_onionify_1.default(main);
var sources = {};
var state$ = mainOnionified(sources).state$;
state$.addListener({
    next: function (i) { return console.log("result: " + JSON.stringify(i)); },
    error: function (err) { return console.error(err); },
    complete: function () { return console.log('s1 completed'); },
});
//# sourceMappingURL=ex11.js.map
"use strict";
var __assign = (this && this.__assign) || Object.assign || function(t) {
    for (var s, i = 1, n = arguments.length; i < n; i++) {
        s = arguments[i];
        for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
            t[p] = s[p];
    }
    return t;
};
exports.__esModule = true;
var http_1 = require("@cycle/http");
var xstream_1 = require("xstream");
var cycle_onionify_1 = require("cycle-onionify");
var ex13_sub_1 = require("./ex13_sub");
var isolate_1 = require("@cycle/isolate");
var run_1 = require("@cycle/run");
function intent() {
    var requests$ = xstream_1["default"].from([
        {
            url: 'http://localhost:8080/rest/plan?select=plan_id,usr,depot_id',
            method: 'GET',
            headers: {
                "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
            },
            category: 'plan'
        },
    ]);
    return requests$;
}
function model(httpSource) {
    var initReducer$ = xstream_1["default"].of(function initReducer(prevState) {
        var initialState = {
            plans: [],
            purchase_orders: [],
            firstPlan: {}
        };
        return initialState;
    });
    var planReducer$ = httpSource.select('plan')
        .flatten()
        .map(function (res) { return function planReducer(prevState) {
        return __assign({}, prevState, { plans: res.body });
    }; });
    return xstream_1["default"].merge(initReducer$, planReducer$);
}
function main(sources) {
    var component = isolate_1["default"](ex13_sub_1.main, { onion: ex13_sub_1.subLens })(sources);
    var parentRequests$ = intent();
    var requests$ = xstream_1["default"].merge(parentRequests$, component.HTTP);
    var parentReducer$ = model(sources.HTTP);
    var reducer$ = xstream_1["default"].merge(parentReducer$, component.onion);
    var state$ = xstream_1["default"].merge(sources.onion.state$, component.state$);
    state$.addListener({
        next: function (i) { return console.log("state:: " + JSON.stringify(i)); },
        error: function (err) { return console.error(err); },
        complete: function () { return console.log('s1 completed'); }
    });
    return {
        onion: reducer$,
        HTTP: requests$
    };
}
var mainOnionified = cycle_onionify_1["default"](main);
run_1.run(mainOnionified, {
    HTTP: http_1.makeHTTPDriver()
});
// component state: {"plans":[],"purchase_orders":[],"firstPlan":[]}
// app state:: {"plans":[],"purchase_orders":[],"firstPlan":{}}
// component state: {"plans":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4},{"plan_id":2,"usr":"usr_4_4_4_4_4_4","depot_id":2},{"plan_id":3,"usr":"usr_2_2_2_2_","depot_id":5},{"plan_id":4,"usr":"usr_1_1_1_1_1_1","depot_id":3},{"plan_id":5,"usr":"usr_1_1_1_1_1_1","depot_id":3}],"purchase_orders":[],"firstPlan":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4}]}
// app state:: {"plans":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4},{"plan_id":2,"usr":"usr_4_4_4_4_4_4","depot_id":2},{"plan_id":3,"usr":"usr_2_2_2_2_","depot_id":5},{"plan_id":4,"usr":"usr_1_1_1_1_1_1","depot_id":3},{"plan_id":5,"usr":"usr_1_1_1_1_1_1","depot_id":3}],"purchase_orders":[],"firstPlan":{}}
// component state: {"plans":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4},{"plan_id":2,"usr":"usr_4_4_4_4_4_4","depot_id":2},{"plan_id":3,"usr":"usr_2_2_2_2_","depot_id":5},{"plan_id":4,"usr":"usr_1_1_1_1_1_1","depot_id":3},{"plan_id":5,"usr":"usr_1_1_1_1_1_1","depot_id":3}],"purchase_orders":[],"firstPlan":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4}]}
// app state:: {"plans":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4},{"plan_id":2,"usr":"usr_4_4_4_4_4_4","depot_id":2},{"plan_id":3,"usr":"usr_2_2_2_2_","depot_id":5},{"plan_id":4,"usr":"usr_1_1_1_1_1_1","depot_id":3},{"plan_id":5,"usr":"usr_1_1_1_1_1_1","depot_id":3}],"purchase_orders":[],"firstPlan":[{"plan_id":1,"usr":"usr_4_4_4_4_4_4","depot_id":4}]}

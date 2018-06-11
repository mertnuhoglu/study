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
var xstream_1 = require("xstream");
function intent() {
    var requests$ = xstream_1["default"].from([
        {
            url: 'http://localhost:8080/rest/purchase_order?select=purchase_order_id,company_id,order_extid,company_extid',
            method: 'GET',
            headers: {
                "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
            },
            category: 'purchase_order'
        }
    ]);
    return requests$;
}
function model(httpSource) {
    var initialReducer$ = xstream_1["default"].of(function (prevState) { return ({
        firstPlan: {}
    }); });
    var firstPlanReducer$ = httpSource.select('purchase_order')
        .flatten()
        .map(function (value) { return function firstPlanReducer(prevState) {
        return __assign({}, prevState);
    }; });
    return xstream_1["default"].merge(firstPlanReducer$);
}
exports.subLens = {
    get: function (state) { return (__assign({}, state, { firstPlan: state.plans.filter(function (plan) { return plan.plan_id === 1; }) })); },
    set: function (state, childState) { return (__assign({}, state, { firstPlan: childState.firstPlan })); }
};
function main(sources) {
    return {
        state$: sources.onion.state$,
        onion: model(sources.HTTP),
        HTTP: intent()
    };
}
exports.main = main;

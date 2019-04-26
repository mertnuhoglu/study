"use strict";
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (Object.hasOwnProperty.call(mod, k)) result[k] = mod[k];
    result["default"] = mod;
    return result;
};
Object.defineProperty(exports, "__esModule", { value: true });
var R = __importStar(require("ramda"));
var as = [{ "pln_orl": [{ "order_line": { "purchase_order": { "purchase_order_id": 1 } } }] },
    { "pln_orl": [{ "order_line": { "purchase_order": { "purchase_order_id": 1 } } }] },
    { "pln_orl": [{ "order_line": { "purchase_order": { "purchase_order_id": 3 } } }] },
    { "pln_orl": [{ "order_line": { "purchase_order": { "purchase_order_id": 4 } } },
            { "order_line": { "purchase_order": { "purchase_order_id": 2 } } }] }];
var ys = R.map(function (a) { return a.pln_orl.map(function (b) { return b.order_line.purchase_order; }); }, as);
console.log(ys);

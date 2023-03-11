goog.provide('contrib.oklab');
contrib.oklab.deg__GT_rad = (function contrib$oklab$deg__GT_rad(alpha){
return ((alpha * Math.PI) / (180));
});
contrib.oklab.oklch__GT_oklab = (function contrib$oklab$oklch__GT_oklab(p__54424){
var vec__54425 = p__54424;
var l = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54425,(0),null);
var c = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54425,(1),null);
var h = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54425,(2),null);
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [l,(c * Math.cos(contrib.oklab.deg__GT_rad(h))),(c * Math.sin(contrib.oklab.deg__GT_rad(h)))], null);
});
contrib.oklab.oklab__GT_lrgb = (function contrib$oklab$oklab__GT_lrgb(p__54429){
var vec__54430 = p__54429;
var l = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54430,(0),null);
var a = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54430,(1),null);
var b = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54430,(2),null);
var L = (((l * 0.9999999984505198) + (0.39633779217376786 * a)) + (0.2158037580607588 * b));
var M = (((l * 1.0000000088817609) - (0.10556134232365635 * a)) - (0.06385417477170591 * b));
var S = (((l * 1.0000000546724108) - (0.08948418209496575 * a)) - (1.2914855378640917 * b));
var L__$1 = Math.pow(L,(3));
var M__$1 = Math.pow(M,(3));
var S__$1 = Math.pow(S,(3));
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(((4.076741661347994 * L__$1) - (3.307711590408193 * M__$1)) + (0.230969928729428 * S__$1)),(((-1.2684380040921763 * L__$1) + (2.6097574006633715 * M__$1)) - (0.3413193963102197 * S__$1)),(((-0.004196086541837188 * L__$1) - (0.7034186144594493 * M__$1)) + (1.7076147009309444 * S__$1))], null);
});
contrib.oklab.sign = (function contrib$oklab$sign(x){
if((x < (0))){
return (-1);
} else {
if((x > (0))){
return (1);
} else {
return (0);

}
}
});
contrib.oklab.lrgb__GT_rgb = (function contrib$oklab$lrgb__GT_rgb(p__54436){
var vec__54437 = p__54436;
var r = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54437,(0),null);
var g = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54437,(1),null);
var b = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54437,(2),null);
var f = (function (c){
var abs = Math.abs(c);
if((abs > 0.0031308)){
var s = (((contrib.oklab.sign(c) === (0)))?(1):contrib.oklab.sign(c));
return (s * ((1.055 * Math.pow(abs,((1) / 2.4))) - 0.055));
} else {
return (c * 12.92);
}
});
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [f(r),f(g),f(b)], null);
});
contrib.oklab.oklab__GT_rgb = (function contrib$oklab$oklab__GT_rgb(lab){
return contrib.oklab.lrgb__GT_rgb(contrib.oklab.oklab__GT_lrgb(lab));
});
contrib.oklab.oklch__GT_rgb = (function contrib$oklab$oklch__GT_rgb(lch){
return contrib.oklab.oklab__GT_rgb(contrib.oklab.oklch__GT_oklab(lch));
});

//# sourceMappingURL=contrib.oklab.js.map

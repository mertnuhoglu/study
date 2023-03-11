goog.provide('contrib.color');
contrib.color.PHI = 0.618033988749895;
contrib.color.SEED_ANGLE = ((125) / (360));
contrib.color.clamp = (function contrib$color$clamp(low,up,x){
var x__5133__auto__ = (function (){var x__5130__auto__ = low;
var y__5131__auto__ = x;
return ((x__5130__auto__ > y__5131__auto__) ? x__5130__auto__ : y__5131__auto__);
})();
var y__5134__auto__ = up;
return ((x__5133__auto__ < y__5134__auto__) ? x__5133__auto__ : y__5134__auto__);
});
contrib.color.rgbint = (function contrib$color$rgbint(x){
return contrib.color.clamp((0),(255),((x * (255)) | (0)));
});
contrib.color.hsluv__GT_rgb = (function contrib$color$hsluv__GT_rgb(p__54460){
var vec__54461 = p__54460;
var h = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54461,(0),null);
var s = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54461,(1),null);
var l = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54461,(2),null);
return cljs.core.mapv.cljs$core$IFn$_invoke$arity$2(contrib.color.rgbint,contrib.hsluv.hsluv__GT_rgb(new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [h,s,l], null)));
});
contrib.color.css_rgb_str = (function contrib$color$css_rgb_str(r,g,b){
return ["rgb(",cljs.core.str.cljs$core$IFn$_invoke$arity$1(r),",",cljs.core.str.cljs$core$IFn$_invoke$arity$1(g),",",cljs.core.str.cljs$core$IFn$_invoke$arity$1(b),")"].join('');
});
/**
 * Hash a value into an harmonious color. Contrast is consistent. Plays well with text and colored backgrounds.
 *   https://www.hsluv.org/
 */
contrib.color.color = (function contrib$color$color(var_args){
var G__54465 = arguments.length;
switch (G__54465) {
case 1:
return contrib.color.color.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 4:
return contrib.color.color.cljs$core$IFn$_invoke$arity$4((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),(arguments[(3)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

(contrib.color.color.cljs$core$IFn$_invoke$arity$1 = (function (x){
return contrib.color.color.cljs$core$IFn$_invoke$arity$4(x,contrib.color.SEED_ANGLE,(50),(70));
}));

(contrib.color.color.cljs$core$IFn$_invoke$arity$4 = (function (x,seed_angle,saturation,lightness){
return cljs.core.apply.cljs$core$IFn$_invoke$arity$2(contrib.color.css_rgb_str,(((x == null))?contrib.color.hsluv__GT_rgb(new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(0),(0),(80)], null)):contrib.color.hsluv__GT_rgb(new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [((360) * cljs.core.mod((seed_angle + (cljs.core.hash(x) * contrib.color.PHI)),(1))),saturation,lightness], null))));
}));

(contrib.color.color.cljs$lang$maxFixedArity = 4);

contrib.color.hsl__GT_rgb_STAR_ = (function contrib$color$hsl__GT_rgb_STAR_(p__54479){
var vec__54480 = p__54479;
var H = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54480,(0),null);
var S = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54480,(1),null);
var L = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54480,(2),null);
var S__$1 = (S / (100));
var L__$1 = (L / (100));
var C = (((1) - Math.abs(((L__$1 * (2)) - (1)))) * S__$1);
var X = (C * ((1) - Math.abs((cljs.core.mod((H / (60)),(2)) - (1)))));
var m = (L__$1 - (C / (2)));
var vec__54483 = (((H < (60)))?new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [C,X,(0)], null):(((H < (120)))?new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [X,C,(0)], null):(((H < (180)))?new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(0),C,X], null):(((H < (240)))?new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(0),X,C], null):(((H < (300)))?new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [X,(0),C], null):(((H < (360)))?new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [C,(0),X], null):null))))));
var R_SINGLEQUOTE_ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54483,(0),null);
var G_SINGLEQUOTE_ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54483,(1),null);
var B_SINGLEQUOTE_ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54483,(2),null);
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [((R_SINGLEQUOTE_ + m) * (255)),((G_SINGLEQUOTE_ + m) * (255)),((B_SINGLEQUOTE_ + m) * (255))], null);
});
contrib.color.hsl__GT_rgb = (function contrib$color$hsl__GT_rgb(hsl){
var vec__54486 = contrib.color.hsl__GT_rgb_STAR_(hsl);
var R = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54486,(0),null);
var G = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54486,(1),null);
var B = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54486,(2),null);
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [Math.round(R),Math.round(G),Math.round(B)], null);
});
contrib.color.oklch__GT_rgb = (function contrib$color$oklch__GT_rgb(p__54498){
var vec__54499 = p__54498;
var l = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54499,(0),null);
var c = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54499,(1),null);
var h = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54499,(2),null);
return cljs.core.mapv.cljs$core$IFn$_invoke$arity$2(contrib.color.rgbint,contrib.oklab.oklch__GT_rgb(new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(l / (100)),c,h], null)));
});

//# sourceMappingURL=contrib.color.js.map

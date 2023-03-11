goog.provide('contrib.hsluv');
contrib.hsluv.MIN_F = 1.0E-8;
contrib.hsluv.MAX_F = 99.9999999;
contrib.hsluv.M = new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [3.240969941904521,-1.537383177570093,-0.498610760293], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [-0.96924363628087,1.87596750150772,0.041555057407175], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [0.055630079696993,-0.20397695888897,1.056971514242878], null)], null);
contrib.hsluv.REF_Y = 1.0;
contrib.hsluv.REF_U = 0.19783000664283;
contrib.hsluv.REF_V = 0.46831999493879;
contrib.hsluv.KAPPA = 903.2962962;
contrib.hsluv.EPSILON = 0.0088564516;
contrib.hsluv.dot_product = (function contrib$hsluv$dot_product(a0,a1,a2,b0,b1,b2){
return (((a0 * b0) + (a1 * b1)) + (a2 * b2));
});
contrib.hsluv.from_linear = (function contrib$hsluv$from_linear(c){
if((c <= 0.0031308)){
return (12.92 * c);
} else {
return ((1.055 * Math.pow(c,((1) / 2.4))) - 0.055);
}
});
contrib.hsluv.lch__GT_luv = (function contrib$hsluv$lch__GT_luv(lch){
var l = (lch[((0) | (0))]);
var c = (lch[((1) | (0))]);
var h = (lch[((2) | (0))]);
var h_rad = ((h / (180)) * Math.PI);
var G__54396 = lch;
(G__54396[((0) | (0))] = l);

(G__54396[((1) | (0))] = (Math.cos(h_rad) * c));

(G__54396[((2) | (0))] = (Math.sin(h_rad) * c));

return G__54396;
});
var vec__54410_54466 = new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [3.240969941904521,-1.537383177570093,-0.498610760293], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [-0.96924363628087,1.87596750150772,0.041555057407175], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [0.055630079696993,-0.20397695888897,1.056971514242878], null)], null);
var vec__54413_54467 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54410_54466,(0),null);
var m1a_54468 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54413_54467,(0),null);
var m1b_54469 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54413_54467,(1),null);
var m1c_54470 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54413_54467,(2),null);
var vec__54416_54471 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54410_54466,(1),null);
var m2a_54472 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54416_54471,(0),null);
var m2b_54473 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54416_54471,(1),null);
var m2c_54474 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54416_54471,(2),null);
var vec__54419_54475 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54410_54466,(2),null);
var m3a_54476 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54419_54475,(0),null);
var m3b_54477 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54419_54475,(1),null);
var m3c_54478 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54419_54475,(2),null);
contrib.hsluv.xyz__GT_rgb = (function contrib$hsluv$xyz__GT_rgb(xyz){
var x = (xyz[((0) | (0))]);
var y = (xyz[((1) | (0))]);
var z = (xyz[((2) | (0))]);
var G__54428 = xyz;
(G__54428[((0) | (0))] = contrib.hsluv.from_linear(contrib.hsluv.dot_product(m1a_54468,m1b_54469,m1c_54470,x,y,z)));

(G__54428[((1) | (0))] = contrib.hsluv.from_linear(contrib.hsluv.dot_product(m2a_54472,m2b_54473,m2c_54474,x,y,z)));

(G__54428[((2) | (0))] = contrib.hsluv.from_linear(contrib.hsluv.dot_product(m3a_54476,m3b_54477,m3c_54478,x,y,z)));

return G__54428;
});
contrib.hsluv.l_to_y = (function contrib$hsluv$l_to_y(L){
if((L <= (8))){
return ((1.0 * L) / 903.2962962);
} else {
return (1.0 * Math.pow(((L + (16)) / (116)),(3)));
}
});
contrib.hsluv.luv__GT_xyz = (function contrib$hsluv$luv__GT_xyz(luv){
var l = (luv[((0) | (0))]);
var u = (luv[((1) | (0))]);
var v = (luv[((2) | (0))]);
if((l === (0))){
var G__54433 = luv;
(G__54433[((0) | (0))] = 0.0);

(G__54433[((1) | (0))] = 0.0);

(G__54433[((2) | (0))] = 0.0);

return G__54433;
} else {
var varU = ((u / ((13) * l)) + 0.19783000664283);
var varV = ((v / ((13) * l)) + 0.46831999493879);
var y = contrib.hsluv.l_to_y(l);
var x = ((0) - ((((9) * y) * varU) / (((varU - (4)) * varV) - (varU * varV))));
var z = (((((9) * y) - (((15) * varV) * y)) - (varV * x)) / ((3) * varV));
var G__54435 = luv;
(G__54435[((0) | (0))] = x);

(G__54435[((1) | (0))] = y);

(G__54435[((2) | (0))] = z);

return G__54435;
}
});
var vec__54440_54528 = new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [3.240969941904521,-1.537383177570093,-0.498610760293], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [-0.96924363628087,1.87596750150772,0.041555057407175], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [0.055630079696993,-0.20397695888897,1.056971514242878], null)], null);
var vec__54443_54529 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54440_54528,(0),null);
var mr0_54530 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54443_54529,(0),null);
var mr1_54531 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54443_54529,(1),null);
var mr2_54532 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54443_54529,(2),null);
var vec__54446_54533 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54440_54528,(1),null);
var mg0_54534 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54446_54533,(0),null);
var mg1_54535 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54446_54533,(1),null);
var mg2_54536 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54446_54533,(2),null);
var vec__54449_54537 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54440_54528,(2),null);
var mb0_54538 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54449_54537,(0),null);
var mb1_54539 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54449_54537,(1),null);
var mb2_54540 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54449_54537,(2),null);
var ra_54541 = (((284517) * mr0_54530) - ((94839) * mr2_54532));
var rb_54542 = ((((838422) * mr2_54532) + ((769860) * mr1_54531)) + ((731718) * mr0_54530));
var rc_54543 = (((632260) * mr2_54532) - ((126452) * mr1_54531));
var ga_54544 = (((284517) * mg0_54534) - ((94839) * mg2_54536));
var gb_54545 = ((((838422) * mg2_54536) + ((769860) * mg1_54535)) + ((731718) * mg0_54534));
var gc_54546 = (((632260) * mg2_54536) - ((126452) * mg1_54535));
var ba_54547 = (((284517) * mb0_54538) - ((94839) * mb2_54540));
var bb_54548 = ((((838422) * mb2_54540) + ((769860) * mb1_54539)) + ((731718) * mb0_54538));
var bc_54549 = (((632260) * mb2_54540) - ((126452) * mb1_54539));
contrib.hsluv.bounding_lines = (function contrib$hsluv$bounding_lines(l){
var sub1 = (Math.pow((l + (16)),(3)) / (1560896));
var sub2 = (((sub1 > 0.0088564516))?sub1:(l / 903.2962962));
var s1r = (sub2 * ra_54541);
var s2r = (sub2 * rb_54542);
var s3r = (sub2 * rc_54543);
var s1g = (sub2 * ga_54544);
var s2g = (sub2 * gb_54545);
var s3g = (sub2 * gc_54546);
var s1b = (sub2 * ba_54547);
var s2b = (sub2 * bb_54548);
var s3b = (sub2 * bc_54549);
var r0s = (s1r / s3r);
var r0i = ((s2r * l) / s3r);
var r1s = (s1r / (s3r + (126452)));
var r1i = (((s2r - (769860)) * l) / (s3r + (126452)));
var g0s = (s1g / s3g);
var g0i = ((s2g * l) / s3g);
var g1s = (s1g / (s3g + (126452)));
var g1i = (((s2g - (769860)) * l) / (s3g + (126452)));
var b0s = (s1b / s3b);
var b0i = ((s2b * l) / s3b);
var b1s = (s1b / (s3b + (126452)));
var b1i = (((s2b - (769860)) * l) / (s3b + (126452)));
return new cljs.core.PersistentVector(null, 12, 5, cljs.core.PersistentVector.EMPTY_NODE, [r0s,r0i,r1s,r1i,g0s,g0i,g1s,g1i,b0s,b0i,b1s,b1i], null);
});
contrib.hsluv.distance_from_origin_angle = (function contrib$hsluv$distance_from_origin_angle(slope,intercept,angle){
var d = (intercept / (Math.sin(angle) - (slope * Math.cos(angle))));
if((d < (0))){
return Infinity;
} else {
return d;
}
});
contrib.hsluv.max_chroma_hsluv = (function contrib$hsluv$max_chroma_hsluv(l,h){
var hue_rad = (((h / (360)) * Math.PI) * (2));
var vec__54453 = contrib.hsluv.bounding_lines(l);
var r0s = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54453,(0),null);
var r0i = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54453,(1),null);
var r1s = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54453,(2),null);
var r1i = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54453,(3),null);
var g0s = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54453,(4),null);
var g0i = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54453,(5),null);
var g1s = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54453,(6),null);
var g1i = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54453,(7),null);
var b0s = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54453,(8),null);
var b0i = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54453,(9),null);
var b1s = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54453,(10),null);
var b1i = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54453,(11),null);
var x__5133__auto__ = (function (){var x__5133__auto__ = (function (){var x__5133__auto__ = (function (){var x__5133__auto__ = (function (){var x__5133__auto__ = contrib.hsluv.distance_from_origin_angle(r0s,r0i,hue_rad);
var y__5134__auto__ = contrib.hsluv.distance_from_origin_angle(r1s,r1i,hue_rad);
return ((x__5133__auto__ < y__5134__auto__) ? x__5133__auto__ : y__5134__auto__);
})();
var y__5134__auto__ = contrib.hsluv.distance_from_origin_angle(g0s,g0i,hue_rad);
return ((x__5133__auto__ < y__5134__auto__) ? x__5133__auto__ : y__5134__auto__);
})();
var y__5134__auto__ = contrib.hsluv.distance_from_origin_angle(g1s,g1i,hue_rad);
return ((x__5133__auto__ < y__5134__auto__) ? x__5133__auto__ : y__5134__auto__);
})();
var y__5134__auto__ = contrib.hsluv.distance_from_origin_angle(b0s,b0i,hue_rad);
return ((x__5133__auto__ < y__5134__auto__) ? x__5133__auto__ : y__5134__auto__);
})();
var y__5134__auto__ = contrib.hsluv.distance_from_origin_angle(b1s,b1i,hue_rad);
return ((x__5133__auto__ < y__5134__auto__) ? x__5133__auto__ : y__5134__auto__);
});
contrib.hsluv.hsluv__GT_lch = (function contrib$hsluv$hsluv__GT_lch(hsl){
var h = (hsl[((0) | (0))]);
var s = (hsl[((1) | (0))]);
var l = (hsl[((2) | (0))]);
if((l > 99.9999999)){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(100),(0),h], null);
} else {
if((l < 1.0E-8)){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(0),(0),h], null);
} else {
var G__54459 = hsl;
(G__54459[((0) | (0))] = l);

(G__54459[((1) | (0))] = ((contrib.hsluv.max_chroma_hsluv(l,h) / (100)) * s));

(G__54459[((2) | (0))] = h);

return G__54459;
}
}
});
/**
 * `hsluv->rgb` convert HSLuv components to RGB
 */
contrib.hsluv.hsluv__GT_rgb = (function contrib$hsluv$hsluv__GT_rgb(hsl){
return cljs.core.vec(contrib.hsluv.xyz__GT_rgb(contrib.hsluv.luv__GT_xyz(contrib.hsluv.lch__GT_luv(contrib.hsluv.hsluv__GT_lch(cljs.core.double_array.cljs$core$IFn$_invoke$arity$1(hsl))))));
});

//# sourceMappingURL=contrib.hsluv.js.map

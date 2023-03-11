goog.provide('contrib.char$');
contrib.char$.char_upper = (function contrib$char$$char_upper(c){
return clojure.string.upper_case(c);
});
contrib.char$.char_lower = (function contrib$char$$char_lower(c){
return clojure.string.lower_case(c);
});
contrib.char$.char_code = (function contrib$char$$char_code(c){
if(cljs.core.truth_(c)){
} else {
throw (new Error("Assert failed: c"));
}

return c.charCodeAt();
});
contrib.char$.dec__GT_hex = cljs.core.PersistentHashMap.fromArrays([(0),(7),(1),(4),(15),(13),(6),(3),(12),(2),(11),(9),(5),(14),(10),(8)],["0","7","1","4","F","D","6","3","C","2","B","9","5","E","A","8"]);
contrib.char$.hex__GT_dec = cljs.core.merge.cljs$core$IFn$_invoke$arity$variadic(cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([clojure.set.map_invert(contrib.char$.dec__GT_hex),clojure.set.map_invert(cljs.core.reduce_kv((function (m,k,v){
return cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(m,k,contrib.char$.char_lower(v));
}),cljs.core.PersistentArrayMap.EMPTY,contrib.char$.dec__GT_hex))], 0));
contrib.char$.char__GT_hex_str = (function contrib$char$$char__GT_hex_str(c){
if(cljs.core.truth_(c)){
} else {
throw (new Error("Assert failed: c"));
}

return clojure.string.join.cljs$core$IFn$_invoke$arity$1(cljs.core.map.cljs$core$IFn$_invoke$arity$2(contrib.char$.dec__GT_hex,cljs.core.juxt.cljs$core$IFn$_invoke$arity$2((function (p1__37906_SHARP_){
return cljs.core.quot(p1__37906_SHARP_,(16));
}),(function (p1__37907_SHARP_){
return cljs.core.mod(p1__37907_SHARP_,(16));
}))(contrib.char$.char_code(c))));
});
contrib.char$.hex_str__GT_char = (function contrib$char$$hex_str__GT_char(s){
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2((2),cljs.core.count(s))){
} else {
throw (new Error("Assert failed: (= 2 (count s))"));
}

var vec__37908 = cljs.core.map.cljs$core$IFn$_invoke$arity$2(contrib.char$.hex__GT_dec,s);
var a = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__37908,(0),null);
var b = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__37908,(1),null);
return cljs.core.char$((((16) * (a | (0))) + (b | (0))));
});

//# sourceMappingURL=contrib.char$.js.map

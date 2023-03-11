goog.provide('contrib.walk');
contrib.walk.has_meta_QMARK_ = (function contrib$walk$has_meta_QMARK_(o){
if((!((o == null)))){
if((((o.cljs$lang$protocol_mask$partition0$ & (131072))) || ((cljs.core.PROTOCOL_SENTINEL === o.cljs$core$IMeta$)))){
return true;
} else {
if((!o.cljs$lang$protocol_mask$partition0$)){
return cljs.core.native_satisfies_QMARK_(cljs.core.IMeta,o);
} else {
return false;
}
}
} else {
return cljs.core.native_satisfies_QMARK_(cljs.core.IMeta,o);
}
});
contrib.walk.walk = (function contrib$walk$walk(inner,outer,form){
if(cljs.core.list_QMARK_(form)){
var G__57254 = form;
var G__57255 = cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.list,cljs.core.map.cljs$core$IFn$_invoke$arity$2(inner,form));
return (outer.cljs$core$IFn$_invoke$arity$2 ? outer.cljs$core$IFn$_invoke$arity$2(G__57254,G__57255) : outer.call(null,G__57254,G__57255));
} else {
if(cljs.core.map_entry_QMARK_(form)){
var G__57256 = form;
var G__57257 = cljs.core.first(cljs.core.PersistentArrayMap.createAsIfByAssoc([(function (){var G__57258 = cljs.core.key(form);
return (inner.cljs$core$IFn$_invoke$arity$1 ? inner.cljs$core$IFn$_invoke$arity$1(G__57258) : inner.call(null,G__57258));
})(),(function (){var G__57259 = cljs.core.val(form);
return (inner.cljs$core$IFn$_invoke$arity$1 ? inner.cljs$core$IFn$_invoke$arity$1(G__57259) : inner.call(null,G__57259));
})()]));
return (outer.cljs$core$IFn$_invoke$arity$2 ? outer.cljs$core$IFn$_invoke$arity$2(G__57256,G__57257) : outer.call(null,G__57256,G__57257));
} else {
if(cljs.core.seq_QMARK_(form)){
var G__57260 = form;
var G__57261 = cljs.core.doall.cljs$core$IFn$_invoke$arity$1(cljs.core.map.cljs$core$IFn$_invoke$arity$2(inner,form));
return (outer.cljs$core$IFn$_invoke$arity$2 ? outer.cljs$core$IFn$_invoke$arity$2(G__57260,G__57261) : outer.call(null,G__57260,G__57261));
} else {
if(cljs.core.record_QMARK_(form)){
var G__57262 = form;
var G__57263 = cljs.core.reduce.cljs$core$IFn$_invoke$arity$3((function (r,x){
return cljs.core.conj.cljs$core$IFn$_invoke$arity$2(r,(inner.cljs$core$IFn$_invoke$arity$1 ? inner.cljs$core$IFn$_invoke$arity$1(x) : inner.call(null,x)));
}),form,form);
return (outer.cljs$core$IFn$_invoke$arity$2 ? outer.cljs$core$IFn$_invoke$arity$2(G__57262,G__57263) : outer.call(null,G__57262,G__57263));
} else {
if(cljs.core.coll_QMARK_(form)){
var G__57264 = form;
var G__57265 = cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.empty(form),cljs.core.map.cljs$core$IFn$_invoke$arity$2(inner,form));
return (outer.cljs$core$IFn$_invoke$arity$2 ? outer.cljs$core$IFn$_invoke$arity$2(G__57264,G__57265) : outer.call(null,G__57264,G__57265));
} else {
return (outer.cljs$core$IFn$_invoke$arity$2 ? outer.cljs$core$IFn$_invoke$arity$2(form,form) : outer.call(null,form,form));

}
}
}
}
}
});
contrib.walk.forward_metas = (function contrib$walk$forward_metas(form,form_SINGLEQUOTE_){
if(contrib.walk.has_meta_QMARK_(form_SINGLEQUOTE_)){
return cljs.core.with_meta(form_SINGLEQUOTE_,cljs.core.merge.cljs$core$IFn$_invoke$arity$variadic(cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([cljs.core.meta(form),cljs.core.meta(form_SINGLEQUOTE_)], 0)));
} else {
return form_SINGLEQUOTE_;
}
});
contrib.walk.prewalk = (function contrib$walk$prewalk(f,form){
if(cljs.core.reduced_QMARK_(form)){
return cljs.core.unreduced(form);
} else {
return cljs.core.unreduced(contrib.walk.walk(cljs.core.partial.cljs$core$IFn$_invoke$arity$2(contrib.walk.prewalk,f),contrib.walk.forward_metas,(f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(form) : f.call(null,form))));
}
});
contrib.walk.postwalk = (function contrib$walk$postwalk(f,form){
if(cljs.core.reduced_QMARK_(form)){
return cljs.core.unreduced(form);
} else {
return cljs.core.unreduced(contrib.walk.walk(cljs.core.partial.cljs$core$IFn$_invoke$arity$2(contrib.walk.postwalk,f),(function (form__$1,form_SINGLEQUOTE_){
return contrib.walk.forward_metas(form__$1,(function (){var G__57269 = contrib.walk.forward_metas(form__$1,form_SINGLEQUOTE_);
return (f.cljs$core$IFn$_invoke$arity$1 ? f.cljs$core$IFn$_invoke$arity$1(G__57269) : f.call(null,G__57269));
})());
}),form));
}
});

//# sourceMappingURL=contrib.walk.js.map

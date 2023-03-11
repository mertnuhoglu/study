goog.provide('hyperfiddle.api');
goog.scope(function(){
  hyperfiddle.api.goog$module$goog$math$Long = goog.module.get('goog.math.Long');
});
cljs.spec.alpha.def_impl(new cljs.core.Keyword("hyperfiddle.api","ref?","hyperfiddle.api/ref?",1733629572),new cljs.core.Symbol("cljs.core","any?","cljs.core/any?",-2068111842,null),cljs.core.any_QMARK_);
hyperfiddle.api._read_edn_str_default = cljs.core.partial.cljs$core$IFn$_invoke$arity$2(clojure.edn.read_string,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"readers","readers",-2118263030),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Symbol("goog.math","Long","goog.math/Long",124568471,null),hyperfiddle.api.goog$module$goog$math$Long.fromString], null)], null));
hyperfiddle.api.entity = (function hyperfiddle$api$entity(ctx){
var or__5045__auto__ = new cljs.core.Keyword("hyperfiddle.api","entity","hyperfiddle.api/entity",-260541520).cljs$core$IFn$_invoke$arity$1(ctx);
if(cljs.core.truth_(or__5045__auto__)){
return or__5045__auto__;
} else {
return new cljs.core.Keyword("hyperfiddle.api","entity","hyperfiddle.api/entity",-260541520).cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword("hyperfiddle.api","parent","hyperfiddle.api/parent",-671161879).cljs$core$IFn$_invoke$arity$1(ctx));
}
});
hyperfiddle.api.attribute = (function hyperfiddle$api$attribute(ctx){
var or__5045__auto__ = new cljs.core.Keyword("hyperfiddle.api","attribute","hyperfiddle.api/attribute",818306709).cljs$core$IFn$_invoke$arity$1(ctx);
if(cljs.core.truth_(or__5045__auto__)){
return or__5045__auto__;
} else {
return new cljs.core.Keyword("hyperfiddle.api","attribute","hyperfiddle.api/attribute",818306709).cljs$core$IFn$_invoke$arity$1(new cljs.core.Keyword("hyperfiddle.api","parent","hyperfiddle.api/parent",-671161879).cljs$core$IFn$_invoke$arity$1(ctx));
}
});
if((typeof hyperfiddle !== 'undefined') && (typeof hyperfiddle.api !== 'undefined') && (typeof hyperfiddle.api.tx_meta !== 'undefined')){
} else {
hyperfiddle.api.tx_meta = (function (){var method_table__5642__auto__ = cljs.core.atom.cljs$core$IFn$_invoke$arity$1(cljs.core.PersistentArrayMap.EMPTY);
var prefer_table__5643__auto__ = cljs.core.atom.cljs$core$IFn$_invoke$arity$1(cljs.core.PersistentArrayMap.EMPTY);
var method_cache__5644__auto__ = cljs.core.atom.cljs$core$IFn$_invoke$arity$1(cljs.core.PersistentArrayMap.EMPTY);
var cached_hierarchy__5645__auto__ = cljs.core.atom.cljs$core$IFn$_invoke$arity$1(cljs.core.PersistentArrayMap.EMPTY);
var hierarchy__5646__auto__ = cljs.core.get.cljs$core$IFn$_invoke$arity$3(cljs.core.PersistentArrayMap.EMPTY,new cljs.core.Keyword(null,"hierarchy","hierarchy",-1053470341),(function (){var fexpr__54596 = cljs.core.get_global_hierarchy;
return (fexpr__54596.cljs$core$IFn$_invoke$arity$0 ? fexpr__54596.cljs$core$IFn$_invoke$arity$0() : fexpr__54596.call(null));
})());
return (new cljs.core.MultiFn(cljs.core.symbol.cljs$core$IFn$_invoke$arity$2("hyperfiddle.api","tx-meta"),(function (schema,tx){
if(cljs.core.map_QMARK_(tx)){
return new cljs.core.Keyword("hyperfiddle.api","map","hyperfiddle.api/map",1285999625);
} else {
return cljs.core.first(tx);
}
}),new cljs.core.Keyword(null,"default","default",-1987822328),hierarchy__5646__auto__,method_table__5642__auto__,prefer_table__5643__auto__,method_cache__5644__auto__,cached_hierarchy__5645__auto__));
})();
}
cljs.spec.alpha.def_impl(new cljs.core.Keyword("hyperfiddle.api","tx-cardinality","hyperfiddle.api/tx-cardinality",1098272925),cljs.core.list(new cljs.core.Symbol("cljs.spec.alpha","or","cljs.spec.alpha/or",-831679639,null),new cljs.core.Keyword(null,"one","one",935007904),new cljs.core.Keyword(null,"many","many",1092119164)),cljs.spec.alpha.or_spec_impl(new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"one","one",935007904)], null),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"many","many",1092119164)], null),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"many","many",1092119164)], null),null));
cljs.spec.alpha.def_impl(new cljs.core.Keyword("hyperfiddle.api","tx-identifier","hyperfiddle.api/tx-identifier",685747368),new cljs.core.Symbol("cljs.core","map?","cljs.core/map?",-1390345523,null),cljs.core.map_QMARK_);
cljs.spec.alpha.def_impl(new cljs.core.Keyword("hyperfiddle.api","tx-inverse","hyperfiddle.api/tx-inverse",223152403),new cljs.core.Symbol("cljs.core","fn?","cljs.core/fn?",71876239,null),cljs.core.fn_QMARK_);
cljs.spec.alpha.def_impl(new cljs.core.Keyword("hyperfiddle.api","tx-special","hyperfiddle.api/tx-special",376372370),new cljs.core.Symbol("cljs.core","fn?","cljs.core/fn?",71876239,null),cljs.core.fn_QMARK_);
cljs.spec.alpha.def_impl(new cljs.core.Keyword("hyperfiddle.api","transaction-meta","hyperfiddle.api/transaction-meta",141673617),cljs.core.list(new cljs.core.Symbol("cljs.spec.alpha","keys","cljs.spec.alpha/keys",1109346032,null),new cljs.core.Keyword(null,"req","req",-326448303),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("hyperfiddle.api","tx-identifier","hyperfiddle.api/tx-identifier",685747368)], null),new cljs.core.Keyword(null,"opt","opt",-794706369),new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("hyperfiddle.api","tx-cardinality","hyperfiddle.api/tx-cardinality",1098272925),new cljs.core.Keyword("hyperfiddle.api","tx-inverse","hyperfiddle.api/tx-inverse",223152403),new cljs.core.Keyword("hyperfiddle.api","tx-special","hyperfiddle.api/tx-special",376372370),new cljs.core.Keyword("hyperfiddle.api","tx-conflicting?","hyperfiddle.api/tx-conflicting?",-995878165)], null)),cljs.spec.alpha.map_spec_impl(cljs.core.PersistentHashMap.fromArrays([new cljs.core.Keyword(null,"req-un","req-un",1074571008),new cljs.core.Keyword(null,"opt-un","opt-un",883442496),new cljs.core.Keyword(null,"gfn","gfn",791517474),new cljs.core.Keyword(null,"pred-exprs","pred-exprs",1792271395),new cljs.core.Keyword(null,"keys-pred","keys-pred",858984739),new cljs.core.Keyword(null,"opt-keys","opt-keys",1262688261),new cljs.core.Keyword(null,"req-specs","req-specs",553962313),new cljs.core.Keyword(null,"req","req",-326448303),new cljs.core.Keyword(null,"req-keys","req-keys",514319221),new cljs.core.Keyword(null,"opt-specs","opt-specs",-384905450),new cljs.core.Keyword(null,"pred-forms","pred-forms",172611832),new cljs.core.Keyword(null,"opt","opt",-794706369)],[null,null,null,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(function (G__54598){
return cljs.core.map_QMARK_(G__54598);
}),(function (G__54598){
return cljs.core.contains_QMARK_(G__54598,new cljs.core.Keyword("hyperfiddle.api","tx-identifier","hyperfiddle.api/tx-identifier",685747368));
})], null),(function (G__54598){
return ((cljs.core.map_QMARK_(G__54598)) && (cljs.core.contains_QMARK_(G__54598,new cljs.core.Keyword("hyperfiddle.api","tx-identifier","hyperfiddle.api/tx-identifier",685747368))));
}),new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("hyperfiddle.api","tx-cardinality","hyperfiddle.api/tx-cardinality",1098272925),new cljs.core.Keyword("hyperfiddle.api","tx-inverse","hyperfiddle.api/tx-inverse",223152403),new cljs.core.Keyword("hyperfiddle.api","tx-special","hyperfiddle.api/tx-special",376372370),new cljs.core.Keyword("hyperfiddle.api","tx-conflicting?","hyperfiddle.api/tx-conflicting?",-995878165)], null),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("hyperfiddle.api","tx-identifier","hyperfiddle.api/tx-identifier",685747368)], null),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("hyperfiddle.api","tx-identifier","hyperfiddle.api/tx-identifier",685747368)], null),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("hyperfiddle.api","tx-identifier","hyperfiddle.api/tx-identifier",685747368)], null),new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("hyperfiddle.api","tx-cardinality","hyperfiddle.api/tx-cardinality",1098272925),new cljs.core.Keyword("hyperfiddle.api","tx-inverse","hyperfiddle.api/tx-inverse",223152403),new cljs.core.Keyword("hyperfiddle.api","tx-special","hyperfiddle.api/tx-special",376372370),new cljs.core.Keyword("hyperfiddle.api","tx-conflicting?","hyperfiddle.api/tx-conflicting?",-995878165)], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.list(new cljs.core.Symbol("cljs.core","fn","cljs.core/fn",-1065745098,null),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol(null,"%","%",-950237169,null)], null),cljs.core.list(new cljs.core.Symbol("cljs.core","map?","cljs.core/map?",-1390345523,null),new cljs.core.Symbol(null,"%","%",-950237169,null))),cljs.core.list(new cljs.core.Symbol("cljs.core","fn","cljs.core/fn",-1065745098,null),new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol(null,"%","%",-950237169,null)], null),cljs.core.list(new cljs.core.Symbol("cljs.core","contains?","cljs.core/contains?",-976526835,null),new cljs.core.Symbol(null,"%","%",-950237169,null),new cljs.core.Keyword("hyperfiddle.api","tx-identifier","hyperfiddle.api/tx-identifier",685747368)))], null),new cljs.core.PersistentVector(null, 4, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword("hyperfiddle.api","tx-cardinality","hyperfiddle.api/tx-cardinality",1098272925),new cljs.core.Keyword("hyperfiddle.api","tx-inverse","hyperfiddle.api/tx-inverse",223152403),new cljs.core.Keyword("hyperfiddle.api","tx-special","hyperfiddle.api/tx-special",376372370),new cljs.core.Keyword("hyperfiddle.api","tx-conflicting?","hyperfiddle.api/tx-conflicting?",-995878165)], null)])));
cljs.spec.alpha.def_impl(new cljs.core.Symbol("hyperfiddle.api","tx-meta","hyperfiddle.api/tx-meta",-1291036779,null),cljs.core.list(new cljs.core.Symbol("cljs.spec.alpha","fspec","cljs.spec.alpha/fspec",-1289128341,null),new cljs.core.Keyword(null,"ret","ret",-468222814),new cljs.core.Keyword("hyperfiddle.api","transaction-meta","hyperfiddle.api/transaction-meta",141673617)),cljs.spec.alpha.fspec_impl(null,null,cljs.spec.alpha.spec_impl.cljs$core$IFn$_invoke$arity$4(new cljs.core.Keyword("hyperfiddle.api","transaction-meta","hyperfiddle.api/transaction-meta",141673617),new cljs.core.Keyword("hyperfiddle.api","transaction-meta","hyperfiddle.api/transaction-meta",141673617),null,null),new cljs.core.Keyword("hyperfiddle.api","transaction-meta","hyperfiddle.api/transaction-meta",141673617),null,null,null));
/**
 * Bound to the HTTP request of the page in which the current Electric program is running.
 */
hyperfiddle.api._STAR_http_request_STAR_ = null;

//# sourceMappingURL=hyperfiddle.api.js.map

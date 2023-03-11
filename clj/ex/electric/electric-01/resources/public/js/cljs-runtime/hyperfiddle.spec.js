goog.provide('hyperfiddle.spec');
hyperfiddle.spec.spec_type = (function hyperfiddle$spec$spec_type(spec){
var desc = cljs.spec.alpha.describe(spec);
if((desc instanceof cljs.core.Symbol)){
return new cljs.core.Keyword("hyperfiddle.spec","predicate","hyperfiddle.spec/predicate",270440669);
} else {
if(cljs.core.seq_QMARK_(desc)){
return cljs.core.keyword.cljs$core$IFn$_invoke$arity$2(cljs.core.namespace(new cljs.core.Keyword("hyperfiddle.spec","_","hyperfiddle.spec/_",-1633783864)),cljs.core.name(cljs.core.first(desc)));
} else {
throw (new Error(["Assert failed: ",["Unknown spec description ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(desc)].join(''),"\n","false"].join('')));


}
}
});
hyperfiddle.spec.renamespace = (function hyperfiddle$spec$renamespace(mapping,ident){
if(cljs.core.ident_QMARK_(ident)){
} else {
throw (new Error("Assert failed: (ident? ident)"));
}

if(cljs.core.qualified_ident_QMARK_(ident)){
var ns = cljs.core.namespace(ident);
var ns__$1 = (mapping.cljs$core$IFn$_invoke$arity$2 ? mapping.cljs$core$IFn$_invoke$arity$2(ns,ns) : mapping.call(null,ns,ns));
if((ident instanceof cljs.core.Keyword)){
return cljs.core.keyword.cljs$core$IFn$_invoke$arity$2(ns__$1,cljs.core.name(ident));
} else {
return cljs.core.symbol.cljs$core$IFn$_invoke$arity$2(ns__$1,cljs.core.name(ident));
}
} else {
return ident;
}
});
hyperfiddle.spec.form = (function hyperfiddle$spec$form(spec){
if(cljs.core.truth_(spec)){
return contrib.walk.prewalk((function (x){
if(cljs.core.ident_QMARK_(x)){
return hyperfiddle.spec.renamespace(new cljs.core.PersistentArrayMap(null, 2, ["cljs.spec.alpha","clojure.spec.alpha","cljs.core","clojure.core"], null),x);
} else {
return x;
}
}),cljs.spec.alpha.form(spec));
} else {
return null;
}
});

hyperfiddle.spec.extend_spec = (function hyperfiddle$spec$extend_spec(spec){
if(cljs.core.truth_(spec)){
return cljs.core.vary_meta.cljs$core$IFn$_invoke$arity$4(spec,cljs.core.assoc,new cljs.core.Symbol("clojure.core.protocols","datafy","clojure.core.protocols/datafy",707534751,null),hyperfiddle.spec.datafy_STAR_);
} else {
return null;
}
});
/**
 * Like s/get-spec, but resolve aliases
 */
hyperfiddle.spec.get_spec = (function hyperfiddle$spec$get_spec(ident){
var temp__5804__auto__ = cljs.spec.alpha.get_spec(ident);
if(cljs.core.truth_(temp__5804__auto__)){
var s = temp__5804__auto__;
return hyperfiddle.spec.extend_spec(((cljs.core.ident_QMARK_(s))?cljs.core.vary_meta.cljs$core$IFn$_invoke$arity$4((hyperfiddle.spec.get_spec.cljs$core$IFn$_invoke$arity$1 ? hyperfiddle.spec.get_spec.cljs$core$IFn$_invoke$arity$1(s) : hyperfiddle.spec.get_spec.call(null,s)),cljs.core.assoc,new cljs.core.Keyword("hyperfiddle.spec","alias","hyperfiddle.spec/alias",242281201),ident):s));
} else {
return null;
}
});
hyperfiddle.spec.parse = (function hyperfiddle$spec$parse(form){
if(cljs.core.seq_QMARK_(form)){
var G__57288 = cljs.core.first(form);
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(new cljs.core.Symbol("clojure.spec.alpha","cat","clojure.spec.alpha/cat",-523389547,null),G__57288)){
var kvs = cljs.core.partition.cljs$core$IFn$_invoke$arity$2((2),cljs.core.rest(form));
var keys = cljs.core.mapv.cljs$core$IFn$_invoke$arity$2(cljs.core.first,kvs);
return new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword("hyperfiddle.spec","type","hyperfiddle.spec/type",-838549555),cljs.core.keyword.cljs$core$IFn$_invoke$arity$2(cljs.core.namespace(new cljs.core.Keyword("hyperfiddle.spec","_","hyperfiddle.spec/_",-1633783864)),cljs.core.name(cljs.core.first(form))),new cljs.core.Keyword("hyperfiddle.spec","keys","hyperfiddle.spec/keys",-960509225),keys,new cljs.core.Keyword("hyperfiddle.spec","values","hyperfiddle.spec/values",771949939),cljs.core.mapv.cljs$core$IFn$_invoke$arity$2(cljs.core.second,kvs)], null);
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(new cljs.core.Symbol("clojure.spec.alpha","alt","clojure.spec.alpha/alt",-612316618,null),G__57288)){
var kvs = cljs.core.partition.cljs$core$IFn$_invoke$arity$2((2),cljs.core.rest(form));
var keys = cljs.core.mapv.cljs$core$IFn$_invoke$arity$2(cljs.core.first,kvs);
return new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword("hyperfiddle.spec","type","hyperfiddle.spec/type",-838549555),cljs.core.keyword.cljs$core$IFn$_invoke$arity$2(cljs.core.namespace(new cljs.core.Keyword("hyperfiddle.spec","_","hyperfiddle.spec/_",-1633783864)),cljs.core.name(cljs.core.first(form))),new cljs.core.Keyword("hyperfiddle.spec","keys","hyperfiddle.spec/keys",-960509225),keys,new cljs.core.Keyword("hyperfiddle.spec","values","hyperfiddle.spec/values",771949939),cljs.core.mapv.cljs$core$IFn$_invoke$arity$2(cljs.core.second,kvs)], null);
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(new cljs.core.Symbol("clojure.spec.alpha","or","clojure.spec.alpha/or",434904251,null),G__57288)){
var kvs = cljs.core.partition.cljs$core$IFn$_invoke$arity$2((2),cljs.core.rest(form));
var keys = cljs.core.mapv.cljs$core$IFn$_invoke$arity$2(cljs.core.first,kvs);
return new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword("hyperfiddle.spec","type","hyperfiddle.spec/type",-838549555),cljs.core.keyword.cljs$core$IFn$_invoke$arity$2(cljs.core.namespace(new cljs.core.Keyword("hyperfiddle.spec","_","hyperfiddle.spec/_",-1633783864)),cljs.core.name(cljs.core.first(form))),new cljs.core.Keyword("hyperfiddle.spec","keys","hyperfiddle.spec/keys",-960509225),keys,new cljs.core.Keyword("hyperfiddle.spec","values","hyperfiddle.spec/values",771949939),cljs.core.mapv.cljs$core$IFn$_invoke$arity$2(cljs.core.second,kvs)], null);
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(new cljs.core.Symbol("clojure.spec.alpha","+","clojure.spec.alpha/+",96423191,null),G__57288)){
return new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword("hyperfiddle.spec","type","hyperfiddle.spec/type",-838549555),new cljs.core.Keyword("hyperfiddle.spec","+","hyperfiddle.spec/+",-368893100),new cljs.core.Keyword("hyperfiddle.spec","pred","hyperfiddle.spec/pred",-86035364),cljs.core.second(form)], null);
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(new cljs.core.Symbol("clojure.core","fn","clojure.core/fn",-980152636,null),G__57288)){
return new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword("hyperfiddle.spec","type","hyperfiddle.spec/type",-838549555),new cljs.core.Keyword("hyperfiddle.spec","predicate","hyperfiddle.spec/predicate",270440669),new cljs.core.Keyword("hyperfiddle.spec","form","hyperfiddle.spec/form",641587392),form,new cljs.core.Keyword("hyperfiddle.spec","description","hyperfiddle.spec/description",1391391953),form], null);
} else {
throw (new Error(["No matching clause: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(G__57288)].join('')));

}
}
}
}
}
} else {
if(cljs.core.ident_QMARK_(form)){
var or__5045__auto__ = clojure.datafy.datafy(hyperfiddle.spec.get_spec(form));
if(cljs.core.truth_(or__5045__auto__)){
return or__5045__auto__;
} else {
return new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword("hyperfiddle.spec","type","hyperfiddle.spec/type",-838549555),new cljs.core.Keyword("hyperfiddle.spec","predicate","hyperfiddle.spec/predicate",270440669),new cljs.core.Keyword("hyperfiddle.spec","form","hyperfiddle.spec/form",641587392),form,new cljs.core.Keyword("hyperfiddle.spec","description","hyperfiddle.spec/description",1391391953),form], null);
}
} else {
return null;
}
}
});
hyperfiddle.spec.datafy_STAR_ = (function hyperfiddle$spec$datafy_STAR_(spec){
var map__57301 = cljs.core.meta(spec);
var map__57301__$1 = cljs.core.__destructure_map(map__57301);
var alias = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__57301__$1,new cljs.core.Keyword("hyperfiddle.spec","alias","hyperfiddle.spec/alias",242281201));
var name = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__57301__$1,new cljs.core.Keyword("cljs.spec.alpha","name","cljs.spec.alpha/name",205233570));
var type = hyperfiddle.spec.spec_type(spec);
return cljs.core.with_meta(cljs.core.merge.cljs$core$IFn$_invoke$arity$variadic(cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword("hyperfiddle.spec","name","hyperfiddle.spec/name",-168775824),(function (){var or__5045__auto__ = alias;
if(cljs.core.truth_(or__5045__auto__)){
return or__5045__auto__;
} else {
return name;
}
})(),new cljs.core.Keyword("hyperfiddle.spec","type","hyperfiddle.spec/type",-838549555),type,new cljs.core.Keyword("hyperfiddle.spec","form","hyperfiddle.spec/form",641587392),hyperfiddle.spec.form(spec),new cljs.core.Keyword("hyperfiddle.spec","description","hyperfiddle.spec/description",1391391953),cljs.spec.alpha.describe(spec)], null),(((!((alias == null))))?new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword("hyperfiddle.spec","alias","hyperfiddle.spec/alias",242281201),name], null):null),(function (){var G__57306 = type;
var G__57306__$1 = (((G__57306 instanceof cljs.core.Keyword))?G__57306.fqn:null);
switch (G__57306__$1) {
case "hyperfiddle.spec/and":
case "hyperfiddle.spec/predicate":
return cljs.core.PersistentArrayMap.EMPTY;

break;
case "hyperfiddle.spec/keys":
var vec__57308 = hyperfiddle.spec.form(spec);
var _ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57308,(0),null);
var ___$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57308,(1),null);
var req = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57308,(2),null);
var ___$2 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57308,(3),null);
var opt = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57308,(4),null);
var ___$3 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57308,(5),null);
var req_un = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57308,(6),null);
var ___$4 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57308,(7),null);
var opt_un = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57308,(8),null);
return new cljs.core.PersistentArrayMap(null, 4, [new cljs.core.Keyword("hyperfiddle.spec","req","hyperfiddle.spec/req",1688583192),req,new cljs.core.Keyword("hyperfiddle.spec","opt","hyperfiddle.spec/opt",1488383872),opt,new cljs.core.Keyword("hyperfiddle.spec","req-un","hyperfiddle.spec/req-un",-132794945),req_un,new cljs.core.Keyword("hyperfiddle.spec","opt-un","hyperfiddle.spec/opt-un",-1128312193),opt_un], null);

break;
case "hyperfiddle.spec/fspec":
return new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword("hyperfiddle.spec","args","hyperfiddle.spec/args",-965698479),(function (){var G__57311 = new cljs.core.Keyword(null,"args","args",1315556576).cljs$core$IFn$_invoke$arity$1(spec);
return (hyperfiddle.spec.spec.cljs$core$IFn$_invoke$arity$1 ? hyperfiddle.spec.spec.cljs$core$IFn$_invoke$arity$1(G__57311) : hyperfiddle.spec.spec.call(null,G__57311));
})(),new cljs.core.Keyword("hyperfiddle.spec","ret","hyperfiddle.spec/ret",1544981473),(function (){var G__57312 = new cljs.core.Keyword(null,"ret","ret",-468222814).cljs$core$IFn$_invoke$arity$1(spec);
return (hyperfiddle.spec.spec.cljs$core$IFn$_invoke$arity$1 ? hyperfiddle.spec.spec.cljs$core$IFn$_invoke$arity$1(G__57312) : hyperfiddle.spec.spec.call(null,G__57312));
})()], null);

break;
case "hyperfiddle.spec/cat":
case "hyperfiddle.spec/alt":
case "hyperfiddle.spec/or":
return hyperfiddle.spec.parse(hyperfiddle.spec.form(spec));

break;
case "hyperfiddle.spec/coll-of":
var vec__57313 = hyperfiddle.spec.form(spec);
var seq__57314 = cljs.core.seq(vec__57313);
var first__57315 = cljs.core.first(seq__57314);
var seq__57314__$1 = cljs.core.next(seq__57314);
var _ = first__57315;
var first__57315__$1 = cljs.core.first(seq__57314__$1);
var seq__57314__$2 = cljs.core.next(seq__57314__$1);
var pred = first__57315__$1;
var opts = seq__57314__$2;
return new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword("hyperfiddle.spec","pred","hyperfiddle.spec/pred",-86035364),pred,new cljs.core.Keyword("hyperfiddle.spec","opts","hyperfiddle.spec/opts",-2126170824),cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.hash_map,opts)], null);

break;
default:
throw (new Error(["No matching clause: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(G__57306__$1)].join('')));

}
})()], 0)),cljs.core.merge.cljs$core$IFn$_invoke$arity$variadic(cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([cljs.core.dissoc.cljs$core$IFn$_invoke$arity$variadic(cljs.core.meta(spec),new cljs.core.Symbol("clojure.core.protocols","datafy","clojure.core.protocols/datafy",707534751,null),cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([new cljs.core.Keyword("clojure.datafy","obj","clojure.datafy/obj",-330079421),new cljs.core.Keyword("clojure.datafy","class","clojure.datafy/class",1962452569)], 0)),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Symbol("clojure.core.protocols","nav","clojure.core.protocols/nav",298936762,null),(function (x,k,v){
var G__57316 = k;
var G__57316__$1 = (((G__57316 instanceof cljs.core.Keyword))?G__57316.fqn:null);
switch (G__57316__$1) {
case "hyperfiddle.spec/alias":
return (hyperfiddle.spec.spec.cljs$core$IFn$_invoke$arity$1 ? hyperfiddle.spec.spec.cljs$core$IFn$_invoke$arity$1(name) : hyperfiddle.spec.spec.call(null,name));

break;
case "hyperfiddle.spec/args":
var G__57317 = new cljs.core.Keyword(null,"args","args",1315556576).cljs$core$IFn$_invoke$arity$1(spec);
return (hyperfiddle.spec.spec.cljs$core$IFn$_invoke$arity$1 ? hyperfiddle.spec.spec.cljs$core$IFn$_invoke$arity$1(G__57317) : hyperfiddle.spec.spec.call(null,G__57317));

break;
case "hyperfiddle.spec/ret":
var G__57318 = new cljs.core.Keyword(null,"ret","ret",-468222814).cljs$core$IFn$_invoke$arity$1(spec);
return (hyperfiddle.spec.spec.cljs$core$IFn$_invoke$arity$1 ? hyperfiddle.spec.spec.cljs$core$IFn$_invoke$arity$1(G__57318) : hyperfiddle.spec.spec.call(null,G__57318));

break;
case "hyperfiddle.spec/values":
return cljs.core.with_meta(new cljs.core.Keyword("hyperfiddle.spec","values","hyperfiddle.spec/values",771949939).cljs$core$IFn$_invoke$arity$1(x),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Symbol("clojure.core.protocols","datafy","clojure.core.protocols/datafy",707534751,null),(function (_){
return cljs.core.mapv.cljs$core$IFn$_invoke$arity$3((function (key,value){
return cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(hyperfiddle.spec.parse(value),new cljs.core.Keyword("hyperfiddle.spec","key","hyperfiddle.spec/key",766908262),key);
}),new cljs.core.Keyword("hyperfiddle.spec","keys","hyperfiddle.spec/keys",-960509225).cljs$core$IFn$_invoke$arity$1(x),new cljs.core.Keyword("hyperfiddle.spec","values","hyperfiddle.spec/values",771949939).cljs$core$IFn$_invoke$arity$1(x));
})], null));

break;
case "hyperfiddle.spec/req":
case "hyperfiddle.spec/opt":
case "hyperfiddle.spec/req-un":
case "hyperfiddle.spec/opt-un":
case "hyperfiddle.spec/predicates":
throw cljs.core.ex_info.cljs$core$IFn$_invoke$arity$2("Not implemented yet. Please log a ticket",new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"nav","nav",719540477),k,new cljs.core.Keyword("hyperfiddle.spec","type","hyperfiddle.spec/type",-838549555),type], null));

break;
default:
return v;

}
})], null)], 0)));
});
hyperfiddle.spec.spec = (function hyperfiddle$spec$spec(spec_or_ident){
if(cljs.core.truth_(spec_or_ident)){
if(cljs.core.truth_((function (){var or__5045__auto__ = cljs.spec.alpha.spec_QMARK_(spec_or_ident);
if(cljs.core.truth_(or__5045__auto__)){
return or__5045__auto__;
} else {
return cljs.core.qualified_ident_QMARK_(spec_or_ident);
}
})())){
} else {
throw (new Error(["Assert failed: ",["Expected a clojure spec object or a qualified identifier for a defined spec. Given ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(spec_or_ident)].join(''),"\n","(or (s/spec? spec-or-ident) (qualified-ident? spec-or-ident))"].join('')));
}

if(cljs.core.truth_(cljs.spec.alpha.spec_QMARK_(spec_or_ident))){
return cljs.core.with_meta(hyperfiddle.spec.form(spec_or_ident),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Symbol("clojure.core.protocols","datafy","clojure.core.protocols/datafy",707534751,null),(function (_){
return clojure.datafy.datafy(hyperfiddle.spec.extend_spec(spec_or_ident));
})], null));
} else {
if(cljs.core.qualified_ident_QMARK_(spec_or_ident)){
var temp__5804__auto__ = hyperfiddle.spec.get_spec(spec_or_ident);
if(cljs.core.truth_(temp__5804__auto__)){
var s = temp__5804__auto__;
return cljs.core.with_meta(hyperfiddle.spec.form(s),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Symbol("clojure.core.protocols","datafy","clojure.core.protocols/datafy",707534751,null),(function (_){
return clojure.datafy.datafy(s);
})], null));
} else {
return null;
}
} else {
return null;
}
}
} else {
return null;
}
});
hyperfiddle.spec.reflect = (function hyperfiddle$spec$reflect(spec_or_ident){
if(cljs.core.truth_((function (){var or__5045__auto__ = cljs.spec.alpha.spec_QMARK_(spec_or_ident);
if(cljs.core.truth_(or__5045__auto__)){
return or__5045__auto__;
} else {
return cljs.core.qualified_ident_QMARK_(spec_or_ident);
}
})())){
} else {
throw (new Error(["Assert failed: ",["Expected a clojure spec object or a qualified identifier for a defined spec. Given ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(spec_or_ident)].join(''),"\n","(or (s/spec? spec-or-ident) (qualified-ident? spec-or-ident))"].join('')));
}

if(cljs.core.truth_(cljs.spec.alpha.spec_QMARK_(spec_or_ident))){
return clojure.datafy.datafy(hyperfiddle.spec.extend_spec(spec_or_ident));
} else {
if(cljs.core.qualified_ident_QMARK_(spec_or_ident)){
return clojure.datafy.datafy(hyperfiddle.spec.get_spec(spec_or_ident));
} else {
return null;
}
}
});
/**
 * Guess the cardinality of a speced function or keyword.
 */
hyperfiddle.spec.cardinality_many_QMARK_ = (function hyperfiddle$spec$cardinality_many_QMARK_(ident){
var s = clojure.datafy.datafy(hyperfiddle.spec.spec(ident));
var temp__5804__auto__ = (function (){var G__57324 = new cljs.core.Keyword("hyperfiddle.spec","type","hyperfiddle.spec/type",-838549555).cljs$core$IFn$_invoke$arity$1(s);
var G__57324__$1 = (((G__57324 instanceof cljs.core.Keyword))?G__57324.fqn:null);
switch (G__57324__$1) {
case "hyperfiddle.spec/fspec":
return new cljs.core.Keyword("hyperfiddle.spec","type","hyperfiddle.spec/type",-838549555).cljs$core$IFn$_invoke$arity$1(clojure.datafy.datafy(clojure.datafy.nav(s,new cljs.core.Keyword("hyperfiddle.spec","ret","hyperfiddle.spec/ret",1544981473),new cljs.core.Keyword("hyperfiddle.spec","ret","hyperfiddle.spec/ret",1544981473).cljs$core$IFn$_invoke$arity$1(s))));

break;
default:
return new cljs.core.Keyword("hyperfiddle.spec","type","hyperfiddle.spec/type",-838549555).cljs$core$IFn$_invoke$arity$1(s);

}
})();
if(cljs.core.truth_(temp__5804__auto__)){
var type = temp__5804__auto__;
return cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword("hyperfiddle.spec","coll-of","hyperfiddle.spec/coll-of",-1333267998),type);
} else {
return null;
}
});
hyperfiddle.spec.args = (function hyperfiddle$spec$args(ident){
var s = clojure.datafy.datafy(hyperfiddle.spec.get_spec(ident));
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword("hyperfiddle.spec","fspec","hyperfiddle.spec/fspec",2022899258),new cljs.core.Keyword("hyperfiddle.spec","type","hyperfiddle.spec/type",-838549555).cljs$core$IFn$_invoke$arity$1(s))){
return clojure.datafy.nav(s,new cljs.core.Keyword("hyperfiddle.spec","args","hyperfiddle.spec/args",-965698479),new cljs.core.Keyword("hyperfiddle.spec","args","hyperfiddle.spec/args",-965698479).cljs$core$IFn$_invoke$arity$1(s));
} else {
return null;
}
});
hyperfiddle.spec.arg = (function hyperfiddle$spec$arg(ident,arg_key){
var _PERCENT_ = hyperfiddle.spec.args(ident);
var _PERCENT___$1 = clojure.datafy.datafy(_PERCENT_);
var _PERCENT___$2 = clojure.datafy.nav(_PERCENT___$1,new cljs.core.Keyword("hyperfiddle.spec","values","hyperfiddle.spec/values",771949939),new cljs.core.Keyword("hyperfiddle.spec","values","hyperfiddle.spec/values",771949939).cljs$core$IFn$_invoke$arity$1(_PERCENT___$1));
var _PERCENT___$3 = clojure.datafy.datafy(_PERCENT___$2);
var _PERCENT___$4 = cljs.core.filter.cljs$core$IFn$_invoke$arity$2((function (p1__57325_SHARP_){
return cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(arg_key,new cljs.core.Keyword("hyperfiddle.spec","key","hyperfiddle.spec/key",766908262).cljs$core$IFn$_invoke$arity$1(p1__57325_SHARP_));
}),_PERCENT___$3);
return cljs.core.first(_PERCENT___$4);
});
hyperfiddle.spec.types = new cljs.core.PersistentVector(null, 17, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","boolean?","clojure.core/boolean?",1566259823,null),new cljs.core.Keyword("hyperfiddle.spec.type","boolean","hyperfiddle.spec.type/boolean",638470051),new cljs.core.Keyword("db.type","boolean","db.type/boolean",-645454270)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","double?","clojure.core/double?",1847770331,null),new cljs.core.Keyword("hyperfiddle.spec.type","double","hyperfiddle.spec.type/double",-1941172450),new cljs.core.Keyword("db.type","double","db.type/double",-521884231)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","float?","clojure.core/float?",-99660463,null),new cljs.core.Keyword("hyperfiddle.spec.type","float","hyperfiddle.spec.type/float",866653167),new cljs.core.Keyword("db.type","float","db.type/float",-315575090)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","decimal?","clojure.core/decimal?",1494290495,null),new cljs.core.Keyword("hyperfiddle.spec.type","bigdec","hyperfiddle.spec.type/bigdec",360077613),new cljs.core.Keyword("db.type","bigdec","db.type/bigdec",1626126666)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","inst?","clojure.core/inst?",-1302678916,null),new cljs.core.Keyword("hyperfiddle.spec.type","instant","hyperfiddle.spec.type/instant",1853612483),new cljs.core.Keyword("db.type","instant","db.type/instant",-1024769248)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","keyword?","clojure.core/keyword?",543424180,null),new cljs.core.Keyword("hyperfiddle.spec.type","keyword","hyperfiddle.spec.type/keyword",-655455634),new cljs.core.Keyword("db.type","keyword","db.type/keyword",205926793)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","string?","clojure.core/string?",-1902673477,null),new cljs.core.Keyword("hyperfiddle.spec.type","string","hyperfiddle.spec.type/string",-505613589),new cljs.core.Keyword("db.type","string","db.type/string",1432572808)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","uri?","clojure.core/uri?",1255469701,null),new cljs.core.Keyword("hyperfiddle.spec.type","uri","hyperfiddle.spec.type/uri",960528290),new cljs.core.Keyword("db.type","uri","db.type/uri",-437575613)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","uuid?","clojure.core/uuid?",-100722718,null),new cljs.core.Keyword("hyperfiddle.spec.type","uuid","hyperfiddle.spec.type/uuid",-1263433310),new cljs.core.Keyword("db.type","uuid","db.type/uuid",1543195203)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","map?","clojure.core/map?",-1425864013,null),new cljs.core.Keyword("hyperfiddle.spec.type","ref","hyperfiddle.spec.type/ref",-445874354),new cljs.core.Keyword("db.type","ref","db.type/ref",-1728373079)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","ref?","clojure.core/ref?",177714038,null),new cljs.core.Keyword("hyperfiddle.spec.type","ref","hyperfiddle.spec.type/ref",-445874354),new cljs.core.Keyword("db.type","ref","db.type/ref",-1728373079)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","symbol?","clojure.core/symbol?",1587987784,null),new cljs.core.Keyword("hyperfiddle.spec.type","symbol","hyperfiddle.spec.type/symbol",431448481),new cljs.core.Keyword("db.type","symbol","db.type/symbol",905953406)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","integer?","clojure.core/integer?",-1617881728,null),new cljs.core.Keyword("hyperfiddle.spec.type","long","hyperfiddle.spec.type/long",-1657167874),new cljs.core.Keyword("db.type","long","db.type/long",700514073)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","number?","clojure.core/number?",-1044499897,null),new cljs.core.Keyword("hyperfiddle.spec.type","long","hyperfiddle.spec.type/long",-1657167874),new cljs.core.Keyword("db.type","long","db.type/long",700514073)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","nat-int?","clojure.core/nat-int?",-65390525,null),new cljs.core.Keyword("hyperfiddle.spec.type","long","hyperfiddle.spec.type/long",-1657167874),new cljs.core.Keyword("db.type","long","db.type/long",700514073)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","int?","clojure.core/int?",1026034806,null),new cljs.core.Keyword("hyperfiddle.spec.type","long","hyperfiddle.spec.type/long",-1657167874),new cljs.core.Keyword("db.type","long","db.type/long",700514073)], null),new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("clojure.core","pos-int?","clojure.core/pos-int?",-1946393424,null),new cljs.core.Keyword("hyperfiddle.spec.type","long","hyperfiddle.spec.type/long",-1657167874),new cljs.core.Keyword("db.type","long","db.type/long",700514073)], null)], null);
hyperfiddle.spec.pred__GT_type = cljs.core.zipmap(cljs.core.map.cljs$core$IFn$_invoke$arity$2(cljs.core.first,hyperfiddle.spec.types),cljs.core.map.cljs$core$IFn$_invoke$arity$2(cljs.core.second,hyperfiddle.spec.types));
hyperfiddle.spec.valueType__GT_type = cljs.core.zipmap(cljs.core.map.cljs$core$IFn$_invoke$arity$2((function (p1__57330_SHARP_){
return cljs.core.get.cljs$core$IFn$_invoke$arity$2(p1__57330_SHARP_,(2));
}),hyperfiddle.spec.types),cljs.core.map.cljs$core$IFn$_invoke$arity$2(cljs.core.second,hyperfiddle.spec.types));
hyperfiddle.spec.valueType__GT_pred = cljs.core.zipmap(cljs.core.map.cljs$core$IFn$_invoke$arity$2((function (p1__57331_SHARP_){
return cljs.core.get.cljs$core$IFn$_invoke$arity$2(p1__57331_SHARP_,(2));
}),hyperfiddle.spec.types),cljs.core.map.cljs$core$IFn$_invoke$arity$2(cljs.core.first,hyperfiddle.spec.types));
hyperfiddle.spec.type_of = (function hyperfiddle$spec$type_of(var_args){
var G__57337 = arguments.length;
switch (G__57337) {
case 1:
return hyperfiddle.spec.type_of.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return hyperfiddle.spec.type_of.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

(hyperfiddle.spec.type_of.cljs$core$IFn$_invoke$arity$1 = (function (spec){
var spec__$1 = clojure.datafy.datafy(hyperfiddle.spec.spec(spec));
var G__57342 = new cljs.core.Keyword("hyperfiddle.spec","type","hyperfiddle.spec/type",-838549555).cljs$core$IFn$_invoke$arity$1(spec__$1);
var G__57342__$1 = (((G__57342 instanceof cljs.core.Keyword))?G__57342.fqn:null);
switch (G__57342__$1) {
case "hyperfiddle.spec/fspec":
var G__57343 = new cljs.core.Keyword("hyperfiddle.spec","ret","hyperfiddle.spec/ret",1544981473).cljs$core$IFn$_invoke$arity$1(spec__$1);
return (hyperfiddle.spec.pred__GT_type.cljs$core$IFn$_invoke$arity$1 ? hyperfiddle.spec.pred__GT_type.cljs$core$IFn$_invoke$arity$1(G__57343) : hyperfiddle.spec.pred__GT_type.call(null,G__57343));

break;
default:
var G__57344 = new cljs.core.Keyword("hyperfiddle.spec","form","hyperfiddle.spec/form",641587392).cljs$core$IFn$_invoke$arity$1(spec__$1);
return (hyperfiddle.spec.pred__GT_type.cljs$core$IFn$_invoke$arity$1 ? hyperfiddle.spec.pred__GT_type.cljs$core$IFn$_invoke$arity$1(G__57344) : hyperfiddle.spec.pred__GT_type.call(null,G__57344));

}
}));

(hyperfiddle.spec.type_of.cljs$core$IFn$_invoke$arity$2 = (function (spec,argument){
var G__57349 = new cljs.core.Keyword("hyperfiddle.spec","form","hyperfiddle.spec/form",641587392).cljs$core$IFn$_invoke$arity$1(hyperfiddle.spec.arg(spec,argument));
return (hyperfiddle.spec.pred__GT_type.cljs$core$IFn$_invoke$arity$1 ? hyperfiddle.spec.pred__GT_type.cljs$core$IFn$_invoke$arity$1(G__57349) : hyperfiddle.spec.pred__GT_type.call(null,G__57349));
}));

(hyperfiddle.spec.type_of.cljs$lang$maxFixedArity = 2);

hyperfiddle.spec.valueType_of = (function hyperfiddle$spec$valueType_of(schema,attr){
var G__57350 = new cljs.core.Keyword(null,"db.valueType","db.valueType",1058682135).cljs$core$IFn$_invoke$arity$1(cljs.core.get.cljs$core$IFn$_invoke$arity$2(schema,attr));
return (hyperfiddle.spec.valueType__GT_type.cljs$core$IFn$_invoke$arity$1 ? hyperfiddle.spec.valueType__GT_type.cljs$core$IFn$_invoke$arity$1(G__57350) : hyperfiddle.spec.valueType__GT_type.call(null,G__57350));
});
hyperfiddle.spec.explain_fspec_data = (function hyperfiddle$spec$explain_fspec_data(var_args){
var args__5775__auto__ = [];
var len__5769__auto___57386 = arguments.length;
var i__5770__auto___57387 = (0);
while(true){
if((i__5770__auto___57387 < len__5769__auto___57386)){
args__5775__auto__.push((arguments[i__5770__auto___57387]));

var G__57388 = (i__5770__auto___57387 + (1));
i__5770__auto___57387 = G__57388;
continue;
} else {
}
break;
}

var argseq__5776__auto__ = ((((1) < args__5775__auto__.length))?(new cljs.core.IndexedSeq(args__5775__auto__.slice((1)),(0),null)):null);
return hyperfiddle.spec.explain_fspec_data.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__5776__auto__);
});

(hyperfiddle.spec.explain_fspec_data.cljs$core$IFn$_invoke$arity$variadic = (function (fspec,args){
var cat = new cljs.core.Keyword(null,"args","args",1315556576).cljs$core$IFn$_invoke$arity$1(hyperfiddle.spec.get_spec(fspec));
if(cljs.core.truth_(cat)){
} else {
throw (new Error(["Assert failed: ","explain-fspec-data is implemented for function spec arguments only (s/fdef :args).","\n","cat"].join('')));
}

return cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(cljs.spec.alpha.explain_data(cat,args),new cljs.core.Keyword("clojure.spec.alpha","spec","clojure.spec.alpha/spec",-1165071684));
}));

(hyperfiddle.spec.explain_fspec_data.cljs$lang$maxFixedArity = (1));

/** @this {Function} */
(hyperfiddle.spec.explain_fspec_data.cljs$lang$applyTo = (function (seq57351){
var G__57352 = cljs.core.first(seq57351);
var seq57351__$1 = cljs.core.next(seq57351);
var self__5754__auto__ = this;
return self__5754__auto__.cljs$core$IFn$_invoke$arity$variadic(G__57352,seq57351__$1);
}));

hyperfiddle.spec.reformat_explain_data = (function hyperfiddle$spec$reformat_explain_data(ed){
if(((cljs.core.map_QMARK_(ed)) && (cljs.core.contains_QMARK_(ed,new cljs.core.Keyword("clojure.spec.alpha","problems","clojure.spec.alpha/problems",1395345052))))){
var problems = cljs.core.sort_by.cljs$core$IFn$_invoke$arity$2((function (p1__57360_SHARP_){
return (- cljs.core.count(new cljs.core.Keyword(null,"path","path",-188191168).cljs$core$IFn$_invoke$arity$1(p1__57360_SHARP_)));
}),cljs.core.sort_by.cljs$core$IFn$_invoke$arity$2((function (p1__57359_SHARP_){
return (- cljs.core.count(new cljs.core.Keyword(null,"in","in",-1531184865).cljs$core$IFn$_invoke$arity$1(p1__57359_SHARP_)));
}),new cljs.core.Keyword("clojure.spec.alpha","problems","clojure.spec.alpha/problems",1395345052).cljs$core$IFn$_invoke$arity$1(ed)));
return cljs.core.not_empty(cljs.core.group_by(new cljs.core.Keyword(null,"path","path",-188191168),cljs.core.map.cljs$core$IFn$_invoke$arity$2((function (p__57361){
var map__57362 = p__57361;
var map__57362__$1 = cljs.core.__destructure_map(map__57362);
var prob = map__57362__$1;
var path = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__57362__$1,new cljs.core.Keyword(null,"path","path",-188191168));
var pred = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__57362__$1,new cljs.core.Keyword(null,"pred","pred",1927423397));
var val = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__57362__$1,new cljs.core.Keyword(null,"val","val",128701612));
var reason = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__57362__$1,new cljs.core.Keyword(null,"reason","reason",-2070751759));
var via = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__57362__$1,new cljs.core.Keyword(null,"via","via",-1904457336));
var in$ = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__57362__$1,new cljs.core.Keyword(null,"in","in",-1531184865));
return new cljs.core.PersistentArrayMap(null, 6, [new cljs.core.Keyword(null,"val","val",128701612),val,new cljs.core.Keyword(null,"in","in",-1531184865),in$,new cljs.core.Keyword(null,"path","path",-188191168),path,new cljs.core.Keyword(null,"spec","spec",347520401),cljs.core.last(via),new cljs.core.Keyword(null,"reason","reason",-2070751759),(function (){var or__5045__auto__ = reason;
if(cljs.core.truth_(or__5045__auto__)){
return or__5045__auto__;
} else {
return cljs.spec.alpha.abbrev(pred);
}
})(),new cljs.core.Keyword(null,"problems","problems",2097327077),cljs.core.dissoc.cljs$core$IFn$_invoke$arity$variadic(prob,new cljs.core.Keyword(null,"path","path",-188191168),cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([new cljs.core.Keyword(null,"pred","pred",1927423397),new cljs.core.Keyword(null,"val","val",128701612),new cljs.core.Keyword(null,"reason","reason",-2070751759),new cljs.core.Keyword(null,"via","via",-1904457336),new cljs.core.Keyword(null,"in","in",-1531184865)], 0))], null);
}),problems)));
} else {
return null;
}
});

//# sourceMappingURL=hyperfiddle.spec.js.map

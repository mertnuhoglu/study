goog.provide('hyperfiddle.hfql');
hyperfiddle.hfql.literal = (function hyperfiddle$hfql$literal(var_args){
var args__5775__auto__ = [];
var len__5769__auto___54592 = arguments.length;
var i__5770__auto___54593 = (0);
while(true){
if((i__5770__auto___54593 < len__5769__auto___54592)){
args__5775__auto__.push((arguments[i__5770__auto___54593]));

var G__54594 = (i__5770__auto___54593 + (1));
i__5770__auto___54593 = G__54594;
continue;
} else {
}
break;
}

var argseq__5776__auto__ = ((((1) < args__5775__auto__.length))?(new cljs.core.IndexedSeq(args__5775__auto__.slice((1)),(0),null)):null);
return hyperfiddle.hfql.literal.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__5776__auto__);
});

(hyperfiddle.hfql.literal.cljs$core$IFn$_invoke$arity$variadic = (function (collf,args){
return (collf.cljs$core$IFn$_invoke$arity$1 ? collf.cljs$core$IFn$_invoke$arity$1(args) : collf.call(null,args));
}));

(hyperfiddle.hfql.literal.cljs$lang$maxFixedArity = (1));

/** @this {Function} */
(hyperfiddle.hfql.literal.cljs$lang$applyTo = (function (seq54578){
var G__54579 = cljs.core.first(seq54578);
var seq54578__$1 = cljs.core.next(seq54578);
var self__5754__auto__ = this;
return self__5754__auto__.cljs$core$IFn$_invoke$arity$variadic(G__54579,seq54578__$1);
}));

cljs.spec.alpha.def_impl(new cljs.core.Symbol("hyperfiddle.hfql","literal","hyperfiddle.hfql/literal",1056099997,null),cljs.core.list(new cljs.core.Symbol("cljs.spec.alpha","fspec","cljs.spec.alpha/fspec",-1289128341,null),new cljs.core.Keyword(null,"args","args",1315556576),cljs.core.list(new cljs.core.Symbol("cljs.spec.alpha","cat","cljs.spec.alpha/cat",-1471398329,null),new cljs.core.Keyword(null,"collf","collf",-1859774332),new cljs.core.Symbol("cljs.core","fn?","cljs.core/fn?",71876239,null),new cljs.core.Keyword(null,"args","args",1315556576),new cljs.core.Symbol("cljs.core","any?","cljs.core/any?",-2068111842,null)),new cljs.core.Keyword(null,"ret","ret",-468222814),new cljs.core.Symbol("cljs.core","any?","cljs.core/any?",-2068111842,null)),cljs.spec.alpha.fspec_impl(cljs.spec.alpha.spec_impl.cljs$core$IFn$_invoke$arity$4(cljs.core.list(new cljs.core.Symbol("cljs.spec.alpha","cat","cljs.spec.alpha/cat",-1471398329,null),new cljs.core.Keyword(null,"collf","collf",-1859774332),new cljs.core.Symbol("cljs.core","fn?","cljs.core/fn?",71876239,null),new cljs.core.Keyword(null,"args","args",1315556576),new cljs.core.Symbol("cljs.core","any?","cljs.core/any?",-2068111842,null)),cljs.spec.alpha.cat_impl(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"collf","collf",-1859774332),new cljs.core.Keyword(null,"args","args",1315556576)], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.fn_QMARK_,cljs.core.any_QMARK_], null),new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol("cljs.core","fn?","cljs.core/fn?",71876239,null),new cljs.core.Symbol("cljs.core","any?","cljs.core/any?",-2068111842,null)], null)),null,null),cljs.core.list(new cljs.core.Symbol("cljs.spec.alpha","cat","cljs.spec.alpha/cat",-1471398329,null),new cljs.core.Keyword(null,"collf","collf",-1859774332),new cljs.core.Symbol("cljs.core","fn?","cljs.core/fn?",71876239,null),new cljs.core.Keyword(null,"args","args",1315556576),new cljs.core.Symbol("cljs.core","any?","cljs.core/any?",-2068111842,null)),cljs.spec.alpha.spec_impl.cljs$core$IFn$_invoke$arity$4(new cljs.core.Symbol("cljs.core","any?","cljs.core/any?",-2068111842,null),cljs.core.any_QMARK_,null,null),new cljs.core.Symbol("cljs.core","any?","cljs.core/any?",-2068111842,null),null,null,null));
/**
 * Given a context, build a sequence of key-value pairs, ignoring any wildcard
 */
hyperfiddle.hfql._drop_wildcards = (function hyperfiddle$hfql$_drop_wildcards(p__54586){
var map__54587 = p__54586;
var map__54587__$1 = cljs.core.__destructure_map(map__54587);
var keys = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__54587__$1,new cljs.core.Keyword("hyperfiddle.api","keys","hyperfiddle.api/keys",861033510));
var values = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__54587__$1,new cljs.core.Keyword("hyperfiddle.api","values","hyperfiddle.api/values",26450064));
return cljs.core.remove.cljs$core$IFn$_invoke$arity$2((function (p__54588){
var vec__54589 = p__54588;
var k = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54589,(0),null);
var _v = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54589,(1),null);
return cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(new cljs.core.Symbol(null,"_","_",-1201019570,null),k);
}),cljs.core.partition.cljs$core$IFn$_invoke$arity$2((2),cljs.core.interleave.cljs$core$IFn$_invoke$arity$2(keys,values)));
});
hyperfiddle.hfql.safe_count = (function hyperfiddle$hfql$safe_count(xs){
if(cljs.core.counted_QMARK_(xs)){
return cljs.core.count(xs);
} else {
return (0);
}
});

//# sourceMappingURL=hyperfiddle.hfql.js.map

goog.provide('contrib.dynamic');
contrib.dynamic.call_sym = (function contrib$dynamic$call_sym(var_args){
var args__5775__auto__ = [];
var len__5769__auto___53376 = arguments.length;
var i__5770__auto___53377 = (0);
while(true){
if((i__5770__auto___53377 < len__5769__auto___53376)){
args__5775__auto__.push((arguments[i__5770__auto___53377]));

var G__53378 = (i__5770__auto___53377 + (1));
i__5770__auto___53377 = G__53378;
continue;
} else {
}
break;
}

var argseq__5776__auto__ = ((((1) < args__5775__auto__.length))?(new cljs.core.IndexedSeq(args__5775__auto__.slice((1)),(0),null)):null);
return contrib.dynamic.call_sym.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),argseq__5776__auto__);
});

(contrib.dynamic.call_sym.cljs$core$IFn$_invoke$arity$variadic = (function (qualified_sym,args){
throw (new Error(["Assert failed: ","contrib.dynamic/call-sym unimplemented in cljs","\n","false"].join('')));

}));

(contrib.dynamic.call_sym.cljs$lang$maxFixedArity = (1));

/** @this {Function} */
(contrib.dynamic.call_sym.cljs$lang$applyTo = (function (seq53354){
var G__53355 = cljs.core.first(seq53354);
var seq53354__$1 = cljs.core.next(seq53354);
var self__5754__auto__ = this;
return self__5754__auto__.cljs$core$IFn$_invoke$arity$variadic(G__53355,seq53354__$1);
}));


//# sourceMappingURL=contrib.dynamic.js.map

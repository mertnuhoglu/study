goog.provide('hyperfiddle.router');

/**
 * @interface
 */
hyperfiddle.router.IHistory = function(){};

var hyperfiddle$router$IHistory$navigate_BANG_$dyn_57402 = (function (this$,route){
var x__5393__auto__ = (((this$ == null))?null:this$);
var m__5394__auto__ = (hyperfiddle.router.navigate_BANG_[goog.typeOf(x__5393__auto__)]);
if((!((m__5394__auto__ == null)))){
return (m__5394__auto__.cljs$core$IFn$_invoke$arity$2 ? m__5394__auto__.cljs$core$IFn$_invoke$arity$2(this$,route) : m__5394__auto__.call(null,this$,route));
} else {
var m__5392__auto__ = (hyperfiddle.router.navigate_BANG_["_"]);
if((!((m__5392__auto__ == null)))){
return (m__5392__auto__.cljs$core$IFn$_invoke$arity$2 ? m__5392__auto__.cljs$core$IFn$_invoke$arity$2(this$,route) : m__5392__auto__.call(null,this$,route));
} else {
throw cljs.core.missing_protocol("IHistory.navigate!",this$);
}
}
});
hyperfiddle.router.navigate_BANG_ = (function hyperfiddle$router$navigate_BANG_(this$,route){
if((((!((this$ == null)))) && ((!((this$.hyperfiddle$router$IHistory$navigate_BANG_$arity$2 == null)))))){
return this$.hyperfiddle$router$IHistory$navigate_BANG_$arity$2(this$,route);
} else {
return hyperfiddle$router$IHistory$navigate_BANG_$dyn_57402(this$,route);
}
});

var hyperfiddle$router$IHistory$back_BANG_$dyn_57403 = (function (this$){
var x__5393__auto__ = (((this$ == null))?null:this$);
var m__5394__auto__ = (hyperfiddle.router.back_BANG_[goog.typeOf(x__5393__auto__)]);
if((!((m__5394__auto__ == null)))){
return (m__5394__auto__.cljs$core$IFn$_invoke$arity$1 ? m__5394__auto__.cljs$core$IFn$_invoke$arity$1(this$) : m__5394__auto__.call(null,this$));
} else {
var m__5392__auto__ = (hyperfiddle.router.back_BANG_["_"]);
if((!((m__5392__auto__ == null)))){
return (m__5392__auto__.cljs$core$IFn$_invoke$arity$1 ? m__5392__auto__.cljs$core$IFn$_invoke$arity$1(this$) : m__5392__auto__.call(null,this$));
} else {
throw cljs.core.missing_protocol("IHistory.back!",this$);
}
}
});
hyperfiddle.router.back_BANG_ = (function hyperfiddle$router$back_BANG_(this$){
if((((!((this$ == null)))) && ((!((this$.hyperfiddle$router$IHistory$back_BANG_$arity$1 == null)))))){
return this$.hyperfiddle$router$IHistory$back_BANG_$arity$1(this$);
} else {
return hyperfiddle$router$IHistory$back_BANG_$dyn_57403(this$);
}
});

var hyperfiddle$router$IHistory$forward_BANG_$dyn_57404 = (function (this$){
var x__5393__auto__ = (((this$ == null))?null:this$);
var m__5394__auto__ = (hyperfiddle.router.forward_BANG_[goog.typeOf(x__5393__auto__)]);
if((!((m__5394__auto__ == null)))){
return (m__5394__auto__.cljs$core$IFn$_invoke$arity$1 ? m__5394__auto__.cljs$core$IFn$_invoke$arity$1(this$) : m__5394__auto__.call(null,this$));
} else {
var m__5392__auto__ = (hyperfiddle.router.forward_BANG_["_"]);
if((!((m__5392__auto__ == null)))){
return (m__5392__auto__.cljs$core$IFn$_invoke$arity$1 ? m__5392__auto__.cljs$core$IFn$_invoke$arity$1(this$) : m__5392__auto__.call(null,this$));
} else {
throw cljs.core.missing_protocol("IHistory.forward!",this$);
}
}
});
hyperfiddle.router.forward_BANG_ = (function hyperfiddle$router$forward_BANG_(this$){
if((((!((this$ == null)))) && ((!((this$.hyperfiddle$router$IHistory$forward_BANG_$arity$1 == null)))))){
return this$.hyperfiddle$router$IHistory$forward_BANG_$arity$1(this$);
} else {
return hyperfiddle$router$IHistory$forward_BANG_$dyn_57404(this$);
}
});

var hyperfiddle$router$IHistory$replace_state_BANG_$dyn_57405 = (function (this$,new_state){
var x__5393__auto__ = (((this$ == null))?null:this$);
var m__5394__auto__ = (hyperfiddle.router.replace_state_BANG_[goog.typeOf(x__5393__auto__)]);
if((!((m__5394__auto__ == null)))){
return (m__5394__auto__.cljs$core$IFn$_invoke$arity$2 ? m__5394__auto__.cljs$core$IFn$_invoke$arity$2(this$,new_state) : m__5394__auto__.call(null,this$,new_state));
} else {
var m__5392__auto__ = (hyperfiddle.router.replace_state_BANG_["_"]);
if((!((m__5392__auto__ == null)))){
return (m__5392__auto__.cljs$core$IFn$_invoke$arity$2 ? m__5392__auto__.cljs$core$IFn$_invoke$arity$2(this$,new_state) : m__5392__auto__.call(null,this$,new_state));
} else {
throw cljs.core.missing_protocol("IHistory.replace-state!",this$);
}
}
});
hyperfiddle.router.replace_state_BANG_ = (function hyperfiddle$router$replace_state_BANG_(this$,new_state){
if((((!((this$ == null)))) && ((!((this$.hyperfiddle$router$IHistory$replace_state_BANG_$arity$2 == null)))))){
return this$.hyperfiddle$router$IHistory$replace_state_BANG_$arity$2(this$,new_state);
} else {
return hyperfiddle$router$IHistory$replace_state_BANG_$dyn_57405(this$,new_state);
}
});

hyperfiddle.router.updatef = (function hyperfiddle$router$updatef(p__57270,f){
var vec__57271 = p__57270;
var history__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57271,(0),null);
var idx = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57271,(1),null);
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.update.cljs$core$IFn$_invoke$arity$3(history__$1,idx,f),idx], null);
});
hyperfiddle.router.notify_watches = (function hyperfiddle$router$notify_watches(this$,p__57277){
var vec__57278 = p__57277;
var oldstate = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57278,(0),null);
var newstate = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57278,(1),null);
var oldval = cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.get,oldstate);
var newval = cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.get,newstate);
var seq__57281 = cljs.core.seq(cljs.core.deref(new cljs.core.Keyword(null,"watches","watches",-273097535).cljs$core$IFn$_invoke$arity$1(this$)));
var chunk__57282 = null;
var count__57283 = (0);
var i__57284 = (0);
while(true){
if((i__57284 < count__57283)){
var vec__57292 = chunk__57282.cljs$core$IIndexed$_nth$arity$2(null,i__57284);
var key = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57292,(0),null);
var callback = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57292,(1),null);
(callback.cljs$core$IFn$_invoke$arity$4 ? callback.cljs$core$IFn$_invoke$arity$4(key,this$,oldval,newval) : callback.call(null,key,this$,oldval,newval));


var G__57406 = seq__57281;
var G__57407 = chunk__57282;
var G__57408 = count__57283;
var G__57409 = (i__57284 + (1));
seq__57281 = G__57406;
chunk__57282 = G__57407;
count__57283 = G__57408;
i__57284 = G__57409;
continue;
} else {
var temp__5804__auto__ = cljs.core.seq(seq__57281);
if(temp__5804__auto__){
var seq__57281__$1 = temp__5804__auto__;
if(cljs.core.chunked_seq_QMARK_(seq__57281__$1)){
var c__5568__auto__ = cljs.core.chunk_first(seq__57281__$1);
var G__57410 = cljs.core.chunk_rest(seq__57281__$1);
var G__57411 = c__5568__auto__;
var G__57412 = cljs.core.count(c__5568__auto__);
var G__57413 = (0);
seq__57281 = G__57410;
chunk__57282 = G__57411;
count__57283 = G__57412;
i__57284 = G__57413;
continue;
} else {
var vec__57295 = cljs.core.first(seq__57281__$1);
var key = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57295,(0),null);
var callback = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57295,(1),null);
(callback.cljs$core$IFn$_invoke$arity$4 ? callback.cljs$core$IFn$_invoke$arity$4(key,this$,oldval,newval) : callback.call(null,key,this$,oldval,newval));


var G__57414 = cljs.core.next(seq__57281__$1);
var G__57415 = null;
var G__57416 = (0);
var G__57417 = (0);
seq__57281 = G__57414;
chunk__57282 = G__57415;
count__57283 = G__57416;
i__57284 = G__57417;
continue;
}
} else {
return null;
}
}
break;
}
});

/**
* @constructor
 * @implements {cljs.core.IRecord}
 * @implements {cljs.core.IWatchable}
 * @implements {cljs.core.IAtom}
 * @implements {cljs.core.IKVReduce}
 * @implements {cljs.core.IEquiv}
 * @implements {cljs.core.IHash}
 * @implements {cljs.core.ICollection}
 * @implements {cljs.core.IReset}
 * @implements {cljs.core.ISwap}
 * @implements {cljs.core.ICounted}
 * @implements {cljs.core.ISeqable}
 * @implements {cljs.core.IMeta}
 * @implements {cljs.core.ICloneable}
 * @implements {cljs.core.IDeref}
 * @implements {cljs.core.IPrintWithWriter}
 * @implements {cljs.core.IIterable}
 * @implements {cljs.core.IWithMeta}
 * @implements {cljs.core.IAssociative}
 * @implements {cljs.core.IMap}
 * @implements {cljs.core.ILookup}
*/
hyperfiddle.router.AtomHistory = (function (state,watches,max_size,__meta,__extmap,__hash){
this.state = state;
this.watches = watches;
this.max_size = max_size;
this.__meta = __meta;
this.__extmap = __extmap;
this.__hash = __hash;
this.cljs$lang$protocol_mask$partition0$ = 2230748938;
this.cljs$lang$protocol_mask$partition1$ = 253954;
});
(hyperfiddle.router.AtomHistory.prototype.cljs$core$ILookup$_lookup$arity$2 = (function (this__5343__auto__,k__5344__auto__){
var self__ = this;
var this__5343__auto____$1 = this;
return this__5343__auto____$1.cljs$core$ILookup$_lookup$arity$3(null,k__5344__auto__,null);
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$ILookup$_lookup$arity$3 = (function (this__5345__auto__,k57303,else__5346__auto__){
var self__ = this;
var this__5345__auto____$1 = this;
var G__57319 = k57303;
var G__57319__$1 = (((G__57319 instanceof cljs.core.Keyword))?G__57319.fqn:null);
switch (G__57319__$1) {
case "state":
return self__.state;

break;
case "watches":
return self__.watches;

break;
case "max-size":
return self__.max_size;

break;
default:
return cljs.core.get.cljs$core$IFn$_invoke$arity$3(self__.__extmap,k57303,else__5346__auto__);

}
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IKVReduce$_kv_reduce$arity$3 = (function (this__5363__auto__,f__5364__auto__,init__5365__auto__){
var self__ = this;
var this__5363__auto____$1 = this;
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3((function (ret__5366__auto__,p__57320){
var vec__57321 = p__57320;
var k__5367__auto__ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57321,(0),null);
var v__5368__auto__ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57321,(1),null);
return (f__5364__auto__.cljs$core$IFn$_invoke$arity$3 ? f__5364__auto__.cljs$core$IFn$_invoke$arity$3(ret__5366__auto__,k__5367__auto__,v__5368__auto__) : f__5364__auto__.call(null,ret__5366__auto__,k__5367__auto__,v__5368__auto__));
}),init__5365__auto__,this__5363__auto____$1);
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IPrintWithWriter$_pr_writer$arity$3 = (function (this__5358__auto__,writer__5359__auto__,opts__5360__auto__){
var self__ = this;
var this__5358__auto____$1 = this;
var pr_pair__5361__auto__ = (function (keyval__5362__auto__){
return cljs.core.pr_sequential_writer(writer__5359__auto__,cljs.core.pr_writer,""," ","",opts__5360__auto__,keyval__5362__auto__);
});
return cljs.core.pr_sequential_writer(writer__5359__auto__,pr_pair__5361__auto__,"#hyperfiddle.router.AtomHistory{",", ","}",opts__5360__auto__,cljs.core.concat.cljs$core$IFn$_invoke$arity$2(new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"state","state",-1988618099),self__.state],null)),(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"watches","watches",-273097535),self__.watches],null)),(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"max-size","max-size",-874966132),self__.max_size],null))], null),self__.__extmap));
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IIterable$_iterator$arity$1 = (function (G__57302){
var self__ = this;
var G__57302__$1 = this;
return (new cljs.core.RecordIter((0),G__57302__$1,3,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"state","state",-1988618099),new cljs.core.Keyword(null,"watches","watches",-273097535),new cljs.core.Keyword(null,"max-size","max-size",-874966132)], null),(cljs.core.truth_(self__.__extmap)?cljs.core._iterator(self__.__extmap):cljs.core.nil_iter())));
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IMeta$_meta$arity$1 = (function (this__5341__auto__){
var self__ = this;
var this__5341__auto____$1 = this;
return self__.__meta;
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$ICloneable$_clone$arity$1 = (function (this__5338__auto__){
var self__ = this;
var this__5338__auto____$1 = this;
return (new hyperfiddle.router.AtomHistory(self__.state,self__.watches,self__.max_size,self__.__meta,self__.__extmap,self__.__hash));
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$ICounted$_count$arity$1 = (function (this__5347__auto__){
var self__ = this;
var this__5347__auto____$1 = this;
return (3 + cljs.core.count(self__.__extmap));
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IHash$_hash$arity$1 = (function (this__5339__auto__){
var self__ = this;
var this__5339__auto____$1 = this;
var h__5154__auto__ = self__.__hash;
if((!((h__5154__auto__ == null)))){
return h__5154__auto__;
} else {
var h__5154__auto____$1 = (function (coll__5340__auto__){
return (-281146277 ^ cljs.core.hash_unordered_coll(coll__5340__auto__));
})(this__5339__auto____$1);
(self__.__hash = h__5154__auto____$1);

return h__5154__auto____$1;
}
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IEquiv$_equiv$arity$2 = (function (this57304,other57305){
var self__ = this;
var this57304__$1 = this;
return (((!((other57305 == null)))) && ((((this57304__$1.constructor === other57305.constructor)) && (((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this57304__$1.state,other57305.state)) && (((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this57304__$1.watches,other57305.watches)) && (((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this57304__$1.max_size,other57305.max_size)) && (cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this57304__$1.__extmap,other57305.__extmap)))))))))));
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IReset$_reset_BANG_$arity$2 = (function (this$,newval){
var self__ = this;
var this$__$1 = this;
return this$__$1.cljs$core$ISwap$_swap_BANG_$arity$2(null,cljs.core.constantly(newval));
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$2 = (function (this$,f){
var self__ = this;
var this$__$1 = this;
return hyperfiddle.router.notify_watches(this$__$1,cljs.core.swap_vals_BANG_.cljs$core$IFn$_invoke$arity$3(self__.state,hyperfiddle.router.updatef,f));
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$3 = (function (this$,f,arg){
var self__ = this;
var this$__$1 = this;
return this$__$1.cljs$core$ISwap$_swap_BANG_$arity$2(null,(function (p1__57298_SHARP_){
return (f.cljs$core$IFn$_invoke$arity$2 ? f.cljs$core$IFn$_invoke$arity$2(p1__57298_SHARP_,arg) : f.call(null,p1__57298_SHARP_,arg));
}));
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$4 = (function (this$,f,arg1,arg2){
var self__ = this;
var this$__$1 = this;
return this$__$1.cljs$core$ISwap$_swap_BANG_$arity$2(null,(function (p1__57299_SHARP_){
return (f.cljs$core$IFn$_invoke$arity$3 ? f.cljs$core$IFn$_invoke$arity$3(p1__57299_SHARP_,arg1,arg2) : f.call(null,p1__57299_SHARP_,arg1,arg2));
}));
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$5 = (function (this$,f,x,y,args){
var self__ = this;
var this$__$1 = this;
return this$__$1.cljs$core$ISwap$_swap_BANG_$arity$2(null,(function (p1__57300_SHARP_){
return cljs.core.apply.cljs$core$IFn$_invoke$arity$5(f,p1__57300_SHARP_,x,y,args);
}));
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IMap$_dissoc$arity$2 = (function (this__5353__auto__,k__5354__auto__){
var self__ = this;
var this__5353__auto____$1 = this;
if(cljs.core.contains_QMARK_(new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"watches","watches",-273097535),null,new cljs.core.Keyword(null,"max-size","max-size",-874966132),null,new cljs.core.Keyword(null,"state","state",-1988618099),null], null), null),k__5354__auto__)){
return cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(cljs.core._with_meta(cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,this__5353__auto____$1),self__.__meta),k__5354__auto__);
} else {
return (new hyperfiddle.router.AtomHistory(self__.state,self__.watches,self__.max_size,self__.__meta,cljs.core.not_empty(cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(self__.__extmap,k__5354__auto__)),null));
}
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IAssociative$_contains_key_QMARK_$arity$2 = (function (this__5350__auto__,k57303){
var self__ = this;
var this__5350__auto____$1 = this;
var G__57326 = k57303;
var G__57326__$1 = (((G__57326 instanceof cljs.core.Keyword))?G__57326.fqn:null);
switch (G__57326__$1) {
case "state":
case "watches":
case "max-size":
return true;

break;
default:
return cljs.core.contains_QMARK_(self__.__extmap,k57303);

}
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IAssociative$_assoc$arity$3 = (function (this__5351__auto__,k__5352__auto__,G__57302){
var self__ = this;
var this__5351__auto____$1 = this;
var pred__57327 = cljs.core.keyword_identical_QMARK_;
var expr__57328 = k__5352__auto__;
if(cljs.core.truth_((pred__57327.cljs$core$IFn$_invoke$arity$2 ? pred__57327.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"state","state",-1988618099),expr__57328) : pred__57327.call(null,new cljs.core.Keyword(null,"state","state",-1988618099),expr__57328)))){
return (new hyperfiddle.router.AtomHistory(G__57302,self__.watches,self__.max_size,self__.__meta,self__.__extmap,null));
} else {
if(cljs.core.truth_((pred__57327.cljs$core$IFn$_invoke$arity$2 ? pred__57327.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"watches","watches",-273097535),expr__57328) : pred__57327.call(null,new cljs.core.Keyword(null,"watches","watches",-273097535),expr__57328)))){
return (new hyperfiddle.router.AtomHistory(self__.state,G__57302,self__.max_size,self__.__meta,self__.__extmap,null));
} else {
if(cljs.core.truth_((pred__57327.cljs$core$IFn$_invoke$arity$2 ? pred__57327.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"max-size","max-size",-874966132),expr__57328) : pred__57327.call(null,new cljs.core.Keyword(null,"max-size","max-size",-874966132),expr__57328)))){
return (new hyperfiddle.router.AtomHistory(self__.state,self__.watches,G__57302,self__.__meta,self__.__extmap,null));
} else {
return (new hyperfiddle.router.AtomHistory(self__.state,self__.watches,self__.max_size,self__.__meta,cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(self__.__extmap,k__5352__auto__,G__57302),null));
}
}
}
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$ISeqable$_seq$arity$1 = (function (this__5356__auto__){
var self__ = this;
var this__5356__auto____$1 = this;
return cljs.core.seq(cljs.core.concat.cljs$core$IFn$_invoke$arity$2(new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(new cljs.core.MapEntry(new cljs.core.Keyword(null,"state","state",-1988618099),self__.state,null)),(new cljs.core.MapEntry(new cljs.core.Keyword(null,"watches","watches",-273097535),self__.watches,null)),(new cljs.core.MapEntry(new cljs.core.Keyword(null,"max-size","max-size",-874966132),self__.max_size,null))], null),self__.__extmap));
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IWatchable$_add_watch$arity$3 = (function (this$,key,callback){
var self__ = this;
var this$__$1 = this;
cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$4(self__.watches,cljs.core.assoc,key,callback);

return this$__$1;
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IWatchable$_remove_watch$arity$2 = (function (_,key){
var self__ = this;
var ___$1 = this;
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$3(self__.watches,cljs.core.dissoc,key);
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (this__5342__auto__,G__57302){
var self__ = this;
var this__5342__auto____$1 = this;
return (new hyperfiddle.router.AtomHistory(self__.state,self__.watches,self__.max_size,G__57302,self__.__extmap,self__.__hash));
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$ICollection$_conj$arity$2 = (function (this__5348__auto__,entry__5349__auto__){
var self__ = this;
var this__5348__auto____$1 = this;
if(cljs.core.vector_QMARK_(entry__5349__auto__)){
return this__5348__auto____$1.cljs$core$IAssociative$_assoc$arity$3(null,cljs.core._nth(entry__5349__auto__,(0)),cljs.core._nth(entry__5349__auto__,(1)));
} else {
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(cljs.core._conj,this__5348__auto____$1,entry__5349__auto__);
}
}));

(hyperfiddle.router.AtomHistory.prototype.cljs$core$IDeref$_deref$arity$1 = (function (_){
var self__ = this;
var ___$1 = this;
var vec__57332 = cljs.core.deref(self__.state);
var history__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57332,(0),null);
var idx = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57332,(1),null);
return cljs.core.get.cljs$core$IFn$_invoke$arity$2(history__$1,idx);
}));

(hyperfiddle.router.AtomHistory.getBasis = (function (){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol(null,"state","state",-348086572,null),new cljs.core.Symbol(null,"watches","watches",1367433992,null),new cljs.core.Symbol(null,"max-size","max-size",765565395,null)], null);
}));

(hyperfiddle.router.AtomHistory.cljs$lang$type = true);

(hyperfiddle.router.AtomHistory.cljs$lang$ctorPrSeq = (function (this__5389__auto__){
return (new cljs.core.List(null,"hyperfiddle.router/AtomHistory",null,(1),null));
}));

(hyperfiddle.router.AtomHistory.cljs$lang$ctorPrWriter = (function (this__5389__auto__,writer__5390__auto__){
return cljs.core._write(writer__5390__auto__,"hyperfiddle.router/AtomHistory");
}));

/**
 * Positional factory function for hyperfiddle.router/AtomHistory.
 */
hyperfiddle.router.__GT_AtomHistory = (function hyperfiddle$router$__GT_AtomHistory(state,watches,max_size){
return (new hyperfiddle.router.AtomHistory(state,watches,max_size,null,null,null));
});

/**
 * Factory function for hyperfiddle.router/AtomHistory, taking a map of keywords to field values.
 */
hyperfiddle.router.map__GT_AtomHistory = (function hyperfiddle$router$map__GT_AtomHistory(G__57307){
var extmap__5385__auto__ = (function (){var G__57336 = cljs.core.dissoc.cljs$core$IFn$_invoke$arity$variadic(G__57307,new cljs.core.Keyword(null,"state","state",-1988618099),cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([new cljs.core.Keyword(null,"watches","watches",-273097535),new cljs.core.Keyword(null,"max-size","max-size",-874966132)], 0));
if(cljs.core.record_QMARK_(G__57307)){
return cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,G__57336);
} else {
return G__57336;
}
})();
return (new hyperfiddle.router.AtomHistory(new cljs.core.Keyword(null,"state","state",-1988618099).cljs$core$IFn$_invoke$arity$1(G__57307),new cljs.core.Keyword(null,"watches","watches",-273097535).cljs$core$IFn$_invoke$arity$1(G__57307),new cljs.core.Keyword(null,"max-size","max-size",-874966132).cljs$core$IFn$_invoke$arity$1(G__57307),null,cljs.core.not_empty(extmap__5385__auto__),null));
});

(hyperfiddle.router.AtomHistory.prototype.hyperfiddle$router$IHistory$ = cljs.core.PROTOCOL_SENTINEL);

(hyperfiddle.router.AtomHistory.prototype.hyperfiddle$router$IHistory$navigate_BANG_$arity$2 = (function (this$,route){
var this$__$1 = this;
return hyperfiddle.router.notify_watches(this$__$1,cljs.core.swap_vals_BANG_.cljs$core$IFn$_invoke$arity$2(this$__$1.state,(function (p__57338){
var vec__57339 = p__57338;
var history__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57339,(0),null);
var idx = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57339,(1),null);
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(cljs.core.count(history__$1),this$__$1.max_size)){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.conj.cljs$core$IFn$_invoke$arity$2(cljs.core.subvec.cljs$core$IFn$_invoke$arity$2(history__$1,(1)),route),idx], null);
} else {
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.conj.cljs$core$IFn$_invoke$arity$2(cljs.core.subvec.cljs$core$IFn$_invoke$arity$3(history__$1,(0),(idx + (1))),route),(idx + (1))], null);
}
})));
}));

(hyperfiddle.router.AtomHistory.prototype.hyperfiddle$router$IHistory$back_BANG_$arity$1 = (function (this$){
var this$__$1 = this;
return hyperfiddle.router.notify_watches(this$__$1,cljs.core.swap_vals_BANG_.cljs$core$IFn$_invoke$arity$2(this$__$1.state,(function (p__57345){
var vec__57346 = p__57345;
var history__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57346,(0),null);
var idx = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57346,(1),null);
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [history__$1,(function (){var x__5130__auto__ = (idx - (1));
var y__5131__auto__ = (0);
return ((x__5130__auto__ > y__5131__auto__) ? x__5130__auto__ : y__5131__auto__);
})()], null);
})));
}));

(hyperfiddle.router.AtomHistory.prototype.hyperfiddle$router$IHistory$forward_BANG_$arity$1 = (function (this$){
var this$__$1 = this;
return hyperfiddle.router.notify_watches(this$__$1,cljs.core.swap_vals_BANG_.cljs$core$IFn$_invoke$arity$2(this$__$1.state,(function (p__57353){
var vec__57354 = p__57353;
var history__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57354,(0),null);
var idx = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57354,(1),null);
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [history__$1,(function (){var x__5133__auto__ = (idx + (1));
var y__5134__auto__ = (cljs.core.count(history__$1) - (1));
return ((x__5133__auto__ < y__5134__auto__) ? x__5133__auto__ : y__5134__auto__);
})()], null);
})));
}));

(hyperfiddle.router.AtomHistory.prototype.hyperfiddle$router$IHistory$replace_state_BANG_$arity$2 = (function (this$,new_state){
var this$__$1 = this;
return cljs.core.reset_BANG_(this$__$1,new_state);
}));
/**
 * Return a new IHistory instance backed by an atom.
 *   Initial history state can be provided with `initial-state`.
 *   Default history size is unbounded and can be constrained to `max-size` elements in a FIFO way.
 *   A negative value or 0 has no effect.
 */
hyperfiddle.router.atom_history = (function hyperfiddle$router$atom_history(var_args){
var G__57358 = arguments.length;
switch (G__57358) {
case 0:
return hyperfiddle.router.atom_history.cljs$core$IFn$_invoke$arity$0();

break;
case 1:
return hyperfiddle.router.atom_history.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return hyperfiddle.router.atom_history.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

(hyperfiddle.router.atom_history.cljs$core$IFn$_invoke$arity$0 = (function (){
return hyperfiddle.router.atom_history.cljs$core$IFn$_invoke$arity$2(null,(0));
}));

(hyperfiddle.router.atom_history.cljs$core$IFn$_invoke$arity$1 = (function (initial_state){
return hyperfiddle.router.atom_history.cljs$core$IFn$_invoke$arity$2(initial_state,(0));
}));

(hyperfiddle.router.atom_history.cljs$core$IFn$_invoke$arity$2 = (function (initial_state,max_size){
return hyperfiddle.router.__GT_AtomHistory(cljs.core.atom.cljs$core$IFn$_invoke$arity$1(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [initial_state], null),(0)], null)),cljs.core.atom.cljs$core$IFn$_invoke$arity$1(cljs.core.PersistentArrayMap.EMPTY),max_size);
}));

(hyperfiddle.router.atom_history.cljs$lang$maxFixedArity = 2);


/**
* @constructor
 * @implements {cljs.core.IRecord}
 * @implements {cljs.core.IWatchable}
 * @implements {cljs.core.IAtom}
 * @implements {cljs.core.IKVReduce}
 * @implements {cljs.core.IEquiv}
 * @implements {cljs.core.IHash}
 * @implements {cljs.core.ICollection}
 * @implements {cljs.core.IReset}
 * @implements {cljs.core.ISwap}
 * @implements {cljs.core.ICounted}
 * @implements {cljs.core.ISeqable}
 * @implements {cljs.core.IMeta}
 * @implements {cljs.core.ICloneable}
 * @implements {cljs.core.IDeref}
 * @implements {cljs.core.IPrintWithWriter}
 * @implements {cljs.core.IIterable}
 * @implements {cljs.core.IWithMeta}
 * @implements {cljs.core.IAssociative}
 * @implements {cljs.core.IMap}
 * @implements {cljs.core.ILookup}
*/
hyperfiddle.router.ProxyHistory = (function (parent,state,__meta,__extmap,__hash){
this.parent = parent;
this.state = state;
this.__meta = __meta;
this.__extmap = __extmap;
this.__hash = __hash;
this.cljs$lang$protocol_mask$partition0$ = 2230748938;
this.cljs$lang$protocol_mask$partition1$ = 253954;
});
(hyperfiddle.router.ProxyHistory.prototype.cljs$core$ILookup$_lookup$arity$2 = (function (this__5343__auto__,k__5344__auto__){
var self__ = this;
var this__5343__auto____$1 = this;
return this__5343__auto____$1.cljs$core$ILookup$_lookup$arity$3(null,k__5344__auto__,null);
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$ILookup$_lookup$arity$3 = (function (this__5345__auto__,k57364,else__5346__auto__){
var self__ = this;
var this__5345__auto____$1 = this;
var G__57368 = k57364;
var G__57368__$1 = (((G__57368 instanceof cljs.core.Keyword))?G__57368.fqn:null);
switch (G__57368__$1) {
case "parent":
return self__.parent;

break;
case "state":
return self__.state;

break;
default:
return cljs.core.get.cljs$core$IFn$_invoke$arity$3(self__.__extmap,k57364,else__5346__auto__);

}
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IKVReduce$_kv_reduce$arity$3 = (function (this__5363__auto__,f__5364__auto__,init__5365__auto__){
var self__ = this;
var this__5363__auto____$1 = this;
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3((function (ret__5366__auto__,p__57369){
var vec__57370 = p__57369;
var k__5367__auto__ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57370,(0),null);
var v__5368__auto__ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__57370,(1),null);
return (f__5364__auto__.cljs$core$IFn$_invoke$arity$3 ? f__5364__auto__.cljs$core$IFn$_invoke$arity$3(ret__5366__auto__,k__5367__auto__,v__5368__auto__) : f__5364__auto__.call(null,ret__5366__auto__,k__5367__auto__,v__5368__auto__));
}),init__5365__auto__,this__5363__auto____$1);
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IPrintWithWriter$_pr_writer$arity$3 = (function (this__5358__auto__,writer__5359__auto__,opts__5360__auto__){
var self__ = this;
var this__5358__auto____$1 = this;
var pr_pair__5361__auto__ = (function (keyval__5362__auto__){
return cljs.core.pr_sequential_writer(writer__5359__auto__,cljs.core.pr_writer,""," ","",opts__5360__auto__,keyval__5362__auto__);
});
return cljs.core.pr_sequential_writer(writer__5359__auto__,pr_pair__5361__auto__,"#hyperfiddle.router.ProxyHistory{",", ","}",opts__5360__auto__,cljs.core.concat.cljs$core$IFn$_invoke$arity$2(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"parent","parent",-878878779),self__.parent],null)),(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"state","state",-1988618099),self__.state],null))], null),self__.__extmap));
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IIterable$_iterator$arity$1 = (function (G__57363){
var self__ = this;
var G__57363__$1 = this;
return (new cljs.core.RecordIter((0),G__57363__$1,2,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"parent","parent",-878878779),new cljs.core.Keyword(null,"state","state",-1988618099)], null),(cljs.core.truth_(self__.__extmap)?cljs.core._iterator(self__.__extmap):cljs.core.nil_iter())));
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IMeta$_meta$arity$1 = (function (this__5341__auto__){
var self__ = this;
var this__5341__auto____$1 = this;
return self__.__meta;
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$ICloneable$_clone$arity$1 = (function (this__5338__auto__){
var self__ = this;
var this__5338__auto____$1 = this;
return (new hyperfiddle.router.ProxyHistory(self__.parent,self__.state,self__.__meta,self__.__extmap,self__.__hash));
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$ICounted$_count$arity$1 = (function (this__5347__auto__){
var self__ = this;
var this__5347__auto____$1 = this;
return (2 + cljs.core.count(self__.__extmap));
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IHash$_hash$arity$1 = (function (this__5339__auto__){
var self__ = this;
var this__5339__auto____$1 = this;
var h__5154__auto__ = self__.__hash;
if((!((h__5154__auto__ == null)))){
return h__5154__auto__;
} else {
var h__5154__auto____$1 = (function (coll__5340__auto__){
return (187769757 ^ cljs.core.hash_unordered_coll(coll__5340__auto__));
})(this__5339__auto____$1);
(self__.__hash = h__5154__auto____$1);

return h__5154__auto____$1;
}
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IEquiv$_equiv$arity$2 = (function (this57365,other57366){
var self__ = this;
var this57365__$1 = this;
return (((!((other57366 == null)))) && ((((this57365__$1.constructor === other57366.constructor)) && (((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this57365__$1.parent,other57366.parent)) && (((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this57365__$1.state,other57366.state)) && (cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this57365__$1.__extmap,other57366.__extmap)))))))));
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IReset$_reset_BANG_$arity$2 = (function (this$,newval){
var self__ = this;
var this$__$1 = this;
return cljs.core.reset_BANG_(self__.state,newval);
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$2 = (function (this$,f){
var self__ = this;
var this$__$1 = this;
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$2(self__.state,f);
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$3 = (function (this$,f,arg){
var self__ = this;
var this$__$1 = this;
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$3(self__.state,f,arg);
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$4 = (function (this$,f,arg1,arg2){
var self__ = this;
var this$__$1 = this;
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$4(self__.state,f,arg1,arg2);
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$5 = (function (this$,f,x,y,args){
var self__ = this;
var this$__$1 = this;
return cljs.core.apply.cljs$core$IFn$_invoke$arity$variadic(cljs.core.swap_BANG_,self__.state,f,x,y,cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([args], 0));
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IMap$_dissoc$arity$2 = (function (this__5353__auto__,k__5354__auto__){
var self__ = this;
var this__5353__auto____$1 = this;
if(cljs.core.contains_QMARK_(new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"parent","parent",-878878779),null,new cljs.core.Keyword(null,"state","state",-1988618099),null], null), null),k__5354__auto__)){
return cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(cljs.core._with_meta(cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,this__5353__auto____$1),self__.__meta),k__5354__auto__);
} else {
return (new hyperfiddle.router.ProxyHistory(self__.parent,self__.state,self__.__meta,cljs.core.not_empty(cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(self__.__extmap,k__5354__auto__)),null));
}
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IAssociative$_contains_key_QMARK_$arity$2 = (function (this__5350__auto__,k57364){
var self__ = this;
var this__5350__auto____$1 = this;
var G__57373 = k57364;
var G__57373__$1 = (((G__57373 instanceof cljs.core.Keyword))?G__57373.fqn:null);
switch (G__57373__$1) {
case "parent":
case "state":
return true;

break;
default:
return cljs.core.contains_QMARK_(self__.__extmap,k57364);

}
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IAssociative$_assoc$arity$3 = (function (this__5351__auto__,k__5352__auto__,G__57363){
var self__ = this;
var this__5351__auto____$1 = this;
var pred__57374 = cljs.core.keyword_identical_QMARK_;
var expr__57375 = k__5352__auto__;
if(cljs.core.truth_((pred__57374.cljs$core$IFn$_invoke$arity$2 ? pred__57374.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"parent","parent",-878878779),expr__57375) : pred__57374.call(null,new cljs.core.Keyword(null,"parent","parent",-878878779),expr__57375)))){
return (new hyperfiddle.router.ProxyHistory(G__57363,self__.state,self__.__meta,self__.__extmap,null));
} else {
if(cljs.core.truth_((pred__57374.cljs$core$IFn$_invoke$arity$2 ? pred__57374.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"state","state",-1988618099),expr__57375) : pred__57374.call(null,new cljs.core.Keyword(null,"state","state",-1988618099),expr__57375)))){
return (new hyperfiddle.router.ProxyHistory(self__.parent,G__57363,self__.__meta,self__.__extmap,null));
} else {
return (new hyperfiddle.router.ProxyHistory(self__.parent,self__.state,self__.__meta,cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(self__.__extmap,k__5352__auto__,G__57363),null));
}
}
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$ISeqable$_seq$arity$1 = (function (this__5356__auto__){
var self__ = this;
var this__5356__auto____$1 = this;
return cljs.core.seq(cljs.core.concat.cljs$core$IFn$_invoke$arity$2(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(new cljs.core.MapEntry(new cljs.core.Keyword(null,"parent","parent",-878878779),self__.parent,null)),(new cljs.core.MapEntry(new cljs.core.Keyword(null,"state","state",-1988618099),self__.state,null))], null),self__.__extmap));
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IWatchable$_add_watch$arity$3 = (function (this$,key,callback){
var self__ = this;
var this$__$1 = this;
cljs.core.add_watch(self__.state,key,callback);

return this$__$1;
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IWatchable$_remove_watch$arity$2 = (function (_,key){
var self__ = this;
var ___$1 = this;
return cljs.core.remove_watch(self__.state,key);
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (this__5342__auto__,G__57363){
var self__ = this;
var this__5342__auto____$1 = this;
return (new hyperfiddle.router.ProxyHistory(self__.parent,self__.state,G__57363,self__.__extmap,self__.__hash));
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$ICollection$_conj$arity$2 = (function (this__5348__auto__,entry__5349__auto__){
var self__ = this;
var this__5348__auto____$1 = this;
if(cljs.core.vector_QMARK_(entry__5349__auto__)){
return this__5348__auto____$1.cljs$core$IAssociative$_assoc$arity$3(null,cljs.core._nth(entry__5349__auto__,(0)),cljs.core._nth(entry__5349__auto__,(1)));
} else {
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(cljs.core._conj,this__5348__auto____$1,entry__5349__auto__);
}
}));

(hyperfiddle.router.ProxyHistory.prototype.cljs$core$IDeref$_deref$arity$1 = (function (_){
var self__ = this;
var ___$1 = this;
return cljs.core.deref(self__.state);
}));

(hyperfiddle.router.ProxyHistory.getBasis = (function (){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.with_meta(new cljs.core.Symbol(null,"parent","parent",761652748,null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Symbol(null,"IHistory","IHistory",-501436262,null)], null)),cljs.core.with_meta(new cljs.core.Symbol(null,"state","state",-348086572,null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Symbol(null,"IAtom","IAtom",-1411134312,null)], null))], null);
}));

(hyperfiddle.router.ProxyHistory.cljs$lang$type = true);

(hyperfiddle.router.ProxyHistory.cljs$lang$ctorPrSeq = (function (this__5389__auto__){
return (new cljs.core.List(null,"hyperfiddle.router/ProxyHistory",null,(1),null));
}));

(hyperfiddle.router.ProxyHistory.cljs$lang$ctorPrWriter = (function (this__5389__auto__,writer__5390__auto__){
return cljs.core._write(writer__5390__auto__,"hyperfiddle.router/ProxyHistory");
}));

/**
 * Positional factory function for hyperfiddle.router/ProxyHistory.
 */
hyperfiddle.router.__GT_ProxyHistory = (function hyperfiddle$router$__GT_ProxyHistory(parent,state){
return (new hyperfiddle.router.ProxyHistory(parent,state,null,null,null));
});

/**
 * Factory function for hyperfiddle.router/ProxyHistory, taking a map of keywords to field values.
 */
hyperfiddle.router.map__GT_ProxyHistory = (function hyperfiddle$router$map__GT_ProxyHistory(G__57367){
var extmap__5385__auto__ = (function (){var G__57378 = cljs.core.dissoc.cljs$core$IFn$_invoke$arity$variadic(G__57367,new cljs.core.Keyword(null,"parent","parent",-878878779),cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([new cljs.core.Keyword(null,"state","state",-1988618099)], 0));
if(cljs.core.record_QMARK_(G__57367)){
return cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,G__57378);
} else {
return G__57378;
}
})();
return (new hyperfiddle.router.ProxyHistory(new cljs.core.Keyword(null,"parent","parent",-878878779).cljs$core$IFn$_invoke$arity$1(G__57367),new cljs.core.Keyword(null,"state","state",-1988618099).cljs$core$IFn$_invoke$arity$1(G__57367),null,cljs.core.not_empty(extmap__5385__auto__),null));
});

(hyperfiddle.router.ProxyHistory.prototype.hyperfiddle$router$IHistory$ = cljs.core.PROTOCOL_SENTINEL);

(hyperfiddle.router.ProxyHistory.prototype.hyperfiddle$router$IHistory$navigate_BANG_$arity$2 = (function (this$,route){
var this$__$1 = this;
return hyperfiddle.router.navigate_BANG_(this$__$1.parent,route);
}));

(hyperfiddle.router.ProxyHistory.prototype.hyperfiddle$router$IHistory$back_BANG_$arity$1 = (function (this$){
var this$__$1 = this;
return hyperfiddle.router.back_BANG_(this$__$1.parent);
}));

(hyperfiddle.router.ProxyHistory.prototype.hyperfiddle$router$IHistory$forward_BANG_$arity$1 = (function (this$){
var this$__$1 = this;
return hyperfiddle.router.forward_BANG_(this$__$1.parent);
}));

(hyperfiddle.router.ProxyHistory.prototype.hyperfiddle$router$IHistory$replace_state_BANG_$arity$2 = (function (this$,new_state){
var this$__$1 = this;
return cljs.core.reset_BANG_(this$__$1,new_state);
}));
/**
 * Return a new IHistory instance backed by an atom.
 *   History state is stored in an atom.
 *   Navigation is forwarded to the `parent` history.
 *   Initial state is provided with `initial-state`. 
 */
hyperfiddle.router.proxy_history = (function hyperfiddle$router$proxy_history(var_args){
var G__57381 = arguments.length;
switch (G__57381) {
case 1:
return hyperfiddle.router.proxy_history.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return hyperfiddle.router.proxy_history.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

(hyperfiddle.router.proxy_history.cljs$core$IFn$_invoke$arity$1 = (function (parent){
return hyperfiddle.router.proxy_history.cljs$core$IFn$_invoke$arity$2(parent,null);
}));

(hyperfiddle.router.proxy_history.cljs$core$IFn$_invoke$arity$2 = (function (parent,initial_state){
return hyperfiddle.router.__GT_ProxyHistory(parent,cljs.core.atom.cljs$core$IFn$_invoke$arity$1(initial_state));
}));

(hyperfiddle.router.proxy_history.cljs$lang$maxFixedArity = 2);

hyperfiddle.router.history_QMARK_ = (function hyperfiddle$router$history_QMARK_(h){
var and__5043__auto__ = (((!((h == null))))?((((false) || ((cljs.core.PROTOCOL_SENTINEL === h.hyperfiddle$router$IHistory$))))?true:(((!h.cljs$lang$protocol_mask$partition$))?cljs.core.native_satisfies_QMARK_(hyperfiddle.router.IHistory,h):false)):cljs.core.native_satisfies_QMARK_(hyperfiddle.router.IHistory,h));
if(and__5043__auto__){
return (h instanceof cljs.core.IAtom);
} else {
return and__5043__auto__;
}
});
hyperfiddle.router.update_in_STAR_ = (function hyperfiddle$router$update_in_STAR_(var_args){
var args__5775__auto__ = [];
var len__5769__auto___57435 = arguments.length;
var i__5770__auto___57436 = (0);
while(true){
if((i__5770__auto___57436 < len__5769__auto___57435)){
args__5775__auto__.push((arguments[i__5770__auto___57436]));

var G__57437 = (i__5770__auto___57436 + (1));
i__5770__auto___57436 = G__57437;
continue;
} else {
}
break;
}

var argseq__5776__auto__ = ((((3) < args__5775__auto__.length))?(new cljs.core.IndexedSeq(args__5775__auto__.slice((3)),(0),null)):null);
return hyperfiddle.router.update_in_STAR_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),argseq__5776__auto__);
});

(hyperfiddle.router.update_in_STAR_.cljs$core$IFn$_invoke$arity$variadic = (function (m,path,f,args){
if(cljs.core.empty_QMARK_(path)){
return cljs.core.apply.cljs$core$IFn$_invoke$arity$3(f,m,args);
} else {
return cljs.core.apply.cljs$core$IFn$_invoke$arity$5(cljs.core.update_in,m,path,f,args);
}
}));

(hyperfiddle.router.update_in_STAR_.cljs$lang$maxFixedArity = (3));

/** @this {Function} */
(hyperfiddle.router.update_in_STAR_.cljs$lang$applyTo = (function (seq57389){
var G__57390 = cljs.core.first(seq57389);
var seq57389__$1 = cljs.core.next(seq57389);
var G__57391 = cljs.core.first(seq57389__$1);
var seq57389__$2 = cljs.core.next(seq57389__$1);
var G__57392 = cljs.core.first(seq57389__$2);
var seq57389__$3 = cljs.core.next(seq57389__$2);
var self__5754__auto__ = this;
return self__5754__auto__.cljs$core$IFn$_invoke$arity$variadic(G__57390,G__57391,G__57392,seq57389__$3);
}));

hyperfiddle.router.check_route_BANG_ = (function hyperfiddle$router$check_route_BANG_(route){
if((((route == null)) || (cljs.core.associative_QMARK_(route)))){
return null;
} else {
throw (new Error(["Assert failed: ",["A route should be an associative data structure. Given ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(route)].join(''),"\n","(or (nil? route) (associative? route))"].join('')));
}
});
hyperfiddle.router.default_platform_history = (function hyperfiddle$router$default_platform_history(){
return hyperfiddle.router.atom_history.cljs$core$IFn$_invoke$arity$0();
});
hyperfiddle.router.cleanup_on_unmount = (function hyperfiddle$router$cleanup_on_unmount(_BANG_history,path){
return missionary.core.observe((function (_BANG__SHARP_){
(_BANG__SHARP_.cljs$core$IFn$_invoke$arity$1 ? _BANG__SHARP_.cljs$core$IFn$_invoke$arity$1(null) : _BANG__SHARP_.call(null,null));

return (function (){
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$2(_BANG_history,(function (h){
if(cljs.core.empty_QMARK_(path)){
return h;
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2((1),cljs.core.count(path))){
return cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(h,cljs.core.first(path));
} else {
return hyperfiddle.router.update_in_STAR_.cljs$core$IFn$_invoke$arity$variadic(h,cljs.core.butlast(path),cljs.core.dissoc,cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([cljs.core.last(path)], 0));

}
}
}));
});
}));
});

//# sourceMappingURL=hyperfiddle.router.js.map

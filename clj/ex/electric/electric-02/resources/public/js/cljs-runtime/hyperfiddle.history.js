goog.provide('hyperfiddle.history');

/**
 * @interface
 */
hyperfiddle.history.IHistory = function(){};

var hyperfiddle$history$IHistory$navigate_BANG_$dyn_54527 = (function (this$,route){
var x__5393__auto__ = (((this$ == null))?null:this$);
var m__5394__auto__ = (hyperfiddle.history.navigate_BANG_[goog.typeOf(x__5393__auto__)]);
if((!((m__5394__auto__ == null)))){
return (m__5394__auto__.cljs$core$IFn$_invoke$arity$2 ? m__5394__auto__.cljs$core$IFn$_invoke$arity$2(this$,route) : m__5394__auto__.call(null,this$,route));
} else {
var m__5392__auto__ = (hyperfiddle.history.navigate_BANG_["_"]);
if((!((m__5392__auto__ == null)))){
return (m__5392__auto__.cljs$core$IFn$_invoke$arity$2 ? m__5392__auto__.cljs$core$IFn$_invoke$arity$2(this$,route) : m__5392__auto__.call(null,this$,route));
} else {
throw cljs.core.missing_protocol("IHistory.navigate!",this$);
}
}
});
hyperfiddle.history.navigate_BANG_ = (function hyperfiddle$history$navigate_BANG_(this$,route){
if((((!((this$ == null)))) && ((!((this$.hyperfiddle$history$IHistory$navigate_BANG_$arity$2 == null)))))){
return this$.hyperfiddle$history$IHistory$navigate_BANG_$arity$2(this$,route);
} else {
return hyperfiddle$history$IHistory$navigate_BANG_$dyn_54527(this$,route);
}
});

var hyperfiddle$history$IHistory$back_BANG_$dyn_54551 = (function (this$){
var x__5393__auto__ = (((this$ == null))?null:this$);
var m__5394__auto__ = (hyperfiddle.history.back_BANG_[goog.typeOf(x__5393__auto__)]);
if((!((m__5394__auto__ == null)))){
return (m__5394__auto__.cljs$core$IFn$_invoke$arity$1 ? m__5394__auto__.cljs$core$IFn$_invoke$arity$1(this$) : m__5394__auto__.call(null,this$));
} else {
var m__5392__auto__ = (hyperfiddle.history.back_BANG_["_"]);
if((!((m__5392__auto__ == null)))){
return (m__5392__auto__.cljs$core$IFn$_invoke$arity$1 ? m__5392__auto__.cljs$core$IFn$_invoke$arity$1(this$) : m__5392__auto__.call(null,this$));
} else {
throw cljs.core.missing_protocol("IHistory.back!",this$);
}
}
});
hyperfiddle.history.back_BANG_ = (function hyperfiddle$history$back_BANG_(this$){
if((((!((this$ == null)))) && ((!((this$.hyperfiddle$history$IHistory$back_BANG_$arity$1 == null)))))){
return this$.hyperfiddle$history$IHistory$back_BANG_$arity$1(this$);
} else {
return hyperfiddle$history$IHistory$back_BANG_$dyn_54551(this$);
}
});

var hyperfiddle$history$IHistory$forward_BANG_$dyn_54553 = (function (this$){
var x__5393__auto__ = (((this$ == null))?null:this$);
var m__5394__auto__ = (hyperfiddle.history.forward_BANG_[goog.typeOf(x__5393__auto__)]);
if((!((m__5394__auto__ == null)))){
return (m__5394__auto__.cljs$core$IFn$_invoke$arity$1 ? m__5394__auto__.cljs$core$IFn$_invoke$arity$1(this$) : m__5394__auto__.call(null,this$));
} else {
var m__5392__auto__ = (hyperfiddle.history.forward_BANG_["_"]);
if((!((m__5392__auto__ == null)))){
return (m__5392__auto__.cljs$core$IFn$_invoke$arity$1 ? m__5392__auto__.cljs$core$IFn$_invoke$arity$1(this$) : m__5392__auto__.call(null,this$));
} else {
throw cljs.core.missing_protocol("IHistory.forward!",this$);
}
}
});
hyperfiddle.history.forward_BANG_ = (function hyperfiddle$history$forward_BANG_(this$){
if((((!((this$ == null)))) && ((!((this$.hyperfiddle$history$IHistory$forward_BANG_$arity$1 == null)))))){
return this$.hyperfiddle$history$IHistory$forward_BANG_$arity$1(this$);
} else {
return hyperfiddle$history$IHistory$forward_BANG_$dyn_54553(this$);
}
});

var hyperfiddle$history$IHistory$replace_state_BANG_$dyn_54557 = (function (this$,new_state){
var x__5393__auto__ = (((this$ == null))?null:this$);
var m__5394__auto__ = (hyperfiddle.history.replace_state_BANG_[goog.typeOf(x__5393__auto__)]);
if((!((m__5394__auto__ == null)))){
return (m__5394__auto__.cljs$core$IFn$_invoke$arity$2 ? m__5394__auto__.cljs$core$IFn$_invoke$arity$2(this$,new_state) : m__5394__auto__.call(null,this$,new_state));
} else {
var m__5392__auto__ = (hyperfiddle.history.replace_state_BANG_["_"]);
if((!((m__5392__auto__ == null)))){
return (m__5392__auto__.cljs$core$IFn$_invoke$arity$2 ? m__5392__auto__.cljs$core$IFn$_invoke$arity$2(this$,new_state) : m__5392__auto__.call(null,this$,new_state));
} else {
throw cljs.core.missing_protocol("IHistory.replace-state!",this$);
}
}
});
hyperfiddle.history.replace_state_BANG_ = (function hyperfiddle$history$replace_state_BANG_(this$,new_state){
if((((!((this$ == null)))) && ((!((this$.hyperfiddle$history$IHistory$replace_state_BANG_$arity$2 == null)))))){
return this$.hyperfiddle$history$IHistory$replace_state_BANG_$arity$2(this$,new_state);
} else {
return hyperfiddle$history$IHistory$replace_state_BANG_$dyn_54557(this$,new_state);
}
});

hyperfiddle.history.updatef = (function hyperfiddle$history$updatef(p__54179,f){
var vec__54180 = p__54179;
var history__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54180,(0),null);
var idx = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54180,(1),null);
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.update.cljs$core$IFn$_invoke$arity$3(history__$1,idx,f),idx], null);
});
hyperfiddle.history.notify_watches = (function hyperfiddle$history$notify_watches(this$,p__54192){
var vec__54193 = p__54192;
var oldstate = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54193,(0),null);
var newstate = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54193,(1),null);
var oldval = cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.get,oldstate);
var newval = cljs.core.apply.cljs$core$IFn$_invoke$arity$2(cljs.core.get,newstate);
var seq__54196 = cljs.core.seq(cljs.core.deref(new cljs.core.Keyword(null,"watches","watches",-273097535).cljs$core$IFn$_invoke$arity$1(this$)));
var chunk__54197 = null;
var count__54198 = (0);
var i__54199 = (0);
while(true){
if((i__54199 < count__54198)){
var vec__54250 = chunk__54197.cljs$core$IIndexed$_nth$arity$2(null,i__54199);
var key = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54250,(0),null);
var callback = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54250,(1),null);
(callback.cljs$core$IFn$_invoke$arity$4 ? callback.cljs$core$IFn$_invoke$arity$4(key,this$,oldval,newval) : callback.call(null,key,this$,oldval,newval));


var G__54561 = seq__54196;
var G__54562 = chunk__54197;
var G__54563 = count__54198;
var G__54564 = (i__54199 + (1));
seq__54196 = G__54561;
chunk__54197 = G__54562;
count__54198 = G__54563;
i__54199 = G__54564;
continue;
} else {
var temp__5804__auto__ = cljs.core.seq(seq__54196);
if(temp__5804__auto__){
var seq__54196__$1 = temp__5804__auto__;
if(cljs.core.chunked_seq_QMARK_(seq__54196__$1)){
var c__5568__auto__ = cljs.core.chunk_first(seq__54196__$1);
var G__54569 = cljs.core.chunk_rest(seq__54196__$1);
var G__54570 = c__5568__auto__;
var G__54571 = cljs.core.count(c__5568__auto__);
var G__54572 = (0);
seq__54196 = G__54569;
chunk__54197 = G__54570;
count__54198 = G__54571;
i__54199 = G__54572;
continue;
} else {
var vec__54254 = cljs.core.first(seq__54196__$1);
var key = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54254,(0),null);
var callback = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54254,(1),null);
(callback.cljs$core$IFn$_invoke$arity$4 ? callback.cljs$core$IFn$_invoke$arity$4(key,this$,oldval,newval) : callback.call(null,key,this$,oldval,newval));


var G__54573 = cljs.core.next(seq__54196__$1);
var G__54574 = null;
var G__54575 = (0);
var G__54576 = (0);
seq__54196 = G__54573;
chunk__54197 = G__54574;
count__54198 = G__54575;
i__54199 = G__54576;
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
hyperfiddle.history.AtomHistory = (function (state,watches,max_size,__meta,__extmap,__hash){
this.state = state;
this.watches = watches;
this.max_size = max_size;
this.__meta = __meta;
this.__extmap = __extmap;
this.__hash = __hash;
this.cljs$lang$protocol_mask$partition0$ = 2230748938;
this.cljs$lang$protocol_mask$partition1$ = 253954;
});
(hyperfiddle.history.AtomHistory.prototype.cljs$core$ILookup$_lookup$arity$2 = (function (this__5343__auto__,k__5344__auto__){
var self__ = this;
var this__5343__auto____$1 = this;
return this__5343__auto____$1.cljs$core$ILookup$_lookup$arity$3(null,k__5344__auto__,null);
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$ILookup$_lookup$arity$3 = (function (this__5345__auto__,k54261,else__5346__auto__){
var self__ = this;
var this__5345__auto____$1 = this;
var G__54271 = k54261;
var G__54271__$1 = (((G__54271 instanceof cljs.core.Keyword))?G__54271.fqn:null);
switch (G__54271__$1) {
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
return cljs.core.get.cljs$core$IFn$_invoke$arity$3(self__.__extmap,k54261,else__5346__auto__);

}
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IKVReduce$_kv_reduce$arity$3 = (function (this__5363__auto__,f__5364__auto__,init__5365__auto__){
var self__ = this;
var this__5363__auto____$1 = this;
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3((function (ret__5366__auto__,p__54276){
var vec__54278 = p__54276;
var k__5367__auto__ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54278,(0),null);
var v__5368__auto__ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54278,(1),null);
return (f__5364__auto__.cljs$core$IFn$_invoke$arity$3 ? f__5364__auto__.cljs$core$IFn$_invoke$arity$3(ret__5366__auto__,k__5367__auto__,v__5368__auto__) : f__5364__auto__.call(null,ret__5366__auto__,k__5367__auto__,v__5368__auto__));
}),init__5365__auto__,this__5363__auto____$1);
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IPrintWithWriter$_pr_writer$arity$3 = (function (this__5358__auto__,writer__5359__auto__,opts__5360__auto__){
var self__ = this;
var this__5358__auto____$1 = this;
var pr_pair__5361__auto__ = (function (keyval__5362__auto__){
return cljs.core.pr_sequential_writer(writer__5359__auto__,cljs.core.pr_writer,""," ","",opts__5360__auto__,keyval__5362__auto__);
});
return cljs.core.pr_sequential_writer(writer__5359__auto__,pr_pair__5361__auto__,"#hyperfiddle.history.AtomHistory{",", ","}",opts__5360__auto__,cljs.core.concat.cljs$core$IFn$_invoke$arity$2(new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"state","state",-1988618099),self__.state],null)),(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"watches","watches",-273097535),self__.watches],null)),(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"max-size","max-size",-874966132),self__.max_size],null))], null),self__.__extmap));
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IIterable$_iterator$arity$1 = (function (G__54260){
var self__ = this;
var G__54260__$1 = this;
return (new cljs.core.RecordIter((0),G__54260__$1,3,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"state","state",-1988618099),new cljs.core.Keyword(null,"watches","watches",-273097535),new cljs.core.Keyword(null,"max-size","max-size",-874966132)], null),(cljs.core.truth_(self__.__extmap)?cljs.core._iterator(self__.__extmap):cljs.core.nil_iter())));
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IMeta$_meta$arity$1 = (function (this__5341__auto__){
var self__ = this;
var this__5341__auto____$1 = this;
return self__.__meta;
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$ICloneable$_clone$arity$1 = (function (this__5338__auto__){
var self__ = this;
var this__5338__auto____$1 = this;
return (new hyperfiddle.history.AtomHistory(self__.state,self__.watches,self__.max_size,self__.__meta,self__.__extmap,self__.__hash));
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$ICounted$_count$arity$1 = (function (this__5347__auto__){
var self__ = this;
var this__5347__auto____$1 = this;
return (3 + cljs.core.count(self__.__extmap));
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IHash$_hash$arity$1 = (function (this__5339__auto__){
var self__ = this;
var this__5339__auto____$1 = this;
var h__5154__auto__ = self__.__hash;
if((!((h__5154__auto__ == null)))){
return h__5154__auto__;
} else {
var h__5154__auto____$1 = (function (coll__5340__auto__){
return (-275901300 ^ cljs.core.hash_unordered_coll(coll__5340__auto__));
})(this__5339__auto____$1);
(self__.__hash = h__5154__auto____$1);

return h__5154__auto____$1;
}
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IEquiv$_equiv$arity$2 = (function (this54262,other54263){
var self__ = this;
var this54262__$1 = this;
return (((!((other54263 == null)))) && ((((this54262__$1.constructor === other54263.constructor)) && (((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this54262__$1.state,other54263.state)) && (((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this54262__$1.watches,other54263.watches)) && (((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this54262__$1.max_size,other54263.max_size)) && (cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this54262__$1.__extmap,other54263.__extmap)))))))))));
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IReset$_reset_BANG_$arity$2 = (function (this$,newval){
var self__ = this;
var this$__$1 = this;
return this$__$1.cljs$core$ISwap$_swap_BANG_$arity$2(null,cljs.core.constantly(newval));
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$2 = (function (this$,f){
var self__ = this;
var this$__$1 = this;
return hyperfiddle.history.notify_watches(this$__$1,cljs.core.swap_vals_BANG_.cljs$core$IFn$_invoke$arity$3(self__.state,hyperfiddle.history.updatef,f));
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$3 = (function (this$,f,arg){
var self__ = this;
var this$__$1 = this;
return this$__$1.cljs$core$ISwap$_swap_BANG_$arity$2(null,(function (p1__54257_SHARP_){
return (f.cljs$core$IFn$_invoke$arity$2 ? f.cljs$core$IFn$_invoke$arity$2(p1__54257_SHARP_,arg) : f.call(null,p1__54257_SHARP_,arg));
}));
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$4 = (function (this$,f,arg1,arg2){
var self__ = this;
var this$__$1 = this;
return this$__$1.cljs$core$ISwap$_swap_BANG_$arity$2(null,(function (p1__54258_SHARP_){
return (f.cljs$core$IFn$_invoke$arity$3 ? f.cljs$core$IFn$_invoke$arity$3(p1__54258_SHARP_,arg1,arg2) : f.call(null,p1__54258_SHARP_,arg1,arg2));
}));
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$5 = (function (this$,f,x,y,args){
var self__ = this;
var this$__$1 = this;
return this$__$1.cljs$core$ISwap$_swap_BANG_$arity$2(null,(function (p1__54259_SHARP_){
return cljs.core.apply.cljs$core$IFn$_invoke$arity$5(f,p1__54259_SHARP_,x,y,args);
}));
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IMap$_dissoc$arity$2 = (function (this__5353__auto__,k__5354__auto__){
var self__ = this;
var this__5353__auto____$1 = this;
if(cljs.core.contains_QMARK_(new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"watches","watches",-273097535),null,new cljs.core.Keyword(null,"max-size","max-size",-874966132),null,new cljs.core.Keyword(null,"state","state",-1988618099),null], null), null),k__5354__auto__)){
return cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(cljs.core._with_meta(cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,this__5353__auto____$1),self__.__meta),k__5354__auto__);
} else {
return (new hyperfiddle.history.AtomHistory(self__.state,self__.watches,self__.max_size,self__.__meta,cljs.core.not_empty(cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(self__.__extmap,k__5354__auto__)),null));
}
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IAssociative$_contains_key_QMARK_$arity$2 = (function (this__5350__auto__,k54261){
var self__ = this;
var this__5350__auto____$1 = this;
var G__54306 = k54261;
var G__54306__$1 = (((G__54306 instanceof cljs.core.Keyword))?G__54306.fqn:null);
switch (G__54306__$1) {
case "state":
case "watches":
case "max-size":
return true;

break;
default:
return cljs.core.contains_QMARK_(self__.__extmap,k54261);

}
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IAssociative$_assoc$arity$3 = (function (this__5351__auto__,k__5352__auto__,G__54260){
var self__ = this;
var this__5351__auto____$1 = this;
var pred__54309 = cljs.core.keyword_identical_QMARK_;
var expr__54310 = k__5352__auto__;
if(cljs.core.truth_((pred__54309.cljs$core$IFn$_invoke$arity$2 ? pred__54309.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"state","state",-1988618099),expr__54310) : pred__54309.call(null,new cljs.core.Keyword(null,"state","state",-1988618099),expr__54310)))){
return (new hyperfiddle.history.AtomHistory(G__54260,self__.watches,self__.max_size,self__.__meta,self__.__extmap,null));
} else {
if(cljs.core.truth_((pred__54309.cljs$core$IFn$_invoke$arity$2 ? pred__54309.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"watches","watches",-273097535),expr__54310) : pred__54309.call(null,new cljs.core.Keyword(null,"watches","watches",-273097535),expr__54310)))){
return (new hyperfiddle.history.AtomHistory(self__.state,G__54260,self__.max_size,self__.__meta,self__.__extmap,null));
} else {
if(cljs.core.truth_((pred__54309.cljs$core$IFn$_invoke$arity$2 ? pred__54309.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"max-size","max-size",-874966132),expr__54310) : pred__54309.call(null,new cljs.core.Keyword(null,"max-size","max-size",-874966132),expr__54310)))){
return (new hyperfiddle.history.AtomHistory(self__.state,self__.watches,G__54260,self__.__meta,self__.__extmap,null));
} else {
return (new hyperfiddle.history.AtomHistory(self__.state,self__.watches,self__.max_size,self__.__meta,cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(self__.__extmap,k__5352__auto__,G__54260),null));
}
}
}
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$ISeqable$_seq$arity$1 = (function (this__5356__auto__){
var self__ = this;
var this__5356__auto____$1 = this;
return cljs.core.seq(cljs.core.concat.cljs$core$IFn$_invoke$arity$2(new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(new cljs.core.MapEntry(new cljs.core.Keyword(null,"state","state",-1988618099),self__.state,null)),(new cljs.core.MapEntry(new cljs.core.Keyword(null,"watches","watches",-273097535),self__.watches,null)),(new cljs.core.MapEntry(new cljs.core.Keyword(null,"max-size","max-size",-874966132),self__.max_size,null))], null),self__.__extmap));
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IWatchable$_add_watch$arity$3 = (function (this$,key,callback){
var self__ = this;
var this$__$1 = this;
cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$4(self__.watches,cljs.core.assoc,key,callback);

return this$__$1;
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IWatchable$_remove_watch$arity$2 = (function (_,key){
var self__ = this;
var ___$1 = this;
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$3(self__.watches,cljs.core.dissoc,key);
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (this__5342__auto__,G__54260){
var self__ = this;
var this__5342__auto____$1 = this;
return (new hyperfiddle.history.AtomHistory(self__.state,self__.watches,self__.max_size,G__54260,self__.__extmap,self__.__hash));
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$ICollection$_conj$arity$2 = (function (this__5348__auto__,entry__5349__auto__){
var self__ = this;
var this__5348__auto____$1 = this;
if(cljs.core.vector_QMARK_(entry__5349__auto__)){
return this__5348__auto____$1.cljs$core$IAssociative$_assoc$arity$3(null,cljs.core._nth(entry__5349__auto__,(0)),cljs.core._nth(entry__5349__auto__,(1)));
} else {
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(cljs.core._conj,this__5348__auto____$1,entry__5349__auto__);
}
}));

(hyperfiddle.history.AtomHistory.prototype.cljs$core$IDeref$_deref$arity$1 = (function (_){
var self__ = this;
var ___$1 = this;
var vec__54312 = cljs.core.deref(self__.state);
var history__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54312,(0),null);
var idx = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54312,(1),null);
return cljs.core.get.cljs$core$IFn$_invoke$arity$2(history__$1,idx);
}));

(hyperfiddle.history.AtomHistory.getBasis = (function (){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol(null,"state","state",-348086572,null),new cljs.core.Symbol(null,"watches","watches",1367433992,null),new cljs.core.Symbol(null,"max-size","max-size",765565395,null)], null);
}));

(hyperfiddle.history.AtomHistory.cljs$lang$type = true);

(hyperfiddle.history.AtomHistory.cljs$lang$ctorPrSeq = (function (this__5389__auto__){
return (new cljs.core.List(null,"hyperfiddle.history/AtomHistory",null,(1),null));
}));

(hyperfiddle.history.AtomHistory.cljs$lang$ctorPrWriter = (function (this__5389__auto__,writer__5390__auto__){
return cljs.core._write(writer__5390__auto__,"hyperfiddle.history/AtomHistory");
}));

/**
 * Positional factory function for hyperfiddle.history/AtomHistory.
 */
hyperfiddle.history.__GT_AtomHistory = (function hyperfiddle$history$__GT_AtomHistory(state,watches,max_size){
return (new hyperfiddle.history.AtomHistory(state,watches,max_size,null,null,null));
});

/**
 * Factory function for hyperfiddle.history/AtomHistory, taking a map of keywords to field values.
 */
hyperfiddle.history.map__GT_AtomHistory = (function hyperfiddle$history$map__GT_AtomHistory(G__54268){
var extmap__5385__auto__ = (function (){var G__54315 = cljs.core.dissoc.cljs$core$IFn$_invoke$arity$variadic(G__54268,new cljs.core.Keyword(null,"state","state",-1988618099),cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([new cljs.core.Keyword(null,"watches","watches",-273097535),new cljs.core.Keyword(null,"max-size","max-size",-874966132)], 0));
if(cljs.core.record_QMARK_(G__54268)){
return cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,G__54315);
} else {
return G__54315;
}
})();
return (new hyperfiddle.history.AtomHistory(new cljs.core.Keyword(null,"state","state",-1988618099).cljs$core$IFn$_invoke$arity$1(G__54268),new cljs.core.Keyword(null,"watches","watches",-273097535).cljs$core$IFn$_invoke$arity$1(G__54268),new cljs.core.Keyword(null,"max-size","max-size",-874966132).cljs$core$IFn$_invoke$arity$1(G__54268),null,cljs.core.not_empty(extmap__5385__auto__),null));
});

(hyperfiddle.history.AtomHistory.prototype.hyperfiddle$history$IHistory$ = cljs.core.PROTOCOL_SENTINEL);

(hyperfiddle.history.AtomHistory.prototype.hyperfiddle$history$IHistory$navigate_BANG_$arity$2 = (function (this$,route){
var this$__$1 = this;
return hyperfiddle.history.notify_watches(this$__$1,cljs.core.swap_vals_BANG_.cljs$core$IFn$_invoke$arity$2(this$__$1.state,(function (p__54333){
var vec__54335 = p__54333;
var history__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54335,(0),null);
var idx = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54335,(1),null);
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(cljs.core.count(history__$1),this$__$1.max_size)){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.conj.cljs$core$IFn$_invoke$arity$2(cljs.core.subvec.cljs$core$IFn$_invoke$arity$2(history__$1,(1)),route),idx], null);
} else {
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.conj.cljs$core$IFn$_invoke$arity$2(cljs.core.subvec.cljs$core$IFn$_invoke$arity$3(history__$1,(0),(idx + (1))),route),(idx + (1))], null);
}
})));
}));

(hyperfiddle.history.AtomHistory.prototype.hyperfiddle$history$IHistory$back_BANG_$arity$1 = (function (this$){
var this$__$1 = this;
return hyperfiddle.history.notify_watches(this$__$1,cljs.core.swap_vals_BANG_.cljs$core$IFn$_invoke$arity$2(this$__$1.state,(function (p__54341){
var vec__54342 = p__54341;
var history__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54342,(0),null);
var idx = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54342,(1),null);
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [history__$1,(function (){var x__5130__auto__ = (idx - (1));
var y__5131__auto__ = (0);
return ((x__5130__auto__ > y__5131__auto__) ? x__5130__auto__ : y__5131__auto__);
})()], null);
})));
}));

(hyperfiddle.history.AtomHistory.prototype.hyperfiddle$history$IHistory$forward_BANG_$arity$1 = (function (this$){
var this$__$1 = this;
return hyperfiddle.history.notify_watches(this$__$1,cljs.core.swap_vals_BANG_.cljs$core$IFn$_invoke$arity$2(this$__$1.state,(function (p__54358){
var vec__54359 = p__54358;
var history__$1 = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54359,(0),null);
var idx = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54359,(1),null);
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [history__$1,(function (){var x__5133__auto__ = (idx + (1));
var y__5134__auto__ = (cljs.core.count(history__$1) - (1));
return ((x__5133__auto__ < y__5134__auto__) ? x__5133__auto__ : y__5134__auto__);
})()], null);
})));
}));

(hyperfiddle.history.AtomHistory.prototype.hyperfiddle$history$IHistory$replace_state_BANG_$arity$2 = (function (this$,new_state){
var this$__$1 = this;
return cljs.core.reset_BANG_(this$__$1,new_state);
}));
/**
 * Return a new IHistory instance backed by an atom.
 *   Initial history state can be provided with `initial-state`.
 *   Default history size is unbounded and can be constrained to `max-size` elements in a FIFO way.
 *   A negative value or 0 has no effect.
 */
hyperfiddle.history.atom_history = (function hyperfiddle$history$atom_history(var_args){
var G__54366 = arguments.length;
switch (G__54366) {
case 0:
return hyperfiddle.history.atom_history.cljs$core$IFn$_invoke$arity$0();

break;
case 1:
return hyperfiddle.history.atom_history.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return hyperfiddle.history.atom_history.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

(hyperfiddle.history.atom_history.cljs$core$IFn$_invoke$arity$0 = (function (){
return hyperfiddle.history.atom_history.cljs$core$IFn$_invoke$arity$2(null,(0));
}));

(hyperfiddle.history.atom_history.cljs$core$IFn$_invoke$arity$1 = (function (initial_state){
return hyperfiddle.history.atom_history.cljs$core$IFn$_invoke$arity$2(initial_state,(0));
}));

(hyperfiddle.history.atom_history.cljs$core$IFn$_invoke$arity$2 = (function (initial_state,max_size){
return hyperfiddle.history.__GT_AtomHistory(cljs.core.atom.cljs$core$IFn$_invoke$arity$1(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.PersistentVector(null, 1, 5, cljs.core.PersistentVector.EMPTY_NODE, [initial_state], null),(0)], null)),cljs.core.atom.cljs$core$IFn$_invoke$arity$1(cljs.core.PersistentArrayMap.EMPTY),max_size);
}));

(hyperfiddle.history.atom_history.cljs$lang$maxFixedArity = 2);


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
hyperfiddle.history.ProxyHistory = (function (parent,state,__meta,__extmap,__hash){
this.parent = parent;
this.state = state;
this.__meta = __meta;
this.__extmap = __extmap;
this.__hash = __hash;
this.cljs$lang$protocol_mask$partition0$ = 2230748938;
this.cljs$lang$protocol_mask$partition1$ = 253954;
});
(hyperfiddle.history.ProxyHistory.prototype.cljs$core$ILookup$_lookup$arity$2 = (function (this__5343__auto__,k__5344__auto__){
var self__ = this;
var this__5343__auto____$1 = this;
return this__5343__auto____$1.cljs$core$ILookup$_lookup$arity$3(null,k__5344__auto__,null);
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$ILookup$_lookup$arity$3 = (function (this__5345__auto__,k54369,else__5346__auto__){
var self__ = this;
var this__5345__auto____$1 = this;
var G__54379 = k54369;
var G__54379__$1 = (((G__54379 instanceof cljs.core.Keyword))?G__54379.fqn:null);
switch (G__54379__$1) {
case "parent":
return self__.parent;

break;
case "state":
return self__.state;

break;
default:
return cljs.core.get.cljs$core$IFn$_invoke$arity$3(self__.__extmap,k54369,else__5346__auto__);

}
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IKVReduce$_kv_reduce$arity$3 = (function (this__5363__auto__,f__5364__auto__,init__5365__auto__){
var self__ = this;
var this__5363__auto____$1 = this;
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3((function (ret__5366__auto__,p__54380){
var vec__54381 = p__54380;
var k__5367__auto__ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54381,(0),null);
var v__5368__auto__ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54381,(1),null);
return (f__5364__auto__.cljs$core$IFn$_invoke$arity$3 ? f__5364__auto__.cljs$core$IFn$_invoke$arity$3(ret__5366__auto__,k__5367__auto__,v__5368__auto__) : f__5364__auto__.call(null,ret__5366__auto__,k__5367__auto__,v__5368__auto__));
}),init__5365__auto__,this__5363__auto____$1);
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IPrintWithWriter$_pr_writer$arity$3 = (function (this__5358__auto__,writer__5359__auto__,opts__5360__auto__){
var self__ = this;
var this__5358__auto____$1 = this;
var pr_pair__5361__auto__ = (function (keyval__5362__auto__){
return cljs.core.pr_sequential_writer(writer__5359__auto__,cljs.core.pr_writer,""," ","",opts__5360__auto__,keyval__5362__auto__);
});
return cljs.core.pr_sequential_writer(writer__5359__auto__,pr_pair__5361__auto__,"#hyperfiddle.history.ProxyHistory{",", ","}",opts__5360__auto__,cljs.core.concat.cljs$core$IFn$_invoke$arity$2(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"parent","parent",-878878779),self__.parent],null)),(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"state","state",-1988618099),self__.state],null))], null),self__.__extmap));
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IIterable$_iterator$arity$1 = (function (G__54368){
var self__ = this;
var G__54368__$1 = this;
return (new cljs.core.RecordIter((0),G__54368__$1,2,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"parent","parent",-878878779),new cljs.core.Keyword(null,"state","state",-1988618099)], null),(cljs.core.truth_(self__.__extmap)?cljs.core._iterator(self__.__extmap):cljs.core.nil_iter())));
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IMeta$_meta$arity$1 = (function (this__5341__auto__){
var self__ = this;
var this__5341__auto____$1 = this;
return self__.__meta;
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$ICloneable$_clone$arity$1 = (function (this__5338__auto__){
var self__ = this;
var this__5338__auto____$1 = this;
return (new hyperfiddle.history.ProxyHistory(self__.parent,self__.state,self__.__meta,self__.__extmap,self__.__hash));
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$ICounted$_count$arity$1 = (function (this__5347__auto__){
var self__ = this;
var this__5347__auto____$1 = this;
return (2 + cljs.core.count(self__.__extmap));
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IHash$_hash$arity$1 = (function (this__5339__auto__){
var self__ = this;
var this__5339__auto____$1 = this;
var h__5154__auto__ = self__.__hash;
if((!((h__5154__auto__ == null)))){
return h__5154__auto__;
} else {
var h__5154__auto____$1 = (function (coll__5340__auto__){
return (1194936367 ^ cljs.core.hash_unordered_coll(coll__5340__auto__));
})(this__5339__auto____$1);
(self__.__hash = h__5154__auto____$1);

return h__5154__auto____$1;
}
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IEquiv$_equiv$arity$2 = (function (this54370,other54371){
var self__ = this;
var this54370__$1 = this;
return (((!((other54371 == null)))) && ((((this54370__$1.constructor === other54371.constructor)) && (((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this54370__$1.parent,other54371.parent)) && (((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this54370__$1.state,other54371.state)) && (cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this54370__$1.__extmap,other54371.__extmap)))))))));
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IReset$_reset_BANG_$arity$2 = (function (this$,newval){
var self__ = this;
var this$__$1 = this;
return cljs.core.reset_BANG_(self__.state,newval);
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$2 = (function (this$,f){
var self__ = this;
var this$__$1 = this;
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$2(self__.state,f);
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$3 = (function (this$,f,arg){
var self__ = this;
var this$__$1 = this;
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$3(self__.state,f,arg);
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$4 = (function (this$,f,arg1,arg2){
var self__ = this;
var this$__$1 = this;
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$4(self__.state,f,arg1,arg2);
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$ISwap$_swap_BANG_$arity$5 = (function (this$,f,x,y,args){
var self__ = this;
var this$__$1 = this;
return cljs.core.apply.cljs$core$IFn$_invoke$arity$variadic(cljs.core.swap_BANG_,self__.state,f,x,y,cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([args], 0));
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IMap$_dissoc$arity$2 = (function (this__5353__auto__,k__5354__auto__){
var self__ = this;
var this__5353__auto____$1 = this;
if(cljs.core.contains_QMARK_(new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"parent","parent",-878878779),null,new cljs.core.Keyword(null,"state","state",-1988618099),null], null), null),k__5354__auto__)){
return cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(cljs.core._with_meta(cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,this__5353__auto____$1),self__.__meta),k__5354__auto__);
} else {
return (new hyperfiddle.history.ProxyHistory(self__.parent,self__.state,self__.__meta,cljs.core.not_empty(cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(self__.__extmap,k__5354__auto__)),null));
}
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IAssociative$_contains_key_QMARK_$arity$2 = (function (this__5350__auto__,k54369){
var self__ = this;
var this__5350__auto____$1 = this;
var G__54385 = k54369;
var G__54385__$1 = (((G__54385 instanceof cljs.core.Keyword))?G__54385.fqn:null);
switch (G__54385__$1) {
case "parent":
case "state":
return true;

break;
default:
return cljs.core.contains_QMARK_(self__.__extmap,k54369);

}
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IAssociative$_assoc$arity$3 = (function (this__5351__auto__,k__5352__auto__,G__54368){
var self__ = this;
var this__5351__auto____$1 = this;
var pred__54386 = cljs.core.keyword_identical_QMARK_;
var expr__54387 = k__5352__auto__;
if(cljs.core.truth_((pred__54386.cljs$core$IFn$_invoke$arity$2 ? pred__54386.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"parent","parent",-878878779),expr__54387) : pred__54386.call(null,new cljs.core.Keyword(null,"parent","parent",-878878779),expr__54387)))){
return (new hyperfiddle.history.ProxyHistory(G__54368,self__.state,self__.__meta,self__.__extmap,null));
} else {
if(cljs.core.truth_((pred__54386.cljs$core$IFn$_invoke$arity$2 ? pred__54386.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"state","state",-1988618099),expr__54387) : pred__54386.call(null,new cljs.core.Keyword(null,"state","state",-1988618099),expr__54387)))){
return (new hyperfiddle.history.ProxyHistory(self__.parent,G__54368,self__.__meta,self__.__extmap,null));
} else {
return (new hyperfiddle.history.ProxyHistory(self__.parent,self__.state,self__.__meta,cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(self__.__extmap,k__5352__auto__,G__54368),null));
}
}
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$ISeqable$_seq$arity$1 = (function (this__5356__auto__){
var self__ = this;
var this__5356__auto____$1 = this;
return cljs.core.seq(cljs.core.concat.cljs$core$IFn$_invoke$arity$2(new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [(new cljs.core.MapEntry(new cljs.core.Keyword(null,"parent","parent",-878878779),self__.parent,null)),(new cljs.core.MapEntry(new cljs.core.Keyword(null,"state","state",-1988618099),self__.state,null))], null),self__.__extmap));
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IWatchable$_add_watch$arity$3 = (function (this$,key,callback){
var self__ = this;
var this$__$1 = this;
cljs.core.add_watch(self__.state,key,callback);

return this$__$1;
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IWatchable$_remove_watch$arity$2 = (function (_,key){
var self__ = this;
var ___$1 = this;
return cljs.core.remove_watch(self__.state,key);
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (this__5342__auto__,G__54368){
var self__ = this;
var this__5342__auto____$1 = this;
return (new hyperfiddle.history.ProxyHistory(self__.parent,self__.state,G__54368,self__.__extmap,self__.__hash));
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$ICollection$_conj$arity$2 = (function (this__5348__auto__,entry__5349__auto__){
var self__ = this;
var this__5348__auto____$1 = this;
if(cljs.core.vector_QMARK_(entry__5349__auto__)){
return this__5348__auto____$1.cljs$core$IAssociative$_assoc$arity$3(null,cljs.core._nth(entry__5349__auto__,(0)),cljs.core._nth(entry__5349__auto__,(1)));
} else {
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(cljs.core._conj,this__5348__auto____$1,entry__5349__auto__);
}
}));

(hyperfiddle.history.ProxyHistory.prototype.cljs$core$IDeref$_deref$arity$1 = (function (_){
var self__ = this;
var ___$1 = this;
return cljs.core.deref(self__.state);
}));

(hyperfiddle.history.ProxyHistory.getBasis = (function (){
return new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [cljs.core.with_meta(new cljs.core.Symbol(null,"parent","parent",761652748,null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Symbol(null,"IHistory","IHistory",-501436262,null)], null)),cljs.core.with_meta(new cljs.core.Symbol(null,"state","state",-348086572,null),new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"tag","tag",-1290361223),new cljs.core.Symbol(null,"IAtom","IAtom",-1411134312,null)], null))], null);
}));

(hyperfiddle.history.ProxyHistory.cljs$lang$type = true);

(hyperfiddle.history.ProxyHistory.cljs$lang$ctorPrSeq = (function (this__5389__auto__){
return (new cljs.core.List(null,"hyperfiddle.history/ProxyHistory",null,(1),null));
}));

(hyperfiddle.history.ProxyHistory.cljs$lang$ctorPrWriter = (function (this__5389__auto__,writer__5390__auto__){
return cljs.core._write(writer__5390__auto__,"hyperfiddle.history/ProxyHistory");
}));

/**
 * Positional factory function for hyperfiddle.history/ProxyHistory.
 */
hyperfiddle.history.__GT_ProxyHistory = (function hyperfiddle$history$__GT_ProxyHistory(parent,state){
return (new hyperfiddle.history.ProxyHistory(parent,state,null,null,null));
});

/**
 * Factory function for hyperfiddle.history/ProxyHistory, taking a map of keywords to field values.
 */
hyperfiddle.history.map__GT_ProxyHistory = (function hyperfiddle$history$map__GT_ProxyHistory(G__54376){
var extmap__5385__auto__ = (function (){var G__54389 = cljs.core.dissoc.cljs$core$IFn$_invoke$arity$variadic(G__54376,new cljs.core.Keyword(null,"parent","parent",-878878779),cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([new cljs.core.Keyword(null,"state","state",-1988618099)], 0));
if(cljs.core.record_QMARK_(G__54376)){
return cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,G__54389);
} else {
return G__54389;
}
})();
return (new hyperfiddle.history.ProxyHistory(new cljs.core.Keyword(null,"parent","parent",-878878779).cljs$core$IFn$_invoke$arity$1(G__54376),new cljs.core.Keyword(null,"state","state",-1988618099).cljs$core$IFn$_invoke$arity$1(G__54376),null,cljs.core.not_empty(extmap__5385__auto__),null));
});

(hyperfiddle.history.ProxyHistory.prototype.hyperfiddle$history$IHistory$ = cljs.core.PROTOCOL_SENTINEL);

(hyperfiddle.history.ProxyHistory.prototype.hyperfiddle$history$IHistory$navigate_BANG_$arity$2 = (function (this$,route){
var this$__$1 = this;
return hyperfiddle.history.navigate_BANG_(this$__$1.parent,route);
}));

(hyperfiddle.history.ProxyHistory.prototype.hyperfiddle$history$IHistory$back_BANG_$arity$1 = (function (this$){
var this$__$1 = this;
return hyperfiddle.history.back_BANG_(this$__$1.parent);
}));

(hyperfiddle.history.ProxyHistory.prototype.hyperfiddle$history$IHistory$forward_BANG_$arity$1 = (function (this$){
var this$__$1 = this;
return hyperfiddle.history.forward_BANG_(this$__$1.parent);
}));

(hyperfiddle.history.ProxyHistory.prototype.hyperfiddle$history$IHistory$replace_state_BANG_$arity$2 = (function (this$,new_state){
var this$__$1 = this;
return cljs.core.reset_BANG_(this$__$1,new_state);
}));
/**
 * Return a new IHistory instance backed by an atom.
 *   History state is stored in an atom.
 *   Navigation is forwarded to the `parent` history.
 *   Initial state is provided with `initial-state`. 
 */
hyperfiddle.history.proxy_history = (function hyperfiddle$history$proxy_history(var_args){
var G__54398 = arguments.length;
switch (G__54398) {
case 1:
return hyperfiddle.history.proxy_history.cljs$core$IFn$_invoke$arity$1((arguments[(0)]));

break;
case 2:
return hyperfiddle.history.proxy_history.cljs$core$IFn$_invoke$arity$2((arguments[(0)]),(arguments[(1)]));

break;
default:
throw (new Error(["Invalid arity: ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(arguments.length)].join('')));

}
});

(hyperfiddle.history.proxy_history.cljs$core$IFn$_invoke$arity$1 = (function (parent){
return hyperfiddle.history.proxy_history.cljs$core$IFn$_invoke$arity$2(parent,null);
}));

(hyperfiddle.history.proxy_history.cljs$core$IFn$_invoke$arity$2 = (function (parent,initial_state){
return hyperfiddle.history.__GT_ProxyHistory(parent,cljs.core.atom.cljs$core$IFn$_invoke$arity$1(initial_state));
}));

(hyperfiddle.history.proxy_history.cljs$lang$maxFixedArity = 2);

hyperfiddle.history.history_QMARK_ = (function hyperfiddle$history$history_QMARK_(h){
var and__5043__auto__ = (((!((h == null))))?((((false) || ((cljs.core.PROTOCOL_SENTINEL === h.hyperfiddle$history$IHistory$))))?true:(((!h.cljs$lang$protocol_mask$partition$))?cljs.core.native_satisfies_QMARK_(hyperfiddle.history.IHistory,h):false)):cljs.core.native_satisfies_QMARK_(hyperfiddle.history.IHistory,h));
if(and__5043__auto__){
return (h instanceof cljs.core.IAtom);
} else {
return and__5043__auto__;
}
});
hyperfiddle.history.update_in_STAR_ = (function hyperfiddle$history$update_in_STAR_(var_args){
var args__5775__auto__ = [];
var len__5769__auto___54603 = arguments.length;
var i__5770__auto___54604 = (0);
while(true){
if((i__5770__auto___54604 < len__5769__auto___54603)){
args__5775__auto__.push((arguments[i__5770__auto___54604]));

var G__54605 = (i__5770__auto___54604 + (1));
i__5770__auto___54604 = G__54605;
continue;
} else {
}
break;
}

var argseq__5776__auto__ = ((((3) < args__5775__auto__.length))?(new cljs.core.IndexedSeq(args__5775__auto__.slice((3)),(0),null)):null);
return hyperfiddle.history.update_in_STAR_.cljs$core$IFn$_invoke$arity$variadic((arguments[(0)]),(arguments[(1)]),(arguments[(2)]),argseq__5776__auto__);
});

(hyperfiddle.history.update_in_STAR_.cljs$core$IFn$_invoke$arity$variadic = (function (m,path,f,args){
if(cljs.core.empty_QMARK_(path)){
return cljs.core.apply.cljs$core$IFn$_invoke$arity$3(f,m,args);
} else {
return cljs.core.apply.cljs$core$IFn$_invoke$arity$5(cljs.core.update_in,m,path,f,args);
}
}));

(hyperfiddle.history.update_in_STAR_.cljs$lang$maxFixedArity = (3));

/** @this {Function} */
(hyperfiddle.history.update_in_STAR_.cljs$lang$applyTo = (function (seq54452){
var G__54456 = cljs.core.first(seq54452);
var seq54452__$1 = cljs.core.next(seq54452);
var G__54457 = cljs.core.first(seq54452__$1);
var seq54452__$2 = cljs.core.next(seq54452__$1);
var G__54458 = cljs.core.first(seq54452__$2);
var seq54452__$3 = cljs.core.next(seq54452__$2);
var self__5754__auto__ = this;
return self__5754__auto__.cljs$core$IFn$_invoke$arity$variadic(G__54456,G__54457,G__54458,seq54452__$3);
}));

hyperfiddle.history.check_route_BANG_ = (function hyperfiddle$history$check_route_BANG_(route){
if((((route == null)) || (cljs.core.associative_QMARK_(route)))){
return null;
} else {
throw (new Error(["Assert failed: ",["A route should be an associative data structure. Given ",cljs.core.str.cljs$core$IFn$_invoke$arity$1(route)].join(''),"\n","(or (nil? route) (associative? route))"].join('')));
}
});
hyperfiddle.history.default_platform_history = (function hyperfiddle$history$default_platform_history(){
return hyperfiddle.history.atom_history.cljs$core$IFn$_invoke$arity$0();
});
hyperfiddle.history.cleanup_on_unmount = (function hyperfiddle$history$cleanup_on_unmount(h,path){
if(cljs.core.empty_QMARK_(path)){
return h;
} else {
if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2((1),cljs.core.count(path))){
return cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(h,cljs.core.first(path));
} else {
return hyperfiddle.history.update_in_STAR_.cljs$core$IFn$_invoke$arity$variadic(h,cljs.core.butlast(path),cljs.core.dissoc,cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([cljs.core.last(path)], 0));

}
}
});
hyperfiddle.history.throttler = (function hyperfiddle$history$throttler(rate_ms){
var _BANG_nextf = cljs.core.atom.cljs$core$IFn$_invoke$arity$1(null);
var _BANG_running = cljs.core.atom.cljs$core$IFn$_invoke$arity$1(false);
return (function hyperfiddle$history$throttler_$_rec(f){
if(cljs.core.truth_(cljs.core.deref(_BANG_running))){
return cljs.core.reset_BANG_(_BANG_nextf,f);
} else {
cljs.core.reset_BANG_(_BANG_running,true);

(f.cljs$core$IFn$_invoke$arity$0 ? f.cljs$core$IFn$_invoke$arity$0() : f.call(null));

return window.setTimeout((function (){
cljs.core.reset_BANG_(_BANG_running,false);

var temp__5804__auto__ = cljs.core.deref(_BANG_nextf);
if(cljs.core.truth_(temp__5804__auto__)){
var nextf = temp__5804__auto__;
cljs.core.reset_BANG_(_BANG_nextf,null);

return hyperfiddle$history$throttler_$_rec(nextf);
} else {
return null;
}
}),rate_ms);
}
});
});

var throttle_54607 = hyperfiddle.history.throttler((300));
hyperfiddle.history.replaceState_BANG_ = (function hyperfiddle$history$replaceState_BANG_(path){
return throttle_54607((function (){
return window.history.replaceState(null,"",path);
}));
});

hyperfiddle.history.html5_pushState_BANG_ = (function hyperfiddle$history$html5_pushState_BANG_(path){
return window.history.pushState(null,"",path);
});

hyperfiddle.history.html5_back_BANG_ = (function hyperfiddle$history$html5_back_BANG_(){
return window.history.back();
});

hyperfiddle.history.html5_forward_BANG_ = (function hyperfiddle$history$html5_forward_BANG_(){
return window.history.forward();
});

hyperfiddle.history.html5_location = cljs.core.constantly(window.location);

hyperfiddle.history.html5_path = (function hyperfiddle$history$html5_path(){
var loc = hyperfiddle.history.html5_location();
return [cljs.core.str.cljs$core$IFn$_invoke$arity$1(loc.pathname),cljs.core.str.cljs$core$IFn$_invoke$arity$1(loc.search),cljs.core.str.cljs$core$IFn$_invoke$arity$1(loc.hash)].join('');
});


/**
* @constructor
 * @implements {cljs.core.IRecord}
 * @implements {cljs.core.IWatchable}
 * @implements {cljs.core.IAtom}
 * @implements {hyperfiddle.history.IHistory}
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
hyperfiddle.history.HTML5History = (function (encode,decode,_BANG_state,__meta,__extmap,__hash){
this.encode = encode;
this.decode = decode;
this._BANG_state = _BANG_state;
this.__meta = __meta;
this.__extmap = __extmap;
this.__hash = __hash;
this.cljs$lang$protocol_mask$partition0$ = 2230748938;
this.cljs$lang$protocol_mask$partition1$ = 253954;
});
(hyperfiddle.history.HTML5History.prototype.cljs$core$ILookup$_lookup$arity$2 = (function (this__5343__auto__,k__5344__auto__){
var self__ = this;
var this__5343__auto____$1 = this;
return this__5343__auto____$1.cljs$core$ILookup$_lookup$arity$3(null,k__5344__auto__,null);
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$ILookup$_lookup$arity$3 = (function (this__5345__auto__,k54506,else__5346__auto__){
var self__ = this;
var this__5345__auto____$1 = this;
var G__54510 = k54506;
var G__54510__$1 = (((G__54510 instanceof cljs.core.Keyword))?G__54510.fqn:null);
switch (G__54510__$1) {
case "encode":
return self__.encode;

break;
case "decode":
return self__.decode;

break;
case "!state":
return self__._BANG_state;

break;
default:
return cljs.core.get.cljs$core$IFn$_invoke$arity$3(self__.__extmap,k54506,else__5346__auto__);

}
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IKVReduce$_kv_reduce$arity$3 = (function (this__5363__auto__,f__5364__auto__,init__5365__auto__){
var self__ = this;
var this__5363__auto____$1 = this;
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3((function (ret__5366__auto__,p__54511){
var vec__54512 = p__54511;
var k__5367__auto__ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54512,(0),null);
var v__5368__auto__ = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54512,(1),null);
return (f__5364__auto__.cljs$core$IFn$_invoke$arity$3 ? f__5364__auto__.cljs$core$IFn$_invoke$arity$3(ret__5366__auto__,k__5367__auto__,v__5368__auto__) : f__5364__auto__.call(null,ret__5366__auto__,k__5367__auto__,v__5368__auto__));
}),init__5365__auto__,this__5363__auto____$1);
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IPrintWithWriter$_pr_writer$arity$3 = (function (this__5358__auto__,writer__5359__auto__,opts__5360__auto__){
var self__ = this;
var this__5358__auto____$1 = this;
var pr_pair__5361__auto__ = (function (keyval__5362__auto__){
return cljs.core.pr_sequential_writer(writer__5359__auto__,cljs.core.pr_writer,""," ","",opts__5360__auto__,keyval__5362__auto__);
});
return cljs.core.pr_sequential_writer(writer__5359__auto__,pr_pair__5361__auto__,"#hyperfiddle.history.HTML5History{",", ","}",opts__5360__auto__,cljs.core.concat.cljs$core$IFn$_invoke$arity$2(new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"encode","encode",-1753429702),self__.encode],null)),(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"decode","decode",-1306165281),self__.decode],null)),(new cljs.core.PersistentVector(null,2,(5),cljs.core.PersistentVector.EMPTY_NODE,[new cljs.core.Keyword(null,"!state","!state",1311412932),self__._BANG_state],null))], null),self__.__extmap));
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IIterable$_iterator$arity$1 = (function (G__54505){
var self__ = this;
var G__54505__$1 = this;
return (new cljs.core.RecordIter((0),G__54505__$1,3,new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"encode","encode",-1753429702),new cljs.core.Keyword(null,"decode","decode",-1306165281),new cljs.core.Keyword(null,"!state","!state",1311412932)], null),(cljs.core.truth_(self__.__extmap)?cljs.core._iterator(self__.__extmap):cljs.core.nil_iter())));
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IMeta$_meta$arity$1 = (function (this__5341__auto__){
var self__ = this;
var this__5341__auto____$1 = this;
return self__.__meta;
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$ICloneable$_clone$arity$1 = (function (this__5338__auto__){
var self__ = this;
var this__5338__auto____$1 = this;
return (new hyperfiddle.history.HTML5History(self__.encode,self__.decode,self__._BANG_state,self__.__meta,self__.__extmap,self__.__hash));
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$ICounted$_count$arity$1 = (function (this__5347__auto__){
var self__ = this;
var this__5347__auto____$1 = this;
return (3 + cljs.core.count(self__.__extmap));
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IHash$_hash$arity$1 = (function (this__5339__auto__){
var self__ = this;
var this__5339__auto____$1 = this;
var h__5154__auto__ = self__.__hash;
if((!((h__5154__auto__ == null)))){
return h__5154__auto__;
} else {
var h__5154__auto____$1 = (function (coll__5340__auto__){
return (132282510 ^ cljs.core.hash_unordered_coll(coll__5340__auto__));
})(this__5339__auto____$1);
(self__.__hash = h__5154__auto____$1);

return h__5154__auto____$1;
}
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IEquiv$_equiv$arity$2 = (function (this54507,other54508){
var self__ = this;
var this54507__$1 = this;
return (((!((other54508 == null)))) && ((((this54507__$1.constructor === other54508.constructor)) && (((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this54507__$1.encode,other54508.encode)) && (((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this54507__$1.decode,other54508.decode)) && (((cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this54507__$1._BANG_state,other54508._BANG_state)) && (cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(this54507__$1.__extmap,other54508.__extmap)))))))))));
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IReset$_reset_BANG_$arity$2 = (function (this$,newval){
var self__ = this;
var this$__$1 = this;
return this$__$1.cljs$core$ISwap$_swap_BANG_$arity$2(null,cljs.core.constantly(newval));
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$ISwap$_swap_BANG_$arity$2 = (function (this$,f){
var self__ = this;
var this$__$1 = this;
var vec__54515 = cljs.core.swap_vals_BANG_.cljs$core$IFn$_invoke$arity$2(self__._BANG_state,f);
var _oldval = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54515,(0),null);
var newval = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__54515,(1),null);
hyperfiddle.history.replaceState_BANG_((self__.encode.cljs$core$IFn$_invoke$arity$1 ? self__.encode.cljs$core$IFn$_invoke$arity$1(newval) : self__.encode.call(null,newval)));

return newval;
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$ISwap$_swap_BANG_$arity$3 = (function (this$,f,arg){
var self__ = this;
var this$__$1 = this;
return this$__$1.cljs$core$ISwap$_swap_BANG_$arity$2(null,(function (p1__54502_SHARP_){
return (f.cljs$core$IFn$_invoke$arity$2 ? f.cljs$core$IFn$_invoke$arity$2(p1__54502_SHARP_,arg) : f.call(null,p1__54502_SHARP_,arg));
}));
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$ISwap$_swap_BANG_$arity$4 = (function (this$,f,arg1,arg2){
var self__ = this;
var this$__$1 = this;
return this$__$1.cljs$core$ISwap$_swap_BANG_$arity$2(null,(function (p1__54503_SHARP_){
return (f.cljs$core$IFn$_invoke$arity$3 ? f.cljs$core$IFn$_invoke$arity$3(p1__54503_SHARP_,arg1,arg2) : f.call(null,p1__54503_SHARP_,arg1,arg2));
}));
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$ISwap$_swap_BANG_$arity$5 = (function (this$,f,x,y,args){
var self__ = this;
var this$__$1 = this;
return this$__$1.cljs$core$ISwap$_swap_BANG_$arity$2(null,(function (p1__54504_SHARP_){
return cljs.core.apply.cljs$core$IFn$_invoke$arity$5(f,p1__54504_SHARP_,x,y,args);
}));
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IMap$_dissoc$arity$2 = (function (this__5353__auto__,k__5354__auto__){
var self__ = this;
var this__5353__auto____$1 = this;
if(cljs.core.contains_QMARK_(new cljs.core.PersistentHashSet(null, new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"!state","!state",1311412932),null,new cljs.core.Keyword(null,"encode","encode",-1753429702),null,new cljs.core.Keyword(null,"decode","decode",-1306165281),null], null), null),k__5354__auto__)){
return cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(cljs.core._with_meta(cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,this__5353__auto____$1),self__.__meta),k__5354__auto__);
} else {
return (new hyperfiddle.history.HTML5History(self__.encode,self__.decode,self__._BANG_state,self__.__meta,cljs.core.not_empty(cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(self__.__extmap,k__5354__auto__)),null));
}
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IAssociative$_contains_key_QMARK_$arity$2 = (function (this__5350__auto__,k54506){
var self__ = this;
var this__5350__auto____$1 = this;
var G__54518 = k54506;
var G__54518__$1 = (((G__54518 instanceof cljs.core.Keyword))?G__54518.fqn:null);
switch (G__54518__$1) {
case "encode":
case "decode":
case "!state":
return true;

break;
default:
return cljs.core.contains_QMARK_(self__.__extmap,k54506);

}
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IAssociative$_assoc$arity$3 = (function (this__5351__auto__,k__5352__auto__,G__54505){
var self__ = this;
var this__5351__auto____$1 = this;
var pred__54519 = cljs.core.keyword_identical_QMARK_;
var expr__54520 = k__5352__auto__;
if(cljs.core.truth_((pred__54519.cljs$core$IFn$_invoke$arity$2 ? pred__54519.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"encode","encode",-1753429702),expr__54520) : pred__54519.call(null,new cljs.core.Keyword(null,"encode","encode",-1753429702),expr__54520)))){
return (new hyperfiddle.history.HTML5History(G__54505,self__.decode,self__._BANG_state,self__.__meta,self__.__extmap,null));
} else {
if(cljs.core.truth_((pred__54519.cljs$core$IFn$_invoke$arity$2 ? pred__54519.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"decode","decode",-1306165281),expr__54520) : pred__54519.call(null,new cljs.core.Keyword(null,"decode","decode",-1306165281),expr__54520)))){
return (new hyperfiddle.history.HTML5History(self__.encode,G__54505,self__._BANG_state,self__.__meta,self__.__extmap,null));
} else {
if(cljs.core.truth_((pred__54519.cljs$core$IFn$_invoke$arity$2 ? pred__54519.cljs$core$IFn$_invoke$arity$2(new cljs.core.Keyword(null,"!state","!state",1311412932),expr__54520) : pred__54519.call(null,new cljs.core.Keyword(null,"!state","!state",1311412932),expr__54520)))){
return (new hyperfiddle.history.HTML5History(self__.encode,self__.decode,G__54505,self__.__meta,self__.__extmap,null));
} else {
return (new hyperfiddle.history.HTML5History(self__.encode,self__.decode,self__._BANG_state,self__.__meta,cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(self__.__extmap,k__5352__auto__,G__54505),null));
}
}
}
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$ISeqable$_seq$arity$1 = (function (this__5356__auto__){
var self__ = this;
var this__5356__auto____$1 = this;
return cljs.core.seq(cljs.core.concat.cljs$core$IFn$_invoke$arity$2(new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [(new cljs.core.MapEntry(new cljs.core.Keyword(null,"encode","encode",-1753429702),self__.encode,null)),(new cljs.core.MapEntry(new cljs.core.Keyword(null,"decode","decode",-1306165281),self__.decode,null)),(new cljs.core.MapEntry(new cljs.core.Keyword(null,"!state","!state",1311412932),self__._BANG_state,null))], null),self__.__extmap));
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IWatchable$_add_watch$arity$3 = (function (this$,key,callback){
var self__ = this;
var this$__$1 = this;
cljs.core.add_watch(self__._BANG_state,key,callback);

return this$__$1;
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IWatchable$_remove_watch$arity$2 = (function (_,key){
var self__ = this;
var ___$1 = this;
return cljs.core.remove_watch(self__._BANG_state,key);
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IWithMeta$_with_meta$arity$2 = (function (this__5342__auto__,G__54505){
var self__ = this;
var this__5342__auto____$1 = this;
return (new hyperfiddle.history.HTML5History(self__.encode,self__.decode,self__._BANG_state,G__54505,self__.__extmap,self__.__hash));
}));

(hyperfiddle.history.HTML5History.prototype.hyperfiddle$history$IHistory$ = cljs.core.PROTOCOL_SENTINEL);

(hyperfiddle.history.HTML5History.prototype.hyperfiddle$history$IHistory$navigate_BANG_$arity$2 = (function (this$,route){
var self__ = this;
var this$__$1 = this;
hyperfiddle.history.html5_pushState_BANG_((self__.encode.cljs$core$IFn$_invoke$arity$1 ? self__.encode.cljs$core$IFn$_invoke$arity$1(route) : self__.encode.call(null,route)));

return cljs.core.reset_BANG_(this$__$1._BANG_state,route);
}));

(hyperfiddle.history.HTML5History.prototype.hyperfiddle$history$IHistory$back_BANG_$arity$1 = (function (this$){
var self__ = this;
var this$__$1 = this;
hyperfiddle.history.html5_back_BANG_();

return cljs.core.reset_BANG_(this$__$1._BANG_state,(function (){var G__54522 = hyperfiddle.history.html5_path();
return (self__.decode.cljs$core$IFn$_invoke$arity$1 ? self__.decode.cljs$core$IFn$_invoke$arity$1(G__54522) : self__.decode.call(null,G__54522));
})());
}));

(hyperfiddle.history.HTML5History.prototype.hyperfiddle$history$IHistory$forward_BANG_$arity$1 = (function (this$){
var self__ = this;
var this$__$1 = this;
hyperfiddle.history.html5_forward_BANG_();

return cljs.core.reset_BANG_(this$__$1._BANG_state,(function (){var G__54523 = hyperfiddle.history.html5_path();
return (self__.decode.cljs$core$IFn$_invoke$arity$1 ? self__.decode.cljs$core$IFn$_invoke$arity$1(G__54523) : self__.decode.call(null,G__54523));
})());
}));

(hyperfiddle.history.HTML5History.prototype.hyperfiddle$history$IHistory$replace_state_BANG_$arity$2 = (function (this$,new_state){
var self__ = this;
var this$__$1 = this;
return cljs.core.reset_BANG_(this$__$1,new_state);
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$ICollection$_conj$arity$2 = (function (this__5348__auto__,entry__5349__auto__){
var self__ = this;
var this__5348__auto____$1 = this;
if(cljs.core.vector_QMARK_(entry__5349__auto__)){
return this__5348__auto____$1.cljs$core$IAssociative$_assoc$arity$3(null,cljs.core._nth(entry__5349__auto__,(0)),cljs.core._nth(entry__5349__auto__,(1)));
} else {
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3(cljs.core._conj,this__5348__auto____$1,entry__5349__auto__);
}
}));

(hyperfiddle.history.HTML5History.prototype.cljs$core$IDeref$_deref$arity$1 = (function (_){
var self__ = this;
var ___$1 = this;
return cljs.core.deref(self__._BANG_state);
}));

(hyperfiddle.history.HTML5History.getBasis = (function (){
return new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Symbol(null,"encode","encode",-112898175,null),new cljs.core.Symbol(null,"decode","decode",334366246,null),new cljs.core.Symbol(null,"!state","!state",-1343022837,null)], null);
}));

(hyperfiddle.history.HTML5History.cljs$lang$type = true);

(hyperfiddle.history.HTML5History.cljs$lang$ctorPrSeq = (function (this__5389__auto__){
return (new cljs.core.List(null,"hyperfiddle.history/HTML5History",null,(1),null));
}));

(hyperfiddle.history.HTML5History.cljs$lang$ctorPrWriter = (function (this__5389__auto__,writer__5390__auto__){
return cljs.core._write(writer__5390__auto__,"hyperfiddle.history/HTML5History");
}));

/**
 * Positional factory function for hyperfiddle.history/HTML5History.
 */
hyperfiddle.history.__GT_HTML5History = (function hyperfiddle$history$__GT_HTML5History(encode,decode,_BANG_state){
return (new hyperfiddle.history.HTML5History(encode,decode,_BANG_state,null,null,null));
});

/**
 * Factory function for hyperfiddle.history/HTML5History, taking a map of keywords to field values.
 */
hyperfiddle.history.map__GT_HTML5History = (function hyperfiddle$history$map__GT_HTML5History(G__54509){
var extmap__5385__auto__ = (function (){var G__54524 = cljs.core.dissoc.cljs$core$IFn$_invoke$arity$variadic(G__54509,new cljs.core.Keyword(null,"encode","encode",-1753429702),cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([new cljs.core.Keyword(null,"decode","decode",-1306165281),new cljs.core.Keyword(null,"!state","!state",1311412932)], 0));
if(cljs.core.record_QMARK_(G__54509)){
return cljs.core.into.cljs$core$IFn$_invoke$arity$2(cljs.core.PersistentArrayMap.EMPTY,G__54524);
} else {
return G__54524;
}
})();
return (new hyperfiddle.history.HTML5History(new cljs.core.Keyword(null,"encode","encode",-1753429702).cljs$core$IFn$_invoke$arity$1(G__54509),new cljs.core.Keyword(null,"decode","decode",-1306165281).cljs$core$IFn$_invoke$arity$1(G__54509),new cljs.core.Keyword(null,"!state","!state",1311412932).cljs$core$IFn$_invoke$arity$1(G__54509),null,cljs.core.not_empty(extmap__5385__auto__),null));
});


hyperfiddle.history.html5_history = (function hyperfiddle$history$html5_history(encode,decode){
return hyperfiddle.history.__GT_HTML5History(encode,decode,cljs.core.atom.cljs$core$IFn$_invoke$arity$1((function (){var G__54525 = hyperfiddle.history.html5_path();
return (decode.cljs$core$IFn$_invoke$arity$1 ? decode.cljs$core$IFn$_invoke$arity$1(G__54525) : decode.call(null,G__54525));
})()));
});

hyperfiddle.history._html5_history_get_state = (function hyperfiddle$history$_html5_history_get_state(this$){
return this$._BANG_state;
});


//# sourceMappingURL=hyperfiddle.history.js.map

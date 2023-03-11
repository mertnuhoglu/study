goog.provide('demo.seven_gui_5_crud');
demo.seven_gui_5_crud._BANG_state = cljs.core.atom.cljs$core$IFn$_invoke$arity$1(new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"selected","selected",574897764),null,new cljs.core.Keyword(null,"stage","stage",1843544772),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"name","name",1843675177),"",new cljs.core.Keyword(null,"surname","surname",1407918027),""], null),new cljs.core.Keyword(null,"names","names",-1943074658),cljs.core.sorted_map.cljs$core$IFn$_invoke$arity$variadic(cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([(0),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"name","name",1843675177),"Emil",new cljs.core.Keyword(null,"surname","surname",1407918027),"Hans"], null)], 0))], null));
demo.seven_gui_5_crud.next_id = cljs.core.partial.cljs$core$IFn$_invoke$arity$3(cljs.core.swap_BANG_,cljs.core.atom.cljs$core$IFn$_invoke$arity$1((0)),cljs.core.inc);
demo.seven_gui_5_crud.select_BANG_ = (function demo$seven_gui_5_crud$select_BANG_(id){
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$2(demo.seven_gui_5_crud._BANG_state,(function (state){
return cljs.core.assoc.cljs$core$IFn$_invoke$arity$variadic(state,new cljs.core.Keyword(null,"selected","selected",574897764),id,cljs.core.prim_seq.cljs$core$IFn$_invoke$arity$2([new cljs.core.Keyword(null,"stage","stage",1843544772),cljs.core.get_in.cljs$core$IFn$_invoke$arity$2(state,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"names","names",-1943074658),id], null))], 0));
}));
});
demo.seven_gui_5_crud.set_name_BANG_ = (function demo$seven_gui_5_crud$set_name_BANG_(name){
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$4(demo.seven_gui_5_crud._BANG_state,cljs.core.assoc_in,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"stage","stage",1843544772),new cljs.core.Keyword(null,"name","name",1843675177)], null),name);
});
demo.seven_gui_5_crud.set_surname_BANG_ = (function demo$seven_gui_5_crud$set_surname_BANG_(surname){
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$4(demo.seven_gui_5_crud._BANG_state,cljs.core.assoc_in,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"stage","stage",1843544772),new cljs.core.Keyword(null,"surname","surname",1407918027)], null),surname);
});
demo.seven_gui_5_crud.create_BANG_ = (function demo$seven_gui_5_crud$create_BANG_(){
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$2(demo.seven_gui_5_crud._BANG_state,(function (p__37679){
var map__37684 = p__37679;
var map__37684__$1 = cljs.core.__destructure_map(map__37684);
var state = map__37684__$1;
var stage = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__37684__$1,new cljs.core.Keyword(null,"stage","stage",1843544772));
return cljs.core.assoc.cljs$core$IFn$_invoke$arity$3(cljs.core.update.cljs$core$IFn$_invoke$arity$5(state,new cljs.core.Keyword(null,"names","names",-1943074658),cljs.core.assoc,demo.seven_gui_5_crud.next_id(),stage),new cljs.core.Keyword(null,"stage","stage",1843544772),new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"name","name",1843675177),"",new cljs.core.Keyword(null,"surname","surname",1407918027),""], null));
}));
});
demo.seven_gui_5_crud.delete_BANG_ = (function demo$seven_gui_5_crud$delete_BANG_(){
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$2(demo.seven_gui_5_crud._BANG_state,(function (p__37708){
var map__37709 = p__37708;
var map__37709__$1 = cljs.core.__destructure_map(map__37709);
var state = map__37709__$1;
var selected = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__37709__$1,new cljs.core.Keyword(null,"selected","selected",574897764));
return cljs.core.update.cljs$core$IFn$_invoke$arity$4(state,new cljs.core.Keyword(null,"names","names",-1943074658),cljs.core.dissoc,selected);
}));
});
demo.seven_gui_5_crud.update_BANG_ = (function demo$seven_gui_5_crud$update_BANG_(){
return cljs.core.swap_BANG_.cljs$core$IFn$_invoke$arity$2(demo.seven_gui_5_crud._BANG_state,(function (p__37719){
var map__37720 = p__37719;
var map__37720__$1 = cljs.core.__destructure_map(map__37720);
var state = map__37720__$1;
var selected = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__37720__$1,new cljs.core.Keyword(null,"selected","selected",574897764));
var stage = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__37720__$1,new cljs.core.Keyword(null,"stage","stage",1843544772));
return cljs.core.assoc_in(state,new cljs.core.PersistentVector(null, 2, 5, cljs.core.PersistentVector.EMPTY_NODE, [new cljs.core.Keyword(null,"names","names",-1943074658),selected], null),stage);
}));
});
demo.seven_gui_5_crud.filter_names = (function demo$seven_gui_5_crud$filter_names(names_map,needle){
if(cljs.core.empty_QMARK_(needle)){
return names_map;
} else {
var needle__$1 = clojure.string.lower_case(needle);
return cljs.core.reduce_kv((function (r,k,p__37726){
var map__37727 = p__37726;
var map__37727__$1 = cljs.core.__destructure_map(map__37727);
var name = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__37727__$1,new cljs.core.Keyword(null,"name","name",1843675177));
var surname = cljs.core.get.cljs$core$IFn$_invoke$arity$2(map__37727__$1,new cljs.core.Keyword(null,"surname","surname",1407918027));
if(((clojure.string.includes_QMARK_(clojure.string.lower_case(name),needle__$1)) || (clojure.string.includes_QMARK_(clojure.string.lower_case(surname),needle__$1)))){
return r;
} else {
return cljs.core.dissoc.cljs$core$IFn$_invoke$arity$2(r,k);
}
}),names_map,names_map);
}
});

//# sourceMappingURL=demo.seven_gui_5_crud.js.map

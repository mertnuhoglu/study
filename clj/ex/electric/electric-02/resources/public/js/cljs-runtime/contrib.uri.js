goog.provide('contrib.uri');
contrib.uri.print_uri = (function contrib$uri$print_uri(o,w){
if(cljs.core.uri_QMARK_(o)){
} else {
throw (new Error("Assert failed: (uri? o)"));
}

var str_rep = (((o instanceof goog.Uri))?cljs.core.str.cljs$core$IFn$_invoke$arity$1(o):null);
return cljs.core._write(w,["#user/uri \"",str_rep,"\""].join(''));
});
(goog.Uri.prototype.cljs$core$IPrintWithWriter$ = cljs.core.PROTOCOL_SENTINEL);

(goog.Uri.prototype.cljs$core$IPrintWithWriter$_pr_writer$arity$3 = (function (o,writer,_){
var o__$1 = this;
return contrib.uri.print_uri(o__$1,writer);
}));

//# sourceMappingURL=contrib.uri.js.map

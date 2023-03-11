goog.provide('contrib.ednish');
contrib.ednish._edn_dialect_mappings = new cljs.core.PersistentArrayMap(null, 6, [" ",",","\"","'","#","~","/","!","[","(","]",")"], null);
/**
 * Re-encode an edn-string to url-safe dialect of edn-ish. Vectors, sets and maps
 * coalesce into lists and are not disambiguated.
 */
contrib.ednish.encode = (function contrib$ednish$encode(edn_str){
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3((function (a,p__37928){
var vec__37929 = p__37928;
var k = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__37929,(0),null);
var v = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__37929,(1),null);
return clojure.string.replace(a,k,v);
}),edn_str,contrib.ednish._edn_dialect_mappings);
});
contrib.ednish.decode = (function contrib$ednish$decode(ednish_str){
return cljs.core.reduce.cljs$core$IFn$_invoke$arity$3((function (a,p__37932){
var vec__37933 = p__37932;
var k = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__37933,(0),null);
var v = cljs.core.nth.cljs$core$IFn$_invoke$arity$3(vec__37933,(1),null);
return clojure.string.replace(a,k,v);
}),ednish_str,clojure.set.map_invert(contrib.ednish._edn_dialect_mappings));
});
contrib.ednish.encode_uri = cljs.core.comp.cljs$core$IFn$_invoke$arity$3(contrib.rfc3986.encode_pchar,contrib.ednish.encode,cljs.core.pr_str);
contrib.ednish.decode_uri = cljs.core.comp.cljs$core$IFn$_invoke$arity$3(clojure.edn.read_string,contrib.ednish.decode,contrib.rfc3986.decode_pchar);
contrib.ednish.decode_path = (function contrib$ednish$decode_path(path,read_edn_str){
if(typeof path === 'string'){
} else {
throw (new Error("Assert failed: (string? path)"));
}

if((!((read_edn_str == null)))){
} else {
throw (new Error("Assert failed: (some? read-edn-str)"));
}

if(cljs.core._EQ_.cljs$core$IFn$_invoke$arity$2(path,"/")){
return null;
} else {
var path__$1 = ((clojure.string.starts_with_QMARK_(path,"/"))?cljs.core.subs.cljs$core$IFn$_invoke$arity$2(path,(1)):path);
return contrib.ednish.decode_uri(path__$1);
}
});

//# sourceMappingURL=contrib.ednish.js.map

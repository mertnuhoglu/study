goog.provide('demo.seven_gui_2_temperature_converter');
demo.seven_gui_2_temperature_converter.c__GT_f = (function demo$seven_gui_2_temperature_converter$c__GT_f(c){
return ((c * ((9) / (5))) + (32));
});
demo.seven_gui_2_temperature_converter.f__GT_c = (function demo$seven_gui_2_temperature_converter$f__GT_c(f){
return ((f - (32)) * ((5) / (9)));
});
demo.seven_gui_2_temperature_converter.random_value = (function demo$seven_gui_2_temperature_converter$random_value(_){
return cljs.core.partial.cljs$core$IFn$_invoke$arity$2((function (){var cr37659_block_0 = (function demo$seven_gui_2_temperature_converter$random_value_$_cr37659_block_0(cr37659_state){
try{var cr37659_place_0 = missionary.core.sleep;
var cr37659_place_1 = (2000);
var cr37659_place_2 = (function (){var G__37718 = cr37659_place_1;
var fexpr__37717 = cr37659_place_0;
return (fexpr__37717.cljs$core$IFn$_invoke$arity$1 ? fexpr__37717.cljs$core$IFn$_invoke$arity$1(G__37718) : fexpr__37717.call(null,G__37718));
})();
(cr37659_state[(0)] = cr37659_block_1);

return missionary.core.park(cr37659_place_2);
}catch (e37715){var cr37659_exception = e37715;
(cr37659_state[(0)] = null);

throw cr37659_exception;
}});
var cr37659_block_1 = (function demo$seven_gui_2_temperature_converter$random_value_$_cr37659_block_1(cr37659_state){
try{var cr37659_place_3 = missionary.core.unpark();
var cr37659_place_4 = cljs.core.rand_int;
var cr37659_place_5 = (250);
var cr37659_place_6 = (function (){var G__37725 = cr37659_place_5;
var fexpr__37724 = cr37659_place_4;
return (fexpr__37724.cljs$core$IFn$_invoke$arity$1 ? fexpr__37724.cljs$core$IFn$_invoke$arity$1(G__37725) : fexpr__37724.call(null,G__37725));
})();
(cr37659_state[(0)] = null);

return cr37659_place_6;
}catch (e37722){var cr37659_exception = e37722;
(cr37659_state[(0)] = null);

throw cr37659_exception;
}});
return cloroutine.impl.coroutine((function (){var G__37728 = cljs.core.object_array.cljs$core$IFn$_invoke$arity$1((1));
(G__37728[(0)] = cr37659_block_0);

return G__37728;
})());
})(),missionary.core.sp_run);
});

//# sourceMappingURL=demo.seven_gui_2_temperature_converter.js.map

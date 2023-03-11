goog.provide('demo.demo_svg');
demo.demo_svg.wave = (function demo$demo_svg$wave(time){
return Math.cos(((cljs.core.mod(Math.round((time / (10))),(360)) * Math.PI) / (180)));
});

//# sourceMappingURL=demo.demo_svg.js.map

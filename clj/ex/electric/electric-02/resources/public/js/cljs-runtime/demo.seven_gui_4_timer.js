goog.provide('demo.seven_gui_4_timer');
demo.seven_gui_4_timer.initial_goal = (10);
demo.seven_gui_4_timer.seconds = (function demo$seven_gui_4_timer$seconds(milliseconds){
return (Math.floor((milliseconds / (100))) / (10));
});
demo.seven_gui_4_timer.second_precision = (function demo$seven_gui_4_timer$second_precision(milliseconds){
return (Math.floor((milliseconds / (1000))) * (1000));
});
demo.seven_gui_4_timer.now = (function demo$seven_gui_4_timer$now(){
return demo.seven_gui_4_timer.second_precision(Date.now());
});

//# sourceMappingURL=demo.seven_gui_4_timer.js.map

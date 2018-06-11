var Tuple = (function () {
    function Tuple(item1, item2) {
        this.item1 = item1;
        this.item2 = item2;
    }
    return Tuple;
}());
var pairToTuple = function (p) {
    return new Tuple(p.item1, p.item2);
};
var tuple = pairToTuple({ item1: "hello", item2: "world" });
//# sourceMappingURL=ex06.js.map
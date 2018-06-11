function genericFunc(argument) {
    var arrayOfT = [];
    arrayOfT.push(argument);
    return arrayOfT;
}
var arrayFromString = genericFunc("beep");
console.log(arrayFromString[0]);
console.log(typeof arrayFromString[0]);
var arrayFromNumber = genericFunc(42);
console.log(arrayFromNumber[0]);
console.log(typeof arrayFromNumber[0]);
//# sourceMappingURL=ex06.js.map
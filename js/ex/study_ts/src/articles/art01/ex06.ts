function genericFunc<T>(argument: T): T[] {    
  var arrayOfT: T[] = [];    
  arrayOfT.push(argument);   
  return arrayOfT;
}

var arrayFromString = genericFunc<string>("beep");
console.log(arrayFromString[0]);         
console.log(typeof arrayFromString[0])   

var arrayFromNumber = genericFunc(42);
console.log(arrayFromNumber[0]);         
console.log(typeof arrayFromNumber[0])   
// beep
// string
// 42
// number

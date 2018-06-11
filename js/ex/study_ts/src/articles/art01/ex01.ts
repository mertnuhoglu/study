var burger: string = 'hamburger',     // String
    calories: number = 300,           // Numeric
    tasty: boolean = true;            // Boolean

// Alternatively, you can omit the type declaration:
// var burger = 'hamburger';

// The function expects a string and an integer.
// It doesn't return anything so the type of the function itself is void.

export function speak(food: string, energy: number): void {
  console.log("Our " + food + " has " + energy + " calories.");
}
var x = 3
var y = 3 + x

speak(burger, calories);

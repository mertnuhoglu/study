interface Food {
    name: string;
    calories: number;
}
function speak(food: Food): void{
  console.log(food.name + " has to have " + food.calories);
}

let ice_cream = {
  name: "ice cream",
  calories: 200
}
speak(ice_cream);
//> ice cream has 200

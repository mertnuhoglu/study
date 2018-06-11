function speak(food: string, energy: number): void {
  console.log("Our " + food + " has " + energy + " calories.");
}
speak("hamburger", "a lot");
//> ../../projects/study/js/ex/study_ts/art01/ex03.ts (4,20): Argument of type '"a lot"' is not assignable to parameter of type 'number'. (2345)

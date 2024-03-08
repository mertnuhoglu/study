const int count = 2;
//const int pin[count] = {2, 3, 4, 5};
const int pin[count] = {2, 3};
const float distance = 0.5; // Sensörler arasındaki mesafe (metre cinsinden)

unsigned long time1;
unsigned long time2;
unsigned long time3;
bool ball = false;
float acceleration;
int state = 1;
bool e1 = false;
bool e2 = false;
bool e3 = false;

void setup() {  
  Serial.begin(9600);
  for (int i = 0; i < count; i++) {
    pinMode(pin[i], INPUT_PULLUP);
  }
}


void loop() {
  if (digitalRead(pin[0])== HIGH) {
    e1 = true;
    Serial.println("object");
  } else {
    e1 = false;
    Serial.println("no object");
  }
  
  if (digitalRead(pin[1])== HIGH) {
    e2 = true;
  } else {
    e2 = false;
  }
  
 //if (digitalRead(pin[2])== HIGH) {
   // e3 = true;
  //} else {
    //e3 = false;
    //}
  
  if (state == 1){
    if (e1 == true){
      state = 2;
      Serial.println("state=2");
    }
  }
  if (state == 2){
    if (e1 == false){
      state = 3;
      Serial.println("state=3");
      time1 = micros();
      Serial.print("time1: ");
      Serial.println(time1);
    }
  }
  if (state == 3){
    if (e2 == true){
      state = 4;
      Serial.println("state=4");
    }
  }
  if (state == 4){
    if (e2 == false){
      state = 1;
      Serial.println("state=1 back");
      time2 = micros();
      Serial.print("time2: ");
      Serial.println(time2);
      float diff = ((float) (time2 - time1) / 1000000);
      Serial.print("diff: ");
      Serial.println(diff);
      float velocity = distance / diff;
      Serial.print("velocity (m/s): ");
      Serial.println(velocity);
    }
  }
  //if (state == 5){
    //if (e3 == true){
      //state = 6;
      //Serial.print("state=6");
    //}
  //}
   //if (state == 6){
    //if (e3 == false){
      //state = 1;
      //Serial.print("state=1*");
    //}
  //}
 
 
 
 
    
  }



 

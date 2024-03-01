//const int sensorCount = 4;
const int sensorCount = 1;
//const int sensorPins[sensorCount] = {2, 3, 4, 5};
const int sensorPins[sensorCount] = {2};
const float distanceBetweenSensors = 0.5; // Sensörler arasındaki mesafe (metre cinsinden)

unsigned long sensorTimes[sensorCount];
bool ball = false;
float acceleration;

void setup() {
  Serial.begin(9600);
  for (int i = 0; i < sensorCount; i++) {
    pinMode(sensorPins[i], INPUT_PULLUP);
  }
}

void loop() {
	Serial.println(" m/s^2");
  if (!ball) {
		Serial.println("1");
    bool event = false;
    for (int i = 0; i < sensorCount; i++) {
      if (digitalRead(sensorPins[i]) == HIGH) {
				Serial.print("2");
        event = true;
        break;
      } 
    }
    if (event) {
      ball = true;
      for (int i = 0; i < sensorCount; i++) {
        sensorTimes[i] = micros();
      }
      Serial.println("Ball detected. Starting measurement.");
    }
  }

	for (int i = 0; i < sensorCount; i++) {
		if (digitalRead(sensorPins[i]) == HIGH) {
			Serial.print("HIGH: Reflected");
		} else {
			Serial.print("LOW: None");
		}
	}
	delay(1000);
}

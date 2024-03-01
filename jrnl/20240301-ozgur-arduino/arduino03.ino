const int sensorCount = 4;
const int sensorPins[sensorCount] = {2, 3, 4, 5};
const float distanceBetweenSensors = 0.5; // Sensörler arasındaki mesafe (metre cinsinden)

unsigned long sensorTimes[sensorCount];
bool ballDetected = false;
float acceleration;

void setup() {
  Serial.begin(9600);
  for (int i = 0; i < sensorCount; i++) {
    pinMode(sensorPins[i], INPUT_PULLUP);
  }
}

void loop() {
	Serial.print("Acceleration: ");
	Serial.println(" m/s^2");
	for (int i = 0; i < sensorCount; i++) {
		if (digitalRead(sensorPins[i]) == HIGH) {
			Serial.print("HIGH");
		} else {
			Serial.print("LOW");
		}
	}
}

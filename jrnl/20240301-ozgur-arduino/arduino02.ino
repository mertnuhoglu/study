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
  if (!ballDetected) {
    bool detected = true;
    for (int i = 0; i < sensorCount; i++) {
      if (digitalRead(sensorPins[i]) == HIGH) {
        detected = false;
        break;
      } 
    }
    if (detected) {
      ballDetected = true;
      for (int i = 0; i < sensorCount; i++) {
        sensorTimes[i] = micros();
      }
      Serial.println("Ball detected. Starting measurement.");
    }
  }
  else {
    bool detected = false;
    for (int i = 0; i < sensorCount; i++) {
      if (digitalRead(sensorPins[i]) == HIGH) {
        detected = true;
        break;
      }
    }
    if (detected) {
      unsigned long totalTime = micros() - sensorTimes[0];
      float position[sensorCount];
      float time[sensorCount];
      for (int i = 0; i < sensorCount; i++) {
        position[i] = i * distanceBetweenSensors;
        time[i] = (sensorTimes[i] - sensorTimes[0]) / 1000000.0;
      }
      float a[sensorCount-2];
      for (int i = 0; i < sensorCount-2; i++) {
        a[i] = 2 * (position[i+2] - 2*position[i+1] + position[i]) / ((time[i+2]-time[i+1])*(time[i+1]-time[i]));
      }
      acceleration = (a[0] + a[1]) / 2;
      Serial.print("Acceleration: ");
      Serial.print(acceleration);
      Serial.println(" m/s^2");
      ballDetected = false;
    }
  }
}

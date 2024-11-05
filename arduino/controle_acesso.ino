#include <Servo.h>

Servo motor;

void setup()
{
  Serial.begin(9600);
  pinMode(5, OUTPUT); // LED verde 
  pinMode(6, OUTPUT); // LED vermelho
  pinMode(7, OUTPUT); // Buzzer
  attach(8); // Servo Motor
}

void loop()
{
   if (Serial.available() > 0) {
    char command = Serial.read(); // Lê o byte enviado pelo Java

    if (command == '1') 
      digitalWrite(5, HIGH);
      digitalWrite(5, LOW);
	motor.write(90);

    else if (command == '0')
      digitalWrite(6, HIGH);
      digitalWrite(6, LOW);
		motor.write(0);    
  }
}
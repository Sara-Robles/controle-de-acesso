#include <Servo.h>

const int LED_VERDE = 3;
const int LED_VERMELHO = 6;
const int BUZZER = 10;
const int SERVO = 13;

Servo motor;

void setup() {
  Serial.begin(9600);
  
  pinMode(LED_VERDE, OUTPUT); 
  digitalWrite(LED_VERDE, LOW);
  
  pinMode(LED_VERMELHO, OUTPUT); 
  digitalWrite(LED_VERMELHO, LOW);
  
  pinMode(BUZZER, OUTPUT); 
  noTone(BUZZER);
  
  motor.attach(SERVO);
  motor.write(0);  
}

void loop() {

  if (Serial.available() > 0) {
    char comando = Serial.read(); // Lê o byte enviado pelo Java

    // Acesso Liberado
    if (comando == '1') {
      digitalWrite(LED_VERDE, HIGH);
      delay(1000);
      digitalWrite(LED_VERDE, LOW);

      tone(BUZZER, 261); 
      delay(400);        
      noTone(BUZZER);
  
      motor.write(90);
      delay(3000);
      motor.write(0);
    } 

    // Acesso Negado
    if (comando == '0') { 
        digitalWrite(LED_VERMELHO, HIGH);
        delay(1000);
        digitalWrite(LED_VERMELHO, LOW);   

        tone(BUZZER, 294); 
        delay(300);        
        noTone(BUZZER);
        delay(100);        
        tone(BUZZER, 294); 
        delay(300);        
        noTone(BUZZER);
    }
    
  }
}

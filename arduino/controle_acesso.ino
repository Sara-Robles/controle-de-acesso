void setup()
{
  Serial.begin(9600);
  pinMode(5, OUTPUT); // LED verde 
  pinMode(6, OUTPUT); // LED vermelho
  pinMode(7, OUTPUT); // Buzzer
}

void loop()
{
   if (Serial.available() > 0) {
    char command = Serial.read(); // Lê o byte enviado pelo Java

    if (command == '1') // se o byte for 1
      digitalWrite(5, HIGH); // liga LED
    else if (command == '0')  // se o byte for 1
      digitalWrite(5, LOW); // desliga LED
    
  }
}
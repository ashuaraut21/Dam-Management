#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>
const char* ssid = "home";// 
const char* password = "home@1234";

// defines pins numbers

const int trigPin = D2;  //D1
const int echoPin = D1;  //D2
const int trigPin1 = D3;  //D1
const int echoPin1 = D4;  //D2


// defines variables
long duration;
int distance;
long duration1;
int distance1;


void setup() {
pinMode(trigPin, OUTPUT); // Sets the trigPin as an Output
pinMode(echoPin, INPUT); // Sets the echoPin as an Input
pinMode(trigPin1, OUTPUT); // Sets the trigPin as an Output
pinMode(echoPin1, INPUT); // Sets the echoPin as an Input
pinMode(D5, OUTPUT);
pinMode(D6, OUTPUT);
pinMode(D7, OUTPUT);
pinMode(D8, OUTPUT);
Serial.begin(115200); // Starts the serial communication
 Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
 
  WiFi.begin(ssid, password);
 
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");
}

void loop() {
//Sensor 1-------------------------------------------
digitalWrite(trigPin, LOW);
delayMicroseconds(2);
digitalWrite(trigPin, HIGH);
delayMicroseconds(10);
digitalWrite(trigPin, LOW);
duration = pulseIn(echoPin, HIGH);
distance= duration*0.034/2;
Serial.print("Distance1: ");
Serial.println(distance);
//Sensor 2-------------------------------------------
digitalWrite(trigPin1, LOW);
delayMicroseconds(2);
digitalWrite(trigPin1, HIGH);
delayMicroseconds(10);
digitalWrite(trigPin1, LOW);
duration1 = pulseIn(echoPin1, HIGH);
distance1= duration1*0.034/2;
Serial.print("Distance2: ");
Serial.println(distance1);
if(distance<=5){
    digitalWrite(D5, LOW);
    digitalWrite(D7, HIGH);
    digitalWrite(D8, HIGH);
  }else{
    digitalWrite(D5, HIGH);
    digitalWrite(D7, LOW);
    digitalWrite(D8, LOW);
    
    
  }
if(distance1<=5){
    digitalWrite(D6, LOW);
    digitalWrite(D7, HIGH);
    digitalWrite(D8, HIGH);
  }else{
    digitalWrite(D6, HIGH);
    digitalWrite(D7, LOW);
    digitalWrite(D8, LOW);
    
  }

   HTTPClient http;
    String url="http://mahavidyalay.in/AcademicDevelopment/IOT2020/DamManagement.php?dam1="+String(distance)+"&dam2="+String(distance1);
    http.begin(url);
    http.addHeader("Content-Type","text/plain");
    int httpCode=http.GET();
    String payload=http.getString();
    Serial.println("While sending I received this from server : "+payload);
    http.end();
delay(1000);
}

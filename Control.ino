#include <ESP8266WiFi.h>
#include <WiFiClient.h>



const char *ssid = "Hola";
const char *pass = "12345678";
const char *host= "192.168.43.83";
int port = 80;

WiFiServer server(port);

void setup() {
  
  Serial.begin(115200);
  Serial.println("");
  Serial.print("Conoectado a ");
  Serial.println(ssid);
  WiFi.mode(WIFI_STA);
  delay(100);
  WiFi.begin(ssid, pass);
  while (WiFi.status() != WL_CONNECTED){
    delay(500);
    Serial.println(".");
  }
    Serial.println("");
    Serial.println(WiFi.localIP());

}

void loop() {
  int up = digitalRead(D8);
  WiFiClient client;
  if(up == HIGH){
    Serial.println("Ya");
    client.connect(host, 9090);
    Serial.println("Conectado");
    client.write("DDDD");
    delay(100);
    client.flush();
    client.stop();
    Serial.println("Enviado");
    return;
  }
  delay(100);
}














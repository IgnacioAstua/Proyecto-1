#include <ESP8266WiFi.h>
#include <WiFiClient.h>


const char *ssid = "Hola"; //Red a la cual se va a conectar
const char *pass = "12345678";//Contraseña de la red
const char *host= "192.168.43.83";//Ip del servidor
int port = 80;

WiFiServer server(port);

void setup() {
  
  Serial.begin(9600);

//Comienza la conexión con la red
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
  //Declara los botones como entradas. Si la entrada recibe HIGH, se envia un mensaje al servidor.
  int OK = digitalRead(D8);
  int UP = digitalRead(D1);
  int DOWN = digitalRead(D5);
  int LEFT = digitalRead(D6);
  int RIGHT = digitalRead(D2); 
  WiFiClient client;
//Botón para seleccionar.
  if(OK == HIGH){
    Serial.println("OK");
    client.connect(host, 9090);
    client.print("Ok");
    delay(125);
    client.flush();
    client.stop();
    return;
  }
//Botón para moverse hacia arriba.
  if(UP == HIGH){
    Serial.println("UP");
    client.connect(host, 9090);
    client.print("UP");
    delay(125);
    client.flush();
    client.stop();
    return;
  }
//Botón para moverse hacia abajo.
  if(DOWN == HIGH){
    Serial.println("DOWN");
    client.connect(host, 9090);
    client.print("DOWN");
    delay(125);
    client.flush();
    client.stop();
    return;
  }
//Botón para moverse hacia la izquierda.
  if(LEFT == HIGH){
    Serial.println("LEFT");
    client.connect(host, 9090);
    client.print("LEFT");
    delay(125);
    client.flush();
    client.stop();
    return;
  }
//Botón para moverse hacia la derecha.
  if(RIGHT == HIGH){
    Serial.println("RIGHT");
    client.connect(host, 9090);
    client.print("RIGHT");
    delay(125);
    client.flush();
    client.stop();
    return;
  }

  delay(100);
}














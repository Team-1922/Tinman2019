#include <Pixy2.h>
#include <Wire.h>
#include <FastLED.h>

#define LED_PIN     7
#define NUM_LEDS    92

Pixy2 pixy;
CRGB leds[NUM_LEDS];

//built in class from arduino, strongly suggest looking at it on their website
//it is not a complicated class



//this is provided by the pixy creators, you will have to go to the arduino sketch editor,
//click sketch, include library, and import the pixy .zip files

//SETUP THE DEVICES

//plug sda on RoboRIO into A4
//plug scl on RoboRIO into A5
//connect the two grounds


String piOutput = String(0);//string to be sent to the robot
String input = "blank";  //string received from the robot
const String PIXY = "pi";
int center;
int error;
long time;
long delayTime;
int x0;
int x1;
void setup() {
  Serial.begin(115200);
  Serial.println("setup");
  Wire.begin(4);                // join i2c bus with address #4 as a slave device
  Wire.onReceive(receiveEvent); // Registers a function to be called when a slave device receives a transmission from a master
  Wire.onRequest(requestEvent); // Register a function to be called when a master requests data from this slave device
  FastLED.addLeds<WS2812, LED_PIN, GRB>(leds, NUM_LEDS);

  pixy.init();
  pixy.ccc.getBlocks();
  pixy.getResolution();

  for (int i = 0; i < 92; i++)
  {
    leds[i] = CRGB(0, 200, 0);
    FastLED.show();
    delay(30);
  }
}

void loop() {

  uint16_t blocks = pixy.ccc.getBlocks();//use this line to get every available object the pixy sees
  //^^^not sure what exactly this is for, honestly
  int Lclosest = 0;
  int Lclosestdiffs = 10000;
  int Rclosest = 0;
  int Rclosestdiffs = 10000;

  if (blocks < 2)
  {
    piOutput = String(-1); //if no blocks tell roborio there are none
  }
  else
  {
    bool rSet = false;
    bool lSet = false;
    for (int i = 0; i < pixy.ccc.numBlocks; i++) {
      if (pixy.ccc.blocks[i].m_x > pixy.frameWidth / 2) {
        continue;
      }
      int diff = (pixy.frameWidth / 2) - pixy.ccc.blocks[i].m_x;
      if (diff < Lclosestdiffs) {
        Lclosest = i;
        Lclosestdiffs = diff;
        lSet = true;
      }
    }
    for (int i = 0; i < pixy.ccc.numBlocks; i++) {
      if (pixy.ccc.blocks[i].m_x < pixy.frameWidth / 2) {
        continue;
      }
      int diff = (pixy.ccc.blocks[i].m_x - pixy.frameWidth / 2);
      if (diff < Rclosestdiffs) {
        Rclosest = i;
        Rclosestdiffs = diff;
        rSet = true;
      }
    }
    if(rSet && lSet)
    {
        piOutput =  pixy.frameWidth / 2 - ((pixy.ccc.blocks[Rclosest].m_x) + (pixy.ccc.blocks[Lclosest].m_x) / 2);
    }
    else
    {
      piOutput = String(-1);
    }
  }


  delay(70); //gives time for everything to process
  time = millis();
  if (time > delayTime)
  {
    Serial.println(piOutput);
    Serial.print("Number of Blocks: ");
    Serial.println(blocks);
    delayTime = time + 50;
  }

}

void requestEvent() { //called when RoboRIO request a message from this device
  Wire.write(piOutput.c_str()); //writes data to the RoboRIO, converts it to string
  Serial.println("request");
}

void receiveEvent(int bytes) { //called when RoboRIO "gives" this device a message

}

#include <Wire.h>
#include <Pixy.h>


Pixy pixy;
//built in class from arduino, strongly suggest looking at it on their website
//it is not a complicated class



//this is provided by the pixy creators, you will have to go to the arduino sketch editor,
//click sketch, include library, and import the pixy .zip files

//SETUP THE DEVICES

//plug sda on RoboRIO into A4
//plug scl on RoboRIO into A5
//connect the two grounds


String piOutput = "none";//string to be sent to the robot

String input = "blank";  //string received from the robot
const String PIXY = "pi";

void setup() {
  Serial.begin(115200);
  Serial.println("setup");
  Wire.begin(4);                // join i2c bus with address #4 as a slave device
  Wire.onReceive(receiveEvent); // Registers a function to be called when a slave device receives a transmission from a master
  Wire.onRequest(requestEvent); // Register a function to be called when a master requests data from this slave device


  pixy.init();
}

void loop() {

  uint16_t blocks = pixy.getBlocks();//use this line to get every available object the pixy sees
  //^^^not sure what exactly this is for, honestly
  int biggest = -1;
  int secondBiggest = -1;
  double secondArea = 0, temp = 0;
  double biggestArea = 0;
  //  Serial.println(blocks);
  if (blocks < 2)
  {
    piOutput = "none"; //if no blocks tell roborio there are none
    delay(70); //gives time for everything to process
    return;
  }

  if (!blocks) {
    piOutput = "none"; //if no blocks tell roborio there are none
  } else {
    piOutput = String(pixy.blocks[0].x);  //turns into a percent of the screen
    piOutput += "|";                //inserts a "pipe" so robrio can split the numbers later
    piOutput += String(pixy.blocks[0].y); //319 and 199 were, we found, the dimensions of the screen
    piOutput += "|";
    piOutput += String(pixy.blocks[0].x * pixy.blocks[0].y);
    piOutput += "|";                //inserts a "pipe" so robrio can split the numbers later
    piOutput += String(pixy.blocks[1].x);  //turns into a percent of the screen
    piOutput += "|";                //inserts a "pipe" so robrio can split the numbers later
    piOutput += String(pixy.blocks[1].y); //319 and 199 were, we found, the dimensions of the screen
    piOutput += "|";                //inserts a "pipe" so robrio can split the numbers later
    piOutput += String(pixy.blocks[1].x * pixy.blocks[1].y);
    Serial.println(piOutput);

  }

  delay(70); //gives time for everything to process
}

void requestEvent() { //called when RoboRIO request a message from this device
  Wire.write(piOutput.c_str()); //writes data to the RoboRIO, converts it to string
  Serial.println("request");
}

void receiveEvent(int bytes) { //called when RoboRIO "gives" this device a message

}

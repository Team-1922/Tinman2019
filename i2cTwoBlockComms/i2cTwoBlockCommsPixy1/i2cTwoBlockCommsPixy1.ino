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

void setup(){
  Serial.begin(115200);
  Serial.println("setup");  
  Wire.begin(4);                // join i2c bus with address #4 as a slave device
  Wire.onReceive(receiveEvent); // Registers a function to be called when a slave device receives a transmission from a master
  Wire.onRequest(requestEvent); // Register a function to be called when a master requests data from this slave device
    

  pixy.init();
}

void loop(){

  uint16_t blocks = pixy.getBlocks();//use this line to get every available object the pixy sees
  //^^^not sure what exactly this is for, honestly
  int biggest = -1;
  int secondBiggest = -1;
  double secondArea = 0, temp = 0;
  double biggestArea = 0;
//  Serial.println(blocks);
  if(blocks < 2)
  {
    piOutput = "none"; //if no blocks tell roborio there are none
      delay(70); //gives time for everything to process
    return;
  }
  for(int i=0;i<blocks;i++){
    if(pixy.blocks[i].signature == 3){ //if checking for an object and have more than one "type" or color to choose from
                         //use this line and choose the signature or "color" you want
      temp = pixy.blocks[i].width * pixy.blocks[i].height;
      if(temp > secondArea ){
        if(temp > biggestArea)
        {
          secondArea = biggestArea;
          secondBiggest = biggest;
          biggestArea = temp;
          biggest = i;
        }
        else
        {
          secondArea = temp;
          secondBiggest = i;          
        }
      }
    }
      //that loops finds the biggest object since sometimes the object you are looking for becomes 
      //broken up into multiple, smaller, objects
  }
  if(!blocks){
    piOutput = "none"; //if no blocks tell roborio there are none 
  }else{
    piOutput = String(pixy.blocks[biggest].x);  //turns into a percent of the screen 
    piOutput += "|";                //inserts a "pipe" so robrio can split the numbers later
    piOutput += String(pixy.blocks[biggest].y); //319 and 199 were, we found, the dimensions of the screen 
    piOutput += "|";          
    piOutput += String(biggestArea);
    piOutput += "|";                //inserts a "pipe" so robrio can split the numbers later
    piOutput += String(pixy.blocks[secondBiggest].x);  //turns into a percent of the screen 
    piOutput += "|";                //inserts a "pipe" so robrio can split the numbers later
    piOutput += String(pixy.blocks[secondBiggest].y); //319 and 199 were, we found, the dimensions of the screen 
    piOutput += "|";                //inserts a "pipe" so robrio can split the numbers later
    piOutput += String(secondArea);
    Serial.println(piOutput);
    Serial.print("biggest= ");
    Serial.println(biggest);
    Serial.print("second biggest= ");
    Serial.println(secondBiggest);
    

  }

  delay(70); //gives time for everything to process
}

void requestEvent(){//called when RoboRIO request a message from this device
  Wire.write(piOutput.c_str()); //writes data to the RoboRIO, converts it to string
  Serial.println("request");
}

void receiveEvent(int bytes){//called when RoboRIO "gives" this device a message

}

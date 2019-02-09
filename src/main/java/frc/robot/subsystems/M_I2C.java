package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PixyPacket;

public class M_I2C {
  private static I2C Wire = new I2C(Port.kOnboard, 4);// uses the i2c port on the RoboRIO
                                                      // uses address 4, must match arduino
  private static final int MAX_BYTES = 88;

  public void write(String input) {// writes to the arduino
    char[] CharArray = input.toCharArray();// creates a char array from the input string
    byte[] WriteData = new byte[CharArray.length];// creates a byte array from the char array
    for (int i = 0; i < CharArray.length; i++) {// writes each byte to the arduino
      WriteData[i] = (byte) CharArray[i];// adds the char elements to the byte array
    }
    Wire.transaction(WriteData, WriteData.length, null, 0);// sends each byte to arduino

  }

  public PixyPacket getPixy() {// reads the data from arduino and saves it
    String temp = read();
    String info[] = temp.split("\\|");// everytime a "|" is used it splits the data, and adds it as a new element in
                                        // the array
    

    PixyPacket pkt = new PixyPacket(); // creates a new packet to hold the data
    if (info[0].equals("none") || info[0].equals("")) {// checks to make sure there is data
      pkt.x1 = -1;// the x val will never be -1 so we can text later in code to make sure there is
                  // data
      pkt.x2 = -1;
      pkt.y1 = -1;
      pkt.y2 = -1;
      pkt.area1 = -1;
      pkt.area2 = -1;
      SmartDashboard.putString("I2C Status", "No Data");
    } else if (info.length == 6) {// if there is an x, y, and area value the length equals 3
      pkt.x1 = Double.parseDouble(info[0]);// set x
      pkt.y1 = Double.parseDouble(info[1]);// set y
      pkt.area1 = Double.parseDouble(info[2]);// set area
      pkt.x2 = Double.parseDouble(info[3]);// set x
      pkt.y2 = Double.parseDouble(info[4]);// set y
      pkt.area2 = Double.parseDouble(info[5]);// set area length????
      SmartDashboard.putString("I2C Status", "Returning Data");
    } else {
      SmartDashboard.putString("I2C Status", "Edge Case");
      pkt.x1 = -1;// the x val will never be -1 so we can text later in code to make sure there is
                  // data
      pkt.x2 = -1;
      pkt.y1 = -1;
      pkt.y2 = -1;
      pkt.area1 = -1;
      pkt.area2 = -1;
    }
    SmartDashboard.putString("Temp", temp);
    return pkt;

  }

  private String read() {// function to read the data from arduino
    byte[] data = new byte[MAX_BYTES];// create a byte array to hold the incoming data
    Wire.read(4, MAX_BYTES, data);// use address 4 on i2c and store it in data
    String output = new String(data);// create a string from the byte array
    int pt = output.indexOf((char) 255);
    return (String) output.subSequence(0, pt < 0 ? 0 : pt);// im not sure what these last two lines do
                                                           // sorry :(
  }

}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import frc.robot.PixyLinePacket;

/**
 * LEGACY: M_I2C Interface for finding lines
 */
public class M_I2CLine {
  private static I2C Wire = new I2C(Port.kOnboard, 4);// uses the i2c port on the RoboRIO
                                                      // uses address 4, must match arduino
  private static final int MAX_BYTES = 32;

  /**
   * writes to the arduino
   */
  public void write(String input) {
    char[] CharArray = input.toCharArray();// creates a char array from the input string
    byte[] WriteData = new byte[CharArray.length];// creates a byte array from the char array
    for (int i = 0; i < CharArray.length; i++) {// writes each byte to the arduino
      WriteData[i] = (byte) CharArray[i];// adds the char elements to the byte array
    }
    Wire.transaction(WriteData, WriteData.length, null, 0);// sends each byte to arduino

  }

  /**
   * Reads the data from arduino and saves it
   */
  public PixyLinePacket getPixy() {
    String info[] = read().split("\\|");// everytime a "|" is used it splits the data, and adds it as a new element in
                                        // the array

    PixyLinePacket pkt = new PixyLinePacket(); // creates a new packet to hold the data
    if (info[0].equals("none") || info[0].equals("")) {// checks to make sure there is data
      pkt.x0 = -1;// the x val will never be -1 so we can text later in code to make sure there is
                  // data
      pkt.y0 = -1;
      pkt.x1 = -1;
      pkt.y1 = -1;
      pkt.linelength = -1;
    } else if (info.length == 4) {// if there is an x, y, and area value the length equals 3
      pkt.x0 = Double.parseDouble(info[0]);// set x0

      pkt.y0 = Double.parseDouble(info[1]);// set y0
      pkt.x1 = Double.parseDouble(info[2]);// set x1
      pkt.y1 = Double.parseDouble(info[3]);// set y1
      pkt.linelength = Math.sqrt(((pkt.x1 - pkt.x0) * (pkt.x1 - pkt.x0)) + ((pkt.y1 - pkt.y0) * (pkt.y1 - pkt.y0)));// set
                                                                                                                    // length
    }

    return pkt;

  }

  /**
   * function to read the data from arduino
   */
  private String read() {
    byte[] data = new byte[MAX_BYTES];// create a byte array to hold the incoming data
    Wire.read(4, MAX_BYTES, data);// use address 4 on i2c and store it in data
    String output = new String(data);// create a string from the byte array
    int pt = output.indexOf((char) 255);
    return (String) output.subSequence(0, pt < 0 ? 0 : pt);// im not sure what these last two lines do
                                                           // sorry :(
  }

}
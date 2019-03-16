package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PixyPacket;

public class M_I2C2 {
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
    String info = read();

    PixyPacket pkt = new PixyPacket(); // creates a new packet to hold the data
    if (info.equals("none") || info.equals("")) {// checks to make sure there is data
      pkt.error = Double.NaN;
      SmartDashboard.putBoolean("I2C Status", false);
    } else {// if there is an x, y, and area value the length equals 3
      pkt.error = Double.parseDouble(info);// set error

      SmartDashboard.putBoolean("I2C Status", true);
    }
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

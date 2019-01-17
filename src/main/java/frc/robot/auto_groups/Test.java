/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto_groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
// import frc.robot.auto_commands.DriveStraight;
import frc.robot.PixyPacket;
import frc.robot.Robot;
import frc.robot.subsystems.M_I2C;

/*public class Test extends CommandGroup{
  public Test() {
    addSequential(new DriveStraight(5,5));
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
}*/
public class Test extends CommandGroup {
  M_I2C i2c = new M_I2C();// setup the i2c interface
  PixyPacket pkt = i2c.getPixy();// create a pixy packet to hold data

  public void centerOnObject() {
    if (pkt.x != -1) {// if data is exist
      if (pkt.x < .48 || pkt.x > .52) {
        // and the 'object', whatever it may be is not in the center
        // the code on the arduino decides what object to send
        while (pkt.x < .48 || pkt.x > .52) {// while it is not center

          if (pkt.x < .48) {// if its on the left side of robot, turn left
            /*
             * drive.setLDrive(-0.2);//this is our left side of tank drive
             * drive.setRDrive(0.2);//you drive code might differ
             */

            Robot.m_drivetrain.drive(-.2, .2);
          }
          if (pkt.x > .52) {// if its on the right side of robot, turn right
            /*
             * drive.setLDrive(0.2); drive.setRDrive(-0.2);
             */
            Robot.m_drivetrain.drive(.2, -.2);
          }
          if (pkt.y == -1)// Restart if ball lost during turn
            break;
          pkt = i2c.getPixy();// refresh the data
          System.out.println("XPos: " + pkt.x);// print the data just to see
        }

      }
    }
  }
}
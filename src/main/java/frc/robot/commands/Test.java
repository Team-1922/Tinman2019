/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
public class Test extends Command {
  M_I2C i2c = new M_I2C();// setup the i2c interface
  PixyPacket pkt = i2c.getPixy();// create a pixy packet to hold data

  public Test() {
    super();
    requires(Robot.m_drivetrain);

  }
  // public void centerOnObject() {

  @Override
  protected void execute() {

    if (pkt.x != -1) {// if data is exist
      if (pkt.x < .48 || pkt.x > .52) {
        // and the 'object', whatever it may be is not in the center
        // the code on the arduino decides what object to send
        while (pkt.x < .48 || pkt.x > .52) {// while it is not center
          if (pkt.x > .52) {// if its on the right side of robot, turn right
            Robot.m_drivetrain.drive(.2, -.2);
            SmartDashboard.putString("direction", "right");
          }
          if (pkt.x < .48) {// if its on the left side of robot, turn left
            SmartDashboard.putString("direction", "left");
            Robot.m_drivetrain.drive(-.2, .2);
          }

          if (pkt.x > .48 && pkt.x < .52) {// while centered, stop
            Robot.m_drivetrain.drive(0, 0);
          }
          if (pkt.y == -1) {// Restart if ball lost during turn
            Robot.m_drivetrain.drive(0, 0);
            break;
          }
          pkt = i2c.getPixy();// refresh the data
          System.out.println("XPos: " + pkt.x);// print the data just to see
        }

      }
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PixyPacket;
import frc.robot.Robot;
import frc.robot.subsystems.M_I2C;

/**
 * An example command. You can replace me with your own command.
 */
public class TankDrive extends Command {
  M_I2C i2c = new M_I2C();// setup the i2c interface
  PixyPacket pkt = i2c.getPixy();// create a pixy packet to hold data

  public TankDrive() {

    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_drivetrain);
  }

  // Called just before this Command runs the first time

  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.m_drivetrain.drive(Robot.m_oi.getLeftStick().getY(), Robot.m_oi.getRightStick().getY());

    SmartDashboard.putNumber("Gyro", Robot.m_drivetrain.getAngle());

    SmartDashboard.putNumber("PixyData1", pkt.x);
    SmartDashboard.putNumber("PixyData2", pkt.y);
    SmartDashboard.putNumber("PixyData3", pkt.area);
    pkt = i2c.getPixy(); // refresh Pixy data

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class DriveStraight extends Command {
  double encoderDifference = 0;
  public DriveStraight() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_drivetrain.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_drivetrain.getEncoders();
    double p = .06;
    encoderDifference = Robot.m_drivetrain.getPosLeft() - Robot.m_drivetrain.getPosRight();
    SmartDashboard.putNumber("Right-Left", encoderDifference);
    double responce = p * encoderDifference;
    double RawY = (Robot.m_oi.getLeftStick().getY() + Robot.m_oi.getRightStick().getY()) / 2;



    Robot.m_drivetrain.drive(RawY - (responce/100), RawY + (responce/100));
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
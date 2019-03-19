
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

/**
 * Makes the robot drive perfectly straight without directional influence
 */
public class DriveStraight extends Command {
  double error = 0;
  double errorPrior = 0;
  double initAngle = 0;

  public DriveStraight() {
    requires(Robot.m_drivetrain);
  }

  @Override
  protected void initialize() {
    Robot.m_drivetrain.resetEncoders();
    initAngle = Robot.m_drivetrain.getAngle();
  }

  @Override
  protected void execute() {
    Robot.m_drivetrain.getEncoders();
    double p = .02;
    double d = 0.0001;
    double derivative;

    error = initAngle - Robot.m_drivetrain.getAngle();
    SmartDashboard.putNumber("Right-Left", error);
    derivative = (error - errorPrior) / .02;
    double responce = p * error + (d * derivative);
    double RawY = (Robot.m_oi.getLeftStick().getY() + Robot.m_oi.getRightStick().getY()) / 2;
    Robot.m_drivetrain.drive(RawY - responce, RawY + responce);
    errorPrior = error;
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
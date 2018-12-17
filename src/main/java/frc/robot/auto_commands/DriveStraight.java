/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto_commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveStraight extends Command {

  private double speed;
  private long starttime;
  private int timesec;

  public DriveStraight(double speed, int timesec) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    this.speed = speed;

    this.timesec = timesec;

    requires(Robot.m_subsystem);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    this.starttime = System.currentTimeMillis();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.m_subsystem.drive(this.speed, this.speed);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    long curtime = System.currentTimeMillis();

    long timespent = curtime - this.starttime;

    if (timespent >= this.timesec * 1000) {
      return true;
    } else {
      return false;
    }
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

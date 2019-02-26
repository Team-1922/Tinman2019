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

public class ClimberCheck extends Command {
  // private int time = 0;
  // private int delayTime = 1000;
  private boolean commandstate = true;
  private boolean newPress = true;

  public ClimberCheck() {
    requires(Robot.m_climber);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putBoolean("Climber Active", commandstate);
    if (Robot.m_oi.getLBumper() && newPress) {
      commandstate = !commandstate;
      newPress = false;

    }
    if (Robot.m_oi.getLBumper() == false) {
      newPress = true;
    }
    // if (Robot.m_oi.getLBumper() && time > delayTime) {
    // if (OperateClimber.getisrunning() == false) {
    // new OperateClimber();
    // } else {
    // OperateClimber.setisrunning(false);
    // }
    // delayTime = time + 1000;
    // }
    // time = time + 20;

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

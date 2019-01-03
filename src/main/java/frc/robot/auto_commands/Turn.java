/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auto_commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class Turn extends Command {

  private double TurnGoal;
  private double Start;
  private boolean IsLeft = false;
  // private boolean isFinished = false;

  public Turn(double TurnGoal) {
    super();
    this.TurnGoal = TurnGoal;
    requires(Robot.m_subsystem);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Start = Robot.m_subsystem.getAngle();
    if (TurnGoal >= 0) {
      IsLeft = false;
    } else {
      IsLeft = true;
    }
    SmartDashboard.putNumber("TurnCommand_TurnGoal", TurnGoal);
    SmartDashboard.putBoolean("TurnCommand Is Left", IsLeft);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    int leftMultiplier = -1;
    if (IsLeft) {
      leftMultiplier = 1;
    }
    Robot.m_subsystem.drive(leftMultiplier * .5, -leftMultiplier * .5);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    SmartDashboard.putNumber("TurnCommand Current Heading", Robot.m_subsystem.getAngle());
    SmartDashboard.putNumber("TurnCommand Target", Start + TurnGoal);
    if (IsLeft) {
      return Robot.m_subsystem.getAngle() <= Start + TurnGoal;
    } else {
      return Robot.m_subsystem.getAngle() >= Start + TurnGoal;
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
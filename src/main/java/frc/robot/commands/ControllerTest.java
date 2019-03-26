/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * you don't see this
 */
public class ControllerTest extends Command {
  public ControllerTest() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double lRumble = Math.abs(Robot.m_oi.getOperator().getRawAxis(5) + Robot.m_oi.getOperator().getRawAxis(1) / 2);
    double rRumble = Math.abs(Robot.m_oi.getOperator().getRawAxis(5) + Robot.m_oi.getOperator().getRawAxis(1) / 2);
    if(rRumble < 0.2){
      rRumble = 0;
    }
    if(lRumble < 0.2){
      lRumble = 0;
    }

    Robot.m_oi.getOperator().setRumble(RumbleType.kLeftRumble, lRumble);
    Robot.m_oi.getOperator().setRumble(RumbleType.kRightRumble, rRumble);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return OperateClimber.isClimberActive();
  }

  // Called once after isFinished returns true
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

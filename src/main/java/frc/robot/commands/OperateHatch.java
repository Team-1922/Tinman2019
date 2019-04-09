/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Takes the value of the Right Bumper on the Xbox controller and sets the hatch
 * solenoid to that
 * 
 * @see Hatch_Subsystem
 */
public class OperateHatch extends Command {
  public OperateHatch() {
    requires(Robot.m_hatchsnatch);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (Robot.m_oi.getRBumper() || Robot.m_oi.getThumbButton()) {
      Robot.m_hatchsnatch.OpenHatch();
    } else {
      Robot.m_hatchsnatch.CloseHatch();
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Command to call the ResetGyro method in the drivetrain
 */
public class ResetGyro extends Command {
  boolean isdone = false;

  public ResetGyro() {
    requires(Robot.m_drivetrain);
  }

  @Override
  protected void initialize() {
    Robot.m_drivetrain.ResetGyro();
    isdone = true;
  }

  @Override
  protected boolean isFinished() {
    return isdone;
  }
}

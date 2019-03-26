/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.FourBarCheck;

/**
 * Subsystem to control the FourBar that makes our collector legal
 */
public class FourBar_Subsystem extends Subsystem {
  private Solenoid FourBar;

  public FourBar_Subsystem() {
    super();
    FourBar = new Solenoid(RobotMap.FourBar);

  }

  /**
   * Extends the fourbar
   */
  public void FourBarDown() {
    FourBar.set(true);
  }

  /**
   * Retracts the fourbar
   */
  public void FourBarUp() {
    FourBar.set(false);
  }

  /**
   * Set the fourbar to a specific position
   * 
   * @param pos Boolean to set the fourbar to
   */
  public void toggleFourBar(boolean pos) {
    FourBar.set(pos);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new FourBarCheck());
  }

}

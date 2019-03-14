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
 * An example subsystem. You can replace me with your own Subsystem.
 */

public class FourBar_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Solenoid FourBar;

  public FourBar_Subsystem() {
    super();
    FourBar = new Solenoid(RobotMap.FourBar);

  }

  public void FourBarDown() {
    FourBar.set(true);
  }

  public void FourBarUp() {
    FourBar.set(false);
  }

  public void toggleFourBar(boolean pos) {
    FourBar.set(pos);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new FourBarCheck());
  }

}

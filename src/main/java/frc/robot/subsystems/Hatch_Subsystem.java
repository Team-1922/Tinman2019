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
import frc.robot.commands.OperateHatch;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Hatch_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Solenoid Hatch;

  public Hatch_Subsystem() {
    super();
    Hatch = new Solenoid(RobotMap.Hatch);
  }

  public void OpenHatch() {
    Hatch.set(true);
  }

  public void CloseHatch() {
    Hatch.set(false);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new OperateHatch());
  }

}

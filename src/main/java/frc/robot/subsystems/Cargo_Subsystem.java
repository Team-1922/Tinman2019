/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.OperateCargo;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class Cargo_Subsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX CargoTop;
  private WPI_TalonSRX CargoBot;

  public Cargo_Subsystem() {
    super();
    CargoTop = new WPI_TalonSRX(RobotMap.cargoTop);
    CargoBot = new WPI_TalonSRX(RobotMap.cargoBot);
  }

  public void run(double Speed) {
    CargoTop.set(Speed);
    CargoBot.set(-Speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new OperateCargo());
  }

}

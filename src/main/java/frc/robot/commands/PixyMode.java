/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.PixyPacket;
import frc.robot.Robot;
import frc.robot.subsystems.M_I2C;

public class PixyMode extends Command {
  M_I2C i2c = new M_I2C();// setup the i2c interface
  PixyPacket pkt = i2c.getPixy();// create a pixy packet to hold data

  public PixyMode() {
    super();
    requires(Robot.m_drivetrain);

  }

 
  @Override
  protected void initialize() {

    pkt = i2c.getPixy();
  }

  @Override
  protected void execute() {
    pkt = i2c.getPixy();
    SmartDashboard.putNumber("x", pkt.x);
    SmartDashboard.putNumber("y", pkt.y);
    SmartDashboard.putNumber("area", pkt.area);
    if (pkt.x != -1) {// if data is exist

      pkt = i2c.getPixy();
      double p = 1;
      double error = 0.5 - pkt.x;
      double responce = p * error;
      Robot.m_drivetrain.drive(responce, -responce);
    } else {
      Robot.m_drivetrain.drive(0, 0);
    }
    pkt = i2c.getPixy();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }
}
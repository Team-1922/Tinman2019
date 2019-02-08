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
  private double center, p, error, responce;

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
    SmartDashboard.putNumber("x1", pkt.x1);
    SmartDashboard.putNumber("y1", pkt.y1);
    SmartDashboard.putNumber("area1", pkt.area1);
    SmartDashboard.putNumber("x2", pkt.x2);
    SmartDashboard.putNumber("y2", pkt.y2);
    SmartDashboard.putNumber("area2", pkt.area2);
    if (pkt.x1 != -1 || pkt.x1 != 0) {// if data is exist

      pkt = i2c.getPixy();
      center = (pkt.x1 + pkt.x2) / 2;
      p = .5;
      error = 0.5 - center;
      responce = p * error;
      Robot.m_drivetrain.drive(responce, -responce);
      SmartDashboard.putNumber("responce", responce);

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

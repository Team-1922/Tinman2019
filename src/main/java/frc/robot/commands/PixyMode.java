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
import frc.robot.subsystems.M_I2C2;

/**
 * Centers the robot to the middle of two blocks from the Pixy
 * @see i2cFinal.ino
 */
public class PixyMode extends Command {
  M_I2C2 i2c = new M_I2C2();// setup the i2c interface
  PixyPacket pkt = i2c.getPixy();// create a pixy packet to hold data
  private double center, derivative, errorPrior;
  private double p = .006;
  private double d = 0;

  public PixyMode() {
    super();
    requires(Robot.m_drivetrain);

  }

  @Override
  protected void initialize() {
    initAngle = Robot.m_drivetrain.getAngle();
  }

  @Override
  protected void execute() {
    pkt = i2c.getPixy();
    SmartDashboard.putNumber("pkt error", pkt.error);
    if (pkt.error != Double.NaN) {// if data is exist
      initAngle = 0.0;

      SmartDashboard.putBoolean("Turning", true);

      double error = pkt.error;
      derivative = (error - errorPrior);
      double responce = p * error + (d * derivative);
      if (responce < -.5) {
        responce = -.5;
      } else if (responce > .5) {
        responce = .5;
      }
      Robot.m_drivetrain.drive(responce + Robot.m_oi.getLeftStick().getY(),
          -responce + Robot.m_oi.getLeftStick().getY());
      SmartDashboard.putNumber("pixy error", error);
      SmartDashboard.putNumber("pixy center", center);
      SmartDashboard.putNumber("pixy responce", responce);
      errorPrior = error;

    } else {
      if (initAngle == 0.0) {
        initAngle = Robot.m_drivetrain.getAngle();
      }

      double straightError = initAngle - Robot.m_drivetrain.getAngle();
      SmartDashboard.putNumber("Right-Left", straightError);
      straightDerivative = (straightError - straightErrorPrior) / .02;
      double responce = straightP * straightError + (straightD * straightDerivative);
      double RawY = Robot.m_oi.getLeftStick().getY();
      Robot.m_drivetrain.drive(RawY - responce, RawY + responce);
      straightErrorPrior = straightError;
      SmartDashboard.putNumber("center", 0);
      SmartDashboard.putBoolean("Turning", false);

    }
  }

  private double initAngle;
  private double straightP = 0.02;
  private double straightD = 0.0001;
  private double straightErrorPrior;
  private double straightDerivative;

  @Override
  protected boolean isFinished() {
    return false;
  }
}

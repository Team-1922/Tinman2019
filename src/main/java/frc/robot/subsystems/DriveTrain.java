/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotSettingsFactory;
import frc.robot.commands.TankDrive;

/**
 * It's the thing that makes the robot go vroom
 */
public class DriveTrain extends Subsystem {
  private WPI_TalonSRX rearLeft = new WPI_TalonSRX(
      RobotSettingsFactory.getRobotSettings(Robot.getCurrentBot()).getM_rearLeft());
  private WPI_TalonSRX rearRight = new WPI_TalonSRX(
      RobotSettingsFactory.getRobotSettings(Robot.getCurrentBot()).getM_rearRight());
  private WPI_TalonSRX frontLeft = new WPI_TalonSRX(
      RobotSettingsFactory.getRobotSettings(Robot.getCurrentBot()).getM_frontLeft());
  private WPI_TalonSRX frontRight = new WPI_TalonSRX(
      RobotSettingsFactory.getRobotSettings(Robot.getCurrentBot()).getM_frontRight());

  private AHRS ahrs = new AHRS(SPI.Port.kMXP);
  private int oldLeft = 0;
  private int oldRight = 0;

  // public NetworkTableEntry RealGyro = Shuffleboard
  // .getTab("SmartDashboard")
  // .add("Real Gyro", Robot.m_drivetrain.getAngle())
  // .withWidget("Gyro")
  // .getEntry();

  public DriveTrain() {
    super();

    frontLeft.setSelectedSensorPosition(0, 0, 10);
    frontRight.setSelectedSensorPosition(0, 0, 10);
    frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

    rearLeft.set(ControlMode.Follower, frontLeft.getDeviceID());
    rearRight.set(ControlMode.Follower, frontRight.getDeviceID());

    
  }

  /**
   * Main method to make the robot drive, controlled tank drive style
   * @param leftSpeed Speed to set the left motors to
   * @param rightSpeed Speed to set the right motors to
   */
  public void drive(double leftSpeed, double rightSpeed) {
    frontLeft.set(-leftSpeed);
    frontRight.set(rightSpeed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TankDrive());

  }

  public double getAngle() {
    // RealGyro.setValue(ahrs.getAngle());
    return ahrs.getAngle();
  }

  public void ResetGyro() {
    ahrs.reset();
  }

  public double getPosLeft() {
    return -frontLeft.getSensorCollection().getQuadraturePosition() - oldLeft;
  }

  public double getPosRight() {
    return frontRight.getSensorCollection().getQuadraturePosition() - oldRight;
  }
  

  public void getEncoders() {
    getPosLeft();
    getPosRight();


    SmartDashboard.putNumber("EncoderLeft", Robot.m_drivetrain.getPosLeft());
    SmartDashboard.putNumber("EncoderRight", Robot.m_drivetrain.getPosRight());

  }

  public void resetEncoders() {
    oldRight = frontRight.getSensorCollection().getQuadraturePosition();
    oldLeft = -frontLeft.getSensorCollection().getQuadraturePosition();
  }

  public boolean getLimitTest() {
    return this.frontLeft /* placeholder */ .getSensorCollection().isFwdLimitSwitchClosed();

  }
}
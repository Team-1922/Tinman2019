/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * It's the thing that makes the robot go vroom
 */
public class Climber_Subsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private WPI_TalonSRX verticalR = new WPI_TalonSRX(RobotMap.verticalClimb_R);
    private WPI_TalonSRX verticalL = new WPI_TalonSRX(RobotMap.verticalClimb_L);
    private WPI_TalonSRX horizontalR = new WPI_TalonSRX(RobotMap.horizontalClimb_R);
    private WPI_TalonSRX horizontalL = new WPI_TalonSRX(RobotMap.horizontalClimb_L);
    private AHRS ahrs = new AHRS(SPI.Port.kMXP);
    private double vp = 0.05;
    private double hp = 0.8;
    private double vError, vResponce, hError, hResponce = 0;

    private int oldLVertical, oldRVertical, oldLHorizontal, oldRHorizontal = 0;

    public Climber_Subsystem() {
        super();
        verticalR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        verticalL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        horizontalR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
        horizontalL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

        verticalR.setSelectedSensorPosition(0, 0, 10);
        verticalL.setSelectedSensorPosition(0, 0, 10);
        horizontalR.setSelectedSensorPosition(0, 0, 10);
        horizontalL.setSelectedSensorPosition(0, 0, 10);
    }

    public void verticalClimb(double y_axis) {
        // error = Robot.m_climber.getPitch();
        vError = ahrs.getPitch();

        vResponce = (vError * vp);
        verticalR.set(-y_axis - vResponce);
        verticalL.set(-y_axis + vResponce);
        SmartDashboard.putNumber("vertical", y_axis);
        SmartDashboard.putNumber("Vertical Responce", vResponce);
    }

    public void horizontalClimb(double x_axis) {
        // hError = ahrs.getAngle();
        hError = getLHorizontalPos() - getRHorizontalPos();
        hResponce = (hError * hp);
        SmartDashboard.putNumber("Difference", hError);
        SmartDashboard.putNumber("horizontal", x_axis);
        SmartDashboard.putNumber("Horizontal Response", hResponce / 10000);
        SmartDashboard.putNumber("Left Encoder", getLHorizontalPos());
        SmartDashboard.putNumber("Right Encoder", getRHorizontalPos());
        horizontalR.set(x_axis + hResponce / 10000);
        horizontalL.set(x_axis - hResponce / 10000);
    }

    public double getPitch() {
        SmartDashboard.putNumber("Pitch", ahrs.getPitch());
        return ahrs.getPitch();
    }

    public boolean getVerticalRLimit() {
        // return verticalLimitR.get();
        return verticalR.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public boolean getVerticalLLimit() {
        // return verticalLimitL.get();
        return verticalL.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public boolean getHorizontalRLimit() {
        // return horizontalLimitR.get();
        return horizontalR.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public boolean getHorizontalLLimit() {
        return horizontalL.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public int getRVerticalPos() {
        return verticalR.getSensorCollection().getQuadraturePosition() - oldRVertical;
    }

    public int getLVerticalPos() {
        return verticalL.getSensorCollection().getQuadraturePosition() - oldLVertical;
    }

    public int getRHorizontalPos() {
        return horizontalR.getSensorCollection().getQuadraturePosition() - oldRHorizontal;
    }

    public int getLHorizontalPos() {
        return (-horizontalL.getSensorCollection().getQuadraturePosition()) - oldLHorizontal;
    }

    public void climberInit() {
        // while (getHorizontalLLimit() != true) {
        // horizontalL.set(.25);
        // }
        oldLHorizontal = getLHorizontalPos();
        // while (getHorizontalRLimit() != true) {
        // horizontalR.set(.25);
        // }
        oldRHorizontal = getRHorizontalPos();
        // while (getVerticalLLimit() != true){
        // while (getVerticalLLimit() != true){
        // verticalL.set(.25);
        // }
        oldLVertical = getLVerticalPos();
        // while (getVerticalRLimit() != true){
        // verticalR.set(.25);
        // }
        oldRVertical = getRVerticalPos();

    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new OperateClimber());
        // setDefaultCommand(new ControllerTest());

    }

}
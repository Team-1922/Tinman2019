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
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ControllerTest;

/**
 * Subsystem to control the two "legs" of the climber
 */
public class Climber_Subsystem extends Subsystem {
    private WPI_TalonSRX verticalR = new WPI_TalonSRX(RobotMap.verticalClimb_R);
    private WPI_TalonSRX verticalL = new WPI_TalonSRX(RobotMap.verticalClimb_L);
    private WPI_TalonSRX horizontalR = new WPI_TalonSRX(RobotMap.horizontalClimb_R);
    private WPI_TalonSRX horizontalL = new WPI_TalonSRX(RobotMap.horizontalClimb_L);
    private AHRS ahrs = new AHRS(SPI.Port.kMXP);
    private double vp = 0.05;
    private double hp = 0;//0.8;
    private double vError, vResponce, hError, hResponce = 0;

    private Boolean updateState = null; 

    private int oldLVertical, oldRVertical, oldLHorizontal, oldRHorizontal = 0;

    public Climber_Subsystem() {
        super();
        verticalR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 50);
        verticalL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 50);
        horizontalR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 50);
        horizontalL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 50);





    }

    public void updateState()
    {
        if(Robot.m_stateFlag.getSelected() == null)
        {
            return;
        }
        if(updateState == null || updateState != Robot.m_stateFlag.getSelected())
        {
            horizontalL.setSensorPhase(Robot.m_hlpChooser.getSelected());
            horizontalR.setSensorPhase(Robot.m_hrpChooser.getSelected());
            verticalL.setSensorPhase(Robot.m_vlpChooser.getSelected());
            verticalR.setSensorPhase(Robot.m_vrpChooser.getSelected());
    
            horizontalL.setInverted(Robot.m_hliChooser.getSelected());
            horizontalR.setInverted(Robot.m_hriChooser.getSelected());
            verticalL.setInverted(Robot.m_vliChooser.getSelected());
            verticalR.setInverted(Robot.m_vriChooser.getSelected());
            updateState = Robot.m_stateFlag.getSelected();
        }
    }

    public void verticalClimb(double y_axis) {
        vError = ahrs.getPitch();

        if(y_axis < 0)
        {
            vResponce = (vError * vp);
        }
        else 
        {
            vResponce = 0;
        }

        verticalL.set(-y_axis + vResponce);
        verticalR.set(-y_axis - vResponce);

        SmartDashboard.putNumber("vertical", y_axis);
        SmartDashboard.putNumber("Vertical Responce", vResponce);
        SmartDashboard.putNumber("Encoder Values", getRVerticalPos());
    }

    public void horizontalClimb(double x_axis) {
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

    public void rawVerticalClimb(double y_axis) {
        verticalL.set(y_axis);
        verticalR.set(y_axis);
        SmartDashboard.putNumber("vertical", y_axis);
        SmartDashboard.putNumber("Encoder Values", getRVerticalPos());
    }

    public void stop()
    {
        verticalL.set(0);
        verticalR.set(0);
    }

    public void rawHorizontalClimb(double x_axis) {

    }

    public double getPitch() {
        SmartDashboard.putNumber("Pitch", ahrs.getPitch());
        return ahrs.getPitch();
    }

    public boolean getVerticalRLimit() {
        return verticalR.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public boolean getVerticalLLimit() {
        return verticalL.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public boolean getHorizontalRLimit() {
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

    public void resetEncoders()
    {
        verticalR.setSelectedSensorPosition(0, 0, 50);
        verticalL.setSelectedSensorPosition(0, 0, 50);
        horizontalR.setSelectedSensorPosition(0, 0, 50);
        horizontalL.setSelectedSensorPosition(0, 0, 50);       
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
        // verticalL.set(.25);
        // }
        oldLVertical = getLVerticalPos();
        // while (getVerticalRLimit() != true){
        // verticalR.set(.25);
        // }
        oldRVertical = getRVerticalPos();

        //NOTE Forward soft limit _must_ be the higher number. It doesn't really matter about which direction it goes
        // soft limits are a range. Reverse is the low number and forward is the high number

        verticalR.configForwardSoftLimitThreshold(oldRVertical - 1200);
        verticalR.configReverseSoftLimitThreshold(oldRVertical - 21000);
        verticalL.configForwardSoftLimitThreshold(oldLVertical - 1200);
        verticalL.configReverseSoftLimitThreshold(oldLVertical - 21000);

        horizontalR.configForwardSoftLimitThreshold(oldRHorizontal + 1000);
        horizontalR.configReverseSoftLimitThreshold(oldRHorizontal - 1000);

        int forwardLeft = oldLHorizontal + 1000;
        int reverseLeft = oldLHorizontal - 1000;

        horizontalL.configForwardSoftLimitThreshold(forwardLeft);
        horizontalL.configReverseSoftLimitThreshold(reverseLeft);

        SmartDashboard.putNumber("Horizontal Left Forward", forwardLeft);
        SmartDashboard.putNumber("Horizontal Left Reverse", reverseLeft);

        verticalR.configForwardSoftLimitEnable(true);
        verticalR.configReverseSoftLimitEnable(true);

        verticalL.configForwardSoftLimitEnable(true);
        verticalL.configReverseSoftLimitEnable(true);

        horizontalL.configForwardSoftLimitEnable(true);
        horizontalL.configReverseSoftLimitEnable(true);

        horizontalR.configForwardSoftLimitEnable(true);
        horizontalR.configReverseSoftLimitEnable(true);
    }

    @Override
    public void initDefaultCommand() {
        // setDefaultCommand(new OperateClimber());
        setDefaultCommand(new ControllerTest());
    }

}
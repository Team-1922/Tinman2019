/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.OperateClimber;

/**
 * It's the thing that makes the robot go vroom
 */
public class Climber_Subsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private WPI_TalonSRX vertical1 = new WPI_TalonSRX(RobotMap.verticalClimb_1);
    private WPI_TalonSRX vertical2 = new WPI_TalonSRX(RobotMap.verticalClimb_2);
    private WPI_TalonSRX horizontal1 = new WPI_TalonSRX(RobotMap.horizontalClimb_1);
    private WPI_TalonSRX horizontal2 = new WPI_TalonSRX(RobotMap.horizontalClimb_2);
    private DigitalInput upperLimit = new DigitalInput(RobotMap.UpperLimit);
    private DigitalInput lowerLimit = new DigitalInput(RobotMap.LowerLimit);
    private AHRS ahrs = new AHRS(SPI.Port.kMXP);
    private double p = 0.01;
    private double error, responce = 0;

    public Climber_Subsystem() {
        super();

    }

    public void verticalClimb(double y_axis) {
        error = Robot.m_climber.getRoll();
        responce = (error * p);
        vertical1.set(y_axis + responce);
        vertical2.set(y_axis - responce);
    }

    public void horizontalClimb(double x_axis) {
        horizontal1.set(x_axis);
        horizontal2.set(x_axis);

    }

    public double getRoll() {
        return ahrs.getRoll();
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new OperateClimber());

    }

}
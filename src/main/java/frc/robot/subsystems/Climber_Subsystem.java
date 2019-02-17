/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * It's the thing that makes the robot go vroom
 */
public class Climber_Subsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private WPI_TalonSRX verticleMaster = new WPI_TalonSRX(RobotMap.verticleClimb_1);
    private WPI_TalonSRX verticleSlave = new WPI_TalonSRX(RobotMap.verticleClimb_2);
    private WPI_TalonSRX horizontalMaster = new WPI_TalonSRX(RobotMap.horizontalClimb_1);
    private WPI_TalonSRX horizontalSlave = new WPI_TalonSRX(RobotMap.horizontalClimb_2);

    public Climber_Subsystem() {
        super();
        verticleSlave.set(ControlMode.Follower, verticleMaster.getDeviceID());
        horizontalSlave.set(ControlMode.Follower, horizontalMaster.getDeviceID());
    }

    public void verticleClimb(double y_axis) {
        verticleMaster.set(y_axis);
    }

    public void horizontalClimb(double x_axis) {
        horizontalMaster.set(x_axis);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());

    }

}
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * Class for moving the Climber mechanism vertical
 */
public class OperateClimber extends Command {
    private static boolean isActive = false;

    public OperateClimber() {
        requires(Robot.m_climber);
    }

    @Override
    protected void initialize() {
        isActive = true;
        SmartDashboard.putBoolean("Climber Mode:", true);
        Robot.m_climber.updateState();
    }

    @Override
    protected void execute() {

        Robot.m_climber.rawVerticalClimb(Robot.m_oi.getOperator().getRawAxis(5));
        Robot.m_climber.horizontalClimb(Robot.m_oi.getOperator().getRawAxis(1));


    }

    @Override
    protected boolean isFinished() {
        return !isActive;
    }

    @Override
    protected void end() {
        Robot.m_climber.verticalClimb(0);
        isActive = false;
        SmartDashboard.putBoolean("Climber Mode:", false);
    }

    @Override
    protected void interrupted() {
        end();
    }

    public static boolean isClimberActive() {
        return isActive;
    }

}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Class for moving the Climber mechanism vertical
 */
public class OperateClimber extends Command {
    private static boolean isrunning = true;

    public OperateClimber() {
        requires(Robot.m_climber);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        isrunning = true;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        Robot.m_climber.verticalClimb(Robot.m_oi.getOperator().getRawAxis(5));
        Robot.m_climber.horizontalClimb(Robot.m_oi.getOperator().getRawAxis(0));

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isrunning;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }

    public static void setisrunning(boolean set) {
        isrunning = set;
    }

    public static boolean getisrunning() {
        return isrunning;
    }
}

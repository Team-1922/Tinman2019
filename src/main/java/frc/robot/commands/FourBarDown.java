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
 * Add your docs here.
 */
public class FourBarDown extends Command {
    private boolean isDone = false;
    private boolean firstpress = true;
    private static boolean isActive = false;

    public FourBarDown() {
        requires(Robot.m_fourbar);
    }

    @Override
    protected void initialize() {
        isActive = true;
        firstpress = true;
    }

    @Override
    protected void execute() {

        if (Robot.m_oi.returnLBumper().get() == false || firstpress == false) {
            firstpress = false;

            if (Robot.m_oi.returnLBumper().get() && firstpress == false) {
                isActive = false;
                isDone = true;
            }
        }
    }

    @Override
    protected boolean isFinished() {
        return isDone;
    }

    @Override
    protected void end() {
        isActive = false;
        Robot.m_fourbar.FourBarUp();

    }

    @Override
    protected void interrupted() {
        end();
    }

    public static boolean getIsActive() {
        return isActive;
    }
}

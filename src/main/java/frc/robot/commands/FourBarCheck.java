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
public class FourBarCheck extends Command {

    public FourBarCheck() {
        requires(Robot.m_fourbar);
    }

    @Override
    protected void execute() {
        if (Robot.m_oi.returnLBumper().get() == true && FourBarDown.getIsActive() == false) {
        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * help
 */
public class RobotSettingsFactory {
    
    public static RobotSettings getRobotSettings(boolean IsPracticeBot) {
        RobotSettings settings = new RobotSettings();

        if (IsPracticeBot = true) {
            settings.setM_frontLeft(RobotMap.PBfrontLeft);
            settings.setM_frontRight(RobotMap.PBfrontRight);
            settings.setM_rearLeft(RobotMap.PBrearLeft);
            settings.setM_rearRight(RobotMap.PBrearRight);
        } else if (IsPracticeBot = false) {
            settings.setM_frontLeft(RobotMap.frontLeft);
            settings.setM_frontRight(RobotMap.frontRight);
            settings.setM_rearLeft(RobotMap.rearLeft);
            settings.setM_rearRight(RobotMap.rearRight);
        } else {
            throw new NullPointerException("IsPracticeBot neither True nor False");
        }


        return settings;
    }
}

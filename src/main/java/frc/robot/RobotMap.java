/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.

  // Motors

  public static final int frontLeft = 1;
  public static final int frontRight = 2;
  public static final int rearLeft = 0;
  public static final int rearRight = 3;
  public static final int verticalClimb_1 = 4;
  public static final int verticalClimb_2 = 5;
  public static final int horizontalClimb_1 = 6;
  public static final int horizontalClimb_2 = 7;

  public static final int PBfrontLeft = 1;
  public static final int PBfrontRight = 2;
  public static final int PBrearLeft = 0;
  public static final int PBrearRight = 4;

  // Solenoids
  public static final int Hatch = 1;
  public static final int FourBar = 2;

  // Sensors
  public static final int UpperLimit = 0;
  public static final int LowerLimit = 1;
  public static final int LeftLimit = 2;
  public static final int RightLimit = 3;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}

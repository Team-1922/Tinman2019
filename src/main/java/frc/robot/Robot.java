/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Climber_Subsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FourBar_Subsystem;
import frc.robot.subsystems.Hatch_Subsystem;
// import frc.robot.subsystems.Cargo_Subsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  // BotInit.botInit();
  public static DriveTrain m_drivetrain = new DriveTrain();
  public static Hatch_Subsystem m_hatchsnatch = new Hatch_Subsystem();
  // public static Cargo_Subsystem m_cargo = new Cargo_Subsystem();
  public static FourBar_Subsystem m_fourbar = new FourBar_Subsystem();
  public static Climber_Subsystem m_climber = new Climber_Subsystem();
  public static OI m_oi;
  private static boolean m_IsStingray;
  private static Boolean chosen;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  SendableChooser<Boolean> m_BotChooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();

    // m_chooser.addDefault("Default Auto", new TankDrive()); //(Depricated,
    // replaced with setDefaultOption)
    m_chooser.setDefaultOption("Default Auto", new TankDrive());
    m_BotChooser.setDefaultOption("Competition Bot", false);
    m_BotChooser.addOption("Stingray", true);
    // m_chooser.addObject("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    SmartDashboard.putData("Bot In Use:", m_BotChooser);
    // NetworkTableEntry RealGyro = Shuffleboard.getTab("SmartDashboard").add("Real
    // Gyro", Robot.m_drivetrain.getAngle())
    // .withWidget("Gyro").getEntry();

    CameraServer.getInstance().startAutomaticCapture();
    //
    // Make sure to uncomment when we get the camera on the comp. bot
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    chosen = m_BotChooser.getSelected();

    if (chosen == null) {
      m_IsStingray = false;
    } else {
      m_IsStingray = true;
    }

  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    // m_oi.getOperator().setRumble(RumbleType.kLeftRumble,
    // Robot.m_oi.getOperator().getRawAxis(2));
    // m_oi.getOperator().setRumble(RumbleType.kRightRumble,
    // Robot.m_oi.getOperator().getRawAxis(3));

  }

  // private static int initCounter = 0;
  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    /*
     * 
     * m_drivetrain.ResetGyro(); SmartDashboard.putNumber("init", initCounter);
     * initCounter++; SmartDashboard.putNumber("While_Gyro_start",
     * Robot.m_drivetrain.getAngle());
     * 
     * while(Math.abs(Robot.m_drivetrain.getAngle()) > 5 ){
     * SmartDashboard.putNumber("While_Gyro", Robot.m_drivetrain.getAngle());
     * 
     * try { Thread.sleep(10); } catch(InterruptedException ex) {
     * 
     * } }
     * 
     * m_autonomousCommand = new Turn(-90);
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public static boolean getCurrentBot() {
    return m_IsStingray;
  }
}

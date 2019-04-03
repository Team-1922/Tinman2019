/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Climber_Subsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FourBar_Subsystem;
import frc.robot.subsystems.Hatch_Subsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static DriveTrain m_drivetrain = new DriveTrain();
  public static Hatch_Subsystem m_hatchsnatch = new Hatch_Subsystem();
  public static FourBar_Subsystem m_fourbar = new FourBar_Subsystem();
  public static Climber_Subsystem m_climber = new Climber_Subsystem();
  public static OI m_oi;
  private static boolean m_IsStingray;
  private static Boolean chosen;

  Command m_autonomousCommand;
  SendableChooser<Boolean> m_BotChooser = new SendableChooser<>();

  //Inversion Chooser
  public static SendableChooser<Boolean> m_vliChooser = new SendableChooser<Boolean>();
  public static SendableChooser<Boolean> m_vriChooser = new SendableChooser<Boolean>();
  public static SendableChooser<Boolean> m_hliChooser = new SendableChooser<Boolean>();
  public static SendableChooser<Boolean> m_hriChooser = new SendableChooser<Boolean>();

  //Phase Chooser
  public static SendableChooser<Boolean> m_vrpChooser = new SendableChooser<Boolean>();
  public static SendableChooser<Boolean> m_vlpChooser = new SendableChooser<Boolean>();
  public static SendableChooser<Boolean> m_hlpChooser = new SendableChooser<Boolean>();
  public static SendableChooser<Boolean> m_hrpChooser = new SendableChooser<Boolean>();

  public static SendableChooser<Boolean> m_stateFlag = new SendableChooser<Boolean>();


  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    m_BotChooser.setDefaultOption("Competition Bot", false);
    m_BotChooser.addOption("Stingray", true);
    SmartDashboard.putData("Bot In Use:", m_BotChooser);

    m_vliChooser.setName("VerticalLeftInvert");
    m_vliChooser.setDefaultOption("true", true);


    m_vriChooser.setName("VerticalRightInvert");
    m_vriChooser.setDefaultOption("true", true);

    m_hliChooser.setName("HorizontalLeftInvert");
    m_hliChooser.setDefaultOption("true", true);
    m_hliChooser.setDefaultOption("false", false);
    SmartDashboard.putData("Horizontal Left Invert", m_hliChooser);

    m_hriChooser.setName("HorizontalRightInvert");
    m_hriChooser.setDefaultOption("true", true);


    m_vlpChooser.setName("VerticalLeftPhase");
    m_vlpChooser.setDefaultOption("true", true);

    m_vrpChooser.setName("VerticalRightPhase");
    m_vrpChooser.setDefaultOption("true", true);

    m_hlpChooser.setName("HorizontalLeftPhase");
    m_hlpChooser.setDefaultOption("true", true);
    m_hlpChooser.setDefaultOption("false", false);
    SmartDashboard.putData("Horizontal Left Phase", m_hlpChooser);


    m_hrpChooser.setName("HorizontalRightPhase");
    m_hrpChooser.setDefaultOption("true", true);


    m_stateFlag.setName("ChangeToRefresh");
    m_stateFlag.setDefaultOption("true", true);
    m_stateFlag.setDefaultOption("false", false);
    SmartDashboard.putData("State Flag", m_stateFlag);
  
    m_climber.stop();


    NetworkTableEntry RealGyro = Shuffleboard
    .getTab("SmartDashboard")
    .add("Real Gyro", Robot.m_drivetrain.getAngle())
    .withWidget("Gyro")
    .getEntry();

    // CameraServer.getInstance().startAutomaticCapture();
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
    m_autonomousCommand = new TankDrive();
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
    // Scheduler.getInstance().run();
    teleopPeriodic();
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
    Robot.m_climber.resetEncoders();
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

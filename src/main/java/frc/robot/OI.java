/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.OperateClimber;
import frc.robot.commands.PixyMode;
import frc.robot.commands.ResetEncoders;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  // Object naming

  /*
   * Controllers
   */

  private Joystick m_LeftStick;
  private Joystick m_RightStick;
  private XboxController m_operator;

  /*
   * Buttons
   */

  private Button lbumper;
  private Button rbumper;
  private Button ltrigger;
  private Button rtrigger;
  private Button resetButton;
  private Button y;

  public OI() {

    // Object Binding

    /*
     * Controllers
     */

    m_LeftStick = new Joystick(0);
    m_RightStick = new Joystick(1);
    m_operator = new XboxController(2);

    /*
     * Buttons
     */

    lbumper = new JoystickButton(getOperator(), 5);
    rbumper = new JoystickButton(getOperator(), 6);
    ltrigger = new JoystickButton(getLeftStick(), 1);
    rtrigger = new JoystickButton(getRightStick(), 1);
    resetButton = new JoystickButton(getRightStick(), 2);
    y = new JoystickButton(getOperator(), 4);

    /*
     * Keybindings
     */

    rtrigger.whileHeld(new PixyMode());
    ltrigger.whileHeld(new DriveStraight());
    y.toggleWhenPressed(new OperateClimber());
    resetButton.whenPressed(new ResetEncoders());
  }

  /*
   * Object Getters
   */

  public Joystick getLeftStick() {
    return m_LeftStick;
  }

  public Joystick getRightStick() {
    return m_RightStick;
  }

  public XboxController getOperator() {
    return m_operator;
  }

  public Button returnLBumper() {
    return lbumper;
  }

  /*
   * Button Getters
   */

  public boolean getRBumper() {
    return rbumper.get();
  }

  public boolean getLBumper() {
    return lbumper.get();
  }

  public boolean getYButton() {
    return y.get();
  }

  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  // joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}

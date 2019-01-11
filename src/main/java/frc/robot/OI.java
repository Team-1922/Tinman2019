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
import frc.robot.commands.LiftBot_Command;
import frc.robot.commands.LowerBot_Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //Controllers
  private Joystick m_LeftStick;
  private Joystick m_RightStick;
  private XboxController m_operator;
  //Buttons
  private Button buttonA;
  private Button buttonB;
  private Button bumper;

  public OI() {
    //Controllers
    m_LeftStick = new Joystick(0); 
    m_RightStick = new Joystick(1);
    m_operator = new XboxController(2); 

    //Buttons
    buttonA = new JoystickButton(getOperator(), 1);
    buttonB = new JoystickButton(getOperator(), 2);
    bumper = new JoystickButton(getOperator(), 6);

    //Keybindings
    buttonA.whenPressed(new LiftBot_Command());
    buttonB.whenPressed(new LowerBot_Command());
  }


  //Getters & Setters
  public Joystick getLeftStick() {
    return m_LeftStick;
  }

  public Joystick getRightStick() {
    return m_RightStick;
  }

  public XboxController getOperator() {
    return m_operator;
  }

  public boolean getBumper() {
    return bumper.get();
  }















  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
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

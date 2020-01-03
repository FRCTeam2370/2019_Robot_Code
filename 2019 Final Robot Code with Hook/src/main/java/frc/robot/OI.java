/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.GrabHatch;
import frc.robot.commands.LightsOff;
import frc.robot.commands.PlaceHatch;
import frc.robot.commands.SwitchAllignmentMode;
import frc.robot.commands.SwitchClimbMode;

public class OI {
  public Joystick stick = new Joystick(0);

  private double deadband = .1;
  public double getDeadBand(){
    return deadband;
  }

  //LeftStick
  public double getLxAxis(){
    double raw = stick.getRawAxis(0);
    return Math.abs(raw) < deadband ? 0.0 : raw;
  }
  public double getLyAxis(){
    double raw = stick.getRawAxis(1);
    return Math.abs(raw) < deadband ? 0.0 : -raw;
  }
  //RightStick
  public double getRxAxis(){
    double raw = stick.getRawAxis(4);
    return Math.abs(raw) < deadband ? 0.0 : raw;
  }
  public double getRyAxis(){
    double raw = stick.getRawAxis(5);
    return Math.abs(raw) < deadband ? 0.0 : raw;
  }

  //POV
  public double getPOV(){
    double raw = stick.getPOV();
    return raw;
  }

  //Triggers
  public double getRightTrigger(){
    double raw = stick.getRawAxis(3);
    return Math.abs(raw) < deadband ? 0.0 : raw;
  }
  public double getLeftTrigger(){
    double raw = stick.getRawAxis(2);
    return Math.abs(raw) < deadband ? 0.0 : raw;
  }
  
  public double getTriggerDifference(){
    double raw = getLeftTrigger() - getRightTrigger();
    return raw;
  }

  public JoystickButton A = new JoystickButton(stick, 1);//a: Actuate Gripper
  public JoystickButton B = new JoystickButton(stick, 2);//b: Elevator Bottom
  public JoystickButton X = new JoystickButton(stick, 3);//x: Turn Off Lights
  public JoystickButton Y = new JoystickButton(stick, 4);//y: Enable Auto ALlign Mode
  public JoystickButton LB = new JoystickButton(stick, 5);//left Bumper: Carriage Down
  public JoystickButton RB = new JoystickButton(stick, 6);//rightBumper: Carriage Up
  public JoystickButton back = new JoystickButton(stick, 7);//back: 
  public JoystickButton start = new JoystickButton(stick, 8);//start: Enable Climb Mode??
  public JoystickButton LSB = new JoystickButton(stick, 9);//Left Stick Button: 
  public JoystickButton RSB = new JoystickButton(stick, 10);//Right Stick Button:

  
  public OI(){
    A.whenPressed(new GrabHatch());// Open and Close hatch manipulator
    B.whenPressed(new PlaceHatch());// Send Elevator to bottom
    X.whileActive(new LightsOff());// Turn Off Lights
    Y.whenPressed(new SwitchAllignmentMode());// Switch operator allign.
    LB.whileActive(new ElevatorDown());// Elevator Down While held
    RB.whileActive(new ElevatorUp());// Elevator Up while held.
    start.whenPressed(new SwitchClimbMode());// Switch climb mode.
  }
}
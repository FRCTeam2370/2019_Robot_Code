/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchManipulator extends Subsystem {
  private WPI_TalonSRX hatchManipulator = new WPI_TalonSRX(RobotMap.HATCH_COLLECTOR.value);
  private DigitalInput hallEffectSensor =  new DigitalInput(9);
  //Gabe is!
  public HatchManipulator(){
    hatchManipulator.configPeakCurrentLimit(0);
  }
  public boolean isOpened(){
    return hallEffectSensor.get();
  }

  public void open() {
    hatchManipulator.set(ControlMode.PercentOutput, 1);
  }

  public void close() {
    hatchManipulator.set(ControlMode.PercentOutput, -1);
  }

  public void stop(){
    hatchManipulator.set(ControlMode.PercentOutput, 0);
  }

  public void changeState(){
    if(isOpened()){
      close();
    }else{
      open();
    }
  }

  
  @Override
  public void initDefaultCommand() {
  }
}
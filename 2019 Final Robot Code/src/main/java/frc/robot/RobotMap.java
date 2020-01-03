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
public enum RobotMap {
  //talon inputs
  RIGHT_DRIVE_TRAIN(20), 
  LEFT_DRIVE_TRAIN(22), 
  RIGHT_REAR_DRIVE_TRAIN(21), 
  LEFT_REAR_DRIVE_TRAIN(23), 

  CLIMBERMASTER(30),
  CLIMBERFOLLOWER(31),

  BALL_COLLECTOR(51),
  WRIST(53),
  HATCH_COLLECTOR(52),

  ELEVATOR_MASTER_INPUT(40),
  ELEVATOR_FOLLOWER_INPUT(41),

  SLIDER(60)
  ;

  public final int value;
  RobotMap(int value){
    this.value = value;
  }
}

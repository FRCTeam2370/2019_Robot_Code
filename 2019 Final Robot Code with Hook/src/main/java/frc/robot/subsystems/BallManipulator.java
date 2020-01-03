/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeControl;

/**
 * Add your docs here.
 */
public class BallManipulator extends Subsystem {
  private WPI_TalonSRX collector = new WPI_TalonSRX(RobotMap.BALL_COLLECTOR.value);
//tfw Gabe is a hecking loser lmao ecks dee
  public void setCollectorSpeed(double speed){
    collector.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new IntakeControl());
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WristControl extends Command {
  private int updateRate = 75;
  private int newSetpoint = Robot.kWrist.getWristPosition();
  private int oldSetpoint = newSetpoint;

  public WristControl() {
    requires(Robot.kWrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Math for setting positions
    oldSetpoint = newSetpoint;
    if (!Robot.kClimber.getClimbMode()) {
      if (Robot.kWrist.getWristPosition() <= 2700 && Robot.kWrist.getWristPosition() >= -400) {
        newSetpoint = (int) (oldSetpoint + Math.round(updateRate * Robot.m_oi.getRyAxis()));
      } else if (Robot.m_oi.getRyAxis() < 0 && Robot.kWrist.getWristPosition() > 2700) {
        newSetpoint = (int) (oldSetpoint + Math.round(updateRate * Robot.m_oi.getRyAxis()));
      } else if (Robot.m_oi.getRyAxis() > 0 && Robot.kWrist.getWristPosition() < -400) {
        newSetpoint = (int) (oldSetpoint + Math.round(updateRate * Robot.m_oi.getRyAxis()));
      }
      Robot.kWrist.setWristPosition(newSetpoint);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

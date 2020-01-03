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
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbControl;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  private WPI_TalonSRX climbMotorMaster = new WPI_TalonSRX(RobotMap.CLIMBERMASTER.value);
  private WPI_TalonSRX climbMotorFollower = new WPI_TalonSRX(RobotMap.CLIMBERFOLLOWER.value);

  private boolean climbMode;
  //What if Gabe was a real boi?
  public Climber() {
    climbMotorFollower.follow(climbMotorMaster);
    climbMotorMaster.disable();
    climbMotorMaster.setSensorPhase(true);
    climbMotorMaster.setInverted(true);
    climbMotorFollower.setSensorPhase(true);
    
  }

  public boolean getClimbMode() {
    return climbMode;
  }

  public int getLifterEncoder() {
    return climbMotorMaster.getSensorCollection().getQuadraturePosition();
  }

  public void switchClimbMode() {
    climbMode = !climbMode;
  }

  public void setLifterSpeed(double xSpeed) {
    climbMotorMaster.set(ControlMode.PercentOutput, xSpeed);
  }

  public void setLifterSpeedWithDPad() {
    double POVIN = Robot.m_oi.getPOV();
    if (getClimbMode()) {
      if (POVIN == -1) { //MIDDLE
        Robot.kClimber.setLifterSpeed(0);
        Robot.kElevator.setElevatorSpeed(0);
      }
      if (POVIN == 0) { // UP
        Robot.kClimber.setLifterSpeed(1);
        if(!Robot.kElevator.getLimitSwitch()){
          //Robot.kElevator.setElevatorSpeed(-.40);
          Robot.kElevator.setElevatorSpeed(-.50);
        }else{
          Robot.kElevator.setElevatorSpeed(-.10);
        }
      }
      if (POVIN == 180) {
        Robot.kClimber.setLifterSpeed(-1);
      }
    }
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimbControl());
  }
}

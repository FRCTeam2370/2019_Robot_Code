/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */

public class Elevator extends Subsystem {
  private WPI_TalonSRX master = new WPI_TalonSRX(RobotMap.ELEVATOR_MASTER_INPUT.value);
  private DigitalInput limitSwitch = new DigitalInput(0);
  //private WPI_TalonSRX follower = new WPI_TalonSRX(RobotMap.ELEVATOR_follower_INPUT.value);
  //Guess who is a hecking toothpick
  private double speedUp;
  private double speedDown;

  public Elevator() {
    int timeout = 0x00;
    int slotIdx = 0;

    double Kp = .94;
    double Ki = 0.0;
    double Kd = 0.0;

    speedUp = 1;
    speedDown = -.8;

    //follower.follow(master);
    master.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeout);
    master.setSensorPhase(true);
    master.setInverted(true);
    //follower.setInverted(true);

    master.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);
    master.configNominalOutputForward(0);
    master.configNominalOutputReverse(0);
    master.configPeakOutputForward(1, timeout);
    master.configPeakOutputReverse(-1, timeout);

    //follower.configNominalOutputForward(0);
    //follower.configNominalOutputReverse(0);
    //follower.configPeakOutputForward(1, timeout);
    //follower.configPeakOutputReverse(-1, timeout);

    master.config_kP(slotIdx, Kp);
    master.config_kI(slotIdx, Ki);
    master.config_kD(slotIdx, Kd);
  }

  public double getElevatorEncoder() {
    return master.getSensorCollection().getQuadraturePosition();
  }

  public boolean getLimitSwitch(){
    return !limitSwitch.get();
  }

  public void zeroEncoders(){
    master.getSensorCollection().setQuadraturePosition(0, 0x00);
  }

  public void setElevatorSpeed(double speed) {
    master.set(ControlMode.PercentOutput, speed);
  }

  public void elevatorUp() {
    setElevatorSpeed(speedUp);
  }

  public void elevatorDown() {
    setElevatorSpeed(speedDown);
  }
  
  public void stopElevator(){
    setElevatorSpeed(.05);
  }

  @Override
  public void initDefaultCommand() {
    
  }
}

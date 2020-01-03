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

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.WristControl;

/**
 * PID Control
 */
public class Wrist extends Subsystem {
  private WPI_TalonSRX wrist = new WPI_TalonSRX(RobotMap.WRIST.value);

  public Wrist() {
    int timeout = 0;
    int slotIdx = 0;

    double kp = 1.4;
    double ki = 0.0;
    double kd = 0.0;

    wrist.setSensorPhase(true);
    wrist.setInverted(true);
    wrist.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeout);
    wrist.configSetParameter(ParamEnum.eFeedbackNotContinuous, 1, 0x00, 0x00, 0x00);
    wrist.configNominalOutputForward(0);
    wrist.configNominalOutputReverse(0);
    wrist.configPeakOutputForward(.5);
    wrist.configPeakOutputReverse(-.5);

    wrist.config_kP(slotIdx, kp);
    wrist.config_kI(slotIdx, ki);
    wrist.config_kD(slotIdx, kd);




    
    wrist.getSensorCollection().setQuadraturePosition(0, timeout);
  }
  //Thou shalt have wrists - Gabe (aka God)
  public int getWristPosition() {
    return wrist.getSensorCollection().getQuadraturePosition();
  }

  public void setWristPosition(int position) {
    wrist.set(ControlMode.Position, position);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new WristControl());
  }
}

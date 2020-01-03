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
import frc.robot.commands.SliderWithDPad;

/**
 * Add your docs here.
 */
public class Slider extends Subsystem {
  private WPI_TalonSRX sliderTalon = new WPI_TalonSRX(RobotMap.SLIDER.value);
  //Gabe finna Slide into loser vile

  public Slider(){
    sliderTalon.configPeakCurrentLimit(20);
  }
  public int getEncoderposition(){
    return sliderTalon.getSensorCollection().getQuadraturePosition();
  }

  public boolean getLimitSwitch(){
    return sliderTalon.getSensorCollection().isFwdLimitSwitchClosed();
  }

  public void setEncoderPosition(int position){
    sliderTalon.getSensorCollection().setQuadraturePosition(position, 0x00);
  }

  public void resetEncoder(){
    setEncoderPosition(0);
  }

  public void setSliderSpeed(double speed){
    sliderTalon.set(ControlMode.PercentOutput, speed);
  }

  public void sliderWithDPad(){
    if(!Robot.kClimber.getClimbMode()){ //if not climbing
      if(Robot.m_oi.getPOV() == 90){ //if POV Right
        Robot.kSlider.setSliderSpeed(.5); //move elevator forwards
      }
      else if(Robot.m_oi.getPOV() == 270 && getEncoderposition() < 66000){ //if POV left
        Robot.kSlider.setSliderSpeed(-.5); //move elevator backwards
      }else{
        stopSlider();
      }
    }
  }

  public void stopSlider(){
    sliderTalon.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new SliderWithDPad());
  }
}

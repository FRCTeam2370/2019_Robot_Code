
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class LEDs extends Subsystem {
  public I2C i2c;
  private byte [] climbMode;
  private byte[] fireworks;
  private byte[] climb;
  private byte[] climbFast;
  private byte[] receivable;
  private byte[] hatchIn;
  private byte[] noHatch;
  private byte[] off;

  public static boolean lightPower = true; //true = on :: false = off

  public LEDs() {
    i2c = new I2C(I2C.Port.kOnboard, 10);
    receivable = new byte[1];
    climbMode = new byte[1];
    fireworks = new byte[1];
    climb = new byte[1];
    climbFast = new byte[1];
    hatchIn = new byte[1];
    noHatch = new byte[1];
    off = new byte[1];

    climbMode[0] = 5;
    fireworks[0] = 0;
    climb[0] = 2;
    climbFast[0] = 3;
    hatchIn[0] = 7;
    noHatch[0] = 8;
    off[0] = 6;
  }
  public boolean getLightPower(){
    return lightPower;
  }
  public void sendLightOption() {
    if(RobotState.isAutonomous()){ // if in auto
      i2c.transaction(fireworks, fireworks.length, receivable, receivable.length); //  do cool fireworks!
    }
    else if(Robot.kClimber.getClimbMode()){
      i2c.transaction(climbMode, climbMode.length, receivable, receivable.length);
    } else if (Timer.getMatchTime() < 25 && Timer.getMatchTime() > 15 && RobotState.isOperatorControl()) { // if within 25 seconds
      i2c.transaction(climb, climb.length, receivable, receivable.length); // climb
    } else if (Timer.getMatchTime() < 15 && Timer.getMatchTime() > 0 && RobotState.isOperatorControl()){ // if within 20 seconds
      i2c.transaction(climbFast, climbFast.length, receivable, receivable.length); // CLIMB!!!
    } else if(!lightPower){// lights are off
      i2c.transaction(off, off.length, receivable, receivable.length);// turn off lights 
    }else if(lightPower){// lights are on
      if(Robot.kHatchManipulator.isOpened()){ // if hatch manipulator open
      i2c.transaction(hatchIn, hatchIn.length, receivable, receivable.length); // light up green
    } else{ // if hatch manipulator closed
      i2c.transaction(noHatch, noHatch.length, receivable, receivable.length); // light up purple
    }
  }
  }
    //if (Timer.getMatchTime() < 15){
      //i2c.transaction(auto, auto.length, receivable, receivable.length);
    //}else{
      //i2c.transaction(teleOp, teleOp.length, receivable, receivable.length);
    //}

    public void switchLightPower(){
      lightPower = !lightPower;
    }
    
  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new WriteLights());
  }
}

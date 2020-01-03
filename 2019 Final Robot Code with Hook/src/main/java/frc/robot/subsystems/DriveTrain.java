/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveControl;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  /** All Motors for the Drive train **/
  private WPI_TalonSRX rightWheelsMaster = new WPI_TalonSRX(RobotMap.RIGHT_DRIVE_TRAIN.value);
  private WPI_TalonSRX rightWheelsFollower = new WPI_TalonSRX(RobotMap.RIGHT_REAR_DRIVE_TRAIN.value);
  private WPI_TalonSRX leftWheelsMaster = new WPI_TalonSRX(RobotMap.LEFT_DRIVE_TRAIN.value);
  private WPI_TalonSRX leftWheelsFollower = new WPI_TalonSRX(RobotMap.LEFT_REAR_DRIVE_TRAIN.value);

  private SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightWheelsMaster, rightWheelsFollower);
  private SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftWheelsMaster, leftWheelsFollower);

  private DifferentialDrive m_drive = new DifferentialDrive(leftMotors, rightMotors);

  /** Limelight tables and offsets */

  private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private static NetworkTableEntry tx = table.getEntry("tx");
  private static NetworkTableEntry ty = table.getEntry("ty");
  private static NetworkTableEntry ta = table.getEntry("ta");
  private boolean operatorAlign = true; 
  private static double offsetRatio = 15;
  public static double offsetInDegrees;

  // Pls move Thank - Gabe
  public DriveTrain() {
    offsetInDegrees = 3.3;
  }

  public double getOffsetDegrees(){
    return offsetInDegrees;
  }
  public double getLimelightXOffset() {
    double x = tx.getDouble(0.0);
    return x;
  }

  public double getLimelightYOffset() {
    double y = ty.getDouble(0.0);
    return y;
  }

  public double getLimelightTargetArea() {
    double a = ta.getDouble(0.0);
    return a;
  }

  public boolean getOperatorAllign() {
    return operatorAlign;
  }

  public double getDriveSpeed(double xSpeed){
    double driveSpeed;
    if (xSpeed < 0) {
      driveSpeed = (xSpeed * xSpeed) * -1;
    }else{
      driveSpeed = (xSpeed * xSpeed);
    }

    return driveSpeed;
  }

  public double getRotation() {
    // Negetive is Left
    // Positive is Right
    return (getLimelightXOffset() - offsetInDegrees) / offsetRatio;
  }

  public void switchOperatorControl() {
    operatorAlign = !operatorAlign;
  }

  public void setCameraMode() {
    if (operatorAlign) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    } else {

      NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    }
  }

  public void drive(double xSpeed, double zRotation) {
    setCameraMode();
    if(operatorAlign){
      m_drive.arcadeDrive(getDriveSpeed(xSpeed), zRotation);
    }else{
      m_drive.arcadeDrive(getDriveSpeed(xSpeed), getRotation());
    }
  }
  //   if (operatorAlign && xSpeed > 0) {
  //     m_drive.arcadeDrive(driveSpeed, zRotation);
  //   } else if (operatorAlign && xSpeed < 0) {
  //     m_drive.arcadeDrive(-driveSpeed, zRotation);
  //   } else if (operatorAlign && xSpeed == 0) {
  //     m_drive.arcadeDrive(0, zRotation);
  //   } else {
  //     m_drive.arcadeDrive(xSpeed, getRotation());
  //   }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveControl());
  }
}

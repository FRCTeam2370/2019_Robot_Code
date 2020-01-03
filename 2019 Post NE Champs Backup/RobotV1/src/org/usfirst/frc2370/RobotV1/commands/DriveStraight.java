// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2370.RobotV1.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2370.RobotV1.RobotMap;
//import org.usfirst.frc.team2370.robot.RobotMap;
import org.usfirst.frc2370.RobotV1.Robot;

/**
 *
 */
public class DriveStraight extends Command {
	
	final double Kp = 0.03;
	double speed;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveStraight(double time, double speed) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveSubsystem);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        
        requires(Robot.driveSubsystem);
        
        this.speed = speed;
        setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.arcadeDrive(speed, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//float angle = org.usfirst.frc2370.RobotV1.RobotMap.AHRS.getRawMagZ();
    	//Robot.driveSubsystem.arcadeDrive(speed, -angle * Kp);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

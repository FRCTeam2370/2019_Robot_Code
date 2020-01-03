// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2370.RobotV1;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.AxisCamera;

//import javax.swing.text.html.TableView.RowView;

import org.usfirst.frc2370.RobotV1.*;

import org.usfirst.frc2370.RobotV1.RobotMap;
import org.usfirst.frc2370.RobotV1.RotarySwitchMode;
import org.usfirst.frc2370.RobotV1.commands.*;
import org.usfirst.frc2370.RobotV1.subsystems.*;

//import com.ni.vision.NIVision;
//import com.ni.vision.NIVision.Image;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    //SendableChooser autoChooser;
    
    CameraServer server;
    
    int session;
    //Image frame;
    //AxisCamera camera;
    
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static driveSubsystem driveSubsystem;
    public static ArmPIDSubsystem armPIDSubsystem;
    public static Shooter shooter;
    public static WinchSubsystem winchSubsystem;
    public static HookLifterSubsystem hookLifterSubsystem;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
    
    	//frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    
    	// Initialize the camera
    	//camera = new AxisCamera("10.23.70.11");

    	SmartDashboard.putNumber("Ball Distance", RobotMap.shooterDistanceSensor1.getValue());
    
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveSubsystem = new driveSubsystem();
        armPIDSubsystem = new ArmPIDSubsystem();
        shooter = new Shooter();
        winchSubsystem = new WinchSubsystem();
        hookLifterSubsystem = new HookLifterSubsystem();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.


        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autonomousCommand = new Autogroup1();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        
        oi = new OI();
        
        /*autoChooser = new SendableChooser();
        
        
        autoChooser.addDefault("Low bar", new AutoLowBar());
        autoChooser.addObject("Moat", new AutoMoat());
        autoChooser.addObject("Ramparts", new AutoRamparts());
        autoChooser.addObject("Rock Wall/Drive to Defense", new AutoRockWall());
        autoChooser.addObject("Rough Terrain", new AutoRoughTerrain());
        autoChooser.addObject("Do Nothing", new AutoDoNothing());
        
        SmartDashboard.putData("Autonomous mode chooser", autoChooser);
        */        
        //camera.getImage(frame);
        
        server = CameraServer.getInstance();
        server.setQuality(50);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture("cam1");

    }
    
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    
    public void autonomousInit() {
    	
    	RotarySwitchMode rotary = RobotMap.AutonomousMode();
    	if (rotary == RotarySwitchMode.C)
    	{
    		autonomousCommand = new AutoDoNothing();
    		SmartDashboard.putString("Auto Chosen", "AutoDoNothing");
    	}	
    	else if (rotary == RotarySwitchMode.B)
    	{
    		autonomousCommand = new AutoLowBar();
			SmartDashboard.putString("Auto Chosen", "AutoLowbar");
    	}
    	else if (rotary == RotarySwitchMode.A)
    	{
    		autonomousCommand = new AutoMoat();
    		SmartDashboard.putString("Auto Chosen", "AutoMoat");
    	}
    	else if (rotary == RotarySwitchMode.D)
    	{
    		autonomousCommand = new AutoSuperLowBar();
    		SmartDashboard.putString("Auto Chosen", "AutoSuperLowBar");
    	}
    	//else
    	
    	//SmartDashboard.putNumber("Ball Distance", RobotMap.shooterDistanceSensor1.getValue());
    	
        // schedule the autonomous command
        if (autonomousCommand != null)
        	autonomousCommand.start();

    	//autonomousCommand = (Command) autoChooser.getSelected();
        // schedule the autonomous command (example)
        //autonomousCommand.start();
        //if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	SmartDashboard.putNumber("Ball Distance", RobotMap.shooterDistanceSensor1.getValue());
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        //Robot.shooter.Suck();
        
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();        
        
        if(Robot.oi.joystick1.getRawAxis(2) > 0.25)
        {
        	// Production Bot Values
        	//Robot.armPIDSubsystem.setSetpoint(285);
        	
        	// Practice Bot Values
        	Robot.armPIDSubsystem.setSetpoint(270);
        }
        else if(Robot.oi.joystick1.getRawAxis(3) > 0.25)
        {
        	// Production Bot Values
        	//Robot.armPIDSubsystem.setSetpoint(285);

        	// Practice Bot Values
        	Robot.armPIDSubsystem.setSetpoint(270);
        }
        else
        {
        	// Production Bot Values
        	//Robot.armPIDSubsystem.setSetpoint(45);

        	// Practice Bot Values        	
        	Robot.armPIDSubsystem.setSetpoint(70);
        }
        
        SmartDashboard.putNumber("Axis 3", Robot.oi.joystick1.getRawAxis(3));
        SmartDashboard.putNumber("Axis 2", Robot.oi.joystick1.getRawAxis(2));
        
        SmartDashboard.putNumber("Arm Encoder Position", Robot.armPIDSubsystem.getPosition());
        SmartDashboard.putNumber("Arm Setpoint", Robot.armPIDSubsystem.getSetpoint());
        SmartDashboard.putBoolean("On Target", Robot.armPIDSubsystem.onTarget() );
        
        SmartDashboard.putBoolean("Digital 7", !RobotMap.autonomousSelectionMode1.get());
        SmartDashboard.putBoolean("Digital 8", !RobotMap.autonomousSelectionMode2.get());
        SmartDashboard.putBoolean("Digital 9", !RobotMap.autonomousSelectionMode3.get());
        SmartDashboard.putString("Rotary Switch", RobotMap.AutonomousMode().toString());
        
        SmartDashboard.putNumber("Ball Distance", RobotMap.shooterDistanceSensor1.getValue());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

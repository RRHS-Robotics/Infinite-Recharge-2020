/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.awt.Color;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.ColorCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;
import frc.robot.subsystems.WheelSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final DriveTrainSubsystem DriveTrain = new DriveTrainSubsystem();
	public static final WheelSubsystem wheelSubsystem = new WheelSubsystem();
	public static final UltrasonicSubsystem ultrasonic = new UltrasonicSubsystem();
	public static final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static OI refOI = new OI();
	public static RobotMap robotMap = new RobotMap();
	
	public Boolean DEBUG;
	public static boolean debug = true;

	public Preferences prefs;
	
	Command driveTrainCommand = new DriveCommand();
	ColorCommand colorCommand = new ColorCommand();
	IntakeCommand intakeCommand = new IntakeCommand();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		prefs = Preferences.getInstance();
		DEBUG = prefs.getBoolean("DEBUG", false);
		
		initCamera("Primary Camera", 0);
		initCamera("Secondary Camera", 1);
		
		RobotMap.robotDriveMain = new DifferentialDrive(RobotMap.leftDrive, RobotMap.rightDrive);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		
	}

	Color oldColor;
	
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		/*System.out.println(ultrasonic.getInches());
		Color c = colorCommand.getClosestColor(colorCommand.getColor());
		//if (c.equals(oldColor)) return;
		if (!refOI.controller.getStartButtonPressed()) return;
		if (c==Color.GREEN) System.out.println("Green");
		if (c==Color.RED) System.out.println("Red");
		if (c==Color.BLUE) System.out.println("Blue");
		if (c==Color.YELLOW) System.out.println("Yellow");
		if (c==Color.BLACK) System.out.println("Black");
		if (c==Color.WHITE) System.out.println("White");
		oldColor = c;*/
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (driveTrainCommand != null) {
			driveTrainCommand.start();
		}
		if (colorCommand != null) {
			colorCommand.start();
		}
		if (intakeCommand != null) {
			intakeCommand.start();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	public void initCamera(String name, int ID) {
		UsbCamera cam = CameraServer.getInstance().startAutomaticCapture(ID);
		cam.setFPS(60);
		cam.setResolution(360, 360);
		CameraServer.getInstance().addServer(name, ID).setSource(cam);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {}
}

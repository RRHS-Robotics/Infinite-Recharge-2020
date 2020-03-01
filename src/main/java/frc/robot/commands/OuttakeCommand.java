 package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class OuttakeCommand extends Command {

	private double speed = -1;

	public OuttakeCommand() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.outtakeSubsystem);
	}
	public OuttakeCommand(double speed, long time) {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.intakeSubsystem);
    	this.speed  = speed;
    	setTimeout(time);
	}
	
	protected void execute() {
		if (speed == -1) {
			Robot.outtakeSubsystem.spin(Robot.refOI.outtakeButton());
		} else Robot.outtakeSubsystem.spin(speed);
	}
	
	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.outtakeSubsystem.stop(); // stop robot
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.outtakeSubsystem.stop();
    }
}

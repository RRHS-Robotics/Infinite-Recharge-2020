package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class OuttakeCommand extends Command {

	public OuttakeCommand() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.outtakeSubsystem);
	}
	protected void execute() {
		Robot.outtakeSubsystem.spin(Robot.refOI.outtakeButton()*.58);
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

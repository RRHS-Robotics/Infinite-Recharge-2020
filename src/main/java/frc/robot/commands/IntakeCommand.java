package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeCommand extends Command {
	private boolean invert;

	public IntakeCommand() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.intakeSubsystem);
	}
	protected void execute() {
		if (Robot.refOI.intakeReverseButton()) invert=!invert;
		Robot.intakeSubsystem.spin((invert?1:-1)*Robot.refOI.intakeButton());
	}
	
	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.intakeSubsystem.stop(); // stop robot
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.intakeSubsystem.stop();
    }
}

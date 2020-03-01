package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeCommand;

public class IntakeSubsystem extends Subsystem {
	@Override protected void initDefaultCommand() {
		setDefaultCommand(new IntakeCommand());
	}
	public IntakeSubsystem() {}
	
	public void spin(Double speed) {
		RobotMap.intakeMotor.set(speed*1);
	}
	public void stop() {
		RobotMap.intakeMotor.stopMotor();
	}
}

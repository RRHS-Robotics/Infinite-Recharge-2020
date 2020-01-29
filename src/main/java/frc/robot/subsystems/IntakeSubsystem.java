package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeCommand;

public class IntakeSubsystem extends Subsystem {
	@Override protected void initDefaultCommand() {
		setDefaultCommand(new IntakeCommand());
	}
	public void spin(Double speed) {
		System.out.println("Spin");
		RobotMap.intakeMotor.set(speed*.33);
	}
	public void stop() {
		System.out.println("Stop");
		RobotMap.intakeMotor.stopMotor();
	}
}

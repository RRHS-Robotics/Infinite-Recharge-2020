package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeCommand;

public class OuttakeSubsystem extends Subsystem {
	@Override protected void initDefaultCommand() {
		setDefaultCommand(new IntakeCommand());
	}
	public void spin(Double speed) {
		System.out.println("Spin");
		RobotMap.outtakeMotor.set(speed);
		RobotMap.outtakeMotor0.set(speed);
	}
	public void stop() {
		System.out.println("Stop");
		RobotMap.outtakeMotor.stopMotor();
		RobotMap.outtakeMotor0.stopMotor();
	}
}

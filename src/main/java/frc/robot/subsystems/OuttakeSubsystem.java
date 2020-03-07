package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.OuttakeCommand;

public class OuttakeSubsystem extends Subsystem {
	@Override protected void initDefaultCommand() {
		setDefaultCommand(new OuttakeCommand());
	}
	public void spin(Double speed) {
		RobotMap.leftOuttakeMotor.set(speed);
		RobotMap.rightOuttakeMotor.set(speed);
	}
	public void stop() {
		RobotMap.leftOuttakeMotor.stopMotor();
		RobotMap.rightOuttakeMotor.stopMotor();
	}
}

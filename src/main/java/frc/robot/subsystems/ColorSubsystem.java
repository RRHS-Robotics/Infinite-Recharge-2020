package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ColorCommand;

public class ColorSubsystem extends Subsystem {
	@Override protected void initDefaultCommand() {
		setDefaultCommand(new ColorCommand());
	}
	public void spin() {
		System.out.println("Spin");
		RobotMap.colorMotor.set(.4);
	}
	public void stop() {
		System.out.println("Stop");
		RobotMap.colorMotor.stopMotor();
	}
}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class UltrasonicSubsystem extends Subsystem {
	@Override protected void initDefaultCommand() {}
	
	public double getInches() {
		return (double)(RobotMap.ultrasonic.getValue()*.125);
	}
}

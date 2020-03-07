package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoModeCommandGroup extends CommandGroup {
	public AutoModeCommandGroup(String string) {
		switch(string) {
			case "left":
				addSequential(new DriveCommand(0.1, 0, .1));
				addSequential(new DriveCommand(0.1, .25, .1));
				addSequential(new DriveCommand(0.1, 0, .1));
				addSequential(new DriveCommand(0.1, .25, .1));
				addSequential(new DriveCommand(0.1, 0, .1));
			case "middle":
				//addSequential(new DriveCommand(0.1, 0, .1));
				addParallel(new OuttakeCommand(.6, 3));
				addParallel(new IntakeCommand(-.5, 3));
			case "right":
				addSequential(new DriveCommand(0.1, 0, .1));
				addSequential(new DriveCommand(0.1, .25, .1));
				addSequential(new DriveCommand(0.1, 0, .1));
				addSequential(new DriveCommand(0.1, .25, .1));
				addSequential(new DriveCommand(0.1, 0, .1));
		}
	}
}

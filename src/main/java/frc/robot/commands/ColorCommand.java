package frc.robot.commands;

import java.awt.Color;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ColorCommand extends Command {
	public ArrayList<ColorPair> colormap = new ArrayList<ColorPair>();
	
	public ColorCommand() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.wheelSubsystem);
    	putColorPair(Color.GREEN, new Color(40, 150, 70));
    	putColorPair(Color.BLUE, new Color(30, 110, 120));
    	putColorPair(Color.RED, new Color(130, 90, 40));
    	putColorPair(Color.YELLOW, new Color(80, 140, 30));
    	
		putColorPair(Color.GREEN, Color.GREEN);
		putColorPair(Color.BLUE, Color.BLUE);
		putColorPair(Color.RED, Color.RED);
		putColorPair(Color.YELLOW, Color.YELLOW);
    }
	
	public void putColorPair(Color translated, Color color) {
		colormap.add(new ColorPair(color, translated));
	}

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    private Color color;
    private int times;
    private boolean isNewColor = false;
    private boolean isDoingFour = false;
    private boolean isDoingColor = false;
    private boolean isColorDetermined = false;
    public Color getClosestColor(Color color) {
    	//System.out.println(color);
    	int[] scores = new int[colormap.size()];
    	for (int i=0; i<scores.length; i++) {
    		scores[i] =
    			Math.abs(colormap.get(i).color.getBlue()-color.getBlue()) +
    			Math.abs(colormap.get(i).color.getGreen()-color.getGreen()) +
    			Math.abs(colormap.get(i).color.getRed()-color.getRed());
    	}
    	int ls = scores[0];
    	int g = 0;
    	for (int i=1; i<scores.length; i++) {
    		if (scores[i]<ls) {
    			ls = scores[i];
    			g=i;
    		}
    	}
		return colormap.get(g).translated;
    }
    private boolean isCloseToColor(Color color, Color ccolor) {return color==ccolor;}
    public Color getColor() {
    	return new Color(
    		(int) (RobotMap.colorSensor.getColor().red*255),
    		(int) (RobotMap.colorSensor.getColor().green*255),
    		(int) (RobotMap.colorSensor.getColor().blue*255)
    );}
    protected void execute() {
    	if (!Robot.refOI.yellowButtonHeld()&&!isDoingFour&&!isDoingColor)
    		Robot.wheelSubsystem.stop();
    	
		if (Robot.refOI.yellowButtonHeld()) {
			isDoingFour = false; isDoingColor = false;
			Robot.wheelSubsystem.spin();
		}
		if (!isDoingFour&&!isDoingColor&&Robot.refOI.spinFourButton()) {
			color = getColor();
			isDoingFour = true; isNewColor = false; times = 0;
			System.out.println("Spin four pressed");
			Robot.wheelSubsystem.spin();
		}
		if (!isDoingFour&&!isDoingColor&&Robot.refOI.spinColorButton()) {
			color = getColor();
			isDoingFour = true; isNewColor = false; times = 0;
			System.out.println("Spin color pressed");
			Robot.wheelSubsystem.spin();
		}
		if (isDoingFour) {
			if (times<8) {
				if (isNewColor&&isCloseToColor(color, getColor())) {
					times++;
					isNewColor = false;
				} else if (!isCloseToColor(color, getColor())) {
					isNewColor = true;
				}
			} else {
				Robot.wheelSubsystem.stop();
				isDoingFour = false;
			}
		}
		if (isDoingColor) {
			if (isColorDetermined) {
				if (isCloseToColor(color, getColor())) {
					Robot.wheelSubsystem.stop();
					isDoingColor = false;
				}
			} else {
				isColorDetermined = true;
				if (Robot.refOI.greenButton()) {
					color = Color.GREEN;
				} else if (Robot.refOI.yellowButton()) {
					color = Color.YELLOW;
				} else if (Robot.refOI.redButton()) {
					color = Color.RED;
				} else if (Robot.refOI.blueButton()) {
					color = Color.BLUE;
				} else {
					isColorDetermined = false;
				}
			}
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.wheelSubsystem.stop(); // stop robot
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.wheelSubsystem.stop();
    }
}

class ColorPair {
	public Color color;
	public Color translated;
	public ColorPair(Color color, Color translated) {
		this.color = color;
		this.translated = translated;
	}
}

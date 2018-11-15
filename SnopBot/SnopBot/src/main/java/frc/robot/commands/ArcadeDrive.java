package src.main.java.frc.robot.commands;

import src.main.java.frc.robot.OI;
import src.main.java.frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArcadeDrive extends Command {
	private DriveTrain ssTrain;
	private OI oi;
	
    public ArcadeDrive() {
    	ssTrain = DriveTrain.getInstance();
    	requires(ssTrain);
    	oi = OI.getInstance();
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ssTrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run

    protected void execute() {
    	
    	ssTrain.Arcade(oi.stick.getY(), oi.stick.getX(), 1); //Kjører Arcade() i DriveTrain subsystemet
    	System.out.println(ssTrain.getTestEncoderPos() + " pos");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
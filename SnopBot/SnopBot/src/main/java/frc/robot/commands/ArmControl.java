/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package src.main.java.frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import src.main.java.frc.robot.OI;
import src.main.java.frc.robot.subsystems.TwoStageArm;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmControl extends Command {

  private TwoStageArm ssArm;
  private OI oi;
  
  public ArmControl() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    ssArm = TwoStageArm.getInstance();
    	requires(ssArm);
    	oi = OI.getInstance();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double armX = (double)SmartDashboard.getNumber("armX", 0.0);
    double armY = (double)SmartDashboard.getNumber("armY", 0.0);

    ssArm.MoveStage2((oi.stick.getRawAxis(3)+oi.stick.getRawAxis(2))*0.3, armX, armY);
    ssArm.MoveStage1(oi.stick.getRawAxis(2)*0.3);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

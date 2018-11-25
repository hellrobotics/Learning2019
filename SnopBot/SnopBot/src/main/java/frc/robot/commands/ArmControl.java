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

  double armX = 20.0;
  double armY = 40.0;

  double rMin = 10.0;
  double rMax = 80.0;
  double xMin = 5.0;
  double xMax = 80.0;
  double yMin = 5.0;
  double yMax = 80.0;

  int Enc1 = 0;
  int Enc2 = 0;
  
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

    if (oi.stick.getRawButton(3)){
      Enc1 = 70;
      Enc2 = 100;
    } else if (oi.stick.getRawButton(2)) {
      Enc1 = 0;
      Enc2 = 0;
    }
    ssArm.MoveToEnc(Enc1, Enc2);
    
    /*
    ssArm.MoveStage2((oi.stick.getRawAxis(3)+oi.stick.getRawAxis(2))*0.3);
    ssArm.MoveStage1(oi.stick.getRawAxis(2)*0.3);
    */


    //System.out.println(ssArm.getStage2EncoderPos() + " pos = " + (double)ssArm.getStage2EncoderPos()*ssArm.encToDeg + " should be: " + (int)Math.round(ssArm.calculateAngle2(armX,armY)/ssArm.encToDeg) + " pos = " + ssArm.calculateAngle2(armX,armY));
  }

  void CoordMove () {
    double xIn = oi.stick.getRawAxis(2);
    double yIn = oi.stick.getRawAxis(3)*-1;

    if (xIn < 0.1 && xIn > -0.1) {
      xIn = 0;
    } else if (xIn < 0 && Math.sqrt(armX*armX+armY*armY) <= rMin) {
      xIn = 0;
    } else if (xIn < 0 && armX <= xMin) {
      xIn = 0;
    } else if (xIn > 0 && Math.sqrt(armX*armX+armY*armY) >= rMax) {
      xIn = 0;
    } 

    if (yIn < 0.1 && yIn > -0.1) {
      yIn = 0;
    } else if (yIn < 0 && Math.sqrt(armX*armX+armY*armY) <= rMin) {
      yIn = 0;
    } else if (yIn < 0 && armY <= yMin) {
      yIn = 0;
    } else if (yIn > 0 && Math.sqrt(armX*armX+armY*armY) >= rMax) {
      yIn = 0;
    } 

    armX += xIn;
    armY += yIn;

    ssArm.MoveToCoord(armX, armY);

    SmartDashboard.putNumber("armX", armX);
    SmartDashboard.putNumber("armY", armY);
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

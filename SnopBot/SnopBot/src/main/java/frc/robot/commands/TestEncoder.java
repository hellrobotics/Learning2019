/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package src.main.java.frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TestEncoder extends Command {
  public TestEncoder() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    int encNum = (int)SmartDashboard.getNumber("TestEncoderNumber", 0); //Henter valgt enkodernummer fra dashboardet
    int encPos = getEncoderPosition(encNum); //Finner posisjonen til den valgte enkoderen
    SmartDashboard.putNumber("TestEncoderResult", encPos); //Sender posisjonen til dashboardet
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

  public int getEncoderPosition (int motorNum) { //Finner posisjonen til den valgte enkoderen -- Motoren må være en talon

    WPI_TalonSRX encMotor = new WPI_TalonSRX(motorNum);
    return encMotor.getSelectedSensorPosition(0);
  }
}

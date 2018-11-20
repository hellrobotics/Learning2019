/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import src.main.java.frc.robot.commands.ArcadeDrive;
import src.main.java.frc.robot.commands.ArmControl;


/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends IterativeRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;

  Command arcadeDrive = new ArcadeDrive();
  Command armControl = new ArmControl();

  @Override
  public void robotInit() {
  

  }

  @Override
  public void teleopInit() {
    arcadeDrive.start();
    armControl.start();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    arcadeDrive.start();
  }

  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package src.main.java.frc.robot.subsystems;

import src.main.java.frc.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Servo;


/**
 * Add your docs here.
 */
public class CandyEngine extends Subsystem {

  VictorSP candyFeed = new VictorSP(RobotMap.CANDYFEED);
  Servo candyHatch = new Servo(RobotMap.CANDYHATCH);

  private static CandyEngine m_instance;
	public static synchronized CandyEngine getInstance() {
		if (m_instance == null){
			m_instance = new CandyEngine();
		}
		return m_instance;
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void RunFeed(double speed) {
    candyFeed.set(speed);
  }
  public void RunHatch(double angle) {
    candyHatch.setAngle(angle);
  }
}

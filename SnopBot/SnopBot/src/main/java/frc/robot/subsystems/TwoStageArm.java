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
import edu.wpi.first.wpilibj.Encoder;

/**
 * Add your docs here.
 */
public class TwoStageArm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  double encToDeg = 360.0/559.0;

  public static VictorSP armStage1 = new VictorSP(RobotMap.ARMSTAGE1);
  public static VictorSP armStage2 = new VictorSP(RobotMap.ARMSTAGE2);
  public static Encoder sampleEncoder = new Encoder(3, 4, false, Encoder.EncodingType.k4X);

  private static TwoStageArm m_instance;
	public static synchronized TwoStageArm getInstance() {
		if (m_instance == null){
			m_instance = new TwoStageArm();
		}
		return m_instance;
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void MoveStage2 (double speed, double x, double y) {
    armStage2.set(speed);
    System.out.println(getTestEncoderPos() + " pos = " + getTestEncoderPos()*encToDeg + " should be: " + encToDeg/calculateAngle2(x,y) + " = " + calculateAngle2(x,y));
  }

  public void MoveStage2 (double speed) {
    MoveStage2(speed,0.0,0.0);
  }

  public void MoveStage1 (double speed) {
    armStage1.set(speed);
  }

  public double calculateAngle2 (double x, double y) {
    double angle = (180/Math.PI)*Math.acos((45.0*45.0*50.0*50.0-(x*x*y*y))/(4500.0));
    return angle;
  }

  public int getTestEncoderPos() {
    return sampleEncoder.get();
}
}

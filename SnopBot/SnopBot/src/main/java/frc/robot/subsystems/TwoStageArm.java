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

  public static double encToDeg = 360.0/559.0;

  VictorSP armStage1 = new VictorSP(RobotMap.ARMSTAGE1);
  VictorSP armStage2 = new VictorSP(RobotMap.ARMSTAGE2);
  Encoder stage1Encoder = new Encoder(RobotMap.ARM1ENCODER1, RobotMap.ARM1ENCODER2);
  Encoder stage2Encoder = new Encoder(RobotMap.ARM2ENCODER1, RobotMap.ARM2ENCODER2);

  int integral1, integral2, prevError1, prevError2 = 0;
  

  private static TwoStageArm m_instance;
	public static synchronized TwoStageArm getInstance() {
		if (m_instance == null){
			m_instance = new TwoStageArm();
		}
		return m_instance;
  }
  
  private TwoStageArm () {
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void MoveStage1 (double speed) {
    armStage1.set(speed);
  }

  public void MoveStage2 (double speed) {
    armStage2.set(speed);
  }

  public void MoveToCoord (double x, double y) {
    double pk = 0.05/10;
    // Calculating target for stage 1
    int stage1Target = (int)Math.round(calculateAngle1(x,y)/encToDeg);
    int stage1Pos = getStage1EncoderPos();

    //Moving stage 1
    int stage1Error = stage1Target-stage1Pos;
    integral1 += (stage1Error*0.02);
    double derivative1 = (stage1Error - prevError1) / 0.02;
    MoveStage1(stage1Error*pk + 1*integral1);
    prevError1 = stage1Error;


    // Calculating target for stage 2
    int stage2Target;
    if (calculateAngle2(x,y)-15.0 < 0) {
      stage2Target = 0;
    } else {
      stage2Target = (int)Math.round(calculateAngle2(x,y)/encToDeg);
    }
    int stage2Pos = getStage2EncoderPos();

    //Moving stage 2
    int stage2Error = stage2Target-stage2Pos;
    integral2 += (stage2Error*0.02);
    double derivative2 = (stage2Error - prevError2) / 0.02;
    MoveStage2(stage2Error*pk + 0.05*integral2);
    prevError2 = stage2Error;

  }

  public double calculateAngle1 (double x, double y) {
    double dist = Math.sqrt(x*x+y*y);
    double angle = Math.atan(y/x)+Math.asin((50*Math.sin(calculateAngle2(x,y)))/dist);
    System.out.println("Vinkel 1 = " + angle);
    return angle;
  }

  public double calculateAngle2 (double x, double y) {
    double dist = (x*x+y*y);
    double angle = (180.0/Math.PI)*Math.acos((4525-dist)/(4500.0));
    return angle;
  }

  public int getStage1EncoderPos() {
    return stage1Encoder.get();
  }

  public int getStage2EncoderPos() {
    return stage2Encoder.get();
  }
}

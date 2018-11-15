package src.main.java.frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;

import src.main.java.frc.robot.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
/**
 *
 */
public class DriveTrain extends Subsystem {
	
	
	double limitFactor = 0.5;
	double slowFactor = 0.35;
	
	double mP1S = 1;
	double degP1S = 45;
	
	public static double diam = 0.15188; //m
	public static double circ = diam*Math.PI; //m
	public static double talonUnitToMPS = (10.0/4096.0)*circ; // 
	public static double talonUnitToM = circ/4096.0;
	public static final double DRV_POS_P_GAIN = (0.5845*1.75)/100.0;
	
	WPI_TalonSRX left1 = new WPI_TalonSRX(RobotMap.LEFTMOTOR1);
	WPI_TalonSRX left2 = new WPI_TalonSRX(RobotMap.LEFTMOTOR2);
	SpeedControllerGroup m_left = new SpeedControllerGroup(left1, left2);
	
	WPI_TalonSRX right1 = new WPI_TalonSRX(RobotMap.RIGHTMOTOR1);
	WPI_TalonSRX right2 = new WPI_TalonSRX(RobotMap.RIGHTMOTOR2);
	SpeedControllerGroup m_right = new SpeedControllerGroup(right1, right2);
	
	DifferentialDrive Drive = new DifferentialDrive(m_right, m_left);
	//DifferentialDrive Drive = new DifferentialDrive(m_left,m_right);
	
	Encoder sampleEncoder = new Encoder(1, 2); // , false, Encoder.EncodingType.k4X

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private static DriveTrain m_instance;
	public static synchronized DriveTrain getInstance() {
		if (m_instance == null){
			m_instance = new DriveTrain();
		}
		return m_instance;
	}
	
	private DriveTrain() {
		right2.setSensorPhase(true);
		left1.setSensorPhase(true);
		right2.config_kF(0, 0.39346, 0);
		left1.config_kF(0, 0.42625, 0);
		right2.config_kP(0, 0.5845*1.75, 0);
		left1.config_kP(0, 0.5845*1.75, 0);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	public void Arcade(double moveValue, double rotateValue, double maxSpeed) {
		double move = moveValue * maxSpeed;
        double turn = rotateValue * maxSpeed;
		
		Drive.arcadeDrive(move, turn, true);	
		
	}
	public void Arcade (double moveValue, double rotateValue) {
		Arcade(moveValue, rotateValue, 1);
	}
	
	
	public void setVelocity (double rmps, double lmps) {
		double targetVelR = rmps/talonUnitToMPS;
		right2.set(ControlMode.Velocity,targetVelR*-1);
		right1.set(ControlMode.Follower, right2.getDeviceID());
		double targetVelL = lmps/talonUnitToMPS;
		left1.set(ControlMode.Velocity,targetVelL);
		left2.set(ControlMode.Follower, left1.getDeviceID());
		
	}
	
	public void resetEncoders () {
		right2.setSelectedSensorPosition(0, 0, 0);
		left1.setSelectedSensorPosition(0, 0, 0);
	}
	
	public void setSafetyEnabled(boolean inp) {
		Drive.setSafetyEnabled(inp);
	}
	
	public void arcadeDrive(double power, double turn) {
		Drive.arcadeDrive(power, turn);
	}

	public int getPositionLeftNu() {
		return left1.getSelectedSensorVelocity(0);
	}

	public int getPositionRightNu() {
		return right2.getSelectedSensorVelocity(0);
	}

	public int getTestEncoderPos() {
		
    	return sampleEncoder.get();
	}
	
}
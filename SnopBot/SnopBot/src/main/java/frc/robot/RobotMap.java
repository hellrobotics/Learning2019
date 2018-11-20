package src.main.java.frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
    //PWM
    public static final int ARMSTAGE1 = 0;
    public static final int ARMSTAGE2 = 1;
    
	//public static final int PWMNUMBER = 0;
	
	//CAN
    
    //public static final int CANNUMBER = 0;
    public static final int LEFTMOTOR1 = 1;
    public static final int LEFTMOTOR2 = 2;
    public static final int RIGHTMOTOR1 = 3;
    public static final int RIGHTMOTOR2 = 4;
	
    //DIO
    
	//public static final int DIONUMBER = 0;
	
	
	
	
	

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
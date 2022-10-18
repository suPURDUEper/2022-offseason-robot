// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //Drivetrain Subsystem
    /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = .4953; // FIXED Measure and set trackwidth
    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = .4953; // FIXED Measure and set wheelbase

  //  public static final int DRIVETRAIN_PIGEON_ID = 0; // Set Pigeon ID

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 4; // FIXED Set front left module drive motor ID
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 3; // FIXED Set front left module steer motor ID
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 1; // FIXED Set front left steer encoder ID
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(202.2); // FIXED Measure and set front left steer offset
    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 5; // FIXED Set front right drive motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 6; // FIXED Set front right steer motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 2; // FIXED Set front right steer encoder ID
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(251.6); // FIXED Measure and set front right steer offset

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 2; // FIXED Set back left drive motor ID
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 1; // FIXED Set back left steer motor ID
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 0; // FIXED Set back left steer encoder ID
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(221.6); // FIXED Measure and set back left steer offset

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 7; // FIXED Set back right drive motor ID
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 8; // FIXED Set back right steer motor ID
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 3; // FIXED Set back right steer encoder ID
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(211.7); // FIXED Measure and set back right steer offset

    public static final Double BASE_SPEED_MULT = 0.75; //0.75 is standard
    public static final Double BOOST_SPEED_MULT = 1.0;
    public static final Double SLOW_SPEED_MULT = 0.15;

    public static final double M3ENCODER_RANGE = 360;

    //Intake Subsystem
    public static final int INTAKE_MOTOR = 9;
    public static final double INTAKE_SPEED = .8;
    public static final double PURGE_INTAKE_SPEED = -.7;
    public static final double STOP_INTAKE = 0.0;
    public static final int DRAWBRIDGE_MOTOR = 1;
    public static final double LOWER_INTAKE_VALUE = 4*4096; //4096 ticks per rotation X 4 rotations FORTNITE BATTLEPASS
    public static final double RAISED_INTAKE_VALUE = 0;
    public static final boolean DRAWBRIDGE_SENSORPHASE = false; //Change boolean for direction of Mag Encoder counting
    public static final double DRAWBRIDGE_ALLOWABLE_ERROR = 5;
    public static final double DRAWBRIDGE_kF = 0;
    public static final double DRAWBRIDGE_kP = 0.45;
    public static final double DRAWBRIDGE_kI = .03;
    public static final double DRAWBRIDGE_kD = 0;
    public static final double DRAWBRIDGE_MAXINTEGRAL = 4000;
    public static final double DRAWBRIDGE_INTEGRAL_ZONE = 100;
    public static final boolean DRAWBRIDGE_INVERTED = true;

    //Indexer Subsystem
    public static final int INDEXER_MOTOR = 10;
    public static final double PURGE_INDEXER_SPEED = -1;
    public static final double INDEXER_RUNSPEED = 0.7;
    public static final double INDEXER_SHOOTSPEED = 1;

    //Shooting Subsystem
    public static final int SHOOTING_MOTOR = 2;
    public static final double SHOOTER_RPM_TOLERANCE = 300;
    public static final double SHOOTER_RPM_STABLE_TIME = 300*1000;
    public static final double SHOOTER_CURRENT_LIMIT_AMPS = 40;
    public static final int PID_LOOP_INDEX = 0;
    public static final double FLYWHEEL_KF = 0.055;
    public static final double FLYWHEEL_KP = 0.08;//0.21;
    public static final double FLYWHEEL_KI = 0;
    public static final double FLYWHEEL_KD = 0;//0.7;

    //Climber Subsystem
    public static final int CLIMBER_CAN_ID = 11;
    public static final float CLIMBER_MAX_HEIGHT = 152; //7.5 times climber gear ratio (currently 20:1)
    public static final double CLIMBER_SPEED = 1.0;
    public static final double ALLOWABLE_TX_ERROR = 1.5;

}

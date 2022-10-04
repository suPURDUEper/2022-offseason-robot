
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.RobotController;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.ShuffleboardInfo;
//import frc.robot.util.LookupTable;
import java.lang.Math;
public class ShootingSubsystem extends SubsystemBase {

  private final TalonFX ShootingMotor;
  // Creates a new Intake. 
  private int targetFlywheelRpm = 0;
  NetworkTableEntry flywheelNetworkTableEntry;
  boolean isShooterEnabled = false;
  double atSpeedTimer;
 // LookupTable lookupTable;
  public ShootingSubsystem() {
    ShootingMotor = new TalonFX(Constants.SHOOTING_MOTOR);
    reinitTalonFx(ShootingMotor);
    setTalonFXPidGains(ShootingMotor);

   // lookupTable = new LookupTable();
   // lookupTable.addValue(17.1, 3200);
   // lookupTable.addValue(10.9, 3450);
   // lookupTable.addValue(4.9, 3600);
   // lookupTable.addValue(-1.33, 3975);
   // lookupTable.addValue(-4.4, 4200);
   // lookupTable.addValue(-4.75, 4350);
  }

public void setFlywheelTargetRPM(int rpm){
  targetFlywheelRpm = rpm;
  SmartDashboard.putNumber("Flywheel Target Speed", targetFlywheelRpm);
}

public void setFlywheelDistanceRPM(double ty){
  targetFlywheelRpm = (int) Math.round(3855 + -68.3 * ty + 4.64 * ty * ty + -.168 * ty * ty *ty);
}
public double getFlywheelRPM(){
  return talonFXUnitsToRpm(ShootingMotor.getSelectedSensorVelocity());
}
/*
  public void RunShooting() {
    ShootingMotor.set(ControlMode.PercentOutput, Constants.SHOOTING_SPEED);
  }
  
  public void StopShooting() {
    ShootingMotor.set(ControlMode.PercentOutput, 0);
  } 
*/
  public void enableShooter(){
    isShooterEnabled = true;
  }
  public void setShooter(){
    enableShooter();
    targetFlywheelRpm = 3500;
    SmartDashboard.putNumber("Flywheel Target Speed", targetFlywheelRpm);
  }

  public void disableShooter(){
    targetFlywheelRpm = 0;
    SmartDashboard.putNumber("Flywheel Target Speed", targetFlywheelRpm);
    isShooterEnabled = false;
  }

  @Override
  public void periodic() {
    // This method will be called one per scheduler run
    //targetFlywheelRpm = flywheelNetworkTableEntry.getNumber(0).intValue();
    if (isShooterEnabled) {
      ShootingMotor.set(ControlMode.Velocity, rpmToTalonFXUnits(SmartDashboard.getNumber("Flywheel Target Speed", targetFlywheelRpm)));
     // System.out.println("Target flywheel RPM" + targetFlywheelRpm);
    } else {
      ShootingMotor.set(ControlMode.Disabled, 0);
      }
      SmartDashboard.putNumber("Flywheel Speed", talonFXUnitsToRpm(ShootingMotor.getSelectedSensorVelocity()));
  }


        
  public static double rpmToTalonFXUnits(double rpm){
    return rpm* 2048 / 600;
  }

  public static double talonFXUnitsToRpm(double talonFXUnit){
    return (talonFXUnit / 2048) * 600;
  }

  public boolean isShooterAtSpeed() {
    if (Math.abs(getFlywheelRPM() - targetFlywheelRpm) < Constants.SHOOTER_RPM_TOLERANCE) {
      // Must be at the target RPM for a certain amount of loops in a row before saying
      // it's safe to fire. 
      return RobotController.getFPGATime() > (atSpeedTimer + Constants.SHOOTER_RPM_STABLE_TIME);
    } else {
      atSpeedTimer = RobotController.getFPGATime();
      return false;
    }
  }

  private void reinitTalonFx(TalonFX talonFX) {
    talonFX.configFactoryDefault();
    talonFX.configNeutralDeadband(0.001);
    talonFX.setNeutralMode(NeutralMode.Coast);
    talonFX.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 
    Constants.SHOOTER_CURRENT_LIMIT_AMPS, Constants.SHOOTER_CURRENT_LIMIT_AMPS + 5, 1));
    talonFX.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.PID_LOOP_INDEX, 30);
    talonFX.configNominalOutputReverse(0, 30);
    talonFX.configPeakOutputForward(1, 30);
    talonFX.configPeakOutputReverse(-1, 30);
  }

  private void setTalonFXPidGains(TalonFX talonFX) {
    talonFX.config_kF(Constants.PID_LOOP_INDEX, Constants.FLYWHEEL_KF, 30);
    talonFX.config_kP(Constants.PID_LOOP_INDEX, Constants.FLYWHEEL_KP, 30);
    talonFX.config_kI(Constants.PID_LOOP_INDEX, Constants.FLYWHEEL_KI, 30);
    talonFX.config_kD(Constants.PID_LOOP_INDEX, Constants.FLYWHEEL_KD, 30);
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new Intake. */
  private final VictorSPX IntakeMotor;  
  private final TalonSRX DrawbridgeMotor;
  ShuffleboardTab tab = Shuffleboard.getTab("Intake");
  ShuffleboardContainer container;

  public IntakeSubsystem(){
    
    IntakeMotor = new VictorSPX(Constants.INTAKE_MOTOR);

    DrawbridgeMotor = new TalonSRX(Constants.DRAWBRIDGE_MOTOR);
    DrawbridgeMotor.configFactoryDefault();
    DrawbridgeMotor.setInverted(Constants.DRAWBRIDGE_INVERTED);
    DrawbridgeMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    DrawbridgeMotor.setSensorPhase(Constants.DRAWBRIDGE_SENSORPHASE);
    DrawbridgeMotor.configAllowableClosedloopError(0, Constants.DRAWBRIDGE_ALLOWABLE_ERROR, 0);
    DrawbridgeMotor.config_kF(0, Constants.DRAWBRIDGE_kF);
    DrawbridgeMotor.config_kP(0, Constants.DRAWBRIDGE_kP);
    DrawbridgeMotor.config_kI(0, Constants.DRAWBRIDGE_kI);
    DrawbridgeMotor.config_kD(0, Constants.DRAWBRIDGE_kD);
    DrawbridgeMotor.config_IntegralZone(0, Constants.DRAWBRIDGE_INTEGRAL_ZONE);
    DrawbridgeMotor.configMaxIntegralAccumulator(0, Constants.DRAWBRIDGE_MAXINTEGRAL);
    DrawbridgeMotor.configClosedLoopPeakOutput(0, 1);
    DrawbridgeMotor.setSelectedSensorPosition(0);
    DrawbridgeMotor.set(ControlMode.Position,0);
  }
  public void setPower(double power){
    if(Math.abs(power) < .05){
      power = 0;
    }
  }
public void RunIntake() {
  if((RobotContainer.FrontSensor.get() == false) && (RobotContainer.BackSensor.get() == false)){
    IntakeMotor.set(ControlMode.PercentOutput, 0);
    DrawbridgeMotor.set(ControlMode.Position, Constants.RAISED_INTAKE_VALUE);
  }else{
  IntakeMotor.set(ControlMode.PercentOutput, Constants.INTAKE_SPEED);
  DrawbridgeMotor.set(ControlMode.Position, Constants.LOWER_INTAKE_VALUE);
  }
}

public void PurgeIntake() {
  IntakeMotor.set(ControlMode.PercentOutput, Constants.PURGE_INTAKE_SPEED);
}

public void StopIntake() {
  IntakeMotor.set(ControlMode.PercentOutput, Constants.STOP_INTAKE);
  DrawbridgeMotor.set(ControlMode.Position, Constants.RAISED_INTAKE_VALUE);
}

/*
public void LowerIntake() {
  DrawbridgeMotor.set(ControlMode.Position, Constants.LOWER_INTAKE_VALUE);
}

public void RaiseIntake() {
  DrawbridgeMotor.set(ControlMode.Position, Constants.RAISED_INTAKE_VALUE);
  
} */
  @Override
  public void periodic() {
    // This method will be called one per scheduler run
 //  System.out.print("     Sensor position: " + DrawbridgeMotor.getSelectedSensorPosition());
 // System.out.print("       Error: " + DrawbridgeMotor.getClosedLoopError());
  // System.out.println("     Set point " + DrawbridgeMotor.getClosedLoopTarget());
  }
}

package frc.robot.subsystems;
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// This is a comment!


import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch.Type;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private final CANSparkMax ClimbMotor;
  private final SparkMaxLimitSwitch ClimbRevLimit;
  private final RelativeEncoder ClimbEncoder;
  /**
   * Creates a new climber object.
   */
  public Climber() {
    ClimbMotor = new CANSparkMax(Constants.CLIMBER_CAN_ID, MotorType.kBrushless);
    ClimbMotor.setInverted(false);
    ClimbRevLimit = ClimbMotor.getReverseLimitSwitch(Type.kNormallyOpen);
    ClimbEncoder = ClimbMotor.getEncoder();
    ConfigureClimbMotor(ClimbMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Reset position if limit switch is triggered
    if (ClimbRevLimit.isPressed() == true) {
      ClimbEncoder.setPosition(0.0);
    }
  }


  public void ClimberUp() {
    ClimbMotor.set(Constants.CLIMBER_SPEED);
    System.out.println("climber Encoder: " + ClimbEncoder.getPosition());
  }

  public void ClimberDown() {
    ClimbMotor.set(Constants.CLIMBER_SPEED * -1);
    System.out.println("climber Encoder: " + ClimbEncoder.getPosition());
  }

public void setPower(double power){
  if(Math.abs(power) < .05){
    power = 0;
  }
  ClimbMotor.set(power);
  System.out.println("climber Encoder: " + ClimbEncoder.getPosition());
}

  public boolean isClimberUp(){
    System.out.println("climber Encoder: " + ClimbEncoder.getPosition());
    if(ClimbEncoder.getPosition() >= Constants.CLIMBER_MAX_HEIGHT){
      return true;
    }
    return false;
  }

  public boolean isClimberDown(){
    System.out.println("climber Encoder: " + ClimbEncoder.getPosition());
    if(ClimbRevLimit.isPressed() == true){
      return true;
    }
    return false;
  }

  public void ConfigureClimbMotor(CANSparkMax ClimbMotor) {
    ClimbMotor.restoreFactoryDefaults();
    ClimbMotor.setSmartCurrentLimit(80);
    ClimbMotor.enableSoftLimit(SoftLimitDirection.kForward, true);
    ClimbMotor.setSoftLimit(SoftLimitDirection.kForward, Constants.CLIMBER_MAX_HEIGHT);
    ClimbMotor.setIdleMode(IdleMode.kBrake);
  }
}
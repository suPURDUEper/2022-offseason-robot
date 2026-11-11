// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IndexerSubsystem extends SubsystemBase {
  /** Creates a new Intake. */
  private final VictorSPX IndexerMotor;
  ShuffleboardTab tab = Shuffleboard.getTab("Indexer");
  ShuffleboardContainer container;

  public IndexerSubsystem(){
    
    IndexerMotor = new VictorSPX(Constants.INDEXER_MOTOR); 
    IndexerMotor.setNeutralMode(NeutralMode.Brake);   
  }

public void RunIndexer() {
  
  if(RobotContainer.BackSensor.get() == false){
    IndexerMotor.set(ControlMode.PercentOutput, 0);
  }
  else{
    if(RobotContainer.FrontSensor.get() == true){
      IndexerMotor.set(ControlMode.PercentOutput, 0);
    }else{
      IndexerMotor.set(ControlMode.PercentOutput, Constants.INDEXER_RUNSPEED);
    }
  }
}

public void PurgeIndexer() {
  IndexerMotor.set(ControlMode.PercentOutput, Constants.PURGE_INDEXER_SPEED);
}
public void StopIndexer() {
  IndexerMotor.set(ControlMode.PercentOutput, 0);
}

public void ShootIndexer(){
  IndexerMotor.set(ControlMode.PercentOutput, Constants.INDEXER_SHOOTSPEED);
}

  @Override
  public void periodic() {
    // This method will be called one per scheduler run
  //  System.out.print("     Sensor 1" + FrontSensor.get());
  //  System.out.print("     Sensor 3" + BackSensor.get());
  //  System.out.println("     Set point " + DrawbridgeMotor.getClosedLoopTarget());
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
public class VisionSubsytem extends SubsystemBase {
  private final NetworkTable mLimeLightTable;
  private double tv, tx, ty;
  private boolean mIsTargetValid;
  private final NetworkTableEntry mLedEntry, mTv;
  // Creates a new Vision 
  public VisionSubsytem() {
    mTv = Shuffleboard.getTab("Limelight Aim").add("TV", 0.0).getEntry();
    mLimeLightTable = NetworkTableInstance.getDefault().getTable("limelight");
    mLedEntry = mLimeLightTable.getEntry("ledmode");
  }

  

  @Override
  public void periodic() {
    // This method will be called one per scheduler run
    tv = mLimeLightTable.getEntry("tv").getDouble(0.0);
    tx = mLimeLightTable.getEntry("tx").getDouble(0.0);
    ty = mLimeLightTable.getEntry("ty").getDouble(0.0);
    mIsTargetValid = IsTargetValid();
    mTv.forceSetDouble(tv);
    }

    public double getTx(){
      return tx;
    }

    public double getTy(){
      return ty;
    }

    public boolean IsTargetValid(){
      return (tv== 1.0);
    }

    public void setL1LedMode(int mode){
      mLedEntry.forceSetNumber(mode);
    }

}
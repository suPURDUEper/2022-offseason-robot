
package frc.robot.commands;

import java.util.function.Supplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
//import frc.robot.ShuffleboardInfo;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.VisionSubsytem;

public class DriveWithLimelight extends CommandBase {
  private final DrivetrainSubsystem mDriveTrainSubsystem;
  private final DoubleSupplier m_translationXSupplier;
  private final DoubleSupplier m_translationYSupplier;
  private final DoubleSupplier m_rotationSupplier;
  private final VisionSubsytem mVision;

  // Constants
  private double mSteeringKp = 0.015;
  private double minCommand = 0.3;
  private double turnCommand;
  private Supplier<Double> throttle;
  // Network Table Entries
  NetworkTableEntry mKpSteer, mMinTa, mDrive_Kp;

  // Creates a new LimelightAim.

  public DriveWithLimelight(DrivetrainSubsystem dt, VisionSubsytem v) {
    this(dt, v, () -> 0.0, () -> 0.0, () -> 0.0);
  }

  public DriveWithLimelight(DrivetrainSubsystem dt, VisionSubsytem v, DoubleSupplier xthrottle, DoubleSupplier ythrottle, DoubleSupplier rotthrottle) {
    // Use addRequirements() here to declare subsystem dependencies.
    mDriveTrainSubsystem = dt;
    mVision = v;
    this.m_translationXSupplier = xthrottle;
    this.m_translationYSupplier = ythrottle;
    this.m_rotationSupplier = rotthrottle;
    addRequirements(dt);
    //this.throttle = throttle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (mVision.IsTargetValid()) {
      double mTx = -mVision.getTx();
      if (Math.abs(mTx) > 1) {
        turnCommand = mSteeringKp * mTx + Math.copySign(minCommand, mTx);
      } else {
        turnCommand = 0;
      }
      mDriveTrainSubsystem.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        m_translationXSupplier.getAsDouble(),
                        m_translationYSupplier.getAsDouble(),
                        turnCommand,
                        mDriveTrainSubsystem.getGyroscopeRotation()
                )
        );
    }
    else {
        mDriveTrainSubsystem.drive(
        ChassisSpeeds.fromFieldRelativeSpeeds(
                m_translationXSupplier.getAsDouble(),
                m_translationYSupplier.getAsDouble(),
                m_rotationSupplier.getAsDouble(),
                mDriveTrainSubsystem.getGyroscopeRotation()
             )
        );
    }      
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
} 
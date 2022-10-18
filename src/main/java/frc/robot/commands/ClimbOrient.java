
package frc.robot.commands;

import java.util.function.DoubleSupplier;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
//import frc.robot.RobotContainer;
//import frc.robot.ShuffleboardInfo;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.VisionSubsytem;

public class ClimbOrient extends CommandBase {
  private final DrivetrainSubsystem mDriveTrainSubsystem;
  private final DoubleSupplier m_translationXSupplier;
  private final DoubleSupplier m_translationYSupplier;
  private final DoubleSupplier m_rotationSupplier;
  private final VisionSubsytem mVision;

  // Constants
  private double mSteeringKp = 0.09; //0.015
  private double minCommand = 0.25; //0.3
  private double turnCommand;
    public ClimbOrient(DrivetrainSubsystem dt, VisionSubsytem v) {
    this(dt, v, () -> 0.0, () -> 0.0, () -> 0.0);
  }

  public ClimbOrient(DrivetrainSubsystem dt, VisionSubsytem v, DoubleSupplier xthrottle, DoubleSupplier ythrottle, DoubleSupplier rotthrottle) {
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
      double currentheading = mDriveTrainSubsystem.getAngleDegrees();
      if(currentheading < 0){
       currentheading = currentheading + 360;
      }
       double error = currentheading -90;
      if (Math.abs(error) > Constants.ALLOWABLE_TX_ERROR) {
        turnCommand = mSteeringKp * error + Math.copySign(minCommand, error);
      } else {
        turnCommand = 0;
      }
      mDriveTrainSubsystem.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
                        -m_translationXSupplier.getAsDouble()*Constants.SLOW_SPEED_MULT,
                        -m_translationYSupplier.getAsDouble()*Constants.SLOW_SPEED_MULT,
                        turnCommand,
                        mDriveTrainSubsystem.getGyroscopeRotation()
                )
        );

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
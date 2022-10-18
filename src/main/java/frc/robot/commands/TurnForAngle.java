// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TurnForAngle extends CommandBase {
  public final DrivetrainSubsystem drivetrain;
  double desiredAngle = 0;
  double currentheading;
  double error;
  private double mSteeringKp = 0.025;
  private double minCommand = 0.25;
  double turnCommand = 0;
  boolean done = false;
  /** Creates a new FreeClimb. */
  public TurnForAngle(DrivetrainSubsystem mdrivetrain, double mangle) {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain = mdrivetrain;
    desiredAngle = mangle;
    addRequirements(mdrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     currentheading = drivetrain.getAngleDegrees();
     if(currentheading < 0){
       currentheading = currentheading + 360;
     }
     error = (currentheading - desiredAngle);
     System.out.print("current Heading: " + currentheading);
     System.out.print("Desired Angle: " + desiredAngle);
     System.out.println("Error: " + error);
        if (Math.abs(error) > 1) {
          turnCommand = mSteeringKp * error + Math.copySign(minCommand, error);
        } else {
          turnCommand = 0;
          done = true;
        }
        drivetrain.drive(new ChassisSpeeds(0,0,turnCommand));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
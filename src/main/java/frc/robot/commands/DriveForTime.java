// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveForTime extends CommandBase {
  public final DrivetrainSubsystem drivetrain;
  double endtime;
  double speed = .6;
  //Timer timer;
  /** Creates a new FreeClimb. */
  public DriveForTime(DrivetrainSubsystem mdrivetrain, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    drivetrain = mdrivetrain;
    addRequirements(mdrivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //timer.reset();
    System.out.println("Intitialized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
drivetrain.drive(new ChassisSpeeds(speed,0,0));
System.out.println("execute");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      drivetrain.drive(new ChassisSpeeds(0,0,0));
      System.out.println("interrupted");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false; //(timer.get() > endtime);
  }
}
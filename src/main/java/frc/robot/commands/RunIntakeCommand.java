// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
public class RunIntakeCommand extends CommandBase {
  public final IntakeSubsystem intake;
  public final IndexerSubsystem indexer;
  /** Creates a new FreeClimb. */
  public RunIntakeCommand(IntakeSubsystem mintake, IndexerSubsystem mindexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    intake = mintake;
    indexer = mindexer;
    addRequirements(mintake, mindexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    intake.RunIntake();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.setPower (RobotContainer.operatorJoyStick.getRightY()*-1);
    indexer.RunIndexer();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      intake.StopIntake();
      indexer.StopIndexer();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
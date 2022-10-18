
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndexerSubsystem;


public class Shoot extends CommandBase {
  private final IndexerSubsystem indexer;
  // Creates a new ShootBall. 
  public Shoot(IndexerSubsystem mIndexer) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.indexer = mIndexer;
    addRequirements(mIndexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }
  
  @Override
  public void execute() {
      indexer.ShootIndexer();
    }
    
  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    indexer.StopIndexer();
    }
}
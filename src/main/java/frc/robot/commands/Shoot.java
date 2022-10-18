
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShootingSubsystem;
import frc.robot.subsystems.VisionSubsytem;
import frc.robot.subsystems.IndexerSubsystem;


public class Shoot extends CommandBase {
  private final ShootingSubsystem shooter;
  private final IndexerSubsystem indexer;
  private final VisionSubsytem vision;
  // Creates a new ShootBall. 
  public Shoot(ShootingSubsystem mShooter, IndexerSubsystem mIndexer, VisionSubsytem mVision) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooter = mShooter;
    this.indexer = mIndexer;
    this.vision = mVision;
    addRequirements(mShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }
  
  @Override
  public void execute() {
    if (vision.IsTargetValid() && shooter.isShooterAtSpeed() == true) {
      indexer.ShootIndexer();
    } else {
        indexer.StopIndexer();
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    }
}

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShootingSubsystem;
import frc.robot.subsystems.VisionSubsytem;


public class SetFlywheelToLimelightShot extends CommandBase {
  private final ShootingSubsystem shooter;
  private final VisionSubsytem vision;
  // Creates a new ShootBall. 
  public SetFlywheelToLimelightShot(ShootingSubsystem mShooter, VisionSubsytem vision) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooter = mShooter;
    this.vision = vision;
    addRequirements(mShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.enableShooter();
    shooter.setFlywheelDistanceRPM(vision.getTy());
  }
  
  @Override
  public void execute() {
    if (vision.IsTargetValid()) {
      shooter.setFlywheelDistanceRPM(vision.getTy());
    
    } else {
      shooter.setFlywheelTargetRPM(3525);

    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.disableShooter();
  }
}
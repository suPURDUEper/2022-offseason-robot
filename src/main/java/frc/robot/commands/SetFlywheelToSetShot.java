
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShootingSubsystem;
//import frc.robot.subsystems.VisionSubsytem;


public class SetFlywheelToSetShot extends CommandBase {
  private final ShootingSubsystem shooter;
  // Creates a new ShootBall. 
  public SetFlywheelToSetShot(ShootingSubsystem mShooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    shooter = mShooter;
    addRequirements(mShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.enableShooter();
    shooter.setFlywheelTargetRPM(4225);
  }
  
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.disableShooter();
  }
}
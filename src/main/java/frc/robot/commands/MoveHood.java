package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hood;

public class MoveHood extends CommandBase {
  /** Creates a new MoveHood. */
  private Hood hood;
  private double targetPos;

  public MoveHood(Hood hood, double target) {
    this.hood = hood;
    targetPos = target;
    addRequirements(hood);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // targetPos = SmartDashboard.getNumber("Target Hood", 0);
    hood.setPosition(targetPos);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hood.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return hood.atPosition(1);
  }
}

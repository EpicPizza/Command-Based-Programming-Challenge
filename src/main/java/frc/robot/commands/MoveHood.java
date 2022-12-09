package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Hood;

public class MoveHood extends CommandBase {
  /** Creates a new MoveHood. */
  private Hood hood;
  private final XboxController xboxController;

  public MoveHood(Hood hood, XboxController XboxController) {
    this.hood = hood;
    xboxController = XboxController;
    addRequirements(hood);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x = -1 * xboxController.getRawAxis(Constants.LEFT_Y2);
    if ((hood.getPosition() >= -1 && x <= 0) || (hood.getPosition() <= 120 && x >= 0)) {
      hood.setOutput(x * Constants.hoodSpeed, true);
    }
    else {
      hood.setOutput(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hood.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

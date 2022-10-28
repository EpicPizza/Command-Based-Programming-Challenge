// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Arm;

public class ArmPosition extends CommandBase {
  private final Arm arm;
  private double position;
  private boolean finish = false;
  private boolean direction;
  /** Creates a new ArmPosition. */
  public ArmPosition(Arm a, int i) {
    arm = a;
    position = i;
    if(arm.getPosition() < i) {
      direction = true;
    } else {
      direction = false;
    }
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(direction && arm.getPosition() <= position) {
      arm.set(Constants.ARM_SPEED);
    } if(!direction && arm.getPosition() >= position) {
      arm.set(-Constants.ARM_SPEED);
    } else {
      arm.stop();
      finish = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    arm.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}

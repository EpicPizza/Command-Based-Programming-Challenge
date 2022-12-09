// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class DriveDistance extends CommandBase {
  private final DriveTrain driveTrain;
  private boolean finish = false;
  double rateOfChange;
  double error;
  double speed;
  private double previousError;
  private double previousSpeed;
  /** Creates a new DriveDistance. */
  public DriveDistance(DriveTrain dt) {
    driveTrain = dt;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    previousError = 0;
    previousSpeed = 0;
    driveTrain.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Position: " + driveTrain.getRightPosition() * 11 / 120);
    if(driveTrain.getRightPosition() * 11 / 120 < Constants.UNITS_PER_ROTATION * 6) {
      error = (20 - driveTrain.getRightPosition() * 11 / 120 / Constants.UNITS_PER_ROTATION);
      rateOfChange = (previousError - error) / 6;
      rateOfChange = Math.abs(rateOfChange);
      speed = Constants.KP * error + rateOfChange * Constants.KD;; //2
      if (speed - previousSpeed > 0.01) {
        speed = previousSpeed + 0.01;
      }
      if (speed - previousSpeed < -0.01) {
        speed = previousSpeed - 0.01;
      }
      if(speed < -0.5) {
        speed = -0.5;
      }
      if(speed > 0.5) {
        speed = 0.5;
      }
      driveTrain.set(speed);
      previousError = error;
      previousSpeed = speed;
      System.out.println("Error: " + error + "\tPosition: " + driveTrain.getRightPosition() * 11 / 120 + "\tSpeed: " + speed);
    } else {
      driveTrain.stop();
      finish = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}

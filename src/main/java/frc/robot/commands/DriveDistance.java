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
  private double reverse;
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
    driveTrain.reset();
    reverse = Constants.REVERSED;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(driveTrain.getLeftPosition() < Constants.UNITS_PER_ROTATION * 6) {
      double error = ((reverse * 6) - (driveTrain.getLeftPosition() / Constants.UNITS_PER_ROTATION));
      double rateOfChange = ((error - previousError) / 0.02) * Constants.DERI_CONSTANT; //0.2
      if(reverse == 1) {
        rateOfChange = Math.abs(rateOfChange);
      } else if(reverse == -1) {
        rateOfChange = -Math.abs(rateOfChange);
      }
      SmartDashboard.putNumber("RateOfChange", rateOfChange);
      double speed = (Constants.PROP_CONSTANT * error) + rateOfChange; //2
      if(reverse == -1) {
        if(speed < -0.3) {
          speed = -0.3;
        }
      } else {
        if(speed > 0.3) {
          speed = 0.3;
        }
      }
      if(((reverse * speed) - (reverse * previousSpeed)) > 0.01) {
        System.out.println("Acceleration Limited");
        speed = previousSpeed + (reverse * 0.01);
      } 
      driveTrain.set(speed);
      previousError = error;
      previousSpeed = speed;
      SmartDashboard.putNumber("Speed", speed);
      SmartDashboard.putNumber("Error", error);
    } else {
      driveTrain.stop();
      finish = true;
    }
    SmartDashboard.putNumber("Percentage", ((driveTrain.getLeftPosition() / Constants.UNITS_PER_ROTATION) / 6));
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

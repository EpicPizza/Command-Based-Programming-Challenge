// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private final TalonSRX rightMaster = new TalonSRX(Constants.RIGHT_MASTER_PORT);
  private final TalonSRX rightFollower = new TalonSRX(Constants.RIGHT_FOLLOWER_PORT);
  private final TalonSRX leftMaster = new TalonSRX(Constants.LEFT_MASTER_PORT);
  private final TalonSRX leftFollower = new TalonSRX(Constants.LEFT_FOLLOWER_PORT);

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    rightMaster.set(ControlMode.PercentOutput, 0);
    rightFollower.set(ControlMode.PercentOutput, 0);
    leftMaster.set(ControlMode.PercentOutput, 0);
    leftFollower.set(ControlMode.PercentOutput, 0);

    leftMaster.setInverted(true);
    leftFollower.setInverted(true);
  }

  public void setLeft(double speed) {
    leftMaster.set(ControlMode.PercentOutput, speed);
    leftFollower.set(ControlMode.PercentOutput, speed);
  }

  public void setRight(double speed) {
    rightMaster.set(ControlMode.PercentOutput, speed);
    rightFollower.set(ControlMode.PercentOutput, speed);
  }

  public void stop() {
    rightMaster.set(ControlMode.PercentOutput, 0);
    rightFollower.set(ControlMode.PercentOutput, 0);
    leftMaster.set(ControlMode.PercentOutput, 0);
    leftFollower.set(ControlMode.PercentOutput, 0);
  }

  public double getLeftPosition() {
    return leftMaster.getSelectedSensorPosition();
  }

  public double getRightPosition() {
    return rightMaster.getSelectedSensorPosition();
  }

  public void reset() {
    rightMaster.setSelectedSensorPosition(0);
    rightFollower.setSelectedSensorPosition(0);
    leftMaster.setSelectedSensorPosition(0);
    leftFollower.setSelectedSensorPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

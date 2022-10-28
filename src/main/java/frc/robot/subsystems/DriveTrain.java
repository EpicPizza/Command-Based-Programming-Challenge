// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase { //change to WPI_RX
  private final WPI_TalonFX rightMaster = new WPI_TalonFX(Constants.RIGHT_MASTER_PORT);
  private final WPI_TalonFX rightFollower = new WPI_TalonFX(Constants.RIGHT_FOLLOWER_PORT);
  private final WPI_TalonFX leftMaster = new WPI_TalonFX(Constants.LEFT_MASTER_PORT);
  private final WPI_TalonFX leftFollower = new WPI_TalonFX(Constants.LEFT_FOLLOWER_PORT);

  private final MotorControllerGroup rightMotor = new MotorControllerGroup(rightMaster, rightFollower);
  private final MotorControllerGroup leftMotor = new MotorControllerGroup(leftMaster, leftFollower);
  private final DifferentialDrive drivetrain = new DifferentialDrive(leftMotor, rightMotor);

  /** Creates a new DriveTrain. */
  public DriveTrain() { //differiental drive
    rightMaster.configFactoryDefault();
    rightFollower.configFactoryDefault();
    leftMaster.configFactoryDefault();
    leftFollower.configFactoryDefault();

    leftMotor.setInverted(true);

    drivetrain.stopMotor();
  }

  public void set(double speed) {
    drivetrain.tankDrive(speed, speed);
  }

  public void arcadeDrive(double ySpeed, double xSpeed) {
    drivetrain.arcadeDrive(ySpeed, xSpeed);    
  }

  public void stop() {
    drivetrain.stopMotor();

    //set to stopMotor
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

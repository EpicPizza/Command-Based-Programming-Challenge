// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  private final CANSparkMax arm = new CANSparkMax(Constants.ARM_PORT, MotorType.kBrushless);
  RelativeEncoder encoder = arm.getEncoder();
  public Arm() {
    arm.restoreFactoryDefaults();
  }

  public void reset() {
    encoder.setPosition(0);
  }

  public double getPosition() {
    return encoder.getPosition();
  }

  public void set(double speed) {
    if(!(encoder.getPosition() < Constants.ARM_LOWER_LIMIT) && !(encoder.getPosition() > Constants.ARM_UPPER_LIMIT)) {
      arm.set(speed);
    } else {
      arm.stopMotor();
    }
  }

  public void stop() {
    arm.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if((encoder.getPosition() < Constants.ARM_LOWER_LIMIT) && (encoder.getPosition() > Constants.ARM_UPPER_LIMIT)) {
      arm.stopMotor();
    }
  }
}

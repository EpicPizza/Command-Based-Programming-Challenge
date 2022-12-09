// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  /** Creates a new Arm. */
  private final CANSparkMax arm = new CANSparkMax(Constants.ARM_PORT, MotorType.kBrushless);
  RelativeEncoder encoder = arm.getEncoder();
  public Arm() {
    arm.restoreFactoryDefaults(); //set direction
    arm.setInverted(true);
    arm.enableSoftLimit(SoftLimitDirection.kForward, true);
    arm.enableSoftLimit(SoftLimitDirection.kReverse, true);
    arm.setSoftLimit(SoftLimitDirection.kForward, 13);
    arm.setSoftLimit(SoftLimitDirection.kReverse, 0);
  }

  public void reset() {
    encoder.setPosition(0);
  }

  public double getPosition() {
    return encoder.getPosition();
  }

  public void set(double speed) { // use soft limits
    if((!(encoder.getPosition() < Constants.ARM_LOWER_LIMIT) && !(encoder.getPosition() > Constants.ARM_UPPER_LIMIT)) || (encoder.getPosition() < Constants.ARM_LOWER_LIMIT && speed > 0) || (encoder.getPosition() > Constants.ARM_LOWER_LIMIT && speed < 0)) {
      arm.set(speed);
    } else {
      arm.stopMotor();
    }
  }

  public void stop() {
    arm.stopMotor();
  }

  public double getVoltage() {
    return arm.getAppliedOutput();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(((encoder.getPosition() < Constants.ARM_LOWER_LIMIT) && (encoder.getPosition() > Constants.ARM_UPPER_LIMIT))  || !(encoder.getPosition() < Constants.ARM_LOWER_LIMIT && arm.get() > 0) || !(encoder.getPosition() > Constants.ARM_LOWER_LIMIT && arm.get() < 0)) {
      arm.stopMotor();
    }
  }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
  private final CANSparkMax indexer = new CANSparkMax(Constants.INDEXER_PORT, MotorType.kBrushless);
  /** Creates a new Indexer. */
  public Indexer() {
    indexer.restoreFactoryDefaults(); //set direction
    indexer.setInverted(false);
  }

  public void set(double speed) {
    indexer.set(speed);
  }

  public void stop() {
    indexer.set(0);
    // indexer.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private final CANSparkMax intake = new CANSparkMax(Constants.INTAKE_PORT, MotorType.kBrushless);
  /** Creates a new Shooter. */
  public Intake() {
    intake.restoreFactoryDefaults(); //set direction
    intake.setInverted(false);
  }

  public void set(double speed) {
    intake.set(speed);
  }

  public void stop() {
    intake.set(0);
    // intake.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

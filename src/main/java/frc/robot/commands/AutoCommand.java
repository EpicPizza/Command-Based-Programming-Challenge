// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoCommand extends SequentialCommandGroup {
  /** Creates a new AutoCommand. */
  private final DriveDistance driveDistance;
  public AutoCommand(DriveTrain driveTrain, Shooter shooter, Arm arm, Intake intake) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    driveDistance = new DriveDistance(driveTrain);
    driveDistance.addRequirements(driveTrain);
    addCommands(driveDistance, new ShooterSpeed(shooter, 0.4), new WaitCommand(1), new ShooterSpeed(shooter, 0), new ArmPosition(arm, 9), new IntakeTimed(intake, 3), new ArmPosition(arm, 0));
  }
}

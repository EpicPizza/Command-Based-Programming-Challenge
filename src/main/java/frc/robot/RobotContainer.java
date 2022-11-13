// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.ArmPosition;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.DriveJoystick;
import frc.robot.commands.IndexerTimed;
import frc.robot.commands.IntakeTimed;
import frc.robot.commands.ShooterSpeed;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain driveTrain;
  private final Arm arm;
  private final Indexer indexer;
  private final Intake intake;
  private final DriveDistance driveDistance;
  private final DriveJoystick driveJoystick;
  private final XboxController driverJoystick;

  private Climber climber;
  private Hood hood;
  private Shooter shooter;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driveTrain = new DriveTrain();
    climber = new Climber();
    hood = new Hood();
    shooter = new Shooter();

    driveDistance = new DriveDistance(driveTrain);
    driveDistance.addRequirements(driveTrain);

    driverJoystick = new XboxController(Constants.JOYSTICK_NUMBER);

    driveJoystick = new DriveJoystick(driveTrain, driverJoystick);
    driveJoystick.addRequirements(driveTrain);
    driveTrain.setDefaultCommand(driveJoystick);

    arm = new Arm();

    
    
    indexer = new Indexer();

    intake = new Intake();
    climber.setDefaultCommand(new RunCommand(() -> climber.rotateArms(driverJoystick.getRawAxis(Constants.kLeftY), true), climber));
    

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton indexButton = new JoystickButton(driverJoystick, XboxController.Button.kA.value);
    indexButton.whenPressed(new IndexerTimed(indexer, 3));


    JoystickButton intakeButton = new JoystickButton(driverJoystick, XboxController.Button.kB.value);
    intakeButton.whenPressed(new IntakeTimed(intake, 3));

    JoystickButton armDown = new JoystickButton(driverJoystick, XboxController.Button.kX.value);
    armDown.whenPressed(new ArmPosition(arm, -9));

    JoystickButton armUp = new JoystickButton(driverJoystick, XboxController.Button.kY.value);
    armUp.whenPressed(new ArmPosition(arm, 0));

    JoystickButton shooterOn = new JoystickButton(driverJoystick, XboxController.Button.kLeftBumper.value);
    shooterOn.whenPressed(new ShooterSpeed(shooter, 0.4));

    JoystickButton shooterOff = new JoystickButton(driverJoystick, XboxController.Button.kLeftBumper.value);
    shooterOff.whenPressed(new ShooterSpeed(shooter, 0));

    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return driveDistance;
  }
}

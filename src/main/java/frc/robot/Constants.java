// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int LEFT_MASTER_PORT = 3;
    public static final int LEFT_FOLLOWER_PORT = 4;
    public static final int RIGHT_MASTER_PORT = 1;
    public static final int RIGHT_FOLLOWER_PORT = 2;
    public static final int kShooterMotorPortLeft = 20;
    public static final int kShooterMotorPortRight = 21;
    public static final int kHoodMotorPort = 22;
    public static final int kMotorLeftPort = 5;
    public static final int kMotorRightPort = 14;
    public static final boolean kClimberLeftInvert = false;
    public static final boolean kClimberRightInvert = false;
    public static final int kDriverController1Port = 0;
    public static final int kDriverController2Port = 1;
    public static final int kLeftX = 0;
    public static final int kLeftY = 1;
    public static final int kLeftTrigger = 2;
    public static final int kRightTrigger = 3;
    public static final int kRightX = 4;
    public static final int kRightY = 5;
    public static final int kA = 1;
    public static final int kB = 2;
    public static final int kX = 3;
    public static final int kY = 4;
    public static final int kLeftBumper = 5;
    public static final int kRightBumper = 6;

    public static final double kDeadZone = 0.1;

    public static final boolean kShooterInvert = false;
    public static final boolean kHoodInvert = false;
    public static final double kDistancePerPulse = 0.1016 * Math.PI / 2048;


    public static final int DERI_CONSTANT = 3;
    public static final double PROP_CONSTANT = 0.3;
    public static final double UNITS_PER_ROTATION = 2048;
    public static final double REVERSED = 1;

    public static final int JOYSTICK_PORT = 0;

    public static final int INTAKE_PORT = 8;
    public static final double INTAKE_SPEED = 0.4;

    public static final int INDEXER_PORT = 25;
    public static final double INDEXER_SPEED = 0.3;

    public static final int ARM_PORT = 23;
    public static final double ARM_UPPER_LIMIT = 9;
    public static final double ARM_LOWER_LIMIT = 0;
    public static final double ARM_SPEED = 0.1;

    public static final int JOYSTICK_NUMBER = 0;
    public static final int XBOX_LEFT_X_AXIS = 2;
    public static final double ARCADE_SPEED = 0.5;
    public static final int XBOX_LEFT_Y_AXIS = 4;
}

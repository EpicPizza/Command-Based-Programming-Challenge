// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Indexer;

public class IndexerTimed extends CommandBase {
  private final Indexer indexer;
  private final Timer timer = new Timer();
  private boolean finish = false;
  private final double time;
  /** Creates a new IndexerTimed. */
  public IndexerTimed(Indexer index, double t) {
    indexer = index;
    time = t;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(timer.get() < time) {
      indexer.set(Constants.INDEXER_SPEED);
    } else {
      finish = true;
      indexer.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    indexer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finish;
  }
}

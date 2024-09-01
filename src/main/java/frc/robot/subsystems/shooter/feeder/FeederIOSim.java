package frc.robot.subsystems.shooter.feeder;

public class FeederIOSim implements FeederIO {
  private double appliedVolts = 0.0;

  public FeederIOSim() {}

  @Override
  public void updateInputs(FeederIOInputs inputs) {
    inputs.appliedVolts = appliedVolts;
  }

  @Override
  public void runPercent(double percent) {
    appliedVolts = percent * 12;
  }

  @Override
  public void stop() {
    appliedVolts = 0.0;
  }
}

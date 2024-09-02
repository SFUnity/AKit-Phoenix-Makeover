package frc.robot.subsystems.drive;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import java.util.function.BooleanSupplier;
import org.littletonrobotics.junction.networktables.LoggedDashboardNumber;

/** All Constants Measured in Meters and Radians (m/s, m/s^2, rad/s, rad/s^2) */
public final class DriveConstants {
  public static final double MAX_LINEAR_VELOCITY = Units.feetToMeters(14.5); // 6328 uses 15 ft/s
  public static final double MAX_LINEAR_ACCELERATION =
      Units.feetToMeters(75.0); // This is what 6328
  public static final double TRACK_WIDTH_X = Units.inchesToMeters(22.75); // Check when copying
  public static final double TRACK_WIDTH_Y = Units.inchesToMeters(22.75);
  public static final double DRIVE_BASE_RADIUS =
      Math.hypot(TRACK_WIDTH_X / 2.0, TRACK_WIDTH_Y / 2.0);
  public static final double MAX_ANGULAR_VELOCITY =
      MAX_LINEAR_VELOCITY / DRIVE_BASE_RADIUS; // 6328 uses 12 rad/s (we're at ~10)
  public static final double MAX_ANGULAR_ACCELERATION = MAX_LINEAR_ACCELERATION / DRIVE_BASE_RADIUS;

  /** Returns an array of module translations. */
  public static final Translation2d[] moduleTranslations =
      new Translation2d[] {
        new Translation2d(TRACK_WIDTH_X / 2.0, TRACK_WIDTH_Y / 2.0),
        new Translation2d(TRACK_WIDTH_X / 2.0, -TRACK_WIDTH_Y / 2.0),
        new Translation2d(-TRACK_WIDTH_X / 2.0, TRACK_WIDTH_Y / 2.0),
        new Translation2d(-TRACK_WIDTH_X / 2.0, -TRACK_WIDTH_Y / 2.0)
      };

  public static final SwerveDriveKinematics kinematics =
      new SwerveDriveKinematics(moduleTranslations);

  /**
   * Drive Command Config
   *
   * @param xJoystick - Left Joystick X axis
   * @param yJoystick - Left Joystick Y axis
   * @param omegaJoystick - Right Joystick X axis
   * @param slowMode - If the joystick drive should be slowed down
   * @param slowDriveMultiplier - Multiplier for slow mode
   * @param slowTurnMultiplier - Multiplier for slow mode
   * @param povUp - POV/Dpad Up
   * @param povDown - POV/Dpad Down
   * @param povLeft - POV/Dpad Left
   * @param povRight - POV/Dpad Right
   */
  public static final record DriveCommandsConfig(
      CommandXboxController controller,
      BooleanSupplier slowMode,
      LoggedDashboardNumber slowDriveMultiplier,
      LoggedDashboardNumber slowTurnMultiplier) {

    private static final boolean simMode = Constants.currentMode == Constants.Mode.SIM;

    public double getXInput() {
      return simMode ? -controller.getLeftX() : controller.getLeftY();
    }

    public double getYInput() {
      return simMode ? controller.getLeftY() : controller.getLeftX();
    }

    public double getOmegaInput() {
      return -controller.getRightX();
    }

    public boolean povUpPressed() {
      return controller.povUp().getAsBoolean();
    }

    public boolean povDownPressed() {
      return controller.povDown().getAsBoolean();
    }

    public boolean povLeftPressed() {
      return controller.povLeft().getAsBoolean();
    }

    public boolean povRightPressed() {
      return controller.povRight().getAsBoolean();
    }
  }
}

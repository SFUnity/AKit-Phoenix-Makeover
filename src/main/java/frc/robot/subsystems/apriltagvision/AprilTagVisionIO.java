package frc.robot.subsystems.apriltagvision;

import edu.wpi.first.math.geometry.Pose2d;
import org.littletonrobotics.junction.AutoLog;

public interface AprilTagVisionIO {
  @AutoLog
  public static class AprilTagVisionIOInputs {
    public Pose2d estimatedPose;
    public double timestamp;
    public int tagCount;
    public double avgTagDist;
    /** percentage of image */
    public double avgTagArea;

    public double pipeline = 0;
    /** 0 = pipeline control, 1 = force off, 2 = force blink, 3 = force on */
    public double ledMode = 0;
  }

  /** Updates the set of loggable inputs. */
  public default void updateInputs(AprilTagVisionIOInputs inputs) {}

  /** Sets the pipeline index. */
  public default void setPipeline(int pipeline) {}

  /** Sets the pipeline through enum. */
  public default void setPipeline(AprilTagVisionConstants.Pipelines pipeline) {}
}

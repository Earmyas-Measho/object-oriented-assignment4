package assignment4;

/**
 * Represents a type of boat called SailBoat.
 * Inherits from the Boat class.
 */
public class SailBoat extends Boat {
  private double depth;

  /**
   * Constructs a SailBoat object with the specified name, length, and depth.
   *
   * @param name   The name of the sailboat.
   *
   * @param length The length of the sailboat.
   *
   * @param depth  The depth of the sailboat.
   */
  public SailBoat(String name, double length, double depth) {
    super(name, length);
    this.depth = depth;
  }

  /**
   * Retrieves the depth of the sailboat.
   *
   * @return The depth of the sailboat.
   */
  public double getDepth() {
    return depth;
  }

  /**
   * Sets the depth of the sailboat.
   *
   * @param depth The new depth of the sailboat.
   */
  public void setDepth(int depth) {
    this.depth = depth;
  }

  /**
   * Returns a string representation of the sailboat in the format.
   *
   * @return A string representation of the sailboat.
   */
  public String getType() {
    return "SailBoat";
  }

  /**
   * Returns a string representation of the sailboat in the format.
   * "BOAT:name:sailboat:length:depth".
   *
   * @return A string representation of the sailboat.
   */
  @Override
  public String toString() {
    return "BOAT:" + getName() + ":sailboat:" + (int) getLength() + ":" + (int) getDepth();
  }

}

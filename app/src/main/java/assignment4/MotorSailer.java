package assignment4;

/**
 * Motorsailer is a of type boat.
 */
public class MotorSailer extends Boat {
  // Atributes
  private int enginePower;
  private double depth;

  /**
   * Constructs a MotorSailer object with the specified name, length, depth, and
   * engine power.
   *
   * @param name        The name of the motorsailer.
   *
   * @param length      The length of the motorsailer.
   *
   * @param depth       The depth of the motorsailer.
   *
   * @param enginePower The engine power of the motorsailer.
   */
  public MotorSailer(String name, double length, double depth, int enginePower) {
    super(name, length);
    this.enginePower = enginePower;
    this.depth = depth;
  }

  /**
   * Retrieves the engine power of the motorsailer.
   *
   * @return The engine power of the motorsailer.
   */
  public int getEnginePower() {
    return enginePower;
  }

  /**
   * Sets the engine power of the motorsailer.
   *
   * @param enginePower The new engine power of the motorsailer.
   */
  public void setEnginePower(int enginePower) {
    this.enginePower = enginePower;
  }

  /**
   * Retrieves the depth of the motorsailer.
   *
   * @return The depth of the motorsailer.
   */
  public double getDepth() {
    return depth;
  }

  /**
   * Sets the depth of the motorsailer.
   *
   * @param depth The new depth of the motorsailer.
   */
  public void setDepth(int depth) {
    this.depth = depth;
  }

  /**
   * Returns a string representation of the motorsailer in the format.
   *
   * @return A string representation of the motorsailer.
   */
  @Override
  public String getType() {
    return "MotorSailer";
  }

  /**
   * Returns a string representation of the motorsailer in the format.
   * "BOAT:name:motorsailer:length:depth:enginePower".
   *
   * @return A string representation of the motorsailer.
   */

  @Override
  public String toString() {
    String formattedLength = (length % 1 == 0) ? String.valueOf((int) length) : String.valueOf(length);
    String formattedDepth = (depth % 1 == 0) ? String.valueOf((int) depth) : String.valueOf(depth);
    String formattedEnginePower = String.valueOf(enginePower);

    return "BOAT:" + getName() + ":motorsailer:" + formattedLength + ":" + formattedDepth + ":" + formattedEnginePower;
  }

}

package assignment4;

/**
 * Represents a type of boat called MotorBoat.
 * Inherits from the Boat class.
 */
public class MotorBoat extends Boat {
  private int enginePower;

  /**
   * Constructs a MotorBoat object with the specified name, length, and engine.
   *
   * @param name        The name of the motorboat.
   *
   * @param length      The length of the motorboat.
   *
   * @param enginePower The engine power of the motorboat.
   */
  public MotorBoat(String name, double length, int enginePower) {
    super(name, length);
    this.enginePower = enginePower;
  }

  /**
   * Retrieves the engine power of the motorboat.
   *
   * @return The engine power of the motorboat.
   */
  public int getEnginePower() {
    return enginePower;
  }

  /**
   * Sets the engine power of the motorboat.
   *
   * @param enginePower The new engine power of the motorboat.
   */
  public void setEnginePower(int enginePower) {
    this.enginePower = enginePower;
  }

  /**
   * Returns a string representation of the motorboat in the format.
   *
   * @return A string representation of the motorboat.
   */
  @Override
  public String getType() {
    return "MotorBoat";
  }

  /**
   * Returns a string representation of the motorboat in the format.
   * "BOAT:name:motorboat:length:enginePower".
   *
   * @return A string representation of the motorboat.
   */
  @Override
  public String toString() {
    return "BOAT:" + getName() + ":motorboat:" + (int) getLength() + ":" + (int) getEnginePower();
  }

}

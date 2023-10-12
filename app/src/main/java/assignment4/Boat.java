package assignment4;

/**
 * Represents a superclass for the four types of boats.
 * Provides common attributes and behaviors for boats.
 */
public abstract class Boat {
  protected String name;
  protected double length;

  /**
   * Constructs a Boat object with the specified name and length.
   *
   * @param name   The name of the boat.
   *
   * @param length The length of the boat.
   */
  public Boat(String name, double length) {
    this.name = name;
    this.length = length;
  }

  /**
   * Retrieves the length of the boat.
   *
   * @return The length of the boat.
   */
  public double getLength() {
    return length;
  }

  /**
   * Sets the length of the boat.
   *
   * @param length The new length of the boat.
   */
  public void setLength(double length) {
    this.length = length;
  }

  /**
   * Retrieves the name of the boat.
   *
   * @return The name of the boat.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the boat.
   *
   * @param name The new name of the boat.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Retrieves the type of the boat.
   *
   * @return The type of the boat.
   */
  public abstract String getType();

  /**
   * Returns a string representation of the boat.
   *
   * @return A string representation of the boat.
   */
  @Override
  public String toString() {
    String formattedLength = (length % 1 == 0) ? String.valueOf((int) length) : String.valueOf(length);
    return "BOAT:" + getName() + ":" + getType() + ":" + formattedLength;
  }

}

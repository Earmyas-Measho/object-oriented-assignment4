package assignment4;

/**
 * Represents a Canoe, which is a type of boat.
 */
public class Canoe extends Boat {
  /**
   * Constructs a Canoe object with the specified name and length.
   *
   * @param name   The name of the canoe.
   *
   * @param length The length of the canoe.
   */
  public Canoe(String name, double length) {
    super(name, length);
  }

  /**
   * Returns a string representation of the canoe.
   *
   * @return A string representation of the canoe.
   */
  @Override
  public String getType() {
    return "Canoe";
  }

  /**
   * Returns a string representation of the canoe.
   *
   * @return A string representation of the canoe.
   */
  @Override
  public String toString() {
    return "BOAT:" + getName() + ":canoe:" + (int) getLength();
  }

}

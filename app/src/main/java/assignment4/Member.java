package assignment4;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a member with attributes such as name, email, ID, and a list of
 * boats.
 */
public class Member {
  // Attributes
  private String name;
  private String email;
  private String id;
  private ArrayList<Boat> myBoats = new ArrayList<>();

  /**
   * Constructs an empty member object.
   */
  public Member() { // empty Constructor
  }

  /**
   * Constructs a member object with the specified name, ID, and email.
   *
   * @param name  The name of the member.
   *
   * @param id    The ID of the member.
   *
   * @param email The email of the member.
   */
  public Member(String name, String id, String email) {
    this.name = name;
    this.id = id;
    this.email = email;
  }

  /**
   * Constructs a member object with the specified name and a list of existing
   * members.
   * The ID of the member is generated randomly and is unique among the existing
   * members.
   *
   * @param name    The name of the member.
   *
   * @param members The list of existing members.
   */
  public Member(String name, List<Member> members) {
    this.name = name;
    this.id = generateRandomId(members);
  }

  /**
   * Constructs a member object with the specified name, email, and a list of
   * existing members.
   * The ID of the member is generated randomly and is unique among the existing
   * members.
   *
   * @param name    The name of the member.
   *
   * @param email   The email of the member.
   *
   * @param members The list of existing members.
   */
  public Member(String name, String email, List<Member> members) {
    this.name = name;
    this.email = email;
    this.id = generateRandomId(members);
  }

  /**
   * Retrieves the name of the member.
   *
   * @return The name of the member.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the member.
   *
   * @param name The new name of the member.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Retrieves the email of the member.
   *
   * @return The email of the member.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email of the member.
   *
   * @param email The new email of the member.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Retrieves the ID of the member.
   *
   * @return The ID of the member.
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the ID of the member.
   *
   * @param id The new ID of the member.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Adds a new boat to the member's list of boats.
   *
   * @param newBoat The new boat to add.
   */
  public void addBoat(Boat newBoat) {
    myBoats.add(newBoat);
  }

  /**
   * Retrieves the list of boats belonging to the member.
   *
   * @return The list of boats belonging to the member.
   */
  protected List<Boat> getBoats() {
    return new ArrayList<>(myBoats);
  }

  /**
   * Generates a unique random ID for a new member.
   * The ID is a combination of a random string and a UUID, ensuring its
   * uniqueness among the existing members.
   * The method continues to generate IDs until it finds one that is not already
   * in use.
   *
   * @param members The list of existing members.
   *
   * @return A unique ID for a new member.
   */
  private String generateRandomId(List<Member> members) {
    SecureRandom secureRandom = new SecureRandom();
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    int idLength = 6; // Fixed length of 6 characters
    String uniqueID;
    do {
      // Generate the random ID
      StringBuilder randomId = new StringBuilder();
      for (int i = 0; i < idLength; i++) {
        int randomIndex = secureRandom.nextInt(characters.length());
        randomId.append(characters.charAt(randomIndex));
      }
      uniqueID = randomId.toString();
    } while (memberExists(uniqueID, members)); // Check if the ID is already in use
    return uniqueID;
  }

  /**
   * Checks if a member with the specified ID already exists in the registry.
   *
   * @param memberId The ID of the member to check.
   * @return true if a member with the specified ID exists, false otherwise.
   */
  private boolean memberExists(String memberId, List<Member> members) {
    for (Member member : members) {
      if (member.getId().equals(memberId)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Retrieves the boat at the specified index from the member's list of boats.
   *
   * @param index The index of the boat.
   * @return The boat at the specified index.
   */
  public Boat getBoat(int index) {
    return myBoats.get(index);
  }

  /**
   * Deletes the boat at the specified index from the member's list of boats.
   *
   * @param index The index of the boat to delete.
   */
  public void deleteBoat(int index) {
    myBoats.remove(index);
  }

  /**
   * Retrieves the boat with the specified name from the member's list of boats.
   *
   * @param name The name of the boat.
   * @return The boat with the specified name, or null if not found.
   */
  public Boat getBoatByName(String name) {
    for (Boat boat : myBoats) {
      if (boat.getName().equalsIgnoreCase(name)) {
        return boat;
      }
    }
    return null;
  }

  /**
   * Returns a string representation of the member in the format:
   * "MEMBER:name:email:id".
   *
   * @return A string representation of the member.
   */
  @Override
  public String toString() {
    return "MEMBER:" + name + ":" + email + ":" + id;
  }

}

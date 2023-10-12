package assignment4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a member with attributes such as name, email, ID, and a list of
 * boats.
 */
public class Registry {

  private ArrayList<Member> members = new ArrayList<>();
  private String fileName = "src/main/registry.data";

  /**
   * Loads member and boat data from the data file.
   * Each member's boats are added to their corresponding member object.
   * The data file must be in the specified format for successful loading.
   */
  public void loadData() {
    File file = new File(fileName);
    Set<String> existingMemberIDs = new HashSet<>();

    try (BufferedReader br = new BufferedReader(
        new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
      String line;
      Member member = null;
      while ((line = br.readLine()) != null) {
        // Ignore empty lines
        if (line.trim().isEmpty()) {
          continue;
        }
        String[] split = line.split(":");
        // Ensure line format is correct
        if (split[0].equals("MEMBER") && split.length == 4) {
          member = new Member(split[1], split[3], split[2]);
          members.add(member);
          // Add existing member IDs to the set
          existingMemberIDs.add(member.getId());
        } else if (split[0].equals("BOAT") && split.length >= 4 && member != null) {
          try {
            Boat boat = createBoat(split);
            if (boat != null) {
              member.addBoat(boat);
            }
          } catch (NumberFormatException ex) {
            System.out.println("Error in reading boat data: " + ex.getMessage());
          }
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("Could not find data file: " + fileName);
    } catch (IOException e) {
      System.out.println("Error reading data file: " + e.getMessage());
    }
  }

  /**
   * The createBoat method takes an array of Strings and returns a new Boat object
   * based on the provided data.
   * The array should be in the format: [name, type, length, additionalInfo].
   *
   * @param split an array of Strings containing boat data: name, type, length,
   *              and additional info if required.
   *
   * @return a new Boat object based on the provided data.
   *
   * @throws IllegalArgumentException if the boat type is not one of: sailboat,
   *                                  motorboat, motorsailer, or canoe.
   */
  private Boat createBoat(String[] split) throws IllegalArgumentException {
    Boat boat = null;
    try {
      String name = split[1];
      String boatType = split[2].toLowerCase();
      double length = Double.parseDouble(split[3]);
      switch (boatType) {
        case "sailboat":
          double depth = Double.parseDouble(split[4]);
          boat = new SailBoat(name, length, depth);
          break;
        case "motorboat":
          int power = Integer.parseInt(split[4]);
          boat = new MotorBoat(name, length, power);
          break;
        case "motorsailer":
          depth = Double.parseDouble(split[4]);
          power = Integer.parseInt(split[5]);
          boat = new MotorSailer(name, length, depth, power);
          break;
        case "canoe":
          boat = new Canoe(name, length);
          break;
        default:
          throw new IllegalArgumentException("Invalid boat type: " + boatType);
      }
    } catch (NumberFormatException nfe) {
      System.out.println("Invalid number format: " + nfe.getMessage());
    }
    return boat;
  }

  /**
   * Saves member and boat data to the data file.
   * Writes the member and boat information to the data file in the specified
   * format.
   * Each member's boats are written with the corresponding member's information.
   * If an error occurs during saving, an error message is displayed.
   */
  public void saveData() {
    try (FileWriter writer = new FileWriter(fileName, StandardCharsets.UTF_8)) {
      for (Member member : members) {
        writer.write("MEMBER:" + member.getName() + ":" + member.getEmail() + ":" + member.getId() + "\n");
        for (Boat boat : member.getBoats()) {
          writer.write("BOAT:");
          writer.write(boat.getName() + ":");
          if (boat instanceof SailBoat) {
            writer.write("sailboat:" + boat.getLength() + ":" + ((SailBoat) boat).getDepth());
          } else if (boat instanceof MotorBoat) {
            writer.write("motorboat:" + boat.getLength() + ":" + ((MotorBoat) boat).getEnginePower());
          } else if (boat instanceof MotorSailer) {
            writer.write("motorsailer:" + boat.getLength() + ":" + ((MotorSailer) boat).getDepth() + ":"
                + ((MotorSailer) boat).getEnginePower());
          } else if (boat instanceof Canoe) {
            writer.write("canoe:" + boat.getLength());
          }
          writer.write("\n");
        }
      }
    } catch (IOException e) {
      System.out.println("Error saving data: " + e.getMessage());
    }
  }

  /**
   * If the member is successfully added, the member list is saved to the data
   * file.
   *
   * @param member The member to add to the registry.
   *
   * @throws IllegalArgumentException if the member or member's email is null, or
   *                                  if the email is already in use.
   */
  public void addMember(Member member) {
    try {
      if (member == null || member.getEmail() == null) {
        throw new IllegalArgumentException("Member or member's email is null.");
      }
      for (Member m : members) {
        if (Objects.equals(member.getEmail(), m.getEmail())) {
          throw new IllegalArgumentException("Email address already in use by another member.");
        }
      }
      members.add(member);
      System.out.println("#####################################################");
      System.out.println("Member " + member.getName() + " added successfully!");
      System.out.println("#####################################################");
      saveData();
    } catch (Exception e) {
      System.out.println(e.getMessage());

      ApplicationManager appManager = new ApplicationManager();
      appManager.printInstructions();
    }
  }

  /**
   * If the member is found and removed, the updated member list is saved to the
   * data file.
   *
   * @param name The name of the member to delete.
   */
  public void deleteMemberByName(String name) {
    Member memberToDelete = null;
    for (Member member : members) {
      if (member.getName().equalsIgnoreCase(name)) {
        memberToDelete = member;
        break;
      }
    }
    if (memberToDelete != null) {
      members.remove(memberToDelete);
      saveData();
      System.out.println("#####################################################");
      System.out.println("Member successfully removed with name: " + memberToDelete.getName());
    } else {
      System.out.println("Member not found with the provided name.");
    }
  }

  /**
   * Retrieves a list of all members in the registry.
   *
   * @return A list of all members in the registry.
   */
  public List<Member> getAllMembers() {
    return new ArrayList<>(members);
  }

  /**
   * Retrieves the list of members in the registry.
   *
   * @return The list of members in the registry.
   */
  public List<Member> getMembers() {
    return new ArrayList<>(members);
  }

  /**
   * If the member is found and removed, the updated member list is saved to the
   * data file.
   *
   * @param id The ID of the member to delete.
   */
  public void deleteMember(String id) {
    Member member = getMemberById(id);
    if (member != null) {
      members.remove(member);
      saveData();
      System.out.println("#####################################################");
      System.out.println("Successfully removed member with ID: " + id);
      System.out.println("#####################################################");
    } else {
      System.out.println("#####################################################");
      System.out.println("Member not found.");
      System.out.println("#####################################################");
    }
  }

  /**
   * If the member is found, the boat is added and the updated member list is
   * saved to the data file.
   *
   * @param memberId The ID of the member to add the boat to.
   *
   * @param boat     The boat to add to the member.
   */
  public void addBoatToMember(String memberId, Boat boat) {
    Member member = getMemberById(memberId);
    if (member != null) {
      member.addBoat(boat);
      System.out.println("#####################################################");
      System.out.println("Boat \" " + boat.getName() + " \"added to member \"" + member.getName() + "\" successfully!");
      saveData();
    } else {
      System.out.println("####  Member not found. #####");
    }
  }

  /**
   * Lists all members in the registry.
   * Prints the details of each member, including their name, email, ID, and their
   * boats' details.
   */
  void listMembers() {
    System.out.println("\n#####################################################");
    for (Member member : members) {
      System.out.println("MEMBER:" + member.getName() + ":" + member.getEmail() + ":" + member.getId());
      for (Boat boat : member.getBoats()) {
        System.out.println(boat.toString());
      }
    }
    System.out.println("#####################################################\n");
  }

  /**
   * Retrieves a member by their ID.
   *
   * @param id The ID of the member to retrieve.
   *
   * @return The member with the specified ID, or null if not found.
   */
  public Member getMemberById(String id) {
    for (Member member : members) {
      if (member.getId().equalsIgnoreCase(id)) {
        return member;
      }
    }
    return null;
  }

  /**
   * Retrieves a member by their name.
   *
   * @param name The name of the member to retrieve.
   *
   * @return The member with the specified name, or null if not found.
   */
  public Member getMemberByName(String name) {
    for (Member member : members) {
      if (member.getName().equalsIgnoreCase(name)) {
        return member;
      }
    }
    return null;
  }

  /**
   * If the member and boat are found and the index is valid, the updated member
   * list is saved to the data file.
   *
   * @param boatName       The ID of the member to remove the boat from.
   *
   * @param memberIdOrName The index of the boat to remove.
   */
  public void removeBoatFromMember(String memberIdOrName, String boatName) {
    Member member = getMemberById(memberIdOrName);
    if (member == null) {
      member = getMemberByName(memberIdOrName);
    }
    if (member != null) {
      Boat boat = member.getBoatByName(boatName);
      if (boat != null) {
        member.deleteBoat(member.getBoats().indexOf(boat));
        saveData();
        System.out.println("#####################################################");
        System.out.println("Successfully removed boat with name: " + boatName + " from member: " + member.getId());
        System.out.println("#####################################################");
      } else {
        System.out.println("#####################################################");
        System.out.println("Could not find boat with name \" " + boatName + "\" ");
        System.out.println("#####################################################");
      }
    } else {
      System.out.println("#####################################################");
      System.out.println("Could not find member with ID or name \"" + memberIdOrName + "\"");
      System.out.println("#####################################################");
    }
  }

  /**
   * If the member and boat are found and the index is valid, the boat is
   * returned.
   *
   * @param memberId  The ID of the member to retrieve the boat from.
   *
   * @param boatIndex The index of the boat to retrieve.
   *
   * @return The boat at the specified index from the member's list of boats, or
   *         null if not found.
   */
  public Boat getBoatFromMember(String memberId, int boatIndex) {
    Member member = getMemberById(memberId);
    if (member != null) {
      if (boatIndex < 0 || boatIndex >= member.getBoats().size()) {
        System.out.println("### Invalid boat index. ###");
        return null;
      }
      return member.getBoat(boatIndex);
    }
    System.out.println("##### No member found with the provided ID. #####");
    return null;
  }

  /**
   * If the member, boat, and name are found, the updated member list is saved to
   * the data file.
   *
   * @param id   The ID of the member to remove the boat from.
   *
   * @param name The name of the boat to remove.
   */
  public void removeBoatFromMemberByName(String id, String name) {
    Member member = getMemberById(id);
    if (member != null) {
      Boat boat = member.getBoatByName(name);
      if (boat != null) {
        member.deleteBoat(member.getBoats().indexOf(boat));
        saveData();
        System.out.println("#####################################################");
        System.out.println("Successfully removed boat with name: " + name + " from member with ID: " + id);
        System.out.println("#####################################################");
      } else {
        System.out.println("####  Could not find boat with name ##### " + name);
      }
    } else {
      System.out.println("#####################################################");
      System.out.println("Could not find member with ID " + id);
    }
  }

  /**
   * If the member and boat are found, the boat is returned.
   *
   * @param id   The ID of the member to retrieve the boat from.
   *
   * @param name The name of the boat to retrieve.
   *
   * @return The boat with the specified name from the member's list of boats, or
   *         null if not found.
   */
  public Boat getBoatFromMemberByName(String id, String name) {
    Member member = getMemberById(id);
    if (member != null) {
      return member.getBoatByName(name);
    }
    return null;
  }

  /**
   * Checks if a member exists in the registry based on the member's ID.
   *
   * @param id The ID of the member to check.
   *
   * @return true if a member with the specified ID exists in the registry, false
   *         otherwise.
   */
  public boolean memberExists(String id) {
    for (Member member : members) {
      if (member.getId().equals(id)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Retrieves the ID of a member in the registry based on the member's name.
   *
   * @param name The name of the member to retrieve the ID for.
   *
   * @return The ID of the member with the specified name, or null if not found.
   */
  public String getMemberIdByName(String name) {
    for (Member member : members) {
      if (member.getName().equals(name)) {
        return member.getId();
      }
    }
    return null;
  }

}

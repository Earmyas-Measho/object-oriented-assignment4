package assignment4;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * main class.
 */
public class ApplicationManager {
  private Scanner scanner;
  private Registry registry;

  /**
   * Constructor for the ApplicationManager class. Initializes the scanner and
   * registry.
   */
  public ApplicationManager() {
    scanner = new Scanner(new InputStreamReader(System.in, StandardCharsets.UTF_8));
    registry = new Registry();
  }

  /**
   * Starts the application and handles user interactions.
   */
  public void start() {
    boolean quit = false;
    int choice = 0;

    registry.loadData();

    printInstructions();

    while (!quit) {
      try {
        System.out.print("Enter your choice: ");
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
          case 1:
            registry.listMembers();
            break;
          case 2:
            addMember();
            break;
          case 3:
            removeMember();
            break;
          case 4:
            addBoatToMember();
            break;
          case 5:
            removeBoatFromMember();
            break;
          case 6:
            viewBoatFromMember();
            break;
          case 7:
            quit = true;
            registry.saveData();
            System.out.println("#####################################################");
            System.out.println("PROGRAM FINISHED !!    GOOD BYE.");
            System.out.println("#####################################################");
            break;
          default:
            System.out.println("#####################################################");
            System.out.println("Invalid choice. Please try again.");
            System.out.println("#####################################################");
        }
        if (!quit) {
          printInstructions();
        }
      } catch (InputMismatchException e) {
        System.out.println("#####################################################");
        System.out.println("Invalid number input. Please try again.");
        System.out.println("#####################################################");
        scanner.nextLine();
        printInstructions();
      } catch (Exception e) {
        System.out.println("#####################################################");
        System.out.println("An unexpected error occurred: " + e.getMessage());
        System.out.println("#####################################################");
        printInstructions();
      }
    }
  }

  /**
   * Prints the instructions for using the program.
   */
  public void printInstructions() {
    System.out.println("\n");
    System.out.println("#####################################################");
    System.out.println("\nPress ");
    System.out.println("1 - To print the list of members.");
    System.out.println("2 - To add a member to the list.");
    System.out.println("3 - To remove a member from the list.");
    System.out.println("4 - To add a boat to a member.");
    System.out.println("5 - To remove a boat from a member by boat's name.");
    System.out.println("6 - To view a boat from a member by boat's name.");
    System.out.println("7 - To quit the application.");
  }

  /**
   * Adds a member to the registry based on user input.
   * Prompts the user to enter the member's name and email, and validates the
   * email address.
   * If the email is valid, a new member is created and added to the registry.
   */
  public void addMember() {
    System.out.print("Enter the member's name: ");
    String name = scanner.nextLine();
    System.out.print("Enter the member's email: ");
    String email = scanner.nextLine();
    if (isValidEmail(email)) {
      try {
        Member newMember = new Member(name, email, registry.getMembers());
        registry.addMember(newMember);
        System.out.println("Member added successfully: " + newMember.toString());
        System.out.println("#####################################################");
      } catch (Exception e) {
        System.out.println(e.getMessage());
        printInstructions();
      }
    } else {
      System.out.println("#####################################################");
      System.out.println("Invalid email. Please try again.");
      System.out.println("#####################################################");
    }
  }

  /**
   * Removes a member from the registry based on user input.
   * Prompts the user to choose whether to delete the member by ID or by name.
   * Then, prompts for the corresponding ID or name and removes the member from
   * the registry.
   */
  public void removeMember() {
    System.out.print("Would you like to delete a member by (1) ID or (2) Name? :");
    int choice = scanner.nextInt();
    scanner.nextLine(); // clear the newline
    if (choice == 1) {
      System.out.print("Enter the member ID to remove: ");
      String id = scanner.nextLine();
      try {
        registry.deleteMember(id);
      } catch (Exception e) {
        System.out.println(e.getMessage());
        printInstructions();
      }
    } else if (choice == 2) {
      System.out.print("Enter the member name to remove: ");
      String name = scanner.nextLine();
      try {
        registry.deleteMemberByName(name);
        System.out.println("#####################################################");
      } catch (Exception e) {
        System.out.println(e.getMessage());
        printInstructions();
      }
    } else {
      System.out.println("#####################################################");
      System.out.println("Invalid choice.");
    }
  }

  /**
   * Adds a boat to a member in the registry based on user input.
   * Prompts the user to choose whether to add the boat by member ID or by member
   * name.
   * Then, prompts for the corresponding ID or name, as well as the boat's
   * details,
   * and adds the boat to the specified member in the registry.
   */
  public void addBoatToMember() {
    String id = null;
    final String name;
    int type;
    int length;
    double depth;
    int power;
    Boat newBoat;
    System.out.println("#####################################################");
    System.out.println("Would you like to add a boat by (1) Member ID or (2) Member Name?");
    int choice = scanner.nextInt();
    scanner.nextLine(); // clear the newline

    if (choice == 1) {
      while (true) {
        System.out.print("Enter the member id: ");
        id = scanner.nextLine();
        if (registry.memberExists(id)) {
          break;
        } else {
          System.out.println("#####################################################");
          System.out.println("Member not found. Please try again.");
          return;
        }
      }
    } else if (choice == 2) {
      while (true) {
        System.out.print("Enter the member name: ");
        String memberName = scanner.nextLine();
        id = registry.getMemberIdByName(memberName);
        if (id != null) {
          break;
        } else {
          System.out.println("#####################################################");
          System.out.println("Member not found. Please try again.");
          return;
        }
      }
    } else {
      System.out.println("Invalid choice.");
      return;
    }

    System.out.print("Enter boat name: ");
    name = scanner.nextLine();

    while (true) {
      System.out.println("#####################################################");
      System.out.print("Enter boat type (1 for SailBoat, 2 for MotorBoat, 3 for MotorSailer, 4 for Canoe): ");
      try {
        type = scanner.nextInt();
        scanner.nextLine(); // Clear newline character

        if (type >= 1 && type <= 4) {
          break;
        } else {

          System.out.println("#####################################################");
          System.out.println("Invalid boat type. Please try again.");
          return;
        }
      } catch (Exception e) {
        System.out.println("#####################################################");
        System.out.println("Invalid input. Please try again.");
        scanner.nextLine();
        return;
      }
    }

    try {
      switch (type) {
        case 1:
          System.out.print("Enter boat length: ");
          length = scanner.nextInt();
          System.out.print("Enter boat depth: ");
          depth = scanner.nextDouble();
          newBoat = new SailBoat(name, length, depth);
          break;
        case 2:
          System.out.print("Enter boat length: ");
          length = scanner.nextInt();
          System.out.print("Enter engine power: ");
          power = scanner.nextInt();
          newBoat = new MotorBoat(name, length, power);
          break;
        case 3:
          System.out.print("Enter boat length: ");
          length = scanner.nextInt();
          System.out.print("Enter boat depth: ");
          depth = scanner.nextDouble();
          System.out.print("Enter engine power: ");
          power = scanner.nextInt();
          newBoat = new MotorSailer(name, length, depth, power);
          break;
        case 4:
          System.out.print("Enter boat length: ");
          length = scanner.nextInt();
          newBoat = new Canoe(name, length);
          break;
        default:
          throw new RuntimeException("Unexpected error. Invalid boat type.");
      }

      scanner.nextLine();

      try {
        registry.addBoatToMember(id, newBoat);
        System.out.println("Boat added successfully to member with ID: " + id);
        System.out.println("#####################################################");
      } catch (Exception e) {
        System.out.println(e.getMessage());
        printInstructions();
      }
    } catch (Exception e) {
      System.out.println("#####################################################");
      System.out.println("An error occurred when creating the boat: " + e.getMessage());
      printInstructions();
    }
  }

  /**
   * Removes a boat from a member in the registry based on user input.
   * Prompts the user to enter the member ID and the boat's name,
   * and removes the boat from the specified member in the registry.
   */
  public void removeBoatFromMember() {
    System.out.print("Enter the member ID or name: ");
    String memberIdOrName = scanner.nextLine().trim();
    System.out.print("Enter the boat's name: ");
    String boatName = scanner.nextLine().trim();
    try {
      registry.removeBoatFromMember(memberIdOrName, boatName);
    } catch (Exception e) {
      System.out.println("#####################################################");
      System.out.println("An error occurred when removing the boat: " + e.getMessage());
      printInstructions();
    }
  }

  /**
   * Views a boat from a member in the registry based on user input.
   * Prompts the user to enter the member ID and the boat's name,
   * and retrieves and displays the boat from the specified member in the
   * registry.
   */
  public void viewBoatFromMember() {
    System.out.println("----- View Boat from Member -----");
    System.out.print("Enter the member ID or name: ");
    String memberIdOrName = scanner.nextLine().trim();
    System.out.print("Enter the boat's name: ");
    String boatName = scanner.nextLine().trim();

    Member member = registry.getMemberById(memberIdOrName);
    if (member == null) {
      member = registry.getMemberByName(memberIdOrName);
    }

    if (member != null) {
      Boat boat = member.getBoatByName(boatName);
      if (boat != null) {
        System.out.println("### Boat Details: ####");
        System.out.println("Name: " + boat.getName());
        System.out.println("Type: " + boat.getType());
        System.out.println("Length: " + boat.getLength());
        if (boat instanceof MotorSailer) {
          MotorSailer motorSailer = (MotorSailer) boat;
          System.out.println("Depth: " + motorSailer.getDepth());
          System.out.println("Engine Power: " + motorSailer.getEnginePower());
        } else {
          System.out.println("Depth: N/A");
          System.out.println("Engine Power: N/A");
        }
      } else {
        System.out.println("#####################################################");
        System.out.println("Could not find the boat with name: " + boatName);
      }
    } else {
      System.out.println("#####################################################");
      System.out.println("Could not find the member with ID or name: " + memberIdOrName);
    }
    System.out.println("#####################################################");
  }

  /**
   * Checks if the given email address is valid.
   *
   * @param email The email address to validate.
   * @return true if the email is valid, false otherwise.
   */
  public boolean isValidEmail(String email) {
    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pat = Pattern.compile(emailRegex);
    if (email == null) {
      return false;
    }
    return pat.matcher(email).matches();
  }

}

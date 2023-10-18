package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.text.View;
import model.Contract;
import model.Item;
import model.Member;
import model.MemberList;
import view.Viewer;

/**
 * setters and code changers.
 */
public class ControlTower {
  private Viewer viewer = new Viewer();
  private MemberList memberlist = new MemberList();
  
  /**
   * Used to initiate.
   */
  public void start() {
    Member member1 = new Member("Kante", "kant.E@hotmail.com", "1231233");
    Member member2 = new Member("Carnold", "cant@hotmail.com", "3213211");
    Item item = new Item("apple", "321", "a1b2", 1);
    Item item1 = new Item("marcel", "123", "bbbb", 5);

    member1.addItemToOwnedItems(item);
    member2.addItemToOwnedItems(item1);
    memberlist.addMember(member1);
    memberlist.addMember(member2);


    viewer.initial();
    Scanner scanner = new Scanner(System.in);
    while (true) {
      int choice = viewer.mainMenu();
      switch (choice) {
        case 1:
        int choice1 = viewer.memberMenu();
          switch (choice1) {
            case 1:
                // Create a member by asking for inputs
                System.out.print("Enter name: ");
                String name = scanner.nextLine();

                System.out.print("Enter email: ");
                String email = scanner.nextLine();

                System.out.print("Enter mobile number: ");
                String mobile = scanner.nextLine();

                // Create the member and add it to the member list
                Member newMember = new Member(name, email, mobile);
                memberlist.addMember(newMember);
                System.out.println("Member created successfully.");
                break;

            case 2:
                List<Member> members = memberlist.getAllMembers(); // Retrieve all members from the model
                viewer.listMembersSimple(members); // Call the Viewer's method
                int choice2 = viewer.menuIds();
                switch (choice2) {
                  case 1:
                      // Case 1: View all members (with IDs)
                      viewer.listFullInfo(members);
                      break;
                  case 2:
                      //return to menu
                      break;

                  default:
                  System.out.println("Incorrect input. Please try again.");
                      }
                        break;
            case 3:
                System.out.print("Enter the ID of the member to remove: ");
                String memberIdToRemove = scanner.nextLine();
                memberlist.deleteMember(memberIdToRemove);
                System.out.println("Member removed successfully.");
                break;

            case 4:
                // Case 4 logic to update a member's information
                System.out.print("Enter the ID of the member to update: ");
                String memberIdToUpdate = scanner.nextLine();

                // Check if the member exists
                if (memberlist.MemberExists(memberIdToUpdate)) {
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Enter new mobile: ");
                    String newMobile = scanner.nextLine();

                    // Update the member's information
                            memberlist.changeMemberInformation(memberIdToUpdate, newName, newEmail, newMobile);
                        } else {
                            System.out.println("Member not found with ID: " + memberIdToUpdate);
                        }
                    break;

            case 5:
                // Case 5 logic to view full information of a specific member
                System.out.print("Enter the ID of the member to view: ");
                String memberIdToView = scanner.nextLine();

                // Find the member by ID
                Member memberToView = memberlist.getMemberById(memberIdToView);

                if (memberToView != null) {
                    // Display the member's information
                    viewer.displayMemberInformation(memberToView);
                } else {
                    System.out.println("Member not found with ID: " + memberIdToView);
                }
                break;
                    
                    
            case 6:
                // Case: List members in a verbose way
                List<Member> allMembers = memberlist.getAllMembers();
                viewer.listMembersVerbose(allMembers);
                break;

                    case 7:
                        System.exit(0);

                    default:
                        System.out.println("Incorrect input. Please try again.");
                }
                break;
                //case 1 done.
  
      case 2:
          int choice2 = viewer.itemMenu();
          switch (choice2) {
          case 1:
              // Case 1: Create an item
              System.out.print("Enter item name: ");
              String itemName = scanner.next();
              
              System.out.print("Enter item description: ");
              String itemDescription = scanner.next();

              System.out.print("Enter item category: ");
              String itemCategory = scanner.next();

              System.out.print("Cost Daily(integer): ");
              Integer itemCost = scanner.nextInt();

              //creation.
                Item newItem = new Item(itemName,itemDescription, itemCategory, itemCost);

              System.out.print("Choose owner using Id: ");
              String owner = scanner.next();
              Member member = memberlist.getMemberById(owner);
              member.addItemToOwnedItems(newItem);

                // Add the item to a member's item list (you need to determine which member owns the item)
                // Example: member.addItem(newItem);

                System.out.println("Item created successfully!");
                  break;

              case 2:
              List<Member> members = memberlist.getAllMembers(); // Retrieve all members from the model
              viewer.listAllItemsAndInfo(members);
              break;

              case 3:
                //delete item.
                break;

              case 4:
                //change item info.
                break;

              case 5:
                //list item contracts.
                break;
              }

        //break for case 2.
          break;
            
      case 3:
          // Create a new contract
          System.out.println("Create a New Contract:");

          // Prompt for item details
          System.out.print("Enter Item Id: ");
          String itemid = scanner.next();
          Item tempItem = null;

          for (Member member : memberlist.getAllMembers()) {
              for (Item item2 : member.getOwnedItems()) {
                  if (item2.getItemId().equals(itemid)) {
                      tempItem = item2;
                      break; // Exit the loop when the item is found
                  }
              }
          }

          if (tempItem == null) {
              System.out.println("Item not found.");
              break; // Exit the case
          }

          // Prompt for lender details
          System.out.print("Enter Lender Id: ");
          String lenderid = scanner.next();
          Member lender = memberlist.getMemberById(lenderid);

          // Prompt for borrower details
          System.out.print("Enter Borrower Id: ");
          String borrowerid = scanner.next();
          Member borrower = memberlist.getMemberById(borrowerid);

          // Prompt for cost, start date, and end date
          System.out.print("Enter Cost: ");
          int cost = scanner.nextInt();
          System.out.print("Enter Start Date: ");
          int startDate = scanner.nextInt();
          System.out.print("Enter End Date: ");
          int endDate = scanner.nextInt();

          // Create the contract
          Contract contract = new Contract(tempItem, cost, startDate, endDate, lender, borrower);
          lender.addContract(contract);
          borrower.addContract(contract);
          tempItem.addContract(contract);

          System.out.println("Contract created successfully.");
          System.out.println("Lender's Contracts: " + lender.getContracts());
          System.out.println("Borrower's Contracts: " + borrower.getContracts());
          System.out.println("Item's Contracts: " + tempItem.getContracts());
          break;

      case 4:
        System.exit(0);
            
      default:
        System.out.println("Incorrect input. Please try again.");
      } 
    }
  }
  
}


    


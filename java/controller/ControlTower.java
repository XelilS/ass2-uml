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

  
  //constructor.
  public ControlTower(Viewer viewer, MemberList memberList) {
  }


  /**
   * Methods.
   */
  public void createMember() {
    String name = viewer.getInput("Enter name: ");
    String email = viewer.getInput("Enter email: ");
    String mobile = viewer.getInput("Enter mobile number: ");

    Member newMember = new Member(name, email, mobile);
    memberlist.addMember(newMember);
    viewer.displayMessage("Member created successfully.");
}

public void removeMember() {
  String memberIdToRemove = viewer.getInput("Enter the ID of the member to remove: ");
  memberlist.deleteMember(memberIdToRemove);
  viewer.displayMessage("Member removed successfully.");
}

public void updateMember() {
  String memberIdToUpdate = viewer.getInput("Enter the ID of the member to update: ");

  if (memberlist.MemberExists(memberIdToUpdate)) {
      String newName = viewer.getInput("Enter new name: ");
      String newEmail = viewer.getInput("Enter new email: ");
      String newMobile = viewer.getInput("Enter new mobile: ");

      memberlist.changeMemberInformation(memberIdToUpdate, newName, newEmail, newMobile);
      viewer.displayMessage("Member updated successfully.");
  } else {
      viewer.displayErrorMessage("Member not found with ID: " + memberIdToUpdate);
  }
}

public void viewMemberInformation() {
  String memberIdToView = viewer.getInput("Enter the ID of the member to view: ");
  Member memberToView = memberlist.getMemberById(memberIdToView);

  if (memberToView != null) {
      viewer.displayMemberInformation(memberToView);
  } else {
      viewer.displayErrorMessage("Member not found with ID: " + memberIdToView);
  }
}

public void createItem() {
  String itemName = viewer.getInput("Enter item name: ");
  String itemDescription = viewer.getInput("Enter item description: ");
  String itemCategory = viewer.getInput("Enter item category: ");
  int itemCost = viewer.getIntInput("Cost Daily(integer): ");

  Item newItem = new Item(itemName, itemDescription, itemCategory, itemCost);

  String ownerId = viewer.getInput("Choose owner using Id: ");
  Member owner = memberlist.getMemberById(ownerId);

  if (owner != null) {
      owner.addItemToOwnedItems(newItem);
      viewer.displayMessage("Item created successfully!");
  } else {
      viewer.displayMessage("Member not found with ID: " + ownerId);
  }
}

public void deleteItem() {
  String memberId = viewer.getInput("Enter the ID of the member who owns the item: ");
  Member member = memberlist.getMemberById(memberId);

  if (member != null) {
      String itemId = viewer.getInput("Enter the ID of the item to delete: ");
      if (member.deleteItemById(itemId)) {
          viewer.displayMessage("Item deleted successfully.");
      } else {
          viewer.displayErrorMessage("Item not found.");
      }
  } else {
      viewer.displayErrorMessage("Member not found.");
  }
}

public void displayItemContracts() {
  String memberId = viewer.getMemberId();
  String itemId = viewer.getItemId();

  Member member = memberlist.getMemberById(memberId);
  if (member != null) {
      Item item = member.getItemById(itemId);
      if (item != null) {
          viewer.displayItemAndContractsInformation(item, memberlist);  // Assuming this method exists in Viewer
      } else {
          viewer.displayErrorMessage("Item with ID " + itemId + " not found for member with ID " + memberId);
      }
  } else {
      viewer.displayErrorMessage("Member with ID " + memberId + " not found");
  }
}



  /**
   * Used to initiate.
   */
  public void start() {

  Member m1 = new Member("Alice", "alice@example.com", "123");
  Member m2 = new Member("Bob", "bob@example.com", "321");
  Member m3 = new Member("Sid", "sid@example.com", "332211");


  Item laptop = new Item("laptop", "performance laptop", "electronic", 50);
  Item bike = new Item("Bike", "A mountain bike", "Sports", 10);

  m1.addItemToOwnedItems(laptop);
  m1.addItemToOwnedItems(bike);

  Contract contract1 = new Contract(bike, m1, m2, 5, 7);

  bike.addContract(contract1);
  m1.addContract(contract1);
  m2.addContract(contract1);

  memberlist.addMember(m1);
  memberlist.addMember(m2);
  memberlist.addMember(m3);

  m1.addCredits(320);
  m2.addCredits(100);
  m3.addCredits(100);



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
                createMember();
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
                  viewer.displayErrorMessage("Incorrect input. Please try again.");
                      }
                        break;
            case 3:
              removeMember();
                break;

            case 4:
                // Case 4 logic to update a member's information
                updateMember();
                    break;

            case 5:
                // Case 5 logic to view full information of a specific member
                viewMemberInformation();
                break;
                    
                    
            case 6:
                // Case: List members in a verbose way
                List<Member> allMembers = memberlist.getAllMembers();
                viewer.listMembersVerbose(allMembers);
                break;

                    case 7:
                        System.exit(0);

                    default:
                      viewer.displayErrorMessage("Incorrect input. Please try again.");
                      }
                break;
                //case 1 done.
  
      case 2:
          int choice2 = viewer.itemMenu();
          switch (choice2) {
          case 1:
              // Case 1: Create an item
              createItem();
                  break;

              case 2:
              List<Member> members = memberlist.getAllMembers(); // Retrieve all members from the model
              viewer.listAllItemsAndInfo(members);
              break;

              case 3:
                //delete item.
                deleteItem();
                break;

              case 4:
                //change item info.
                String ownerId = viewer.getInput("Enter the ID of the member who owns the item: ");
                Member ownerr = memberlist.getMemberById(ownerId);
                if (ownerr != null) {
                  String itemId = viewer.getInput("Enter the ID of the item to update: ");
                  Item itemToUpdate = ownerr.getItemById(itemId);
                if (itemToUpdate != null) {
                  String newName = viewer.getInput("Enter new name: ");
                  String newDescription = viewer.getInput("Enter new description: ");
                  String newCategory = viewer.getInput("Enter new category: ");
                  int newCostDaily = Integer.parseInt(viewer.getInput("Enter new daily cost: "));
                  itemToUpdate.changeItemInfo(newName, newDescription, newCategory, newCostDaily);
                  viewer.displayMessage("Item information updated successfully.");
                } else {
                  viewer.displayMessage("Item not found.");
                }
                  } else {
                  viewer.displayMessage("Member not found.");
                }
                break;

              case 5:
                //list item contracts.
                displayItemContracts();
                break;
              }

        //break for case 2.
          break;
            
      case 3:
          // Create a new contract
    String lenderId = viewer.getStringInput("Enter the lender ID: ");
    Member lender = memberlist.getMemberById(lenderId);

    if (lender != null) {
        String itemId = viewer.getStringInput("Enter the item ID: ");
        Item item = lender.getItemById(itemId);

        if (item != null) {
            String borrowerId = viewer.getStringInput("Enter the borrower ID: ");
            Member borrower = memberlist.getMemberById(borrowerId);

            if (borrower != null) {
                int startDate = viewer.getIntInput("Enter the start date: ");
                int endDate = viewer.getIntInput("Enter the end date: ");

                Contract contract = new Contract(item, lender, borrower, startDate, endDate);
                if (contract.isValid()) {  // Add this method to check if the contract is valid
                    item.addContract(contract);
                    lender.addContract(contract);
                    borrower.addContract(contract);
                    viewer.displayMessage("Contract created successfully.");
                } else {
                    viewer.displayMessage("Failed to create contract. Check the entered details and try again.");
                }
            } else {
                viewer.displayMessage("Borrower not found.");
            }
        } else {
            viewer.displayMessage("Item not found.");
        }
    } else {
        viewer.displayMessage("Lender not found.");
    }
          break;

      case 4:
        System.exit(0);
            
      default:
        viewer.displayErrorMessage("Incorrect input. Please try again.");

      } 
    }
  }
  
}


    


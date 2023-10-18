package view;
import model.Contract;
import model.Item;
import model.Member;
import model.MemberList;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Viewer {
  private Scanner sc;

  public void initial(){
    System.out.println("The Stuff Lending System");
}

  public Viewer() {
    this.sc = new Scanner(System.in);
  }

  public int mainMenu(){
    System.out.println("----------------------");
    System.out.println("Choose an option: ");
    System.out.println("1.Member");
    System.out.println("2.Item");
    System.out.println("3.Create contract");
    System.out.println("4.Exit");
    int choice = sc.nextInt();
    return choice;
  }

  
  public int memberMenu(){
    System.out.println("----------------------");
    System.out.println("Choose an option: ");
    System.out.println("1.Add member");
    System.out.println("2.Show all members");
    System.out.println("3.Remove a member");
    System.out.println("4.Update members info");
    System.out.println("5.Specify a member");
    System.out.println("6.Show all members (Verbose)");
    System.out.println("7.Exit");
    int choice = sc.nextInt();
    return choice;
  }

  public int itemMenu(){
    System.out.println("----------------------");
    System.out.println("Choose an option: ");
    System.out.println("1.Add item");
    System.out.println("2.Show all items");
    System.out.println("3.Remove an item");
    System.out.println("4.Change item info");
    System.out.println("5.List item contracts(previous, current and upcoming)");
    System.out.println("7.Exit");
    int choice = sc.nextInt();
    return choice;
  }

  public int menuIds() {
    System.out.println("----------------------");
    System.out.println("Choose an option: ");
    System.out.println("1.Detailed view");
    System.out.println("2.Exit");
    int choice = sc.nextInt();
    return choice;
  }


  /**
   * Method to list all members in a simple way.
   */
  public void listMembersSimple(List<Member> members) {
    System.out.println("----------------------");
    System.out.println("List of Members (Name, Email, Current Credits, Owned Items):");
    for (Member member : members) {
        System.out.println("Name: " + member.getName());
        System.out.println("Email: " + member.getEmail());
        System.out.println("Current Credits: " + member.getCredits());
        System.out.println("Number of Owned Items: " + member.getOwnedItems().size());
        System.out.println(); // Add a blank line for separation
    }
  }


  /**
   * Method to list all members in a verbose way.
   */
  public void listMembersVerbose(List<Member> members) {
    System.out.println("----------------------");
    System.out.println("List of Members (Verbose):");
    for (Member member : members) {
        System.out.println("Name: " + member.getName());
        System.out.println("Email: " + member.getEmail());
        System.out.println("Owned Items:");

        for (Item item : member.getOwnedItems()) {
            System.out.println("- Item Name: " + item.getItemName());
            System.out.println("  Description: " + item.getDescription());

            // Get contracts associated with this member
            List<Contract> contracts = member.getContracts();
            if (!contracts.isEmpty()) {
                System.out.println("  Contracts:");
                for (Contract contract : contracts) {
                    System.out.println("    Lent To: " + contract.getborrower());
                    System.out.println("    Time Period: " + contract.getStartDate() + " to " + contract.getEndDate());
                }
            } else {
                System.out.println("  Not Lent");
            }
        }
        System.out.println(); // Add a blank line for separation
    }
}




private Contract getContractForItem(Item item, List<Contract> contracts) {
  for (Contract contract : contracts) {
      if (contract.getItem().equals(item)) {
          return contract;
      }
  }
  return null; // Contract for the specified item not found
}


private Member getMemberById(Member member2, List<Member> members) {
  for (Member member : members) {
      if (member.getMemberId().equals(member2)) {
          return member;
      }
  }
  return null; // Member with the specified ID not found
}

    // Method to display member's information
    public void displayMemberInformation(Member member) {
      System.out.println("----------------------");
      System.out.println("Member Information:");
      System.out.println("ID: " + member.getMemberId());
      System.out.println("Name: " + member.getName());
      System.out.println("Email: " + member.getEmail());
      System.out.println("Mobile: " + member.getMobile());
      System.out.println("Current Credits: " + member.getCredits());
      // You can display other member information as needed
  }

  public void listFullInfo(List<Member> members) {
    System.out.println("----------------------");
    System.out.println("List of Members (Id, Name, Email):");
    for (Member member : members) {
        System.out.println("Member Id: " + member.getMemberId());
        System.out.println("Name: " + member.getName());
        System.out.println("Email: " + member.getEmail());
        System.out.println(); // Add a blank line for separation
    }
}

public Contract createContract() {
  System.out.println("----------------------");
  System.out.println("Create a New Contract:");

  // Prompt for item details
  System.out.print("Enter Item Name: ");
  String itemName = sc.nextLine();

  // Prompt for lender details
  System.out.print("Enter Lender Name: ");
  String lenderName = sc.nextLine();

  // Prompt for borrower details
  System.out.print("Enter Borrower Name: ");
  String borrowerName = sc.nextLine();

  // Prompt for cost, start date, and end date
  System.out.print("Enter Cost: ");
  int cost = sc.nextInt();
  System.out.print("Enter Start Date: ");
  int startDate = sc.nextInt();
  System.out.print("Enter End Date: ");
  int endDate = sc.nextInt();

  // Create a new Contract instance
  Item item = new Item(itemName, "X", "X", 1);
  Member lender = new Member(lenderName, "lender@mail.com", "1234");
  Member borrower = new Member(borrowerName, "borrower@mail.com", "4321");

  return new Contract(item, startDate, endDate, cost, borrower, lender);
}

public void listAllItemsAndInfo(List<Member> members) {
  for (Member member : members) {
      System.out.println("Member Name: " + member.getName());
      List<Item> items = member.getOwnedItems();
      for (Item item : items) {
          System.out.println("Item Name: " + item.getItemName());
          System.out.println("Item Id: " + item.getItemId());
          System.out.println("Item Description: " + item.getDescription());
          System.out.println("Item Category: " + item.getCategory());
          System.out.println("Item Cost Per Day: " + item.getCostDaily());
          System.out.println(); // Add a blank line for separation
      }
  }
}

}

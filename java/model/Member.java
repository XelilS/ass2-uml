package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating a member.
 */
public class Member {
  private String memberId;
  private String name;
  private String email;
  private String mobile;
  private float credits;
  private List<Item> ownedItems;
  private Integer creationDate;  
  private List<Contract> currentContracts;

  AlphaNumericGen ran = new AlphaNumericGen();


  /**
   * Member constructor.
   */
  public Member(String name, String email, String mobile) {
    this.memberId = ran.generateAlphaNum(6);
    this.name = name;
    this.email = email;
    this.mobile = mobile;
    this.credits = 0;
    this.creationDate = 0;
    this.ownedItems = new ArrayList<>();
    this.currentContracts = new ArrayList<>();
  }

  // Getters
  public String getMemberId() {
    return memberId;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getMobile() {
    return mobile;
  }

  public float getCredits() {
    return credits;
  }

  public List<Item> getOwnedItems() {
    return ownedItems;
  }

  public Integer getCreationDate() {
    return creationDate;
  }

  public List<Contract> getContracts() {
    return currentContracts;
}

  //setters
  public void setMemberId(String memberId) {
    this.memberId = memberId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public void setCredits(float credits) {
    this.credits = credits;
  }

  public void setOwnedItems(List<Item> ownedItems) {
    this.ownedItems = ownedItems;
  }

  public void setCreationDate(Integer creationDate) {
    this.creationDate = creationDate;
  }



  /**
   * Add credits.
   */
  public void addCredits(float amount) {
    credits += amount;
  }

  /**
   * Deduct credits.
   */
  public void deductCredits(float amount) {
    if (credits >= amount) {
      credits -= amount;
    } else {
      System.out.println("Insufficient credits.");
    }
  }

  /**
   * Method to add an item to the member's items list.
   */
  public void addItemToOwnedItems(Item item) {
    ownedItems.add(item);
    addCredits(100);

  }

  
  /**
   * Deletion of items.
   */
  public boolean deleteItemById(String itemId) {
    Item itemToDelete = null;
    for (Item item : ownedItems) {
      if (item.getItemId().equals(itemId)) {
        itemToDelete = item;
        break;
      }
    }

    if (itemToDelete != null) {
        ownedItems.remove(itemToDelete);
        return true; // Item deleted successfully
    }

    return false; // Item not found
  }


  /**
   * Method to create an item for the member and add 100 credits.
   */
  public void createItem(String itemName, String itemId, String itemDescription, String itemCategory, int itemCostDaily) {
    // Create the item
    Item newItem = new Item(itemName, itemDescription, itemCategory, itemCostDaily);

    // Add 100 credits to the member
    addCredits(100);

    // Add the item to the member's items list
    ownedItems.add(newItem);
  }

  public void updateMemberInformation(String name, String email, String mobile) {
    this.name = name;
    this.email = email;
    this.mobile = mobile;
}

public void addContract(Contract contract) {
  currentContracts.add(contract);
}

}

package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating an item.
 */
public class Item {

  private String name;
  private String itemId;
  private String ownerId;
  private String description;
  private String category;
  private int dayCreation;
  private int costDaily;
  private List<Contract> contracts;

  AlphaNumericGen ran = new AlphaNumericGen();

  /**
   * item constructor.
   */
  public Item(String name, String description, String category, int costDaily) {
    this.itemId = ran.generateAlphaNum(3);
    this.name = name;
    this.description = description;
    this.category = category;
    this.dayCreation = 0;
    this.costDaily = costDaily;
    this.contracts = new ArrayList<>();
  }




  /**
   * Method to change item information.
   */
  public void changeItemInfo(String name, String description, String category, int costDaily) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.costDaily = costDaily;
    // You can update other properties if needed
  }


  // Getters

  public String getItemName() {
    return name;
  }

  public String getItemId() {
    return itemId;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public String getDescription() {
    return description;
  }

  public String getCategory() {
    return category;
  }

  public int getDayCreation() {
    return dayCreation;
  }

  public int getCostDaily() {
    return costDaily;
  }

  public List<Contract> getContracts() {
    return contracts;
  }

  //setters

  public void setName(String name) {
    this.name = name;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public void setDayCreation(int dayCreation) {
    this.dayCreation = dayCreation;
  }

  public void setCostDaily(int costDaily) {
    this.costDaily = costDaily;
  }

  public void addContract(Contract contract) {
    contracts.add(contract);
  }

}

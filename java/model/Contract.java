package model;

/**
 * Class for creating the contract.
 */
public class Contract {

  // attributes
  private int startDate;
  private int endDate;
  private Item item;
  private Member lender;
  private Member borrower;
  private boolean status;
  private int cost;

  /**
   * Contract constructor.
   */
  public Contract(Item item, Member lender, Member borrower, int startDate, int endDate, Time time) {
    if (startDate < time.getCurrentDay() || endDate < startDate) {
      System.out.println("Invalid contract dates.");
      return;
    }

    // Check if the lender has enough credits
    if (lender.getCredits() < item.getCostDaily() * (endDate - startDate + 1)) {
      System.out.println("The lender does not have enough credits.");
      return;
    }

    // Check if the item is available during the specified time period
    if (!isItemAvailable(item, startDate, endDate)) {
      System.out.println("The item is not available during the specified time period.");
      return;
    }

    // Initialize the contract
    this.item = new Item(item);
    this.startDate = startDate;
    this.endDate = endDate;
    this.lender = new Member(lender);
    this.borrower = new Member(borrower);
    this.status = true;
    this.cost = item.getCostDaily() * (endDate - startDate + 1);

    // Deduct credits from the lender
    lender.deductCredits(this.cost);
  }

  private boolean isItemAvailable(Item item, int startDate, int endDate) {
    for (Contract contract : item.getContracts()) {
      if (contract.getStartDate() < endDate && contract.getEndDate() > startDate) {
        return false; // The item is already lent out during the specified period
      }
    }
    return true;
  }

  @Override
  public String toString() {
    return "Contract{"
        + "item=" + item
        + ", cost=" + cost
        + ", startDate=" + startDate
        + ", endDate=" + endDate
        + ", lender=" + lender.getName()
        + ", borrower=" + borrower.getName()
        + '}';
  }

  /**
   * checks the validity.
   */
  public boolean isValid() {
    // check if item is null.
    if (this.item == null) {
      return false;
    }
    // Check if the lender has enough credits
    int totalCost = item.getCostDaily() * (endDate - startDate + 1);
    if (lender.getCredits() < totalCost) {
      return false;
    }

    // Check if the item is available during the specified period
    if (!item.isAvailable(startDate, endDate)) {
      return false;
    }

    // Check if the start date is before or equal to the end date
    if (startDate > endDate) {
      return false;
    }

    return true;
  }

  // getters

  public Item getItem() {
    return new Item(item);
  }

  public boolean getStatus() {
    return status;
  }

  public int getEndDate() {
    return endDate;
  }

  public int getStartDate() {
    return startDate;
  }

  public Member getborrower() {
    return new Member(borrower);
  }

  public Member getOwner() {
    return new Member(lender); // Assuming Member has a copy constructor
  }

  // setters

  public void setItem(Item item) {
    this.item = new Item(item);
  }

  public void setEndDate(int endDate) {
    this.endDate = endDate;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

}

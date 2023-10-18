package model;

import java.util.List;

/**
 * Class for creating the contract.
 */
public class Contract {

  //attributes
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
  public Contract(Item item, int cost, int start, int end, Member Lender, Member borrower) {
    this.item = item;
    this.startDate = start;
    this.endDate = end;
    this.lender = Lender;
    this.cost = cost;
    this.borrower = borrower;
    this.status = true;
  }

  // getters

  public Item getItem() {
    return item;
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
    return borrower;
  }

  public Member getOwner(){
    return lender;
  }

  // setters

  public void setItem(Item item) {
    this.item = item;
  }
  
  public void setEndDate(int endDate) {
    this.endDate = endDate;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

}

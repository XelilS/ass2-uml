package model;


/**
 * Time class.
 */
public class Time {
  //attribute(s)
  private int currentDay;

  public Time() {
    this.currentDay = 0; // Initialize the day counter to 0 when the system starts.
  }

  // Getter for currentDay
  public int getCurrentDay() {
    return currentDay;
  }

  // Method to advance the day
  public void advanceDay() {
    currentDay++;
  }
}

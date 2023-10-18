package model;

import java.util.Random;

/**
 * Class for generating a random id.
 */
public class AlphaNumericGen {
  private final String letters = "abcdefghijklmnopqrstuvwxyz";
  private final char[] alphanumeric = (letters + letters.toUpperCase() + "0123456789").toCharArray();

  /**
   * Method generating the random id.
   */
  public String generateAlphaNum(int length) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < length; i++) {
      result.append(alphanumeric[new Random().nextInt(alphanumeric.length)]);
    }
    return result.toString();
  } 
}

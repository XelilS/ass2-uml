package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Class used to keep track of member addition and deletion.
 */
public class MemberList {

  private List<Member> members = new ArrayList<>();
  private Set<String> registeredEmails = new HashSet<>();
  private Set<String> registeredMobiles = new HashSet<>();

  /**
   * Method to register a member if it is unique.
   */
  public boolean addMember(Member member) {
    if (!isEmailUnique(member.getEmail()) || !isMobileUnique(member.getMobile())) {
      // Email or mobile is not unique; member registration failed
      return false;
    }

    // Email and mobile are unique; add the member to the registry
    members.add(member);
    registeredEmails.add(member.getEmail());
    registeredMobiles.add(member.getMobile());
    return true;
  }

  /**
   * Method to delete a member by member ID.
   */
  public boolean deleteMember(String memberId) {
    for (Member member : members) {
      if (member.getMemberId().equals(memberId)) {
        // Found the member; remove them from the registry
        members.remove(member);
        registeredEmails.remove(member.getEmail());
        registeredMobiles.remove(member.getMobile());
        return true;
      }
    }

    // Member not found; deletion failed
    return false;
  }

  /**
   * Method to change a members info.
   */
  public boolean changeMemberInformation(String memberId, String newName, String newEmail, String newMobile) {
    for (Member member : members) {
      if (member.getMemberId().equals(memberId)) {
        // Found the member; update their information
        member.setName(newName);
        member.setEmail(newEmail);
        member.setMobile(newMobile);
        return true;
      }
    }

    // Member not found; information change failed
    return false;
  }

  /**
   * Method to get the full information of a specific member by member ID.
   */
  public Member getMemberById(String memberId) {
    for (Member member : members) {
      if (member.getMemberId().equals(memberId)) {
        // Found the member; return their information
        return member;
      }
    }

    // Member not found; return null or handle accordingly
    return null;
  }

  // Helper method to get a contract associated with an item
  private Contract getContractForItem(Item item) {
      for (Member member : members) {
          for (Contract contract : member.getContracts()) {
              if (contract.getItem().equals(item)) {
                  return contract;
              }
          }
      }
      return null; // Item is not currently lent
  }

  /**
   * Method to check if the member exists in the list.
   */
  public boolean MemberExists(String memberId) {
    for (Member member : members) {
        if (member.getMemberId().equals(memberId)) {
            return true; // Member with the specified ID exists
        }
    }
    return false; // Member with the specified ID does not exist
  }


  /**
   * Method to check if an email is unique.
   */
  private boolean isEmailUnique(String email) {
    return !registeredEmails.contains(email);
  }

  /**
   * Method to check if a mobile number is unique.
   */
  private boolean isMobileUnique(String mobile) {
    return !registeredMobiles.contains(mobile);
  }

  public List<Member> getAllMembers() {
    return members;
  }

}

package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class used to keep track of member addition and deletion.
 */
public class MemberList {

  //private List<Member> members = new ArrayList<>();
  //private Set<String> registeredEmails = new HashSet<>();
  //private Set<String> registeredMobiles = new HashSet<>();
  private Time time;
  private List<Member> members;
  private Set<String> registeredEmails;
  private Set<String> registeredMobiles;


  public MemberList() {
    this.members = new ArrayList<>();
    this.registeredEmails = new HashSet<>();
    this.registeredMobiles = new HashSet<>();
    this.time = new Time();
  }
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
   * used to create a member directly for an owner.
   */
  public Member memberCreation(String name, String email, String mobile) {
    String memberId;
    AlphaNumericGen ran = new AlphaNumericGen(); // Assuming you have a class for generating random alphanumeric strings

    do {
      memberId = ran.generateAlphaNum(6); // Generate a new ID
    } while (isMemberIdExists(memberId)); // Check if the ID already exists
    Member member = new Member(name, email, mobile, memberId, time);
    members.add(member);
    return member; // Assuming Member constructor takes these parameters
  }

  private boolean isMemberIdExists(String memberId) {
    // Iterate over your member list to check if memberId already exists
    for (Member member : members) { // Assuming memberList is your list of members
      if (member.getMemberId().equals(memberId)) {
        return true; // ID already exists
      }
    }
    return false; // ID is unique
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
   * Used to ensure uniqueness.
   */
  public boolean isEmailOrMobileExists(String email, String mobile) {
    for (Member member : this.members) {
      if (member.getEmail().equalsIgnoreCase(email) || member.getMobile().equals(mobile)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Ensures member existing.
   */
  public boolean memberExists(String memberId) {
    for (Member member : this.members) {
      if (member.getMemberId().equals(memberId)) {
        return true;
      }
    }
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
  /**
   * get members.
   */
  public List<Member> getAllMembers() {
    return members;
  }

  /**
   * get time.
   */
  public Time getTime() {
    return time;
  }

}

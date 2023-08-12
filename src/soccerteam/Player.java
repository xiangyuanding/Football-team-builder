package soccerteam;

import java.util.Comparator;

/**
 * Class Player that represents a soccer player. Keeps the information of first name, last name,
 * date of birth, preferred position and skill level.
 */
public class Player implements Comparable<Player> {

  private String firstName;
  private String lastName;
  private String dateOfBirth;
  private Position preferredPosition;
  private int skill;

  /**
   * Constructor of the class Player.
   * @param firstName the first name of the player
   * @param lastName the last name of the player
   * @param dateOfBirth the date of birth of the player
   * @param preferredPosition the preferred position to play
   * @param skill the player's skill level
   * @throws IllegalArgumentException if skill is larger than 5 or smaller than 1
   * @throws IllegalArgumentException if the date of birth is later than 2013
   */
  public Player(String firstName, String lastName, String dateOfBirth,
                Position preferredPosition, int skill) throws IllegalArgumentException {
    if (skill > 5 || skill < 1) {
      throw new IllegalArgumentException("skill should be 1-5");
    }
    if (Integer.parseInt(dateOfBirth.substring(0, 4)) > 2013) {
      throw new IllegalArgumentException("age should be over 10, by the end of 2023");
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.preferredPosition = preferredPosition;
    this.skill = skill;
  }

  /**
   * the getter method of dateOfBirth.
   * @return String
   */
  public String getDateOfBirth() {
    return dateOfBirth;
  }

  /**
   * the getter method of preferredPosition.
   * @return Position
   */
  public Position getPreferredPosition() {
    return preferredPosition;
  }

  /**
   * the getter method of skill.
   * @return int
   */
  public int getSkill() {
    return skill;
  }

  /**
   *the getter method of firstName.
   * @return String
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   *the getter method of lastName.
   * @return String
   */
  public String getLastName() {
    return lastName;
  }

  @Override
  public int compareTo(Player p) {
    return (this.getLastName() + this.getFirstName()).compareTo(p.getLastName()
            + p.getFirstName());
  }

  @Override
  public String toString() {
    return this.getLastName() + " " + this.getFirstName();
  }
}

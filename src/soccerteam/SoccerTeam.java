package soccerteam;

/**
 * Interface SoccerTeam.
 * A soccer team for children over 10 years old. The maximum capacity of the team is 20 person,
 * and the minimum is 10 person. 7 of the players will be chosen to play, others will be on the
 * bench. The chosen players are selected by skill levels, and their positions are determined
 * with their will.
 * The constructor will throw IllegalArgumentException if the parameters are invalid.
 */
public interface SoccerTeam {

  /**
   * returns the String representation of all team members, including their name and jersey
   * number. Requires no parameters and throws no errors.
   * @return String
   */
  public String getPlayerList();

  /**
   * returns the String representation of 7 chosen team members that plays the game, including
   * their position, name and jersey number. Requires no parameters and throws no errors.
   * @return String
   */
  public String getChosenList();

  /**
   * create and add a player to the team.
   * @param firstName the first name of the player
   * @param lastName the last name of the player
   * @param dateOfBirth the date of birth of the player
   * @param preferredPosition the preferred position to play
   * @param skill the player's skill level
   */
  public void addPlayer(String firstName, String lastName, String dateOfBirth,
                  Position preferredPosition, int skill);

  /**
   * builds the soccer team.
   */
  public void buildTeam();
}

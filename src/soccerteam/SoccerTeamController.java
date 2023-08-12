package soccerteam;

/**
 * Interface SoccerTeamController.
 * The controller of a soccer team program, responsible for giving instructions to view and
 * model. The constructor takes 2 parameters, the view and the model.
 */
public interface SoccerTeamController {

  /**
   * Run the program.
   */
  public void run();

  /**
   * Save the player to a list that will be used for building the team.
   * @param firstName the first name of the player
   * @param lastName the last name of the player
   * @param dateOfBirth the date of birth of the player
   * @param preferredPosition the preferred position to play
   * @param skill the player's skill level
   */
  public void savePlayer(String firstName, String lastName, String dateOfBirth,
                         Position preferredPosition, int skill);

  /**
   * Confirm and build the team.
   */
  public void submitTeam();
}

package soccerteam;

/**
 * Interface SoccerTeamView.
 * The view of a soccer team program, responsible for providing methods for the controller to use.
 * Including the creating of the user interactive window and the output window.
 */
public interface SoccerTeamView {

  /**
   * Create the window used for collecting user input.
   * @param controller a controller object.
   */
  public void createWindow(SoccerTeamController controller);

  /**
   * Updates the window used for collecting user input, provides some information about the input,
   * such as the correctness.
   */
  public void updatePlayerView();

  /**
   * Creates a window to show the starting lineup and all players in the team.
   * @param str the string representation of the team information.
   * @param title the title of the window
   * @param xposition the x coordinate of where the window should show up
   */
  public void updateTeamView(String str, String title, int xposition);

  /**
   * Display the error message in the main window.
   * @param e the error object
   */
  public void displayErrorMessage(IllegalArgumentException e);
}

package soccerteam;

/**
 * The controller of a soccer team program, responsible for giving instructions to view and
 * model. The constructor takes 2 parameters, the view and the model.
 */
public class SoccerTeamControllerImpl implements SoccerTeamController {
  private SoccerTeam team;
  private SoccerTeamView view;

  /**
   * The constructor of SoccerTeamControllerImpl.
   * @param team the object of model
   * @param view the object of view
   */
  public SoccerTeamControllerImpl(SoccerTeam team, SoccerTeamView view) {
    this.team = team;
    this.view = view;
  }

  @Override
  public void run() {
    view.createWindow(this);
  }

  @Override
  public void savePlayer(String firstName, String lastName, String dateOfBirth,
                         Position preferredPosition, int skill) {
    try {
      team.addPlayer(firstName, lastName, dateOfBirth, preferredPosition, skill);
      view.updatePlayerView();
    } catch (IllegalArgumentException e) {
      view.displayErrorMessage(e);
    }
  }

  @Override
  public void submitTeam() {
    try {
      team.buildTeam();
      view.updateTeamView(team.getPlayerList(), "All players", 100);
      view.updateTeamView(team.getChosenList(), "Start lineup", 200);
    } catch (IllegalArgumentException e) {
      view.displayErrorMessage(e);
    }
  }
}

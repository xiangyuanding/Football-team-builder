package soccerteam;

/**
 * the driver class of the final project: soccer team.
 */
public class Driver {

  /**
   * main function that runs a program that is used to create a SoccerTeam.
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    SoccerTeamView view = new SoccerTeamViewImpl();
    SoccerTeam model = new SoccerTeamImpl();
    SoccerTeamController controller = new SoccerTeamControllerImpl(model, view);
    controller.run();
  }
}

package soccerteam;

/**
 * represents the chosen 20 players that will assign jersey numbers to. Based on the
 * information in Player class, added jersey number and position in the match.
 */
public class ChosenPlayer implements Comparable<ChosenPlayer> {

  Player player;
  private int jerseyNumber;
  private Position position;

  /**
   *the constructor of class ChosenPlayer.
   * @param player the Player object, contains a player's information
   * @param jerseyNumber the jersey number assigned to this player
   * @param position the position that is assigned to this player, null if not any
   */
  public ChosenPlayer(Player player, int jerseyNumber, Position position) {
    this.player = player;
    this.jerseyNumber = jerseyNumber;
    this.position = position;
  }

  /**
   *the getter method of player.
   * @return Player
   */
  public Player getPlayer() {
    return player;
  }

  /**
   *the getter method of jerseyNumber.
   * @return int
   */
  public int getJerseyNumber() {
    return jerseyNumber;
  }

  /**
   *the getter method of position.
   * @return Position
   */
  public Position getPosition() {
    return position;
  }

  @Override
  public int compareTo(ChosenPlayer p) {
    if (this.position == null && p == null) {
      return 0;
    } else if (this.position == null) {
      return -1;
    } else if (p == null) {
      return 1;
    }
    return this.getPosition().compareTo(p.getPosition());
  }

  @Override
  public String toString() {
    return this.position + ", " + this.player.getLastName() + " " + this.player.getFirstName()
            + ", " + this.jerseyNumber;
  }
}

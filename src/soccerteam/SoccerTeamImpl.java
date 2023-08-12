package soccerteam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * A class that implements SoccerTeam.
 * A soccer team for children over 10 years old. The maximum capacity of the team is 20 person,
 * and the minimum is 10 person. 7 of the players will be chosen to play, others will be on the
 * bench. The chosen players are selected by skill levels, and their positions are determined
 * with their will.
 */
public class SoccerTeamImpl implements SoccerTeam {

  private ArrayList<Player> input;
  private ChosenPlayer[] playerList;
  private int playerCount;
  private ChosenPlayer[] chosenList;
  private ArrayList<Integer> jerseyTable;

  /**
   * Constructor of SoccerTeamImpl, takes in nothing.
   */
  public SoccerTeamImpl() {
    input = new ArrayList<>();
  }

  /**
   * Constructor of SoccerTeamImpl, takes in an array of type Player, will throw
   * IllegalArgumentException if the parameters are invalid.
   * @param playerList an array of players
   * @throws IllegalArgumentException if the players are less than 10
   */
  private void buildSoccerTeam(Player[] playerList) throws IllegalArgumentException {

    this.playerCount = 0;
    for (int i = 0; i < playerList.length; i++) {
      if (playerList[i] != null) {
        this.playerCount += 1;
      }
    }
    if (this.playerCount < 10) {
      throw new IllegalArgumentException("there must be at least 10 people in a team");
    }
    this.playerCount = Math.min(20, this.playerCount);
    this.playerList = new ChosenPlayer[playerCount];
    this.chosenList = new ChosenPlayer[7];
    this.jerseyTable = new ArrayList<Integer>();
    for (int i = 0; i < playerCount; i++) {
      jerseyTable.add(i, i + 1);
    }
    Random rand = new Random();
    Collections.shuffle(jerseyTable);
    Comparator<Player> comparator = new Comparator<Player>() {
      @Override
      public int compare(Player a, Player b) {
        return b.getSkill() - a.getSkill();
      }
    };
    Arrays.sort(playerList, comparator);
    for (int i = 7; i < this.playerCount; i++) {
      this.playerList[i] = new ChosenPlayer(playerList[i], jerseyTable.get(i), null);
    }
    Player[] candidate = new Player[7];
    for (int i = 0; i < 7; i++) {
      candidate[i] = playerList[i];
    }
    this.selectPlayer(candidate);
    Comparator<ChosenPlayer> nameComparator = new Comparator<ChosenPlayer>() {
      @Override
      public int compare(ChosenPlayer a, ChosenPlayer b) {
        return a.getPlayer().compareTo(b.getPlayer());
      }
    };
    Arrays.sort(this.playerList, nameComparator);
    Arrays.sort(this.chosenList, nameComparator);
    Arrays.sort(this.chosenList);
  }


  /**
   * selects the best players to have a match, and do our best to let them pick their
   * interested position.
   * @param candidate a list of 7 players that have the best skills
   */
  private void selectPlayer(Player[] candidate) {
    int goalie = 1;
    int defender = 2;
    int midfielder = 3;
    int forward = 1;
    for (int i = 0; i < 7; i++) {
      if (candidate[i].getPreferredPosition() == Position.FORWARD && forward > 0) {
        chosenList[i] = new ChosenPlayer(candidate[i], this.jerseyTable.get(i),
                candidate[i].getPreferredPosition());
        forward -= 1;
      } else if (candidate[i].getPreferredPosition() == Position.MIDFIELDER && midfielder > 0) {
        chosenList[i] = new ChosenPlayer(candidate[i], this.jerseyTable.get(i),
                candidate[i].getPreferredPosition());
        midfielder -= 1;
      } else if (candidate[i].getPreferredPosition() == Position.DEFENDER && defender > 0) {
        chosenList[i] = new ChosenPlayer(candidate[i], this.jerseyTable.get(i),
                candidate[i].getPreferredPosition());
        defender -= 1;
      } else if (candidate[i].getPreferredPosition() == Position.GOALIE && goalie > 0) {
        chosenList[i] = new ChosenPlayer(candidate[i], this.jerseyTable.get(i),
                candidate[i].getPreferredPosition());
        goalie -= 1;
      }
    }
    for (int i = 0; i < 7; i++) {
      if (!(chosenList[i] instanceof ChosenPlayer)) {
        if (forward > 0) {
          chosenList[i] = new ChosenPlayer(candidate[i], this.jerseyTable.get(i),
                  Position.FORWARD);
          forward -= 1;
        } else if (midfielder > 0) {
          chosenList[i] = new ChosenPlayer(candidate[i], this.jerseyTable.get(i),
                  Position.MIDFIELDER);
          midfielder -= 1;
        } else if (defender > 0) {
          chosenList[i] = new ChosenPlayer(candidate[i], this.jerseyTable.get(i),
                  Position.DEFENDER);
          defender -= 1;
        } else if (goalie > 0) {
          chosenList[i] = new ChosenPlayer(candidate[i], this.jerseyTable.get(i),
                  Position.GOALIE);
          goalie -= 1;
        }
      }
    }
    for (int i = 0; i < 7; i++) {
      playerList[i] = chosenList[i];
    }
  }

  @Override
  public String getPlayerList() {
    String str = "Lastname&Firstname, Jersey Number\n";
    for (ChosenPlayer i : playerList) {
      str = str + i.getPlayer().toString() + ", " + i.getJerseyNumber() + "\n";
    }
    return str;
  }

  @Override
  public String getChosenList() {
    String str = "Position, Lastname&Firstname, Jersey Number\n";
    for (int i = 0; i < 7; i++) {
      str = str + chosenList[i].toString() + "\n";
    }
    return str;
  }

  @Override
  public void addPlayer(String firstName, String lastName, String dateOfBirth,
                        Position preferredPosition, int skill) {
    input.add(new Player(firstName, lastName, dateOfBirth, preferredPosition, skill));
  }

  @Override
  public void buildTeam() {
    Player[] temp = new Player[input.size()];
    input.toArray(temp);
    buildSoccerTeam(temp);
  }
}

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import soccerteam.ChosenPlayer;
import soccerteam.Player;
import soccerteam.Position;
import soccerteam.SoccerTeam;
import soccerteam.SoccerTeamImpl;



/**
 * Test cases for the interface SoccerTeam, which is implemented by SoccerTeamImpl.
 */
public class SoccerTeamTest {

  private SoccerTeam test;

  /**
   * Setup for the Test cases.
   */
  @Before
  public void setup() {
    test = new SoccerTeamImpl();
    test.addPlayer("Quincey", "Jolene", "20100908", Position.FORWARD, 2);
    test.addPlayer("Ryanne", "Julie", "20120109", Position.FORWARD, 4);
    test.addPlayer("Austin", "Rowan", "20110212", Position.FORWARD, 1);
    test.addPlayer("Veronica", "David", "20120317", Position.DEFENDER, 2);
    test.addPlayer("Eunice", "Ireland", "20100203", Position.FORWARD, 1);
    test.addPlayer("Isebella", "Earlene", "20120406", Position.FORWARD, 2);
    test.addPlayer("Elsabeth", "Gresham", "20110705", Position.MIDFIELDER, 4);
    test.addPlayer("Winnie", "Alycia", "20120508", Position.FORWARD, 3);
    test.addPlayer("Petal", "Sherisse", "20100429", Position.FORWARD, 3);
    test.addPlayer("Karsyn", "Dorian", "20121121", Position.FORWARD, 1);
    test.addPlayer("Palmer", "Jameson", "20111102", Position.DEFENDER, 5);
    test.addPlayer("Willard", "Mabel", "20121219", Position.MIDFIELDER, 1);
    test.addPlayer("Driscoll", "Tamsyn", "20100201", Position.FORWARD, 4);
    test.addPlayer("Jessy", "Shannon", "20121205", Position.FORWARD, 3);
    test.buildTeam();
  }

  @Test
  public void testPlayerGetters() {
    Player u = new Player("Driscoll", "Tamsyn", "20100201", Position.FORWARD, 4);
    assertEquals("Driscoll", u.getFirstName());
    assertEquals("Tamsyn", u.getLastName());
    assertEquals(Position.FORWARD, u.getPreferredPosition());
    assertEquals("20100201", u.getDateOfBirth());
    assertEquals(4, u.getSkill());
  }

  @Test
  public void testChosenPlayerGetters() {
    Player u = new Player("Eunice", "Ireland", "20100203", Position.FORWARD, 1);
    ChosenPlayer v = new ChosenPlayer(u, 3, Position.MIDFIELDER);
    assertEquals(v.getPlayer().toString(), "Ireland Eunice");
    assertEquals(v.getJerseyNumber(), 3);
    assertEquals(v.getPosition(), Position.MIDFIELDER);
  }

  @Test
  public void testPlayerToString() {
    Player u = new Player("Willard", "Mabel", "20121219", Position.MIDFIELDER, 1);
    assertEquals(u.toString(), "Mabel Willard");
  }

  @Test
  public void testChosenPlayerToString() {
    Player u = new Player("Willard", "Mabel", "20121219", Position.MIDFIELDER, 1);
    ChosenPlayer v = new ChosenPlayer(u, 15, Position.GOALIE);
    assertEquals(v.toString(), "GOALIE, Mabel Willard, 15");
  }

  @Test
  public void testPlayerCompareTo() {
    Player u = new Player("Willard", "Mabel", "20121219", Position.MIDFIELDER, 1);
    Player v = new Player("Eunice", "Ireland", "20100203", Position.FORWARD, 2);
    assertTrue(u.compareTo(v) > 0);
    Player s = new Player("Palmer", "Jameson", "20120102", Position.FORWARD, 3);
    Player t = new Player("Palmer", "Jameson", "20111102", Position.DEFENDER, 5);
    assertTrue(s.compareTo(t) == 0);
    assertTrue(t.compareTo(u) < 0);
  }

  @Test
  public void testChosenPlayerCompareTo() {
    Player u = new Player("Willard", "Mabel", "20121219", Position.MIDFIELDER, 1);
    Player v = new Player("Eunice", "Ireland", "20100203", Position.FORWARD, 2);
    Player s = new Player("Palmer", "Jameson", "20120102", Position.FORWARD, 3);
    Player t = new Player("Palmer", "Jameson", "20111102", Position.DEFENDER, 5);
    ChosenPlayer a = new ChosenPlayer(u, 3, Position.GOALIE);
    ChosenPlayer b = new ChosenPlayer(u, 4, Position.FORWARD);
    ChosenPlayer c = new ChosenPlayer(u, 5, Position.FORWARD);
    ChosenPlayer d = new ChosenPlayer(u, 6, Position.MIDFIELDER);
    assertTrue(b.compareTo(a) > 0);
    assertTrue(c.compareTo(b) == 0);
    assertTrue(d.compareTo(b) < 0);
  }

  // we are not testing jersey numbers for now because it's randomly generated.
  @Test
  public void testGetPlayerList() {
    String[] lines = test.getPlayerList().split("\n");
    assertEquals(15, lines.length);
    String[] playera = lines[1].split(", ");
    assertEquals("Alycia Winnie", playera[0]);
    String[] playerb = lines[2].split(", ");
    assertEquals("David Veronica", playerb[0]);
  }

  @Test
  public void testJerseyNumbers() {
    ArrayList<Integer> jerseyTable = new ArrayList<Integer>();
    String[] lines = test.getPlayerList().split("\n");
    for (int i = 0; i < 14; i++) {
      jerseyTable.add(i, Integer.parseInt(lines[i + 1].split(", ")[1]));
    }
    //prove that the table contains every number between 1-14
    for (int i = 1; i < 15; i++) {
      assertTrue(jerseyTable.contains(i));
    }
    int sumOfSubtraction = 0; //used to test that the jersey numbers are random,
    // if not random, the sum of subtraction should be 13
    for (int i = 0; i < 13; i++) {
      sumOfSubtraction += jerseyTable.get(i + 1) - jerseyTable.get(i);
    }
    assertFalse(sumOfSubtraction == 13);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModelNone() {
    SoccerTeam m = new SoccerTeamImpl();
    m.buildTeam();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModelNotEnough() {
    SoccerTeam m = new SoccerTeamImpl();
    m.addPlayer("Isebella", "Earlene", "20120406", Position.FORWARD, 2);
    m.addPlayer("Elsabeth", "Gresham", "20110705", Position.MIDFIELDER, 4);
    m.addPlayer("Winnie", "Alycia", "20120508", Position.FORWARD, 3);
    m.addPlayer("Petal", "Sherisse", "20100429", Position.FORWARD, 3);
    m.addPlayer("Karsyn", "Dorian", "20121121", Position.FORWARD, 1);
    m.buildTeam();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSkillNegative() {
    Player u = new Player("Willard", "Mabel", "20121219", Position.MIDFIELDER, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSkillOver() {
    Player u = new Player("Willard", "Mabel", "20121219", Position.MIDFIELDER, 7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAge() {
    Player v = new Player("Eunice", "Ireland", "20140203", Position.FORWARD, 2);
  }

  @Test
  public void testOverTwentyPeople() {
    SoccerTeamImpl m = new SoccerTeamImpl();
    m.addPlayer("Joslyn", "Herman", "20110923", Position.FORWARD, 3);
    m.addPlayer("Madelaine", "Bobby", "20100812", Position.GOALIE, 4);
    m.addPlayer("Joline", "Ariah", "20120714", Position.FORWARD, 5);
    m.addPlayer("Jaden", "Abbi", "20110617", Position.MIDFIELDER, 2);
    m.addPlayer("Willoughby", "Braylen", "20110501", Position.GOALIE, 1);
    m.addPlayer("Jaren", "Zachary", "20110425", Position.GOALIE, 3);
    m.addPlayer("Valorie", "Lotus", "20120807", Position.FORWARD, 5);
    m.addPlayer("Quincey", "Jolene", "20100908", Position.FORWARD, 2);
    m.addPlayer("Ryanne", "Julie", "20120109", Position.FORWARD, 4);
    m.addPlayer("Austin", "Rowan", "20110212", Position.FORWARD, 5);
    m.addPlayer("Veronica", "David", "20120317", Position.DEFENDER, 2);
    m.addPlayer("Eunice", "Ireland", "20100203", Position.FORWARD, 5);
    m.addPlayer("Isebella", "Earlene", "20120406", Position.FORWARD, 2);
    m.addPlayer("Elsabeth", "Gresham", "20110705", Position.MIDFIELDER, 4);
    m.addPlayer("Winnie", "Alycia", "20120508", Position.FORWARD, 3);
    m.addPlayer("Petal", "Sherisse", "20100429", Position.FORWARD, 3);
    m.addPlayer("Karsyn", "Dorian", "20121121", Position.FORWARD, 5);
    m.addPlayer("Palmer", "Jameson", "20111102", Position.DEFENDER, 5);
    m.addPlayer("Willard", "Mabel", "20121219", Position.MIDFIELDER, 5);
    m.addPlayer("Driscoll", "Tamsyn", "20100201", Position.FORWARD, 4);
    m.addPlayer("Jessy", "Shannon", "20121205", Position.FORWARD, 1);
    m.addPlayer("Mysie", "Chrystal", "20110701", Position.GOALIE, 4);
    m.addPlayer("Pauline", "Tammy", "20120301", Position.FORWARD, 1);
    m.addPlayer("Joella", "Tenley", "20110509", Position.FORWARD, 1);
    m.buildTeam();
    String[] lines = m.getPlayerList().split("\n");
    assertEquals(21, lines.length);
  }
}

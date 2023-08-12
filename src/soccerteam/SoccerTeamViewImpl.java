package soccerteam;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * The view of a soccer team program, responsible for providing methods for the controller to use.
 * Including the creating of the user interactive window and the output window.
 */
public class SoccerTeamViewImpl implements SoccerTeamView {
  private SoccerTeamController controller;
  private JFrame window;
  private JPanel panel;
  private JTextField firstNameText;
  private JLabel firstNameLabel;
  private JTextField lastNameText;
  private JLabel lastNameLabel;
  private JTextField dateOfBirthText;
  private JLabel dateOfBirthLabel;
  private JComboBox preferredPositionText;
  private JLabel preferredPositionLabel;
  private JTextField skillText;
  private JLabel skillLabel;
  private JButton add;
  private JButton submit;
  private int count;
  private JLabel countLabel;
  private JLabel message;

  @Override
  public void createWindow(SoccerTeamController controller) {
    this.controller = controller;
    count = 0;
    window = new JFrame("SoccerTeamBuilder");
    window.setBounds(800, 300, 300, 500);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new JPanel();
    panel.setLayout(null);
    panel.setBounds(0, 0, 300, 500);
    window.add(panel);
    firstNameLabel = new JLabel("First name:");
    firstNameLabel.setBounds(1, 50, 100, 20);
    firstNameText = new JTextField();
    firstNameText.setBounds(150, 50, 100, 20);

    lastNameLabel = new JLabel("Last name:");
    lastNameLabel.setBounds(1, 100, 100, 20);
    lastNameText = new JTextField();
    lastNameText.setBounds(150, 100, 100, 20);

    dateOfBirthLabel = new JLabel("Date of birth:");
    dateOfBirthLabel.setBounds(1, 150, 100, 20);
    JLabel dateOfBirthExample = new JLabel("(e.g. 20121229. Represents 29th Dec, 2012)");
    dateOfBirthExample.setBounds(1, 165, 300, 20);
    dateOfBirthText = new JTextField();
    dateOfBirthText.setBounds(150, 150, 100, 20);

    preferredPositionLabel = new JLabel("Preferred position:");
    preferredPositionLabel.setBounds(1, 200, 150, 20);
    preferredPositionText = new JComboBox<Position>();
    preferredPositionText.addItem(Position.FORWARD);
    preferredPositionText.addItem(Position.MIDFIELDER);
    preferredPositionText.addItem(Position.DEFENDER);
    preferredPositionText.addItem(Position.GOALIE);
    preferredPositionText.setBounds(150, 200, 100, 20);

    skillLabel = new JLabel("Skill(1-5):");
    skillLabel.setBounds(1, 250, 100, 20);
    skillText = new JTextField();
    skillText.setBounds(150, 250, 100, 20);

    add = new JButton("Add player");
    add.setBounds(1, 300, 100, 30);
    add.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.savePlayer(firstNameText.getText(), lastNameText.getText(),
                dateOfBirthText.getText(), (Position) preferredPositionText.getSelectedItem(),
                Integer.parseInt(skillText.getText()));
      }
    });
    submit = new JButton("Build team");
    submit.setBounds(1, 350, 100, 30);
    submit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        controller.submitTeam();
      }
    });

    countLabel = new JLabel("Players: " + count);
    countLabel.setBounds(1, 1, 100, 20);

    message = new JLabel("");
    message.setBounds(1, 400, 300, 20);

    panel.add(countLabel);
    panel.add(firstNameText);
    panel.add(firstNameLabel);
    panel.add(lastNameText);
    panel.add(lastNameLabel);
    panel.add(dateOfBirthText);
    panel.add(dateOfBirthLabel);
    panel.add(dateOfBirthExample);
    panel.add(preferredPositionLabel);
    panel.add(preferredPositionText);
    panel.add(skillText);
    panel.add(skillLabel);
    panel.add(add);
    panel.add(submit);
    panel.add(message);
    window.setVisible(true);
  }

  @Override
  public void updatePlayerView() {
    count += 1;
    countLabel.setText("Players: " + count);
    firstNameText.setText("");
    lastNameText.setText("");
    dateOfBirthText.setText("");
    skillText.setText("");
    message.setForeground(Color.GREEN);
    message.setText("Successful");
  }

  @Override
  public void displayErrorMessage(IllegalArgumentException e) {
    message.setForeground(Color.RED);
    message.setText(e.getMessage());
  }

  @Override
  public void updateTeamView(String str, String title, int xposition) {
    String[] output = str.split("\n");
    JFrame outputWindow = new JFrame(title);
    outputWindow.setBounds(xposition, 300, 300, (output.length + 2) * 20);
    JPanel panel = new JPanel();
    panel.setLayout(null);
    for (int i = 0; i < output.length; i++) {
      JLabel temp = new JLabel(output[i]);
      temp.setBounds(1, i * 20, 300, 20);
      panel.add(temp);

    }
    outputWindow.add(panel);
    outputWindow.setVisible(true);
  }
}

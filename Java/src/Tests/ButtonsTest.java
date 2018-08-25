package Tests;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

import acm.program.GraphicsProgram;

@SuppressWarnings("serial")
public class ButtonsTest extends GraphicsProgram {
  public void init() {
     add(new JButton("Green"), SOUTH);
     add(new JButton("Yellow"), SOUTH);
     add(new JButton("Red"), SOUTH);
     addActionListeners();
  }

/** Listen for a button action */
  public void actionPerformed(ActionEvent e) {
     println(e.getActionCommand());
  }
}

package pijakogui.test;

import javax.swing.*;

import static javax.swing.JOptionPane.QUESTION_MESSAGE;

public class CreateDialog {

        public static void main(final String[] args) {
            final JFrame parent = new JFrame();
            JButton button = new JButton();

            button.setText("Click me to show dialog!");
            parent.add(button);
            parent.pack();
            parent.setVisible(true);

            button.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    String name = JOptionPane.showInputDialog(parent,
                            "What is your name?", QUESTION_MESSAGE);
                }
            });
        }
    }


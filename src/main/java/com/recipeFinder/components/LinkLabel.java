package com.recipeFinder.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LinkLabel extends JLabel {
    public JLabel label;

    public LinkLabel(String initialText, String hoverText) {

        label = new JLabel("<html>" + initialText + "</html");
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label.setForeground(Color.BLUE);

        label.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                // Handle the label click event here
                System.out.println("Label clicked!");
            }

            public void mousePressed(MouseEvent e) {}

            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {
                label.setText("<html><u>" + hoverText + "</u></html");
            }

            public void mouseExited(MouseEvent e) {
                label.setText("<html>" + initialText + "</html");
            }
        });
    }

    public void addMouseListener(MouseListener listener) {
        label.addMouseListener(listener);
    }


}

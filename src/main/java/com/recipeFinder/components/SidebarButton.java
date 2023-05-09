package com.recipeFinder.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SidebarButton {
    public static JButton create(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(300, 50));
        button.setBackground(new Color(0, 0, 0, 0));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                button.setBackground(new Color(255, 255, 255, 50));
            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(255, 255, 255, 20));
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(0, 0, 0, 0));
            }
        });

        return button;
    }
}

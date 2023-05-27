package com.flavorfinder.shared.utils;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Theme {
    public static void setFlatLafTheme(String theme) {
        try {
            switch (theme) {
                case "Light":
                    FlatLightLaf.setup();
                    break;
                case "Dark":
                    FlatDarkLaf.setup();
                    break;
                case "IntelliJ":
                    FlatIntelliJLaf.setup();
                    break;
                default:
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
            }

            SwingUtilities.updateComponentTreeUI(new JFrame());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }
}

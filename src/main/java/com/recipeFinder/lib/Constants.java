package com.recipeFinder.lib;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Constants {
    public static final String SQLITE_URL = "jdbc:sqlite:db/recipeFinder.db";

    public static final Image STANDARD_ICON = new ImageIcon("src/main/resources/recipe.png").getImage();

    public static Font getMainFont() {
        try {
            File fontFile = new File("src/main/resources/Inter.ttf");

            // Create the font instance by loading the font file
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            // Use the custom font in your application
            return customFont.deriveFont(13f);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }


}

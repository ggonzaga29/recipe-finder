package com.flavorfinder.shared.utils;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Constants {
    public static final String SQLITE_URL = "jdbc:sqlite:db/recipeFinder.db";

    public static final Image STANDARD_ICON = new ImageIcon("src/main/resources/recipe.png").getImage();

    public static Font getMainFont() {
        try {
            File fontFile = new File("src/main/resources/OpenSans.ttf");

            // Create the font instance by loading the font file
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            // Use the custom font in your application
            return customFont.deriveFont(13f);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public static String VERSION_NUMBER() {
        try {
            MavenXpp3Reader reader = new MavenXpp3Reader();
            Model model = reader.read(new FileReader("pom.xml"));
            return model.getVersion();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static class Colors {
        public static Color PRIMARY = Color.decode("#eeeeee");
    }


}

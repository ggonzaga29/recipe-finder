package com.recipeFinder.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
    private BufferedImage backgroundImage;
    private int darknessLevel; // Darkness level of the background (0-255)

    public ImagePanel(BufferedImage image) {
        this.backgroundImage = image;
        setBackground(Color.decode("#eeeeee"));
        this.darknessLevel = 0; // Set the initial darkness level to 0
    }

    /**
     * Set the darkness level of the background.
     *
     * @param darknessLevel The darkness level (0-255)
     */
    public void setDarknessLevel(int darknessLevel) {
        this.darknessLevel = darknessLevel;
        repaint(); // Redraw the panel with the new darkness level
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the scale factor for resizing the image
        double widthRatio = (double) getWidth() / backgroundImage.getWidth();
        double heightRatio = (double) getHeight() / backgroundImage.getHeight();
        double scale = Math.max(widthRatio, heightRatio);

        // Calculate the new size of the image
        int newWidth = (int) (backgroundImage.getWidth() * scale);
        int newHeight = (int) (backgroundImage.getHeight() * scale);

        // Calculate the position to center the image
        int x = (getWidth() - newWidth) / 2;
        int y = (getHeight() - newHeight) / 2;

        // Draw the resized image
        g.drawImage(backgroundImage, x, y, newWidth, newHeight, null);

        // Draw the darkened background
        if (darknessLevel > 0) {
            g.setColor(new Color(0, 0, 0, darknessLevel));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }
}

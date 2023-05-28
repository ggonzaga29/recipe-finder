package com.flavorfinder.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A panel that displays an image responsively based on its preferred size.
 * <p>
 *     The image is resized to fit the panel's preferred size.
 *     The image can also be darkened by setting the darkness level.
 *     The darkness level is a value between 0 and 255.
 *     The darkness level can be changed gradually with an animated option.
 * </p>
 */
public class ImagePanel extends JPanel {
    private BufferedImage backgroundImage;
    private int darknessLevel; // Darkness level of the background (0-255)
    private int animationDuration; // Duration of the darkness level animation in milliseconds

    public ImagePanel(BufferedImage image) {
        this.backgroundImage = image;
        this.darknessLevel = 0; // Set the initial darkness level to 0
        this.animationDuration = 0; // Set the initial animation duration to 0 (no animation)
    }

    public ImagePanel() {
        setBackground(Color.decode("#eeeeee"));
        this.darknessLevel = 0; // Set the initial darkness level to 0
        this.animationDuration = 0; // Set the initial animation duration to 0 (no animation)
    }

    public void setBackgroundImage(BufferedImage image) {
        this.backgroundImage = image;
        revalidate();
        repaint(); // Redraw the panel with the new background image
    }

    /**
     * Set the darkness level of the background.
     *
     * @param darknessLevel The darkness level (0-255)
     * @param animated      Whether to animate the darkness level change
     * @param duration      The duration of the darkness level animation in milliseconds
     */
    public void setDarknessLevel(int darknessLevel, boolean animated, int duration) {
        if (animated) {
            if (duration <= 0) {
                setDarknessLevel(darknessLevel);
                return;
            }

            int currentDarknessLevel = this.darknessLevel;
            this.animationDuration = duration;
            Timer timer = new Timer(16, null); // Timer fires every 16 milliseconds (60 FPS)

            timer.addActionListener(new ActionListener() {
                private final long startTime = System.currentTimeMillis();

                @Override
                public void actionPerformed(ActionEvent e) {
                    long currentTime = System.currentTimeMillis();
                    long elapsedTime = currentTime - startTime;

                    if (elapsedTime >= animationDuration) {
                        timer.stop();
                        setDarknessLevel(darknessLevel);
                    } else if (elapsedTime >= 100) { // Add a 50 ms delay before starting the animation
                        float progress = (float) (elapsedTime - 100) / (animationDuration - 50);
                        int interpolatedDarknessLevel = (int) (currentDarknessLevel +
                                (darknessLevel - currentDarknessLevel) * progress);
                        setDarknessLevel(interpolatedDarknessLevel);
                    }
                }
            });

            timer.start();
        } else {
            setDarknessLevel(darknessLevel);
        }
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

        /*
         * Resize the image to fit the panel's preferred size.
         * Much like background-size: cover in CSS.
         */

        // Calculate the scale factor for resizing the image
        double widthRatio = (double) getWidth() / backgroundImage.getWidth();
        double heightRatio = (double) getHeight() / backgroundImage.getHeight();
        double scale = Math.max(widthRatio, heightRatio); // determines how much the image should be resized

        // Calculate the new size of the image based on the scale factor
        int newWidth = (int) (backgroundImage.getWidth() * scale);
        int newHeight = (int) (backgroundImage.getHeight() * scale);

        // Calculate the position to center the image
        // center the image as the last step to emulate background-position: center
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

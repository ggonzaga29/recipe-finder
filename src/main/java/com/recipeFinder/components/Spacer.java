package com.recipeFinder.components;

import javax.swing.*;
import java.awt.*;

public class Spacer extends JPanel {
    private int width;
    private int height;

    public Spacer(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

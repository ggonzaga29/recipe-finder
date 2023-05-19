package com.recipeFinder.utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WrapLayout extends FlowLayout {
    private static final int DEFAULT_HGAP = 5; // Default horizontal gap
    private static final int DEFAULT_VGAP = 5; // Default vertical gap

    public WrapLayout() {
        super();
    }

    public WrapLayout(int align) {
        super(align);
    }

    public WrapLayout(int align, int hgap, int vgap) {
        super(align, hgap, vgap);
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return layoutSize(parent, true);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return layoutSize(parent, false);
    }

    private Dimension layoutSize(Container parent, boolean preferred) {
        synchronized (parent.getTreeLock()) {
            int targetWidth = parent.getWidth();
            if (targetWidth == 0) {
                targetWidth = Integer.MAX_VALUE;
            }

            int hgap = getHgap();
            int vgap = getVgap();
            int horizontalSpace = targetWidth - hgap * 2;
            int maxRowHeight = 0;
            int accumulatedHeight = 0;
            int maxRowWidth = 0;
            int rowWidth = 0;
            int componentCount = parent.getComponentCount();
            List<Dimension> dimensions = new ArrayList<>();

            for (int i = 0; i < componentCount; i++) {
                Component component = parent.getComponent(i);
                if (component.isVisible()) {
                    Dimension componentSize = preferred ? component.getPreferredSize() : component.getMinimumSize();

                    if (rowWidth + componentSize.width + hgap <= horizontalSpace) {
                        rowWidth += componentSize.width + hgap;
                        maxRowHeight = Math.max(maxRowHeight, componentSize.height);
                    } else {
                        dimensions.add(new Dimension(rowWidth, maxRowHeight));
                        accumulatedHeight += maxRowHeight + vgap;
                        maxRowWidth = Math.max(maxRowWidth, rowWidth);
                        rowWidth = componentSize.width;
                        maxRowHeight = componentSize.height;
                    }
                }
            }

            dimensions.add(new Dimension(rowWidth, maxRowHeight));
            accumulatedHeight += maxRowHeight;
            maxRowWidth = Math.max(maxRowWidth, rowWidth);

            return new Dimension(maxRowWidth + hgap * 2, accumulatedHeight + vgap);
        }
    }

    @Override
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int containerWidth = parent.getWidth() - insets.left - insets.right;
            int x = insets.left + getHgap();
            int y = insets.top + getVgap();
            int rowHeight = 0;

            for (int i = 0; i < parent.getComponentCount(); i++) {
                Component component = parent.getComponent(i);
                if (component.isVisible()) {
                    Dimension componentSize = component.getPreferredSize();
                    if (x + componentSize.width > containerWidth) {
                        x = insets.left + getHgap();
                        y += rowHeight + getVgap();
                        rowHeight = 0;
                    }

                    component.setBounds(x, y, componentSize.width, componentSize.height);
                    x += componentSize.width + getHgap();
                    rowHeight = Math.max(rowHeight, componentSize.height);
                }
            }
        }
    }
}


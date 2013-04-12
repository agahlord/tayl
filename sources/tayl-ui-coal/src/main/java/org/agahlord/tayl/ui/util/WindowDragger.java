package org.agahlord.tayl.ui.util;

import java.awt.Component;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.SwingUtilities;

/**
 * 
 * @author explodingpixels.com
 */
public class WindowDragger {

    private Window window;

    private Component component;

    private int x;

    private int y;

    public static WindowDragger registerWindowDragger(Window window, Component component) {
        return new WindowDragger(window, component);
    }
    
    public WindowDragger(Window window, Component component) {
        this.window = window;
        this.component = component;

        this.component.addMouseListener(createMouseListener());
        this.component.addMouseMotionListener(createMouseMotionListener());
    }

    private MouseListener createMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point clickPoint = new Point(e.getPoint());
                SwingUtilities.convertPointToScreen(clickPoint, component);

                x = clickPoint.x - window.getX();
                y = clickPoint.y - window.getY();
            }
        };
    }

    private MouseMotionAdapter createMouseMotionListener() {
        return new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point dragPoint = new Point(e.getPoint());
                SwingUtilities.convertPointToScreen(dragPoint, component);

                window.setLocation(dragPoint.x - x, dragPoint.y - y);
            }
        };
    }

}



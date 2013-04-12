package org.agahlord.tayl.ui.coal.widget;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.Border;
import org.agahlord.tayl.ui.coal.CoalPainter;
import org.agahlord.tayl.ui.util.Corners;
import org.agahlord.tayl.ui.util.ShapeUtils;

/**
 *
 * @author john.vasquez
 */
public class CoalBorder implements Border {

    private int arc;
    private Color color;
    private Corners roundCorners;

    public CoalBorder() {
        this.arc = 10;
        this.color = CoalPainter.WidgetBorderStroke;
        this.roundCorners = Corners.ALL;
    }

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public int getArc() {
        return arc;
    }

    public void setArc(int arc) {
        this.arc = arc;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public Corners getRoundCorners() {
        return roundCorners;
    }
    
    public void setRoundCorners(Corners roundCorners) {
        this.roundCorners = roundCorners;
    }
    //</editor-fold>
    
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D graphics = (Graphics2D) g;

        // Set antialiasing if HQ.
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the border
        graphics.setColor( color );
        graphics.draw( ShapeUtils.createRoundRectangle(x, y, width - 1, height - 1, roundCorners, arc) );
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(1,1,1,1);
    }

    public boolean isBorderOpaque() {
        return false;
    }
}

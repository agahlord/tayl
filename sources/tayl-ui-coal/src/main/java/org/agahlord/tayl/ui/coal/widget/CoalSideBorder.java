package org.agahlord.tayl.ui.coal.widget;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.border.Border;
import org.agahlord.tayl.ui.coal.CoalPainter;
import org.agahlord.tayl.ui.util.Sides;

/**
 *
 * @author john.vasquez
 */
public class CoalSideBorder implements Border {

    private Color color;
    private Sides sides;

    public CoalSideBorder() {
        this.color = CoalPainter.WidgetBorderStroke;
        this.sides = Sides.ALL;
    }

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public Sides getSides() {
        return sides;
    }
    
    public void setSides(Sides sides) {
        this.sides = sides;
    }
    //</editor-fold>
    
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D graphics = (Graphics2D) g;

        // Set antialiasing if HQ.
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the border
        graphics.setColor( color );
        
        if( sides.isTop() ) {
            graphics.drawLine(x, y, width, y);
        }
        if( sides.isLeft() ) {
            graphics.drawLine(x, y, x, height);
        }
        if( sides.isRight()) {
            graphics.drawLine(width - 1, y, width - 1, height);
        }
        if( sides.isBottom()) {
            graphics.drawLine(x, height - 1, width, height - 1);
        }
        //graphics.drawLine(0, height-1, width, height-1);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets( sides.isTop() ? 1 : 0 , sides.isLeft() ? 1 : 0, sides.isBottom() ? 1 : 0, sides.isRight() ? 1 : 0 );
    }

    public boolean isBorderOpaque() {
        return false;
    }
    
}

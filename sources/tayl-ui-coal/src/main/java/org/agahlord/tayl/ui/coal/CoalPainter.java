package org.agahlord.tayl.ui.coal;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import org.agahlord.tayl.ui.util.Corners;
import org.agahlord.tayl.ui.util.ShapeUtils;

/**
 *
 * @author john.vasquez
 */
public class CoalPainter {
    
    public static final Color Transparent = new Color(0, 0, 0, 0);
    public static final Color WindowBorder = new Color(0, 0, 0, 180); 
    
    public static final Color WidgetBorderStroke = new Color(34, 34, 34); 
    public static final Color WidgetBorderStrokeLight = new Color(114, 114, 114, 200);
    public static final Color WidgetBorderStrokeDark = new Color(51, 51, 51, 200); 
    
    public static final Color PlainFill = new Color(61, 61, 61, 230);
    public static final Color WidgetFillTop = new Color(81, 81, 81, 245);
    public static final Color WidgetFillBottom = new Color(41, 41, 41, 245);
    
    public static final Color Text = new Color(240, 240, 240, 245);
    public static final Color TextDisabled = new Color(120, 120, 120, 245);
    public static final Color TextShadow = new Color(11, 11, 11, 245);
    
    public static final Color Gloss_1 = new Color(57, 57, 57, 245);
    public static final Color Gloss_2 = new Color(46, 46, 46, 245);
    public static final Color Gloss_3 = new Color(35, 35, 35, 245);
    public static final Color Gloss_4 = new Color(40, 40, 40, 245);
    
    public static final Color GlossActive_1 = new Color(107, 169, 244);
    public static final Color GlossActive_2 = new Color(17, 96, 227);
    
    public static final Color GlossBorder = new Color(23, 23, 23, 255);
    public static final Color GlossBorderLeft = new Color(255, 255, 255, 21);
    public static final Color GlossBorderRight = new Color(0, 0, 0, 125);

    public static final Color GlossPressed_1 = new Color(20, 20, 20, 245);
    public static final Color GlossPressed_2 = new Color(30, 30, 30, 245);
    public static final Color GlossPressed_3 = new Color(25, 25, 25, 245);
    public static final Color GlossPressed_4 = new Color(30, 30, 30, 245);
    
    public static final Color GlossPressedBorderTop = new Color(3, 3, 3, 245);
    public static final Color GlossPressedBorderBottom = new Color(41, 41, 41, 245);
    
    public static final Color GlossPressedShadow_1 = new Color(22, 22, 22, 245);
    public static final Color GlossPressedShadow_2 = new Color(23, 23, 23, 245);
    public static final Color GlossPressedShadow_3 = new Color(25, 25, 25, 245);
        
    private CoalPainter() {}
    
    public static Graphics2D activateAntiAlias(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        // Set antialiasing if HQ.
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        return graphics;
    }
    
    public static void paintPlain(int x, int y, int width, int height, Graphics g, Corners corners, int arc) {
        Graphics2D graphics = activateAntiAlias(g);

        // Draw the panel background.
        graphics.setPaint( PlainFill );
        graphics.fill( ShapeUtils.createRoundRectangle(x, y, width, height, corners, arc) );
    }
    
    public static void paintWidget(int x, int y, int width, int height, Graphics g, Corners corners, int arc) {
        Graphics2D graphics = activateAntiAlias(g);

        // Draw the panel background.
        Paint widgetFill = new GradientPaint(x, y, WidgetFillTop, x, height, WidgetFillBottom);
        graphics.setPaint( widgetFill );
        graphics.fill( ShapeUtils.createRoundRectangle(x, y, width, height, corners, arc) );
       
        // Draw the border stroke
        Paint widgetBorderStroke = new GradientPaint(x, y, WidgetBorderStrokeLight, x, height, WidgetBorderStrokeDark);
        graphics.setPaint( widgetBorderStroke );
        graphics.draw( ShapeUtils.createRoundRectangle(x, y, width - 1, height - 2, corners, arc) );
    }
    
    public static void paintGloss(int x, int y, int width, int height, Graphics g, boolean pressed) {
        Graphics2D graphics = activateAntiAlias(g);
        
        // calculate the middle of the area to paint.
        int midY = height/2;
        
        Color color1 = pressed ? GlossPressed_1 : Gloss_1 ;
        Color color2 = pressed ? GlossPressed_2 : Gloss_2 ;
        Color color3 = pressed ? GlossPressed_3 : Gloss_3 ;
        Color color4 = pressed ? GlossPressed_4 : Gloss_4 ;
        
        // paint the top half of the background with the corresponding
        // gradient. note that if we were using Java 6, we could use a
        // LinearGradientPaint with multiple stops.
        graphics.setPaint( new GradientPaint(x, y, color1, x, midY, color2) );
        graphics.fillRect( x, y+1, width, midY );
                
        // paint the top half of the background with the corresponding gradient.
        graphics.setPaint( new GradientPaint(x, midY + 1, color3, x, height, color4) );
        graphics.fillRect(x, midY, width, height - midY - 1);
        
        if( pressed ) {
            // draw the top and bottom border.
            graphics.setColor(GlossPressedBorderTop);
            graphics.drawLine(x, y, x+width-1, y);
            graphics.setColor(GlossPressedBorderBottom);
            graphics.drawLine(x, y+height-1, x+width-1, y+height-1);

            // paint the outter part of the inner shadow.
            graphics.setColor(GlossPressedShadow_1);
            graphics.drawLine(x, y+1, x, y+height-2);
            graphics.drawLine(x, y+1, x+width-1, y+1);
            graphics.drawLine(x+width-1, y+1, x+width-1, y+height-2);

            // paint the middle part of the inner shadow.
            graphics.setColor(GlossPressedShadow_2);
            graphics.drawLine(x+1, y+2, x+1, y+height-3);
            graphics.drawLine(x+1, y+2, x+width-2, y+2);
            graphics.drawLine(x+width-2, y+2, x+width-2, y+height-3);

            // paint the inner part of the inner shadow.
            graphics.setColor(GlossPressedShadow_3);
            graphics.drawLine(x+2, y+3, x+2, y+height-4);
            graphics.drawLine(x+2, y+3, x+width-3, y+3);
            graphics.drawLine(x+width-3, y+3, x+width-3, y+height-4);
            
        } else {
            // draw the top and bottom border.
            graphics.setColor(GlossBorder);
            graphics.drawLine(x, y, width, y);
            graphics.drawLine(x, height-1, width, height-1);
        }
    }
    
    public static void paintGlossActive(int x, int y, int width, int height, Graphics g) {
        Graphics2D graphics = activateAntiAlias(g);
        
        // paint the top half of the background with the corresponding gradient.
        graphics.setPaint(new GradientPaint(x, y+1, GlossActive_1, x, height-2, GlossActive_2));
        graphics.fillRect(x, y+1, width, height-2);
        
        // draw the top and bottom border.
        graphics.setColor(GlossBorder);
        graphics.drawLine(x, y, width, y);
        graphics.drawLine(x, height-1, width, height-1);
    }
    
    public static void paintText(Graphics g, Font f, int mnemonicIndex, int shiftOffset, Rectangle textRect, String text, boolean enabled) {
        FontMetrics fontMetrics = g.getFontMetrics(f);

        // paint the shadow text.
        g.setColor( TextShadow );
        BasicGraphicsUtils.drawStringUnderlineCharAt(g, text, mnemonicIndex,
                textRect.x + shiftOffset,
                textRect.y + fontMetrics.getAscent() + shiftOffset - 1);

        // paint the actual text.
        g.setColor(enabled ? Text : TextDisabled );
        BasicGraphicsUtils.drawStringUnderlineCharAt(g, text, mnemonicIndex,
                textRect.x + shiftOffset,
                textRect.y + fontMetrics.getAscent() + shiftOffset);
    }
}

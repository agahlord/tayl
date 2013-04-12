package org.agahlord.tayl.ui.coal.widget;

import java.awt.Graphics;
import java.awt.LayoutManager;
import javax.swing.JPanel;
import org.agahlord.tayl.ui.coal.CoalPainter;
import org.agahlord.tayl.ui.util.Corners;

/**
 *
 * @author john.vasquez
 */
public class CoalPanel extends JPanel {
    
    public enum Type {
        PLAIN, WIDGET, TABBED; 
    }
    
    private Type type;
    
    private Corners corners;
    private int arc;
    
    public CoalPanel() {
        super();
        
        this.type = Type.WIDGET;
        
        this.corners = Corners.ALL;
        this.arc = 9;
        
        this.setOpaque(false);
    }

    public CoalPanel(LayoutManager layout) {
        super(layout);
    }
    
    public CoalPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }
    
    public CoalPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public Type getType() {
        return type;
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    
    public Corners getCorners() {
        return corners;
    }
    
    public void setCorners(Corners corners) {
        this.corners = corners;
    }
    
    public int getArc() {
        return arc;
    }
    
    public void setArc(int arc) {
        this.arc = arc;
    }
    //</editor-fold>
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        
        if( type == Type.PLAIN ) {
            CoalPainter.paintPlain(0, 0, width, height, g, corners, arc);
        }
        if( type == Type.WIDGET ) {
            CoalPainter.paintWidget(0, 0, width, height, g, corners, arc);
        }
        if( type == Type.TABBED ) {
            CoalPainter.paintGloss(0, 0, width, 30, g, false);
            CoalPainter.paintPlain(0, 30, width, height - 30, g, corners, arc);
        }
    }
}


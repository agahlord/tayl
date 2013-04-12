package org.agahlord.tayl.ui.coal.plaf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollBar;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicScrollBarUI;
import org.agahlord.tayl.ui.coal.CoalPainter;

/**
 *
 * @author john.vasquez
 */
public class CoalScrollBarUI extends BasicScrollBarUI {

    @Override
    protected void installDefaults() {
        super.installDefaults();
        
        this.scrollBarWidth = 10;
        this.minimumThumbSize = scrollbar.getOrientation() == JScrollBar.VERTICAL ? 
                new Dimension(10, 20) : 
                new Dimension(20, 10);
        
        // We need the scrollbar to be not opaque
        LookAndFeel.installProperty(scrollbar, "opaque", Boolean.FALSE);
        
        this.thumbColor = new Color(120, 120, 120, 175);
        this.thumbHighlightColor = new Color(120, 120, 120, 245);
    }
    
    @Override
    protected void installComponents() {
        super.installComponents();
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createZeroButton();
    }

    @Override    
    protected JButton createIncreaseButton(int orientation) {
        return createZeroButton();
    }

    private JButton createZeroButton() {
        JButton jbutton = new JButton();
        jbutton.setPreferredSize(new Dimension(0, 0));
        jbutton.setMinimumSize(new Dimension(0, 0));
        jbutton.setMaximumSize(new Dimension(0, 0));
        return jbutton;
    }
    
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // Track is not painted
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        int arc = (scrollbar.getOrientation() == JScrollBar.VERTICAL ? thumbBounds.width : thumbBounds.height) - 4;
        
        Graphics2D graphics = CoalPainter.activateAntiAlias(g);
        
        graphics.setColor( this.isThumbRollover() ? thumbHighlightColor : thumbColor );
        graphics.fillRoundRect(thumbBounds.x + 2, thumbBounds.y + 2, thumbBounds.width - 4, thumbBounds.height - 4, arc, arc);
    }    
}

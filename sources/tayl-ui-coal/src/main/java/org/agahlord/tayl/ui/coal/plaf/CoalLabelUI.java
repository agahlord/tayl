package org.agahlord.tayl.ui.coal.plaf;

import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicLabelUI;
import org.agahlord.tayl.ui.coal.CoalPainter;
import sun.swing.SwingUtilities2;

/**
 *
 * @author john.vasquez
 */
public class CoalLabelUI extends BasicLabelUI {
    
    @Override
    public void paint(Graphics g, JComponent c) {
        c.setOpaque(false);
        CoalPainter.activateAntiAlias(g);
        
        super.paint(g, c);
    }
    
    @Override
    protected void paintEnabledText(JLabel l, Graphics g, String s, int textX, int textY) {
        int mnemonicIndex = l.getDisplayedMnemonicIndex();
        
        g.setColor( CoalPainter.TextShadow );
        SwingUtilities2.drawStringUnderlineCharAt(l, g, s, mnemonicIndex, textX, textY - 1);
        
        g.setColor( CoalPainter.Text );
        SwingUtilities2.drawStringUnderlineCharAt(l, g, s, mnemonicIndex, textX, textY);
    }

    @Override
    protected void paintDisabledText(JLabel l, Graphics g, String s, int textX, int textY) {
        int mnemonicIndex = l.getDisplayedMnemonicIndex();
        
        g.setColor( CoalPainter.TextShadow );
        SwingUtilities2.drawStringUnderlineCharAt(l, g, s, mnemonicIndex, textX, textY - 1);
        
        g.setColor( CoalPainter.TextDisabled );
        SwingUtilities2.drawStringUnderlineCharAt(l, g, s, mnemonicIndex, textX, textY);
    }
}

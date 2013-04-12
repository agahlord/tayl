package org.agahlord.tayl.ui.coal.plaf;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.WEST;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.text.View;
import org.agahlord.tayl.ui.coal.CoalFactory;
import org.agahlord.tayl.ui.coal.CoalPainter;

/**
 *
 * @author john.vasquez
 */
public class CoalTabbedPaneUI extends BasicTabbedPaneUI {

    private static final Insets NO_INSETS = new Insets(0, 0, 0, 0);
    
    @Override
    protected int calculateTabAreaHeight(int tabPlacement, int tabIndex, int fontHeight) {
        return 30;
    }
    
    @Override
    protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
        return 30;
    }

    @Override
    protected int calculateMaxTabHeight(int tabIndex) {
        return 30;
    }
    
    @Override
    protected JButton createScrollButton(int direction) {       
        return new ScrollButton(direction);
    }
    
    @Override
    protected Insets getContentBorderInsets(int tabPlacement) {
        return NO_INSETS;
    }

    @Override
    protected Insets getSelectedTabPadInsets(int tabIndex) {
        return NO_INSETS;
    }

    @Override
    protected Insets getTabAreaInsets(int tabIndex) {
        return NO_INSETS;
    }
    
    @Override
    protected int getTabLabelShiftX(int tabPlacement, int tabIndex, boolean isSelected) {
        return 0;
    }

    @Override
    protected int getTabLabelShiftY(int tabPlacement, int tabIndex, boolean isSelected) {
        return 0;
    }
    
    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.installProperty(tabPane, "opaque", false);
        tabPane.setFont(tabPane.getFont().deriveFont(Font.BOLD));
    }
    
    @Override
    protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
        // do not paint border
    }

    @Override
    protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
        // do not paint focus indicator
    }
    
    @Override
    protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
        super.paintTabArea(g, tabPlacement, selectedIndex); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        if( isSelected ) {
            CoalPainter.paintGlossActive(x, y, w, h, g);
        } else {
            g.setColor( CoalPainter.Transparent );
            g.fillRect( x, y, w, h );
        }
    }
        
    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        if( !isSelected ) {
            if( tabIndex == 0 ) {
                g.setColor(CoalPainter.GlossBorderRight);
                g.drawLine(x+w-1, y, x+w-1, y+h);
            } else if ( tabIndex == tabPane.getTabCount() - 1 ) {
                g.setColor(CoalPainter.GlossBorderLeft);
                g.drawLine(x, y, x, y+h);
                g.setColor(CoalPainter.GlossBorderRight);
                g.drawLine(x+w-2, y, x+w-2, y+h);
                g.setColor(CoalPainter.GlossBorderLeft);
                g.drawLine(x+w-1, y, x+w-1, y+h);
            } else {
                g.setColor(CoalPainter.GlossBorderLeft);
                g.drawLine(x, y, x, y+h);
                g.setColor(CoalPainter.GlossBorderRight);
                g.drawLine(x+w-1, y, x+w-1, y+h);
            }
        }
    }
    
    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        g.setFont(isSelected ? font.deriveFont(Font.BOLD) : font);
        View textView = getTextViewForTab(tabIndex);

        if (textView != null) {
            textView.paint(g, textRect);
            return;
        }

        int mnemonicIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);

        // paint the shadow text.
        g.setColor( CoalPainter.TextShadow );
        BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemonicIndex,
                textRect.x,
                textRect.y + metrics.getAscent() - 1);

        // paint the actual text.
        g.setColor( tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex) ? CoalPainter.Text : CoalPainter.TextDisabled );
        BasicGraphicsUtils.drawStringUnderlineCharAt(g, title, mnemonicIndex,
                textRect.x,
                textRect.y + metrics.getAscent());
    }
    
    @Override
    protected boolean shouldPadTabRun(int tabPlacement, int run) {
        return runCount > 1 && run < runCount - 1;
    }
    
    @Override
    protected boolean shouldRotateTabRuns(int tabPlacement) {
        return false;
    }

    private class ScrollButton extends JButton implements UIResource, SwingConstants {
        public ScrollButton(int direction) {
            this.setUI( new ScrollButtonUI(direction) );
            this.setPreferredSize( new Dimension(30, 30) );

            this.setIcon( CoalFactory.buildIcon(ScrollButton.class, "/org/agahlord/tayl/ui/coal/rsc/" + (direction == WEST ? "previous.png" : "next.png")) );
            this.setDisabledIcon( CoalFactory.buildIcon(ScrollButton.class, "/org/agahlord/tayl/ui/coal/rsc/" + (direction == WEST ? "previous-disabled.png" : "next-disabled.png")) );
        }
    }
    
    private class ScrollButtonUI extends BasicButtonUI {
        private int direction;

        public ScrollButtonUI(int direction) {
            this.direction = direction;
        }
        
        @Override
        protected void installDefaults(AbstractButton button) {
            super.installDefaults(button);
            button.setBackground(CoalPainter.Transparent);
            button.setOpaque(false);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton button = (AbstractButton) c;
            button.setBorderPainted(false);

            int x = 0;
            int y = 0;
            int w = button.getWidth();
            int h = button.getHeight();

            if (button.isSelected()) {
                CoalPainter.paintGloss(x, y, w, h, g, true);
            }
            
            super.paint(g, c);
        }

        @Override
        protected void paintText(Graphics g, AbstractButton button, Rectangle textRect, String text) {
            CoalPainter.paintText(g, button.getFont(), button.getDisplayedMnemonicIndex(), getTextShiftOffset(), textRect, text, button.isEnabled());
        }

        @Override
        protected void paintButtonPressed(Graphics graphics, AbstractButton button) {
            CoalPainter.paintGloss(0, 0, button.getWidth(), button.getHeight(), graphics, true);
        }

        @Override
        protected void paintFocus(Graphics grphcs, AbstractButton ab, Rectangle rctngl, Rectangle rctngl1, Rectangle rctngl2) {
        }
    }
}

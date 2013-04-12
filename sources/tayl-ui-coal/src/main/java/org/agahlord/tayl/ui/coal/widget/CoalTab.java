package org.agahlord.tayl.ui.coal.widget;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.agahlord.tayl.ui.coal.CoalFactory;
import org.agahlord.tayl.ui.coal.plaf.CoalLabelUI;
import org.agahlord.tayl.ui.coal.widget.osx.OSXTab;
import org.agahlord.tayl.ui.util.TextUtils;

/**
 *
 * @author john.vasquez
 */
public class CoalTab extends JPanel {
 
    private final JTabbedPane pane;
    private JLabel label;
    private JButton closeButton;
    
    public CoalTab(final JTabbedPane pane) {
        super(new FlowLayout(FlowLayout.LEADING, 0, 0), true);
        this.pane = pane;
        this.label = new TabLabel();
        
        this.label.setUI( new CoalLabelUI() );
        
        this.closeButton = CoalFactory.buildImageButton();
        this.closeButton.setIcon( CoalFactory.buildIcon(OSXTab.class, "/org/agahlord/tayl/ui/coal/rsc/close.png") );
        this.closeButton.setPressedIcon( CoalFactory.buildIcon(OSXTab.class, "/org/agahlord/tayl/ui/coal/rsc/close-pressed.png") );
        this.closeButton.setRolloverIcon( CoalFactory.buildIcon(OSXTab.class, "/org/agahlord/tayl/ui/coal/rsc/close-rollover.png") );
        
        init();
    }
    
    private void init() {
        //this.add( Box.createRigidArea(new Dimension(10, 10)) );
        this.add( closeButton );
        this.add( Box.createRigidArea(new Dimension(10, 10)) );
        this.add( label );
        //this.add( Box.createRigidArea(new Dimension(10, 10)) );
    }
    
    @Override
    public boolean isOpaque() {
        return false;
    }
    
    private class TabLabel extends JLabel {
        
        private int index() {
            return pane.indexOfTabComponent(CoalTab.this);
        }
        
        @Override
        public String getText() {
            return index() >= 0 ? pane.getTitleAt( index() ) : "";
        }

        @Override
        public Icon getIcon() {
            return index() >= 0 ? pane.getIconAt( index() ) : null;
        }

        @Override
        public Icon getDisabledIcon() {
            return index() >= 0 ? pane.getDisabledIconAt( index() ) : null;
        }
        
        @Override
        public boolean isEnabled() {
            return index() >= 0 ? pane.isEnabledAt( index() ) : false;
        }
        
        @Override
        public Font getFont() {
            return pane.getFont();
        }
    }
    
}

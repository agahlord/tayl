package org.agahlord.tayl.ui.coal.widget.osx;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import org.agahlord.tayl.ui.coal.CoalFactory;
import org.agahlord.tayl.ui.coal.widget.CoalWindowControlsPanel;

/**
 *
 * @author john.vasquez
 */
public class OSXWindowControlsPanel extends CoalWindowControlsPanel {

    public OSXWindowControlsPanel(JFrame owner) {
        super(owner);
    }
    
    @Override
    protected String getResourcePath() {
        return "/org/agahlord/tayl/ui/coal/rsc/osx/";
    }
    
    @Override
    protected void init() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        super.setLayout(layout);
        
        Component boxLeft = Box.createRigidArea(new Dimension(6, 25));
        Component boxMiddleLeft = Box.createRigidArea(new Dimension(6, 6));
        Component boxMiddleRight = Box.createRigidArea(new Dimension(6, 6));
        Component boxRight = Box.createRigidArea(new Dimension(6, 25));
        
        super.add( boxLeft );
        super.add( btnClose );
        super.add( boxMiddleLeft );
        super.add( btnMinimize );
        super.add( boxMiddleRight );
        super.add( btnMaximize );
        super.add( boxRight );
        
        btnClose.addMouseListener( new RolloverListener() );
        boxMiddleLeft.addMouseListener( new RolloverListener() );
        btnMaximize.addMouseListener( new RolloverListener() );
        boxMiddleRight.addMouseListener( new RolloverListener() );
        btnMinimize.addMouseListener( new RolloverListener() );
    }
    
    private class RolloverListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            btnClose.setIcon( CoalFactory.buildIcon(getClass(), getResourcePath() + CLOSE + (inactive ? INACTIVE : "") + ROLLOVER) );
            btnMaximize.setIcon( CoalFactory.buildIcon(getClass(), getResourcePath() + MAXIMIZE + (inactive ? INACTIVE : "") + ROLLOVER) );
            btnMinimize.setIcon( CoalFactory.buildIcon(getClass(), getResourcePath() + MINIMIZE + (inactive ? INACTIVE : "") + ROLLOVER) );
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setButtonIcons(btnClose, CLOSE, inactive);
            setButtonIcons(btnMaximize, MAXIMIZE, inactive);
            setButtonIcons(btnMinimize, MINIMIZE, inactive);
        }
    }
}

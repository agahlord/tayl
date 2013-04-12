package org.agahlord.tayl.ui.coal.widget;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.agahlord.tayl.ui.coal.CoalFactory;

/**
 *
 * @author john.vasquez
 */
public abstract class CoalWindowControlsPanel extends JPanel {

    protected static final String CLOSE = "close";
    protected static final String MAXIMIZE = "maximize";
    protected static final String MINIMIZE = "minimize";
    
    protected static final String INACTIVE = "-inactive";
    
    protected static final String NORMAL = "-normal.png";
    protected static final String ROLLOVER = "-rollover.png";
    protected static final String PRESSED = "-pressed.png";
    protected static final String DISABLED = "-disabled.png";
    
    protected final JFrame owner;
    
    protected JButton btnClose;
    protected JButton btnMaximize;
    protected JButton btnMinimize;
    
    protected boolean inactive;
    protected boolean iconified;
    protected boolean maximize;
    
    public CoalWindowControlsPanel(final JFrame owner) {
        this.owner = owner;
        this.owner.addWindowListener( new DeactivationListener() );
        this.owner.addWindowStateListener( new IconifiedStateListener() );
        
        this.btnClose = CoalFactory.buildImageButton();
        this.btnMaximize = CoalFactory.buildImageButton();
        this.btnMinimize = CoalFactory.buildImageButton();
        
        setButtonIcons(this.btnClose, CLOSE, false);
        setButtonIcons(this.btnMaximize, MAXIMIZE, false);
        setButtonIcons(this.btnMinimize, MINIMIZE, false);
        
        btnClose.addActionListener( new CloseActionListener() );
        btnMaximize.addActionListener( new MaximizeActionListener() );
        btnMinimize.addActionListener( new IconifyActionListener() );
        
        init();
    }
    
    @Override
    public boolean isOpaque() {
        return false;
    }
    
    protected abstract void init();
    
    protected abstract String getResourcePath();
    
    protected final void setButtonIcons(JButton button, String buttonName, boolean inactive) {
        String name = getResourcePath() + buttonName + ( inactive ? INACTIVE : "" );
        button.setIcon( CoalFactory.buildIcon(getClass(), name + NORMAL) );
        button.setRolloverIcon( CoalFactory.buildIcon(getClass(), name + ROLLOVER) );
        button.setPressedIcon( CoalFactory.buildIcon(getClass(), name + PRESSED) );
        button.setDisabledIcon( CoalFactory.buildIcon(getClass(), name + DISABLED) );
    }
    
    private class DeactivationListener extends WindowAdapter {
        @Override
        public void windowActivated(WindowEvent e) {
            inactive = false;
            setButtonIcons(btnClose, CLOSE, false);
            setButtonIcons(btnMaximize, MAXIMIZE, false);
            setButtonIcons(btnMinimize, MINIMIZE, false);
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            inactive = true;
            setButtonIcons(btnClose, CLOSE, true);
            setButtonIcons(btnMaximize, MAXIMIZE, true);
            setButtonIcons(btnMinimize, MINIMIZE, true);
        }
    }
    
    private class IconifiedStateListener implements WindowStateListener {
        public void windowStateChanged(WindowEvent e) {
            if( e.getNewState() == JFrame.ICONIFIED ) {
                iconified = true;
                if( e.getOldState() == JFrame.MAXIMIZED_BOTH ) {
                    maximize = true;
                } else {
                    maximize = false;
                }
            }
            
            if( e.getNewState() == JFrame.NORMAL && iconified ) {
                if( maximize ) {
                    maximize = false;
                    ((JFrame)e.getWindow()).setExtendedState( JFrame.MAXIMIZED_BOTH );
                }
                iconified = false;
            }
        }
    }
    
    private class CloseActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch( owner.getDefaultCloseOperation() ) {
                case JFrame.DISPOSE_ON_CLOSE: {
                    owner.dispose();
                } break;
                case JFrame.DO_NOTHING_ON_CLOSE: {
                
                } break;
                case JFrame.EXIT_ON_CLOSE: {
                    owner.setVisible(false);
                    System.exit(0);
                } break;
                case JFrame.HIDE_ON_CLOSE: {
                    owner.setVisible(false);
                } break;
                default: {
                    owner.setVisible(false);
                    System.exit(0);
                } break;
            }
        }
    }
    
    private class IconifyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            owner.setExtendedState(JFrame.ICONIFIED);
        }
    }
    
    private class MaximizeActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if( owner.getExtendedState() == JFrame.MAXIMIZED_BOTH || owner.getExtendedState() == JFrame.MAXIMIZED_HORIZ || owner.getExtendedState() == JFrame.MAXIMIZED_VERT ) {
                owner.setExtendedState(JFrame.NORMAL);
            } else if( owner.getExtendedState() == JFrame.NORMAL ) {
                owner.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        }
    }
    
}

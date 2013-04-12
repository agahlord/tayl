package org.agahlord.tayl.ui.coal.widget.mswin;

import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import org.agahlord.tayl.ui.coal.widget.CoalWindowControlsPanel;

/**
 *
 * @author john.vasquez
 */
public class MSWindowControlsPanel extends CoalWindowControlsPanel {

    public MSWindowControlsPanel(JFrame owner) {
        super(owner);
    }
    
    @Override
    protected String getResourcePath() {
        return "/org/agahlord/tayl/ui/coal/rsc/mswin/";
    }
    
    @Override
    protected void init() {
        // Prevents the window to paint over the taskbar
        owner.setMaximizedBounds( GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds() );
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnMinimize)
                .addComponent(btnMaximize)
                .addComponent(btnClose)
                .addGap(5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMinimize)
                    .addComponent(btnMaximize)
                    .addComponent(btnClose)))
        );
    }
    
}

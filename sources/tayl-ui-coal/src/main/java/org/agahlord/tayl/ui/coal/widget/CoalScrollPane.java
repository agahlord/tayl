package org.agahlord.tayl.ui.coal.widget;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import org.agahlord.tayl.ui.coal.plaf.CoalScrollBarUI;

/**
 *
 * @author john.vasquez
 */
public class CoalScrollPane extends JScrollPane {

    public CoalScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
        super(view, vsbPolicy, hsbPolicy);
        init();
    }

    public CoalScrollPane(Component view) {
        super(view);
        init();
    }

    public CoalScrollPane(int vsbPolicy, int hsbPolicy) {
        super(vsbPolicy, hsbPolicy);
        init();
    }

    public CoalScrollPane() {
        init();
    }
    
    private void init() {
        this.setBorder(null);
        
        this.getViewport().setOpaque(false);
        if( this.getViewport().getView() instanceof JComponent ) {
            ((JComponent) this.getViewport().getView()).setOpaque(false);
        }
        
        this.getHorizontalScrollBar().setUI( new CoalScrollBarUI() );
        this.getVerticalScrollBar().setUI( new CoalScrollBarUI() );
    }

    @Override
    public boolean isOpaque() {
        return false;
    }
    
}

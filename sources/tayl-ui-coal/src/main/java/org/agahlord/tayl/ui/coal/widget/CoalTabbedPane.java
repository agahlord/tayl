package org.agahlord.tayl.ui.coal.widget;

import javax.swing.JTabbedPane;
import org.agahlord.tayl.ui.coal.plaf.CoalTabbedPaneUI;
import org.agahlord.tayl.ui.util.TextUtils;

/**
 *
 * @author john.vasquez
 */
public class CoalTabbedPane extends JTabbedPane {

    public CoalTabbedPane() {
        super(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        init();
    }
    
    private void init() {
        this.setUI( new CoalTabbedPaneUI() );
    }
    
    @Override
    public String getTitleAt(int index) {
        return TextUtils.clipText(super.getTitleAt(index), 40, true);
    }
    
    @Override
    public boolean isOpaque() {
        return false;
    }
}

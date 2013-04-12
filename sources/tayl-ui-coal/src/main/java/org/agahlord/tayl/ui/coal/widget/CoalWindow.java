package org.agahlord.tayl.ui.coal.widget;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import org.agahlord.tayl.ui.coal.CoalPainter;
import org.agahlord.tayl.ui.util.Corners;

/**
 *
 * @author john.vasquez
 */
public class CoalWindow extends JFrame {

    private Corners corners;
    
    public CoalWindow() throws HeadlessException {
        super();
        init();
    }

    public CoalWindow(GraphicsConfiguration gc) {
        super(gc);
        init();
    }

    public CoalWindow(String title) throws HeadlessException {
        super(title);
        init();
    }

    public CoalWindow(String title, GraphicsConfiguration gc) {
        super(title, gc);
        init();
    }

    public Corners getCorners() {
        return corners;
    }

    public void setCorners(Corners corners) {
        this.corners = corners;
        setBorder();
    }
    
    private void init() {
        this.corners = Corners.TOP;
        this.setUndecorated( true );
        this.setBackground( CoalPainter.Transparent );
        setBorder();
    }
    
    private void setBorder() {
        CoalBorder border = new CoalBorder();
        border.setColor(CoalPainter.WindowBorder);
        border.setRoundCorners(corners);
        this.getRootPane().setBorder( border );
    }
}


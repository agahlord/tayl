package org.agahlord.tayl.ui.util;

/**
 *
 * @author john.vasquez
 */
public class Corners {

    public static final Corners ALL = new Corners(true, true, true, true);
    public static final Corners NONE = new Corners(false, false, false, false);
    
    public static final Corners TOP = new Corners(true, true, false, false);
    public static final Corners TOP_LEFT = new Corners(true, false, false, false);
    public static final Corners TOP_RIGHT = new Corners(false, true, false, false);
    
    public static final Corners LEFT = new Corners(true, false, true, false);
    public static final Corners RIGHT = new Corners(false, true, false, true);
    
    public static final Corners BOTTOM_LEFT = new Corners(false, false, true, false);
    public static final Corners BOTTOM_RIGHT = new Corners(false, false, false, true);
    public static final Corners BOTTOM = new Corners(false, false, true, true);
        
    private boolean topLeft;
    private boolean topRight;
    private boolean bottomLeft;
    private boolean bottomRight;

    public Corners(final boolean topLeft, final boolean topRight, final boolean bottomLeft, final boolean bottomRight) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
    
    public boolean isTopLeft() {
        return topLeft;
    }

    public boolean isTopRight() {
        return topRight;
    }

    public boolean isBottomLeft() {
        return bottomLeft;
    }

    public boolean isBottomRight() {
        return bottomRight;
    }
    
}

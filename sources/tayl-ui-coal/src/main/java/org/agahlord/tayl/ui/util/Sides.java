package org.agahlord.tayl.ui.util;

/**
 *
 * @author john.vasquez
 */
public class Sides {
    
    public static final Sides ALL = new Sides(true, true, true, true);
    public static final Sides NONE = new Sides(false, false, false, false);
    
    public static final Sides TOP = new Sides(true, false, false, false);
    public static final Sides TOP_BOTTOM = new Sides(true, false, true, false);
    public static final Sides BOTTOM = new Sides(false, false, true, false);
    
    public static final Sides LEFT = new Sides(false, true, false, false);
    public static final Sides LEFT_RIGHT = new Sides(false, true, false, true);
    public static final Sides RIGHT = new Sides(false, false, false, true);
        
    public static final Sides TOP_LEFT = new Sides(true, true, false, false);
    public static final Sides TOP_RIGHT = new Sides(true, false, false, true);
    public static final Sides BOTTOM_LEFT = new Sides(false, true, true, false);
    public static final Sides BOTTOM_RIGHT = new Sides(false, false, true, true);
        
    private boolean top;
    private boolean left;
    private boolean right;
    private boolean bottom;

    public Sides(final boolean top, final boolean left, final boolean bottom, final boolean right) {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    public boolean isTop() {
        return top;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isBottom() {
        return bottom;
    }
    
}

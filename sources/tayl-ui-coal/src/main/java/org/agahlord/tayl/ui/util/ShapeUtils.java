package org.agahlord.tayl.ui.util;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 *
 * @author john.vasquez
 */
public class ShapeUtils {
    
    public static Shape createRoundRectangle(int x, int y, int width, int height, Corners corners, int arc) {
        Area shape = new Area( new RoundRectangle2D.Float(x, y, width, height, arc, arc) );
        
        if( !corners.isTopLeft() ) {
            shape.add( new Area(new Rectangle2D.Float(x, y, arc, arc)) ); 
        }
        if( !corners.isTopRight() ) {
            shape.add( new Area(new Rectangle2D.Float(width - arc, y, arc, arc)) ); 
        }
        if( !corners.isBottomLeft() ) {
            shape.add( new Area(new Rectangle2D.Float(x, height - arc, arc, arc)) ); 
        }
        if( !corners.isBottomRight() ) {
            shape.add( new Area(new Rectangle2D.Float(width - arc, height - arc, arc, arc)) ); 
        }        
        
        return shape;
    }
    
}

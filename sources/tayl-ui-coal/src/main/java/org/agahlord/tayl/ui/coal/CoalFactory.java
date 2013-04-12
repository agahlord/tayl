package org.agahlord.tayl.ui.coal;

import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.agahlord.tayl.ui.coal.plaf.CoalLabelUI;

/**
 *
 * @author john.vasquez
 */
public class CoalFactory {

    public static ImageIcon buildIcon(Class clazz, String url) {
        return new ImageIcon( clazz.getResource(url) );
    }
    
    public static JLabel buildCoalLabel() {
        JLabel label = new JLabel();
        label.setUI( new CoalLabelUI() );
        return label;
    }
    
    public static JButton buildCoalButton() {
        JButton button = new JButton();
        button.setOpaque(false);
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        
        return button;
    }
    
    public static JButton buildImageButton() {
        JButton button = new JButton();
        button.setOpaque(false);
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        
        return button;
    }
    
    public static JButton buildCloseButton() {
        JButton button = new JButton();
        button.setOpaque(false);
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        
        return button;
    }
    
}

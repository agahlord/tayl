package org.agahlord.tayl.tester.ui.coal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.agahlord.tayl.ui.coal.CoalFactory;
import org.agahlord.tayl.ui.coal.widget.CoalPanel;
import org.agahlord.tayl.ui.coal.widget.CoalScrollPane;
import org.agahlord.tayl.ui.coal.widget.CoalTab;
import org.agahlord.tayl.ui.coal.widget.CoalTabbedPane;
import org.agahlord.tayl.ui.coal.widget.CoalWindow;
import org.agahlord.tayl.ui.coal.widget.osx.OSXWindowControlsPanel;
import org.agahlord.tayl.ui.coal.widget.mswin.MSWindowControlsPanel;
import org.agahlord.tayl.ui.coal.widget.osx.OSXTab;
import org.agahlord.tayl.ui.util.ComponentResizer;
import org.agahlord.tayl.ui.util.Corners;
import org.agahlord.tayl.ui.util.WindowDragger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author john.vasquez
 */
public class CoalTester {
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
            //UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel" );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CoalTester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CoalTester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CoalTester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(CoalTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CoalWindow window = new CoalWindow();
        window.setLayout( new BorderLayout() );

        CoalPanel topPanel = new CoalPanel();
        topPanel.setLayout( new BorderLayout() );
        
        JPanel osx = new OSXWindowControlsPanel(window);
        topPanel.add( osx, BorderLayout.WEST );
                
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER) );
        titlePanel.setOpaque(false);
        JLabel titleLabel = CoalFactory.buildCoalLabel();
        titleLabel.setText("Tayl");
        titleLabel.setFont( new Font("Segoe UI", Font.PLAIN, 14) );
        //titleLabel.setEnabled(false);
        //titleLabel.setHorizontalAlignment( JLabel.CENTER );
        //titlePanel.add( titleLabel );
        //topPanel.add( titlePanel , BorderLayout.CENTER);
        //topPanel.add( titleLabel, BorderLayout.CENTER );
        
        JPanel win = new MSWindowControlsPanel(window);
        //JPanel win = new OSXWindowControlsPanel(window);
        //topPanel.add( win, BorderLayout.EAST );
        topPanel.setCorners(Corners.TOP);
        
        //ComponentMover cm = new ComponentMover(window, topPanel);
        WindowDragger.registerWindowDragger(window, topPanel);
        window.add(topPanel, BorderLayout.NORTH);
                
        /*CoalPanel leftPanel = new CoalPanel();
        leftPanel.setCorners(Corners.NONE);
        window.add(leftPanel, BorderLayout.WEST);*/
        
        CoalPanel midPanel = new CoalPanel();
        midPanel.setCorners(Corners.NONE);
        midPanel.setLayout(new BorderLayout());
        midPanel.setType(CoalPanel.Type.TABBED);
        midPanel.setArc(0);
        window.add(midPanel, BorderLayout.CENTER);
        
        JTextArea area = new JTextArea("a\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na\na BATMAN!");
        area.setForeground(Color.GREEN);
        CoalScrollPane scrollPane = new CoalScrollPane(area);
                
        ImageIcon icon = CoalFactory.buildIcon(CoalTester.class, "/org/agahlord/tayl/ui/coal/rsc/icon.png");
        JTabbedPane tabbedPane = new CoalTabbedPane();
        tabbedPane.addTab("test tab 0", scrollPane);
        tabbedPane.addTab("test tab 1", new JButton("perro"));
        tabbedPane.addTab("test tab 2", new JLabel("gato"));
        tabbedPane.addTab("test tab 3", new JButton("perro"));
        tabbedPane.addTab("test tab 4", new JLabel("gato"));
        tabbedPane.addTab("test tab 5", new JButton("perro"));
        tabbedPane.addTab("test tab 6", new JLabel("gato"));
        tabbedPane.addTab("test tab 7", new JButton("perro"));
        tabbedPane.addTab("test tab 8", new JLabel("gato"));
        tabbedPane.addTab("test tab 9", new JButton("perro"));
        tabbedPane.addTab("test tab 10", new JLabel("gato"));
        tabbedPane.addTab("test tab 11", new JButton("perro"));
        tabbedPane.addTab("test tab 12", new JLabel("gato"));
        
        tabbedPane.setEnabledAt(2, false);
        tabbedPane.setEnabledAt(4, false);
        tabbedPane.setTabComponentAt(0, new CoalTab(tabbedPane) );
        tabbedPane.setTabComponentAt(1, new CoalTab(tabbedPane) );
        tabbedPane.setTabComponentAt(2, new CoalTab(tabbedPane) );
        tabbedPane.setTabComponentAt(3, new CoalTab(tabbedPane) );
        tabbedPane.setTabComponentAt(4, new CoalTab(tabbedPane) );
        tabbedPane.setTabComponentAt(5, new CoalTab(tabbedPane) );
        tabbedPane.setTabComponentAt(6, new CoalTab(tabbedPane) );
        tabbedPane.setTabComponentAt(7, new CoalTab(tabbedPane) );
        tabbedPane.setTabComponentAt(8, new CoalTab(tabbedPane) );
        tabbedPane.setTabComponentAt(9, new CoalTab(tabbedPane) );
        
        
        midPanel.add( tabbedPane );
        
        /*CoalPanel rightPanel = new CoalPanel();
        rightPanel.setCorners(Corners.NONE);
        window.add(rightPanel, BorderLayout.EAST);*/
        
        CoalPanel bottomPanel = new CoalPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setCorners(Corners.NONE);
        JLabel resizer = new JLabel(new ImageIcon(CoalTester.class.getResource("/org/agahlord/tayl/ui/coal/rsc/resize-grip.png")));
        resizer.setHorizontalAlignment( JLabel.RIGHT );
        resizer.setVerticalAlignment( JLabel.BOTTOM );
        new ComponentResizer(window);
        bottomPanel.add(resizer);
        window.add(bottomPanel, BorderLayout.SOUTH);
        
        window.setSize(500, 300);
        //window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }
    
}

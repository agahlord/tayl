package org.agahlord.tayl.tester;

import java.io.IOException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.DefaultCaret;
import org.agahlord.tayl.TailConfiguration;
import org.agahlord.tayl.TailHandler;
import org.agahlord.tayl.reader.JavaTailReader;

/**
 *
 * @author john.vasquez
 */
public class Tester extends javax.swing.JFrame implements TailHandler {
    
    private JavaTailReader reader;
    private Writer writer;
    private Thread writerThread;
    
    /**
     * Creates new form Tester
     */
    public Tester() {
        initComponents();
                
        cbxWriter.setModel(new DefaultComboBoxModel(
                new String[] { 
                    "Java Util Logging", 
                    "Logback" 
                }));
        
        txtConsole.setEditable(false);
        DefaultCaret caret = (DefaultCaret)txtConsole.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        btnStop = new javax.swing.JButton();
        btnStart = new javax.swing.JButton();
        cbxWriter = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtConsole.setColumns(20);
        txtConsole.setRows(5);
        jScrollPane1.setViewportView(txtConsole);

        btnStop.setText("Stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        cbxWriter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbxWriter, 0, 308, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStop)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStop)
                    .addComponent(btnStart)
                    .addComponent(cbxWriter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        try {
            
            writerThread.interrupt();
            reader.stopReading();
            
            btnStart.setEnabled(true);
            btnStop.setEnabled(false);
            
        } catch( IOException ex ) {
            this.exceptionThrown(ex);
        }
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        try {
            if( cbxWriter.getSelectedItem().equals("Java Util Logging") ) {
                writer = new JavaLoggerWriter();
            } else if( cbxWriter.getSelectedItem().equals("Logback") ) {
                writer = new LogbackWriter();
            }
            reader = new JavaTailReader(this, new TailConfiguration(writer.getFileName()));
            writerThread = new Thread(writer);

            writerThread.start();
            reader.startReading();
            
            txtConsole.setText("");
            btnStop.setEnabled(true);
            btnStart.setEnabled(false);
        } catch( IOException ex ) {
            this.exceptionThrown(ex);
        }
    }//GEN-LAST:event_btnStartActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel( javax.swing.UIManager.getSystemLookAndFeelClassName());       
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tester.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tester().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.JComboBox cbxWriter;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtConsole;
    // End of variables declaration//GEN-END:variables

    public void lineRead(String line) {
        txtConsole.append(line);
    }

    public void fileTruncated() {
        txtConsole.append("\r\n-- FILE TRUNCATED --\r\n\r\n");
    }

    public void exceptionThrown(Exception exception) {
        txtConsole.append("\r\n-- EXCEPTION: " + exception.getClass() + " --\r\n\r\n");
    }
}

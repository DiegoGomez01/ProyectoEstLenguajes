package visuales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.StringReader;
import analizadorLexico.*;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mario A. Yusti
 */
public class InterfazPrincipal extends javax.swing.JFrame {

    int p;
    String str;
    token token;
    DefaultStyledDocument documento;
    Style stypr;
    Style styid;
    Style styer;
    Style stytx;
    Style stynm;

    /**
     * Creates new form InterfazPrincipal
     */
    public InterfazPrincipal() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(this);
        this.documento = new DefaultStyledDocument();
        this.jTextPane_tx.setDocument(this.documento);
        StyleContext context = new StyleContext();
        this.stypr = context.addStyle("Palabra Reservada", null);
        this.styid = context.addStyle("Identificador", null);
        this.stynm = context.addStyle("Normal", null);
        this.stytx = context.addStyle("Texto", null);
        this.styer = context.addStyle("Error", null);
        StyleConstants.setForeground(this.stypr, Color.BLUE);
        StyleConstants.setForeground(this.styid, new Color(4, 108, 48));
        StyleConstants.setForeground(this.styer, Color.RED);
        StyleConstants.setForeground(this.stytx, new Color(255, 204, 46));
    }

    void analizarTexto() {
        this.p = 0;
        this.jTextPane_tx.setText("");
        StringReader sreader = new StringReader(this.str);
        BufferedReader breader = new BufferedReader(sreader);
        AnalizadorLexico al = new AnalizadorLexico(breader);
        do {
            try {
                if ((this.token = al.nextToken()) != null) {
                    switch (this.token.tipo) {
                        case "Palabra Reservada":
                            this.documento.insertString(p, token.token, stypr);
                            break;
                        case "Error":
                            this.documento.insertString(p, token.token, styer);
                            break;
                        case "Texto":
                            this.documento.insertString(p, token.token, stytx);
                            break;
                        case "Identificador":
                            this.documento.insertString(p, token.token, styid);
                            break;
                        default:
                            this.documento.insertString(p, token.token, null);
                            break;
                    }
                    this.p += this.token.token.length();
                }
            } catch (BadLocationException ex) {
            } catch (IOException ex) {
                Logger.getLogger(InterfazPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (this.token != null);
        try {
            this.str = "";
            sreader.close();
            breader.close();
        } catch (IOException ex) {
        }
    }

    String leerArchivo(String archivo) {
        String linea;
        String texto = "";
        try {
            BufferedReader b = new BufferedReader(new FileReader(archivo));
            while ((linea = b.readLine()) != null) {
                texto += linea + "\n";
            }
            b.close();
        } catch (IOException e) {
        }
        return texto;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_Cargar = new javax.swing.JButton();
        jButton_LogLex = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane_tx = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Versión Beta");

        jButton_Cargar.setText("Cargar Archivo");
        jButton_Cargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CargarActionPerformed(evt);
            }
        });

        jButton_LogLex.setText("Exportar Logs");
        jButton_LogLex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LogLexActionPerformed(evt);
            }
        });

        jTextPane_tx.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextPane_txKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTextPane_tx);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addComponent(jButton_Cargar)
                .addGap(96, 96, 96)
                .addComponent(jButton_LogLex)
                .addContainerGap(189, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_LogLex)
                    .addComponent(jButton_Cargar))
                .addGap(10, 10, 10))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_CargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CargarActionPerformed
        JFileChooser FileChooser = new JFileChooser();
        FileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Texto", "txt"));
        //FileChooser.setCurrentDirectory(new File("C:\\users\\%userprofile%\\documents"));
        FileChooser.setDialogTitle("Elija el Archivo de texto");
        FileChooser.showOpenDialog(null);
        this.str = leerArchivo(FileChooser.getSelectedFile() + "");
        analizarTexto();
    }//GEN-LAST:event_jButton_CargarActionPerformed

    private void jButton_LogLexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LogLexActionPerformed
        this.str = this.jTextPane_tx.getText().replaceAll("\r", "");
        StringReader sreader = new StringReader(this.str);
        BufferedReader breader = new BufferedReader(sreader);
        AnalizadorLexico al = new AnalizadorLexico(breader);
        try {
            do {
                try {
                    this.token = al.nextToken();
                } catch (IOException ex) {
                }
            } while (this.token != null);
            al.exportarLog();
            sreader.close();
            breader.close();
            JOptionPane.showMessageDialog(null, "Los tokens se exportaron exitosamente", "Exito!!", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(InterfazPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_LogLexActionPerformed

    private void jTextPane_txKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextPane_txKeyReleased
        if (!evt.isActionKey() && evt.getKeyCode() != 32 && evt.getKeyCode() != 10) {
            int cp = this.jTextPane_tx.getCaretPosition();
            this.str = this.jTextPane_tx.getText().replaceAll("\r", "");
            analizarTexto();
            if (cp < this.jTextPane_tx.getText().length()) {
                this.jTextPane_tx.setCaretPosition(cp);
            }
        }
    }//GEN-LAST:event_jTextPane_txKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cargar;
    private javax.swing.JButton jButton_LogLex;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane_tx;
    // End of variables declaration//GEN-END:variables
}

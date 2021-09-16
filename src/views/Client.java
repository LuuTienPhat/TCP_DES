/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Phat
 */
public class Client extends javax.swing.JFrame {

    private String host = "localhost";
    private int port = 9999;
    Socket client;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    String fileTextPath = "", fileSecketKeyPath = "";

    /**
     * Creates new form Client
     *
     * @throws java.io.IOException
     */
    public Client() throws IOException {
        initComponents();
        client = new Socket(host, port);
        dataInputStream = new DataInputStream(client.getInputStream());
        dataOutputStream = new DataOutputStream(client.getOutputStream());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        textFile = new javax.swing.JTextField();
        chooseTextFilePath = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        keyFile = new javax.swing.JTextField();
        chooseKeyFilePath = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        result = new javax.swing.JTextArea();
        doDecryption = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Chọn đường dẫn file mã hóa");

        chooseTextFilePath.setText("...");
        chooseTextFilePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseTextFilePathActionPerformed(evt);
            }
        });

        jLabel2.setText("Chọn đường dẫn đến file Key");

        chooseKeyFilePath.setText("...");
        chooseKeyFilePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseKeyFilePathActionPerformed(evt);
            }
        });

        jLabel3.setText("Kết quả");

        result.setColumns(20);
        result.setRows(5);
        jScrollPane1.setViewportView(result);

        doDecryption.setText("Giải mã");
        doDecryption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doDecryptionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(keyFile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(textFile, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chooseTextFilePath)
                            .addComponent(chooseKeyFilePath)))
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(doDecryption))
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseTextFilePath))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(keyFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseKeyFilePath))
                .addGap(18, 18, 18)
                .addComponent(doDecryption)
                .addGap(45, 45, 45)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doDecryptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doDecryptionActionPerformed
        if (!checkDir()) {
            JOptionPane.showMessageDialog(null, "Xin hãy kiểm tra dữ liệu đầu vào");
        } else {
            try {
                // TODO add your handling code here:
                dataOutputStream.writeUTF(textFile.getText());
                result.setText(dataInputStream.readUTF());

            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_doDecryptionActionPerformed

    private Boolean checkDir() {
        if (textFile.getText().isEmpty()) {
            return false;
        } else if (keyFile.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private String openFileChooser(JTextField textField) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle("Chọn file");
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            fileTextPath = fileChooser.getSelectedFile().getAbsolutePath();
            textFile.setText(fileChooser.getSelectedFile().getName());
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return "";
    }

    private void chooseTextFilePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseTextFilePathActionPerformed
        // TODO add your handling code here:
        fileTextPath = openFileChooser(textFile);
    }//GEN-LAST:event_chooseTextFilePathActionPerformed

    private void chooseKeyFilePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseKeyFilePathActionPerformed
        // TODO add your handling code here:
        fileSecketKeyPath = openFileChooser(textFile);
    }//GEN-LAST:event_chooseKeyFilePathActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Client().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseKeyFilePath;
    private javax.swing.JButton chooseTextFilePath;
    private javax.swing.JButton doDecryption;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField keyFile;
    private javax.swing.JTextArea result;
    private javax.swing.JTextField textFile;
    // End of variables declaration//GEN-END:variables
}

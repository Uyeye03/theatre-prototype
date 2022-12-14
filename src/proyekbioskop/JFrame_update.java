/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyekbioskop;



import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;

/**
 *
 * @author Clarissa Angelia
 */
public class JFrame_update extends javax.swing.JFrame {
    String filename;
    /**
     * Creates new form JFrame_update
     */
    public JFrame_update() {
        initComponents();
        
        //date format dd/mm/yy
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        jLabel_tanggal.setText(String.valueOf(dtf.format(now)));
        
        //date format yyyy-mm-dd
//        jLabel_tanggal.setText(String.valueOf(java.time.LocalDate.now()));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_tanggal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField_judul = new javax.swing.JTextField();
        jComboBox_theater = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_sinopsis = new javax.swing.JTextArea();
        jButton_upload = new javax.swing.JButton();
        jLabel_poster = new javax.swing.JLabel();
        jButton_update = new javax.swing.JButton();
        jButton_kembali = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(790, 550));
        setMinimumSize(new java.awt.Dimension(790, 550));
        setPreferredSize(new java.awt.Dimension(790, 550));
        getContentPane().setLayout(null);

        jLabel_tanggal.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel_tanggal.setForeground(new java.awt.Color(247, 179, 44));
        jLabel_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_tanggal.setText("jLabel_tanggal");
        getContentPane().add(jLabel_tanggal);
        jLabel_tanggal.setBounds(535, 16, 225, 21);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(247, 179, 44));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("UPDATE FILM");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 55, 775, 54);

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(247, 179, 44));
        jLabel2.setText("THEATER");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(48, 130, 63, 21);

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 179, 44));
        jLabel3.setText("JUDUL FILM");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(48, 175, 84, 21);

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(247, 179, 44));
        jLabel4.setText("POSTER FILM");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(48, 219, 95, 21);

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(247, 179, 44));
        jLabel5.setText("SINOPSIS FILM");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(48, 262, 130, 21);

        jTextField_judul.setBackground(new java.awt.Color(247, 179, 44));
        jTextField_judul.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jTextField_judul.setForeground(new java.awt.Color(102, 0, 0));
        jTextField_judul.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.orange, java.awt.Color.orange));
        jTextField_judul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_judulActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_judul);
        jTextField_judul.setBounds(190, 172, 290, 25);

        jComboBox_theater.setBackground(new java.awt.Color(247, 179, 44));
        jComboBox_theater.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jComboBox_theater.setForeground(new java.awt.Color(102, 0, 0));
        jComboBox_theater.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "THEATER 1", "THEATER 2", "THEATER 3", "THEATER 4" }));
        jComboBox_theater.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jComboBox_theater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_theaterActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox_theater);
        jComboBox_theater.setBounds(190, 127, 125, 36);

        jTextArea_sinopsis.setBackground(new java.awt.Color(247, 179, 44));
        jTextArea_sinopsis.setColumns(20);
        jTextArea_sinopsis.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jTextArea_sinopsis.setForeground(new java.awt.Color(102, 0, 0));
        jTextArea_sinopsis.setLineWrap(true);
        jTextArea_sinopsis.setRows(5);
        jTextArea_sinopsis.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.orange, java.awt.Color.orange));
        jScrollPane1.setViewportView(jTextArea_sinopsis);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(190, 262, 290, 160);

        jButton_upload.setBackground(new java.awt.Color(247, 179, 44));
        jButton_upload.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jButton_upload.setForeground(new java.awt.Color(102, 0, 0));
        jButton_upload.setText("UPLOAD");
        jButton_upload.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_uploadActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_upload);
        jButton_upload.setBounds(190, 215, 125, 27);

        jLabel_poster.setBackground(new java.awt.Color(253, 246, 176));
        jLabel_poster.setForeground(new java.awt.Color(247, 179, 44));
        jLabel_poster.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_poster.setText("jLabel_poster");
        getContentPane().add(jLabel_poster);
        jLabel_poster.setBounds(521, 127, 212, 295);

        jButton_update.setBackground(new java.awt.Color(247, 179, 44));
        jButton_update.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jButton_update.setForeground(new java.awt.Color(102, 0, 0));
        jButton_update.setText("UPDATE");
        jButton_update.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_updateActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_update);
        jButton_update.setBounds(350, 440, 130, 45);

        jButton_kembali.setBackground(new java.awt.Color(247, 179, 44));
        jButton_kembali.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jButton_kembali.setForeground(new java.awt.Color(102, 0, 0));
        jButton_kembali.setText("KEMBALI");
        jButton_kembali.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_kembaliActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_kembali);
        jButton_kembali.setBounds(15, 16, 120, 40);

        jPanel1.setBackground(new java.awt.Color(102, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 550));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 550));
        jPanel1.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(247, 179, 44));
        jPanel1.add(jLabel6);
        jLabel6.setBounds(330, 220, 150, 20);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 780, 510);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_judulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_judulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_judulActionPerformed

    private void jButton_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_updateActionPerformed
         if("".equals(jComboBox_theater.getSelectedItem().toString())){
            JOptionPane.showMessageDialog(null, "Tidak ada perubahan data");
        }else{
            System.out.println(jComboBox_theater.getSelectedIndex());
            // TODO add your handling code here:

            String judul = jTextField_judul.getText();
            String theater  = (String) jComboBox_theater.getSelectedItem();
            theater = theater.substring(theater.lastIndexOf(" ") + 1);
            String sinop = jTextArea_sinopsis.getText();

            try{
                String newpath = "asset/";
                File directory = new File(newpath);
                if(!directory.exists()){
                    directory.mkdirs();
                }
                File fileawal = null;
                File fileakhir = null;
                String ext = this.filename.substring(filename.lastIndexOf('.'));
                fileawal = new File(filename);
                fileakhir = new File(newpath+judul+ext);
    //            System.out.println(theater);

                if(filename!= "" && judul != "" && sinop != "" && theater != ""){
                    Connection conn;
                    ResultSet rs;
                    PreparedStatement pst;
                    String url = "jdbc:mysql://localhost:3306/bioskop_ndut";
                    try{
                        //setup driver
                        Class.forName("com.mysql.jdbc.Driver");
                    } catch(ClassNotFoundException ex){
                        System.out.println("mysql driver not found");
                    }
                    try{
                        //database:bioskop_ndut, user:root, pwd:None
                        conn = DriverManager.getConnection(url, "root", "");
                        //create query
                        String query = "UPDATE theatre SET judul=?, sinopsis=?, poster=? WHERE id_theatre="+theater;

                        //open the statement if no condition
            //            Statement st = conn.createStatement();

                        //handle result if no condition
                        //rs = st.executeQuery(query);

                        //set Statement
                        pst = conn.prepareStatement(query);
                        pst.setString(1, judul);
                        pst.setString(2, sinop);                
                        pst.setString(3, fileakhir.toString());

            //            System.out.println(query);
            //            System.out.println(pst);

                        //handle the results

                        int done = pst.executeUpdate();
                        if(done >= 1){
                            dispose();
                            Files.copy(fileawal.toPath(), fileakhir.toPath());   
                            JFrame_Admin admin = new JFrame_Admin();
                            admin.setVisible(true);
                            JOptionPane.showMessageDialog(this, "Berhasil Update!");
                        }else{
                            //create query
                            String query2 = "INSERT INTO theatre VALUES (?,?, ?, ?, ?, ?, ?, ?)";
                            
                            //open the statement if no condition
                //           Statement st = conn.createStatement();

                            //handle result if no condition
                            //rs = st.executeQuery(query);

                            //set Statement
                            pst = conn.prepareStatement(query2);
                            pst.setInt(1, Integer.parseInt(theater));
                            pst.setString(2, judul);
                            pst.setString(3, sinop);                         
                            pst.setInt(4, 9);                
                            pst.setInt(5, 9);                
                            pst.setInt(6, 30000);                        
                            pst.setInt(7, 50000);                
                            pst.setString(8, fileakhir.toString());

                            int done2 = pst.executeUpdate();
                            if(done2 >= 1){
                                dispose();
                                Files.copy(fileawal.toPath(), fileakhir.toPath());   
                                JFrame_Admin admin = new JFrame_Admin();
                                admin.setVisible(true);
                                JOptionPane.showMessageDialog(this, "Berhasil Menambahkan Data!");  

                            }
                        }

                    }catch (SQLException ex){
                        System.out.println("not connected");
                        System.out.println(ex);

                    }
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }       
    }//GEN-LAST:event_jButton_updateActionPerformed

    private void jButton_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_kembaliActionPerformed
        // TODO add your handling code here:
        
        JFrame_Admin admin = new JFrame_Admin();
        admin.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_jButton_kembaliActionPerformed

    private void jButton_uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_uploadActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Choose Image File");
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnval = fc.showOpenDialog(this);
        if(returnval == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            BufferedImage bi;

            try{
                // display image in JLable
                bi = ImageIO.read(file);
                ImageIcon icon = new ImageIcon(bi);
                Image img = icon.getImage().getScaledInstance(jLabel_poster.getWidth(), jLabel_poster.getHeight(), Image.SCALE_DEFAULT);
                jLabel_poster.setIcon(new ImageIcon(img));
                filename = file.getAbsolutePath();
                jLabel6.setText(filename);

            }catch(Exception e){
                e.printStackTrace();
            }
            this.pack();
        }
        
            
    }//GEN-LAST:event_jButton_uploadActionPerformed

    private void jComboBox_theaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_theaterActionPerformed
        // TODO add your handling code here:
        jComboBox_theater.removeItem("");
        String theater = jComboBox_theater.getSelectedItem().toString();
        theater = theater.substring(theater.lastIndexOf(" ") + 1);
        
        Connection conn;
        ResultSet rs;
        PreparedStatement pst;
        String url = "jdbc:mysql://localhost:3306/bioskop_ndut";
        try{
            //setup driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ex){
            System.out.println("mysql driver not found");
        }
        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=?";

            //open the statement if no condition
//            Statement st = conn.createStatement();

            //handle result if no condition
            //rs = st.executeQuery(query);

            //set Statement
            pst = conn.prepareStatement(query);
            pst.setInt(1,Integer.parseInt(theater));
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                jTextField_judul.setText(rs.getString("judul"));
                jTextArea_sinopsis.setText(rs.getString("sinopsis"));
                
                ImageIcon icon = new ImageIcon(rs.getString("poster"));
                Image img = icon.getImage().getScaledInstance(jLabel_poster.getWidth(), jLabel_poster.getHeight(), Image.SCALE_DEFAULT);
                jLabel_poster.setIcon(new ImageIcon(img));
                jLabel6.setText(rs.getString("poster"));
                
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox_theaterActionPerformed

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
            java.util.logging.Logger.getLogger(JFrame_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_update.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame_update().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_kembali;
    private javax.swing.JButton jButton_update;
    private javax.swing.JButton jButton_upload;
    private javax.swing.JComboBox<String> jComboBox_theater;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_poster;
    private javax.swing.JLabel jLabel_tanggal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_sinopsis;
    private javax.swing.JTextField jTextField_judul;
    // End of variables declaration//GEN-END:variables
}

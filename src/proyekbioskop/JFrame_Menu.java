/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyekbioskop;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Clarissa Angelia
 */
public class JFrame_Menu extends javax.swing.JFrame {

    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    String url = "jdbc:mysql://localhost:3306/bioskop_ndut";

    /**
     * Creates new form JFrame_Menu
     */
    public JFrame_Menu() {
        initComponents();
        
        jButton_film1.setText("");
        jButton_film2.setText("");
        jButton_film3.setText("");
        jButton_film4.setText("");
        //date format dd/mm/yy
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        jLabel_tanggal.setText(String.valueOf(dtf.format(now)));
        
        //date format yyyy-mm-dd
//        jLabel_tanggal.setText(String.valueOf(java.time.LocalDate.now()));

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
            String query = "SELECT * FROM theatre";
            
            //set Statement
            pst = conn.prepareStatement(query);
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            //to generate image for home poster
            int count = 0;
            while(rs.next()){
                //Scale image
                ImageIcon film1 = new ImageIcon(rs.getString("poster"));
                Image temp = film1.getImage();
                Image imgscale1 = temp.getScaledInstance(jButton_film1.getWidth(), jButton_film1.getHeight(), Image.SCALE_DEFAULT);
                ImageIcon scale1 = new ImageIcon(imgscale1);


                //set condition
                if(count==0){
                    jButton_film1.setIcon(scale1);
                    jLabel_film1.setText(rs.getString("judul"));
                }
                if(count==1){
                    jButton_film2.setIcon(scale1);
                    jLabel_film2.setText(rs.getString("judul"));

                }
                if(count==2){
                    jButton_film3.setIcon(scale1);
                    jLabel_film3.setText(rs.getString("judul"));
                }
                if(count==3){
                    jButton_film4.setIcon(scale1);
                    jLabel_film4.setText(rs.getString("judul"));
                }
                count+=1;
            }
                
        }catch (Exception ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }
        
        
        
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
        jButton_admin = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton_film4 = new javax.swing.JButton();
        jLabel_film2 = new javax.swing.JLabel();
        jLabel_film1 = new javax.swing.JLabel();
        jButton_film1 = new javax.swing.JButton();
        jButton_film2 = new javax.swing.JButton();
        jLabel_film4 = new javax.swing.JLabel();
        jLabel_film3 = new javax.swing.JLabel();
        jButton_film3 = new javax.swing.JButton();
        jButton_booking = new javax.swing.JButton();
        jLabel_bioskop = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(815, 589));
        setMinimumSize(new java.awt.Dimension(815, 589));
        setPreferredSize(new java.awt.Dimension(815, 565));
        getContentPane().setLayout(null);

        jLabel_tanggal.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel_tanggal.setForeground(new java.awt.Color(247, 179, 44));
        jLabel_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_tanggal.setText("jLabel_tanggal");
        getContentPane().add(jLabel_tanggal);
        jLabel_tanggal.setBounds(534, 16, 251, 21);

        jButton_admin.setBackground(new java.awt.Color(247, 179, 44));
        jButton_admin.setFont(new java.awt.Font("Tw Cen MT", 0, 24)); // NOI18N
        jButton_admin.setForeground(new java.awt.Color(102, 0, 0));
        jButton_admin.setText("ADMIN");
        jButton_admin.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_adminActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_admin);
        jButton_admin.setBounds(15, 484, 140, 50);

        jPanel1.setBackground(new java.awt.Color(102, 0, 0));
        jPanel1.setLayout(null);

        jButton_film4.setForeground(new java.awt.Color(247, 179, 44));
        jButton_film4.setText("poster film 4");
        jButton_film4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_film4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_film4);
        jButton_film4.setBounds(590, 130, 165, 225);

        jLabel_film2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel_film2.setForeground(new java.awt.Color(247, 179, 44));
        jLabel_film2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_film2.setText("film 2");
        jPanel1.add(jLabel_film2);
        jLabel_film2.setBounds(232, 370, 160, 21);

        jLabel_film1.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel_film1.setForeground(new java.awt.Color(247, 179, 44));
        jLabel_film1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_film1.setText("film 1");
        jPanel1.add(jLabel_film1);
        jLabel_film1.setBounds(52, 370, 160, 21);

        jButton_film1.setForeground(new java.awt.Color(247, 179, 44));
        jButton_film1.setText("poster film 1");
        jButton_film1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_film1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_film1);
        jButton_film1.setBounds(50, 130, 165, 225);

        jButton_film2.setForeground(new java.awt.Color(247, 179, 44));
        jButton_film2.setText("poster film 2");
        jButton_film2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_film2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_film2);
        jButton_film2.setBounds(230, 130, 165, 225);

        jLabel_film4.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel_film4.setForeground(new java.awt.Color(247, 179, 44));
        jLabel_film4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_film4.setText("film 4");
        jPanel1.add(jLabel_film4);
        jLabel_film4.setBounds(592, 370, 160, 21);

        jLabel_film3.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel_film3.setForeground(new java.awt.Color(247, 179, 44));
        jLabel_film3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_film3.setText("film 3");
        jPanel1.add(jLabel_film3);
        jLabel_film3.setBounds(412, 370, 160, 21);

        jButton_film3.setForeground(new java.awt.Color(247, 179, 44));
        jButton_film3.setText("poster film 3");
        jButton_film3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_film3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_film3);
        jButton_film3.setBounds(410, 130, 165, 225);

        jButton_booking.setBackground(new java.awt.Color(247, 179, 44));
        jButton_booking.setFont(new java.awt.Font("Tw Cen MT", 1, 36)); // NOI18N
        jButton_booking.setForeground(new java.awt.Color(102, 0, 0));
        jButton_booking.setText("BOOKING");
        jButton_booking.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_booking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bookingActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_booking);
        jButton_booking.setBounds(250, 410, 300, 70);

        jLabel_bioskop.setFont(new java.awt.Font("Tw Cen MT", 0, 48)); // NOI18N
        jLabel_bioskop.setForeground(new java.awt.Color(247, 179, 44));
        jLabel_bioskop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_bioskop.setText("BIOSKOP MINI");
        jPanel1.add(jLabel_bioskop);
        jLabel_bioskop.setBounds(0, 50, 800, 54);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 800, 550);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_adminActionPerformed
        // TODO add your handling code here:
        
        JFrame_Login login = new JFrame_Login();
        login.setVisible(true);
        
    }//GEN-LAST:event_jButton_adminActionPerformed

    private void jButton_bookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_bookingActionPerformed
        // TODO add your handling code here:
        
        JFrame_Booking booking = new JFrame_Booking();
        booking.setVisible(true);
        
    }//GEN-LAST:event_jButton_bookingActionPerformed

    private void jButton_film1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_film1ActionPerformed
        // TODO add your handling code here:
        
        
        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=? ";
            
            //open the statement if no condition
            //Statement st = conn.createStatement();
            
            //handle result if no condition
            //rs = st.executeQuery(query);
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1, "1");
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                String imageref = rs.getString("poster");
                String judul = rs.getString("judul");
                String sinop = rs.getString("sinopsis");
                JFrame_DetailFilm df = new JFrame_DetailFilm();
                df.setAtt(judul, imageref, sinop, 1);
                
                df.setVisible(true);            
            } else {
            JOptionPane.showMessageDialog(this, "Error Occured!");
            }
        }catch (SQLException ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }

    }//GEN-LAST:event_jButton_film1ActionPerformed

    private void jButton_film2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_film2ActionPerformed
        // TODO add your handling code here:

       try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=? ";
            
            //open the statement if no condition
            //Statement st = conn.createStatement();
            
            //handle result if no condition
            //rs = st.executeQuery(query);
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1, "2");
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                String imageref = rs.getString("poster");
                String judul = rs.getString("judul");
                String sinop = rs.getString("sinopsis");
                JFrame_DetailFilm df = new JFrame_DetailFilm();
                df.setAtt(judul, imageref, sinop, 2);
                
                df.setVisible(true);            
            } else {
            JOptionPane.showMessageDialog(this, "Error Occured!");
            }
        }catch (SQLException ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }

    }//GEN-LAST:event_jButton_film2ActionPerformed

    private void jButton_film3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_film3ActionPerformed
        // TODO add your handling code here:

        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=? ";
            
            //open the statement if no condition
            //Statement st = conn.createStatement();
            
            //handle result if no condition
            //rs = st.executeQuery(query);
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1, "3");
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                String imageref = rs.getString("poster");
                String judul = rs.getString("judul");
                String sinop = rs.getString("sinopsis");
                JFrame_DetailFilm df = new JFrame_DetailFilm();
                df.setAtt(judul, imageref, sinop, 3);
                
                df.setVisible(true);            
            } else {
            JOptionPane.showMessageDialog(this, "Error Occured!");
            }
        }catch (SQLException ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }

        

    }//GEN-LAST:event_jButton_film3ActionPerformed

    private void jButton_film4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_film4ActionPerformed
        // TODO add your handling code here:

        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=? ";
            
            //open the statement if no condition
            //Statement st = conn.createStatement();
            
            //handle result if no condition
            //rs = st.executeQuery(query);
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1, "4");
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                String imageref = rs.getString("poster");
                String judul = rs.getString("judul");
                String sinop = rs.getString("sinopsis");
                JFrame_DetailFilm df = new JFrame_DetailFilm();
                df.setAtt(judul, imageref, sinop, 4);
                
                df.setVisible(true);            
            } else {
            JOptionPane.showMessageDialog(this, "Error Occured!");
            }
        }catch (SQLException ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }

    }//GEN-LAST:event_jButton_film4ActionPerformed

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
            java.util.logging.Logger.getLogger(JFrame_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame_Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_admin;
    private javax.swing.JButton jButton_booking;
    private javax.swing.JButton jButton_film1;
    private javax.swing.JButton jButton_film2;
    private javax.swing.JButton jButton_film3;
    private javax.swing.JButton jButton_film4;
    private javax.swing.JLabel jLabel_bioskop;
    private javax.swing.JLabel jLabel_film1;
    private javax.swing.JLabel jLabel_film2;
    private javax.swing.JLabel jLabel_film3;
    private javax.swing.JLabel jLabel_film4;
    private javax.swing.JLabel jLabel_tanggal;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

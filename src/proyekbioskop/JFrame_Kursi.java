/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyekbioskop;

import com.mysql.jdbc.Util;
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Clarissa Angelia
 */
public class JFrame_Kursi extends javax.swing.JFrame {
    int seats[][];
    String taken; //berisi array2d dr index kursi taken
    int total;
    Calendar c;
    Connection conn;
    ResultSet rs;
    PreparedStatement pst;
    String url = "jdbc:mysql://localhost:3306/bioskop_ndut";
    
    
            String query2 = "SELECT * FROM transaksi WHERE theater=? and tanggal=?";
    
    /**
     * Creates new form JFrame_Kursi
     */
    public JFrame_Kursi() {
        initComponents();
        seats = new int[3][3];
        
        c = Calendar.getInstance();
        
        //date format dd/mm/yy
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        jLabel_tanggal.setText(dtf.format(now));
        
        //set disabled text color to red
        UIManager.put("Button.disabledText", new Color(102, 0, 0));
        repaint();
        
        
        
        //date format yyyy-mm-dd
//        jLabel_tanggal.setText(String.valueOf(java.time.LocalDate.now()));
        
        try{
            //setup driver
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ex){
            System.out.println("mysql driver not found");
        }
        
        
    }

    public void setSeats(){
        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM transaksi WHERE theater=? AND tanggal=? AND jam=?";
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1,jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));
           
            java.util.Date javaDate = new java.util.Date();
            java.sql.Date sqldate = new java.sql.Date(javaDate.getTime());
            pst.setDate(2, sqldate);
            pst.setString(3,jLabel8.getText().substring(jLabel8.getText().indexOf(":") + 2) );
//            System.out.println(sqldate);
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                String seat = rs.getString("seat_taken");
//                System.out.println(seat);
                int charindex = 0;
                for (int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                      seats[i][j] = Integer.parseInt(String.valueOf(seat.charAt(charindex)));
//                      System.out.println(i + "_" + j + "= " + seats[i][j]);
//                      System.out.println();      
                      charindex++;
                    }
                }
              
                
            }else{
                query = "INSERT INTO transaksi VALUES (default, ?, ?, ?, ?, ?, ?, ?)";
                pst = conn.prepareStatement(query);
                pst.setString(1,jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));            
                pst.setDate(2, sqldate);
                pst.setString(3, jLabel10.getText().substring(jLabel10.getText().indexOf(":") + 2));
                pst.setString(4, jLabel8.getText().substring(jLabel8.getText().indexOf(":") + 2));
                pst.setInt(5, 0);
                pst.setInt(6, 0);
                pst.setString(7, "000000000");
                
                pst.executeUpdate();
                
            }
            
//           set seat color
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    
                    if(i == 0 && j == 0 && seats[i][j] == 2){
                        jButton_a1.setBackground(new java.awt.Color(189,143,77)); 
                        jButton_a1.setForeground(new java.awt.Color(128,66,66));
                        jButton_a1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
                        jButton_a1.setEnabled(false);
                        
                    }
                    if(i == 0 && j == 1 && seats[i][j] == 2){
                        jButton_a2.setBackground(new java.awt.Color(189,143,77));
                        jButton_a2.setForeground(new java.awt.Color(128,66,66));
                        jButton_a2.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
                        jButton_a2.setEnabled(false);
                    }
                    if(i == 0 && j == 2 && seats[i][j] == 2){
                        jButton_a3.setBackground(new java.awt.Color(189,143,77));
                        jButton_a3.setForeground(new java.awt.Color(128,66,66));
                        jButton_a3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
                        jButton_a3.setEnabled(false);
                    }
                    if(i == 1 && j == 0 && seats[i][j] == 2){
                        jButton_b1.setBackground(new java.awt.Color(189,143,77));
                        jButton_b1.setForeground(new java.awt.Color(128,66,66));
                        jButton_b1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
                        jButton_b1.setEnabled(false);
                    }
                    if(i == 1 && j == 1 && seats[i][j] == 2){
                        jButton_b2.setBackground(new java.awt.Color(189,143,77));
                        jButton_b2.setForeground(new java.awt.Color(128,66,66));
                        jButton_b2.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
                        jButton_b2.setEnabled(false);
                    }
                    if(i == 1 && j == 2 && seats[i][j] == 2){
                        jButton_b3.setBackground(new java.awt.Color(189,143,77));
                        jButton_b3.setForeground(new java.awt.Color(128,66,66));
                        jButton_b3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
                        jButton_b3.setEnabled(false);
                    }
                    if(i == 2 && j == 0 && seats[i][j] == 2){
                        jButton_c1.setBackground(new java.awt.Color(189,143,77));
                        jButton_c1.setForeground(new java.awt.Color(128,66,66));
                        jButton_c1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
                        jButton_c1.setEnabled(false);
                    }
                    if(i == 2 && j == 1 && seats[i][j] == 2){
                        jButton_c2.setBackground(new java.awt.Color(189,143,77));
                        jButton_c2.setForeground(new java.awt.Color(128,66,66));
                        jButton_c2.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
                        jButton_c2.setEnabled(false);
                    }
                    if(i == 2 && j == 2 && seats[i][j] == 2){
                        jButton_c3.setBackground(new java.awt.Color(189,143,77));
                        jButton_c3.setForeground(new java.awt.Color(128,66,66));
                        jButton_c3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
                        jButton_c3.setEnabled(false);
                    }
                }
            }
                
        }catch (Exception ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }
    }
    public void setTitle(String text, String teater){
        String jam = text.substring(text.lastIndexOf("-") + 1);
        String judul = text.substring(0, text.lastIndexOf("-"));
        
        jLabel10.setText("Judul Film : " + judul);
        jLabel3.setText(teater);
        jLabel9.setText("Theater : " + jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));
        jLabel8.setText("Jam Tayang : " + jam);
        
        setSeats();
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
        jButton_c1 = new javax.swing.JButton();
        jButton_c2 = new javax.swing.JButton();
        jButton_c3 = new javax.swing.JButton();
        jButton_b1 = new javax.swing.JButton();
        jButton_b2 = new javax.swing.JButton();
        jButton_b3 = new javax.swing.JButton();
        jButton_a1 = new javax.swing.JButton();
        jButton_a2 = new javax.swing.JButton();
        jButton_a3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton_bayar = new javax.swing.JButton();
        jButton_kembali = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(690, 565));
        getContentPane().setLayout(null);

        jLabel_tanggal.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel_tanggal.setForeground(new java.awt.Color(247, 179, 44));
        jLabel_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_tanggal.setText("jLabel_tanggal");
        getContentPane().add(jLabel_tanggal);
        jLabel_tanggal.setBounds(435, 16, 225, 21);

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(247, 179, 44));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SCREEN");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(247, 179, 44), 4));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 50, 600, 30);

        jButton_c1.setBackground(new java.awt.Color(247, 179, 44));
        jButton_c1.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jButton_c1.setForeground(new java.awt.Color(102, 0, 0));
        jButton_c1.setText("C1");
        jButton_c1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 200, 0), new java.awt.Color(255, 200, 0), new java.awt.Color(255, 200, 0), new java.awt.Color(255, 200, 0)));
        jButton_c1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_c1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_c1);
        jButton_c1.setBounds(134, 94, 75, 60);

        jButton_c2.setBackground(new java.awt.Color(247, 179, 44));
        jButton_c2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jButton_c2.setForeground(new java.awt.Color(102, 0, 0));
        jButton_c2.setText("C2");
        jButton_c2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_c2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_c2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_c2);
        jButton_c2.setBounds(312, 94, 75, 60);

        jButton_c3.setBackground(new java.awt.Color(247, 179, 44));
        jButton_c3.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jButton_c3.setForeground(new java.awt.Color(102, 0, 0));
        jButton_c3.setText("C3");
        jButton_c3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_c3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_c3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_c3);
        jButton_c3.setBounds(487, 94, 75, 60);

        jButton_b1.setBackground(new java.awt.Color(247, 179, 44));
        jButton_b1.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jButton_b1.setForeground(new java.awt.Color(102, 0, 0));
        jButton_b1.setText("B1");
        jButton_b1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_b1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_b1);
        jButton_b1.setBounds(134, 163, 75, 60);

        jButton_b2.setBackground(new java.awt.Color(247, 179, 44));
        jButton_b2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jButton_b2.setForeground(new java.awt.Color(102, 0, 0));
        jButton_b2.setText("B2");
        jButton_b2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_b2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_b2);
        jButton_b2.setBounds(312, 163, 75, 60);

        jButton_b3.setBackground(new java.awt.Color(247, 179, 44));
        jButton_b3.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jButton_b3.setForeground(new java.awt.Color(102, 0, 0));
        jButton_b3.setText("B3");
        jButton_b3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_b3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_b3);
        jButton_b3.setBounds(487, 163, 75, 60);

        jButton_a1.setBackground(new java.awt.Color(247, 179, 44));
        jButton_a1.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jButton_a1.setForeground(new java.awt.Color(102, 0, 0));
        jButton_a1.setText("A1");
        jButton_a1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_a1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_a1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_a1);
        jButton_a1.setBounds(134, 232, 75, 60);

        jButton_a2.setBackground(new java.awt.Color(247, 179, 44));
        jButton_a2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jButton_a2.setForeground(new java.awt.Color(102, 0, 0));
        jButton_a2.setText("A2");
        jButton_a2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_a2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_a2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_a2);
        jButton_a2.setBounds(312, 232, 75, 60);

        jButton_a3.setBackground(new java.awt.Color(247, 179, 44));
        jButton_a3.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jButton_a3.setForeground(new java.awt.Color(102, 0, 0));
        jButton_a3.setText("A3");
        jButton_a3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_a3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_a3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_a3);
        jButton_a3.setBounds(487, 232, 75, 60);

        jPanel1.setBackground(new java.awt.Color(247, 179, 44));

        jButton_bayar.setBackground(new java.awt.Color(102, 0, 0));
        jButton_bayar.setForeground(new java.awt.Color(247, 179, 44));
        jButton_bayar.setText("BAYAR");
        jButton_bayar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.green, java.awt.Color.green, java.awt.Color.green, java.awt.Color.green));
        jButton_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bayarActionPerformed(evt);
            }
        });

        jButton_kembali.setBackground(new java.awt.Color(102, 0, 0));
        jButton_kembali.setForeground(new java.awt.Color(247, 179, 44));
        jButton_kembali.setText("KEMBALI");
        jButton_kembali.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.green, java.awt.Color.green, java.awt.Color.green, java.awt.Color.green));
        jButton_kembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_kembaliActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 0));
        jLabel4.setText("Total Pemesanan");

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 0));
        jLabel5.setText("Lokasi Kursi :");

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 0));
        jLabel7.setText("Jumlah Kursi :");

        jLabel8.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 0));
        jLabel8.setText("Jam Tayang :");

        jLabel9.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 0));
        jLabel9.setText("Theater :");

        jLabel10.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 0, 0));
        jLabel10.setText("Judul Film :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jButton_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 308, 675, 270);

        jPanel2.setBackground(new java.awt.Color(102, 0, 0));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 179, 44));
        jLabel3.setText("jLabel3");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(30, 10, 180, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(-4, -2, 680, 530);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_kembaliActionPerformed
        // TODO add your handling code here:
        
        JFrame_Booking booking = new JFrame_Booking();
        booking.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_jButton_kembaliActionPerformed

    private void jButton_c1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_c1ActionPerformed
        // TODO add your handling code here:
        
        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=?";
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1,jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                if(seats[2][0] == 1){
                    int input = JOptionPane.showConfirmDialog(null, "Cancel seat?", "Confirm cancel",  JOptionPane.OK_CANCEL_OPTION);
                  
                    if(input==0){
                        seats[2][0] = 0;
                        total--;
                        
                        int price;
                        int day = c.get(Calendar.DAY_OF_WEEK);
                        if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                             price = rs.getInt("weekend");
                        }else{
                            price = rs.getInt("weekday");
                        }
                    
                        jLabel7.setText("Jumlah Kursi : " + total);
                        jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
          
                        String temp = jLabel5.getText();
                        if(temp.contains(", C1")){
                            temp = temp.replace(", C1", "");
                        } else if(temp.contains(" C1, ")){
                            temp = temp.replace("C1, ", "");
                        }else{
                            temp = temp.replace(" C1", "");
                        }
                        jLabel5.setText(temp);
                        jButton_c1.setBackground(new java.awt.Color(247,179,44)); 

                    }
                }else{
                    seats[2][0] = 1;
                    String temp = jLabel5.getText();
                    System.out.println(temp);
                    //differentiate price based on days
                    int price;
                    int day = c.get(Calendar.DAY_OF_WEEK);
                    if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                         price = rs.getInt("weekend");
                    }else{
                        price = rs.getInt("weekday");
                    }

                    if("Lokasi Kursi :".equals(temp)){
                        temp += " C1";
                    }else{
                        temp += ", C1";
                    }
                    
                    jLabel5.setText(temp);
                    total++;
                    jLabel7.setText("Jumlah Kursi : " + String.valueOf(total));
                    jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
                    jButton_c1.setBackground(new java.awt.Color(199, 139, 18));                        
                
                }
            }              
                          
        }catch (Exception ex){
            System.out.println("not connected");
            System.out.println(ex);            
        }        
    }//GEN-LAST:event_jButton_c1ActionPerformed

    private void jButton_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_bayarActionPerformed
        // TODO add your handling code here:
        if("".equals(jLabel7.getText().substring(jLabel7.getText().lastIndexOf(":")+1))){
            JOptionPane.showMessageDialog(null, "Anda belum memesan kursi");
        }else if(Integer.parseInt(jLabel7.getText().substring(jLabel7.getText().lastIndexOf(":")+2)) <= 0){
            JOptionPane.showMessageDialog(null, "Anda belum memesan kursi");
        }else{
            String msg = "";
            msg += jLabel10.getText() + "\n";
            msg += jLabel9.getText() + "\n";        
            msg += jLabel8.getText() + "\n";       
            msg += jLabel7.getText() + "\n";       
            msg += jLabel5.getText() + "\n";
            msg += "==================================\n";
            msg += jLabel4.getText() + "\n";

            String seat_data="";
            int confpay = JOptionPane.showConfirmDialog(null, msg, "Confirm order",  JOptionPane.OK_CANCEL_OPTION);
            if(confpay == 0){
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        if(seats[i][j] == 1 || seats[i][j] == 2){
                            seat_data += "2";                    
                        }else{
                            seat_data += "0";
                        }
                    }
                }

                try{
                    //database:bioskop_ndut, user:root, pwd:None
                    conn = DriverManager.getConnection(url, "root", "");
                    //create query
                    String query = "SELECT * FROM transaksi WHERE theater=? AND tanggal=? AND jam=?";

                    //set Statement
                    pst = conn.prepareStatement(query);
                    pst.setString(1,jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));

                    java.util.Date javaDate = new java.util.Date();
                    java.sql.Date sqldate = new java.sql.Date(javaDate.getTime());
                    pst.setDate(2, sqldate);
                    pst.setString(3,jLabel8.getText().substring(jLabel8.getText().indexOf(":") + 2) );


        //            System.out.println(query);
    //                System.out.println(pst);

                    //handle the results
                    rs = pst.executeQuery();

                    if(rs.next()){
                        String query3 = "UPDATE transaksi SET total_pendapatan=?, total_penjualan=?, seat_taken=? WHERE theater=? AND tanggal=? AND jam=?";
                        pst = conn.prepareStatement(query3);
                        pst.setInt(1, rs.getInt("total_pendapatan") + Integer.parseInt(jLabel4.getText().substring(jLabel4.getText().lastIndexOf(" ") + 1)));
                        pst.setInt(2, rs.getInt("total_penjualan") + Integer.parseInt(jLabel7.getText().substring(jLabel7.getText().lastIndexOf(" ") + 1)));
                        pst.setString(3, seat_data);
                        pst.setString(4,jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));
                        pst.setDate(5, sqldate);
                        pst.setString(6,jLabel8.getText().substring(jLabel8.getText().indexOf(":") + 2) );

                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Selamat Menonton!");                       
                    }
                }catch (Exception ex){
                    System.out.println("not connected");
                    System.out.println(ex);

                }

            }
        }
            
        
        
        
        
        
        
    }//GEN-LAST:event_jButton_bayarActionPerformed

    private void jButton_c2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_c2ActionPerformed
        // TODO add your handling code here:
        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=?";
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1,jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                if(seats[2][1] == 1){
                    int input = JOptionPane.showConfirmDialog(null, "Cancel seat?", "Confirm cancel",  JOptionPane.OK_CANCEL_OPTION);
                    if(input==0){
                        seats[2][1] = 0;
                        total--;
                        
                        int price;
                        int day = c.get(Calendar.DAY_OF_WEEK);
                        if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                             price = rs.getInt("weekend");
                        }else{
                            price = rs.getInt("weekday");
                        }
                    
                        jLabel7.setText("Jumlah Kursi : " + total);
                        jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
          
                        String temp = jLabel5.getText();
                        
                        if(temp.contains(", C2")){
                            temp = temp.replace(", C2", "");
                        } else if(temp.contains(" C2, ")){
                            temp = temp.replace("C2,", "");
                        }else{
                            temp = temp.replace(" C2", "");
                        }
                        jLabel5.setText(temp);
                        jButton_c2.setBackground(new java.awt.Color(247,179,44)); 
                    }
                }else{
                    seats[2][1] = 1;
                    String temp = jLabel5.getText();

                    //differentiate price based on days
                    int price;
                    int day = c.get(Calendar.DAY_OF_WEEK);
                    if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                         price = rs.getInt("weekend");
                    }else{
                        price = rs.getInt("weekday");
                    }

                    if("Lokasi Kursi :".equals(temp)){
                        temp += " C2";
                    }else{
                        temp += ", C2";
                    }
                    
                    jLabel5.setText(temp);
                    total++;
                    jLabel7.setText("Jumlah Kursi : " + String.valueOf(total));
                    jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
                    jButton_c2.setBackground(new java.awt.Color(199, 139, 18));                        

                }
            }
            
            
            
                
        }catch (Exception ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }
    }//GEN-LAST:event_jButton_c2ActionPerformed

    private void jButton_c3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_c3ActionPerformed
        // TODO add your handling code here:
        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=?";
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1,jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                if(seats[2][2] == 1){
                    int input = JOptionPane.showConfirmDialog(null, "Cancel seat?", "Confirm cancel",  JOptionPane.OK_CANCEL_OPTION);
                    if(input==0){
                        seats[2][2] = 0;
                        total--;
                        
                        int price;
                        int day = c.get(Calendar.DAY_OF_WEEK);
                        if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                             price = rs.getInt("weekend");
                        }else{
                            price = rs.getInt("weekday");
                        }
                    
                        jLabel7.setText("Jumlah Kursi : " + total);
                        jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
          
                        String temp = jLabel5.getText();
                        if(temp.contains(", C3")){
                            temp = temp.replace(", C3", "");
                        } else if(temp.contains(" C3, ")){
                            temp = temp.replace("C3,", "");
                        }else{
                            temp = temp.replace(" C3", "");
                        }
                        
                        jLabel5.setText(temp);
                        jButton_c3.setBackground(new java.awt.Color(247,179,44)); 

                    }
                }else{
                    seats[2][2] = 1;
                    String temp = jLabel5.getText();

                    //differentiate price based on days
                    int price;
                    int day = c.get(Calendar.DAY_OF_WEEK);
                    if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                         price = rs.getInt("weekend");
                    }else{
                        price = rs.getInt("weekday");
                    }

                    if("Lokasi Kursi :".equals(temp)){
                        temp += " C3";
                    }else{
                        temp += ", C3";
                    }
                    
                    jLabel5.setText(temp);
                    total++;
                    jLabel7.setText("Jumlah Kursi : " + String.valueOf(total));
                    jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
                    jButton_c3.setBackground(new java.awt.Color(199, 139, 18));                        

                }
                
                
            }
            
            
            
                
        }catch (Exception ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }
    }//GEN-LAST:event_jButton_c3ActionPerformed

    private void jButton_b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_b1ActionPerformed
        // TODO add your handling code here:
        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=?";
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1,jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                if(seats[1][0] == 1){
                    int input = JOptionPane.showConfirmDialog(null, "Cancel seat?", "Confirm cancel",  JOptionPane.OK_CANCEL_OPTION);
                  
                    if(input==0){
                        seats[1][0] = 0;
                        total--;
                        
                        int price;
                        int day = c.get(Calendar.DAY_OF_WEEK);
                        if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                             price = rs.getInt("weekend");
                        }else{
                            price = rs.getInt("weekday");
                        }
                    
                        jLabel7.setText("Jumlah Kursi : " + total);
                        jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
          
                        String temp = jLabel5.getText();
                        if(temp.contains(", B1")){
                            temp = temp.replace(", B1", "");
                        } else if(temp.contains(" B1, ")){
                            temp = temp.replace("B1, ", "");
                        }else{
                            temp = temp.replace(" B1", "");
                        }
                        jLabel5.setText(temp);
                        jButton_b1.setBackground(new java.awt.Color(247,179,44));
                    }
                }else{
                    seats[1][0] = 1;
                    String temp = jLabel5.getText();

                    //differentiate price based on days
                    int price;
                    int day = c.get(Calendar.DAY_OF_WEEK);
                    if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                         price = rs.getInt("weekend");
                    }else{
                        price = rs.getInt("weekday");
                    }

                    if(temp == "Lokasi Kursi :"){
                        temp += " B1";
                    }else{
                        temp += ", B1";
                    }
                    
                    jLabel5.setText(temp);
                    total++;
                    jLabel7.setText("Jumlah Kursi : " + String.valueOf(total));
                    jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
                    jButton_b1.setBackground(new java.awt.Color(199, 139, 18));                    

                }
            }
            
            
            
                
        }catch (Exception ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }
    }//GEN-LAST:event_jButton_b1ActionPerformed

    private void jButton_b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_b2ActionPerformed
        // TODO add your handling code here:
        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=?";
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1,jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                if(seats[1][1] == 1){
                    int input = JOptionPane.showConfirmDialog(null, "Cancel seat?", "Confirm cancel",  JOptionPane.OK_CANCEL_OPTION);
                  
                    if(input==0){
                        seats[1][1] = 0;
                        total--;
                        
                        int price;
                        int day = c.get(Calendar.DAY_OF_WEEK);
                        if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                             price = rs.getInt("weekend");
                        }else{
                            price = rs.getInt("weekday");
                        }
                    
                        jLabel7.setText("Jumlah Kursi : " + total);
                        jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
          
                        String temp = jLabel5.getText();
                        if(temp.contains(", B2")){
                            temp = temp.replace(", B2", "");
                        } else if(temp.contains(" B2, ")){
                            temp = temp.replace("B2, ", "");
                        }else{
                            temp = temp.replace(" B2", "");
                        }
                        jLabel5.setText(temp);
                        jButton_b2.setBackground(new java.awt.Color(247,179,44));
                    }
                }else{
                    seats[1][1] = 1;
                    String temp = jLabel5.getText();

                    //differentiate price based on days
                    int price;
                    int day = c.get(Calendar.DAY_OF_WEEK);
                    if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                         price = rs.getInt("weekend");
                    }else{
                        price = rs.getInt("weekday");
                    }

                    if("Lokasi Kursi :".equals(temp)){
                        temp += " B2";
                    }else{
                        temp += ", B2";
                    }
                    
                    jLabel5.setText(temp);
                    total++;
                    jLabel7.setText("Jumlah Kursi : " + String.valueOf(total));
                    jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
                    jButton_b2.setBackground(new java.awt.Color(199, 139, 18));                       
                
                }
            }
            
            
            
                
        }catch (Exception ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }
    }//GEN-LAST:event_jButton_b2ActionPerformed

    private void jButton_b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_b3ActionPerformed
        // TODO add your handling code here:
        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=?";
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1,jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                if(seats[1][2] == 1){
                    int input = JOptionPane.showConfirmDialog(null, "Cancel seat?", "Confirm cancel",  JOptionPane.OK_CANCEL_OPTION);
                  
                    if(input==0){
                        seats[1][2] = 0;
                        total--;
                        
                        int price;
                        int day = c.get(Calendar.DAY_OF_WEEK);
                        if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                             price = rs.getInt("weekend");
                        }else{
                            price = rs.getInt("weekday");
                        }
                    
                        jLabel7.setText("Jumlah Kursi : " + total);
                        jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
          
                        String temp = jLabel5.getText();
                        if(temp.contains(", B3")){
                            temp = temp.replace(", B3", "");
                        } else if(temp.contains(" B3, ")){
                            temp = temp.replace("B3, ", "");
                        }else{
                            temp = temp.replace(" B3", "");
                        }
                        jLabel5.setText(temp);
                        jButton_b3.setBackground(new java.awt.Color(247,179,44));
                    }
                }else{
                    seats[1][2] = 1;
                    String temp = jLabel5.getText();

                    //differentiate price based on days
                    int price;
                    int day = c.get(Calendar.DAY_OF_WEEK);
                    if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                         price = rs.getInt("weekend");
                    }else{
                        price = rs.getInt("weekday");
                    }

                    if("Lokasi Kursi :".equals(temp)){
                        temp += " B3";
                    }else{
                        temp += ", B3";
                    }
                    
                    jLabel5.setText(temp);
                    total++;
                    jLabel7.setText("Jumlah Kursi : " + String.valueOf(total));
                    jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
                    jButton_b3.setBackground(new java.awt.Color(199, 139, 18));     
                
                }
            }
            
            
            
                
        }catch (Exception ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }
    }//GEN-LAST:event_jButton_b3ActionPerformed

    private void jButton_a1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_a1ActionPerformed
        // TODO add your handling code here:
        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=?";
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1,jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                if(seats[0][0] == 1){
                    int input = JOptionPane.showConfirmDialog(null, "Cancel seat?", "Confirm cancel",  JOptionPane.OK_CANCEL_OPTION);
                    if(input==0){
                        seats[0][0] = 0;
                        total--;
                        
                        int price;
                        int day = c.get(Calendar.DAY_OF_WEEK);
                        if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                             price = rs.getInt("weekend");
                        }else{
                            price = rs.getInt("weekday");
                        }
                    
                        jLabel7.setText("Jumlah Kursi : " + total);
                        jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
          
                        String temp = jLabel5.getText();
                        if(temp.contains(", A1")){
                            temp = temp.replace(", A1", "");
                        } else if(temp.contains(" A1, ")){
                            temp = temp.replace("A1, ", "");
                        }else{
                            temp = temp.replace(" A1", "");
                        }
                        jLabel5.setText(temp);
                        jButton_a1.setBackground(new java.awt.Color(247,179,44));
                        
                    }
                }else{
                    seats[0][0] = 1;
                    String temp = jLabel5.getText();
                    //differentiate price based on days
                    int price;
                    int day = c.get(Calendar.DAY_OF_WEEK);
                    if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                         price = rs.getInt("weekend");
                    }else{
                        price = rs.getInt("weekday");
                    }

                    if("Lokasi Kursi :".equals(temp)){
                        temp += " A1";
                    }else{
                        temp += ", A1";
                    }
                    
                    jLabel5.setText(temp);
                    total++;
                    jLabel7.setText("Jumlah Kursi : " + String.valueOf(total));
                    jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
                    jButton_a1.setBackground(new java.awt.Color(199, 139, 18));                        
                
                }
            }
            
            
            
                
        }catch (Exception ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }
    }//GEN-LAST:event_jButton_a1ActionPerformed

    private void jButton_a2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_a2ActionPerformed
        // TODO add your handling code here:
        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=?";
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1,jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                if(seats[0][1] == 1){
                    int input = JOptionPane.showConfirmDialog(null, "Cancel seat?", "Confirm cancel",  JOptionPane.OK_CANCEL_OPTION);
                    if(input==0){
                        seats[0][1] = 0;
                        total--;
                        
                        int price;
                        int day = c.get(Calendar.DAY_OF_WEEK);
                        if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                             price = rs.getInt("weekend");
                        }else{
                            price = rs.getInt("weekday");
                        }
                    
                        jLabel7.setText("Jumlah Kursi : " + total);
                        jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
          
                        String temp = jLabel5.getText();
                        if(temp.contains(", A2")){
                            temp = temp.replace(", A2", "");
                        } else if(temp.contains(" A2, ")){
                            temp = temp.replace("A2, ", "");
                        }else{
                            temp = temp.replace(" A2", "");
                        }
                        jLabel5.setText(temp);
                        jButton_a2.setBackground(new java.awt.Color(247,179,44));
                    }
                }else{
                    seats[0][1] = 1;
                    String temp = jLabel5.getText();

                    //differentiate price based on days
                    int price;
                    int day = c.get(Calendar.DAY_OF_WEEK);
                    if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                         price = rs.getInt("weekend");
                    }else{
                        price = rs.getInt("weekday");
                    }

                    if("Lokasi Kursi :".equals(temp)){
                        temp += " A2";
                    }else{
                        temp += ", A2";
                    }
                    
                    jLabel5.setText(temp);
                    total++;
                    jLabel7.setText("Jumlah Kursi : " + String.valueOf(total));
                    jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
                    jButton_a2.setBackground(new java.awt.Color(199, 139, 18));                        
                
                }
            }
            
            
            
                
        }catch (Exception ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }
    }//GEN-LAST:event_jButton_a2ActionPerformed

    private void jButton_a3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_a3ActionPerformed
        // TODO add your handling code here:
        try{
            //database:bioskop_ndut, user:root, pwd:None
            conn = DriverManager.getConnection(url, "root", "");
            //create query
            String query = "SELECT * FROM theatre WHERE id_theatre=?";
            
            //set Statement
            pst = conn.prepareStatement(query);
            pst.setString(1,jLabel3.getText().substring(jLabel3.getText().lastIndexOf(" ") + 1));
          
//            System.out.println(query);
//            System.out.println(pst);
            
            //handle the results
            rs = pst.executeQuery();
            
            if(rs.next()){
                if(seats[0][2] == 1){
                    int input = JOptionPane.showConfirmDialog(null, "Cancel seat?", "Confirm cancel",  JOptionPane.OK_CANCEL_OPTION);
                    
                    if(input==0){
                        seats[0][2] = 0;
                        total--;
                        
                        int price;
                        int day = c.get(Calendar.DAY_OF_WEEK);
                        if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                             price = rs.getInt("weekend");
                        }else{
                            price = rs.getInt("weekday");
                        }
                    
                        jLabel7.setText("Jumlah Kursi : " + total);
                        jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
          
                        String temp = jLabel5.getText();
                        if(temp.contains(", A3")){
                            temp = temp.replace(", A3", "");
                        } else if(temp.contains(" A3, ")){
                            temp = temp.replace("A3, ", "");
                        }else{
                            temp = temp.replace(" A3", "");
                        }
                        jLabel5.setText(temp);
                        jButton_a3.setBackground(new java.awt.Color(247,179,44));
                    }
                }else{
                    seats[0][2] = 1;
                    String temp = jLabel5.getText();

                    //differentiate price based on days
                    int price;
                    int day = c.get(Calendar.DAY_OF_WEEK);
                    if(day == Calendar.SUNDAY || day == Calendar.SATURDAY){
                         price = rs.getInt("weekend");
                    }else{
                        price = rs.getInt("weekday");
                    }

                    if(temp == "Lokasi Kursi :"){
                        temp += " A3";
                    }else{
                        temp += ", A3";
                    }
                    
                    jLabel5.setText(temp);
                    total++;
                    jLabel7.setText("Jumlah Kursi : " + String.valueOf(total));
                    jLabel4.setText("Total Pemesanan : Rp " + String.valueOf(total* price));
                    jButton_a3.setBackground(new java.awt.Color(199, 139, 18));                        
                
                }
            }
            
            
            
                
        }catch (Exception ex){
            System.out.println("not connected");
            System.out.println(ex);
                    
        }
    }//GEN-LAST:event_jButton_a3ActionPerformed

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
            java.util.logging.Logger.getLogger(JFrame_Kursi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_Kursi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_Kursi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_Kursi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame_Kursi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_a1;
    private javax.swing.JButton jButton_a2;
    private javax.swing.JButton jButton_a3;
    private javax.swing.JButton jButton_b1;
    private javax.swing.JButton jButton_b2;
    private javax.swing.JButton jButton_b3;
    private javax.swing.JButton jButton_bayar;
    private javax.swing.JButton jButton_c1;
    private javax.swing.JButton jButton_c2;
    private javax.swing.JButton jButton_c3;
    private javax.swing.JButton jButton_kembali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_tanggal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}

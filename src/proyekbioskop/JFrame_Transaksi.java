/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyekbioskop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Clarissa Angelia
 */
public class JFrame_Transaksi extends javax.swing.JFrame {

    /**
     * Creates new form JFrame_Transaksi
     */
    
    Connection conn;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;
    Object[] row;
    DefaultTableModel modelTabel;
    
    public JFrame_Transaksi() {
        initComponents();
        clearLabel();
        loadTanggal();
        loadDatabase();
//        loadTabel();
        loadTabelx();
//        sizeColumn();
        sizeColumnSearch();
//        loadLabel();
        loadLabelx();
    }
    
    public void clearLabel() {
        jTextField_cari.setText("");
        jLabel_total.setText("");
    }
    
    public void loadTanggal() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        jLabel_tanggal.setText(String.valueOf(dtf.format(now)));
    }
    
    public void loadDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop_ndut","root","");
        } catch (Exception ex) {
            System.out.println("error database");
        }
    }
    
    public void loadTabel() {
        row = new Object[]{"No.", "Theater", "Tanggal", "Judul", "Total Penjualan", "Total Pendapatan"};
        jTable1.setModel(new DefaultTableModel(row,0));
        modelTabel = (DefaultTableModel) jTable1.getModel();
        try {
            // TODO add your handling code here:
            pst = conn.prepareStatement("SELECT THEATER, TANGGAL, JUDUL, SUM(TOTAL_PENJUALAN) 'TOTAL PENJUALAN', SUM(TOTAL_PENDAPATAN) 'TOTAL PENDAPATAN' FROM TRANSAKSI GROUP BY THEATER, TANGGAL ORDER BY TANGGAL DESC, THEATER ASC");
//            pst = conn.prepareStatement("SELECT THEATER, TANGGAL, JUDUL, JAM, TOTAL_PENJUALAN, TOTAL_PENDAPATAN FROM TRANSAKSI ORDER BY TANGGAL DESC");
            rs = pst.executeQuery();
            int count = 1;
            while(rs.next()) {
                int num = count++;
                String theater = rs.getString("THEATER");
                String tgl = String.valueOf(rs.getDate("TANGGAL"));
                String judul = rs.getString("JUDUL");
//                String jam = rs.getString("JAM");
                int sell = rs.getInt("TOTAL PENJUALAN");
                int income = rs.getInt("TOTAL PENDAPATAN");
                row = new Object[]{num, theater, tgl, judul, sell, income};
                modelTabel.addRow(row);
            }
        } catch (SQLException ex) {
            System.out.println("error table");
        }
    }
    
    public void loadTabelx() {
        row = new Object[]{"No.", "Tanggal", "Theater", "Jam", "Judul", "Total Penjualan", "Total Pendapatan"};
        jTable1.setModel(new DefaultTableModel(row,0));
        modelTabel = (DefaultTableModel) jTable1.getModel();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT THEATER, TANGGAL, JUDUL, JAM, TOTAL_PENJUALAN, TOTAL_PENDAPATAN FROM TRANSAKSI ORDER BY TANGGAL DESC, THEATER ASC, JAM ASC");
            int count = 1;
            while(rs.next()) {
                int num = count++;
                String theater = rs.getString("THEATER");
                String tgl = String.valueOf(rs.getDate("TANGGAL"));
                String judul = rs.getString("JUDUL");
                String jam = rs.getString("JAM");
                int sell = rs.getInt("TOTAL_PENJUALAN");
                int income = rs.getInt("TOTAL_PENDAPATAN");
                row = new Object[]{num, tgl, theater, jam, judul, sell, income};
                modelTabel.addRow(row);
            }
        } catch (SQLException ex) {
            System.out.println("error search");
        }
    }
    
    public void searchData(String key) {
        row = new Object[]{"No.", "Tanggal", "Theater", "Jam", "Judul", "Total Penjualan", "Total Pendapatan"};
        jTable1.setModel(new DefaultTableModel(row,0));
        modelTabel = (DefaultTableModel) jTable1.getModel();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT THEATER, TANGGAL, JUDUL, JAM, TOTAL_PENJUALAN, TOTAL_PENDAPATAN FROM TRANSAKSI WHERE TANGGAL LIKE '%"+key+"%' OR JUDUL LIKE '%"+key+"%' ORDER BY TANGGAL DESC, THEATER ASC, JAM ASC");
            int count = 1;
            while(rs.next()) {
                int num = count++;
                String theater = rs.getString("THEATER");
                String tgl = String.valueOf(rs.getDate("TANGGAL"));
                String judul = rs.getString("JUDUL");
                String jam = rs.getString("JAM");
                int sell = rs.getInt("TOTAL_PENJUALAN");
                int income = rs.getInt("TOTAL_PENDAPATAN");
                row = new Object[]{num, tgl, theater, jam, judul, sell, income};
                modelTabel.addRow(row);
            }
        } catch (SQLException ex) {
            System.out.println("error search");
        }
    }
    
    public void sizeColumn() {
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(400);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
    }
    
    public void sizeColumnSearch() {
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(400);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(200);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(200);
    }
    
//    public void loadLabel() {
//        int sum = 0;
//        for(int i = 0; i < jTable1.getRowCount(); i++){
//            sum = sum+Integer.parseInt(jTable1.getValueAt(i,5).toString());
//        }
//        jLabel_total.setText(String.valueOf(sum));
//    }
    
    public void loadLabelx() {
        int sum = 0;
        for(int i = 0; i < jTable1.getRowCount(); i++){
            sum = sum+Integer.parseInt(jTable1.getValueAt(i,6).toString());
        }
        jLabel_total.setText(String.valueOf(sum));
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
        jLabel2 = new javax.swing.JLabel();
        jButton_kembali = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel_total = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_cari = new javax.swing.JTextField();
        jButton_export = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(760, 564));
        setMinimumSize(new java.awt.Dimension(760, 564));
        getContentPane().setLayout(null);

        jLabel_tanggal.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel_tanggal.setForeground(new java.awt.Color(247, 179, 44));
        jLabel_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel_tanggal.setText("jLabel_tanggal");
        getContentPane().add(jLabel_tanggal);
        jLabel_tanggal.setBounds(535, 16, 200, 21);

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(247, 179, 44));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LAPORAN TRANSAKSI");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 65, 750, 54);

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

        jTable1.setBackground(new java.awt.Color(247, 179, 44));
        jTable1.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jTable1.setForeground(new java.awt.Color(102, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(15, 176, 720, 290);

        jPanel1.setBackground(new java.awt.Color(102, 0, 0));
        jPanel1.setLayout(null);

        jLabel_total.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel_total.setForeground(new java.awt.Color(247, 179, 44));
        jLabel_total.setText("TOTAL HARGA");
        jPanel1.add(jLabel_total);
        jLabel_total.setBounds(600, 470, 130, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(247, 179, 44));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("TOTAL PENDAPATAN : Rp. ");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(360, 470, 230, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(247, 179, 44));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("CARI :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(434, 140, 80, 30);

        jTextField_cari.setText("jTextField_cari");
        jTextField_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_cariActionPerformed(evt);
            }
        });
        jTextField_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_cariKeyReleased(evt);
            }
        });
        jPanel1.add(jTextField_cari);
        jTextField_cari.setBounds(520, 140, 210, 30);

        jButton_export.setBackground(new java.awt.Color(247, 179, 44));
        jButton_export.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_export.setForeground(new java.awt.Color(102, 0, 0));
        jButton_export.setText("DOWNLOAD TXT FILE");
        jButton_export.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange, java.awt.Color.orange));
        jButton_export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_exportActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_export);
        jButton_export.setBounds(20, 140, 190, 30);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 750, 530);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_kembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_kembaliActionPerformed
        // TODO add your handling code here:

        JFrame_Admin admin = new JFrame_Admin();
        admin.setVisible(true);
        dispose();

    }//GEN-LAST:event_jButton_kembaliActionPerformed

    private void jTextField_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_cariKeyReleased
        // TODO add your handling code here:
        String key = jTextField_cari.getText();
        System.out.println(key);  
        if(key!="") {
            searchData(key);
            sizeColumnSearch();
        } else {
//            loadTabel();
            loadTabelx();
        }
    }//GEN-LAST:event_jTextField_cariKeyReleased

    private void jTextField_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_cariActionPerformed

    private void jButton_exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_exportActionPerformed
        // TODO add your handling code here:
        try{
            File file = new File("C:\\Users\\Clarissa Angelia\\Downloads\\proyekbioskop\\proyekbioskop\\txtfiles\\data.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (int i = 0; i < jTable1.getColumnCount(); i++){
                if(i == jTable1.getColumnCount()-1){
                    bw.write(jTable1.getColumnName(i));
                }else{
                    bw.write(jTable1.getColumnName(i) + ", ");
                }
            }
            bw.write("\n");
            for(int i = 0; i < jTable1.getRowCount(); i++){
                for (int j = 0; j < jTable1.getColumnCount(); j++){
                    if(j == jTable1.getColumnCount()-1){
                        bw.write((String)jTable1.getModel().getValueAt(i,j));
                    }else{
                        bw.write(jTable1.getModel().getValueAt(i,j)+ ", ");
                    }
                    
                }
                bw.write("\n\n");
            }
            bw.close();
            fw.close();
            JOptionPane.showMessageDialog(null, "Data Exported Successfully");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton_exportActionPerformed

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
            java.util.logging.Logger.getLogger(JFrame_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame_Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame_Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_export;
    private javax.swing.JButton jButton_kembali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_tanggal;
    private javax.swing.JLabel jLabel_total;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_cari;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

package Form;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author Rasyid_
 */
public class LaporanTransaksi extends javax.swing.JInternalFrame {

    /** Creates new form LaporanTransaksi */
    Connection conn = null;
    PreparedStatement pst = null;
    Statement stat;
    ResultSet rs = null;
    int idTransaksi;
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    DefaultTableModel tableModel = new DefaultTableModel(
	new Object[][]{},
	new String[]{
		"idTransaksi","Nama Pelanggan","Total Transaksi","Tanggal"
	}
    );
    
    public LaporanTransaksi() {
        initComponents();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
        jTableLaporanTransaksi.setModel(tableModel);
        getLaporanTransaksi();
    }
    
    private void getLaporanTransaksi(){
    //menghapus isi table dataPelanggan
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
        try{
            stat = (Statement) ctrlKoneksi.mariaDB().createStatement();
            String sql = "SELECT * FROM laporantransaki ORDER BY tanggalTransaksi ASC";
            rs = stat.executeQuery(sql);
            while(rs.next()){
                Object[] obj = new Object[4];
                obj[0] = rs.getString("idTransaksi");
                obj[1] = rs.getString("Nama");
                obj[2] = rs.getString("Total");
                obj[3] = rs.getString("tanggalTransaksi");
                tableModel.addRow(obj);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLaporanTransaksi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButtonHapusBaris = new javax.swing.JButton();
        jButtonHapusSemua = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Laporan Transaksi");

        jTableLaporanTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "idTransaksi", "Nama Pelanggan", "Total Transaksi", "Tanggal Transaksi"
            }
        ));
        jTableLaporanTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLaporanTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableLaporanTransaksi);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Laporan Transaksi");

        jButtonHapusBaris.setText("Hapus Baris");
        jButtonHapusBaris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapusBarisActionPerformed(evt);
            }
        });

        jButtonHapusSemua.setText("Hapus Semua");
        jButtonHapusSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHapusSemuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonHapusBaris)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonHapusSemua)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHapusBaris)
                    .addComponent(jButtonHapusSemua))
                .addContainerGap(141, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonHapusBarisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapusBarisActionPerformed
        // TODO add your handling code here:
        try{
            conn = ctrlKoneksi.mariaDB();
            String sql = "DELETE FROM laporantransaki WHERE idTransaksi='"+idTransaksi+"'";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
            getLaporanTransaksi();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        getLaporanTransaksi();
    }//GEN-LAST:event_jButtonHapusBarisActionPerformed

    private void jButtonHapusSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHapusSemuaActionPerformed
        // TODO add your handling code here:
        try{
            conn = ctrlKoneksi.mariaDB();
            String sql = "TRUNCATE TABLE laporantransaki";
            pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Semua Data Berhasil Dihapus!");
            getLaporanTransaksi();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        getLaporanTransaksi();
    }//GEN-LAST:event_jButtonHapusSemuaActionPerformed

    private void jTableLaporanTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLaporanTransaksiMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1){
            int row = jTableLaporanTransaksi.getSelectedRow();
            idTransaksi = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
            tableModel.getValueAt(row, 1).toString();
            tableModel.getValueAt(row, 2).toString();
            tableModel.getValueAt(row, 3).toString();
        }
    }//GEN-LAST:event_jTableLaporanTransaksiMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonHapusBaris;
    private javax.swing.JButton jButtonHapusSemua;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLaporanTransaksi;
    // End of variables declaration//GEN-END:variables

}

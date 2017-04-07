/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.fichaCliente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import process.CRUDClientes.ModificarCliente;
import view.tarifas.simples.EditorCeldaTabla;

/**
 *
 * @author loquat
 */
public final class FrameContactos extends javax.swing.JFrame {

    
    
    public void actualizarTabla() {

       
    }

    public FrameContactos() {
        initComponents();
        jTable1.getColumnModel().getColumn(1).setCellEditor(new EditorCeldaTabla());
        jTable1.getColumnModel().getColumn(2).setCellEditor(new EditorCeldaTabla());
        actualizarTabla();

        // modificacion en la columna de emails
        jTable1.getColumnModel().getColumn(2).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";

            @Override
            public void editingStopped(ChangeEvent e) {

                valor = (String) jTable1.getColumnModel().getColumn(2).getCellEditor().getCellEditorValue();
                ModificarCliente modificar = null;

                switch (jTable1.getSelectedRow()) {

                    case 1:
                        modificar = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(), "correo", valor);
                        break;
                    case 2:
                        modificar = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(), "emailConsultas", valor);
                        break;
                    case 3:
                        modificar = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(), "emailAdmon", valor);
                        break;
                    case 4:
                        modificar = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(), "emailAsistencia", valor);
                        break;

                    case 5:
                        modificar = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(), "emailExtra", valor);
                        break;

                }

                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }

                SingletonCliente.getInstance().actualizarCliente();
                actualizarTabla();
                  try {
                    FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).fichaModelo.actualizarCliente();
                } catch (Exception ex) {
                    Logger.getLogger(FrameContactos.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });

        // modificacion en la columna de telefonos
        jTable1.getColumnModel().getColumn(1).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";
        
            @Override
            public void editingStopped(ChangeEvent e) {

                valor = (String) jTable1.getColumnModel().getColumn(1).getCellEditor().getCellEditorValue();
                ModificarCliente modificar = null;
                switch (jTable1.getSelectedRow()) {

                    case 1:
                        modificar = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(), "telefono1", valor);
                        break;
                    case 2:
                        modificar = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(), "telefonoConsultas", valor);
                        break;
                    case 3:
                        modificar = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(), "telefonoAdmon", valor);
                        break;
                    case 4:
                        modificar = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(), "telefonoAsistencia", valor);
                        break;

                    case 5:
                        modificar = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(), "telefonoExtra", valor);
                        break;

                    case 6:
                        modificar = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(), "fax", valor);
                        break;

                }

                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                // NOS TRAEMOS EL CLIENTE COMPLETO SEGUN SU ID PARA PONERLO EN EL SINGLETON Y QUE TODAS LAS CLASES LO PUEDAN INSTANCIAR

                SingletonCliente.getInstance().actualizarCliente();
                actualizarTabla();
                try {
                    FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).fichaModelo.actualizarCliente();
                } catch (Exception ex) {
                    Logger.getLogger(FrameContactos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONTACTOS CLIENTE");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {" Telefono ", null, null},
                {" Consultas", null, null},
                {" Administracion", null, null},
                {" Asistencia", null, null},
                {" Extra", null, null},
                {" Fax", null, null}
            },
            new String [] {
                "", "Teléfono", "Email"
            }
        ));
        jTable1.setRowHeight(22);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("AGENDA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FrameContactos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameContactos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameContactos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameContactos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrameContactos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

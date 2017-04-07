/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.basesOperativas;

import DatosPrecargados.SingletonBasesOperativas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.basesOperativas.BaseOperativa;
import process.CRUDBasesOperativas.EliminarBaseOperativa;
import process.CRUDBasesOperativas.ModificarBaseOperativa;
import process.CRUDBasesOperativas.RegistrarBaseOperativa;


public class BasesOperativasCRUDFrame extends javax.swing.JFrame {

    JTablaBasesOperativasCRUD tabla;
    private static BasesOperativasCRUDFrame instance;
    
    public static BasesOperativasCRUDFrame getInstance() { 
        if (instance == null) {
            instance = new BasesOperativasCRUDFrame();
        }
        return instance;
    }

    public JTablaBasesOperativasCRUD getTabla() {
        return tabla;
    }
 
    
 
    private BasesOperativasCRUDFrame() {
      
            initComponents();
            
            tabla = new JTablaBasesOperativasCRUD();
            tabla.getModel().setEditable(true);
            jScrollPaneTOTAL.setViewportView(tabla);
         
            addWindowListener( new WindowAdapter() {
                @Override
                public void windowClosing( WindowEvent evt ) {
                    try {
                        AsociarBasesOperativasAClienteFrame a = new AsociarBasesOperativasAClienteFrame();
                        a.setVisible(true);
                        
                    } catch (IOException ex) {
                        Logger.getLogger(BasesOperativasCRUDFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            actualizarTabla();
    
    }

    
    public void actualizarTabla() {
           
          tabla.mostrar(SingletonBasesOperativas.getInstance().getLista());
      
        
    }
    
    
    
    
    public void modificarMunicipioBase(Integer id) {
      
        tabla.modificarMunicipioBase(id);
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPaneTOTAL = new javax.swing.JScrollPane();
        jLabel47 = new javax.swing.JLabel();
        botonNuevaBase = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel47.setBackground(new java.awt.Color(51, 51, 255));
        jLabel47.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("LISTADO TOTAL DE BASES OPERATIVAS");

        botonNuevaBase.setBackground(new java.awt.Color(255, 255, 255));
        botonNuevaBase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonNuevaBase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/plus.png"))); // NOI18N
        botonNuevaBase.setText("Nueva base operativa");
        botonNuevaBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevaBaseActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/basesOperativas/papelera.jpg"))); // NOI18N
        jButton1.setText("Eliminar Base");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("* Para modificar, haz click sobre la celda a editar directamente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneTOTAL)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botonNuevaBase, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonNuevaBase, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevaBaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevaBaseActionPerformed
         
        try {
            BaseOperativa baseOperativanueva = new BaseOperativa();
            boolean encontrado = false;
            String input = JOptionPane.showInputDialog(null,"Introduce el nombre de la nueva base: " );
            input  = input.trim();
            for (int i=0; i<SingletonBasesOperativas.getInstance().getLista().size();i++) {
                if (input.equals(SingletonBasesOperativas.getInstance().getLista().get(i).getBaseOperativa())) {
                    JOptionPane.showMessageDialog(null,"Ya existe una base operativa registrada con ese nombre");
                    encontrado = true;
                    break;
                }
            
            }
            if (!encontrado) {
                baseOperativanueva.setBaseOperativa(input);
                RegistrarBaseOperativa registrador = new RegistrarBaseOperativa(baseOperativanueva);
                registrador.registrar();
                SingletonBasesOperativas.getInstance().actualizarLista();
                actualizarTabla();
            }
        } catch (Exception ex) {
            Logger.getLogger(BasesOperativasCRUDFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }//GEN-LAST:event_botonNuevaBaseActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if (tabla.isEditing()) {
             tabla.setEliminar(true);
       
            tabla.getCellEditor().stopCellEditing();
        }
        try {
            EliminarBaseOperativa eliminar  = new EliminarBaseOperativa(SingletonBasesOperativas.getInstance().getLista().get(tabla.getSelectedRow()));
            eliminar.eliminar();
            SingletonBasesOperativas.getInstance().actualizarLista();
            actualizarTabla();
            
        } catch (Exception ex) {
            Logger.getLogger(BasesOperativasCRUDFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        tabla.setEliminar(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(BasesOperativasCRUDFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BasesOperativasCRUDFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BasesOperativasCRUDFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BasesOperativasCRUDFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BasesOperativasCRUDFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonNuevaBase;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPaneTOTAL;
    // End of variables declaration//GEN-END:variables
}

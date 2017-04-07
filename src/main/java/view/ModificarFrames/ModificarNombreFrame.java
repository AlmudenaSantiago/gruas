/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.ModificarFrames;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import process.CRUDClientes.ModificarCliente;
import process.CRUDProveedores.ModificarProveedor;
import process.CRUD.Modificar;
import process.CRUDProductos.ModificarServicio;
import process.CRUDVendedores.ModificarVendedor;
import process.limpiador.LimpiarTexto;
import view.clientes.FichaClienteFrame;
import view.pedidos.MainFrame;


public class ModificarNombreFrame extends javax.swing.JFrame {

    private Integer idFila;
    private String quienMeLlama;
    private boolean clienteModificado;
    private FichaClienteFrame ficha;
    
    public ModificarNombreFrame(Integer idFila, String quienMeLlama, FichaClienteFrame ficha) {
        this.idFila = idFila;
        this.quienMeLlama = quienMeLlama;
        this.ficha = ficha;
        this.setTitle("Modificar nombre");
        initComponents();
        
    }

  
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jButtonEnviarNombre = new javax.swing.JButton();

        jLabelNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelNombre.setText("Nuevo nombre: ");

        jTextFieldNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });

        jButtonEnviarNombre.setText("Enviar");
        jButtonEnviarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarNombreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNombre)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jButtonEnviarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonEnviarNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jButtonEnviarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarNombreActionPerformed
        
            switch (quienMeLlama) { 
                case "producto": 
                            {
                                try {
                                    new ModificarServicio(idFila,"nombre",new LimpiarTexto().limpiar(this.jTextFieldNombre.getText())).modificarProducto();
                               
                                } catch (IOException ex) {
                                    Logger.getLogger(ModificarNombreFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                     break;
                case "proveedor":
                            {
                                try {
                                    new ModificarProveedor(idFila,"nombre",new LimpiarTexto().limpiar(this.jTextFieldNombre.getText())).modificarProveedor();
                                     ficha.consultar();
                                   ficha.actualizar(); 
                                } catch (IOException ex) {
                                    Logger.getLogger(ModificarNombreFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                    break;
                case "cliente":
                            {
                                try {
                                    new ModificarCliente(idFila,"nombre",new LimpiarTexto().limpiar(this.jTextFieldNombre.getText())).modificarCliente();
                                    ficha.consultar();
                                    ficha.actualizar();
                                  } catch (IOException ex) {
                                    Logger.getLogger(ModificarNombreFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                    break;
                    
                    
               case "vendedor":
                            {
                                try {
                                    new ModificarVendedor(idFila,"nombre",new LimpiarTexto().limpiar(this.jTextFieldNombre.getText())).modificarVendedor();
                                } catch (IOException ex) {
                                    Logger.getLogger(ModificarNombreFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                    break;
            
            }
  
              this.setVisible(false);
    }//GEN-LAST:event_jButtonEnviarNombreActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEnviarNombre;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.ModificarFrames;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ficha;
import process.CRUDClientes.ModificarCliente;
import process.CRUDProveedores.ModificarProveedor;
import process.limpiador.LimpiarTexto;
import view.clientes.FichaClienteFrame;

/**
 *
 * @author Aaron
 */
public class ModificarDomicilioFrame extends javax.swing.JFrame {

  Integer idFila;
  String quienMeLlama;
  Ficha ficha;
  
  
    public ModificarDomicilioFrame(Integer idFila, String quienMeLlama, Ficha ficha) {
        initComponents();
        this.idFila = idFila;
        this.quienMeLlama = quienMeLlama;
        this.ficha = ficha;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldDomicilio = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Nuevo Domicilio:");

        jTextFieldDomicilio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldDomicilio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDomicilioActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(jButton1)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldDomicilioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDomicilioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDomicilioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         switch (quienMeLlama) { 
              
                case "proveedor":
                            {
                                try {
                                    new ModificarProveedor(idFila,"domicilio",new LimpiarTexto().limpiar(this.jTextFieldDomicilio.getText())).modificarProveedor();
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
                                  new ModificarCliente(idFila,"domicilio",new LimpiarTexto().limpiar(this.jTextFieldDomicilio.getText())).modificarCliente();
                                   ficha.consultar();
                                   ficha.actualizar();
                                } catch (IOException ex) {
                                    Logger.getLogger(ModificarNombreFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                              break;
  
                }
         this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

  
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextFieldDomicilio;
    // End of variables declaration//GEN-END:variables
}

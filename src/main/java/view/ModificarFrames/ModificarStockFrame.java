/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.ModificarFrames;

import javax.swing.JOptionPane;
import model.Gasto;
import process.CRUDGastos.InsertarGasto;
import process.CRUDProductos.ModificarServicio;


public class ModificarStockFrame extends javax.swing.JFrame {

    private Integer idFila;
    private String nombre;
    private Double precioCompra;
   
    public ModificarStockFrame(Integer idFila, String nombre, Double precioCompra) {
        this.idFila = idFila;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
       
        this.setTitle("Modificar stock");
        initComponents();
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNombre = new javax.swing.JLabel();
        jTextFieldStock = new javax.swing.JTextField();
        jButtonEnviarNombre = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxFormaPago = new javax.swing.JComboBox();
        jTextFieldPrecioCompra = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabelNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelNombre.setText("¿Cuánto unidades del producto deseas añadir?");

        jTextFieldStock.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldStockActionPerformed(evt);
            }
        });

        jButtonEnviarNombre.setText("Enviar");
        jButtonEnviarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnviarNombreActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Forma de pago:");

        jComboBoxFormaPago.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "banco", "caja", "no" }));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setText("Nuevo precio de compra: ");

        jLabel3.setText("(si ha variado respecto al último)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxFormaPago, 0, 102, Short.MAX_VALUE)
                            .addComponent(jTextFieldPrecioCompra))))
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jButtonEnviarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jButtonEnviarNombre)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldStockActionPerformed

    private void jButtonEnviarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnviarNombreActionPerformed
        try {
                   new ModificarServicio(idFila,"stock",this.jTextFieldStock.getText()).modificarProducto();
                   Gasto gasto = new Gasto();
                   gasto.setNombre("Rep. Stock: " + nombre);
                   
                   if (jTextFieldPrecioCompra.getText().equals("")){
                        gasto.setPrecio(precioCompra.toString());
                   } else {
                           new ModificarServicio(idFila,"precioCompra",this.jTextFieldPrecioCompra.getText()).modificarProducto();
                           gasto.setPrecio(this.jTextFieldPrecioCompra.getText());
                   }
                   
                    gasto.setCantidad(this.jTextFieldStock.getText());
                    gasto.setForma(this.jComboBoxFormaPago.getSelectedItem().toString());
        
                     InsertarGasto insertaGasto = new InsertarGasto(gasto);
                    try {
                        insertaGasto.insertaGasto();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

         } catch (Exception e) {
            System.out.println(e);
         }
           
              this.setVisible(false);
    }//GEN-LAST:event_jButtonEnviarNombreActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEnviarNombre;
    private javax.swing.JComboBox jComboBoxFormaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JTextField jTextFieldPrecioCompra;
    private javax.swing.JTextField jTextFieldStock;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.contactos;

import command.contactos.CargarContactosCommand;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Contacto;
import process.CRUD.contactos.EliminarContacto;
import process.CRUD.contactos.InsertarContacto;
import process.cargador.contactos.CargadorListaContacto;
import process.parser.contactos.ContactoParserJson;
import view.fichaCliente.SingletonCliente;


public class FrameAgenda extends javax.swing.JFrame {

    
    private static FrameAgenda instance;
    
    private FrameAgenda() {
        try {
            initComponents();
            CargarContactosCommand cargarContactosCommand = new CargarContactosCommand(CargadorListaContacto.getInstance(), new ContactoParserJson());
            jScrollPane1.setViewportView(JTablaCRUDContactos.getInstance());
            JTablaCRUDContactos.getInstance().mostrarContactos(cargarContactosCommand.executeListaPorCliente(SingletonCliente.getInstance().getCliente().getId()));
           
        } catch (Exception ex) {
            Logger.getLogger(FrameAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public static FrameAgenda getInstance() {
        if (instance == null) {
              instance = new FrameAgenda();
        }
        return instance;
    
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        botonNuevoContacto = new javax.swing.JButton();
        botonNuevoContacto1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AGENDA");

        botonNuevoContacto.setBackground(new java.awt.Color(255, 255, 255));
        botonNuevoContacto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonNuevoContacto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/plus.png"))); // NOI18N
        botonNuevoContacto.setText("Nuevo contacto");
        botonNuevoContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoContactoActionPerformed(evt);
            }
        });

        botonNuevoContacto1.setBackground(new java.awt.Color(255, 255, 255));
        botonNuevoContacto1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonNuevoContacto1.setText("Eliminar contacto");
        botonNuevoContacto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevoContacto1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonNuevoContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonNuevoContacto1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(212, 212, 212))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonNuevoContacto1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonNuevoContacto))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevoContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoContactoActionPerformed
        try {
            Contacto contacto = new Contacto();
           
            // MANDO EL ID DEL CLIENTE APROVECHANDO EL POST DE CONTACTO
            // PARA QUE DESDE EL SERVIDOR SE ASOCIE EL ID DEL CLIENTE QUE MANDO 
            // CON EL ID DEL NUEVO CONTACTO QUE VA A CREAR Y NO TENER QUE HACER DOS CONSULTAS 
            contacto.setId(SingletonCliente.getInstance().getCliente().getId());
            InsertarContacto registrador = new InsertarContacto(contacto);
            registrador.inserta();
            JTablaCRUDContactos.getInstance().actualizarTabla();
            
        } catch (Exception ex) {
            Logger.getLogger(FrameAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           
    
      
    }//GEN-LAST:event_botonNuevoContactoActionPerformed

    private void botonNuevoContacto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevoContacto1ActionPerformed
        try {
            Contacto contacto = new Contacto();
            contacto.setId((Integer) JTablaCRUDContactos.getInstance().getValueAt(JTablaCRUDContactos.getInstance().getSelectedRow(), 0));
            // VOY A APROVECHAR EL CAMPO DE TELEFONO PARA MANDAR EL ID DEL CLIENTE AL SERVIDOR Y NO TENER QUE HACER DOS CONSULTAS
            contacto.setTelefono(SingletonCliente.getInstance().getCliente().getId().toString());
            EliminarContacto eliminar = new EliminarContacto(contacto);
            eliminar.elimina();
            JTablaCRUDContactos.getInstance().actualizarTabla();
        } catch (Exception ex) {
            Logger.getLogger(FrameAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonNuevoContacto1ActionPerformed

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
            java.util.logging.Logger.getLogger(FrameAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrameAgenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonNuevoContacto;
    private javax.swing.JButton botonNuevoContacto1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tarifas.tiposDeTarifas;

import DatosPrecargados.SingletonTiposTarifas;
import command.tarifas.CargarTiposDeTarifaCommand;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Tarifas.TipoDeTarifa;
import process.CRUDTiposDeTarifa.EliminarTipoDeTarifa;
import process.CRUDTiposDeTarifa.RegistrarTipoDeTarifa;
import process.cargador.tarifas.CargadorListaTiposDeTarifa;
import process.parser.tipoDeTarifas.TipoDeTarifaParserJson;
import view.basesOperativas.BasesOperativasCRUDFrame;
import view.fichaCliente.FichaCliente;
import view.fichaCliente.SingletonCliente;
import view.tarifas.simples.AsociarTarifasAClienteFrame;

/**
 *
 * @author loquat
 */
public class TiposDeTarifaCRUDFrame extends javax.swing.JFrame {

   
   
    JTablaTiposDeTarifas tabla;
    private static TiposDeTarifaCRUDFrame instance;

    public static TiposDeTarifaCRUDFrame getInstance() {
        if (instance == null) {
            instance = new TiposDeTarifaCRUDFrame();
        }
        return instance;
    }

    private TiposDeTarifaCRUDFrame() {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        actualizarTabla();
         
        addWindowListener( new WindowAdapter() {
                @Override
                public void windowClosing( WindowEvent evt ) {
                    
                    try {
                        FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).actualizarListaTodosLosTipos();
                        AsociarTarifasAClienteFrame asociarFrame = new AsociarTarifasAClienteFrame(FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()));
                        asociarFrame.setVisible(true);
                        asociarFrame.setLocationRelativeTo(null);
                        asociarFrame.pack();
                    } catch (IOException ex) {
                        Logger.getLogger(TiposDeTarifaCRUDFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(TiposDeTarifaCRUDFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        
        
        

    }

    public void actualizarTabla() {
       
            SingletonTiposTarifas.getInstance().actualizarLista();
            tabla = new JTablaTiposDeTarifas();
            tabla.getModel().setEditable(true);
            jScrollPaneTOTAL.setViewportView(tabla);
            tabla.mostrarTarifas(SingletonTiposTarifas.getInstance().getLista());
       
       
        
    }

      
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botonNuevaBase = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPaneTOTAL = new javax.swing.JScrollPane();
        jLabel47 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONFIGURACIÓN TARIFAS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        botonNuevaBase.setBackground(new java.awt.Color(255, 255, 255));
        botonNuevaBase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonNuevaBase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/plus.png"))); // NOI18N
        botonNuevaBase.setText("Nueva tarifa");
        botonNuevaBase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevaBaseActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/basesOperativas/papelera.jpg"))); // NOI18N
        jButton1.setText("Eliminar tarifa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("* Para modificar, haz click sobre la celda a editar directamente");

        jLabel47.setBackground(new java.awt.Color(51, 51, 255));
        jLabel47.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("LISTADO TOTAL DE TIPOS DE TARIFA");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonNuevaBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
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
            TipoDeTarifa tarifa = new TipoDeTarifa();
            String input = JOptionPane.showInputDialog(null, "Introduce el nombre de la nueva base: ");
            tarifa.setTipo(input);
            RegistrarTipoDeTarifa registrador = new RegistrarTipoDeTarifa(tarifa);
            registrador.registrar();
            actualizarTabla();
        } catch (Exception ex) {
            Logger.getLogger(TiposDeTarifaCRUDFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_botonNuevaBaseActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int confirm = JOptionPane.showConfirmDialog(null, "Atención: Se eliminarán todos los registros en los que aparezca la tarifa seleccionada. ¿Deseas continuar?");
        if (confirm == 0) {
        try {
            EliminarTipoDeTarifa eliminar = new EliminarTipoDeTarifa(SingletonTiposTarifas.getInstance().getLista().get(tabla.getSelectedRow()));
            eliminar.eliminar();
            actualizarTabla();
        } catch (Exception ex) {
            Logger.getLogger(BasesOperativasCRUDFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
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
            java.util.logging.Logger.getLogger(TiposDeTarifaCRUDFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TiposDeTarifaCRUDFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TiposDeTarifaCRUDFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TiposDeTarifaCRUDFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TiposDeTarifaCRUDFrame().setVisible(true);
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

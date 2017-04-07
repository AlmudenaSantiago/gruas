/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.distancias;

import command.distancias.CargarDistanciasCommand;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import model.Distancia;
import process.CRUDDistancia.EliminarDistancia;
import process.CRUDDistancia.RegistrarDistancia;
import process.cargador.distancias.CargadorListaDistancias;
import process.parser.distancias.DistanciaParserJson;


public class DistanciasFrame extends javax.swing.JFrame {

    TableRowSorter rowSorter;
    List<Distancia> lista;
    private static DistanciasFrame instance;
     
    
    
     
    private DistanciasFrame() {
        initComponents();
        lista = new ArrayList<>();
        actualizarTabla();
        rowSorter = new TableRowSorter(TablaDistancias.getInstance().getModel());
        TablaDistancias.getInstance().setRowSorter(rowSorter);
        jScrollPaneTabla.setViewportView(TablaDistancias.getInstance());
        setListenerFilter();
        setListenerHabilitarEdicion();
    }

 
    
    public void actualizarTabla() {
         lista = new CargarDistanciasCommand(CargadorListaDistancias.getInstance(), new DistanciaParserJson()).executeLista();
         TablaDistancias.getInstance().mostrar(lista);
    }
    
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxFiltros = new javax.swing.JComboBox<>();
        jComboBoxFiltrosContieneOEmpieza = new javax.swing.JComboBox<>();
        jTextFieldFilter = new javax.swing.JTextField();
        botonNuevaGrua1 = new javax.swing.JButton();
        botonEliminarGrua1 = new javax.swing.JButton();
        jCheckBoxEdicion = new javax.swing.JCheckBox();
        jScrollPaneTabla = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DISTANCIAS");
        setExtendedState(6);

        panelContenedor.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Filtrar por");

        jComboBoxFiltros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Municipio Origen", "Municipio Destino", "Kms" }));

        jComboBoxFiltrosContieneOEmpieza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Contiene", "Empieza por" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addComponent(jComboBoxFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxFiltrosContieneOEmpieza, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(117, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxFiltrosContieneOEmpieza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        botonNuevaGrua1.setBackground(new java.awt.Color(255, 255, 255));
        botonNuevaGrua1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonNuevaGrua1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/plus.png"))); // NOI18N
        botonNuevaGrua1.setText("Nueva fila");
        botonNuevaGrua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevaGrua1ActionPerformed(evt);
            }
        });

        botonEliminarGrua1.setBackground(new java.awt.Color(255, 255, 255));
        botonEliminarGrua1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonEliminarGrua1.setText("Eliminar fila");
        botonEliminarGrua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarGrua1ActionPerformed(evt);
            }
        });

        jCheckBoxEdicion.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxEdicion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBoxEdicion.setText("Permitir editar tabla");
        jCheckBoxEdicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEdicionActionPerformed(evt);
            }
        });

        jScrollPaneTabla.setMaximumSize(new java.awt.Dimension(300, 300));

        javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panelContenedor);
        panelContenedor.setLayout(panelContenedorLayout);
        panelContenedorLayout.setHorizontalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addContainerGap(300, Short.MAX_VALUE)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelContenedorLayout.createSequentialGroup()
                        .addComponent(jCheckBoxEdicion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonNuevaGrua1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonEliminarGrua1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(300, Short.MAX_VALUE))
        );
        panelContenedorLayout.setVerticalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botonNuevaGrua1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botonEliminarGrua1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBoxEdicion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelContenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevaGrua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevaGrua1ActionPerformed
        
        
        RegistrarDistancia registrar = new RegistrarDistancia(new Distancia());
        try {
            registrar.insertar();
        } catch (Exception ex) {
            Logger.getLogger(DistanciasFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        actualizarTabla();
        
    }//GEN-LAST:event_botonNuevaGrua1ActionPerformed

    private void botonEliminarGrua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarGrua1ActionPerformed
        
        Distancia d = new Distancia();
        d.setId(Integer.parseInt(TablaDistancias.getInstance().getValueAt(TablaDistancias.getInstance().getSelectedRow(),0).toString()));
        EliminarDistancia eliminar = new EliminarDistancia(d);
        try {
            eliminar.eliminar();
        } catch (Exception ex) {
            Logger.getLogger(DistanciasFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        actualizarTabla();
    }//GEN-LAST:event_botonEliminarGrua1ActionPerformed

    private void jCheckBoxEdicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEdicionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxEdicionActionPerformed

    
    public void setListenerHabilitarEdicion() {
        jCheckBoxEdicion.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {

                        if (jCheckBoxEdicion.isSelected()) {
                                 TablaDistancias.getInstance().habilitarEdicionTabla();
                                 
                       
                        } else {
                                 TablaDistancias.getInstance().deshabilitarEdicionTabla();
                        
                        }


                    }
                  });
    }
  

    
    public void setListenerFilter() {

         jTextFieldFilter.addKeyListener(new KeyAdapter() {
             @Override
             public void keyReleased(final KeyEvent e) {
                 String textofiltro = "";

                 if (jComboBoxFiltrosContieneOEmpieza.getSelectedIndex() == 0) {
                     textofiltro = "(?i)" + jTextFieldFilter.getText();
                 } else {
                     textofiltro = "(?i)^" + jTextFieldFilter.getText();
                 }

                 repaint();
                 rowSorter.setRowFilter(RowFilter.regexFilter(textofiltro, columnaPorLaQueFiltrar()));
              

             }
         });
     }        
    
    public int columnaPorLaQueFiltrar() {
    
        return jComboBoxFiltros.getSelectedIndex()+1;
    }
    
    
    public static DistanciasFrame getInstance() {
        if (instance == null) {
              instance = new DistanciasFrame();
        }
        return instance;
    
    }   
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
            java.util.logging.Logger.getLogger(DistanciasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DistanciasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DistanciasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DistanciasFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                DistanciasFrame.getInstance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminarGrua1;
    private javax.swing.JButton botonNuevaGrua1;
    private javax.swing.JCheckBox jCheckBoxEdicion;
    private javax.swing.JComboBox<String> jComboBoxFiltros;
    private javax.swing.JComboBox<String> jComboBoxFiltrosContieneOEmpieza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPaneTabla;
    private javax.swing.JTextField jTextFieldFilter;
    private javax.swing.JPanel panelContenedor;
    // End of variables declaration//GEN-END:variables
}

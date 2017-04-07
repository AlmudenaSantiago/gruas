/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.avisos;

import command.imagenes.CargarImagenesCommand;
import command.productos.CargarImagenesProductoCommand;
import java.awt.BorderLayout;
import static java.awt.Color.blue;
import java.awt.Desktop;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Imagen;
import process.CRUDAvisos.AsociarImagenAviso;
import process.CRUDAvisos.EliminarImagenAviso;
import process.CRUDVehiculos.ModificarVehiculo;
import process.cargador.imagenes.CargadorListaImagenes;
import process.parser.imagenes.ImagenParserJson;
import view.detalleVenta.JTablaDetalleVenta;
import view.fichasVehiculos.CargarFoto;
import view.fichasVehiculos.FichaVehiculo;
import view.fichasVehiculos.FileCopy;

/**
 *
 * @author gruasjoseantonio
 */
public class FotosAvisoFrame extends javax.swing.JFrame {

    Integer id;
    List<Imagen> listaImagenes;
     
    
    
    
    
    public FotosAvisoFrame(final Integer id) {
        this.id = id;
        initComponents();
        jLabel1.setText(jLabel1.getText() + " " + id.toString());
        actualizarFrame();
        System.out.println("paso por aqui");
      
        
    }

    public void actualizarFrame() {
        actualizarListaImagenes();
        setPanelesFoto();
    }
   
    public void actualizarListaImagenes() {
        CargarImagenesCommand command = new CargarImagenesCommand(new CargadorListaImagenes(), new ImagenParserJson());
        listaImagenes = new ArrayList<>();
        try {
            listaImagenes = command.executeListaImagenesAviso(id);
            System.out.println("paso por aqui");
        } catch (Exception ex) {
            Logger.getLogger(FotosAvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setPanelesFoto() {
        panel.setLayout(new GridLayout(4,4));
        panel.removeAll();
        for (int i=0; i<listaImagenes.size(); i++) {
            final int iAux = i;
            final String imagen =  listaImagenes.get(i).getImagen();
             JLabel jLabelImagen = new JLabel();
             jLabelImagen.setSize(400, 400);
             
             ImageIcon icon = new ImageIcon(listaImagenes.get(i).getImagen());
             Icon icono = new ImageIcon(icon.getImage().getScaledInstance(jLabelImagen.getWidth(), jLabelImagen.getHeight(), Image.SCALE_DEFAULT));
             jLabelImagen.setText(null);
             
             jLabelImagen.setIcon(icono);
             jLabelImagen.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            if (e.getClickCount() == 2) {
                               // Runtime.getRuntime( ).exec( new String[] { "cmd.exe", "/c", "start", imagen } );
                                // las dos instruccciones sirven pero quizas desktop tambien funcione para mac
                                Desktop.getDesktop().open(new File(imagen));
                            }
                            } catch (IOException ex) {
                            Logger.getLogger(JTablaDetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
             });
             
          
             PanelFoto panelFoto = new PanelFoto(jLabelImagen);
             panelFoto.getBotonEliminar().addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                            Imagen imagen = new Imagen();
                            imagen.setId(listaImagenes.get(iAux).getId());
                            // aprovecho este campo para mandar la id del aviso y no crear otro modelo
                            imagen.setImagen(id.toString());
                            EliminarImagenAviso eliminar = new EliminarImagenAviso(imagen);
                            try {
                                eliminar.eliminar();
                            } catch (Exception ex) {
                                Logger.getLogger(FotosAvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            actualizarFrame();
                                                   
                       
                    }
             });
             panel.add(panelFoto);
             panel.revalidate();
             jScrollPane1.revalidate();
            
        }
    
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fotos Aviso");
        setExtendedState(6);

        panel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1132, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1157, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panel);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 204));
        jLabel1.setText("Imágenes del aviso ");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/avisos/import.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Importar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1139, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                
         int resultado;
         File[] ficheros;

         CargarFoto ventana = new CargarFoto();

         FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG","jpg","png");

         ventana.jFichCargarFoto.setFileFilter(filtro);
         ventana.jFichCargarFoto.setMultiSelectionEnabled(true);

         resultado= ventana.jFichCargarFoto.showOpenDialog(null);
       
          if (JFileChooser.APPROVE_OPTION == resultado){
              Integer contador = 0;
                  ficheros = ventana.jFichCargarFoto.getSelectedFiles();
                  for (File fichero : ficheros) {
                      contador++;
                        // COPIAMOS LA IMAGEN EN EL SERVIDOR Y LA GUARDAMOS EN EL AVISO EN LA BASE DE DATOS TABLA AVISO_IMAGEN
                        FileCopy filecopy = new FileCopy(fichero.getAbsolutePath(), "C:\\xampp\\htdocs\\gruas\\gruas\\imagenes\\avisos\\aviso_" + id + "_imagen_" + contador.toString());
                        String compruebaCopia = filecopy.copiar();
                        if (compruebaCopia.equals("ok")) {
                            Imagen imagen = new Imagen();
                            imagen.setId(id);
                            imagen.setImagen("C:\\xampp\\htdocs\\gruas\\gruas\\imagenes\\avisos\\aviso_" + id + "_imagen_" + contador.toString());
                            AsociarImagenAviso asociar = new AsociarImagenAviso(imagen);
                            try {
                                asociar.asociar();
                            } catch (Exception ex) {
                                Logger.getLogger(FotosAvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            actualizarFrame();
                            
                        }
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
            java.util.logging.Logger.getLogger(FotosAvisoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FotosAvisoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FotosAvisoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FotosAvisoFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FotosAvisoFrame(34).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}

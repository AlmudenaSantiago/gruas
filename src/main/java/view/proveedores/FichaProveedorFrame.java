
package view.proveedores;



import command.proveedores.CargarProveedoresCommand;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Ficha;
import model.Proveedor;
import process.cargador.CargadorListaProveedor;

import process.parser.proveedor.ProveedorParserJson;
import view.ModificarFrames.ModificarCorreoFrame;
import view.ModificarFrames.ModificarCpFrame;
import view.ModificarFrames.ModificarDomicilioFrame;
import view.ModificarFrames.ModificarNifFrame;
import view.ModificarFrames.ModificarNombreFrame;
import view.ModificarFrames.ModificarPaisFrame;
import view.ModificarFrames.ModificarPoblacionFrame;
import view.ModificarFrames.ModificarProvinciaFrame;
import view.ModificarFrames.ModificarTelefono1Frame;
import view.ModificarFrames.ModificarTelefono2Frame;




public class FichaProveedorFrame extends Ficha {

    
    List<Proveedor> proveedores;
    Proveedor proveedorConsultado;
    
    public FichaProveedorFrame(Integer id) {
        super(id);
        System.out.println(id);
        this.setVisible(true);
        initComponents();
        consultar();
        actualizar();
        setListenerEtiquetas();
        this.setVisible(true);
    }

    
     @Override
   public void consultar () {
        try {
            proveedores = new CargarProveedoresCommand(new CargadorListaProveedor(), new ProveedorParserJson()).executeProveedor(getId());
            proveedorConsultado = proveedores.get(0);
           
        } catch (IOException ex) {
            Logger.getLogger(FichaProveedorFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
          
   }
   
   
     @Override
   public void actualizar () {
        jLabelNombre.setText(proveedorConsultado.getNombre());
        jLabelNIF.setText(proveedorConsultado.getNif());
        jLabelDomicilio.setText(proveedorConsultado.getDomicilio());
        jLabelPoblacion.setText(proveedorConsultado.getPoblacion());
        jLabelProvincia.setText(proveedorConsultado.getProvincia());
        jLabelCP.setText(proveedorConsultado.getCp());
        jLabelTelefono1.setText(proveedorConsultado.getTelefono1());
        jLabelTelefono2.setText(proveedorConsultado.getTelefono2());
        jLabelCorreo.setText(proveedorConsultado.getCorreo());
        jLabelPais.setText(proveedorConsultado.getPais());
        validate();
        repaint();
   
   }
    
   public void setListenerEtiquetas () {
          setListenerEtiquetaNombre();
          setListenerEtiquetaPais();
          setListenerEtiquetaNIF();
          setListenerEtiquetaDomicilio();
          setListenerEtiquetaProvincia();
          setListenerEtiquetaCP();
          setListenerEtiquetaPoblacion();
          setListenerEtiquetaTelefono1();
          setListenerEtiquetaTelefono2();
          setListenerEtiquetaCorreo();
      
   }
   
  

   
   public void setListenerEtiquetaNombre () {
      jLabelNombreProveedor.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {     
                                              try {
                                                  ModificarNombreFrame mnf = new ModificarNombreFrame(getId(), "proveedor", null);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ProveedoresFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el nombre: " + ex);
                                              }
                                             
                                 }
                             }
                        
                    });
   
   
   }
   
    
     
     public void setListenerEtiquetaNIF () {
      jLabelNIFProveedor.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {   
                            try {
                                                  ModificarNifFrame mnf = new ModificarNifFrame(getId(), "proveedor", FichaProveedorFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ProveedoresFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el nif: " + ex);
                                              }
                        }}
                    });
   
   
   }
     
     public void setListenerEtiquetaPais () {
               jLabelPaisProveedor.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {   
                            try {
                                                  ModificarPaisFrame mnf = new ModificarPaisFrame(getId(), "proveedor", FichaProveedorFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ProveedoresFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el nif: " + ex);
                                              }
                        }}
                    });
   
   
   }
   
     public void setListenerEtiquetaDomicilio () {
      jLabelDomicilioProveedor.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {      
                                     try {
                                                  ModificarDomicilioFrame mnf = new ModificarDomicilioFrame(getId(), "proveedor", FichaProveedorFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ProveedoresFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el domicilio: " + ex);
                                              }
                        }}
                    });
   
   
   }
     
     
      public void setListenerEtiquetaPoblacion () {
      jLabelPoblacionProveedor.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {     
                            try {
                                                  ModificarPoblacionFrame mnf = new ModificarPoblacionFrame(getId(), "proveedor",FichaProveedorFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ProveedoresFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar la población: " + ex);
                                              }
                        }}
                    });
   
   
   }
     
       public void setListenerEtiquetaCP () {
      jLabelCPProveedor.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {  
                            try {
                                                  ModificarCpFrame mnf = new ModificarCpFrame(getId(), "proveedor",FichaProveedorFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ProveedoresFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el cod. postal: " + ex);
                                              }
                        }}
                    });
   
   
   }
       
       
       
       public void setListenerEtiquetaProvincia () {
           jLabelProvinciaProveedor.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {     
                            try {
                                                  ModificarProvinciaFrame mnf = new ModificarProvinciaFrame(getId(), "proveedor",FichaProveedorFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ProveedoresFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar la provincia: " + ex);
                                              }
                        }}
                    });
   
   
   }
       
         public void setListenerEtiquetaTelefono1() {
          jLabelTelefono1Proveedor.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {     
                            try {
                                                  ModificarTelefono1Frame mnf = new ModificarTelefono1Frame(getId(), "proveedor", FichaProveedorFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ProveedoresFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el telefono: " + ex);
                                              }
                        }}
                    });
   
   
   }
         
    public void setListenerEtiquetaTelefono2() {
      jLabelTelefono2Proveedor.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {    
                            try {
                                                  ModificarTelefono2Frame mnf = new ModificarTelefono2Frame(getId(), "proveedor",FichaProveedorFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ProveedoresFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el telefono: " + ex);
                                              }
                        }}
                    });
   
   
   }
     
     
    
            
         
    public void setListenerEtiquetaCorreo() {
      jLabelCorreoProveedor.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                          if  (e.getClickCount() == 2) { 
                            try {
                                                  ModificarCorreoFrame mnf = new ModificarCorreoFrame(getId(), "proveedor",FichaProveedorFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ProveedoresFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el correo: " + ex);
                     }      }                 }
                    });
       }
   
        
     
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNombreProveedor = new javax.swing.JLabel();
        jLabelDescripcionProducto = new javax.swing.JLabel();
        jLabelDomicilioProveedor = new javax.swing.JLabel();
        jLabelPoblacionProveedor = new javax.swing.JLabel();
        jLabelProvinciaProveedor = new javax.swing.JLabel();
        jLabelTelefono1Proveedor = new javax.swing.JLabel();
        jLabelTelefono2Proveedor = new javax.swing.JLabel();
        jLabelCPProveedor = new javax.swing.JLabel();
        jLabelNIFProveedor = new javax.swing.JLabel();
        jLabelPaisProveedor = new javax.swing.JLabel();
        jLabelCorreoProveedor = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabelNombre = new javax.swing.JLabel();
        jLabelNIF = new javax.swing.JLabel();
        jLabelTelefono1 = new javax.swing.JLabel();
        jLabelTelefono2 = new javax.swing.JLabel();
        jLabelCorreo = new javax.swing.JLabel();
        jLabelDomicilio = new javax.swing.JLabel();
        jLabelPoblacion = new javax.swing.JLabel();
        jLabelCP = new javax.swing.JLabel();
        jLabelProvincia = new javax.swing.JLabel();
        jLabelPais = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setResizable(false);

        jLabelNombreProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelNombreProveedor.setText("Nombre:");

        jLabelDomicilioProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelDomicilioProveedor.setText("Domicilio:");

        jLabelPoblacionProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPoblacionProveedor.setText("Poblacion:");

        jLabelProvinciaProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelProvinciaProveedor.setText("Provincia:");

        jLabelTelefono1Proveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTelefono1Proveedor.setText("Telefono 1:");

        jLabelTelefono2Proveedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTelefono2Proveedor.setText("Telefono 2:");

        jLabelCPProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCPProveedor.setText("Cod.Postal:");

        jLabelNIFProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelNIFProveedor.setText("NIF:");

        jLabelPaisProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPaisProveedor.setText("País:");

        jLabelCorreoProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCorreoProveedor.setText("Correo:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("FICHA PROVEEDOR");

        jLabelNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNombre.setText("jLabel2");

        jLabelNIF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNIF.setText("jLabel3");

        jLabelTelefono1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTelefono1.setText("jLabel10");

        jLabelTelefono2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTelefono2.setText("jLabel11");

        jLabelCorreo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCorreo.setText("jLabel12");

        jLabelDomicilio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDomicilio.setText("jLabel13");

        jLabelPoblacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelPoblacion.setText("jLabel14");

        jLabelCP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCP.setText("jLabel15");

        jLabelProvincia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelProvincia.setText("jLabel16");

        jLabelPais.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelPais.setText("jLabel17");

        jLabel2.setText("* Para modificar algun campo pulsa sobre su identificador");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelProvinciaProveedor)
                        .addGap(171, 171, 171)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCPProveedor)
                            .addComponent(jLabelPaisProveedor))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabelCP))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabelPais, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelDomicilioProveedor)
                                    .addComponent(jLabelPoblacionProveedor)
                                    .addComponent(jLabelDescripcionProducto))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(29, 29, 29)
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabelProvincia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                                .addComponent(jLabelPoblacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabelDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTelefono1Proveedor)
                                    .addComponent(jLabelCorreoProveedor)
                                    .addComponent(jLabelNIFProveedor))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabelNIF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabelCorreo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(27, 27, 27)
                                            .addComponent(jLabelTelefono2Proveedor)
                                            .addGap(28, 28, 28)
                                            .addComponent(jLabelTelefono2)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelNombreProveedor)))
                .addGap(0, 57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDescripcionProducto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreProveedor)
                    .addComponent(jLabelNombre))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNIF)
                    .addComponent(jLabelNIFProveedor))
                .addGap(27, 27, 27)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefono1Proveedor)
                    .addComponent(jLabelTelefono2Proveedor)
                    .addComponent(jLabelTelefono1)
                    .addComponent(jLabelTelefono2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCorreoProveedor)
                    .addComponent(jLabelCorreo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDomicilioProveedor)
                    .addComponent(jLabelDomicilio))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPoblacionProveedor)
                    .addComponent(jLabelCPProveedor)
                    .addComponent(jLabelPoblacion)
                    .addComponent(jLabelCP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelProvinciaProveedor)
                        .addComponent(jLabelProvincia))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelPaisProveedor)
                        .addComponent(jLabelPais)))
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelCP;
    private javax.swing.JLabel jLabelCPProveedor;
    private javax.swing.JLabel jLabelCorreo;
    private javax.swing.JLabel jLabelCorreoProveedor;
    private javax.swing.JLabel jLabelDescripcionProducto;
    private javax.swing.JLabel jLabelDomicilio;
    private javax.swing.JLabel jLabelDomicilioProveedor;
    private javax.swing.JLabel jLabelNIF;
    private javax.swing.JLabel jLabelNIFProveedor;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombreProveedor;
    private javax.swing.JLabel jLabelPais;
    private javax.swing.JLabel jLabelPaisProveedor;
    private javax.swing.JLabel jLabelPoblacion;
    private javax.swing.JLabel jLabelPoblacionProveedor;
    private javax.swing.JLabel jLabelProvincia;
    private javax.swing.JLabel jLabelProvinciaProveedor;
    private javax.swing.JLabel jLabelTelefono1;
    private javax.swing.JLabel jLabelTelefono1Proveedor;
    private javax.swing.JLabel jLabelTelefono2;
    private javax.swing.JLabel jLabelTelefono2Proveedor;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
}

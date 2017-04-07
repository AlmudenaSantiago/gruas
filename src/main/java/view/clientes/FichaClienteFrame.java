
package view.clientes;

import command.clientes.CargarClientesCommand;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Ficha;
import process.cargador.CargadorListaCliente;
import process.cargador.CargadorTotales;
import process.parser.clientes.ClienteParserJson;
import view.ModificarFrames.ModificarApellidosFrame;
import view.ModificarFrames.ModificarCorreoFrame;
import view.ModificarFrames.ModificarCpFrame;
import view.ModificarFrames.ModificarDomicilioFrame;
import view.ModificarFrames.ModificarNifFrame;
import view.ModificarFrames.ModificarNombreFrame;
import view.ModificarFrames.ModificarPoblacionFrame;
import view.ModificarFrames.ModificarProvinciaFrame;
import view.ModificarFrames.ModificarTelefono1Frame;
import view.ModificarFrames.ModificarTelefono2Frame;


public class FichaClienteFrame extends Ficha  {
  
     List<Cliente> clientes;
     Cliente clienteConsultado;
     
     
    public FichaClienteFrame(Integer id) {
        super(id);
        initComponents();
        consultar();
        actualizar();
        setListenerEtiquetas();
        this.setVisible(true);
      
    }


     @Override
   public void consultar () {
         try {
             clientes = new CargarClientesCommand(new CargadorListaCliente(), new ClienteParserJson()).executeCliente(getId());
         } catch (Exception ex) {
             Logger.getLogger(FichaClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
         clienteConsultado = clientes.get(0);
   }
   
   
     @Override
   public void actualizar () {
        jLabelNombre.setText(clienteConsultado.getNombre());
        jLabelApellidos.setText(clienteConsultado.getApellidos());
        jLabelNIF.setText(clienteConsultado.getNif());
        jLabelDomicilio.setText(clienteConsultado.getDomicilio());
        jLabelPoblacion.setText(clienteConsultado.getPoblacion());
        jLabelProvincia.setText(clienteConsultado.getProvincia());
        jLabelCP.setText(clienteConsultado.getCp());
        jLabelTelefono1.setText(clienteConsultado.getTelefono1());
        jLabelTelefono2.setText(clienteConsultado.getTelefono2());
        jLabelCorreo.setText(clienteConsultado.getCorreo());
        jLabelCantidadPendiente.setText(CargadorTotales.getInstance().cargarTotalCantidadPendiente(getId()));
        jLabelCantidadAbonada.setText(CargadorTotales.getInstance().cargarTotalCantidadAbonada(getId()));
        validate();
        repaint();
   
   }
    
   public void setListenerEtiquetas () {
          setListenerEtiquetaNombre();
          setListenerEtiquetaApellidos();
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
      jLabelNombreProducto.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {     
                                             try {
                                      
                                                  ModificarNombreFrame mnf = new ModificarNombreFrame(getId(), "cliente", FichaClienteFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ClientesFrame.getInstance());
                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el nombre: " + ex);
                                              }
                                 }
                        }
                    });
   
   
   }
   
     public void setListenerEtiquetaApellidos () {
      jLabel3.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                                 if  (e.getClickCount() == 2) {  
                               try {
                                                  ModificarApellidosFrame mnf = new ModificarApellidosFrame(getId(),"cliente",FichaClienteFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ClientesFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar los apellidos: " + ex);
                                              }
                                            
                                            
                        }}
                    });
   
   
   }
     
     
     public void setListenerEtiquetaNIF () {
      jLabel9.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {   
                            try {
                                                  ModificarNifFrame mnf = new ModificarNifFrame(getId(), "cliente", FichaClienteFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ClientesFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el nif: " + ex);
                                              }
                        }}
                    });
   
   
   }
   
     public void setListenerEtiquetaDomicilio () {
      jLabelURL.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {      
                                     try {
                                                  ModificarDomicilioFrame mnf = new ModificarDomicilioFrame(getId(), "cliente",FichaClienteFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ClientesFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el domicilio: " + ex);
                                              }
                        }}
                    });
   
   
   }
     
     
      public void setListenerEtiquetaPoblacion () {
      jLabel4.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {     
                            try {
                                                  ModificarPoblacionFrame mnf = new ModificarPoblacionFrame(getId(), "cliente",FichaClienteFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ClientesFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar la población: " + ex);
                                              }
                        }}
                    });
   
   
   }
     
       public void setListenerEtiquetaCP () {
      jLabel8.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {  
                            try {
                                                  ModificarCpFrame mnf = new ModificarCpFrame(getId(), "cliente",FichaClienteFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ClientesFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el cod. postal: " + ex);
                                              }
                        }}
                    });
   
   
   }
       
       
       
       public void setListenerEtiquetaProvincia () {
      jLabel5.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {     
                            try {
                                                  ModificarProvinciaFrame mnf = new ModificarProvinciaFrame(getId(), "cliente",FichaClienteFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ClientesFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar la provincia: " + ex);
                                              }
                        }}
                    });
   
   
   }
       
         public void setListenerEtiquetaTelefono1() {
      jLabel6.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {     
                            try {
                                                  ModificarTelefono1Frame mnf = new ModificarTelefono1Frame(getId(), "cliente", FichaClienteFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ClientesFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el telefono: " + ex);
                                              }
                        }}
                    });
   
   
   }
         
    public void setListenerEtiquetaTelefono2() {
      jLabel7.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {    
                            try {
                                                  ModificarTelefono2Frame mnf = new ModificarTelefono2Frame(getId(), "cliente",FichaClienteFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ClientesFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el telefono: " + ex);
                                              }
                        }}
                    });
   
   
   }
     
     
    
            
         
    public void setListenerEtiquetaCorreo() {
      jLabel1.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                          if  (e.getClickCount() == 2) { 
                            try {
                                                  ModificarCorreoFrame mnf = new ModificarCorreoFrame(getId(), "cliente",FichaClienteFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(CRUD_ClientesFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el correo: " + ex);
                     }      }                 }
                    });
       }
   
        
     
   
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNombreProducto = new javax.swing.JLabel();
        jLabelDescripcionProducto = new javax.swing.JLabel();
        jLabelURL = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabelNombre = new javax.swing.JLabel();
        jLabelApellidos = new javax.swing.JLabel();
        jLabelNIF = new javax.swing.JLabel();
        jLabelDomicilio = new javax.swing.JLabel();
        jLabelPoblacion = new javax.swing.JLabel();
        jLabelProvincia = new javax.swing.JLabel();
        jLabelCP = new javax.swing.JLabel();
        jLabelTelefono1 = new javax.swing.JLabel();
        jLabelTelefono2 = new javax.swing.JLabel();
        jLabelCorreo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelCantidadPendiente = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabelCantidadAbonada = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();

        setResizable(false);

        jLabelNombreProducto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelNombreProducto.setText("Nombre:");

        jLabelURL.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabelURL.setText("Domicilio:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Apellidos:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Poblacion:");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Provincia:");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Telefono 1:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Telefono 2:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Cod.Postal:");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("NIF:");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Correo:");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 255));
        jLabel10.setText("FICHA CLIENTE");

        jLabelNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelNombre.setText("jLael22222222222222222222222222222222222222222222222222222222222222222222222222222222222");
        jLabelNombre.setSize(300, 200);
        jLabelApellidos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelApellidos.setText("jLabel1111111111111111111111111111111111222222222222222222222222222222222222222222222");

        jLabelNIF.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelNIF.setText("jLabel1222222222222222222222222222222222222222222222222222222222222222222222222222222222222");

        jLabelDomicilio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelDomicilio.setText("jLabel1333333333333333333333333333333333");

        jLabelPoblacion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelPoblacion.setText("jLabel14444444444444444444444444444444444");

        jLabelProvincia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelProvincia.setText("jLabel15555555555555555555555555555555555");

        jLabelCP.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelCP.setText("jLabel16");

        jLabelTelefono1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelTelefono1.setText("jLabel17");

        jLabelTelefono2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelTelefono2.setText("jLabel18");

        jLabelCorreo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelCorreo.setText("jLabel19");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 51));
        jLabel2.setText("Cantidad Total Pendiente:");

        jLabelCantidadPendiente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelCantidadPendiente.setText("jLabel11");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 255));
        jLabel12.setText("Cantidad Total Abonada:");

        jLabelCantidadAbonada.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabelCantidadAbonada.setText("jLabel13");

        jLabel11.setText("* Para modificar algun campo pulsa sobre su identificador");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelDescripcionProducto)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelCantidadAbonada))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(55, 55, 55)
                                    .addComponent(jLabelCantidadPendiente)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelNombreProducto)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel9))
                                    .addGap(37, 37, 37)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelNIF)
                                        .addComponent(jLabelApellidos)
                                        .addComponent(jLabelNombre)))
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabelTelefono1))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel1))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelCorreo)
                                        .addComponent(jLabelTelefono2)))
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelURL)
                                        .addComponent(jLabel4))
                                    .addGap(31, 31, 31)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelPoblacion)
                                        .addComponent(jLabelDomicilio)
                                        .addComponent(jLabelProvincia)
                                        .addComponent(jLabelCP)))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(jLabel10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 181, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(175, 175, 175))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(19, 19, 19)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreProducto)
                    .addComponent(jLabelNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelApellidos))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabelNIF))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelDescripcionProducto))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelURL)
                            .addComponent(jLabelDomicilio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabelPoblacion))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabelProvincia))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabelCP))
                        .addGap(28, 28, 28)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTelefono1)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabelTelefono2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabelCorreo))))
                .addGap(36, 36, 36)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelCantidadPendiente))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabelCantidadAbonada))
                .addGap(30, 30, 30)
                .addComponent(jLabel11)
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelApellidos;
    private javax.swing.JLabel jLabelCP;
    private javax.swing.JLabel jLabelCantidadAbonada;
    private javax.swing.JLabel jLabelCantidadPendiente;
    private javax.swing.JLabel jLabelCorreo;
    private javax.swing.JLabel jLabelDescripcionProducto;
    private javax.swing.JLabel jLabelDomicilio;
    private javax.swing.JLabel jLabelNIF;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombreProducto;
    private javax.swing.JLabel jLabelPoblacion;
    private javax.swing.JLabel jLabelProvincia;
    private javax.swing.JLabel jLabelTelefono1;
    private javax.swing.JLabel jLabelTelefono2;
    private javax.swing.JLabel jLabelURL;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables
}

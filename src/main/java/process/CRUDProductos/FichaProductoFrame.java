
package process.CRUDProductos;



import command.productos.CargarDiasFestivosCommand;
import view.proveedores.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Ficha;
import model.Producto;
import model.Proveedor;
import process.cargador.CargadorListaProducto;
import process.parser.productos.ProductoParserJson;
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







/***************************************************************************************************************************/

//  *********************************************** ESTA CLASE DE MOMENTO NO SE USA ***************************************//

/***************************************************************************************************************************/

public class FichaProductoFrame extends Ficha {

   
    List<Producto> productos;
    Proveedor productoConsultado;
    
    public FichaProductoFrame(Integer id) {
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
            productos = new CargarDiasFestivosCommand(new CargadorListaProducto(), new ProductoParserJson()).executeProducto(getId());
            productoConsultado = productos.get(0);
           
        } catch (IOException ex) {
            Logger.getLogger(FichaProductoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
          
   }
   
   
     @Override
   public void actualizar () {
        jLabelNombre.setText(productoConsultado.getNombre());
        jLabelProveedor.setText(productoConsultado.getNif());
        jLabelAcabado.setText(productoConsultado.getDomicilio());
        jLabelPrecioCompra.setText(productoConsultado.getPoblacion());
        jLabelImagen.setText(productoConsultado.getProvincia());
        jLabelCP.setText(productoConsultado.getCp());
        jLabelCategoria1.setText(productoConsultado.getTelefono1());
        jLabelCategoria2.setText(productoConsultado.getTelefono2());
        jLabelCategoria4Producto.setText(productoConsultado.getCorreo());
        jLabelDescripcion.setText(productoConsultado.getPais());
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
      jLabelProveedorProducto.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {   
                            try {
                                                  ModificarNifFrame mnf = new ModificarNifFrame(getId(), "proveedor", FichaProductoFrame.this);
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
                                                  ModificarPaisFrame mnf = new ModificarPaisFrame(getId(), "proveedor", FichaProductoFrame.this);
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
      jLabelAcabadoProducto.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {      
                                     try {
                                                  ModificarDomicilioFrame mnf = new ModificarDomicilioFrame(getId(), "proveedor", FichaProductoFrame.this);
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
      jLabelPrecioCompraProveedor.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {     
                            try {
                                                  ModificarPoblacionFrame mnf = new ModificarPoblacionFrame(getId(), "proveedor",FichaProductoFrame.this);
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
      jLabelPrecioVentaProducto.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {  
                            try {
                                                  ModificarCpFrame mnf = new ModificarCpFrame(getId(), "proveedor",FichaProductoFrame.this);
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
           jLabelImagenProducto.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {     
                            try {
                                                  ModificarProvinciaFrame mnf = new ModificarProvinciaFrame(getId(), "proveedor",FichaProductoFrame.this);
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
          jLabelCategoria1Producto.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {     
                            try {
                                                  ModificarTelefono1Frame mnf = new ModificarTelefono1Frame(getId(), "proveedor", FichaProductoFrame.this);
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
                                                  ModificarTelefono2Frame mnf = new ModificarTelefono2Frame(getId(), "proveedor",FichaProductoFrame.this);
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
      jLabelCategoria3Producto.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                          if  (e.getClickCount() == 2) { 
                            try {
                                                  ModificarCorreoFrame mnf = new ModificarCorreoFrame(getId(), "proveedor",FichaProductoFrame.this);
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
        jLabelAcabadoProducto = new javax.swing.JLabel();
        jLabelPrecioCompraProveedor = new javax.swing.JLabel();
        jLabelImagenProducto = new javax.swing.JLabel();
        jLabelCategoria1Producto = new javax.swing.JLabel();
        jLabelTelefono2Proveedor = new javax.swing.JLabel();
        jLabelPrecioVentaProducto = new javax.swing.JLabel();
        jLabelProveedorProducto = new javax.swing.JLabel();
        jLabelPaisProveedor = new javax.swing.JLabel();
        jLabelCategoria3Producto = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabelNombre = new javax.swing.JLabel();
        jLabelProveedor = new javax.swing.JLabel();
        jLabelCategoria1 = new javax.swing.JLabel();
        jLabelCategoria2 = new javax.swing.JLabel();
        jLabelCategoria4Producto = new javax.swing.JLabel();
        jLabelAcabado = new javax.swing.JLabel();
        jLabelPrecioCompra = new javax.swing.JLabel();
        jLabelCP = new javax.swing.JLabel();
        jLabelImagen = new javax.swing.JLabel();
        jLabelDescripcion = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelCategoria3 = new javax.swing.JLabel();
        jLabelCategoria4 = new javax.swing.JLabel();
        jLabelStockProducto = new javax.swing.JLabel();
        jLabelStock = new javax.swing.JLabel();

        setResizable(false);

        jLabelNombreProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelNombreProveedor.setText("Nombre:");

        jLabelAcabadoProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelAcabadoProducto.setText("Acabado:");

        jLabelPrecioCompraProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPrecioCompraProveedor.setText("Precio compra:");

        jLabelImagenProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelImagenProducto.setText("URL imagen:");

        jLabelCategoria1Producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCategoria1Producto.setText("Categoria 1:");

        jLabelTelefono2Proveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTelefono2Proveedor.setText("Categoria 2:");

        jLabelPrecioVentaProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPrecioVentaProducto.setText("Precio venta:");

        jLabelProveedorProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelProveedorProducto.setText("Proveedor:");

        jLabelPaisProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPaisProveedor.setText("Descripción:");

        jLabelCategoria3Producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCategoria3Producto.setText("Categoria 3:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("FICHA PRODUCTO");

        jLabelNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelNombre.setText("jLabel2");

        jLabelProveedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelProveedor.setText("jLabel3");

        jLabelCategoria1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCategoria1.setText("jLabel10");

        jLabelCategoria2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCategoria2.setText("jLabel11");

        jLabelCategoria4Producto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCategoria4Producto.setText("Categoria 4:");

        jLabelAcabado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelAcabado.setText("jLabel13");

        jLabelPrecioCompra.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelPrecioCompra.setText("jLabel14");

        jLabelCP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCP.setText("jLabel15");

        jLabelImagen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelImagen.setText("jLabel16");

        jLabelDescripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDescripcion.setText("jLabel17");

        jLabel2.setText("* Para modificar algun campo pulsa sobre su identificador");

        jLabelCategoria3.setText("jLabel3");

        jLabelCategoria4.setText("jLabel3");

        jLabelStockProducto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelStockProducto.setText("Stock:");

        jLabelStock.setText("jLabel4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(193, 193, 193))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelProveedorProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelCategoria4Producto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelCategoria3Producto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelAcabadoProducto)))
                .addGap(36, 36, 36))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPrecioCompraProveedor)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelDescripcionProducto)
                                .addGap(132, 132, 132)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelNombreProveedor)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelPaisProveedor)
                                    .addComponent(jLabelCategoria1Producto)
                                    .addComponent(jLabelTelefono2Proveedor)
                                    .addComponent(jLabelStockProducto)
                                    .addComponent(jLabelImagenProducto))
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelStock)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabelDescripcion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabelCategoria4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabelCategoria1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                        .addComponent(jLabelCategoria2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelCategoria3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabelAcabado, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabelPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(31, 31, 31)
                                            .addComponent(jLabelPrecioVentaProducto)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabelCP))
                                        .addComponent(jLabelNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 57, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPaisProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAcabadoProducto)
                    .addComponent(jLabelAcabado))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelImagenProducto)
                    .addComponent(jLabelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCategoria1Producto)
                    .addComponent(jLabelCategoria1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefono2Proveedor)
                    .addComponent(jLabelCategoria2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCategoria3Producto)
                    .addComponent(jLabelCategoria3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCategoria4Producto)
                    .addComponent(jLabelCategoria4))
                .addGap(35, 35, 35)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrecioCompraProveedor)
                    .addComponent(jLabelPrecioCompra)
                    .addComponent(jLabelPrecioVentaProducto)
                    .addComponent(jLabelCP))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelStockProducto)
                    .addComponent(jLabelStock))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProveedorProducto)
                    .addComponent(jLabelProveedor))
                .addGap(114, 114, 114)
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
    private javax.swing.JLabel jLabelAcabado;
    private javax.swing.JLabel jLabelAcabadoProducto;
    private javax.swing.JLabel jLabelCP;
    private javax.swing.JLabel jLabelCategoria1;
    private javax.swing.JLabel jLabelCategoria1Producto;
    private javax.swing.JLabel jLabelCategoria2;
    private javax.swing.JLabel jLabelCategoria3;
    private javax.swing.JLabel jLabelCategoria3Producto;
    private javax.swing.JLabel jLabelCategoria4;
    private javax.swing.JLabel jLabelCategoria4Producto;
    private javax.swing.JLabel jLabelDescripcion;
    private javax.swing.JLabel jLabelDescripcionProducto;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabelImagenProducto;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombreProveedor;
    private javax.swing.JLabel jLabelPaisProveedor;
    private javax.swing.JLabel jLabelPrecioCompra;
    private javax.swing.JLabel jLabelPrecioCompraProveedor;
    private javax.swing.JLabel jLabelPrecioVentaProducto;
    private javax.swing.JLabel jLabelProveedor;
    private javax.swing.JLabel jLabelProveedorProducto;
    private javax.swing.JLabel jLabelStock;
    private javax.swing.JLabel jLabelStockProducto;
    private javax.swing.JLabel jLabelTelefono2Proveedor;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables

    @Override
    public void consultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


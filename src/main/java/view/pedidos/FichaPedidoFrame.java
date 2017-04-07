
package view.pedidos;



import com.itextpdf.text.DocumentException;
import command.clientes.CargarClientesCommand;
import command.pedidos.CargarPedidosCommand;
import command.pedidos.CargarVentaCommand;
import command.productos.CargarProductosPedidoCommand;
import static java.awt.Frame.MAXIMIZED_BOTH;
import view.proveedores.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import model.Cliente;
import model.Ficha;
import model.Pedido;
import process.PDFs.GenerarDevolucion;
import process.cargador.CargadorListaCliente;
import process.cargador.CargadorListaPedido;
import process.cargador.CargadorListaProducto;
import process.cargador.CargadorProductosPedido;
import process.parser.clientes.ClienteParserJson;
import process.parser.pedidos.PedidoParserJson;
import process.parser.productos.ProductoParserJson;
import process.parser.productos.ProductoPedidoParserJson;

import view.ModificarFrames.ModificarCorreoFrame;
import view.ModificarFrames.ModificarDescuentoFrame;
import view.ModificarFrames.ModificarDomicilioFrame;
import view.ModificarFrames.ModificarNifFrame;
import view.ModificarFrames.ModificarNombreFrame;
import view.ModificarFrames.ModificarTelefono1Frame;
import view.ModificarFrames.ModificarTelefono2Frame;
import view.SelectorDeRuta;
import view.detallesPedidoEnFicha.JTablaPedido;
import view.venta.ActualizarVentaFrame;
import view.venta.JTablaVenta;
import view.venta.ModificarVentaFrame;
import view.venta.VentaFrame;




public class FichaPedidoFrame extends Ficha {

    
    List<Pedido> pedidos;
    Pedido pedidoConsultado;
    JTablaPedido jTablaPedido;
    CargarProductosPedidoCommand cargarProductosPedidoCommand;
    JScrollPane panelScrollTabla = new JScrollPane();
    
    public FichaPedidoFrame(Integer id) throws IOException {
        super(id);
        System.out.println(id);
        this.setVisible(true);
        initComponents();
        jTablaPedido = new JTablaPedido(id, FichaPedidoFrame.this);
        setLocationRelativeTo(MainFrame.getInstance());
        cargarProductosPedidoCommand = new CargarProductosPedidoCommand(new ProductoPedidoParserJson(), CargadorProductosPedido.getInstance());
        jTablaPedido.setCargarProductosPedidoCommand(cargarProductosPedidoCommand);
        cargarProductosPedidoCommand.executeTablaEnFicha(id, jTablaPedido);
        
        panelScrollTabla.setViewportView(jTablaPedido); 
        scrollPaneTabla.add(panelScrollTabla);
        setListenerBotonMasProductos();
        setListenerEtiquetaDescuento();
        setListenerEtiquetaCantidadPendiente();
       
        
        consultar();
        actualizar();
        //setListenerEtiquetas();
        this.setVisible(true);
       
        
    }

    public JTablaPedido getjTablaPedido() {
        return jTablaPedido;
    }

    
    
    
    
   @Override
   public void consultar () {
        try {
           pedidoConsultado  = new CargarPedidosCommand(new CargadorListaPedido(), new PedidoParserJson()).executePedido(getId());
        } catch (IOException ex) {
            Logger.getLogger(FichaPedidoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    public Pedido getPedidoConsultado() {
        return pedidoConsultado;
    }
   
   
   
     @Override
   public void actualizar () {
        jLabelId.setText(pedidoConsultado.getId().toString());
        jLabelCliente.setText(pedidoConsultado.getCliente());
        jLabelVendedor.setText(pedidoConsultado.getVendedor());
        
        jLabelImporte.setText(redondear(pedidoConsultado.getImporte()).toString());
        Double importeFinal = pedidoConsultado.getImporte()- Double.parseDouble(pedidoConsultado.getDescuento());
        jLabelImporteFinal.setText(redondear(importeFinal).toString());
       
        Double descuentoRedondeado = redondear(Double.parseDouble(pedidoConsultado.getDescuento()));
        jLabelDescuento.setText(descuentoRedondeado.toString());
        jLabelCantidadPendiente.setText(redondear(pedidoConsultado.getCantidadPendiente()).toString());
        cargarProductosPedidoCommand = new CargarProductosPedidoCommand(new ProductoPedidoParserJson(), CargadorProductosPedido.getInstance());
        jTablaPedido.setCargarProductosPedidoCommand(cargarProductosPedidoCommand);
        cargarProductosPedidoCommand.executeTablaEnFicha(getId(), jTablaPedido);
        validate();
        repaint();
   
   }
    

public Double redondear(Double numero)
{
       return Math.rint(numero*100)/100;
}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNombreProveedor = new javax.swing.JLabel();
        jLabelDescripcionProducto = new javax.swing.JLabel();
        jLabelDomicilioProveedor = new javax.swing.JLabel();
        jLabelTelefono1Proveedor = new javax.swing.JLabel();
        jLabelTelefono2Proveedor = new javax.swing.JLabel();
        jLabelNIFProveedor = new javax.swing.JLabel();
        jLabelDescuentoPedido = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabelId = new javax.swing.JLabel();
        jLabelCliente = new javax.swing.JLabel();
        jLabelImporte = new javax.swing.JLabel();
        jLabelImporteFinal = new javax.swing.JLabel();
        jLabelDescuento = new javax.swing.JLabel();
        jLabelVendedor = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelCantidad = new javax.swing.JLabel();
        jLabelCantidadPendiente = new javax.swing.JLabel();
        scrollPaneTabla = new java.awt.ScrollPane();
        jButtonMasProductos = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setResizable(false);

        jLabelNombreProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelNombreProveedor.setText("Id:");

        jLabelDomicilioProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelDomicilioProveedor.setText("Vendedor:");

        jLabelTelefono1Proveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTelefono1Proveedor.setText("Importe:");

        jLabelTelefono2Proveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTelefono2Proveedor.setText("Importe Final:");

        jLabelNIFProveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelNIFProveedor.setText("Cliente:");

        jLabelDescuentoPedido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelDescuentoPedido.setText("Descuento:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("FICHA PEDIDO");

        jLabelId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelId.setText("id");

        jLabelCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelCliente.setText("cliente");

        jLabelImporte.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelImporte.setText("jLabel10");

        jLabelImporteFinal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelImporteFinal.setText("jLabel11");

        jLabelDescuento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelDescuento.setText("jLabel12");

        jLabelVendedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelVendedor.setText("vendedor");

        jLabel2.setText("* Para modificar el descuento pulsa el identificador \"Descuento\"");

        jLabelCantidad.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCantidad.setText("Cantidad Pendiente:");

        jLabelCantidadPendiente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCantidadPendiente.setText("jLabel11");

        jButtonMasProductos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonMasProductos.setText("Añadir productos");
        jButtonMasProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMasProductosActionPerformed(evt);
            }
        });

        jButton1.setText("Generar PDF Abono");
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelNIFProveedor)
                                    .addComponent(jLabelNombreProveedor)
                                    .addComponent(jLabelDomicilioProveedor)
                                    .addComponent(jLabelTelefono1Proveedor))
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabelDescuentoPedido)
                                    .addGap(38, 38, 38)
                                    .addComponent(jLabelDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelTelefono2Proveedor)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabelImporteFinal)
                                            .addGap(84, 84, 84)
                                            .addComponent(jLabelCantidad)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabelCantidadPendiente))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(77, 77, 77)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelDescripcionProducto)
                        .addGap(28, 611, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPaneTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonMasProductos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDescripcionProducto)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelId)
                        .addComponent(jLabelNombreProveedor)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNIFProveedor)
                    .addComponent(jLabelCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDomicilioProveedor)
                    .addComponent(jLabelVendedor))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefono1Proveedor)
                    .addComponent(jLabelImporte))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDescuentoPedido)
                    .addComponent(jLabelDescuento))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTelefono2Proveedor)
                    .addComponent(jLabelImporteFinal)
                    .addComponent(jLabelCantidad)
                    .addComponent(jLabelCantidadPendiente))
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(scrollPaneTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMasProductos)
                    .addComponent(jButton1))
                .addGap(8, 8, 8)
                .addComponent(jLabel2))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void setListenerEtiquetaCantidadPendiente () {
        jLabelCantidad.addMouseListener(new MouseAdapter() {
                @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {     
                                       ActualizarVentaFrame actualizarVentaFrame = new ActualizarVentaFrame(getId(),pedidoConsultado.getClienteId(),pedidoConsultado.getImporte(), pedidoConsultado.getCantidadPendiente(), Double.parseDouble(pedidoConsultado.getDescuento()), "entrega");
                                       actualizarVentaFrame.setVisible(true);
        
                           }
                        }
        });
    } 
    
    
    
     public void setListenerEtiquetaDescuento () {
           jLabelDescuentoPedido.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {     
                            try {
                                                  ModificarDescuentoFrame mnf = new ModificarDescuentoFrame(getId(),FichaPedidoFrame.this);
                                                  mnf.setVisible(true);
                                                  mnf.setLocationRelativeTo(MainFrame.getInstance());

                                              } catch (Exception ex) {
                                                  System.out.println(ex);
                                                  JOptionPane.showMessageDialog (null,"No se ha podido modificar el descuento: " + ex);
                                              }
                        }}
                    });
   
   
   }
    
    
    private void setListenerBotonMasProductos () {
        jButtonMasProductos.addMouseListener(
                                     new MouseAdapter() { 
                                          @Override
                                          public void mouseClicked(MouseEvent e) {
                                                ModificarVentaFrame modificarVentaFrame = ModificarVentaFrame.getInstance(FichaPedidoFrame.this);
                                                modificarVentaFrame.setVisible(true);
                                                modificarVentaFrame.setExtendedState(MAXIMIZED_BOTH);
                                                modificarVentaFrame.setIdPedidoAModificar(pedidoConsultado.getId());
                                                JTablaVenta jTablaCRUD= JTablaVenta.getInstance();
                                                jTablaCRUD.setIdPedido(pedidoConsultado.getId());
                                                jTablaCRUD.setModificar(true);
                                                jTablaCRUD.getColumnModel().getColumn(0).setPreferredWidth(20);
                                                jTablaCRUD.getColumnModel().getColumn(1).setPreferredWidth(150);
                                                jTablaCRUD.getColumnModel().getColumn(2).setPreferredWidth(200);
                                                jTablaCRUD.getColumnModel().getColumn(3).setPreferredWidth(50);
                                                jTablaCRUD.getColumnModel().getColumn(4).setPreferredWidth(50);
                                                CargarVentaCommand cargarStockCommand = new CargarVentaCommand (CargadorListaProducto.getInstance(), new ProductoParserJson());
                                                jTablaCRUD.setCargarVentaCommand(cargarStockCommand);
                                                cargarStockCommand.execute();
                                          }
                                     });
    
    
    }
    
    private void jButtonMasProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMasProductosActionPerformed
        
    }//GEN-LAST:event_jButtonMasProductosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         List<Cliente> clientes = new CargarClientesCommand(new CargadorListaCliente(), new ClienteParserJson()).executeCliente(pedidoConsultado.getClienteId());
         Cliente clienteConsultado = clientes.get(0);
         System.out.println("me llega para generar el pdf " + jTablaPedido.getPedidoParaDevolucion());
         GenerarDevolucion generador = new GenerarDevolucion(jTablaPedido.getPedidoParaDevolucion(), jTablaPedido.getListaDevoluciones(), clienteConsultado,new SelectorDeRuta().seleccionDeRuta());
                                try {
                                    generador.createPdf();
                                } catch (DocumentException ex) {
                                    JOptionPane.showMessageDialog (null,ex);
                                    Logger.getLogger(JTablaPedido.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    JOptionPane.showMessageDialog (null,ex);
                                    Logger.getLogger(JTablaPedido.class.getName()).log(Level.SEVERE, null, ex);
                                }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonMasProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelCantidad;
    private javax.swing.JLabel jLabelCantidadPendiente;
    private javax.swing.JLabel jLabelCliente;
    private javax.swing.JLabel jLabelDescripcionProducto;
    private javax.swing.JLabel jLabelDescuento;
    private javax.swing.JLabel jLabelDescuentoPedido;
    private javax.swing.JLabel jLabelDomicilioProveedor;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelImporte;
    private javax.swing.JLabel jLabelImporteFinal;
    private javax.swing.JLabel jLabelNIFProveedor;
    private javax.swing.JLabel jLabelNombreProveedor;
    private javax.swing.JLabel jLabelTelefono1Proveedor;
    private javax.swing.JLabel jLabelTelefono2Proveedor;
    private javax.swing.JLabel jLabelVendedor;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private java.awt.ScrollPane scrollPaneTabla;
    // End of variables declaration//GEN-END:variables
}

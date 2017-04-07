
package view.detallesPedidoEnFicha;

import com.itextpdf.text.DocumentException;
import command.clientes.CargarClientesCommand;
import command.pedidos.CargarPedidosCommand;
import command.productos.CargarProductosPedidoCommand;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import model.Cliente;
import model.Pedido;
import model.Producto;
import model.ProductoPedido;
import process.CRUDPedidos.EliminarProductoDePedido;
import process.PDFs.GenerarDevolucion;
import process.cargador.CargadorListaCliente;
import process.cargador.CargadorListaPedido;
import process.parser.clientes.ClienteParserJson;
import process.parser.pedidos.ServicioParserJson;
import view.ModificarFrames.ModificarVariacionesFrame;
import view.SelectorDeRuta;
import view.pedidos.FichaPedidoFrame;





public class JTablaPedido extends javax.swing.JTable{
    
    private TablaTPVModelo tablaTPVModelo;
    private CargarProductosPedidoCommand cargarProductosPedidoCommand;
    private ArrayList<Producto> listaDevoluciones;  
    private ArrayList<ProductoPedido> listapp;
    private Integer id;
    private Pedido pedidoParaDevolucion;
    private FichaPedidoFrame ficha;
   
    
    
    public JTablaPedido(Integer id, FichaPedidoFrame ficha) throws IOException {
         this.pedidoParaDevolucion = new Pedido();
         this.listapp = new ArrayList<>();
         this.listaDevoluciones = new ArrayList<>();
         this.id = id;
         this.ficha = ficha;
         setFont(new java.awt.Font("Arial", 0, 16));
         setRowHeight(30);
         setTable();
         setListenerTabla();
         setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
            //    if(String.valueOf(jtable.getValueAt(1,3)).equals("5"))  setForeground(Color.blue);
            //    else  setForeground(Color.red);
                return (Component) objeto;
                
            }
         });
    }

    public Pedido getPedidoParaDevolucion() {
        return pedidoParaDevolucion;
    }
    
    

      public void setTable() {
        if (tablaTPVModelo == null ) {
            tablaTPVModelo = new TablaTPVModelo();
            setModel(tablaTPVModelo);
        }
      }
    
    @Override
     public TablaTPVModelo getModel() {
        return tablaTPVModelo;
     }

 
  
    public void mostrarListaProductos(List<Producto> listaProducto) {
       setTable();
       tablaTPVModelo.actualizarListaProductos(listaProducto);
       tablaTPVModelo.fireTableDataChanged();
    }

    public ArrayList<Producto> getListaDevoluciones() {
        return listaDevoluciones;
    }

    public ArrayList<ProductoPedido> getListapp() {
        return listapp;
    }
      
    
    
    
    
    
    
    public void setListenerTabla() throws IOException {
        
     
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 String consultaIdDeLaMesa = null;
                 Producto productoAModificar = null;
                 
                try {
                    pedidoParaDevolucion = new CargarPedidosCommand(new CargadorListaPedido(), new ServicioParserJson()).executePedido(id);
                } catch (IOException ex) {
                    Logger.getLogger(JTablaPedido.class.getName()).log(Level.SEVERE, null, ex);
                }

                  // CONSULTAMOS EL PRODUCTO A ACTUALIZAR O ELIMINAR (PARA LUEGO VER SU ID)
                      for (int i=0; i<tablaTPVModelo.getListaProducto().size(); i++) {
                        
                         if (tablaTPVModelo.getListaProducto().get(i).getNombre() == getModel().getValueAt(rowAtPoint(e.getPoint()), 0)) {
                               productoAModificar = tablaTPVModelo.getListaProducto().get(i);
                               break;    
                         }
                     }
                
                  if  (columnAtPoint(e.getPoint()) == 2) {
                        ModificarVariacionesFrame mvf = new ModificarVariacionesFrame(pedidoParaDevolucion.getId(),
                                                                                      productoAModificar.getId(),
                                                                                      ficha);
                        
                  }
                
                  if (columnAtPoint(e.getPoint()) == 4) {
                       
                            int seleccion = JOptionPane.showOptionDialog(
                             null,
                            "Seleccione opcion", 
                            "Selector de opciones",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,    // null para icono por defecto.
                            new Object[] { "Devolver", "Eliminar" },   // null para YES, NO y CANCEL
                            "opcion 1");
                        
                      
                         
                        if (seleccion == 0) {
                           System.out.println("EL ID ES "+ ficha.getPedidoConsultado().getId());
                         
                           boolean productoEncontradoEnListaDevoluciones = false;
                           for (int i=0; i<listapp.size(); i++) {
                              if (listapp.get(i).getProducto_id() == productoAModificar.getId()) {
                                 
                                  JOptionPane.showMessageDialog (null,"Añadido para lista de devolución con su igual");
                                  productoAModificar.getProductoPedido().setCantidad(listapp.get(i).getCantidad()+1);
                                  listapp.get(i).setCantidad(listapp.get(i).getCantidad()+1);
                                         productoEncontradoEnListaDevoluciones= true;
                                         break;
                              } 
                           } 
                           
                           if (productoEncontradoEnListaDevoluciones == false) {
                                JOptionPane.showMessageDialog (null,"Añadido para lista de devolución. Para finalizar la devolución genere el PDF de Abono");
                                ProductoPedido pp  = new ProductoPedido();
                                pp.setPedido_id(ficha.getPedidoConsultado().getId());
                                pp.setCantidad(1.0);
                                pp.setProducto_id(productoAModificar.getId());
                                listapp.add(pp);
                                productoAModificar.setProductoPedido(pp);
                           }
                           System.out.println("el pedido es "  + pedidoParaDevolucion);
                           pedidoParaDevolucion.setProductos(listapp);
                           listaDevoluciones.add(productoAModificar);
                        }      
                      
                        EliminarProductoDePedido eliminar = new EliminarProductoDePedido(pedidoParaDevolucion.getId(), productoAModificar.getId());
                     try {
                         eliminar.eliminar();
                     } catch (Exception ex) {
                         Logger.getLogger(JTablaPedido.class.getName()).log(Level.SEVERE, null, ex);
                     }
                       tablaTPVModelo.getListaProducto().remove(productoAModificar);
                       cargarProductosPedidoCommand.executeTablaEnFicha(pedidoParaDevolucion.getId(),JTablaPedido.this);
                       ficha.consultar();
                       ficha.actualizar();
                        
                  }
              } 
         }
      );
 
    
    }   
    
  
    public void setCargarProductosPedidoCommand(CargarProductosPedidoCommand cargarProductosPedidoCommand) {
        this.cargarProductosPedidoCommand = cargarProductosPedidoCommand;
    }
    
}
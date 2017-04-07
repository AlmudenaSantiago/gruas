/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.detalleVenta;

import command.productos.CargarProductosPedidoCommand;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import model.Producto;
import process.CRUDPedidos.EliminarProductoDePedido;
import process.cargador.CargadorListaProducto;
import process.cargador.CargadorProductosPedido;
import process.parser.productos.ProductoParserJson;
import process.parser.productos.ProductoPedidoParserJson;
import view.pedidos.JTablaPedidos;
import view.pedidos.MainFrame;




public final class JTablaDetalleVenta extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private ListaProductosModelo listaProductosModelo;
    private CargarProductosPedidoCommand cargarProductosPedidoCommand;
    private Integer idPedido;
  
    
    public JTablaDetalleVenta(Integer idPedido) {
       setListeners();
        this.setFont(new java.awt.Font("Arial", 0, 14));
       this.idPedido = idPedido;
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
            //    if(String.valueOf(jtable.getValueAt(1,3)).equals("5"))  setForeground(Color.blue);
            //    else  setForeground(Color.red);
                
                return (Component) objeto;
                
            }
        });
            
    }
    
        public void setListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            @SuppressWarnings("empty-statement")
            public void mouseClicked(MouseEvent e) {
             
               Producto producto = new Producto();
               Integer idProducto = (Integer) getValueAt(rowAtPoint(e.getPoint()), 0);
               ProductoParserJson productoParser = new ProductoParserJson();
               List<Producto> listaProductos = productoParser.parsear(CargadorListaProducto.getInstance().cargar()) ;
                  
                    for (Producto productoIterador : listaProductos) {
                         if (productoIterador.getId().equals(idProducto)) {
                            producto = productoIterador;
                        }
                    }
               
               
               String correo =  (String) getValueAt(rowAtPoint(e.getPoint()), 5);
               String cuerpo = "Producto: " + (String) getValueAt(rowAtPoint(e.getPoint()), 1) + ",  "
                             + "Cantidad: " + (Double) getValueAt(rowAtPoint(e.getPoint()), 2)  + ",   "
                             + "Detalles: " + (String) getValueAt(rowAtPoint(e.getPoint()), 3)  + ",   "
                             + "Precio:   " +  producto.getPrecio() + ".   "
                             + "Total:   "  + (producto.getPrecio()*(Double)getValueAt(rowAtPoint(e.getPoint()), 2)) + ".   ";;
                         
               if (columnAtPoint(e.getPoint()) == 5) {
                try {
                    Runtime.getRuntime( ).exec( new String[] { "cmd.exe", "/c", "start", "\"title\"", "\"mailto:" + correo +  "?subject=Muebles Alca%20&body="+ cuerpo + "%20%20&attachment=\'file:///myPdf.pdf\'\"" } );
                } catch (IOException ex) {
                    Logger.getLogger(JTablaDetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
               }
               
               if (MainFrame.palabraClave.getText().equals("alca")) {
                    if (columnAtPoint(e.getPoint()) == 4) {
                        EliminarProductoDePedido  elimina = new EliminarProductoDePedido(JTablaPedidos.getInstance().getFramesAbiertos().get(idPedido).getIdPedido(),idProducto);

                        try {
                            elimina.eliminar();

                        }  catch (Exception ex) {
                            Logger.getLogger(JTablaDetalleVenta.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        
                         cargarProductosPedidoCommand = new CargarProductosPedidoCommand(new ProductoPedidoParserJson(), CargadorProductosPedido.getInstance());
                         JTablaPedidos.getInstance().getFramesAbiertos().get(idPedido).getjTablaDetalleComanda().getListaProductosModelo().setCargarProductosPedidoCommand(cargarProductosPedidoCommand);
                         JTablaPedidos.getInstance().getFramesAbiertos().get(idPedido).getjTablaDetalleComanda().getListaProductosModelo().getCargarProductosPedidosCommand().execute(JTablaPedidos.getInstance().getFramesAbiertos().get(idPedido).getIdPedido());
                         
                   
                    }
            }
            }
            
        });
    }

    public void mostrarListaProductos(List<Producto> listaProducto) {
       setTable();
       listaProductosModelo.actualizarListaProductos(listaProducto);
       listaProductosModelo.fireTableDataChanged();
     }
          
    public void setTable() {
       
        if (listaProductosModelo == null ) {
            listaProductosModelo = new ListaProductosModelo();
            setModel(listaProductosModelo);
        }
        
    }

    public ListaProductosModelo getListaProductosModelo() {
        return listaProductosModelo;
    }
    
    

    public void setCargarProductosPedidoCommand(CargarProductosPedidoCommand cargarProductosPedidoCommand) {
        this.cargarProductosPedidoCommand = cargarProductosPedidoCommand;
    }
 
}

    
    


package view.detalleVenta;

import command.productos.CargarProductosPedidoCommand;
import java.awt.Color;
import model.Producto;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import javax.swing.JButton;
import process.cargador.CargadorCorreoProveedor;

public class ListaProductosModelo extends AbstractTableModel {

       private CargarProductosPedidoCommand cargarProductosPedidoCommand;
    private  List<Producto> listaProducto;

    protected String[] columnNames = new String[] {
          "Id", "Producto", "Cantidad","Variaciones", "Eliminar Uno", "Correo"
    };

    protected Class[] columnClasses = new Class[] {
         Integer.class, String.class, Double.class, String.class, JButton.class,  String.class
    };



    @Override
    public int getRowCount() {
        return listaProducto.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto producto = listaProducto.get(rowIndex);
        
        switch (columnIndex){
            
            
            case 0:
               return producto.getId();
            case 1:
                return producto.getNombre();
            case 2:
                return producto.getProductoPedido().getCantidad();
            case 3:
                 return producto.getProductoPedido().getVariaciones();
            case 4:
                 return new JButton("Eliminar");
            case 5:
                return new CargadorCorreoProveedor().cargar(producto.getIdProveedor());
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }
    
    

    public void actualizarListaProductos(List<Producto> listaProductos){
        
       if (!listaProductos.equals(this.listaProducto)) {
           System.out.println("");
       } 
        this.listaProducto = listaProductos;
    }

   
      public void setCargarProductosPedidoCommand(CargarProductosPedidoCommand cargarProductosPedidoCommand) {
        this.cargarProductosPedidoCommand = cargarProductosPedidoCommand;
    }

    public CargarProductosPedidoCommand getCargarProductosPedidosCommand() {
        return cargarProductosPedidoCommand;
    }
    
      
      
      
}

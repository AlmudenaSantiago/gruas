package view.detallesPedidoEnFicha;

import command.productos.CargarProductosPedidoCommand;
import java.util.ArrayList;
import model.Producto;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import javax.swing.JButton;

public class TablaTPVModelo extends AbstractTableModel {

    private CargarProductosPedidoCommand cargarProductosPedidoCommand;
    private  List<Producto> listaProducto = new ArrayList<Producto>();
    protected String[] columnNames = new String[] {
          "Producto", "Precio","Detalles","Cantidad","Eliminar"
    };

    protected Class[] columnClasses = new Class[] {
           String.class, Double.class,String.class, Double.class, JButton.class
    };



   

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto producto = listaProducto.get(rowIndex);
        System.out.println("llega al wwitch");
        switch (columnIndex){
            
            case 0:
                return producto.getNombre();
            case 1:
                return producto.getPrecio();
            case 2:
                return producto.getProductoPedido().getVariaciones();
            case 3:
                return producto.getProductoPedido().getCantidad();
            case 4:
                 return new JButton("Eliminar");
          
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
        this.listaProducto = listaProductos;
    }

    public List<Producto> getListaProducto() {
        return listaProducto;
    }

   
  
    @Override
    public int getRowCount() {
        return listaProducto.size();
    }
    
      
      
      
}

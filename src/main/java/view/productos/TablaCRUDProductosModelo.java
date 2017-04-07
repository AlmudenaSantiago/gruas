package view.productos;


import javax.swing.table.AbstractTableModel;
import java.util.List;
import javax.swing.JButton;

import model.Producto;





public class TablaCRUDProductosModelo extends AbstractTableModel {

    private List<Producto> listaProducto;
   

       
    
    protected String[] columnNames = new String[] {
          "Proveedor", "id", "Producto","Categoria1" ,"Categoria2","Categoria3","Categoria4", "Acabado", "Descripcion","Pvc.Compra", "Pvc.Venta","Imagen","Eliminar","Stk"
    };

    protected Class[] columnClasses = new Class[] {
           String.class, Integer.class, String.class, String.class, String.class,String.class,String.class,String.class, String.class, Double.class,Double.class,JButton.class, JButton.class, String.class};

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public int getRowCount() {
        return (listaProducto != null) ? listaProducto.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 14;
    }
    
 
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        Producto producto = listaProducto.get(rowIndex);
        switch (columnIndex){
            case 0:
                return producto.getProveedor();
            case 1:
                return producto.getId();
            case 2:
                  return producto.getNombre();
             case 3:
                 return producto.getCategoria1_nombre();
            case 4:
                 return producto.getCategoria2_nombre();
            case 5:
                 return producto.getCategoria3_nombre();
            case 6:
                 return producto.getCategoria4_nombre();
            case 7:
                 return producto.getColor();
            case 8: 
                  return producto.getDescripcion();
            case 9:
                  return producto.getPrecioCompra();
            case 10:
                  return producto.getPrecio();
            case 11:
                JButton botonImagen = new JButton("Imag");
                botonImagen.setFont(new java.awt.Font("Arial", 0, 14));
                return botonImagen;
            case 12: 
                JButton botonEliminar = new JButton("Elim");
                botonEliminar.setFont(new java.awt.Font("Arial", 0, 14));
                return botonEliminar;
            case 13:
                  return producto.getStock();
        }
        
        return null;
    }

   
    public void actualizarListaProducto(List<Producto> listaProducto){
        this.listaProducto = listaProducto;
    
    }

}

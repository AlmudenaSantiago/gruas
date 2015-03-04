package view;

import model.Producto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ListaProductosModelo extends AbstractTableModel {

    private final List<Producto> listaProducto;

    protected String[] columnNames = new String[] {
            "Producto", "Cantidad"
    };

    protected Class[] columnClasses = new Class[] {
            String.class, Double.class
    };


    public ListaProductosModelo(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    @Override
    public int getRowCount() {
        return listaProducto.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto producto = listaProducto.get(rowIndex);

        switch (columnIndex){
            case 0:
                return producto.getNombre();
            case 1:
                return producto.getProductoPedido().getCantidad();
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
}

package view;

import model.Pedido;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TablaPedidosModelo extends AbstractTableModel {

    private List<Pedido> listaPedido;

    protected String[] columnNames = new String[] {
            "NºPedido", "Usuario", "Cliente","Importe", "Fecha", "Detalle","Estado","Hacer factura"
    };

    protected Class[] columnClasses = new Class[] {
            Integer.class, String.class, String.class,
            Double.class, String.class, JButton.class,  String.class, JButton.class
    };

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
        return (listaPedido != null) ? listaPedido.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        Pedido pedido = listaPedido.get(rowIndex);
        switch (columnIndex){
            case 0:
                return pedido.getId();
            case 1:
                return pedido.getUsuario();
            case 2:
                return pedido.getCliente();
            case 3:
                return pedido.getImporte();
            case 4:
                return pedido.getFechaCreacion();
            case 5:
                JButton jButton = new JButton("Más detalles");
                return jButton;
            case 6:
                return pedido.getEstado();
            case 7:
                JButton jButtonFactura = new JButton("Generar Factura");
                return jButtonFactura;
        }
        return null;
    }

    public void actualizarListaPedido(List<Pedido> listaPedido){
        this.listaPedido = listaPedido;
    }

}

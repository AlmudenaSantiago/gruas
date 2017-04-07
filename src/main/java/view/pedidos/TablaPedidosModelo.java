package view.pedidos;

import java.util.ArrayList;
import model.Pedido;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;


public class TablaPedidosModelo extends AbstractTableModel {

    private List<Pedido> listaPedido;
    private String fechaOrdenada;
    
    
    protected String[] columnNames = new String[] {
          "Fecha", "Id.Pedido", "Cliente","Cantidad Pendiente","Importe","Descuento","Imp.Final", "Forma","Atendido por","Ver Comanda","Imprimir Factura", "Eliminar"
    };

    protected Class[] columnClasses = new Class[] {
         String.class, Integer.class,   String.class, Double.class , Double.class, Double.class, Double.class, String.class, String.class,JButton.class , JButton.class, JButton.class};
   

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
        return 12;
    }
    
 
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        
        Pedido pedido = listaPedido.get(rowIndex);
        switch (columnIndex){
         
            case 0:
                if (rowIndex == listaPedido.size()-1) return "TOTAL";
                else
                    fechaOrdenada = pedido.getFechaCreacion().substring(0, pedido.getFechaCreacion().length()-8);
                    String year  = fechaOrdenada.substring(0,4);
                    String month = fechaOrdenada.substring(5,7);
                    String day   = fechaOrdenada.substring(8,11);
                    String fecha = day + "-" + month + "-" + year;
                    return fecha; 
                
             case 1:
                  return pedido.getId(); 
            case 2:
               if (rowIndex == listaPedido.size()-1) {
                    return listaPedido.size()-1;
                } else {
                     return pedido.getCliente() + " (" + pedido.getClienteId() + ")";  
                }
            case 3:
                 if (rowIndex == listaPedido.size()-1) {
                    return process.parser.pedidos.PedidoRespuestaJson.contadorImportePendiente;
                }
                else     
                    return pedido.getCantidadPendiente();
                        
            case 4:
                if (rowIndex == listaPedido.size()-1) {
                    return process.parser.pedidos.PedidoRespuestaJson.contadorImporte;
                }
                else     
                    return pedido.getImporte();
            
            case 5: 
                if (rowIndex == listaPedido.size()-1) {
                    return process.parser.pedidos.PedidoRespuestaJson.contadorDescuentos;
                }
                else  
                return Double.parseDouble(pedido.getDescuento());
            case 6: 
                if (rowIndex == listaPedido.size()-1) {
                    return process.parser.pedidos.PedidoRespuestaJson.contadorImporteConDescuento;
                }
                else     
                    return pedido.getImporte()-Double.parseDouble(pedido.getDescuento());
                
            case 7: 
                if (rowIndex == listaPedido.size()-1) {
                    return pedido.getId();
               } else {
                    return pedido.getForma();
                }
            case 8: 
                if (rowIndex == listaPedido.size()-1) {
                    return pedido.getId();
               } else {
                    return pedido.getVendedor();
                }
            
            case 9:
                if (rowIndex == listaPedido.size()-1) {
                    return pedido.getId();
                } else {
                    JButton jButton = new JButton("Detalles");
                    jButton.setFont(new java.awt.Font("Arial", 0, 14));
                    return jButton;
                }
            
            case 10:
                 if (rowIndex == listaPedido.size()-1) {
                    return pedido.getId();
                } else {
                JButton jButtonFactura = new JButton("Imprimir Factura");
                jButtonFactura.setFont(new java.awt.Font("Arial", 0, 14));
                return jButtonFactura;
                 }
            case 11:
                 if (rowIndex == listaPedido.size()-1) {
                    return pedido.getId();
                } else {
                JButton jButtonEliminar = new JButton("Eliminar");
                jButtonEliminar.setFont(new java.awt.Font("Arial", 0, 14));
                return jButtonEliminar;
              
             }  
         
                
             
        }
        
        return null;
    }


    
    public void actualizarListaPedido(List<Pedido> listaPedido, Pedido pedido){
        
         if (listaPedido == null) {
            List<Pedido> list = new ArrayList<Pedido>();
            
             
             this.listaPedido = list;
         
         } else{
           this.listaPedido = listaPedido;
           this.listaPedido.add(pedido);
         }
        
        
    }

}

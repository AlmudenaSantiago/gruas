
package view.Ranking;

import command.pedidos.CargarPlatosServidosCommand;
import command.productos.CargarProductosPedidoCommand;
import java.awt.Component;


import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import process.cargador.CargadorProductosPedido;
import process.parser.productos.ProductoParserJson;




public final class JTablaRanking extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaRankingModelo tablaPlatosServidosModelo;
    private CargarPlatosServidosCommand cargarPlatosServidosCommand;
    private static JTablaRanking instance;
   
    
    private JTablaRanking() {
       setTable();
       setRowSorter();
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
      }
    
                
 

    public static JTablaRanking getInstance() {
        if (instance == null) {
            instance = new JTablaRanking();
        }
        return instance;
    }
    
        
    public void setTable() {
        tablaPlatosServidosModelo = new TablaRankingModelo();
        setModel(tablaPlatosServidosModelo);
    }
   
    
    public void setRowSorter () {
     		TableRowSorter sorter = new TableRowSorter(tablaPlatosServidosModelo);
                this.setRowSorter(sorter);
                 sorter.setRowFilter (RowFilter.regexFilter(".",0));
    }

    public void mostrarPlatosServidos(String listaPlatosServidos) {
         tablaPlatosServidosModelo.actualizarListaPlatos(listaPlatosServidos);
         tablaPlatosServidosModelo.fireTableDataChanged();
    }

    
    

    
    
    public void setCargarPlatosServidosCommand(CargarPlatosServidosCommand cargarPlatosServidosCommand) {
        this.cargarPlatosServidosCommand = cargarPlatosServidosCommand;
    }
   
}

package view.basesOperativas;


import command.basesOperativas.CargarBasesOperativasCommand;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

import java.util.List;
import javax.swing.table.TableRowSorter;
import model.basesOperativas.BaseOperativa;






public final class JTablaBasesOperativasVista extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaBasesOperativasModelo tablaCRUDModelo;
    private CargarBasesOperativasCommand cargarCommand;
    private static JTablaBasesOperativasVista instance;
    private TableRowSorter sorter;
   private boolean eliminar;
    
    public JTablaBasesOperativasVista() {
       setTable();
       eliminar = false;
       this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
       
   //    setListenerModificaCelda();
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
     
       
    
       
    
    
    
    
    
    
    
    
    
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }
    

    
    
    
 
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           
     }

     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
     }
   
  
   
    public void setTable() {
        tablaCRUDModelo = new TablaBasesOperativasModelo();
        setModel(tablaCRUDModelo);
      }
    
    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
    */


    
    @Override
   public TablaBasesOperativasModelo getModel() {
      return tablaCRUDModelo;
   
   }

   
   
       
      
     
     
     
     
     
     

    
    public void mostrar(List<BaseOperativa> lista) {
         tablaCRUDModelo.actualizarLista(lista);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarBasesOperativasCommand cargarCommand) {
        this.cargarCommand = cargarCommand;
    }
}
   

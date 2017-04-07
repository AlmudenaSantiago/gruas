package view.avisos;


import command.avisos.CargarAvisosCommand;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableRowSorter;
import model.Aviso;
import process.cargador.avisos.CargadorListaAvisos;
import process.parser.avisos.AvisoParserJson;

import view.diasFestivos.JTablaCRUDDiasFestivos;






public final class JTablaAvisos extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaAvisosModelo tablaCRUDModelo;
    private CargarAvisosCommand cargarCommand;
    private static JTablaAvisos instance;
    private TableRowSorter sorter;
   private boolean eliminar;
    
   
   
    private JTablaAvisos() {
       setTable();
       eliminar = false;
       this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
       getColumnModel().getColumn(0).setPreferredWidth(20);
     
      
       getColumnModel().getColumn(2).setMinWidth(100);
       
   //    setListenerModificaCelda();
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
     
       
       
       setListeners();

    
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }
    
    
    public static JTablaAvisos getInstance() {
        
        if (instance == null) instance = new JTablaAvisos();
        return instance;
    
    }

    
    
    
 
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           
     }

     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
     }
   
  
   
    public void setTable() {
        tablaCRUDModelo = new TablaAvisosModelo();
        setModel(tablaCRUDModelo);
      }
    
    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
    */


    
    @Override
   public TablaAvisosModelo getModel() {
      return tablaCRUDModelo;
   
   }

  
     
     
     
     
     

    
    public void mostrar(List<Aviso> lista) {
         tablaCRUDModelo.actualizarLista(lista);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarAvisosCommand cargarCommand) {
        this.cargarCommand = cargarCommand;
    }

    private void setListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              Integer id = (Integer) getValueAt(rowAtPoint(e.getPoint()), 0);
              
                 if (columnAtPoint(e.getPoint()) == 1 || columnAtPoint(e.getPoint()) == 0 || columnAtPoint(e.getPoint()) == 2 || columnAtPoint(e.getPoint()) == 3 || columnAtPoint(e.getPoint()) == 4 || columnAtPoint(e.getPoint()) == 5 || columnAtPoint(e.getPoint()) == 6) {
                   
                         AvisoFrame.getInstance().vistaAviso(); 
                         FichaAviso.getInstance().cargarAviso(id);
                        
                        
                          
                    
                }
            }
            
        });
    
    
    }
}
   

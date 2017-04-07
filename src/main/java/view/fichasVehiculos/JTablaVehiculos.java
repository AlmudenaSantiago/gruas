package view.fichasVehiculos;


import command.vehiculos.CargarVehiculosCommand;

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
import model.vehiculos.Vehiculo;
import process.cargador.vehiculos.CargadorListaVehiculo;
import process.parser.vehiculos.VehiculoParserJson;
import view.diasFestivos.JTablaCRUDDiasFestivos;






public final class JTablaVehiculos extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaVehiculosModelo tablaCRUDModelo;
    private CargarVehiculosCommand cargarCommand;
    private static JTablaVehiculos instance;
    private TableRowSorter sorter;
   private boolean eliminar;
    
   
   
    private JTablaVehiculos() {
       setTable();
       eliminar = false;
       this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
       getColumnModel().getColumn(0).setMinWidth(0);
       getColumnModel().getColumn(0).setMaxWidth(0);
       
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
    
    
    public static JTablaVehiculos getInstance() {
        
        if (instance == null) instance = new JTablaVehiculos();
        return instance;
    
    }

    
    
    
 
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           
     }

     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
     }
   
  
   
    public void setTable() {
        tablaCRUDModelo = new TablaVehiculosModelo();
        setModel(tablaCRUDModelo);
      }
    
    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
    */


    
    @Override
   public TablaVehiculosModelo getModel() {
      return tablaCRUDModelo;
   
   }

  
     
     
     
     
     

    
    public void mostrar(List<Vehiculo> lista) {
         tablaCRUDModelo.actualizarLista(lista);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarVehiculosCommand cargarCommand) {
        this.cargarCommand = cargarCommand;
    }

    private void setListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              Integer id = (Integer) getValueAt(rowAtPoint(e.getPoint()), 0);
              
                 if (columnAtPoint(e.getPoint()) == 1 || columnAtPoint(e.getPoint()) == 0 || columnAtPoint(e.getPoint()) == 2 || columnAtPoint(e.getPoint()) == 3 || columnAtPoint(e.getPoint()) == 4 || columnAtPoint(e.getPoint()) == 5 || columnAtPoint(e.getPoint()) == 6) {
                     try {
                         
                          CargarVehiculosCommand cargar = new CargarVehiculosCommand(new CargadorListaVehiculo(), new VehiculoParserJson());
                          List<Vehiculo> lista = cargar.executePorId(id);
                      
                          new FichaVehiculo(lista.get(0)).setVisible(true);
                         
                         
                     } catch (IOException ex) {
                         Logger.getLogger(JTablaCRUDDiasFestivos.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
            }
            
        });
    
    
    }
}
   

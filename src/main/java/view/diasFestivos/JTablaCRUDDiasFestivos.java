package view.diasFestivos;


import view.productos.*;
import command.productos.CargarDiasFestivosCommand;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableRowSorter;
import model.DiaFestivo;

import process.CRUDDiasFestivos.EliminarDiaFestivo;




public final class JTablaCRUDDiasFestivos extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaCRUDDiasFestivosModelo tablaCRUDModelo;
    private CargarDiasFestivosCommand cargarDiasFestivosCommand;
    private static JTablaCRUDDiasFestivos instance;
    private TableRowSorter sorter;
    private ImageFrame image;
    
    private JTablaCRUDDiasFestivos() {
       setTable();
       setRowSorter();
       setListeners();

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
    

    
    public static JTablaCRUDDiasFestivos getInstance() {
        if (instance == null) {
            instance = new JTablaCRUDDiasFestivos();
        
        }
        return instance;
    }
    
    
    

   
    public void setTable() {
        tablaCRUDModelo = new TablaCRUDDiasFestivosModelo();
        setModel(tablaCRUDModelo);
      }
    
    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
    */

    public TableRowSorter getSorter() {
        return sorter;
    }
    
    public void setListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                 if (columnAtPoint(e.getPoint()) == 2) {
                     try {
                         EliminarDiaFestivo eliminar = new EliminarDiaFestivo((Integer) getValueAt(rowAtPoint(e.getPoint()), 0));
                         eliminar.eliminar();
                      
                         SeleccionDiasFestivosFrame.getInstance().actualizarTablaFestivos();
                         
                     } catch (Exception ex) {
                         Logger.getLogger(JTablaCRUDDiasFestivos.class.getName()).log(Level.SEVERE, null, ex);
                     }
                }
            }
            
        });
    }
    

     public void setRowSorter () {
     	  sorter = new TableRowSorter(tablaCRUDModelo);
          this.setRowSorter(sorter);
         //sorter.setRowFilter (RowFilter.regexFilter(".",0)); 
      
     }
    
     
   


    
    public void mostrarDiasFestivos(List<DiaFestivo> lista) {
         tablaCRUDModelo.actualizarLista(lista);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarDiasFestivosCommand cargarCommand) {
        this.cargarDiasFestivosCommand = cargarCommand;
    }

    
  
}

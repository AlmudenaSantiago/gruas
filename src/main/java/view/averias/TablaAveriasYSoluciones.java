package view.averias;


import DatosPrecargados.SingletonAverias;
import DatosPrecargados.SingletonNumRespuesta;
import DatosPrecargados.SingletonSoluciones;
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
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableRowSorter;
import process.CRUDAverias.ModificarAveria;
import process.CRUDAverias.ModificarSolucion;
import process.CRUDNumRespuestas.ModificarNumRespuesta;
import view.contactos.JTablaCRUDContactos;
import view.tarifas.simples.EditorCeldaTabla;






public final class TablaAveriasYSoluciones extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaAveriasYSolucionesModelo tablaCRUDModelo;
    private CargarVehiculosCommand cargarCommand;
    private static TablaAveriasYSoluciones instance;
    private TableRowSorter sorter;
    private boolean eliminar;
    
   
   
    private TablaAveriasYSoluciones() {
       setTable();
       eliminar = false;
       this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
       getColumnModel().getColumn(0).setMinWidth(0);
       getColumnModel().getColumn(0).setMaxWidth(0);
       
       getColumnModel().getColumn(2).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(3).setCellEditor(new EditorCeldaTabla());
       setEditors();
  
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
     
       
       
       setListeners();

    
    }

    public void setEditors() {
       
        
        getColumnModel().getColumn(3).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";

            @Override
            public void editingStopped(ChangeEvent e) {

                try {
                    valor = (String) getColumnModel().getColumn(3).getCellEditor().getCellEditorValue();
                   System.out.println("nuevo valor" +  valor);
                    if (getValueAt(getSelectedRow(),1).equals("Averia")) {    
                        ModificarAveria modificar = null;
                        modificar = new ModificarAveria((Integer) getValueAt(getSelectedRow(),0), "averia", valor);
                        modificar.modificarAveria();
                        SingletonAverias.getInstance().actualizarLista();
                    }
                     if (getValueAt(getSelectedRow(),1).equals("Solucion")) {    
                        ModificarSolucion modificar = null;
                        modificar = new ModificarSolucion((Integer) getValueAt(getSelectedRow(),0), "solucion", valor);
                        modificar.modificarSolucion();
                        SingletonSoluciones.getInstance().actualizarLista();
                    }
                     if (getValueAt(getSelectedRow(),1).equals("Num Respuesta")) {    
                        ModificarNumRespuesta modificar = null;
                        modificar = new ModificarNumRespuesta((Integer) getValueAt(getSelectedRow(),0), "numRespuesta", valor);
                        modificar.modificarNumRespuesta();
                        SingletonNumRespuesta.getInstance().actualizarLista();
                    }
                    
                      AveriasYSolucionesFrame.getInstance().actualizarTabla();
                 
               
                
                } catch (IOException ex) {
                    Logger.getLogger(JTablaCRUDContactos.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
              @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });
          
           
            getColumnModel().getColumn(2).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";

            @Override
            public void editingStopped(ChangeEvent e) {

                try {
                    valor = (String) getColumnModel().getColumn(2).getCellEditor().getCellEditorValue();
                     if (getValueAt(getSelectedRow(),1).equals("Averia")) {    
                        ModificarAveria modificar = null;
                        modificar = new ModificarAveria((Integer) getValueAt(getSelectedRow(),0), "codigo", valor);
                        modificar.modificarAveria();
                        SingletonAverias.getInstance().actualizarLista();
                    }
                    if (getValueAt(getSelectedRow(),1).equals("Solucion")) {    
                        ModificarSolucion modificar = null;
                        modificar = new ModificarSolucion((Integer) getValueAt(getSelectedRow(),0), "codigo", valor);
                        modificar.modificarSolucion();
                        SingletonSoluciones.getInstance().actualizarLista();
                        
                    }
                    if (getValueAt(getSelectedRow(),1).equals("Num Respuesta")) {    
                        ModificarNumRespuesta modificar = null;
                        modificar = new ModificarNumRespuesta((Integer) getValueAt(getSelectedRow(),0), "codigo", valor);
                        modificar.modificarNumRespuesta();
                        SingletonNumRespuesta.getInstance().actualizarLista();
                    }
                    
                    AveriasYSolucionesFrame.getInstance().actualizarTabla();
                     
                } catch (IOException ex) {
                    Logger.getLogger(JTablaCRUDContactos.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
              @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });
           
}
        
    
    
    
    
    
    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }
    
    
    public static TablaAveriasYSoluciones getInstance() {
        
        if (instance == null) instance = new TablaAveriasYSoluciones();
        return instance;
    
    }

    
    
    
 
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           
     }

     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
     }
   
  
   
    public void setTable() {
        tablaCRUDModelo = new TablaAveriasYSolucionesModelo();
        setModel(tablaCRUDModelo);
      }
    
 

    
    @Override
   public TablaAveriasYSolucionesModelo getModel() {
      return tablaCRUDModelo;
   
   }

  
     
     
     
     
     

    
    public void mostrar(List<Object> lista) {
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
              
                 if (columnAtPoint(e.getPoint()) == 1) {
                     
                   
                }
            }
            
        });
    
    
    }
    
     
    
    
}
   

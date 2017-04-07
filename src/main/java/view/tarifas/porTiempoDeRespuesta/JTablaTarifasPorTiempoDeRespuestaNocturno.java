package view.tarifas.porTiempoDeRespuesta;


import command.tarifas.CargarTarifasPorTiempoDeRespuestaCommand;
import view.productos.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.IOException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableRowSorter;
import model.Tarifas.TarifaPorTiempoDeRespuesta;
import process.CRUDTarifasPorTiempoDeRespuesta.ModificarTarifaPorTiempoDeRespuestaNocturno;
import view.tarifas.simples.EditorCeldaTabla;
import view.tarifas.simples.JTablaTarifas;






public final class JTablaTarifasPorTiempoDeRespuestaNocturno extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaTarifasPorTiempoDeRespuestaNocturnoModelo tablaCRUDModelo;
    private CargarTarifasPorTiempoDeRespuestaCommand cargarTarifasCommand;
    private static JTablaTarifasPorTiempoDeRespuestaNocturno instance;
    private TableRowSorter sorter;
    private ImageFrame image;
    
    public JTablaTarifasPorTiempoDeRespuestaNocturno() {
       setTable();
       setRowSorter();

       this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
       
       
       
       
   //    setListenerModificaCelda();
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
       this.setForeground(new Color(153, 0, 51));
       getColumnModel().getColumn(1).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(2).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(3).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(4).setCellEditor(new EditorCeldaTabla());
 
        setListeners();

    
    
    }
    


  
   
    public void setTable() {
        tablaCRUDModelo = new TablaTarifasPorTiempoDeRespuestaNocturnoModelo();
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
    

     public void setRowSorter () {
     	  sorter = new TableRowSorter(tablaCRUDModelo);
          this.setRowSorter(sorter);
         //sorter.setRowFilter (RowFilter.regexFilter(".",0)); 
      
     }
    
    public void setListeners() {
      
      
     
       
          getColumnModel().getColumn(1).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "Servicio";
                      valor = (String) getColumnModel().getColumn(1).getCellEditor().getCellEditorValue();
                    ModificarTarifaPorTiempoDeRespuestaNocturno modificador = new ModificarTarifaPorTiempoDeRespuestaNocturno(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoDeRespuestaNocturno.this);
                       try {
                            modificador.modificarTarifa();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
        
      
         getColumnModel().getColumn(2).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "Km";
                      valor = (String) getColumnModel().getColumn(2).getCellEditor().getCellEditorValue();
                    ModificarTarifaPorTiempoDeRespuestaNocturno modificador = new ModificarTarifaPorTiempoDeRespuestaNocturno(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoDeRespuestaNocturno.this);
                       try {
                            modificador.modificarTarifa();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
         
         
           
         
        
         getColumnModel().getColumn(3).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                    variableAModificar = "Ris";
                    valor = (String) getColumnModel().getColumn(3).getCellEditor().getCellEditorValue();
                    ModificarTarifaPorTiempoDeRespuestaNocturno modificador = new ModificarTarifaPorTiempoDeRespuestaNocturno(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoDeRespuestaNocturno.this);
                       try {
                            modificador.modificarTarifa();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
      
      
      
        getColumnModel().getColumn(4).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "SegundaSalida";
                      valor = (String) getColumnModel().getColumn(4).getCellEditor().getCellEditorValue();
                    ModificarTarifaPorTiempoDeRespuestaNocturno modificador = new ModificarTarifaPorTiempoDeRespuestaNocturno(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoDeRespuestaNocturno.this);
                       try {
                            modificador.modificarTarifa();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
  
 
    }
     
     

 
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           
     }

     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
     }
   
    
    public void mostrarTarifas(List<TarifaPorTiempoDeRespuesta> listaMunicipios) {
         tablaCRUDModelo.actualizarLista(listaMunicipios);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarTarifasPorTiempoDeRespuestaCommand cargarTarifasCommand) {
        this.cargarTarifasCommand = cargarTarifasCommand;
    }
}
   

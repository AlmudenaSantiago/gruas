package view.serviciosEspeciales;


import command.serviciosEspeciales.CargarServiciosEspecialesCommand;
import view.tarifas.porTiempoDeRespuesta.*;
import command.tarifas.CargarRecorridosyKmCommand;
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
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import model.RecorridosyKm;
import model.serviciosEspeciales.ServicioEspecial;
import model.Tarifas.TarifaPorTiempoDeRespuesta;
import process.CRUDServiciosEspeciales.ModificarServicioEspecial;
import process.CRUDTarifas.ModificarTarifa;
import process.CRUDTarifasPorTiempoDeRespuesta.ModificarTarifaPorTiempoDeRespuesta;
import view.gruas.FormatoTablaGruas;
import view.tarifas.simples.EditorCeldaTabla;
import view.tarifas.simples.EditorCeldaTablaString;
import view.tarifas.simples.JTablaTarifas;






public final class JTablaServiciosEspeciales extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaServiciosEspecialesModelo tablaCRUDModelo;
    private CargarServiciosEspecialesCommand cargarCommand;
    private static JTablaServiciosEspeciales instance;
    private TableRowSorter sorter;
    private ImageFrame image;
    
    public JTablaServiciosEspeciales() {
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
     
       
       
       getColumnModel().getColumn(2).setCellEditor(new EditorCeldaTablaString());
       getColumnModel().getColumn(3).setCellEditor(new EditorCeldaTablaString());
       getColumnModel().getColumn(4).setCellEditor(new EditorCeldaTablaString());
       getColumnModel().getColumn(5).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(6).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(7).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(8).setCellEditor(new EditorCeldaTablaString());
       getColumnModel().getColumn(9).setCellEditor(new EditorCeldaTablaInteger());
       getColumnModel().getColumn(10).setCellEditor(new EditorCeldaTablaInteger());
       getColumnModel().getColumn(11).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(12).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(13).setCellEditor(new EditorCeldaTabla());
       TableColumn tm = this.getColumnModel().getColumn(11);
       TableColumn tm1 = this.getColumnModel().getColumn(12);
       
       tm.setCellRenderer(new ColorColumnRenderer(Color.lightGray, new Color(153, 0, 51)));
       tm1.setCellRenderer(new ColorColumnRenderer(Color.lightGray, new Color(153, 0, 51)));
       
       getColumnModel().getColumn(3).setPreferredWidth(250);
       getColumnModel().getColumn(1).setPreferredWidth(10);
       getColumnModel().getColumn(2).setPreferredWidth(100);
      
       
        setListeners();

   
    
    }
    
 
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           
     }

     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
     }
   
  
   
    public void setTable() {
        tablaCRUDModelo = new TablaServiciosEspecialesModelo();
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
       
          
       
         
          
         
         
         getColumnModel().getColumn(2).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "servicio";
                      valor = (String) getColumnModel().getColumn(2).getCellEditor().getCellEditorValue();
                      ModificarServicioEspecial modificador = new ModificarServicioEspecial(tablaCRUDModelo.getListaServicio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaServiciosEspeciales.this);
                       try {
                            modificador.modificarServicioEspecial();
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
                      variableAModificar = "nombreServicio";
                      valor = (String) getColumnModel().getColumn(3).getCellEditor().getCellEditorValue();
                      ModificarServicioEspecial modificador = new ModificarServicioEspecial(tablaCRUDModelo.getListaServicio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaServiciosEspeciales.this);
                       try {
                            modificador.modificarServicioEspecial();
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
                      variableAModificar = "unidad";
                      valor = (String) getColumnModel().getColumn(4).getCellEditor().getCellEditorValue();
                    ModificarServicioEspecial modificador = new ModificarServicioEspecial(tablaCRUDModelo.getListaServicio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaServiciosEspeciales.this);
                       try {
                            modificador.modificarServicioEspecial();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
         
      
      
        getColumnModel().getColumn(5).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "importeUnidad";
                      valor = (String) getColumnModel().getColumn(5).getCellEditor().getCellEditorValue();
                    ModificarServicioEspecial modificador = new ModificarServicioEspecial(tablaCRUDModelo.getListaServicio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaServiciosEspeciales.this);
                       try {
                            modificador.modificarServicioEspecial();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
       getColumnModel().getColumn(6).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "importeSalida";
                      valor = (String) getColumnModel().getColumn(6).getCellEditor().getCellEditorValue();
                    ModificarServicioEspecial modificador = new ModificarServicioEspecial(tablaCRUDModelo.getListaServicio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaServiciosEspeciales.this);
                       try {
                            modificador.modificarServicioEspecial();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
       
        getColumnModel().getColumn(7).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "suplemento";
                      valor = (String) getColumnModel().getColumn(7).getCellEditor().getCellEditorValue();
                    ModificarServicioEspecial modificador = new ModificarServicioEspecial(tablaCRUDModelo.getListaServicio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaServiciosEspeciales.this);
                       try {
                            modificador.modificarServicioEspecial();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
        
        
         getColumnModel().getColumn(8).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "tipo";
                      valor = (String) getColumnModel().getColumn(8).getCellEditor().getCellEditorValue();
                    ModificarServicioEspecial modificador = new ModificarServicioEspecial(tablaCRUDModelo.getListaServicio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaServiciosEspeciales.this);
                       try {
                            modificador.modificarServicioEspecial();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
        
        
        
        
          
        getColumnModel().getColumn(9).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                   variableAModificar = "umbral";
                   valor = (String) getColumnModel().getColumn(9).getCellEditor().getCellEditorValue();
                    ModificarServicioEspecial modificador = new ModificarServicioEspecial(tablaCRUDModelo.getListaServicio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaServiciosEspeciales.this);
                       try {
                            modificador.modificarServicioEspecial();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
        
        
        
        
         getColumnModel().getColumn(10).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                   variableAModificar = "igic";
                   valor = (String) getColumnModel().getColumn(10).getCellEditor().getCellEditorValue();
                   ModificarServicioEspecial modificador = new ModificarServicioEspecial(tablaCRUDModelo.getListaServicio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaServiciosEspeciales.this);
                       try {
                            modificador.modificarServicioEspecial();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
        
      
           getColumnModel().getColumn(11).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                   variableAModificar = "importeUnidadConSuplemento";
                   valor = (String) getColumnModel().getColumn(11).getCellEditor().getCellEditorValue();
                   ModificarServicioEspecial modificador = new ModificarServicioEspecial(tablaCRUDModelo.getListaServicio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaServiciosEspeciales.this);
                       try {
                            modificador.modificarServicioEspecial();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
           
              getColumnModel().getColumn(12).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                   variableAModificar = "importeSalidaConSuplemento";
                   valor = (String) getColumnModel().getColumn(12).getCellEditor().getCellEditorValue();
                   ModificarServicioEspecial modificador = new ModificarServicioEspecial(tablaCRUDModelo.getListaServicio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaServiciosEspeciales.this);
                       try {
                            modificador.modificarServicioEspecial();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
           
       getColumnModel().getColumn(13).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                   variableAModificar = "nombre";
                   valor = (String) getColumnModel().getColumn(13).getCellEditor().getCellEditorValue();
                   ModificarServicioEspecial modificador = new ModificarServicioEspecial(tablaCRUDModelo.getListaServicio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaServiciosEspeciales.this);
                       try {
                            modificador.modificarServicioEspecial();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
           
              
              
 
    }
     
     
     
     
     
     
     

    
    public void mostrarServiciosEspeciales(List<ServicioEspecial> lista) {
         tablaCRUDModelo.actualizarLista(lista);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarServiciosEspecialesCommand cargarCommand) {
        this.cargarCommand = cargarCommand;
    }
}
   

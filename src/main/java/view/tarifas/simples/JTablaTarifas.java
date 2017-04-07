package view.tarifas.simples;


import command.tarifas.CargarRecorridosyKmCommand;
import command.tarifas.CargarTarifasCommand;
import command.tarifas.CargarTarifasPorTiempoDeRespuestaCommand;
import view.productos.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;
import model.RecorridosyKm;
import model.Tarifas.Tarifa;
import model.Tarifas.TarifaPorTiempoDeRespuesta;
import process.CRUDProveedores.EliminarProveedor;
import process.CRUDRecorridosyKm.ModificarRecorridosYKilometros;
import process.CRUDTarifas.ModificarTarifa;
import view.pedidos.MainFrame;
import view.proveedores.CRUD_ProveedoresFrame;
import view.proveedores.FichaProveedorFrame;
import view.proveedores.JTablaCRUDProveedores;
import view.recorridosyKm.JTablaCRUDRecorridosyKm;






public final class JTablaTarifas extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaTarifasModelo tablaCRUDModelo;
    private CargarTarifasCommand cargarTarifasCommand;
    private static JTablaTarifas instance;
    private TableRowSorter sorter;
    private ImageFrame image;
    EditorCeldaTabla editor ;
    int columna;
    int fila;
    
    
    
    public JTablaTarifas() {
       setTable();
     
      
       
       getColumnModel().getColumn(0).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(1).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(2).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(3).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(4).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(5).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(6).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(7).setCellEditor(new EditorCeldaTabla());
    
        setListeners();

       
       this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
        
       
      // setListenerModificaCelda();
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
      }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }
    

    
    
    
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           
     }

     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
     }
   
  
   
    public void setTable() {
        tablaCRUDModelo = new TablaTarifasModelo();
        setModel(tablaCRUDModelo);
      }
    
    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
    */

  
   
     
     
     
  public void setListeners() {
      
      
       getColumnModel().getColumn(0).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                @Override
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "Urbano";
                      valor = (String) getColumnModel().getColumn(0).getCellEditor().getCellEditorValue();
                      ModificarTarifa modificador = new ModificarTarifa(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                      modificador.setTabla(JTablaTarifas.this);
                       try {
                            modificador.modificarTarifa();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
       
       
          getColumnModel().getColumn(1).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "Salida";
                      valor = (String) getColumnModel().getColumn(1).getCellEditor().getCellEditorValue();
                    ModificarTarifa modificador = new ModificarTarifa(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifas.this);
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
                    ModificarTarifa modificador = new ModificarTarifa(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifas.this);
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
                      variableAModificar = "Rescate";
                      valor = (String) getColumnModel().getColumn(3).getCellEditor().getCellEditorValue();
                    ModificarTarifa modificador = new ModificarTarifa(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifas.this);
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
                      variableAModificar = "Dsi";
                      valor = (String) getColumnModel().getColumn(4).getCellEditor().getCellEditorValue();
                    ModificarTarifa modificador = new ModificarTarifa(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifas.this);
                       try {
                            modificador.modificarTarifa();
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
                      variableAModificar = "Sar";
                      valor = (String) getColumnModel().getColumn(5).getCellEditor().getCellEditorValue();
                    ModificarTarifa modificador = new ModificarTarifa(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifas.this);
                       try {
                            modificador.modificarTarifa();
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
                      variableAModificar = "Espera";
                      valor = (String) getColumnModel().getColumn(6).getCellEditor().getCellEditorValue();
                    ModificarTarifa modificador = new ModificarTarifa(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifas.this);
                       try {
                            modificador.modificarTarifa();
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
                   variableAModificar = "Custodia";
                   valor = (String) getColumnModel().getColumn(7).getCellEditor().getCellEditorValue();
                   ModificarTarifa modificador = new ModificarTarifa(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifas.this);
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
     
     
     
     

    

    
    public void mostrarTarifas(List<Tarifa> lista) {
         tablaCRUDModelo.actualizarLista(lista);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarTarifasCommand cargarTarifasCommand) {
        this.cargarTarifasCommand = cargarTarifasCommand;
    }
}
   

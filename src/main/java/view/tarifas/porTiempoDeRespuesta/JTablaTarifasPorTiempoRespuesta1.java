package view.tarifas.porTiempoDeRespuesta;


import command.tarifas.CargarRecorridosyKmCommand;
import command.tarifas.CargarTarifasPorTiempoDeRespuestaCommand;
import command.tarifas.CargarTiposDeTarifaCommand;
import view.productos.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.IOException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.GroupLayout.Alignment.values;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import model.RecorridosyKm;
import model.Tarifas.TarifaPorTiempoDeRespuesta;
import model.Tarifas.TipoDeTarifa;
import process.CRUDTarifas.ModificarTarifa;
import process.CRUDTarifasPorTiempoDeRespuesta.ModificarTarifaPorTiempoDeRespuesta;
import process.cargador.tarifas.CargadorListaTiposDeTarifa;
import process.parser.tipoDeTarifas.TipoDeTarifaParserJson;
import view.tarifas.simples.EditorCeldaTabla;
import view.tarifas.simples.JTablaTarifas;






public final class JTablaTarifasPorTiempoRespuesta1 extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaTarifasPorTiempoDeRespuestaModelo1 tablaCRUDModelo;
    private CargarTarifasPorTiempoDeRespuestaCommand cargarTarifasCommand;
    private static JTablaTarifasPorTiempoRespuesta1 instance;
    private TableRowSorter sorter;
    private ImageFrame image;
    
    public JTablaTarifasPorTiempoRespuesta1() {
       setTable();
       setRowSorter();

       this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
       
   //    setListenerModificaCelda();
       setDefaultRenderer(JComboBoxTipoDeTarifa.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                
                return (Component) objeto;
            }
        });
     
       
       getColumnModel().getColumn(2).setCellEditor(new EditorCeldaTabla());
     //  getColumnModel().getColumn(1).setCellEditor(new EditorCeldaTablaComboBox());
     
       getColumnModel().getColumn(3).setCellEditor(new EditorCeldaTablaInteger());
       getColumnModel().getColumn(4).setCellEditor(new EditorCeldaTablaInteger());
       getColumnModel().getColumn(5).setCellEditor(new EditorCeldaTablaInteger());
       getColumnModel().getColumn(6).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(7).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(8).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(9).setCellEditor(new EditorCeldaTabla());
       
        setListeners();

    
    
    
    
    
    
    }
    

 
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           
     }

     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
     }
   
  
   
    public void setTable() {
        
        try {
            tablaCRUDModelo = new TablaTarifasPorTiempoDeRespuestaModelo1();
            setModel(tablaCRUDModelo);
         
            // todo esto es para poner el combobox de la celda tipo de tarifa
            CargarTiposDeTarifaCommand cargar = new CargarTiposDeTarifaCommand(new CargadorListaTiposDeTarifa(), new TipoDeTarifaParserJson());
            List<TipoDeTarifa> lista = cargar.executeLista();
            TableColumn col = getColumnModel().getColumn(1);
            String[] array = new String[lista.size()];
            for (int i = 0; i<lista.size(); i++) {
                array[i] = lista.get(i).getTipo();
            }
            
            
            col.setCellEditor(new MyComboBoxEditor(array));
            col.setCellRenderer(new MyComboBoxRenderer(array));
            
            tablaCRUDModelo.setCombo();
        } catch (IOException ex) {
            Logger.getLogger(JTablaTarifasPorTiempoRespuesta1.class.getName()).log(Level.SEVERE, null, ex);
        }
         
       
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
            @Override
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "tarifa";
                      valor = (String) getColumnModel().getColumn(2).getCellEditor().getCellEditorValue();
                    ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta1.this);
                       try {
                            modificador.modificarTarifa();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
            @Override
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
      
      
      
        
         getColumnModel().getColumn(3).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "hastaKm";
                      valor = (String) getColumnModel().getColumn(3).getCellEditor().getCellEditorValue();
                    ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta1.this);
                       try {
                            modificador.modificarTarifa();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
            @Override
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
      
      
      
        getColumnModel().getColumn(4).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "Desde";
                      valor = (String) getColumnModel().getColumn(4).getCellEditor().getCellEditorValue();
                    ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta1.this);
                       try {
                            modificador.modificarTarifa();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
            @Override
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
       getColumnModel().getColumn(5).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "Hasta";
                      valor = (String) getColumnModel().getColumn(5).getCellEditor().getCellEditorValue();
                    ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta1.this);
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
                      variableAModificar = "Servicio";
                      valor = (String) getColumnModel().getColumn(6).getCellEditor().getCellEditorValue();
                    ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta1.this);
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
                   variableAModificar = "Km";
                   valor = (String) getColumnModel().getColumn(7).getCellEditor().getCellEditorValue();
                   ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta1.this);
                       try {
                            modificador.modificarTarifa();
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
                   variableAModificar = "Ris";
                   valor = (String) getColumnModel().getColumn(8).getCellEditor().getCellEditorValue();
                   ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta1.this);
                       try {
                            modificador.modificarTarifa();
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
                   variableAModificar = "SegundaSalida";
                   valor = (String) getColumnModel().getColumn(9).getCellEditor().getCellEditorValue();
                   ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta1.this);
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
     
     
     
     
     
     
     

    
    public void mostrarTarifas(List<TarifaPorTiempoDeRespuesta> lista) {
         tablaCRUDModelo.actualizarLista(lista);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarTarifasPorTiempoDeRespuestaCommand cargarTarifasCommand) {
        this.cargarTarifasCommand = cargarTarifasCommand;
    }
}
   

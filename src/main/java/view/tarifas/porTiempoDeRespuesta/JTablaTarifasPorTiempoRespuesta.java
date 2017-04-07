package view.tarifas.porTiempoDeRespuesta;


import command.tarifas.CargarRecorridosyKmCommand;
import command.tarifas.CargarTarifasPorTiempoDeRespuestaCommand;
import command.tarifas.CargarTiposDeTarifaCommand;
import view.productos.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import process.cargador.tarifas.CargadorListaTarifas;
import process.cargador.tarifas.CargadorListaTiposDeTarifa;
import process.parser.tarifas.TarifaPorTiempoDeRespuestaParserJson;
import process.parser.tipoDeTarifas.TipoDeTarifaParserJson;
import view.fichaCliente.SingletonCliente;
import view.tarifas.simples.EditorCeldaTabla;
import view.tarifas.simples.JTablaTarifas;






public final class JTablaTarifasPorTiempoRespuesta extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaTarifasPorTiempoDeRespuestaModelo tablaCRUDModelo;
    private CargarTarifasPorTiempoDeRespuestaCommand cargarTarifasCommand;
    private static JTablaTarifasPorTiempoRespuesta instance;
    private TableRowSorter sorter;
    private ImageFrame image;
    
    public JTablaTarifasPorTiempoRespuesta() {
       setTable();
     
       this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
       
   //    setListenerModificaCelda();
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                
                return (Component) objeto;
            }
        });
     
          getColumnModel().getColumn(0).setMinWidth(0);
          getColumnModel().getColumn(0).setMaxWidth(0);
          
    
       getColumnModel().getColumn(2).setCellEditor(new EditorCeldaTablaInteger());
       getColumnModel().getColumn(3).setCellEditor(new EditorCeldaTablaInteger());
       getColumnModel().getColumn(4).setCellEditor(new EditorCeldaTablaInteger());
       getColumnModel().getColumn(5).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(6).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(7).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(8).setCellEditor(new EditorCeldaTabla());
       
        setListeners();

    
    
    
    
    
    
    }
    

 
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           
     }

    @Override
    public TablaTarifasPorTiempoDeRespuestaModelo getModel() {
        return tablaCRUDModelo;
    }

     public void actualizar () {
        try {
            CargarTarifasPorTiempoDeRespuestaCommand cargarTarifasCommand = new CargarTarifasPorTiempoDeRespuestaCommand (CargadorListaTarifas.getInstance(), new TarifaPorTiempoDeRespuestaParserJson());
            mostrarTarifas(cargarTarifasCommand.executeLista(SingletonCliente.getInstance().getCliente().getId()));
        } catch (IOException ex) {
            Logger.getLogger(JTablaTarifasPorTiempoRespuesta.class.getName()).log(Level.SEVERE, null, ex);
        }
           
     
     }
     
     
     
     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
     }
   
  
     public void modificarTarifa(String valor) {
     
           ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), "tarifa", valor);
           modificador.setTabla(JTablaTarifasPorTiempoRespuesta.this);
           try {
               modificador.modificarTarifa();
            } catch (IOException ex) {
                Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
     }
   
    public void setTable() {
        
       
            tablaCRUDModelo = new TablaTarifasPorTiempoDeRespuestaModelo();
            setModel(tablaCRUDModelo);
         
      
       
      }
    
    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
    */

  
    
   

      public void setListeners() {
   
         
           addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            
                       Integer id = (Integer) getValueAt(rowAtPoint(e.getPoint()), 0);
                       if (e.getClickCount() == 2) {
                       if (columnAtPoint(e.getPoint()) == 1) {
                               EleccionTipoDeTarifaFrame.getInstance().setVisible(true);
                               
                           }
                       }
               
            }

               });
           
               
       
      
      
      
        
         getColumnModel().getColumn(2).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "hastaKm";
                      valor = (String) getColumnModel().getColumn(2).getCellEditor().getCellEditorValue();
                    ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta.this);
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
                      variableAModificar = "Desde";
                      valor = (String) getColumnModel().getColumn(3).getCellEditor().getCellEditorValue();
                    ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta.this);
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
                      variableAModificar = "Hasta";
                      valor = (String) getColumnModel().getColumn(4).getCellEditor().getCellEditorValue();
                    ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta.this);
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
            @Override
                public void editingStopped(ChangeEvent e){
                      variableAModificar = "Servicio";
                      valor = (String) getColumnModel().getColumn(5).getCellEditor().getCellEditorValue();
                    ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta.this);
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
            @Override
                public void editingStopped(ChangeEvent e){
                   variableAModificar = "Km";
                   valor = (String) getColumnModel().getColumn(6).getCellEditor().getCellEditorValue();
                   ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta.this);
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
            @Override
                public void editingStopped(ChangeEvent e){
                   variableAModificar = "Ris";
                   valor = (String) getColumnModel().getColumn(7).getCellEditor().getCellEditorValue();
                   ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta.this);
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
        
      
              getColumnModel().getColumn(8).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                   variableAModificar = "SegundaSalida";
                   valor = (String) getColumnModel().getColumn(8).getCellEditor().getCellEditorValue();
                   ModificarTarifaPorTiempoDeRespuesta modificador = new ModificarTarifaPorTiempoDeRespuesta(tablaCRUDModelo.getListaTarifas().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaTarifasPorTiempoRespuesta.this);
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
   

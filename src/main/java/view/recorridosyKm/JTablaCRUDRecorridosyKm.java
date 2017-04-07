package view.recorridosyKm;


import command.tarifas.CargarRecorridosyKmCommand;
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
import javax.swing.table.TableRowSorter;
import model.RecorridosyKm;
import process.CRUDMunicipios.ModificarMunicipio;
import process.CRUDRecorridosyKm.ModificarRecorridosYKilometros;
import process.CRUDTarifas.ModificarTarifa;
import process.CRUDTarifasPorTiempoDeRespuesta.ModificarTarifaPorTiempoDeRespuesta;
import process.cargador.recorridosykm.CargadorListaRecorridosyKm;
import process.parser.recorridosykm.RecorridosyKmParserJson;
import view.fichaCliente.SingletonCliente;
import view.municipios.EleccionMunicipioFrame;
import view.municipios.MunicipioCRUDFrame;
import view.tarifas.porTiempoDeRespuesta.EleccionTipoDeTarifaFrame;
import view.tarifas.simples.EditorCeldaTabla;
import view.tarifas.simples.JTablaTarifas;







public final class JTablaCRUDRecorridosyKm extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaCRUDRecorridosyKmModelo tablaCRUDModelo;
    private CargarRecorridosyKmCommand cargarRecorridosykmCommand;

    private TableRowSorter sorter;
    private ImageFrame image;
    
    
    
    int columnaSeleccionada;
    int filaSeleccionada;
    
    public JTablaCRUDRecorridosyKm() {
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
      
      
       getColumnModel().getColumn(1).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(2).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(3).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(4).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(5).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(6).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(7).setCellEditor(new EditorCeldaTabla());
   
       setListeners();
     
      }
    


  
   
    public void setTable() {
        tablaCRUDModelo = new TablaCRUDRecorridosyKmModelo();
        setModel(tablaCRUDModelo);
      }
    
    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
    */

    @Override
    public TablaCRUDRecorridosyKmModelo getModel() {
        return tablaCRUDModelo;
    }

    
    
   
    
       
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           
     }

     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
     }
   
    

     public void setRowSorter () {
     	  sorter = new TableRowSorter(tablaCRUDModelo);
          this.setRowSorter(sorter);
         //sorter.setRowFilter (RowFilter.regexFilter(".",0)); 
      
     }
     
     
     
       public void modificarTarifa(String valor) {
     
           ModificarRecorridosYKilometros modificador = new ModificarRecorridosYKilometros(tablaCRUDModelo.getListaMunicipio().get(getSelectedRow()).getId(), "municipio", valor);
           try {
               modificador.setTabla(this);
               modificador.modificarRecorridosyKm();
               
            } catch (IOException ex) {
                Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
     }
     
       
       public void actualizar() {
           CargarRecorridosyKmCommand cargador = new CargarRecorridosyKmCommand (CargadorListaRecorridosyKm.getInstance(), new RecorridosyKmParserJson());
           mostrarRecorridosyKm(cargador.executeLista(SingletonCliente.getInstance().getCliente().getId()));
          
       
       }
    
    public void setListeners() {
         addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2) {
                       if (columnAtPoint(e.getPoint()) == 0) {
                               EleccionMunicipioFrame.getInstance().setVisible(true);
                               
                           }
                       }
               
            }

               });
           
         
        
        
        getColumnModel().getColumn(1).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
                public void editingStopped(ChangeEvent e){
                       variableAModificar = "IDA";
                       valor = (String) getColumnModel().getColumn(1).getCellEditor().getCellEditorValue();
                       ModificarRecorridosYKilometros modificador = new ModificarRecorridosYKilometros(tablaCRUDModelo.getListaMunicipio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaCRUDRecorridosyKm.this);
                       try {
                            modificador.modificarRecorridosyKm();
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
                       variableAModificar = "Kmtotal";
                       valor = (String) getColumnModel().getColumn(2).getCellEditor().getCellEditorValue();
                       ModificarRecorridosYKilometros modificador = new ModificarRecorridosYKilometros(tablaCRUDModelo.getListaMunicipio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaCRUDRecorridosyKm.this);
                       try {
                            modificador.modificarRecorridosyKm();
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
                       variableAModificar = "Turismo";
                       valor = (String) getColumnModel().getColumn(3).getCellEditor().getCellEditorValue();
                       ModificarRecorridosYKilometros modificador = new ModificarRecorridosYKilometros(tablaCRUDModelo.getListaMunicipio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaCRUDRecorridosyKm.this);
                       try {
                            modificador.modificarRecorridosyKm();
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
                       variableAModificar = "Todoterreno";
                       valor = (String) getColumnModel().getColumn(4).getCellEditor().getCellEditorValue();
                       ModificarRecorridosYKilometros modificador = new ModificarRecorridosYKilometros(tablaCRUDModelo.getListaMunicipio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaCRUDRecorridosyKm.this);
                       try {
                            modificador.modificarRecorridosyKm();
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
                       variableAModificar = "Furgon";
                       valor = (String) getColumnModel().getColumn(5).getCellEditor().getCellEditorValue();
                       ModificarRecorridosYKilometros modificador = new ModificarRecorridosYKilometros(tablaCRUDModelo.getListaMunicipio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaCRUDRecorridosyKm.this);
                       try {
                            modificador.modificarRecorridosyKm();
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
                       variableAModificar = "Maquinaria";
                       valor = (String) getColumnModel().getColumn(6).getCellEditor().getCellEditorValue();
                       ModificarRecorridosYKilometros modificador = new ModificarRecorridosYKilometros(tablaCRUDModelo.getListaMunicipio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaCRUDRecorridosyKm.this);
                       try {
                            modificador.modificarRecorridosyKm();
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
                       variableAModificar = "Tarifaparamasdetres";
                       valor = (String) getColumnModel().getColumn(7).getCellEditor().getCellEditorValue();
                       ModificarRecorridosYKilometros modificador = new ModificarRecorridosYKilometros(tablaCRUDModelo.getListaMunicipio().get(getSelectedRow()).getId(), variableAModificar, valor);
                       modificador.setTabla(JTablaCRUDRecorridosyKm.this);
                       try {
                            modificador.modificarRecorridosyKm();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                       
                }
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
    
 
    }


    
    public void mostrarRecorridosyKm(List<RecorridosyKm> listaMunicipios) {
         tablaCRUDModelo.actualizarListaMunicipios(listaMunicipios);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarRecorridosyKmCommand cargarMunicipiosCommand) {
        this.cargarRecorridosykmCommand = cargarMunicipiosCommand;
    }
    
    
    
    
    
}
   

package view.gruas;

import DatosPrecargados.SingletonGruas;
import command.gruas.CargarGruasCommand;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;

import javax.swing.table.TableRowSorter;
import model.gruas.Grua;
import process.CRUDGruas.ModificarGrua;
import view.gruistas.EleccionGruistaFrame;
import view.tarifas.simples.EditorCeldaTabla;
import view.tarifas.simples.JTablaTarifas;





public final class TablaGruasCRUD extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaGruasModelo tablaGruasModelo;
    private CargarGruasCommand cargarGruasCommand;
    private static TablaGruasCRUD instance;
    
    
   


    private TablaGruasCRUD() {
        setTable();
        this.setFont(new java.awt.Font("Arial", 0, 16));
        this.setRowHeight(30);
        this.setDefaultRenderer(Object.class, new FormatoTablaGruas());
       
        getColumnModel().getColumn(0).setMaxWidth(0);
        getColumnModel().getColumn(0).setMinWidth(0);
        
          
        getColumnModel().getColumn(1).setCellEditor(new EditorCeldaTabla());
        // la columna 2 tiene un doble click listener para desplegar nuevo frame de seleccion
        getColumnModel().getColumn(3).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(4).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(5).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(6).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(7).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(8).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(9).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(10).setCellEditor(new EditorCeldaTabla());
        getColumnModel().getColumn(11).setCellEditor(new EditorCeldaTabla());

      this.setListeners();
       
      }
    
    public static TablaGruasCRUD getInstance() {
        if (instance == null) instance= new TablaGruasCRUD();
        return instance;
    }
      public void habilitarEdicionTabla() {
           tablaGruasModelo.setEditable(true);
           
     }

     public void deshabilitarEdicionTabla() {
          tablaGruasModelo.setEditable(false);
     }
   
    
    
    public void setListeners() {
     
       addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 Integer numGrua = (Integer) getValueAt(rowAtPoint(e.getPoint()), 0);
                 
                  
                if (e.getClickCount() == 2) {
                    if ( columnAtPoint(e.getPoint()) == 2) {
                        GruasCRUDFrame.getInstance().setFileSeleccionada(rowAtPoint(e.getPoint()));
                        EleccionGruistaFrame.getInstance().setVisible(true);
                    }

                 }

                 
                
             }
            @Override
            public void mouseReleased(MouseEvent e) {
            } 
            
            
        });
       
       
           getColumnModel().getColumn(1).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                       variableAModificar = "numGrua";
                       valor = (String) getColumnModel().getColumn(1).getCellEditor().getCellEditorValue();
                       ModificarGrua modificador = new ModificarGrua(tablaGruasModelo.getListaGruas().get(getSelectedRow()).getId(), variableAModificar, valor);
                     
                       try {
                            modificador.modificarGrua();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                     SingletonGruas.getInstance().actualizarLista();
                     mostrarGruas(SingletonGruas.getInstance().getLista());
        
                    
                       
                }
            @Override
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
       
       
       
       
       
        getColumnModel().getColumn(5).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                       variableAModificar = "mensaje";
                       valor = (String) getColumnModel().getColumn(5).getCellEditor().getCellEditorValue();
                       ModificarGrua modificador = new ModificarGrua(tablaGruasModelo.getListaGruas().get(getSelectedRow()).getId(), variableAModificar, valor);
                     
                       try {
                            modificador.modificarGrua();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                     SingletonGruas.getInstance().actualizarLista();
                     mostrarGruas(SingletonGruas.getInstance().getLista());
        
                    
                       
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
                       variableAModificar = "tipo";
                       valor = (String) getColumnModel().getColumn(3).getCellEditor().getCellEditorValue();
                       ModificarGrua modificador = new ModificarGrua(tablaGruasModelo.getListaGruas().get(getSelectedRow()).getId(), variableAModificar, valor);
                     
                       try {
                            modificador.modificarGrua();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                     SingletonGruas.getInstance().actualizarLista();
                     mostrarGruas(SingletonGruas.getInstance().getLista());
        
                    
                       
                }
            @Override
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
        
          getColumnModel().getColumn(6).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                       variableAModificar = "rotulado";
                       valor = (String) getColumnModel().getColumn(6).getCellEditor().getCellEditorValue();
                       ModificarGrua modificador = new ModificarGrua(tablaGruasModelo.getListaGruas().get(getSelectedRow()).getId(), variableAModificar, valor);
                     
                       try {
                            modificador.modificarGrua();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                     SingletonGruas.getInstance().actualizarLista();
                     mostrarGruas(SingletonGruas.getInstance().getLista());
        
                    
                       
                }
            @Override
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
        
       getColumnModel().getColumn(7).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                       variableAModificar = "horario";
                       valor = (String) getColumnModel().getColumn(7).getCellEditor().getCellEditorValue();
                       ModificarGrua modificador = new ModificarGrua(tablaGruasModelo.getListaGruas().get(getSelectedRow()).getId(), variableAModificar, valor);
                     
                       try {
                            modificador.modificarGrua();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                     SingletonGruas.getInstance().actualizarLista();
                     mostrarGruas(SingletonGruas.getInstance().getLista());
        
                    
                       
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
                       variableAModificar = "km";
                       valor = (String) getColumnModel().getColumn(8).getCellEditor().getCellEditorValue();
                       ModificarGrua modificador = new ModificarGrua(tablaGruasModelo.getListaGruas().get(getSelectedRow()).getId(), variableAModificar, valor);
                     
                       try {
                            modificador.modificarGrua();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                     SingletonGruas.getInstance().actualizarLista();
                     mostrarGruas(SingletonGruas.getInstance().getLista());
        
                    
                       
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
                       variableAModificar = "km";
                       valor = (String) getColumnModel().getColumn(8).getCellEditor().getCellEditorValue();
                       ModificarGrua modificador = new ModificarGrua(tablaGruasModelo.getListaGruas().get(getSelectedRow()).getId(), variableAModificar, valor);
                     
                       try {
                            modificador.modificarGrua();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                     SingletonGruas.getInstance().actualizarLista();
                     mostrarGruas(SingletonGruas.getInstance().getLista());
        
                    
                       
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
                       variableAModificar = "base";
                       valor = (String) getColumnModel().getColumn(4).getCellEditor().getCellEditorValue();
                       ModificarGrua modificador = new ModificarGrua(tablaGruasModelo.getListaGruas().get(getSelectedRow()).getId(), variableAModificar, valor);
                     
                       try {
                            modificador.modificarGrua();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                     SingletonGruas.getInstance().actualizarLista();
                     mostrarGruas(SingletonGruas.getInstance().getLista());
        
                    
                       
                }
            @Override
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
        
        
       getColumnModel().getColumn(10).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                       variableAModificar = "demora";
                       valor = (String) getColumnModel().getColumn(10).getCellEditor().getCellEditorValue();
                       ModificarGrua modificador = new ModificarGrua(tablaGruasModelo.getListaGruas().get(getSelectedRow()).getId(), variableAModificar, valor);
                     
                       try {
                            modificador.modificarGrua();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                     SingletonGruas.getInstance().actualizarLista();
                     mostrarGruas(SingletonGruas.getInstance().getLista());
        
                    
                       
                }
            @Override
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
        
       
       
       getColumnModel().getColumn(11).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                       variableAModificar = "estado";
                       valor = (String) getColumnModel().getColumn(11).getCellEditor().getCellEditorValue();
                       ModificarGrua modificador = new ModificarGrua(tablaGruasModelo.getListaGruas().get(getSelectedRow()).getId(), variableAModificar, valor);
                     
                       try {
                            modificador.modificarGrua();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                     SingletonGruas.getInstance().actualizarLista();
                     mostrarGruas(SingletonGruas.getInstance().getLista());
        
                    
                       
                }
            @Override
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
        
       
    }
    
   
   

   
  
    public void setTable() {
        tablaGruasModelo = new TablaGruasModelo();
        setModel(tablaGruasModelo);
        
       }
   
    
   

  

    public void mostrarGruas(List<Grua> listaGruas) {
          tablaGruasModelo.actualizarListaGruas(listaGruas);
          tablaGruasModelo.fireTableDataChanged();
    }

    public void setCargarGruasCommand(CargarGruasCommand cargarGruasCommand) {
        this.cargarGruasCommand = cargarGruasCommand;
    }

  
}

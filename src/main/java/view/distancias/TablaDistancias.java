package view.distancias;


import DatosPrecargados.SingletonNumRespuesta;
import command.distancias.CargarDistanciasCommand;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableRowSorter;
import model.Distancia;
import process.CRUDDistancia.ModificarDistancia;
import view.tarifas.simples.EditorCeldaTabla;






public final class TablaDistancias extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaDistanciasModelo tablaCRUDModelo;
    private CargarDistanciasCommand cargarCommand;
    private static TablaDistancias instance;
    private TableRowSorter sorter;
    private boolean eliminar;
    
   
   
    private TablaDistancias() {
       setTable();
       eliminar = false;
       this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
       getColumnModel().getColumn(0).setMinWidth(0);
       getColumnModel().getColumn(0).setMaxWidth(0);
       getColumnModel().getColumn(3).setCellEditor(new EditorCeldaTabla());
       setEditors();
     
  
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
     
       
       
       //setListeners();

    
    }

    public void setEditors() {
       
          getColumnModel().getColumn(3).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";

            @Override
            public void editingStopped(ChangeEvent e) {
                        valor = (String) getColumnModel().getColumn(3).getCellEditor().getCellEditorValue();
                     
                        ModificarDistancia modificar = null;
                        
                        modificar = new ModificarDistancia((Integer) getValueAt(getSelectedRow(),0), "kms", valor);
                        try {
                            modificar.modificar();
                            
                            //   SingletonDistancias.getInstance().actualizarLista();
                        } catch (IOException ex) {
                            Logger.getLogger(TablaDistancias.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    
                        DistanciasFrame.getInstance().actualizarTabla();
                     
              
             }
                @Override
              public void editingCanceled(ChangeEvent e) {

              }
        });
           
}
        
    
    
    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }
    
    
    public static TablaDistancias getInstance() {
        
        if (instance == null) instance = new TablaDistancias();
        return instance;
    
    }

    
    
    
 
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           setListeners();
           
     }

     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
          removeListeners();
     }
   
  
   
    public void setTable() {
        tablaCRUDModelo = new TablaDistanciasModelo();
        setModel(tablaCRUDModelo);
      }
    
 

    
    @Override
   public TablaDistanciasModelo getModel() {
      return tablaCRUDModelo;
   
   }

  
     
     
     
     
     

    
    public void mostrar(List<Distancia> lista) {
         tablaCRUDModelo.actualizarLista(lista);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarDistanciasCommand cargarCommand) {
        this.cargarCommand = cargarCommand;
    }

    private void removeListeners() {
       this.removeMouseListener(mouse);
    }
    
    private void setListeners() {
        addMouseListener(mouse);
    
    
    }    
     
    MouseListener mouse =  new MouseAdapter() {
      
        @Override
        public void mouseClicked(MouseEvent e) {
            Integer id = (Integer) getValueAt(rowAtPoint(e.getPoint()), 0);
              
                 if (columnAtPoint(e.getPoint()) == 1) {
                     EleccionMunicipioFrameParaDistancias.getInstance().setOrigenODestino("origen");
                     EleccionMunicipioFrameParaDistancias.getInstance().setVisible(true);
                    
                }
                  if (columnAtPoint(e.getPoint()) == 2) {
                     EleccionMunicipioFrameParaDistancias.getInstance().setOrigenODestino("destino");
                     EleccionMunicipioFrameParaDistancias.getInstance().setVisible(true);
                    
                }
            }

       
      };
    
    
}
   

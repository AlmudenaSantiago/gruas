package view.basesOperativas;


import DatosPrecargados.SingletonBasesOperativas;
import command.basesOperativas.CargarBasesOperativasCommand;
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
import model.basesOperativas.BaseOperativa;
import process.CRUDBasesOperativas.ModificarBaseOperativa;
import view.tarifas.simples.EditorCeldaTabla;






public final class JTablaBasesOperativasCRUD extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaBasesOperativasModeloCRUD tablaCRUDModelo;
    private CargarBasesOperativasCommand cargarCommand;
    private static JTablaBasesOperativasCRUD instance;
    private TableRowSorter sorter;
   private boolean eliminar;
    
    public JTablaBasesOperativasCRUD() {
       setTable();
       eliminar = false;
       this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
       
   //    setListenerModificaCelda();
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
     
       
    
       getColumnModel().getColumn(0).setCellEditor(new EditorCeldaTabla());
 
       
       
        setListeners();

    
    
    
    
    
    
    
    
    
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }
    

    
    
    
 
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           
     }

     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
     }
   
  
   
    public void setTable() {
        tablaCRUDModelo = new TablaBasesOperativasModeloCRUD();
        setModel(tablaCRUDModelo);
      }
    
    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
    */


    
    @Override
   public TablaBasesOperativasModeloCRUD getModel() {
      return tablaCRUDModelo;
   
   }

   
   public void modificarMunicipioBase(Integer idMunicipio) {
              String variableAModificar = "idMunicipio";
                       ModificarBaseOperativa modificador = new ModificarBaseOperativa(tablaCRUDModelo.getListaBasesOperativas().get(getSelectedRow()).getId(), variableAModificar, idMunicipio.toString());
                      modificador.setTabla(JTablaBasesOperativasCRUD.this);
                       try {
                            modificador.modificarBaseOperativa();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaBasesOperativasCRUD.class.getName()).log(Level.SEVERE, null, ex);
                        }
                     SingletonBasesOperativas.getInstance().actualizarLista();
                     BasesOperativasCRUDFrame.getInstance().actualizarTabla();
                  
     
   
   }
   
      public void setListeners() {
      
        getColumnModel().getColumn(0).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                     
                    if (eliminar == false) { 
                    
                      variableAModificar = "baseOperativa";
                      valor = (String) getColumnModel().getColumn(0).getCellEditor().getCellEditorValue();
                      System.out.println(valor + tablaCRUDModelo.getListaBasesOperativas().get(getSelectedRow()).getId());
                      ModificarBaseOperativa modificador = new ModificarBaseOperativa(tablaCRUDModelo.getListaBasesOperativas().get(getSelectedRow()).getId(), variableAModificar, valor.trim());
                      modificador.setTabla(JTablaBasesOperativasCRUD.this);
                       try {
                            modificador.modificarBaseOperativa();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaBasesOperativasCRUD.class.getName()).log(Level.SEVERE, null, ex);
                        }
                     SingletonBasesOperativas.getInstance().actualizarLista();
                     BasesOperativasCRUDFrame.getInstance().actualizarTabla();
                    }
                    
                }
            @Override
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
        
        
        
         addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                 if (columnAtPoint(e.getPoint()) == 1) {
                     EleccionMunicipioFrame.getInstance().setVisible(true);
                   
                }
                }
            }
            
        });

      }
     
     
     
     
     
     

    
    public void mostrar(List<BaseOperativa> lista) {
         tablaCRUDModelo.actualizarLista(lista);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarBasesOperativasCommand cargarCommand) {
        this.cargarCommand = cargarCommand;
    }
}
   

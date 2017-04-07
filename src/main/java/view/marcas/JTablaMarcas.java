package view.marcas;


import DatosPrecargados.SingletonMarcas;
import command.vehiculos.CargarMarcasCommand;
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
import model.vehiculos.Marca;
import process.CRUDMarcas.ModificarMarca;

import view.tarifas.simples.EditorCeldaTabla;






public final class JTablaMarcas extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaMarcasModelo tablaCRUDModelo;
    private CargarMarcasCommand cargarCommand;
    private static JTablaMarcas instance;
    private TableRowSorter sorter;
   private boolean eliminar;
    
    private JTablaMarcas() {
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
    

   public static JTablaMarcas getInstance() {
      if (instance == null)  instance = new JTablaMarcas();
      return instance;
          
   }     
    
    
 
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           
     }

     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
     }
   
  
   
    public void setTable() {
        tablaCRUDModelo = new TablaMarcasModelo();
        setModel(tablaCRUDModelo);
      }
    
    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
    */


    
    @Override
   public TablaMarcasModelo getModel() {
      return tablaCRUDModelo;
   
   }

      public void setListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            
                       if  (e.getClickCount() == 2) {
                             MarcasCRUDFrame.getInstance().actualizarTablaModelos(SingletonMarcas.getInstance().getLista().get(getSelectedRow()).getId());
                       }
            }
           });
          
          
          
        
          
                
        getColumnModel().getColumn(0).getCellEditor().addCellEditorListener(new CellEditorListener(){
         
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                     
                    if (eliminar == false) { 
                    
                        try {
                            variableAModificar = "marca";
                            valor = (String) getColumnModel().getColumn(0).getCellEditor().getCellEditorValue();
                            ModificarMarca modificador = new ModificarMarca(tablaCRUDModelo.getListaMarcas().get(getSelectedRow()).getId(), variableAModificar, valor.trim());
                            modificador.modificarMarca();
                            SingletonMarcas.getInstance().actualizarLista();
                            MarcasCRUDFrame.getInstance().actualizarTablaMarcas();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaMarcas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }
            @Override
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });

      }

    public TablaMarcasModelo getTablaCRUDModelo() {
        return tablaCRUDModelo;
    }
     
     
     
    public void mostrar(List<Marca> lista) {
         tablaCRUDModelo.actualizarLista(lista);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarMarcasCommand cargarCommand) {
        this.cargarCommand = cargarCommand;
    }
}
   

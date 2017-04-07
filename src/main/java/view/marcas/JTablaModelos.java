package view.marcas;


import DatosPrecargados.SingletonMarcas;
import command.vehiculos.CargarMarcasCommand;
import command.vehiculos.CargarModelosCommand;
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
import model.basesOperativas.BaseOperativa;
import model.vehiculos.Marca;
import model.vehiculos.Modelo;
import process.CRUDMarcas.ModificarMarca;

import view.tarifas.simples.EditorCeldaTabla;






public final class JTablaModelos extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaModelosModelo tablaCRUDModelo;
    private CargarModelosCommand cargarCommand;
    private static JTablaModelos instance;
    private TableRowSorter sorter;
   private boolean eliminar;
    
    private JTablaModelos() {
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
    

   public static JTablaModelos getInstance() {
      if (instance == null)  instance = new JTablaModelos();
      return instance;
          
   }     
    
    
 
     public void habilitarEdicionTabla() {
           tablaCRUDModelo.setEditable(true);
           
     }

     public void deshabilitarEdicionTabla() {
          tablaCRUDModelo.setEditable(false);
     }
   
  
   
    public void setTable() {
        tablaCRUDModelo = new TablaModelosModelo();
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
                     
                    if (eliminar == false) { 
                    
                     
                            variableAModificar = "nombre";
                            valor = (String) getColumnModel().getColumn(0).getCellEditor().getCellEditorValue();
                        //    ModificarModelo modificador = new ModificarModelo(tablaCRUDModelo.getListaMarcas().get(getSelectedRow()).getId(), variableAModificar, valor.trim());
                          //  modificador.modificarMarca();
                          //   MarcasCRUDFrame.getInstance().actualizarTablaModelos();
                        
                    }
                    
                }
            @Override
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });

      }

    public TablaModelosModelo getTablaCRUDModelo() {
        return tablaCRUDModelo;
    }
     
     
     
    public void mostrar(List<Modelo> lista) {
         tablaCRUDModelo.actualizarLista(lista);
         tablaCRUDModelo.fireTableDataChanged();
    }

   
}
   

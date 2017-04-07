package view.municipios;


import DatosPrecargados.SingletonMunicipios;
import command.municipios.CargarMunicipiosCommand;
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
import javax.swing.table.TableRowSorter;
import model.Municipio;
import process.CRUDMunicipios.ModificarMunicipio;
import view.tarifas.simples.EditorCeldaTabla;
import view.tarifas.simples.JTablaTarifas;







public final class JTablaMunicipios extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaCRUDMunicipioModelo tablaCRUDModelo;
    private CargarMunicipiosCommand cargarRecorridosykmCommand;

    private TableRowSorter sorter;
    private ImageFrame image;
    
    
    
    int columnaSeleccionada;
    int filaSeleccionada;
    
    public JTablaMunicipios() {
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
     
       getColumnModel().getColumn(0).setCellEditor(new EditorCeldaTabla());
     
       setListeners();
     
      }
    


  
   
    public void setTable() {
        tablaCRUDModelo = new TablaCRUDMunicipioModelo();
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
    
       
   
    

     
     
     
     
    
    public void setListeners() {
       
          
        
        getColumnModel().getColumn(0).getCellEditor().addCellEditorListener(new CellEditorListener(){
            
            String valor ="";
            String variableAModificar = "";
            @Override
                public void editingStopped(ChangeEvent e){
                       variableAModificar = "municipio";
                       valor = (String) getColumnModel().getColumn(0).getCellEditor().getCellEditorValue();
                       ModificarMunicipio modificador = new ModificarMunicipio(tablaCRUDModelo.getListaMunicipio().get(getSelectedRow()).getId(), variableAModificar, valor);
                     
                       try {
                            modificador.modificar();
                        } catch (IOException ex) {
                            Logger.getLogger(JTablaTarifas.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                     SingletonMunicipios.getInstance().actualizarLista();
                     mostrar(SingletonMunicipios.getInstance().getLista());
        
                    
                       
                }
            @Override
                public void editingCanceled(ChangeEvent e){
                      
                
                }
        });
        
    }  
        


    
    public void mostrar(List<Municipio> listaMunicipios) {
         tablaCRUDModelo.actualizarListaMunicipios(listaMunicipios);
         tablaCRUDModelo.fireTableDataChanged();
    }

   
    
    
    
    
    
}
   

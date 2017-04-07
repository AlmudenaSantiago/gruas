
package view.marcas;

import view.basesOperativas.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;

import model.vehiculos.Modelo;



public class TablaModelosModelo extends AbstractTableModel {

    private List<Modelo> listaModelos;
    private Boolean editable = false;

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public List<Modelo> getListaModelos() {
        return listaModelos;
    }
    
    
    
    
    protected String[] columnNames = new String[] {
         
          "Modelo"
     
    };

    protected Class[] columnClasses = new Class[] {
        
         String.class,

    
     };

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public int getRowCount() {
        return (listaModelos != null) ? listaModelos.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }
 
    
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return editable;
    }
  

    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        
        Modelo modelo = listaModelos.get(rowIndex);
        switch (columnIndex){
            
            case 0:
                 return modelo.getModelo();
               
             }  
        return null;
             
        }
    
    /**
     *
     * @param lista
     */
    public void actualizarLista(List<Modelo> lista){
          if (lista == null) {
           List<Modelo> list = new ArrayList<>();
           this.listaModelos = list;
         } else{
           this.listaModelos = lista;
         }
       
    }
    
    
    

}





package view.marcas;

import view.basesOperativas.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.vehiculos.Marca;



public class TablaMarcasModelo extends AbstractTableModel {

    private List<Marca> listaMarcas;
    private Boolean editable = false;

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public List<Marca> getListaMarcas() {
        return listaMarcas;
    }
    
    
    
    
    protected String[] columnNames = new String[] {
         
          "Marca"
     
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
        return (listaMarcas != null) ? listaMarcas.size() : 0;
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
        
        Marca marca = listaMarcas.get(rowIndex);
        switch (columnIndex){
            
            case 0:
                 return marca.getMarca();
               
             }  
        return null;
             
        }
    
    /**
     *
     * @param lista
     */
    public void actualizarLista(List<Marca> lista){
          if (lista == null) {
           List<Marca> list = new ArrayList<>();
           this.listaMarcas = list;
         } else{
           this.listaMarcas = lista;
         }
       
    }

}




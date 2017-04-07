
package view.basesOperativas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.basesOperativas.BaseOperativa;


public class TablaBasesOperativasModeloCRUD extends AbstractTableModel {

    private List<BaseOperativa> listaBasesOperativas;
    private Boolean editable = false;

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public List<BaseOperativa> getListaBasesOperativas() {
        return listaBasesOperativas;
    }
    
    
    
    
    protected String[] columnNames = new String[] {
         
          "Base operativa", "Municipio de localización"
     
    };

    protected Class[] columnClasses = new Class[] {
        
         String.class, String.class

    
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
        return (listaBasesOperativas != null) ? listaBasesOperativas.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
 
    
    
    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 1) return false;
        return editable;
    }
  

    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        
        BaseOperativa base = listaBasesOperativas.get(rowIndex);
        switch (columnIndex){
            
            case 0:
                 return base.getBaseOperativa();
            case 1:
                 return base.getMunicipio();
               
             }  
        return null;
             
        }
    
    /**
     *
     * @param lista
     */
    public void actualizarLista(List<BaseOperativa> lista){
          if (lista == null) {
           List<BaseOperativa> list = new ArrayList<>();
           this.listaBasesOperativas = list;
         } else{
           this.listaBasesOperativas = lista;
         }
       
    }

}





package view.distancias;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Distancia;




public class TablaDistanciasModelo extends AbstractTableModel {

    private List<Distancia> lista;
    private Boolean editable = false;

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public List<Distancia> getListaModelos() {
        return lista;
    }
    
    
    
    
    protected String[] columnNames = new String[] {
         
          "Id","Municipio Origen","Municipio Destino", "Kms"
     
    };

    protected Class[] columnClasses = new Class[] {
        
         Integer.class, String.class, String.class, Double.class,

    
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
        return (lista != null) ? lista.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
 
    
    
    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 1) return false;
        if (column == 0) return false;
        if (column == 2) return false;
        return editable;
    }
  

    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        
        Distancia distancia = lista.get(rowIndex);
        switch (columnIndex){
            case 0:
                 return distancia.getId();
            
            case 1:
                 return distancia.getMunicipioOrigen();
            case 2:
                 return distancia.getMunicipioDestino();
            case 3:
                 return distancia.getKms();
                 
                
             }  
        return null;
             
        }
    
    /**
     *
     * @param lista
     */
    public void actualizarLista(List<Distancia> lista){
          if (lista == null) {
           List<Distancia> list = new ArrayList<>();
           this.lista = list;
         } else{
           this.lista = lista;
         }
       
    }
    
    
    

}




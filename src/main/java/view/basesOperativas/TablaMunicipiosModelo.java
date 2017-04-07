package view.basesOperativas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Municipio;


public class TablaMunicipiosModelo extends AbstractTableModel  {

    private List<Municipio> listaMunicipios;
    private String fechaOrdenada;
    
    
    protected String[] columnNames = new String[] {
          "Id", "Municipio"
                };

    protected Class[] columnClasses = new Class[] {
         String.class, String.class,   String.class, String.class }
            ;
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
        return (listaMunicipios != null) ? listaMunicipios.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return  2;
    }
    
 
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        
        Municipio municipio = listaMunicipios.get(rowIndex);
        switch (columnIndex){
         
            case 0:
                  return municipio.getId();
                
            case 1:
                  return municipio.getMunicipio();
   
           
             
        }
        
        return null;
    }


    
    public void actualizarLista(List<Municipio> listaMunicipios){
        
         if (listaMunicipios == null) {
            List<Municipio> list = new ArrayList<>();
            this.listaMunicipios = list;
         
         } else{
           this.listaMunicipios = listaMunicipios;
       
         }
        
        
    }


    
   
}

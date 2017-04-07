package view.fichasVehiculos;



import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;

import model.vehiculos.Vehiculo;


public class TablaVehiculosModelo extends AbstractTableModel {

    private List<Vehiculo> listaVehiculos;
    private Boolean editable = false;

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public List<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }
    
    
    
    
    protected String[] columnNames = new String[] {
         
          "Id", "Marca","Modelo","Tipo","Peso","Largo","Alto"
     
    };

    protected Class[] columnClasses = new Class[] {
        
         Integer.class, String.class, String.class, String.class, Double.class, Double.class, Double.class 

    
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
        return (listaVehiculos != null) ? listaVehiculos.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }
 
    
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return editable;
    }
  

    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        
        Vehiculo vehiculo = listaVehiculos.get(rowIndex);
        switch (columnIndex){
            case 0:
                 return vehiculo.getId();
            case 1:
                 return vehiculo.getMarca();
            case 2:
                 return vehiculo.getModelo();
            case 3:
                 return vehiculo.getTipo();
            case 4:
                 return vehiculo.getPeso();
            case 5:
                 return vehiculo.getLargo();
            case 6: 
                 return vehiculo.getAlto();
              }  
        return null;
             
        }
    
    /**
     *
     * @param lista
     */
    public void actualizarLista(List<Vehiculo> lista){
          if (lista == null) {
           List<Vehiculo> list = new ArrayList<>();
           this.listaVehiculos = list;
         } else{
           this.listaVehiculos = lista;
         }
       
    }

}




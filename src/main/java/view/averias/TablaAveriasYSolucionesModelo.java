package view.averias;



import view.fichasVehiculos.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.averiasYSoluciones.Averia;
import model.averiasYSoluciones.NumRespuesta;
import model.averiasYSoluciones.Solucion;

import model.vehiculos.Vehiculo;


public class TablaAveriasYSolucionesModelo extends AbstractTableModel {

    private List<Object> lista;
    private Boolean editable = false;

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public List<Object> getListaVehiculos() {
        return lista;
    }
    
    
    
    
    protected String[] columnNames = new String[] {
         
          "Id","Tipo","Codigo","Descripcion"
     
    };

    protected Class[] columnClasses = new Class[] {
        
         Integer.class, String.class, Integer.class, String.class 
    
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
        return editable;
    }
  

    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        
        Object obj  = lista.get(rowIndex);
        if (obj instanceof Averia) {
                Averia averia = (Averia) obj;
                switch (columnIndex){
                    case 0:
                         return averia.getId();
                    case 1:
                         return "Averia";
                    case 2:
                         return averia.getCodigo();
                    case 3:
                         return averia.getAveria();

              }  
        } else if (obj instanceof Solucion) {
                Solucion solucion = (Solucion) obj;
                switch (columnIndex){
                    case 0:
                         return solucion.getId();
                    case 1:
                         return "Solucion";
                    case 2:
                         return solucion.getCodigo();
                    case 3:
                         return solucion.getSolucion();
            
                }
        } else if (obj instanceof NumRespuesta) {
               NumRespuesta num = (NumRespuesta) obj;
                switch (columnIndex){
                    case 0:
                         return num.getId();
                    case 1:
                         return "Num Respuesta";
                    case 2:
                         return num.getCodigo();
                    case 3:
                         return num.getNumRespuesta();
           }
       
             
        }
         return null;
    }
    
    /**
     *
     * @param lista
     */
    public void actualizarLista(List<Object> lista){
          if (lista == null) {
           List<Object> list = new ArrayList<>();
           this.lista = list;
         } else{
           this.lista = lista;
         }
       
    }

}




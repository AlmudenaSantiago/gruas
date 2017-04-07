/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.serviciosEspeciales;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.serviciosEspeciales.ServicioEspecial;


public class TablaServiciosEspecialesModelo extends AbstractTableModel {

    private List<ServicioEspecial> listaServicio;
    private Boolean editable;

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public List<ServicioEspecial> getListaServicio() {
        return listaServicio;
    }
    
    
    
    
    protected String[] columnNames = new String[] {
          "Id",
          "Cte",
          "Servicio",
          "Servicio", 
          "Unidad",
          "Imp.Unidad",
          "Imp.Salida",
          "Suplemento",
          "Tipo",
          "Umbral",
          "IGIC",
          "Imp.Unidad (SUPL)",
          "Imp.Salida (SUPL)","Nombre"
     
    };

    protected Class[] columnClasses = new Class[] {
         Integer.class,
         Integer.class,
         String.class, String.class,
         String.class,
         Double.class,
         Double.class,
         Double.class,
         String.class,
         Integer.class,
         Integer.class,
         Double.class,
         Double.class,
         String.class
        
    
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
        return (listaServicio != null) ? listaServicio.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 14;
    }
 
   @Override
    public boolean isCellEditable (int row, int column) {
        return editable;
    }

    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        
        ServicioEspecial servicio = listaServicio.get(rowIndex);
        switch (columnIndex){
            
            case 0:
                 return servicio.getId();
            case 1:
                return servicio.getCliente();
                
            case 2:
                 return servicio.getServicio();
            case 3:
                return servicio.getNombreServicio();
          
            case 4: 
                return servicio.getUnidad();
                
            case 5:
                return servicio.getImporteUnidad();
            case 6:
               return servicio.getImporteSalida();
            case 7:
               return servicio.getSuplemento();
            case 8:
               return servicio.getTipo();
            case 9:
               return servicio.getUmbral();
            case 10: 
               return servicio.getIgic();
            case 11:
               return servicio.getImporteUnidadSuplemento();
            case 12:
                return servicio.getImporteSalidaSuplemento();
            case 13: 
                return servicio.getNombre();
               
             }  
        return null;
             
        }
    
    /**
     *
     * @param listaServicio
     */
    public void actualizarLista(List<ServicioEspecial> listaServicio){
          if (listaServicio == null) {
           List<ServicioEspecial> list = new ArrayList<>();
           this.listaServicio = list;
         } else{
           this.listaServicio = listaServicio;
         }
        
        
    }

}




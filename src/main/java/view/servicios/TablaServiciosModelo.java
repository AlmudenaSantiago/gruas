/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.servicios;

import model.Servicio;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import javax.swing.JComboBox;


public class TablaServiciosModelo extends AbstractTableModel {

    private List<Servicio> listaServicio;
   
    
    protected String[] columnNames = new String[] {
          "Id",
          "Grua",
          "Gruista", 
          "Aseguradpra",
          "Compañia",
          "Negocio",
          "Expediente",
          "Empresa",
          "Hora intervenciÃ³n",
          "Marca/Modelo",
          "Matricula",
          "Dir.Origen",
          "Poblacion",
          "Dir.Destino",
          "Poblacion",
          "Averia",
          "Estado",
          "Operador"
    };

    protected Class[] columnClasses = new Class[] {
         Integer.class,
         String.class,
         String.class,
         String.class,
         String.class,
         String.class,
         String.class,
         String.class,
         Date.class,
         String.class,
         String.class,
         String.class,
         String.class,
         String.class,
         String.class,
         String.class,
         String.class,
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
        return 18;
    }
 


    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        
        Servicio servicio = listaServicio.get(rowIndex);
        switch (columnIndex){
            
            case 0:
                 return servicio.getId();
            case 1:
                 return servicio.getGrua();
            case 2:
                return servicio.getGruista();
            case 3:
                return servicio.getAseguradora();
            case 4:
               return servicio.getCompania();
            case 5:
               return servicio.getNegocio();
            case 6:
               return servicio.getExpediente();
            case 7:
               return servicio.getEmpresa();
            case 8: 
               return servicio.getHoraIntervencion();
            case 9:
               return servicio.getMarca_modelo();
            case 10:
                return servicio.getMatricula();
            case 11:
                return servicio.getDireccionOrigen();
            case 12:
                return servicio.getPoblacionOrigen();
            case 13:
                return servicio.getDireccionDestino();
            case 14:
                return servicio.getPoblacionDestino();
            case 15:
                return servicio.getAveria();
            case 16:
               return servicio.getEstado();
            case 17:
                return servicio.getOperador();
             }  
        return null;
             
        }
    
    /**
     *
     * @param listaServicio
     */
    public void actualizarLista(List<Servicio> listaServicio){
          if (listaServicio == null) {
           List<Servicio> list = new ArrayList<>();
           this.listaServicio = list;
         } else{
           this.listaServicio = listaServicio;
         }
        
        
    }

}




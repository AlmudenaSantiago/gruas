/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tarifas.porTiempoDeRespuesta;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Tarifas.TarifaPorTiempoDeRespuesta;

/**
 *
 * @author loquat
 */
public class TablaTarifasPorTiempoDeRespuestaNocturnoModelo extends AbstractTableModel {

    private List<TarifaPorTiempoDeRespuesta> listaTarifas;
      private boolean editable;


    
         
    
    protected String[] columnNames = new String[] {
          "Id", "Servicio Nocturno", "KM Nocturno","Ris Nocturno","2ºSalida Nocturno"
    };

    protected Class[] columnClasses = new Class[] {
           Integer.class, Double.class, Double.class, Double.class, Double.class};

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
        return (listaTarifas != null) ? listaTarifas.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    
      
    @Override
    public boolean isCellEditable (int row, int column) {
        return editable;
    }
 
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        TarifaPorTiempoDeRespuesta tarifa = listaTarifas.get(rowIndex);
        switch (columnIndex){
            
            case 0:
                 return tarifa.getId();
            case 1:
                 return tarifa.getServicioNocturno();
            case 2:
                 return tarifa.getKmNocturno();
            case 3:
                 return tarifa.getRisNocturno();
            case 4:
                return tarifa.getSegundaSalidaNocturno();
        }
        
        return null;
    }

    
    
   
    public void actualizarLista(List<TarifaPorTiempoDeRespuesta> lista){
        this.listaTarifas = lista;
    
    }

    public List<TarifaPorTiempoDeRespuesta> getListaTarifas() {
        return listaTarifas;
    }

    
       public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    
}

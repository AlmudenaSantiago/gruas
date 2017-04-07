package view.tarifas.porTiempoDeRespuesta;


import javax.swing.table.AbstractTableModel;
import java.util.List;
import javax.swing.JComboBox;
import model.Tarifas.TarifaPorTiempoDeRespuesta;






public class TablaTarifasPorTiempoDeRespuestaModelo1 extends AbstractTableModel {

    private List<TarifaPorTiempoDeRespuesta> listaTarifas;
    private boolean editable;
    private  JComboBox combo;
    
         
    
    protected String[] columnNames = new String[] {
          "Id", "Tipo Tarifa","Tarifa","Hasta Km","Desde","Hasta","Servicio","KM","Ris","2ºSalida"
    };

    protected Class[] columnClasses = new Class[] {
           Integer.class, JComboBoxTipoDeTarifa.class, String.class, Integer.class, Integer.class,Integer.class,Integer.class, Double.class, Double.class, Double.class, Double.class};

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
        return 10;
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
               return combo.getSelectedItem();
            case 2:
                 return tarifa.getTarifa();
             case 3:
                  return tarifa.getHastaKm();
            case 4:
                  return tarifa.getDesde();
            case 5:
                  return tarifa.getHasta();
            case 6:
                 return tarifa.getServicio();
            case 7:
                 return tarifa.getKm();
            case 8:
                 return tarifa.getRis();
            case 9:
                return tarifa.getSegundaSalida();
        }
        
        return null;
    }

   
   
    
    public void actualizarLista(List<TarifaPorTiempoDeRespuesta> lista){
       
        this.listaTarifas = lista;
    
    }

    public void  setCombo() {
        combo = new JComboBoxTipoDeTarifa();
    }

    
    
    public List<TarifaPorTiempoDeRespuesta> getListaTarifas() {
        return listaTarifas;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
  
}

package view.tarifas.simples;


import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Tarifas.Tarifa;
import model.Tarifas.TarifaNocturno;






public class TablaTarifasNocturnoModelo extends AbstractTableModel {

    private List<TarifaNocturno> listaTarifas;
    private boolean editable;
   
    protected String[] columnNames = new String[] {
          "Sup","Urbano","Salida","Km","Rescate","DSI","SAR","Espera","Custodia"
    };

    protected Class[] columnClasses = new Class[] {
          Double.class,Double.class, Double.class, Double.class, Double.class,  Double.class, Double.class, Double.class, Double.class};

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
        return 9;
    }
    
    
    @Override
    public boolean isCellEditable (int row, int column) {
        return editable;
    }
    
    
    
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        TarifaNocturno tarifa = listaTarifas.get(rowIndex);
     
                switch (columnIndex){
                     case 0:
                         return tarifa.getSuplemento();
                    case 1:
                         return tarifa.getUrbano();
                    case 2:
                         return tarifa.getSalida();
                    case 3:
                          return tarifa.getKm();
                    case 4:
                          return tarifa.getRescate();
                   
                    case 5:
                          return tarifa.getDsi();
                    case 6:
                         return tarifa.getSar();
               
                    case 7:
                         return tarifa.getEspera();
                    case 8:
                        return tarifa.getCustodia();
                       
                }
          
      
        return null;
    }

   
    public void actualizarLista(List<TarifaNocturno> lista){
        this.listaTarifas = lista;
    
    }

    public List<TarifaNocturno> getListaTarifas() {
        return listaTarifas;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    
    
    

}

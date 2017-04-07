package view.tarifas.tiposDeTarifas;


import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Tarifas.TipoDeTarifa;






public class TablaTiposDeTarifasModelo1 extends AbstractTableModel {

    private List<TipoDeTarifa> listaTarifas;
   
    private boolean editable=false;

   

    public List<TipoDeTarifa> getListaTarifas() {
        return listaTarifas;
    }
    
    
    
         
    
    protected String[] columnNames = new String[] {
          "Tipo tarifa"
    };

    protected Class[] columnClasses = new Class[] {
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
        return (listaTarifas != null) ? listaTarifas.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }
    
    
    @Override
    public boolean isCellEditable (int row, int column) {
      return false;
     
    }
 
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        TipoDeTarifa tarifa = listaTarifas.get(rowIndex);
        switch (columnIndex){
            case 0:
                return tarifa.getTipo();
            
        }
        
        return null;
    }

   
    public void actualizarLista(List<TipoDeTarifa> lista){
        this.listaTarifas = lista;
    
    }

}

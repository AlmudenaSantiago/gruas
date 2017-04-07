package view.recorridosyKm;


import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.RecorridosyKm;






public class TablaCRUDRecorridosyKmModelo extends AbstractTableModel {

    private List<RecorridosyKm> lista;
    private boolean editable;

    
         
    
    protected String[] columnNames = new String[] {
          "A localidad", "IDA","Km total","Turismo,moto,monovolumen","Todo terreno 4x4 Furgon","Furgon Doble Rueda Camion Ligero","Maquinaria +2500kg","Tarifa para 3 o + VHC"
    };

    protected Class[] columnClasses = new Class[] {
           String.class, Double.class, Double.class, Double.class, Double.class,Double.class,Double.class, Double.class, Double.class};

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
        return 8;
    }
     
    
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    
  
 
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        RecorridosyKm rykm = lista.get(rowIndex);
        switch (columnIndex){
            case 0:
                return rykm.getMunicipio();
            case 1:
                return rykm.getIda();
            case 2:
                 return rykm.getKmtotal();
             case 3:
                  return rykm.getTurismo();
            case 4:
                  return rykm.getTodoterreno();
            case 5:
                  return rykm.getFurgon();
            case 6:
                 return rykm.getMaquinaria();
            case 7:
                 return rykm.getTarifaparamasdetres();
        }
        
        return null;
    }

   
    public void actualizarListaMunicipios(List<RecorridosyKm> lista){
        this.lista = lista;
    
    }

    public List<RecorridosyKm> getListaMunicipio() {
        return lista;
    }
    
    
    
    @Override
    public boolean isCellEditable (int row, int column) {
       
        if (column == 0) return false;
        return true;
    }
    
    

}

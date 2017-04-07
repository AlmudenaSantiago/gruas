package view.gruas;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.gruas.Grua;


public class TablaGruasModelo extends AbstractTableModel  {

    private List<Grua> listaGruas;
    private String fechaOrdenada;
    private boolean editable;
    
    protected String[] columnNames = new String[] {
          "Id","Num grua", "Conductor", "Tipo","Base","Mensaje","Rotulado","Horario", "km","Src","Demora","Estado"
    };

    protected Class[] columnClasses = new Class[] {
         String.class,Integer.class, String.class,   String.class, Integer.class , String.class, String.class, String.class, Double.class, String.class, String.class, String.class };
   

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
        return (listaGruas != null) ? listaGruas.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 12;
    }
    
 
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        
        Grua grua = listaGruas.get(rowIndex);
        switch (columnIndex){
         
            case 0:
                    return grua.getId();
            case 1:
                  return grua.getNumGrua(); 
            case 2:
                  return grua.getConductor();
            case 3:
                     return grua.getTipo();
            case 4:
                    return grua.getBase();
                        
            case 5:
                    return grua.getMensaje();
            
            case 6: 
                return grua.getRotulado();
            case 7: 
                    return grua.getHorario();
                
            case 8: 
                    return grua.getKm();
               
            case 9: 
                    return grua.getSrc();
            case 10:
                    return grua.getDemora();
            
            case 11:
                return grua.getEstado();
            
             
        }
        
        return null;
    }

    public List<Grua> getListaGruas() {
        return listaGruas;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }


    
    @Override
    public boolean isCellEditable(int row, int column) {
         if (column == 0 || column == 1) return false;
        return editable;
    
    }
    
    
    public void actualizarListaGruas(List<Grua> listaGruas){
        
         if (listaGruas == null) {
            List<Grua> list = new ArrayList<>();
            this.listaGruas = list;
         
         } else{
           this.listaGruas = listaGruas;
       
         }
        
        
    }


    
   
}

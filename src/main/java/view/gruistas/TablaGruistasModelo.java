package view.gruistas;

import view.gruas.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.gruas.Grua;
import model.gruas.Gruista;


public class TablaGruistasModelo extends AbstractTableModel  {

    private List<Gruista> listaGruistas;
    private String fechaOrdenada;
    
    
    protected String[] columnNames = new String[] {
          "Id", "Nombre", "Apellidos","Tipo"
                };

    protected Class[] columnClasses = new Class[] {
         String.class, String.class,   String.class, String.class }
            ;
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
        return (listaGruistas != null) ? listaGruistas.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return  4;
    }
    
 
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        
        Gruista gruista = listaGruistas.get(rowIndex);
        switch (columnIndex){
         
            case 0:
                  return gruista.getId();
                
            case 1:
                  return gruista.getNombre();
            case 2:
                     return gruista.getApellidos();
            case 3:
                    return gruista.getTipo();
           
             
        }
        
        return null;
    }


    
    public void actualizarListaGruas(List<Gruista> listaGruistas){
        
         if (listaGruistas == null) {
            List<Gruista> list = new ArrayList<>();
            this.listaGruistas = list;
         
         } else{
           this.listaGruistas = listaGruistas;
       
         }
        
        
    }


    
   
}

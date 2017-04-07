package view.contactos;


import javax.swing.table.AbstractTableModel;
import java.util.List;
import javax.swing.table.TableModel;
import model.Contacto;



public class TablaCRUDContactosModelo extends AbstractTableModel  implements TableModel {

    private List<Contacto> lista;

       
    protected String[] columnNames = new String[] {
            "Id", "Nombre" ,"Telefono", "Email"};

    protected Class[] columnClasses = new Class[] {
           Integer.class, String.class,  String.class, String.class};

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
    
    private int avisoSinCalculos;

    public void setAvisoSinCalculos(int avisoSinCalculos) {
        this.avisoSinCalculos = avisoSinCalculos;
    }
    
  

    @Override
 public boolean isCellEditable(int row, int column) {
    if (column != 0) 
     return true; 
    return false;
 }
    
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        Contacto contacto = lista.get(rowIndex);
        switch (columnIndex){
            case 0:
                return contacto.getId();
            case 1:
                 return contacto.getNombre();
            case 2:
                  return contacto.getTelefono();
            case 3:
                return contacto.getEmail();
          
           
           }
        
        return null;
    }

   
    public void actualizarListaContactos(List<Contacto> lista){
        this.lista = lista;
    
    }

 

}

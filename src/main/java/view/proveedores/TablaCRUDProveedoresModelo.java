
package view.proveedores;

import java.util.List;
import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;
import model.Proveedor;


public class TablaCRUDProveedoresModelo extends AbstractTableModel {

    private List<Proveedor> listaProveedor;

       
    protected String[] columnNames = new String[] {
            "id", "Nombre" , "NIF","Telefono 1", "Telefono 2", "Correo", "Ver ficha","Eliminar"  };

    protected Class[] columnClasses = new Class[] {
           Integer.class, String.class, String.class, Integer.class, Integer.class, String.class, JButton.class , JButton.class};

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
        return (listaProveedor != null) ? listaProveedor.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }
    
 
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        Proveedor proveedor = listaProveedor.get(rowIndex);
        switch (columnIndex){
            case 0:
                return proveedor.getId();
            case 1:
                 return proveedor.getNombre();
            case 2:
                return proveedor.getNif();
          
            case 3:
                  return proveedor.getTelefono1();
            case 4:
                  return proveedor.getTelefono2();
            case 5:
                  return proveedor.getCorreo();    
            case 6:
                  return new JButton("Ver ficha");
            case 7:
                  return new JButton("Eliminar");
     
     }
        return null;
    }
    

   
    public void actualizarListaProveedor(List<Proveedor> listaProveedor){
        this.listaProveedor = listaProveedor;
    
    }

}

    
    
    
    
    


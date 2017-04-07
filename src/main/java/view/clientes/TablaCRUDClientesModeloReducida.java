package view.clientes;


import javax.swing.table.AbstractTableModel;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.TableModel;
import model.Cliente;



public class TablaCRUDClientesModeloReducida extends AbstractTableModel  implements TableModel {

    private List<Cliente> listaClientes;

       
    protected String[] columnNames = new String[] {
            "id", "Nombre" ,"Apellidos", "NIF", "Ver ficha" };

    protected Class[] columnClasses = new Class[] {
           Integer.class, String.class, String.class, String.class, JButton.class, 
          
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
        return (listaClientes != null) ? listaClientes.size() : 0;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    


 
    @Override
    public Object getValueAt(final int rowIndex, int columnIndex) {
        Cliente cliente = listaClientes.get(rowIndex);
        switch (columnIndex){
            case 0:
                return cliente.getId();
            case 1:
                 return cliente.getNombre();
            case 2:
                  return cliente.getApellidos();
            case 3:
                return cliente.getNif();
            case 4: 
                  return new JButton("Ver ficha");
        }
        
        return null;
    }

   
    public void actualizarListaClientes(List<Cliente> listaCliente){
        this.listaClientes = listaCliente;
    
    }

 

}

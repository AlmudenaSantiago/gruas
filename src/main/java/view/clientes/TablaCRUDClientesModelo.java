package view.clientes;


import javax.swing.table.AbstractTableModel;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.TableModel;
import model.Cliente;
import process.cargador.CargadorTotales;
import view.pedidos.MainFrame;



public class TablaCRUDClientesModelo extends AbstractTableModel  implements TableModel {

    private List<Cliente> listaClientes;

       
    protected String[] columnNames = new String[] {
            "Id", "Nombre" ,"Apellidos", "NIF","Telefono 1", "Pendiente", "Abonado","Enviar correo","Ver ficha","Eliminar"};

    protected Class[] columnClasses = new Class[] {
           Integer.class, String.class,  String.class, String.class, String.class, Double.class,Double.class, JButton.class, JButton.class, JButton.class};

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
        return 10;
    }
    
    private int avisoSinCalculos;

    public void setAvisoSinCalculos(int avisoSinCalculos) {
        this.avisoSinCalculos = avisoSinCalculos;
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
                  return cliente.getTelefono1();
            case 5:
                if (MainFrame.getInstance().getClientesSinCalculos() == 1) {
                    return 0;
                 } else  {
                    return cliente.getCantidadPendiente();
                 }
             case 6:
                 if (MainFrame.getInstance().getClientesSinCalculos() == 1) {
                    return 0;
                 } else  {
                    return cliente.getCantidadAbonada();
                 }
            case 7:
                return new JButton("Enviar correo");
            case 8:
                return new JButton("Ver ficha");
            case 9:
                return new JButton("Eliminar");
           }
        
        return null;
    }

   
    public void actualizarListaClientes(List<Cliente> listaCliente){
        this.listaClientes = listaCliente;
    
    }

 

}

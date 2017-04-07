package view.clientes;


import command.clientes.CargarClientesCommand;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.Cliente;
import process.CRUDClientes.EliminarCliente;
import view.ModificarFrames.ModificarApellidosFrame;
import view.ModificarFrames.ModificarCorreoFrame;
import view.ModificarFrames.ModificarCpFrame;
import view.ModificarFrames.ModificarDomicilioFrame;
import view.ModificarFrames.ModificarNifFrame;
import view.ModificarFrames.ModificarNombreFrame;
import view.ModificarFrames.ModificarPoblacionFrame;
import view.ModificarFrames.ModificarProvinciaFrame;
import view.ModificarFrames.ModificarTelefono1Frame;
import view.ModificarFrames.ModificarTelefono2Frame;


import view.venta.VentaFrame;





public final class JTablaCRUDClientesReducida extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaCRUDClientesModeloReducida tablaCRUDModelo;
    private CargarClientesCommand cargarClientesCommand;
    private static JTablaCRUDClientesReducida instance;
    private  TableRowSorter sorter;
    
    private JTablaCRUDClientesReducida() {
       setTable();
       setListeners();
      this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
 //    setListenerModificaCelda();
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
      }
    


    public static JTablaCRUDClientesReducida getInstance() {
        if (instance == null) {
            instance = new JTablaCRUDClientesReducida();
        }
        return instance;
    }
    
   
    @Override
    public TableModel getModel() {
        return tablaCRUDModelo;
    
    }
    
    public void setTable() {
        tablaCRUDModelo = new TablaCRUDClientesModeloReducida();
        setModel(tablaCRUDModelo);
       }
    


     public void setListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            
                       if  (e.getClickCount() == 2) {
                            Integer id = (Integer) getValueAt(rowAtPoint(e.getPoint()), 0);
                                   
                            switch (columnAtPoint(e.getPoint())) {

                                case 0: 
                                      VentaFrame.getInstance().getPedido().setClienteId(id);
                                      CRUD_ClientesFrameReducida.getInstance().setVisible(false);
                                      break;
                                          
                         
                               case 4:
                                   FichaClienteFrame fcf = new FichaClienteFrame(id);
                                   fcf.setVisible(true);
                                   fcf.setLocationRelativeTo(VentaFrame.getInstance());
                            }
                        }
                   
                 }
            
            
        });
 
    }
    
    public void mostrarClientes(List<Cliente> listaCliente) {
         tablaCRUDModelo.actualizarListaClientes(listaCliente);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarClientesCommand(CargarClientesCommand cargarClientesCommand) {
        this.cargarClientesCommand = cargarClientesCommand;
    }

   
}

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
import process.cargador.CargadorListaCliente;
import process.parser.clientes.ClienteParserJson;
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
import view.pedidos.MainFrame;


import view.venta.VentaFrame;





public final class JTablaCRUDClientes extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaCRUDClientesModelo tablaCRUDModelo;
    private CargarClientesCommand cargarClientesCommand;
    private static JTablaCRUDClientes instance;
  
    
    private JTablaCRUDClientes() {
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
    


    public static JTablaCRUDClientes getInstance() {
        if (instance == null) {
            instance = new JTablaCRUDClientes();
        }
        return instance;
    }
    
   
    @Override
    public TableModel getModel() {
        return tablaCRUDModelo;
    
    }
    
    public void setTable() {
        tablaCRUDModelo = new TablaCRUDClientesModelo();
        setModel(tablaCRUDModelo);
       }
    
   
    

     public void setListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            
                       if  (e.getClickCount() == 2) {
                           try {
                               Integer id = (Integer) getValueAt(rowAtPoint(e.getPoint()), 0);
                               String nombre= (String) getValueAt(rowAtPoint(e.getPoint()), 1);
                               switch (columnAtPoint(e.getPoint())) {
                                   
                                   
                                   
                                   case 7:
                                       List<Cliente> clientes = new CargarClientesCommand(new CargadorListaCliente(), new ClienteParserJson()).executeCliente(id);
                                       Cliente clienteConsultado = clientes.get(0);
                                       try {
                                           Runtime.getRuntime( ).exec(
                                                   new String[]
                                                   { "cmd.exe",
                                                       "/c",
                                                       "start",
                                                       "\"title\"",
                                                       "\"mailto:"
                                                               +  clienteConsultado.getCorreo()
                                                               +  "?subject=Muebles Alca%20&body=le envio su factura%20%20&attachment=\'file:///myPdf.pdf\'\""
                                                   }
                                           );
                                       } catch (IOException ex) {
                                           Logger.getLogger(JTablaCRUDClientes.class.getName()).log(Level.SEVERE, null, ex);
                                       }
                                       break;
                                   case 8:
                                       
                                       FichaClienteFrame fcf = new FichaClienteFrame(id);
                                       fcf.setLocationRelativeTo(CRUD_ClientesFrame.getInstance());
                                       break;
                                       
                                       
                                   case 9:
                                       int confirmado = JOptionPane.showConfirmDialog(null,"¿Quieres realmente eliminar a este cliente?");
                                       
                                       if (JOptionPane.OK_OPTION == confirmado) {
                                           try {
                                               System.out.println("confirmado");
                                               EliminarCliente eliminaCliente = new EliminarCliente(id);
                                               try {
                                                   eliminaCliente.eliminar();
                                               } catch (Exception ex) {
                                                   Logger.getLogger(JTablaCRUDClientes.class.getName()).log(Level.SEVERE, null, ex);
                                               }
                                               cargarClientesCommand.execute();
                                           } catch (Exception ex) {
                                               Logger.getLogger(JTablaCRUDClientes.class.getName()).log(Level.SEVERE, null, ex);
                                           }
                                       }
                                       break;
                                       
                                       
                                       
                                       
                               } } catch (Exception ex) {
                               Logger.getLogger(JTablaCRUDClientes.class.getName()).log(Level.SEVERE, null, ex);
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

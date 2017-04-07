package view.contactos;



import command.contactos.CargarContactosCommand;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableModel;
import model.Contacto;
import process.CRUD.contactos.ModificarContacto;
import process.CRUDClientes.ModificarCliente;
import process.cargador.contactos.CargadorListaContacto;
import process.parser.contactos.ContactoParserJson;
import view.fichaCliente.FichaCliente;
import view.fichaCliente.FichaClienteModelo;
import view.fichaCliente.FrameContactos;
import view.fichaCliente.SingletonCliente;
import view.tarifas.simples.EditorCeldaTabla;







public final class JTablaCRUDContactos extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaCRUDContactosModelo tablaCRUDModelo;
    private CargarContactosCommand cargarContactosCommand;
    private static JTablaCRUDContactos instance;
  
    
    private JTablaCRUDContactos() {
       setTable();
       getColumnModel().getColumn(0).setPreferredWidth(5);
       getColumnModel().getColumn(1).setPreferredWidth(150);
        getColumnModel().getColumn(2).setPreferredWidth(150);
       getColumnModel().getColumn(3).setPreferredWidth(150);
    
       getColumnModel().getColumn(1).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(2).setCellEditor(new EditorCeldaTabla());
       getColumnModel().getColumn(3).setCellEditor(new EditorCeldaTabla());
      
       setEditors();
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
    

     public void setEditors() {
      
           getColumnModel().getColumn(3).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";

            @Override
            public void editingStopped(ChangeEvent e) {

                try {
                    valor = (String) getColumnModel().getColumn(3).getCellEditor().getCellEditorValue();
                    ModificarContacto modificar = null;
                    modificar = new ModificarContacto((Integer) getValueAt(getSelectedRow(),0), "email", valor);
                    modificar.modificarContacto();
                    actualizarTabla();
                } catch (IOException ex) {
                    Logger.getLogger(JTablaCRUDContactos.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
              @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });
          
           
            getColumnModel().getColumn(2).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";

            @Override
            public void editingStopped(ChangeEvent e) {

                try {
                    valor = (String) getColumnModel().getColumn(2).getCellEditor().getCellEditorValue();
                    ModificarContacto modificar = null;
                    modificar = new ModificarContacto((Integer) getValueAt(getSelectedRow(),0), "telefono", valor);
                    modificar.modificarContacto();
                    actualizarTabla();
                } catch (IOException ex) {
                    Logger.getLogger(JTablaCRUDContactos.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
              @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });
           
            
             getColumnModel().getColumn(1).getCellEditor().addCellEditorListener(new CellEditorListener() {

            String valor = "";

            @Override
            public void editingStopped(ChangeEvent e) {

                try {
                    valor = (String) getColumnModel().getColumn(1).getCellEditor().getCellEditorValue();
                    ModificarContacto modificar = null;
                    modificar = new ModificarContacto((Integer) getValueAt(getSelectedRow(),0), "nombre", valor);
                    modificar.modificarContacto();
                    actualizarTabla();
                } catch (IOException ex) {
                    Logger.getLogger(JTablaCRUDContactos.class.getName()).log(Level.SEVERE, null, ex);
                }
             }
              @Override
            public void editingCanceled(ChangeEvent e) {

            }
        });
           
     }

     
     public void actualizarTabla() {
        try {
            CargarContactosCommand cargarContactosCommand = new CargarContactosCommand(CargadorListaContacto.getInstance(), new ContactoParserJson());
            mostrarContactos(cargarContactosCommand.executeListaPorCliente(SingletonCliente.getInstance().getCliente().getId()));
        
        } catch (Exception ex) {
            Logger.getLogger(JTablaCRUDContactos.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
   
    public static JTablaCRUDContactos getInstance() {
        if (instance == null) {
            instance = new JTablaCRUDContactos();
        }
        return instance;
    }
    
   
    @Override
    public TableModel getModel() {
        return tablaCRUDModelo;
    
    }
    
    public void setTable() {
        tablaCRUDModelo = new TablaCRUDContactosModelo();
        setModel(tablaCRUDModelo);
       }
    
   
    
    public void mostrarContactos(List<Contacto> listaContacto) {

         tablaCRUDModelo.actualizarListaContactos(listaContacto);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarContactosCommand(CargarContactosCommand cargarContactosCommand) {
        this.cargarContactosCommand = cargarContactosCommand;
    }

   
}

package view.proveedores;


import command.proveedores.CargarProveedoresCommand;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableRowSorter;


import model.Proveedor;
import process.CRUDProveedores.EliminarProveedor;
import view.ModificarFrames.ModificarCorreoFrame;
import view.ModificarFrames.ModificarCpFrame;
import view.ModificarFrames.ModificarDomicilioFrame;
import view.ModificarFrames.ModificarPoblacionFrame;
import view.ModificarFrames.ModificarProvinciaFrame;
import view.ModificarFrames.ModificarTelefono1Frame;
import view.ModificarFrames.ModificarTelefono2Frame;
import view.productos.InsertarProductoFrame;
import view.ModificarFrames.ModificarNifFrame;
import view.ModificarFrames.ModificarNombreFrame;
import view.ModificarFrames.ModificarPaisFrame;
import view.pedidos.MainFrame;





public final class JTablaCRUDProveedores extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaCRUDProveedoresModelo tablaCRUDModelo;
    private CargarProveedoresCommand cargarProveedoresCommand;
    private static JTablaCRUDProveedores instance;
   
    
    private JTablaCRUDProveedores() {
       setTable();
        this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
       setRowSorter();
       setListeners();

       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
      }
    


    public static JTablaCRUDProveedores getInstance() {
        if (instance == null) {
            instance = new JTablaCRUDProveedores();
        }
        return instance;
    }
    
   
    public void setTable() {
        tablaCRUDModelo = new TablaCRUDProveedoresModelo();
        setModel(tablaCRUDModelo);
       }
    
    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
    */
    

     public void setRowSorter () {
     	TableRowSorter sorter = new TableRowSorter(tablaCRUDModelo);
        this.setRowSorter(sorter);
        tablaCRUDModelo.fireTableDataChanged();
     }
    
     
    /* private void setListenerModificaCelda() {
        this.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent evento) {
                try {
                   if (evento.getType() == TableModelEvent.UPDATE) {
                      System.out.println("entra");
                    
                    int idFila = (Integer) getValueAt(evento.getFirstRow(), evento.getColumn());
                    setValueAt("500", evento.getFirstRow(), evento.getColumn());
                    System.out.println("llega");
                    ModificarProducto mp = new ModificarProducto(idFila,"precio");
                    mp.modificar();
                    CargarProductosCommand cargarProductosCommand = new CargarProductosCommand (CargadorListaProducto.getInstance(), new ProductoParserJson());
                    setCargarProductosCommand(cargarProductosCommand);
                    cargarProductosCommand.execute();
                   }
                } catch (Exception ex) {
                    Logger.getLogger(JTablaCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }*/

     
     
     
     public void setListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            
               if    (e.getClickCount() == 2) {
                            Integer id = (Integer) getValueAt(rowAtPoint(e.getPoint()), 0);


                            switch (columnAtPoint(e.getPoint())) {

                                case 0: 
                                    InsertarProductoFrame  instance = InsertarProductoFrame.getInstance();  
                                 
                                    instance.getProducto().setProveedor((String) getValueAt(rowAtPoint(e.getPoint()), 1));
                                    instance.getProducto().setIdProveedor(((Integer) getValueAt(rowAtPoint(e.getPoint()), 0)));
                                    CRUD_ProveedoresFrame.getInstance().setVisible(false);
                                    break;
                
                                case 6:
                                       FichaProveedorFrame fpf = new FichaProveedorFrame(id);
                                    
                                       fpf.setLocationRelativeTo(MainFrame.getInstance());
                                       break;


                                 case 7:
                                      int confirmado = JOptionPane.showConfirmDialog(null,"¿Quieres realmente eliminar a este proveedor?");

                                        if (JOptionPane.OK_OPTION == confirmado) {

                                          EliminarProveedor eliminaProveedor = new EliminarProveedor(id);
                                            try {
                                                eliminaProveedor.eliminar();
                                            } catch (Exception ex) {
                                                Logger.getLogger(JTablaCRUDProveedores.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            try {
                                                cargarProveedoresCommand.execute();
                                            } catch (IOException ex) {
                                                Logger.getLogger(JTablaCRUDProveedores.class.getName()).log(Level.SEVERE, null, ex);
                                            }

                                      }
                                       break;
                                 
                                   
                            }

                    }
            } 
           
        });
 
    }
     
     
     
     

    
    public void mostrarProveedores(List<Proveedor> listaProveedor) {
         tablaCRUDModelo.actualizarListaProveedor(listaProveedor);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProveedoresCommand(CargarProveedoresCommand cargarProveedoresCommand) {
        this.cargarProveedoresCommand = cargarProveedoresCommand;
    }

    
   
}

package view.productos;


import view.ModificarFrames.ModificarDescripcionFrame;
import view.ModificarFrames.ModificarNombreFrame;
import view.ModificarFrames.ModificarPrecioFrame;
import command.productos.CargarDiasFestivosCommand;
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

import model.Producto;
import process.CRUDProductos.EliminarProducto;
import process.cargador.CargadorDeRutaDeImagenes;
import process.cargador.CargadorListaProducto;
import process.parser.productos.ProductoParserJson;
import view.ModificarFrames.ModificarAcabadoFrame;
import view.ModificarFrames.ModificarStockFrame;
import view.venta.JTablaVenta;




public final class JTablaCRUD extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private TablaCRUDProductosModelo tablaCRUDModelo;
    private CargarDiasFestivosCommand cargarProductosCommand;
    private static JTablaCRUD instance;
    private TableRowSorter sorter;
    private ImageFrame image;
    
    private JTablaCRUD() {
       setTable();
       setRowSorter();
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
    


    public static JTablaCRUD getInstance() {
        if (instance == null) {
            instance = new JTablaCRUD();
        }
        return instance;
    }
    
   
    public void setTable() {
        tablaCRUDModelo = new TablaCRUDProductosModelo();
        setModel(tablaCRUDModelo);
      }
    
    /*@Override
    public boolean isCellEditable(int row, int col) { 
        return col > 1 && col < 5 ; 
    }
    */

    public TableRowSorter getSorter() {
        return sorter;
    }
    
    
    

     public void setRowSorter () {
     	  sorter = new TableRowSorter(tablaCRUDModelo);
          this.setRowSorter(sorter);
         //sorter.setRowFilter (RowFilter.regexFilter(".",0)); 
      
     }
    
   


    
    public void mostrarProductos(List<Producto> listaProducto) {
         tablaCRUDModelo.actualizarListaProducto(listaProducto);
         tablaCRUDModelo.fireTableDataChanged();
    }

    public void setCargarProductosCommand(CargarDiasFestivosCommand cargarProductosCommand) {
        this.cargarProductosCommand = cargarProductosCommand;
    }

    
    public void setListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            
             
              if    (e.getClickCount() == 2) {    
                
                Integer idFila = (Integer) getValueAt(rowAtPoint(e.getPoint()), 1);
                String nombreProducto = (String) getValueAt(rowAtPoint(e.getPoint()), 2);  
                String descripcionProducto = (String) getValueAt(rowAtPoint(e.getPoint()), 8);
                String colorProducto = (String) getValueAt(rowAtPoint(e.getPoint()), 7);
                
               
                Double precioCompra = (Double) getValueAt(rowAtPoint(e.getPoint()), 9); 
                
                  if (columnAtPoint(e.getPoint()) == 2) {
                    try {
                        ModificarNombreFrame mnf = new ModificarNombreFrame(idFila, "producto",null);
                        mnf.setVisible(true);
                        mnf.setLocationRelativeTo(CRUD_ProductosFrame.getInstance());
                      
                    } catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog (null,"No se ha podido modificar el nombre: " + ex);
                    }
                
                  }
                  
                  if (columnAtPoint(e.getPoint()) == 7) {
              
                    try {
                        ModificarAcabadoFrame mnf = new ModificarAcabadoFrame(idFila);
                        mnf.setVisible(true);
                        mnf.setLocationRelativeTo(CRUD_ProductosFrame.getInstance());
                      
                    } catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog (null,"No se ha podido modificar el color: " + ex);
                    }
                
                  }
                   
                if (columnAtPoint(e.getPoint()) == 8) {
                    try {
                        ModificarDescripcionFrame mdf = new ModificarDescripcionFrame(idFila);
                        mdf.setVisible(true);
                        mdf.setLocationRelativeTo(CRUD_ProductosFrame.getInstance());
                    } catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog (null,"No se ha podido modificar la descripcion: " + ex);
                    }
                }
                
                   
                if (columnAtPoint(e.getPoint()) == 9) {
                    try {
                        ModificarPrecioFrame mpf = new ModificarPrecioFrame(idFila,"compra");
                        mpf.setVisible(true);
                        mpf.setLocationRelativeTo(CRUD_ProductosFrame.getInstance());
                      
                    } catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog (null,"No se ha podido modificar el precio: " + ex);
                    }
                }
                
                
                if (columnAtPoint(e.getPoint()) == 10) {
                    try {
                        ModificarPrecioFrame mpf = new ModificarPrecioFrame(idFila,"venta");
                        mpf.setVisible(true);
                        mpf.setLocationRelativeTo(CRUD_ProductosFrame.getInstance());
                      
                    } catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog (null,"No se ha podido modificar el precio: " + ex);
                    }
                }
                
                if (columnAtPoint(e.getPoint()) == 11) {
                    
                    try {
                        image = new ImageFrame(nombreProducto,descripcionProducto,colorProducto, new CargadorDeRutaDeImagenes().cargar(idFila) );
                    } catch (IOException ex) {
                        Logger.getLogger(JTablaCRUD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                  
                    
                    try {
                        image.verPrimeraImagen();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JTablaCRUD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
               
                
                
                if (columnAtPoint(e.getPoint()) == 12) {
                   
                      int confirmado = JOptionPane.showConfirmDialog(null,"¿Quieres realmente eliminar a este producto?");

                      if (JOptionPane.OK_OPTION == confirmado) {
                    
                            try {
                                new EliminarProducto(idFila).eliminar();
                                CargarDiasFestivosCommand cargarProductosCommand = new CargarDiasFestivosCommand (CargadorListaProducto.getInstance(), new ProductoParserJson());
                                setCargarProductosCommand(cargarProductosCommand);
                                cargarProductosCommand.execute();

                            } catch (Exception ex) {
                                System.out.println(ex);
                                JOptionPane.showMessageDialog (null,"No se ha podido eliminar el producto: " + ex);
                            }
                        }
                }
                
                if (columnAtPoint(e.getPoint()) == 13) {
                    try {
                        ModificarStockFrame mstockf = new ModificarStockFrame(idFila, nombreProducto, precioCompra);
                        mstockf.setVisible(true);
                        mstockf.setLocationRelativeTo(CRUD_ProductosFrame.getInstance());
                      
                    } catch (Exception ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog (null,"No se ha podido modificar el precio: " + ex);
                    }
                
                }
            }
                
                
            }         
    
        });
    
   
    }
}

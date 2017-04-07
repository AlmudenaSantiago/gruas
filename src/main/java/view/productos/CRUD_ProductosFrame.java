/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.productos;

import com.itextpdf.text.DocumentException;
import command.productos.CargarDiasFestivosCommand;

import java.awt.BorderLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import process.parser.productos.ProductoParserJson;
import process.PDFs.GenerarInventario;
import process.PDFs.GenerarInventarioProveedores;
import view.SelectorDeRuta;
import view.categorias.CategoriasSelectorFrame;
import view.venta.VentaFrame;


/**
 *
 * @author Aarón
 */
public class CRUD_ProductosFrame extends javax.swing.JFrame  implements ActionListener,ItemListener {


    private JScrollPane jScrollPane1;
    private CargarDiasFestivosCommand cargarProductosCommand;
    private JButton botonInsertar = new JButton("INSERTAR NUEVO");
    private JButton botonNuevaCategoria = new JButton("NUEVA CATEGORIA");
    private static CRUD_ProductosFrame instance;
    private Timer timer;
    private JButton jBotonFiltrar;
    private JButton jBotonInventario;
    private JButton jBotonInventarioProveedores;
    private JCheckBox checkBoxBuscarPorNombre = new JCheckBox("Buscar por nombre");
    private JCheckBox checkBoxBuscarPorAcabado = new JCheckBox("Buscar por acabado");
    private JCheckBox checkBoxBuscarPorDescrpicion = new JCheckBox("Buscar por descripción");     
    private TableRowSorter<TableModel> rowSorter;
    private JTextField jTextFieldFilter;
 
     private       JMenuBar menuBar;
     private       JMenu menu, submenu;
    private        JMenuItem menuItemInsertarProducto;
    private        JMenuItem menuItemInsertarCategoria;
    private        JMenuItem menuItemGenerarInventarioPorProveedor;
    private        JMenuItem menuItemGenerarInventarioPorCategorias;

    private ProductoParserJson productoParserJson;
    TablaCRUDProductosModelo modelo = new TablaCRUDProductosModelo();
    
    private CRUD_ProductosFrame() {
       initComponents();
       rowSorter = new TableRowSorter(JTablaCRUD.getInstance().getModel());
       JTablaCRUD.getInstance().setRowSorter(rowSorter);
       
       setExtendedState(MAXIMIZED_BOTH);
       setVisible(true);
     
       setListenerFilter();
       setListenerCheckNombre();
       setListenerCheckDescripcion();
       setListenerCheckAcabado();

   }


    public static CRUD_ProductosFrame getInstance() { 
        if (instance == null) {
            instance = new CRUD_ProductosFrame();
        }
        return instance;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        
           //Create the menu bar.
            menuBar = new JMenuBar();

            //Build the first menu.
            menu = new JMenu("Menú Productos");
            menu.setMnemonic(KeyEvent.VK_A);
            menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
            menuBar.add(menu);
         
            //a group of JMenuItems
            menuItemInsertarProducto = new JMenuItem("Insertar Producto", KeyEvent.VK_T);
           // menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menuItemInsertarProducto.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
            menu.add(menuItemInsertarProducto);
            menuItemInsertarProducto.addActionListener(this);


            //Build second menu in the menu bar.
            menu = new JMenu("Menu categorias");
            menu.setMnemonic(KeyEvent.VK_N);
            menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
            menuBar.add(menu);
              //a group of JMenuItems
            menuItemInsertarCategoria = new JMenuItem("Insertar Categoria", KeyEvent.VK_T);
          //  menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menuItemInsertarCategoria.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
            menu.add(menuItemInsertarCategoria);
             menuItemInsertarCategoria.addActionListener(this);


            
            menu = new JMenu("Menu inventarios");
            menu.setMnemonic(KeyEvent.VK_N);
            menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
            
            
            menuBar.add(menu);
   
            //a submenu
            menu.addSeparator();
            submenu = new JMenu("Generar Inventarios");
            submenu.setMnemonic(KeyEvent.VK_S);
            menuItemGenerarInventarioPorCategorias = new JMenuItem("Por categorias");
            submenu.add(menuItemGenerarInventarioPorCategorias);
            menuItemGenerarInventarioPorCategorias.addActionListener(this);

            menuItemGenerarInventarioPorProveedor = new JMenuItem("Por proveedores");
            submenu.add(menuItemGenerarInventarioPorProveedor);
            menuItemGenerarInventarioPorProveedor.addActionListener(this);

                menu.add(submenu);


             setJMenuBar(menuBar);

        
        
        
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("GESTIÓN DE PRODUCTOS");
         jTextFieldFilter= new JTextField(20);
     
        
        JPanel jPanelBoton = new JPanel();
       

       // PRIMER PANEL  
        JPanel jPaneFiltro = new JPanel();
        jPaneFiltro.add(new JLabel("Palabra a buscar"));
        jPaneFiltro.add(jTextFieldFilter);
        jPaneFiltro.add(checkBoxBuscarPorNombre);
        jPaneFiltro.add(checkBoxBuscarPorDescrpicion);
        jPaneFiltro.add(checkBoxBuscarPorAcabado);  
        
        jPanelBoton.add(jPaneFiltro);
      
        
        add(jPanelBoton, BorderLayout.NORTH);
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setViewportView(JTablaCRUD.getInstance());
        add(jScrollPane1, BorderLayout.CENTER);
        pack();
    }             
    
    
    
    
      public void setListenerCheckNombre() {
    
             checkBoxBuscarPorNombre.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                                checkBoxBuscarPorDescrpicion.setSelected(false);
                                checkBoxBuscarPorAcabado.setSelected(false);    
                            
                        }
                    });
        }  
     
       public void setListenerCheckDescripcion() {
    
       checkBoxBuscarPorDescrpicion.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                                checkBoxBuscarPorNombre.setSelected(false);
                                checkBoxBuscarPorAcabado.setSelected(false);    
                            
                        }
                    });
        }  
       
       
      public void setListenerCheckAcabado() {
    
       checkBoxBuscarPorAcabado.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                                checkBoxBuscarPorNombre.setSelected(false);
                                checkBoxBuscarPorDescrpicion.setSelected(false);    
                        }
                    });
        }  
    
    
    @Override
      public void actionPerformed(ActionEvent e) {
        if (e.getSource()==menuItemInsertarProducto) {
             InsertarProductoFrame.getInstance().setVisible(true);
             InsertarProductoFrame.getInstance().setLocationRelativeTo(instance);
        }
        if (e.getSource()== menuItemInsertarCategoria) {
                 CategoriasSelectorFrame a = new CategoriasSelectorFrame();
        }
        if (e.getSource()==menuItemGenerarInventarioPorCategorias) {
             
                                              GenerarInventario generarInventario = new GenerarInventario(new SelectorDeRuta().seleccionDeRuta()); 
                                              try {
                                                  generarInventario.createPdf();
                                              } catch (DocumentException ex) {
                                                    JOptionPane.showMessageDialog (null, "Error: "  + ex  );
                                                  Logger.getLogger(VentaFrame.class.getName()).log(Level.SEVERE, null, ex);
                                              } catch (IOException ex) {
                                                   JOptionPane.showMessageDialog (null, "Error:"  + ex  );
                                                  Logger.getLogger(VentaFrame.class.getName()).log(Level.SEVERE, null, ex);
                                              }
        }
        if (e.getSource()==menuItemGenerarInventarioPorProveedor) {
                  GenerarInventarioProveedores generarInventario = new GenerarInventarioProveedores(new SelectorDeRuta().seleccionDeRuta()); 
                                              try {
                                                  generarInventario.createPdf();
                                              } catch (DocumentException ex) {
                                                    JOptionPane.showMessageDialog (null, "Error: "  + ex  );
                                                  Logger.getLogger(VentaFrame.class.getName()).log(Level.SEVERE, null, ex);
                                              } catch (IOException ex) {
                                                   JOptionPane.showMessageDialog (null, "Error:"  + ex  );
                                                  Logger.getLogger(VentaFrame.class.getName()).log(Level.SEVERE, null, ex);
                                              }
        }
    }
    
    
     public void setListenerFilter() {
  
              jTextFieldFilter.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(final KeyEvent e) {
                          System.out.println("texo a filtrar" + jTextFieldFilter.getText());
                          System.out.println("columna por la que filtrar" +  columnaPorLaQueFiltrar());
                            repaint();
                            rowSorter.setRowFilter(RowFilter.regexFilter((jTextFieldFilter.getText()), columnaPorLaQueFiltrar()));

                        }
                    });
        }  

   
    
    
    public int columnaPorLaQueFiltrar () {
            if (checkBoxBuscarPorNombre.isSelected()) { return 2; }
            if (checkBoxBuscarPorDescrpicion.isSelected()) { return 8; }
            if (checkBoxBuscarPorAcabado.isSelected()) { return 7; }
            return 2;
   }
    
  
      
    public void setListenerBotonInsertar() {
           botonInsertar.addMouseListener(
                                     new MouseAdapter() { 
                                          @Override
                                          public void mouseClicked(MouseEvent e) {
                                              InsertarProductoFrame.getInstance().setVisible(true);
                                              InsertarProductoFrame.getInstance().setLocationRelativeTo(instance);
                                         
                                          }
                                     });
    }
    
    public void setListenerBotonInventario() {
          jBotonInventario.addMouseListener(new MouseAdapter() { 
                                          @Override
                                          public void mouseClicked(MouseEvent e) {
                                              
                                              GenerarInventario generarInventario = new GenerarInventario(new SelectorDeRuta().seleccionDeRuta()); 
                                              try {
                                                  generarInventario.createPdf();
                                              } catch (DocumentException ex) {
                                                    JOptionPane.showMessageDialog (null, "Error: "  + ex  );
                                                  Logger.getLogger(VentaFrame.class.getName()).log(Level.SEVERE, null, ex);
                                              } catch (IOException ex) {
                                                   JOptionPane.showMessageDialog (null, "Error:"  + ex  );
                                                  Logger.getLogger(VentaFrame.class.getName()).log(Level.SEVERE, null, ex);
                                              }
                                              
                                              
                                          }
                                     });
    }
   
    
      public void setListenerBotonInventarioProveedores() {
          jBotonInventarioProveedores.addMouseListener(new MouseAdapter() { 
                                          @Override
                                          public void mouseClicked(MouseEvent e) {
                                              
                                              GenerarInventarioProveedores generarInventario = new GenerarInventarioProveedores(new SelectorDeRuta().seleccionDeRuta()); 
                                              try {
                                                  generarInventario.createPdf();
                                              } catch (DocumentException ex) {
                                                    JOptionPane.showMessageDialog (null, "Error: "  + ex  );
                                                  Logger.getLogger(VentaFrame.class.getName()).log(Level.SEVERE, null, ex);
                                              } catch (IOException ex) {
                                                   JOptionPane.showMessageDialog (null, "Error:"  + ex  );
                                                  Logger.getLogger(VentaFrame.class.getName()).log(Level.SEVERE, null, ex);
                                              }
                                              
                                              
                                          }
                                     });
    }
    
    
    
    

    
    public void setListenerBotonNuevaCategoria() {
           botonNuevaCategoria.addMouseListener(
                                     new MouseAdapter() { 
                                          @Override
                                          public void mouseClicked(MouseEvent e) {
                                              CategoriasSelectorFrame a = new CategoriasSelectorFrame();
 
                                       
                                         
                                          }
                                     });
    }
    
    
    
    public void setCargarProductosCommand(CargarDiasFestivosCommand cargarProductosCommand) {
        this.cargarProductosCommand = cargarProductosCommand;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    


   
     
 }



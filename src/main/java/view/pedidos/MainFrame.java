
package view.pedidos;


import com.itextpdf.text.DocumentException;
import command.gastos.CargarGastosCommand;
import command.pedidos.CargarPedidosCommand;

import command.pedidos.CargarPlatosServidosCommand;
import command.gruas.CargarGruasCommand;
import command.productos.CargarDiasFestivosCommand;
import command.productos.CargarProductosPedidoCommand;
import command.proveedores.CargarProveedoresCommand;
import command.pedidos.CargarVentaCommand;
import command.clientes.CargarClientesCommand;
import datechooser.beans.DateChooserCombo;
import java.awt.BorderLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import process.GeneradorCodigosBarra.GeneradorCodigoBarras;
import process.cargador.CargadorListaCliente;

import process.cargador.CargadorListaGasto;
import process.cargador.CargadorListaPedido;
import process.cargador.CargadorListaPlatosServidos;
import process.cargador.CargadorListaProducto;
import process.cargador.CargadorListaProveedor;
import process.cargador.CargadorListaVendedor;

import process.cargador.CargadorTotales;
import process.parser.clientes.ClienteParserJson;
import process.parser.gastos.GastoParserJson;
import process.parser.pedidos.PedidoParserJson;

import process.parser.productos.ProductoParserJson;
import process.parser.proveedor.ProveedorParserJson;
import process.parser.gruas.GruasParserJson;
import process.PDFs.GenerarInventario;
import process.PDFs.GenerarInventarioProveedores;
import view.gastos.GastosFrame;
import view.gastos.JTablaGastos;
import view.Ranking.JTablaRanking;
import view.venta.JTablaVenta;
import view.Ranking.RankingFrame;
import view.SelectorDeRuta;
import view.categorias.CategoriasSelectorFrame;
import view.clientes.CRUD_ClientesFrame;
import view.clientes.InsertarClienteFrame;
import view.clientes.JTablaCRUDClientes;
import view.gastos.InsertarGastoFrame;
import view.productos.CRUD_ProductosFrame;
import view.productos.InsertarProductoFrame;
import view.productos.JTablaCRUD;
import view.proveedores.CRUD_ProveedoresFrame;
import view.proveedores.InsertarProveedorFrame;
import view.proveedores.JTablaCRUDProveedores;
import view.vendedores.CRUD_VendedoresFrame;
import view.vendedores.DatosParaConsultaComisionesFrame;
import view.vendedores.InsertarVendedorFrame;
import view.vendedores.JTablaVendedores;
import view.venta.VentaFrame;


public final class MainFrame extends javax.swing.JFrame implements ActionListener,ItemListener {

    private JScrollPane jScrollPane1;
    private CargarPedidosCommand cargarPedidosCommand;
    private CargarClientesCommand cargarClientesCommand;
    private CargarProductosPedidoCommand cargarProductosPedidoCommand;
    private static MainFrame instance;
    private JCheckBox filtroEstado;
    private Timer timer;
    private Timer timerFinalizados;
    private DateChooserCombo dateChooserComboDesde;
    private DateChooserCombo dateChooserComboHasta;
    private Calendar dateDesde;
    private Calendar dateHasta;
    private JButton botonConsultaRango;
    private int clientesSinCalculos;
    
    
    public static TextField palabraClave;
    public static JPasswordField password;
    private  ImageIcon icon = new ImageIcon("logo_icon.png");
    private  JLabel label1; 
    private       JMenuBar menuBar;
    private       JMenu menu, submenu;
    private        JMenuItem menuItemInsertarProducto;
    private        JMenuItem menuItemVerProductos;
    private        JMenuItem menuItemInsertarGasto;
    private        JMenuItem menuItemVerGastos;
    private        JMenuItem menuItemInsertarCliente;
    private        JMenuItem menuItemVerClientes;
    private        JMenuItem menuItemVerClientesSinCalculos;
   
    private        JMenuItem menuItemInsertarProveedor;
    private        JMenuItem menuItemVerProveedores;
    private        JMenuItem menuItemInsertarVendedor;
    private        JMenuItem menuItemVerVendedores;
    private        JMenuItem menuItemVerComisiones;
    
    private        JMenuItem menuItemVerCajaDia;
  
    private        JMenuItem menuItemRealizarPedido;
    private        JMenuItem menuItemVerRankingVentas;
  
    private        JMenuItem menuItemInsertarCategoria;
    private        JMenuItem menuItemGenerarInventarioPorProveedor;
    private        JMenuItem menuItemGenerarInventarioPorCategorias;
    
    public JCheckBox getFiltroEstado() {
        return filtroEstado;
    }
    
 

    private PedidoParserJson pedidoParserJson;
    TablaPedidosModelo modelo = new TablaPedidosModelo();
    private CargarVentaCommand cargarStockCommand;
    
    
    public MainFrame()  {
        setVisible(true);
        initComponents();
         // ICONO DEL FRAME
          setExtendedState(MAXIMIZED_BOTH);
        this.setIconImage(icon.getImage());
        setListenerFiltro();
        setListenerBotonConsultaRango();
     
        cargarPedidosCommand = new CargarPedidosCommand(CargadorListaPedido.getInstance(), new PedidoParserJson() );
        
        setCargarPedidosCommand(cargarPedidosCommand);
        setCargarStockCommand(cargarStockCommand);
        sincronizacion();
  
   }


    public static MainFrame getInstance() { 
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }
 
    
    public void sincronizacion() {
       timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
              cargarPedidosCommand.execute();
            }
        };
        timer.schedule(task, 0, 15000);
    }
      
     public void stop() {
        timer.cancel();
     }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
           setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
           setTitle("MENU PRINCIPAL");
        
            menuBar = new JMenuBar();

            menu = new JMenu("Facturación");
          
            menu.setMnemonic(KeyEvent.VK_N);
            menuItemRealizarPedido = new JMenuItem("Realizar pedido", KeyEvent.VK_T);
            menu.add(menuItemRealizarPedido);
            menuItemRealizarPedido.addActionListener(this);
            menuItemVerRankingVentas = new JMenuItem("Ver Ranking ventas", KeyEvent.VK_T);
            menu.add( menuItemVerRankingVentas);
            menuItemVerRankingVentas.addActionListener(this);
            menuBar.add(menu);
            menuItemVerCajaDia = new JMenuItem("Ver Caja del dia", KeyEvent.VK_T);
            menu.add( menuItemVerCajaDia);
            menuItemVerCajaDia.addActionListener(this);
            menuBar.add(menu);
            
      
            
            menu = new JMenu("Productos");
          
            menu.setMnemonic(KeyEvent.VK_A);
            menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
            menuBar.add(menu);
         
            //a group of JMenuItems
            menuItemInsertarProducto = new JMenuItem("Insertar Producto", KeyEvent.VK_T);
           // menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menuItemInsertarProducto.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
            menu.add(menuItemInsertarProducto);
            menuItemInsertarProducto.addActionListener(this);
            menuItemVerProductos = new JMenuItem("Ver Productos", KeyEvent.VK_T);
           // menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menu.add(menuItemVerProductos);
            menuItemVerProductos.addActionListener(this);


            //Build second menu in the menu bar.
            menu = new JMenu("Categorias");
           
            menu.setMnemonic(KeyEvent.VK_N);
            menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
            menuBar.add(menu);
              //a group of JMenuItems
            menuItemInsertarCategoria = new JMenuItem("Insertar Categoria", KeyEvent.VK_T);
            menuItemInsertarCategoria.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
            menu.add(menuItemInsertarCategoria);
            menuItemInsertarCategoria.addActionListener(this);


            
            menu = new JMenu("Inventarios");
          
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

            menu = new JMenu("Gastos");
         
            menu.setMnemonic(KeyEvent.VK_N);
            menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
                //a group of JMenuItems
            menuItemInsertarGasto = new JMenuItem("Insertar Gasto", KeyEvent.VK_T);
           // menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menuItemInsertarGasto.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
            menu.add(menuItemInsertarGasto);
            menuItemInsertarGasto.addActionListener(this);
            menuItemVerGastos = new JMenuItem("Ver Gastos", KeyEvent.VK_T);
           // menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menu.add(menuItemVerGastos);
            menuItemVerGastos.addActionListener(this);
            
            menuBar.add(menu);
            
            
             menu = new JMenu("Clientes");

            menu.setMnemonic(KeyEvent.VK_N);
            menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
                //a group of JMenuItems
            menuItemInsertarCliente = new JMenuItem("Insertar Cliente", KeyEvent.VK_T);
           // menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menuItemInsertarCliente.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
            menu.add(menuItemInsertarCliente);
            menuItemInsertarCliente.addActionListener(this);
            menuItemVerClientes = new JMenuItem("Ver Clientes", KeyEvent.VK_T);
           // menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menu.add(menuItemVerClientes);
            menuItemVerClientes.addActionListener(this);
            
           // menuItemVerClientesSinCalculos = new JMenuItem("Ver Clientes (sin cálculos))", KeyEvent.VK_T);
           // menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
           //  menu.add( menuItemVerClientesSinCalculos);
           //  menuItemVerClientesSinCalculos.addActionListener(this);
            
            menuBar.add(menu);
            
            
                     
            menu = new JMenu("Proveedores");
           
            menu.setMnemonic(KeyEvent.VK_N);
            menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
                //a group of JMenuItems
            menuItemInsertarProveedor = new JMenuItem("Insertar Proveedor", KeyEvent.VK_T);
           // menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menuItemInsertarProveedor.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
            menu.add(menuItemInsertarProveedor);
            menuItemInsertarProveedor.addActionListener(this);
            menuItemVerProveedores = new JMenuItem("Ver Proveedores", KeyEvent.VK_T);
           // menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menu.add(menuItemVerProveedores);
            menuItemVerProveedores.addActionListener(this);
            
            menuBar.add(menu);
            
             menu = new JMenu("Vendedores");
            menu.setMnemonic(KeyEvent.VK_N);
            menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
                //a group of JMenuItems
            menuItemInsertarVendedor = new JMenuItem("Insertar Vendedor", KeyEvent.VK_T);
           // menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menuItemInsertarVendedor.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
            menu.add(menuItemInsertarVendedor);
            menuItemInsertarVendedor.addActionListener(this);
            menuItemVerVendedores = new JMenuItem("Ver Vendedores", KeyEvent.VK_T);
           // menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menu.add(menuItemVerVendedores);
            menuItemVerVendedores.addActionListener(this);
            menuItemVerComisiones = new JMenuItem("Ver Comisiones", KeyEvent.VK_T);
           // menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
            menu.add(menuItemVerComisiones);
            menuItemVerComisiones.addActionListener(this);
            
            menuBar.add(menu);
            
            
            
             setJMenuBar(menuBar);

        
        
        
       // PRIMER PANEL  
        JPanel jPanelFechas = new JPanel();
        JPanel jPanelBotones = new JPanel();
        JLabel etiquetaDesde = new JLabel("Desde");
        JLabel etiquetaHasta = new JLabel("Hasta");
        
        
        jPanelFechas.add(etiquetaDesde);
        
        
        botonConsultaRango = new JButton("Consultar Rango");
        dateChooserComboDesde = new DateChooserCombo();
        jPanelFechas.add(dateChooserComboDesde);
        dateChooserComboHasta = new DateChooserCombo();
        jPanelFechas.add(etiquetaHasta);
        jPanelFechas.add(dateChooserComboHasta);
        filtroEstado = new JCheckBox("Ver finalizados/entrada");
        
        palabraClave = new TextField(10);
        palabraClave.setEchoChar('*');
        label1 = new JLabel(icon, JLabel.CENTER);

        
        jPanelFechas.add(botonConsultaRango);
        jPanelFechas.add(filtroEstado);
        jPanelFechas.add(palabraClave);
        
     
      
        
      
      
        add(jPanelFechas, BorderLayout.NORTH);
        add(jPanelBotones, BorderLayout.SOUTH);
        
      
       
        
        //jPanel.add(label1);
        
        
        // SCROLL PANEL DE TABLA 
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setViewportView(JTablaPedidos.getInstance());
        add(jScrollPane1, BorderLayout.CENTER);
        pack();
        
    }             

    public int getClientesSinCalculos() {
        return clientesSinCalculos;
    }
    
    

   
    
    
   
    
    
    
    
    
    
    
     @Override
      public void actionPerformed(ActionEvent e) {
        
          
          if (e.getSource()==menuItemRealizarPedido) {
          
                  GeneradorCodigoBarras generador = new GeneradorCodigoBarras();
                  
                  VentaFrame ventaFrame = VentaFrame.getInstance();
                  ventaFrame.setVisible(true);
                  ventaFrame.setExtendedState(MAXIMIZED_BOTH);
                  JTablaVenta jTablaCRUD= JTablaVenta.getInstance();
                  jTablaCRUD.getColumnModel().getColumn(0).setPreferredWidth(20);
                  jTablaCRUD.getColumnModel().getColumn(1).setPreferredWidth(150);
                  jTablaCRUD.getColumnModel().getColumn(2).setPreferredWidth(200);
                  jTablaCRUD.getColumnModel().getColumn(3).setPreferredWidth(50);
                  jTablaCRUD.getColumnModel().getColumn(4).setPreferredWidth(50);
                  CargarVentaCommand cargarStockCommand = new CargarVentaCommand (CargadorListaProducto.getInstance(), new ProductoParserJson());
                  jTablaCRUD.setCargarVentaCommand(cargarStockCommand);
                  cargarStockCommand.execute();
          
            }
            if (e.getSource()==menuItemVerRankingVentas) {
                 RankingFrame platosFrame = RankingFrame.getInstance();
                                                                platosFrame.setVisible(true);
                                                                platosFrame.setLocationRelativeTo(instance);
                                                                JTablaRanking jTablaPlatosServidos= JTablaRanking.getInstance();
                                                                CargarPlatosServidosCommand cargarPlatosServidosCommand = new CargarPlatosServidosCommand(CargadorListaPlatosServidos.getInstance());
                                                                jTablaPlatosServidos.setCargarPlatosServidosCommand(cargarPlatosServidosCommand);
                                                                cargarPlatosServidosCommand.execute();
            }
            
            
            if (e.getSource()==menuItemVerCajaDia) {
            
             CargadorTotales cargadorTotales = new CargadorTotales();
                                          
                                            String importeGasto = cargadorTotales.cargarTotalGastos();
                                            String importeIngreso = cargadorTotales.cargarTotalIngresos();
                                            Double caja  = Double.parseDouble(importeIngreso) - Double.parseDouble(importeGasto); 
                                     
                                            JOptionPane.showMessageDialog (null, "Ingresos:  " + importeIngreso + "\nGastos:     " + importeGasto +  " \n------------------------\nTotal:         " + caja);
                                            
            
            }
          
          
          if (e.getSource()==menuItemInsertarProducto) {
             InsertarProductoFrame.getInstance().setVisible(true);
             InsertarProductoFrame.getInstance().setLocationRelativeTo(instance);
        }
          if (e.getSource()==menuItemVerProductos) {
                CRUD_ProductosFrame crudProductosFrame = CRUD_ProductosFrame.getInstance();
                                                                crudProductosFrame.setVisible(true);
                                                                crudProductosFrame.setLocationRelativeTo(instance);
                                                                JTablaCRUD jTablaCRUD = JTablaCRUD.getInstance();
                                                                jTablaCRUD.getColumnModel().getColumn(0).setPreferredWidth(150);
                                                                jTablaCRUD.getColumnModel().getColumn(1).setPreferredWidth(15);
                                                                jTablaCRUD.getColumnModel().getColumn(2).setPreferredWidth(200);
                                                                jTablaCRUD.getColumnModel().getColumn(3).setPreferredWidth(80);
                                                                jTablaCRUD.getColumnModel().getColumn(4).setPreferredWidth(80);
                                                                jTablaCRUD.getColumnModel().getColumn(5).setPreferredWidth(80);
                                                                jTablaCRUD.getColumnModel().getColumn(6).setPreferredWidth(80);
                                                                jTablaCRUD.getColumnModel().getColumn(8).setPreferredWidth(200);
                                                                jTablaCRUD.getColumnModel().getColumn(9).setPreferredWidth(20);
                                                                jTablaCRUD.getColumnModel().getColumn(10).setPreferredWidth(20);
                                                                jTablaCRUD.getColumnModel().getColumn(11).setPreferredWidth(20);
                                                                jTablaCRUD.getColumnModel().getColumn(12).setPreferredWidth(20);
                                                                
                                                                jTablaCRUD.getColumnModel().getColumn(13).setPreferredWidth(15);
                                                                CargarDiasFestivosCommand cargarProductosCommand = new CargarDiasFestivosCommand (CargadorListaProducto.getInstance(), new ProductoParserJson());
                                                                jTablaCRUD.setCargarProductosCommand(cargarProductosCommand);
                                                                cargarProductosCommand.execute();
            
            
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
        
        if (e.getSource()==menuItemInsertarGasto) {
            InsertarGastoFrame.getInstance().setVisible(true);
            InsertarGastoFrame.getInstance().setLocationRelativeTo(instance);
        }
        if (e.getSource()==menuItemVerGastos) {
        
                        GastosFrame gastosFrame = GastosFrame.getInstance();
                        gastosFrame.setVisible(true);
                        gastosFrame.setLocationRelativeTo(instance);
                        JTablaGastos jTablaGastos = JTablaGastos.getInstance();
                        CargarGastosCommand cargarGastosCommand = new CargarGastosCommand (CargadorListaGasto.getInstance(), new GastoParserJson());
                        jTablaGastos.setCargarGastosCommand(cargarGastosCommand);
                        cargarGastosCommand.execute();
        }
     
        if (e.getSource()==menuItemInsertarCliente) {
            InsertarClienteFrame insertarFrame = new InsertarClienteFrame();
            insertarFrame.setLocationRelativeTo(instance);
                                         
        }
        
        if (e.getSource()==menuItemVerClientes) {
              clientesSinCalculos = 0;
              CRUD_ClientesFrame crudClientesFrame = CRUD_ClientesFrame.getInstance();
                                                                crudClientesFrame.setVisible(true);
                                                                crudClientesFrame.setLocationRelativeTo(instance);
                                                                JTablaCRUDClientes jTablaCRUD = JTablaCRUDClientes.getInstance();
                                                                CargarClientesCommand cargarClientesCommand = new CargarClientesCommand (CargadorListaCliente.getInstance(), new ClienteParserJson());
                                                                jTablaCRUD.setCargarClientesCommand(cargarClientesCommand);
                                                                
                                                                jTablaCRUD.getColumnModel().getColumn(0).setPreferredWidth(2);
                                                                jTablaCRUD.getColumnModel().getColumn(1).setPreferredWidth(80);
                                                                jTablaCRUD.getColumnModel().getColumn(2).setPreferredWidth(100);
                                                                jTablaCRUD.getColumnModel().getColumn(3).setPreferredWidth(35);
                                                                jTablaCRUD.getColumnModel().getColumn(4).setPreferredWidth(150);
                                                                jTablaCRUD.getColumnModel().getColumn(5).setPreferredWidth(10);
                                                 

                                                                 cargarClientesCommand.execute();
        }
        
        
        
          if (e.getSource()==menuItemVerClientesSinCalculos) {
              clientesSinCalculos = 1;
              CRUD_ClientesFrame crudClientesFrame = CRUD_ClientesFrame.getInstance();
                                                                crudClientesFrame.setVisible(true);
                                                                crudClientesFrame.setLocationRelativeTo(instance);
                                                                JTablaCRUDClientes jTablaCRUD = JTablaCRUDClientes.getInstance();
                                                                CargarClientesCommand cargarClientesCommand = new CargarClientesCommand (CargadorListaCliente.getInstance(), new ClienteParserJson());
                                                                jTablaCRUD.setCargarClientesCommand(cargarClientesCommand);
                                                                
                                                                jTablaCRUD.getColumnModel().getColumn(0).setPreferredWidth(2);
                                                                jTablaCRUD.getColumnModel().getColumn(1).setPreferredWidth(80);
                                                                jTablaCRUD.getColumnModel().getColumn(2).setPreferredWidth(100);
                                                                jTablaCRUD.getColumnModel().getColumn(3).setPreferredWidth(35);
                                                             
                                                                 cargarClientesCommand.execute();
        }
        
        
          if (e.getSource()==menuItemInsertarProveedor) {
             InsertarProveedorFrame insertarFrame = new InsertarProveedorFrame();
                                            insertarFrame.setLocationRelativeTo(instance);
                                         
          }
          if (e.getSource()==menuItemVerProveedores) {
           CRUD_ProveedoresFrame crudProveedoresFrame = CRUD_ProveedoresFrame.getInstance();
                                                                crudProveedoresFrame.setVisible(true);
                                                                crudProveedoresFrame.setLocationRelativeTo(instance);
                                                                JTablaCRUDProveedores jTablaCRUD = JTablaCRUDProveedores.getInstance();
                                                                 // id
                                                                jTablaCRUD.getColumnModel().getColumn(0).setPreferredWidth(6);
                                                                // nombre
                                                                jTablaCRUD.getColumnModel().getColumn(1).setPreferredWidth(120);
                                                                // nif
                                                                jTablaCRUD.getColumnModel().getColumn(2).setPreferredWidth(70);
                                                               
                                                    
                                                                CargarProveedoresCommand cargarProveedoresCommand = new CargarProveedoresCommand (CargadorListaProveedor.getInstance(), new ProveedorParserJson());
                                                                jTablaCRUD.setCargarProveedoresCommand(cargarProveedoresCommand);
                                                                
                                                             try {
                                                                 cargarProveedoresCommand.execute();
                                                             } catch (IOException ex) {
                                                                 Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                                                             }
          }
          
           if (e.getSource()==menuItemInsertarVendedor) {
              InsertarVendedorFrame iv = new InsertarVendedorFrame();
                                              iv.setVisible(true);
                                              iv.setLocationRelativeTo(instance);
           }
        
            if (e.getSource()==menuItemVerVendedores) {
                    CRUD_VendedoresFrame crudVendedoresFrame = CRUD_VendedoresFrame.getInstance();
                                                                crudVendedoresFrame.setVisible(true);
                                                                crudVendedoresFrame.setLocationRelativeTo(instance);
                                                                JTablaVendedores jTablaCRUD = JTablaVendedores.getInstance();
                                                                CargarGruasCommand cargarVendedoresCommand = new CargarGruasCommand (CargadorListaVendedor.getInstance(), new GruasParserJson());
                                                                jTablaCRUD.setCargarVendedoresCommand(cargarVendedoresCommand);
                                                                cargarVendedoresCommand.execute();
           }
            
              if (e.getSource()==menuItemVerComisiones) {
            
                    DatosParaConsultaComisionesFrame datosFrame  = new DatosParaConsultaComisionesFrame();
                    datosFrame.setVisible(true);
                    datosFrame.setLocationRelativeTo(instance);
              }
            
        
        
    }
 
     
       
    
    public void setListenerBotonConsultaRango() {
        dateDesde = dateChooserComboDesde.getSelectedDate();
        dateHasta = dateChooserComboHasta.getSelectedDate();
        botonConsultaRango.addMouseListener(
                                     new MouseAdapter() {
                                                         @Override
                                                         public void mouseClicked(MouseEvent e) {
                                                            stop();
                                                            cargarPedidosCommand.executeFechas(dateDesde.getTime(), dateHasta.getTime());
                                                       
                                                          
                                                         }
                                    });
    }
    

    public void setListenerFiltro () {
        filtroEstado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (filtroEstado.isSelected()) {
                    stop();// paramos la sincronizacion actual y empezamos la de finalizados
                    cargarPedidosCommand.executeFinalizados();
                } else {
                    sincronizacion();
                }
            }});
    }
    
    
    public void setCargarPedidosCommand(CargarPedidosCommand cargarPedidosCommand) {
        this.cargarPedidosCommand = cargarPedidosCommand;
    }

    public void setCargarProductosPedidoCommand(CargarProductosPedidoCommand cargarProductosPedidoCommand) {
        this.cargarProductosPedidoCommand = cargarProductosPedidoCommand;
    }

    public CargarPedidosCommand getCargarPedidosCommand() {
        return cargarPedidosCommand;
    }
    
    
      public void setCargarStockCommand(CargarVentaCommand cargarStockCommand) {
        this.cargarStockCommand = cargarStockCommand;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
 }


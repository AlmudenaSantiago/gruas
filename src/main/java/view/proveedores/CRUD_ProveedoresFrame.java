/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.proveedores;

import command.proveedores.CargarProveedoresCommand;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import process.parser.proveedor.ProveedorParserJson;




public class CRUD_ProveedoresFrame extends javax.swing.JFrame {


    private JScrollPane jScrollPane1;
    private CargarProveedoresCommand cargarProveedoresCommand;
    private JButton botonInsertar = new JButton("INSERTAR NUEVO");
    private static CRUD_ProveedoresFrame instance;
    private Timer timer;
    private TableRowSorter<TableModel> rowSorter;
    private JTextField jTextFieldFilter;
    private JCheckBox checkBoxBuscarPorNombre = new JCheckBox("Buscar por nombre");
    private JCheckBox checkBoxBuscarPorNif = new JCheckBox("Buscar por nif");
    private JCheckBox checkBoxBuscarPorApellidos = new JCheckBox("Buscar por apellidos");
    
    private ProveedorParserJson proveedorParserJson;
    TablaCRUDProveedoresModelo modelo = new TablaCRUDProveedoresModelo();
    
    private CRUD_ProveedoresFrame() {
       
       jTextFieldFilter= new JTextField(20);
       rowSorter = new TableRowSorter<>(JTablaCRUDProveedores.getInstance().getModel());
       JTablaCRUDProveedores.getInstance().setRowSorter(rowSorter);
        
       initComponents();
       setVisible(true);
       setListenerFilter();
        setListenerCheckNombre();
        setListenerCheckNif();
    
       setExtendedState(MAXIMIZED_BOTH);
       setListenerBotonInsertar();
   
   }


    public static CRUD_ProveedoresFrame getInstance() { 
        if (instance == null) {
            instance = new CRUD_ProveedoresFrame();
        }
        return instance;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("GESTIÓN DE PROVEEDORES");
        
       // PRIMER PANEL  
        JPanel jPanelBoton = new JPanel();
        jPanelBoton.add(new JLabel("Palabra a buscar:"));
        jPanelBoton.add(jTextFieldFilter);
        jPanelBoton.add(checkBoxBuscarPorNombre);
        jPanelBoton.add(checkBoxBuscarPorNif);  
        
        jPanelBoton.add(botonInsertar);
        
        add(jPanelBoton, BorderLayout.NORTH);
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setViewportView(JTablaCRUDProveedores.getInstance());
        add(jScrollPane1, BorderLayout.CENTER);
        pack();
    }             
    
    
      public void setListenerCheckNombre() {
    
             checkBoxBuscarPorNombre.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                                checkBoxBuscarPorApellidos.setSelected(false);
                                checkBoxBuscarPorNif.setSelected(false);    
                            
                        }
                    });
        }  
     
      
       
       
      public void setListenerCheckNif() {
    
       checkBoxBuscarPorNif.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                                checkBoxBuscarPorNombre.setSelected(false);
                                checkBoxBuscarPorApellidos.setSelected(false);    
                        }
                    });
        }  
    
    
      public void setListenerFilter() {
  
        System.out.println("llega");
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
           
            if (checkBoxBuscarPorNombre.isSelected()) { return 1; }
            if (checkBoxBuscarPorNif.isSelected()) { return 2; }
          
            return 1;
   }
    
    
      
    public void setListenerBotonInsertar() {
           botonInsertar.addMouseListener(
                                     new MouseAdapter() { 
                                          @Override
                                          public void mouseClicked(MouseEvent e) {
                                            InsertarProveedorFrame insertarFrame = new InsertarProveedorFrame();
                                            insertarFrame.setLocationRelativeTo(instance);
                                         
                                          }
                                     });
    }

    /**
     *
     * @param cargarProveedoresCommand
     */
    public void setCargarProveedoresCommand(CargarProveedoresCommand cargarProveedoresCommand) {
        this.cargarProveedoresCommand = cargarProveedoresCommand;
    }


   
    
 }




package view.fichaCliente;

import command.clientes.CargarClientesCommand;
import command.serviciosEspeciales.CargarServiciosEspecialesCommand;
import command.tarifas.CargarRecorridosyKmCommand;
import command.tarifas.CargarTarifasCommand;
import command.tarifas.CargarTarifasNocturnoCommand;
import command.tarifas.CargarTarifasPorTiempoDeRespuestaCommand;
import command.tarifas.CargarTiposDeTarifaCommand;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Cliente;
import model.RecorridosyKm;
import model.serviciosEspeciales.ServicioEspecial;
import model.Tarifas.Tarifa;
import model.Tarifas.TarifaNocturno;
import model.Tarifas.TarifaPorTiempoDeRespuesta;
import model.Tarifas.TipoDeTarifa;
import process.CRUDClientes.ModificarCliente;
import process.CRUDClientes.RegistrarCliente;
import process.CRUDRecorridosyKm.EliminarRecorridosyKm;
import process.CRUDRecorridosyKm.RegistrarRecorridosyKm;
import process.CRUDServiciosEspeciales.RegistrarServicioEspecial;
import process.CRUDTarifasPorTiempoDeRespuesta.EliminarTarifaPorTiempoDeRespuesta;
import process.CRUDTarifasPorTiempoDeRespuesta.RegistrarTarifaPorTiempoDeRespuesta;
import process.cargador.CargadorListaCliente;
import process.cargador.recorridosykm.CargadorListaRecorridosyKm;
import process.cargador.serviciosEspeciales.CargadorListaServiciosEspeciales;
import process.cargador.tarifas.CargadorListaTarifas;
import process.cargador.tarifas.CargadorListaTarifasNocturno;
import process.cargador.tarifas.CargadorListaTiposDeTarifa;
import process.parser.clientes.ClienteParserJson;
import process.parser.recorridosykm.RecorridosyKmParserJson;
import process.parser.serviciosEspeciales.ServicioEspecialParserJson;
import process.parser.tarifas.TarifaNocturnoParserJson;
import process.parser.tarifas.TarifaParserJson;
import process.parser.tarifas.TarifaPorTiempoDeRespuestaParserJson;
import process.parser.tipoDeTarifas.TipoDeTarifaParserJson;
import view.clientes.FichaClienteFrame;
import view.diasFestivos.SeleccionHorariosClientesFrame;
import view.recorridosyKm.JTablaCRUDRecorridosyKm;
import view.serviciosEspeciales.JTablaServiciosEspeciales;
import view.tarifas.simples.JTablaTarifas;
import view.tarifas.porTiempoDeRespuesta.JTablaTarifasPorTiempoRespuesta;
import view.tarifas.porTiempoDeRespuesta.JTablaTarifasPorTiempoDeRespuestaNocturno;
import view.tarifas.simples.AsociarTarifasAClienteFrame;
import view.tarifas.simples.JTablaTarifasNocturno;
import view.tarifas.tiposDeTarifas.JTablaTiposDeTarifas;



public class FichaCliente extends javax.swing.JFrame {

    
    HashMap<Integer,Integer> hashContador;
    JTablaTarifas tablaDiurno;
    JTablaTarifasNocturno tablaNocturno;
    List<Cliente> clientesBusqueda;
    JTablaTiposDeTarifas tablaTipos;
    JTablaCRUDRecorridosyKm tabla;
    JTablaTarifasPorTiempoRespuesta tablaTR;
    JTablaTarifasPorTiempoDeRespuestaNocturno tablaTRNocturno; 
    JTablaServiciosEspeciales tablaServiciosEspeciales;
    private boolean ultimo;
    List<Cliente> clientes;
    private Integer id;
    private Integer a=1;
    
    Integer index = 1;
   
    SeleccionHorariosClientesFrame seleccion;
    
    List<ServicioEspecial> listaServiciosEspecialesDelCliente;
    List<ServicioEspecial> listaServiciosEspeciales;
    
    Cliente cliente;
    List<TipoDeTarifa> listaTodosLosTipos;
    List<TipoDeTarifa> listaTiposDelCliente;
    
   
    TextFieldBusqueda gtextfieldId;
    
    FichaClienteModelo fichaModelo;
    private static FichaCliente instance;

    public JTablaTarifas getTablaDiurno() {
        return tablaDiurno;
    }
 
    
   
      public static FichaCliente getInstance(Integer id) throws Exception { 
        if (instance == null) {
            instance = new FichaCliente(id);
        }
        return instance;
    }

    public JTablaTarifasPorTiempoRespuesta getTablaTR() {
        return tablaTR;
    }

    public JTablaCRUDRecorridosyKm getTabla() {
        return tabla;
    }

      
      
    public FichaClienteModelo getFichaModelo() {
        return fichaModelo;
    }
 
 
     public void setListenerChangerTab() { 
                     ChangeListener changeListener = new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent changeEvent) {
                   deshabilitarCheckboxesEdicion();
                }

              
    };
    
    
    jTabbedPane1.addChangeListener(changeListener);
    
    
     }
    private FichaCliente(Integer id) throws Exception {
      
        // NOS TRAEMOS EL CLIENTE COMPLETO SEGUN SU ID PARA PONERLO EN EL SINGLETON Y QUE TODAS LAS CLASES LO PUEDAN INSTANCIAR
        try {
             clientes = new CargarClientesCommand(new CargadorListaCliente(), new ClienteParserJson()).executeCliente(id);
         } catch (Exception ex) {
             Logger.getLogger(FichaClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
        
         cliente = clientes.get(0);
        
         SingletonCliente.getInstance().setCliente(clientes.get(0));
          
     
          fichaModelo =  new FichaClienteModelo();
           
        
        initComponents();
        
        setListenerChangerTab();
       
        if (!"".equals(cliente.getImagen())) {
            imagenCliente.setIcon(new javax.swing.ImageIcon(cliente.getImagen()));
            jLabelNombreCliente.setText(SingletonCliente.getInstance().getCliente().getNombre());
            jLabelCodigoCliente.setText(SingletonCliente.getInstance().getCliente().getId().toString());
       
            imagenCliente.setText("");
            imagenCliente.repaint();
            imagenCliente.revalidate();
        }
        imagenCliente.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(final MouseEvent e) {
                            if  (e.getClickCount() == 2) {  
                                
                                JFileChooser chooser = new JFileChooser();
                                chooser.setCurrentDirectory(new java.io.File("."));
                                chooser.setDialogTitle("Selector de imagen");
                                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                                chooser.setAcceptAllFileFilterUsed(false);
                                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                                   ModificarCliente modificar  = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(),"imagen",chooser.getSelectedFile().toString()) ;
                                  try {
                                      modificar.modificarCliente();
                                  } catch (IOException ex) {
                                      Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                                  }
                                  // NOS TRAEMOS EL CLIENTE COMPLETO SEGUN SU ID PARA PONERLO EN EL SINGLETON Y QUE TODAS LAS CLASES LO PUEDAN INSTANCIAR
                              // ESTE CACHO DE CODIGO NO ME GUSTA SOLO QUIERO TRAERME LA NUEVA IMAGEN DEL CLIENTE Y ACTUALIZARLA EN EL SINGLETON
                                  
                                  try {
                                     clientes = new CargarClientesCommand(new CargadorListaCliente(), new ClienteParserJson()).executeCliente(SingletonCliente.getInstance().getCliente().getId());
                                 } catch (Exception ex) {
                                     Logger.getLogger(FichaClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
                                 }

                                 cliente = clientes.get(0);
                                 SingletonCliente.getInstance().setCliente(cliente);
                                  if (!"".equals(SingletonCliente.getInstance().getCliente().getImagen())) {
                                        imagenCliente.setIcon(new javax.swing.ImageIcon(SingletonCliente.getInstance().getCliente().getImagen()));
                                        imagenCliente.setText("");
                                     }
                                 
                                } 
                                                
                        }
                        }
                    });



        llenarTablaRecorridosyKm();
        llenarTablaTarifasPorTiempoDeRespuesta();
        llenarTablaTarifas();
        llenarPanelBusquedaClientes();
        setListenerMostrarClientesBaja();
        llenarTablaServiciosEspeciales();
       
        setListenerMuestraSuplemento();
        
        setListenerEditarTablaTarifas();
        setListenerEditarTablaTarifasPorTiempoDeRespuesta();
        setListenerEditarTablaRecorridos();
        setListenerEditarTablaServiciosEspeciales();
        setListenersVariablesTarifas();
       
        setLocationRelativeTo(null);
        
        llenarBarraNumClientes();
        pack();
        
        
        
    }

    public JTabbedPane getjTabbedPane1() {
        return jTabbedPane1;
    }

    public JPanel getjPanel8() {
        return jPanel8;
    }

    
    
    
     
    public void llenarBarraNumClientes () {
       jTextFieldNumCliente.setText(gtextfieldId.getHashContador().get(SingletonCliente.getInstance().getCliente().getId()).toString());
       jLabelNumTotalClientes.setText(CargadorListaCliente.getInstance().cargarTotalClientes());
   
      }
  
   public void setListenersVariablesTarifas() {
  
       jTextFieldRadioUrbano.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                   // e.getOpositeComponent() devuelve el Component que le
                   // cede el foco a nuestro botón.
                }
                @Override
                public void focusLost(FocusEvent e) {
                    ModificarCliente modificar  = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(),"radioUrbano",jTextFieldRadioUrbano.getText() ) ;
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
         });
       
       
       
        jTextFieldRadioUrbano.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                  
                   // e.getOpositeComponent() devuelve el Component que le
                   // cede el foco a nuestro botón.
                }
                @Override
                public void focusLost(FocusEvent e) {
                    ModificarCliente modificar  = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(),"radioUrbano",jTextFieldRadioUrbano.getText() ) ;
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
         });
       
       
       jTextFieldUmbral.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                  
                   // e.getOpositeComponent() devuelve el Component que le
                   // cede el foco a nuestro botón.
                }
                @Override
                public void focusLost(FocusEvent e) {
                    ModificarCliente modificar  = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(),"umbral",jTextFieldUmbral.getText() ) ;
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
         });
       
        jTextFieldUmbral.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                  
                   // e.getOpositeComponent() devuelve el Component que le
                   // cede el foco a nuestro botón.
                }
                @Override
                public void focusLost(FocusEvent e) {
                    ModificarCliente modificar  = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(),"umbral",jTextFieldUmbral.getText() ) ;
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
         });
       
       
       
         jTextFieldRIS.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                  
                   // e.getOpositeComponent() devuelve el Component que le
                   // cede el foco a nuestro botón.
                }
                @Override
                public void focusLost(FocusEvent e) {
                    ModificarCliente modificar  = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(),"RIS",jTextFieldRIS.getText() ) ;
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
         });
        
  }
    
    
   
   
    
   public void llenarPanelBusquedaClientes() throws Exception {
    
         gtextfieldId = new TextFieldBusqueda(this,0,0,true);
         gtextfieldId.setWidthPopupPanel(200);
	 gtextfieldId.setHeightPopupPanel(200);
         gtextfieldId.setVisible(true);
         
         gtextfieldId.llenarLista();
       
         /* gtextfieldId.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent evt)
                {
                    if(evt.getKeyCode() == KeyEvent.VK_ENTER)
                    {
                       cambiarDeCliente();
                    }
                }
        });
         */
           
         JLabel j = new JLabel("    Búsqueda de clientes   ", SwingConstants.CENTER );
         j.setFont(new java.awt.Font("Tahoma", 2, 16));
         j.setVisible(true);
        
       
         jPanelBusquedaClientes.setLayout(new GridLayout(1,4));
         jPanelBusquedaClientes.add(j);
         jPanelBusquedaClientes.add(gtextfieldId);
        // jPanelBusquedaClientes.add(gtextfield);
     
         jLabelNombreCliente.setText(SingletonCliente.getInstance().getCliente().getNombre());
         jLabelCodigoCliente.setText(SingletonCliente.getInstance().getCliente().getId().toString());
         
         if (!"".equals(SingletonCliente.getInstance().getCliente().getImagen())) {
             imagenCliente.setSize(100, 100);
             ImageIcon icon = new ImageIcon(SingletonCliente.getInstance().getCliente().getImagen());
             Icon icono = new ImageIcon(icon.getImage().getScaledInstance(imagenCliente.getWidth(), imagenCliente.getHeight(), Image.SCALE_DEFAULT));
             imagenCliente.setText(null);
             imagenCliente.setIcon(icono);
         } else {
               imagenCliente.setText("LOGO CLIENTE AQUI");
         }
         imagenCliente.repaint();
         imagenCliente.revalidate();
     
   }
   
    public void setListenerMostrarClientesBaja() {
            jCheckBoxClientesDeBaja.addItemListener(new ItemListener() {
               @Override
               public void itemStateChanged(ItemEvent e) {
                       
                   if (jCheckBoxClientesDeBaja.isSelected()) {
                       System.out.println("entro en baja");  
                       gtextfieldId.llenarListaClientesDeBaja();
                       permitirEdicion(false);
                       } else {
                        gtextfieldId.llenarLista();
                        permitirEdicion(true);
                    }
                   
                   
               }
             });
    }
   
   
    
    public void setListenerEditarTablaTarifas() {
            jCheckBoxEdicion.addItemListener(new ItemListener() {
               @Override
               public void itemStateChanged(ItemEvent e) {
                       
                   if (jCheckBoxEdicion.isSelected()) {
                             tablaDiurno.habilitarEdicionTabla();
                             tablaNocturno.habilitarEdicionTabla();
                             
                   } else {
                             tablaDiurno.deshabilitarEdicionTabla();
                             tablaNocturno.deshabilitarEdicionTabla();
                             
                   }
                   
                   
               }
             });
    }
    
      public void setListenerEditarTablaRecorridos() {
            jCheckBoxEdicionRecorridos.addItemListener(new ItemListener() {
               @Override
               public void itemStateChanged(ItemEvent e) {
                       
                   if (jCheckBoxEdicionRecorridos.isSelected()) {
                             tabla.habilitarEdicionTabla();
                             
                   } else {
                             tabla.deshabilitarEdicionTabla();
                   }
                   
                   
               }
             });
    }
      
        public void setListenerEditarTablaServiciosEspeciales() {
            jCheckBoxEdicionServiciosEspeciales.addItemListener(new ItemListener() {
               @Override
               public void itemStateChanged(ItemEvent e) {
                       
                   if (jCheckBoxEdicionServiciosEspeciales.isSelected()) {
                             tablaServiciosEspeciales.habilitarEdicionTabla();
                             
                   } else {
                             tablaServiciosEspeciales.deshabilitarEdicionTabla();
                   }
                   
                   
               }
             });
    }
      
      
    
      public void setListenerEditarTablaTarifasPorTiempoDeRespuesta() {
            jCheckBoxEdicionTarifasTiempoRespuesta.addItemListener(new ItemListener() {
               @Override
               public void itemStateChanged(ItemEvent e) {
                       
                   if (jCheckBoxEdicionTarifasTiempoRespuesta.isSelected()) {
                             tablaTR.habilitarEdicionTabla();
                             tablaTRNocturno.habilitarEdicionTabla();
                    } else {
                             tablaTRNocturno.deshabilitarEdicionTabla();
                             tablaTR.deshabilitarEdicionTabla();
                   }
                   
                   
               }
             });
    }
    
      
    
        public void setListenerMuestraSuplemento() {
            jCheckBoxMuestraSupNocturno.addItemListener(new ItemListener() {
               @Override
               public void itemStateChanged(ItemEvent e) {
                   Integer i;   
                   if (jCheckBoxMuestraSupNocturno.isSelected()) {
                      i = 1;
                    } else {
                        i = 0; 
                   }
                     ModificarCliente modificar  = new ModificarCliente(cliente.getId(),"muestraSuplemento",i.toString() ) ;
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }      
                   
               }
             });
    }
    
      
    
      
      
      

    public void actualizarListaTarifas (List<Tarifa> listaTarifasDiurno, List<TarifaNocturno> listaTarifasNocturno, List<TipoDeTarifa> listaTiposDelCliente)  {
        
        tablaDiurno.mostrarTarifas(listaTarifasDiurno);
        tablaNocturno.mostrarTarifas(listaTarifasNocturno);
        tablaTipos.mostrarTarifas(listaTiposDelCliente);
    
    }
  
    public void actualizarListaTodosLosTipos() {
        try {
            CargarTiposDeTarifaCommand cargarTipos = new CargarTiposDeTarifaCommand(new CargadorListaTiposDeTarifa(), new TipoDeTarifaParserJson());
            
            listaTodosLosTipos = cargarTipos.executeLista();
        } catch (IOException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    
    }
    
    private void llenarTablaTarifas() throws IOException {
        
        tablaDiurno = new JTablaTarifas();
        tablaNocturno = new JTablaTarifasNocturno();
        tablaNocturno.setForeground(Color.red);
        CargarTarifasCommand cargar = new CargarTarifasCommand(new CargadorListaTarifas(), new TarifaParserJson());
        CargarTarifasNocturnoCommand cargarNocturno = new CargarTarifasNocturnoCommand(new CargadorListaTarifasNocturno(), new TarifaNocturnoParserJson());
       
        CargarTiposDeTarifaCommand cargarTipos = new CargarTiposDeTarifaCommand(new CargadorListaTiposDeTarifa(), new TipoDeTarifaParserJson());
       
        tablaTipos = new JTablaTiposDeTarifas();
      
        List<Tarifa> listaTarifasDiurno  =  cargar.executeLista(SingletonCliente.getInstance().getCliente().getId());
        List<TarifaNocturno> listaTarifasNocturno = cargarNocturno.executeListaNocturno(SingletonCliente.getInstance().getCliente().getId());
        
        listaTodosLosTipos = cargarTipos.executeLista();
        listaTiposDelCliente = new ArrayList<>();
       
        for (Tarifa lista1 : listaTarifasDiurno) {
            for (int j=0;j<listaTodosLosTipos.size();j++) {
                if (lista1.getTipoDeTarifa().equals(listaTodosLosTipos.get(j).getId())) {
                    TipoDeTarifa tipo = new TipoDeTarifa();
                    tipo.setTipo(listaTodosLosTipos.get(j).getTipo());
                    tipo.setId(listaTodosLosTipos.get(j).getId());
                    listaTiposDelCliente.add(tipo);
                    
                }
            }
        }
        
        
        
        tablaDiurno.mostrarTarifas(listaTarifasDiurno);
        tablaNocturno.mostrarTarifas(listaTarifasNocturno);
        tablaTipos.mostrarTarifas(listaTiposDelCliente);
        
        jScrollPaneTarifas.setViewportView(tablaDiurno);
        jScrollPaneTarifasNocturno.setViewportView(tablaNocturno);
        jScrollPaneTiposDeTarifa.setViewportView(tablaTipos);
   
    
    }
    
    
    private void llenarTablaTarifasPorTiempoDeRespuesta() throws Exception {
        tablaTR = new JTablaTarifasPorTiempoRespuesta();
        tablaTR.getColumnModel().getColumn(0).setPreferredWidth(1);
        tablaTRNocturno = new JTablaTarifasPorTiempoDeRespuestaNocturno();
        CargarTarifasPorTiempoDeRespuestaCommand cargar = new CargarTarifasPorTiempoDeRespuestaCommand(new CargadorListaTarifas(), new TarifaPorTiempoDeRespuestaParserJson());
       
        List<TarifaPorTiempoDeRespuesta> lista  =  cargar.executeLista(SingletonCliente.getInstance().getCliente().getId());
        tablaTR.mostrarTarifas(lista);
        tablaTRNocturno.mostrarTarifas(lista);
        panelScrollTablaTarifas.setViewportView(tablaTR);
        jScrollPaneNocturno.setViewportView(tablaTRNocturno);
    
    }
    
    
    private void llenarTablaServiciosEspeciales() throws Exception {
        tablaServiciosEspeciales = new JTablaServiciosEspeciales();
       
        CargarServiciosEspecialesCommand cargar = new CargarServiciosEspecialesCommand(new CargadorListaServiciosEspeciales(), new ServicioEspecialParserJson());
       
        listaServiciosEspecialesDelCliente  =  cargar.executeLista(SingletonCliente.getInstance().getCliente().getId());
        tablaServiciosEspeciales.mostrarServiciosEspeciales(listaServiciosEspecialesDelCliente);
       
        jScrollPaneTablaServiciosEspeciales.setViewportView(tablaServiciosEspeciales);
      
    
    }
    
    
    
    private void llenarTablaRecorridosyKm() throws Exception {
        
            jTextFieldRadioUrbano.setText(cliente.getRadioUrbano().toString());
            jTextFieldPorcentajeSuplementoNocturno.setText(cliente.getPorcentajeSuplementoNocturno().toString());
            jTextFieldNumDiasCoberturaBase.setText(cliente.getNumDiasCoberturaEnBase().toString());
            
            int i = cliente.getMuestraSuplemento();
            
            if (i == 1) jCheckBoxMuestraSupNocturno.setSelected(true);
            else jCheckBoxMuestraSupNocturno.setSelected(false);
            
            tabla = new JTablaCRUDRecorridosyKm();
            tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(10);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(10);
            tabla.getColumnModel().getColumn(3).setPreferredWidth(10);
            tabla.getColumnModel().getColumn(4).setPreferredWidth(10);
            tabla.getColumnModel().getColumn(5).setPreferredWidth(10);
            tabla.getColumnModel().getColumn(6).setPreferredWidth(10);
            tabla.getColumnModel().getColumn(7).setPreferredWidth(10);
            
           
            
            
           CargarRecorridosyKmCommand cargar = new CargarRecorridosyKmCommand(new CargadorListaRecorridosyKm(), new RecorridosyKmParserJson());
           List<RecorridosyKm> lista = cargar.executeLista(SingletonCliente.getInstance().getCliente().getId());
           tabla.mostrarRecorridosyKm(lista);
           panelContenedorTabla.setViewportView(tabla);
           
           
           
           
           
    }

    public Integer getIdCliente() {
        return SingletonCliente.getInstance().getCliente().getId();
    }

    
    
    
    
    public List<TipoDeTarifa> getListaTodosLosTipos() {
        return listaTodosLosTipos;
    }

    public List<TipoDeTarifa> getListaTiposDelCliente() {
        return listaTiposDelCliente;
    }
    
    
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        panelScrollTablaTarifas = new javax.swing.JScrollPane();
        jScrollPaneNocturno = new javax.swing.JScrollPane();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jCheckBoxEdicionTarifasTiempoRespuesta = new javax.swing.JCheckBox();
        jButtonNuevaTarifaPorTiempoDeRespuesta = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButtonEliminarTarifaPorTiempoDeRespuesta = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPaneContenedorServicioEspeciales = new javax.swing.JScrollPane();
        jScrollPaneTablaServiciosEspeciales = new javax.swing.JScrollPane();
        jButton5 = new javax.swing.JButton();
        jButtonAsociarServicioEspecial = new javax.swing.JButton();
        jCheckBoxEdicionTarifasTiempoRespuesta1 = new javax.swing.JCheckBox();
        jCheckBoxEdicionServiciosEspeciales = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jButtonAsociarServicioEspecial1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jScrollPaneTarifas = new javax.swing.JScrollPane();
        jScrollPaneTarifasNocturno = new javax.swing.JScrollPane();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jTextFieldRadioUrbano = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jTextFieldNumDiasCoberturaBase = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jCheckBoxMuestraSupNocturno = new javax.swing.JCheckBox();
        jLabel46 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jTextFieldUmbral = new javax.swing.JTextField();
        jTextFieldRIS = new javax.swing.JTextField();
        jScrollPaneTiposDeTarifa = new javax.swing.JScrollPane();
        jLabel47 = new javax.swing.JLabel();
        jCheckBoxEdicion = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        panelContenedorTabla = new javax.swing.JScrollPane();
        jCheckBoxEdicionRecorridos = new javax.swing.JCheckBox();
        jButtonNuevaTarifaRecorridosyKm = new javax.swing.JButton();
        jTextFieldPorcentajeSuplementoNocturno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonNuevaTarifaRecorridosyKm1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jLabelNombreCliente = new javax.swing.JLabel();
        jLabelCodigoCliente = new javax.swing.JLabel();
        jPanelBusquedaClientes = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        imagenCliente = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTextFieldNumCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabelNumTotalClientes = new javax.swing.JLabel();
        jCheckBoxClientesDeBaja = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TARIFICACIONES");

        jTabbedPane1.addTab("Ficha Cliente", fichaModelo);
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1216, 1247));

        panelScrollTablaTarifas.setMinimumSize(new java.awt.Dimension(1, 1));

        jScrollPaneNocturno.setBackground(new java.awt.Color(255, 0, 0));
        jScrollPaneNocturno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red));
        jScrollPaneNocturno.setViewportBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red));

        jLabel32.setBackground(new java.awt.Color(51, 51, 255));
        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 51, 255));
        jLabel32.setText("DIURNO");

        jLabel34.setBackground(new java.awt.Color(51, 51, 255));
        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(153, 0, 51));
        jLabel34.setText("NOCTURNO Y FESTIVOS");

        jCheckBoxEdicionTarifasTiempoRespuesta.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxEdicionTarifasTiempoRespuesta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBoxEdicionTarifasTiempoRespuesta.setText("Permitir editar tabla");
        jCheckBoxEdicionTarifasTiempoRespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEdicionTarifasTiempoRespuestaActionPerformed(evt);
            }
        });

        jButtonNuevaTarifaPorTiempoDeRespuesta.setBackground(new java.awt.Color(255, 255, 255));
        jButtonNuevaTarifaPorTiempoDeRespuesta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonNuevaTarifaPorTiempoDeRespuesta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/plus.png"))); // NOI18N
        jButtonNuevaTarifaPorTiempoDeRespuesta.setText("Nueva tarifa por tiempo de respuesta");
        jButtonNuevaTarifaPorTiempoDeRespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaTarifaPorTiempoDeRespuestaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jButtonEliminarTarifaPorTiempoDeRespuesta.setBackground(new java.awt.Color(255, 255, 255));
        jButtonEliminarTarifaPorTiempoDeRespuesta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonEliminarTarifaPorTiempoDeRespuesta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/papelera.jpg"))); // NOI18N
        jButtonEliminarTarifaPorTiempoDeRespuesta.setText("Eliminar tarifa");
        jButtonEliminarTarifaPorTiempoDeRespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarTarifaPorTiempoDeRespuestaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBoxEdicionTarifasTiempoRespuesta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonNuevaTarifaPorTiempoDeRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEliminarTarifaPorTiempoDeRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel32)
                                .addComponent(panelScrollTablaTarifas, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneNocturno, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))))
                .addContainerGap(116, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPaneNocturno, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                    .addComponent(panelScrollTablaTarifas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEliminarTarifaPorTiempoDeRespuesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBoxEdicionTarifasTiempoRespuesta)
                        .addComponent(jButtonNuevaTarifaPorTiempoDeRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tarifas por tiempo de respuesta", jPanel1);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPaneContenedorServicioEspeciales.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneContenedorServicioEspeciales.setViewportView(jScrollPaneTablaServiciosEspeciales);

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/plus.png"))); // NOI18N
        jButton5.setText("Configurar tarifas del cliente");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButtonAsociarServicioEspecial.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAsociarServicioEspecial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAsociarServicioEspecial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/plus.png"))); // NOI18N
        jButtonAsociarServicioEspecial.setText("Nuevo servicio especial");
        jButtonAsociarServicioEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAsociarServicioEspecialActionPerformed(evt);
            }
        });

        jCheckBoxEdicionTarifasTiempoRespuesta1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxEdicionTarifasTiempoRespuesta1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBoxEdicionTarifasTiempoRespuesta1.setText("Permitir editar tabla");

        jCheckBoxEdicionServiciosEspeciales.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxEdicionServiciosEspeciales.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBoxEdicionServiciosEspeciales.setText("Permitir editar tabla");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 0, 51));
        jLabel3.setText("(con suplemento)");

        jButtonAsociarServicioEspecial1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAsociarServicioEspecial1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAsociarServicioEspecial1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/papelera.jpg"))); // NOI18N
        jButtonAsociarServicioEspecial1.setText("Eliminar servicio");
        jButtonAsociarServicioEspecial1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAsociarServicioEspecial1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(124, 124, 124))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneContenedorServicioEspeciales, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jCheckBoxEdicionServiciosEspeciales)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAsociarServicioEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAsociarServicioEspecial1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 724, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(606, 606, 606)
                    .addComponent(jButton5)
                    .addContainerGap(586, Short.MAX_VALUE)))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(617, 617, 617)
                    .addComponent(jCheckBoxEdicionTarifasTiempoRespuesta1)
                    .addContainerGap(597, Short.MAX_VALUE)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneContenedorServicioEspeciales, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxEdicionServiciosEspeciales)
                    .addComponent(jButtonAsociarServicioEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAsociarServicioEspecial1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(391, 391, 391)
                    .addComponent(jButton5)
                    .addContainerGap(370, Short.MAX_VALUE)))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(396, 396, 396)
                    .addComponent(jCheckBoxEdicionTarifasTiempoRespuesta1)
                    .addContainerGap(369, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Servicios Especiales", jPanel8);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setAutoscrolls(true);

        jScrollPaneTarifas.setMaximumSize(new java.awt.Dimension(50000, 50000));
        jScrollPane8.setViewportView(jScrollPaneTarifas);

        jScrollPaneTarifasNocturno.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red));

        jLabel33.setBackground(new java.awt.Color(51, 51, 255));
        jLabel33.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 51, 255));
        jLabel33.setText("DIURNO");

        jLabel35.setBackground(new java.awt.Color(51, 51, 255));
        jLabel35.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(153, 0, 0));
        jLabel35.setText("NOCTURNO Y FESTIVO");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.blue, null, null));
        jPanel10.setForeground(new java.awt.Color(51, 51, 255));

        jLabel36.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel36.setText("Radio urbano:");

        jTextFieldRadioUrbano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRadioUrbanoActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel42.setText("Nº dias:");

        jLabel43.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(102, 0, 0));
        jLabel43.setText("Muestra suplementos en nocturnos y festivos");

        jCheckBoxMuestraSupNocturno.setBackground(new java.awt.Color(255, 255, 255));

        jLabel46.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel46.setText("cobertura en base");

        jLabel44.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel44.setText("Umbral:");

        jLabel45.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel45.setText("RIS:");

        jTextFieldRIS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRISActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldRadioUrbano, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldUmbral, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldRIS, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNumDiasCoberturaBase, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxMuestraSupNocturno)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxMuestraSupNocturno)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(jTextFieldRadioUrbano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel42)
                        .addComponent(jTextFieldNumDiasCoberturaBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel46)
                        .addComponent(jLabel43)
                        .addComponent(jLabel44)
                        .addComponent(jLabel45)
                        .addComponent(jTextFieldUmbral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldRIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPaneTiposDeTarifa.setBorder(null);

        jLabel47.setBackground(new java.awt.Color(51, 51, 255));
        jLabel47.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(51, 51, 51));
        jLabel47.setText("TIPOS DE TARIFA");

        jCheckBoxEdicion.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxEdicion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBoxEdicion.setText("Permitir editar tabla");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/plus.png"))); // NOI18N
        jButton2.setText("Configurar tarifas del cliente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton9.setText("Ver horarios y festivos");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jCheckBoxEdicion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneTiposDeTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel35)
                            .addComponent(jScrollPaneTarifasNocturno, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
                        .addGap(29, 29, 29))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel33))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(16, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(jLabel35))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPaneTarifasNocturno, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                        .addComponent(jScrollPane8))
                    .addComponent(jScrollPaneTiposDeTarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxEdicion))
                .addGap(34, 34, 34))
        );

        jTabbedPane1.addTab("Tarifas", jPanel7);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jCheckBoxEdicionRecorridos.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxEdicionRecorridos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBoxEdicionRecorridos.setText("Permitir editar tabla");

        jButtonNuevaTarifaRecorridosyKm.setBackground(new java.awt.Color(255, 255, 255));
        jButtonNuevaTarifaRecorridosyKm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonNuevaTarifaRecorridosyKm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/plus.png"))); // NOI18N
        jButtonNuevaTarifaRecorridosyKm.setText("Nueva tarifa por recorridos y km");
        jButtonNuevaTarifaRecorridosyKm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaTarifaRecorridosyKmActionPerformed(evt);
            }
        });

        jTextFieldPorcentajeSuplementoNocturno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldPorcentajeSuplementoNocturno.setForeground(new java.awt.Color(153, 0, 51));

        jLabel1.setForeground(new java.awt.Color(153, 0, 51));
        jLabel1.setText("Porcentaje de suplemento en nocturno y festivos en tarifa por recorridos y km de este cliente:");

        jLabel2.setForeground(new java.awt.Color(153, 0, 51));
        jLabel2.setText("%");

        jButtonNuevaTarifaRecorridosyKm1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonNuevaTarifaRecorridosyKm1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonNuevaTarifaRecorridosyKm1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/papelera.jpg"))); // NOI18N
        jButtonNuevaTarifaRecorridosyKm1.setText("Eliminar tarifa");
        jButtonNuevaTarifaRecorridosyKm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevaTarifaRecorridosyKm1ActionPerformed(evt);
            }
        });

        jLabel5.setText("Desde:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "LAS PALMAS", "TELDE" }));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelContenedorTabla)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jCheckBoxEdicionRecorridos)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonNuevaTarifaRecorridosyKm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonNuevaTarifaRecorridosyKm1))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldPorcentajeSuplementoNocturno, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addGap(84, 84, 84)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 553, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPorcentajeSuplementoNocturno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelContenedorTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxEdicionRecorridos)
                    .addComponent(jButtonNuevaTarifaRecorridosyKm, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonNuevaTarifaRecorridosyKm1))
                .addGap(32, 32, 32))
        );

        jTabbedPane1.addTab("Recorridos y Km", jPanel11);

        jPanel12.setBackground(new java.awt.Color(126, 157, 252));

        jLabelNombreCliente.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabelNombreCliente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNombreCliente.setText("Cliente");

        jLabelCodigoCliente.setFont(new java.awt.Font("Cambria", 1, 18)); // NOI18N
        jLabelCodigoCliente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCodigoCliente.setText("Cod.cliente");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabelCodigoCliente)
                .addGap(30, 30, 30)
                .addComponent(jLabelNombreCliente)
                .addContainerGap(520, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreCliente)
                    .addComponent(jLabelCodigoCliente))
                .addContainerGap())
        );

        jPanelBusquedaClientes.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBusquedaClientes.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanelBusquedaClientesLayout = new javax.swing.GroupLayout(jPanelBusquedaClientes);
        jPanelBusquedaClientes.setLayout(jPanelBusquedaClientesLayout);
        jPanelBusquedaClientesLayout.setHorizontalGroup(
            jPanelBusquedaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelBusquedaClientesLayout.setVerticalGroup(
            jPanelBusquedaClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/plus.png"))); // NOI18N
        jButton3.setText("Nuevo cliente");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        imagenCliente.setText("LOGO CLIENTE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imagenCliente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imagenCliente)
                .addContainerGap())
        );

        jButton4.setText(">");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText(">>");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("<");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("<<");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTextFieldNumCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNumClienteActionPerformed(evt);
            }
        });

        jLabel4.setText("de");

        jLabelNumTotalClientes.setText("jLabel5");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldNumCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelNumTotalClientes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jTextFieldNumCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jButton6)
                    .addComponent(jLabel4)
                    .addComponent(jLabelNumTotalClientes))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jCheckBoxClientesDeBaja.setText("Mostrar clientes de baja");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelBusquedaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxClientesDeBaja)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
            .addComponent(jTabbedPane1)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBoxClientesDeBaja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(jPanelBusquedaClientes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            AsociarTarifasAClienteFrame asociarFrame = new AsociarTarifasAClienteFrame(this);
            asociarFrame.setVisible(true);
            asociarFrame.setLocationRelativeTo(null);
            asociarFrame.pack();

        } catch (IOException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    
    
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      
                    
                   Cliente nuevo = new Cliente();
                   nuevo.setId( Integer.parseInt(gtextfieldId.getText()));
                   
                    RegistrarCliente insertaCliente = new RegistrarCliente(nuevo);
                    String resultado = null;
                    try {
                      resultado =  insertaCliente.insertaCliente();
                   
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
 
                    if (resultado != null ) {
                      cambiarDeCliente(nuevo.getId().toString());
                      gtextfieldId.llenarLista();
                    }
                    
                      
                    
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButtonAsociarServicioEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAsociarServicioEspecialActionPerformed
       
             ServicioEspecial servicio = new ServicioEspecial();
            
             servicio.setCliente(SingletonCliente.getInstance().getCliente().getId());
             servicio.setIgic(7);
             servicio.setImporteSalida(0.0);
             servicio.setImporteSalidaSuplemento(0.0);
             servicio.setUnidad("Km");
             servicio.setImporteUnidadSuplemento(0.0);
             servicio.setImporteUnidad(0.0);
             servicio.setImporteSalida(0.0);
             servicio.setNombre("");
             servicio.setServicioNombre("");
             servicio.setServicio("");
             servicio.setSuplemento(0.0);
             servicio.setTipo("");
             servicio.setUmbral(1);
             servicio.setNombreServicio("");
             
            
             RegistrarServicioEspecial nuevo = new RegistrarServicioEspecial(servicio);
                try {
                    nuevo.registrar();
                } catch (Exception ex) {
                    Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
             
             CargarServiciosEspecialesCommand cargar = new CargarServiciosEspecialesCommand(new CargadorListaServiciosEspeciales(), new ServicioEspecialParserJson());

             try {
                 listaServiciosEspecialesDelCliente  =  cargar.executeLista(SingletonCliente.getInstance().getCliente().getId());
             } catch (IOException ex) {
                 Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
             }
               tablaServiciosEspeciales.mostrarServiciosEspeciales(listaServiciosEspecialesDelCliente);
       
                    
       
    }//GEN-LAST:event_jButtonAsociarServicioEspecialActionPerformed

    private void jButtonNuevaTarifaPorTiempoDeRespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaTarifaPorTiempoDeRespuestaActionPerformed
        
       
        TarifaPorTiempoDeRespuesta tarifaTR = new TarifaPorTiempoDeRespuesta();
        tarifaTR.setHasta(0);
        tarifaTR.setDesde(0);
        tarifaTR.setHastaKm(0);
        tarifaTR.setKm(0.0);
        tarifaTR.setKmNocturno(0.0);
        tarifaTR.setRis(0.0);
        tarifaTR.setRisNocturno(0.0);
        tarifaTR.setServicio(0.0);
        tarifaTR.setServicioNocturno(0.0);
        tarifaTR.setSegundaSalida(0.0);
        tarifaTR.setSegundaSalidaNocturno(0.0);
        tarifaTR.setTipoTarifa(5);
        tarifaTR.setTarifa("Tarifa");
        tarifaTR.setIdCliente(SingletonCliente.getInstance().getCliente().getId());
       
        RegistrarTarifaPorTiempoDeRespuesta registrar = new RegistrarTarifaPorTiempoDeRespuesta(tarifaTR);
        try {
            registrar.registrar();
        } catch (Exception ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
         CargarTarifasPorTiempoDeRespuestaCommand cargar = new CargarTarifasPorTiempoDeRespuestaCommand(new CargadorListaTarifas(), new TarifaPorTiempoDeRespuestaParserJson());
       
        List<TarifaPorTiempoDeRespuesta> lista = null;
        try {
            lista = cargar.executeLista(SingletonCliente.getInstance().getCliente().getId());
        } catch (IOException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        tablaTR.mostrarTarifas(lista);
        tablaTRNocturno.mostrarTarifas(lista);
        
    }//GEN-LAST:event_jButtonNuevaTarifaPorTiempoDeRespuestaActionPerformed

    
    public void deshabilitarCheckboxesEdicion() {
          
        jCheckBoxEdicion.setSelected(false);
        jCheckBoxEdicionRecorridos.setSelected(false);
        jCheckBoxEdicionServiciosEspeciales.setSelected(false);
        jCheckBoxEdicionTarifasTiempoRespuesta.setSelected(false);
        jCheckBoxEdicionTarifasTiempoRespuesta1.setSelected(false);
        fichaModelo.getCheckEdicionCliente().setSelected(false);
     
    }
    
    
      public void permitirEdicion(boolean permitir) {
          
        jCheckBoxEdicion.setEnabled(permitir);
        jCheckBoxEdicionRecorridos.setEnabled(permitir);
        jCheckBoxEdicionServiciosEspeciales.setEnabled(permitir);
        jCheckBoxEdicionTarifasTiempoRespuesta.setEnabled(permitir);
        jCheckBoxEdicionTarifasTiempoRespuesta1.setEnabled(permitir);
        fichaModelo.getCheckEdicionCliente().setEnabled(permitir);
        
        
        
     
    }
    
    
    
    private void jButtonNuevaTarifaRecorridosyKmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaTarifaRecorridosyKmActionPerformed
       
        RecorridosyKm r = new RecorridosyKm();
        r.setFurgon(0.0);
        r.setIda(0.0);
        r.setMunicipio("");
        r.setTodoterreno(0.0);
        r.setTurismo(0.0);
        r.setMaquinaria(0.0);
        r.setTarifaparamasdetres(0.0);
        r.setKmtotal(0.0);
        r.setIdCliente(SingletonCliente.getInstance().getCliente().getId());
        RegistrarRecorridosyKm reg = new RegistrarRecorridosyKm(r);
        try {
            reg.registrar();
        } catch (Exception ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
         CargarRecorridosyKmCommand cargar = new CargarRecorridosyKmCommand(new CargadorListaRecorridosyKm(), new RecorridosyKmParserJson());
           List<RecorridosyKm> lista = cargar.executeLista(SingletonCliente.getInstance().getCliente().getId());
           tabla.mostrarRecorridosyKm(lista);
          
        
        
        
        
    }//GEN-LAST:event_jButtonNuevaTarifaRecorridosyKmActionPerformed
   
    
    
    
    public void cambiarDeCliente(String idCliente) {
        
      try {
             clientes = new CargarClientesCommand(new CargadorListaCliente(), new ClienteParserJson()).executeCliente(Integer.parseInt(idCliente.trim()));
         } catch (Exception ex) {
             Logger.getLogger(FichaClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
        
         cliente = clientes.get(0);
         SingletonCliente.getInstance().setCliente(cliente);
       
       
        
        fichaModelo.actualizarCliente();
        jLabelNombreCliente.setText(SingletonCliente.getInstance().getCliente().getNombre());
        jLabelCodigoCliente.setText(SingletonCliente.getInstance().getCliente().getId().toString());
       if (!"".equals(SingletonCliente.getInstance().getCliente().getImagen())) {
          
             imagenCliente.setSize(20,20);
             ImageIcon icon = new ImageIcon(SingletonCliente.getInstance().getCliente().getImagen());
             Icon icono = new ImageIcon(icon.getImage().getScaledInstance(imagenCliente.getWidth(), imagenCliente.getHeight(), Image.SCALE_DEFAULT));
             imagenCliente.setText(null);
             imagenCliente.setIcon(icono);
       
       
       } else {
               imagenCliente.setIcon(null);
               imagenCliente.setText("LOGO  AQUI");
         }                        
        
      //  gtextfieldId.setText(idCliente);
        
        try {
            llenarTablaRecorridosyKm();
        } catch (Exception ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            llenarTablaTarifasPorTiempoDeRespuesta();
        } catch (Exception ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            llenarTablaTarifas();
        } catch (IOException ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        try {
            llenarTablaServiciosEspeciales();
        } catch (Exception ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    
      
        
    }
    
    
    private void jTextFieldRadioUrbanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRadioUrbanoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRadioUrbanoActionPerformed

   public void cargarClienteDesdeBarraBusqueda() {
         
        StringTokenizer st = new StringTokenizer(gtextfieldId.getText(), "()");
        String idCliente = st.nextToken();
                    
        cambiarDeCliente(idCliente);
   
   
   }
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
     
        cambiarDeCliente(clientesBusqueda.get(clientesBusqueda.size() - 1).getId().toString());
        index = clientesBusqueda.size();
        jTextFieldNumCliente.setText(index.toString());
        
  
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
      
        cambiarDeCliente(clientesBusqueda.get(0).getId().toString());
        index = 1;
       jTextFieldNumCliente.setText(index.toString());
  
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         if (index < clientesBusqueda.size()) {
            cambiarDeCliente(clientesBusqueda.get(index).getId().toString());
            index++;
        }
        jTextFieldNumCliente.setText(index.toString());
      
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        if (index > 0) {
            index--;
            cambiarDeCliente(clientesBusqueda.get(index).getId().toString());

        }
        jTextFieldNumCliente.setText(index.toString());
  
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextFieldNumClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNumClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNumClienteActionPerformed

    private void jTextFieldRISActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRISActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRISActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        seleccion = SeleccionHorariosClientesFrame.getInstance();
        seleccion.setVisible(true);
        seleccion.rellenarTextFieldsFestivosSemanales();
        seleccion.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_jButton9ActionPerformed

    public void setSeleccionHorarioNull () {
        seleccion = null; 
    }
    
    private void jButtonEliminarTarifaPorTiempoDeRespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarTarifaPorTiempoDeRespuestaActionPerformed
        try {
            TarifaPorTiempoDeRespuesta t = new TarifaPorTiempoDeRespuesta();
            t.setId(tablaTR.getModel().getListaTarifas().get(tablaTR.getSelectedRow()).getId());
            EliminarTarifaPorTiempoDeRespuesta eliminar = new EliminarTarifaPorTiempoDeRespuesta(t);
            eliminar.eliminar();
            tablaTR.actualizar();
        } catch (Exception ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonEliminarTarifaPorTiempoDeRespuestaActionPerformed

    private void jButtonAsociarServicioEspecial1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAsociarServicioEspecial1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAsociarServicioEspecial1ActionPerformed

    private void jButtonNuevaTarifaRecorridosyKm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevaTarifaRecorridosyKm1ActionPerformed
        try {
            RecorridosyKm r = new RecorridosyKm();
            r.setId(tabla.getModel().getListaMunicipio().get(tabla.getSelectedRow()).getId());
             EliminarRecorridosyKm eliminar = new EliminarRecorridosyKm(r);
            eliminar.eliminar();
            tabla.actualizar();
        } catch (Exception ex) {
            Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }//GEN-LAST:event_jButtonNuevaTarifaRecorridosyKm1ActionPerformed

    private void jCheckBoxEdicionTarifasTiempoRespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEdicionTarifasTiempoRespuestaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxEdicionTarifasTiempoRespuestaActionPerformed

    
   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FichaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FichaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FichaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FichaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FichaCliente.getInstance(4300170).setVisible(true);
                            
                            } catch (Exception ex) {
                    Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imagenCliente;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonAsociarServicioEspecial;
    private javax.swing.JButton jButtonAsociarServicioEspecial1;
    private javax.swing.JButton jButtonEliminarTarifaPorTiempoDeRespuesta;
    private javax.swing.JButton jButtonNuevaTarifaPorTiempoDeRespuesta;
    private javax.swing.JButton jButtonNuevaTarifaRecorridosyKm;
    private javax.swing.JButton jButtonNuevaTarifaRecorridosyKm1;
    private javax.swing.JCheckBox jCheckBoxClientesDeBaja;
    private javax.swing.JCheckBox jCheckBoxEdicion;
    private javax.swing.JCheckBox jCheckBoxEdicionRecorridos;
    private javax.swing.JCheckBox jCheckBoxEdicionServiciosEspeciales;
    private javax.swing.JCheckBox jCheckBoxEdicionTarifasTiempoRespuesta;
    private javax.swing.JCheckBox jCheckBoxEdicionTarifasTiempoRespuesta1;
    private javax.swing.JCheckBox jCheckBoxMuestraSupNocturno;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelCodigoCliente;
    private javax.swing.JLabel jLabelNombreCliente;
    private javax.swing.JLabel jLabelNumTotalClientes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelBusquedaClientes;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPaneContenedorServicioEspeciales;
    private javax.swing.JScrollPane jScrollPaneNocturno;
    private javax.swing.JScrollPane jScrollPaneTablaServiciosEspeciales;
    private javax.swing.JScrollPane jScrollPaneTarifas;
    private javax.swing.JScrollPane jScrollPaneTarifasNocturno;
    private javax.swing.JScrollPane jScrollPaneTiposDeTarifa;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldNumCliente;
    private javax.swing.JTextField jTextFieldNumDiasCoberturaBase;
    private javax.swing.JTextField jTextFieldPorcentajeSuplementoNocturno;
    private javax.swing.JTextField jTextFieldRIS;
    private javax.swing.JTextField jTextFieldRadioUrbano;
    private javax.swing.JTextField jTextFieldUmbral;
    private javax.swing.JScrollPane panelContenedorTabla;
    private javax.swing.JScrollPane panelScrollTablaTarifas;
    // End of variables declaration//GEN-END:variables
}

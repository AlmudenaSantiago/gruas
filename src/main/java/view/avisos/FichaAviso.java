
package view.avisos;

import DatosPrecargados.SingletonAverias;
import DatosPrecargados.SingletonBasesOperativas;
import DatosPrecargados.SingletonClientes;
import DatosPrecargados.SingletonColores;
import DatosPrecargados.SingletonGruas;
import DatosPrecargados.SingletonGruistas;
import DatosPrecargados.SingletonMarcas;
import DatosPrecargados.SingletonMunicipios;
import DatosPrecargados.SingletonNumRespuesta;
import DatosPrecargados.SingletonSoluciones;
import DatosPrecargados.SingletonTiposTarifas;
import command.averias.CargarPlusesCommand;
import command.avisos.CargarAvisosCommand;
import command.basesOperativas.CargarBasesOperativasCommand;
import command.clientes.CargarClientesCommand;
import command.tarifas.CargarTarifasCommand;
import command.tarifas.CargarTarifasNocturnoCommand;
import command.vehiculos.CargarModelosCommand;
import command.vehiculos.CargarVehiculosCommand;
import datechooser.model.exeptions.IncompatibleDataExeption;
import datechooser.model.multiple.PeriodSet;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.FocusManager;
import javax.swing.JOptionPane;
import model.Aviso;
import model.Cliente;
import model.Distancia;
import model.Tarifas.Tarifa;
import model.Tarifas.TarifaNocturno;
import model.Tarifas.TipoDeTarifa;
import model.averiasYSoluciones.Averia;
import model.averiasYSoluciones.NumRespuesta;
import model.averiasYSoluciones.Plus;
import model.averiasYSoluciones.Solucion;
import model.basesOperativas.BaseOperativa;
import model.vehiculos.ColorVehiculo;
import model.vehiculos.Marca;
import model.vehiculos.Modelo;
import model.vehiculos.Vehiculo;
import process.CRUDAvisos.ModificarAviso;
import process.CRUDDistancia.ConsultarDistancias;
import process.cargador.CargadorListaBasesOperativas;
import process.cargador.CargadorListaCliente;
import process.cargador.averias.CargadorListaPlus;
import process.cargador.avisos.CargadorListaAvisos;
import process.cargador.tarifas.CargadorListaTarifas;
import process.cargador.tarifas.CargadorListaTarifasNocturno;
import process.cargador.vehiculos.CargadorListaModelo;
import process.cargador.vehiculos.CargadorListaVehiculo;
import process.parser.averias.PlusParserJson;
import process.parser.avisos.AvisoParserJson;
import process.parser.basesOperativas.BaseOperativaParserJson;
import process.parser.clientes.ClienteParserJson;
import process.parser.tarifas.TarifaNocturnoParserJson;
import process.parser.tarifas.TarifaParserJson;
import process.parser.vehiculos.ModeloParserJson;
import process.parser.vehiculos.VehiculoParserJson;
import view.clientes.CRUD_ClientesFrame;
import view.clientes.FichaClienteFrame;
import view.fichaCliente.FichaCliente;
import view.fichaCliente.FichaClienteModelo;

import view.fichaCliente.SingletonCliente;
import view.fichasVehiculos.FichaVehiculo;

/**
 *
 * @author gruasjoseantonio
 */
public class FichaAviso extends javax.swing.JPanel {

    
    List<Marca> listaMarcas;
    List<ColorVehiculo> listaColores;
    List<Modelo> listaModelos;
    String[] listaComboMarcas;
    String[] listaComboModelos;
    List<Cliente> listaClientes;
    TextFieldBusqueda gtextfieldMunicipiosLocalizacion;
    TextFieldBusqueda gtextfieldMunicipiosDestino;
    TextFieldBusqueda gtextfieldIdCliente;

    TextFieldBusqueda gtextfieldMarcas;
    TextFieldBusqueda gtextfieldModelos;
    TextFieldBusqueda gtextfieldColores;
    TextFieldBusqueda gtextfieldConductor;
    TextFieldBusqueda gtextfieldGruas;
    HashMap<Object, Object> hashContador;
    HashMap<Object, Object> hashContadorMarcas;
Component unComponente;
    Aviso aviso;
    
  
    HashMap<Object, Object> hashContadorModelos ;
    HashMap<String, Integer> hashContadorIdModelo  ;
     
    private String tieneElFoco = "asignacion";
    
    
    private static FichaAviso instance;
    
    
    public static FichaAviso getInstance() { 
        if (instance == null) {
            instance = new FichaAviso();
        }
        return instance;
    }
    
    
    private FichaAviso() {
       
        initComponents();
        averia.setVisible(false);
        comboAverias.setVisible(false);
        solucion.setVisible(false);
        comboSoluciones.setVisible(false);
        numRespuesta.setVisible(false);
        comboNumRespuesta.setVisible(false);
        plus.setVisible(false);
        comboPlus.setVisible(false);
        
        
        inicializarEtiquetasHoras();
        
        
        
        
         try {
            inicializarComboClientes();
        } catch (Exception ex) {
            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            inicializarComboGruistas();
        } catch (IOException ex) {
            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            inicializarComboGruas();
        } catch (IOException ex) {
            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            inicializarComboMarcas();
        } catch (IOException ex) {
            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
        }
        inicializarComboModelos();
        try {
            inicializarComboColores();
        } catch (IOException ex) {
            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            llenarComboTipoDeServicio();
        } catch (IOException ex) {
            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
        }
     
       try {
            inicializarCombosMunicipios();
        } catch (Exception ex) {
            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cargarAviso(1);
      
        
        if (aviso.getIdCliente().equals(4300170)) {
         
            averia.setVisible(true);
            comboAverias.setVisible(true);
            solucion.setVisible(true);
            comboSoluciones.setVisible(true);
            numRespuesta.setVisible(true);
            comboNumRespuesta.setVisible(true);
                           
       
        }
        
        if (!aviso.getIdCliente().equals(0)) {
                try {
                   llenarComboDesdeBase();
               } catch (IOException ex) {
                   Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
               }


                try {
                   llenarComboBases();
               } catch (IOException ex) {
                   Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
               }
        }
        
        
        setTotalListeners();
       
    }
    
    
    public void setTotalListeners() {
        setListeners();
        setListenerComboMarcas();
        setListenerComboModelos();
        setListenerComboColores();
        setListenerComboGruas();
        setListenerComboGruistas();
        setListenersCombosMuninicipios();
        setListenersComboClientes();
        //setListenerComboAverias();
        // setListenerComboSoluciones();
        // setListenerComboNumRespuesta();
        setListenerComboTiposTarifas();
        setListenersFechas();
        setListenerComboBases();
    
    
    }
     public void inicializarComboModelos() {
        gtextfieldModelos = new TextFieldBusqueda(0, 0, true);
        gtextfieldModelos.setWidthPopupPanel(200);
        gtextfieldModelos.setHeightPopupPanel(200);
        gtextfieldModelos.setVisible(true);
        jPanelModelo.setLayout(new BorderLayout());
        jPanelModelo.add(gtextfieldModelos);

    }

    public void inicializarComboMarcas() throws IOException {
        gtextfieldMarcas = new TextFieldBusqueda(0, 0, true);
        gtextfieldMarcas.setWidthPopupPanel(200);
        gtextfieldMarcas.setHeightPopupPanel(200);
        gtextfieldMarcas.setVisible(true);

        jPanelMarca.setLayout(new BorderLayout());
        jPanelMarca.add(gtextfieldMarcas);

    }
    
    
  
    
     private void dateChooserComboFechaAsignacionOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {                                                            
        try {
            
              String fecha = new SimpleDateFormat("yyyy-MM-dd").format(dateChooserAsignacion.getSelectedDate().getTime());
              String horaMasFecha =  fecha + " " + horaAsignacion.getText();
                  
              ModificarAviso modificar = new ModificarAviso(aviso.getId(), "fechaAsignacion", horaMasFecha);
              List<Aviso> aviso1 = modificar.modificar();
              actualizarAviso(aviso1.get(0));
                          
        } catch (IOException ex) {
            Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      

     
      private void dateChooserComboFechaRealizacionOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {                                                            
        try {
          
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(dateChooserCombo1.getSelectedDate().getTime());
            String horaMasFecha =  fecha + " " + horaRealizar.getText().trim() + ":00";
          
            ModificarAviso modificar = new ModificarAviso(aviso.getId(), "fechaRealizacion", horaMasFecha);
            List<Aviso> aviso1 = modificar.modificar();
            actualizarAviso(aviso1.get(0));
                          
        } catch (IOException ex) {
            Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
     
    
    
    public void setListenersFechas(){

     dateChooserAsignacion.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            @Override
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dateChooserComboFechaAsignacionOnSelectionChange(evt);
            }
        });

    dateChooserAsignacion.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                   tieneElFoco = "asignacion";
                 }

            @Override
            public void focusLost(FocusEvent e) {
             //horaRealizar.requestFocus();
               
            }
        });
      dateChooserCombo1.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            @Override
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dateChooserComboFechaRealizacionOnSelectionChange(evt);
            }
        });
      dateChooserCombo1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                   tieneElFoco = "realizar";
                 }

            @Override
            public void focusLost(FocusEvent e) {
             //horaRealizar.requestFocus();
               
            }
        });
    
     
       horaAsignacion.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "horaAsignacion";
                 }

            @Override
            public void focusLost(FocusEvent e) {
                //dateChooserCombo1.requestFocus();
                try {
                   //if (!aviso.getModelo().equals(gtextfieldModelos.getText()) && !gtextfieldModelos.getText().equals("")) {
                   SimpleDateFormat curFormaterFecha = new SimpleDateFormat("yyyy-MM-dd");
                
                   String fecha = curFormaterFecha.format(dateChooserAsignacion.getSelectedDate().getTime());
                   
                   // FALTA COMPROBAR QUE LA HORA SEA CORRECTA
                   String horaMasFecha =  fecha + " " + horaAsignacion.getText();
                   
                   ModificarAviso modificar = new ModificarAviso(aviso.getId(),"fechaAsignacion", horaMasFecha);
                   modificar.modificar();
                   List<Aviso> aviso1 = modificar.modificar();
                   actualizarAviso(aviso1.get(0));
                  // }
                    } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });

        horaRealizar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "horaRealizar";
             }

            @Override
            public void focusLost(FocusEvent e) {
                //comboBase.requestFocus();
               try {
                   // FALTA COMPROBNAR SI HAY CAMBIO PARA MANDAR A ACTUALIZAR O NO
               // if (!aviso.getFechaRealizacion().equals(horaRealizar.getText()) && !horaRealizar.getText().equals("")) {
                   SimpleDateFormat curFormaterFecha = new SimpleDateFormat("yyyy-MM-dd");
                
                   String fecha = curFormaterFecha.format(dateChooserCombo1.getSelectedDate().getTime());
                   
                   // FALTA COMPROBAR QUE LA HORA SEA CORRECTA
                   String horaMasFecha =  fecha + " " + horaRealizar.getText().trim() + ":00";
                   
                   ModificarAviso modificar = new ModificarAviso(aviso.getId(),"fechaRealizacion", horaMasFecha);
                   modificar.modificar();
                   List<Aviso> aviso1 = modificar.modificar();
                   actualizarAviso(aviso1.get(0));
                  // }
                    } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });



   } 

    public void setListenerComboMarcas() {

       
        for (int it = 0; it < SingletonMarcas.getInstance().getLista().size(); it++) {
           gtextfieldMarcas.getDataList().add(SingletonMarcas.getInstance().getLista().get(it).getMarca());

        }

        gtextfieldMarcas.getjTable().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                  tieneElFoco = "marcas";
            }

            @Override
            public void focusLost(FocusEvent e) {
                  gtextfieldColores.requestFocus();
                try {
                
                       if (!aviso.getIdMarca().equals(SingletonMarcas.getInstance().getHash().get(gtextfieldMarcas.getText()))) {
                            if (SingletonMarcas.getInstance().getHash().get(gtextfieldMarcas.getText()) != null) {
                                    // CUANDO ACTUALIZAMOS UNA MARCA TAMBIEN ACTUALIZAMOS EL MODELO PARA QUE NO HAYA INCONSISTENCIAS ENTRE MARCAS Y MODELOS. POR DEFECTO, AL PRIMERO DE ESA MARCA.
                                    // esta accion la realiza el servidor
                                    ModificarAviso modificar = new ModificarAviso(aviso.getId(), "marca", SingletonMarcas.getInstance().getHash().get(gtextfieldMarcas.getText()).toString());
                                    List<Aviso> aviso1 = modificar.modificar();
                                    actualizarAviso(aviso1.get(0));
                                    CargarModelosCommand cargar = new CargarModelosCommand(new CargadorListaModelo(), new ModeloParserJson());
                                    listaModelos = cargar.executePorIdMarcaPadre(aviso.getIdMarca());
                                    llenarComboModelos(listaModelos);
                                    // gtextfieldColores.requestFocus();        
                          
                            }
                      }

                    
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    
   /* public void setListenerComboAverias() {
    
        comboAverias.addItemListener(new ItemListener() {
             @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                 try {
                     ModificarAviso modificar = new ModificarAviso(aviso.getId(), "averia", SingletonAverias.getInstance().getLista().get(comboAverias.getSelectedIndex()).getId().toString());
                     List<Aviso> aviso1 = modificar.modificar();
                     actualizarAviso(aviso1.get(0));
                      tieneElFoco = "averia";
                 } catch (IOException ex) {
                     Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                 }
                }
            }

          }   
        );
        
       
      
    }*/
    
    
     public void setListenerComboTiposTarifas() {
    
        comboTipoDeServicio.addItemListener(new ItemListener() {
             @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                 try {
                     ModificarAviso modificar = new ModificarAviso(aviso.getId(), "servicioTipo", SingletonTiposTarifas.getInstance().getLista().get(comboTipoDeServicio.getSelectedIndex()).getId().toString());
                     List<Aviso> aviso1 = modificar.modificar();
                     actualizarAviso(aviso1.get(0));
                     
                     
                 } catch (IOException ex) {
                     Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                 }
                }
            }

          }   
        );
        
       comboTipoDeServicio.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                    tieneElFoco = "servicioTipo";
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
      
    }
    
    
    
    public void setListenerComboSoluciones() {
    
        comboSoluciones.addItemListener(new ItemListener() {
             @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                 try {
                     ModificarAviso modificar = new ModificarAviso(aviso.getId(), "solucion", SingletonSoluciones.getInstance().getLista().get(comboSoluciones.getSelectedIndex()).getId().toString());
                     List<Aviso> aviso1 = modificar.modificar();
                     actualizarAviso(aviso1.get(0));
                 } catch (IOException ex) {
                     Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                 }
                }
            }

          }   
        );
        
       comboSoluciones.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                    tieneElFoco = "solucion";
            }

            @Override
            public void focusLost(FocusEvent e) {
               //  comboNumRespuesta.requestFocus();
            }
        });
        
   
    }
    
    
    
      public void setListenerComboNumRespuesta() {
    
        comboNumRespuesta.addItemListener(new ItemListener() {
             @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                 try {
                     ModificarAviso modificar = new ModificarAviso(aviso.getId(), "numRespuesta", SingletonNumRespuesta.getInstance().getLista().get(comboNumRespuesta.getSelectedIndex()).getId().toString());
                     List<Aviso> aviso1 = modificar.modificar();
                     actualizarAviso(aviso1.get(0));
                 } catch (IOException ex) {
                     Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                 }
                }
            }

          }   
        );
        
       comboNumRespuesta.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                    tieneElFoco = "numRespuesta";
            }

            @Override
            public void focusLost(FocusEvent e) {
               // textFieldkmsGrua.requestFocus();
            }
        });
        
      
    }
    
    
    
    
    public void llenarComboModelos(List<Modelo> listaModelos) throws IOException {

        hashContadorModelos = new HashMap<>();
        hashContadorIdModelo  = new HashMap<>();
         gtextfieldModelos.getDataList().removeAll(gtextfieldModelos.getDataList());
        for (int it = 0; it < listaModelos.size(); it++) {
            hashContadorModelos.put(listaModelos.get(it).getId(), it);
            hashContadorIdModelo.put(listaModelos.get(it).getModelo(),listaModelos.get(it).getId());
       
            gtextfieldModelos.getDataList().add(listaModelos.get(it).getModelo());

        }
        

    }

    
    public void setListenerComboModelos() {
       gtextfieldModelos.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "modelos";
                //gtextfieldModelos.getjTable().setVisible(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
               try {
                 
                   if (!aviso.getModelo().equals(gtextfieldModelos.getText()) && !gtextfieldModelos.getText().equals("")) {
                      if (hashContadorIdModelo.get(gtextfieldModelos.getText()) != null) { 
                        ModificarAviso modificar = new ModificarAviso(aviso.getId(),"modelo",hashContadorIdModelo.get(gtextfieldModelos.getText()).toString());
                        modificar.modificar();
                        List<Aviso> aviso1 = modificar.modificar();
                        actualizarAviso(aviso1.get(0));
                        
                        
                        }
                   }
                  
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });

    
    }
    
    
    private void inicializarEtiquetasHoras() {
        // hora actual
        horaAsignacion.setText(new GregorianCalendar().get(Calendar.HOUR_OF_DAY) + ":" + new GregorianCalendar().get(Calendar.MINUTE));
        horaRealizar.setText(new GregorianCalendar().get(Calendar.HOUR_OF_DAY) + ":" + new GregorianCalendar().get(Calendar.MINUTE));
    }

    public void llenarComboAverias() throws IOException {
        List<Averia> listaAverias = SingletonAverias.getInstance().getLista();
        Iterator<Averia> it = listaAverias.iterator();
        comboAverias.removeAllItems();
        while (it.hasNext()) {
            Averia averiaIte= it.next();
            comboAverias.addItem(averiaIte.getCodigo() + " (" +  averiaIte.getAveria() + ")");
         }

    }

    
      public void llenarComboDesdeBase() throws IOException {
        CargarBasesOperativasCommand cargar = new CargarBasesOperativasCommand(new CargadorListaBasesOperativas(), new BaseOperativaParserJson());
        List<BaseOperativa> lista = cargar.executeLista(aviso.getIdCliente());
        Iterator<BaseOperativa> it = lista.iterator();
     
        comboBases.addItem("");
        while (it.hasNext()) {
            comboBases.addItem(it.next().getBaseOperativa());
        
        }

    }
      
      
    public void llenarComboBases() throws IOException {
        CargarBasesOperativasCommand cargar = new CargarBasesOperativasCommand(new CargadorListaBasesOperativas(), new BaseOperativaParserJson());
        List<BaseOperativa> lista = cargar.executeLista(aviso.getIdCliente());
        Iterator<BaseOperativa> it = lista.iterator();
        while (it.hasNext()) {
            comboBase.addItem(it.next().getBaseOperativa());
        }

    }
    
    
    
    public void inicializarComboClientes() throws IOException, Exception {

        gtextfieldIdCliente = new TextFieldBusqueda(0, 0, true);
        gtextfieldIdCliente.setWidthPopupPanel(200);
        gtextfieldIdCliente.setHeightPopupPanel(200);
        gtextfieldIdCliente.setVisible(true);
        jPanelClientes.setLayout(new BorderLayout());
        jPanelClientes.add(gtextfieldIdCliente);


    }

    public void inicializarCombosMunicipios() throws IOException, Exception {

        gtextfieldMunicipiosLocalizacion = new TextFieldBusqueda(0, 0, true);
        gtextfieldMunicipiosLocalizacion.setWidthPopupPanel(200);
        gtextfieldMunicipiosLocalizacion.setHeightPopupPanel(200);
        gtextfieldMunicipiosLocalizacion.setVisible(true);

        gtextfieldMunicipiosDestino = new TextFieldBusqueda(0, 0, true);
        gtextfieldMunicipiosDestino.setWidthPopupPanel(200);
        gtextfieldMunicipiosDestino.setHeightPopupPanel(200);
        gtextfieldMunicipiosDestino.setVisible(true);
            
        jPanel6.add(gtextfieldMunicipiosLocalizacion);

        jPanel7.add(gtextfieldMunicipiosDestino);


    }

    public void setListenersComboClientes() {

        HashMap<Object, Object> hashContador = new HashMap<>();
        List<Cliente> clientesBusqueda = null;
        try {
            clientesBusqueda = new CargarClientesCommand(new CargadorListaCliente(), new ClienteParserJson()).executeLista();
            
        } catch (Exception ex) {
            Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int it = 0; it < clientesBusqueda.size(); it++) {
            hashContador.put(clientesBusqueda.get(it).getId(), it);
            String st = clientesBusqueda.get(it).getId().toString() + "   (" + clientesBusqueda.get(it).getNombre() + ")";
            gtextfieldIdCliente.getDataList().add(st);

        }
    
        gtextfieldIdCliente.getjTable().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "clientes";
            }

            @Override
            public void focusLost(FocusEvent e) {
             //  jTextFieldExpediente.requestFocus();
                if (!aviso.getIdCliente().equals(SingletonClientes.getInstance().getLista().get(gtextfieldIdCliente.getFilaSeleccionada()).getId())) {
                        try {

                            ModificarAviso modificar = new ModificarAviso(aviso.getId(), "idCliente", SingletonClientes.getInstance().getLista().get(gtextfieldIdCliente.getFilaSeleccionada()).getId().toString());
                            List<Aviso> aviso1 = modificar.modificar();
                            actualizarAviso(aviso1.get(0));

                        } catch (IOException ex) {
                            Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        try {
                            comboBase.removeAllItems();
                            llenarComboBases();
                        } catch (IOException ex) {
                            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            comboBases.removeAllItems();
                            llenarComboDesdeBase();
                        } catch (IOException ex) {
                            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
                        }
                } 
             }
        });

    }

    
     public void llenarComboPlus() throws IOException {
        List<Plus> listaPluses;
        listaPluses = new CargarPlusesCommand(CargadorListaPlus.getInstance(), new PlusParserJson()).executeLista();
        Iterator<Plus> it = listaPluses.iterator();
        while (it.hasNext()) {
            Plus plusIte= it.next();
            comboPlus.addItem(plusIte.getPlus());
        }

    }
    
    public void setListenersCombosMuninicipios() {
          for (int it = 0; it < SingletonMunicipios.getInstance().getLista().size(); it++) {
            gtextfieldMunicipiosLocalizacion.getDataList().add(SingletonMunicipios.getInstance().getLista().get(it).getMunicipio());
            gtextfieldMunicipiosDestino.getDataList().add(SingletonMunicipios.getInstance().getLista().get(it).getMunicipio());

        }
   
        gtextfieldMunicipiosLocalizacion.getjTable().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "municipioLocalizacion";

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    // falta comprobar que sea distinto
                    ModificarAviso modificar = new ModificarAviso(aviso.getId(), "municipioLocalizacion", SingletonMunicipios.getInstance().getHashContadorMunicipios().get(gtextfieldMunicipiosLocalizacion.getText()).toString());
                    List<Aviso> aviso1 = modificar.modificar();
                    actualizarAviso(aviso1.get(0));
             
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        gtextfieldMunicipiosDestino.getjTable().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "municipioDestino";
            }

            @Override
            public void focusLost(FocusEvent e) {
              //  jTextFieldDireccionDestino.requestFocus();
                try {
                    
                    ModificarAviso modificar = new ModificarAviso(aviso.getId(), "municipioDestino", SingletonMunicipios.getInstance().getHashContadorMunicipios().get(gtextfieldMunicipiosDestino.getText()).toString());
                    List<Aviso> aviso1 = modificar.modificar();
                    actualizarAviso(aviso1.get(0));
                 } catch (Exception ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    public void consultarDistancia(Integer idOrigen, Integer idDestino) {
        
        Distancia distancia = new Distancia();
        distancia.setIdDestino(idDestino);
        distancia.setIdOrigen(idOrigen);
        ConsultarDistancias consulta = new ConsultarDistancias(distancia) ;
        try {
           distancia =  consulta.consultar();
        } catch (Exception ex) {
            Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(distancia.getKms());
        
    }
    
    

    
    
    public void actualizarAviso(Aviso aviso) {

        this.aviso = aviso;
        
        llenarCamposAviso();
        situarFoco();
        llenarImportes();
        

    }

    
    public void cargarAviso(Integer id) {

        CargarAvisosCommand cargador = new CargarAvisosCommand(CargadorListaAvisos.getInstance(), new AvisoParserJson());
        List<Aviso> listaAvisos;
     
        try {

            listaAvisos = cargador.executeListaPorId(id);
            this.aviso = listaAvisos.get(0);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al consultar el aviso" + id);
            Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
        llenarCamposAviso();
        llenarImportes();
        CargarModelosCommand cargar = new CargarModelosCommand(new CargadorListaModelo(), new ModeloParserJson());
        System.out.println("antes de traer modelos" + aviso.getIdMarca());
          
       
                    try {
                     listaModelos = cargar.execute();
                   } catch (IOException ex) {
                     Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
                   } 
        
        
        
        try {                    
            llenarComboModelos(listaModelos);
        } catch (IOException ex) {
            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
        }
        

      
    }
    
    
    public void llenarFechasYHoras() {
    
        //************************** ASIGNACION ********************************//
      
        String dateStrAsignacion = aviso.getFechaAsignacion();
        SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       
        Date dateObjAsignacion = new Date();
     
        Date dateHora = new Date();  
            try {
                dateObjAsignacion = curFormater.parse(dateStrAsignacion);
         
            } catch (ParseException ex) {
                Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        
           
        Calendar fechaAsignacion = Calendar.getInstance();
        fechaAsignacion.setTime(dateObjAsignacion);
       
        PeriodSet periodo = new PeriodSet();
        periodo.add(fechaAsignacion);
        try {
            dateChooserAsignacion.setDefaultPeriods(periodo);
        } catch (IncompatibleDataExeption ex) {
            Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        Integer horasAsignacion =  fechaAsignacion.get(Calendar.HOUR_OF_DAY);
        Integer minutosAsignacion = fechaAsignacion.get(Calendar.MINUTE);
       
        String hora;
        String minutos;
        if(horasAsignacion<10){
              hora = "0" + horasAsignacion.toString();
        } else {
             hora = horasAsignacion.toString();
        }
        if (minutosAsignacion<10) {
             minutos = "0" + minutosAsignacion.toString();
        } else {
             minutos = minutosAsignacion.toString();
        }
        
        
        horaAsignacion.setText(hora +":"+ minutos);
      
        
        //***********************************  REALIZACION **************************//
         String dateStrRealizacion = aviso.getFechaRealizacion();
         Date dateObjRealizacion = new Date();
          
            try {
                dateObjRealizacion = curFormater.parse(dateStrRealizacion);
            } catch (ParseException ex) {
                Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        Calendar fechaRealizacion = Calendar.getInstance();
        fechaRealizacion.setTime(dateObjRealizacion);
        PeriodSet periodo2 = new PeriodSet();
      
        periodo2.add(fechaRealizacion);
        try {
            dateChooserCombo1.setDefaultPeriods(periodo2);
        } catch (IncompatibleDataExeption ex) {
            Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        String horaR;
        String minutosR;
       Integer horasRealizacion = fechaRealizacion.get(Calendar.HOUR_OF_DAY);
       Integer minutosRealizacion = fechaRealizacion.get(Calendar.MINUTE);
          
        if(horasRealizacion<10){
              horaR = "0" + horasRealizacion.toString();
        } else {
             horaR = horasRealizacion.toString();
        }
        if (minutosRealizacion<10) {
             minutosR = "0" + minutosRealizacion.toString();
             
              System.out.println(minutosRealizacion);
     
        } else {
             minutosR = minutosRealizacion.toString();
        }
        
              System.out.println(minutosRealizacion);
     
        horaRealizar.setText(horaR + ":" + minutosR);
       

     //***************************************************************************//
        
    
    }

   
    
    
    public void llenarCamposAviso() {
        
        llenarFechasYHoras();
      
        jTextFieldNombre.setText(aviso.getNombre());
        jTextFieldTelefono.setText(aviso.getTelefono());

        jFormattedTextFieldHoraLocalizado.setText(aviso.getHoraLocalizado().substring(11,aviso.getHoraSalida().length()-3));
        jFormattedTextFieldHoraFinalizado.setText(aviso.getHoraFinalizado().substring(11,aviso.getHoraSalida().length()-3));
        jFormattedTextFieldHoraSalida.setText(aviso.getHoraSalida().substring(11,aviso.getHoraSalida().length()-3));
        String st = aviso.getIdCliente().toString() + "   (" + aviso.getCliente() + ")";

        gtextfieldIdCliente.setText(st);

        gtextfieldMarcas.setText(aviso.getMarca());
        gtextfieldModelos.setText(aviso.getModelo());
        gtextfieldColores.setText(aviso.getColor());
        
        jTextFieldRescate.setText(aviso.getRescate().toString());
        jTextFieldMatricula.setText(aviso.getMatricula());
        jTextFieldBastidor.setText(aviso.getBastidor().toString());
        jTextFieldKms.setText(aviso.getKms().toString());
        jTextFieldIdAviso.setText(aviso.getId().toString());

        jTextFieldPoliza.setText(aviso.getPoliza().toString());
        jTextFieldDireccionLocalizacion.setText(aviso.getDireccionLocalizacion());
        jTextFieldDireccionDestino.setText(aviso.getDireccionDestino());

        jTextFieldExpediente.setText(aviso.getExpediente());
     
        gtextfieldGruas.setText(aviso.getGrua().toString());
        gtextfieldMunicipiosLocalizacion.setText(aviso.getMunicipioLocalizacion());
        gtextfieldMunicipiosLocalizacion.popup.setVisible(false);
       
        gtextfieldMunicipiosDestino.setText(aviso.getMunicipioDestino());
         gtextfieldMunicipiosDestino.popup.setVisible(false);
        jTextFieldTalon.setText(aviso.getTalon().toString());
       
        gtextfieldConductor.setText(aviso.getConductorGrua());
        gtextfieldConductor.popup.setVisible(false);
       
        if (aviso.getIdCliente().equals(4300230)) {
              comboPlus.setVisible(true);
              plus.setVisible(true);
                           
       } else {
              comboPlus.setVisible(false);
              plus.setVisible(false);
        }
                   
        if (aviso.getIdCliente().equals(4300170)) {
                            
                            averia.setVisible(true);
                            comboAverias.setVisible(true);
                            solucion.setVisible(true);
                            comboSoluciones.setVisible(true);
                            numRespuesta.setVisible(true);
                            comboNumRespuesta.setVisible(true);
                            
                            
          
                 } else {
                 
                            averia.setVisible(false);
                            comboAverias.setVisible(false);
                            solucion.setVisible(false);
                            comboSoluciones.setVisible(false);
                            numRespuesta.setVisible(false);
                            comboNumRespuesta.setVisible(false);
                 
                   
                 }
        
        if (aviso.getIdCliente().equals(4300170)) {
            try {
                                llenarComboAverias();
                            } catch (IOException ex) {
                                Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            try {
                                llenarComboSoluciones();
                            } catch (IOException ex) {
                                Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            try {
                                llenarNumRespuesta();
                            } catch (IOException ex) {
                                Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
                            }
            for (int i = 0; i < comboNumRespuesta.getModel().getSize(); i++) {
               if (aviso.getNumRespuesta().equals(comboNumRespuesta.getItemAt(i).toString())) {
                   comboNumRespuesta.setSelectedIndex(i);
                   break;
                }
            }
             for (int i = 0; i < comboAverias.getModel().getSize(); i++) {
                    String aux = aviso.getCodigoAveria() +  " (" + aviso.getAveria()+ ")"; 
                    if (aux.equals(comboAverias.getItemAt(i).toString())) {
                        comboAverias.setSelectedIndex(i);
                        break;
                    }
            }
             
             for (int i = 0; i < comboSoluciones.getModel().getSize(); i++) {
                    if (aviso.getSolucion().equals(comboSoluciones.getItemAt(i).toString())) {
                        comboSoluciones.setSelectedIndex(i);
                        break;
                    }
            }
        }
        
        if (!aviso.getIdModelo().equals(0) && !aviso.getIdCliente().equals(0)) {
         if (aviso.getNf().equals("-")) {
                CargarTarifasCommand cargar = new CargarTarifasCommand(CargadorListaTarifas.getInstance(), new TarifaParserJson());
                 
                    try {
                                      List<Tarifa> lista = cargar.executeListaPorTipo(aviso.getIdCliente(), aviso.getIdServicioTipo());
                                      if (lista.isEmpty())  {
                                             importeDiasCustodia.setText("No definido");
                                             importeRescate.setText("No definido");
                                             importeEspera.setText("No definido");
                                      } else {
                                             importeDiasCustodia.setText(lista.get(0).getCustodia().toString() + "€");
                                             importeRescate.setText(lista.get(0).getRescate().toString() + "€");
                                             importeEspera.setText(lista.get(0).getEspera().toString() + "€");
                                      }

                   } catch (IOException ex) {
                                    Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
                   }
                                         
         } else {
                  CargarTarifasNocturnoCommand cargarn = new CargarTarifasNocturnoCommand(CargadorListaTarifasNocturno.getInstance(), new TarifaNocturnoParserJson());
                  try {
                     List<TarifaNocturno> lista = cargarn.executeListaNocturnoPorTipo(aviso.getIdCliente(), aviso.getIdServicioTipo());
                     if (lista.isEmpty())  {
                                             importeDiasCustodia.setText("No definido");
                                             importeRescate.setText("No definido");
                                             importeEspera.setText("No definido");
                                      } else {
                                             importeDiasCustodia.setText(lista.get(0).getCustodia().toString() + "€");
                                             importeRescate.setText(lista.get(0).getRescate().toString() + "€");
                                             importeEspera.setText(lista.get(0).getEspera().toString() + "€");
                                      }
                 } catch (IOException ex) {
                    Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
                 }
         }
        }
                  

       

        for (int i = 0; i < comboTipoDeServicio.getModel().getSize(); i++) {
            if (aviso.getTipotarifa().equals(comboTipoDeServicio.getItemAt(i).toString())) {
                comboTipoDeServicio.setSelectedIndex(i);
                break;
            }
        }
        
        if (aviso.getIdCliente().equals(4300230)) {
             try {
                llenarComboPlus();
            } catch (IOException ex) {
                Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int i = 0; i < comboPlus.getModel().getSize(); i++) {
            if (aviso.getPlus().equals(comboPlus.getItemAt(i).toString())) {
                comboPlus.setSelectedIndex(i);
                break;
            }
         }
        }
        
        
        boolean encontrado = false;
        for (int i = 0; i < comboBases.getModel().getSize(); i++) {
            if (aviso.getDesdeBaseNombre().equals(comboBases.getItemAt(i))) {
                comboBases.setSelectedIndex(i);
                encontrado = true;
                break;
            }
            if (encontrado = false) {
            comboBases.setSelectedIndex(0);
            }
         }
        
        

        textFieldkmsGrua.setText(aviso.getKmsGrua().toString());
        jTextFieldEspera.setText(aviso.getEspera().toString());
        jTextFieldRescate.setText(aviso.getRescate().toString());
        jTextFieldDiasCustodia.setText(aviso.getDiasCustodia().toString());
        jTextFieldNocturnoOFestivo.setText(aviso.getNf());
        jFormattedTextFieldTiempoLlegada.setText(aviso.getTiempoLlegada().toString());
        
      
   
    }

    public void inicializarComboGruas() throws IOException {

        gtextfieldGruas = new TextFieldBusqueda(0, 0, true);
        gtextfieldGruas.setWidthPopupPanel(200);
        gtextfieldGruas.setHeightPopupPanel(200);
        gtextfieldGruas.setVisible(true);

    }
    
    public void setListenerComboBases() {
        comboBases.addItemListener(new ItemListener() {
                @Override
               public void itemStateChanged(ItemEvent e) {
                   if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        ModificarAviso modificar ;
                        if (comboBases.getSelectedIndex() == 0) {
                             modificar = new ModificarAviso(aviso.getId(), "desdeBase", "");
                        } else {
                             modificar = new ModificarAviso(aviso.getId(), "desdeBase", SingletonBasesOperativas.getInstance().getLista().get(comboBases.getSelectedIndex()-1).getId().toString());
                        }
                        List<Aviso> aviso1 = modificar.modificar();
                        actualizarAviso(aviso1.get(0));
                    } catch (IOException ex) {
                        Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   }
               }

             }   
           );
        
       comboBases.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                    tieneElFoco = "desdeBase";
            }

            @Override
            public void focusLost(FocusEvent e) {
               // gtextfieldMunicipiosLocalizacion.requestFocus();
            }
        });
    
    }

    
    public void setListenerComboBase() {
        comboBase.addItemListener(new ItemListener() {
                @Override
               public void itemStateChanged(ItemEvent e) {
                   if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        ModificarAviso modificar ;
                        if (comboBase.getSelectedIndex() == 0) {
                             modificar = new ModificarAviso(aviso.getId(), "base", "");
                        } else {
                             modificar = new ModificarAviso(aviso.getId(), "base", SingletonBasesOperativas.getInstance().getLista().get(comboBases.getSelectedIndex()).getId().toString());
                        }
                        List<Aviso> aviso1 = modificar.modificar();
                        actualizarAviso(aviso1.get(0));
                    } catch (IOException ex) {
                        Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   }
               }

             }   
           );
        
       comboBase.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                    tieneElFoco = "base";
            }

            @Override
            public void focusLost(FocusEvent e) {
             //   gtextfieldIdCliente.requestFocus();
            }
        });
    
    }
    
    
    
    public void setListenerComboGruas() {

        HashMap<Object, Object> hashContadorGruas = new HashMap<>();

        for (int it = 0; it < SingletonGruas.getInstance().getLista().size(); it++) {
            hashContadorGruas.put(SingletonGruas.getInstance().getLista().get(it).getId(), it);
            gtextfieldGruas.getDataList().add(SingletonGruas.getInstance().getLista().get(it).getNumGrua().toString());

        }

        jPanel5.setLayout(new BorderLayout());
        jPanel5.setPreferredSize(new Dimension(100, 30));
        jPanel5.add(gtextfieldGruas);

        gtextfieldGruas.getjTable().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                 tieneElFoco = "grua";
            }

            @Override
            public void focusLost(FocusEvent e) {
                
                try {
                 
                    ModificarAviso modificar = new ModificarAviso(aviso.getId(), "grua", SingletonGruas.getInstance().getLista().get(gtextfieldGruas.getFilaSeleccionada()).getId().toString());
                    List<Aviso> aviso1 = modificar.modificar();
                    actualizarAviso(aviso1.get(0));
                  //  gtextfieldConductor.requestFocus();
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    public void setListeners() {

         jTextFieldTalon.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
              tieneElFoco = "talon";
            }

            @Override
            public void focusLost(FocusEvent e) {
               
                try {
                   
                    if (!jTextFieldTalon.getText().trim().equals(aviso.getMatricula()) && !jTextFieldTalon.getText().equals("")) {

                       ModificarAviso modificar = new ModificarAviso(aviso.getId(), "talon", jTextFieldTalon.getText().trim());
                       List<Aviso> aviso = modificar.modificar();
                       actualizarAviso(aviso.get(0));
                       
                                   

                    }
                   
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        
        jTextFieldMatricula.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
              tieneElFoco = "matricula";
            }

            @Override
            public void focusLost(FocusEvent e) {
                gtextfieldModelos.requestFocus();
                try {
                   
                    if (!jTextFieldMatricula.getText().trim().equals(aviso.getMatricula()) && !jTextFieldMatricula.getText().equals("")) {

                       ModificarAviso modificar = new ModificarAviso(aviso.getId(), "matricula", jTextFieldMatricula.getText().trim());
                       List<Aviso> aviso = modificar.modificar();
                       actualizarAviso(aviso.get(0));
                       CargarModelosCommand cargar = new CargarModelosCommand(new CargadorListaModelo(), new ModeloParserJson());
                       listaModelos = cargar.executePorIdMarcaPadre(aviso.get(0).getIdMarca());
                       llenarComboModelos(listaModelos);
                                   

                    }
                   
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
         jTextFieldBastidor.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                 tieneElFoco = "bastidor";
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                   // jTextFieldKms.requestFocus();
                    // falta poner la grua qeu es 
                    if (!jTextFieldBastidor.getText().trim().equals(aviso.getBastidor().toString()) && !jTextFieldBastidor.getText().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "bastidor", jTextFieldBastidor.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
                    }

                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        
      

      

        jTextFieldNombre.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                 tieneElFoco = "nombre";
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                   // jTextFieldTelefono.requestFocus();
                 
                    // falta poner la grua qeu es 
                    if (!jTextFieldNombre.getText().trim().equals(aviso.getNombre()) && !jTextFieldNombre.getText().trim().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "nombre", jTextFieldNombre.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
                    }

                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jTextFieldKms.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                         tieneElFoco = "kms";
            }

            @Override
            public void focusLost(FocusEvent e) {
                   //jTextFieldPoliza.requestFocus();
                try {
                
                    if (!jTextFieldKms.getText().trim().equals(aviso.getKms().toString()) && !jTextFieldKms.getText().trim().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "kms", jTextFieldKms.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
                    }
                    
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jTextFieldTelefono.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                 tieneElFoco = "telefono";
            }

            @Override
            public void focusLost(FocusEvent e) {
                   // jTextFieldBastidor.requestFocus();
              
                try {
                    // falta poner la grua qeu es 
                    if (!jTextFieldTelefono.getText().trim().equals(aviso.getTelefono()) && !jTextFieldTelefono.getText().trim().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "telefono", jTextFieldTelefono.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
  }
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        textFieldkmsGrua.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                     tieneElFoco = "kmsGrua";
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                  //  comboTipoDeServicio.requestFocus();
                    // 
                    // falta poner la grua qeu es 
                    if (!textFieldkmsGrua.getText().trim().equals(aviso.getKmsGrua().toString()) && !textFieldkmsGrua.getText().trim().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "kmsGrua", textFieldkmsGrua.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));

                            }
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jTextFieldPoliza.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                 tieneElFoco = "poliza";
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                 //   comboBases.requestFocus();
                    // falta poner la grua qeu es 
                    if (!jTextFieldPoliza.getText().trim().equals(aviso.getPoliza().toString()) && !jTextFieldPoliza.getText().trim().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "poliza", jTextFieldPoliza.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
 
                    
                    }
                    
                   
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        
        
       
        

        jTextFieldDireccionLocalizacion.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                 tieneElFoco = "direccionLocalizacion";
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                  //  gtextfieldMunicipiosDestino.requestFocus();
                    // falta poner la grua qeu es 
                    if (!jTextFieldDireccionLocalizacion.getText().trim().equals(aviso.getDireccionLocalizacion()) && !jTextFieldDireccionLocalizacion.getText().trim().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "direccionLocalizacion", jTextFieldDireccionLocalizacion.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));

                     }
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jTextFieldDireccionDestino.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                 tieneElFoco = "direccionDestino";
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                 //gtextfieldGruas.requestFocus();
                    // falta poner la grua qeu es 
                    if (!jTextFieldDireccionDestino.getText().trim().equals(aviso.getDireccionDestino()) && !jTextFieldDireccionDestino.getText().trim().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "direccionDestino", jTextFieldDireccionDestino.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
                     }
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jFormattedTextFieldHoraSalida.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
              tieneElFoco = "horaSalida";
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                   //  jFormattedTextFieldTiempoLlegada.requestFocus();
                    // falta poner la grua qeu es 
                    if (!jFormattedTextFieldHoraSalida.getText().trim().equals(aviso.getHoraSalida().substring(11,aviso.getHoraSalida().length()-3)) && !jFormattedTextFieldHoraSalida.getText().trim().equals("")) {
                        String horaMasFecha =  aviso.getFechaAsignacion().substring(0,11) + jFormattedTextFieldHoraSalida.getText().trim() + ":00";
                        System.out.println("fecha salida" +  horaMasFecha);
                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "horaSalida",horaMasFecha );
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
                              System.out.println("he actualizado biwn");
                     
                    }
                    
                } catch (Exception ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jFormattedTextFieldTiempoLlegada.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "tiempoLlegada";
            }
Component unComponente = FocusManager.getCurrentManager().getFocusOwner();

            @Override
            public void focusLost(FocusEvent e) {
                try {
                 //   jFormattedTextFieldHoraLocalizado.requestFocus();
                 if(aviso.getHoraLocalizado() != "0"){
                     System.out.println("pasa por aquiiiii kentakyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
                    // falta poner la grua qeu es 
                    if (!jFormattedTextFieldTiempoLlegada.getText().trim().equals(aviso.getTiempoLlegada().toString()) && !jFormattedTextFieldTiempoLlegada.getText().trim().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "tiempoLlegada", jFormattedTextFieldTiempoLlegada.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
                     }
                 }  
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jFormattedTextFieldHoraLocalizado.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "horaLocalizado";
            }

            @Override
            public void focusLost(FocusEvent e) {
                 // jFormattedTextFieldHoraFinalizado.requestFocus();
                   
                try {
                   if (!jFormattedTextFieldHoraLocalizado.getText().trim().equals(aviso.getHoraLocalizado().substring(11,aviso.getHoraLocalizado().length()-3)) && !jFormattedTextFieldHoraLocalizado.getText().trim().equals("")) {
                        String horaMasFecha =  aviso.getFechaAsignacion().substring(0,11) + jFormattedTextFieldHoraLocalizado.getText().trim() + ":00";
                        System.out.println("fecha localizado" +  horaMasFecha);
                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "horaLocalizado",horaMasFecha );
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
                    }
                  
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jFormattedTextFieldHoraFinalizado.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "horaFinalizado";
            }

            @Override
            public void focusLost(FocusEvent e) {
                /* if (aviso.getIdCliente().equals(4300170)) {
                        comboAverias.requestFocus();
                    } else {
                        textFieldkmsGrua.requestFocus();
                    }
                */
                try {
                    if (!jFormattedTextFieldHoraFinalizado.getText().trim().equals(aviso.getHoraFinalizado().substring(11,aviso.getHoraFinalizado().length()-3)) && !jFormattedTextFieldHoraFinalizado.getText().trim().equals("")) {
                        String horaMasFecha =  aviso.getFechaAsignacion().substring(0,11) + jFormattedTextFieldHoraFinalizado.getText().trim() + ":00";
                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "horaFinalizado",horaMasFecha );
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
                     }
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jTextFieldExpediente.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                 
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                       tieneElFoco = "expediente";
               
                    // falta poner la grua qeu es 
                    if (!jTextFieldExpediente.getText().trim().equals(aviso.getExpediente()) && !jTextFieldExpediente.getText().trim().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "expediente", jTextFieldExpediente.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
                      }
                   
                    
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jTextFieldEspera.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "espera";
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
               //  jTextFieldRescate.requestFocus();
                    // falta poner la grua qeu es 
                    if (!jTextFieldEspera.getText().trim().equals(aviso.getEspera().toString()) && !jTextFieldEspera.getText().trim().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "espera", jTextFieldEspera.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
                     }
                } catch (Exception ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jTextFieldRescate.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "rescate";
            }

            @Override
            public void focusLost(FocusEvent e) {
               // jTextFieldDiasCustodia.requestFocus();
                try {
                 
                   if (!jTextFieldRescate.getText().trim().equals(aviso.getRescate().toString()) && !jTextFieldRescate.getText().trim().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "rescate", jTextFieldRescate.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
                     }

                } catch (Exception ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jTextFieldDiasCustodia.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "diasCustodia";
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                 
                    // falta poner la grua qeu es 
                    if (!jTextFieldDiasCustodia.getText().trim().equals(aviso.getDiasCustodia().toString()) && !jTextFieldDiasCustodia.getText().trim().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "custodia", jTextFieldDiasCustodia.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
                     }
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jTextFieldNocturnoOFestivo.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "nf";
            }

            @Override
            public void focusLost(FocusEvent e) {
               
                 // jTextFieldEspera.requestFocus();
                try {
                 
                    // falta poner la grua qeu es 
                    if (!jTextFieldNocturnoOFestivo.getText().trim().equals(aviso.getDiasCustodia().toString()) && !jTextFieldNocturnoOFestivo.getText().trim().equals("")) {

                        ModificarAviso modificar = new ModificarAviso(aviso.getId(), "nocturnoOFestivo", jTextFieldNocturnoOFestivo.getText().trim());
                        List<Aviso> aviso = modificar.modificar();
                        actualizarAviso(aviso.get(0));
                    }
                } catch (Exception ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    

    public void inicializarComboGruistas() throws IOException {

        gtextfieldConductor = new TextFieldBusqueda(0, 0, true);
        gtextfieldConductor.setWidthPopupPanel(200);
        gtextfieldConductor.setHeightPopupPanel(200);
        jPanelConductor.setLayout(new BorderLayout());

        jPanelConductor.add(gtextfieldConductor);
        gtextfieldConductor.setVisible(true);
        
        gtextfieldConductor.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                  tieneElFoco = "conductor";
                    }

            @Override
            public void focusLost(FocusEvent e) {
               // jFormattedTextFieldHoraSalida.requestFocus();
            }
        });
         

    }

    public void setListenerComboGruistas() {

        HashMap<Object, Object> hashContadorConductor = new HashMap<>();

        for (int it = 0; it < SingletonGruistas.getInstance().getLista().size(); it++) {
            hashContadorConductor.put(SingletonGruistas.getInstance().getLista().get(it).getId(), it);
            gtextfieldConductor.getDataList().add(SingletonGruistas.getInstance().getLista().get(it).getNombre());

        }

            gtextfieldConductor.getjTable().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                  tieneElFoco = "conductor";
                    }

            @Override
            public void focusLost(FocusEvent e) {
               try {
                 
                if (!aviso.getConductorGrua().equals(gtextfieldConductor.getText()) && !gtextfieldConductor.getText().equals("")) {
                    ModificarAviso modificar = new ModificarAviso(aviso.getId(), "gruista", SingletonGruistas.getInstance().getLista().get(gtextfieldConductor.getFilaSeleccionada()).getId().toString());
                    List<Aviso> aviso =   modificar.modificar();
                    actualizarAviso(aviso.get(0));
                } 
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
         
    }

    public void llenarComboSoluciones() throws IOException {
        List<Solucion> listaSoluciones = SingletonSoluciones.getInstance().getLista();
        comboSoluciones.removeAllItems();
        Iterator<Solucion> it = listaSoluciones.iterator();
        while (it.hasNext()) {
            comboSoluciones.addItem(it.next().getSolucion());
        }

    }

    
     public void llenarNumRespuesta() throws IOException {
       List<NumRespuesta> lista = SingletonNumRespuesta.getInstance().getLista();
        comboNumRespuesta.removeAllItems();
        Iterator<NumRespuesta> it = lista.iterator();
        while (it.hasNext()) {
            comboNumRespuesta.addItem(it.next().getNumRespuesta());
        }

    }
    
    public void llenarComboTipoDeServicio() throws IOException {
        Iterator<TipoDeTarifa> it =  SingletonTiposTarifas.getInstance().getLista().iterator();
        while (it.hasNext()) {
            comboTipoDeServicio.addItem(it.next().getTipo());
        }

    }

    public void inicializarComboColores() throws IOException {

        gtextfieldColores = new TextFieldBusqueda(0, 0, true);
        gtextfieldColores.setWidthPopupPanel(200);
        gtextfieldColores.setHeightPopupPanel(200);
        jPanelColor.setLayout(new BorderLayout());

        jPanelColor.add(gtextfieldColores);
        jPanelColor.setVisible(true);

    }

    public void setListenerComboColores() {

        HashMap<Object, Object> hashContadorColores = new HashMap<>();

        for (int it = 0; it < SingletonColores.getInstance().getLista().size(); it++) {
            hashContadorColores.put(SingletonColores.getInstance().getLista().get(it).getId(), it);
            gtextfieldColores.getDataList().add(SingletonColores.getInstance().getLista().get(it).getNombre());

        }
        gtextfieldColores.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                tieneElFoco = "colores";
            }

            @Override
            public void focusLost(FocusEvent e) {
               try {
                
                   if (!aviso.getColor().equals(gtextfieldColores.getText()) && !gtextfieldColores.getText().equals("")) {
                       ModificarAviso modificar = new ModificarAviso(aviso.getId(),"color", SingletonColores.getInstance().getLista().get(gtextfieldColores.getFilaSeleccionada()).getId().toString());
                       modificar.modificar();
                       List<Aviso> aviso1 = modificar.modificar();
                       actualizarAviso(aviso1.get(0));
                   }
                 
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
  
    }
    
    
    public void llenarImportes() {
    
       jTextFieldSuplemento.setText(aviso.getSuplemento().toString());
       jTextFieldImporteServicio.setText(aviso.getImporteServicio().toString());
       jTextFieldServicioEspecial.setText(aviso.getImporteServicioEspecial().toString());
       jTextFieldIgicServicioEspecial.setText(aviso.getIgicServicioEspecial().toString());
       jTextFieldBaseImponible.setText(aviso.getBaseImponible().toString());
       jTextFieldIgic.setText(aviso.getIgic().toString());
       jTextFieldTotal.setText(aviso.getTotal().toString());
    
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSuperior = new javax.swing.JPanel();
        jPanelSuperior1 = new javax.swing.JPanel();
        asignacion = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldIdAviso = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        realizar = new javax.swing.JLabel();
        horaAsignacion = new javax.swing.JFormattedTextField();
        horaRealizar = new javax.swing.JFormattedTextField();
        expediente = new javax.swing.JLabel();
        jTextFieldExpediente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldTelefono = new javax.swing.JTextField();
        jTextFieldMatricula = new javax.swing.JTextField();
        matricula = new javax.swing.JLabel();
        marca = new javax.swing.JLabel();
        modelo = new javax.swing.JLabel();
        color = new javax.swing.JLabel();
        bastidor = new javax.swing.JLabel();
        kmsPanelSuperior = new javax.swing.JLabel();
        poliza = new javax.swing.JLabel();
        jTextFieldPoliza = new javax.swing.JTextField();
        jTextFieldBastidor = new javax.swing.JTextField();
        jTextFieldKms = new javax.swing.JTextField();
        localizacion = new javax.swing.JLabel();
        destino = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserAsignacion = new datechooser.beans.DateChooserCombo();
        jPanelClientes = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jTextFieldDireccionDestino = new javax.swing.JTextField();
        jTextFieldDireccionLocalizacion = new javax.swing.JTextField();
        jPanelMarca = new javax.swing.JPanel();
        jPanelModelo = new javax.swing.JPanel();
        jPanelColor = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        comboBases = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        cliente = new javax.swing.JLabel();
        expediente1 = new javax.swing.JLabel();
        comboBase = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jPanelInferior = new javax.swing.JPanel();
        conductor = new javax.swing.JLabel();
        grua = new javax.swing.JLabel();
        talon = new javax.swing.JLabel();
        jTextFieldTalon = new javax.swing.JTextField();
        horaSalida = new javax.swing.JLabel();
        horaLocalizado = new javax.swing.JLabel();
        horaFinalizado = new javax.swing.JLabel();
        tiempoLlegada = new javax.swing.JLabel();
        jFormattedTextFieldTiempoLlegada = new javax.swing.JFormattedTextField();
        jFormattedTextFieldHoraFinalizado = new javax.swing.JFormattedTextField();
        averia = new javax.swing.JLabel();
        solucion = new javax.swing.JLabel();
        numRespuesta = new javax.swing.JLabel();
        comboAverias = new javax.swing.JComboBox();
        comboSoluciones = new javax.swing.JComboBox();
        kms = new javax.swing.JLabel();
        servicioTipo = new javax.swing.JLabel();
        plus = new javax.swing.JLabel();
        nf = new javax.swing.JLabel();
        jTextFieldNocturnoOFestivo = new javax.swing.JTextField();
        comboTipoDeServicio = new javax.swing.JComboBox();
        textFieldkmsGrua = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldSuplemento = new javax.swing.JTextField();
        jTextFieldImporteServicio = new javax.swing.JTextField();
        jTextFieldServicioEspecial = new javax.swing.JTextField();
        jTextFieldIgicServicioEspecial = new javax.swing.JTextField();
        jTextFieldBaseImponible = new javax.swing.JTextField();
        jTextFieldIgic = new javax.swing.JTextField();
        jTextFieldTotal = new javax.swing.JTextField();
        comboPlus = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldEspera = new javax.swing.JTextField();
        jTextFieldRescate = new javax.swing.JTextField();
        jTextFieldDiasCustodia = new javax.swing.JTextField();
        jFormattedTextFieldHoraLocalizado = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        botonEstadisticas = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanelConductor = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jFormattedTextFieldHoraSalida = new javax.swing.JFormattedTextField();
        comboNumRespuesta = new javax.swing.JComboBox();
        importeEspera = new javax.swing.JLabel();
        importeRescate = new javax.swing.JLabel();
        importeDiasCustodia = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        jPanelSuperior.setBackground(new java.awt.Color(255, 255, 255));

        jPanelSuperior1.setBackground(new java.awt.Color(255, 255, 255));

        asignacion.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        asignacion.setText("Asignación:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel2.setText("Aviso");

        jTextFieldIdAviso.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldIdAviso.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jTextFieldIdAviso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdAvisoActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/avisos/logo_gruas.png"))); // NOI18N

        realizar.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        realizar.setText("Realizar:");
        realizar.setRequestFocusEnabled(false);

        horaAsignacion.setBackground(new java.awt.Color(204, 204, 204));
        horaAsignacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
        horaAsignacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        horaRealizar.setBackground(new java.awt.Color(204, 204, 204));
        horaRealizar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
        horaRealizar.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        expediente.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        expediente.setText("Expediente:");

        jTextFieldExpediente.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldExpediente.setText("codigo de x digitos");

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel7.setText("Base:");

        nombre.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        nombre.setText("Nombre:");

        jTextFieldNombre.setBackground(new java.awt.Color(204, 204, 204));

        jTextFieldTelefono.setBackground(new java.awt.Color(204, 204, 204));

        jTextFieldMatricula.setBackground(new java.awt.Color(204, 204, 204));

        matricula.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        matricula.setText("Matrícula:");

        marca.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        marca.setText("Marca:");

        modelo.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        modelo.setText("Modelo:");

        color.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        color.setText("Color:");

        bastidor.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        bastidor.setText("Bastidor:");

        kmsPanelSuperior.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        kmsPanelSuperior.setText("Kms: ");

        poliza.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        poliza.setText("Póliza:");

        jTextFieldPoliza.setBackground(new java.awt.Color(204, 204, 204));

        jTextFieldBastidor.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldBastidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBastidorActionPerformed(evt);
            }
        });

        jTextFieldKms.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldKms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldKmsActionPerformed(evt);
            }
        });

        localizacion.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        localizacion.setText("Localización:");

        destino.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        destino.setText("Destino:");

        dateChooserCombo1.setCurrentView(new datechooser.view.appearance.AppearancesList("Light",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));

    dateChooserAsignacion.setCurrentView(new datechooser.view.appearance.AppearancesList("Light",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));

jPanelClientes.setPreferredSize(new java.awt.Dimension(0, 38));

javax.swing.GroupLayout jPanelClientesLayout = new javax.swing.GroupLayout(jPanelClientes);
jPanelClientes.setLayout(jPanelClientesLayout);
jPanelClientesLayout.setHorizontalGroup(
    jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGap(0, 0, Short.MAX_VALUE)
    );
    jPanelClientesLayout.setVerticalGroup(
        jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 38, Short.MAX_VALUE)
    );

    jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jPanel6.setLayout(new java.awt.BorderLayout());

    jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jPanel7.setLayout(new java.awt.BorderLayout());

    jTextFieldDireccionLocalizacion.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextFieldDireccionLocalizacionActionPerformed(evt);
        }
    });

    jPanelMarca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jPanelMarca.setMaximumSize(new java.awt.Dimension(200, 30));
    jPanelMarca.setMinimumSize(new java.awt.Dimension(200, 30));
    jPanelMarca.setLayout(new java.awt.BorderLayout());

    jPanelModelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jPanelModelo.setMinimumSize(new java.awt.Dimension(200, 30));
    jPanelModelo.setLayout(new java.awt.BorderLayout());

    jPanelColor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jPanelColor.setMinimumSize(new java.awt.Dimension(264, 34));

    javax.swing.GroupLayout jPanelColorLayout = new javax.swing.GroupLayout(jPanelColor);
    jPanelColor.setLayout(jPanelColorLayout);
    jPanelColorLayout.setHorizontalGroup(
        jPanelColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );
    jPanelColorLayout.setVerticalGroup(
        jPanelColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 30, Short.MAX_VALUE)
    );

    jLabel21.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    jLabel21.setText("Desde base:");

    jSeparator3.setForeground(new java.awt.Color(0, 0, 153));

    cliente.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    cliente.setText("Cliente:");

    expediente1.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    expediente1.setText("Teléfono:");

    jButton2.setBackground(new java.awt.Color(255, 255, 255));
    jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/avisos/refresh_256.png"))); // NOI18N
    jButton2.setBorder(null);
    jButton2.setBorderPainted(false);
    jButton2.setContentAreaFilled(false);
    jButton2.setFocusable(false);
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    jPanelInferior.setBackground(new java.awt.Color(255, 255, 255));

    conductor.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    conductor.setText("Conductor: ");

    grua.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    grua.setText(" NºGrúa: ");

    talon.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
    talon.setText("Talón ");

    jTextFieldTalon.setBackground(new java.awt.Color(204, 255, 255));
    jTextFieldTalon.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N

    horaSalida.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    horaSalida.setText("Hora Salida: ");

    horaLocalizado.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    horaLocalizado.setText("H.Localizado");

    horaFinalizado.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    horaFinalizado.setText("H.Finalizado");

    tiempoLlegada.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    tiempoLlegada.setText("T. llegada: ");

    jFormattedTextFieldTiempoLlegada.setBackground(new java.awt.Color(204, 255, 255));
    jFormattedTextFieldTiempoLlegada.setText(" ");
    jFormattedTextFieldTiempoLlegada.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextFieldTiempoLlegadaActionPerformed(evt);
        }
    });

    jFormattedTextFieldHoraFinalizado.setBackground(new java.awt.Color(204, 255, 255));
    jFormattedTextFieldHoraFinalizado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("H:mm"))));

    averia.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    averia.setText("Avería: ");

    solucion.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    solucion.setText("Solución: ");

    numRespuesta.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    numRespuesta.setText("Nºrespuesta: ");

    comboAverias.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            comboAveriasActionPerformed(evt);
        }
    });

    kms.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    kms.setText("Kms: ");

    servicioTipo.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    servicioTipo.setText("Servicio Tipo: ");

    plus.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    plus.setText("Plus: ");

    nf.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    nf.setText("N/F: ");

    jTextFieldNocturnoOFestivo.setBackground(new java.awt.Color(204, 255, 255));
    jTextFieldNocturnoOFestivo.setText(" ");

    textFieldkmsGrua.setBackground(new java.awt.Color(204, 255, 255));
    textFieldkmsGrua.setText(" ");

    jPanel3.setBackground(new java.awt.Color(255, 255, 255));
    jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray, java.awt.Color.gray));

    jLabel1.setBackground(new java.awt.Color(255, 255, 255));
    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
    jLabel1.setText("VALORACIÓN");

    jSeparator1.setBackground(new java.awt.Color(51, 51, 255));
    jSeparator1.setForeground(new java.awt.Color(51, 51, 255));

    jLabel4.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(102, 102, 102));
    jLabel4.setText("Suplementos ");

    jLabel5.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(102, 102, 102));
    jLabel5.setText("Importe servicio ");

    jLabel6.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
    jLabel6.setForeground(new java.awt.Color(0, 153, 0));
    jLabel6.setText("Servicio Especial  ");

    jLabel8.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
    jLabel8.setForeground(new java.awt.Color(0, 153, 0));
    jLabel8.setText("Igic Serv. Esp. ");

    jLabel9.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
    jLabel9.setForeground(new java.awt.Color(102, 102, 102));
    jLabel9.setText("Base Imponible ");

    jLabel11.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
    jLabel11.setForeground(new java.awt.Color(102, 102, 102));
    jLabel11.setText("IGIC ");

    jSeparator2.setBackground(new java.awt.Color(51, 51, 255));

    jLabel12.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
    jLabel12.setText("Total ");

    jTextFieldSuplemento.setFocusable(false);
    jTextFieldSuplemento.setRequestFocusEnabled(false);

    jTextFieldImporteServicio.setFocusable(false);
    jTextFieldImporteServicio.setRequestFocusEnabled(false);

    jTextFieldServicioEspecial.setFocusable(false);
    jTextFieldServicioEspecial.setRequestFocusEnabled(false);

    jTextFieldIgicServicioEspecial.setFocusable(false);
    jTextFieldIgicServicioEspecial.setRequestFocusEnabled(false);

    jTextFieldBaseImponible.setFocusable(false);
    jTextFieldBaseImponible.setRequestFocusEnabled(false);
    jTextFieldBaseImponible.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextFieldBaseImponibleActionPerformed(evt);
        }
    });

    jTextFieldIgic.setFocusable(false);
    jTextFieldIgic.setRequestFocusEnabled(false);

    jTextFieldTotal.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
    jTextFieldTotal.setText("  ");
    jTextFieldTotal.setFocusable(false);
    jTextFieldTotal.setRequestFocusEnabled(false);

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(88, 88, 88)
                    .addComponent(jLabel1))
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8)
                        .addComponent(jLabel6)
                        .addComponent(jLabel9))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldIgicServicioEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldServicioEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldBaseImponible, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGap(0, 0, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldSuplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldImporteServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(47, 47, 47)
                            .addComponent(jLabel10))
                        .addComponent(jTextFieldIgic, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addComponent(jLabel12)
                    .addGap(18, 18, 18)
                    .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(52, 52, 52))))
    );
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel3Layout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel10)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldSuplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGap(5, 5, 5)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldImporteServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jTextFieldIgic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(13, 13, 13)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6)
                        .addComponent(jTextFieldServicioEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(5, 5, 5)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldIgicServicioEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(5, 5, 5)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jTextFieldBaseImponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextFieldTotal)
                .addComponent(jLabel12))
            .addContainerGap())
    );

    jLabel13.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    jLabel13.setText("Espera:");

    jLabel14.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    jLabel14.setText("Rescate:");

    jLabel15.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    jLabel15.setText("Dias custodia:");

    jTextFieldEspera.setBackground(new java.awt.Color(204, 255, 255));
    jTextFieldEspera.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextFieldEsperaActionPerformed(evt);
        }
    });

    jTextFieldRescate.setBackground(new java.awt.Color(204, 255, 255));

    jTextFieldDiasCustodia.setBackground(new java.awt.Color(204, 255, 255));

    jFormattedTextFieldHoraLocalizado.setBackground(new java.awt.Color(204, 255, 255));
    jFormattedTextFieldHoraLocalizado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("H:mm"))));
    jFormattedTextFieldHoraLocalizado.setHorizontalAlignment(javax.swing.JTextField.LEFT);

    jButton1.setText("Factura");

    jButton3.setText("Daños");

    botonEstadisticas.setText("Estadísticas");

    jButton5.setText("Servicios especiales");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton5ActionPerformed(evt);
        }
    });

    jPanelConductor.setLayout(new java.awt.BorderLayout());

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 84, Short.MAX_VALUE)
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
    );

    jFormattedTextFieldHoraSalida.setBackground(new java.awt.Color(204, 255, 255));
    jFormattedTextFieldHoraSalida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("H:mm"))));
    jFormattedTextFieldHoraSalida.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jFormattedTextFieldHoraSalidaActionPerformed(evt);
        }
    });

    importeEspera.setBackground(new java.awt.Color(0, 102, 102));
    importeEspera.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
    importeEspera.setText("0.0€");

    importeRescate.setBackground(new java.awt.Color(0, 102, 102));
    importeRescate.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
    importeRescate.setText("0.0€");

    importeDiasCustodia.setBackground(new java.awt.Color(0, 102, 102));
    importeDiasCustodia.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
    importeDiasCustodia.setText("0.0€");

    javax.swing.GroupLayout jPanelInferiorLayout = new javax.swing.GroupLayout(jPanelInferior);
    jPanelInferior.setLayout(jPanelInferiorLayout);
    jPanelInferiorLayout.setHorizontalGroup(
        jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelInferiorLayout.createSequentialGroup()
            .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelInferiorLayout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(horaSalida)
                        .addComponent(averia)
                        .addComponent(kms)))
                .addGroup(jPanelInferiorLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel13)
                        .addComponent(grua))))
            .addGap(29, 29, 29)
            .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelInferiorLayout.createSequentialGroup()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(conductor)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanelConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(talon)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jTextFieldTalon, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelInferiorLayout.createSequentialGroup()
                    .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelInferiorLayout.createSequentialGroup()
                            .addComponent(textFieldkmsGrua, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(servicioTipo)
                            .addGap(7, 7, 7)
                            .addComponent(comboTipoDeServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(plus)
                            .addGap(18, 18, 18)
                            .addComponent(comboPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(nf)
                            .addGap(18, 18, 18)
                            .addComponent(jTextFieldNocturnoOFestivo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelInferiorLayout.createSequentialGroup()
                            .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelInferiorLayout.createSequentialGroup()
                                    .addComponent(jFormattedTextFieldHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(horaLocalizado)
                                    .addGap(18, 18, 18)
                                    .addComponent(jFormattedTextFieldHoraLocalizado, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(15, 15, 15)
                                    .addComponent(horaFinalizado)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jFormattedTextFieldHoraFinalizado, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(tiempoLlegada)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jFormattedTextFieldTiempoLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelInferiorLayout.createSequentialGroup()
                                    .addComponent(comboAverias, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(55, 55, 55)
                                    .addComponent(solucion)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(comboSoluciones, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(numRespuesta)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(comboNumRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanelInferiorLayout.createSequentialGroup()
                            .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelInferiorLayout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(74, 74, 74)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelInferiorLayout.createSequentialGroup()
                                    .addComponent(jTextFieldEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(importeEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(58, 58, 58)
                                    .addComponent(jLabel14)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelInferiorLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(botonEstadisticas)
                                    .addGap(58, 58, 58)
                                    .addComponent(jButton5)
                                    .addGap(19, 19, 19))
                                .addGroup(jPanelInferiorLayout.createSequentialGroup()
                                    .addComponent(jTextFieldRescate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(importeRescate, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldDiasCustodia, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(importeDiasCustodia, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(18, 18, 18)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(28, 28, 28))
    );
    jPanelInferiorLayout.setVerticalGroup(
        jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInferiorLayout.createSequentialGroup()
            .addGap(10, 10, 10)
            .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(conductor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelConductor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(grua, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addGroup(jPanelInferiorLayout.createSequentialGroup()
                    .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(talon)
                        .addComponent(jTextFieldTalon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(3, 3, 3)))
            .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelInferiorLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(horaSalida)
                        .addComponent(jFormattedTextFieldTiempoLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(horaLocalizado)
                        .addComponent(horaFinalizado)
                        .addComponent(jFormattedTextFieldHoraFinalizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tiempoLlegada)
                        .addComponent(jFormattedTextFieldHoraLocalizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextFieldHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboAverias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(numRespuesta)
                        .addComponent(averia)
                        .addComponent(comboSoluciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(solucion)
                        .addComponent(comboNumRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(kms)
                        .addComponent(textFieldkmsGrua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(servicioTipo)
                        .addComponent(comboTipoDeServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(plus)
                        .addComponent(comboPlus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nf)
                        .addComponent(jTextFieldNocturnoOFestivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(20, 20, 20)
                    .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15)
                        .addComponent(jTextFieldEspera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldRescate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldDiasCustodia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(importeEspera)
                        .addComponent(importeRescate)
                        .addComponent(importeDiasCustodia))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addComponent(botonEstadisticas)
                        .addComponent(jButton5))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanelInferiorLayout.createSequentialGroup()
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );

    jButton4.setBackground(new java.awt.Color(255, 255, 255));
    jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/avisos/índice.jpg"))); // NOI18N
    jButton4.setBorder(null);
    jButton4.setBorderPainted(false);
    jButton4.setContentAreaFilled(false);
    jButton4.setFocusable(false);
    jButton4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
        }
    });

    jButton6.setBackground(new java.awt.Color(255, 255, 255));
    jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/avisos/índice.jpg"))); // NOI18N
    jButton6.setBorder(null);
    jButton6.setBorderPainted(false);
    jButton6.setContentAreaFilled(false);
    jButton6.setFocusable(false);
    jButton6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton6ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanelSuperior1Layout = new javax.swing.GroupLayout(jPanelSuperior1);
    jPanelSuperior1.setLayout(jPanelSuperior1Layout);
    jPanelSuperior1Layout.setHorizontalGroup(
        jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelSuperior1Layout.createSequentialGroup()
            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(localizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(destino, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldDireccionDestino))
                                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldDireccionLocalizacion))
                                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                    .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                            .addComponent(jTextFieldBastidor, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(kmsPanelSuperior)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextFieldKms, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(expediente1)
                                                .addComponent(poliza, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                .addComponent(jTextFieldPoliza)))
                                        .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                            .addGap(7, 7, 7)
                                            .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jPanelMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGap(18, 18, 18)
                                    .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanelColor, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(84, 84, 84))))
                        .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                    .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                                    .addComponent(asignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(36, 36, 36)
                                                    .addComponent(dateChooserAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(horaAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(bastidor))
                                            .addGap(30, 30, 30)
                                            .addComponent(realizar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(horaRealizar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(matricula, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(32, 32, 32)
                                            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jPanelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                                                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                                    .addComponent(jTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                                    .addComponent(jPanelModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jButton2)
                                                    .addGap(10, 10, 10)
                                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(35, 35, 35)
                                            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                                    .addComponent(jLabel7)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(comboBase, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                                    .addComponent(expediente)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jTextFieldExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                            .addGap(30, 30, 30)
                                            .addComponent(jLabel21)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(comboBases, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldIdAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35))))
                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1147, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(0, 0, Short.MAX_VALUE))
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperior1Layout.createSequentialGroup()
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );
    jPanelSuperior1Layout.setVerticalGroup(
        jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelSuperior1Layout.createSequentialGroup()
            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3)
                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldIdAviso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))))
            .addGap(15, 15, 15)
            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(asignacion)
                    .addComponent(realizar)
                    .addComponent(horaAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dateChooserAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(horaRealizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(comboBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                    .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cliente)
                        .addComponent(jPanelClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10)
                    .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(matricula)
                        .addComponent(jTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(10, 10, 10))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperior1Layout.createSequentialGroup()
                    .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(expediente)
                        .addComponent(jTextFieldExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(52, 52, 52)))
            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jPanelMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanelColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanelModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(10, 10, 10)
            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(nombre)
                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(expediente1)
                .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bastidor)
                .addComponent(jTextFieldBastidor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(kmsPanelSuperior)
                .addComponent(jTextFieldKms, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(poliza)
                .addComponent(jTextFieldPoliza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel21)
                .addComponent(comboBases, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(10, 10, 10)
            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(localizacion)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextFieldDireccionLocalizacion))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelSuperior1Layout.createSequentialGroup()
                    .addComponent(destino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(37, 37, 37))
                .addGroup(jPanelSuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTextFieldDireccionDestino)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, 0)
            .addComponent(jPanelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
    );

    javax.swing.GroupLayout jPanelSuperiorLayout = new javax.swing.GroupLayout(jPanelSuperior);
    jPanelSuperior.setLayout(jPanelSuperiorLayout);
    jPanelSuperiorLayout.setHorizontalGroup(
        jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanelSuperior1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );
    jPanelSuperiorLayout.setVerticalGroup(
        jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanelSuperior1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(10, 10, 10))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanelSuperior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanelSuperior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldIdAvisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdAvisoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdAvisoActionPerformed

    private void jTextFieldBastidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBastidorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBastidorActionPerformed

    private void jTextFieldKmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldKmsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldKmsActionPerformed

    private void jFormattedTextFieldTiempoLlegadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTiempoLlegadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldTiempoLlegadaActionPerformed

    private void comboAveriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAveriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboAveriasActionPerformed

    private void jTextFieldBaseImponibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBaseImponibleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBaseImponibleActionPerformed

    private void jTextFieldEsperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEsperaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEsperaActionPerformed

    private void jFormattedTextFieldHoraSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldHoraSalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldHoraSalidaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         List<Cliente> clientes = null;
       
         try {
             clientes = new CargarClientesCommand(new CargadorListaCliente(), new ClienteParserJson()).executeCliente(aviso.getIdCliente());
         } catch (Exception ex) {
             Logger.getLogger(FichaClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
          SingletonCliente.getInstance().setCliente(clientes.get(0));
       
            
          
          FrameServiciosEspecialesCliente frame = new FrameServiciosEspecialesCliente(aviso);
          frame.setVisible(true);
             
       
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextFieldDireccionLocalizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDireccionLocalizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDireccionLocalizacionActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CargarModelosCommand cargar = new CargarModelosCommand(new CargadorListaModelo(), new ModeloParserJson());
        try {
            listaModelos = cargar.execute();
        } catch (IOException ex) {
            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            llenarComboModelos(listaModelos);
        } catch (IOException ex) {
            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            CargarVehiculosCommand cargar = new CargarVehiculosCommand(new CargadorListaVehiculo(), new VehiculoParserJson());
            List<Vehiculo> lista = cargar.executePorMarcaYModelo(aviso.getIdMarca(), aviso.getIdModelo());
            new FichaVehiculo(lista.get(0)).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        List<Cliente> clientes = null;
       
         try {
             clientes = new CargarClientesCommand(new CargadorListaCliente(), new ClienteParserJson()).executeCliente(aviso.getIdCliente());
         } catch (Exception ex) {
             Logger.getLogger(FichaClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
         }
          SingletonCliente.getInstance().setCliente(clientes.get(0));
        try {
            FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).setVisible(true);
       
            
        } catch (Exception ex) {
            Logger.getLogger(FichaAviso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    public Aviso getAviso() {
        return aviso;
    }

 public void situarFoco() {
     
        
        
        switch (tieneElFoco) {
        
            case "talon": 
                  gtextfieldGruas.requestFocus();
                  break;
            case "horaAsignacion":
                   dateChooserCombo1.requestFocus();
                   break;
            case "realizar":
                   horaRealizar.requestFocus();
                   break;
            case "horaRealizar":
                   gtextfieldIdCliente.requestFocus();
                   break;
            case "expediente":
                   matricula.requestFocus();
                   break; 
            case "base": 
                   gtextfieldIdCliente.requestFocus();
            case "clientes":
                  jTextFieldExpediente.requestFocus();
                  break;
           
            case "nombre":
                  jTextFieldTelefono.requestFocus();
                  break;
            case "telefono":
                  jTextFieldBastidor.requestFocus();
                  break;
            case "matricula":
                  gtextfieldModelos.requestFocus();
                  break;
           case "modelos":
                  gtextfieldMarcas.requestFocus();
                  break;
           case "marcas": 
                  gtextfieldColores.requestFocus();
                  break;
                  
            case "colores":
                  nombre.requestFocus();  
                  break;
            case "bastidor":
                  jTextFieldKms.requestFocus();
                  break;
            case "kms":
                  jTextFieldPoliza.requestFocus();
                  break;
            case "poliza":
                  comboBases.requestFocus();
                  break;
           case "desdeBase":
                  gtextfieldMunicipiosLocalizacion.requestFocus();
                  break;
            case "municipioLocalizacion":
                   jTextFieldDireccionLocalizacion.requestFocus();
                  break;
            case "direccionLocalizacion":
                  gtextfieldMunicipiosDestino.requestFocus();
                  break;
            case "municipioDestino":
                    jTextFieldDireccionDestino.requestFocus();
                    break;
            case "direccionDestino":
                  gtextfieldGruas.requestFocus();
                  break;
            case "grua":
                  gtextfieldConductor.requestFocus();
                  break;
            case "conductor":
                 jFormattedTextFieldHoraSalida.requestFocus();
                 break;
            case "horaSalida":
                 jFormattedTextFieldHoraLocalizado.requestFocus();
                 break;
            case "tiempoLlegada":
                 jFormattedTextFieldHoraFinalizado.requestFocus();
                 break;
            case "horaLocalizado":
                 jFormattedTextFieldHoraFinalizado.requestFocus();
                 break;
            case "horaFinalizado":
               if (aviso.getIdCliente().equals(4300230)) {
                  comboAverias.requestFocus();
                  break;
               } else {
                  textFieldkmsGrua.requestFocus();
                  break;
               }
            case "averia":
                comboSoluciones.requestFocus();
                break;
            case "solucion":
                comboNumRespuesta.requestFocus();
                break;
            case "numRespuesta":
                textFieldkmsGrua.requestFocus();
                break;
            case "kmsGrua":
                comboTipoDeServicio.requestFocus();
                break;
            case "servicioTipo":
                comboPlus.requestFocus();
                break;
            case "plus":
                jTextFieldNocturnoOFestivo.requestFocus();
                break;
            case "nf":
                jTextFieldEspera.requestFocus();
                break;
            case "espera":
                jTextFieldRescate.requestFocus();
                break;
            case "rescate":
                jTextFieldDiasCustodia.requestFocus();
                break;
            case "diasCustodia":
                jButton1.requestFocus();
                break;
                
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asignacion;
    private javax.swing.JLabel averia;
    private javax.swing.JLabel bastidor;
    private javax.swing.JButton botonEstadisticas;
    private javax.swing.JLabel cliente;
    private javax.swing.JLabel color;
    private javax.swing.JComboBox comboAverias;
    private javax.swing.JComboBox<String> comboBase;
    private javax.swing.JComboBox<String> comboBases;
    private javax.swing.JComboBox comboNumRespuesta;
    private javax.swing.JComboBox comboPlus;
    private javax.swing.JComboBox comboSoluciones;
    private javax.swing.JComboBox comboTipoDeServicio;
    private javax.swing.JLabel conductor;
    private datechooser.beans.DateChooserCombo dateChooserAsignacion;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private javax.swing.JLabel destino;
    private javax.swing.JLabel expediente;
    private javax.swing.JLabel expediente1;
    private javax.swing.JLabel grua;
    private javax.swing.JFormattedTextField horaAsignacion;
    private javax.swing.JLabel horaFinalizado;
    private javax.swing.JLabel horaLocalizado;
    private javax.swing.JFormattedTextField horaRealizar;
    private javax.swing.JLabel horaSalida;
    private javax.swing.JLabel importeDiasCustodia;
    private javax.swing.JLabel importeEspera;
    private javax.swing.JLabel importeRescate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JFormattedTextField jFormattedTextFieldHoraFinalizado;
    private javax.swing.JFormattedTextField jFormattedTextFieldHoraLocalizado;
    private javax.swing.JFormattedTextField jFormattedTextFieldHoraSalida;
    private javax.swing.JFormattedTextField jFormattedTextFieldTiempoLlegada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelClientes;
    private javax.swing.JPanel jPanelColor;
    private javax.swing.JPanel jPanelConductor;
    private javax.swing.JPanel jPanelInferior;
    private javax.swing.JPanel jPanelMarca;
    private javax.swing.JPanel jPanelModelo;
    private javax.swing.JPanel jPanelSuperior;
    private javax.swing.JPanel jPanelSuperior1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextFieldBaseImponible;
    private javax.swing.JTextField jTextFieldBastidor;
    private javax.swing.JTextField jTextFieldDiasCustodia;
    private javax.swing.JTextField jTextFieldDireccionDestino;
    private javax.swing.JTextField jTextFieldDireccionLocalizacion;
    private javax.swing.JTextField jTextFieldEspera;
    private javax.swing.JTextField jTextFieldExpediente;
    private javax.swing.JTextField jTextFieldIdAviso;
    private javax.swing.JTextField jTextFieldIgic;
    private javax.swing.JTextField jTextFieldIgicServicioEspecial;
    private javax.swing.JTextField jTextFieldImporteServicio;
    private javax.swing.JTextField jTextFieldKms;
    private javax.swing.JTextField jTextFieldMatricula;
    private javax.swing.JTextField jTextFieldNocturnoOFestivo;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPoliza;
    private javax.swing.JTextField jTextFieldRescate;
    private javax.swing.JTextField jTextFieldServicioEspecial;
    private javax.swing.JTextField jTextFieldSuplemento;
    private javax.swing.JTextField jTextFieldTalon;
    private javax.swing.JTextField jTextFieldTelefono;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JLabel kms;
    private javax.swing.JLabel kmsPanelSuperior;
    private javax.swing.JLabel localizacion;
    private javax.swing.JLabel marca;
    private javax.swing.JLabel matricula;
    private javax.swing.JLabel modelo;
    private javax.swing.JLabel nf;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel numRespuesta;
    private javax.swing.JLabel plus;
    private javax.swing.JLabel poliza;
    private javax.swing.JLabel realizar;
    private javax.swing.JLabel servicioTipo;
    private javax.swing.JLabel solucion;
    private javax.swing.JLabel talon;
    private javax.swing.JTextField textFieldkmsGrua;
    private javax.swing.JLabel tiempoLlegada;
    // End of variables declaration//GEN-END:variables
}

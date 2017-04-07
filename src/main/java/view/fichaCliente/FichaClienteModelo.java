/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.fichaCliente;

import DatosPrecargados.Poblaciones;
import DatosPrecargados.Provincias;
import command.basesOperativas.CargarBasesOperativasCommand;
import command.clientes.CargarClientesCommand;
import datechooser.model.exeptions.IncompatibleDataExeption;
import datechooser.model.multiple.PeriodSet;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import model.basesOperativas.BaseOperativa;
import model.Cliente;
import model.Poblacion;
import model.Provincia;
import process.CRUDClientes.DarAltaCliente;
import process.CRUDClientes.DarBajaCliente;
import process.CRUDClientes.ModificarCliente;
import process.cargador.CargadorListaBasesOperativas;
import process.cargador.CargadorListaCliente;
import process.parser.basesOperativas.BaseOperativaParserJson;
import process.parser.clientes.ClienteParserJson;
import view.basesOperativas.AsociarBasesOperativasAClienteFrame;
import view.basesOperativas.JTablaBasesOperativasCRUD;
import view.basesOperativas.JTablaBasesOperativasVista;
import view.clientes.FichaClienteFrame;

import view.contactos.FrameAgenda;

/**
 *
 * @author loquat
 */
public class FichaClienteModelo extends javax.swing.JPanel {

    Cliente cliente;
    Cliente clienteEditado;
    JTablaBasesOperativasVista tabla;
    Calendar fechaBajaCalendar;
    Calendar fechaAltaCalendar;
    
    
    public FichaClienteModelo() throws IOException {
        
        cliente = SingletonCliente.getInstance().getCliente();
        initComponents();
        actualizar();
        deshabilitarEditarCliente();
        llenarBasesOperativas();
        setListenerEditarCliente();
        setListeners();
        
      
        
        
    }

    public void actualizarCliente() {
        cliente = SingletonCliente.getInstance().getCliente();
        actualizar();

    }

    public void setListenerEditarCliente() {
        checkEdicionCliente.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (checkEdicionCliente.isSelected()) {
                    habilitarEditarCliente();
                    clienteEditado = cliente;

                } else {
                    deshabilitarEditarCliente();

                }

            }
        });
    }

    public void actualizarBasesOperativas(List<BaseOperativa> lista) {
        tabla.mostrar(lista);
        jScrollPaneBasesOperativas.setViewportView(tabla);
    }

    public void llenarBasesOperativas() throws IOException {

        tabla = new JTablaBasesOperativasVista();
        tabla.getColumnModel().getColumn(0).setPreferredWidth(10);

        CargarBasesOperativasCommand cargar = new CargarBasesOperativasCommand(new CargadorListaBasesOperativas(), new BaseOperativaParserJson());

        List<BaseOperativa> lista = cargar.executeLista(SingletonCliente.getInstance().getCliente().getId());

        tabla.mostrar(lista);

        jScrollPaneBasesOperativas.setViewportView(tabla);

    }

    public void deshabilitarEditarCliente() {

        jTextFieldNumProveedor.setEditable(false);
        jTextFieldTipoCliente.setEditable(false);
        jTextPaneGrupo.setEditable(false);
        jTextFieldNombre.setEditable(false);
        jTextFieldNIF.setEditable(false);
        dateChooserComboFechaAlta.setEnabled(false);
        jTextFieldDireccion.setEditable(false);
        jTextFieldPoblacion.setEditable(false);
        jTextFieldProvincia.setEditable(false);
        jTextFielCP.setEditable(false);
        jTextFieldPais.setEditable(false);
        //    jTextFieldTelefono.setEditable(false);
        //    jTextFieldTelfAdmon.setEditable(false);
        jTextFieldTelfFax.setEditable(false);
        jTextFieldTelfAsistencia.setEditable(false);
        //    jTextFieldTelfConsulta.setEditable(false);
        //    jTextFieldTelfExtra.setEditable(false);
        jTextFieldMinRetraso.setEditable(false);
        jTextFieldNumInfFinalizado.setEditable(false);
        jTextFieldContacto.setEditable(false);
        jTextFieldPuesto.setEditable(false);
        jTextFieldEmailAdmon.setEditable(false);
        jTextFieldEmailAvisos.setEditable(false);
        jComboBoxTipoFacturacion.setEnabled(false);
        jTextFieldFechaCobro.setEditable(false);
        jTextFieldFormaCobro.setEditable(false);
        jTextFieldFechaEntregaFra.setEditable(false);
        jTextAreaObservaciones.setEditable(false);
        jTextImpuesto.setEditable(false);
        
        //     jTextPaneIdentificador.setEditable(false);
        checkIGIC.setEnabled(false);
        checkImprimeKM.setEnabled(false);

    }

    public void habilitarEditarCliente() {
        jTextFieldTipoCliente.setEditable(true);
        jTextPaneGrupo.setEditable(true);
        jTextFieldNombre.setEditable(true);
        jTextFieldNIF.setEditable(true);
        dateChooserComboFechaAlta.setEnabled(true);
        // dateChooserComboFechaBaja.setEnabled(true);
        jTextFieldProvincia.setEditable(true);
        jTextFieldDireccion.setEditable(true);
        jTextFieldPoblacion.setEditable(true);
        jTextFielCP.setEditable(true);
        jTextImpuesto.setEditable(true);
        
            jTextFieldPais.setEditable(true);
        //      jTextFieldTelefono.setEditable(true);
        //      jTextFieldTelfAdmon.setEditable(true);
        jTextFieldTelfFax.setEditable(true);
        jTextFieldTelfAsistencia.setEditable(true);
        //      jTextFieldTelfConsulta.setEditable(true);
        //      jTextFieldTelfExtra.setEditable(true);
        jTextFieldMinRetraso.setEditable(true);
        jTextFieldNumInfFinalizado.setEditable(true);
        jTextFieldContacto.setEditable(true);
        jTextFieldPuesto.setEditable(true);
        jTextFieldEmailAdmon.setEditable(true);
        jTextFieldEmailAvisos.setEditable(true);
        jComboBoxTipoFacturacion.setEnabled(true);
        jTextFieldFechaCobro.setEditable(true);
        jTextFieldFormaCobro.setEditable(true);
        jTextFieldFechaEntregaFra.setEditable(true);
        jTextAreaObservaciones.setEditable(true);
        jTextFieldNumProveedor.setEditable(true);
        //     jTextPaneIdentificador.setEditable(true);
        checkIGIC.setEnabled(true);
        checkImprimeKM.setEnabled(true);

    }

    public void actualizar() {

        try {
            
            
        if (cliente.getEstado()==0) {
           jButton1.setText("Dar de ALTA");
           dateChooserComboFechaBaja.setEnabled(true);
           dateChooserComboFechaAlta.setEnabled(false);
           
        } else {
           jButton1.setText("Dar de BAJA");
           dateChooserComboFechaAlta.setEnabled(true);
           dateChooserComboFechaBaja.setEnabled(false);
        
        }
            
            jTextFieldNumProveedor.setText(cliente.getNumProveedor());
            jTextFieldTipoCliente.setText(cliente.getTipoCliente());
            jTextPaneGrupo.setText(cliente.getGrupo());
            jTextFieldNombre.setText(cliente.getNombre());
            jTextFieldNIF.setText(cliente.getNif());
            
            
            //****************** FECHAS DE ALTA Y BAJA *********************//
             String dateStrAlta = cliente.getFechaAlta();

            SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
            Date dateObjAlta = null;
            
            try {
                dateObjAlta = curFormater.parse(dateStrAlta);
            } catch (ParseException ex) {
                Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            fechaAltaCalendar = Calendar.getInstance();
            fechaAltaCalendar.setTime(dateObjAlta);
    
               
            PeriodSet   persetALTA = new PeriodSet();
            persetALTA.add(fechaAltaCalendar);
            dateChooserComboFechaAlta.setDefaultPeriods(persetALTA);
            
            //System.out.println(fechaBajaCalendar.getTime());
            jTextFieldDireccion.setText(cliente.getDomicilio());
            jTextFieldPoblacion.setText(cliente.getPoblacion());
            jTextFieldProvincia.setText(cliente.getProvincia());
            jTextFielCP.setText(cliente.getCp());
             jTextFieldPais.setText(cliente.getPais());
            //  jTextFieldTelefono.setText(cliente.getTelefono1());
            //  jTextFieldTelfAdmon.setText(cliente.getTelefonoAdmon());
            jTextFieldTelfFax.setText(cliente.getFax());
            jTextFieldTelfAsistencia.setText(cliente.getTelefonoAsistencia());
            //  jTextFieldTelfConsulta.setText(cliente.getTelefonoConsulta());
            //  jTextFieldTelfExtra.setText(cliente.getTelefonoExtra());
            jTextFieldMinRetraso.setText(cliente.getMinimoRetraso().toString());
            jTextFieldNumInfFinalizado.setText(cliente.getNumInfFinalizado().toString());
            jTextFieldContacto.setText(cliente.getContacto());
            jTextFieldPuesto.setText(cliente.getPuestoContacto());
            jTextFieldEmailAdmon.setText(cliente.getEmailAdmon());
            jTextFieldEmailAvisos.setText(cliente.getCorreoAvisos());
            jComboBoxTipoFacturacion.setSelectedIndex(cliente.getTipoFacturacion());
            //  jComboBoxTipoFacturacion.setSelectedItem(cliente.getTipoFacturacion());
            jTextFieldFechaCobro.setText(cliente.getFechaCobro());
            jTextFieldFormaCobro.setText(cliente.getFormaCobro());
            jTextFieldFechaEntregaFra.setText(cliente.getFechaEntregaFra());
            jTextAreaObservaciones.setText(cliente.getObservaciones());
            jTextImpuesto.setText(cliente.getImpuesto().toString());
        
            
//          jTextPaneIdentificador.setText(cliente.getId().toString());

if ("1".equals(cliente.getClienteSinIGIC())) {
    checkIGIC.setState(true);
} else {
    checkIGIC.setState(false);
}

if (cliente.getImprimeKm().equals("1")) {
    checkImprimeKM.setState(true);
} else {
    checkImprimeKM.setState(false);
}

validate();
repaint();
        } catch (IncompatibleDataExeption ex) {
            Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            llenarBasesOperativas();
        } catch (IOException ex) {
            Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setListeners() {

        checkImprimeKM.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (checkImprimeKM.getState() == true) {
                    ModificarCliente modificar = new ModificarCliente(cliente.getId(), "imprimeKm", "1");
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    consultar();
                    actualizar();

                } else {
                    ModificarCliente modificar = new ModificarCliente(cliente.getId(), "imprimeKm", "0");
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    consultar();
                    actualizar();

                }

            }
        });

        checkIGIC.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (checkIGIC.getState() == true) {
                    ModificarCliente modificar = new ModificarCliente(cliente.getId(), "clienteSinIGIC", "1");
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    consultar();
                    actualizar();

                } else {
                    ModificarCliente modificar = new ModificarCliente(cliente.getId(), "clienteSinIGIC", "0");
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    consultar();
                    actualizar();

                }

            }
        });

        jComboBoxTipoFacturacion.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "tipoFacturacion", jComboBoxTipoFacturacion.getSelectedItem().toString());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                jComboBoxTipoFacturacion.setSelectedItem(cliente.getTipoFacturacion());

            }
        });

        jTextPaneGrupo.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "grupo", jTextPaneGrupo.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        jTextFieldTipoCliente.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "tipoCliente", jTextFieldTipoCliente.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        jTextFieldNumProveedor.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "numProveedor", jTextFieldNumProveedor.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        jTextFieldNIF.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "nif", jTextFieldNIF.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        /*      jTextFieldTelfExtra.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                  
                   // e.getOpositeComponent() devuelve el Component que le
                   // cede el foco a nuestro botón.
                }
                public void focusLost(FocusEvent e) {
                    ModificarCliente modificar  = new ModificarCliente(cliente.getId(),"telefonoExtra",jTextFieldTelfExtra.getText() ) ;
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    consultar();
                    actualizar();
                }
                });*/
        jTextFieldDireccion.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "domicilio", jTextFieldDireccion.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        
          jTextFieldDireccion.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "domicilio", jTextFieldDireccion.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        
        jTextImpuesto.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            @Override
            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "impuesto", jTextImpuesto.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        /* jTextFieldTelefono.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                  
                   // e.getOpositeComponent() devuelve el Component que le
                   // cede el foco a nuestro botón.
                }
                public void focusLost(FocusEvent e) {
                    ModificarCliente modificar  = new ModificarCliente(cliente.getId(),"telefono",jTextFieldTelefono.getText() ) ;
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    consultar();
                    actualizar();
                }
                });
                
                jTextFieldTelfAdmon.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                  
                   // e.getOpositeComponent() devuelve el Component que le
                   // cede el foco a nuestro botón.
                }
                public void focusLost(FocusEvent e) {
                    ModificarCliente modificar  = new ModificarCliente(cliente.getId(),"telefonoAdmon",jTextFieldTelfAdmon.getText() ) ;
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    consultar();
                    actualizar();
                }
                });*/
        jTextFieldTelfFax.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "fax", jTextFieldTelfFax.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        jTextFieldTelfAsistencia.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "telefonoAsistencia", jTextFieldTelfAsistencia.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        
                jTextFieldPuesto.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                  
                   // e.getOpositeComponent() devuelve el Component que le
                   // cede el foco a nuestro botón.
                }
                @Override
                public void focusLost(FocusEvent e) {
                    ModificarCliente modificar  = new ModificarCliente(cliente.getId(),"puestoContacto",jTextFieldPuesto.getText() ) ;
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    consultar();
                    actualizar();
                }
                });
         
        jTextFieldNumInfFinalizado.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "numInfFinalizado", jTextFieldNumInfFinalizado.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        jTextFieldMinRetraso.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "minimoRetraso", jTextFieldMinRetraso.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        jTextFieldContacto.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "contacto", jTextFieldContacto.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

         jTextFieldContacto.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "contacto", jTextFieldContacto.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });
        
        
        
        jTextFieldPais.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "pais", jTextFieldPais.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        /*   jTextFieldPais.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                  
                   // e.getOpositeComponent() devuelve el Component que le
                   // cede el foco a nuestro botón.
                }
                public void focusLost(FocusEvent e) {
                    ModificarCliente modificar  = new ModificarCliente(cliente.getId(),"pais",jTextFieldPais.getText() ) ;
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    consultar();
                    actualizar();
                }
                });
                
         */
    jTextFieldPoblacion.addFocusListener(new FocusListener() {
                        public void focusGained(FocusEvent e) {
                           
                           // e.getOpositeComponent() devuelve el Component que le
                           // cede el foco a nuestro botón.
                        }
                        public void focusLost(FocusEvent e) {
                            
                            ModificarCliente modificar  = new ModificarCliente(cliente.getId(),"poblacion",jTextFieldPoblacion.getText() ) ;
                            try {
                                modificar.modificarCliente();
                            } catch (IOException ex) {
                                Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            consultar();
                            actualizar();
                        }
                        });
                
                
         
        jTextFielCP.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            @Override
            public void focusLost(FocusEvent e) {

                try {
                    
                    ModificarCliente modificar = new ModificarCliente(cliente.getId(), "cp", jTextFielCP.getText());
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Poblaciones poblaciones = null;
                    
                    poblaciones = new Poblaciones();
                    
                    List<Poblacion> pob = new ArrayList<>();
                    
                    pob = poblaciones.getPoblacionPorCp(jTextFielCP.getText());
                    
                    
                    ModificarCliente modificar2 = new ModificarCliente(cliente.getId(), "poblacion", pob.get(0).getNombrePoblacion());
                    try {
                        modificar2.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Provincias provincias = new Provincias();
                    List<Provincia> prov = new ArrayList<>();
                    
                   
                        prov = provincias.getProvinciaPorId(pob.get(0).getProvincia());
                    
                    
                    ModificarCliente modificar3 = new ModificarCliente(cliente.getId(), "provincia", prov.get(0).getProvincia());
                   
                        modificar3.modificarCliente();
                   
                    
                    jTextFieldPoblacion.requestFocus();
                    
                    consultar();
                    actualizar();
                } catch (Exception ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

                  
                jTextFieldProvincia.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                  
                   // e.getOpositeComponent() devuelve el Component que le
                   // cede el foco a nuestro botón.
                }
                public void focusLost(FocusEvent e) {
                    ModificarCliente modificar  = new ModificarCliente(cliente.getId(),"provincia",jTextFieldProvincia.getText() ) ;
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    consultar();
                    actualizar();
                }
                });
         
        jTextFieldEmailAvisos.addFocusListener(new FocusListener() {
                public void focusGained(FocusEvent e) {
                  
                   // e.getOpositeComponent() devuelve el Component que le
                   // cede el foco a nuestro botón.
                }
                public void focusLost(FocusEvent e) {
                    ModificarCliente modificar  = new ModificarCliente(cliente.getId(),"correoAvisos",jTextFieldEmailAvisos.getText() ) ;
                    try {
                        modificar.modificarCliente();
                    } catch (IOException ex) {
                        Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    consultar();
                    actualizar();
                }
                });
        jTextFieldEmailAdmon.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "emailAdmon", jTextFieldEmailAdmon.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        jTextFieldFechaCobro.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "fechaCobro", jTextFieldFechaCobro.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        jTextAreaObservaciones.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            @Override
            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "observacionesAImprimir", jTextAreaObservaciones.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        jTextFieldFechaEntregaFra.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            @Override
            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "fechaEntregaFra", jTextFieldFechaEntregaFra.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

        jTextFieldFormaCobro.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {

                // e.getOpositeComponent() devuelve el Component que le
                // cede el foco a nuestro botón.
            }

            public void focusLost(FocusEvent e) {
                ModificarCliente modificar = new ModificarCliente(cliente.getId(), "formaCobro", jTextFieldFormaCobro.getText());
                try {
                    modificar.modificarCliente();
                } catch (IOException ex) {
                    Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                consultar();
                actualizar();
            }
        });

    }

    public void consultar() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            clientes = new CargarClientesCommand(new CargadorListaCliente(), new ClienteParserJson()).executeCliente(cliente.getId());
        } catch (Exception ex) {
            Logger.getLogger(FichaClienteFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        cliente = clientes.get(0);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jTextFieldTipoCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextPaneGrupo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        dateChooserComboFechaAlta = new datechooser.beans.DateChooserCombo();
        jLabel8 = new javax.swing.JLabel();
        dateChooserComboFechaBaja = new datechooser.beans.DateChooserCombo();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldDireccion = new javax.swing.JTextField();
        jTextFieldPoblacion = new javax.swing.JTextField();
        jTextFieldContacto = new javax.swing.JTextField();
        jTextFieldPuesto = new javax.swing.JTextField();
        jTextFieldProvincia = new javax.swing.JTextField();
        jTextFielCP = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldNIF = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldPais = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldTelfAsistencia = new javax.swing.JTextField();
        jTextFieldTelfFax = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextFieldEmailAdmon = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldMinRetraso = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextFieldNumInfFinalizado = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldNumProveedor = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldEmailAvisos = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldFechaCobro = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldFormaCobro = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldFechaEntregaFra = new javax.swing.JTextField();
        jComboBoxTipoFacturacion = new javax.swing.JComboBox<>();
        checkImprimeKM = new java.awt.Checkbox();
        checkIGIC = new java.awt.Checkbox();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextAreaObservaciones = new javax.swing.JTextArea();
        jScrollPaneBasesOperativas = new javax.swing.JScrollPane();
        checkEdicionCliente = new javax.swing.JCheckBox();
        jButton3 = new javax.swing.JButton();
        jButtonAsociarServicioEspecial = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jTextImpuesto = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setAutoscrolls(true);

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 204));
        jLabel3.setText("Tipo Cliente:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 204));
        jLabel5.setText("Grupo Empresas:");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(102, 102, 102));
        jButton1.setText("Dar De Baja al cliente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel7.setText("Fecha de Alta ");

        dateChooserComboFechaAlta.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
        dateChooserComboFechaAlta.addSelectionChangedListener(new datechooser.events.SelectionChangedListener() {
            public void onSelectionChange(datechooser.events.SelectionChangedEvent evt) {
                dateChooserComboFechaAltaOnSelectionChange(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel8.setText("Fecha de baja ");

        dateChooserComboFechaBaja.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
        dateChooserComboFechaBaja.setEnabled(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, java.awt.Color.blue));

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel9.setText("Dirección:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel10.setText("Población:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel11.setText("Provincia:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel12.setText("Pais:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel15.setText("Contacto:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel16.setText("Puesto:");

        jTextFieldDireccion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTextFieldDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDireccionActionPerformed(evt);
            }
        });

        jTextFieldPoblacion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jTextFieldContacto.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jTextFieldPuesto.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jTextFieldProvincia.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jTextFielCP.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("Nombre:");

        jTextFieldNombre.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setText("NIF:");

        jTextFieldNIF.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel13.setText("CP:");

        jTextFieldPais.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldNombre)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldNIF, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel14))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel15)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextFieldContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel16)
                                .addGap(33, 33, 33)
                                .addComponent(jTextFieldPuesto))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextFieldPais, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel13)
                                .addGap(27, 27, 27)
                                .addComponent(jTextFielCP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(jLabel11)
                                .addGap(42, 42, 42)
                                .addComponent(jTextFieldProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldPoblacion, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldDireccion))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldNombre)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldNIF))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldDireccion))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPoblacion)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFielCP)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldProvincia)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldContacto)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldPuesto))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, java.awt.Color.blue));

        jLabel19.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel19.setText("Telefono asistencia: ");

        jLabel22.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel22.setText("Fax:");

        jTextFieldTelfAsistencia.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTextFieldTelfAsistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTelfAsistenciaActionPerformed(evt);
            }
        });

        jTextFieldTelfFax.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel23.setText("Email Admon:");

        jTextFieldEmailAdmon.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel25.setText("Minimo Retraso");

        jTextFieldMinRetraso.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel31.setText("NºInf. Finalizado");

        jTextFieldNumInfFinalizado.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jButton2.setText("Agenda");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel32.setText("NºProveedor");

        jTextFieldNumProveedor.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel24.setText("Email Avisos:");

        jTextFieldEmailAvisos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel24))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldNumProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jTextFieldNumInfFinalizado, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldMinRetraso, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldTelfFax, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel23)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextFieldEmailAdmon))))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldTelfAsistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEmailAvisos, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldTelfAsistencia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldEmailAvisos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTelfFax)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldEmailAdmon)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldNumProveedor)
                        .addComponent(jLabel32))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldMinRetraso)
                        .addComponent(jTextFieldNumInfFinalizado)))
                .addGap(52, 52, 52))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.yellow, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.yellow));

        jLabel28.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel28.setText("Tipo facturación:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel27.setText("Fecha cobro:");

        jTextFieldFechaCobro.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel26.setText("Forma de cobro:");

        jTextFieldFormaCobro.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel29.setText("Fecha entrega fra:");

        jTextFieldFechaEntregaFra.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jComboBoxTipoFacturacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxTipoFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldFechaCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldFormaCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldFechaEntregaFra, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldFechaCobro)
                    .addComponent(jTextFieldFormaCobro)
                    .addComponent(jTextFieldFechaEntregaFra)
                    .addComponent(jComboBoxTipoFacturacion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        checkImprimeKM.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        checkImprimeKM.setLabel("Imprime KM en Fra");

        checkIGIC.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        checkIGIC.setLabel("Cte Exento de IGIC");

        jLabel30.setFont(new java.awt.Font("Tahoma", 2, 16)); // NOI18N
        jLabel30.setText("Observaciones a imprimir en factura ");

        jTextAreaObservaciones.setColumns(20);
        jTextAreaObservaciones.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTextAreaObservaciones.setRows(5);
        jScrollPane7.setViewportView(jTextAreaObservaciones);

        jScrollPaneBasesOperativas.setBackground(new java.awt.Color(255, 255, 255));

        checkEdicionCliente.setBackground(new java.awt.Color(255, 255, 255));
        checkEdicionCliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        checkEdicionCliente.setText("Permitir editar cliente");

        jButton3.setText("Ver observaciones generales");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButtonAsociarServicioEspecial.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAsociarServicioEspecial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAsociarServicioEspecial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/plus.png"))); // NOI18N
        jButtonAsociarServicioEspecial.setText("Asociar base operativa");
        jButtonAsociarServicioEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAsociarServicioEspecialActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 204));
        jLabel17.setText("IMPUESTO");

        jLabel18.setFont(new java.awt.Font("Tahoma", 2, 13)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 204));
        jLabel18.setText("%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30)
                                            .addComponent(checkEdicionCliente))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3))
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkImprimeKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(checkIGIC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(184, 184, 184)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPaneBasesOperativas)
                            .addComponent(jButtonAsociarServicioEspecial, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(14, 14, 14)
                                .addComponent(jTextFieldTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextPaneGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(295, 295, 295))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateChooserComboFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(dateChooserComboFechaBaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(145, 145, 145))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jTextPaneGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jTextImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dateChooserComboFechaAlta, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                            .addComponent(dateChooserComboFechaBaja, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(14, 14, 14)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkIGIC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkImprimeKM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPaneBasesOperativas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jButtonAsociarServicioEspecial)
                        .addContainerGap(48, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkEdicionCliente)
                            .addComponent(jButton3))
                        .addContainerGap(74, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    public JCheckBox getCheckEdicionCliente() {
        return checkEdicionCliente;
    }
    
    
    
    
    private void jButtonAsociarServicioEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAsociarServicioEspecialActionPerformed

        try {
            AsociarBasesOperativasAClienteFrame asociar = new AsociarBasesOperativasAClienteFrame();
            asociar.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAsociarServicioEspecialActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FrameAgenda f = FrameAgenda.getInstance();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDireccionActionPerformed

    private void jTextFieldTelfAsistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTelfAsistenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTelfAsistenciaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        FrameObservacionesGenerales f = new FrameObservacionesGenerales();
        f.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
         if (cliente.getEstado()== 1) {
           int confirm = JOptionPane.showConfirmDialog(null,"¿Estas seguro de que quieres dar de baja al cliente?");
            if (confirm == 0) {
                    try {
                       Cliente clientePasarela = new Cliente();
                       clientePasarela.setId(SingletonCliente.getInstance().getCliente().getId());
                       SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");


                       String fecha = new SimpleDateFormat("yyyy-MM-dd").format(dateChooserComboFechaBaja.getSelectedDate().getTime());

                       System.out.println(dateChooserComboFechaBaja.getSelectedDate().getTime());

                       clientePasarela.setFechaBaja(fecha);
                       DarBajaCliente dar = new DarBajaCliente(clientePasarela);
                       dar.dar();
                        SingletonCliente.getInstance().actualizarCliente();
                          actualizarCliente();
                          actualizar();
                   } catch (Exception ex) {
                       Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
                   }
           }
        } else  {
              int confirm = JOptionPane.showConfirmDialog(null,"¿Estas seguro de que quieres dar de alta al cliente?");
            if (confirm == 0) {
           
                try {
                   Cliente clientePasarela = new Cliente();
                   clientePasarela.setId(SingletonCliente.getInstance().getCliente().getId());
                   SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");


                   String fecha = new SimpleDateFormat("yyyy-MM-dd").format(dateChooserComboFechaAlta.getSelectedDate().getTime());

                   System.out.println(dateChooserComboFechaAlta.getSelectedDate().getTime());

                   clientePasarela.setFechaAlta(fecha);
                   DarAltaCliente dar = new DarAltaCliente(clientePasarela);
                   dar.dar();
                   
                   SingletonCliente.getInstance().actualizarCliente();
                   actualizarCliente();
                   actualizar();
               } catch (Exception ex) {
                   Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
               }
              }
        
        
        
        }
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void dateChooserComboFechaAltaOnSelectionChange(datechooser.events.SelectionChangedEvent evt) {//GEN-FIRST:event_dateChooserComboFechaAltaOnSelectionChange
        try {
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(dateChooserComboFechaAlta.getSelectedDate().getTime());
            ModificarCliente modificar = new ModificarCliente(SingletonCliente.getInstance().getCliente().getId(), "fechaAlta",fecha);
            modificar.modificarCliente();
        } catch (IOException ex) {
            Logger.getLogger(FichaClienteModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_dateChooserComboFechaAltaOnSelectionChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkEdicionCliente;
    private java.awt.Checkbox checkIGIC;
    private java.awt.Checkbox checkImprimeKM;
    private datechooser.beans.DateChooserCombo dateChooserComboFechaAlta;
    private datechooser.beans.DateChooserCombo dateChooserComboFechaBaja;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAsociarServicioEspecial;
    private javax.swing.JComboBox<String> jComboBoxTipoFacturacion;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPaneBasesOperativas;
    private javax.swing.JTextArea jTextAreaObservaciones;
    private javax.swing.JTextField jTextFielCP;
    private javax.swing.JTextField jTextFieldContacto;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldEmailAdmon;
    private javax.swing.JTextField jTextFieldEmailAvisos;
    private javax.swing.JTextField jTextFieldFechaCobro;
    private javax.swing.JTextField jTextFieldFechaEntregaFra;
    private javax.swing.JTextField jTextFieldFormaCobro;
    private javax.swing.JTextField jTextFieldMinRetraso;
    private javax.swing.JTextField jTextFieldNIF;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNumInfFinalizado;
    private javax.swing.JTextField jTextFieldNumProveedor;
    private javax.swing.JTextField jTextFieldPais;
    private javax.swing.JTextField jTextFieldPoblacion;
    private javax.swing.JTextField jTextFieldProvincia;
    private javax.swing.JTextField jTextFieldPuesto;
    private javax.swing.JTextField jTextFieldTelfAsistencia;
    private javax.swing.JTextField jTextFieldTelfFax;
    private javax.swing.JTextField jTextFieldTipoCliente;
    private javax.swing.JTextField jTextImpuesto;
    private javax.swing.JTextField jTextPaneGrupo;
    // End of variables declaration//GEN-END:variables
}

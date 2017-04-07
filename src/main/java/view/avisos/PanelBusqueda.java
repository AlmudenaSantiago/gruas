/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.avisos;

import DatosPrecargados.SingletonClientes;
import DatosPrecargados.SingletonMarcas;
import command.clientes.CargarClientesCommand;
import command.vehiculos.CargarModelosCommand;
import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Aviso;
import model.Cliente;
import model.vehiculos.Modelo;
import process.CRUDAvisos.EnviarConsultaBusqueda;
import process.CRUDAvisos.ModificarAviso;
import process.CRUDVehiculos.ModificarVehiculo;
import process.cargador.CargadorListaCliente;
import process.cargador.vehiculos.CargadorListaModelo;
import process.parser.clientes.ClienteParserJson;
import process.parser.vehiculos.ModeloParserJson;


public class PanelBusqueda extends javax.swing.JPanel {

     TextFieldBusqueda gtextfieldIdCliente;
     JTablaAvisos tabla;
     TextFieldBusqueda gtextfieldMarcas;
     TextFieldBusqueda gtextfieldModelos;
     HashMap<Object, Object> hashContadorModelos ;
     HashMap<String, Integer> hashContadorIdModelo  = new HashMap<>();
     HashMap<String, Integer> hashContadorIdMarca = new HashMap<>();
         
     
     public PanelBusqueda() {
        initComponents();
        try {
            inicializarComboClientes();
            llenarComboClientes();
            inicializarComboMarcas();
            inicializarComboModelos();
           
            
            tabla = JTablaAvisos.getInstance();
            jScrollPane1.setViewportView(tabla);
            
            
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(PanelBusqueda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
       
       
       public void llenarComboModelosPorMarca(int idMarcaPadre) throws IOException {
       
        CargarModelosCommand cargar = new CargarModelosCommand(new CargadorListaModelo(), new ModeloParserJson());
        List<Modelo> listaModelos = cargar.executePorIdMarcaPadre(idMarcaPadre);
      
        gtextfieldModelos.getDataList().removeAll(gtextfieldModelos.getDataList());
        
        for (int it=0;it<listaModelos.size();it++){
                    hashContadorIdModelo.put(listaModelos.get(it).getModelo(),listaModelos.get(it).getId());
                    gtextfieldModelos.getDataList().add(listaModelos.get(it).getModelo());
         }
         
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
        
         
        for (int it = 0; it < SingletonMarcas.getInstance().getLista().size(); it++) {
                   hashContadorIdMarca.put(SingletonMarcas.getInstance().getLista().get(it).getMarca(),SingletonMarcas.getInstance().getLista().get(it).getId());
       
            gtextfieldMarcas.getDataList().add(SingletonMarcas.getInstance().getLista().get(it).getMarca());

        }
        
        gtextfieldMarcas.getjTable().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                 //   gtextfieldModelos.getDataList().removeAll(gtextfieldModelos.getDataList());
      
            }

            @Override
            public void focusLost(FocusEvent e) {
              
                    
                try {
                    int marcaIdPadre = SingletonMarcas.getInstance().getLista().get(gtextfieldMarcas.getFilaSeleccionada()).getId();
                    llenarComboModelosPorMarca(marcaIdPadre);
                } catch (IOException ex) {
                    Logger.getLogger(PanelBusqueda.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
               
            }
        });
        

    }
    
    
    public void llenarComboClientes() {

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
    
      
    }
    
      public void inicializarComboClientes() throws IOException, Exception {

        gtextfieldIdCliente = new TextFieldBusqueda(0, 0, true);
        gtextfieldIdCliente.setWidthPopupPanel(200);
        gtextfieldIdCliente.setHeightPopupPanel(200);
        gtextfieldIdCliente.setVisible(true);
        jPanelClientes.setLayout(new BorderLayout());
        jPanelClientes.add(gtextfieldIdCliente);


    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelSuperior = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanelClientes = new javax.swing.JPanel();
        jPanelMarca = new javax.swing.JPanel();
        jPanelModelo = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jCheckBoxNombre = new javax.swing.JCheckBox();
        jCheckBoxExpediente = new javax.swing.JCheckBox();
        jCheckBoxBastidor = new javax.swing.JCheckBox();
        jCheckBoxMarca = new javax.swing.JCheckBox();
        jCheckBoxModelo = new javax.swing.JCheckBox();
        jCheckBoxCliente = new javax.swing.JCheckBox();
        jCheckBoxFecha = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jComboBoxNombre = new javax.swing.JComboBox<>();
        jTextFieldNombre = new javax.swing.JTextField();
        jComboBoxExpediente = new javax.swing.JComboBox<>();
        jTextFieldExpediente = new javax.swing.JTextField();
        jComboBoxBastidor = new javax.swing.JComboBox<>();
        jTextFieldBastidor = new javax.swing.JTextField();
        jCheckBoxTelefono = new javax.swing.JCheckBox();
        jComboBoxTelefono = new javax.swing.JComboBox<>();
        jTextFieldTelefono = new javax.swing.JTextField();
        jCheckBoxMatricula = new javax.swing.JCheckBox();
        jComboBoxMatricula = new javax.swing.JComboBox<>();
        jTextFieldMatricula = new javax.swing.JTextField();
        jCheckBoxPoliza = new javax.swing.JCheckBox();
        jComboBoxPoliza = new javax.swing.JComboBox<>();
        jTextFieldPoliza = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        realizar1 = new javax.swing.JLabel();
        dateChooserRealizacionDesde = new datechooser.beans.DateChooserCombo();
        realizar = new javax.swing.JLabel();
        horaRealizacion = new javax.swing.JFormattedTextField();
        horaRealizacionHasta = new javax.swing.JFormattedTextField();
        dateChooserComboRealizacionHasta = new datechooser.beans.DateChooserCombo();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxOrAnd = new javax.swing.JComboBox<>();
        jCheckBoxId = new javax.swing.JCheckBox();
        jTextFieldId = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        jPanelSuperior.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/avisos/logo_gruas.png"))); // NOI18N

        jPanelClientes.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanelClientesLayout = new javax.swing.GroupLayout(jPanelClientes);
        jPanelClientes.setLayout(jPanelClientesLayout);
        jPanelClientesLayout.setHorizontalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelClientesLayout.setVerticalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelMarca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelMarca.setMaximumSize(new java.awt.Dimension(200, 30));
        jPanelMarca.setMinimumSize(new java.awt.Dimension(200, 30));
        jPanelMarca.setLayout(new java.awt.BorderLayout());

        jPanelModelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelModelo.setMinimumSize(new java.awt.Dimension(200, 30));
        jPanelModelo.setLayout(new java.awt.BorderLayout());

        jLabel19.setText("Resultados:");

        jCheckBoxNombre.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxNombre.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jCheckBoxNombre.setText("Por nombre");

        jCheckBoxExpediente.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxExpediente.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jCheckBoxExpediente.setText("Por expediente");

        jCheckBoxBastidor.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxBastidor.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jCheckBoxBastidor.setText("Por bastidor");

        jCheckBoxMarca.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxMarca.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jCheckBoxMarca.setText("Por marca");

        jCheckBoxModelo.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxModelo.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jCheckBoxModelo.setText("Por modelo");

        jCheckBoxCliente.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxCliente.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jCheckBoxCliente.setText("Por cliente");
        jCheckBoxCliente.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxClienteStateChanged(evt);
            }
        });

        jCheckBoxFecha.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxFecha.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jCheckBoxFecha.setText("Por fecha ");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 153));
        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBoxNombre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exactamente", "Contiene", "Empieza por" }));
        jComboBoxNombre.setPreferredSize(new java.awt.Dimension(80, 30));

        jTextFieldNombre.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldNombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldNombre.setMinimumSize(new java.awt.Dimension(10, 20));

        jComboBoxExpediente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exactamente", "Contiene", "Empieza por" }));
        jComboBoxExpediente.setPreferredSize(new java.awt.Dimension(88, 30));
        jComboBoxExpediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxExpedienteActionPerformed(evt);
            }
        });

        jTextFieldExpediente.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldExpediente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldExpediente.setPreferredSize(new java.awt.Dimension(10, 20));

        jComboBoxBastidor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exactamente", "Contiene", "Empieza por" }));

        jTextFieldBastidor.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldBastidor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldBastidor.setPreferredSize(new java.awt.Dimension(10, 20));
        jTextFieldBastidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBastidorActionPerformed(evt);
            }
        });

        jCheckBoxTelefono.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxTelefono.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jCheckBoxTelefono.setText("Por telefono");

        jComboBoxTelefono.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exactamente", "Contiene", "Empieza por" }));
        jComboBoxTelefono.setPreferredSize(new java.awt.Dimension(80, 30));

        jTextFieldTelefono.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldTelefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldTelefono.setPreferredSize(new java.awt.Dimension(10, 20));

        jCheckBoxMatricula.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxMatricula.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jCheckBoxMatricula.setText("Por matricula ");

        jComboBoxMatricula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exactamente", "Contiene", "Empieza por" }));
        jComboBoxMatricula.setMinimumSize(new java.awt.Dimension(80, 30));

        jTextFieldMatricula.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldMatricula.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldMatricula.setPreferredSize(new java.awt.Dimension(10, 20));

        jCheckBoxPoliza.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxPoliza.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jCheckBoxPoliza.setText("Por poliza");

        jComboBoxPoliza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exactamente", "Contiene", "Empieza por" }));

        jTextFieldPoliza.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldPoliza.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextFieldPoliza.setMinimumSize(new java.awt.Dimension(10, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));

        realizar1.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        realizar1.setText("Desde");

        dateChooserRealizacionDesde.setCurrentView(new datechooser.view.appearance.AppearancesList("Light",
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

    realizar.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
    realizar.setText("Hasta");

    horaRealizacion.setBackground(new java.awt.Color(204, 204, 204));
    horaRealizacion.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
    horaRealizacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);

    horaRealizacionHasta.setBackground(new java.awt.Color(204, 204, 204));
    horaRealizacionHasta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("HH:mm"))));
    horaRealizacionHasta.setHorizontalAlignment(javax.swing.JTextField.CENTER);

    dateChooserComboRealizacionHasta.setCurrentView(new datechooser.view.appearance.AppearancesList("Light",
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

javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
jPanel1.setLayout(jPanel1Layout);
jPanel1Layout.setHorizontalGroup(
    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(realizar1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dateChooserRealizacionDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(horaRealizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(realizar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dateChooserComboRealizacionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(horaRealizacionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(27, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(dateChooserRealizacionDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(realizar1)
                .addComponent(horaRealizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(realizar)
                    .addComponent(horaRealizacionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(dateChooserComboRealizacionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(20, Short.MAX_VALUE))
    );

    jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel1.setText("Conjunción de la consulta múltiple:");

    jComboBoxOrAnd.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "O  (OR)", "Y  (AND)" }));

    jCheckBoxId.setBackground(new java.awt.Color(255, 255, 255));
    jCheckBoxId.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
    jCheckBoxId.setText("Por id");

    jTextFieldId.setBackground(new java.awt.Color(204, 204, 204));
    jTextFieldId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

    jButton2.setBackground(new java.awt.Color(255, 255, 102));
    jButton2.setForeground(new java.awt.Color(0, 51, 204));
    jButton2.setText("VER TODOS LOS AVISOS");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanelSuperiorLayout = new javax.swing.GroupLayout(jPanelSuperior);
    jPanelSuperior.setLayout(jPanelSuperiorLayout);
    jPanelSuperiorLayout.setHorizontalGroup(
        jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                    .addComponent(jScrollPane1)
                    .addGap(10, 10, 10))
                .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                    .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                                        .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBoxBastidor, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCheckBoxTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                                        .addComponent(jCheckBoxMatricula)
                                        .addGap(26, 26, 26)))
                                .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                                    .addComponent(jCheckBoxPoliza)
                                    .addGap(52, 52, 52)))
                            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBoxBastidor, 0, 102, Short.MAX_VALUE)
                                .addComponent(jComboBoxTelefono, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxMatricula, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxPoliza, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                                    .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextFieldMatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jTextFieldPoliza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                    .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jCheckBoxModelo)
                                            .addGap(18, 18, 18))
                                        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(jCheckBoxMarca)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(105, 105, 105))
                                        .addComponent(jPanelModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanelMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jTextFieldBastidor, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                                            .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(47, 47, 47)
                                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(118, 118, 118)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBoxOrAnd, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                                    .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                                            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jCheckBoxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jCheckBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(35, 35, 35)
                                            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                                                    .addComponent(jComboBoxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jPanelClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                                            .addComponent(jCheckBoxExpediente)
                                            .addGap(18, 18, 18)
                                            .addComponent(jComboBoxExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jTextFieldExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBoxFecha)
                                        .addComponent(jCheckBoxId, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(0, 81, Short.MAX_VALUE)))
                    .addContainerGap())))
    );
    jPanelSuperiorLayout.setVerticalGroup(
        jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                    .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jComboBoxOrAnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(28, 28, 28)))
            .addGap(11, 11, 11)
            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jCheckBoxCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jPanelClientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBoxId)
                    .addComponent(jTextFieldId)))
            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                    .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBoxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBoxNombre))
                            .addGap(15, 15, 15)
                            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jCheckBoxExpediente)
                                .addComponent(jComboBoxExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(15, 15, 15)
                            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jCheckBoxBastidor)
                                .addComponent(jComboBoxBastidor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldBastidor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(15, 15, 15))
                        .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(jCheckBoxFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBoxTelefono)
                        .addComponent(jComboBoxTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBoxMarca))
                    .addGap(18, 18, 18)
                    .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBoxMatricula)
                        .addComponent(jComboBoxMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBoxModelo)))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSuperiorLayout.createSequentialGroup()
                    .addGap(44, 44, 44)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jPanelMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jPanelModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(18, 18, 18)
            .addGroup(jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCheckBoxPoliza)
                .addComponent(jComboBoxPoliza, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextFieldPoliza, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton1))
            .addGap(20, 20, 20)
            .addComponent(jLabel19)
            .addGap(8, 8, 8)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
            .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jPanelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBastidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBastidorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBastidorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        String consulta = "select * from avisos  where ";
        
        Integer contador = 0;
        
        
        if (jCheckBoxMarca.isSelected()) {
          if (contador > 1) {
               if (jComboBoxOrAnd.getSelectedIndex() == 0) {
                   consulta =  consulta.concat( " OR ");
                   } else {
                   consulta =  consulta.concat( " AND ");
                   }
            
            } else {
                  Integer idMarca =  hashContadorIdMarca.get(gtextfieldMarcas.getText());
                  consulta = consulta.concat("  idMarca   = '" + idMarca +  "'");
            }
        }
       
         if (jCheckBoxModelo.isSelected() ) {
            
               if (jComboBoxOrAnd.getSelectedIndex() == 0) {
                   consulta =  consulta.concat( " OR ");
                   } else {
                   consulta =  consulta.concat( " AND ");
                   }
            
              
                  Integer idModelo =  hashContadorIdModelo.get(gtextfieldModelos.getText());
                  consulta = consulta.concat("  idModelo   = '" + idModelo +  "'");
             
        }
       
        
        
        if (jCheckBoxCliente.isSelected()) {
            
            contador++;
            
            if (contador > 1) {
               if (jComboBoxOrAnd.getSelectedIndex() == 0) {
                   consulta =  consulta.concat( " OR ");
                   } else {
                   consulta =  consulta.concat( " AND ");
                   }
            
            } else {
                
                  StringTokenizer st = new StringTokenizer(gtextfieldIdCliente.getText(), "()");
                  String idCliente = st.nextToken();
                  consulta = consulta.concat("  idCliente  = '" + idCliente.trim()  + "'");
            
            }
                
        }
       
       
        if (jCheckBoxNombre.isSelected()) {
                contador++;
                if (contador > 1) {
                 if (jComboBoxOrAnd.getSelectedIndex() == 0) {
                   consulta =  consulta.concat( " OR ");
                   } else {
                   consulta =  consulta.concat( " AND ");
                   }
                }
                    switch (jComboBoxNombre.getSelectedIndex()) {
                        case 0:
                            consulta = consulta.concat("  nombre   = '" + jTextFieldNombre.getText() +  "'");
                            break;
                        case 1:
                            consulta = consulta.concat("  nombre  LIKE '%" + jTextFieldNombre.getText() +  "%'");
                            break;
                        case 2:
                            consulta = consulta.concat("  nombre  LIKE '" + jTextFieldNombre.getText() +  "%'"); 
                            break;
                      
                    }
                  
                            
            
        }
       
        
        
        if (jCheckBoxBastidor.isSelected()) {
               contador++;
                if (contador > 1) {
                   if (jComboBoxOrAnd.getSelectedIndex() == 0) {
                   consulta =  consulta.concat( " OR ");
                   } else {
                   consulta =  consulta.concat( " AND ");
                   }
                }
                    switch (jComboBoxBastidor.getSelectedIndex()) {
                        case 0:
                            consulta = consulta.concat(" bastidor   = '" + jTextFieldBastidor.getText() +  "'");
                            break;
                        case 1:
                            consulta = consulta.concat("  bastidor  LIKE '%" + jTextFieldBastidor.getText() +  "%'"); 
                            break;
                        case 2:
                            consulta = consulta.concat("  bastidor  LIKE '" + jTextFieldBastidor.getText() +  "%'");
                            break;
                      
                    }
             
                    
        }
        
        
         if (jCheckBoxExpediente.isSelected()) {
               contador++;
                if (contador > 1) {
                   if (jComboBoxOrAnd.getSelectedIndex() == 0) {
                   consulta =  consulta.concat( " OR ");
                   } else {
                   consulta =  consulta.concat( " AND ");
                   }
                }
                    switch (jComboBoxExpediente.getSelectedIndex()) {
                        case 0:
                            consulta = consulta.concat(" expediente   = '" + jTextFieldExpediente.getText() +  "'");
                            break;
                        case 1:
                            consulta = consulta.concat("  expediente  LIKE '%" + jTextFieldExpediente.getText() +  "%'"); 
                            break;
                        case 2:
                            consulta = consulta.concat("  expediente  LIKE '" + jTextFieldExpediente.getText() +  "%'"); 
                            break;
                      
                    }
            
        }
        
         if (jCheckBoxPoliza.isSelected()) {
             contador++;
             if (contador > 1) {
                  if (jComboBoxOrAnd.getSelectedIndex() == 0) {
                   consulta =  consulta.concat( " OR ");
                   } else {
                   consulta =  consulta.concat( " AND ");
                   }
                }
                    switch (jComboBoxPoliza.getSelectedIndex()) {
                        case 0:
                            consulta = consulta.concat(" poliza   = '" + jTextFieldPoliza.getText() +  "'");
                            break;
                        case 1:
                            consulta = consulta.concat("  poliza  LIKE '%" + jTextFieldPoliza.getText() +  "%'"); 
                            break;
                        case 2:
                            consulta = consulta.concat("  poliza  LIKE '" + jTextFieldPoliza.getText() +  "%'"); 
                            break;
                      
                    }
             
         }
         
         if (jCheckBoxId.isSelected()) {
            
               contador++;
                if (contador > 1) {
                   if (jComboBoxOrAnd.getSelectedIndex() == 0) {
                      consulta =  consulta.concat( " OR ");
                   } else {
                      consulta =  consulta.concat( " AND ");
                   }
                }
                    consulta = consulta.concat(" id = '" + jTextFieldId.getText() +  "'");
                          
                
                
         }
         
         if (jCheckBoxMatricula.isSelected()) {
              contador++;  
             if (contador > 1) {
                  if (jComboBoxOrAnd.getSelectedIndex() == 0) {
                   consulta =  consulta.concat( " OR ");
                   } else {
                   consulta =  consulta.concat( " AND ");
                   }
                }
                    switch (jComboBoxMatricula.getSelectedIndex()) {
                        case 0:
                            consulta = consulta.concat(" matricula   = '" + jTextFieldMatricula.getText() +  "'");
                            break;
                        case 1:
                            consulta = consulta.concat("  matricula  LIKE '%" + jTextFieldMatricula.getText() +  "%'");
                            break;
                        case 2:
                            consulta = consulta.concat("  matricula  LIKE '" + jTextFieldMatricula.getText() +  "%'");
                            break;
                      
                    }
             
         }
       
         
         
         if (jCheckBoxTelefono.isSelected()) {
            
               contador++;
                if (contador > 1) {
                   if (jComboBoxOrAnd.getSelectedIndex() == 0) {
                      consulta =  consulta.concat( " OR ");
                   } else {
                      consulta =  consulta.concat( " AND ");
                   }
                }
                    switch (jComboBoxTelefono.getSelectedIndex()) {
                        case 0:
                            consulta = consulta.concat(" telefono   = '" + jTextFieldTelefono.getText() +  "'");
                            break;
                        case 1:
                            consulta = consulta.concat("  telefono  LIKE '%" + jTextFieldTelefono.getText() +  "%'"); 
                            break;
                        case 2:
                            consulta = consulta.concat("  telefono  LIKE '" + jTextFieldTelefono.getText() +  "%'");
                            break;
                      
                    }
          
         }
         
         
         if (jCheckBoxFecha.isSelected()) {
            contador++;
                if (contador > 1) {
                   if (jComboBoxOrAnd.getSelectedIndex() == 0) {
                      consulta =  consulta.concat( " OR ");
                   } else {
                      consulta =  consulta.concat( " AND ");
                   }
                } else {
                        String fechaDesde = new SimpleDateFormat("yyyy-MM-dd").format(dateChooserRealizacionDesde.getSelectedDate().getTime());
                        String horaMasFechaDesde =  fechaDesde + " " + horaRealizacion.getText() + ":00";
                        String fechaHasta = new SimpleDateFormat("yyyy-MM-dd").format(dateChooserComboRealizacionHasta.getSelectedDate().getTime());
                        String horaMasFechaHasta =  fechaDesde + " " + horaRealizacionHasta.getText() + ":00";
                       
                        consulta = consulta.concat("  fechaRealizacion >=  '" + horaMasFechaDesde  + "' AND fechaRealizacion <= '" + horaMasFechaHasta + "'");
                           
                    
                }
             
             
             
             
         }
         
      
         
         Aviso avisoAux = new Aviso();
         avisoAux.setNombre(consulta);
         System.out.println("YO ENVIO " + consulta);
         EnviarConsultaBusqueda enviar = new EnviarConsultaBusqueda(avisoAux);
         try {
            List<Aviso> listaResultados  =  enviar.enviar();
           
            if (listaResultados == null  )
                listaResultados = new ArrayList();
           
               tabla.mostrar(listaResultados);
            
         } catch (IOException ex) {
             Logger.getLogger(PanelBusqueda.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
       
         
         
         
         
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBoxClienteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxClienteStateChanged
        
    }//GEN-LAST:event_jCheckBoxClienteStateChanged

    private void jComboBoxExpedienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxExpedienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxExpedienteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
         Aviso avisoAux = new Aviso();
         avisoAux.setNombre("select * from avisos");
         EnviarConsultaBusqueda enviar = new EnviarConsultaBusqueda(avisoAux);
         try {
            List<Aviso> listaResultados  =  enviar.enviar();
           
            if (listaResultados == null  )
                listaResultados = new ArrayList();
           
               tabla.mostrar(listaResultados);
            
         } catch (IOException ex) {
             Logger.getLogger(PanelBusqueda.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo dateChooserComboRealizacionHasta;
    private datechooser.beans.DateChooserCombo dateChooserRealizacionDesde;
    private javax.swing.JFormattedTextField horaRealizacion;
    private javax.swing.JFormattedTextField horaRealizacionHasta;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBoxBastidor;
    private javax.swing.JCheckBox jCheckBoxCliente;
    private javax.swing.JCheckBox jCheckBoxExpediente;
    private javax.swing.JCheckBox jCheckBoxFecha;
    private javax.swing.JCheckBox jCheckBoxId;
    private javax.swing.JCheckBox jCheckBoxMarca;
    private javax.swing.JCheckBox jCheckBoxMatricula;
    private javax.swing.JCheckBox jCheckBoxModelo;
    private javax.swing.JCheckBox jCheckBoxNombre;
    private javax.swing.JCheckBox jCheckBoxPoliza;
    private javax.swing.JCheckBox jCheckBoxTelefono;
    private javax.swing.JComboBox<String> jComboBoxBastidor;
    private javax.swing.JComboBox<String> jComboBoxExpediente;
    private javax.swing.JComboBox<String> jComboBoxMatricula;
    private javax.swing.JComboBox<String> jComboBoxNombre;
    private javax.swing.JComboBox<String> jComboBoxOrAnd;
    private javax.swing.JComboBox<String> jComboBoxPoliza;
    private javax.swing.JComboBox<String> jComboBoxTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelClientes;
    private javax.swing.JPanel jPanelMarca;
    private javax.swing.JPanel jPanelModelo;
    private javax.swing.JPanel jPanelSuperior;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldBastidor;
    private javax.swing.JTextField jTextFieldExpediente;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldMatricula;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPoliza;
    private javax.swing.JTextField jTextFieldTelefono;
    private javax.swing.JLabel realizar;
    private javax.swing.JLabel realizar1;
    // End of variables declaration//GEN-END:variables
}

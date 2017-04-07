/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.fichasVehiculos;
import java.awt.Image;
import DatosPrecargados.SingletonMarcas;
import DatosPrecargados.SingletonTiposTarifas;

import command.vehiculos.CargarModelosCommand;
import command.vehiculos.CargarVehiculosCommand;
import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.vehiculos.Vehiculo;
import model.vehiculos.Marca;
import model.vehiculos.Modelo;
import process.CRUDMarcas.InsertarMarca;
import process.CRUDModelos.InsertarModelo;
import process.CRUDVehiculos.EliminarVehiculo;
import process.CRUDVehiculos.ModificarVehiculo;
import process.cargador.vehiculos.CargadorListaModelo;
import process.cargador.vehiculos.CargadorListaVehiculo;
import process.parser.vehiculos.ModeloParserJson;
import process.parser.vehiculos.VehiculoParserJson;
import view.SelectorDeRutaDeImagenes;
import view.avisos.AvisoFrame;
import view.avisos.TextFieldBusqueda;
import view.marcas.MarcasCRUDFrame;

/**
 *
 * @author gruasjoseantonio
 */
public class FichaVehiculo extends javax.swing.JFrame {

    
    private  Vehiculo vehiculo;
    private  TextFieldBusqueda gtextfieldMarcas;
    private  TextFieldBusqueda gtextfieldModelos;
    private  TextFieldBusqueda gtextfieldTipos;   
    private  File fichero;     
    private  Boolean cambio = false;
   
    
    public FichaVehiculo(Vehiculo vehiculo) {
         this.vehiculo = vehiculo;
         
         initComponents();
         
         inicializarComboMarcas(); // este metodo inicializa los modelos segun la marca del vehiculo
         inicializarComboTipos();
         setCaracteristicas();
         setListenerNuevaMarca();
         setListenerNuevoModelo();
         setListenerEditarImagen();
         setListenerEditarFicha();
         setListenerActualizarAlCerrar();
          gtextfieldModelos.setEditable(false);
                            gtextfieldMarcas.setEditable(false);
                            jCheckBoxNuevaMarca.setEnabled(false);
                            jCheckBoxNuevoModelo.setEnabled(false);
                            jTextFieldPeso.setEditable(false);
                            jTextFieldLargo.setEditable(false);
                            jTextFieldAlto.setEditable(false);
                            gtextfieldTipos.setEditable(false);
         gtextfieldMarcas.requestFocus();
    }

   

 
    public void inicializarComboMarcas() {
          gtextfieldMarcas = new TextFieldBusqueda(0,0,true);
          gtextfieldMarcas.setWidthPopupPanel(200);
          gtextfieldMarcas.setHeightPopupPanel(200);
          gtextfieldMarcas.setVisible(true);
       
          jPanelMarca.setLayout(new BorderLayout());
          
          jPanelMarca.add(gtextfieldMarcas);
          
          gtextfieldMarcas.setEspecificTextInTextField(vehiculo.getMarca());
          try {
                llenarComboMarcas();
            } catch (IOException ex) {
                Logger.getLogger(FichaVehiculo.class.getName()).log(Level.SEVERE, null, ex);
           }
           // lleno los modelos segun la marca 
           inicializarComboModelos();
           
      
    
    }
    
       public void inicializarComboModelos() {
    
          gtextfieldModelos  = new TextFieldBusqueda(0,0,true);
          gtextfieldModelos.setWidthPopupPanel(200);
          gtextfieldModelos.setHeightPopupPanel(200);
          gtextfieldModelos.setVisible(true);
          jPanelModelo.add(gtextfieldModelos);
          gtextfieldModelos.setEspecificTextInTextField(vehiculo.getModelo());
            try {
                llenarComboModelosPorMarca(vehiculo.getIdMarca());
            } catch (IOException ex) {
                Logger.getLogger(FichaVehiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    
       }
       
       
      public void inicializarComboTipos() {
    
          gtextfieldTipos = new TextFieldBusqueda(0,0,true);
          gtextfieldTipos.setWidthPopupPanel(200);
          gtextfieldTipos.setHeightPopupPanel(200);
          gtextfieldTipos.setVisible(true);
      
          jPanelTipo.add(gtextfieldTipos);
          gtextfieldTipos.setEspecificTextInTextField(vehiculo.getTipo());
          llenarComboTipos();
      
          
    } 
     
      
      public void setListenerActualizarAlCerrar() {
      
         addWindowListener( new WindowAdapter() {
                @Override
                public void windowClosing( WindowEvent evt ) {
                      if (cambio) {
                          VehiculosFrame.getInstance().actualizarListaVehiculos();
                      }
                    
                }
            });
      
      }
      
      
      public void setListenerEditarImagen() {
        
          
          jPanelImagen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               
                   if (e.getClickCount() ==  2) {
                       int resultado;


                        CargarFoto ventana = new CargarFoto();

                        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG","jpg","png");

                        ventana.jFichCargarFoto.setFileFilter(filtro);

                        resultado= ventana.jFichCargarFoto.showOpenDialog(null);


                        if (JFileChooser.APPROVE_OPTION == resultado){


                                fichero = ventana.jFichCargarFoto.getSelectedFile();

                               // COPIAMOS LA IMAGEN EN EL SERVIDOR Y LA GUARDAMOS EN EL VEHICULO EN LA BASE DE DATOS
                                
                               FileCopy filecopy = new FileCopy(fichero.getAbsolutePath(),"C:\\xampp\\htdocs\\gruas\\gruas\\imagenes\\" + fichero.getName()); 
                               String compruebaCopia = filecopy.copiar();
                                if (compruebaCopia.equals("ok")) {
                                        ModificarVehiculo modificar = new ModificarVehiculo(vehiculo.getId(),"imagen","C:\\xampp\\htdocs\\gruas\\gruas\\imagenes\\" + fichero.getName());
                                        try {
                                            modificar.modificar();

                                        } catch (IOException ex) {
                                            
                                            Logger.getLogger(FichaVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        actualizarVehiculo();
                                        cambio = true;
                                        try{

                                                ImageIcon icon = new ImageIcon("C:\\xampp\\htdocs\\gruas\\gruas\\imagenes\\" + fichero.getName());

                                                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(jLabelImagen.getWidth(), jLabelImagen.getHeight(), Image.SCALE_DEFAULT));

                                                jLabelImagen.setText(null);

                                                jLabelImagen.setIcon(icono);

                                            }catch(Exception ex){
                                                    JOptionPane.showMessageDialog(null, "Error abriendo la imagen "+ ex);
                                             }
                                        }
                         }
                      }
                     }
               
                });
      }
       
       
     public void llenarComboMarcas() throws IOException {
         
        HashMap<Object, Object> hashContadorMarcas = new HashMap<>();
        final HashMap<String, Integer> hashContadorIdMarca = new HashMap<>();
              
       // borramos todos los elementos de la lista por si estamos actualizando que no se vean los elementos dobles 
        gtextfieldMarcas.getDataList().removeAll(gtextfieldMarcas.getDataList());

         for (int it=0;it<SingletonMarcas.getInstance().getLista().size();it++){
                    hashContadorMarcas.put(SingletonMarcas.getInstance().getLista().get(it).getId(), it);
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
                   
                    // si ha cambiado de marca la modifico
                   if (!gtextfieldMarcas.getText().equals(vehiculo.getMarca()) && !gtextfieldMarcas.getText().equals(""))  {
                        ModificarVehiculo modificar = new ModificarVehiculo(vehiculo.getId(),"marca",hashContadorIdMarca.get(gtextfieldMarcas.getText()).toString());
                        modificar.modificar();
                        actualizarVehiculo();
                        gtextfieldModelos.setText("");
                        cambio = true;
                  
                    }
                    gtextfieldModelos.requestFocus();
                    
                     
                } catch (IOException ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });

       }

     
 
          
    public void llenarComboModelosPorMarca(int idMarcaPadre) throws IOException {
        HashMap<Object, Object> hashContadorModelos  = new HashMap<>();
        // hashmap con clave nombre y valor id para buscar por el nombre seleccionado
        final HashMap<String, Integer> hashContadorIdModelo  = new HashMap<>();
       
        CargarModelosCommand cargar = new CargarModelosCommand(new CargadorListaModelo(), new ModeloParserJson());
        List<Modelo> listaModelos = cargar.executePorIdMarcaPadre(idMarcaPadre);
      
        gtextfieldModelos.getDataList().removeAll(gtextfieldModelos.getDataList());
        
        for (int it=0;it<listaModelos.size();it++){
                    hashContadorModelos.put(listaModelos.get(it).getId(), it);
                    hashContadorIdModelo.put(listaModelos.get(it).getModelo(),listaModelos.get(it).getId());
                    gtextfieldModelos.getDataList().add(listaModelos.get(it).getModelo());
         }
         
          gtextfieldModelos.getjTable().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                 }

            @Override
            public void focusLost(FocusEvent e) {
               try {
                   if (!vehiculo.getModelo().equals(gtextfieldModelos.getText()) && !gtextfieldModelos.getText().equals("")) {
                   
                       ModificarVehiculo modificar = new ModificarVehiculo(vehiculo.getId(),"modelo",hashContadorIdModelo.get(gtextfieldModelos.getText()).toString());
                       modificar.modificar();
                       actualizarVehiculo();
                       cambio = true;
                   
                   }
                   gtextfieldTipos.requestFocus();
                } catch (Exception ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });

         
 
      }
    

    
    public void llenarComboTipos() {
    
         HashMap<Object, Object> hashContador = new HashMap<>();
            final HashMap<String, Integer> hashContadorIdTipo = new HashMap<>();
     
        // borramos todos los elementos de la lista por si estamos actualizando que no se vean los elementos dobles 
        gtextfieldTipos.getDataList().removeAll(gtextfieldTipos.getDataList());

         for (int it=0;it<SingletonTiposTarifas.getInstance().getLista().size();it++){
                    hashContador.put(SingletonTiposTarifas.getInstance().getLista().get(it).getId(), it);
                    hashContadorIdTipo.put(SingletonTiposTarifas.getInstance().getLista().get(it).getTipo(), SingletonTiposTarifas.getInstance().getLista().get(it).getId());
                    gtextfieldTipos.getDataList().add(SingletonTiposTarifas.getInstance().getLista().get(it).getTipo());
                             
         }
         
         gtextfieldTipos.getjTable().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                 }

            @Override
            public void focusLost(FocusEvent e) {
               try {
                   
                   ModificarVehiculo modificar = new ModificarVehiculo(vehiculo.getId(),"tipo",hashContadorIdTipo.get(gtextfieldTipos.getText()).toString());
                   modificar.modificar();
                   actualizarVehiculo();
                     cambio = true;
                   gtextfieldTipos.requestFocus();
                   
                   
                } catch (Exception ex) {
                    Logger.getLogger(AvisoFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
         
         
         
          
    
    
    }
    
    public void actualizarVehiculo() {
          CargarVehiculosCommand cargar = new CargarVehiculosCommand(new CargadorListaVehiculo(), new VehiculoParserJson());
          List<Vehiculo> lista = null;
            try {
                lista = cargar.executePorId(1);
            } catch (IOException ex) {
                Logger.getLogger(FichaVehiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
          this.vehiculo = lista.get(0);
                 
    
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelMarca = new javax.swing.JPanel();
        jPanelModelo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanelTipo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jCheckBoxEditar = new javax.swing.JCheckBox();
        botonNuevaGrua = new javax.swing.JButton();
        botonEliminarGrua = new javax.swing.JButton();
        jPanelImagen = new javax.swing.JPanel();
        jLabelImagen = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldLargo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldAlto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldPeso = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jCheckBoxNuevaMarca = new javax.swing.JCheckBox();
        jCheckBoxNuevoModelo = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ficha Vehiculo");
        setExtendedState(6);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanelMarca.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelMarca.setLayout(new java.awt.BorderLayout());

        jPanelModelo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelModelo.setPreferredSize(new java.awt.Dimension(246, 37));
        jPanelModelo.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Marca");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Modelo");

        jPanelTipo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelTipo.setPreferredSize(new java.awt.Dimension(243, 37));
        jPanelTipo.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Tipo");

        jCheckBoxEditar.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBoxEditar.setText("Permitir editar ficha");
        jCheckBoxEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxEditarActionPerformed(evt);
            }
        });

        botonNuevaGrua.setBackground(new java.awt.Color(255, 255, 255));
        botonNuevaGrua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonNuevaGrua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichaCliente/plus.png"))); // NOI18N
        botonNuevaGrua.setText("Nuevo vehiculo");
        botonNuevaGrua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNuevaGruaActionPerformed(evt);
            }
        });

        botonEliminarGrua.setBackground(new java.awt.Color(255, 255, 255));
        botonEliminarGrua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botonEliminarGrua.setText("Eliminar vehiculo");
        botonEliminarGrua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarGruaActionPerformed(evt);
            }
        });

        jPanelImagen.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue));

        jLabelImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImagen.setText("FOTO");

        javax.swing.GroupLayout jPanelImagenLayout = new javax.swing.GroupLayout(jPanelImagen);
        jPanelImagen.setLayout(jPanelImagenLayout);
        jPanelImagenLayout.setHorizontalGroup(
            jPanelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelImagenLayout.setVerticalGroup(
            jPanelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagenLayout.createSequentialGroup()
                .addComponent(jLabelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 153));
        jLabel7.setText("FICHA VEHICULO");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 153));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Largo");

        jTextFieldLargo.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jTextFieldLargo.setForeground(new java.awt.Color(0, 0, 153));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Alto");

        jTextFieldAlto.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jTextFieldAlto.setForeground(new java.awt.Color(0, 0, 153));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Peso");

        jTextFieldPeso.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jTextFieldPeso.setForeground(new java.awt.Color(0, 0, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLargo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldAlto, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldLargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldAlto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichasVehiculos/iconoconfiguracion.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/fichasVehiculos/iconoconfiguracion.png"))); // NOI18N

        jCheckBoxNuevaMarca.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxNuevaMarca.setText("Nueva marca");

        jCheckBoxNuevoModelo.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxNuevoModelo.setText("Nuevo modelo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxNuevaMarca)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jPanelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addComponent(jPanelModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jPanelMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jCheckBoxNuevoModelo))))
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jPanelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jCheckBoxEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(botonNuevaGrua, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botonEliminarGrua, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxNuevaMarca)
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addComponent(jLabel1))
                                .addComponent(jPanelMarca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBoxNuevoModelo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanelModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonNuevaGrua, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEliminarGrua, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxEditar)
                .addGap(183, 183, 183))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonNuevaGruaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNuevaGruaActionPerformed
       
        //SeleccionNuevoVehiculoFrame seleccion = new SeleccionNuevoVehiculoFrame() ;
       // seleccion.setVisible(true);
       /*
        try {
            Vehiculo vehiculoR = new Vehiculo();
            InsertarVehiculo registrador = new InsertarVehiculo(vehiculoR);
            Integer id = registrador.inserta();
            actualizarVehiculo();
            //SingletonGruas.getInstance().actualizarLista();
            /// tabla.mostrarGruas(SingletonGruas.getInstance().getLista());
        } catch (Exception ex) {
            Logger.getLogger(FichaVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        */

    }//GEN-LAST:event_botonNuevaGruaActionPerformed

    public void setCaracteristicas () {
        jTextFieldPeso.setText(vehiculo.getPeso().toString());
        jTextFieldAlto.setText(vehiculo.getAlto().toString());
        jTextFieldLargo.setText(vehiculo.getLargo().toString());
        
        if (!vehiculo.getImagen().equals("")) {
           ImageIcon icon = new ImageIcon(vehiculo.getImagen());
           Icon icono = new ImageIcon(icon.getImage().getScaledInstance(jLabelImagen.getWidth(), jLabelImagen.getHeight(), Image.SCALE_DEFAULT));
          jLabelImagen.setText(null);
          jLabelImagen.setIcon(icono);
        }

       
    
    
    
    }
    
      public void setListenerEditarFicha() {
            jCheckBoxEditar.addItemListener(new ItemListener() {
               @Override
               public void itemStateChanged(ItemEvent e) {
                       
                   if (jCheckBoxEditar.isSelected()) {
                            
                            gtextfieldMarcas.setEditable(true);
                            gtextfieldModelos.setEditable(true);
                            jCheckBoxNuevoModelo.setEnabled(true);
                            jCheckBoxNuevaMarca.setEnabled(true);
                            jTextFieldPeso.setEditable(true);
                            jTextFieldLargo.setEditable(true);
                            jTextFieldAlto.setEditable(true);
                            gtextfieldTipos.setEditable(true);
                            
                         
                            
                    } else {
                            gtextfieldModelos.setEditable(false);
                            gtextfieldMarcas.setEditable(false);
                            jCheckBoxNuevaMarca.setEnabled(false);
                            jCheckBoxNuevoModelo.setEnabled(false);
                            jTextFieldPeso.setEditable(false);
                            jTextFieldLargo.setEditable(false);
                            jTextFieldAlto.setEditable(false);
                            gtextfieldTipos.setEditable(false);
                   }
                   
                   
               }
             });
    }
    
    
      public void setListenerNuevaMarca() {
            jCheckBoxNuevaMarca.addItemListener(new ItemListener() {
               @Override
               public void itemStateChanged(ItemEvent e) {
                       
                   if (jCheckBoxNuevaMarca.isSelected()) {
                       try {
                           String marcanombre =  JOptionPane.showInputDialog(null,"Introduce el nombre de la nueva marca");
                           Marca marca = new Marca();
                           marca.setMarca(marcanombre);
                           InsertarMarca i = new InsertarMarca(marca);
                           i.inserta();
                           SingletonMarcas.getInstance().actualizarLista();
                           llenarComboMarcas();
                           gtextfieldMarcas.setEspecificTextInTextField(marcanombre);
                           gtextfieldModelos.setEspecificTextInTextField("");
                           
                           
                       } catch (Exception ex) {
                           Logger.getLogger(FichaVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       
                    } 
                   
                   
               }
             });
    }
    
      public void setListenerNuevoModelo() {
            jCheckBoxNuevoModelo.addItemListener(new ItemListener() {
               @Override
               public void itemStateChanged(ItemEvent e) {
                       
                   if (jCheckBoxNuevoModelo.isSelected()) {
                       try {
                           String modelonombre =  JOptionPane.showInputDialog(null,"Introduce el nombre del nuevo modelo. Este modelo se asociará a la marca seleccionada.");
                           for (int it=0 ; it<SingletonMarcas.getInstance().getLista().size(); it++) {
                               if (SingletonMarcas.getInstance().getLista().get(it).getMarca().equals(gtextfieldMarcas.getText())) {
                                        Integer idMarca = SingletonMarcas.getInstance().getLista().get(it).getId();
                                        Modelo modelo = new Modelo();
                                        modelo.setModelo(modelonombre);
                                        modelo.setIdMarcaPadre(idMarca);
                                        InsertarModelo i = new InsertarModelo(modelo);
                                        i.inserta();
                                        llenarComboModelosPorMarca(idMarca);
                                        gtextfieldModelos.setEspecificTextInTextField(modelonombre);
                                      
                                        break;    
                               }
                           }
                          
                          
                           
                           
                       } catch (Exception ex) {
                           Logger.getLogger(FichaVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       
                    } 
                   
                   
               }
             });
    }
    
    
    
    private void botonEliminarGruaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarGruaActionPerformed
         try {
            EliminarVehiculo eliminar = new EliminarVehiculo(vehiculo);
            eliminar.elimina();
            //SingletonGruas.getInstance().actualizarLista();
            // tabla.mostrarGruas(SingletonGruas.getInstance().getLista());
        } catch (Exception ex) {
            Logger.getLogger(FichaVehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }

        

    }//GEN-LAST:event_botonEliminarGruaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       MarcasCRUDFrame.getInstance().setVisible(true);
       

       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBoxEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxEditarActionPerformed

    
 
    
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEliminarGrua;
    private javax.swing.JButton botonNuevaGrua;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBoxEditar;
    private javax.swing.JCheckBox jCheckBoxNuevaMarca;
    private javax.swing.JCheckBox jCheckBoxNuevoModelo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelImagen;
    private javax.swing.JPanel jPanelMarca;
    private javax.swing.JPanel jPanelModelo;
    private javax.swing.JPanel jPanelTipo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldAlto;
    private javax.swing.JTextField jTextFieldLargo;
    private javax.swing.JTextField jTextFieldPeso;
    // End of variables declaration//GEN-END:variables
}

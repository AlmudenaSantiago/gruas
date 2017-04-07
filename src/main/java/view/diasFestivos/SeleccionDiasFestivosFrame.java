/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diasFestivos;

import command.diasFestivos.CargarDiasFestivosCommand;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import model.DiaFestivo;
import model.FestivoSemanal;
import process.CRUDDiasFestivos.ActualizarFestivosSemanales;
import process.CRUDDiasFestivos.InsertarDiaFestivo;
import process.cargador.diasFestivos.CargadorListaDiasFestivos;
import process.parser.diasFestivos.DiaFestivoParserJson;
import process.parser.diasFestivos.FestivoSemanalParserJson;
import view.fichaCliente.SingletonCliente;


public class SeleccionDiasFestivosFrame extends javax.swing.JFrame {

   
    private static SeleccionDiasFestivosFrame instance;
    private JTablaCRUDDiasFestivos tabla;
     
    public static SeleccionDiasFestivosFrame getInstance() {
         if (instance == null) {
              instance= new SeleccionDiasFestivosFrame();
         }
         return instance;
        
    }
    
    
    private SeleccionDiasFestivosFrame() {
       initComponents();
           
       inicializarTablaFestivos();
       setListenerBotonFlecha();
       rellenarCombosFestivosSemanales();
       pack();
       setLocationRelativeTo(null);
    }
    
    
    public void rellenarCombosFestivosSemanales () {
          CargarDiasFestivosCommand cargador = new CargarDiasFestivosCommand(new CargadorListaDiasFestivos(), new FestivoSemanalParserJson());
          List<FestivoSemanal> lista = cargador.executeListaFestivosSemanal();
    
          
          // HORAS Y MINUTOS COMIENZO DIA FESTIVO 1 (elemento 0 de la lista de festivos)
          
          jComboBoxHoraComienzo1.addItem(lista.get(0).getHoraComienzo().toString());
          rellenaElementosCombo(jComboBoxHoraComienzo1,lista.get(0).getHoraComienzo(),24);
          
        
          jComboBoxMinutosComienzo1.addItem(lista.get(0).getMinutosComienzo().toString());
          rellenaElementosCombo(jComboBoxMinutosComienzo1,lista.get(0).getMinutosComienzo(),60);
          
           // HORAS Y MINUTOS FINAL DIA FESTIVO 1 
          
          jComboBoxHoraFinal1.addItem(lista.get(0).getHoraFinal().toString());
          rellenaElementosCombo(jComboBoxHoraFinal1,lista.get(0).getHoraFinal(),24);
         
           
          jComboBoxMinutosFinal1.addItem(lista.get(0).getMinutosFinal().toString());
          rellenaElementosCombo(jComboBoxMinutosFinal1,lista.get(0).getMinutosFinal(),60);
        
        
          
           // HORAS Y MINUTOS COMIENZO DIA FESTIVO 2 (elemento 1 de la lista de festivos)
          
          jComboBoxHoraComienzo2.addItem(lista.get(1).getHoraComienzo().toString());
          rellenaElementosCombo(jComboBoxHoraComienzo2,lista.get(1).getHoraComienzo(),24);
         
          jComboBoxMinutosComienzo2.addItem(lista.get(1).getMinutosComienzo().toString());
          rellenaElementosCombo(jComboBoxMinutosComienzo2,lista.get(1).getMinutosComienzo(),60);
          
          // HORAS Y MINUTOS FINAL DIA FESTIVO 2
       
          
          jComboBoxHoraFinal2.addItem(lista.get(1).getHoraFinal().toString());
          rellenaElementosCombo(jComboBoxHoraFinal2,lista.get(1).getHoraFinal(),24);
       
          jComboBoxMinutosFinal2.addItem(lista.get(1).getMinutosFinal().toString());
          rellenaElementosCombo(jComboBoxMinutosFinal2,lista.get(1).getMinutosFinal(),60);
          
         
          
          jComboBoxDiaFestivo1.addItem(conversorDiaSemanaEnLetra(lista.get(0).getDiaSemana()));
          rellenaElementosComboDiaSemanaEnLetra(jComboBoxDiaFestivo1,conversorDiaSemanaEnLetra(lista.get(0).getDiaSemana()));
          jComboBoxDiaFestivo2.addItem(conversorDiaSemanaEnLetra(lista.get(1).getDiaSemana()));
          rellenaElementosComboDiaSemanaEnLetra(jComboBoxDiaFestivo2,conversorDiaSemanaEnLetra(lista.get(1).getDiaSemana()));
     
    }

    
    
    public void rellenaElementosCombo(JComboBox jcombo, Integer elementoQueYaEsta, Integer maxValue) {
    
        for (int i=0;i<maxValue;i++) {
             if (i!=elementoQueYaEsta) {
                   jcombo.addItem(i);
             }
        }
    }
    
     public void rellenaElementosComboDiaSemanaEnLetra(JComboBox jcombo, String elementoQueYaEsta) {
         String [] diasSemana = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
         for (int i=0;i<diasSemana.length;i++) {
              if (!diasSemana[i].equals(elementoQueYaEsta)) 
                  jcombo.addItem(diasSemana[i]);
         }
         
     }
  
    
    public String conversorDiaSemanaEnLetra(int numDia) {
     switch (numDia) {
              case 1: 
                   return "Lunes";
              case 2: 
                    return "Martes";
              case 3: 
                    return "Miercoles";
              case 4: 
                   return "Jueves";
              case 5: 
                   return "Viernes";
              case 6: 
                   return "Sabado";
              case 7: 
                    return "Domingo";
          } 
     return "";
    } 
    
    
    
    public void setListenerBotonFlecha () {
    
        botonAddFecha.addMouseListener(new MouseAdapter() {
             
            @Override
            public void mouseClicked(MouseEvent e) {
                     DiaFestivo dia = new DiaFestivo();
                     SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
                     String strDate = sdfDate.format(selectorFecha.getSelectedDate().getTime());
                     dia.setDiaFestivoString(strDate);
                     InsertarDiaFestivo insertar = new InsertarDiaFestivo(dia);
                     actualizarTablaFestivos();
                  try {
                      insertar.insertaDiaFestivo();
                      actualizarTablaFestivos();
                  } catch (Exception ex) {
                      Logger.getLogger(SeleccionDiasFestivosFrame.class.getName()).log(Level.SEVERE, null, ex);
                  }
                 
              }
        
        });
        
        
        selectorFecha.getSelectedDate();
        
    }
    
    
    
    
    public void inicializarTablaFestivos() {
          tabla = JTablaCRUDDiasFestivos.getInstance();
          tabla.getColumnModel().getColumn(0).setPreferredWidth(1);
          tabla.getColumnModel().getColumn(1).setPreferredWidth(180);
          tabla.getColumnModel().getColumn(2).setPreferredWidth(20);
          CargarDiasFestivosCommand cargar = new CargarDiasFestivosCommand(new CargadorListaDiasFestivos(), new DiaFestivoParserJson());
          List<DiaFestivo> lista = cargar.executeLista();
          tabla.mostrarDiasFestivos(lista);
          panelContenedorTabla.setViewportView(tabla);
        
    }
    
    public void actualizarTablaFestivos() {
          CargarDiasFestivosCommand cargar = new CargarDiasFestivosCommand(new CargadorListaDiasFestivos(), new DiaFestivoParserJson());
          List<DiaFestivo> lista = cargar.executeLista();
       
          tabla.mostrarDiasFestivos(lista);
       
    
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        dateChooserDialog1 = new datechooser.beans.DateChooserDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        selectorFecha = new datechooser.beans.DateChooserPanel();
        panelContenedorTabla = new javax.swing.JScrollPane();
        botonAddFecha = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxDiaFestivo1 = new javax.swing.JComboBox<>();
        jComboBoxHoraComienzo1 = new javax.swing.JComboBox<>();
        jComboBoxMinutosComienzo1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxHoraFinal1 = new javax.swing.JComboBox<>();
        jComboBoxMinutosFinal1 = new javax.swing.JComboBox<>();
        jComboBoxDiaFestivo2 = new javax.swing.JComboBox<>();
        jComboBoxHoraComienzo2 = new javax.swing.JComboBox<>();
        jComboBoxMinutosComienzo2 = new javax.swing.JComboBox<>();
        jComboBoxHoraFinal2 = new javax.swing.JComboBox<>();
        jComboBoxMinutosFinal2 = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBoxHoraComienzo3 = new javax.swing.JComboBox<>();
        jComboBoxMinutosComienzo3 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxHoraComienzo4 = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jComboBoxMinutosComienzo4 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión Dias Festivos");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Días Festivos");

        jSeparator4.setForeground(new java.awt.Color(51, 102, 255));

        botonAddFecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/diasFestivos/arrow.png"))); // NOI18N
        botonAddFecha.setText("jButton1");
        botonAddFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAddFechaActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue));

        jLabel2.setText("Dias festivos");

        jComboBoxDiaFestivo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDiaFestivo1ActionPerformed(evt);
            }
        });

        jComboBoxHoraComienzo1.setMinimumSize(new java.awt.Dimension(23, 21));
        jComboBoxHoraComienzo1.setPreferredSize(new java.awt.Dimension(33, 25));

        jComboBoxMinutosComienzo1.setMinimumSize(new java.awt.Dimension(23, 21));
        jComboBoxMinutosComienzo1.setPreferredSize(new java.awt.Dimension(33, 25));

        jLabel3.setText("Hora comienzo");

        jLabel4.setText("Hora final");

        jComboBoxHoraFinal1.setMinimumSize(new java.awt.Dimension(23, 21));
        jComboBoxHoraFinal1.setPreferredSize(new java.awt.Dimension(33, 25));

        jComboBoxMinutosFinal1.setMinimumSize(new java.awt.Dimension(23, 21));
        jComboBoxMinutosFinal1.setPreferredSize(new java.awt.Dimension(33, 25));
        jComboBoxMinutosFinal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMinutosFinal1ActionPerformed(evt);
            }
        });

        jComboBoxHoraComienzo2.setMinimumSize(new java.awt.Dimension(23, 21));
        jComboBoxHoraComienzo2.setPreferredSize(new java.awt.Dimension(33, 25));

        jComboBoxMinutosComienzo2.setMinimumSize(new java.awt.Dimension(23, 21));
        jComboBoxMinutosComienzo2.setPreferredSize(new java.awt.Dimension(33, 25));

        jComboBoxHoraFinal2.setMinimumSize(new java.awt.Dimension(23, 21));
        jComboBoxHoraFinal2.setPreferredSize(new java.awt.Dimension(33, 25));

        jComboBoxMinutosFinal2.setMinimumSize(new java.awt.Dimension(23, 21));
        jComboBoxMinutosFinal2.setPreferredSize(new java.awt.Dimension(33, 25));

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setForeground(new java.awt.Color(51, 51, 51));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel6.setText(":");

        jLabel7.setText(":");

        jLabel8.setText(":");

        jLabel9.setText(":");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBoxHoraComienzo1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxHoraFinal1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxMinutosFinal1, 0, 49, Short.MAX_VALUE)
                            .addComponent(jComboBoxMinutosComienzo1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jComboBoxDiaFestivo1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBoxHoraFinal2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBoxHoraComienzo2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxMinutosComienzo2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxMinutosFinal2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jComboBoxDiaFestivo2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBoxDiaFestivo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBoxDiaFestivo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(jComboBoxHoraComienzo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxMinutosComienzo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxHoraComienzo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxMinutosComienzo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxMinutosFinal2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jComboBoxHoraFinal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxMinutosFinal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxHoraFinal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(jLabel9))))
                    .addComponent(jSeparator3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Configuración días festivos semanales");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue));

        jLabel11.setText("Hora comienzo:");

        jLabel12.setText("Hora final:");

        jComboBoxHoraComienzo3.setMinimumSize(new java.awt.Dimension(23, 21));
        jComboBoxHoraComienzo3.setPreferredSize(new java.awt.Dimension(33, 25));

        jComboBoxMinutosComienzo3.setMinimumSize(new java.awt.Dimension(23, 21));
        jComboBoxMinutosComienzo3.setPreferredSize(new java.awt.Dimension(33, 25));

        jLabel13.setText(":");

        jComboBoxHoraComienzo4.setMinimumSize(new java.awt.Dimension(23, 21));
        jComboBoxHoraComienzo4.setPreferredSize(new java.awt.Dimension(33, 25));

        jLabel14.setText(":");

        jComboBoxMinutosComienzo4.setMinimumSize(new java.awt.Dimension(23, 21));
        jComboBoxMinutosComienzo4.setPreferredSize(new java.awt.Dimension(33, 25));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxHoraComienzo4, 0, 59, Short.MAX_VALUE)
                    .addComponent(jComboBoxHoraComienzo3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxMinutosComienzo3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxMinutosComienzo4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxHoraComienzo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMinutosComienzo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jComboBoxHoraComienzo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBoxMinutosComienzo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel10.setText("Configuración  horario nocturno");

        jButton1.setForeground(new java.awt.Color(0, 51, 204));
        jButton1.setText("ACTUALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel15.setText("Selecciona los dias festivos que quieres añadir a la tabla y pulsa la flecha azul");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(384, 384, 384)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(26, 26, 26))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(selectorFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonAddFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(panelContenedorTabla)
                                .addGap(32, 32, 32))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addContainerGap(49, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(selectorFecha, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                            .addComponent(panelContenedorTabla)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(botonAddFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(35, 35, 35))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonAddFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAddFechaActionPerformed
       // INSERTAR DIA FESTIVO EN TABLA Y REFRESCAR 
       
       
       
    }//GEN-LAST:event_botonAddFechaActionPerformed

    private void jComboBoxDiaFestivo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDiaFestivo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxDiaFestivo1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
      FestivoSemanal dia = new FestivoSemanal();
      dia.setDiaSemana(jComboBoxDiaFestivo1.getSelectedIndex());
      dia.setHoraComienzo(Integer.parseInt(jComboBoxHoraComienzo1.getSelectedItem().toString()));
      dia.setHoraFinal(Integer.parseInt(jComboBoxHoraFinal1.getSelectedItem().toString()));
      dia.setMinutosComienzo(Integer.parseInt(jComboBoxMinutosComienzo1.getSelectedItem().toString()));
      dia.setMinutosFinal(Integer.parseInt(jComboBoxMinutosFinal1.getSelectedItem().toString()));
      dia.setIdCliente(SingletonCliente.getInstance().getCliente().getId());
      
      
      ActualizarFestivosSemanales actualizador = new ActualizarFestivosSemanales(dia);
        try {
            actualizador.actualizar();
        } catch (Exception ex) {
            Logger.getLogger(SeleccionDiasFestivosFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
      FestivoSemanal dia2 = new FestivoSemanal();
      dia2.setDiaSemana(jComboBoxDiaFestivo2.getSelectedIndex());
      dia2.setHoraComienzo(Integer.parseInt(jComboBoxHoraComienzo2.getSelectedItem().toString()));
      dia2.setHoraFinal(Integer.parseInt(jComboBoxHoraFinal2.getSelectedItem().toString()));
      dia2.setMinutosComienzo(Integer.parseInt(jComboBoxMinutosComienzo2.getSelectedItem().toString()));
      dia2.setMinutosFinal(Integer.parseInt(jComboBoxMinutosFinal2.getSelectedItem().toString()));
      dia2.setIdCliente(SingletonCliente.getInstance().getCliente().getId());
     
      
       ActualizarFestivosSemanales actualizador2  = new ActualizarFestivosSemanales(dia2);
        try {
            actualizador2.actualizar();
        } catch (Exception ex) {
            Logger.getLogger(SeleccionDiasFestivosFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
          
             
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxMinutosFinal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMinutosFinal1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMinutosFinal1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(SeleccionDiasFestivosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionDiasFestivosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionDiasFestivosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionDiasFestivosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SeleccionDiasFestivosFrame.getInstance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAddFecha;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxDiaFestivo1;
    private javax.swing.JComboBox<String> jComboBoxDiaFestivo2;
    private javax.swing.JComboBox<String> jComboBoxHoraComienzo1;
    private javax.swing.JComboBox<String> jComboBoxHoraComienzo2;
    private javax.swing.JComboBox<String> jComboBoxHoraComienzo3;
    private javax.swing.JComboBox<String> jComboBoxHoraComienzo4;
    private javax.swing.JComboBox<String> jComboBoxHoraFinal1;
    private javax.swing.JComboBox<String> jComboBoxHoraFinal2;
    private javax.swing.JComboBox<String> jComboBoxMinutosComienzo1;
    private javax.swing.JComboBox<String> jComboBoxMinutosComienzo2;
    private javax.swing.JComboBox<String> jComboBoxMinutosComienzo3;
    private javax.swing.JComboBox<String> jComboBoxMinutosComienzo4;
    private javax.swing.JComboBox<String> jComboBoxMinutosFinal1;
    private javax.swing.JComboBox<String> jComboBoxMinutosFinal2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JScrollPane panelContenedorTabla;
    private datechooser.beans.DateChooserPanel selectorFecha;
    // End of variables declaration//GEN-END:variables
}

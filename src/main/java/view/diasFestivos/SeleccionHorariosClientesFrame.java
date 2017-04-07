/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.diasFestivos;

import command.diasFestivos.CargarDiasFestivosCommand;
import command.diasFestivos.CargarNocturnoCommand;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.FestivoSemanal;
import model.HorarioNocturno;
import process.CRUDDiasFestivos.ActualizarFestivosSemanales;
import process.CRUDHorarioNocturno.ActualizarHorarioNocturno;
import process.cargador.diasFestivos.CargadorListaDiasFestivos;
import process.cargador.diasFestivos.CargadorListaNocturno;
import process.parser.diasFestivos.FestivoSemanalParserJson;
import process.parser.diasFestivos.NocturnoParserJson;
import view.fichaCliente.FichaCliente;
import view.fichaCliente.SingletonCliente;


public class SeleccionHorariosClientesFrame extends javax.swing.JFrame {

   
    private static SeleccionHorariosClientesFrame instance;
    private JTablaCRUDDiasFestivos tabla;
     
    public static SeleccionHorariosClientesFrame getInstance() {
         if (instance == null) {
              instance= new SeleccionHorariosClientesFrame();
         }
         return instance;
        
    }


    
    
    
    private SeleccionHorariosClientesFrame() {
       initComponents();
       rellenarTextFieldsFestivosSemanales();
       rellenarTextFieldNocturnos();
       pack();
       setLocationRelativeTo(null);
       setListenerCerrar();
    }
    
    public void setListenerCerrar() {
        this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        instance = null;
                        FichaCliente.getInstance(SingletonCliente.getInstance().getCliente().getId()).setSeleccionHorarioNull();
                    } catch (Exception ex) {
                        Logger.getLogger(SeleccionHorariosClientesFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        
        
    }
    
    public void inicializarCombo1() {
   
        
         jComboBoxDiaFestivo1.removeAllItems();
        
    }
    
    public void inicializarCombo2(){
    
      
         jComboBoxDiaFestivo2.removeAllItems();
    
    }
    public void rellenarTextFieldsFestivosSemanales () {
       
        
          CargarDiasFestivosCommand cargador = new CargarDiasFestivosCommand(new CargadorListaDiasFestivos(), new FestivoSemanalParserJson());
          List<FestivoSemanal> lista = cargador.executeListaFestivosSemanal(SingletonCliente.getInstance().getCliente().getIdFestivo1());
       
          if (lista.size() > 0 ) {
                // HORAS Y MINUTOS COMIENZO DIA FESTIVO 1 (elemento 0 de la lista de festivos)
                inicializarCombo1();
                inputHoraComienzo1.setText(lista.get(0).getHoraComienzo().substring(0,lista.get(0).getHoraComienzo().length()-3));
                inputHoraFinal1.setText(lista.get(0).getHoraFinal().substring(0,lista.get(0).getHoraFinal().length()-3));
                jComboBoxDiaFestivo1.addItem(lista.get(0).getDiaSemana());
                rellenaElementosComboDiaSemanaEnLetra(jComboBoxDiaFestivo1,lista.get(0).getDiaSemana());
          }
                /*   float minutes = 100.5f; // 1:40
                   Calendar c = Calendar.getInstance();
                   c.set(Calendar.HOUR_OF_DAY, 0);
                   c.set(Calendar.MINUTE, 0);
                   c.set(Calendar.SECOND, 0);
                   c.add(Calendar.MINUTE, (int) minutes);
                   c.add(Calendar.SECOND, (int) ((minutes % (int) minutes) * 60));
                   final Date date = c.getTime();

                   Format timeFormat = new SimpleDateFormat("HH:mm:ss");
                   input = new JFormattedTextField(timeFormat);
                   input.setValue(date);  
                   */
        

           // HORAS Y MINUTOS COMIENZO DIA FESTIVO 2 (elemento 1 de la lista de festivos)
          
          lista = cargador.executeListaFestivosSemanal(SingletonCliente.getInstance().getCliente().getIdFestivo2());
     
          if (lista.size() > 0) {
           
            inicializarCombo2();
            jComboBoxDiaFestivo2.addItem(lista.get(0).getDiaSemana());
            inputComienzo2.setText(lista.get(0).getHoraComienzo().substring(0,lista.get(0).getHoraComienzo().length()-3));
            inputFinal2.setText(lista.get(0).getHoraFinal().substring(0,lista.get(0).getHoraFinal().length()-3));
            rellenaElementosComboDiaSemanaEnLetra(jComboBoxDiaFestivo2,lista.get(0).getDiaSemana());

           }
          
          
      }

        public void rellenarTextFieldNocturnos() {
       
          CargarNocturnoCommand cargador = new CargarNocturnoCommand(new CargadorListaNocturno(), new NocturnoParserJson());
          List<HorarioNocturno> lista = cargador.executeListaNocturno(SingletonCliente.getInstance().getCliente().getId());
          if (lista.size() > 0 ) {
                horarioNocturnoComienzo.setText(lista.get(0).getHoraEntrada().substring(0,lista.get(0).getHoraEntrada().length()-3));
                HorarioNocturnoSalida.setText(lista.get(0).getHoraSalida().substring(0,lista.get(0).getHoraSalida().length()-3));
 
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
              case 0: 
                   return "Lunes";
              case 1: 
                    return "Martes";
              case 2: 
                    return "Miercoles";
              case 3: 
                   return "Jueves";
              case 4: 
                   return "Viernes";
              case 5: 
                   return "Sabado";
              case 6: 
                   return "Domingo";
          } 
     return "";
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
        jPanel2 = new javax.swing.JPanel();
        jComboBoxDiaFestivo1 = new javax.swing.JComboBox<>();
        jComboBoxDiaFestivo2 = new javax.swing.JComboBox<>();
        inputHoraComienzo1 = new javax.swing.JFormattedTextField();
        inputHoraFinal1 = new javax.swing.JFormattedTextField();
        inputComienzo2 = new javax.swing.JFormattedTextField();
        inputFinal2 = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        horarioNocturnoComienzo = new javax.swing.JTextField();
        HorarioNocturnoSalida = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión Dias Festivos");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Días Festivos de la semana");

        jSeparator4.setForeground(new java.awt.Color(51, 102, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue));

        jComboBoxDiaFestivo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" }));
        jComboBoxDiaFestivo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDiaFestivo1ActionPerformed(evt);
            }
        });

        jComboBoxDiaFestivo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" }));

        inputHoraComienzo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputHoraComienzo1ActionPerformed(evt);
            }
        });

        inputHoraFinal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputHoraFinal1ActionPerformed(evt);
            }
        });

        jLabel6.setText("de");

        jLabel7.setText("de");

        jLabel8.setText("a");

        jLabel9.setText("a");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBoxDiaFestivo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDiaFestivo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inputHoraComienzo1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(inputComienzo2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inputHoraFinal1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(inputFinal2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputHoraComienzo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDiaFestivo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputHoraFinal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputComienzo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxDiaFestivo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputFinal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setText("Configuración días festivos semanales");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue, java.awt.Color.blue));

        jLabel11.setText("Hora comienzo:");

        jLabel12.setText("Hora final:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HorarioNocturnoSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(horarioNocturnoComienzo))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(horarioNocturnoComienzo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(HorarioNocturnoSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setText("Configuración  horario nocturno");

        jButton1.setForeground(new java.awt.Color(0, 51, 204));
        jButton1.setText("ACTUALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setForeground(new java.awt.Color(0, 51, 204));
        jButton2.setText("ACTUALIZAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("* Formato HH:mm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jSeparator4)))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxDiaFestivo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDiaFestivo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxDiaFestivo1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
      if (validaHora(inputHoraComienzo1.getText()) && validaHora(inputHoraFinal1.getText()) && validaHora(inputComienzo2.getText()) && validaHora(inputFinal2.getText()))  {
            FestivoSemanal dia = new FestivoSemanal();
            dia.setDiaSemana(jComboBoxDiaFestivo1.getSelectedItem().toString());
            dia.setHoraComienzo(inputHoraComienzo1.getText());
            dia.setHoraFinal(inputHoraFinal1.getText());
            dia.setId(SingletonCliente.getInstance().getCliente().getIdFestivo1());
            ActualizarFestivosSemanales actualizador = new ActualizarFestivosSemanales(dia);
              try {
                  actualizador.actualizar();
              } catch (Exception ex) {
                  Logger.getLogger(SeleccionHorariosClientesFrame.class.getName()).log(Level.SEVERE, null, ex);
              }
     
            FestivoSemanal dia2 = new FestivoSemanal();
            dia2.setDiaSemana(jComboBoxDiaFestivo2.getSelectedItem().toString());
            dia2.setHoraComienzo(inputComienzo2.getText());
            dia2.setHoraFinal(inputFinal2.getText());
            dia2.setId(SingletonCliente.getInstance().getCliente().getIdFestivo2());


             ActualizarFestivosSemanales actualizador2  = new ActualizarFestivosSemanales(dia2);
              try {
                  actualizador2.actualizar();
              } catch (Exception ex) {
                  Logger.getLogger(SeleccionHorariosClientesFrame.class.getName()).log(Level.SEVERE, null, ex);
              }
              rellenarTextFieldsFestivosSemanales();
      } else {
           JOptionPane.showMessageDialog(null,"El formato de hora es incorrecto. Debe ser: HH:mm");
   
      }
        
      
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public boolean validaHora(String time) {
       return time.matches( "([01]?[0-9]|2[0-3]):[0-5][0-9]");
   }
    
   
    
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
      if (validaHora(horarioNocturnoComienzo.getText()) && validaHora(HorarioNocturnoSalida.getText())) {
             HorarioNocturno nocturno = new HorarioNocturno();
             nocturno.setHoraEntrada(horarioNocturnoComienzo.getText());
             nocturno.setHoraSalida(HorarioNocturnoSalida.getText());
             nocturno.setId(SingletonCliente.getInstance().getCliente().getIdNocturno());


             ActualizarHorarioNocturno actualizador = new ActualizarHorarioNocturno(nocturno);
               try {
                   actualizador.actualizar();
               } catch (Exception ex) {
                   Logger.getLogger(SeleccionHorariosClientesFrame.class.getName()).log(Level.SEVERE, null, ex);
               }
               rellenarTextFieldNocturnos();
               
      } else {
              JOptionPane.showMessageDialog(null,"El formato de hora es incorrecto. Debe ser: HH:mm");
       }
     
    }//GEN-LAST:event_jButton2ActionPerformed

    private void inputHoraFinal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputHoraFinal1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputHoraFinal1ActionPerformed

    private void inputHoraComienzo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputHoraComienzo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputHoraComienzo1ActionPerformed

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
            java.util.logging.Logger.getLogger(SeleccionHorariosClientesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionHorariosClientesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionHorariosClientesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionHorariosClientesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SeleccionHorariosClientesFrame.getInstance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField HorarioNocturnoSalida;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private javax.swing.JTextField horarioNocturnoComienzo;
    private javax.swing.JFormattedTextField inputComienzo2;
    private javax.swing.JFormattedTextField inputFinal2;
    private javax.swing.JFormattedTextField inputHoraComienzo1;
    private javax.swing.JFormattedTextField inputHoraFinal1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBoxDiaFestivo1;
    private javax.swing.JComboBox<String> jComboBoxDiaFestivo2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables
}

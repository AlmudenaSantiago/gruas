/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tarifas.porTiempoDeRespuesta;

import command.tarifas.CargarTiposDeTarifaCommand;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.Tarifas.TipoDeTarifa;
import process.cargador.tarifas.CargadorListaTiposDeTarifa;
import process.parser.tipoDeTarifas.TipoDeTarifaParserJson;

/**
 *
 * @author gruasjoseantonio
 */
public class JComboBoxTipoDeTarifa extends JComboBox {

    public JComboBoxTipoDeTarifa (){
        try {
            CargarTiposDeTarifaCommand cargar = new CargarTiposDeTarifaCommand(new CargadorListaTiposDeTarifa(), new TipoDeTarifaParserJson());
            List<TipoDeTarifa> lista = cargar.executeLista();
            for (int i=0;i<lista.size();i++) {
                System.out.println("entro");
               addItem(lista.get(i).getTipo());
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(JComboBoxTipoDeTarifa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        addItemListener(new ItemListener() {
         @Override
            public void itemStateChanged(ItemEvent e) {
                 System.out.println("HE CAMBIADO");
            }
    });
        
        
       this.addMouseListener(new MouseAdapter() {
        
          @Override
          public void mouseClicked(MouseEvent e) {
             JOptionPane.showMessageDialog(null,"he pulsado");
          
          }
        });
        
        
          
       
    }
 
    
    
    
}

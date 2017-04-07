/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import command.gruas.CargarGruasCommand;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import process.cargador.gruas.CargadorListaGruas;
import process.parser.gruas.GruasParserJson;
import view.gruas.TablaGruas;

/**
 *
 * @author Aaron
 */
public class PanelTablaGruas extends Panel {
    
    Timer timer;
    TablaGruas tablaGruas;
    CargarGruasCommand cargarGruasCommand;
    JScrollPane scrollPaneTabla;
    
    
   
	public PanelTablaGruas() {
            setLayout(new GridLayout(1,2,4,4));
            tablaGruas  = new TablaGruas();
            scrollPaneTabla=  new javax.swing.JScrollPane();
            scrollPaneTabla.setViewportView(tablaGruas);
            add(scrollPaneTabla, BorderLayout.CENTER);
            
            cargarGruasCommand = new CargarGruasCommand(CargadorListaGruas.getInstance(), new GruasParserJson());
            sincronizacion();
        }
    
    public void sincronizacion() {
       timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                cargarGruasCommand.execute(tablaGruas);
            }
        };
        timer.schedule(task, 0, 15000);
    }
      
  
     public void timerStop() {
        timer.cancel();
     }
     
     
     
    
}

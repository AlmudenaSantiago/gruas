package view;

import com.itextpdf.text.Image;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public final class JTabbedPanel extends JTabbedPane {
      
        private static int maxW = 0;
        private static int maxH = 0;
    
        PanelTablaServicios panelCursando;
        PanelTablaServicios panelAceptados ;
        PanelTablaServicios panelRechazados;
        PanelTablaServicios panelIniciados;
        PanelTablaServicios panelLocalizados;
        PanelTablaServicios panelReparando;
        PanelTablaServicios panelFinalizados;
       // En el futuro hacer modelo con icono, nombre pestaña y panel y rellenar un array de ModeloPestañaJTabbedPanel[] pestañas;
        
               
 
	   public JTabbedPanel() {
               
              
                String nombreLogo = "iconocurso.png";
                String nombreLogoEnCurso = "encurso.png";
                String nombreLogoAceptado = "aceptado.png";
                String nombreLogoRechazado = "rechazado.png";
                String nombreLogoLocalizado = "localizado.png";
                String nombreLogoIniciado= "iniciado.png";
                String nombreLogoFinalizado = "finalizado.png";
                
           

                 URL imagenUrlReparando = this.getClass().getResource(nombreLogo);
                 URL imagenUrlEnCurso = this.getClass().getResource(nombreLogoEnCurso);
                 URL imagenUrlAceptado = this.getClass().getResource(nombreLogoAceptado);
                 URL imagenUrlRechazado = this.getClass().getResource(nombreLogoRechazado);
                 URL imagenUrlLocalizado = this.getClass().getResource(nombreLogoLocalizado);
                 URL imagenUrlIniciado = this.getClass().getResource(nombreLogoIniciado);
                 URL imagenUrlFinalizado = this.getClass().getResource(nombreLogoFinalizado);
               
               
                ImageIcon icon = new ImageIcon("iconocurso.png");
	           
            
               // setOpaque(false);
               // putClientProperty("TabbedPane.contentOpaque", Boolean.FALSE);
               // putClientProperty("TabbedPane.tabsOpaque", Boolean.FALSE);
     
                panelCursando = new PanelTablaServicios();
                panelAceptados = new PanelTablaServicios();
                panelRechazados = new PanelTablaServicios();
                panelIniciados = new PanelTablaServicios();
                panelLocalizados = new PanelTablaServicios();
                panelReparando = new PanelTablaServicios();
                panelFinalizados = new PanelTablaServicios();
                addTab("En curso", panelCursando);
                addTab("Aceptados", panelAceptados);
                addTab("Rechazados",  panelRechazados);
                addTab("Iniciados", panelIniciados);
                addTab("Localizados",  panelLocalizados);
                addTab("Reparando",  panelReparando);
                addTab("Finalizados",  panelFinalizados);
               
         
                setIconAt(0, new ImageIcon(imagenUrlEnCurso));
                setIconAt(1, new ImageIcon(imagenUrlAceptado));
                setIconAt(2, new ImageIcon(imagenUrlRechazado));
                setIconAt(3, new ImageIcon(imagenUrlIniciado));
                setIconAt(4, new ImageIcon(imagenUrlLocalizado));
                setIconAt(5, new ImageIcon(imagenUrlReparando));
                setIconAt(6, new ImageIcon(imagenUrlFinalizado));
                
                setBackgroundAt(0, Color.white);
                setBackgroundAt(1, Color.white);
                setBackgroundAt(2, Color.white);
                setBackgroundAt(3, Color.white);
                setBackgroundAt(4, Color.white);
                setBackgroundAt(5, Color.white);
                setBackgroundAt(6, Color.white);
                
                setBackground(Color.white);
               
/*
                final Dimension originalTabsDim = getPreferredSize();
                this.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        updateUI();
                        setSelectedIndex(getSelectedIndex());
                        System.out.println("Tab: " + getSelectedIndex());
                        
                        Component p =   ((JTabbedPane) e.getSource()).getSelectedComponent();
                        Dimension panelDim = p.getPreferredSize();
                        Dimension nd = new Dimension(
                                                        originalTabsDim.width - ( maxW - panelDim.width),
                                                        originalTabsDim.height - ( maxH - panelDim.height) 
                                                    );

                        setPreferredSize(nd);
                        
                      
                    }
                });
          */
	   }
           
  
    public void actualizar() {
      revalidate();
      repaint();
      updateUI();
    }       
           
    public PanelTablaServicios getPanelCursando() {
        return panelCursando;
    }

    public PanelTablaServicios getPanelAceptados() {
        return panelAceptados;
    }

    public PanelTablaServicios getPanelRechazados() {
        return panelRechazados;
    }

    public PanelTablaServicios getPanelIniciados() {
        return panelIniciados;
    }

    public PanelTablaServicios getPanelLocalizados() {
        return panelLocalizados;
    }

    public PanelTablaServicios getPanelReparando() {
        return panelReparando;
    }

    public PanelTablaServicios getPanelFinalizados() {
        return panelFinalizados;
    }

 

}
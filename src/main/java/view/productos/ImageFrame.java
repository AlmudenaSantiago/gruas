/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.productos;

import java.awt.BorderLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


    public class ImageFrame extends JFrame {
    
        JFrame f;
        String nombre;
        String descripcion;
        String color;
        String folder;
        File[] files;
     
        JLabel  label  = new JLabel("");
        int i = 0;
        JLabel labelInfo = new JLabel("");
         JButton imagenSiguiente;
        JButton imagenAnterior;


    public  ImageFrame(String nombre, String descripcion, String color, String folder) throws IOException {
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.color=color;
        this.folder=folder;
        files = new File(folder).listFiles();
        configurarFrame();
        insertarBotones();
        accionesToolBar(); 
        
    }    
        
    
      public void configurarFrame() throws IOException {
               f  = new JFrame();
               f.setExtendedState(MAXIMIZED_BOTH);
               f.setLocation(200, 200);
               f.setVisible(true);
               f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
             
               
       }
    
    /*  public void cerrar ()  {
        f.addWindowListener(new WindowAdapter(){
                @Override
                public void windowClosing(WindowEvent e) {
                     f.dispose();
                     
                     SwingUtilities.updateComponentTreeUI(CRUD_ProductosFrame.getInstance());
                     CRUD_ProductosFrame.getInstance().setVisible(true);
                }    
        });
                
        }*/
              
      
      
       public void verImagen (int i) throws InterruptedException {
            f.remove(label); 
            File url = new File(folder + "/" + files[i].getName());
            BufferedImage image = null;
                    try {
                        image = ImageIO.read(url);
                    } catch (IOException ex) {
                        Logger.getLogger(ImageFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
            label = new JLabel(new ImageIcon(image));
            f.getContentPane().add(label);
            SwingUtilities.updateComponentTreeUI(f);
    }
    
     
      public void verInfo() {
          JPanel panel = new JPanel();
          labelInfo = new JLabel("<html></br></br></br><font color=#3333FF><font size='6'><center><u>" + nombre + "</u></br>"
           + "</br></br></br><center><center>" + descripcion + "</br></br> " 
           + "<br></br> "  +  color  + "</html>");
        
          panel.add(labelInfo);
 
          f.getContentPane().add(panel,  BorderLayout.NORTH);
                    
      }  
    
    public void insertarBotones () throws IOException {
        imagenSiguiente = new JButton(">>");
        imagenAnterior = new JButton("<<");
        JPanel panelBotones = new JPanel();
        panelBotones.add(imagenAnterior);
        panelBotones.add(imagenSiguiente);
        f.getContentPane().add(panelBotones,  BorderLayout.SOUTH);
        
    }
      
     
       public void verPrimeraImagen() throws InterruptedException {
                   verInfo();
                   verImagen(0);
       }
       
     public void accionesToolBar() {
            
            imagenSiguiente.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                    System.out.println("accion hacia delante");
                try {
                    i = i+1;
                    if (i == files.length) {
                        i=0;
                    }   
                    System.out.println("voy a ver la " + i);
                    verImagen(i);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ImageFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            });
              
          imagenAnterior.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                    System.out.println("accion hacia atras");
                  try {
                    if (i == 0) {
                      i=files.length; 
                    }
                    i = i-1;
                    verImagen(i);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ImageFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
            });
    }  
       
       
}
        
    
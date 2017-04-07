package view;



import command.servicios.CargarServiciosCommand;
import command.gruas.CargarGruasCommand;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import model.Servicio;
import process.cargador.gruas.CargadorListaGruas;
import process.cargador.CargadorListaServicios;
import process.parser.servicios.ServicioParserJson;
import process.parser.gruas.GruasParserJson;
import view.gruas.TablaGruas;
import view.servicios.TablaServicios;

public final class MainFrame extends JFrame  {

  private static MainFrame instance;  
   
    
  private Panel panelContenedorSuperior;
  private PanelTablaServicios panelContenedorMedio;
  private Panel panelContenedorInferior;
  private JScrollPane scrollPaneTablaSuperior;
  private JScrollPane scrollPaneTablaMedio;
  private JScrollPane scrollPaneTablaInferior;
  private TablaServicios tablaServiciosPendientes;
  private TablaServicios tablaServicios;
  private TablaGruas tablaGruas;
  //declaracion de variables
  private Point p;
  private Point pl;
  private int lx,ly;
  boolean calcular_diferencia=true;
  
  
    List<Servicio> listaGeneral;
    List<Servicio> listaAceptados;
    List<Servicio> listaRechazados;
    List<Servicio> listaLocalizados;
    List<Servicio> listaFinalizados;
    List<Servicio> listaCursando;
    List<Servicio> listaReparando;
    List<Servicio> listaIniciados;
    List<Servicio> listaPendientes;
    
  private JTabbedPanel jTabbedPanel;
  private  Timer timer;
  private CargarServiciosCommand  cargarServiciosCommand;
  private CargarGruasCommand cargarGruasCommand;
  
  
  
  
   public static MainFrame getInstance() { 
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

  
   
   
    private MainFrame()  {
        setVisible(true);
        initComponents();
    
       
    }

    public PanelTablaServicios getPanelContenedorMedio() {
        return panelContenedorMedio;
    }

  

   
  
     
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
           inicializarAtributosDelFrame();
           inicializarPaneles();
           inicializarListas();
           cargarServiciosCommand = new CargarServiciosCommand(CargadorListaServicios.getInstance(), new ServicioParserJson());
           sincronizacion();
    }
    
 
    public void inicializarListas() {
           listaGeneral =  new ArrayList<>();
           listaPendientes =  new ArrayList<>();
           listaCursando =  new ArrayList<>();
           listaAceptados =  new ArrayList<>();
           listaRechazados =  new ArrayList<>();
           listaFinalizados=  new ArrayList<>();
           listaIniciados = new ArrayList<>();
           listaReparando = new ArrayList<>();
           listaLocalizados = new ArrayList<>();
    
    }
   
    public void inicializarAtributosDelFrame() {
           setLayout(new GridLayout(3,1,10,20));
           setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
           setExtendedState(MAXIMIZED_BOTH);
           setTitle("PANEL DE CONTROL");
    }
   
 
   public void sincronizacion() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                    inicializarListas();
                    clasificarListas();
                    actualizarListasEnPaneles();
                    mostrarListasEnPaneles();
                    panelContenedorMedio.getTablaServicios().getModel().actualizarLista(listaPendientes);
                    panelContenedorMedio.getTablaServicios().mostrarServicios(listaPendientes);
               }
        };
        timer.schedule(task, 0, 1500);
    }
   
   
   
   public void clasificarListas() {
    try {    
        listaGeneral = cargarServiciosCommand.executeLista();
        for (Servicio servicioIterador : listaGeneral) {
            switch (servicioIterador.getEstado()) {
                case "pendiente":
                    listaPendientes.add(servicioIterador);
                    break;
                case "Cursando":
                    listaCursando.add(servicioIterador);
                    break;
                case "Aceptado":
                    listaAceptados.add(servicioIterador);
                    break;
                case "Rechazado":
                    listaRechazados.add(servicioIterador);
                    break;
                case "Iniciado":
                    listaIniciados.add(servicioIterador);
                    break;
                case "Reparando":
                    listaReparando.add(servicioIterador);
                    break;
                case "Finalizado":
                    listaFinalizados.add(servicioIterador);
                    break;
                case "Localizado":
                    listaLocalizados.add(servicioIterador);
                    break;
                }
            }
        } catch (Exception e) {
             System.out.println(e);      
         }
                      
   }

   
   
   public void actualizarListasEnPaneles() {
       
         jTabbedPanel.getPanelCursando().getTablaServicios().getModel().actualizarLista(listaCursando);
         jTabbedPanel.getPanelAceptados().getTablaServicios().getModel().actualizarLista(listaAceptados);
         jTabbedPanel.getPanelRechazados().getTablaServicios().getModel().actualizarLista(listaRechazados);
         jTabbedPanel.getPanelIniciados().getTablaServicios().getModel().actualizarLista(listaIniciados);
         jTabbedPanel.getPanelReparando().getTablaServicios().getModel().actualizarLista(listaReparando);
         jTabbedPanel.getPanelFinalizados().getTablaServicios().getModel().actualizarLista(listaFinalizados);
         jTabbedPanel.getPanelLocalizados().getTablaServicios().getModel().actualizarLista(listaLocalizados);
   }
   
   public void mostrarListasEnPaneles() {
                  
        jTabbedPanel.getPanelCursando().getTablaServicios().mostrarServicios(listaCursando);
        jTabbedPanel.getPanelAceptados().getTablaServicios().mostrarServicios(listaAceptados);
        jTabbedPanel.getPanelRechazados().getTablaServicios().mostrarServicios(listaRechazados);
        jTabbedPanel.getPanelIniciados().getTablaServicios().mostrarServicios(listaIniciados);
        jTabbedPanel.getPanelReparando().getTablaServicios().mostrarServicios(listaReparando);
        jTabbedPanel.getPanelFinalizados().getTablaServicios().mostrarServicios(listaFinalizados);
        jTabbedPanel.getPanelLocalizados().getTablaServicios().mostrarServicios(listaLocalizados);   
              
        
      //  jTabbedPanel.actualizar();
       
                    
   }  
   
    public void inicializarPaneles() {
         /***********  3 PANELES GENERALES ***********/
      
         panelContenedorSuperior = new PanelTablaGruas();
         panelContenedorMedio    = new PanelTablaServicios();
   
         panelContenedorInferior = new Panel();
         panelContenedorInferior.setLayout(new BorderLayout());
         jTabbedPanel = new JTabbedPanel();

         panelContenedorInferior.add(jTabbedPanel);
         jTabbedPanel.actualizar();
         
         add(panelContenedorSuperior);
         add(panelContenedorMedio);
         add(panelContenedorInferior);
       
         
         
    }

 
    
}

    
    
    

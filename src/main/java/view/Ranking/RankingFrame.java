package view.Ranking;

import view.Ranking.TablaRankingModelo;
import view.Ranking.JTablaRanking;
import command.pedidos.CargarPlatosServidosCommand;
import datechooser.beans.DateChooserCombo;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import process.cargador.CargadorListaPlatosServidos;
import process.parser.productos.ProductoParserJson;


public class RankingFrame extends javax.swing.JFrame {


    private JScrollPane jScrollPane1;
    private CargarPlatosServidosCommand cargarPlatosServidosCommand;

    private static RankingFrame instance;


    private DateChooserCombo dateChooserComboDesde;
    private DateChooserCombo dateChooserComboHasta;
    private Calendar dateDesde;
    private Calendar dateHasta;
    private JButton botonConsultaRango;

    private ProductoParserJson productoParserJson;
    TablaRankingModelo modelo = new TablaRankingModelo();
    
    private RankingFrame() {
       
       initComponents();
        setVisible(true);
 
        setCargarPlatosServidosCommand(cargarPlatosServidosCommand);
        setListenerBotonConsultaRangoFechas();
     
        pack();
   }


    public static RankingFrame getInstance() { 
        if (instance == null) {
            instance = new RankingFrame();
        }
        return instance;
    }
 
 
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
       
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        
 
        pack();
            
        setTitle("RANKING DE VENTAS");
        
       // PRIMER PANEL  
        JPanel jPanelFechas = new JPanel();
        JLabel etiquetaDesde = new JLabel("Desde");
        JLabel etiquetaHasta = new JLabel("Hasta");
        jPanelFechas.add(etiquetaDesde);
        botonConsultaRango = new JButton("Consultar Rango");
        dateChooserComboDesde = new DateChooserCombo();
        jPanelFechas.add(dateChooserComboDesde);
        dateChooserComboHasta = new DateChooserCombo();
        jPanelFechas.add(etiquetaHasta);
        jPanelFechas.add(dateChooserComboHasta);
    
        jPanelFechas.add(botonConsultaRango);

        add(jPanelFechas, BorderLayout.NORTH);
               // SCROLL PANEL DE TABLA 
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setViewportView(JTablaRanking.getInstance());
        add(jScrollPane1, BorderLayout.CENTER);
        pack();
    }             
    
    
  public void setListenerBotonConsultaRangoFechas() {
        dateDesde = dateChooserComboDesde.getSelectedDate();
        dateHasta = dateChooserComboHasta.getSelectedDate();
        botonConsultaRango.addMouseListener(new MouseAdapter() {
                                                         @Override
                                                         public void mouseClicked(MouseEvent e) {
                                                             JTablaRanking jTablaPlatosServidos= JTablaRanking.getInstance();
                                                             CargarPlatosServidosCommand cargarPlatosServidosCommand = new CargarPlatosServidosCommand(CargadorListaPlatosServidos.getInstance());
                                                             jTablaPlatosServidos.setCargarPlatosServidosCommand(cargarPlatosServidosCommand);
                                                             cargarPlatosServidosCommand.executeFechas(dateDesde.getTime(), dateHasta.getTime());
                                                         }
                                    });
    }
    
    
  public void setCargarPlatosServidosCommand(CargarPlatosServidosCommand cargarPlatosServidosCommand) {
        this.cargarPlatosServidosCommand = cargarPlatosServidosCommand;
  }
    
 }



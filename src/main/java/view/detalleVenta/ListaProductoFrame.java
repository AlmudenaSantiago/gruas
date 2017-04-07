package view.detalleVenta;

import command.productos.CargarProductosPedidoCommand;
import java.awt.BorderLayout;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

import process.cargador.CargadorProductosPedido;
import process.parser.productos.ProductoPedidoParserJson;
import view.pedidos.JTablaPedidos;
import view.pedidos.MainFrame;

public class ListaProductoFrame extends javax.swing.JFrame {

    private JScrollPane jScrollPane1;
    private JTable jTable1;

    
    private String cliente;
    private Integer idPedido;
    private JTablaDetalleVenta jTablaDetalleComanda;
    private Timer timer;
    private CargarProductosPedidoCommand cargarProductosPedidoCommand;
   
    public ListaProductoFrame(Integer idPedido,String cliente) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        initComponents();
        sincronizacion();
        setVisible(true);
        setLocationRelativeTo(MainFrame.getInstance());
        
    }

     public void sincronizacion() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
  
            public void run() {
                if (JTablaPedidos.getInstance().getFramesAbiertos().get(idPedido) != null ) {
                    cargarProductosPedidoCommand = new CargarProductosPedidoCommand(new ProductoPedidoParserJson(), CargadorProductosPedido.getInstance());
                    JTablaPedidos.getInstance().getFramesAbiertos().get(idPedido).getjTablaDetalleComanda().getListaProductosModelo().setCargarProductosPedidoCommand(cargarProductosPedidoCommand);
                    JTablaPedidos.getInstance().getFramesAbiertos().get(idPedido).getjTablaDetalleComanda().getListaProductosModelo().getCargarProductosPedidosCommand().execute(JTablaPedidos.getInstance().getFramesAbiertos().get(idPedido).getIdPedido());
               }
             }
        };
        timer.schedule(task, 0, 15000);
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setTitle("Cliente " + cliente);
    
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablaDetalleComanda = new JTablaDetalleVenta(idPedido);
        jScrollPane1.setViewportView(jTablaDetalleComanda);
        add(jScrollPane1, BorderLayout.CENTER);
        pack();
    }

      
    
 

    public Integer getIdPedido() {
        return idPedido;
    }

  
   
    
    
    public JTablaDetalleVenta getjTablaDetalleComanda() {
        return jTablaDetalleComanda;
    }

    public void setjTablaDetalleComanda(JTablaDetalleVenta jTablaDetalleComanda) {
        this.jTablaDetalleComanda = jTablaDetalleComanda;
    }
    
    
    public void setCargarProductosPedidoCommand(CargarProductosPedidoCommand cargarProductosPedidoCommand) {
        this.cargarProductosPedidoCommand = cargarProductosPedidoCommand;
    }
    
    
}
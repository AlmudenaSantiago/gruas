package view;

import com.itextpdf.text.DocumentException;
import command.CargarPedidosCommand;
import command.CargarProductosPedidoCommand;
import model.Pedido;
import process.cargador.CargadorProductosPedido;
import process.parser.ProductoParserJson;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TablaPedidosFrame extends javax.swing.JFrame {

    private JScrollPane jScrollPane1;
    private JTable jTablePedido;
    private TablaPedidosModelo tablaPedidosModelo;
    private CargarPedidosCommand cargarPedidosCommand;
    private CargarProductosPedidoCommand cargarProductosPedidoCommand;

    public TablaPedidosFrame() {
        initComponents();
        setTable();
        setVisible(true);
        cargarProductosPedidoCommand = new CargarProductosPedidoCommand(new ProductoParserJson(), CargadorProductosPedido.getInstance());
    }

    public void sincronizacion() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                cargarPedidosCommand.execute();
            }
        };

        timer.schedule(task, 0, 1200);
    }

    private void setTable() {
        tablaPedidosModelo = new TablaPedidosModelo();
        jTablePedido.setModel(tablaPedidosModelo);
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePedido = new javax.swing.JTable();

        jTablePedido.setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });

        jTablePedido.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Integer idFila = (Integer) jTablePedido.getModel().getValueAt(jTablePedido.rowAtPoint(e.getPoint()), 0);
                if (jTablePedido.columnAtPoint(e.getPoint()) == 5) {
                    cargarProductosPedidoCommand.execute(idFila);
                }
                if (jTablePedido.columnAtPoint(e.getPoint()) == 7) {
                    ActualizarEstado actualiza = new ActualizarEstado(idFila);
                    Pedido pedido = new Pedido();
                    try {
                        pedido = actualiza.actualizarEstado();

                    } catch (Exception ex) {
                        System.out.println("Excepcion en la llamada a actualizar estado");
                    }

                    JFileChooser chooser = new JFileChooser();
                    chooser.setCurrentDirectory(new java.io.File("."));
                    chooser.setDialogTitle("Seleccion la ubicación de la factura");
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setAcceptAllFileFilterUsed(false);
                    chooser.setApproveButtonText("Guardar");

                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File path = chooser.getSelectedFile();

                        GenerarFacturaPDF generarPDF = new GenerarFacturaPDF(pedido, path);
                        try {
                            generarPDF.createPdf();
                        } catch (DocumentException ex) {
                            System.out.println("Excepcion en el documento generar factura pdf ");
                        } catch (IOException ex) {
                             System.out.println("Excepcion IO en generar factura ");
                        }

                        System.out.println(" getCurrentDirectory(): " + chooser.getCurrentDirectory());
                        System.out.println("Directorio seleccionado : " + chooser.getSelectedFile());

                    } else {
                        System.out.println("No se ha seleccionado ruta ");
                    }

                }
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jTablePedido);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE));

        pack();
    }

    public void mostrarPedidos(List<Pedido> listaPedido) {
        tablaPedidosModelo.actualizarListaPedido(listaPedido);
        tablaPedidosModelo.fireTableDataChanged();
    }

    public void setCargarPedidosCommand(CargarPedidosCommand cargarPedidosCommand) {
        this.cargarPedidosCommand = cargarPedidosCommand;
    }

    public void setCargarProductosPedidoCommand(CargarProductosPedidoCommand cargarProductosPedidoCommand) {
        this.cargarProductosPedidoCommand = cargarProductosPedidoCommand;
    }
}

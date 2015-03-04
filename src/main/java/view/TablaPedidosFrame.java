package view;

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
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


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

        timer.schedule(task, 0, 120000);
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
                if(jTablePedido.columnAtPoint(e.getPoint()) == 5) {
                    cargarProductosPedidoCommand.execute((Integer) jTablePedido.getModel().getValueAt(
                            jTablePedido.rowAtPoint(e.getPoint()),0));
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
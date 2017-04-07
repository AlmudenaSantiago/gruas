package view.pedidos;

import command.pedidos.CargarPedidosCommand;
import command.productos.CargarProductosPedidoCommand;
import model.Pedido;
import process.cargador.CargadorProductosPedido;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;

import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;

import javax.swing.table.TableRowSorter;
import org.apache.commons.codec.binary.Base64;
import process.CRUDClientes.ActualizarCantidadesPendientes;
import process.CRUDPedidos.EliminarPedido;
import process.parser.productos.ProductoPedidoParserJson;
import view.detalleVenta.ListaProductoFrame;
import view.venta.ActualizarVentaFrame;
import view.venta.ActualizarVentaFrameBack;



public final class JTablaPedidos extends javax.swing.JTable {

    private JScrollPane jScrollPane1;
    private ListaProductoFrame listaProductoFrame;
    private TablaPedidosModelo tablaPedidosModelo;
    private CargarPedidosCommand cargarPedidosCommand;
    private CargarProductosPedidoCommand cargarProductosPedidoCommand;
    private HashMap<Integer,ListaProductoFrame> framesAbiertos;  
    private JFileChooser chooser;
    
    
    private static JTablaPedidos instance;
   


    private JTablaPedidos() {
       setTable();
       this.setFont(new java.awt.Font("Arial", 0, 16));
       this.setRowHeight(30);
       setDefaultRenderer(JButton.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
                return (Component) objeto;
            }
        });
       setListeners();
       setRowSorter();
    
      }
    
  
    public static JTablaPedidos getInstance() {
        if (instance == null) {
            instance = new JTablaPedidos();
        }
        return instance;
    }
    
        
    public void setTable() {
        tablaPedidosModelo = new TablaPedidosModelo();
        setModel(tablaPedidosModelo);
        
       }
   
    public void setRowSorter () {
     	TableRowSorter sorter = new TableRowSorter(tablaPedidosModelo);
        this.setRowSorter(sorter);
        sorter.addRowSorterListener(new RowSorterListener() {
                 private Object jTable;
        @Override
        public void sorterChanged(RowSorterEvent evt) {
            System.out.println("evento");
        }
             });
    }

    public HashMap<Integer, ListaProductoFrame> getFramesAbiertos() {
        return framesAbiertos;
    }

  

     public void setListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            
                Integer idPedido = (Integer) getValueAt(rowAtPoint(e.getPoint()), 1);
                String cliente =  (String)   getValueAt(rowAtPoint(e.getPoint()), 2);
                Double cantidadPendiente = (Double) getValueAt(rowAtPoint(e.getPoint()), 3);
                Double importePedido = (Double) getValueAt(rowAtPoint(e.getPoint()), 4);
                Double descuento = (Double) getValueAt(rowAtPoint(e.getPoint()), 5);
                
               
               
              if  (e.getClickCount() == 2) {
                  
                   if (columnAtPoint(e.getPoint()) == 1) {
                       FichaPedidoFrame ficha = null;
                       try {
                           ficha = new FichaPedidoFrame(idPedido);
                       } catch (IOException ex) {
                           Logger.getLogger(JTablaPedidos.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       ficha.setVisible(true);
                   }
                  
                  
                  
                 if (columnAtPoint(e.getPoint()) == 3) {
                     // obtengo la id del cliente que esta entre parentesis 
                    StringTokenizer st = new StringTokenizer(cliente, "();");
                    String idCliente = st.nextToken();
                    
                    while(st.hasMoreTokens()) {
                             idCliente = st.nextToken();
                    }
                  
                    ActualizarVentaFrame actualizarVentaFrame = new ActualizarVentaFrame(idPedido, Integer.parseInt(idCliente),importePedido, cantidadPendiente, descuento, "entrega");
                    actualizarVentaFrame.setVisible(true);
                    
                }
              }     
                
                
                if (columnAtPoint(e.getPoint()) == 9) {
                   
                    if (framesAbiertos == null) {
                             framesAbiertos = new HashMap<>();
                    }
                    
                    if (framesAbiertos.containsKey(idPedido)) {
                        framesAbiertos.get(idPedido).setVisible(false);
                    }
                     
                     framesAbiertos.put(idPedido, new ListaProductoFrame(idPedido,cliente));
                     framesAbiertos.get(idPedido).setVisible(true);
                     framesAbiertos.get(idPedido).setLocationRelativeTo(instance);
                     cargarProductosPedidoCommand = new CargarProductosPedidoCommand(new ProductoPedidoParserJson(), CargadorProductosPedido.getInstance());
                     framesAbiertos.get(idPedido).getjTablaDetalleComanda().setCargarProductosPedidoCommand(cargarProductosPedidoCommand);
                     cargarProductosPedidoCommand.execute(idPedido);
                    
                }
                
                if (columnAtPoint(e.getPoint()) == 10) {
                
                    StringTokenizer st = new StringTokenizer(cliente, "();");
                    String idCliente = st.nextToken();
                    
                    while(st.hasMoreTokens()) {
                             idCliente = st.nextToken();
                    }
                    System.out.println("idcliente = " + idCliente);
                    ActualizarVentaFrame actualizarVentaFrame = new ActualizarVentaFrame(idPedido, Integer.parseInt(idCliente),importePedido, cantidadPendiente,descuento, "factura");
                    actualizarVentaFrame.setVisible(true);
                    
                }
             
                
                
               if (columnAtPoint(e.getPoint()) == 11) {
                         
                   
                          if (MainFrame.palabraClave.getText().equals("alca")) {
                          
                                StringTokenizer st = new StringTokenizer(cliente, "();");
                                String idCliente = st.nextToken();

                                while(st.hasMoreTokens()) {
                                         idCliente = st.nextToken();
                                }
                                EliminarPedido eliminaPedido = new EliminarPedido(idPedido);

                                 try {
                                       eliminaPedido.eliminar();
                                      if ( MainFrame.getInstance().getFiltroEstado().isSelected() ) {
                                            cargarPedidosCommand.executeFinalizados();
                                      } else {
                                          cargarPedidosCommand.execute();

                                          }
                                } catch (Exception ex) {
                                    Logger.getLogger(JTablaPedidos.class.getName()).log(Level.SEVERE, null, ex);
                                }
                               // Actualizamos las cantidades del cliente 
                                try {
                                    new ActualizarCantidadesPendientes(Integer.parseInt(idCliente)).actualizar();
                                } catch (Exception ex) {
                                    Logger.getLogger(JTablaPedidos.class.getName()).log(Level.SEVERE, null, ex);
                                }

                          }
                       }
             }
        });
     }
     
     
    
     
            public byte[] cifra(String sinCifrar) throws Exception {
                   final byte[] bytes = sinCifrar.getBytes("UTF-8");
                   final Cipher aes = obtieneCipher(true);
                   final byte[] cifrado = aes.doFinal(bytes);
                   return cifrado;
           }
            private Cipher obtieneCipher(boolean paraCifrar) throws Exception {
                   final String frase = "234+";
                   final MessageDigest digest = MessageDigest.getInstance("SHA");
                   digest.update(frase.getBytes("UTF-8"));
                   final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

                   final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
                   if (paraCifrar) {
                           aes.init(Cipher.ENCRYPT_MODE, key);
                   } else {
                           aes.init(Cipher.DECRYPT_MODE, key);
                   }

                   return aes;
           }
  /*   NO LO USAMOS  
  public void usuarioYcontraseña() {   
        String nombreAceptado = "der";
        String claveAceptada = "derlanza";
        String nombreEntrado="";
        String claveEntrada="";
        while(nombreEntrado.equals(nombreAceptado) == false || claveEntrada.equals(claveAceptada) == false) {
            
            nombreEntrado = JOptionPane.showInputDialog ( "Introduzca nombre de usuario:" );
            
            claveEntrada = JOptionPane.showInputDialog ( "Introduzca su contraseña:" );
           if (nombreEntrado.equals(nombreAceptado) == false || claveEntrada.equals(claveAceptada) == false) 
             JOptionPane.showMessageDialog(null, "Usuario o password INCORRECTO." );
        }
            
    }
     */
  
     
      
     
     public static String Desencriptar(String textoEncriptado) throws Exception {
 
        String secretKey = "qualityinfosolutions"; //llave para desenciptar datos
        String base64EncryptedString = "";
 
        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
 
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
 
            byte[] plainText = decipher.doFinal(message);
 
            base64EncryptedString = new String(plainText, "UTF-8");
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
}
     
     
     public static String Encriptar(String texto) {
 
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";
 
        try {
 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
}
     

    public void mostrarPedidos(List<Pedido> listaPedido) {
          Pedido pedido = new Pedido();
          pedido.setFechaCreacion("TOTAL________________________");
          pedido.setImporte(11.12);
          pedido.setCantidadPendiente(11.12);
          pedido.setClienteId(0);
          pedido.setEstado("");
          pedido.abonado = "";
      
          tablaPedidosModelo.actualizarListaPedido(listaPedido, pedido);
          tablaPedidosModelo.fireTableDataChanged();
    }

    public void setCargarPedidosCommand(CargarPedidosCommand cargarPedidosCommand) {
        this.cargarPedidosCommand = cargarPedidosCommand;
    }

}

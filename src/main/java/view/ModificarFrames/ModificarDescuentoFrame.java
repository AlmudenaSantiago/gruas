
package view.ModificarFrames;

import command.productos.CargarProductosPedidoCommand;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pedido;
import model.ProductoPedido;
import process.CRUDPedidos.ActualizarPedido;
import process.CRUDPedidos.ModificarPedido;
import process.cargador.CargadorProductosPedido;
import process.parser.productos.ProductoPedidoParserJson;
import view.pedidos.FichaPedidoFrame;


public class ModificarDescuentoFrame extends javax.swing.JFrame {

    Integer idPedido;
   FichaPedidoFrame ficha;
    
 
    
    
    public ModificarDescuentoFrame(Integer idPedido, FichaPedidoFrame ficha) {
        this.idPedido = idPedido;
        this.ficha = ficha;
        initComponents();
        setLocationRelativeTo(ficha);
        setVisible(true);
        
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldDescuento= new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setTitle("MODIFICAR DESCUENTO\n");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Descuento:");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jTextFieldDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
         try {
            new ModificarPedido(idPedido,"descuento",this.jTextFieldDescuento.getText()).modificarPedido();
            this.setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(ModificarDescripcionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        ficha.consultar();
        ficha.actualizar();
    }                                        

   
   
    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextFieldDescuento;
    // End of variables declaration                   
}

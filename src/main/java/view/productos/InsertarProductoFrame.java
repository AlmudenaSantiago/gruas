/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.productos;


import static com.oracle.nio.BufferSecrets.instance;
import command.productos.CargarDiasFestivosCommand;
import command.categorias.CargarCategorias2Command;
import command.categorias.CargarCategorias3Command;
import command.categorias.CargarCategorias4Command;
import command.categorias.CargarCategoriasCommand;
import command.proveedores.CargarProveedoresCommand;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Categoria;
import model.Producto;
import process.CRUDProductos.InsertarProducto;
import process.cargador.CargadorListaCategoria;
import process.cargador.CargadorListaCategoria2;
import process.cargador.CargadorListaCategoria3;
import process.cargador.CargadorListaCategoria4;
import process.cargador.CargadorListaProducto;
import process.cargador.CargadorListaProveedor;
import process.limpiador.LimpiarTexto;
import process.parser.categorias.CategoriaParserJson;
import process.parser.productos.ProductoParserJson;
import process.parser.proveedor.ProveedorParserJson;
import view.SelectorDeRuta;
import view.pedidos.MainFrame;
import view.proveedores.CRUD_ProveedoresFrame;
import view.proveedores.JTablaCRUDProveedores;

/**
 *
 * @author Aarón
 */
public class InsertarProductoFrame extends javax.swing.JFrame {

    private static InsertarProductoFrame instance;
    private Producto producto;

    
    private InsertarProductoFrame() {
        this.setVisible(true);
        initComponents();
        producto = new Producto();
        llenarCombo();
    
    }
    
     public static InsertarProductoFrame getInstance() { 
        if (instance == null) {
            instance = new InsertarProductoFrame();
        }
        return instance;
    }


   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabelNombreProducto = new javax.swing.JLabel();
        jLabelDescripcionProducto = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxCategoria1 = new javax.swing.JComboBox();
        jTextFieldNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDecripcion = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        botonInsertar = new javax.swing.JButton();
        jTextFieldStock = new javax.swing.JTextField();
        jLabelStock = new javax.swing.JLabel();
        jTextFieldPrecioCompra = new javax.swing.JTextField();
        jLabelURL = new javax.swing.JLabel();
        jComboBoxCategoria2 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldColor = new javax.swing.JTextField();
        jComboBoxCategorias3 = new javax.swing.JComboBox();
        jComboBoxCategorias4 = new javax.swing.JComboBox();
        jButtonSelectorImagenes = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabelProveedor = new javax.swing.JLabel();
        jButtonProveedores = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldPrecioVenta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 255));
        jLabel5.setText("NUEVO PROVEEDOR");

        setResizable(false);

        jLabelNombreProducto.setText("Nombre producto:");

        jLabelDescripcionProducto.setText("Descripción:");

        jLabel1.setText("Categoria:");

        jComboBoxCategoria1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategoria1ActionPerformed(evt);
            }
        });

        jTextAreaDecripcion.setColumns(20);
        jTextAreaDecripcion.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDecripcion);

        jLabel2.setText("Precio de compra:");

        botonInsertar.setText("Insertar");
        botonInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInsertarActionPerformed(evt);
            }
        });

        jLabelStock.setText("Stock:");

        jTextFieldPrecioCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrecioCompraActionPerformed(evt);
            }
        });

        jLabelURL.setText("Selecciona la imagen:");

        jComboBoxCategoria2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategoria2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Acabado:");

        jComboBoxCategorias3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategorias3ActionPerformed(evt);
            }
        });

        jComboBoxCategorias4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategorias4ActionPerformed(evt);
            }
        });

        jButtonSelectorImagenes.setText("Selecciona la imagen");
        jButtonSelectorImagenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSelectorImagenesActionPerformed(evt);
            }
        });

        jLabelProveedor.setText("Selecciona el proveedor:");

        jButtonProveedores.setText("Proveedores");
        jButtonProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProveedoresActionPerformed(evt);
            }
        });

        jLabel6.setText("Precio de venta: ");

        jTextFieldPrecioVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrecioVentaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 255));
        jLabel7.setText("NUEVO PRODUCTO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombreProducto)
                            .addComponent(jLabel2)
                            .addComponent(jLabelURL)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldColor)
                            .addComponent(jTextFieldNombre)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonSelectorImagenes)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(105, 105, 105)
                                        .addComponent(jLabel4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(jLabelProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonProveedores))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(132, 132, 132)
                                .addComponent(botonInsertar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jComboBoxCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jComboBoxCategoria2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jTextFieldPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBoxCategorias3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jComboBoxCategorias4, 0, 111, Short.MAX_VALUE)
                                            .addComponent(jTextFieldPrecioVenta)))
                                    .addComponent(jScrollPane1)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelStock)
                            .addComponent(jLabelDescripcionProducto))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(227, 227, 227))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNombreProducto)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelURL)
                    .addComponent(jButtonSelectorImagenes)
                    .addComponent(jLabelProveedor)
                    .addComponent(jButtonProveedores))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabelDescripcionProducto))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxCategoria1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxCategorias3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxCategoria2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxCategorias4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelStock)
                    .addComponent(jTextFieldStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(botonInsertar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInsertarActionPerformed
    
        LimpiarTexto limpiador = new LimpiarTexto();
        producto.setNombre(limpiador.limpiar(this.jTextFieldNombre.getText()));
        producto.setDescripcion(limpiador.limpiar(this.jTextAreaDecripcion.getText()));
        producto.setColor(limpiador.limpiar(this.jTextFieldColor.getText()));
        producto.setCategoria1_nombre(this.jComboBoxCategoria1.getSelectedItem().toString());
        producto.setCategoria1_id(jComboBoxId1);
        
        
         producto.setCategoria2_nombre("");
         producto.setCategoria3_nombre("");
         producto.setCategoria4_nombre("");
         
         
         if (this.jComboBoxCategoria2.getSelectedItem() != null) {
              producto.setCategoria2_nombre(this.jComboBoxCategoria2.getSelectedItem().toString());
              producto.setCategoria2_id(jComboBoxId2);
             
         }
        if (this.jComboBoxCategorias3.getSelectedItem() != null) {
            producto.setCategoria3_nombre(this.jComboBoxCategorias3.getSelectedItem().toString());
            producto.setCategoria3_id(jComboBoxId3);
        }
          
        if (this.jComboBoxCategorias4.getSelectedItem() != null) {
            producto.setCategoria4_nombre(this.jComboBoxCategorias4.getSelectedItem().toString());
            producto.setCategoria4_id(jComboBoxId4);
        }
     

              
        try {
             producto.setPrecio(Double.parseDouble(this.jTextFieldPrecioVenta.getText()));
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog (null,"El formato del precio erróneo");
        }
        
        try {
             producto.setPrecioCompra(Double.parseDouble(this.jTextFieldPrecioCompra.getText()));
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog (null,"El formato del precio erróneo");
        }
        
        try {
            if (this.jTextFieldStock.getText().equals("")) producto.setStock(0.0);
            else producto.setStock(Double.parseDouble(this.jTextFieldStock.getText()));
        
        } 
        catch (Exception e) {
             JOptionPane.showMessageDialog (null,"El formato del stock erróneo");
        }
        
        InsertarProducto insertaProducto = new InsertarProducto(producto);
        try {
            insertaProducto.insertaProducto();
            this.dispose();
            CargarDiasFestivosCommand cargarProductosCommand = new CargarDiasFestivosCommand (CargadorListaProducto.getInstance(), new ProductoParserJson());
            JTablaCRUD.getInstance().setCargarProductosCommand(cargarProductosCommand);
            cargarProductosCommand.execute();
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
       
    }//GEN-LAST:event_botonInsertarActionPerformed

    List<Categoria> lista;
    List<Categoria> lista2;
    List<Categoria> lista3; 
    List<Categoria> lista4; 
    int jComboBoxId3;
    int jComboBoxId2;
    int jComboBoxId1;
    int jComboBoxId4;
    
    
    
    
    
    CargarCategoriasCommand cargar = new CargarCategoriasCommand(new CargadorListaCategoria(), new CategoriaParserJson());

    
    void llenarCombo() {
        CargarCategoriasCommand cargar = new CargarCategoriasCommand(new CargadorListaCategoria(), new CategoriaParserJson());
        lista = cargar.execute();
        Iterator<Categoria> it = lista.iterator();

        while (it.hasNext()) {
           Categoria cat = it.next();
           jComboBoxCategoria1.addItem(cat.getNombre());
           jComboBoxId1 = cat.getId();

        }
    }
    
     public Producto getProducto() {
        return producto;
    }

   
    
    private void jComboBoxCategoria2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategoria2ActionPerformed
         Iterator<Categoria> it = lista2.iterator();
         while (it.hasNext()) {
             Categoria categoriaIterator = it.next();
             if (categoriaIterator.getNombre().equals(jComboBoxCategoria2.getSelectedItem())) {
                CargarCategorias3Command command = new CargarCategorias3Command(new CargadorListaCategoria3(), new CategoriaParserJson()); 
                
                lista3 = command.execute(categoriaIterator.getId2());
                Iterator<Categoria> it3 = lista3.iterator();
                jComboBoxCategorias3.removeAllItems();
                jComboBoxCategorias4.removeAllItems();
                    while (it3.hasNext()) {
                        Categoria cat = it3.next();
                        jComboBoxCategorias3.addItem(cat.getNombre());
                        jComboBoxId3 = cat.getId();
                    }
             }
         }
         
    }//GEN-LAST:event_jComboBoxCategoria2ActionPerformed

    private void jComboBoxCategoria1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategoria1ActionPerformed
    
        lista = cargar.execute();
        Iterator<Categoria> it = lista.iterator();
         while (it.hasNext()) {
             Categoria categoriaIterator = it.next();
             if (categoriaIterator.getNombre().equals(jComboBoxCategoria1.getSelectedItem())) {
                CargarCategorias2Command command = new CargarCategorias2Command(new CargadorListaCategoria2(), new CategoriaParserJson()); 
                
               lista2 = command.execute(categoriaIterator.getId());
                Iterator<Categoria> it2 = lista2.iterator();
                jComboBoxCategoria2.removeAllItems();
                jComboBoxCategorias3.removeAllItems();
                jComboBoxCategorias4.removeAllItems();
                    while (it2.hasNext()) {
                        Categoria cat = it2.next();
                        jComboBoxCategoria2.addItem(cat.getNombre());
                        jComboBoxId2 = cat.getId();
                    }
             }
         }
    }//GEN-LAST:event_jComboBoxCategoria1ActionPerformed

    private void jComboBoxCategorias3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategorias3ActionPerformed
        Iterator<Categoria> it = lista3.iterator();
         while (it.hasNext()) {
             Categoria categoriaIterator = it.next();
             if (categoriaIterator.getNombre().equals(jComboBoxCategorias3.getSelectedItem())) {
                CargarCategorias4Command command = new CargarCategorias4Command(new CargadorListaCategoria4(), new CategoriaParserJson()); 
                
                lista4 = command.execute(categoriaIterator.getId3());
                Iterator<Categoria> it4 = lista4.iterator();
                jComboBoxCategorias4.removeAllItems();
                    while (it4.hasNext()) {
                        Categoria cat = it4.next();
                        jComboBoxCategorias4.addItem(cat.getNombre());
                        jComboBoxId4 = cat.getId();
                    }
             }
         }
    }//GEN-LAST:event_jComboBoxCategorias3ActionPerformed

    private void jComboBoxCategorias4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategorias4ActionPerformed
       
    }//GEN-LAST:event_jComboBoxCategorias4ActionPerformed

    private void jButtonSelectorImagenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelectorImagenesActionPerformed
        producto.setImagen(URLEncoder.encode( new SelectorDeRuta().seleccionDeRuta().toString()));
       
        
    }//GEN-LAST:event_jButtonSelectorImagenesActionPerformed

    private void jButtonProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProveedoresActionPerformed
          CRUD_ProveedoresFrame crudProveedoresFrame = CRUD_ProveedoresFrame.getInstance();
          crudProveedoresFrame.setVisible(true);
          crudProveedoresFrame.setLocationRelativeTo(instance);
          JTablaCRUDProveedores jTablaCRUD = JTablaCRUDProveedores.getInstance();
          CargarProveedoresCommand cargarProveedoresCommand = new CargarProveedoresCommand (CargadorListaProveedor.getInstance(), new ProveedorParserJson());
          jTablaCRUD.setCargarProveedoresCommand(cargarProveedoresCommand);
          try {
              cargarProveedoresCommand.execute();
          } catch (IOException ex) {
              Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
          }
                                                           
    }//GEN-LAST:event_jButtonProveedoresActionPerformed

    private void jTextFieldPrecioVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrecioVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrecioVentaActionPerformed

    private void jTextFieldPrecioCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrecioCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrecioCompraActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonInsertar;
    private javax.swing.JButton jButtonProveedores;
    private javax.swing.JButton jButtonSelectorImagenes;
    private javax.swing.JComboBox jComboBoxCategoria1;
    private javax.swing.JComboBox jComboBoxCategoria2;
    private javax.swing.JComboBox jComboBoxCategorias3;
    private javax.swing.JComboBox jComboBoxCategorias4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelDescripcionProducto;
    private javax.swing.JLabel jLabelNombreProducto;
    private javax.swing.JLabel jLabelProveedor;
    private javax.swing.JLabel jLabelStock;
    private javax.swing.JLabel jLabelURL;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextAreaDecripcion;
    private javax.swing.JTextField jTextFieldColor;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPrecioCompra;
    private javax.swing.JTextField jTextFieldPrecioVenta;
    private javax.swing.JTextField jTextFieldStock;
    // End of variables declaration//GEN-END:variables
}

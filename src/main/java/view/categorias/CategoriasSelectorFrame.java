
package view.categorias;

import command.categorias.CargarCategorias2Command;
import command.categorias.CargarCategorias3Command;
import command.categorias.CargarCategorias4Command;
import command.categorias.CargarCategoriasCommand;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreePath;
import model.Categoria;
import process.CRUDCategorias.InsertarCategoria;
import process.cargador.CargadorListaCategoria;
import process.cargador.CargadorListaCategoria2;
import process.cargador.CargadorListaCategoria3;
import process.cargador.CargadorListaCategoria4;
import process.parser.categorias.CategoriaParserJson;
import view.pedidos.MainFrame;
import view.productos.CRUD_ProductosFrame;
 
public class CategoriasSelectorFrame extends JFrame
{
    private JTree tree;
    private JButton botonEnviar;
    private JLabel jLabelCategoria;
    private JTextField jCategoria;
    
    private TreePath path;
    
    List<Categoria> lista;
    List<Categoria> lista2;
    List<Categoria> lista3; 
    List<Categoria> lista4; 
    Iterator<Categoria> it;
    Iterator<Categoria> it2;
    Iterator<Categoria> it3;
    Iterator<Categoria> it4;
    
    
    Object [] nodos;
    
    String[] splitNodos;
    
    DefaultMutableTreeNode nodoNuevo;
    DefaultMutableTreeNode nodoNuevo2;
    DefaultMutableTreeNode nodoNuevo3;
    DefaultMutableTreeNode nodoNuevo4;
    
    String ruta;
    
    public CategoriasSelectorFrame()  {
        JPanel jPanel = new JPanel();
       
        jLabelCategoria = new JLabel("Nueva categoria:");
        jPanel.add(jLabelCategoria);
        botonEnviar = new JButton("Enviar");
        jCategoria = new JTextField(15);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categorias Alca");
        tree = new JTree(root);
        add(tree);
          setLocationRelativeTo(CRUD_ProductosFrame.getInstance());
        
        jPanel.add(jCategoria);
        jPanel.add(botonEnviar);
        add(jPanel, BorderLayout.NORTH);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Categorias Alca");       
        this.pack();
        this.setVisible(true);
        
        ruta = new String();
       setListenerBotonEnviar();
        CargarCategoriasCommand command = new CargarCategoriasCommand(new CargadorListaCategoria(), new CategoriaParserJson()); 
        lista = command.execute();
        it = lista.iterator();

  
         while (it.hasNext()) {
             Categoria categoriaIterator = it.next();
             nodoNuevo = new DefaultMutableTreeNode(categoriaIterator.getNombre());
             root.add(nodoNuevo);
          }
             
           tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
		
               @Override
               public void valueChanged(TreeSelectionEvent e) {
                    
                   
                                    path = e.getPath();
                                    nodos = path.getPath();
                                
                                    ruta = "";
                                    for (int i=1;i<nodos.length; i++) {
                                          
                                           ruta += nodos[i].toString() + "-";
                                     }
                                           
                                    splitNodos = ruta.split("-");
                                     // SI ES CATEGORIA PADRE (1º NIVEL)
                                    Iterator<Categoria> its =lista.iterator(); 
                                    
                                             if (splitNodos.length == 1) {
                                                 while(its.hasNext()){
                                                         Categoria cat= its.next();
                                                         if (cat.getNombre().equals(splitNodos[0])) {
                                                         
                                                               CargarCategorias2Command command2 = new CargarCategorias2Command(new CargadorListaCategoria2(), new CategoriaParserJson()); 
                                                               lista2 = command2.execute(cat.getId()); 
                                                               it2 = lista2.iterator();
                                                               
                                                               
                                                               while (it2.hasNext()) {
                                                            
                                                                    Categoria categoriaIterator2 = it2.next();
                                                                     nodoNuevo2 = new DefaultMutableTreeNode(categoriaIterator2.getNombre());
  
                                                                        DefaultMutableTreeNode ultimoNodo = (DefaultMutableTreeNode)nodos[nodos.length-1];

                                                                        DefaultTreeSelectionModel modeloSeleccion = (DefaultTreeSelectionModel)e.getSource();
                                                                        if (modeloSeleccion.isPathSelected(path))
                                                                                ultimoNodo.add(nodoNuevo2);

                                                                 }

                                                                 }
                         
                                                          }
                                                 }
                                             
                                             
                                               
                                                 if (splitNodos.length == 2) {
                                                         Iterator<Categoria> its2 = lista2.iterator(); 
                                             
                                                     while(its2.hasNext()){
                                                         Categoria cat= its2.next();
                                                         if (cat.getNombre().equals(splitNodos[1])) {
                                                           
                                                               CargarCategorias3Command command3 = new CargarCategorias3Command(new CargadorListaCategoria3(), new CategoriaParserJson()); 
                                                               lista3 = command3.execute(cat.getId2()); 
                                                               it3 = lista3.iterator();
                                                               
                                                               
                                                               while (it3.hasNext()) {
                                                          
                                                                    Categoria categoriaIterator3 = it3.next();
                                                                     nodoNuevo3 = new DefaultMutableTreeNode(categoriaIterator3.getNombre());
  
                                                                        DefaultMutableTreeNode ultimoNodo = (DefaultMutableTreeNode)nodos[nodos.length-1];

                                                                        DefaultTreeSelectionModel modeloSeleccion = (DefaultTreeSelectionModel)e.getSource();
                                                                        if (modeloSeleccion.isPathSelected(path))
                                                                            ultimoNodo.add(nodoNuevo3);

                                                                 }
                                                         }
                                                      }
                                                 }
                                                if (splitNodos.length == 3) {
                                                         Iterator<Categoria> its3 = lista3.iterator(); 
                                             
                                                     while(its3.hasNext()){
                                                         Categoria cat= its3.next();
                                                       
                                                         
                                                         if (cat.getNombre().equals(splitNodos[2])) {
                                                               CargarCategorias4Command command4 = new CargarCategorias4Command(new CargadorListaCategoria4(), new CategoriaParserJson()); 
                                                               lista4 = command4.execute(cat.getId3()); 
                                                               it4 = lista4.iterator();
                                                               
                                                               
                                                               while (it4.hasNext()) {
                                                                     Categoria categoriaIterator4 = it4.next();
                                                                     nodoNuevo4 = new DefaultMutableTreeNode(categoriaIterator4.getNombre());
                                                                     DefaultMutableTreeNode ultimoNodo = (DefaultMutableTreeNode)nodos[nodos.length-1];
                                                                     DefaultTreeSelectionModel modeloSeleccion = (DefaultTreeSelectionModel)e.getSource();
                                                                     if (modeloSeleccion.isPathSelected(path))
                                                                        ultimoNodo.add(nodoNuevo4);
                                                                    }
                                                                }
                                                    }
                                                 }

                                             }
                                             
                                    });
           
    }


   
public void setListenerBotonEnviar() {
           botonEnviar.addMouseListener(
                                     new MouseAdapter() { 
                                          @Override
                                          public void mouseClicked(MouseEvent e) {
                                             
                                             Categoria nuevaCategoria = new Categoria();;
                                             InsertarCategoria insertar;
                                            
                                            System.out.println(ruta);
                                            System.out.println(splitNodos[0]);
                                            System.out.println("la lenght es" + splitNodos.length);
                                            
                                       
                                            
                                             // SI ES CATEGORIA PADRE (1º NIVEL)
                                             if (splitNodos.length == 1 && ruta.equals("")) {
                                                 nuevaCategoria.setNombre(jCategoria.getText());
                                                 insertar = new InsertarCategoria(nuevaCategoria);
                                                 try {
                                                     insertar.insertaCategoria(1);
                                                 } catch (Exception ex) {
                                                     Logger.getLogger(CategoriasSelectorFrame.class.getName()).log(Level.SEVERE, null, ex);
                                                 }
                                                 
                                              }
                                              // SI ES CATEGORIA HIJA (2 NIVEL)
                                              if (splitNodos.length == 1 && !ruta.equals("") ) {
                                                  nuevaCategoria.setNombre(jCategoria.getText());
                                                  it = lista.iterator();
                                                  while (it.hasNext()) {
                                                       Categoria cat = it.next();
                                                       if (cat.getNombre().equals(splitNodos[0])) {
                                                          nuevaCategoria.setId(cat.getId());
                                                       }
                                                  }
                                                  
                                                insertar = new InsertarCategoria(nuevaCategoria);
                                                try {
                                                     insertar.insertaCategoria(2);
                                                } catch (Exception ex) {
                                                     Logger.getLogger(CategoriasSelectorFrame.class.getName()).log(Level.SEVERE, null, ex);
                                                 } 
                                                                                                     
                                                }
                                              
                                              // SI ES CATEGORIA HIJA (3 NIVEL)
                                              if (splitNodos.length == 2) {
                                                  nuevaCategoria.setNombre(jCategoria.getText());
                                                  it = lista.iterator();
                                                
                                                  // Buscamos el id categoria1
                                                  while (it.hasNext()) {
                                                       Categoria categoriaIteradora1 = it.next();
                                                            if (categoriaIteradora1.getNombre().equals(splitNodos[0])) {
                                                                nuevaCategoria.setId(categoriaIteradora1.getId());
                                                             }
                                                            
                                                          Iterator<Categoria> ite2 = lista2.iterator(); 
                                                         
                                                            while (ite2.hasNext()) {  
                                                                Categoria categoriaIteradora2 = ite2.next();
                                                                if (categoriaIteradora2.getNombre().equals(splitNodos[1])) {
                                                                    nuevaCategoria.setId2(categoriaIteradora2.getId2());
                                                                }
                                                           } 
                                                   }
                                       
                                                   insertar = new InsertarCategoria(nuevaCategoria);
                                                         try {
                                                             insertar.insertaCategoria(3);
                                                         } catch (Exception ex) {
                                                             Logger.getLogger(CategoriasSelectorFrame.class.getName()).log(Level.SEVERE, null, ex);
                                                         }  

                                                   
                                            }
                                              
                                              
                                              // SI ES CATEGORIA HIJA (3 NIVEL)
                                              if (splitNodos.length == 3) {
                                                  nuevaCategoria.setNombre(jCategoria.getText());
                                                  it = lista.iterator();
                                                
                                                  // Buscamos el id categoria1
                                                  while (it.hasNext()) {
                                                       Categoria categoriaIteradora1 = it.next();
                                                            if (categoriaIteradora1.getNombre().equals(splitNodos[0])) {
                                                                nuevaCategoria.setId(categoriaIteradora1.getId());
                                                             }
                                                            
                                                         Iterator<Categoria> iter2 = lista2.iterator(); 
                                                            while (iter2.hasNext()) {  
                                                                Categoria categoriaIteradora2 = iter2.next();
                                                                if (categoriaIteradora2.getNombre().equals(splitNodos[1])) {
                                                                    nuevaCategoria.setId2(categoriaIteradora2.getId2());
                                                                }
                                                                
                                                                Iterator<Categoria> ite3 = lista3.iterator(); 
                                                                while (ite3.hasNext()) {  
                                                                    Categoria categoriaIteradora3 = ite3.next();
                                                                    if (categoriaIteradora3.getNombre().equals(splitNodos[2])) {
                                                                        nuevaCategoria.setId3(categoriaIteradora3.getId3());
                                                                    }
                                                                } 
                                                            }
                                                    }
                                                   insertar = new InsertarCategoria(nuevaCategoria);
                                                   try {
                                                        insertar.insertaCategoria(4);
                                                   } catch (Exception ex) {
                                                        Logger.getLogger(CategoriasSelectorFrame.class.getName()).log(Level.SEVERE, null, ex);
                                                   }  

                                              }
                                     }
                                     });
           }
   
   

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CategoriasSelectorFrame();
            }
        });
    }       
}

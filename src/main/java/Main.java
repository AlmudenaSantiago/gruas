
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MainFrame;
import view.fichaCliente.FichaCliente;




public class Main {

    public static void main(String args[]) throws IOException {
        //MainFrame mf = MainFrame.getInstance();
        //mf.setVisible(true);
        
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FichaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FichaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FichaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FichaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
                try {
                    FichaCliente.getInstance(5).setVisible(true);
                            
                            } catch (Exception ex) {
                    Logger.getLogger(FichaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            
      
        
         
      
         
      
    }
    }


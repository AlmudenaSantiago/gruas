package view;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class FromTransferHandler extends TransferHandler{

        @Override
        public int getSourceActions(JComponent comp){
           return COPY;
        }
       
        private int column = 1;
       
        @Override
        public Transferable createTransferable(JComponent comp){
            column = MainFrame.getInstance().getPanelContenedorMedio().getTablaServicios().getSelectedColumn();
            if (column == 1){
                return null;
            }
           
            return new StringSelection((String)MainFrame.getInstance().getPanelContenedorMedio().getTablaServicios().getValueAt(NONE, column));
           
        }
        @Override
        public void exportDone(JComponent comp, Transferable trans, int action){
            if(action != MOVE){
                return;
            }
            
            MainFrame.getInstance().sincronizacion();
        }
        
    }
       



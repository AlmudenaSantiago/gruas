/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

   
 public class ToTransferHandler extends TransferHandler {
        int action;
       
        public ToTransferHandler(int action){
            this.action = action;
        }
       
        @Override
        public boolean canImport(TransferHandler.TransferSupport support){
         return true;
        }
       
        @Override
        public boolean importData(TransferHandler.TransferSupport support){
          
             
            return true;
        }
         
    }
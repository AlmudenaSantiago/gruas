/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.fichasVehiculos;



import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileCopy {
	
    FileInputStream in;
    FileOutputStream out;
    
    public FileCopy(String sourceFile, String destinationFile) {
		
			File inFile = new File(sourceFile);
			File outFile = new File(destinationFile);

                        try {
                            in = new FileInputStream(inFile);
                            out = new FileOutputStream(outFile);

                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(FileCopy.class.getName()).log(Level.SEVERE, null, ex);
                        }
			
			
		
	}
    
    
    public String copiar () {
         
        try {
            int c;
            while( (c = in.read() ) != -1)
                out.write(c);
           
            in.close();
            out.close();
            
            return "ok";
      
        } catch (IOException ex) {
            Logger.getLogger(FileCopy.class.getName()).log(Level.SEVERE, null, ex);
        }
    
      return "error";
    }

	
}
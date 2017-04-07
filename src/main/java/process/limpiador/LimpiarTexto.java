/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.limpiador;

/**
 *
 * @author Aaron
 */
public class LimpiarTexto {
 
    
    public String limpiar (String texto) {
        
      String textoLimpio = texto.replace("ñ","laene");
      textoLimpio = textoLimpio.replace("Ñ","LAENE");
      textoLimpio = textoLimpio.replace("Á","A");
      textoLimpio = textoLimpio.replace("É","E");
      textoLimpio = textoLimpio.replace("Í","I");
      textoLimpio = textoLimpio.replace("Ó","O");
      textoLimpio = textoLimpio.replace("Ú","U");
      
      textoLimpio = textoLimpio.replace("á","acontilde");
      textoLimpio = textoLimpio.replace("é","econtilde");
      textoLimpio = textoLimpio.replace("í","icontilde");
      textoLimpio = textoLimpio.replace("ó","ocontilde");
      textoLimpio = textoLimpio.replace("ú","ucontilde");
      textoLimpio = textoLimpio.replace("ª", "simboloa");
      textoLimpio = textoLimpio.replace("º", "simbolonumero");
      textoLimpio = textoLimpio.replace("/","simbolobarra");
      System.out.println(textoLimpio);
      return textoLimpio;
    } 
    
    
    
    
    
    
    
    
}

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
        
      String textoLimpio = texto.replace("�","laene");
      textoLimpio = textoLimpio.replace("�","LAENE");
      textoLimpio = textoLimpio.replace("�","A");
      textoLimpio = textoLimpio.replace("�","E");
      textoLimpio = textoLimpio.replace("�","I");
      textoLimpio = textoLimpio.replace("�","O");
      textoLimpio = textoLimpio.replace("�","U");
      
      textoLimpio = textoLimpio.replace("�","acontilde");
      textoLimpio = textoLimpio.replace("�","econtilde");
      textoLimpio = textoLimpio.replace("�","icontilde");
      textoLimpio = textoLimpio.replace("�","ocontilde");
      textoLimpio = textoLimpio.replace("�","ucontilde");
      textoLimpio = textoLimpio.replace("�", "simboloa");
      textoLimpio = textoLimpio.replace("�", "simbolonumero");
      textoLimpio = textoLimpio.replace("/","simbolobarra");
      System.out.println(textoLimpio);
      return textoLimpio;
    } 
    
    
    
    
    
    
    
    
}

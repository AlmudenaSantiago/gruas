/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process.CRUDGruas;

import command.gruas.CargarGruasCommand;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JOptionPane;

import model.gruas.Grua;
import process.CRUDProductos.ConexionPost;
import process.CRUD.Modificar;
import process.cargador.gruas.CargadorListaGruas;
import process.parser.gruas.GruasParserJson;




public class ModificarGrua extends Modificar {
    
    Grua grua;
    String base = "http://localhost/gruas/gruas/api/";
    URL urlGrua;
    String stringRespuestaPOST;
       
   
    public ModificarGrua(Integer id, String var, String valor) {
        super(id,var,valor);
    }
    
    public void modificarGrua () throws IOException {
            preparar();
            enviarModificacion();
          
    }
    
    public void preparar() throws MalformedURLException {
        grua = new Grua();
        grua.setId(id);
        
        switch (variableAModificar) {
            
                          case "numGrua":   
                                grua.setNumGrua(Integer.parseInt(valor));
                                urlGrua = new URL(base + "modificarNumGrua");
                                break;
            
                           case "tipo":   
                                grua.setTipo(valor);
                                urlGrua = new URL(base + "modificarTipoGrua");
                                break;
            
                            case "base":   
                               grua.setBase(Integer.parseInt(valor));
                               urlGrua = new URL(base + "modificarBaseGrua");
                                break;
                                
                            case "conductor":  
                                grua.setConductor(valor);
                                urlGrua = new URL(base + "modificarConductorGrua");
                                break;
                                
                            case "demora":
                                 grua.setDemora(valor);
                                urlGrua = new URL(base + "modificarDemoraGrua");
                                break;
                                
                            case "estado":
                                 grua.setEstado(valor);
                                 urlGrua = new URL(base  + "modificarEstadoGrua");
                                break;
                                
                            case "horario":
                                grua.setHorario(valor);
                                urlGrua= new URL(base  + "modificarHorarioGrua");
                                break;
                             
                            case "km":
                                grua.setKm(Double.parseDouble(valor));
                                urlGrua= new URL(base  + "modificarKmGrua");
                                break;
                            
                            case "src":
                                grua.setSrc(valor);
                                urlGrua= new URL(base  + "modificarSrcGrua");
                                break;
                                
                            case "mensaje":
                                grua.setMensaje(valor);
                                urlGrua= new URL(base  + "modificarMensajeGrua");
                                break; 
                                
                            case "rotulado":
                                grua.setRotulado(valor);
                                urlGrua= new URL(base  + "modificarRotuladoGrua");
                                break; 
                      
                    }
                 
    }
    
    public void enviarModificacion() throws IOException {
        System.out.println("envio"  + new GruasParserJson().devuelveJsonDeGrua(grua));
        stringRespuestaPOST = new ConexionPost(new GruasParserJson().devuelveJsonDeGrua(grua),urlGrua).conectar();
        if (stringRespuestaPOST == null) JOptionPane.showMessageDialog(null,"No se ha podido modificar la grua.");
    
    }
    
   

}

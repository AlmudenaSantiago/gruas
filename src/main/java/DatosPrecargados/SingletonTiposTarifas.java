/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosPrecargados;

import command.tarifas.CargarTiposDeTarifaCommand;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tarifas.TipoDeTarifa;
import process.cargador.tarifas.CargadorListaTiposDeTarifa;
import process.parser.tipoDeTarifas.TipoDeTarifaParserJson;

/**
 *
 * @author gruasjoseantonio
 */
public final class SingletonTiposTarifas {

    
    private static SingletonTiposTarifas instance;
    CargarTiposDeTarifaCommand cargar;
    List<TipoDeTarifa> lista;
    
    private SingletonTiposTarifas() throws IOException {
       actualizarLista();
    }
    
    public void actualizarLista() {
        try {
            cargar = new CargarTiposDeTarifaCommand(new CargadorListaTiposDeTarifa(), new TipoDeTarifaParserJson());
            lista = cargar.executeLista();
        } catch (IOException ex) {
            Logger.getLogger(SingletonTiposTarifas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public List<TipoDeTarifa> getLista() {
        return lista;
    }
    
    public static SingletonTiposTarifas getInstance() {
        try {
            if (instance == null)
                instance = new SingletonTiposTarifas();
           
        } catch (IOException ex) {
            Logger.getLogger(SingletonTiposTarifas.class.getName()).log(Level.SEVERE, null, ex);
        }
         return instance;
    }
            
    
    
             
    
    
}

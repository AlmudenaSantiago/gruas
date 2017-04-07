/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatosPrecargados;

import command.basesOperativas.CargarBasesOperativasCommand;
import command.tarifas.CargarTiposDeTarifaCommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Tarifas.TipoDeTarifa;
import model.basesOperativas.BaseOperativa;
import process.cargador.CargadorListaBasesOperativas;
import process.cargador.tarifas.CargadorListaTiposDeTarifa;
import process.parser.basesOperativas.BaseOperativaParserJson;
import process.parser.tipoDeTarifas.TipoDeTarifaParserJson;

/**
 *
 * @author gruasjoseantonio
 */
public final class SingletonBasesOperativas {

    
    private static SingletonBasesOperativas instance;
    CargarBasesOperativasCommand cargar;
    List<BaseOperativa> lista;
    HashMap<String, Integer> hash;
    
    private SingletonBasesOperativas() throws IOException {
        hash = new HashMap<>();
       actualizarLista();
    }
    
    public void actualizarLista() {
        try {
         cargar = new CargarBasesOperativasCommand(new CargadorListaBasesOperativas(), new BaseOperativaParserJson());
         lista = cargar.executeListaTodasLasBases();
         for (int it = 0; it <lista.size(); it++) {
                     hash.put(lista.get(it).getBaseOperativa(), lista.get(it).getId());
                }
        } catch (IOException ex) {
            Logger.getLogger(SingletonBasesOperativas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public List<BaseOperativa> getLista() {
        return lista;
    }
    
    public static SingletonBasesOperativas getInstance() {
        try {
            if (instance == null)
                instance = new SingletonBasesOperativas();
           
        } catch (IOException ex) {
            Logger.getLogger(SingletonBasesOperativas.class.getName()).log(Level.SEVERE, null, ex);
        }
         return instance;
    }
            
    
    
             
    
    
}

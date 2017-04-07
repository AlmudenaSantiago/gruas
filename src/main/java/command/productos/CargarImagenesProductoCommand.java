/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command.productos;

import process.cargador.CargadorDeRutaDeImagenes;

/**
 *
 * @author Aaron
 */
public class CargarImagenesProductoCommand {
    
 
  
    private final CargadorDeRutaDeImagenes cargador;
  
    
    
    public CargarImagenesProductoCommand (CargadorDeRutaDeImagenes cargador) {
        this.cargador = cargador;
       }

   
  

    public void execute(Integer idProducto){
         cargador.cargar(idProducto);
      }

   

}


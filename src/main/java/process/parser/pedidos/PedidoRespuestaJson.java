package process.parser.pedidos;

import model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoRespuestaJson {

    private List<Pedido> pedidos;
  
    public static double  contadorImporte = 0;
    public static int contadorImportePendiente = 0;
    public static double  contadorImporteConDescuento = 0;
    public static double contadorDescuentos = 0;
    
    public PedidoRespuestaJson() {
        pedidos = new ArrayList<>();
    }

    
   // HAGO ESTE DIFERENTE AL OTRO PARA QUE NO SE SUME EL CONTADORIMPORTE CON CADA SINCRONIZACION DE LOS PEDIDOS DEL MAINFRAME Y SOLO LO HAGA DE COMISIONESFRAME
    public List<Pedido> getPedidosVendedor() {
        contadorImporte = 0;
        for (Pedido pedido : pedidos) {
            contadorImporte    += pedido.getImporte();
        }
        return pedidos;
    }
    
    
        
    public List<Pedido> getPedidos() {
        contadorImporte = 0;
        contadorImportePendiente = 0;
        contadorImporteConDescuento = 0;
        contadorDescuentos = 0;
        for (Pedido pedido : pedidos) {
            contadorImporte    += pedido.getImporte();
            contadorImportePendiente += pedido.getCantidadPendiente();
            contadorImporteConDescuento  += pedido.getImporte() - Double.parseDouble(pedido.getDescuento());
            contadorDescuentos += Double.parseDouble(pedido.getDescuento());
        }
     
        System.out.println("llego aqui con" + pedidos);   
        return pedidos;
    }
}

package process.parser;

import model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoRespuestaJson {

    private List<Pedido> pedidos;

    public PedidoRespuestaJson() {
        pedidos = new ArrayList<Pedido>();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}

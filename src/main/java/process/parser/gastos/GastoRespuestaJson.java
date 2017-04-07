package process.parser.gastos;


import java.util.ArrayList;
import java.util.List;
import model.Gasto;

public class GastoRespuestaJson {

    private List<Gasto> gastos;
    public static double  contadorImporte = 0;
     
    public GastoRespuestaJson() {
        gastos = new ArrayList<Gasto>();
    }

    public List<Gasto> getGastos() {
         contadorImporte = 0;
        for (Gasto gasto: gastos) {
           contadorImporte    +=  gasto.getImporte();
     
        }
         
        return gastos;
    }
}

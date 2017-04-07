package process.cargador;


import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


import java.text.SimpleDateFormat;
import java.util.Date;

public class CargadorListaServicios {

    private static CargadorListaServicios cargadorListaServicios;
    private Date fechaDesde;
    private Date fechaHasta;
    private String estado;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    private Object response;
    
    public String cargar() throws IOException {
        return ejecutarHttpGet();
    }
     public String cargarServiciosPorEstado(String estado) throws IOException {
         this.estado = estado;
        return ejecutarHttpGetEstado();
    }
    
    public String cargarServiciosFinalizados () throws IOException {

        return ejecutarHttpGetServiciosFinalizados();
    }

    public String cargarServiciosFechas (Date fechaDesde, Date fechaHasta) throws IOException {
        this.fechaDesde = fechaDesde;
         this.fechaHasta = fechaHasta;
         
        return ejecutarHttpGetServiciosFechas();
    }
    
    public CargadorListaServicios() {
    }

    public static CargadorListaServicios getInstance() {
        if (cargadorListaServicios == null) {
            cargadorListaServicios = new CargadorListaServicios();
        }
        return cargadorListaServicios;
    }

    private String ejecutarHttpGet() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
    }
    
    
    private String ejecutarHttpGetEstado() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetEstado()).getEntity());
    }
    
    
    private String ejecutarHttpGetServiciosFinalizados() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetServiciosFinalizados()).getEntity());
    }
    
        
    private String ejecutarHttpGetServiciosFechas() throws IOException {
        return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetFechas()).getEntity());
    }
 

     private HttpGet crearHttpGetFechas() {
        String fechDesde = new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde);
        String fechHasta = new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta);
       
        String URL = "http://localhost/gruas/gruas/api/mostrarServiciosPorFecha/" + fechDesde + "/" + fechHasta ;
        HttpGet request = new HttpGet(URL);
        request.setHeader("content-type", "application/json");
        System.out.println(request);
        return request;
    }
    

     private HttpGet crearHttpGetServiciosFinalizados() {
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarServiciosFinalizados");
        request.setHeader("content-type", "application/json");
        return request;
    }

    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarServicios");
        request.setHeader("content-type", "application/json");
         return request;
    }

 private HttpGet crearHttpGetEstado() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarServiciosPorEstado/" + estado);
        request.setHeader("content-type", "application/json");
         return request;
    }


}

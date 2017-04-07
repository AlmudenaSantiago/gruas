package process.cargador;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


import java.text.SimpleDateFormat;
import java.util.Date;

public class CargadorListaPedido {

    private static CargadorListaPedido cargadorListaPedido;
    private Date fechaDesde;
    private Date fechaHasta;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    private Object response;
    private String vendedor;
    private Integer id;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    
      public String cargarPedido(Integer id) throws IOException {
        this.id = id;
        return ejecutarHttpGetPedidoPorId();
    }
    
    
    public String cargarPedidosFinalizados () {

        return ejecutarHttpGetPedidosFinalizados();
    }

    public String cargarPedidosFechas (Date fechaDesde, Date fechaHasta) {
        this.fechaDesde = fechaDesde;
         this.fechaHasta = fechaHasta;
         
         
        return ejecutarHttpGetPedidosFechas();
    }
    
    public String cargarPedidosFechasYVendedor (String vendedor,Date fechaDesde, Date fechaHasta) {
         this.fechaDesde = fechaDesde;
         this.fechaHasta = fechaHasta;
         this.vendedor = vendedor;
        return ejecutarHttpGetPedidosFechasYVendedor();
    }
    
    
    public CargadorListaPedido() {
    }

    public static CargadorListaPedido getInstance() {
        if (cargadorListaPedido == null) {
            cargadorListaPedido = new CargadorListaPedido();
        }
        return cargadorListaPedido;
    }

    
      private String ejecutarHttpGetPedidoPorId() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetPedidoPorId()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    private String ejecutarHttpGet() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
    private String ejecutarHttpGetPedidosFinalizados() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetPedidosFinalizados()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
        
    private String ejecutarHttpGetPedidosFechas() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetFechas()).getEntity());
        } catch (IOException ex) {
            System.out.println(ex);
            throw new ExceptionHttpGet();
        }
    }
 
    
     private String ejecutarHttpGetPedidosFechasYVendedor() {
        try {
            
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetFechasYVendedor()).getEntity());
        } catch (IOException ex) {
            System.out.println(ex);
            throw new ExceptionHttpGet();
        }
    }
    

     private HttpGet crearHttpGetFechas() {
        String fechDesde = new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde);
        String fechHasta = new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta);
   
        String URL = "http://localhost/gruas/gruas/api/mostrarPedidosPorFecha/" + fechDesde + "/" + fechHasta ;
       
        HttpGet request = new HttpGet(URL);
        request.setHeader("content-type", "application/json");
        return request;
    }
     
     
      private HttpGet crearHttpGetFechasYVendedor() throws UnsupportedEncodingException {
        String fechDesde = new SimpleDateFormat("yyyy-MM-dd").format(fechaDesde);
        String fechHasta = new SimpleDateFormat("yyyy-MM-dd").format(fechaHasta);
        this.vendedor = this.vendedor.replace(" ","+");
        String URL = "http://localhost/gruas/gruas/api/mostrarPedidosPorFechaYVendedor/" +  this.vendedor +"/"+ fechDesde + "/" + fechHasta ;
        System.out.println(URL);
        HttpGet request = new HttpGet(URL);
        request.setHeader("content-type", "application/json");
        return request;
    }
    

     private HttpGet crearHttpGetPedidosFinalizados() {
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarPedidosFinalizados");
        request.setHeader("content-type", "application/json");
        return request;
    }

    private HttpGet crearHttpGet() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarPedidos");
        request.setHeader("content-type", "application/json");
         return request;
    }
    
    
    private HttpGet crearHttpGetPedidoPorId() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarPedidoPorId/" + id.toString());
        request.setHeader("content-type", "application/json");
         return request;
    }





}

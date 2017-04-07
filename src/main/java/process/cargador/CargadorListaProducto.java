
    
    package process.cargador;

import exception.ExceptionHttpGet;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;

import java.util.Date;

public class CargadorListaProducto {

    private static CargadorListaProducto cargadorListaProducto;
    private Date fechaDesde;
    private Date fechaHasta;
    private String categoria1;
    private String categoria2;
    private String categoria3;
    private String categoria4;
    private Integer idProveedor;
    
    private Object response;
    
    public String cargar() {
        return ejecutarHttpGet();
    }
    
     public String cargarConFiltroProveedor(Integer idProveedor) {
         
         this.idProveedor = idProveedor;
         return ejecutarHttpGetConFiltroProveedor();
     }
    
    
    public String cargarConFiltroNivel4(String categoria1, String categoria2, String categoria3, String categoria4) {
       this.categoria1= categoria1;
       this.categoria2= categoria2;
       this.categoria3= categoria3;
       this.categoria4= categoria4;
       
       return ejecutarHttpGetConFiltroNivel4();
    }
    
      public String cargarStock() {
        return ejecutarHttpGetStock();
    }
    
     public String cargarConFiltroNivel1(String categoria1) {
       this.categoria1= categoria1;
       return ejecutarHttpGetConFiltroNivel1();
    }

      public String cargarConFiltroNivel2(String categoria1, String categoria2) {
       this.categoria1= categoria1;
       this.categoria2= categoria2;
       return ejecutarHttpGetConFiltroNivel2();
    }
      
      public String cargarConFiltroNivel3(String categoria1, String categoria2, String categoria3) {
       this.categoria1= categoria1;
       this.categoria2= categoria2;
       this.categoria3= categoria3;
       return ejecutarHttpGetConFiltroNivel3();
    }

     
     
     
    
    public CargadorListaProducto() {
    }

    public static CargadorListaProducto getInstance() {
        if (cargadorListaProducto == null) {
            cargadorListaProducto = new CargadorListaProducto();
        }
        return cargadorListaProducto;
    }

    
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                              //  EJECUTAR HTTTP GET //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
     private String ejecutarHttpGet() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGet()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
    private String ejecutarHttpGetConFiltroProveedor() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetConFiltroProveedor()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
     private String ejecutarHttpGetConFiltroNivel4() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetConFiltro()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
      private String ejecutarHttpGetConFiltroNivel1() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetConFiltroNivel1()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
      private String ejecutarHttpGetConFiltroNivel2() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetConFiltroNivel2()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
      private String ejecutarHttpGetConFiltroNivel3() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetConFiltroNivel3()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }

    
    
    
        private String ejecutarHttpGetStock() {
        try {
            return EntityUtils.toString(HttpClients.createDefault().execute(crearHttpGetStock()).getEntity());
        } catch (IOException ex) {
            throw new ExceptionHttpGet();
        }
    }
    
  

     
     
     
     
     
     
     
     
     
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                              //  HTTTP GET //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
     
     
    private HttpGet crearHttpGet() {
        System.out.println("muestra productos");
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarProductos");
        request.setHeader("content-type", "application/json");
         return request;
    }
    
     private HttpGet crearHttpGetConFiltro() {
        String URL = "http://localhost/gruas/gruas/api/mostrarProductosFiltrados/" + URLEncoder.encode(categoria1) + "/" + URLEncoder.encode(categoria2) + "/" + URLEncoder.encode(categoria3) +  "/" + URLEncoder.encode(categoria4);
    
        HttpGet request = new HttpGet(URL);
        request.setHeader("content-type", "application/json");
         return request;
    }
     
      private HttpGet crearHttpGetConFiltroNivel1() {
        String URL = "http://localhost/gruas/gruas/api/mostrarProductosFiltradosNivel1/" + URLEncoder.encode(categoria1);
  
        HttpGet request = new HttpGet(URL);
        request.setHeader("content-type", "application/json");
         return request;
    }
     
       private HttpGet crearHttpGetConFiltroNivel2() {
        String URL = "http://localhost/gruas/gruas/api/mostrarProductosFiltradosNivel2/" + URLEncoder.encode(categoria1) + "/" + URLEncoder.encode(categoria2);
       
        HttpGet request = new HttpGet(URL);
        request.setHeader("content-type", "application/json");
         return request;
    }
       
       private HttpGet crearHttpGetConFiltroNivel3() {
        String URL = "http://localhost/gruas/gruas/api/mostrarProductosFiltradosNivel3/" + URLEncoder.encode(categoria1) + "/" + URLEncoder.encode(categoria2) +  "/" + URLEncoder.encode(categoria3);
     
        HttpGet request = new HttpGet(URL);
        request.setHeader("content-type", "application/json");
         return request;
    }  
     
      private HttpGet crearHttpGetStock() {
       
        HttpGet request =
                new HttpGet("http://localhost/gruas/gruas/api/mostrarStock");
        request.setHeader("content-type", "application/json");
         return request;
    }
      
       private HttpGet crearHttpGetConFiltroProveedor() {
        String URL = "http://localhost/gruas/gruas/api/mostrarProductosFiltradosPorProveedor/" + idProveedor;
     
        HttpGet request = new HttpGet(URL);
        request.setHeader("content-type", "application/json");
         return request;
    }  
  
      
      
      
      
}



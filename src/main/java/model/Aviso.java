/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author gruasjoseantonio
 */
public class Aviso {
    
    
    //********** id **********//
    
    // este es el id de la base de datos
    private Integer id;
    // este es un localizador para ellos 
    private Integer localizador;
    // este es el talon del gruista que trae con todos los datos
    private Integer talon;
    
    //********* coche ************//
    
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
   
    private Integer idMarca;
    private Integer idModelo;
    private Integer idColor;
    
    private Integer bastidor;
    private Integer kms; 
   
    
    //*********** aseguradora **********//
    private String cliente;
    private Integer idCliente;
    private String expediente;
    private Long poliza;
  

    private String base;
   
    //************** cliente *********//
    private String nombre;
    private String telefono;
  
   
    
    //*************** donde ***********//
    
    private String municipioLocalizacion;
    private String municipioDestino;
    private String direccionLocalizacion;
    private String direccionDestino;
    private Integer idDesdeBase;
    private String   desdeBaseNombre;
    
   
 
    
    
    //******* cuando *****//
    private String horaSalida;
    private String horaLocalizado;
    private String horaFinalizado;
    private Double tiempoLlegada;
    private String fechaAsignacion;
    private String fechaRealizacion;
   
    

    //***** que ocurre ******//
    private String averia;
    private Integer idAveria;
    private Integer codigoAveria;
    
    
    private Integer idSolucion;
    private String solucion;
    private Integer codigoSolucion;
  
    private String numRespuesta;
    private Integer idNumRespuesta;
    private Integer codigoNumRespuesta;
   
    
    //********* grua **********//
      
    private Integer grua;
    private Integer idGrua;
    private Integer idGruista;
    private String conductorGrua;
 
   
   //********* cuanto cobrar **********//
  
    private Integer kmsGrua;
    private Integer idServicioTipo;
    private String tipotarifa; //lo que es igual a tipo de servicio
    private Integer espera;
    private Integer rescate;
    private Integer diasCustodia;
    private String nf;
    private String plus;

    
    //************* variables para calculo del total del importe ***************//
    private Double servicioEspecial;

   
    private Double importeServicioEspecial;
    private Double igicServicioEspecial;
    private Double suplemento;
    private Double importeServicio;
    private Double baseImponible;
    private Double igic;
    private Double total;

    
    private String observaciones;
    // ________________________________________________________________//

    public Integer getTalon() {
        return talon;
    }

    public void setTalon(Integer talon) {
        this.talon = talon;
    }

    
    
    
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setCodigoSolucion(Integer codigoSolucion) {
        this.codigoSolucion = codigoSolucion;
    }

    public void setIdNumRespuesta(Integer idNumRespuesta) {
        this.idNumRespuesta = idNumRespuesta;
    }

    public void setCodigoNumRespuesta(Integer codigoNumRespuesta) {
        this.codigoNumRespuesta = codigoNumRespuesta;
    }
    
    
    
    
    
     public void setServicioEspecial(Double servicioEspecial) {
        this.servicioEspecial = servicioEspecial;
    }

    public Integer getCodigoSolucion() {
        return codigoSolucion;
    }

    public Integer getIdNumRespuesta() {
        return idNumRespuesta;
    }

    public Integer getCodigoNumRespuesta() {
        return codigoNumRespuesta;
    }

     
    public Double getServicioEspecial() {
        return servicioEspecial;
    }
    public void setImporteServicioEspecial(Double importeServicioEspecial) {
        this.importeServicioEspecial = importeServicioEspecial;
    }

    public void setIgicServicioEspecial(Double igicServicioEspecial) {
        this.igicServicioEspecial = igicServicioEspecial;
    }

    public void setSuplemento(Double suplemento) {
        this.suplemento = suplemento;
    }

    public void setImporteServicio(Double importeServicio) {
        this.importeServicio = importeServicio;
    }

    public void setBaseImponible(Double baseImponible) {
        this.baseImponible = baseImponible;
    }

    public void setIgic(Double igic) {
        this.igic = igic;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getImporteServicioEspecial() {
        return importeServicioEspecial;
    }

    public Double getIgicServicioEspecial() {
        return igicServicioEspecial;
    }

    public Double getSuplemento() {
        return suplemento;
    }

    public Double getImporteServicio() {
        return importeServicio;
    }

    public Double getBaseImponible() {
        return baseImponible;
    }

    public Double getIgic() {
        return igic;
    }

    public Double getTotal() {
        return total;
    }

    public String getDesdeBaseNombre() {
        return desdeBaseNombre;
    }

    public void setDesdeBaseNombre(String desdeBaseNombre) {
        this.desdeBaseNombre = desdeBaseNombre;
    }
    
    
    
    
    
    public Integer getIdMarca() {
        return idMarca;
    }

    public Integer getCodigoAveria() {
        return codigoAveria;
    }

    public void setCodigoAveria(Integer codigoAveria) {
        this.codigoAveria = codigoAveria;
    }
    
    
    

    public Integer getIdModelo() {
        return idModelo;
    }

    public Integer getIdColor() {
        return idColor;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public void setIdModelo(Integer idModelo) {
        this.idModelo = idModelo;
    }

    public void setIdColor(Integer idColor) {
        this.idColor = idColor;
    }

    public Integer getIdGrua() {
        return idGrua;
    }

    public void setIdGrua(Integer idGrua) {
        this.idGrua = idGrua;
    }

    public Integer getIdGruista() {
        return idGruista;
    }

    public void setIdGruista(Integer idGruista) {
        this.idGruista = idGruista;
    }
    
    
    
    

     
    
    public String getFechaRealizacion() {
        return fechaRealizacion;
    }

    public Integer getIdServicioTipo() {
        return idServicioTipo;
    }

    public String getTipotarifa() {
        return tipotarifa;
    }

    public void setFechaRealizacion(String fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public void setIdServicioTipo(Integer idServicioTipo) {
        this.idServicioTipo = idServicioTipo;
    }

    public void setTipotarifa(String tipotarifa) {
        this.tipotarifa = tipotarifa;
    }

    
    
    
    public Integer getIdAveria() {
        return idAveria;
    }

    public Integer getIdSolucion() {
        return idSolucion;
    }

    public void setIdAveria(Integer idAveria) {
        this.idAveria = idAveria;
    }

    public void setIdSolucion(Integer idSolucion) {
        this.idSolucion = idSolucion;
    }
    
    
    

    public Integer getId() {
        return id;
    }

    public Integer getLocalizador() {
        return localizador;
    }

    public Integer getBastidor() {
        return bastidor;
    }

    public Integer getIdDesdeBase() {
        return idDesdeBase;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLocalizador(Integer localizador) {
        this.localizador = localizador;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setPlus(String plus) {
        this.plus = plus;
    }
    
    

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBastidor(Integer bastidor) {
        this.bastidor = bastidor;
    }

    public void setKms(Integer kms) {
        this.kms = kms;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public void setPoliza(Long poliza) {
        this.poliza = poliza;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setMunicipioLocalizacion(String municipioLocalizacion) {
        this.municipioLocalizacion = municipioLocalizacion;
    }

    public void setMunicipioDestino(String municipioDestino) {
        this.municipioDestino = municipioDestino;
    }

    public void setDireccionLocalizacion(String direccionLocalizacion) {
        this.direccionLocalizacion = direccionLocalizacion;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public void setIdDesdeBase(Integer desdeBase) {
        this.idDesdeBase = desdeBase;
    }

   
    public void setAveria(String averia) {
        this.averia = averia;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public void setNumRespuesta(String numRespuesta) {
        this.numRespuesta = numRespuesta;
    }

    public void setGrua(Integer grua) {
        this.grua = grua;
    }

    public void setConductorGrua(String conductorGrua) {
        this.conductorGrua = conductorGrua;
    }

    public void setKmsGrua(Integer kmsGrua) {
        this.kmsGrua = kmsGrua;
    }

    public void setServicioTipo(Integer servicioTipo) {
        this.idServicioTipo = servicioTipo;
    }

    public void setEspera(Integer espera) {
        this.espera = espera;
    }

    public void setRescate(Integer rescate) {
        this.rescate = rescate;
    }

    public void setDiasCustodia(Integer diasCustodia) {
        this.diasCustodia = diasCustodia;
    }

    public void setNf(String nf) {
        this.nf = nf;
    }

    public String getPlus() {
        return plus;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getHoraLocalizado() {
        return horaLocalizado;
    }

    public String getHoraFinalizado() {
        return horaFinalizado;
    }

    public String getFechaAsignacion() {
        return fechaAsignacion;
    }

    public String getFechaRealizar() {
        return fechaRealizacion;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setHoraLocalizado(String horaLocalizado) {
        this.horaLocalizado = horaLocalizado;
    }

    public void setHoraFinalizado(String horaFinalizado) {
        this.horaFinalizado = horaFinalizado;
    }

    public void setTiempoLlegada(Double tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public void setFechaAsignacion(String fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public void setFechaRealizar(String fechaRealizar) {
        this.fechaRealizacion = fechaRealizar;
    }
    
    
    

    
    
  

    public String getCliente() {
        return cliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getExpediente() {
        return expediente;
    }

    public String getBase() {
        return base;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public Integer getKms() {
        return kms;
    }

    public Long getPoliza() {
        return poliza;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMunicipioLocalizacion() {
        return municipioLocalizacion;
    }

    public String getMunicipioDestino() {
        return municipioDestino;
    }

    public String getDireccionLocalizacion() {
        return direccionLocalizacion;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public String getConductorGrua() {
        return conductorGrua;
    }

    public Integer getGrua() {
        return grua;
    }

  

    public Double getTiempoLlegada() {
        return tiempoLlegada;
    }

    public String getAveria() {
        return averia;
    }

    public String getSolucion() {
        return solucion;
    }

    public String getNumRespuesta() {
        return numRespuesta;
    }

    public Integer getKmsGrua() {
        return kmsGrua;
    }

    public Integer getServicioTipo() {
        return idServicioTipo;
    }

    public Integer getEspera() {
        return espera;
    }

    public Integer getRescate() {
        return rescate;
    }

    public Integer getDiasCustodia() {
        return diasCustodia;
    }

    public String getNf() {
        return nf;
    }
    
    



    
    
}

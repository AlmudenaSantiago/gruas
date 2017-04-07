/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


public class Cliente {
   
    
    private String tipoCliente;
    private String grupo;
    private Integer impuesto;
    
    
    private Integer id;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private String poblacion;
    private String provincia;

  
    private Double cantidadPendiente;
    private Double cantidadAbonada;
    
    
    private String correoAvisos;
    private String telefono1;
    private String telefono2;
    private String nif;
    private String cp;
    private String fax;
    private String correo;
    
    
    private String telefonoAdmon;
    private String telefonoConsulta;
    private String telefonoAsistencia;
    private String telefonoExtra;
    
    private String emailAdmon;
    private String emailAsistencia;
    private String emailExtra;
    private String emailConsultas;
    
    private String fechaAlta;
    private String fechaBaja;
    
    private Integer tipoFacturacion;
    private String formaCobro;
    private String fechaCobro;
    private String observaciones;
    private String observacionesGenerales;
    
    
    private String fechaEntregaFra;
    private String clienteSinIGIC;
    private String pais;
    private String formaPago;
    
    private Integer minimoRetraso;

    private String imprimeKm;
    private String contacto;
    private String puestoContacto;
    private Integer numInfFinalizado;
    
    private String imagen;
    
    private Integer estado;
    
    
    private Integer idNocturno;

    public void setIdNocturno(Integer idNocturno) {
        this.idNocturno = idNocturno;
    }

    public Integer getIdNocturno() {
        return idNocturno;
    }
    private Double porcentajeSuplementoNocturno;
    private Integer muestraSuplemento;
    private Integer numDiasCoberturaEnBase;
    private Integer radioUrbano;
    private String numProveedor;
    private Double RIS;
    private Double umbral;
    
    
    
    
    
    private Integer idFestivo1;
    private Integer idFestivo2;

    public Integer getIdFestivo1() {
        return idFestivo1;
    }

    public Integer getIdFestivo2() {
        return idFestivo2;
    }

    public Integer getImpuesto() {
        return impuesto;
    }

    
    public void setIdFestivo1(Integer idFestivo1) {
        this.idFestivo1 = idFestivo1;
    }

    public void setIdFestivo2(Integer idFestivo2) {
        this.idFestivo2 = idFestivo2;
    }

    public void setImpuesto(Integer impuesto) {
        this.impuesto = impuesto;
    }
    
    

    public Double getRIS() {
        return RIS;
    }

    public Double getUmbral() {
        return umbral;
    }

    public void setRIS(Double RIS) {
        this.RIS = RIS;
    }

    public void setUmbral(Double umbral) {
        this.umbral = umbral;
    }
    
    

    public String getNumProveedor() {
        return numProveedor;
    }

    public void setNumProveedor(String numProveedor) {
        this.numProveedor = numProveedor;
    }

    
    
    public Integer getMuestraSuplemento() {
        return muestraSuplemento;
    }

    public Integer getNumDiasCoberturaEnBase() {
        return numDiasCoberturaEnBase;
    }

    public Integer getRadioUrbano() {
        return radioUrbano;
    }

    public void setMuestraSuplemento(Integer muestraSuplemento) {
        this.muestraSuplemento = muestraSuplemento;
    }

    public void setNumDiasCoberturaEnBase(Integer numDiasCoberturaEnBase) {
        this.numDiasCoberturaEnBase = numDiasCoberturaEnBase;
    }

    public void setRadioUrbano(Integer radioUrbano) {
        this.radioUrbano = radioUrbano;
    }

    public Double getPorcentajeSuplementoNocturno() {
        return porcentajeSuplementoNocturno;
    }

    public void setPorcentajeSuplementoNocturno(Double porcentajeSuplementoNocturno) {
        this.porcentajeSuplementoNocturno = porcentajeSuplementoNocturno;
    }

    public void setEmailAdmon(String emailAdmon) {
        this.emailAdmon = emailAdmon;
    }

    public void setEmailAsistencia(String emailAsistencia) {
        this.emailAsistencia = emailAsistencia;
    }

    public void setEmailExtra(String emailExtra) {
        this.emailExtra = emailExtra;
    }

    public void setEmailConsultas(String emailConsultas) {
        this.emailConsultas = emailConsultas;
    }

    public String getEmailAdmon() {
        return emailAdmon;
    }

    public String getEmailAsistencia() {
        return emailAsistencia;
    }

    public String getEmailExtra() {
        return emailExtra;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    
    
    

    public String getEmailConsultas() {
        return emailConsultas;
    }

    public String getObservacionesGenerales() {
        return observacionesGenerales;
    }

    public void setObservacionesGenerales(String observacionesGenerales) {
        this.observacionesGenerales = observacionesGenerales;
    }
    
     
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
       

    public void setMinimoRetraso(Integer minimoRetraso) {
        this.minimoRetraso = minimoRetraso;
    }

    public void setTelefonoExtra(String telefonoExtra) {
        this.telefonoExtra = telefonoExtra;
    }

    public void setImprimeKm(String imprimeKm) {
        this.imprimeKm = imprimeKm;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setPuestoContacto(String puestoContacto) {
        this.puestoContacto = puestoContacto;
    }

    public void setNumInfFinalizado(Integer numInfFinalizado) {
        this.numInfFinalizado = numInfFinalizado;
    }

    public Integer getMinimoRetraso() {
        return minimoRetraso;
    }

    public String getTelefonoExtra() {
        return telefonoExtra;
    }

    public String getImprimeKm() {
        return imprimeKm;
    }

    public String getContacto() {
        return contacto;
    }

    public String getPuestoContacto() {
        return puestoContacto;
    }

    public Integer getNumInfFinalizado() {
        return numInfFinalizado;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
 

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    
    
    
    
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setTelefonoAsistencia(String telefonoAsistencia) {
        this.telefonoAsistencia = telefonoAsistencia;
    }

    public String getTelefonoAsistencia() {
        return telefonoAsistencia;
    }
     
    
     
     
     

    public String getClienteSinIGIC() {
        return clienteSinIGIC;
    }

    public void setClienteSinIGIC(String clienteSinIGIC) {
        this.clienteSinIGIC = clienteSinIGIC;
    }
     
    
    
    

    public String getCorreoAvisos() {
        return correoAvisos;
    }

    public String getTelefonoAdmon() {
        return telefonoAdmon;
    }

    public String getTelefonoConsulta() {
        return telefonoConsulta;
    }

    public String getFax() {
        return fax;
    }

    public String getCorreoAdmon() {
        return correo;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public Integer getTipoFacturacion() {
        return tipoFacturacion;
    }

    public String getFormaCobro() {
        return formaCobro;
    }

    public String getFechaCobro() {
        return fechaCobro;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getFechaEntregaFra() {
        return fechaEntregaFra;
    }

    public void setCorreoAvisos(String correoAvisos) {
        this.correoAvisos = correoAvisos;
    }

    public void setTelefonoAdmon(String telefonoAdmon) {
        this.telefonoAdmon = telefonoAdmon;
    }

    public void setTelefonoConsulta(String telefonoConsulta) {
        this.telefonoConsulta = telefonoConsulta;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

   

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public void setTipoFacturacion(Integer tipoFacturacion) {
        this.tipoFacturacion = tipoFacturacion;
    }

    public void setFormaCobro(String formaCobro) {
        this.formaCobro = formaCobro;
    }

    public void setFechaCobro(String fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setFechaEntregaFra(String fechaEntregaFra) {
        this.fechaEntregaFra = fechaEntregaFra;
    }
    
    
    
    
           
    
    
    
    

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Double getCantidadAbonada() {
        return cantidadAbonada;
    }

    public void setCantidadAbonada(Double cantidadAbonada) {
        this.cantidadAbonada = cantidadAbonada;
    }

    
    
    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
    
        this.correo = correo;
       
    }

    
    public Double getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(Double cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }
    

    
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public String getProvincia() {
        return provincia;
    }


    public String getTelefono1() {
        return telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

 
    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }



    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

 
    
    
}
